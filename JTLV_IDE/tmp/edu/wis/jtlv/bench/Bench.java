package edu.wis.jtlv.bench;

import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.old_lib.mc.CTLModelChecker;
import edu.wis.jtlv.old_lib.mc.LTLModelChecker;
import edu.wis.jtlv.old_lib.mc.ModelCheckException;
import edu.wis.jtlv.old_lib.mc.SimpleModelChecker;

public class Bench {
	public static void main(String[] args) {
		german_inv(args);
		//very_good_ltl(args);
		//tc0(args);
	}
	
	public static void german_inv(String[] args) {
		//System.setProperty("bdd", "buddy");
		System.setProperty("bdd_table_size", "10000000");
		System.setProperty("bdd_cache_size", "10000");
		SMVModule.initModulesWithoutRunningVar();
		SMVModule main;
		Spec[] all_specs;
		String folder = "./testcases/tlv_bench/";
		try {
			Env.loadModule(folder + "german3c.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile(folder + "german3c.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("====== DONE Loading Modules and Specs ==========");
		// ///////////////////////////////////////
		long start = System.currentTimeMillis();
		double time;
		SimpleModelChecker schecker;
		try {
			schecker = new SimpleModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			schecker.checkInvariance(all_specs[0]);
			System.out.println("*** Property is VALID ***");
		} catch (ModelCheckException e) {
			System.out.println("*** Property is NOT VALID ***");
		}
		//System.out.println("model checking property: " + all_specs[0]);
		// if (lchecker.modelCheckWithNoCounterExample(all_specs[0])) {
		// System.out.println("*** Property is VALID ***");
		// } else {
		// System.out.println("*** Property is NOT VALID ***");
		// }
		time = ((double) (System.currentTimeMillis() - start)) / 1000;
		System.out.println("user time: " + time + " s");
	}
	
	public static void very_good_ltl(String[] args) {
		//System.setProperty("bdd", "buddy");
		System.setProperty("bdd_table_size", "5000000");
		System.setProperty("bdd_cache_size", "3000");
		SMVModule.initModulesWithoutRunningVar();
		SMVModule main;
		Spec[] all_specs;
		String folder = "./testcases/tlv_bench/";
		try {
			Env.loadModule(folder + "counter15.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile(folder + "counter15.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("====== DONE Loading Modules and Specs ==========");
		// ///////////////////////////////////////
		
		long start = System.currentTimeMillis();
		double time;
		LTLModelChecker lchecker;
		try {
			lchecker = new LTLModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}
		//System.out.println("model checking property: " + all_specs[0]);
		if (lchecker.modelCheckWithNoCounterExample(all_specs[0])) {
			System.out.println("*** Property is VALID ***");			
		} else {
			System.out.println("*** Property is NOT VALID ***");
		}
		time = ((double) (System.currentTimeMillis() - start)) / 1000;
		System.out.println("user time: " + time + " s");
		
		//System.out.println("model checking property: " + all_specs[1]);
		if (lchecker.modelCheckWithNoCounterExample(all_specs[1])) {
			System.out.println("*** Property is VALID ***");			
		} else {
			System.out.println("*** Property is NOT VALID ***");
		}
		time = ((double) (System.currentTimeMillis() - start)) / 1000;
		System.out.println("user time: " + time + " s");
		
		//System.out.println("model checking property: " + all_specs[2]);
		if (lchecker.modelCheckWithNoCounterExample(all_specs[2])) {
			System.out.println("*** Property is VALID ***");			
		} else {
			System.out.println("*** Property is NOT VALID ***");
		}
		time = ((double) (System.currentTimeMillis() - start)) / 1000;
		System.out.println("user time: " + time + " s");
	}

	public static void tc0(String[] args) {
		System.setProperty("bdd", "buddy");
		SMVModule main;
		Spec[] all_specs;

		try {
			SMVModule.initModulesWithoutRunningVar();
			Env.loadModule("counter.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile("counter.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("========= DONE Loading Modules and Specs =======");
		// ///////////////////////////////////////

		CTLModelChecker cchecker;
		LTLModelChecker lchecker;
		try {
			cchecker = new CTLModelChecker(main, true);
			lchecker = new LTLModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}
		cchecker.modelCheckStandardOutput(all_specs[0]);
		cchecker.modelCheckStandardOutput(all_specs[1]);

		lchecker.modelCheckStandardOutput(all_specs[2]);
	}

	public static void tc1(String[] args) {
		System.setProperty("bdd", "buddy");
		SMVModule main;
		Spec[] all_specs;

		try {
			SMVModule.initModulesWithoutRunningVar();
			Env.loadModule("bmc_tutorial.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile("bmc_tutorial.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out
				.println("========= DONE Loading Modules and Specs ==========");
		// ///////////////////////////////////////

		LTLModelChecker lchecker;
		try {
			lchecker = new LTLModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}
		lchecker.modelCheckStandardOutput(all_specs[0]);
	}

	public static void tc2(String[] args) {
		System.setProperty("bdd", "buddy");
		SMVModule main;
		Spec[] all_specs;

		try {
			SMVModule.initModulesWithoutRunningVar();
			Env.loadModule("barrel5.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile("barrel5.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out
				.println("========= DONE Loading Modules and Specs ==========");
		// ///////////////////////////////////////

		LTLModelChecker lchecker;
		try {
			lchecker = new LTLModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}
		lchecker.modelCheckStandardOutput(all_specs[0]);
	}

	public static void tc3(String[] args) {
		System.setProperty("bdd", "buddy");
		SMVModule main;
		Spec[] all_specs;

		try {
			SMVModule.initModulesWithoutRunningVar();
			Env.loadModule("multiplier.smv");
			main = (SMVModule) Env.getModule("main");
			all_specs = Env.loadSpecFile("multiplier.spc");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out
				.println("========= DONE Loading Modules and Specs ==========");
		// ///////////////////////////////////////

		CTLModelChecker cchecker;
		// LTLModelChecker lchecker;
		try {
			cchecker = new CTLModelChecker(main, true);
			// lchecker = new LTLModelChecker(main, true);
		} catch (ModelCheckException e) {
			e.printStackTrace();
			return;
		}

		// cchecker.modelCheckStandardOutput(all_specs[0]);
		cchecker.modelCheckStandardOutput(all_specs[1]);
		// lchecker.modelCheckStandardOutput(all_specs[2]);
		// lchecker.modelCheckStandardOutput(all_specs[3]);
		// ///////////////////////////////////////

	}

}
