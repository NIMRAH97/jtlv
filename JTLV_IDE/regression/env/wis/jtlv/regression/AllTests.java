package env.wis.jtlv.regression;

import junit.framework.Test;
import junit.framework.TestSuite;
import env.wis.jtlv.regression.fds.AllFDSTests;
import env.wis.jtlv.regression.games.AllGameTests;
import env.wis.jtlv.regression.smv.AllSMVSuites;
import env.wis.jtlv.regression.spec.AllMCTests;
import env.wis.jtlv.regression.spec.AllSpecTests;

public class AllTests {

	public static boolean rmLogs = true;
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Main tests suite for JTLV.");

		// adding sub testing suites
		suite.addTest(AllSMVSuites.suite());
		suite.addTest(AllSpecTests.suite());
		suite.addTest(AllMCTests.suite());
		suite.addTest(AllFDSTests.suite());
		suite.addTest(AllGameTests.suite());

		// adding the actual tests
		suite.addTest(new TestCaseTemplate());

		return suite;
	}
}
