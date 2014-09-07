package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;
import env.wis.jtlv.regression.smv.for_loops.ConstructorForLoopsTC;

public class SMVConstructorForLoopsTests {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for constructor for loops.");

		// adding sub testing suites
		// smv_suite.addTest(....suite());

		// adding the actual tests
		smv_suite.addTest(new ConstructorForLoopsTC("simple1"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple2"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple3"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple4"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple5"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple6"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple7"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple8"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple9"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple10"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple11"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple12"));
		smv_suite.addTest(new ConstructorForLoopsTC("simple13"));


		return smv_suite;
	}
}
