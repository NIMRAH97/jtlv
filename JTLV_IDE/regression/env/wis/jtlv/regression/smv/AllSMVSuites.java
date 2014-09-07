package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllSMVSuites {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for SMV.");

		// adding sub testing suites
		smv_suite.addTest(SuiteSMVMisc.suite());
		smv_suite.addTest(SuiteSMVForLoops.suite());
		smv_suite.addTest(SMVConstructorForLoopsTests.suite());
		smv_suite.addTest(SuiteSMVMixedNestedForLoops.suite());
		smv_suite.addTest(SuiteSMVLegacyTests.suite());

		return smv_suite;
	}
}
