package edu.wis.jtlv.experimental.threads.mc5;

import java.util.concurrent.Semaphore;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

// ///////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////

// /////////////////// === PARALLEL === //////////////////////////////

// ///////////////// PARALLEL -- Ariel's method, sharing transitions
// /////////////////
public class ParallelMain_german extends JTLVThread {

	public SMVModule main;
	public int N;
	public int threadNum;
	public int procPerThread;
	public int lastProcPerThread;
	public boolean verbose;

	long start;
	long time1;
	long time2;

	SMVModule client;

	// == of home:
	BDDVarSet sharedVars;
	BDDVarSet primeSharedVars;
	private BDD trans;
	private BDD extTrans;
	private BDD idleTrans;
	private BDD globalTrans;
	@SuppressWarnings("unused")
	private BDD othersGlobalTrans;
	BDDVarSet localVars;
	BDDVarSet primeLocalVars;
	BDDVarSet primeUnprimeLocalVars;
	private BDD initCond;
	private BDD oldTheta;
	private BDD newTheta;
	// variables (local and shared):
	BDDVarSet vars;

	BDDVarSet[] varsHomeToQuantifyForP;
	BDDVarSet[] varsToQuantifyForP;
	BDD[] idleFromPObtainByHome;
	BDD[] idleByOtherP;

	MCThreadTrans[] threads;
	Semaphore[] sem;
	Semaphore sync;

	Channel[] outputChannel;

	BDD False;
	BDD True;

	private BDD assignTransToDedicatedManager(SMVModule m) {
		BDD res = True;
		for (BDD toConj : m.getAllConjunctTransElements()) {
			res = res.and(adjustBDDToManager(toConj));
		}
		return res;
	}

	// computing one step successors
	public BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD prime_to = from.and(trans).exist(unprimeVars);
		// System.out.println("prime_to:\n "
		// + Env.toNiceString(client, prime_to));
		return Env.unprime(prime_to);
	}

	// computing all successors
	// !! I should probably change the while-loop to a fixpoint
	public BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		} while (!oldSucc.equals(newSucc));
		return newSucc;
	}

	public BDD newAllSuccNotIncl(BDD from, BDD trans, BDDVarSet unprimeVars) {
		return newSuccNotInclWithLoopCond(-1, from, trans, unprimeVars);
	}

	public BDD newSuccNotInclWithLoopCond(int max_iter, BDD from, BDD trans,
			BDDVarSet unprimeVars) {
		// /////////////////////////
		// int counter = 0;
		// BDD oldSucc = from;
		// BDD newSucc = from;
		//
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
		// from.not());
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		int counter = 0;
		BDD deltaSucc = newSucc(from, trans, unprimeVars).and(from.not());
		BDD newSucc = deltaSucc;
		BDD oldSucc = deltaSucc;

		do {
			oldSucc = newSucc;
			deltaSucc = newSucc(deltaSucc, trans, unprimeVars).and(
					oldSucc.not()).and(from.not());
			newSucc = oldSucc.or(deltaSucc);
			counter++;
		} while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		printTimedMes("Counter " + counter, 0);
		return newSucc;
		// /////////////////////////
		// int counter = 0;
		// trans = trans.and(Env.prime(from).not());
		// BDD oldSucc, newSucc = newSucc(from, trans, unprimeVars);
		//		
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		// BDD oldSucc = from;
		// BDD newSucc = from;
		// trans = trans.and(Env.prime(from).not());
		//		
		// boolean first_time = true;
		// do {
		// oldSucc = newSucc;
		// newSucc = newSucc(oldSucc, trans, unprimeVars);
		// if (first_time) { // only newSucc
		// first_time = false;
		// } else { // otherwise, include the previous
		// newSucc = oldSucc.or(newSucc);
		// }
		// } while (!oldSucc.equals(newSucc));
		// return newSucc;
		// /////////////////////////
	}

	public BDD newSuccNotInclWithTimeCond(long max_time, BDD from, BDD trans,
			BDDVarSet unprimeVars) {
		long start = System.currentTimeMillis();
		long time_from_start = start;
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
					from.not());
			time_from_start = System.currentTimeMillis() - start;
		} while ((!oldSucc.equals(newSucc)) & (time_from_start < max_time));
		return newSucc;
	}

	// handles home + issues other threads
	public ParallelMain_german(SMVModule _main, int _N, int _threadNum,
			int _procPerThread, int _lastProcPerThread, boolean _verbose) {

		this.main = _main;
		this.N = _N;
		this.threadNum = _threadNum;
		this.procPerThread = _procPerThread;
		this.lastProcPerThread = _lastProcPerThread;
		this.verbose = _verbose;
		False = adjustBDDToManager(Env.FALSE());
		True = adjustBDDToManager(Env.TRUE());

		// System.out.println("Running the parallel algorithm");

		// getting home
		client = (SMVModule) Env.getModule("main.PHome");
		// removing "running" variable.
		client.removeRunningVar();
		trans = assignTransToDedicatedManager(client);
		extTrans = assignTransToDedicatedManager(client);
		idleTrans = adjustBDDToManager(client.idleStep());

		initCond = adjustBDDToManager(client.initial()); // .and(initM);

		this.localVars = adjustBDDVarSetToManager(client.moduleUnprimeVars());
		this.vars = localVars;
		this.primeLocalVars = adjustBDDVarSetToManager(client.modulePrimeVars());
		this.primeUnprimeLocalVars = localVars.union(primeLocalVars);

		// shared vars for home:
		// BDDVarSet sharedVars = main.moduleUnprimeVars();
		ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
		ModuleBDDField shar_2 = main.getVar("x_granted", false);
		sharedVars = adjustBDDVarSetToManager(shar_1.getDomain().set());
		sharedVars = sharedVars.union(adjustBDDVarSetToManager(shar_2
				.getDomain().set()));

		for (int i = 1; i < N; i++) {
			ModuleBDDField temp = main.getVar("sharer_list[" + i + "]", false);
			// ModuleBDDField shar_3b = main.getVar("sharer_list[2]", false);
			sharedVars = sharedVars.union(adjustBDDVarSetToManager(temp
					.getDomain().set()));
		}
		// output channels of main
		outputChannel = new Channel[N];

		// setting the vars of home
		vars = vars.union(sharedVars);
		ModuleBDDField[] h_cha1 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha2 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha3 = new ModuleBDDField[N];
		varsHomeToQuantifyForP = new BDDVarSet[N];

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

		// constructing the threads:
		time1 = System.currentTimeMillis();
		threads = new MCThreadTrans[threadNum];
		sem = new Semaphore[threadNum];
		sync = new Semaphore(0, true);

		for (int i = 1; i < threadNum; i++) {
			sem[i] = new Semaphore(0, true);
			outputChannel[i] = new Channel(this);
			threads[i] = new MCThreadTrans(i, N, main, sync, sem[i],
					outputChannel[i]);
		}
		for (int i = 1; i < threadNum; i++) {
			threads[i].setOtherThreads(threads);
		}
		time2 = System.currentTimeMillis();
		System.out.println("constructing threads took - " + (time2 - time1));
	}

	public void run() {
		// System.out.println("Starting the main thread....");
		// System.out.println("main Initiating all the threads...");

		// without resizing during the algorithm for German 5...
		trans.getFactory().setNodeTableSize(9000000);
		// without resizing during the algorithm for German 6...
		//trans.getFactory().setNodeTableSize(35000000);

		long start = System.currentTimeMillis();

		time1 = System.currentTimeMillis();
		for (int i = 1; i < threadNum; i++) {
			threads[i].start();
		}
		time2 = System.currentTimeMillis();
		System.out.println("Starting the threads took - " + (time2 - time1));

		oldTheta = initCond;
		newTheta = initCond;

		// flag indicating a fixpoint reached
		boolean foundNewThetas = true;

		sync.acquireUninterruptibly(threadNum - 1);

		if (foundNewThetas) {
			for (int i = 1; i < N; i++) {
				BDD temp = adjustBDDToManager(threads[i].getGlobalTrans());
				temp = temp.and(idleFromPObtainByHome[i]);
				extTrans = extTrans.or(temp);
			}
		}

		int counter = 0;
		do {
			long time = System.currentTimeMillis();
			counter++;
			printTimedMes("==== starting iteration " + counter + " ====", time);
			foundNewThetas = false;

			boolean simple_all_succ = false;
			if (simple_all_succ) {
				newTheta = newAllSucc(oldTheta, extTrans, vars);
				printTimedMes("after all succ", time);

				foundNewThetas = !(newTheta.equals(oldTheta));
				printTimedMes("after calculating local foundNewThetas", time);

				if (!foundNewThetas) {
					globalTrans = False;
				} else {
					globalTrans = (trans.and(newTheta).and(Env.prime(newTheta)));
				}
				printTimedMes("after global trans", time);
			} else {
				// ////////////////
				BDD deltaTheta = newAllSuccNotIncl(oldTheta, extTrans, vars);
				// ////////////////
				// BDD deltaTheta = newSuccNotInclWithLoopCond(30, oldTheta,
				// extTrans, vars);
				// ////////////////
				// BDD deltaTheta = newSuccNotInclWithTimeCond(20000, oldTheta,
				// extTrans, vars);
				// ////////////////
				// BDD deltaTheta = newAllSuccNotIncl(oldTheta, extTrans
				// .and(Env.prime(oldTheta).not()), vars);
				// ////////////////
				newTheta = oldTheta.or(deltaTheta);
				printTimedMes("after all succ", time);

				foundNewThetas = !deltaTheta.isZero();
				printTimedMes("after calculating local foundNewThetas", time);

				if (!foundNewThetas) {
					globalTrans = False;
				} else if (globalTrans == null) { // the first time
					globalTrans = (trans.and(newTheta).and(Env.prime(newTheta)));
				} else {
					globalTrans = (trans.and(deltaTheta).and(Env
							.prime(deltaTheta)));
				}
				printTimedMes("after global trans", time);
			}

			oldTheta = newTheta;

			printTimedMes("start copy", time);
			for (int i = 1; i < N; i++) {
				outputChannel[i].writeBDD(globalTrans
						.exist(varsHomeToQuantifyForP[i]));
				sem[i].release(1);
			}
			printTimedMes("end copy", time);

			sync.acquireUninterruptibly(threadNum - 1);
			for (int i = 1; i < N; i++)
				foundNewThetas = foundNewThetas | threads[i].foundNewThetas();
			printTimedMes("after calculating global foundNewThetas", time);

			if (foundNewThetas) {
				for (int i = 1; i < N; i++) {
					BDD temp = adjustBDDToManager(threads[i].getGlobalTrans());
					temp = temp.and(idleFromPObtainByHome[i]);
					extTrans = extTrans.or(temp);
				}
			}
			printTimedMes("after constructing extTrans", time);

		} while (foundNewThetas);

		long time = System.currentTimeMillis();
		System.out.println("Overall time parallel - " + (time - start));

		BDD foundStates = adjustBDDToManager(newTheta);
		for (int i = 1; i < threadNum; i++) {
			foundStates = foundStates.and(adjustBDDToManager(threads[i]
					.getNewThetaConjunct()));
		}

		// checking the property:
		// property
		SMVModule client1 = (SMVModule) Env.getModule("main.P" + 1);
		SMVModule client2 = (SMVModule) Env.getModule("main.P" + 2);
		SMVModule client3 = (SMVModule) Env.getModule("main.P" + 3);
		ModuleBDDField P1_cache = client1.getVar("cache", false);
		ModuleBDDField P2_cache = client2.getVar("cache", false);
		ModuleBDDField P3_cache = client3.getVar("cache", false);

		BDD P1_cache_exc = adjustBDDToManager(P1_cache.getDomain().ithVar(2));
		BDD P2_cache_exc = adjustBDDToManager(P2_cache.getDomain().ithVar(2));
		BDD P3_cache_exc = adjustBDDToManager(P3_cache.getDomain().ithVar(2));
		BDD P1_cache_sha = adjustBDDToManager(P1_cache.getDomain().ithVar(1));
		BDD P2_cache_sha = adjustBDDToManager(P2_cache.getDomain().ithVar(1));
		BDD P3_cache_sha = adjustBDDToManager(P3_cache.getDomain().ithVar(1));

		BDD neg_property = (P1_cache_exc.and(P2_cache_sha)).or(
				P1_cache_exc.and(P3_cache_sha)).or(
				P2_cache_exc.and(P1_cache_sha)).or(
				P2_cache_exc.and(P3_cache_sha)).or(
				P3_cache_exc.and(P1_cache_sha)).or(
				P3_cache_exc.and(P2_cache_sha));

		BDD neg_property2 = (P1_cache_exc);

		BDD is_prop_recahble = foundStates.and(neg_property);
		BDD is_prop_recahble2 = foundStates.and(neg_property2);

		if (verbose) {
			System.out.println("====== Split - inv (PAR) =====:");
			System.out.println("--Property to check:\n "
					+ Env.toNiceString(main, neg_property));
			if (is_prop_recahble.equals(Env.FALSE()))
				System.out.println("->Property is not reachable (great!!!)");
			else
				System.out.println("->Property is reachable (bad!!!)");

			System.out.println("--Property to check:\n "
					+ Env.toNiceString(main, neg_property2));
			if (is_prop_recahble2.equals(Env.FALSE()))
				System.out.println("->Property is not reachable (bad!!!)");
			else
				System.out.println("->Property is reachable (great!!!)");
		}

		for (int i = 1; i < threadNum; i++) {
			threads[i].killThread();
			sem[i].release(1);
		}
	}

	public static void printTimedMes(String header, long rel) {
		System.out.println(header + ": " + (System.currentTimeMillis() - rel));
	}
}

// ///////////////////////////////////////////////////////////////////////

// /////////////////////////////// Ariel's THREAD
// ////////////////////////////
// /////////////////////////////// Ariel's THREAD
// ////////////////////////////

