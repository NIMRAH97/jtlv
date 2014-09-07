import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.env.spec.SpecException;
import edu.wis.jtlv.old_lib.games.GameException;
import edu.wis.jtlv.old_lib.games.RkGame;
import edu.wis.jtlv.old_lib.games.RkGame.ImplicationEntity;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 * 
 */
public class RkGameMain {

	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// System.setProperty("bdd", "j");
			System.out.print("== Strating algorithm ======================\n");
			String ex_name = "";
			ex_name = "single_arbiter2";
			//ex_name = "split_single_arbiter2";
			//ex_name = "dual_arbiter2";
			//ex_name = "split_dual_arbiter2";
			//ex_name = "split_test_cases/nir_test";
				
			// 1. load SMV files /////////////////////////////////////////////
			String smv_input_file = "./" + ex_name + ".smv";
			System.out.print("== Loading players... ");
			Env.loadModule(smv_input_file);
			Module cx = Env.getModule("main");
			Module player1 = Env.getModule("main.e");
			Module player2 = Env.getModule("main.s");
			System.out.print("Done\n");
			// System.out.print("---------------------------------------------\n");
			// if (player1 instanceof SMVModule)
			// ((SMVModule) player1).setFullPrintingMode(true);
			// System.out.println(player1);
			// System.out.print("---------------------------------------------\n");
			// if (player2 instanceof SMVModule)
			// ((SMVModule) player2).setFullPrintingMode(true);
			// System.out.println(player2);
			// System.out.print("---------------------------------------------\n");

			System.out.print("== Loading specifications... ");
			String spc_input_file = "./" + ex_name + "_spec.spc";
			Spec[] specs = Env.loadSpecFile(spc_input_file);
			Set<ImplicationEntity> pairs = new HashSet<ImplicationEntity>();
			// fill in the pairs...
			for (int i = 0; i < specs.length; i = i + 2) {
				try {
					BDD g = specs[i].toBDD();
					BDD r = specs[i + 1].toBDD();
					pairs.add(new ImplicationEntity(g, r));
				} catch (SpecException e) {
					// toBDD() isn't suppose to get here since all
					// specifications are first order.
					e.printStackTrace();
				}
			}
			System.out.print("Done\n");

			System.out.print("== Constructing and playing the game...\n");
			RkGame g = new RkGame(player1, player2, pairs);
			// System.out.println("Player sys winning states are:");
			// System.out.println(Env.toNiceString(cx, g.sysWinningStates()));
			// System.out.println("Player env winning states are:");
			// System.out.println(Env.toNiceString(cx, g.envWinningStates()));

			BDD all_init = g.getSysPlayer().initial().and(
					g.getEnvPlayer().initial());
			BDD counter_exmple = g.envWinningStates().and(all_init);
			if (!counter_exmple.isZero()) {
				System.out.print("Specification is unrealizable...\n");
				System.out.print("The env player can win from states:\n\t");
				System.out.print(Env.toNiceString(cx, counter_exmple) + "\n");
			} else {
				System.out.print("Specification is realizable...\n");
				System.out.print("== Building an implementation...\n");
				System.out.print("---------------------------------------\n");
				g.printWinningStrategy();
				System.out.print("---------------------------------------\n");
			}
			System.out.print("== Done ====================================\n");
		} catch (GameException e) {
			// error in the game...
			e.printStackTrace();
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
		}
	}
}
