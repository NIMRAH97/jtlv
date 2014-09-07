package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;
import env.wis.jtlv.regression.smv.for_loops.MixedNestedForLoopsTC;

public class SuiteSMVMixedNestedForLoops {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for mixed nested for loops.");

		// adding sub testing suites
		// smv_suite.addTest(....suite());

		// adding the actual tests
		smv_suite.addTest(new MixedNestedForLoopsTC("nested1"));
		smv_suite.addTest(new MixedNestedForLoopsTC("nested2"));
		smv_suite.addTest(new MixedNestedForLoopsTC("nested3"));
		smv_suite.addTest(new MixedNestedForLoopsTC("nested4"));
		smv_suite.addTest(new MixedNestedForLoopsTC("nested5"));
		smv_suite.addTest(new MixedNestedForLoopsTC("nested6"));

		return smv_suite;
	}
}
