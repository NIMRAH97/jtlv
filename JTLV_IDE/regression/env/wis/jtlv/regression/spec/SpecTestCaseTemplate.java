package env.wis.jtlv.regression.spec;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.spec.Spec;

public class SpecTestCaseTemplate extends TestCase {

	public SpecTestCaseTemplate() {
		// setting the procedure to execute as a test.
		super("SpecTestTemplate");
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("bdd", "java");
		Env.resetEnv();
		Env.beQuiet();
	}
	
	Spec[] results;
	@After
	public void tearDown() throws Exception {
		// check is all is OK...
		// assertFalse(...boolean statement...);
		// assertTrue(...boolean statement...);
		// (you can also do the checks during the test() procedure.
	}

	@Test
	public void SpecTestTemplate() {
		
	}
}