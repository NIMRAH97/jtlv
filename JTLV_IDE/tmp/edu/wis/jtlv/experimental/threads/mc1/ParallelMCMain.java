package edu.wis.jtlv.experimental.threads.mc1;

import java.io.FileReader;
import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.SMVModule;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * @author ariel
 * 
 */
public class ParallelMCMain {
	static int N;
	static int procPerThread;
	static boolean do_parallel;
	static boolean sanity_check;
	static boolean verbose;
	static int threadNum;
	static int lastProcPerThread;
	static SMVModule main;

	public static void usage(String[] args) {
		String fName = "";
		N = -1;
		procPerThread = -1;
		do_parallel = false;
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
					continue;
				}
				if (flag.equals("--doSequential") || flag.equals("-seq")) {
					do_parallel = false;
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
			String mName = ParallelMCMain.class.getCanonicalName();
			System.err.println("Failed parsing flags.");
			System.err.println("\t" + args);
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

	public static void main(String[] args) {
		usage(args);
		BDD res;
		if (do_parallel) {
			res = do_parallel(main, N, threadNum, procPerThread,
					lastProcPerThread);
			if (sanity_check) {
				if (!res.biimp(do_sequential(main, N)).isOne())
					System.err.println("sequencial is not identical to the"
							+ " parallel version.");
				else
					System.out.println("sanity check passed.");
			}
		} else {
			res = do_sequential(main, N);
			if (sanity_check) {
				if (!res.biimp(
						do_parallel(main, N, threadNum, procPerThread,
								lastProcPerThread)).isOne())
					System.err.println("parallelis not identical to the"
							+ " sequencial parallel version.");
				else
					System.out.println("sanity check passed.");
			}
		}
	}

	public static BDD do_sequential(SMVModule main, int N) {
		long start = System.currentTimeMillis();

		// initial states of M
		BDD initM = main.initial();
		// shared vars:
		BDDVarSet sharedVars = main.moduleUnprimeVars();
		// all vars in the system
		BDDVarSet allVars = sharedVars;

		// == clients:
		// JTLVBDDManager[] bdd_client_managers = new
		// JTLVBDDManager(10000,10000);

		SMVModule[] clients = new SMVModule[N];
		// transition relation:
		BDD[] trans = new BDD[N];
		// extended transition relation:
		BDD[] extTrans = new BDD[N];
		// initial conditions:
		BDD[] initCond = new BDD[N];
		// idle step:
		BDD[] idle = new BDD[N];
		// variables (local and shared):
		BDDVarSet[] vars = new BDDVarSet[N];
		// local variables only:
		BDDVarSet[] localVars = new BDDVarSet[N];
		// local variables of other clients
		BDDVarSet[] otherLocalVars = new BDDVarSet[N];
		// Theta of each process
		BDD[] oldThetas = new BDD[N];
		BDD[] newThetas = new BDD[N];

		for (int i = 0; i < N; i++) {
			// getting the clients
			clients[i] = (SMVModule) Env.getModule("main.P" + (i + 1));
			// removing "running" variable.
			clients[i].removeRunningVar();
			// transition relations:
			trans[i] = clients[i].trans();
			// the initial condition of each client (including shared vars)
			initCond[i] = clients[i].initial().and(initM);
			// the idle step
			idle[i] = clients[i].idleStep();
			// the local vars:
			localVars[i] = clients[i].moduleUnprimeVars();
			// the local and shared vars:
			vars[i] = localVars[i].union(sharedVars);
			// extend allVars:
			allVars = allVars.union(vars[i]);
			// initial states:
			oldThetas[i] = initCond[i];
			newThetas[i] = initCond[i];
			// initializing othersVars
			otherLocalVars[i] = Env.getEmptySet();
		}

		// Defining the local variables of the "other" processes;
		// Fixing the transition relations
		for (int i = 0; i < N; i++) {
			extTrans[i] = trans[i];
			for (int j = 0; j < N; j++) {
				if (j != i) {
					// compute local variables of other clients
					otherLocalVars[i] = otherLocalVars[i].union(localVars[j]);
					// modify transition relations to idle otherVars:
					extTrans[i] = extTrans[i].and(idle[j]);
				}
			}
		}

		// the new states added by each process each step
		BDD new_frontiers;

		// all states found (over approximation of reachability)
		BDD foundStates = Env.TRUE();
		for (int i = 0; i < N; i++) {
			foundStates = foundStates.and(initCond[i]);
		}

		// flag indicating a fixpoint reached
		boolean fixPointFlag = false;

		if (verbose)
			System.out
					.println("done with preparations, stating calculation (Preload time:"
							+ (System.currentTimeMillis() - start)
							+ ")\n RESETTING TIME.");

		start = System.currentTimeMillis();
		do {
			if (verbose)
				System.out.println("new calculation (Time:"
						+ (System.currentTimeMillis() - start) + ")");
			fixPointFlag = false;

			// computing new frontiers for each process
			// THIS SHOULD BE PARALLEL!!!
			BDD[] newClientFrontiers = new BDD[N];
			for (int i = 0; i < N; i++) {
				newClientFrontiers[i] = Env.allSucc(extTrans[i], foundStates);
			}

			if (verbose)
				System.out.println("\tdone collecting client frontiers (Time:"
						+ (System.currentTimeMillis() - start) + ")");

			// gathering all the states
			// THIS IS THE TRICKY PART:
			// How do we share information when newClientFrontiers[i]
			// and new_frontiers resides on different BDD data structures
			new_frontiers = Env.FALSE();
			for (int i = 0; i < N; i++) {
				new_frontiers = new_frontiers.or(newClientFrontiers[i]);
			}

			if (verbose)
				System.out.println("\tstarting sequential computation - thread"
						+ " main (Time:" + (System.currentTimeMillis() - start)
						+ ")");

			// saving the old thetas
			// THIS SHOULD BE PARALLEL (or not)
			for (int i = 0; i < N; i++) {
				oldThetas[i] = newThetas[i];
			}

			// computing the new thetas
			// THIS SHOULD BE PARALLEL!!!
			for (int i = 0; i < N; i++) {
				newThetas[i] = oldThetas[i].or(new_frontiers
						.exist(otherLocalVars[i]));
			}

			if (verbose)
				System.out.println("\tstarting to collect results" + " (Time:"
						+ (System.currentTimeMillis() - start) + ")");

			// initializing the set of states (to True)
			foundStates = Env.TRUE();

			// computing the new states and the flag for the fixpoint
			// Again a tricky part! (not parallel)
			for (int i = 0; i < N; i++) {
				foundStates = foundStates.and(newThetas[i]);
				fixPointFlag = fixPointFlag
						| (!oldThetas[i].equals(newThetas[i]));
			}

			if (verbose)
				System.out.println("\tdone with iteration (Time:"
						+ (System.currentTimeMillis() - start) + ")");
		} while (fixPointFlag);
		// Basically we are done;
		System.out.println("Done sequential! loop has taken "
				+ (System.currentTimeMillis() - start));

		return foundStates;
	}

	public static BDD do_parallel(SMVModule main, int N, int threadNum,
			int procPerThread, int lastProcPerThread) {
		long start = System.currentTimeMillis();

		// initial states of M
		BDD initM = main.initial();
		// shared vars:
		BDDVarSet sharedVars = main.moduleUnprimeVars();
		// all vars in the system
		BDDVarSet allVars = sharedVars;

		// == clients:
		// JTLVBDDManager[] bdd_client_managers = new
		// JTLVBDDManager(10000,10000);

		SMVModule[] clients = new SMVModule[N];
		// transition relation:
		BDD[] trans = new BDD[N];
		// extended transition relation:
		BDD[] extTrans = new BDD[N];
		// initial conditions:
		BDD[] initCond = new BDD[N];
		// idle step:
		BDD[] idle = new BDD[N];
		// variables (local and shared):
		BDDVarSet[] vars = new BDDVarSet[N];
		// local variables only:
		BDDVarSet[] localVars = new BDDVarSet[N];
		// local variables of other clients
		BDDVarSet[] otherLocalVars = new BDDVarSet[N];
		// Theta of each process
		BDD[] oldThetas = new BDD[N];
		BDD[] newThetas = new BDD[N];

		for (int i = 0; i < N; i++) {
			// getting the clients
			clients[i] = (SMVModule) Env.getModule("main.P" + (i + 1));
			// removing "running" variable.
			clients[i].removeRunningVar();
			// transition relations:
			trans[i] = clients[i].trans();
			// the initial condition of each client (including shared vars)
			initCond[i] = clients[i].initial().and(initM);
			// the idle step
			idle[i] = clients[i].idleStep();
			// the local vars:
			localVars[i] = clients[i].moduleUnprimeVars();
			// the local and shared vars:
			vars[i] = localVars[i].union(sharedVars);
			// extend allVars:
			allVars = allVars.union(vars[i]);
			// initial states:
			oldThetas[i] = initCond[i];
			newThetas[i] = initCond[i];
			// initializing othersVars
			otherLocalVars[i] = Env.getEmptySet();
		}

		// Defining the local variables of the "other" processes;
		// Fixing the transition relations
		for (int i = 0; i < N; i++) {
			extTrans[i] = trans[i];
			for (int j = 0; j < N; j++) {
				if (j != i) {
					// compute local variables of other clients
					otherLocalVars[i] = otherLocalVars[i].union(localVars[j]);
					// modify transition relations to idle otherVars:
					extTrans[i] = extTrans[i].and(idle[j]);
				}
			}
		}

		// the new states added by each process each step
		BDD new_frontiers;

		// all states found (over approximation of reachability)
		BDD foundStates = Env.TRUE();
		for (int i = 0; i < N; i++) {
			foundStates = foundStates.and(initCond[i]);
		}

		// flag indicating a fixpoint reached
		boolean fixPointFlag = false;

		if (verbose)
			System.out
					.println("done with preparations, stating calculation (Preload time:"
							+ (System.currentTimeMillis() - start)
							+ ")\n RESETTING TIME.");

		start = System.currentTimeMillis();
		do {
			if (verbose)
				System.out.println("new calculation (Time:"
						+ (System.currentTimeMillis() - start) + ")");
			fixPointFlag = false;

			// computing new frontiers for each process
			// THIS SHOULD BE PARALLEL!!!
			BDD[] newClientFrontiers = new BDD[N];
			for (int i = 0; i < N; i++) {
				newClientFrontiers[i] = Env.allSucc(extTrans[i], foundStates);
			}

			if (verbose)
				System.out.println("\tdone collecting client frontiers (Time:"
						+ (System.currentTimeMillis() - start) + ")");

			// gathering all the states
			// THIS IS THE TRICKY PART:
			// How do we share information when newClientFrontiers[i]
			// and new_frontiers resides on different BDD data structures
			new_frontiers = Env.FALSE();
			for (int i = 0; i < N; i++) {
				new_frontiers = new_frontiers.or(newClientFrontiers[i]);
			}

			if (verbose)
				System.out.println("\tstarting threads (Time:"
						+ (System.currentTimeMillis() - start) + ")");

			MCThread[] pool = getNewThreadsPool(threadNum, procPerThread,
					lastProcPerThread, newThetas, otherLocalVars, new_frontiers);

			for (int i = 0; i < pool.length; i++) {
				pool[i].start();
			}

			for (int i = 0; i < pool.length; i++) {
				try {
					pool[i].join();
				} catch (InterruptedException e) {
					i--;
				}
			}

			if (verbose)
				System.out.println("\tstarting to collect threads results"
						+ " (Time:" + (System.currentTimeMillis() - start)
						+ ")");

			// initializing the set of states (to True)
			foundStates = Env.TRUE();

			// computing the new states and the flag for the fixpoint
			// Again a tricky part! (not parallel)
			for (int i = 0; i < threadNum; i++) {
				foundStates = foundStates.and(pool[i].getResults());
				fixPointFlag = fixPointFlag | pool[i].hasChanged();
			}

			for (int j = 0; j < pool.length; j++) {
				BDD[] tmpNewThetas = pool[j].getNewThetas();
				for (int i = 0; i < tmpNewThetas.length; i++) {
					newThetas[(j * procPerThread) + i] = tmpNewThetas[i];
				}
			}

			if (verbose)
				System.out.println("\tdone with iteration (Time:"
						+ (System.currentTimeMillis() - start) + ")");
		} while (fixPointFlag);
		// Basically we are done;
		long curr_time = (System.currentTimeMillis() - start);
		System.out.println("Done parallel! loop has taken " + curr_time + "\n");

		return foundStates;
	}

	public static MCThread[] getNewThreadsPool(int threadNum,
			int procPerThread, int lastProcPerThread, BDD[] newThetas,
			BDDVarSet[] otherLocalVars, BDD new_frontiers) {
		MCThread[] pool = new MCThread[threadNum];
		for (int i = 0; i < pool.length; i++) {
			int thread_size = procPerThread;
			if (i == (pool.length - 1))
				thread_size = lastProcPerThread;
			int offset = i * procPerThread;

			BDD[] newThetasArr = new BDD[thread_size];
			BDDVarSet[] otherLocalVarsArr = new BDDVarSet[thread_size];
			for (int j = 0; j < newThetasArr.length; j++) {
				newThetasArr[j] = newThetas[offset + j];
				otherLocalVarsArr[j] = otherLocalVars[offset + j];
			}
			pool[i] = new MCThread(newThetasArr, otherLocalVarsArr,
					new_frontiers);
		}

		return pool;
	}

	public static SMVModule loadMainModule(String input_file) {
		// System.setProperty("bdd", "buddy");
		Env.resetEnv();
		Env.beQuiet();
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

	static class MCThread extends JTLVThread {
		BDD[] old_theta;
		BDD[] new_theta;
		BDD new_frontier;
		BDDVarSet[] otherLocalVars;

		// BDD result; // in base manager....!!

		public MCThread(BDD[] given_start_theta,
				BDDVarSet[] given_otherLocalVars, BDD given_new_frontier) {
			this.old_theta = adjustBDDToManager(given_start_theta);
			this.new_theta = new BDD[given_start_theta.length];
			this.otherLocalVars = adjustBDDVarSetToManager(given_otherLocalVars);
			this.new_frontier = adjustBDDToManager(given_new_frontier);
			hasChanged = false;
		}

		public void run() {
			this.result = Env.TRUE();
			for (int i = 0; i < this.new_theta.length; i++) {
				this.new_theta[i] = this.old_theta[i].or(new_frontier
						.exist(otherLocalVars[i]));
				result = result.id().and(this.new_theta[i]);
				if (!hasChanged)
					hasChanged |= (!this.old_theta[i].equals(this.new_theta[i]));
				this.old_theta[i] = this.new_theta[i];
			}
		}

		private boolean hasChanged;
		private BDD result;

		public BDD getResults() {
			return adjustBDDToBase(this.result);
		}

		public BDD[] getNewThetas() {
			return adjustBDDToBase(this.old_theta);
		}

		public boolean hasChanged() {
			return hasChanged;
		}
	}
}