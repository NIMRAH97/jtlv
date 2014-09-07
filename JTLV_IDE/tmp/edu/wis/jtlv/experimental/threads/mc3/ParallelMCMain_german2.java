package edu.wis.jtlv.experimental.threads.mc3;

import java.io.FileReader;
import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * @author ariel
 * 
 */
public class ParallelMCMain_german2 {
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
			String mName = ParallelMCMain_german2.class.getCanonicalName();
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

		System.setProperty("pool_size", "" + (N * (threadNum + 1)));
		main = loadMainModule(fName);
	}

	public static SMVModule loadMainModule(String input_file) {
		// System.setProperty("bdd", "buddy");
		System.setProperty("bdd_table_size", "1000");
		System.setProperty("bdd_cache_size", "1000");
		Env.resetEnv();
		// Env.beQuiet();
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
		if (do_parallel) {
			do_parallel_trans(main, N, threadNum, procPerThread,
					lastProcPerThread);
		}
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

		ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
		ModuleBDDField shar_2 = main.getVar("x_granted", false);
		BDDVarSet sharedVars = shar_1.getDomain().set();
		sharedVars = sharedVars.union(shar_2.getDomain().set());
		for (int i = 1; i < N; i++) {
			ModuleBDDField temp = main.getVar("sharer_list[" + i + "]", false);
			// ModuleBDDField shar_3b = main.getVar("sharer_list[2]", false);
			sharedVars = sharedVars.union(temp.getDomain().set());
		}

		ModuleBDDField home_1 = clients[0].getVar("curr_command", false);
		ModuleBDDField home_2 = clients[0].getVar("curr_client", false);

		BDD[] idleOtherVars = new BDD[N];
		for (int i = 1; i < N; i++) {
			idleOtherVars[i] = Env.TRUE();
			// home's vars
			idleOtherVars[i] = idleOtherVars[i].and(shar_1.getDomain()
					.buildEquals(shar_1.prime().getDomain()));
			idleOtherVars[i] = idleOtherVars[i].and(shar_2.getDomain()
					.buildEquals(shar_2.prime().getDomain()));
			idleOtherVars[i] = idleOtherVars[i].and(home_1.getDomain()
					.buildEquals(home_1.prime().getDomain()));
			idleOtherVars[i] = idleOtherVars[i].and(home_2.getDomain()
					.buildEquals(home_2.prime().getDomain()));
			for (int j = 1; j < N; j++) {
				ModuleBDDField temp1 = main.getVar("sharer_list[" + j + "]",
						false);
				idleOtherVars[i] = idleOtherVars[i].and(temp1.getDomain()
						.buildEquals(temp1.prime().getDomain()));
				ModuleBDDField temp_home1 = clients[0].getVar(
						"invalidate_list[" + j + "]", false);
				idleOtherVars[i] = idleOtherVars[i].and(temp_home1.getDomain()
						.buildEquals(temp_home1.prime().getDomain()));
				// other clients' cache and channels
				if (j != i) {
					ModuleBDDField Pj_cache = clients[j].getVar("cache", false);
					idleOtherVars[i] = idleOtherVars[i].and(Pj_cache
							.getDomain().buildEquals(
									Pj_cache.prime().getDomain()));
					ModuleBDDField j_cha1 = main.getVar("channel_1[" + j + "]",
							false);
					ModuleBDDField j_cha2 = main.getVar("channel_2[" + j + "]",
							false);
					ModuleBDDField j_cha3 = main.getVar("channel_3[" + j + "]",
							false);
					idleOtherVars[i] = idleOtherVars[i].and(j_cha1.getDomain()
							.buildEquals(j_cha1.prime().getDomain()));
					idleOtherVars[i] = idleOtherVars[i].and(j_cha2.getDomain()
							.buildEquals(j_cha2.prime().getDomain()));
					idleOtherVars[i] = idleOtherVars[i].and(j_cha3.getDomain()
							.buildEquals(j_cha3.prime().getDomain()));
				}
			}
		}

		// trans of home
		BDD transAll = Env.FALSE();
		BDD tempTrans0 = trans[0];
		for (int j = 1; j < N; j++) {
			tempTrans0 = tempTrans0.and(idle[j]);
		}
		// System.out.println("tempTrans0:\n" + Env.toNiceString(main,
		// tempTrans0));
		transAll = transAll.or(tempTrans0);
		// trans of clients
		for (int i = 1; i < N; i++) {
			BDD tempTrans = trans[i].and(idleOtherVars[i]);
			transAll = transAll.or(tempTrans);
		}

		// recahble states
		BDD foundStates = newAllSucc(init, transAll, allUnprimeVars);

		long time = System.currentTimeMillis();
		System.out.println("Overall time mc - " + (time - start));

		// checking the property:
		// property
		ModuleBDDField P1_cache = clients[1].getVar("cache", false);
		ModuleBDDField P2_cache = clients[2].getVar("cache", false);
		ModuleBDDField P3_cache = clients[3].getVar("cache", false);

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

		BDD is_prop_recahble = foundStates.and(neg_property);

		BDD neg_property2 = (P1_cache_exc);
		BDD is_prop_recahble2 = foundStates.and(neg_property2);

		if (verbose) {
			System.out.println("====== Split - inv (MC) =====:");
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
		// BDDVarSet sharedVars = main.moduleUnprimeVars();
		ModuleBDDField shar_1 = main.getVar("exclusive_granted", false);
		ModuleBDDField shar_2 = main.getVar("x_granted", false);
		BDDVarSet sharedVars = shar_1.getDomain().set();
		sharedVars = sharedVars.union(shar_2.getDomain().set());
		for (int i = 1; i < N; i++) {
			ModuleBDDField temp = main.getVar("sharer_list[" + i + "]", false);
			sharedVars = sharedVars.union(temp.getDomain().set());
		}

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
			// the local vars:
			primeLocalVars[i] = clients[i].modulePrimeVars();
			primeUnprimeLocalVars[i] = localVars[i].union(primeLocalVars[i]);
			unprimeLocalGlobalVars[i] = main.moduleUnprimeVars().union(
					clients[i].moduleUnprimeVars());
		}

		// setting the vars each process should quantify out
		ModuleBDDField[] h_cha1 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha2 = new ModuleBDDField[N];
		ModuleBDDField[] h_cha3 = new ModuleBDDField[N];
		BDDVarSet[] varsHomeToQuantifyForP = new BDDVarSet[N];

		for (int i = 1; i < N; i++) {
			h_cha1[i] = clients[0].getVar("channel_1_" + i, false);
			h_cha2[i] = clients[0].getVar("channel_2_" + i, false);
			h_cha3[i] = clients[0].getVar("channel_3_" + i, false);
		}
		// setting the vars of home
		vars[0] = localVars[0].union(sharedVars);
		for (int i = 1; i < N; i++) {
			vars[0] = vars[0].union(h_cha1[i].getDomain().set());
			vars[0] = vars[0].union(h_cha2[i].getDomain().set());
			vars[0] = vars[0].union(h_cha3[i].getDomain().set());
		}
		allVars = allVars.union(vars[0]);
		for (int i = 1; i < N; i++) {
			varsHomeToQuantifyForP[i] = primeUnprimeLocalVars[0];
			for (int j = 1; j < N; j++) {
				if (j != i) {
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha1[j].getDomain().set());
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha2[j].getDomain().set());
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha3[j].getDomain().set());
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha1[j].prime().support());
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha2[j].prime().support());
					varsHomeToQuantifyForP[i] = varsHomeToQuantifyForP[i]
							.union(h_cha3[j].prime().support());

				}
			}
		}
		// for Home each process should only q. its locals
		ModuleBDDField[] cha1_p = new ModuleBDDField[N];
		ModuleBDDField[] cha2_p = new ModuleBDDField[N];
		ModuleBDDField[] cha3_p = new ModuleBDDField[N];
		BDDVarSet[] varsToQuantifyForP = new BDDVarSet[N];
		BDD[] idleByOtherP = new BDD[N];
		for (int i = 1; i < N; i++) {
			cha1_p[i] = clients[i].getVar("my_channel_1", false);
			cha2_p[i] = clients[i].getVar("my_channel_2", false);
			cha3_p[i] = clients[i].getVar("my_channel_3", false);
			varsToQuantifyForP[i] = primeUnprimeLocalVars[i];
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha1_p[i]
					.getDomain().set());
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha2_p[i]
					.getDomain().set());
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha3_p[i]
					.getDomain().set());
			vars[i] = localVars[i].union(sharedVars);
			vars[i] = vars[i].union(cha1_p[i].getDomain().set());
			vars[i] = vars[i].union(cha2_p[i].getDomain().set());
			vars[i] = vars[i].union(cha3_p[i].getDomain().set());
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha1_p[i]
					.prime().support());
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha2_p[i]
					.prime().support());
			varsToQuantifyForP[i] = varsToQuantifyForP[i].union(cha3_p[i]
					.prime().support());
			// a bdd that other s should preserve
			idleByOtherP[i] = cha1_p[i].getDomain().buildEquals(
					cha1_p[i].prime().getDomain());
			idleByOtherP[i] = idleByOtherP[i].and(cha2_p[i].getDomain()
					.buildEquals(cha2_p[i].prime().getDomain()));
			idleByOtherP[i] = idleByOtherP[i].and(cha3_p[i].getDomain()
					.buildEquals(cha3_p[i].prime().getDomain()));
		}

		for (int i = 0; i < N; i++) {
			// transition relations:
			trans[i] = clients[i].trans();
			// the idle trans
			idleTrans[i] = clients[i].idleStep();
			// the initial condition of each client (including shared vars)
			initCond[i] = clients[i].initial(); // .and(initM);
			// the idle step
			// idle[i] =
			// (clients[i].idleStep()).exist(sharedVars).exist(sharedPrimeVars);
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
		// what home gives others:
		BDD[] homeGlobalTransToBeUsedByProc = new BDD[N];
		// what clients give to other clients and to home
		BDD[] jGlobalTransToBeUsedByProc = new BDD[N];
		BDD[] jGlobalTransToBeUsedByHome = new BDD[N];

		@SuppressWarnings("unused")
		int counter = 0;
		do {
			// counter++;
			// System.out.println("counter = " + counter);
			// getting the global transition of home
			for (int i = 1; i < N; i++) {
				homeGlobalTransToBeUsedByProc[i] = (trans[0].and(oldThetas[0])
						.and(Env.prime(newThetas[0])))
						.exist(varsHomeToQuantifyForP[i]);
			}
			// getting the global transition of each process
			for (int i = 1; i < N; i++) {
				jGlobalTransToBeUsedByHome[i] = (trans[i].and(oldThetas[i])
						.and(Env.prime(newThetas[i])))
						.exist(primeUnprimeLocalVars[i]);
				jGlobalTransToBeUsedByProc[i] = (trans[i].and(oldThetas[i])
						.and(Env.prime(newThetas[i])))
						.exist(varsToQuantifyForP[i]);
			}

			// computing the new transition of each process
			// first for home:
			BDD othersGlobalTransForHome = Env.FALSE();
			for (int j = 1; j < N; j++) {
				othersGlobalTransForHome = othersGlobalTransForHome
						.or(jGlobalTransToBeUsedByHome[j]);
			}
			extTrans[0] = extTrans[0].or(othersGlobalTransForHome
					.and(idleTrans[0]));

			// now for all clients
			for (int i = 1; i < N; i++) {
				// first take home's
				BDD othersGlobalTrans = homeGlobalTransToBeUsedByProc[i];
				// now of all other clients
				for (int j = 1; j < N; j++) {
					if (j != i) {
						// THIS CAN BE MADE SIMPLER catching only tre NEW trans
						// IT IS IMPORTANT THAT NEXT WE USE trans and not
						// exttrans
						// since we want to expose only things that "we"
						// contribute!!!
						othersGlobalTrans = othersGlobalTrans
								.or(jGlobalTransToBeUsedByProc[j]);
					}
				}
				extTrans[i] = extTrans[i].or(othersGlobalTrans
						.and(idleTrans[i]).and(idleByOtherP[i]));
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
		// property
		ModuleBDDField P1_cache = clients[1].getVar("cache", false);
		ModuleBDDField P2_cache = clients[2].getVar("cache", false);
		ModuleBDDField P3_cache = clients[3].getVar("cache", false);

		BDD P1_cache_exc = P1_cache.getDomain().ithVar(2);
		BDD P2_cache_exc = P2_cache.getDomain().ithVar(2);
		BDD P3_cache_exc = P3_cache.getDomain().ithVar(2);
		BDD P1_cache_sha = P1_cache.getDomain().ithVar(1);
		BDD P2_cache_sha = P2_cache.getDomain().ithVar(1);
		BDD P3_cache_sha = P3_cache.getDomain().ithVar(1);

		// BDD neg_property = (P1_cache_sha);
		BDD neg_property = (P1_cache_exc.and(P2_cache_sha)).or(
				P1_cache_exc.and(P3_cache_sha)).or(
				P2_cache_exc.and(P1_cache_sha)).or(
				P2_cache_exc.and(P3_cache_sha)).or(
				P3_cache_exc.and(P1_cache_sha)).or(
				P3_cache_exc.and(P2_cache_sha));

		BDD is_prop_recahble = foundStates.and(neg_property);

		BDD neg_property2 = (P1_cache_exc);

		BDD is_prop_recahble2 = foundStates.and(neg_property2);

		if (verbose) {
			System.out.println("====== Split - inv (SEQ) =====:");
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
	}

	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////

	// /////////////////// === PARALLEL === //////////////////////////////

	// ///////////////// PARALLEL -- Ariel's method, sharing transitions
	// /////////////////
	public static ParallelMain_german main_german;

	public static void do_parallel_trans(SMVModule main, int N, int threadNum,
			int procPerThread, int lastProcPerThread) {

		System.out.println("Running the parallel algorithm");
		main_german = new ParallelMain_german(main, N, threadNum,
				procPerThread, lastProcPerThread, verbose);
		main_german.start();
	}
}
