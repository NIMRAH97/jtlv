package env.wis.jtlv.regression.smv.for_loops;

import java.io.IOException;

import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.SMVModule;
import env.wis.jtlv.regression.TestCaseUtil;
import env.wis.jtlv.regression.smv.ParsingTestCase;

public class ConstructorForLoopsTC extends ParsingTestCase {
	public static final String foldername = "./testcases/constructor_for_loops/";

	public ConstructorForLoopsTC(String testname) {
		this(testname, false);
	}

	public ConstructorForLoopsTC(String testname, boolean save_ref) {
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
	public void simple1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple3() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple4() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple5() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple6() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple7() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple8() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple9() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple10() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple11() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple12() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void simple13() throws IOException {
		this.doParsingTest();
	}

}
