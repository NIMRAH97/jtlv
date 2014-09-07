import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;


/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * 
 */
public class SimpleFileLoadTC {
	public static void main(String[] args) {
		System.setProperty("bdd", "jtlv");
		String file_name = "./testcases/games_tc/nir_get_initials_tc.smv";
		try {
			Env.loadModule(file_name);
		} catch (IOException e) {
			// error with reading the file.
			e.printStackTrace();
			System.exit(0);
		}
		SMVModule main = (SMVModule) Env.getModule("main");
		SMVModule player1 = (SMVModule) Env.getModule("main.s");
		SMVModule player2 = (SMVModule) Env.getModule("main.e");

		System.out.print("======================\n");
		main.setFullPrintingMode(true);
		System.out.println(main);
		System.out.print("======================\n");
		player1.setFullPrintingMode(true);
		System.out.println(player1);
		System.out.print("======================\n");
		player2.setFullPrintingMode(true);
		System.out.println(player2);
		System.out.print("======================\n");
		System.out.println("");

		BDD init = main.initial();
		System.out.println(Env.toNiceString(init));

		BDDVarSet av = Env.globalPrimeVars().union(Env.globalUnprimeVars());
		System.out.println("all vars are:" + av);
		System.out.println("after intersecting with the player's prime "
				+ "variables:"
				+ av.intersect(player1.modulePrimeVars().union(
						player2.modulePrimeVars())));
		System.out.println("after intersecting with the player's prime "
				+ "variables:"
				+ player1.modulePrimeVars().union(player2.modulePrimeVars())
						.intersect(av));

	}

	// public static void main(String[] args) {
	// String[] file_names = new String[1];
	// file_names[0] = "./testcases/games_tc/nir_get_initials_tc.smv";
	// for (int i = 0; i < file_names.length; i++) {
	// test_main(file_names[i]);
	// Env.resetEnv();
	// }
	// }
	//
	// public static void test_main(String args) {
	// String file_name;
	// file_name = args;
	//		
	// try {
	// Env.loadModule(file_name);
	// SMVModule main = (SMVModule) Env.getModule("main");
	// SMVModule player1 = (SMVModule) Env.getModule("main.s");
	// SMVModule player2 = (SMVModule) Env.getModule("main.e");
	//
	// System.out.print("=============================================\n");
	// System.out.println(main);
	// System.out.print("=============================================\n");
	// System.out.println(player1);
	// System.out.print("=============================================\n");
	// System.out.println(player2);
	// System.out.print("=============================================\n");
	// System.out.println("");
	//
	// BDD init = main.initial();
	// System.out.println(Env.toNiceString(init));
	// } catch (IOException e) {
	// // error with reading the file.
	// e.printStackTrace();
	// System.exit(0);
	// }
	// }
}
