package edu.wis.jtlv.experimental.games;

import java.io.IOException;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleWithWeakFairness;
import edu.wis.jtlv.old_lib.games.GR1Game;
import edu.wis.jtlv.old_lib.games.Game;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 */
public class ParallelGRGamesMain {

	public static void parseArgs(String[] args) {
		// expecting "boolean do_parallel", "boolean do_sanity", "String
		// test_file_name", "int N"
		if ((args == null) || (args.length == 0))
			return; // do nothing leaving the static assignments
		if (args.length != 4)
			throw new RuntimeException("\nWrong usage, should be:\n"
					+ "\tjava ParallelGRGamesMain "
					+ "<boolean: \"true\"=parallel, \"false\"=sequential> "
					+ "<boolean:do sanity check> " + "<String:file to load> "
					+ "<int: thread pool size>");
		do_parallel = Boolean.parseBoolean(args[0]);
		do_sanity = Boolean.parseBoolean(args[1]);
		input_file = args[2];
		N = Integer.parseInt(args[3]);
	}

	public static boolean do_parallel = true;
	public static boolean do_sanity = true;
	public static int N = 10;
	public static String input_file = "./testcases/games_tc/arbiter" + N
			+ "_with_spec.smv";

	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		parseArgs(args);

		long start = System.currentTimeMillis();
		long curr_time;

		System.setProperty("bdd", "java");
		System.setProperty("bdd_table_size", "40000");
		System.setProperty("bdd_cache_size", "40000");
		System.setProperty("pool_size", "" + N);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

		Env.beQuiet();
		try {
			// 1. SMV example /////////////////////////////////////////////////
			System.out.print("==== Strating synthesis algorithm ========\n");
			System.out.print("===== Loading players ====================\n");
			Env.loadModule(input_file);
			ModuleWithWeakFairness player1, player2;
			player1 = (ModuleWithWeakFairness) Env.getModule("main.e");
			player2 = (ModuleWithWeakFairness) Env.getModule("main.s");

			curr_time = (System.currentTimeMillis() - start);
			System.out.print("==== Constructing and playing the game ======");
			System.out.print(" Time from start: " + curr_time + "\n");

			Game g = (do_parallel) ? new ParallelGRGame(player1, player2)
					: new GR1Game(player1, player2);

			curr_time = (System.currentTimeMillis() - start);
			System.out.print("===== Done ==================================");
			System.out.print(" Time from start: " + curr_time + "\n");

			if (do_sanity) {
				BDD all_init = g.gameInitials();
				BDD counter_exmple = g.playersWinningStates()[0].and(all_init);
				if (!counter_exmple.isZero()) {
					System.err.println("The Game failed.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
