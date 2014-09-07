package env.wis.jtlv.regression.fds;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllFDSTests {

	public static Test suite() {
		TestSuite fds_suite = new TestSuite("Tests suite for FDS.");

		// adding sub testing suites
		// fds_suite.addTest(....suite());

		// adding the actual tests
		fds_suite.addTest(new FDSTestCaseTemplate());

		return fds_suite;
	}
}
