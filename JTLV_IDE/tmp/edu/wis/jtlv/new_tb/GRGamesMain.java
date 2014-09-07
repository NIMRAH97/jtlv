package edu.wis.jtlv.new_tb;

/**
 * 
 */

import java.io.IOException;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.old_lib.games.GR1Game;

/**
 * @author yaniv
 * 
 */
public class GRGamesMain {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String input_file = "./testcases/games_tc/arbiter2_with_spec.smv";

		try {
			System.out.print("===== Strating synthesis algorithm ==========\n");
			Env.loadModule(input_file);
			SMVModule player1 = (SMVModule) Env.getModule("main.e");
			SMVModule player2 = (SMVModule) Env.getModule("main.s");
			player1.setFullPrintingMode(true);
			player2.setFullPrintingMode(true);

			System.out.print("===== Loaded players ========================\n");
			System.out.print("---------------------------------------------\n");
			// System.out.println(Env.toNiceString(player1)); // !! this print
			// is exponential !!!
			System.out.print("---------------------------------------------\n");
			// System.out.println(Env.toNiceString(player2)); // !! this print
			// is exponential !!!
			System.out.print("---------------------------------------------\n");

			System.out.print("===== Constructing and playing the game =====\n");
			GR1Game g = new GR1Game(player1, player2);
			System.out.print("Player sys winning states are:\n\t");
			// System.out.print(Env.toNiceString(g.sys_winning_states()) +
			// "\n");
			System.out.print("Player env winning states are:\n\t");
			// System.out.print(Env.toNiceString(g.env_winning_states()) +
			// "\n");

			BDD all_init = g.getSysPlayer().initial().and(
					g.getEnvPlayer().initial());
			BDD counter_exmple = g.envWinningStates().and(all_init);
			if (!counter_exmple.isZero()) {
				System.out.print("Specification is unrealizable...\n");
				System.out
						.print("The env player can win from initial states:\n\t");
				System.out.print(counter_exmple
						.toStringWithDomains(Env.stringer));
			} else {
				System.out.print("Specification is realizable...\n");
				System.out.print("===== Building an implementation =======\n");
				System.out.print("----------------------------------------\n");
				// System.out.println(Env.toNiceString(g.calculate_strategy()));
				g.printWinningStrategy();
				System.out.print("----------------------------------------\n");
			}
			System.out.print("===== Done =============================\n");
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
			System.exit(0);
		}
	}
}
