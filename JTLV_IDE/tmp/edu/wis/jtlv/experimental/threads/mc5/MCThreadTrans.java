package edu.wis.jtlv.experimental.threads.mc5;

import java.util.concurrent.Semaphore;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

	public class MCThreadTrans extends JTLVThread {
		private SMVModule main;
		private SMVModule client;
		private BDDVarSet sharedVars;
		//all vars known to this thread
		private BDDVarSet vars;
		private BDDVarSet varsToQuantifyForP;
		private BDD trans;
		private BDD extTrans;
		private BDD idleTrans;	
		private BDD idleTransForP;	
		private BDD globalTrans;
		private BDD othersGlobalTrans;
		private BDD initCond;
		private BDD oldTheta;
		private BDD newTheta;
		private boolean foundNewThetas;
		private boolean alive;
		private Semaphore sync;
		private Semaphore sem;
		private int id;
		private int N;
		private BDD False;
		private BDD True;
		BDDVarSet localVars;
		BDDVarSet primeLocalVars;
		BDDVarSet primeUnprimeLocalVars;
		public Channel inputChannelFromHome;
		private MCThreadTrans[] otherThreads;
		public Channel[] outputChannels;

		// ASK YANIV:: is there adjustSMVModuleToManager ???
		// this will save all the initial passing

		public MCThreadTrans(int _id, int _N, SMVModule _main, //BDD _idleStep,
							 //BDDVarSet _sharedVars,
							 //BDDVarSet _vars, //BDD _initCond, 
							 Semaphore _sync, 
							 Semaphore _sem, 
							 Channel _inputChannelFromHome) {
//System.out.println("constructor of " + _id);
			this.main = _main;
			False = adjustBDDToManager(Env.FALSE());
			True = adjustBDDToManager(Env.TRUE());
			globalTrans = False;
			othersGlobalTrans = False;
			
			// getting the client
			client = (SMVModule) Env.getModule("main.P" + _id);
				// removing "running" variable.
			client.removeRunningVar();
		
			
			trans = assignTransToDedicatedManager(client);
			extTrans = assignTransToDedicatedManager(client);
			idleTrans = adjustBDDToManager(client.idleStep());
			//System.out.println("main in pre-step 5b i=" + i);
						// the initial condition of each client (including shared vars)
			initCond = adjustBDDToManager(client.initial()); // .and(initM);
			
			this.id = _id;
			this.N = _N;
			//this.vars = adjustBDDVarSetToManager(_vars);
			this.oldTheta = adjustBDDToManager(initCond);
			// this.newTheta = new BDD;
			this.sync = _sync;
			this.sem = _sem;
			
			this.localVars = adjustBDDVarSetToManager(client.moduleUnprimeVars());
			this.vars = localVars;
			this.primeLocalVars = adjustBDDVarSetToManager(client.modulePrimeVars());
			this.primeUnprimeLocalVars = localVars.union(primeLocalVars);			

			// shared vars:
			ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
			ModuleBDDField shar_2 = main.getVar("x_granted", false);
			sharedVars = adjustBDDVarSetToManager(shar_1.getDomain().set());
			sharedVars = sharedVars.union(adjustBDDVarSetToManager(shar_2.getDomain().set()));
			for (int i = 1; i < N; i++) {
				ModuleBDDField temp = main.getVar("sharer_list[" + i + "]", false);
				sharedVars = sharedVars.union(adjustBDDVarSetToManager(temp.getDomain().set()));
			}	

			this.vars = vars.union(sharedVars);;
			
			// for Home each process should only q. its locals
			ModuleBDDField	cha1_p = client.getVar("my_channel_1", false);
			ModuleBDDField	cha2_p = client.getVar("my_channel_2", false);
			ModuleBDDField	cha3_p = client.getVar("my_channel_3", false);
			
			varsToQuantifyForP = adjustBDDVarSetToManager(cha1_p.getDomain().set());
			varsToQuantifyForP = varsToQuantifyForP.union(adjustBDDVarSetToManager(cha2_p.getDomain().set()));
			varsToQuantifyForP = varsToQuantifyForP.union(adjustBDDVarSetToManager(cha3_p.getDomain().set()));	
			varsToQuantifyForP = varsToQuantifyForP.union(adjustBDDVarSetToManager(cha1_p.prime().support()));
			varsToQuantifyForP = varsToQuantifyForP.union(adjustBDDVarSetToManager(cha2_p.prime().support()));
			varsToQuantifyForP = varsToQuantifyForP.union(adjustBDDVarSetToManager(cha3_p.prime().support()));
			
			vars = vars.union(adjustBDDVarSetToManager(cha1_p.getDomain().set()));
			vars = vars.union(adjustBDDVarSetToManager(cha2_p.getDomain().set()));
			vars = vars.union(adjustBDDVarSetToManager(cha3_p.getDomain().set()));
			// adding more things others should preserve
			idleTransForP = True;
			idleTransForP = idleTransForP.and(adjustBDDToManager(cha1_p.getDomain().buildEquals(cha1_p.prime().getDomain())));
			idleTransForP = idleTransForP.and(adjustBDDToManager(cha2_p.getDomain().buildEquals(cha2_p.prime().getDomain())));
			idleTransForP = idleTransForP.and(adjustBDDToManager(cha3_p.getDomain().buildEquals(cha3_p.prime().getDomain())));

			this.inputChannelFromHome = _inputChannelFromHome;
			// extTrans = trans;
			// this.extTrans = new BDD[clients.length];
			alive = true;
			// extTrans = trans;
			oldTheta = initCond;
			newTheta = initCond;
			outputChannels = new Channel[N];
			for (int i = 1; i < N; i++) {
				if(i != id)
					outputChannels[i] = new Channel(this);
			}
		}

	
		private BDD assignTransToDedicatedManager(SMVModule m) {
			BDD res = True;
			for (BDD toConj : m.getAllConjunctTransElements()) {
				res = res.and(adjustBDDToManager(toConj));
			}
			return res;
		}

		public void run() {
			computeNewThetas();
			while (alive) {
				
				BDD globalTransForP;
				if (foundNewThetas){
					//System.out.println("id=" + id + " is computing globalTrans -- NOT False");
					computeGlobalTrans(); //this is also globalTrans for home
					globalTransForP = globalTrans.exist(varsToQuantifyForP);
				}
				else{
					//System.out.println("id=" + id + " is computing globalTrans -- FALSE");					
					globalTrans = False;
					globalTransForP = False;
				}
				//writing to all other P; home takes from globalTrans
				//System.out.println("id=" + id + " is putting globalTrans on outputchannels");
				// TODO: YANIV implement parallel copy. 
				boolean do_multi_copy = true;
				if (!do_multi_copy) {
					for (int i = 1; i < N; i++) {
						if(i != id)
							outputChannels[i].writeBDD(globalTransForP);
					}
				} else {
					int[] all_managers_index = new int [N - 2];
					for (int i = 1; i < N; i++) {
						if(i < id)
							all_managers_index[i - 1] = outputChannels[i].getManagerIndex();
						else if (i > id) 
							all_managers_index[i - 2] = outputChannels[i].getManagerIndex();						
					}
					BDD[] all_globalTransForP = Patch1_2_0_copyToManyFactories
								.multiCopyBDD(all_managers_index, globalTransForP);
				
					for (int i = 1; i < N; i++) {
						if(i < id)
							outputChannels[i].writeBDDWithoutCopy(all_globalTransForP[i - 1]);
						else if (i > id) 
							outputChannels[i].writeBDDWithoutCopy(all_globalTransForP[i - 2]);
					}
				}

				//next letting main run 
				sync.release(1);

				//waiting
				sem.acquireUninterruptibly(1);

				//get global trans from other threads
				obtainGlobalTrans();
				if (alive)
					computeNewThetas();
			}
		}

		public void computeGlobalTrans() {
			globalTrans = (trans.and(newTheta).and(Env.prime(newTheta)))
							.exist(primeUnprimeLocalVars);
		}

		public BDD getGlobalTrans() {
			//return adjustBDDToManager(globalTrans);
			return globalTrans;
		}
		

		private void obtainGlobalTrans(){
			//System.out.println("id=" + id + " is reading from channels");

			othersGlobalTrans = False;
			BDD fromHome = inputChannelFromHome.read();
			if(fromHome != null){
				BDD fromHomeAdjusted = adjustBDDToManager(fromHome);
				othersGlobalTrans = fromHomeAdjusted;
			}
			
			BDD othersGlobalTransFromP = False;
			for (int i = 1; i < N; i++) {
				if(i != id){
					//System.out.println("id=" + id + " is reading channel of " + i);
					BDD temp = otherThreads[i].outputChannels[id].read();
					if(temp != null){
						BDD tempLocal = adjustBDDToManager(temp);
						othersGlobalTransFromP = othersGlobalTransFromP.or(tempLocal);
					}
				}
			}
			othersGlobalTrans = othersGlobalTrans.or(othersGlobalTransFromP.and(idleTransForP));
			extTrans = extTrans.or(othersGlobalTrans.and(idleTrans));
		}
		
		
		public void computeNewThetas() {
			newTheta = newAllSucc(oldTheta, extTrans, vars);
			foundNewThetas = !(newTheta.equals(oldTheta));
			//System.out.println("id=" + id + " foundNewThetas is " + foundNewThetas);
			oldTheta = newTheta;
		}

		public void setOtherThreads(MCThreadTrans[] _otherThreads){
			otherThreads = _otherThreads;
		}
		
		public BDD getNewThetaConjunct() {
			return newTheta;
		}

		public boolean foundNewThetas() {
			return foundNewThetas;
		}

		public void killThread() {
			alive = false;
		}
		
		// computing one step successors
		public BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
			BDD prime_to = from.and(trans).exist(unprimeVars);
			return Env.unprime(prime_to);
		}

		// computing all successors
		public BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
			BDD oldSucc = from;
			BDD newSucc = from;
			do {
				oldSucc = newSucc;
				newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
			} while (!oldSucc.equals(newSucc));
			return newSucc;
		}		
	}//end of MCThreadTrans
	
