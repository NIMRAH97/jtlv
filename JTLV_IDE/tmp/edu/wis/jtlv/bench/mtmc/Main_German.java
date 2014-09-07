package edu.wis.jtlv.bench.mtmc;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

public class Main_German {
	public static Configuration conf = new Configuration();

	public static void main(String[] args) {
		args = new String[] { "-v", "-vb", "-ics", "500", "-tcs", "500",
				"-ibs", "7000000", "-tbs", "7000000", "-cs", "-N", "6", "-seq",
				"GR", "-ppt", "1", "-delta_imp",
				"./testcases/mtmc_tc/german6f.smv" };
		args = new String[] { "-v", "-vb", "-ics", "500", "-tcs", "500",
				"-ibs", "100000", "-tbs", "3000000", "-cs", "-N", "6", "-par",
				"GRF", "-ppt", "1", "-delta_imp",
				"./testcases/mtmc_tc/german6f.smv" };

		// ////////////////////////////////////////////////////////////////////
		// New German parallel ////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////
		// args = new String[] { "-v", "-vb", "-ics", "500", "-tcs", "500",
		// "-ibs", "250000", "-tbs", "18000000", "-cs", "-N", "7", "-seq",
		// "GR", "-ppt", "1", "-delta_imp", "./testcases/mtmc_tc/german7f.smv"
		// };
		/* 50 */
		// args = new String[] { "-v", "-vb", "-ics", "500", "-tcs", "500",
		// "-ibs", "250000", "-tbs", "3000000", "-cs", "-N", "7", "-par",
		// "GRF", "-ppt", "1", "-delta_imp", "./testcases/mtmc_tc/german7f.smv"
		// };
		/* 30.5 */

		// starting the actual algorithm.
		//
		// a. reading the configuration from the prompt.
		conf.readUsage(args);
		if (conf.verbose)
			System.out.println("Running " + conf.impl + " algorithm");

		// b. getting the algorithm performer.
		AbstractAlgorithm performer = conf.getAlg();
		if (performer == null)
			return;

		// c. executing the algorithm.
		BDD foundStates = performer.performAlg();

		// d. sanity check.
		if (conf.sanity_check)
			check_sanity(foundStates);
	}

	public static void check_sanity(BDD foundStates) {
		int N = conf.N;
		boolean verbose = conf.verbose;

		// checking the property:
		SMVModule main = conf.main;
		// SMVModule[] clients = new SMVModule[N];
		ModuleBDDField[][] Pi_cache_vars = new ModuleBDDField[N][N];
		BDD[][] Pi_cache_exc = new BDD[N][N];
		BDD[][] Pi_cache_sha = new BDD[N][N];
		for (int c = 0; c < N; c++) {
			for (int i = 0; i < N; i++) {
				// getting the clients
				// clients[i] = (SMVModule) Env.getModule("main.P" + i);
				// removing "running" variable.
				// clients[i].removeRunningVar();
				// obtain "cache"
				Pi_cache_vars[c][i] = main.getVar("cr[" + c + "].cache[" + i
						+ "]", true);
				// set values
				Pi_cache_exc[c][i] = Pi_cache_vars[c][i].getDomain().ithVar(2);
				Pi_cache_sha[c][i] = Pi_cache_vars[c][i].getDomain().ithVar(1);
			}
		}

		BDD[] neg_property = new BDD[N];
		for (int c = 0; c < N; c++) {
			neg_property[c] = Env.FALSE();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != i) {
						neg_property[c] = neg_property[c].or(Pi_cache_exc[c][i]
								.and(Pi_cache_sha[c][j]));
					}
				}
			}
		}
		// negation of real property to check

		for (int c = 0; c < N; c++) {
			System.out.println("cl[" + c + "]");
			BDD is_prop_recahble = foundStates.and(neg_property[c]);
			if (verbose) {
				System.out.println("Real Property to check:");
				// System.out.println(Env.toNiceString(main, neg_property));
				if (is_prop_recahble.equals(Env.FALSE()))
					System.out.println("-->It is not reachable (success!!!)");
				else
					System.out.println("-->It is reachable (failure!!!)");

				System.out.println("Sanity Property to check:");
				for (int i = 0; i < Pi_cache_exc.length; i++) {
					// another property just to make sure states are detected
					BDD neg_property2 = (Pi_cache_exc[c][i]);
					BDD is_prop_recahble2 = foundStates.and(neg_property2);
					if (is_prop_recahble2.equals(Env.FALSE()))
						System.out
								.println("-->It is not reachable (failure!!!)");
					else
						System.out.println("-->It is reachable (success!!!)");
				}
			}
		}
	}

}
