package env.wis.jtlv.regression.spec;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllMCTests {

	public static Test suite() {
		TestSuite spec_suite = new TestSuite("Tests suite for Model Checking.");

		// adding sub testing suites
		// spec_suite.addTest(....suite());

		// //////////////////////////////////////
		// model checking test cases.
		ModelCheckAlgTC[] all_deadlock = ModelCheckAlgTC.getAllDeadlockTC();
		for (int i = 0; i < all_deadlock.length; i++) {
			spec_suite.addTest(all_deadlock[i]);
		}

		// invariance check.
		ModelCheckAlgTC[] all_invariance = ModelCheckAlgTC.getAllInvarianceTC();
		for (int i = 0; i < all_invariance.length; i++) {
			spec_suite.addTest(all_invariance[i]);
		}

		// tempEntail check.
		ModelCheckAlgTC[] all_temp_entail = ModelCheckAlgTC
				.getAllTempEntailTC();
		for (int i = 0; i < all_temp_entail.length; i++) {
			spec_suite.addTest(all_temp_entail[i]);
		}

		// react check.
		ModelCheckAlgTC[] all_react = ModelCheckAlgTC.getAllReactTC();
		for (int i = 0; i < all_react.length; i++) {
			spec_suite.addTest(all_react[i]);
		}

		// stand alone valid check.
		ModelCheckAlgTC[] all_sa_valid = ModelCheckAlgTC.getAllSAValidTC();
		for (int i = 0; i < all_sa_valid.length; i++) {
			spec_suite.addTest(all_sa_valid[i]);
		}

		// valid with design check.
		ModelCheckAlgTC[] all_valid = ModelCheckAlgTC.getAllValidTC();
		for (int i = 0; i < all_valid.length; i++) {
			spec_suite.addTest(all_valid[i]);
		}

		// LTL model check.
		ModelCheckAlgTC[] all_ltl_mc = ModelCheckAlgTC.getAllLTLmcTC();
		for (int i = 0; i < all_ltl_mc.length; i++) {
			spec_suite.addTest(all_ltl_mc[i]);
		}
		// CTL model check.
		ModelCheckAlgTC[] all_ctl_mc = ModelCheckAlgTC.getAllCTLmcTC();
		for (int i = 0; i < all_ctl_mc.length; i++) {
			spec_suite.addTest(all_ctl_mc[i]);
		}

		spec_suite.addTest(old_suite());
		
		return spec_suite;
	}

	public static Test old_suite() {
		TestSuite spec_suite = new TestSuite("Tests suite for the old Model "
				+ "Checking implementations.");
		// //////////////////////////////////////
		// model checking test cases.

		// deadlock check.
		OldModelCheckTC[] all_deadlock = OldModelCheckTC.getAllDeadlockTC();
		for (int i = 0; i < all_deadlock.length; i++) {
			spec_suite.addTest(all_deadlock[i]);
		}

		// invariance check.
		OldModelCheckTC[] all_invariance = OldModelCheckTC.getAllInvarianceTC();
		for (int i = 0; i < all_invariance.length; i++) {
			spec_suite.addTest(all_invariance[i]);
		}

		// tempEntail check.
		OldModelCheckTC[] all_temp_entail = OldModelCheckTC
				.getAllTempEntailTC();
		for (int i = 0; i < all_temp_entail.length; i++) {
			spec_suite.addTest(all_temp_entail[i]);
		}

		// react check.
		OldModelCheckTC[] all_react = OldModelCheckTC.getAllReactTC();
		for (int i = 0; i < all_react.length; i++) {
			spec_suite.addTest(all_react[i]);
		}

		// stand alone valid check.
		OldModelCheckTC[] all_sa_valid = OldModelCheckTC.getAllSAValidTC();
		for (int i = 0; i < all_sa_valid.length; i++) {
			spec_suite.addTest(all_sa_valid[i]);
		}

		// valid with design check.
		OldModelCheckTC[] all_valid = OldModelCheckTC.getAllValidTC();
		for (int i = 0; i < all_valid.length; i++) {
			spec_suite.addTest(all_valid[i]);
		}

		// LTL model check.
		OldModelCheckTC[] all_ltl_mc = OldModelCheckTC.getAllLTLmcTC();
		for (int i = 0; i < all_ltl_mc.length; i++) {
			spec_suite.addTest(all_ltl_mc[i]);
		}
		// CTL model check.
		OldModelCheckTC[] all_ctl_mc = OldModelCheckTC.getAllCTLmcTC();
		for (int i = 0; i < all_ctl_mc.length; i++) {
			spec_suite.addTest(all_ctl_mc[i]);
		}

		return spec_suite;
	}

}
