package env.wis.jtlv.regression.smv.for_loops;

import java.io.IOException;

import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.SMVModule;
import env.wis.jtlv.regression.TestCaseUtil;
import env.wis.jtlv.regression.smv.ParsingTestCase;

public class SimpleForLoopsTC extends ParsingTestCase {
	public static final String foldername = "./testcases/for_loops/";

	public SimpleForLoopsTC(String testname) {
		this(testname, false);
	}

	public SimpleForLoopsTC(String testname, boolean save_ref) {
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
	public void assign1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void assign2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void assign3() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define3() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define4() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define5() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void define6() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void justice1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void justice2() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void compassion1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void empty1() throws IOException {
		this.doParsingTest();
	}

	@Test
	public void index_op1() throws IOException {
		this.doParsingTest();
	}

}
