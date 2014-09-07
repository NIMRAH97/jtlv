package env.wis.jtlv.regression.smv.for_loops;

import java.io.IOException;

import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.SMVModule;
import env.wis.jtlv.regression.TestCaseUtil;
import env.wis.jtlv.regression.smv.ParsingTestCase;

public class MixedNestedForLoopsTC extends ParsingTestCase {
	public static final String foldername = "./testcases/for_loops/";

	public MixedNestedForLoopsTC(String testname) {
		this(testname, false);
	}

	public MixedNestedForLoopsTC(String testname, boolean save_ref) {
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
	public void nested1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void nested2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void nested3() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void nested4() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void nested5() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void nested6() throws IOException {
		this.doParsingTest();
	}

}
