package edu.wis.jtlv.bench.mtmc;

import java.io.FileReader;
import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;

public class Configuration {
	public SMVModule main;
	public int N;
	public int procPerThread;
	public int threadNum;
	public int lastProcPerThread;
	public boolean sanity_check = false;
	public boolean verbose = false;
	public boolean do_already_sent_imp = false;
	public boolean verbose_bdd = false;

	public int initial_bdd_table_size = 40000;
	public int initial_cache_size = 40000;
	public int thread_bdd_table_size = 40000;
	public int thread_cache_size = 40000;

	public boolean do_delta_improvement;

	public Kind impl;

	static enum Kind {
		// simple
		simple_mc, // 
		// all sequential types
		sequential, //
		sequential_multi_proc, // 
		// all parallel types
		parallel_fully_connected_top_with_channels, //
		parallel_star_top_with_channels, //
		parallel_star_top_with_semaphores, //
		parallel_star_top_with_semaphores_multi_proc, //
		//
		//
		//
		parallel_star_szyman, //
		parallel_leaf_tree_szyman, //
		parallel_tree_szyman, //
		sequential_szyman, //
		next_impl, //
		parallel_german, //
		parallel_singular_german, //
		sequential_german, //
		parallel_full_german,

	}

	private boolean doParallel() {
		boolean res = false;
		res |= impl == Kind.parallel_fully_connected_top_with_channels;
		res |= impl == Kind.parallel_star_top_with_channels;
		res |= impl == Kind.parallel_star_top_with_semaphores;
		res |= impl == Kind.parallel_star_top_with_semaphores_multi_proc;
		res |= impl == Kind.parallel_star_szyman;
		res |= impl == Kind.parallel_leaf_tree_szyman;
		res |= impl == Kind.parallel_tree_szyman;
		res |= impl == Kind.parallel_german;
		res |= impl == Kind.parallel_singular_german;
		res |= impl == Kind.parallel_full_german;
		return res;
	}

	@SuppressWarnings("unused")
	private boolean doSequential() {
		boolean res = false;
		res |= impl == Kind.sequential;
		res |= impl == Kind.sequential_multi_proc;
		return res;
	}

	@SuppressWarnings("unused")
	private boolean doMC() {
		boolean res = false;
		res |= impl == Kind.simple_mc;
		return res;
	}

	public void readUsage(String[] args) {
		String fName = "";
		N = -1;
		procPerThread = -1;
		sanity_check = false;
		verbose = false;
		try {

			for (int i = 0; i < args.length; i++) {
				String flag = args[i];
				if (flag.equals("-par")) {
					String kind = args[++i];
					if (kind.equals("FC_C"))
						impl = Kind.parallel_fully_connected_top_with_channels;
					else if (kind.equals("S_C"))
						impl = Kind.parallel_star_top_with_channels;
					else if (kind.equals("S_S"))
						impl = Kind.parallel_star_top_with_semaphores;
					else if (kind.equals("MPS_S"))
						impl = Kind.parallel_star_top_with_semaphores_multi_proc;
					else if (kind.equals("SZ"))
						impl = Kind.parallel_star_szyman;
					else if (kind.equals("SZ_LTR"))
						impl = Kind.parallel_leaf_tree_szyman;
					else if (kind.equals("SZ_TR"))
						impl = Kind.parallel_tree_szyman;
					else if (kind.equals("NX"))
						impl = Kind.next_impl;
					else if (kind.equals("GR"))
						impl = Kind.parallel_german;
					else if (kind.equals("GRS"))
						impl = Kind.parallel_singular_german;
					else if (kind.equals("GRF"))
						impl = Kind.parallel_full_german;
					else
						throw new Exception("Error");

					continue;
				}
				if (flag.equals("-seq")) {
					String kind = args[++i];
					if (kind.equals("S"))
						impl = Kind.sequential;
					else if (kind.equals("MP"))
						impl = Kind.sequential_multi_proc;
					else if (kind.equals("SZ"))
						impl = Kind.sequential_szyman;
					else if (kind.equals("GR"))
						impl = Kind.sequential_german;
					else
						throw new Exception("Error");

					continue;
				}
				if (flag.equals("-mc")) {
					impl = Kind.simple_mc;
					continue;
				}
				if (flag.equals("-cs")) {
					sanity_check = true;
					continue;
				}
				if (flag.equals("-N")) {
					N = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("-ibs")) {
					initial_bdd_table_size = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("-ics")) {
					initial_cache_size = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("-tbs")) {
					thread_bdd_table_size = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("-tcs")) {
					thread_cache_size = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("--ProcPerThread") || flag.equals("-ppt")) {
					procPerThread = Integer.parseInt(args[++i]);
					continue;
				}
				if (flag.equals("-delta_imp")) {
					do_delta_improvement = true;
					continue;
				}
				if (flag.equals("-already_sent_imp")) {
					do_already_sent_imp = true;
					continue;
				}
				if (flag.equals("-vb")) {
					verbose_bdd = true;
					continue;
				}
				if (flag.equals("-v")) {
					verbose = true;
					continue;
				}

				// if didn't read a file so far, then trying to parse as a file.
				if (fName.equals("")) {
					FileReader fr = new FileReader(flag);
					fName = flag;
					fr.close();
					continue;
				}
				throw new Exception("Error");
			}

			if ((N == -1) || (doParallel() & (procPerThread == -1))
					|| (fName.equals(""))) {
				throw new Exception("Error");
			}

		} catch (Exception e) {
			// String mName = MainMultiThreadedMC.class.getCanonicalName();
			String err = "";
			String mName = "<Main File>";
			// for (int i = 0; i < args.length; i++)
			// err += ((i == 0) ? "" : ",") + args[i];

			String new_ls = "\t                              ";
			String new_br = "\t---------------------------------------------";
			new_br += "-----------------------------------\n";
			// err += "\n";
			err += "Wrong usage: java " + mName + " <-par KIND | -seq KIND |";
			err += " -mc> -N <Number> [OPTIONAL] file.smv\n";
			//
			err += "\t-par  <KIND>                  ";
			err += "Do parallel algorithm. Where: \n";
			err += new_ls;
			err += "FC_C   - Fully Connected with Channels.\n";
			err += new_ls;
			err += "S_C    - Star topology with Channels.\n";
			err += new_ls;
			err += "S_S    - Star topology with Semaphores.\n";
			err += new_ls;
			err += "MPS_S  - Multi Proc Star topology with Semaphores.\n";
			err += new_ls;
			err += "        (-ppt must also be supplied)\n";
			err += new_ls;
			err += "SZ     - Szymanski star implementation.\n";
			err += new_ls;
			err += "SZ_LTE - Szymanski Leaf Tree implementation (each leaf is a proccess).\n";
			err += new_ls;
			err += "SZ_TE  - Szymanski Tree implementation (each node is a proccess).\n";
			err += new_ls;
			err += "NX     - Next implementation template....\n";
			err += new_br;
			//
			err += "\t-seq  <KIND>                  ";
			err += "Do sequential algorithm. Where: \n";
			err += new_ls;
			err += "S      - Simple.\n";
			err += new_ls;
			err += "MP     - Multi Proc. (-ppt must also be supplied)\n";
			err += new_ls;
			err += "SZ     - Szymanski sequential implementation ....\n";
			err += new_br;
			//
			err += "\t-mc                           ";
			err += "Do simple model checking (Forward Reachability)\n";
			err += new_br;
			// 
			err += "\t-N    <Number>                ";
			err += "Number of processes.\n";
			err += new_br;
			// 
			err += "\t-ppt  <Number>                ";
			err += "Number of processes per thread in a relevat       \n";
			err += new_ls;
			err += "implementation. (i.e. \"-par MPS_S\" or \"-seq MP\")\n";
			err += new_br;
			//
			err += "\t-ibs  <Number>                ";
			err += "Initial BDD table Size. (important for channels)\n";
			err += new_br;
			//
			err += "\t-ics  <Number>                ";
			err += "Initial Cache Size. (important for channels)\n";
			err += new_br;
			//
			err += "\t-tbs  <Number>                ";
			err += "Threads BDD table Size. (inclueing main threads\n";
			err += new_ls + "for the sequential)\n";
			err += new_br;
			//
			err += "\t-tcs  <Number>                ";
			err += "Threads Cache Size. (inclueing main threads\n";
			err += new_ls + "for the sequential)\n";
			err += new_br;
			// 
			err += "\t-delta_imp                    ";
			err += "Do delta theta computation for successors.\n";
			err += new_ls;
			err += "(currentlly not supported by \"-sec S\", and \"-mc\")\n";
			err += new_br;
			//
			err += "\t-already_sent_imp             ";
			err += "Do not re-send information. (supported only for\n";
			err += new_ls;
			err += " \"-seq S\" and \"-par S_S\")\n";
			err += new_br;
			//
			err += "\t-cs                           ";
			err += "Do sanity check by running both algorithms, and \n";
			err += new_ls;
			err += "checking for identical BDD result.\n";
			err += new_br;
			// 
			err += "\t-vb                           ";
			err += "BDD Factory verbosity level \n";
			err += new_br;
			// 
			err += "\t-v                            ";
			err += "Verbosity level.\n";
			//

			System.err.println(err);
			System.exit(1);
		}

		threadNum = N / procPerThread + ((N % procPerThread != 0) ? 1 : 0);
		lastProcPerThread = N % procPerThread;
		if (lastProcPerThread == 0)
			lastProcPerThread = procPerThread;

		if (doParallel()) {
			if (impl == Kind.parallel_german)
				System.setProperty("pool_size", "" + (7 * N * (threadNum + 1)));
			else {
				System.setProperty("pool_size", "" + (threadNum + 1));
			}
		}

		main = loadMainModule(fName);
	}

	public SMVModule loadMainModule(String input_file) {
		System.setProperty("bdd", "cudd");
		
		System.setProperty("bdd_table_size", "" + initial_bdd_table_size);
		System.setProperty("bdd_cache_size", "" + initial_cache_size);
		Env.resetEnv();
		if (!verbose_bdd)
			Env.beQuiet();
		SMVModule.initModulesWithoutRunningVar();
		try {
			// System.out.print("===== Loading smv file ==========\n");
			Env.loadModule(input_file);
		} catch (IOException e) {
			// error with reading the file...
			e.printStackTrace();
			System.exit(0);
		}

		// == the shared environment
		SMVModule main = (SMVModule) Env.getModule("main");
		// removing "running" variable.
		main.removeRunningVar();
		// initial states of M
		return main;
	}

	public AbstractAlgorithm getAlg() {
		switch (impl) {
		case simple_mc:
			return null; // new DoSimpleMC(this);
		case sequential:
			return null; // new DoSequential(this);
		case sequential_multi_proc:
			return null; // new DoSequentialMultiProc(this);
		case parallel_fully_connected_top_with_channels:
			return null; // new
			// DoParallelFullyConnectedTopologyWithChannels(this);
		case parallel_star_top_with_channels:
			return null; // new DoParallelStarTopologyWithChannels(this);
		case parallel_star_top_with_semaphores:
			return null; // new DoParallelStarTopologyWithSemaphores(this);
		case parallel_star_top_with_semaphores_multi_proc:
			return null; // new
			// DoParallelStarTopologyWithSemaphoresMultiProc(this);
			// case parallel_star_szyman:
			// return new DoParallelSzymanStarTopologyWithSemaphores(this);
			// case parallel_leaf_tree_szyman:
			// return new DoParallelSzymanLeafTreeTopologyWithSemaphores(this);
			// case parallel_tree_szyman:
			// return new DoParallelSzymanTreeTopologyWithSemaphores(this);
			// case sequential_szyman:
			// return new DoSequentialSzyman(this);
		case next_impl:
			return null; // new DoNextImpl(this);
		case parallel_german:
			return null;
			// new DoParallelGermanCompleteTopologyWithSemaphores(this);
			// return new DoParallelGermanStarTopologyWithSemaphores(this);
		case parallel_singular_german:
			return null;
			// new DoParallelGermanSingularCompleteTopologyWithSemaphores(this);
		case sequential_german:
			return new DoSequentialGerman(this);
		case parallel_full_german:
			return new DoParallelGermanWithoutTopology(this);

		default:
			System.err.println("Couldn't find implementation.");
			return null;
		}
	}
}
