package edu.wis.jtlv.experimental.threads.mc1;

import java.io.IOException;
import java.util.Vector;
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDException;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.SMVModule;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * @author ariel
 */
public class MuxsemLastEfficient {

	public static Module[] loadArgs(String[] args) throws IOException {
		Vector<Module> res_vec = new Vector<Module>(10);
		for (String file : args) {
			Module[] loaded = Env.loadModule(file);
			for (Module m : loaded) {
				res_vec.add(m);
			}
		}
		Module[] res = new Module[res_vec.size()];
		res_vec.toArray(res);
		return res;
	}

	// computing one step successors
	public static BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars)
			throws BDDException {
		BDD prime_to = from.and(trans).exist(unprimeVars);
		return Env.unprime(prime_to);
	}

	// computing all successors
	// !! I should probably change the while-loop to a fixpoint
	public static BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars)
			throws BDDException {
		BDD oldSucc = from;
		BDD newSucc = from;

		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		} while (!oldSucc.equals(newSucc));

		return newSucc;
	}

	public static void main(String[] args) {
		boolean do_parallel = true;
		int N = 8;
		int thread_pool = 8;
		if (do_parallel)
			System.setProperty("pool_size", "" + thread_pool);

		String input_file = "./testcases/parallel_mc_tc/muxsem_last" + N + ".smv";
		// System.setProperty("bdd", "buddy");
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

		if (do_parallel) {
			// setting "main" thread priority.
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			MCThread[] pool = new MCThread[thread_pool];
			do {
				fixPointFlag = false;

				// computing new frontiers for each process
				// THIS SHOULD BE PARALLEL!!!
				BDD[] newClientFrontiers = new BDD[N];
				for (int i = 0; i < N; i++) {
					newClientFrontiers[i] = newAllSucc(foundStates,
							extTrans[i], allVars);
				}

				// gathering all the states
				// THIS IS THE TRICKY PART:
				// How do we share information when newClientFrontiers[i]
				// and new_frontiers resides on different BDD data structures
				new_frontiers = Env.FALSE();
				for (int i = 0; i < N; i++) {
					new_frontiers = new_frontiers.or(newClientFrontiers[i]);
				}

				// initializing the set of states (to True)
				foundStates = Env.TRUE();
				
				int chunk_start = 0;
				int chunk_end = thread_pool;
				while (chunk_start < N) {
					int chunk_size = chunk_end - chunk_start;
					for (int i = 0; i < chunk_size; i++)
						pool[i] = new MCThread(newThetas[i + chunk_start],
								new_frontiers, otherLocalVars[i + chunk_start]);
					for (int i = 0; i < chunk_size; i++)
						pool[i].start();
					for (int i = 0; i < chunk_size; i++) {
						try {
							pool[i].join();
						} catch (InterruptedException e) {
							i--;
						}
					}
					// computing the new states and the flag for the fixpoint
					// Again a tricky part! (not parallel)
					for (int i = 0; i < chunk_size; i++) {
						foundStates = foundStates.and(pool[i].getResults());
						fixPointFlag = fixPointFlag | pool[i].hasChanged();
					}
					
					chunk_start += thread_pool;
					chunk_end += thread_pool;
					if (chunk_end > N)
						chunk_end = N;
				}

			} while (fixPointFlag);
			// Basically we are done;
			long curr_time = (System.currentTimeMillis() - start);
			System.out.println("Done! loop has taken " + curr_time + "\n");
		} else { // the simple implementation
			do {
				fixPointFlag = false;

				// computing new frontiers for each process
				// THIS SHOULD BE PARALLEL!!!
				BDD[] newClientFrontiers = new BDD[N];
				for (int i = 0; i < N; i++) {
					newClientFrontiers[i] = newAllSucc(foundStates,
							extTrans[i], allVars);
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
			} while (fixPointFlag);
			// Basically we are done;
			System.out.println("Done! (loop has taken "
					+ (System.currentTimeMillis() - start) + ")");
		}

		// Maybe next we can perform a sanity check...
		// System.out.println(Env.toNiceString(foundStates));

	}

	static class MCThread extends JTLVThread {
		BDD old_theta;
		BDD new_theta;
		BDD new_frontier;
		BDDVarSet otherLocalVars;

		// BDD result; // in base manager....!!

		public MCThread(BDD old_theta, BDD new_frontier,
				BDDVarSet given_otherLocalVars) {
			this.old_theta = adjustBDDToManager(old_theta);
			this.new_frontier = adjustBDDToManager(new_frontier);
			this.otherLocalVars = adjustBDDVarSetToManager(given_otherLocalVars);
		}

		public void run() {
			this.new_theta = this.old_theta.or(new_frontier
					.exist(otherLocalVars));
		}

		public BDD getResults() {
			return adjustBDDToBase(this.new_theta);
		}

		public boolean hasChanged() {
			return !(this.old_theta.equals(this.new_theta));
		}
	}
}