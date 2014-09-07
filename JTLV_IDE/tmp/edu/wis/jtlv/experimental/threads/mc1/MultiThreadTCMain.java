package edu.wis.jtlv.experimental.threads.mc1;
import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.old_lib.games.GR1Game;
import edu.wis.jtlv.old_lib.games.GameException;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 */
public class MultiThreadTCMain {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd", "jtlv");
		
		String input_file = "./testcases/games_tc/arbiter2_with_spec.smv";
		SMVModule player1 = null, player2 = null;
		try {
			System.out.print("===== Strating synthesis algorithm ==========\n");
			Env.loadModule(input_file);
			player1 = (SMVModule) Env.getModule("main.e");
			player2 = (SMVModule) Env.getModule("main.s");
			player1.setFullPrintingMode(true);
			player2.setFullPrintingMode(true);
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
			System.exit(0);
		}

		System.out.print("===== Loaded players ========================\n");
		System.out.print("---------------------------------------------\n");
		// System.out.println(Env.toNiceString(player1)); // !! this print
		// is exponential !!!
		System.out.print("---------------------------------------------\n");
		// System.out.println(Env.toNiceString(player2)); // !! this print
		// is exponential !!!
		System.out.print("---------------------------------------------\n");

		System.out.print("===== Constructing and playing the game =====\n");
		GR1Game g;
		try {
			g = new GR1Game(player1, player2);
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
			
			// /////////////////////////////////////
			System.out.println("Constructing the threads.");
			MultiThreadTCMain me = new MultiThreadTCMain(player1, player2);
			System.out.println("Running the threads.");
			me.runIt();

		} catch (GameException e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.print("===== Done =============================\n");

	}

	public static int completeToPow2(int curr) {
		int res = 1;
		while (res < curr)
			res = res * 2;
		return res - curr;
	}

	public Runner r1;
	public Runner r2;
	public Runner r3;
	public Runner r4;

	public MultiThreadTCMain(SMVModule m1, SMVModule m2) {
		this.r1 = new Runner(new BDD[] { m1.initial(), m1.trans() }, m1.moduleUnprimeVars());
		this.r2 = new Runner(new BDD[] { m2.initial(), m2.trans() }, m2.moduleUnprimeVars());
		this.r3 = new Runner(new BDD[] { m1.initial(), m1.trans() }, m1.moduleUnprimeVars());
		this.r4 = new Runner(new BDD[] { m2.initial(), m2.trans() }, m2.moduleUnprimeVars());
	}

	public void runIt() {
		this.r1.start();
		this.r2.start();
		this.r3.start();
		this.r4.start();
	}

	public class Runner extends JTLVThread {
		public BDD[] lb;
		public BDDVarSet var;

		public Runner(BDD[] l, BDDVarSet v) {
//			lb = this.adjustBDD(l);
			this.var = v;
		}

		public void run() {
			BDD all = Env.TRUE();
			for (int i = 0; i < 5000000; i++) {
				all = Env.TRUE();
				for (int j = 0; j < lb.length; j++) {
					all = all.id().and(lb[j]);
				}
				all = all.exist(this.var);
				for (int j = 0; j < lb.length; j++) {
					all = all.id().or(lb[j]);
				}
				all = all.forAll(this.var);
			}
			int idx = 0;
//			Thread t = Thread.currentThread();
//			if (t instanceof JTLVThread)
//				idx = ((JTLVThread)t).getDedicatedBDDManagerId(); 
			System.out.println("Thread working with manager " + idx + " with factory: " + all.getFactory() + ", result is: " + Env.toNiceSignleLineString(null, all));
		}

	}
}
