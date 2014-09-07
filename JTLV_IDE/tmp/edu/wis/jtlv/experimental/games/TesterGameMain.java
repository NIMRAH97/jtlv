package edu.wis.jtlv.experimental.games;
import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.spec.Operator;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.env.spec.SpecExp;
import edu.wis.jtlv.old_lib.mc.CTLModelChecker;
import edu.wis.jtlv.old_lib.mc.LTLModelChecker;
import edu.wis.jtlv.old_lib.mc.ModelCheckException;
import edu.wis.jtlv.old_lib.mc.LTLModelChecker.LTLTesterBuilder;

public class TesterGameMain {
	public static void main(String[] args) {
		build();
	}
	
	public static void build() {
		// System.setProperty("bdd", "buddy");
		String file_name = "testcases/simple.smv";

		try {
			Env.loadModule(file_name);
			SMVModule main = (SMVModule) Env.getModule("main");
			main.setFullPrintingMode(true);
			System.out.println("========= DONE Loading Modules ==========");

			String to_parse = "";
			to_parse += "LTLSPEC\n" + "[] aa\n";
			Spec[] all_specs = Env.loadSpecString(to_parse);
			System.out.println("========= DONE Loading Specs ============");

			try {
				//Spec spec = all_specs[0];
				Spec spec = new SpecExp(Operator.NOT, all_specs[0]);
				LTLTesterBuilder builder = new LTLTesterBuilder(spec,false);
				builder.getTester().setFullPrintingMode(true);
				System.out.println(builder.getTester());
			} catch (ModelCheckException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.exit(0);
		}

		System.out.println("DONE");
	}

	public static void standAloneValidAndPrint(Spec toCheck) {
		try {
			System.out.println("checking: " + toCheck);
			LTLModelChecker.standAloneValid(toCheck);
		} catch (ModelCheckException mce) {
			System.err.println(mce.toString());
		}
	}

	public static void validAndPrint(LTLModelChecker checker, Spec toCheck) {
		try {
			System.out.println("checking: " + toCheck);
			checker.valid(toCheck);
		} catch (ModelCheckException mce) {
			System.err.println(mce.toString());
		}
	}

	public static void ltlMCAndPrint(LTLModelChecker checker, Spec toCheck) {
		try {
			System.out.println("checking: " + toCheck);
			checker.modelCheck(toCheck);
		} catch (ModelCheckException mce) {
			System.err.println(mce.toString());
		}
	}

	public static void ctlMCAndPrint(CTLModelChecker checker, Spec toCheck) {
		try {
			System.out.println("checking: " + toCheck);
			checker.modelCheck(toCheck);
		} catch (ModelCheckException mce) {
			System.err.println(mce.toString());
		}
	}
}
