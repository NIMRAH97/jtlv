package edu.wis.jtlv.experimental.threads.mc6;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

public class HomeThread extends SplitInvThread {
	// vars home should quantify when sending to client[i]
	private BDDVarSet[] varsHomeToQuantifyForP;
	// the idle transition to conjunct with trans obtained from client[i]
	BDD[] idleFromPObtainByHome;

	public HomeThread(int _id, int _N, SMVModule _main, boolean _verbose) {
		super(_id, _N, _main, _verbose);

		// getting the module of main
		module = (SMVModule) Env.getModule("main.PHome");
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

		// construct the shared vars:
		ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
		ModuleBDDField shar_2 = main.getVar("x_granted", false);
		sharedVars = adjustBDDVarSetToManager(shar_1.getDomain().set());
		sharedVars = sharedVars.union(adjustBDDVarSetToManager(shar_2
				.getDomain().set()));

		for (int i = 0; i < N; i++) {
			if (i != id) {
				ModuleBDDField temp = main.getVar("sharer_list[" + i + "]",
						false);
				// ModuleBDDField shar_3b = main.getVar("sharer_list[2]",
				// false);
				sharedVars = sharedVars.union(adjustBDDVarSetToManager(temp
						.getDomain().set()));
			}
		}
		// adding the shared vars to vars
		vars = vars.union(sharedVars);

		ModuleBDDField[] h_cha1 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha2 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha3 = new ModuleBDDField[N];
		varsHomeToQuantifyForP = new BDDVarSet[N];

		// handle the variables that are shared with distinct clients
		BDDVarSet[] local_h_cha1 = new BDDVarSet[N];
		BDDVarSet[] local_h_cha2 = new BDDVarSet[N];
		BDDVarSet[] local_h_cha3 = new BDDVarSet[N];
		BDDVarSet[] local_h_prime_cha1 = new BDDVarSet[N];
		BDDVarSet[] local_h_prime_cha2 = new BDDVarSet[N];
		BDDVarSet[] local_h_prime_cha3 = new BDDVarSet[N];

		for (int i = 1; i < N; i++) {
			h_cha1[i] = main.getVar("channel_1[" + i + "]", false);
			h_cha2[i] = main.getVar("channel_2[" + i + "]", false);
			h_cha3[i] = main.getVar("channel_3[" + i + "]", false);
		}
		for (int i = 1; i < N; i++) {
			local_h_cha1[i] = adjustBDDVarSetToManager(h_cha1[i].getDomain()
					.set());
			local_h_cha2[i] = adjustBDDVarSetToManager(h_cha2[i].getDomain()
					.set());
			local_h_cha3[i] = adjustBDDVarSetToManager(h_cha3[i].getDomain()
					.set());
			vars = vars.union(local_h_cha1[i]);
			vars = vars.union(local_h_cha2[i]);
			vars = vars.union(local_h_cha3[i]);
			local_h_prime_cha1[i] = adjustBDDVarSetToManager(h_cha1[i].prime()
					.support());
			local_h_prime_cha2[i] = adjustBDDVarSetToManager(h_cha2[i].prime()
					.support());
			local_h_prime_cha3[i] = adjustBDDVarSetToManager(h_cha3[i].prime()
					.support());
		}
		idleFromPObtainByHome = new BDD[N];
		for (int i = 1; i < N; i++) {
			varsHomeToQuantifyForP[i] = primeUnprimeLocalVars;
			idleFromPObtainByHome[i] = idleTrans;
			for (int j = 1; j < N; j++) {
				if (j != i) {
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_cha1[j]);
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_cha2[j]);
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_cha3[j]);
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_prime_cha1[j]);
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_prime_cha2[j]);
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(local_h_prime_cha3[j]);

					BDD temp1 = adjustBDDToManager(h_cha1[j].getDomain()
							.buildEquals(h_cha1[j].prime().getDomain()));
					idleFromPObtainByHome[i] = idleFromPObtainByHome[i]
							.and(temp1);
					BDD temp2 = adjustBDDToManager(h_cha2[j].getDomain()
							.buildEquals(h_cha2[j].prime().getDomain()));
					idleFromPObtainByHome[i] = idleFromPObtainByHome[i]
							.and(temp2);
					BDD temp3 = adjustBDDToManager(h_cha3[j].getDomain()
							.buildEquals(h_cha3[j].prime().getDomain()));
					idleFromPObtainByHome[i] = idleFromPObtainByHome[i]
							.and(temp3);
				}
			}
		}
	}

	public void run() {
		int myLastSentMarkerIndex = -1;
		boolean foundNewThetas;
		boolean obtainedNewTrans;

		long time = System.currentTimeMillis();

		// System.out.println("main computing new thetas");
		if (!delta_theta_pref_imp) {
			newTheta = newAllSucc(oldTheta, extTrans, vars);
			foundNewThetas = !(newTheta.equals(oldTheta));
		} else {
			deltaTheta = newAllSuccNotIncl(oldTheta, extTrans, vars);
			newTheta = oldTheta.or(deltaTheta);
			printTimedMes("after the first all succ", time);
			foundNewThetas = !deltaTheta.isZero();
		}

		int counter = 0;
		alive = true;
		while (alive) {
			//Thread.yield();
			time = System.currentTimeMillis();
			counter++;
			printTimedMes("==== starting iteration " + counter + " ====", time);
			if (foundNewThetas) {
				computeGlobalTransAndBroadcast();
				myMarkerIndex++;
			}
			printTimedMes("after computeGlobalTransAnd... (globalTrans)", time);
			obtainedNewTrans = obtainInfoFromOtherThreads();
			printTimedMes("after obtainInfoFromOtherThreads (extTrans)", time);

			if (alive) {
				foundNewThetas = false;
				if (obtainedNewTrans) {
					foundNewThetas = computeNewThetas();
				}
				printTimedMes("after computeNewThetas (all succ)", time);
				if (!foundNewThetas) {
					// didn't find new thetas, send markers
					if (myLastSentMarkerIndex != myMarkerIndex) {
						// System.out.println("home sending a new marker id " +
						// myMarkerIndex);
						for (int i = 1; i < N; i++) {
							outputChannels[i].writeMarker(new Marker(0,
									myMarkerIndex));
							repliedToMyLatestMarker[i] = false;
						}
						myLastSentMarkerIndex = myMarkerIndex;
					} else {
						// check if everyone replied to my latest marker
						boolean allReplied = true;
						for (int i = 1; i < N; i++) {
							if (!repliedToMyLatestMarker[i]) {
								allReplied = false;
								break;
							}
						}
						if (allReplied) {
							killAll();
						}
					}
					// reply to obtained markers
					printTimedMes("start copy", time);
					for (int i = 1; i < N; i++) {
						if (markersFromOthers[i] != -1) {
							outputChannels[i].writeMarker(new Marker(i,
									markersFromOthers[i]));
							// System.out.println("home replies to id " + i +
							// " with " + markersFromOthers[i]);
						}
						markersFromOthers[i] = -1;
					}
					printTimedMes("end copy", time);
				}
			}
			printTimedMes("====== ended iteration " + counter + " =====", time);
		}
		// System.out.println("home is waiting on semaphore");
		semMain.release(1);
		semThread.acquireUninterruptibly(1);
		// System.out.println("Bye Bye client " + id);
	}

	public void computeGlobalTransAndBroadcast() {
		BDD globalTrans;
		if (!delta_theta_pref_imp) {
			globalTrans = (trans.and(newTheta).and(Env.prime(newTheta)));
		} else {
			globalTrans = (trans.and(deltaTheta).and(Env.prime(deltaTheta)));
		}

		for (int i = 1; i < N; i++) {
			// System.out.println("main writing globalTrans on channel " + i);
			outputChannels[i].writeBDD(globalTrans
					.exist(varsHomeToQuantifyForP[i]));
		}
	}

	private boolean obtainInfoFromOtherThreads() {
		// System.out.println("home obtainInfoFromOtherThreads()");
		boolean obtainedNewBdd = false;
		for (int i = 1; i < N; i++) {
			Object objectFromclient = otherThreads[i].outputChannels[0].read();
			while (objectFromclient != null) {
				if (objectFromclient instanceof Marker) {
					Marker markerFromClient = (Marker) objectFromclient;
					if (markerFromClient.getId() == i)
						markersFromOthers[i] = markerFromClient.getIndex();
					if (markerFromClient.getId() == 0)
						if (markerFromClient.getIndex() == myMarkerIndex) {
							repliedToMyLatestMarker[i] = true;
							// System.out.println("home got a reply from " + i);
						}
				}
				if (objectFromclient instanceof BDD) {
					BDD bddFromClient = adjustBDDToManager((BDD) objectFromclient);
					bddFromClient = bddFromClient.and(idleFromPObtainByHome[i]);
					extTrans = extTrans.or(bddFromClient);
					obtainedNewBdd = true;
				}
				objectFromclient = otherThreads[i].outputChannels[0].read();
			}
		}
		return obtainedNewBdd;
	}

	// computes a new theta, starting from oldtheta, using extTrans
	// returns true if a new theat was found
	protected boolean computeNewThetas() {
		boolean foundNewSucc;
		if (!delta_theta_pref_imp) {
			newTheta = newAllSucc(oldTheta, extTrans, vars);
			foundNewSucc = !(newTheta.equals(oldTheta));
		} else {
			deltaTheta = newAllSuccNotIncl(oldTheta, extTrans, vars);
			newTheta = oldTheta.or(deltaTheta);

			foundNewSucc = !deltaTheta.isZero();
			// printTimedMes("after calculating local foundNewThetas", time);
			//
			// if (!foundNewThetas) {
			// globalTrans = False;
			// } else if (globalTrans == null) { // the first time
			// globalTrans = (trans.and(newTheta).and(Env.prime(newTheta)));
			// } else {
			// globalTrans = (trans.and(deltaTheta).and(Env
			// .prime(deltaTheta)));
			// }
			// printTimedMes("after global trans", time);
		}

		oldTheta = newTheta;
		// System.out.println("module " + id + " computes a new theta -- " +
		// foundNewSucc);
		return foundNewSucc;
	}
}
