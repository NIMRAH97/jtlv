package env.wis.jtlv.regression.spec;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllSpecTests {

	public static Test suite() {
		TestSuite spec_suite = new TestSuite("Tests suite for Specifications.");

		// adding sub testing suites
		// spec_suite.addTest(....suite());

		// adding the actual tests
		spec_suite.addTest(new SpecTestCaseTemplate());
		spec_suite.addTest(new CounterStringLoadTestcases(0));
		spec_suite.addTest(new CounterStringLoadTestcases(1));
		spec_suite.addTest(new CounterStringLoadTestcases(2));
		spec_suite.addTest(new CounterStringLoadTestcases(3));
		spec_suite.addTest(new CounterStringLoadTestcases(4));
		spec_suite.addTest(new CounterStringLoadTestcases(5));
		spec_suite.addTest(new CounterStringLoadTestcases(6));
		spec_suite.addTest(new CounterStringLoadTestcases(7));
		spec_suite.addTest(new CounterStringLoadTestcases(8));
		spec_suite.addTest(new CounterStringLoadTestcases(9));
		spec_suite.addTest(new CounterStringLoadTestcases(10));
		spec_suite.addTest(new CounterStringLoadTestcases(11));
		spec_suite.addTest(new CounterStringLoadTestcases(12));
		spec_suite.addTest(new CounterStringLoadTestcases(13));
		spec_suite.addTest(new CounterStringLoadTestcases(14));
		spec_suite.addTest(new CounterStringLoadTestcases(15));
		spec_suite.addTest(new CounterStringLoadTestcases(16));
		spec_suite.addTest(new CounterStringLoadTestcases(17));
		spec_suite.addTest(new CounterStringLoadTestcases(18));
		spec_suite.addTest(new CounterStringLoadTestcases(19));
		spec_suite.addTest(new CounterStringLoadTestcases(20));
		spec_suite.addTest(new CounterStringLoadTestcases(21));
		spec_suite.addTest(new CounterStringLoadTestcases(22));
		spec_suite.addTest(new CounterStringLoadTestcases(23));
		spec_suite.addTest(new CounterStringLoadTestcases(24));
		spec_suite.addTest(new CounterStringLoadTestcases(25));
		spec_suite.addTest(new CounterStringLoadTestcases(26));
		spec_suite.addTest(new CounterStringLoadTestcases(27));

		return spec_suite;
	}
}
