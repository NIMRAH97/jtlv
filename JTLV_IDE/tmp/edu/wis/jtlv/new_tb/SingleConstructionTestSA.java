package edu.wis.jtlv.new_tb;

import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;

public class SingleConstructionTestSA {
	public static void main(String[] args) {
		// System.setProperty("bdd", "buddy");
		String file_name = "";
		//file_name = "testcases/legacy/mutex1.smv";
		file_name = "testcases/legacy/abp/abp10.smv";
		file_name = "testcases/legacy/ctl-ltl/counter.smv";
		//file_name = "testcases/legacy/ctl-ltl/dme2.smv";
		 file_name = "testcases/legacy/ctl-ltl/syncarb5.smv";
		//file_name = "testcases/legacy/example_cmu/dme1.smv";
		//file_name = "testcases/legacy/deadlock/key10.smv";
		//file_name = "testcases/legacy/brp/brp.smv";
		//file_name = "testcases/legacy/example_irst/dme5.smv";
		//
		//file_name = "testcases/legacy/ctl-ltl/p-queue.smv";
		//file_name = "testcases/legacy/ctl-ltl/prod-cons.smv";
		// file_name = "testcases/legacy/psl-samples/counter.smv";
		// file_name = "testcases/legacy/simp_counter.smv";

		// file_name = "testcases/legacy/bmc/MCP.smv";
		// file_name = "testcases/legacy/bmc/barrel5.smv";
		file_name = "testcases/legacy/simp_barrel.smv";
		// file_name = "testcases/legacy/brp_simp.smv";

		// file_name = "testcases/legacy/examp1.smv";
		// file_name = "testcases/legacy/examp4.smv";
		// file_name = "testcases/legacy/ctl-ltl/periodic.smv";
		// file_name = "testcases/legacy/pci/pci.smv";
		// file_name = "testcases/legacy/examp5.smv";

		// ////////////////////////////////////////////////////////////////////
		// issues list.
		// ==================
		// file_name = "testcases/legacy/abp/abp16.smv"; ~~~
		// *. performance.
		// file_name = "testcases/legacy/ctl-ltl/guidance.smv";
		// file_name = "testcases/legacy/bmc/dme/dme10.smv";
		// 1. good bug... cannot find variable since in is defined in ISA
		//file_name = "testcases/legacy/example_cmu/gigamax.smv";
		// file_name = "testcases/legacy/example_irst/gigamax.smv";
		// file_name = "testcases/legacy/example_irst/gigamax_ltl.smv";
		// file_name = "testcases/legacy/psl-samples/gigamax.smv";
		// file_name = "testcases/legacy/smv-dist/gigamax.smv";
		// 2. seems like a real bug in the design....
		// the un-found variables are really not existing
		// file_name = "testcases/legacy/smv-dist/pci3p.smv"; // cannot find b_stop
		// file_name = "testcases/legacy/reactor/base.smv"; // cannot find IN0002
		// file_name = "testcases/legacy/reactor/idle.smv"; // cannot find IN0002
		// ////////////////////////////////////////////////////////////////////
		// file_name = "testcases/legacy/examp6a.smv";
		// file_name = "testcases/legacy/examp6b.smv";
		// file_name = "testcases/legacy/examp6c.smv";
		// file_name = "testcases/legacy/examp_counter.smv";
		// file_name = "testcases/legacy/examp_mut.smv";
		// file_name = "testcases/legacy/examp_mut2.smv";

		try {
			//Env.beQuiet();
			Env.loadModule(file_name);
			for (String key : Env.getModuleNames()) {
				System.out.println("=========================================");
				SMVModule m = (SMVModule) Env.getModule(key);
				m.setFullPrintingMode(true);
				System.out.println(m);
				System.out.println("=========================================");

				//System.out.println(m.trans());
				// System.out.println("=========================================");
				// m.trans().printDot();

			}
			System.out.println("=========== DONE ========================");
		} catch (IOException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		}
	}
}
