package edu.wis.jtlv.bench;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.env.spec.SpecException;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * @author ariel
 * 
 */
public class ParallelMCMain_german_v_1_2 {
	static int N;
	static int procPerThread;
	static boolean do_parallel;
	static boolean do_seq;
	static boolean do_mc;
	static boolean sanity_check;
	static boolean verbose;
	static int threadNum;
	static int lastProcPerThread;
	static SMVModule main;

	// computing one step successors
	public static BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD prime_to = from.and(trans).exist(unprimeVars);
		return Env.unprime(prime_to);
	}

	// computing all successors
	// !! I should probably change the while-loop to a fixpoint
	public static BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		} while (!oldSucc.equals(newSucc));
		return newSucc;
	}

	public static void usage(String[] args) {
		String fName = "";
		N = -1;
		procPerThread = -1;
		do_parallel = false;
		do_seq = false;
		do_mc = false;
		sanity_check = false;
		verbose = false;
		try {

			for (int i = 0; i < args.length; i++) {
				String flag = args[i];
				if (flag.equals("-N")) {
					N = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("--ProcPerThread") || flag.equals("-ppt")) {
					procPerThread = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("--doParallel") || flag.equals("-par")) {
					do_parallel = true;
					do_seq = false;
					do_mc = false;
					continue;
				}
				if (flag.equals("--doSequential") || flag.equals("-seq")) {
					do_parallel = false;
					do_seq = true;
					do_mc = false;
					continue;
				}
				if (flag.equals("--doMc") || flag.equals("-mc")) {
					do_parallel = false;
					do_seq = false;
					do_mc = true;
					continue;
				}
				if (flag.equals("--checkSanity") || flag.equals("-cs")) {
					sanity_check = true;
					continue;
				}
				if (flag.equals("--verbose") || flag.equals("-v")) {
					verbose = true;
					continue;
				}
				// if didn't read a file so far, then trying to parse as a file.
				if (fName.equals("")) {
					FileReader fr = new FileReader(flag);
					fName = flag;
					fr.close();
					continue;
				}
				throw new Exception("Error");
			}

			if ((N == -1) || (do_parallel & (procPerThread == -1))
					|| (fName.equals(""))) {
				throw new Exception("Error");
			}

		} catch (Exception e) {
			String mName = ParallelMCMain_german_v_1_2.class.getCanonicalName();
			System.err.print("Failed parsing flags : ");
			for (int i = 0; i < args.length; i++) {
				System.err.print(((i == 0) ? "" : ",") + args);
			}
			System.err.println("");
			System.err.println("Usage: java " + mName
					+ " -N <number> [OPTIONS] file.smv");
			System.err.println("\t-N <number>                               "
					+ "Number of processes.");
			System.err.println("\t-ppt <number>, --ProcPerThread <number>   "
					+ "Number of processes per thread in the multi threaded "
					+ "algorithm.");
			System.err.println("\t-par, --doParallel                        "
					+ "Do parallel algorithm. When set, flag -ppt must also "
					+ "be assigned.");
			System.err.println("\t-seq, --doSequential                      "
					+ "Do sequential algorithm. When set, flag -ppt won't be"
					+ " taken into consideration.");
			System.err.println("\t-cs, --checkSanity                        "
					+ "Do sanity check by running both algorithms, and "
					+ "checking for identical BDD result.");
			System.err.println("\t-v, --verbose                             "
					+ "Print calculation times during the fixpoint loop.");
			System.exit(1);
		}

		threadNum = N / procPerThread + ((N % procPerThread != 0) ? 1 : 0);
		lastProcPerThread = N % procPerThread;
		if (lastProcPerThread == 0)
			lastProcPerThread = procPerThread;

		System.setProperty("pool_size", "" + threadNum);
		main = loadMainModule(fName);
	}

	public static SMVModule loadMainModule(String input_file) {
		// System.setProperty("bdd", "buddy");
		Env.resetEnv();
		Env.beQuiet();
		SMVModule.initModulesWithoutRunningVar();
		try {
			System.out.print("===== Loading smv file ==========\n");
			Env.loadModule(input_file);
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
			System.exit(0);
		}

		// == the shared environment
		SMVModule main = (SMVModule) Env.getModule("main");
		// removing "running" variable.
		main.removeRunningVar();
		// initial states of M
		return main;
	}

	public static void main(String[] args) {
		usage(args);
		if (do_parallel)
			do_parallel_trans(main, N, threadNum, procPerThread,
					lastProcPerThread);
		if (do_seq)
			do_sequential_trans(main, N);
		if (do_mc)
			do_mc_trans(main, N);

	}

	// ///////////////////////////////////////////////
	// ///////////////////////////////////////////////
	// regular reachability
	// ///////////////////////////////////////////////
	// ///////////////////////////////////////////////
	public static void do_mc_trans(SMVModule main, int N) {
		long start = System.currentTimeMillis();

		SMVModule[] clients = new SMVModule[N];
		BDDVarSet allUnprimeVars = main.moduleUnprimeVars();

		BDD init = main.initial();
		// System.out.println("init:\n" + Env.toNiceString(main, init));

		// home:
		clients[0] = (SMVModule) Env.getModule("main.PHome");
		// removing "running" variable.
		clients[0].removeRunningVar();
		// processes:
		for (int i = 1; i < N; i++) {
			// getting the clients
			clients[i] = (SMVModule) Env.getModule("main.P" + i);
			// removing "running" variable.
			clients[i].removeRunningVar();
		}

		BDD[] trans = new BDD[N];
		BDD[] idle = new BDD[N];
		for (int i = 0; i < N; i++) {
			// transition relations:
			trans[i] = clients[i].trans();
			// the idle trans -- ONLY OVER LOCALS!!!
			idle[i] = clients[i].idleStep(); //		
		}

		BDD transAll = Env.FALSE();
		for (int i = 0; i < N; i++) {
			BDD tempTrans = trans[i];
			for (int j = 0; j < N; j++) {
				if (i != j) {
					tempTrans = tempTrans.and(idle[j]);
				}
			}
			transAll = transAll.or(tempTrans);
		}

		// recahble states
		BDD foundStates = newAllSucc(init, transAll, allUnprimeVars);

		long time = System.currentTimeMillis();
		System.out.println("Overall time mc - " + (time - start));

		// checking the property:
		BDD property = null;
		try {
			Spec[] all_specs = Env.loadSpecFile("./testcases/tlv_bench/german3c.spc");
			property = all_specs[0].toBDD();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SpecException e) {
			e.printStackTrace();
		}

		BDD neg_property = property.not();
		BDD is_prop_recahble = foundStates.and(neg_property);

		if (verbose) {
			System.out.println("====== Split - inv (mc) =====:");
			System.out.println("--Property to check:\n"
					+ Env.toNiceString(main, neg_property));
			if (is_prop_recahble.equals(Env.FALSE()))
				System.out.println("->Property is not reachable (great!!!)");
			else
				System.out.println("->Property is reachable (bad!!!)");
			// System.out.println("==reachable states AND p1.cache=exclusive:");
			// System.out.println(Env.toNiceString(main,
			// foundStates.and(P1_cache_exc)));
		}
	}

	// ///////////////////////////////////////////////
	// ///////////////// SEQUENTIAL -- "OLD" method, sharing transitions
	// /////////////////
	// ///////////////////////////////////////////////
	// ///////////////////////////////////////////////

	public static void do_sequential_trans(SMVModule main, int N) {
		long start = System.currentTimeMillis();

		// initial states of M
		BDD initM = main.initial();
		// shared vars:
		BDDVarSet sharedVars = main.moduleUnprimeVars();
		// all vars in the system
		BDDVarSet allVars = sharedVars;

		// == clients:
		SMVModule[] clients = new SMVModule[N];
		// transition relation:
		BDD[] trans = new BDD[N];
		// extended transition relation:
		BDD[] extTrans = new BDD[N];
		// idle trans:
		BDD[] idleTrans = new BDD[N];
		// initial conditions:
		BDD[] initCond = new BDD[N];
		// variables (local and shared):
		BDDVarSet[] vars = new BDDVarSet[N];
		// local variables only:
		BDDVarSet[] localVars = new BDDVarSet[N];
		BDDVarSet[] primeLocalVars = new BDDVarSet[N];
		BDDVarSet[] primeUnprimeLocalVars = new BDDVarSet[N];
		BDDVarSet[] unprimeLocalGlobalVars = new BDDVarSet[N];
		// local variables of other clients
		BDDVarSet[] otherLocalVars = new BDDVarSet[N];
		// Theta of each process
		BDD[] oldThetas = new BDD[N];
		BDD[] newThetas = new BDD[N];
		// preserving all the variables not of i
		BDD[] preserveOtherVars = new BDD[N];

		// getting the client 0
		clients[0] = (SMVModule) Env.getModule("main.PHome");
		// removing "running" variable.
		clients[0].removeRunningVar();

		localVars[0] = clients[0].moduleUnprimeVars();

		// THIS SHOULDN"T BE LIKE THIS!!!!!
		// it's because initM includes the local of all the clients
		initM = initM.exist(localVars[0]);

		// getting the other clients
		for (int i = 1; i < N; i++) {
			// getting the clients
			clients[i] = (SMVModule) Env.getModule("main.P" + i);
			// removing "running" variable.
			clients[i].removeRunningVar();

			localVars[i] = clients[i].moduleUnprimeVars();

			// THIS SHOULDN"T BE LIKE THIS!!!!!
			// it's because initM includes the local of all the clients
			initM = initM.exist(localVars[i]);
		}

		for (int i = 0; i < N; i++) {
			// transition relations:
			trans[i] = clients[i].trans();
			// the idle trans
			idleTrans[i] = clients[i].idleStep();
			// the initial condition of each client (including shared vars)
			initCond[i] = clients[i].initial().and(initM);
			// the idle step
			// idle[i] =
			// (clients[i].idleStep()).exist(sharedVars).exist(sharedPrimeVars);
			// the local vars:
			primeLocalVars[i] = clients[i].modulePrimeVars();
			primeUnprimeLocalVars[i] = localVars[i].union(primeLocalVars[i]);
			unprimeLocalGlobalVars[i] = main.moduleUnprimeVars().union(
					clients[i].moduleUnprimeVars());
			// the local and shared vars:
			vars[i] = localVars[i].union(sharedVars);
			// extend allVars:
			allVars = allVars.union(vars[i]);
			// initial states:
			oldThetas[i] = initCond[i];
			newThetas[i] = initCond[i];
			// initializing othersVars
			otherLocalVars[i] = Env.getEmptySet();
			// preserving all the variables not of i
			preserveOtherVars[i] = Env.TRUE();
		}

		// ///////// === FINISH initial settings

		for (int i = 0; i < N; i++) {
			extTrans[i] = trans[i];
		}

		// compute thetas initially
		for (int i = 0; i < N; i++) {
			newThetas[i] = newAllSucc(oldThetas[i], extTrans[i], vars[i]);
		}

		// flag indicating a fixpoint reached
		boolean fixPointFlag;
		BDD[] jGlobalTrans = new BDD[N];

		do {
			// getting the global transition of each process
			for (int i = 0; i < N; i++) {
				jGlobalTrans[i] = (trans[i].and(oldThetas[i]).and(Env
						.prime(newThetas[i]))).exist(primeUnprimeLocalVars[i]);
			}
			// computing the new transition of each process
			for (int i = 0; i < N; i++) {
				BDD othersGlobalTrans = Env.FALSE();
				for (int j = 0; j < N; j++) {
					if (j != i) {
						// THIS CAN BE MADE SIMPLER catching only tre NEW trans
						// IT IS IMPORTANT THAT NEXT WE USE trans and not
						// exttrans
						// since we want to expose only things that "we"
						// contribute!!!
						othersGlobalTrans = othersGlobalTrans
								.or(jGlobalTrans[j]);
					}
				}
				extTrans[i] = extTrans[i].or(othersGlobalTrans
						.and(idleTrans[i]));
			}

			fixPointFlag = true;
			for (int i = 0; i < N; i++) {
				// when done in parallel maybe this part should wait for the
				// first one...
				newThetas[i] = newAllSucc(oldThetas[i], extTrans[i], vars[i]);
				fixPointFlag = fixPointFlag
						& (newThetas[i].equals(oldThetas[i]));
				oldThetas[i] = newThetas[i];
			}
		} while (!fixPointFlag);

		BDD foundStates = Env.TRUE();
		for (int i = 0; i < N; i++) {
			foundStates = foundStates.and(newThetas[i]);
		}

		long time = System.currentTimeMillis();
		System.out.println("Overall time sequential - " + (time - start));

		// checking the property:
		BDD property = null;
		try {
			Spec[] all_specs = Env.loadSpecFile("./testcases/tlv_bench/german3c.spc");
			property = all_specs[0].toBDD();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BDD neg_property = property.not();
		BDD is_prop_recahble = foundStates.and(neg_property);


		if (verbose) {
			System.out.println("====== Split - inv (seq) =====:");
			System.out.println("--Property to check:\n "
					+ Env.toNiceString(main, neg_property));
			if (is_prop_recahble.equals(Env.FALSE()))
				System.out.println("->Property is not reachable (great!!!)");
			else
				System.out.println("->Property is reachable (bad!!!)");
			// System.out.println("==reachable states AND p1.cache=exclusive:");
			// System.out.println(Env.toNiceString(main,
			// foundStates.and(P1_cache_exc)));
		}
	}

	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////

	// /////////////////// === PARALLEL === //////////////////////////////

	// ///////////////// PARALLEL -- Ariel's method, sharing transitions
	// /////////////////

	public static void do_parallel_trans(SMVModule main, int N, int threadNum,
			int procPerThread, int lastProcPerThread) {

		System.out.println("Running the parallel algorithm");

		long start = System.currentTimeMillis();

		long time1;
		long time2;

		// initial states of M
		BDD initM = main.initial();
		// shared vars:
		BDDVarSet sharedVars = main.moduleUnprimeVars();
		BDDVarSet sharedPrimeVars = main.modulePrimeVars();
		// all vars in the system
		BDDVarSet allVars = sharedVars;

		// == clients:
		// JTLVBDDManager[] bdd_client_managers = new
		// JTLVBDDManager(10000,10000);

		SMVModule[][] clients = new SMVModule[threadNum][procPerThread];
		// transition relation:
		// //////BDD[][] trans = new BDD[threadNum][procPerThread];
		// the global trans of the client
		BDD[] globalTrans = new BDD[threadNum];
		// idle trans:
		BDD[][] idleTrans = new BDD[threadNum][procPerThread];
		// initial conditions:
		BDD[][] initCond = new BDD[threadNum][procPerThread];
		// idle step:
		BDD[][] idle = new BDD[threadNum][procPerThread];
		// variables (local and shared):
		BDDVarSet[][] vars = new BDDVarSet[threadNum][procPerThread];
		// local variables only:
		BDDVarSet[][] localVars = new BDDVarSet[threadNum][procPerThread];
		BDDVarSet[][] primeLocalVars = new BDDVarSet[threadNum][procPerThread];
		BDDVarSet[][] primeUnprimeLocalVars = new BDDVarSet[threadNum][procPerThread];
		BDDVarSet[][] unprimeLocalGlobalVars = new BDDVarSet[threadNum][procPerThread];
		// local variables of other clients
		BDDVarSet[][] otherLocalVars = new BDDVarSet[threadNum][procPerThread];
		// Theta of each process
		BDD[][] oldThetas = new BDD[threadNum][procPerThread];
		BDD[][] newThetas = new BDD[threadNum][procPerThread];
		// preserving all the variables not of i
		BDD[][] preserveOtherVars = new BDD[threadNum][procPerThread];

		// getting the clients
		clients[0][0] = (SMVModule) Env.getModule("main.PHome");
		// removing "running" variable.
		clients[0][0].removeRunningVar();

		localVars[0][0] = clients[0][0].moduleUnprimeVars();

		// THIS SHOULDN"T BE LIKE THIS!!!!!
		// it's because initM includes the local of all the clients
		initM = initM.exist(localVars[0][0]);
		
		////////////////////////////////////////////////////
		// yaniv's change...
		//// fill the first thread with more processes 
		for (int j = 1; j < procPerThread; j++) {
			// getting the clients
			clients[0][j] = (SMVModule) Env.getModule("main.P" + j);
			// removing "running" variable.
			clients[0][j].removeRunningVar();

			localVars[0][j] = clients[0][j].moduleUnprimeVars();

			// THIS SHOULDN"T BE LIKE THIS!!!!!
			// it's because initM includes the local of all the clients
			initM = initM.exist(localVars[0][j]);
		}
		////////////////////////////////////////////////////
		
		for (int i = 1; i < threadNum; i++) {
			for (int j = 0; j < procPerThread; j++) {
				// getting the clients
				clients[i][j] = (SMVModule) Env.getModule("main.P" + ((i*procPerThread) + j));
				// removing "running" variable.
				clients[i][j].removeRunningVar();

				localVars[i][j] = clients[i][j].moduleUnprimeVars();

				// THIS SHOULDN"T BE LIKE THIS!!!!!
				// it's because initM includes the local of all the clients
				initM = initM.exist(localVars[i][j]);
			}
		}

		for (int i = 0; i < threadNum; i++) {
			for (int j = 0; j < procPerThread; j++) {
				// transition relations:
				// //////trans[i][j] = clients[i][j].trans();
				// the idle trans
				idleTrans[i][j] = clients[i][j].idleStep();
				// the initial condition of each client (including shared vars)
				initCond[i][j] = clients[i][j].initial().and(initM);
				// the idle step
				idle[i][j] = (clients[i][j].idleStep()).exist(sharedVars)
						.exist(sharedPrimeVars);
				// the local vars:
				primeLocalVars[i][j] = clients[i][j].modulePrimeVars();
				primeUnprimeLocalVars[i][j] = localVars[i][j]
						.union(primeLocalVars[i][j]);
				unprimeLocalGlobalVars[i][j] = main.moduleUnprimeVars().union(
						clients[i][j].moduleUnprimeVars());
				// the local and shared vars:
				vars[i][j] = localVars[i][j].union(sharedVars);
				// extend allVars:
				allVars = allVars.union(vars[i][j]);
				// initial states:
				oldThetas[i][j] = initCond[i][j];
				newThetas[i][j] = initCond[i][j];
				// initializing othersVars
				otherLocalVars[i][j] = Env.getEmptySet();
				// preserving all the variables not of i
				preserveOtherVars[i][j] = Env.TRUE();
			}
		}

		// ///////// === FINISH initial settings

		time1 = System.currentTimeMillis();
		MCThreadTrans[] threads = new MCThreadTrans[threadNum];

		// int permits = N;
		Semaphore[] sem = new Semaphore[threadNum];
		Semaphore sync = new Semaphore(0, true);

		System.out.println("About to construct the threads... There are "
				+ threadNum + " threads");

		for (int i = threadNum - 1; i >= 0; i--) {
		//for (int i = 0; i < threadNum; i++) {
			sem[i] = new Semaphore(0, true);
			// threads[i] = new MCThreadTrans(i, procPerThread, trans[i],
			threads[i] = new MCThreadTrans(i, procPerThread, clients[i],
					idleTrans[i], unprimeLocalGlobalVars[i],
					primeUnprimeLocalVars[i], initCond[i], sync, sem[i]);
			// threads[i].start();
		}
		time2 = System.currentTimeMillis();
		System.out.println("constructing time - " + (time2 - time1));

		time1 = System.currentTimeMillis();
		for (int i = 0; i < threadNum; i++) {
			threads[i].start();
		}
		time2 = System.currentTimeMillis();
		System.out.println("Starting the threads time - " + (time2 - time1));

		// wait until semaphor is N
		sync.acquireUninterruptibly(threadNum);

		// flag indicating a fixpoint reached
		boolean foundNewThetas;

		int counter = 0;

		do {
			counter++;
			System.out.println("counter=" + counter);
			// get the threads global transitions
			for (int i = 0; i < threadNum; i++) {
				globalTrans[i] = threads[i].getGlobalTrans();
			}

			// give each threads disj of global transitions of others
			for (int i = 0; i < threadNum; i++) {
				BDD othersGlobalTrans = Env.FALSE();
				for (int j = 0; j < threadNum; j++) {
					if (j != i) {
						othersGlobalTrans = othersGlobalTrans
								.or(globalTrans[j]);
					}
				}
				threads[i].setOthersGlobalTrans(othersGlobalTrans);
				// let i continue
				sem[i].release(1);
			}

			// wait until semaphor is N
			sync.acquireUninterruptibly(threadNum);

			foundNewThetas = false;
			for (int i = 0; i < threadNum; i++) {
				foundNewThetas = foundNewThetas | threads[i].foundNewThetas();
			}
		} while (foundNewThetas);

		long time = System.currentTimeMillis();
		System.out.println("Overall time parallel - " + (time - start));

		BDD foundStates = Env.TRUE();
		for (int i = 0; i < threadNum; i++) {
			foundStates = foundStates.and(threads[i].getNewThetaConjunct());
		}

		// checking the property:
		// property
		ModuleBDDField P1_cache = clients[1][0].getVar("cache", false);
		ModuleBDDField P2_cache = clients[2][0].getVar("cache", false);
		ModuleBDDField P3_cache = clients[3][0].getVar("cache", false);
		
		BDD P1_cache_exc = P1_cache.getDomain().ithVar(2);
		BDD P2_cache_exc = P2_cache.getDomain().ithVar(2);
		BDD P3_cache_exc = P3_cache.getDomain().ithVar(2);
		BDD P1_cache_sha = P1_cache.getDomain().ithVar(1);
		BDD P2_cache_sha = P2_cache.getDomain().ithVar(1);
		BDD P3_cache_sha = P3_cache.getDomain().ithVar(1);

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

		for (int i = 0; i < threadNum; i++) {
			threads[i].killThread();
			sem[i].release(1);
		}
	}

	// ///////////////////////////////////////////////////////////////////////

	// /////////////////////////////// Ariel's THREAD
	// ////////////////////////////
	// /////////////////////////////// Ariel's THREAD
	// ////////////////////////////

	static class MCThreadTrans extends JTLVThread {
		private BDDVarSet[] unprimeLocalGlobalVars;
		private BDDVarSet[] primeUnprimeLocalVars;
		private BDD trans[];
		private BDD extTrans[];
		private BDD idleTrans[];
		private BDD globalTrans;
		@SuppressWarnings("unused")
		private BDD previousGlobalTrans;
		private BDD othersGlobalTrans;
		@SuppressWarnings("unused")
		private BDD initCond[];
		private BDD oldTheta[];
		private BDD newTheta[];
		private boolean foundNewThetas;
		private boolean alive;
		private Semaphore sync;
		private Semaphore sem;
		@SuppressWarnings("unused")
		private int id;
		private int procPerThread;

		// ASK YANIV:: is there adjustSMVModuleToManager ???
		// this will save all the initial passing

		public MCThreadTrans(int _id,
				int _procPerThread,
				SMVModule[] clients, // BDD[] _trans,
				BDD[] _idleStep, BDDVarSet[] _unprimeLocalGlobalVars,
				BDDVarSet[] _primeUnprimeLocalVars, BDD[] _initCond,
				Semaphore _sync, Semaphore _sem) {
			this.id = _id;
			this.procPerThread = _procPerThread;
			this.unprimeLocalGlobalVars = adjustBDDVarSetToManager(_unprimeLocalGlobalVars);
			this.primeUnprimeLocalVars = adjustBDDVarSetToManager(_primeUnprimeLocalVars);
			this.idleTrans = adjustBDDToManager(_idleStep);
			this.oldTheta = adjustBDDToManager(_initCond);
			this.newTheta = new BDD[_initCond.length];
			this.sync = _sync;
			this.sem = _sem;
			// extTrans = trans;
			this.extTrans = new BDD[clients.length];
			alive = true;
			othersGlobalTrans = adjustBDDToManager(Env.FALSE());
			previousGlobalTrans = adjustBDDToManager(Env.FALSE());

			// this.trans = adjustBDDToManager(_trans);
			trans = new BDD[clients.length];
			for (int i = 0; i < clients.length; i++) {
				trans[i] = assignTransToDedicatedManager(clients[i]);
			}
			for (int i = 0; i < procPerThread; i++) {
				extTrans[i] = trans[i];
				newTheta[i] = oldTheta[i];
			}
			globalTrans = adjustBDDToManager(Env.FALSE());
			System.out.println("done with thread...");
		}

		private BDD assignTransToDedicatedManager(SMVModule m) {
			BDD res = adjustBDDToManager(Env.TRUE());
			for (BDD toConj : m.getAllConjunctTransElements()) {
				res = res.and(adjustBDDToManager(toConj));
			}
			return res;
		}

		public void run() {
			computeNewThetas();
			while (alive) {
				if (foundNewThetas)
					computeGlobalTrans();
				sync.release(1);
				sem.acquireUninterruptibly(1);
				// MUST BE THAT SOMEONE did getGlobalTrans()
				// and then setOthersGlobalTrans(BDD _othersGlobalTrans)
				if (alive)
					computeNewThetas();
			}
		}

		public void computeGlobalTrans() {
			globalTrans = adjustBDDToManager(Env.FALSE());
			for (int i = 0; i < procPerThread; i++) {
				globalTrans = globalTrans.or((trans[i].and(oldTheta[i]).and(Env
						.prime(newTheta[i]))).exist(primeUnprimeLocalVars[i]));
			}
			// avoiding giving again same transitions
			// globalTrans = globalTrans.and(previousGlobalTrans.not());
			// previousGlobalTrans = previousGlobalTrans.or(globalTrans);
		}

		public BDD getGlobalTrans() {
			// System.out.println("get from id =" + id);
			return adjustBDDToBase(globalTrans);
		}

		public void setOthersGlobalTrans(BDD _othersGlobalTrans) {
			this.othersGlobalTrans = adjustBDDToManager(_othersGlobalTrans);
			for (int i = 0; i < procPerThread; i++) {
				extTrans[i] = extTrans[i].or(othersGlobalTrans
						.and(idleTrans[i]));
			}
		}

		// Important: computing the new thetas does allow us to take advantage
		// that we are familiar with ALL local thetas for this thread
		public void computeNewThetas() {
			// flag indicating a fixpoint reached
			boolean fixPointFlag;
			foundNewThetas = false;

			BDD[] jGlobalTrans = new BDD[N];

			do {

				fixPointFlag = true;
				for (int i = 0; i < procPerThread; i++) {
					newTheta[i] = newAllSucc(oldTheta[i], extTrans[i],
							unprimeLocalGlobalVars[i]);
					fixPointFlag = fixPointFlag
							& (newTheta[i].equals(oldTheta[i]));
					foundNewThetas = foundNewThetas
							| (!newTheta[i].equals(oldTheta[i]));
					oldTheta[i] = newTheta[i];
				}

				// we use the new thetas to compute further localy
				if (!fixPointFlag) {
					for (int i = 0; i < procPerThread; i++) {
						// compute the shared trans
						jGlobalTrans[i] = (trans[i].and(oldTheta[i]).and(Env
								.prime(newTheta[i])))
								.exist(primeUnprimeLocalVars[i]);
					}

					// computing the new transition of each process
					for (int i = 0; i < procPerThread; i++) {
						BDD localOthersGlobalTrans = adjustBDDToManager(Env
								.FALSE());
						for (int j = 0; j < procPerThread; j++) {
							if (j != i) {
								localOthersGlobalTrans = localOthersGlobalTrans
										.or(jGlobalTrans[j]);
							}
						}
						extTrans[i] = extTrans[i].or(localOthersGlobalTrans
								.and(idleTrans[i]));
					}
				}
			} while (!fixPointFlag);
			// System.out.println("for id =" + id + " foundNewThetas=" +
			// foundNewThetas);
		}

		public BDD getNewThetaConjunct() {
			BDD newThetasConj = adjustBDDToManager(Env.TRUE());
			for (int i = 0; i < procPerThread; i++) {
				newThetasConj = newThetasConj.and(newTheta[i]);
			}
			// return globalTrans;
			return adjustBDDToBase(newThetasConj);
		}

		public boolean foundNewThetas() {
			return foundNewThetas;
		}

		public void killThread() {
			alive = false;
		}
	}
}
