import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;

public class SimpleForTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Env.loadModule("./testcases/misc/assign_on_arr_index2.smv");
			// Env.loadModule("./testcases/constructor_for_loops/bakery_workaround4.smv");
			// Env.loadModule("./testcases/misc/arr_ref2.smv");
			// Env.loadModule("./testcases/for_loops/empty1.smv");
			// Env.loadModule("./testcases/for_loops/nested1.smv");
			// Env.loadModule("./testcases/for_loops/define1.smv");
			// Env.loadModule("./testcases/for_loops/justice1.smv");
			// Env.loadModule("./testcases/constructor_for_loops/simple4.smv");
			// Env.loadModule("./testcases/for_loops/index_op1.smv");
			 Env.loadModule("./testcases/misc_bugs/var_define3.smv");
			// Env.loadModule("./testcases/legacy/not_eq_bug.smv");
			
			SMVModule main = (SMVModule) Env.getModule("main");
			main.setFullPrintingMode(true);
			System.out.println(main);

			// System.out.println(main.getDefine("temp[3]", false).getBDDVal());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
