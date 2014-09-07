package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;
import env.wis.jtlv.regression.smv.for_loops.SimpleForLoopsTC;

public class SuiteSMVForLoops {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for simple for loops.");

		// adding sub testing suites
		// smv_suite.addTest(....suite());

		// adding the actual tests
		smv_suite.addTest(new SimpleForLoopsTC("assign1"));
		smv_suite.addTest(new SimpleForLoopsTC("assign2"));
		smv_suite.addTest(new SimpleForLoopsTC("assign3"));
		smv_suite.addTest(new SimpleForLoopsTC("define1"));
		smv_suite.addTest(new SimpleForLoopsTC("define2"));
		smv_suite.addTest(new SimpleForLoopsTC("define3"));
		smv_suite.addTest(new SimpleForLoopsTC("define4"));
		smv_suite.addTest(new SimpleForLoopsTC("define5"));
		smv_suite.addTest(new SimpleForLoopsTC("define6"));
		smv_suite.addTest(new SimpleForLoopsTC("justice1"));
		smv_suite.addTest(new SimpleForLoopsTC("justice2"));
		smv_suite.addTest(new SimpleForLoopsTC("compassion1"));
		smv_suite.addTest(new SimpleForLoopsTC("empty1"));
		smv_suite.addTest(new SimpleForLoopsTC("index_op1"));

		return smv_suite;
	}
}
