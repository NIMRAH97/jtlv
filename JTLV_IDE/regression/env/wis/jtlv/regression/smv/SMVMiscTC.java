package env.wis.jtlv.regression.smv;

import java.io.IOException;

import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.SMVModule;
import env.wis.jtlv.regression.TestCaseUtil;
import env.wis.jtlv.regression.smv.ParsingTestCase;

public class SMVMiscTC extends ParsingTestCase {
	public static final String foldername = "./testcases/misc/";

	public SMVMiscTC(String testname) {
		this(testname, false);
	}

	public SMVMiscTC(String testname, boolean save_ref) {
		super(testname, foldername + testname + ".smv", save_ref);
	}

	public void doParsingTest() throws IOException {
		// the actual test...
		Env.loadModule(filename);
		String[] names = TestCaseUtil.sortStringSet(Env.getModuleNames());
		for (String key : names) {
			System.out.println("=========================================");
			Module m = Env.getModule(key);
			if (m instanceof SMVModule)
				((SMVModule) m).setFullPrintingMode(true);
			System.out.println(Env.getModule(key));

		}
	}

	@Test
	public void assign_plus() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void arr_ref1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void arr_ref2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void assign_on_arr_index1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void assign_on_arr_index2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void var_define1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void var_define2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void var_define3() throws IOException {
		this.doParsingTest();
	}

}
