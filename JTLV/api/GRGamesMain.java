/**
 * 
 */

import java.io.IOException;

import net.sf.javabdd.BDD;

import org.antlr.runtime.RecognitionException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.FDSModule;
import edu.wis.jtlv.lib.games.GRGame;
import edu.wis.jtlv.lib.games.IllegalGameException;

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
		String input_file;// , output_file;
		input_file = "/home/yaniv/workspace/JTLV/testcases/arbiter2_with_spec.fds";
		//input_file = args[0];
		// output_file =
		// "/home/yaniv/workspace/JTLV/testcases/arbiters.synt";
		// output_file = args[1];
		//input_file = "/home/yaniv/runtime-EclipseApplication/pde_testing/multiSR.fds";
		edu.wis.jtlv.env.JTLVBDDCouple.full_print = true;
		try {
			System.out.print("===== Strating synthesis algorithm ===============\n");
			FDSModule.load_from_file(input_file);
			FDSModule player1 = (FDSModule) Env.all_modules.get("env");
			FDSModule player2 = (FDSModule) Env.all_modules.get("sys");
			
			System.out.print("===== Loaded players =============================\n");
			System.out.print("--------------------------------------------------\n");
			//System.out.println(Env.toNiceString(player1)); // !! this print is exponential !!!
			System.out.print("--------------------------------------------------\n");
			//System.out.println(Env.toNiceString(player2)); // !! this print is exponential !!!
			System.out.print("--------------------------------------------------\n");

			System.out.print("===== Constructing and playing the game ==========\n");
			GRGame g = new GRGame(player1, player2);
			System.out.print("Player sys winning states are:\n\t");
			//System.out.print(Env.toNiceString(g.sys_winning_states()) + "\n");
			System.out.print("Player env winning states are:\n\t");
			//System.out.print(Env.toNiceString(g.env_winning_states()) + "\n");

			BDD all_init = g.get_sys_player().initial().and(
					g.get_env_player().initial());
			BDD counter_exmple = g.env_winning_states().and(all_init);
			if (!counter_exmple.isZero()) {
				System.out.print("Specification is unrealizable...\n");
				System.out.print("The env player can win from initial states:\n\t");
				System.out.print(counter_exmple.toStringWithDomains(Env.stringer));
			} else {
				System.out.print("Specification is realizable...\n");
				System.out.print("===== Building an implementation =================\n");
				System.out.print("--------------------------------------------------\n");
				//System.out.println(Env.toNiceString(g.calculate_strategy()));
				g.calculate_strategy();
				System.out.print("--------------------------------------------------\n");
			}
			System.out.print("===== Done =======================================\n");
		} catch (IllegalGameException e) {
			// error in the game...
			e.printStackTrace();
			System.exit(0);
		} catch (RecognitionException e) {
			// error with parsing the file...
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
			System.exit(0);
		}
	}
}
