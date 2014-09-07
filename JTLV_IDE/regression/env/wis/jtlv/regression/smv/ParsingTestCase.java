package env.wis.jtlv.regression.smv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import env.wis.jtlv.regression.AllTests;
import env.wis.jtlv.regression.TestCaseUtil;

/**
 * Upon given a file to test,<br>
 * <filename>.jtlv.out.ref is what the output stream is suppose to give.<br>
 * <filename>.jtlv.err.ref is what the error stream is suppose to give.<br>
 * <filename>.jtlv.<factory>.out is what this current run has written to the
 * output stream.<br>
 * <filename>.jtlv.<factory>.err is what this current run has written to the
 * error stream.<br>
 * </p>
 * 
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * 
 */
public class ParsingTestCase extends TestCase {
	protected String filename;
	protected boolean save_ref;
	private String out_ref, err_ref;
	private String out_log, err_log;

	/**
	 * This testcase should not be instantiated. Only implementor can
	 * instantiate this test.
	 * 
	 * @param testname
	 *            The procedure to run at test.
	 * @param filename
	 *            The file which this test should run.
	 * @param save_ref
	 *            whether to save reference or to compare.
	 */
	protected ParsingTestCase(String testname, String filename, boolean save_ref) {
		super(testname); // test procedures
		this.filename = filename;
		this.save_ref = save_ref;
	}

	@Before
	public void setUp() throws Exception {
		// ///////////////////////
		// reseting the environment for a new run.
		System.setProperty("bdd", "jtlv");
		SMVModule.initModulesWithRunningVar();
		Env.resetEnv();
		Env.beQuiet();

		// ///////////////////////
		// setting the references for this run.
		out_ref = filename + ".jtlv.out.ref";
		err_ref = filename + ".jtlv.err.ref";

		// ///////////////////////
		// preparing the logs for this run.
		String factory_name = System.getProperty("bdd", "java");
		out_log = filename + ".jtlv." + factory_name + ".out";
		err_log = filename + ".jtlv." + factory_name + ".err";
		System.setOut(new PrintStream(new FileOutputStream(out_log, false)));
		System.setErr(new PrintStream(new FileOutputStream(err_log, false)));
	}

	@After
	public void tearDown() throws Exception {
		// ///////////////////////
		// checking if the out put is different then what was expected.
		try {
			if (save_ref) {
				try {
					TestCaseUtil.copy(out_log, out_ref);
					TestCaseUtil.copy(err_log, err_ref);
				} catch (IOException e) {
					fail("could not save reference.");
				}
			} else {
				String out_diff = TestCaseUtil.diff(out_log, out_ref);
				assertTrue(out_diff, (out_diff == null)
						|| (out_diff.equals("")));
				String err_diff = TestCaseUtil.diff(err_log, err_ref);
				assertTrue(err_diff, (err_diff == null)
						|| (err_diff.equals("")));
			}

			System.out.close();
			System.err.close();
		} finally {
			if (AllTests.rmLogs) {
				new File(out_log).delete();
				new File(err_log).delete();
			}
			SMVModule.initModulesWithoutRunningVar();
		}
	}

	public void doParsingTest() throws IOException {
		// the actual test...
		Env.loadModule(filename);
		String[] names = TestCaseUtil.sortStringSet(Env.getModuleNames());
		for (String key : names) {
			System.out.println("=========================================");
			// Module m = Env.getModule(key);
			// if (m instanceof SMVModule)
			// ((SMVModule) m).setFullPrintingMode(true);
			System.out.println(Env.getModule(key));

		}
	}
}