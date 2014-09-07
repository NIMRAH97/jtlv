package edu.wis.jtlv.bench.mtmc;

import java.util.concurrent.Semaphore;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVThread;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;

public class ThreadFullGerman extends JTLVThread {
	BDD[] newThetas_cl;
	BDD[] newThetas_h0;
	BDD[] newThetas_h1;
	BDD[] newThetas_hu;
	BDD[] newThetas_h2;
	BDD[] newThetas_h3;
	BDD[] newThetas_h4;

	int thread_bdd_table_size;
	int thread_cache_size;
	boolean do_delta_improvement;
	Semaphore workDone;
	Semaphore semConstruction;
	Semaphore semConstructionGo;
	SMVModule main;
	int N;
	int proc_id;
	public BDD True;
	public BDD False;
	public BDDVarSet EmptySet;

	public ThreadFullGerman(int thread_bdd_table_size, int thread_cache_size,
			boolean do_delta_improvement, Semaphore workDone,
			Semaphore semConstruction, Semaphore semConstructionGo,
			SMVModule main, int proc_id, int N) {
		this.thread_bdd_table_size = thread_bdd_table_size;
		this.thread_cache_size = thread_cache_size;
		this.do_delta_improvement = do_delta_improvement;
		this.workDone = workDone;
		this.semConstruction = semConstruction;
		this.semConstructionGo = semConstructionGo;
		this.main = main;
		this.proc_id = proc_id;
		this.N = N;

		// /////////////////////////
		this.False = adjustBDDToManager(Env.FALSE());
		this.True = adjustBDDToManager(Env.TRUE());
		this.EmptySet = adjustBDDVarSetToManager(Env.getEmptySet());
		// /////////////////////////
		// the initial condition of each client (including shared vars)
		initCond_cl = new BDD[N];
		initCond_h0 = new BDD[N];
		initCond_h1 = new BDD[N];
		initCond_hu = new BDD[N];
		initCond_h2 = new BDD[N];
		initCond_h3 = new BDD[N];
		initCond_h4 = new BDD[N];
		home0s = new SMVModule[N];
		home1s = new SMVModule[N];
		homeus = new SMVModule[N];
		home2s = new SMVModule[N];
		home3s = new SMVModule[N];
		home4s = new SMVModule[N];

		for (int i = 0; i < N; i++) {
			initCond_cl[i] = True;
			for (int j = 1; j <= 6; j++) {
				SMVModule inner_client;
				if (j != 6)
					inner_client = (SMVModule) Env.getModule("main.cr["
							+ proc_id + "].C" + i + ".c" + j);
				else
					inner_client = (SMVModule) Env.getModule("main.cr["
							+ proc_id + "].C" + i + ".cu");
				initCond_cl[i] = initCond_cl[i]
						.and(adjustBDDToManager(inner_client.initial()));
			}

			// getting the clients
			// clients[i] = (SMVModule) Env.getModule("main.c" + i);
			home0s[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".h0");
			home1s[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".h1");
			homeus[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".hu");
			home2s[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".h2");
			home3s[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".h3");
			home4s[i] = (SMVModule) Env.getModule("main.cr[" + proc_id + "].H"
					+ i + ".h4");

			initCond_h0[i] = adjustBDDToManager(home0s[i].initial()); // .and(initM);
			initCond_h1[i] = adjustBDDToManager(home1s[i].initial()); // .and(initM);
			initCond_hu[i] = adjustBDDToManager(homeus[i].initial()); // .and(initM);
			initCond_h2[i] = adjustBDDToManager(home2s[i].initial()); // .and(initM);
			initCond_h3[i] = adjustBDDToManager(home3s[i].initial()); // .and(initM);
			initCond_h4[i] = adjustBDDToManager(home4s[i].initial()); // .and(initM);

		}

	}

	SMVModule[] home0s = new SMVModule[N];
	SMVModule[] home1s = new SMVModule[N];
	SMVModule[] homeus = new SMVModule[N];
	SMVModule[] home2s = new SMVModule[N];
	SMVModule[] home3s = new SMVModule[N];
	SMVModule[] home4s = new SMVModule[N];
	BDD[] initCond_cl = new BDD[N];
	BDD[] initCond_h0 = new BDD[N];
	BDD[] initCond_h1 = new BDD[N];
	BDD[] initCond_hu = new BDD[N];
	BDD[] initCond_h2 = new BDD[N];
	BDD[] initCond_h3 = new BDD[N];
	BDD[] initCond_h4 = new BDD[N];
	BDD[] trans_cl = new BDD[N];
	BDD[] trans_h0 = new BDD[N];
	BDD[] trans_h1 = new BDD[N];
	BDD[] trans_hu = new BDD[N];
	BDD[] trans_h2 = new BDD[N];
	BDD[] trans_h3 = new BDD[N];
	BDD[] trans_h4 = new BDD[N];

	public void run() {
		Env.TRUE().getFactory().setNodeTableSize(thread_bdd_table_size);
		Env.TRUE().getFactory().setCacheSize(thread_cache_size);

		trans_cl = new BDD[N];
		trans_h0 = new BDD[N];
		trans_h1 = new BDD[N];
		trans_hu = new BDD[N];
		trans_h2 = new BDD[N];
		trans_h3 = new BDD[N];
		trans_h4 = new BDD[N];

		for (int i = 0; i < N; i++) {
			trans_cl[i] = False;
			for (int j = 1; j <= 6; j++) {
				SMVModule inner_client;
				if (j != 6)
					inner_client = (SMVModule) Env.getModule("main.cr["
							+ proc_id + "].C" + i + ".c" + j);
				else
					inner_client = (SMVModule) Env.getModule("main.cr["
							+ proc_id + "].C" + i + ".cu");
				trans_cl[i] = trans_cl[i]
						.or(assignTransToDedicatedManager(inner_client));
			}
			trans_h0[i] = assignTransToDedicatedManager(home0s[i]);
			trans_h1[i] = assignTransToDedicatedManager(home1s[i]);
			trans_hu[i] = assignTransToDedicatedManager(homeus[i]);
			trans_h2[i] = assignTransToDedicatedManager(home2s[i]);
			trans_h3[i] = assignTransToDedicatedManager(home3s[i]);
			trans_h4[i] = assignTransToDedicatedManager(home4s[i]);

		}

		// == processes:
		// SMVModule[] clients = new SMVModule[N];
		// extended transition relation:
		BDD[] extTrans_cl = new BDD[N];
		BDD[] extTrans_h0 = new BDD[N];
		BDD[] extTrans_h1 = new BDD[N];
		BDD[] extTrans_hu = new BDD[N];
		BDD[] extTrans_h2 = new BDD[N];
		BDD[] extTrans_h3 = new BDD[N];
		BDD[] extTrans_h4 = new BDD[N];
		// variables (local and shared):
		BDDVarSet[] vars_cl = new BDDVarSet[N];
		BDDVarSet[] vars_h0 = new BDDVarSet[N];
		BDDVarSet[] vars_h1 = new BDDVarSet[N];
		BDDVarSet[] vars_hu = new BDDVarSet[N];
		BDDVarSet[] vars_h2 = new BDDVarSet[N];
		BDDVarSet[] vars_h3 = new BDDVarSet[N];
		BDDVarSet[] vars_h4 = new BDDVarSet[N];
		// Theta of each process
		BDD[] oldThetas_cl = new BDD[N];
		BDD[] oldThetas_h0 = new BDD[N];
		BDD[] oldThetas_h1 = new BDD[N];
		BDD[] oldThetas_hu = new BDD[N];
		BDD[] oldThetas_h2 = new BDD[N];
		BDD[] oldThetas_h3 = new BDD[N];
		BDD[] oldThetas_h4 = new BDD[N];
		BDD[] deltaThetas_cl = new BDD[N];
		BDD[] deltaThetas_h0 = new BDD[N];
		BDD[] deltaThetas_h1 = new BDD[N];
		BDD[] deltaThetas_hu = new BDD[N];
		BDD[] deltaThetas_h2 = new BDD[N];
		BDD[] deltaThetas_h3 = new BDD[N];
		BDD[] deltaThetas_h4 = new BDD[N];

		// part of the results
		newThetas_cl = new BDD[N];
		newThetas_h0 = new BDD[N];
		newThetas_h1 = new BDD[N];
		newThetas_hu = new BDD[N];
		newThetas_h2 = new BDD[N];
		newThetas_h3 = new BDD[N];
		newThetas_h4 = new BDD[N];

		// getting the info
		for (int i = 0; i < N; i++) {
			// vars (all) of each process
			vars_cl[i] = Env.getEmptySet();
			vars_h0[i] = Env.getEmptySet();
			vars_h1[i] = Env.getEmptySet();
			vars_hu[i] = Env.getEmptySet();
			vars_h2[i] = Env.getEmptySet();
			vars_h3[i] = Env.getEmptySet();
			vars_h4[i] = Env.getEmptySet();

			// original disjuncted with what obtained from others
			extTrans_cl[i] = trans_cl[i];
			extTrans_h0[i] = trans_h0[i];
			extTrans_h1[i] = trans_h1[i];
			extTrans_hu[i] = trans_hu[i];
			extTrans_h2[i] = trans_h2[i];
			extTrans_h3[i] = trans_h3[i];
			extTrans_h4[i] = trans_h4[i];
			// initial thetas:
			oldThetas_cl[i] = initCond_cl[i];
			oldThetas_h0[i] = initCond_h0[i];
			oldThetas_h1[i] = initCond_h1[i];
			oldThetas_hu[i] = initCond_hu[i];
			oldThetas_h2[i] = initCond_h2[i];
			oldThetas_h3[i] = initCond_h3[i];
			oldThetas_h4[i] = initCond_h4[i];
			deltaThetas_cl[i] = initCond_cl[i];
			deltaThetas_h0[i] = initCond_h0[i];
			deltaThetas_h1[i] = initCond_h1[i];
			deltaThetas_hu[i] = initCond_hu[i];
			deltaThetas_h2[i] = initCond_h2[i];
			deltaThetas_h3[i] = initCond_h3[i];
			deltaThetas_h4[i] = initCond_h4[i];
			newThetas_cl[i] = initCond_cl[i];
			newThetas_h0[i] = initCond_h0[i];
			newThetas_h1[i] = initCond_h1[i];
			newThetas_hu[i] = initCond_hu[i];
			newThetas_h2[i] = initCond_h2[i];
			newThetas_h3[i] = initCond_h3[i];
			newThetas_h4[i] = initCond_h4[i];
		}

		// vars
		ModuleBDDField cur_cln = main.getVar("cr[" + proc_id + "].cur_client",
				true);
		ModuleBDDField cur_com = main.getVar("cr[" + proc_id + "].cur_command",
				true);
		ModuleBDDField exc_grn = main.getVar("cr[" + proc_id
				+ "].exclusive_granted", true);
		ModuleBDDField x_grn = main.getVar("cr[" + proc_id + "].x_granted",
				true);
		ModuleBDDField[] channel1 = new ModuleBDDField[N];
		ModuleBDDField[] channel2 = new ModuleBDDField[N];
		ModuleBDDField[] channel3 = new ModuleBDDField[N];
		ModuleBDDField[] sharer_l = new ModuleBDDField[N];
		ModuleBDDField[] invald_l = new ModuleBDDField[N];
		ModuleBDDField[] cache = new ModuleBDDField[N];
		for (int i = 0; i < N; i++) {
			channel1[i] = main.getVar(
					"cr[" + proc_id + "].channel1[" + i + "]", true);
			channel2[i] = main.getVar(
					"cr[" + proc_id + "].channel2[" + i + "]", true);
			channel3[i] = main.getVar(
					"cr[" + proc_id + "].channel3[" + i + "]", true);
			sharer_l[i] = main.getVar("cr[" + proc_id + "].sharer_list[" + i
					+ "]", true);
			invald_l[i] = main.getVar("cr[" + proc_id + "].invalidate_list["
					+ i + "]", true);
			cache[i] = main
					.getVar("cr[" + proc_id + "].cache[" + i + "]", true);
		}
		// clients
		for (int i = 0; i < N; i++) {
			vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(cache[i].getDomain().set()));
			vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_cl[i] = vars_cl[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}

		// home0
		for (int i = 0; i < N; i++) {
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set()));
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_h0[i] = vars_h0[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}
		// home1s
		for (int i = 0; i < N; i++) {
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(x_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_h1[i] = vars_h1[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}
		// homeus
		for (int i = 0; i < N; i++) {
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(x_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_hu[i] = vars_hu[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}
		// home2s
		for (int i = 0; i < N; i++) {
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set()));
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
				vars_h2[i] = vars_h2[i].union(adjustBDDVarSetToManager(invald_l[j].getDomain().set()));
			}
		}
		// home3s
		for (int i = 0; i < N; i++) {
			// vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set());
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(invald_l[i].getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_h3[i] = vars_h3[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}
		// home4s
		for (int i = 0; i < N; i++) {
			// vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(cur_cln.getDomain().set());
			vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(cur_com.getDomain().set()));
			vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(channel1[i].getDomain().set()));
			vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(channel2[i].getDomain().set()));
			vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(channel3[i].getDomain().set()));
			vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(exc_grn.getDomain().set()));
			for (int j = 0; j < N; j++) {
				vars_h4[i] = vars_h4[i].union(adjustBDDVarSetToManager(sharer_l[j].getDomain().set()));
			}
		}

		// === clients
		BDDVarSet[] varsCl_i_QuantForH0_i = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH1_i = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForHu_i = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH4_i = new BDDVarSet[N];
		BDD[] idleOfCl_i_FromH0_i = new BDD[N];
		BDD[] idleOfCl_i_FromH1_i = new BDD[N];
		BDD[] idleOfCl_i_FromHu_i = new BDD[N];
		BDD[] idleOfCl_i_FromH2_i = new BDD[N];
		BDD[] idleOfCl_i_FromH3_i = new BDD[N];
		BDD[] idleOfCl_i_FromH4_i = new BDD[N];
		for (int i = 0; i < N; i++) {
			varsCl_i_QuantForH0_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromH0_i[i] = idleVars(cache[i]);

			varsCl_i_QuantForH1_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromH1_i[i] = idleVars(cache[i]);

			varsCl_i_QuantForHu_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromHu_i[i] = idleVars(cache[i]);

			varsCl_i_QuantForH2_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromH2_i[i] = idleVars(cache[i]);

			varsCl_i_QuantForH3_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromH3_i[i] = idleVars(cache[i]);

			varsCl_i_QuantForH4_i[i] = varsToQant(cache[i]);
			idleOfCl_i_FromH4_i[i] = idleVars(cache[i]);
		}
		// Ariel
		BDDVarSet[] varsCl_i_QuantForH0_j = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH1_j = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForHu_j = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH2_j = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH3_j = new BDDVarSet[N];
		BDDVarSet[] varsCl_i_QuantForH4_j = new BDDVarSet[N];

		BDD[][] idleOfCl_i_FromH0_j = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH1_j = new BDD[N][N];
		BDD[][] idleOfCl_i_FromHu_j = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH2_j = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH4_j = new BDD[N][N];
		for (int i = 0; i < N; i++) {
			// Ariel
			varsCl_i_QuantForH0_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);
			varsCl_i_QuantForH1_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);
			varsCl_i_QuantForHu_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);
			varsCl_i_QuantForH2_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);
			varsCl_i_QuantForH3_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);
			varsCl_i_QuantForH4_j[i] = varsToQant(cache[i], channel1[i],
					channel2[i], channel3[i]);

			for (int j = 0; j < N; j++) {
				if (j != i) {
					idleOfCl_i_FromH0_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
					idleOfCl_i_FromH1_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
					idleOfCl_i_FromHu_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
					idleOfCl_i_FromH2_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
					idleOfCl_i_FromH3_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
					idleOfCl_i_FromH4_j[i][j] = idleVars(cache[i], channel1[i],
							channel2[i], channel3[i]);
				}
			}
		}

		// === H0
		BDDVarSet[] varsH0_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[][] varsH0_i_QuantForCl_j = new BDDVarSet[N][N];
		// BDDVarSet varsH0_i_QuantForH0_i;
		BDDVarSet[] varsH0_i_QuantForH0_j = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH1_i = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH1_j = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForHu_i = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForHu_j = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH2_j = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH3_j = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH4_i = new BDDVarSet[N];
		BDDVarSet[] varsH0_i_QuantForH4_j = new BDDVarSet[N];
		BDD[] idleOfH0_i_FromCl_i = new BDD[N];
		BDD[][] idleOfH0_i_FromCl_j = new BDD[N][N];
		// BDD[] idleOfH0_i_FromH0_i = new BDD[N];
		BDD[] idleOfH0_i_FromH0_j = new BDD[N];
		BDD[] idleOfH0_i_FromH1_i = new BDD[N];
		BDD[] idleOfH0_i_FromH1_j = new BDD[N];
		BDD[] idleOfH0_i_FromHu_i = new BDD[N];
		BDD[] idleOfH0_i_FromHu_j = new BDD[N];
		BDD[] idleOfH0_i_FromH2_i = new BDD[N];
		BDD[] idleOfH0_i_FromH2_j = new BDD[N];
		BDD[] idleOfH0_i_FromH3_i = new BDD[N];
		BDD[] idleOfH0_i_FromH3_j = new BDD[N];
		BDD[] idleOfH0_i_FromH4_i = new BDD[N];
		BDD[] idleOfH0_i_FromH4_j = new BDD[N];
		for (int i = 0; i < N; i++) {
			varsH0_i_QuantForCl_i[i] = varsToQant(cur_cln, cur_com);
			idleOfH0_i_FromCl_i[i] = idleVars(cur_cln, cur_com);

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH0_i_QuantForCl_j[i][j] = varsToQant(cur_cln, cur_com,
							channel1[i], channel2[i], channel3[i]);
					idleOfH0_i_FromCl_j[i][j] = idleVars(cur_cln, cur_com,
							channel1[i], channel2[i], channel3[i]);
				}
			}

			varsH0_i_QuantForH0_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i], cur_cln);
			idleOfH0_i_FromH0_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i], cur_cln);

			varsH0_i_QuantForH1_i[i] = varsToQant();
			idleOfH0_i_FromH1_i[i] = idleVars();

			varsH0_i_QuantForH1_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i], cur_cln);
			idleOfH0_i_FromH1_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i], cur_cln);

			varsH0_i_QuantForHu_i[i] = varsToQant();
			idleOfH0_i_FromHu_i[i] = idleVars();

			varsH0_i_QuantForHu_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i], cur_cln);
			idleOfH0_i_FromHu_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i], cur_cln);

			varsH0_i_QuantForH2_i[i] = varsToQant();
			idleOfH0_i_FromH2_i[i] = idleVars();

			varsH0_i_QuantForH2_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i], cur_cln);
			idleOfH0_i_FromH2_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i], cur_cln);

			varsH0_i_QuantForH3_i[i] = varsToQant(cur_cln);
			idleOfH0_i_FromH3_i[i] = idleVars(cur_cln);

			varsH0_i_QuantForH3_j[i] = varsToQant(cur_cln, channel1[i],
					channel2[i], channel3[i]);
			idleOfH0_i_FromH3_j[i] = idleVars(cur_cln, channel1[i],
					channel2[i], channel3[i]);

			varsH0_i_QuantForH4_i[i] = varsToQant(cur_cln);
			idleOfH0_i_FromH4_i[i] = idleVars(cur_cln);

			varsH0_i_QuantForH4_j[i] = varsToQant(cur_cln, channel1[i],
					channel2[i], channel3[i]);
			idleOfH0_i_FromH4_j[i] = idleVars(cur_cln, channel1[i],
					channel2[i], channel3[i]);
		}

		// === H1
		BDDVarSet[] varsH1_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[] varsH1_i_QuantForH0_i = new BDDVarSet[N];
		// BDDVarSet varsH1_i_QuantForH1_i;
		BDDVarSet[] varsH1_i_QuantForHu_i = new BDDVarSet[N];
		BDDVarSet[] varsH1_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[] varsH1_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[] varsH1_i_QuantForH4_i = new BDDVarSet[N];
		BDD[] idleOfH1_i_FromCl_i = new BDD[N];
		BDD[] idleOfH1_i_FromH0_i = new BDD[N];
		// BDD[] idleOfH1_i_FromH1_i = new BDD[N];
		BDD[] idleOfH1_i_FromHu_i = new BDD[N];
		BDD[] idleOfH1_i_FromH2_i = new BDD[N];
		BDD[] idleOfH1_i_FromH3_i = new BDD[N];
		BDD[] idleOfH1_i_FromH4_i = new BDD[N];
		for (int i = 0; i < N; i++) {
			varsH1_i_QuantForCl_i[i] = varsToQant(cur_cln, cur_com, x_grn);
			idleOfH1_i_FromCl_i[i] = idleVars(cur_cln, cur_com, x_grn);

			varsH1_i_QuantForH0_i[i] = varsToQant(x_grn);
			idleOfH1_i_FromH0_i[i] = idleVars(x_grn);

			varsH1_i_QuantForHu_i[i] = varsToQant();
			idleOfH1_i_FromHu_i[i] = idleVars();

			varsH1_i_QuantForH2_i[i] = varsToQant(x_grn);
			idleOfH1_i_FromH2_i[i] = idleVars(x_grn);

			varsH1_i_QuantForH3_i[i] = varsToQant(cur_cln, x_grn);
			idleOfH1_i_FromH3_i[i] = idleVars(cur_cln, x_grn);

			varsH1_i_QuantForH4_i[i] = varsToQant(cur_cln, x_grn);
			idleOfH1_i_FromH4_i[i] = idleVars(cur_cln, x_grn);
		}

		BDDVarSet[][] varsH1_i_QuantForCl_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfH1_i_FromCl_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH0_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH1_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromHu_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH2_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH4_j = new BDD[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH1_i_QuantForCl_j[i][j] = varsToQant(cur_cln, cur_com,
							x_grn, channel1[i], channel2[i], channel3[i]);
					idleOfH1_i_FromCl_j[i][j] = idleVars(cur_cln, cur_com,
							x_grn, channel1[i], channel2[i], channel3[i]);

					varsH1_i_QuantForH0_j[i][j] = varsToQant(x_grn,
							channel1[i], channel2[i], channel3[i], cur_cln);
					idleOfH1_i_FromH0_j[i][j] = idleVars(x_grn, channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsH1_i_QuantForH1_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH1_i_FromH1_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsH1_i_QuantForHu_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH1_i_FromHu_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsH1_i_QuantForH2_j[i][j] = varsToQant(x_grn,
							channel1[i], channel2[i], channel3[i], cur_cln);
					idleOfH1_i_FromH2_j[i][j] = idleVars(x_grn, channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsH1_i_QuantForH3_j[i][j] = varsToQant(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
					idleOfH1_i_FromH3_j[i][j] = idleVars(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);

					varsH1_i_QuantForH4_j[i][j] = varsToQant(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
					idleOfH1_i_FromH4_j[i][j] = idleVars(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
				}
			}
		}

		// === Hu
		BDDVarSet[] varsHu_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[] varsHu_i_QuantForH0_i = new BDDVarSet[N];
		BDDVarSet[] varsHu_i_QuantForH1_i = new BDDVarSet[N];
		// BDDVarSet varsHu_i_QuantForHu_i;
		BDDVarSet[] varsHu_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[] varsHu_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[] varsHu_i_QuantForH4_i = new BDDVarSet[N];
		BDD[] idleOfHu_i_FromCl_i = new BDD[N];
		BDD[] idleOfHu_i_FromH0_i = new BDD[N];
		BDD[] idleOfHu_i_FromH1_i = new BDD[N];
		// BDD[] idleOfHu_i_FromHu_i = new BDD[N];
		BDD[] idleOfHu_i_FromH2_i = new BDD[N];
		BDD[] idleOfHu_i_FromH3_i = new BDD[N];
		BDD[] idleOfHu_i_FromH4_i = new BDD[N];
		for (int i = 0; i < N; i++) {
			varsHu_i_QuantForCl_i[i] = varsToQant(cur_cln, cur_com, x_grn);
			idleOfHu_i_FromCl_i[i] = idleVars(cur_cln, cur_com, x_grn);

			varsHu_i_QuantForH0_i[i] = varsToQant(x_grn);
			idleOfHu_i_FromH0_i[i] = idleVars(x_grn);

			varsHu_i_QuantForH1_i[i] = varsToQant();
			idleOfHu_i_FromH1_i[i] = idleVars();

			varsHu_i_QuantForH2_i[i] = varsToQant(x_grn);
			idleOfHu_i_FromH2_i[i] = idleVars(x_grn);

			varsHu_i_QuantForH3_i[i] = varsToQant(cur_cln, x_grn);
			idleOfHu_i_FromH3_i[i] = idleVars(cur_cln, x_grn);

			varsHu_i_QuantForH4_i[i] = varsToQant(cur_cln, x_grn);
			idleOfHu_i_FromH4_i[i] = idleVars(cur_cln, x_grn);
		}

		BDDVarSet[][] varsHu_i_QuantForCl_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfHu_i_FromCl_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH0_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH1_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromHu_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH2_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH4_j = new BDD[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsHu_i_QuantForCl_j[i][j] = varsToQant(cur_cln, cur_com,
							x_grn, channel1[i], channel2[i], channel3[i]);
					idleOfHu_i_FromCl_j[i][j] = idleVars(cur_cln, cur_com,
							x_grn, channel1[i], channel2[i], channel3[i]);

					varsHu_i_QuantForH0_j[i][j] = varsToQant(x_grn,
							channel1[i], channel2[i], channel3[i], cur_cln);
					idleOfHu_i_FromH0_j[i][j] = idleVars(x_grn, channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsHu_i_QuantForH1_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfHu_i_FromH1_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsHu_i_QuantForHu_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfHu_i_FromHu_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsHu_i_QuantForH2_j[i][j] = varsToQant(x_grn,
							channel1[i], channel2[i], channel3[i], cur_cln);
					idleOfHu_i_FromH2_j[i][j] = idleVars(x_grn, channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsHu_i_QuantForH3_j[i][j] = varsToQant(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
					idleOfHu_i_FromH3_j[i][j] = idleVars(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);

					varsHu_i_QuantForH4_j[i][j] = varsToQant(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
					idleOfHu_i_FromH4_j[i][j] = idleVars(cur_cln, x_grn,
							channel1[i], channel2[i], channel3[i]);
				}
			}
		}

		// === H2
		BDDVarSet[] varsH2_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[] varsH2_i_QuantForH0_i = new BDDVarSet[N];
		BDDVarSet[] varsH2_i_QuantForH1_i = new BDDVarSet[N];
		BDDVarSet[] varsH2_i_QuantForHu_i = new BDDVarSet[N];
		// BDDVarSet varsH2_i_QuantForH2_i;
		BDDVarSet[] varsH2_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[] varsH2_i_QuantForH4_i = new BDDVarSet[N];
		BDD[] idleOfH2_i_FromCl_i = new BDD[N];
		BDD[] idleOfH2_i_FromH0_i = new BDD[N];
		BDD[] idleOfH2_i_FromH1_i = new BDD[N];
		BDD[] idleOfH2_i_FromHu_i = new BDD[N];
		// BDD[] idleOfH2_i_FromH2_i = new BDD[N];
		BDD[] idleOfH2_i_FromH3_i = new BDD[N];
		BDD[] idleOfH2_i_FromH4_i = new BDD[N];
		for (int i = 0; i < N; i++) {

			varsH2_i_QuantForCl_i[i] = varsToQant(cur_cln, cur_com);
			idleOfH2_i_FromCl_i[i] = idleVars(cur_cln, cur_com);
			for (int k = 0; k < N; k++) {
				varsH2_i_QuantForCl_i[i] = varsH2_i_QuantForCl_i[i]
						.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
				idleOfH2_i_FromCl_i[i] = idleOfH2_i_FromCl_i[i]
						.and(idleVars(invald_l[k]));
			}

			varsH2_i_QuantForH0_i[i] = varsToQant();
			idleOfH2_i_FromH0_i[i] = idleVars();
			for (int k = 0; k < N; k++) {
				varsH2_i_QuantForH0_i[i] = varsH2_i_QuantForH0_i[i]
						.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
				idleOfH2_i_FromH0_i[i] = idleOfH2_i_FromH0_i[i]
						.and(idleVars(invald_l[k]));
			}

			varsH2_i_QuantForH1_i[i] = varsToQant();
			idleOfH2_i_FromH1_i[i] = idleVars();
			for (int k = 0; k < N; k++) {
				varsH2_i_QuantForH1_i[i] = varsH2_i_QuantForH1_i[i]
						.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
				idleOfH2_i_FromH1_i[i] = idleOfH2_i_FromH1_i[i]
						.and(idleVars(invald_l[k]));
			}

			varsH2_i_QuantForHu_i[i] = varsToQant();
			idleOfH2_i_FromHu_i[i] = idleVars();
			for (int k = 0; k < N; k++) {
				varsH2_i_QuantForHu_i[i] = varsH2_i_QuantForHu_i[i]
						.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
				idleOfH2_i_FromHu_i[i] = idleOfH2_i_FromHu_i[i]
						.and(idleVars(invald_l[k]));
			}

			varsH2_i_QuantForH3_i[i] = varsToQant(cur_cln);
			idleOfH2_i_FromH3_i[i] = idleVars(cur_cln);
			for (int k = 0; k < N; k++) {
				if (k != i) {
					varsH2_i_QuantForH3_i[i] = varsH2_i_QuantForH3_i[i]
							.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
					idleOfH2_i_FromH3_i[i] = idleOfH2_i_FromH3_i[i]
							.and(idleVars(invald_l[k]));
				}
			}

			varsH2_i_QuantForH4_i[i] = varsToQant(cur_cln);
			idleOfH2_i_FromH4_i[i] = idleVars(cur_cln);
			for (int k = 0; k < N; k++) {
				varsH2_i_QuantForH4_i[i] = varsH2_i_QuantForH4_i[i]
						.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
				idleOfH2_i_FromH4_i[i] = idleOfH2_i_FromH4_i[i]
						.and(idleVars(invald_l[k]));
			}
		}

		BDDVarSet[][] varsH2_i_QuantForCl_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfH2_i_FromCl_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH0_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH1_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromHu_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH2_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH4_j = new BDD[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH2_i_QuantForCl_j[i][j] = varsToQant(cur_cln, cur_com,
							channel1[i], channel2[i], channel3[i]);
					idleOfH2_i_FromCl_j[i][j] = idleVars(cur_cln, cur_com,
							channel1[i], channel2[i], channel3[i]);
					for (int k = 0; k < N; k++) {
						varsH2_i_QuantForCl_j[i][j] = varsH2_i_QuantForCl_j[i][j]
								.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
						idleOfH2_i_FromCl_j[i][j] = idleOfH2_i_FromCl_j[i][j]
								.and(idleVars(invald_l[k]));
					}

					varsH2_i_QuantForH0_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH2_i_FromH0_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);
					for (int k = 0; k < N; k++) {
						varsH2_i_QuantForH0_j[i][j] = varsH2_i_QuantForH0_j[i][j]
								.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
						idleOfH2_i_FromH0_j[i][j] = idleOfH2_i_FromH0_j[i][j]
								.and(idleVars(invald_l[k]));
					}

					varsH2_i_QuantForH1_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH2_i_FromH1_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);
					for (int k = 0; k < N; k++) {
						varsH2_i_QuantForH1_j[i][j] = varsH2_i_QuantForH1_j[i][j]
								.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
						idleOfH2_i_FromH1_j[i][j] = idleOfH2_i_FromH1_j[i][j]
								.and(idleVars(invald_l[k]));
					}

					varsH2_i_QuantForHu_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH2_i_FromHu_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);
					for (int k = 0; k < N; k++) {
						varsH2_i_QuantForHu_j[i][j] = varsH2_i_QuantForHu_j[i][j]
								.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
						idleOfH2_i_FromHu_j[i][j] = idleOfH2_i_FromHu_j[i][j]
								.and(idleVars(invald_l[k]));
					}

					varsH2_i_QuantForH2_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i], cur_cln);
					idleOfH2_i_FromH2_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i], cur_cln);

					varsH2_i_QuantForH3_j[i][j] = varsToQant(cur_cln,
							channel1[i], channel2[i], channel3[i]);
					idleOfH2_i_FromH3_j[i][j] = idleVars(cur_cln, channel1[i],
							channel2[i], channel3[i]);
					for (int k = 0; k < N; k++) {
						if (k != j) {
							varsH2_i_QuantForH3_j[i][j] = varsH2_i_QuantForH3_j[i][j]
									.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
							idleOfH2_i_FromH3_j[i][j] = idleOfH2_i_FromH3_j[i][j]
									.and(idleVars(invald_l[k]));
						}
					}

					varsH2_i_QuantForH4_j[i][j] = varsToQant(cur_cln,
							channel1[i], channel2[i], channel3[i]);
					idleOfH2_i_FromH4_j[i][j] = idleVars(cur_cln, channel1[i],
							channel2[i], channel3[i]);
					for (int k = 0; k < N; k++) {
						varsH2_i_QuantForH4_j[i][j] = varsH2_i_QuantForH4_j[i][j]
								.union(adjustBDDVarSetToManager(varsToQant(invald_l[k])));
						idleOfH2_i_FromH4_j[i][j] = idleOfH2_i_FromH4_j[i][j]
								.and(idleVars(invald_l[k]));
					}
				}
			}
		}

		// === H3
		BDDVarSet[] varsH3_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[][] varsH3_i_QuantForCl_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH3_i_QuantForH0_i = new BDDVarSet[N];
		BDDVarSet[][] varsH3_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH3_i_QuantForH1_i = new BDDVarSet[N];
		BDDVarSet[][] varsH3_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH3_i_QuantForHu_i = new BDDVarSet[N];
		;
		BDDVarSet[][] varsH3_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH3_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[][] varsH3_i_QuantForH2_j = new BDDVarSet[N][N];
		// BDDVarSet varsH3_i_QuantForH3_i;
		BDDVarSet[] varsH3_i_QuantForH3_j = new BDDVarSet[N];
		BDDVarSet[] varsH3_i_QuantForH4_i = new BDDVarSet[N];
		BDDVarSet[] varsH3_i_QuantForH4_j = new BDDVarSet[N];
		BDD[] idleOfH3_i_FromCl_i = new BDD[N];
		BDD[][] idleOfH3_i_FromCl_j = new BDD[N][N];
		BDD[] idleOfH3_i_FromH0_i = new BDD[N];
		BDD[][] idleOfH3_i_FromH0_j = new BDD[N][N];
		BDD[] idleOfH3_i_FromH1_i = new BDD[N];
		BDD[][] idleOfH3_i_FromH1_j = new BDD[N][N];
		BDD[] idleOfH3_i_FromHu_i = new BDD[N];
		BDD[][] idleOfH3_i_FromHu_j = new BDD[N][N];
		BDD[] idleOfH3_i_FromH2_i = new BDD[N];
		BDD[][] idleOfH3_i_FromH2_j = new BDD[N][N];
		// BDD[] idleOfH3_i_FromH3_i = new BDD[N];
		BDD[] idleOfH3_i_FromH3_j = new BDD[N];
		BDD[] idleOfH3_i_FromH4_i = new BDD[N];
		BDD[] idleOfH3_i_FromH4_j = new BDD[N];

		for (int i = 0; i < N; i++) {
			varsH3_i_QuantForCl_i[i] = varsToQant(cur_com);
			idleOfH3_i_FromCl_i[i] = idleVars(cur_com);
			varsH3_i_QuantForCl_i[i] = varsH3_i_QuantForCl_i[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromCl_i[i] = idleOfH3_i_FromCl_i[i]
					.and(idleVars(invald_l[i]));

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH3_i_QuantForCl_j[i][j] = varsToQant(cur_com,
							channel1[i], channel2[i], channel3[i]);
					idleOfH3_i_FromCl_j[i][j] = idleVars(cur_com, channel1[i],
							channel2[i], channel3[i]);
					varsH3_i_QuantForCl_j[i][j] = varsH3_i_QuantForCl_j[i][j]
							.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
					idleOfH3_i_FromCl_j[i][j] = idleOfH3_i_FromCl_j[i][j]
							.and(idleVars(invald_l[i]));
				}
			}

			varsH3_i_QuantForH0_i[i] = varsToQant();
			idleOfH3_i_FromH0_i[i] = idleVars();
			varsH3_i_QuantForH0_i[i] = varsH3_i_QuantForH0_i[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromH0_i[i] = idleOfH3_i_FromH0_i[i]
					.and(idleVars(invald_l[i]));
			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH3_i_QuantForH0_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH3_i_FromH0_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
					varsH3_i_QuantForH0_j[i][j] = varsH3_i_QuantForH0_j[i][j]
							.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
					idleOfH3_i_FromH0_j[i][j] = idleOfH3_i_FromH0_j[i][j]
							.and(idleVars(invald_l[i]));
				}
			}

			varsH3_i_QuantForH1_i[i] = varsToQant();
			idleOfH3_i_FromH1_i[i] = idleVars();
			varsH3_i_QuantForH1_i[i] = varsH3_i_QuantForH1_i[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromH1_i[i] = idleOfH3_i_FromH1_i[i]
					.and(idleVars(invald_l[i]));

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH3_i_QuantForH1_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH3_i_FromH1_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
					varsH3_i_QuantForH1_j[i][j] = varsH3_i_QuantForH1_j[i][j]
							.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
					idleOfH3_i_FromH1_j[i][j] = idleOfH3_i_FromH1_j[i][j]
							.and(idleVars(invald_l[i]));
				}
			}

			varsH3_i_QuantForHu_i[i] = varsToQant();
			idleOfH3_i_FromHu_i[i] = idleVars();
			varsH3_i_QuantForHu_i[i] = varsH3_i_QuantForHu_i[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromHu_i[i] = idleOfH3_i_FromHu_i[i]
					.and(idleVars(invald_l[i]));

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH3_i_QuantForHu_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH3_i_FromHu_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
					varsH3_i_QuantForHu_j[i][j] = varsH3_i_QuantForHu_j[i][j]
							.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
					idleOfH3_i_FromHu_j[i][j] = idleOfH3_i_FromHu_j[i][j]
							.and(idleVars(invald_l[i]));
				}
			}

			varsH3_i_QuantForH2_i[i] = varsToQant();
			idleOfH3_i_FromH2_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH3_i_QuantForH2_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH3_i_FromH2_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH3_i_QuantForH3_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i]);
			idleOfH3_i_FromH3_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i]);
			varsH3_i_QuantForH3_j[i] = varsH3_i_QuantForH3_j[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromH3_j[i] = idleOfH3_i_FromH3_j[i]
					.and(idleVars(invald_l[i]));

			varsH3_i_QuantForH4_i[i] = varsToQant();
			idleOfH3_i_FromH4_i[i] = idleVars();
			varsH3_i_QuantForH4_i[i] = varsH3_i_QuantForH4_i[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromH4_i[i] = idleOfH3_i_FromH4_i[i]
					.and(idleVars(invald_l[i]));

			varsH3_i_QuantForH4_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i]);
			idleOfH3_i_FromH4_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i]);
			varsH3_i_QuantForH4_j[i] = varsH3_i_QuantForH4_j[i]
					.union(adjustBDDVarSetToManager(varsToQant(invald_l[i])));
			idleOfH3_i_FromH4_j[i] = idleOfH3_i_FromH4_j[i]
					.and(idleVars(invald_l[i]));
		}

		// === H4
		BDDVarSet[] varsH4_i_QuantForCl_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForCl_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH4_i_QuantForH0_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH4_i_QuantForH1_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH4_i_QuantForHu_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH4_i_QuantForH2_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[] varsH4_i_QuantForH3_i = new BDDVarSet[N];
		BDDVarSet[][] varsH4_i_QuantForH3_j = new BDDVarSet[N][N];
		// BDDVarSet varsH4_i_QuantForH4_i;
		BDDVarSet[] varsH4_i_QuantForH4_j = new BDDVarSet[N];
		BDD[] idleOfH4_i_FromCl_i = new BDD[N];
		BDD[][] idleOfH4_i_FromCl_j = new BDD[N][N];
		BDD[] idleOfH4_i_FromH0_i = new BDD[N];
		BDD[][] idleOfH4_i_FromH0_j = new BDD[N][N];
		BDD[] idleOfH4_i_FromH1_i = new BDD[N];
		BDD[][] idleOfH4_i_FromH1_j = new BDD[N][N];
		BDD[] idleOfH4_i_FromHu_i = new BDD[N];
		BDD[][] idleOfH4_i_FromHu_j = new BDD[N][N];
		BDD[] idleOfH4_i_FromH2_i = new BDD[N];
		BDD[][] idleOfH4_i_FromH2_j = new BDD[N][N];
		BDD[] idleOfH4_i_FromH3_i = new BDD[N];
		BDD[][] idleOfH4_i_FromH3_j = new BDD[N][N];
		// BDD[] idleOfH4_i_FromH4_i = new BDD[N];
		BDD[] idleOfH4_i_FromH4_j = new BDD[N];
		for (int i = 0; i < N; i++) {
			varsH4_i_QuantForCl_i[i] = varsToQant(cur_com);
			idleOfH4_i_FromCl_i[i] = idleVars(cur_com);

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForCl_j[i][j] = varsToQant(cur_com,
							channel1[i], channel2[i], channel3[i]);
					idleOfH4_i_FromCl_j[i][j] = idleVars(cur_com, channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForH0_i[i] = varsToQant();
			idleOfH4_i_FromH0_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForH0_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH4_i_FromH0_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForH1_i[i] = varsToQant();
			idleOfH4_i_FromH1_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForH1_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH4_i_FromH1_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForHu_i[i] = varsToQant();
			idleOfH4_i_FromHu_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForHu_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH4_i_FromHu_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForH2_i[i] = varsToQant();
			idleOfH4_i_FromH2_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForH2_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH4_i_FromH2_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForH3_i[i] = varsToQant();
			idleOfH4_i_FromH3_i[i] = idleVars();

			for (int j = 0; j < N; j++) {
				if (j != i) {
					varsH4_i_QuantForH3_j[i][j] = varsToQant(channel1[i],
							channel2[i], channel3[i]);
					idleOfH4_i_FromH3_j[i][j] = idleVars(channel1[i],
							channel2[i], channel3[i]);
				}
			}

			varsH4_i_QuantForH4_j[i] = varsToQant(channel1[i], channel2[i],
					channel3[i]);
			idleOfH4_i_FromH4_j[i] = idleVars(channel1[i], channel2[i],
					channel3[i]);
		}
		
		// ////////// === FINISH initial settings
		this.semConstruction.release(1);
		// stop to calculate construction.
		this.semConstructionGo.acquireUninterruptibly(1);

		// compute thetas initially
		for (int i = 0; i < N; i++) {
			if (do_delta_improvement) {
				deltaThetas_cl[i] = Util.newAllSuccNotIncl(oldThetas_cl[i],
						extTrans_cl[i], vars_cl[i]);
				deltaThetas_h0[i] = Util.newAllSuccNotIncl(oldThetas_h0[i],
						extTrans_h0[i], vars_h0[i]);
				deltaThetas_h1[i] = Util.newAllSuccNotIncl(oldThetas_h1[i],
						extTrans_h1[i], vars_h1[i]);
				deltaThetas_hu[i] = Util.newAllSuccNotIncl(oldThetas_hu[i],
						extTrans_hu[i], vars_hu[i]);
				deltaThetas_h2[i] = Util.newAllSuccNotIncl(oldThetas_h2[i],
						extTrans_h2[i], vars_h2[i]);
				deltaThetas_h3[i] = Util.newAllSuccNotIncl(oldThetas_h3[i],
						extTrans_h3[i], vars_h3[i]);
				deltaThetas_h4[i] = Util.newAllSuccNotIncl(oldThetas_h4[i],
						extTrans_h4[i], vars_h4[i]);
				newThetas_cl[i] = oldThetas_cl[i].or(deltaThetas_cl[i]);
				newThetas_h0[i] = oldThetas_h0[i].or(deltaThetas_h0[i]);
				newThetas_h1[i] = oldThetas_h1[i].or(deltaThetas_h1[i]);
				newThetas_hu[i] = oldThetas_hu[i].or(deltaThetas_hu[i]);
				newThetas_h2[i] = oldThetas_h2[i].or(deltaThetas_h2[i]);
				newThetas_h3[i] = oldThetas_h3[i].or(deltaThetas_h3[i]);
				newThetas_h4[i] = oldThetas_h4[i].or(deltaThetas_h4[i]);
			} else {
				newThetas_cl[i] = Util.newAllSucc(oldThetas_cl[i],
						extTrans_cl[i], vars_cl[i]);
				newThetas_h0[i] = Util.newAllSucc(oldThetas_h0[i],
						extTrans_h0[i], vars_h0[i]);
				newThetas_h1[i] = Util.newAllSucc(oldThetas_h1[i],
						extTrans_h1[i], vars_h1[i]);
				newThetas_hu[i] = Util.newAllSucc(oldThetas_hu[i],
						extTrans_hu[i], vars_hu[i]);
				newThetas_h2[i] = Util.newAllSucc(oldThetas_h2[i],
						extTrans_h2[i], vars_h2[i]);
				newThetas_h3[i] = Util.newAllSucc(oldThetas_h3[i],
						extTrans_h3[i], vars_h3[i]);
				newThetas_h4[i] = Util.newAllSucc(oldThetas_h4[i],
						extTrans_h4[i], vars_h4[i]);
			}
			oldThetas_cl[i] = newThetas_cl[i];
			oldThetas_h0[i] = newThetas_h0[i];
			oldThetas_h1[i] = newThetas_h1[i];
			oldThetas_hu[i] = newThetas_hu[i];
			oldThetas_h2[i] = newThetas_h2[i];
			oldThetas_h3[i] = newThetas_h3[i];
			oldThetas_h4[i] = newThetas_h4[i];
		}

		// flag indicating a fixpoint reached
		boolean fixPointFlag;
		// what processes give others:

		int counter = 0;
		do {
			counter++;
			if (proc_id == 0)
				System.out.println("counter = " + counter);

			// computing the global transition of each process
			BDD[] globalTrans_cl = new BDD[N];
			BDD[] globalTrans_h0 = new BDD[N];
			BDD[] globalTrans_h1 = new BDD[N];
			BDD[] globalTrans_hu = new BDD[N];
			BDD[] globalTrans_h2 = new BDD[N];
			BDD[] globalTrans_h3 = new BDD[N];
			BDD[] globalTrans_h4 = new BDD[N];
			for (int i = 0; i < N; i++) {
				if (do_delta_improvement) {
					globalTrans_cl[i] = (trans_cl[i].and(oldThetas_cl[i])
							.and(Env.prime(deltaThetas_cl[i])));
					globalTrans_h0[i] = (trans_h0[i].and(oldThetas_h0[i])
							.and(Env.prime(deltaThetas_h0[i])));
					globalTrans_h1[i] = (trans_h1[i].and(oldThetas_h1[i])
							.and(Env.prime(deltaThetas_h1[i])));
					globalTrans_hu[i] = (trans_hu[i].and(oldThetas_hu[i])
							.and(Env.prime(deltaThetas_hu[i])));
					globalTrans_h2[i] = (trans_h2[i].and(oldThetas_h2[i])
							.and(Env.prime(deltaThetas_h2[i])));
					globalTrans_h3[i] = (trans_h3[i].and(oldThetas_h3[i])
							.and(Env.prime(deltaThetas_h3[i])));
					globalTrans_h4[i] = (trans_h4[i].and(oldThetas_h4[i])
							.and(Env.prime(deltaThetas_h4[i])));
				} else {
					globalTrans_cl[i] = (trans_cl[i].and(oldThetas_cl[i])
							.and(Env.prime(newThetas_cl[i])));
					globalTrans_h0[i] = (trans_h0[i].and(oldThetas_h0[i])
							.and(Env.prime(newThetas_h0[i])));
					globalTrans_h1[i] = (trans_h1[i].and(oldThetas_h1[i])
							.and(Env.prime(newThetas_h1[i])));
					globalTrans_hu[i] = (trans_hu[i].and(oldThetas_hu[i])
							.and(Env.prime(newThetas_hu[i])));
					globalTrans_h2[i] = (trans_h2[i].and(oldThetas_h2[i])
							.and(Env.prime(newThetas_h2[i])));
					globalTrans_h3[i] = (trans_h3[i].and(oldThetas_h3[i])
							.and(Env.prime(newThetas_h3[i])));
					globalTrans_h4[i] = (trans_h4[i].and(oldThetas_h4[i])
							.and(Env.prime(newThetas_h4[i])));
				}
			}
			// == setting the new transition to be sent for each process
			// --clients give
			BDD[] globalTrans_cl_i_For_H0_i = new BDD[N];
			BDD[] globalTrans_cl_i_For_H1_i = new BDD[N];
			BDD[] globalTrans_cl_i_For_Hu_i = new BDD[N];
			BDD[] globalTrans_cl_i_For_H2_i = new BDD[N];
			BDD[] globalTrans_cl_i_For_H3_i = new BDD[N];
			BDD[] globalTrans_cl_i_For_H4_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				globalTrans_cl_i_For_H0_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForH0_i[i]);
				globalTrans_cl_i_For_H1_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForH1_i[i]);
				globalTrans_cl_i_For_Hu_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForHu_i[i]);
				globalTrans_cl_i_For_H2_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForH2_i[i]);
				globalTrans_cl_i_For_H3_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForH3_i[i]);
				globalTrans_cl_i_For_H4_i[i] = globalTrans_cl[i]
						.exist(varsCl_i_QuantForH4_i[i]);
			}
			BDD[][] globalTrans_cl_i_For_H0_j = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H1_j = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_Hu_j = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H2_j = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H3_j = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_cl_i_For_H0_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForH0_j[i]);
						globalTrans_cl_i_For_H1_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForH1_j[i]);
						globalTrans_cl_i_For_Hu_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForHu_j[i]);
						globalTrans_cl_i_For_H2_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForH2_j[i]);
						globalTrans_cl_i_For_H3_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForH3_j[i]);
						globalTrans_cl_i_For_H4_j[i][j] = globalTrans_cl[i]
								.exist(varsCl_i_QuantForH4_j[i]);
					}
				}
			}

			// --H0s give
			BDD[] globalTrans_H0_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_Cl_j = new BDD[N][N];
			// BDD[] globalTrans_H0_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_H0_j = new BDD[N][N];
			BDD[] globalTrans_H0_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_H1_j = new BDD[N][N];
			BDD[] globalTrans_H0_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_Hu_j = new BDD[N][N];
			BDD[] globalTrans_H0_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_H2_j = new BDD[N][N];
			BDD[] globalTrans_H0_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_H3_j = new BDD[N][N];
			BDD[] globalTrans_H0_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_H0_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_H0_i_For_Cl_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForCl_i[i]);
				// globalTrans_H0_i_For_H0_i[i] =
				// globalTrans_h0[i].exist(varsH0_i_QuantForH0_i[i]);
				globalTrans_H0_i_For_H1_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForH1_i[i]);
				globalTrans_H0_i_For_Hu_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForHu_i[i]);
				globalTrans_H0_i_For_H2_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForH2_i[i]);
				globalTrans_H0_i_For_H3_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForH3_i[i]);
				globalTrans_H0_i_For_H4_i[i] = globalTrans_h0[i]
						.exist(varsH0_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_H0_i_For_Cl_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForCl_j[i][j]);
						globalTrans_H0_i_For_H0_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForH0_j[i]);
						globalTrans_H0_i_For_H1_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForH1_j[i]);
						globalTrans_H0_i_For_Hu_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForHu_j[i]);
						globalTrans_H0_i_For_H2_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForH2_j[i]);
						globalTrans_H0_i_For_H3_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForH3_j[i]);
						globalTrans_H0_i_For_H4_j[i][j] = globalTrans_h0[i]
								.exist(varsH0_i_QuantForH4_j[i]);
					}
				}
			}
			// --H1s give
			BDD[] globalTrans_H1_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_Cl_j = new BDD[N][N];
			BDD[] globalTrans_H1_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_H0_j = new BDD[N][N];
			// BDD[] globalTrans_H1_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_H1_j = new BDD[N][N];
			BDD[] globalTrans_H1_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_Hu_j = new BDD[N][N];
			BDD[] globalTrans_H1_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_H2_j = new BDD[N][N];
			BDD[] globalTrans_H1_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_H3_j = new BDD[N][N];
			BDD[] globalTrans_H1_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_H1_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_H1_i_For_Cl_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForCl_i[i]);
				globalTrans_H1_i_For_H0_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForH0_i[i]);
				// globalTrans_H1_i_For_H1_i[i] =
				// globalTrans_h1[i].exist(varsH1_i_QuantForH1_i[i]);
				globalTrans_H1_i_For_Hu_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForHu_i[i]);
				globalTrans_H1_i_For_H2_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForH2_i[i]);
				globalTrans_H1_i_For_H3_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForH3_i[i]);
				globalTrans_H1_i_For_H4_i[i] = globalTrans_h1[i]
						.exist(varsH1_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_H1_i_For_Cl_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForCl_j[i][j]);
						globalTrans_H1_i_For_H0_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForH0_j[i][j]);
						globalTrans_H1_i_For_H1_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForH1_j[i][j]);
						globalTrans_H1_i_For_Hu_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForHu_j[i][j]);
						globalTrans_H1_i_For_H2_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForH2_j[i][j]);
						globalTrans_H1_i_For_H3_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForH3_j[i][j]);
						globalTrans_H1_i_For_H4_j[i][j] = globalTrans_h1[i]
								.exist(varsH1_i_QuantForH4_j[i][j]);
					}
				}
			}
			// --Hus give
			BDD[] globalTrans_Hu_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_Cl_j = new BDD[N][N];
			BDD[] globalTrans_Hu_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_H0_j = new BDD[N][N];
			BDD[] globalTrans_Hu_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_H1_j = new BDD[N][N];
			// BDD[] globalTrans_Hu_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_Hu_j = new BDD[N][N];
			BDD[] globalTrans_Hu_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_H2_j = new BDD[N][N];
			BDD[] globalTrans_Hu_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_H3_j = new BDD[N][N];
			BDD[] globalTrans_Hu_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_Hu_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_Hu_i_For_Cl_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForCl_i[i]);
				globalTrans_Hu_i_For_H0_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForH0_i[i]);
				globalTrans_Hu_i_For_H1_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForH1_i[i]);
				// globalTrans_Hu_i_For_Hu_i[i] =
				// globalTrans_hu[i].exist(varsHu_i_QuantForHu_i[i]);
				globalTrans_Hu_i_For_H2_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForH2_i[i]);
				globalTrans_Hu_i_For_H3_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForH3_i[i]);
				globalTrans_Hu_i_For_H4_i[i] = globalTrans_hu[i]
						.exist(varsHu_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_Hu_i_For_Cl_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForCl_j[i][j]);
						globalTrans_Hu_i_For_H0_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForH0_j[i][j]);
						globalTrans_Hu_i_For_H1_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForH1_j[i][j]);
						globalTrans_Hu_i_For_Hu_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForHu_j[i][j]);
						globalTrans_Hu_i_For_H2_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForH2_j[i][j]);
						globalTrans_Hu_i_For_H3_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForH3_j[i][j]);
						globalTrans_Hu_i_For_H4_j[i][j] = globalTrans_hu[i]
								.exist(varsHu_i_QuantForH4_j[i][j]);
					}
				}
			}
			// --H2s give
			BDD[] globalTrans_H2_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_Cl_j = new BDD[N][N];
			BDD[] globalTrans_H2_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_H0_j = new BDD[N][N];
			BDD[] globalTrans_H2_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_H1_j = new BDD[N][N];
			BDD[] globalTrans_H2_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_Hu_j = new BDD[N][N];
			// BDD[] globalTrans_H2_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_H2_j = new BDD[N][N];
			BDD[] globalTrans_H2_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_H3_j = new BDD[N][N];
			BDD[] globalTrans_H2_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_H2_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_H2_i_For_Cl_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForCl_i[i]);
				globalTrans_H2_i_For_H0_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForH0_i[i]);
				globalTrans_H2_i_For_H1_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForH1_i[i]);
				globalTrans_H2_i_For_Hu_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForHu_i[i]);
				// globalTrans_H2_i_For_H2_i[i] =
				// globalTrans_h2[i].exist(varsH2_i_QuantForH2_i[i]);
				globalTrans_H2_i_For_H3_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForH3_i[i]);
				globalTrans_H2_i_For_H4_i[i] = globalTrans_h2[i]
						.exist(varsH2_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_H2_i_For_Cl_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForCl_j[i][j]);
						globalTrans_H2_i_For_H0_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForH0_j[i][j]);
						globalTrans_H2_i_For_H1_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForH1_j[i][j]);
						globalTrans_H2_i_For_Hu_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForHu_j[i][j]);
						globalTrans_H2_i_For_H2_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForH2_j[i][j]);
						globalTrans_H2_i_For_H3_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForH3_j[i][j]);
						globalTrans_H2_i_For_H4_j[i][j] = globalTrans_h2[i]
								.exist(varsH2_i_QuantForH4_j[i][j]);
					}
				}
			}
			// --H3s give
			BDD[] globalTrans_H3_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_Cl_j = new BDD[N][N];
			BDD[] globalTrans_H3_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_H0_j = new BDD[N][N];
			BDD[] globalTrans_H3_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_H1_j = new BDD[N][N];
			BDD[] globalTrans_H3_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_Hu_j = new BDD[N][N];
			BDD[] globalTrans_H3_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_H2_j = new BDD[N][N];
			// BDD[] globalTrans_H3_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_H3_j = new BDD[N][N];
			BDD[] globalTrans_H3_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_H3_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_H3_i_For_Cl_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForCl_i[i]);
				globalTrans_H3_i_For_H0_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForH0_i[i]);
				globalTrans_H3_i_For_H1_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForH1_i[i]);
				globalTrans_H3_i_For_Hu_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForHu_i[i]);
				globalTrans_H3_i_For_H2_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForH2_i[i]);
				// globalTrans_H3_i_For_H3_i[i] =
				// globalTrans_h0[i].exist(varsH3_i_QuantForH3_i[i]);
				globalTrans_H3_i_For_H4_i[i] = globalTrans_h3[i]
						.exist(varsH3_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_H3_i_For_Cl_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForCl_j[i][j]);
						globalTrans_H3_i_For_H0_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForH0_j[i][j]);
						globalTrans_H3_i_For_H1_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForH1_j[i][j]);
						globalTrans_H3_i_For_Hu_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForHu_j[i][j]);
						globalTrans_H3_i_For_H2_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForH2_j[i][j]);
						globalTrans_H3_i_For_H3_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForH3_j[i]);
						globalTrans_H3_i_For_H4_j[i][j] = globalTrans_h3[i]
								.exist(varsH3_i_QuantForH4_j[i]);
					}
				}
			}
			// --H4s give
			BDD[] globalTrans_H4_i_For_Cl_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_Cl_j = new BDD[N][N];
			BDD[] globalTrans_H4_i_For_H0_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_H0_j = new BDD[N][N];
			BDD[] globalTrans_H4_i_For_H1_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_H1_j = new BDD[N][N];
			BDD[] globalTrans_H4_i_For_Hu_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_Hu_j = new BDD[N][N];
			BDD[] globalTrans_H4_i_For_H2_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_H2_j = new BDD[N][N];
			BDD[] globalTrans_H4_i_For_H3_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_H3_j = new BDD[N][N];
			// BDD[] globalTrans_H4_i_For_H4_i = new BDD[N];
			BDD[][] globalTrans_H4_i_For_H4_j = new BDD[N][N];
			for (int i = 0; i < N; i++) {
				globalTrans_H4_i_For_Cl_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForCl_i[i]);
				globalTrans_H4_i_For_H0_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForH0_i[i]);
				globalTrans_H4_i_For_H1_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForH1_i[i]);
				globalTrans_H4_i_For_Hu_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForHu_i[i]);
				globalTrans_H4_i_For_H2_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForH2_i[i]);
				globalTrans_H4_i_For_H3_i[i] = globalTrans_h4[i]
						.exist(varsH4_i_QuantForH3_i[i]);
				// globalTrans_H4_i_For_H4_i[i] =
				// globalTrans_h0[i].exist(varsH4_i_QuantForH4_i[i]);
				for (int j = 0; j < N; j++) {
					if (j != i) {
						globalTrans_H4_i_For_Cl_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForCl_j[i][j]);
						globalTrans_H4_i_For_H0_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForH0_j[i][j]);
						globalTrans_H4_i_For_H1_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForH1_j[i][j]);
						globalTrans_H4_i_For_Hu_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForHu_j[i][j]);
						globalTrans_H4_i_For_H2_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForH2_j[i][j]);
						globalTrans_H4_i_For_H3_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForH3_j[i][j]);
						globalTrans_H4_i_For_H4_j[i][j] = globalTrans_h4[i]
								.exist(varsH4_i_QuantForH4_j[i]);
					}
				}
			}
			// ====== build new transition for each process =========
			// for clients
			BDD[] newGlobalTransFor_Cl_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_Cl_i[i] = Env.FALSE();
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_H0_i_For_Cl_i[i]
								.and(idleOfCl_i_FromH0_i[i]));
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_H1_i_For_Cl_i[i]
								.and(idleOfCl_i_FromH1_i[i]));
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_Hu_i_For_Cl_i[i]
								.and(idleOfCl_i_FromHu_i[i]));
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_H2_i_For_Cl_i[i]
								.and(idleOfCl_i_FromH2_i[i]));
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_H3_i_For_Cl_i[i]
								.and(idleOfCl_i_FromH3_i[i]));
				newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
						.or(globalTrans_H4_i_For_Cl_i[i]
								.and(idleOfCl_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_H0_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromH0_j[i][j]));
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_H1_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromH1_j[i][j]));
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_Hu_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromHu_j[i][j]));
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_H2_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromH2_j[i][j]));
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_H3_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromH3_j[i][j]));
						newGlobalTransFor_Cl_i[i] = newGlobalTransFor_Cl_i[i]
								.or(globalTrans_H4_i_For_Cl_j[j][i]
										.and(idleOfCl_i_FromH4_j[i][j]));
					}
				}
			}

			// for Home0s
			BDD[] newGlobalTransFor_H0_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_H0_i[i] = Env.FALSE();
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_cl_i_For_H0_i[i]
								.and(idleOfH0_i_FromCl_i[i]));
				// newGlobalTransFor_H0_i[i] =
				// newGlobalTransFor_H0_i[i].or(globalTrans_H0_i_For_H0_i[i]
				// .and(idleOfH0_i_FromH0_i[i]));
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_H1_i_For_H0_i[i]
								.and(idleOfH0_i_FromH1_i[i]));
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_Hu_i_For_H0_i[i]
								.and(idleOfH0_i_FromHu_i[i]));
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_H2_i_For_H0_i[i]
								.and(idleOfH0_i_FromH2_i[i]));
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_H3_i_For_H0_i[i]
								.and(idleOfH0_i_FromH3_i[i]));
				newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
						.or(globalTrans_H4_i_For_H0_i[i]
								.and(idleOfH0_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_cl_i_For_H0_j[j][i]// ariel j
										.and(idleOfH0_i_FromCl_j[i][j]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_H0_i_For_H0_j[j][i]// ariel j
										.and(idleOfH0_i_FromH0_j[i]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_H1_i_For_H0_j[j][i]
										.and(idleOfH0_i_FromH1_j[i]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_Hu_i_For_H0_j[j][i]
										.and(idleOfH0_i_FromHu_j[i]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_H2_i_For_H0_j[j][i]
										.and(idleOfH0_i_FromH2_j[i]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_H3_i_For_H0_j[j][i]
										.and(idleOfH0_i_FromH3_j[i]));
						newGlobalTransFor_H0_i[i] = newGlobalTransFor_H0_i[i]
								.or(globalTrans_H4_i_For_H0_j[j][i]
										.and(idleOfH0_i_FromH4_j[i]));
					}
				}
			}
			// For Home1s
			BDD[] newGlobalTransFor_H1_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_H1_i[i] = Env.FALSE();
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_cl_i_For_H1_i[i]
								.and(idleOfH1_i_FromCl_i[i]));
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_H0_i_For_H1_i[i]
								.and(idleOfH1_i_FromH0_i[i]));
				// newGlobalTransFor_H1_i[i] =
				// newGlobalTransFor_H1_i[i].or(globalTrans_H1_i_For_H1_i[i]
				// .and(idleOfH1_i_FromH1_i[i]));
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_Hu_i_For_H1_i[i]
								.and(idleOfH1_i_FromHu_i[i]));
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_H2_i_For_H1_i[i]
								.and(idleOfH1_i_FromH2_i[i]));
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_H3_i_For_H1_i[i]
								.and(idleOfH1_i_FromH3_i[i]));
				newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
						.or(globalTrans_H4_i_For_H1_i[i]
								.and(idleOfH1_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_cl_i_For_H1_j[j][i]// ariel j
										.and(idleOfH1_i_FromCl_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_H0_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromH0_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_H1_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromH1_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_Hu_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromHu_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_H2_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromH2_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_H3_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromH3_j[i][j]));
						newGlobalTransFor_H1_i[i] = newGlobalTransFor_H1_i[i]
								.or(globalTrans_H4_i_For_H1_j[j][i]
										.and(idleOfH1_i_FromH4_j[i][j]));
					}
				}
			}
			// For Homeus
			BDD[] newGlobalTransFor_Hu_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_Hu_i[i] = Env.FALSE();
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_cl_i_For_Hu_i[i]
								.and(idleOfHu_i_FromCl_i[i]));
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_H0_i_For_Hu_i[i]
								.and(idleOfHu_i_FromH0_i[i]));
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_H1_i_For_Hu_i[i]
								.and(idleOfHu_i_FromH1_i[i]));
				// newGlobalTransFor_Hu_i[i] =
				// newGlobalTransFor_Hu_i[i].or(globalTrans_Hu_i_For_Hu_i[i]
				// .and(idleOfHu_i_FromHu_i[i]));
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_H2_i_For_Hu_i[i]
								.and(idleOfHu_i_FromH2_i[i]));
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_H3_i_For_Hu_i[i]
								.and(idleOfHu_i_FromH3_i[i]));
				newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
						.or(globalTrans_H4_i_For_Hu_i[i]
								.and(idleOfHu_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_cl_i_For_Hu_j[j][i]// ariel j
										.and(idleOfHu_i_FromCl_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_H0_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromH0_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_H1_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromH1_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_Hu_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromHu_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_H2_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromH2_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_H3_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromH3_j[i][j]));
						newGlobalTransFor_Hu_i[i] = newGlobalTransFor_Hu_i[i]
								.or(globalTrans_H4_i_For_Hu_j[j][i]
										.and(idleOfHu_i_FromH4_j[i][j]));
					}
				}
			}
			// For Home2s
			BDD[] newGlobalTransFor_H2_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_H2_i[i] = Env.FALSE();
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_cl_i_For_H2_i[i]
								.and(idleOfH2_i_FromCl_i[i]));
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_H0_i_For_H2_i[i]
								.and(idleOfH2_i_FromH0_i[i]));
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_H1_i_For_H2_i[i]
								.and(idleOfH2_i_FromH1_i[i]));
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_Hu_i_For_H2_i[i]
								.and(idleOfH2_i_FromHu_i[i]));
				// newGlobalTransFor_H2_i[i] =
				// newGlobalTransFor_H2_i[i].or(globalTrans_H2_i_For_H2_i[i]
				// .and(idleOfH2_i_FromH2_i[i]));
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_H3_i_For_H2_i[i]
								.and(idleOfH2_i_FromH3_i[i]));
				newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
						.or(globalTrans_H4_i_For_H2_i[i]
								.and(idleOfH2_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_cl_i_For_H2_j[j][i]// ariel j
										.and(idleOfH2_i_FromCl_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_H0_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromH0_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_H1_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromH1_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_Hu_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromHu_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_H2_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromH2_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_H3_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromH3_j[i][j]));
						newGlobalTransFor_H2_i[i] = newGlobalTransFor_H2_i[i]
								.or(globalTrans_H4_i_For_H2_j[j][i]
										.and(idleOfH2_i_FromH4_j[i][j]));
					}
				}
			}
			// For home3s
			BDD[] newGlobalTransFor_H3_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_H3_i[i] = Env.FALSE();
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_cl_i_For_H3_i[i]
								.and(idleOfH3_i_FromCl_i[i]));
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_H0_i_For_H3_i[i]
								.and(idleOfH3_i_FromH0_i[i]));
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_H1_i_For_H3_i[i]
								.and(idleOfH3_i_FromH1_i[i]));
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_Hu_i_For_H3_i[i]
								.and(idleOfH3_i_FromHu_i[i]));
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_H2_i_For_H3_i[i]
								.and(idleOfH3_i_FromH2_i[i]));
				// newGlobalTransFor_H3_i[i] =
				// newGlobalTransFor_H3_i[i].or(globalTrans_H3_i_For_H3_i[i]
				// .and(idleOfH3_i_FromH3_i[i]));
				newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
						.or(globalTrans_H4_i_For_H3_i[i]
								.and(idleOfH3_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_cl_i_For_H3_j[j][i]// ariel j
										.and(idleOfH3_i_FromCl_j[i][j]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_H0_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromH0_j[i][j]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_H1_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromH1_j[i][j]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_Hu_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromHu_j[i][j]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_H2_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromH2_j[i][j]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_H3_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromH3_j[i]));
						newGlobalTransFor_H3_i[i] = newGlobalTransFor_H3_i[i]
								.or(globalTrans_H4_i_For_H3_j[j][i]
										.and(idleOfH3_i_FromH4_j[i]));
					}
				}
			}
			// for home4s
			BDD[] newGlobalTransFor_H4_i = new BDD[N];
			for (int i = 0; i < N; i++) {
				newGlobalTransFor_H4_i[i] = Env.FALSE();
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_cl_i_For_H4_i[i]
								.and(idleOfH4_i_FromCl_i[i]));
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_H0_i_For_H4_i[i]
								.and(idleOfH4_i_FromH0_i[i]));
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_H1_i_For_H4_i[i]
								.and(idleOfH4_i_FromH1_i[i]));
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_Hu_i_For_H4_i[i]
								.and(idleOfH4_i_FromHu_i[i]));
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_H2_i_For_H4_i[i]
								.and(idleOfH4_i_FromH2_i[i]));
				newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
						.or(globalTrans_H3_i_For_H4_i[i]
								.and(idleOfH4_i_FromH3_i[i]));
				// newGlobalTransFor_H4_i[i] =
				// newGlobalTransFor_H4_i[i].or(globalTrans_H4_i_For_H4_i[i]
				// .and(idleOfH4_i_FromH4_i[i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_cl_i_For_H4_j[j][i]// ariel j
										.and(idleOfH4_i_FromCl_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_H0_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromH0_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_H1_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromH1_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_Hu_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromHu_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_H2_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromH2_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_H3_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromH3_j[i][j]));
						newGlobalTransFor_H4_i[i] = newGlobalTransFor_H4_i[i]
								.or(globalTrans_H4_i_For_H4_j[j][i]
										.and(idleOfH4_i_FromH4_j[i]));
					}
				}
			}
			// collecting the transitions of each process
			for (int i = 0; i < N; i++) {
				extTrans_cl[i] = extTrans_cl[i].or(newGlobalTransFor_Cl_i[i]);
				extTrans_h0[i] = extTrans_h0[i].or(newGlobalTransFor_H0_i[i]);
				extTrans_h1[i] = extTrans_h1[i].or(newGlobalTransFor_H1_i[i]);
				extTrans_hu[i] = extTrans_hu[i].or(newGlobalTransFor_Hu_i[i]);
				extTrans_h2[i] = extTrans_h2[i].or(newGlobalTransFor_H2_i[i]);
				extTrans_h3[i] = extTrans_h3[i].or(newGlobalTransFor_H3_i[i]);
				extTrans_h4[i] = extTrans_h4[i].or(newGlobalTransFor_H4_i[i]);
			}

			fixPointFlag = true;
			for (int i = 0; i < N; i++) {
				// clients
				if (do_delta_improvement) {
					deltaThetas_cl[i] = Util.newAllSuccNotIncl(oldThetas_cl[i],
							extTrans_cl[i], vars_cl[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_cl[i].isZero());
					newThetas_cl[i] = oldThetas_cl[i].or(deltaThetas_cl[i]);

					// home0
					deltaThetas_h0[i] = Util.newAllSuccNotIncl(oldThetas_h0[i],
							extTrans_h0[i], vars_h0[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_h0[i].isZero());
					newThetas_h0[i] = oldThetas_h0[i].or(deltaThetas_h0[i]);

					// home1
					deltaThetas_h1[i] = Util.newAllSuccNotIncl(oldThetas_h1[i],
							extTrans_h1[i], vars_h1[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_h1[i].isZero());
					newThetas_h1[i] = oldThetas_h1[i].or(deltaThetas_h1[i]);

					// homeu
					deltaThetas_hu[i] = Util.newAllSuccNotIncl(oldThetas_hu[i],
							extTrans_hu[i], vars_hu[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_hu[i].isZero());
					newThetas_hu[i] = oldThetas_hu[i].or(deltaThetas_hu[i]);

					// home2
					deltaThetas_h2[i] = Util.newAllSuccNotIncl(oldThetas_h2[i],
							extTrans_h2[i], vars_h2[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_h2[i].isZero());
					newThetas_h2[i] = oldThetas_h2[i].or(deltaThetas_h2[i]);

					// home3
					deltaThetas_h3[i] = Util.newAllSuccNotIncl(oldThetas_h3[i],
							extTrans_h3[i], vars_h3[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_h3[i].isZero());
					newThetas_h3[i] = oldThetas_h3[i].or(deltaThetas_h3[i]);

					// home4
					deltaThetas_h4[i] = Util.newAllSuccNotIncl(oldThetas_h4[i],
							extTrans_h4[i], vars_h4[i]);
					fixPointFlag = fixPointFlag & (deltaThetas_h4[i].isZero());
					newThetas_h4[i] = oldThetas_h4[i].or(deltaThetas_h4[i]);

				} else {
					newThetas_cl[i] = Util.newAllSucc(oldThetas_cl[i],
							extTrans_cl[i], vars_cl[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_cl[i].equals(oldThetas_cl[i]));
					// home0
					newThetas_h0[i] = Util.newAllSucc(oldThetas_h0[i],
							extTrans_h0[i], vars_h0[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_h0[i].equals(oldThetas_h0[i]));
					// home1
					newThetas_h1[i] = Util.newAllSucc(oldThetas_h1[i],
							extTrans_h1[i], vars_h1[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_h1[i].equals(oldThetas_h1[i]));
					// homeus
					newThetas_hu[i] = Util.newAllSucc(oldThetas_hu[i],
							extTrans_hu[i], vars_hu[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_hu[i].equals(oldThetas_hu[i]));
					// home2s
					newThetas_h2[i] = Util.newAllSucc(oldThetas_h2[i],
							extTrans_h2[i], vars_h2[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_h2[i].equals(oldThetas_h2[i]));
					// home3s
					newThetas_h3[i] = Util.newAllSucc(oldThetas_h3[i],
							extTrans_h3[i], vars_h3[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_h3[i].equals(oldThetas_h3[i]));
					// home4s
					newThetas_h4[i] = Util.newAllSucc(oldThetas_h4[i],
							extTrans_h4[i], vars_h4[i]);
					fixPointFlag = fixPointFlag
							& (newThetas_h4[i].equals(oldThetas_h4[i]));
				}
				oldThetas_cl[i] = newThetas_cl[i];
				oldThetas_h0[i] = newThetas_h0[i];
				oldThetas_h1[i] = newThetas_h1[i];
				oldThetas_hu[i] = newThetas_hu[i];
				oldThetas_h2[i] = newThetas_h2[i];
				oldThetas_h3[i] = newThetas_h3[i];
				oldThetas_h4[i] = newThetas_h4[i];
			}
		} while (!fixPointFlag);

		workDone.release(1);
	}

	public BDD getFoundStates() {
		System.out
				.println("node num of size:" + True.getFactory().getNodeNum());

		BDD foundStates = True;
		for (int i = 0; i < newThetas_cl.length; i++) {
			foundStates = foundStates.and(newThetas_cl[i]);
			foundStates = foundStates.and(newThetas_h0[i]);
			foundStates = foundStates.and(newThetas_h1[i]);
			foundStates = foundStates.and(newThetas_hu[i]);
			foundStates = foundStates.and(newThetas_h2[i]);
			foundStates = foundStates.and(newThetas_h3[i]);
			foundStates = foundStates.and(newThetas_h4[i]);
		}
		return adjustBDDToBase(foundStates);
	}

	// computing the transition relation a conjunct by conjunct
	public BDD assignTransToDedicatedManager(SMVModule m) {
		BDD res = True;
		for (BDD toConj : m.getAllConjunctTransElements()) {
			res = res.and(adjustBDDToManager(toConj));
		}
		return res;
	}

	public BDD idleVars(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4, ModuleBDDField f5,
			ModuleBDDField f6) {
		// return idleVars(new ModuleBDDField[] { f1, f2, f3, f4, f5, f6 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		BDDDomain dp2 = f.getDomain(f2.getDomain().getIndex());
		BDDDomain dup2 = f.getDomain(f2.getOtherDomain().getIndex());
		temp = temp.and(dp2.buildEquals(dup2));
		BDDDomain dp3 = f.getDomain(f3.getDomain().getIndex());
		BDDDomain dup3 = f.getDomain(f3.getOtherDomain().getIndex());
		temp = temp.and(dp3.buildEquals(dup3));
		BDDDomain dp4 = f.getDomain(f4.getDomain().getIndex());
		BDDDomain dup4 = f.getDomain(f4.getOtherDomain().getIndex());
		temp = temp.and(dp4.buildEquals(dup4));
		BDDDomain dp5 = f.getDomain(f5.getDomain().getIndex());
		BDDDomain dup5 = f.getDomain(f5.getOtherDomain().getIndex());
		temp = temp.and(dp5.buildEquals(dup5));
		BDDDomain dp6 = f.getDomain(f6.getDomain().getIndex());
		BDDDomain dup6 = f.getDomain(f6.getOtherDomain().getIndex());
		temp = temp.and(dp6.buildEquals(dup6));
		return temp;
	}

	public BDD idleVars(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4, ModuleBDDField f5) {
		// return idleVars(new ModuleBDDField[] { f1, f2, f3, f4, f5 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		BDDDomain dp2 = f.getDomain(f2.getDomain().getIndex());
		BDDDomain dup2 = f.getDomain(f2.getOtherDomain().getIndex());
		temp = temp.and(dp2.buildEquals(dup2));
		BDDDomain dp3 = f.getDomain(f3.getDomain().getIndex());
		BDDDomain dup3 = f.getDomain(f3.getOtherDomain().getIndex());
		temp = temp.and(dp3.buildEquals(dup3));
		BDDDomain dp4 = f.getDomain(f4.getDomain().getIndex());
		BDDDomain dup4 = f.getDomain(f4.getOtherDomain().getIndex());
		temp = temp.and(dp4.buildEquals(dup4));
		BDDDomain dp5 = f.getDomain(f5.getDomain().getIndex());
		BDDDomain dup5 = f.getDomain(f5.getOtherDomain().getIndex());
		temp = temp.and(dp5.buildEquals(dup5));
		return temp;
	}

	public BDD idleVars(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4) {
		// return idleVars(new ModuleBDDField[] { f1, f2, f3, f4 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		BDDDomain dp2 = f.getDomain(f2.getDomain().getIndex());
		BDDDomain dup2 = f.getDomain(f2.getOtherDomain().getIndex());
		temp = temp.and(dp2.buildEquals(dup2));
		BDDDomain dp3 = f.getDomain(f3.getDomain().getIndex());
		BDDDomain dup3 = f.getDomain(f3.getOtherDomain().getIndex());
		temp = temp.and(dp3.buildEquals(dup3));
		BDDDomain dp4 = f.getDomain(f4.getDomain().getIndex());
		BDDDomain dup4 = f.getDomain(f4.getOtherDomain().getIndex());
		temp = temp.and(dp4.buildEquals(dup4));
		return temp;
	}

	public BDD idleVars(ModuleBDDField f1, ModuleBDDField f2, ModuleBDDField f3) {
		// return idleVars(new ModuleBDDField[] { f1, f2, f3 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		BDDDomain dp2 = f.getDomain(f2.getDomain().getIndex());
		BDDDomain dup2 = f.getDomain(f2.getOtherDomain().getIndex());
		temp = temp.and(dp2.buildEquals(dup2));
		BDDDomain dp3 = f.getDomain(f3.getDomain().getIndex());
		BDDDomain dup3 = f.getDomain(f3.getOtherDomain().getIndex());
		temp = temp.and(dp3.buildEquals(dup3));
		return temp;
	}

	public BDD idleVars(ModuleBDDField f1, ModuleBDDField f2) {
		// return idleVars(new ModuleBDDField[] { f1, f2 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		BDDDomain dp2 = f.getDomain(f2.getDomain().getIndex());
		BDDDomain dup2 = f.getDomain(f2.getOtherDomain().getIndex());
		temp = temp.and(dp2.buildEquals(dup2));
		return temp;
	}

	public BDD idleVars(ModuleBDDField f1) {
		// return idleVars(new ModuleBDDField[] { f1 });
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		BDDDomain dp1 = f.getDomain(f1.getDomain().getIndex());
		BDDDomain dup1 = f.getDomain(f1.getOtherDomain().getIndex());
		temp = temp.and(dp1.buildEquals(dup1));
		return temp;
	}

	public BDD idleVars() {
		return True;
	}

	public BDD idleVars(ModuleBDDField[] fields) {
		BDDFactory f = True.getFactory();
		BDD temp = f.one();
		for (int i = 0; i < fields.length; i++) {
			BDDDomain dp = f.getDomain(fields[i].getDomain().getIndex());
			BDDDomain dup = f.getDomain(fields[i].getOtherDomain().getIndex());
			temp = temp.and(dp.buildEquals(dup));
		}
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4, ModuleBDDField f5,
			ModuleBDDField f6) {
		// return varsToQant(new ModuleBDDField[] { f1, f2, f3, f4, f5, f6 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f5.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f5.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f6.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f6.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4, ModuleBDDField f5) {
		// return varsToQant(new ModuleBDDField[] { f1, f2, f3, f4, f5 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f5.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f5.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3, ModuleBDDField f4) {
		// return varsToQant(new ModuleBDDField[] { f1, f2, f3, f4 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f4.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1, ModuleBDDField f2,
			ModuleBDDField f3) {
		// return varsToQant(new ModuleBDDField[] { f1, f2, f3 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f3.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1, ModuleBDDField f2) {
		// return varsToQant(new ModuleBDDField[] { f1, f2 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f2.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant(ModuleBDDField f1) {
		// return varsToQant(new ModuleBDDField[] { f1 });
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		temp = temp.union(f.getDomain(f1.getDomain().getIndex()).set());
		temp = temp.union(f.getDomain(f1.getOtherDomain().getIndex()).set());
		return temp;
	}

	public BDDVarSet varsToQant() {
		return EmptySet;
	}

	public BDDVarSet varsToQant(ModuleBDDField[] fields) {
		BDDFactory f = True.getFactory();
		BDDVarSet temp = f.emptySet();
		for (int i = 0; i < fields.length; i++) {
			temp = temp.union(f.getDomain(fields[i].getDomain().getIndex())
					.set());
			temp = temp.union(f
					.getDomain(fields[i].getOtherDomain().getIndex()).set());
		}
		return temp;
	}
}
