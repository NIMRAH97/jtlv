package edu.wis.jtlv.experimental.threads.mc1;

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
public class MuxsemLast {

	public static void main(String[] args) {
		int N = 64;
		int procPerThread = 16;
		boolean do_parallel = true;
		boolean sanity_check = false;
		int threadNum = N / procPerThread + ((N % procPerThread != 0) ? 1 : 0);
		int lastProcPerThread = N % procPerThread;
		if (lastProcPerThread == 0)
			lastProcPerThread = procPerThread;
		System.setProperty("pool_size", "" + threadNum);
		SMVModule main = loadMainModule("./testcases/parallel_mc_tc/new_muxsem_last" + N + ".smv");

		BDD res;
		if (do_parallel) {
			res = do_parallel(main, N, threadNum, procPerThread,
					lastProcPerThread);
			if (sanity_check) {
				BDD otherRes = do_sequential(main, N);
				if (!res.biimp(otherRes).isOne())
					System.err.println("sequencial is not identical to the"
							+ " parallel version.");
				else
					System.out.println("sanity check passed.");
			}
		} else {
			res = do_sequential(main, N);
			if (sanity_check) {
				BDD otherRes = do_parallel(main, N, threadNum, procPerThread,
						lastProcPerThread);
				if (!res.biimp(otherRes).isOne())
					System.err.println("parallelis not identical to the"
							+ " sequencial parallel version.");
				else
					System.out.println("sanity check passed.");
			}
		}
	}

	public static BDD do_sequential(SMVModule main, int N) {

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
		long start = System.currentTimeMillis();

		do {
			fixPointFlag = false;

			// computing new frontiers for each process
			// THIS SHOULD BE PARALLEL!!!
			BDD[] newClientFrontiers = new BDD[N];
			for (int i = 0; i < N; i++) {
				newClientFrontiers[i] = Env.allSucc(extTrans[i], foundStates);
			}

			// gathering all the states
			// THIS IS THE TRICKY PART:
			// How do we share information when newClientFrontiers[i]
			// and new_frontiers resides on different BDD data structures
			new_frontiers = Env.FALSE();
			for (int i = 0; i < N; i++) {
				new_frontiers = new_frontiers.or(newClientFrontiers[i]);
			}

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

			// initializing the set of states (to True)
			foundStates = Env.TRUE();

			// computing the new states and the flag for the fixpoint
			// Again a tricky part! (not parallel)
			for (int i = 0; i < N; i++) {
				foundStates = foundStates.and(newThetas[i]);
				fixPointFlag = fixPointFlag
						| (!oldThetas[i].equals(newThetas[i]));
			}
			// System.out.println(Env.toNiceString(foundStates));
			// System.out.println("====================================");
		} while (fixPointFlag);
		// Basically we are done;
		System.out.println("Done sequential! loop has taken "
				+ (System.currentTimeMillis() - start));

		return foundStates;
	}

	public static BDD do_parallel(SMVModule main, int N, int threadNum,
			int procPerThread, int lastProcPerThread) {

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
		long start = System.currentTimeMillis();

		// setting "main" thread priority.
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
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

			pool[i] = new MCThread(newThetasArr, otherLocalVarsArr);
			pool[i].setPriority(Thread.MIN_PRIORITY);
			pool[i].start();
		}

		do {
			System.out.print("new calculation ");
			fixPointFlag = false;

			// computing new frontiers for each process
			// THIS SHOULD BE PARALLEL!!!
			BDD[] newClientFrontiers = new BDD[N];
			for (int i = 0; i < N; i++) {
				newClientFrontiers[i] = Env.allSucc(extTrans[i], foundStates);
			}

			System.out.print("- collecting client frontiers ");
			
			// gathering all the states
			// THIS IS THE TRICKY PART:
			// How do we share information when newClientFrontiers[i]
			// and new_frontiers resides on different BDD data structures
			new_frontiers = Env.FALSE();
			for (int i = 0; i < N; i++) {
				new_frontiers = new_frontiers.or(newClientFrontiers[i]);
			}

			System.out.println("- starting threads...");
			for (int i = 0; i < threadNum; i++) {
				pool[i].new_calculation(new_frontiers);
			}

			// initializing the set of states (to True)
			foundStates = Env.TRUE();

			// computing the new states and the flag for the fixpoint
			// Again a tricky part! (not parallel)
			for (int i = 0; i < threadNum; i++) {
				foundStates = foundStates.and(pool[i].getResults());
				fixPointFlag = fixPointFlag | pool[i].hasChanged();
			}
			// System.out.println(Env.toNiceString(foundStates));
			// System.out.println("====================================");
		} while (fixPointFlag);
		// Basically we are done;
		long curr_time = (System.currentTimeMillis() - start);
		System.out.println("Done parallel! loop has taken " + curr_time + "\n");
		// System.out.println("Done! loop has taken " + curr_time + " - "
		// + Env.BDDCopyTime + "[BDD copy] - " + Env.BDDVarSetCopyTime
		// + "[BDDVarSet copy] = "
		// + (curr_time - Env.BDDCopyTime - Env.BDDVarSetCopyTime)
		// + "[net copying time]");
		MCThread.terminateAll();

		return foundStates;
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
				BDDVarSet[] given_otherLocalVars) {
			this.old_theta = new BDD[given_start_theta.length];
			this.new_theta = adjustBDDToManager(given_start_theta);
			this.otherLocalVars = adjustBDDVarSetToManager(given_otherLocalVars);
		}

		private boolean calculationStarted = false;

		public void new_calculation(BDD given_new_frontier) {
			this.new_frontier = adjustBDDToManager(given_new_frontier);
			hasChanged = false;
			calculationStarted = true;
		}

		private static boolean terminate_all = false;

		public static void terminateAll() {
			terminate_all = true;
		}

		public void run() {
			while (true) {
				// waiting until the previous results will be given.
				while (!calculationStarted) {
					// yield();
					try {
						sleep(5);
					} catch (InterruptedException e) {
					}
					if (terminate_all)
						return;
				}
				for (int i = 0; i < this.old_theta.length; i++)
					this.old_theta[i] = this.new_theta[i];

				this.result = Env.TRUE();
				for (int i = 0; i < this.new_theta.length; i++) {
					this.new_theta[i] = this.old_theta[i].or(new_frontier
							.exist(otherLocalVars[i]));
					result = result.id().and(this.new_theta[i]);
					hasChanged |= (!this.old_theta[i].equals(this.new_theta[i]));
				}

				// this.result = adjustBDDToBase(this.new_theta);

				calculationStarted = false;
			}
		}

		private boolean hasChanged;
		private BDD result;

		public BDD getResults() {
			// yield();
			try {
				sleep(5);
			} catch (InterruptedException e) {
			}
			return adjustBDDToBase(this.result);
		}

		public boolean hasChanged() {
			// yield();
			try {
				sleep(5);
			} catch (InterruptedException e) {
			}
			return hasChanged;
		}
	}
}