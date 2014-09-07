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
import edu.wis.jtlv.lib.AlgResultI;
import edu.wis.jtlv.lib.AlgRunnerThread;
import edu.wis.jtlv.lib.AlgResultI.ResultStatus;
import edu.wis.jtlv.lib.mc.SimpleDeadlockAlg;
import edu.wis.jtlv.lib.mc.SimpleInvarianceAlg;
import edu.wis.jtlv.lib.mc.SimpleTempEntailAlg;
import edu.wis.jtlv.lib.mc.tl.CTLModelCheckAlg;
import edu.wis.jtlv.lib.mc.tl.LTLModelCheckAlg;
import edu.wis.jtlv.lib.mc.tl.LTLValidAlg;
import env.wis.jtlv.regression.AllTests;

public class ModelCheckAlgTC extends TestCase {
	private FileOutputStream out_fos;
	private FileOutputStream err_fos;
	private String filename;
	private SMVModule design;
	private Spec[] specification;
	private String spc;
	private boolean isFailingTest;

	private ModelCheckAlgTC(String testname, String design_filename,
			String spc_str, boolean isFailingTest) {
		// setting the procedure to execute as a test.
		super(testname);
		this.filename = design_filename;
		this.spc = spc_str;
		this.isFailingTest = isFailingTest;
	}

	public static ModelCheckAlgTC[] getAllDeadlockTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[2];
		res[0] = new ModelCheckAlgTC("deadlock_0", "testcases/mux-sem.smv",
				null, false);
		res[1] = new ModelCheckAlgTC("deadlock_1", "testcases/mux-sem.smv",
				null, true);
		return res;
	}

	private static String[] invariance_spec_succ = new String[] { "SPEC\n    !((proc[1].loc = 3) & (proc[2].loc = 3)) \n" };
	private static String[] invariance_spec_fail = new String[] { "SPEC\n    !((proc[1].loc = 3) & (proc[2].loc = 2)) \n" };

	public static ModelCheckAlgTC[] getAllInvarianceTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[2];
		res[0] = new ModelCheckAlgTC("invariance_0", "testcases/mux-sem.smv",
				invariance_spec_succ[0], false);
		res[1] = new ModelCheckAlgTC("invariance_1", "testcases/mux-sem.smv",
				invariance_spec_fail[0], true);
		return res;
	}

	private static String[] temp_entail_spec = new String[] { "SPEC\n  aa \n",
			"SPEC\n  bb \n", "SPEC\n  !bb \n", "SPEC\n ((!bb) & cc) \n", };

	public static ModelCheckAlgTC[] getAllTempEntailTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[4];
		res[0] = new ModelCheckAlgTC("temp_entail_0",
				"testcases/simple_mc.smv", temp_entail_spec[0]
						+ temp_entail_spec[0], false);
		res[1] = new ModelCheckAlgTC("temp_entail_1",
				"testcases/simple_mc.smv", temp_entail_spec[0]
						+ temp_entail_spec[1], false);
		res[2] = new ModelCheckAlgTC("temp_entail_2",
				"testcases/simple_mc.smv", temp_entail_spec[0]
						+ temp_entail_spec[2], true);
		res[3] = new ModelCheckAlgTC("temp_entail_3",
				"testcases/simple_mc.smv", temp_entail_spec[0]
						+ temp_entail_spec[3], true);
		return res;
	}

	public static ModelCheckAlgTC[] getAllReactTC() {
		return new ModelCheckAlgTC[] {};
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

	public static ModelCheckAlgTC[] getAllSAValidTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[sa_valid_spec_succ.length
				+ sa_valid_spec_fail.length];
		for (int i = 0; i < sa_valid_spec_succ.length; i++) {
			res[i] = new ModelCheckAlgTC("sa_valid_" + i,
					"testcases/simple_mc.smv", sa_valid_spec_succ[i], false);
		}
		for (int i = 0; i < sa_valid_spec_fail.length; i++) {
			int rsIdx = sa_valid_spec_succ.length + i;
			res[rsIdx] = new ModelCheckAlgTC("sa_valid_" + rsIdx,
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

	public static ModelCheckAlgTC[] getAllValidTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[valid_spec_succ.length
				+ valid_spec_fail.length];
		for (int i = 0; i < valid_spec_succ.length; i++) {
			res[i] = new ModelCheckAlgTC("valid_" + i,
					"testcases/simple_mc.smv", valid_spec_succ[i], false);
		}
		for (int i = 0; i < valid_spec_fail.length; i++) {
			int rsIdx = valid_spec_succ.length + i;
			res[rsIdx] = new ModelCheckAlgTC("valid_" + rsIdx,
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

	public static ModelCheckAlgTC[] getAllLTLmcTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[ltl_spec_succ.length
				+ ltl_spec_fail.length];
		for (int i = 0; i < ltl_spec_succ.length; i++) {
			res[i] = new ModelCheckAlgTC("ltl_mc_" + i,
					"testcases/simple_mc.smv", ltl_spec_succ[i], false);
		}
		for (int i = 0; i < ltl_spec_fail.length; i++) {
			int rsIdx = ltl_spec_succ.length + i;
			res[rsIdx] = new ModelCheckAlgTC("ltl_mc_" + rsIdx,
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

	public static ModelCheckAlgTC[] getAllCTLmcTC() {
		ModelCheckAlgTC[] res = new ModelCheckAlgTC[ctl_spec_succ.length
				+ ctl_spec_fail.length];
		for (int i = 0; i < ctl_spec_succ.length; i++) {
			res[i] = new ModelCheckAlgTC("ctl_mc_" + i,
					"testcases/simple_mc.smv", ctl_spec_succ[i], false);
		}
		for (int i = 0; i < ctl_spec_fail.length; i++) {
			int rsIdx = ctl_spec_succ.length + i;
			res[rsIdx] = new ModelCheckAlgTC("ctl_mc_" + rsIdx,
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
	public void deadlock_0() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleDeadlockAlg(this.design));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void deadlock_1() throws ModuleException {
		BDD old_initials = this.design.initial().id();
		this.design.setInitial(Env.TRUE());
		BDD[] old_sub_initials = new BDD[this.design.getAllInstances().length];
		SMVModule[] sub = this.design.getAllInstances();
		for (int i = 0; i < sub.length; i++) {
			old_sub_initials[i] = sub[i].initial().id();
			sub[i].setInitial(Env.TRUE());
		}

		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleDeadlockAlg(this.design));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());

		// returning the initials.
		this.design.setInitial(old_initials);
		for (int i = 0; i < sub.length; i++)
			sub[i].setInitial(old_sub_initials[i]);
	}

	@Test
	public void invariance_0() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleInvarianceAlg(this.design,
				this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void invariance_1() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleInvarianceAlg(this.design,
				this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void temp_entail_0() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleTempEntailAlg(this.design,
				this.specification[0], this.specification[1]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void temp_entail_1() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleTempEntailAlg(this.design,
				this.specification[0], this.specification[1]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void temp_entail_2() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleTempEntailAlg(this.design,
				this.specification[0], this.specification[1]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void temp_entail_3() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new SimpleTempEntailAlg(this.design,
				this.specification[0], this.specification[1]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
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
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new LTLValidAlg(this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void valid_0() {
		valid();
	}

	@Test
	public void valid_1() {
		valid();
	}

	@Test
	public void valid_2() {
		valid();
	}

	@Test
	public void valid_3() {
		valid();
	}

	@Test
	public void valid_4() {
		valid();
	}

	@Test
	public void valid_5() {
		valid();
	}

	@Test
	public void valid_6() {
		valid();
	}

	@Test
	public void valid_7() {
		valid();
	}

	public void valid() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new LTLValidAlg(this.design,
				this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void ltl_mc_0() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_1() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_2() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_3() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_4() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_5() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_6() {
		LTLmodelCheck();
	}

	@Test
	public void ltl_mc_7() {
		LTLmodelCheck();
	}

	public void LTLmodelCheck() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new LTLModelCheckAlg(this.design,
				this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
	}

	@Test
	public void ctl_mc_0() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_1() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_2() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_3() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_4() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_5() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_6() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_7() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_8() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_9() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_10() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_11() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_12() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_13() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_14() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_15() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_16() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_17() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_18() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_19() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_20() {
		CTLmodelCheck();
	}

	@Test
	public void ctl_mc_21() {
		CTLmodelCheck();
	}

	public void CTLmodelCheck() {
		AlgRunnerThread runner;
		runner = new AlgRunnerThread(new CTLModelCheckAlg(this.design,
				this.specification[0]));
		runner.runSequential();
		if (runner.getDoResult() != null) {
			AlgResultI res = runner.getDoResult();
			if (res.getResultStat() == ResultStatus.failed)
				System.err.println(runner.getDoResult().resultString());
			else
				System.out.println(runner.getDoResult().resultString());
		}
		if (runner.getDoException() != null)
			System.err.println(runner.getDoException().getMessage());
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