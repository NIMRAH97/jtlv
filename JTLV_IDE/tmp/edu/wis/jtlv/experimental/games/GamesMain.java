package edu.wis.jtlv.experimental.games;

import java.io.IOException;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleWithWeakFairness;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.old_lib.games.GR1Game;
import edu.wis.jtlv.old_lib.games.Game;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 */
public class GamesMain {
	public enum Kind {
		seq, par, new_par, check
	}

	public static int N = 20;
	public static Kind do_parallel = Kind.par;
	public static String tc_dir = "./testcases/games_tc/";
	public static String input_file = tc_dir + "arbiter" + N + "_with_spec.smv";
	public static String ord_file = tc_dir + "arbiter" + N + "_with_spec.ord";
	public static boolean do_sanity = true;

	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd_table_size", "50000");
		System.setProperty("bdd_cache_size", "10000");
		// System.setProperty("bdd", "jtlv");
		System.setProperty("pool_size", "" + 10);
		SMVModule.initModulesWithoutRunningVar();
		Env.resetEnv();
		// Env.beQuiet();

		long start = System.currentTimeMillis();
		float curr_time;
		curr_time = (float) (System.currentTimeMillis() - start) / 1000;
		System.out.print("==== Strating synthesis algorithm ========");
		System.out.print(" Time from start: " + curr_time + "\n");

		try {
			// 1. SMV example /////////////////////////////////////////////////
			curr_time = (float) (System.currentTimeMillis() - start) / 1000;
			System.out.print("==== Starting to Load players ============");
			System.out.print(" Time from start: " + curr_time + "\n");

			Env.loadModule(input_file, ord_file);
			// Env.loadModule(input_file, ord_file);
			ModuleWithWeakFairness player1, player2;
			player1 = (ModuleWithWeakFairness) Env.getModule("main.e");
			player2 = (ModuleWithWeakFairness) Env.getModule("main.s");
			// printOrder();

			// System.out.println(Env.toNiceString(player1.trans()));

			BDD check = Env.FALSE();
			BDD check_all_init = Env.FALSE();

			curr_time = (float) (System.currentTimeMillis() - start) / 1000;
			Game g = null;
			if (do_parallel == Kind.seq) {
				System.out.print("==== Constructing sequential game ========");
				System.out.print(" Time from start: " + curr_time + "\n");
				g = new GR1Game(player1, player2);
			} else if (do_parallel == Kind.par) {
				System.out.print("==== Constructing parallel game ==========");
				System.out.print(" Time from start: " + curr_time + "\n");
				g = new ParallelGRGame(player1, player2);
			} else if (do_parallel == Kind.check) {
				System.out.print("==== performaing check ==========");
				System.out.print(" Time from start: " + curr_time + "\n");
				/* check_for_identical_games(player1, player2); */
				do_sanity = false;
			} else if (do_parallel == Kind.new_par) {
				System.out.print("==== Constructing parallel disjoint game =");
				System.out.print(" Time from start: " + curr_time + "\n");
				curr_time = (float) (System.currentTimeMillis() - start) / 1000;
				System.out.print("==== Starting threads ====================");
				System.out.print(" Time from start: " + curr_time + "\n");

				int justice_num = player1.justiceNum();
				for (int i = 0; (10 * i) < justice_num; i++) {
					GDRGame[] gArr = new GDRGame[10];
					for (int j = 0; j < gArr.length; j++) {
						if (((10 * i) + j) >= justice_num)
							continue;
						gArr[j] = new GDRGame(player1, (10 * i) + j, player2);
					}
					for (int j = 0; j < gArr.length; j++) {
						if (((10 * i) + j) >= justice_num)
							continue;
						gArr[j].start();
					}
					for (int j = 0; j < gArr.length; j++) {
						if (((10 * i) + j) >= justice_num)
							continue;
						try {
							gArr[j].join();
						} catch (InterruptedException e) {
							j--; // try again
						}
					}
					if (g == null) {
						for (int j = 0; j < gArr.length; j++) {
							if (((10 * i) + j) >= justice_num)
								continue;
							BDD all_init = gArr[j].gameInitials();
							BDD counter_exmple = gArr[j]
									.firstPlayersWinningStates().and(all_init);
							if (counter_exmple.isZero()) {
								g = gArr[j];
							}
							check_all_init = gArr[j].gameInitials();
							check = check.or(gArr[j]
									.firstPlayersWinningStates());
						}
					}
				}
			} else {
				System.err.println("Unknown game type...");
			}

			curr_time = (float) (System.currentTimeMillis() - start) / 1000;
			System.out.print("==== Done ================================");
			System.out.print(" Time from start: " + curr_time + "\n");

			if (do_sanity) {
				if (do_parallel == Kind.new_par) {
					BDD counter_exmple = check.and(check_all_init);
					if (!counter_exmple.isZero()) {
						System.err.println("The Game failed.");
					} else {
						System.err.println("The Game is OK.");
					}
					// if (g == null) {
					// System.err.println("All envionment justice failed.");
					// } else {
					// g.printWinningStrategy();
					// }
				} else {
					// System.out.println("Environment winnings are:");
					// System.out.println(Env.toNiceString(g.firstPlayersWinningStates()));
					BDD all_init = g.gameInitials();
					BDD counter_exmple = g.playersWinningStates()[0]
							.and(all_init);
					if (!counter_exmple.isZero()) {
						System.err.println("The Game failed.");
					} else {
						System.out.println("Game has strategy.");
						//g.printWinningStrategy();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printOrder() {
		for (int i = 0; i <= N; i++) {
			int[] forder = Env.copyBDDIntoManager(i, Env.TRUE()).getFactory()
					.getVarOrder();
			System.out.println("factory " + i + " ordering is:");
			for (int j = 0; j < forder.length; j++) {
				System.out.print(forder[j] + ", ");
			}
			System.out.println("");
			System.out.println("======================");
		}
	}

	public static void parseArgs(String[] args) {
		// expecting "boolean do_parallel", "boolean do_sanity", "String
		// test_file_name", "int N"
		if ((args == null) || (args.length == 0))
			return; // do nothing leaving the static assignments
		if (args.length != 4)
			throw new RuntimeException("\nWrong usage, should be:\n"
					+ "\tjava ParallelGRGamesMain "
					+ "<kind: seq | par | new_partrue> "
					+ "<boolean : do sanity check> "
					+ "<String : file to load> " + "<int : thread pool size>");
		do_parallel = Enum.valueOf(Kind.class, args[0]);
		do_sanity = Boolean.parseBoolean(args[1]);
		input_file = args[2];
		N = Integer.parseInt(args[3]);
	}

	/*
	 * 
	 * public static void check_for_identical_games(ModuleWithWeakFairness
	 * player1, ModuleWithWeakFairness player2) { try { System.out.println("");
	 * ParallelGRGame p = new ParallelGRGame(player1, player2); GRGame s = new
	 * GRGame(player1, player2);
	 * 
	 * ASSERT("p.x_mem.length != s.x_mem.length", p.x_mem.length,
	 * s.x_mem.length); for (int i = 0; i < p.x_mem.length; i++) { ASSERT(
	 * "p.x_mem[" + i + "].length != s.x_mem[" + i + "].length",
	 * p.x_mem[i].length, s.x_mem[i].length); for (int j = 0; j <
	 * p.x_mem[i].length; j++) { ASSERT("p.x_mem[" + i + "][" + j +
	 * "].length != s.x_mem[" + i + "][" + j + "].length", p.x_mem[i][j].length,
	 * s.x_mem[i][j].length); for (int k = 0; k < p.x_mem[i][j].length; k++) {
	 * ASSERT("p.x_mem[" + i + "][" + j + "][" + k + "].biimp(s.x_mem[" + i +
	 * "][" + j + "][" + k + "]).isOne()", p.x_mem[i][j][k].toString(),
	 * s.x_mem[i][j][k].toString()); } } }
	 * 
	 * 
	 * ASSERT("p.y_mem.length != s.y_mem.length", p.y_mem.length,
	 * s.y_mem.length); for (int i = 0; i < p.y_mem.length; i++) { ASSERT(
	 * "p.y_mem[" + i + "].length != s.y_mem[" + i + "].length",
	 * p.y_mem[i].length, s.y_mem[i].length); for (int j = 0; j <
	 * p.y_mem[i].length; j++) { ASSERT("p.y_mem[" + i + "][" + j +
	 * "].biimp(s.y_mem[" + i + "][" + j + "]).isOne()",
	 * p.y_mem[i][j].toString(), s.y_mem[i][j].toString()); } }
	 * 
	 * 
	 * ASSERT("p.z_mem.length != s.z_mem.length", p.z_mem.length,
	 * s.z_mem.length); for (int i = 0; i < p.z_mem.length; i++) {
	 * ASSERT("p.z_mem[" + i + "].biimp(s.z_mem[" + i + "]).isOne()",
	 * p.z_mem[i].toString(), s.z_mem[i].toString()); }
	 * 
	 * } catch (GameException e) { e.printStackTrace(); }
	 * System.out.println("chack ended."); }
	 * 
	 * public static void ASSERT(String mes, String val1, String val2) { if
	 * (!val1.equals(val2)) System.out.println("Problem: " + mes); }
	 * 
	 * public static void ASSERT(String mes, int val1, int val2) { if (val1 !=
	 * val2) System.out.println("Problem: " + mes); }
	 */
}
