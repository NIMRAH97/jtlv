package env.wis.jtlv.regression.spec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import junit.framework.TestCase;

import net.sf.javabdd.BDD;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.old_lib.mc.CTLModelChecker;
import edu.wis.jtlv.old_lib.mc.LTLModelChecker;
import edu.wis.jtlv.old_lib.mc.ModelCheckException;
import edu.wis.jtlv.old_lib.mc.SimpleModelChecker;
import env.wis.jtlv.regression.AllTests;

public class OldModelCheckTC extends TestCase {
	private FileOutputStream out_fos;
	private FileOutputStream err_fos;
	private String filename;
	private SMVModule design;
	private Spec[] specification;
	private String spc;
	private boolean isFailingTest;

	private OldModelCheckTC(String testname, String design_filename,
			String spc_str, boolean isFailingTest) {
		// setting the procedure to execute as a test.
		super(testname);
		this.filename = design_filename;
		this.spc = spc_str;
		this.isFailingTest = isFailingTest;
	}

	public static OldModelCheckTC[] getAllDeadlockTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[2];
		res[0] = new OldModelCheckTC("deadlock_0", "testcases/mux-sem.smv", null,
				false);
		res[1] = new OldModelCheckTC("deadlock_1", "testcases/mux-sem.smv", null,
				true);
		return res;
	}

	private static String[] invariance_spec_succ = new String[] { "SPEC\n    !((proc[1].loc = 3) & (proc[2].loc = 3)) \n" };
	private static String[] invariance_spec_fail = new String[] { "SPEC\n    !((proc[1].loc = 3) & (proc[2].loc = 2)) \n" };

	public static OldModelCheckTC[] getAllInvarianceTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[2];
		res[0] = new OldModelCheckTC("invariance_0", "testcases/mux-sem.smv",
				invariance_spec_succ[0], false);
		res[1] = new OldModelCheckTC("invariance_1", "testcases/mux-sem.smv",
				invariance_spec_fail[0], true);
		return res;
	}

	private static String[] temp_entail_spec = new String[] { "SPEC\n  aa \n",
			"SPEC\n  bb \n", "SPEC\n  !bb \n", "SPEC\n ((!bb) & cc) \n", };

	public static OldModelCheckTC[] getAllTempEntailTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[4];
		res[0] = new OldModelCheckTC("temp_entail_0", "testcases/simple_mc.smv",
				temp_entail_spec[0] + temp_entail_spec[0], false);
		res[1] = new OldModelCheckTC("temp_entail_1", "testcases/simple_mc.smv",
				temp_entail_spec[0] + temp_entail_spec[1], false);
		res[2] = new OldModelCheckTC("temp_entail_2", "testcases/simple_mc.smv",
				temp_entail_spec[0] + temp_entail_spec[2], true);
		res[3] = new OldModelCheckTC("temp_entail_3", "testcases/simple_mc.smv",
				temp_entail_spec[0] + temp_entail_spec[3], true);
		return res;
	}

	public static OldModelCheckTC[] getAllReactTC() {
		return new OldModelCheckTC[] {};
	}

	private static String[] sa_valid_spec_succ = new String[] {
			"LTLSPEC\n    GLOBALLY aa -> FINALLY aa             \n",
			"LTLSPEC\n    ([](aa -> ()aa)) -> (<>aa -> <>[]aa)  \n",
			"LTLSPEC\n    [] aa -> () aa                        \n",
			"LTLSPEC\n    [] aa -> <> aa                        \n" };

	private static String[] sa_valid_spec_fail = new String[] {
			"LTLSPEC\n    [] aa                                 \n",
			"LTLSPEC\n    <>[] aa                               \n",
			"LTLSPEC\n    <> aa -> [] aa                        \n",
			"LTLSPEC\n    ([](aa -> ()aa))->(<>aa -> []aa)      \n" };

	public static OldModelCheckTC[] getAllSAValidTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[sa_valid_spec_succ.length
				+ sa_valid_spec_fail.length];
		for (int i = 0; i < sa_valid_spec_succ.length; i++) {
			res[i] = new OldModelCheckTC("sa_valid_" + i,
					"testcases/simple_mc.smv", sa_valid_spec_succ[i], false);
		}
		for (int i = 0; i < sa_valid_spec_fail.length; i++) {
			int rsIdx = sa_valid_spec_succ.length + i;
			res[rsIdx] = new OldModelCheckTC("sa_valid_" + rsIdx,
					"testcases/simple_mc.smv", sa_valid_spec_fail[i], true);
		}
		return res;
	}

	private static String[] valid_spec_succ = new String[] {
			"LTLSPEC\n    GLOBALLY aa -> FINALLY aa             \n",
			"LTLSPEC\n    ([](aa -> ()aa)) -> (<>aa -> <>[]aa)  \n",
			"LTLSPEC\n    [] aa -> () aa                        \n",
			"LTLSPEC\n    [] aa -> <> aa                        \n" };

	private static String[] valid_spec_fail = new String[] {
			"LTLSPEC\n    [] aa                                 \n",
			"LTLSPEC\n    <>[] aa                               \n",
			"LTLSPEC\n    <> aa -> [] aa                        \n",
			"LTLSPEC\n    ([](aa -> ()aa))->(<>aa -> []aa)      \n" };

	public static OldModelCheckTC[] getAllValidTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[valid_spec_succ.length
				+ valid_spec_fail.length];
		for (int i = 0; i < valid_spec_succ.length; i++) {
			res[i] = new OldModelCheckTC("valid_" + i, "testcases/simple_mc.smv",
					valid_spec_succ[i], false);
		}
		for (int i = 0; i < valid_spec_fail.length; i++) {
			int rsIdx = valid_spec_succ.length + i;
			res[rsIdx] = new OldModelCheckTC("valid_" + rsIdx,
					"testcases/simple_mc.smv", valid_spec_fail[i], true);
		}
		return res;
	}

	private static String[] ltl_spec_succ = new String[] {
			"LTLSPEC\n    GLOBALLY aa -> FINALLY aa             \n",
			"LTLSPEC\n    ([](aa -> ()aa)) -> (<>aa -> <>[]aa)  \n",
			"LTLSPEC\n    [] aa -> () aa                        \n",
			"LTLSPEC\n    [] aa -> <> aa                        \n" };

	private static String[] ltl_spec_fail = new String[] {
			"LTLSPEC\n    [] aa                                 \n",
			"LTLSPEC\n    <>[] aa                               \n",
			"LTLSPEC\n    <> aa -> [] aa                        \n",
			"LTLSPEC\n    ([](aa -> ()aa))->(<>aa -> []aa)      \n" };

	public static OldModelCheckTC[] getAllLTLmcTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[ltl_spec_succ.length
				+ ltl_spec_fail.length];
		for (int i = 0; i < ltl_spec_succ.length; i++) {
			res[i] = new OldModelCheckTC("ltl_mc_" + i, "testcases/simple_mc.smv",
					ltl_spec_succ[i], false);
		}
		for (int i = 0; i < ltl_spec_fail.length; i++) {
			int rsIdx = ltl_spec_succ.length + i;
			res[rsIdx] = new OldModelCheckTC("ltl_mc_" + rsIdx,
					"testcases/simple_mc.smv", ltl_spec_fail[i], true);
		}
		return res;
	}

	private static String[] ctl_spec_succ = new String[] {
			"SPEC\n" + "AF aa -> EF aa                          \n",
			"SPEC\n" + "(AF (EG aa)) -> EG aa                   \n",
			"SPEC\n" + "EG aa -> (EX aa & EF aa)                \n",
			"SPEC\n" + "EF aa                                   \n",
			"SPEC\n" + "(EG aa & AX aa) -> (EF !aa)             \n",
			"SPEC\n" + "AF bb -> EF bb                          \n",
			"SPEC\n" + "EG bb -> (EX bb & EF bb)                \n",
			"SPEC\n" + "EF bb -> AF bb                          \n",
			"SPEC\n" + "EF bb                                   \n",
			"SPEC\n" + "(EG bb & AX bb) -> (EF !bb)             \n",
			"SPEC\n" + "AF cc -> EF cc                          \n",
			"SPEC\n" + "(AF (EG cc)) -> EG cc                   \n",
			"SPEC\n" + "EG cc -> (EX cc & EF cc)                \n",
			"SPEC\n" + "EF cc -> AF cc                          \n",
			"SPEC\n" + "EF cc                                   \n",
			"SPEC\n" + "(EG cc & AX cc) -> (EF !cc)             \n",
			"SPEC\n" + "EF EG bb                                \n" };
	private static String[] ctl_spec_fail = new String[] {
			"SPEC\n" + "EF EG !bb                               \n",
			"SPEC\n" + "EF aa -> AF aa                          \n",
			"SPEC\n" + "(AF (EG bb)) -> EG bb                   \n",
			"SPEC\n" + "EF AG !bb                               \n",
			"SPEC\n" + "EF AG bb                                \n" };

	public static OldModelCheckTC[] getAllCTLmcTC() {
		OldModelCheckTC[] res = new OldModelCheckTC[ctl_spec_succ.length
				+ ctl_spec_fail.length];
		for (int i = 0; i < ctl_spec_succ.length; i++) {
			res[i] = new OldModelCheckTC("ctl_mc_" + i, "testcases/simple_mc.smv",
					ctl_spec_succ[i], false);
		}
		for (int i = 0; i < ctl_spec_fail.length; i++) {
			int rsIdx = ctl_spec_succ.length + i;
			res[rsIdx] = new OldModelCheckTC("ctl_mc_" + rsIdx,
					"testcases/simple_mc.smv", ctl_spec_fail[i], true);
		}
		return res;
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("bdd", "jtlv");
		Env.resetEnv();
		SMVModule.initModulesWithoutRunningVar();
		Env.beQuiet();

		this.out_fos = new FileOutputStream(".junit_tmp.out", false);
		System.setOut(new PrintStream(out_fos));
		this.err_fos = new FileOutputStream(".junit_tmp.err", false);
		System.setErr(new PrintStream(err_fos));

		// loading module.
		Env.loadModule(this.filename);
		this.design = (SMVModule) Env.getModule("main");
		// loading specification.
		if (this.spc != null) // deadlock test cases doesn't have spec.
			this.specification = Env.loadSpecString(this.spc);
	}

	@After
	public void tearDown() throws Exception {
		this.out_fos.close();
		String output = readStream(new File(".junit_tmp.out"));
		this.err_fos.close();
		String error = readStream(new File(".junit_tmp.err"));
		if (this.isFailingTest) {
			String message = "\nFAILED: expecting error, but it is empty,\n"
					+ "while output is:" + output;
			assertTrue(message, !error.equals(""));
		} else {
			String message = "\nFAILED: expecting error to be empty, but is:"
					+ error + "\n" + "while output is:" + output;
			assertTrue(message, (!output.equals("")) && error.equals(""));
		}
		if (AllTests.rmLogs) {
			new File(".junit_tmp.out").delete();
			new File(".junit_tmp.err").delete();
		}
	}

	@Test
	public void deadlock_0() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkDeadlockStrandardOutput();
	}

	@Test
	public void deadlock_1() throws ModelCheckException, ModuleException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		BDD old_initials = this.design.initial().id();
		this.design.setInitial(Env.TRUE());
		BDD[] old_sub_initials = new BDD[this.design.getAllInstances().length];
		SMVModule[] sub = this.design.getAllInstances();
		for (int i = 0; i < sub.length; i++) {
			old_sub_initials[i] = sub[i].initial().id();
			sub[i].setInitial(Env.TRUE());
		}

		checker.checkDeadlockStrandardOutput();
		// returning the initials.
		this.design.setInitial(old_initials);
		for (int i = 0; i < sub.length; i++)
			sub[i].setInitial(old_sub_initials[i]);
	}

	@Test
	public void invariance_0() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkInvarianceStrandardOutput(this.specification[0]);
	}

	@Test
	public void invariance_1() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkInvarianceStrandardOutput(this.specification[0]);
	}

	@Test
	public void temp_entail_0() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkTempEntailStrandardOutput(this.specification[0], this.specification[1]);
	}

	@Test
	public void temp_entail_1() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkTempEntailStrandardOutput(this.specification[0], this.specification[1]);
	}
	@Test
	public void temp_entail_2() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkTempEntailStrandardOutput(this.specification[0], this.specification[1]);
	}
	@Test
	
	public void temp_entail_3() throws ModelCheckException {
		SimpleModelChecker checker = new SimpleModelChecker(this.design, true);
		checker.checkTempEntailStrandardOutput(this.specification[0], this.specification[1]);
	}



	@Test
	public void sa_valid_0() {
		standAloneValid();
	}

	@Test
	public void sa_valid_1() {
		standAloneValid();
	}

	@Test
	public void sa_valid_2() {
		standAloneValid();
	}

	@Test
	public void sa_valid_3() {
		standAloneValid();
	}

	@Test
	public void sa_valid_4() {
		standAloneValid();
	}

	@Test
	public void sa_valid_5() {
		standAloneValid();
	}

	@Test
	public void sa_valid_6() {
		standAloneValid();
	}

	@Test
	public void sa_valid_7() {
		standAloneValid();
	}

	private void standAloneValid() {
		LTLModelChecker.standAloneValidStarndardOutput(this.specification[0]);
	}

	@Test
	public void valid_0() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_1() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_2() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_3() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_4() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_5() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_6() throws ModelCheckException {
		valid();
	}

	@Test
	public void valid_7() throws ModelCheckException {
		valid();
	}

	public void valid() throws ModelCheckException {
		LTLModelChecker checker = new LTLModelChecker(this.design, true);
		checker.validStarndardOutput(this.specification[0]);
	}

	@Test
	public void ltl_mc_0() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_1() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_2() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_3() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_4() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_5() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_6() throws ModelCheckException {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_7() throws ModelCheckException {
		LTLmodelCheck();
	}

	public void LTLmodelCheck() throws ModelCheckException {
		LTLModelChecker checker = new LTLModelChecker(this.design, true);
		checker.modelCheckStandardOutput(this.specification[0]);
	}

	@Test
	public void ctl_mc_0() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_1() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_2() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_3() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_4() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_5() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_6() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_7() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_8() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_9() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_10() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_11() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_12() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_13() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_14() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_15() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_16() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_17() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_18() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_19() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_20() throws ModelCheckException {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_21() throws ModelCheckException {
		CTLmodelCheck();
	}

	public void CTLmodelCheck() throws ModelCheckException {
		CTLModelChecker checker = new CTLModelChecker(this.design, true);
		checker.modelCheckStandardOutput(this.specification[0]);
	}

	private String readStream(File f) throws IOException {
		String res = "";
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader in = new BufferedReader(isr);
		String next_read = in.readLine();
		while (next_read != null) {
			res += "\n" + next_read;
			next_read = in.readLine();
		}
		fis.close();
		return res;
	}
}