import java.util.LinkedList;

import edu.wis.jtlv.env.module.SMVModule;

public class Patch1_3_2_resetStructure {

	public static void reset_structure(SMVModule main) {
		LinkedList<SMVModule> all_mod = new LinkedList<SMVModule>();
		all_mod.add(main);

		while (!all_mod.isEmpty()) {
			SMVModule m = all_mod.pop();
			SMVModule[] sub_inst = m.getAllInstances();
			for (int i = 0; i < sub_inst.length; i++) {
				all_mod.push(sub_inst[i]);
			}

			m.getValue("STAMMMMMMMM__SADADASD");
		}
	}
}
