package env.wis.jtlv.regression;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wis.jtlv.env.Env;

public class TestCaseTemplate extends TestCase {

	public TestCaseTemplate() {
		// setting the procedure to execute as a test.
		super("JTLVTestTemplate"); 
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("bdd", "jtlv");
		// System.setProperty("bdd", "java");
		// System.setProperty("bdd", "buddy");
		// System.setProperty("bdd", "micro");
		// ..
		Env.resetEnv();
		Env.beQuiet();
	}

	@After
	public void tearDown() throws Exception {
		// check is all is OK...
		// assertFalse(...boolean statement...);
		// assertTrue(...boolean statement...);
		// (you can also do the checks during the test() procedure.
	}

	@Test
	public void JTLVTestTemplate() {
		// do your tests...
	}
}