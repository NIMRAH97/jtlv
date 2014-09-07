package edu.wis.jtlv.experimental.threads.mc1;

import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDVarSet;
import net.sf.javabdd.JTLVJddFactory;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.old_lib.games.GR1Game;
import edu.wis.jtlv.old_lib.games.GameException;

/**
 * @version {@value edu.wis.jtlv.env.Env#version}
 * @author yaniv sa'ar.
 */
public class MultiThreadTCMain2 {
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
			JTLVJddFactory f1 = (JTLVJddFactory) all_init.getFactory();
			System.out.println("----------------------------------------");
			System.out.println(all_init);
			System.out.println(f1.numberOfDomains());
			System.out.println("----------------------------------------");
			BDDDomain d1 = f1.extDomain(4);
			BDDDomain d2 = f1.extDomain(4);
			System.out.println(f1.numberOfDomains());
			BDD b = d1.buildEquals(d2);
			System.out.println(b);
			System.out.println(all_init);
			System.out.println(f1.numberOfDomains());
			System.out.println("----------------------------------------");
			System.out.println(b);
			// ////////////////////////////////////////////////////////////////
			// preparing and fixing all issues in the factory clone.
			// int loop = completeToPow2(f1.numberOfDomains());
			// for (int i = 0; i < loop; i++) {
			// @SuppressWarnings("unused")
			// BDDDomain d3 = f1.extDomain(1);
			// }
			// b.applyAll(Env.TRUE(), BDDFactory.and, player1.unprimeVars());
			// ////////////////////////////////////////////////////////////////
			//
			//
			//
			//
			// JTLVBDDFactory f2 = f1.cloneFactory();
			//
			//
			//		
			//
			// BDD b2 = f2.copyNode(b);
			// System.out.println(b.and(all_init));
			// System.out.println(b2);
			// System.out.println(all_init);
			// System.out.println(f1.numberOfDomains());
			// System.out.println(f2.numberOfDomains());
			// System.out.println("----------------------------------------");
			// @SuppressWarnings("unused")
			// BDDDomain d4 = f2.extDomain(1);
			// System.out.println(f1.numberOfDomains());
			// System.out.println(f2.numberOfDomains());
			// System.out.println(b.and(all_init));
			// System.out.println(b2.and(all_init));
			// System.out.println(all_init);
			// System.out.println(f1.numberOfDomains());
			// System.out.println(f2.numberOfDomains());
			// System.out.println("----------------------------------------");
			// // f2.done();
			// // BDDDomain d4 = f2.extDomain(1);
			// System.out.println(f1.numberOfDomains());
			// System.out.println(f2.numberOfDomains());
			// System.out.println(b.and(all_init));
			// System.out.println(b2.and(all_init));
			// System.out.println(all_init);
			// System.out.println(f1.numberOfDomains());
			// System.out.println(f2.numberOfDomains());
			// System.out.println("----------------------------------------");
			//
			// System.out.println(all_init.getFactory());
			// System.out.println(b.getFactory());
			// System.out.println(b.and(all_init).getFactory());
			// System.out.println(b2.getFactory());
			// System.out.println(b2.and(all_init).getFactory());
			// System.out.println(all_init.and(b2).getFactory());

			//
			//
			//
			//
			//
			// System.out.println("Constructing the threads.");
			// FactoryTCMain2 me = new FactoryTCMain2(f1, player1, f2, player2);
			// System.out.println("Running the threads.");
			// me.run();

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

	public MultiThreadTCMain2(BDDFactory f1, SMVModule m1, BDDFactory f2,
			SMVModule m2) {

		this.r1 = new Runner(f1, new BDD[] { f1.one().and(m1.initial()),
				f1.one().and(m1.trans()) }, f1.emptySet().union(
				m1.moduleUnprimeVars()));
		this.r2 = new Runner(f2, new BDD[] { f2.one().and(m2.initial()),
				f2.one().and(m2.trans()) }, f2.emptySet().union(
				m2.moduleUnprimeVars()));
	}

	public void run() {
		Thread countThread1 = new Thread(this.r1);
		Thread countThread2 = new Thread(this.r2);
		countThread1.start();
		countThread2.start();
		// countThread2.
	}

	public class Runner implements Runnable {
		public BDDFactory f;
		public BDD[] lb;
		public BDDVarSet var;

		public Runner(BDDFactory f, BDD[] l, BDDVarSet v) {
			this.f = f;
			this.lb = l;
			this.var = v;
		}

		public void run() {
			BDD all = this.f.one();
			for (int i = 0; i < 20000000; i++) {
				all = this.f.one();
				for (int j = 0; j < lb.length; j++) {
					all = all.id().and(lb[j]);
				}
				// all = all.exist(this.var);
				for (int j = 0; j < lb.length; j++) {
					all = all.id().or(lb[j]);
				}
				all = all.forAll(this.var);
			}
			System.out.println("Thread " + f.toString() + "result:"
					+ Env.toNiceSignleLineString(null, all));
		}

	}
}
