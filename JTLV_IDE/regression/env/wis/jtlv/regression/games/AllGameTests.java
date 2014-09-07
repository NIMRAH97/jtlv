package env.wis.jtlv.regression.games;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllGameTests {

	public static Test suite() {
		TestSuite game_suite = new TestSuite("Tests suite for Games.");

		// adding sub testing suites
		// game_suite.addTest(....suite());

		// adding the actual tests
		game_suite.addTest(new BugyGamesTC());

		return game_suite;
	}
}
