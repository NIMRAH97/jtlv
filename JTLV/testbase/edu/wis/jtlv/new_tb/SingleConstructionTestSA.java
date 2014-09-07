package edu.wis.jtlv.new_tb;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;

public class SingleConstructionTestSA {
	public static void main(String[] args) {
		// System.setProperty("bdd", "buddy");
		String file_name = "";
		file_name = "testcases/mutex1.smv";
		file_name = "testcases/abp/abp10.smv";
		// file_name = "testcases/ctl-ltl/counter.smv";
		//file_name = "testcases/ctl-ltl/dme2.smv";
		// file_name = "testcases/ctl-ltl/syncarb5.smv";
		// file_name = "testcases/example_cmu/dme1.smv";
		// file_name = "testcases/deadlock/key10.smv";
		//file_name = "testcases/brp/brp.smv";
		// file_name = "testcases/example_irst/dme5.smv";
		//
		//file_name = "testcases/ctl-ltl/p-queue.smv";
		//file_name = "testcases/ctl-ltl/prod-cons.smv";
		// file_name = "testcases/psl-samples/counter.smv";
		// file_name = "testcases/simp_counter.smv";

		// file_name = "testcases/bmc/MCP.smv";
		// file_name = "testcases/bmc/barrel5.smv";
		// file_name = "testcases/simp_barrel.smv";
		// file_name = "testcases/brp_simp.smv";

		// file_name = "testcases/examp1.smv";
		// file_name = "testcases/examp4.smv";
		// file_name = "testcases/ctl-ltl/periodic.smv";
		// file_name = "testcases/pci/pci.smv";
		// file_name = "testcases/examp5.smv";

		// ////////////////////////////////////////////////////////////////////
		// issues list.
		// ==================
		// file_name = "testcases/abp/abp16.smv"; ~~~
		// *. performance.
		// file_name = "testcases/ctl-ltl/guidance.smv";
		// file_name = "testcases/bmc/dme/dme10.smv";
		// 1. good bug... cannot find variable since in is defined in ISA
		// file_name = "testcases/example_cmu/gigamax.smv";
		// file_name = "testcases/example_irst/gigamax.smv";
		// file_name = "testcases/example_irst/gigamax_ltl.smv";
		// file_name = "testcases/psl-samples/gigamax.smv";
		// file_name = "testcases/smv-dist/gigamax.smv";
		// 2. seems like a real bug in the design....
		// the un-found variables are really not existing
		// file_name = "testcases/smv-dist/pci3p.smv"; // cannot find b_stop
		// file_name = "testcases/reactor/base.smv"; // cannot find IN0002
		// file_name = "testcases/reactor/idle.smv"; // cannot find IN0002
		// ////////////////////////////////////////////////////////////////////
		// file_name = "testcases/examp6a.smv";
		// file_name = "testcases/examp6b.smv";
		// file_name = "testcases/examp6c.smv";
		// file_name = "testcases/examp_counter.smv";
		// file_name = "testcases/examp_mut.smv";
		// file_name = "testcases/examp_mut2.smv";

		try {
			Env.beQuiet();
			// SMVModule.debug_parse_tree = true;
			SMVModule.load_from_file(file_name);
			for (String key : Env.all_modules.keySet()) {
				System.out.println("=========================================");
				SMVModule m = (SMVModule) Env.all_modules.get(key);
				m.set_toString_print_with_bdd();
				System.out.println(m);
				System.out.println("=========================================");

				//System.out.println(m.trans());
				// System.out.println("=========================================");
				// m.trans().printDot();

			}
			System.out.println("=========== DONE ========================");
		} catch (ModuleException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		} catch (IOException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		} catch (RecognitionException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		}
	}
}
