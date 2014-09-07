package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteSMVMisc {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for simple for loops.");

		// adding sub testing suites
		// smv_suite.addTest(....suite());

		// adding the actual tests
		smv_suite.addTest(new SMVMiscTC("assign_plus"));
		smv_suite.addTest(new SMVMiscTC("arr_ref1"));
		smv_suite.addTest(new SMVMiscTC("arr_ref2"));
		smv_suite.addTest(new SMVMiscTC("assign_on_arr_index1"));
		smv_suite.addTest(new SMVMiscTC("assign_on_arr_index2"));
		smv_suite.addTest(new SMVMiscTC("var_define1"));
		smv_suite.addTest(new SMVMiscTC("var_define2"));
		smv_suite.addTest(new SMVMiscTC("var_define3"));
		
		return smv_suite;
	}
}
