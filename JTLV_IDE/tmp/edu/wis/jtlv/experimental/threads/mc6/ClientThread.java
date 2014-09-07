package edu.wis.jtlv.experimental.threads.mc6;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

public class ClientThread extends SplitInvThread {
	// vars that are invisible to other clients (but not to home)
	private BDDVarSet varsToQuantifyForP;// /
	// idle transition to be conjuncted with global transitions
	// obtained from other CLIENTS
	private BDD idleTransForP; // /

	public ClientThread(int _id, int _N, SMVModule _main, boolean _verbose) {
		super(_id, _N, _main, _verbose);
		// getting the client
		module = (SMVModule) Env.getModule("main.P" + _id);
		// removing "running" variable.
		module.removeRunningVar();
		trans = assignTransToDedicatedManager(module);
		extTrans = assignTransToDedicatedManager(module);
		idleTrans = adjustBDDToManager(module.idleStep());
		initCond = adjustBDDToManager(module.initial());
		oldTheta = initCond;
		newTheta = initCond;
		localVars = adjustBDDVarSetToManager(module.moduleUnprimeVars());
		vars = localVars;
		primeLocalVars = adjustBDDVarSetToManager(module.modulePrimeVars());
		primeUnprimeLocalVars = localVars.union(primeLocalVars);

		// build the shared vars:
		ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
		ModuleBDDField shar_2 = main.getVar("x_granted", false);
		sharedVars = adjustBDDVarSetToManager(shar_1.getDomain().set());
		sharedVars = sharedVars.union(adjustBDDVarSetToManager(shar_2
				.getDomain().set()));
		for (int i = 1; i < N; i++) {
			ModuleBDDField temp = main.getVar("sharer_list[" + i + "]", false);
			sharedVars = sharedVars.union(adjustBDDVarSetToManager(temp
					.getDomain().set()));
		}
		// add the shared vars to vars
		this.vars = vars.union(sharedVars);
		;

		// handle the variables that are only shared with home
		ModuleBDDField cha1_p = module.getVar("my_channel_1", false);
		ModuleBDDField cha2_p = module.getVar("my_channel_2", false);
		ModuleBDDField cha3_p = module.getVar("my_channel_3", false);

		varsToQuantifyForP = adjustBDDVarSetToManager(cha1_p.getDomain().set());
		varsToQuantifyForP = varsToQuantifyForP
				.union(adjustBDDVarSetToManager(cha2_p.getDomain().set()));
		varsToQuantifyForP = varsToQuantifyForP
				.union(adjustBDDVarSetToManager(cha3_p.getDomain().set()));
		varsToQuantifyForP = varsToQuantifyForP
				.union(adjustBDDVarSetToManager(cha1_p.prime().support()));
		varsToQuantifyForP = varsToQuantifyForP
				.union(adjustBDDVarSetToManager(cha2_p.prime().support()));
		varsToQuantifyForP = varsToQuantifyForP
				.union(adjustBDDVarSetToManager(cha3_p.prime().support()));

		vars = vars.union(adjustBDDVarSetToManager(cha1_p.getDomain().set()));
		vars = vars.union(adjustBDDVarSetToManager(cha2_p.getDomain().set()));
		vars = vars.union(adjustBDDVarSetToManager(cha3_p.getDomain().set()));
		// adding more things others should preserve
		idleTransForP = True;
		idleTransForP = idleTransForP.and(adjustBDDToManager(cha1_p.getDomain()
				.buildEquals(cha1_p.prime().getDomain())));
		idleTransForP = idleTransForP.and(adjustBDDToManager(cha2_p.getDomain()
				.buildEquals(cha2_p.prime().getDomain())));
		idleTransForP = idleTransForP.and(adjustBDDToManager(cha3_p.getDomain()
				.buildEquals(cha3_p.prime().getDomain())));
	}

	public void run() {
		int myLastSentMarkerIndex = -1;
		boolean foundNewThetas = computeNewThetas();
		boolean obtainedNewTrans;

		alive = true;
		while (alive) {
			//Thread.yield();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (foundNewThetas) {
				// System.out.println("client " + id +
				// " computeGlobalTransAndBroadcast");
				computeGlobalTransandBroadcast();
				myMarkerIndex++;
			}

			obtainedNewTrans = obtainInfoFromOtherThreads();

			if (alive) {
				foundNewThetas = false;
				if (obtainedNewTrans) {
					foundNewThetas = computeNewThetas();
					// myMarkerIndex++;
				}
				if (!foundNewThetas) {
					// didn't find new thetas, send markers
					if (myLastSentMarkerIndex != myMarkerIndex) {
						for (int i = 0; i < N; i++) {
							if (i != id) {
								// System.out.println("client " + id +
								// " send a marker " + myMarkerIndex);
								outputChannels[i].writeMarker(new Marker(id,
										myMarkerIndex));
								repliedToMyLatestMarker[i] = false;
							}
						}
						myLastSentMarkerIndex = myMarkerIndex;
					} else {
						// check if everyone replied to my latest marker
						boolean allReplied = true;
						for (int i = 0; i < N; i++) {
							if (i != id) {
								if (!repliedToMyLatestMarker[i]) {
									allReplied = false;
									break;
								}
							}
						}
						if (allReplied) {
							killAll();
						}
					}
					// reply to obtained markers from previous round
					for (int i = 0; i < N; i++) {
						if (i != id) {
							if (markersFromOthers[i] != -1) {
								outputChannels[i].writeMarker(new Marker(i,
										markersFromOthers[i]));
								// System.out.println("client " + id +
								// " replies to id " + i + " with " +
								// markersFromOthers[i]);
							}
							markersFromOthers[i] = -1;
						}
					}
				}
			}
		}
		// System.out.println("client " + id + " is waiting on semaphore");
		semMain.release(1);
		semThread.acquireUninterruptibly(1);
		// System.out.println("Bye Bye client " + id);
	}

	private void computeGlobalTransandBroadcast() {
		BDD globalTransForP;
		BDD globalTrans = computeGlobalTrans(); // this is also globalTrans for
		// home
		// the condition could be eventuall removed when we stop
		// printing the conjunction
		outputChannels[0].writeBDD(globalTrans);
		globalTransForP = globalTrans.exist(varsToQuantifyForP);
		// outputChannels[0].writeBDD(globalTrans);
		for (int i = 1; i < N; i++) {
			if (i != id)
				outputChannels[i].writeBDD(globalTransForP);
		}
	}

	private boolean obtainInfoFromOtherThreads() {
		// System.out.println("client " + id + " obtainInfoFromOtherThreads()");
		BDD othersGlobalTrans = False;
		boolean obtainedNewBdd = false;

		// the channel from home
		Object objectFromHome = otherThreads[0].outputChannels[id].read();
		while (objectFromHome != null) {
			if (objectFromHome instanceof Marker) {
				Marker markerFromHome = (Marker) objectFromHome;
				if (markerFromHome.getId() == 0)
					markersFromOthers[0] = markerFromHome.getIndex();
				if (markerFromHome.getId() == id)
					if (markerFromHome.getIndex() == myMarkerIndex) {
						repliedToMyLatestMarker[0] = true;
						// System.out.println("client " + id +
						// " got a reply from home");
					}
			}
			if (objectFromHome instanceof BDD) {
				BDD bddFromHomeAdjusted = adjustBDDToManager((BDD) objectFromHome);
				othersGlobalTrans = bddFromHomeAdjusted;
				obtainedNewBdd = true;
			}
			objectFromHome = otherThreads[0].outputChannels[id].read();
		}

		// the other channels
		BDD othersGlobalTransFromP = False;
		for (int i = 1; i < N; i++) {
			if (i != id) {
				// System.out.println("id=" + id + " is reading channel of " +
				// i);
				Object objectFromclient = otherThreads[i].outputChannels[id]
						.read();
				while (objectFromclient != null) {
					if (objectFromclient instanceof Marker) {
						Marker markerFromClient = (Marker) objectFromclient;
						if (markerFromClient.getId() == i)
							markersFromOthers[i] = markerFromClient.getIndex();
						if (markerFromClient.getId() == id)
							if (markerFromClient.getIndex() == myMarkerIndex) {
								repliedToMyLatestMarker[i] = true;
								// System.out.println("client " + id +
								// " got a reply from " + i);
							}
					}
					if (objectFromclient instanceof BDD) {
						BDD bddFromClient = adjustBDDToManager((BDD) objectFromclient);
						othersGlobalTransFromP = othersGlobalTransFromP
								.or(bddFromClient);
						obtainedNewBdd = true;
					}
					objectFromclient = otherThreads[i].outputChannels[id]
							.read();
				}
			}
		}

		// conjunct it with my idle and add to extTrans
		othersGlobalTrans = othersGlobalTrans.or(othersGlobalTransFromP
				.and(idleTransForP));
		extTrans = extTrans.or(othersGlobalTrans.and(idleTrans));
		return obtainedNewBdd;
	}

	// computes a new theta, starting from oldtheta, using extTrans
	// returns true if a new theat was found
	protected boolean computeNewThetas() {
		newTheta = newAllSucc(oldTheta, extTrans, vars);
		boolean foundNewSucc = !(newTheta.equals(oldTheta));

		oldTheta = newTheta;
		// System.out.println("module " + id + " computes a new theta -- " +
		// foundNewSucc);
		return foundNewSucc;
	}
}
