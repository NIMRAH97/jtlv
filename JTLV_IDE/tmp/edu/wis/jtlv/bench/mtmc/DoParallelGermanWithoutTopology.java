package edu.wis.jtlv.bench.mtmc;

import java.util.concurrent.Semaphore;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;

/**
 * === PARALLEL Parallel Star Topology With Semaphores ===
 * 
 * @author yaniv, ariel
 * 
 */
public class DoParallelGermanWithoutTopology extends AbstractAlgorithm {

	public DoParallelGermanWithoutTopology(Configuration conf) {
		super(conf);
	}

	@Override
	public BDD performAlg() {
		long start = System.currentTimeMillis();
		int threadNum = conf.threadNum;

		ThreadFullGerman[] clients = new ThreadFullGerman[conf.N];

		Semaphore semSuperMain = new Semaphore(0, true);
		Semaphore semConstructionGo = new Semaphore(0, true);
		Semaphore semConstruction = new Semaphore(0, true);

		for (int i = 0; i < conf.N; i++) {
			clients[i] = new ThreadFullGerman(conf.thread_bdd_table_size,
					conf.thread_cache_size, conf.do_delta_improvement,
					semSuperMain, semConstruction, semConstructionGo, conf.main, i, conf.N);
		}

		for (int i = 0; i < threadNum; i++)
			clients[i].start();
		semConstruction.acquireUninterruptibly(conf.N);
		// ////////// === FINISH initial settings
		long global_construction_time = System.currentTimeMillis();
		
		semConstructionGo.release(conf.N);
		
		// ////////// === FINISHED
		semSuperMain.acquireUninterruptibly(conf.N);
		long time = System.currentTimeMillis();

		System.out.println("====== SPLIT-INVARIANT PARALLEL ======");
		System.out.println("Global construction time: "
				+ (global_construction_time - start));
		System.out.println("Algorithm Run-time: "
				+ (time - global_construction_time));
		System.out.println("Overall time: " + (time - start));

		BDD foundStates = Env.TRUE();
		for (int i = 0; i < threadNum; i++)
			foundStates = foundStates.and(clients[i].getFoundStates());
		
		return foundStates;
	}
}
