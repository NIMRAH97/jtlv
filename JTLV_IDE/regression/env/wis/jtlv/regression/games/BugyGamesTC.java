package env.wis.jtlv.regression.games;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleWithWeakFairness;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.experimental.games.ParallelGRGame;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 */
public class BugyGamesTC extends TestCase {

	public BugyGamesTC() {
		// setting the procedure to execute as a test.
		super("BugyMultiThreadedGameTC");
	}

	public static int N = 4;
	public static String tc_dir = "./testcases/games_tc/";
	public static String input_file = tc_dir + "arbiter" + N + "_with_spec.smv";
	public static String ord_file = tc_dir + "arbiter" + N + "_with_spec.ord";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void BugyMultiThreadedGameTC() {
		System.setProperty("bdd", "jtlv");
		System.setProperty("pool_size", "" + N);
		System.setProperty("bdd_table_size", "10000");
		System.setProperty("bdd_cache_size", "10000");
		Env.resetEnv();
		Env.beQuiet();
		SMVModule.initModulesWithoutRunningVar();

		try {
			// 1. SMV example /////////////////////////////////////////////////
			Env.loadModule(input_file);
			// Env.loadModule(input_file, ord_file);
			ModuleWithWeakFairness player1, player2;
			player1 = (ModuleWithWeakFairness) Env.getModule("main.e");
			player2 = (ModuleWithWeakFairness) Env.getModule("main.s");

			ParallelGRGame g = new ParallelGRGame(player1, player2);

			BDD all_init = g.gameInitials();
			BDD counter_exmple = g.playersWinningStates()[0].and(all_init);
			if (!counter_exmple.isZero()) {
				assertTrue("The Game failed.", false);
			} else {
				assertTrue("Game has strategy.", true);
				// throws a run time null pointer exception...
				g.printWinningStrategy();
			}
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
			e.printStackTrace();
		}
	}
}
