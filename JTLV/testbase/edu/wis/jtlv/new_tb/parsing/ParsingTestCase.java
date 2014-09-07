package edu.wis.jtlv.new_tb.parsing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.core.smv.StructuralInit;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.new_tb.TestCaseUtil;

public class ParsingTestCase extends TestCase {

	public String file_name;
	public boolean save_ref = false;

	public ParsingTestCase(String name) {
		super(name);
	}

	public void set_file(String a_file_name) {
		this.file_name = a_file_name;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public String out_log, out_ref;
	public String err_log, err_ref;

	public void pre_test(String factory_name) throws Exception {
		Env.reset_env();
		Env.beQuiet();
		StructuralInit.get_instance().do_phase_end();
		
		this.out_log = this.file_name + ".jtlv." + factory_name + ".out";
		this.err_log = this.file_name + ".jtlv." + factory_name + ".err";
		System.setOut(new PrintStream(new FileOutputStream(out_log, false)));
		System.setErr(new PrintStream(new FileOutputStream(err_log, false)));
		this.out_ref = this.file_name + ".jtlv.out.ref";
		this.err_ref = this.file_name + ".jtlv.err.ref";
	}

	public void post_test(String factory_name) {
		System.out.close();
		System.err.close();
	}

	public void do_test(String factory_name) {
		try {
			SMVModule.load_from_file(file_name);
			Set<String> all = Env.all_modules.keySet();
			for (Iterator<String> iter_names = all.iterator(); iter_names
					.hasNext();) {
				String key = iter_names.next();
				System.out.println("=========================================");
				System.out.println(Env.all_modules.get(key));

			}
		} catch (Exception e) {
			Env.doError(e, e.toString());
		}

		if (save_ref) {
			try {
				TestCaseUtil.copy(out_log, out_ref);
				TestCaseUtil.copy(err_log, err_ref);
			} catch (IOException e) {
				assertFalse(true);
			}
		} else {
			assertFalse(TestCaseUtil.diff(out_log, out_ref));
			assertFalse(TestCaseUtil.diff(err_log, err_ref));
		}
	}

//	@org.junit.Test
//	public void testBuDDy() {
//		System.setProperty("bdd", "buddy");
//		try {
//			pre_test("buddy");
//			do_test("buddy");
//			post_test("buddy");
//		} catch (Exception e) {
//			assertTrue("Couldn't initialize test.", false);
//		}
//	}

	// @org.junit.Test
	// public void testCUDD() {
	// System.setProperty("bdd", "cudd");
	// try {
	// pre_test("cudd");
	// do_test("cudd");
	// post_test("cudd");
	// } catch (Exception e) {
	// assertTrue("Couldn't initialize test.", false);
	// }
	// }

	// @org.junit.Test
	// public void testCAL() {
	// System.setProperty("bdd", "cal");
	// try {
	// pre_test("cal");
	// do_test("cal");
	// post_test("cal");
	// } catch (Exception e) {
	// assertTrue("Couldn't initialize test.", false);
	// }
	// }

	@org.junit.Test
	public void testJava() {
		System.setProperty("bdd", "java");
		try {
			pre_test("java");
			do_test("java");
		} catch (Exception e) {
			assertTrue("Couldn't initialize test.", false);
		}
		post_test("java");
	}

//	@org.junit.Test
//	public void testJDD() {
//		System.setProperty("bdd", "jdd");
//		try {
//			pre_test("jdd");
//			do_test("jdd");
//		} catch (Exception e) {
//			assertTrue("Couldn't initialize test.", false);
//		}
//		post_test("jdd");
//	}

	// @org.junit.Test
	// public void testMicro() {
	// System.setProperty("bdd", "micro");
	// try {
	// pre_test("micro");
	// do_test("micro");
	// post_test("micro");
	// } catch (Exception e) {
	// assertTrue("Couldn't initialize test.", false);
	// }
	// }

	// @org.junit.Test
	// public void testUberMicro() {
	// System.setProperty("bdd", "u");
	// try {
	// pre_test("uber");
	// do_test("uber");
	// post_test("uber");
	// } catch (Exception e) {
	// assertTrue("Couldn't initialize test.", false);
	// }
	// }

	// @org.junit.Test
	// public void testTyped() {
	// System.setProperty("bdd", "typed");
	// try {
	// pre_test("typed");
	// do_test("typed");
	// post_test("typed");
	// } catch (Exception e) {
	// assertTrue("Couldn't initialize test.", false);
	// }
	// }

}