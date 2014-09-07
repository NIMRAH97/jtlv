package edu.wis.jtlv.bench.mtmc;

//package mtmc.alg;

//import mtmc.Configuration;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;

/**
 * === Sequential ===
 * 
 * @author yaniv, ariel
 * 
 */
public class DoSequentialGerman extends AbstractAlgorithm {

	public DoSequentialGerman(Configuration conf) {
		super(conf);
	}

	public BDD performAlg() {
		long start = System.currentTimeMillis();
		SMVModule main = conf.main;
		int N = conf.N;

		Env.TRUE().getFactory().setNodeTableSize(conf.thread_bdd_table_size);
		Env.TRUE().getFactory().setCacheSize(conf.thread_cache_size);

		// == processes:
		// SMVModule[] clients = new SMVModule[N];
		SMVModule[][] home0s = new SMVModule[N][N];
		SMVModule[][] home1s = new SMVModule[N][N];
		SMVModule[][] homeus = new SMVModule[N][N];
		SMVModule[][] home2s = new SMVModule[N][N];
		SMVModule[][] home3s = new SMVModule[N][N];
		SMVModule[][] home4s = new SMVModule[N][N];
		// transition relation:
		BDD[][] trans_cl = new BDD[N][N];
		BDD[][] trans_h0 = new BDD[N][N];
		BDD[][] trans_h1 = new BDD[N][N];
		BDD[][] trans_hu = new BDD[N][N];
		BDD[][] trans_h2 = new BDD[N][N];
		BDD[][] trans_h3 = new BDD[N][N];
		BDD[][] trans_h4 = new BDD[N][N];
		// extended transition relation:
		BDD[][] extTrans_cl = new BDD[N][N];
		BDD[][] extTrans_h0 = new BDD[N][N];
		BDD[][] extTrans_h1 = new BDD[N][N];
		BDD[][] extTrans_hu = new BDD[N][N];
		BDD[][] extTrans_h2 = new BDD[N][N];
		BDD[][] extTrans_h3 = new BDD[N][N];
		BDD[][] extTrans_h4 = new BDD[N][N];
		// initial conditions:
		BDD[][] initCond_cl = new BDD[N][N];
		BDD[][] initCond_h0 = new BDD[N][N];
		BDD[][] initCond_h1 = new BDD[N][N];
		BDD[][] initCond_hu = new BDD[N][N];
		BDD[][] initCond_h2 = new BDD[N][N];
		BDD[][] initCond_h3 = new BDD[N][N];
		BDD[][] initCond_h4 = new BDD[N][N];
		// variables (local and shared):
		BDDVarSet[][] vars_cl = new BDDVarSet[N][N];
		BDDVarSet[][] vars_h0 = new BDDVarSet[N][N];
		BDDVarSet[][] vars_h1 = new BDDVarSet[N][N];
		BDDVarSet[][] vars_hu = new BDDVarSet[N][N];
		BDDVarSet[][] vars_h2 = new BDDVarSet[N][N];
		BDDVarSet[][] vars_h3 = new BDDVarSet[N][N];
		BDDVarSet[][] vars_h4 = new BDDVarSet[N][N];
		// Theta of each process
		BDD[][] oldThetas_cl = new BDD[N][N];
		BDD[][] oldThetas_h0 = new BDD[N][N];
		BDD[][] oldThetas_h1 = new BDD[N][N];
		BDD[][] oldThetas_hu = new BDD[N][N];
		BDD[][] oldThetas_h2 = new BDD[N][N];
		BDD[][] oldThetas_h3 = new BDD[N][N];
		BDD[][] oldThetas_h4 = new BDD[N][N];
		BDD[][] deltaThetas_cl = new BDD[N][N];
		BDD[][] deltaThetas_h0 = new BDD[N][N];
		BDD[][] deltaThetas_h1 = new BDD[N][N];
		BDD[][] deltaThetas_hu = new BDD[N][N];
		BDD[][] deltaThetas_h2 = new BDD[N][N];
		BDD[][] deltaThetas_h3 = new BDD[N][N];
		BDD[][] deltaThetas_h4 = new BDD[N][N];
		BDD[][] newThetas_cl = new BDD[N][N];
		BDD[][] newThetas_h0 = new BDD[N][N];
		BDD[][] newThetas_h1 = new BDD[N][N];
		BDD[][] newThetas_hu = new BDD[N][N];
		BDD[][] newThetas_h2 = new BDD[N][N];
		BDD[][] newThetas_h3 = new BDD[N][N];
		BDD[][] newThetas_h4 = new BDD[N][N];

		for (int c = 0; c < N; c++) {
			// getting the info
			for (int i = 0; i < N; i++) {
				// getting the clients
				// clients[i] = (SMVModule) Env.getModule("main.c" + i);
				home0s[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".h0");
				home1s[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".h1");
				homeus[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".hu");
				home2s[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".h2");
				home3s[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".h3");
				home4s[c][i] = (SMVModule) Env.getModule("main.cr[" + c + "].H"
						+ i + ".h4");
				// vars (all) of each process
				vars_cl[c][i] = Env.getEmptySet();
				vars_h0[c][i] = Env.getEmptySet();
				vars_h1[c][i] = Env.getEmptySet();
				vars_hu[c][i] = Env.getEmptySet();
				vars_h2[c][i] = Env.getEmptySet();
				vars_h3[c][i] = Env.getEmptySet();
				vars_h4[c][i] = Env.getEmptySet();

				// transition relations:
				// the original
				trans_cl[c][i] = Env.FALSE();
				initCond_cl[c][i] = Env.TRUE();
				for (int j = 1; j <= 6; j++) {
					SMVModule inner_client;
					if (j != 6)
						inner_client = (SMVModule) Env.getModule("main.cr[" + c
								+ "].C" + i + ".c" + j);
					else
						inner_client = (SMVModule) Env.getModule("main.cr[" + c
								+ "].C" + i + ".cu");
					trans_cl[c][i] = trans_cl[c][i].or(inner_client.trans());
					initCond_cl[c][i] = initCond_cl[c][i].and(inner_client
							.initial());
				}

				trans_h0[c][i] = home0s[c][i].trans();
				trans_h1[c][i] = home1s[c][i].trans();
				trans_hu[c][i] = homeus[c][i].trans();
				trans_h2[c][i] = home2s[c][i].trans();
				trans_h3[c][i] = home3s[c][i].trans();
				trans_h4[c][i] = home4s[c][i].trans();
				// original disjuncted with what obtained from others
				extTrans_cl[c][i] = trans_cl[c][i];
				extTrans_h0[c][i] = trans_h0[c][i];
				extTrans_h1[c][i] = trans_h1[c][i];
				extTrans_hu[c][i] = trans_hu[c][i];
				extTrans_h2[c][i] = trans_h2[c][i];
				extTrans_h3[c][i] = trans_h3[c][i];
				extTrans_h4[c][i] = trans_h4[c][i];
				// the initial condition of each client (including shared vars)
				// initCond_cl[c][i] = clients[c][i].initial(); // .and(initM);
				initCond_h0[c][i] = home0s[c][i].initial(); // .and(initM);
				initCond_h1[c][i] = home1s[c][i].initial(); // .and(initM);
				initCond_hu[c][i] = homeus[c][i].initial(); // .and(initM);
				initCond_h2[c][i] = home2s[c][i].initial(); // .and(initM);
				initCond_h3[c][i] = home3s[c][i].initial(); // .and(initM);
				initCond_h4[c][i] = home4s[c][i].initial(); // .and(initM);
				// initial thetas:
				oldThetas_cl[c][i] = initCond_cl[c][i];
				oldThetas_h0[c][i] = initCond_h0[c][i];
				oldThetas_h1[c][i] = initCond_h1[c][i];
				oldThetas_hu[c][i] = initCond_hu[c][i];
				oldThetas_h2[c][i] = initCond_h2[c][i];
				oldThetas_h3[c][i] = initCond_h3[c][i];
				oldThetas_h4[c][i] = initCond_h4[c][i];
				deltaThetas_cl[c][i] = initCond_cl[c][i];
				deltaThetas_h0[c][i] = initCond_h0[c][i];
				deltaThetas_h1[c][i] = initCond_h1[c][i];
				deltaThetas_hu[c][i] = initCond_hu[c][i];
				deltaThetas_h2[c][i] = initCond_h2[c][i];
				deltaThetas_h3[c][i] = initCond_h3[c][i];
				deltaThetas_h4[c][i] = initCond_h4[c][i];
				newThetas_cl[c][i] = initCond_cl[c][i];
				newThetas_h0[c][i] = initCond_h0[c][i];
				newThetas_h1[c][i] = initCond_h1[c][i];
				newThetas_hu[c][i] = initCond_hu[c][i];
				newThetas_h2[c][i] = initCond_h2[c][i];
				newThetas_h3[c][i] = initCond_h3[c][i];
				newThetas_h4[c][i] = initCond_h4[c][i];
			}
		}

		// vars
		ModuleBDDField[] cur_cln = new ModuleBDDField[N];
		ModuleBDDField[] cur_com = new ModuleBDDField[N];
		ModuleBDDField[] exc_grn = new ModuleBDDField[N];
		ModuleBDDField[] x_grn = new ModuleBDDField[N];
		ModuleBDDField[][] channel1 = new ModuleBDDField[N][N];
		ModuleBDDField[][] channel2 = new ModuleBDDField[N][N];
		ModuleBDDField[][] channel3 = new ModuleBDDField[N][N];
		ModuleBDDField[][] sharer_l = new ModuleBDDField[N][N];
		ModuleBDDField[][] invald_l = new ModuleBDDField[N][N];
		ModuleBDDField[][] cache = new ModuleBDDField[N][N];
		for (int c = 0; c < N; c++) {
			cur_cln[c] = main.getVar("cr[" + c + "].cur_client", true);
			cur_com[c] = main.getVar("cr[" + c + "].cur_command", true);
			exc_grn[c] = main.getVar("cr[" + c + "].exclusive_granted", true);
			x_grn[c] = main.getVar("cr[" + c + "].x_granted", true);
			for (int i = 0; i < N; i++) {
				channel1[c][i] = main.getVar("cr[" + c + "].channel1[" + i
						+ "]", true);
				channel2[c][i] = main.getVar("cr[" + c + "].channel2[" + i
						+ "]", true);
				channel3[c][i] = main.getVar("cr[" + c + "].channel3[" + i
						+ "]", true);
				sharer_l[c][i] = main.getVar("cr[" + c + "].sharer_list[" + i
						+ "]", true);
				invald_l[c][i] = main.getVar("cr[" + c + "].invalidate_list["
						+ i + "]", true);
				cache[c][i] = main.getVar("cr[" + c + "].cache[" + i + "]",
						true);
			}

		}

		for (int c = 0; c < N; c++) {
			// clients
			for (int i = 0; i < N; i++) {
				vars_cl[c][i] = vars_cl[c][i].union(cache[c][i].getDomain()
						.set());
				vars_cl[c][i] = vars_cl[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_cl[c][i] = vars_cl[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_cl[c][i] = vars_cl[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_cl[c][i] = vars_cl[c][i].union(exc_grn[c].getDomain()
						.set());
				for (int j = 0; j < N; j++) {
					vars_cl[c][i] = vars_cl[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}

			// home0
			for (int i = 0; i < N; i++) {
				vars_h0[c][i] = vars_h0[c][i].union(cur_cln[c].getDomain()
						.set());
				vars_h0[c][i] = vars_h0[c][i].union(cur_com[c].getDomain()
						.set());
				vars_h0[c][i] = vars_h0[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_h0[c][i] = vars_h0[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_h0[c][i] = vars_h0[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_h0[c][i] = vars_h0[c][i].union(exc_grn[c].getDomain()
						.set());
				for (int j = 0; j < N; j++) {
					vars_h0[c][i] = vars_h0[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}
			// home1s
			for (int i = 0; i < N; i++) {
				vars_h1[c][i] = vars_h1[c][i].union(cur_cln[c].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(cur_com[c].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(exc_grn[c].getDomain()
						.set());
				vars_h1[c][i] = vars_h1[c][i].union(x_grn[c].getDomain().set());
				for (int j = 0; j < N; j++) {
					vars_h1[c][i] = vars_h1[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}
			// homeus
			for (int i = 0; i < N; i++) {
				vars_hu[c][i] = vars_hu[c][i].union(cur_cln[c].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(cur_com[c].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(exc_grn[c].getDomain()
						.set());
				vars_hu[c][i] = vars_hu[c][i].union(x_grn[c].getDomain().set());
				for (int j = 0; j < N; j++) {
					vars_hu[c][i] = vars_hu[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}
			// home2s
			for (int i = 0; i < N; i++) {
				vars_h2[c][i] = vars_h2[c][i].union(cur_cln[c].getDomain()
						.set());
				vars_h2[c][i] = vars_h2[c][i].union(cur_com[c].getDomain()
						.set());
				vars_h2[c][i] = vars_h2[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_h2[c][i] = vars_h2[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_h2[c][i] = vars_h2[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_h2[c][i] = vars_h2[c][i].union(exc_grn[c].getDomain()
						.set());
				for (int j = 0; j < N; j++) {
					vars_h2[c][i] = vars_h2[c][i].union(sharer_l[c][j]
							.getDomain().set());
					vars_h2[c][i] = vars_h2[c][i].union(invald_l[c][j]
							.getDomain().set());
				}
			}
			// home3s
			for (int i = 0; i < N; i++) {
				// vars_h3[c][i] =
				// vars_h3[c][i].union(cur_cln.getDomain().set());
				vars_h3[c][i] = vars_h3[c][i].union(cur_com[c].getDomain()
						.set());
				vars_h3[c][i] = vars_h3[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_h3[c][i] = vars_h3[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_h3[c][i] = vars_h3[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_h3[c][i] = vars_h3[c][i].union(exc_grn[c].getDomain()
						.set());
				vars_h3[c][i] = vars_h3[c][i].union(invald_l[c][i].getDomain()
						.set());
				for (int j = 0; j < N; j++) {
					vars_h3[c][i] = vars_h3[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}
			// home4s
			for (int i = 0; i < N; i++) {
				// vars_h4[c][i] =
				// vars_h4[c][i].union(cur_cln.getDomain().set());
				vars_h4[c][i] = vars_h4[c][i].union(cur_com[c].getDomain()
						.set());
				vars_h4[c][i] = vars_h4[c][i].union(channel1[c][i].getDomain()
						.set());
				vars_h4[c][i] = vars_h4[c][i].union(channel2[c][i].getDomain()
						.set());
				vars_h4[c][i] = vars_h4[c][i].union(channel3[c][i].getDomain()
						.set());
				vars_h4[c][i] = vars_h4[c][i].union(exc_grn[c].getDomain()
						.set());
				for (int j = 0; j < N; j++) {
					vars_h4[c][i] = vars_h4[c][i].union(sharer_l[c][j]
							.getDomain().set());
				}
			}
		}

		// === clients
		BDDVarSet[][] varsCl_i_QuantForH0_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH1_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForHu_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH4_i = new BDDVarSet[N][N];
		BDD[][] idleOfCl_i_FromH0_i = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH1_i = new BDD[N][N];
		BDD[][] idleOfCl_i_FromHu_i = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH2_i = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfCl_i_FromH4_i = new BDD[N][N];
		// Ariel
		BDDVarSet[][] varsCl_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsCl_i_QuantForH4_j = new BDDVarSet[N][N];

		BDD[][][] idleOfCl_i_FromH0_j = new BDD[N][N][N];
		BDD[][][] idleOfCl_i_FromH1_j = new BDD[N][N][N];
		BDD[][][] idleOfCl_i_FromHu_j = new BDD[N][N][N];
		BDD[][][] idleOfCl_i_FromH2_j = new BDD[N][N][N];
		BDD[][][] idleOfCl_i_FromH3_j = new BDD[N][N][N];
		BDD[][][] idleOfCl_i_FromH4_j = new BDD[N][N][N];

		// ////////////////////////////////////////////////////////////////////////////////////
		// === H0
		BDDVarSet[][] varsH0_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH0_i_QuantForCl_j = new BDDVarSet[N][N][N];
		// BDDVarSet varsH0_i_QuantForH0_i;
		BDDVarSet[][] varsH0_i_QuantForH0_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH1_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH1_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForHu_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForHu_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH2_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH4_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH0_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfH0_i_FromCl_i = new BDD[N][N];
		BDD[][][] idleOfH0_i_FromCl_j = new BDD[N][N][N];
		// BDD[][] idleOfH0_i_FromH0_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH0_j = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH1_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH1_j = new BDD[N][N];
		BDD[][] idleOfH0_i_FromHu_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromHu_j = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH2_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH2_j = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH4_i = new BDD[N][N];
		BDD[][] idleOfH0_i_FromH4_j = new BDD[N][N];
		// === H1
		BDDVarSet[][] varsH1_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH0_i = new BDDVarSet[N][N];
		// BDDVarSet varsH1_i_QuantForH1_i;
		BDDVarSet[][] varsH1_i_QuantForHu_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH1_i_QuantForH4_i = new BDDVarSet[N][N];
		BDD[][] idleOfH1_i_FromCl_i = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH0_i = new BDD[N][N];
		// BDD[][] idleOfH1_i_FromH1_i = new BDD[N][N];
		BDD[][] idleOfH1_i_FromHu_i = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH2_i = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfH1_i_FromH4_i = new BDD[N][N];

		BDDVarSet[][][] varsH1_i_QuantForCl_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForH0_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForH1_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForHu_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForH2_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForH3_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH1_i_QuantForH4_j = new BDDVarSet[N][N][N];
		BDD[][][] idleOfH1_i_FromCl_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromH0_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromH1_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromHu_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromH2_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromH3_j = new BDD[N][N][N];
		BDD[][][] idleOfH1_i_FromH4_j = new BDD[N][N][N];
		// === Hu
		BDDVarSet[][] varsHu_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH0_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH1_i = new BDDVarSet[N][N];
		// BDDVarSet varsHu_i_QuantForHu_i;
		BDDVarSet[][] varsHu_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsHu_i_QuantForH4_i = new BDDVarSet[N][N];
		BDD[][] idleOfHu_i_FromCl_i = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH0_i = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH1_i = new BDD[N][N];
		// BDD[][] idleOfHu_i_FromHu_i = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH2_i = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfHu_i_FromH4_i = new BDD[N][N];
		BDDVarSet[][][] varsHu_i_QuantForCl_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForH0_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForH1_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForHu_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForH2_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForH3_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsHu_i_QuantForH4_j = new BDDVarSet[N][N][N];
		BDD[][][] idleOfHu_i_FromCl_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromH0_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromH1_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromHu_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromH2_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromH3_j = new BDD[N][N][N];
		BDD[][][] idleOfHu_i_FromH4_j = new BDD[N][N][N];
		// === H2
		BDDVarSet[][] varsH2_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH0_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH1_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForHu_i = new BDDVarSet[N][N];
		// BDDVarSet varsH2_i_QuantForH2_i;
		BDDVarSet[][] varsH2_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH2_i_QuantForH4_i = new BDDVarSet[N][N];
		BDD[][] idleOfH2_i_FromCl_i = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH0_i = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH1_i = new BDD[N][N];
		BDD[][] idleOfH2_i_FromHu_i = new BDD[N][N];
		// BDD[][] idleOfH2_i_FromH2_i = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfH2_i_FromH4_i = new BDD[N][N];
		BDDVarSet[][][] varsH2_i_QuantForCl_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForH0_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForH1_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForHu_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForH2_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForH3_j = new BDDVarSet[N][N][N];
		BDDVarSet[][][] varsH2_i_QuantForH4_j = new BDDVarSet[N][N][N];
		BDD[][][] idleOfH2_i_FromCl_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromH0_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromH1_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromHu_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromH2_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromH3_j = new BDD[N][N][N];
		BDD[][][] idleOfH2_i_FromH4_j = new BDD[N][N][N];
		// === H3
		BDDVarSet[][] varsH3_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH3_i_QuantForCl_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH3_i_QuantForH0_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH3_i_QuantForH0_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH3_i_QuantForH1_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH3_i_QuantForH1_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH3_i_QuantForHu_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH3_i_QuantForHu_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH3_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH3_i_QuantForH2_j = new BDDVarSet[N][N][N];
		// BDDVarSet varsH3_i_QuantForH3_i;
		BDDVarSet[][] varsH3_i_QuantForH3_j = new BDDVarSet[N][N];
		BDDVarSet[][] varsH3_i_QuantForH4_i = new BDDVarSet[N][N];
		BDDVarSet[][] varsH3_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfH3_i_FromCl_i = new BDD[N][N];
		BDD[][][] idleOfH3_i_FromCl_j = new BDD[N][N][N];
		BDD[][] idleOfH3_i_FromH0_i = new BDD[N][N];
		BDD[][][] idleOfH3_i_FromH0_j = new BDD[N][N][N];
		BDD[][] idleOfH3_i_FromH1_i = new BDD[N][N];
		BDD[][][] idleOfH3_i_FromH1_j = new BDD[N][N][N];
		BDD[][] idleOfH3_i_FromHu_i = new BDD[N][N];
		BDD[][][] idleOfH3_i_FromHu_j = new BDD[N][N][N];
		BDD[][] idleOfH3_i_FromH2_i = new BDD[N][N];
		BDD[][][] idleOfH3_i_FromH2_j = new BDD[N][N][N];
		// BDD[][] idleOfH3_i_FromH3_i = new BDD[N][N];
		BDD[][] idleOfH3_i_FromH3_j = new BDD[N][N];
		BDD[][] idleOfH3_i_FromH4_i = new BDD[N][N];
		BDD[][] idleOfH3_i_FromH4_j = new BDD[N][N];
		// === H4
		BDDVarSet[][] varsH4_i_QuantForCl_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForCl_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH4_i_QuantForH0_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForH0_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH4_i_QuantForH1_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForH1_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH4_i_QuantForHu_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForHu_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH4_i_QuantForH2_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForH2_j = new BDDVarSet[N][N][N];
		BDDVarSet[][] varsH4_i_QuantForH3_i = new BDDVarSet[N][N];
		BDDVarSet[][][] varsH4_i_QuantForH3_j = new BDDVarSet[N][N][N];
		// BDDVarSet varsH4_i_QuantForH4_i;
		BDDVarSet[][] varsH4_i_QuantForH4_j = new BDDVarSet[N][N];
		BDD[][] idleOfH4_i_FromCl_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromCl_j = new BDD[N][N][N];
		BDD[][] idleOfH4_i_FromH0_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromH0_j = new BDD[N][N][N];
		BDD[][] idleOfH4_i_FromH1_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromH1_j = new BDD[N][N][N];
		BDD[][] idleOfH4_i_FromHu_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromHu_j = new BDD[N][N][N];
		BDD[][] idleOfH4_i_FromH2_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromH2_j = new BDD[N][N][N];
		BDD[][] idleOfH4_i_FromH3_i = new BDD[N][N];
		BDD[][][] idleOfH4_i_FromH3_j = new BDD[N][N][N];
		// BDD[][] idleOfH4_i_FromH4_i = new BDD[N][N];
		BDD[][] idleOfH4_i_FromH4_j = new BDD[N][N];

		// // finished declaring

		for (int c = 0; c < N; c++) {
			// === clients
			for (int i = 0; i < N; i++) {
				varsCl_i_QuantForH0_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromH0_i[c][i] = Util.idleVars(cache[c][i]);

				varsCl_i_QuantForH1_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromH1_i[c][i] = Util.idleVars(cache[c][i]);

				varsCl_i_QuantForHu_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromHu_i[c][i] = Util.idleVars(cache[c][i]);

				varsCl_i_QuantForH2_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromH2_i[c][i] = Util.idleVars(cache[c][i]);

				varsCl_i_QuantForH3_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromH3_i[c][i] = Util.idleVars(cache[c][i]);

				varsCl_i_QuantForH4_i[c][i] = Util.varsToQant(cache[c][i]);
				idleOfCl_i_FromH4_i[c][i] = Util.idleVars(cache[c][i]);
			}

			for (int i = 0; i < N; i++) {
				// Ariel
				varsCl_i_QuantForH0_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				varsCl_i_QuantForH1_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				varsCl_i_QuantForHu_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				varsCl_i_QuantForH2_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				varsCl_i_QuantForH3_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				varsCl_i_QuantForH4_j[c][i] = Util.varsToQant(cache[c][i],
						channel1[c][i], channel2[c][i], channel3[c][i]);

				for (int j = 0; j < N; j++) {
					if (j != i) {
						idleOfCl_i_FromH0_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfCl_i_FromH1_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfCl_i_FromHu_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfCl_i_FromH2_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfCl_i_FromH3_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfCl_i_FromH4_j[c][i][j] = Util.idleVars(
								cache[c][i], channel1[c][i], channel2[c][i],
								channel3[c][i]);
					}
				}
			}

			// === H0
			for (int i = 0; i < N; i++) {
				varsH0_i_QuantForCl_i[c][i] = Util.varsToQant(cur_cln[c],
						cur_com[c]);
				idleOfH0_i_FromCl_i[c][i] = Util.idleVars(cur_cln[c],
						cur_com[c]);

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH0_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_cln[c], cur_com[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfH0_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_cln[c], cur_com[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
					}
				}

				varsH0_i_QuantForH0_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);
				idleOfH0_i_FromH0_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);

				varsH0_i_QuantForH1_i[c][i] = Util.varsToQant();
				idleOfH0_i_FromH1_i[c][i] = Util.idleVars();

				varsH0_i_QuantForH1_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);
				idleOfH0_i_FromH1_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);

				varsH0_i_QuantForHu_i[c][i] = Util.varsToQant();
				idleOfH0_i_FromHu_i[c][i] = Util.idleVars();

				varsH0_i_QuantForHu_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);
				idleOfH0_i_FromHu_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);

				varsH0_i_QuantForH2_i[c][i] = Util.varsToQant();
				idleOfH0_i_FromH2_i[c][i] = Util.idleVars();

				varsH0_i_QuantForH2_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);
				idleOfH0_i_FromH2_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i], cur_cln[c]);

				varsH0_i_QuantForH3_i[c][i] = Util.varsToQant(cur_cln[c]);
				idleOfH0_i_FromH3_i[c][i] = Util.idleVars(cur_cln[c]);

				varsH0_i_QuantForH3_j[c][i] = Util.varsToQant(cur_cln[c],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				idleOfH0_i_FromH3_j[c][i] = Util.idleVars(cur_cln[c],
						channel1[c][i], channel2[c][i], channel3[c][i]);

				varsH0_i_QuantForH4_i[c][i] = Util.varsToQant(cur_cln[c]);
				idleOfH0_i_FromH4_i[c][i] = Util.idleVars(cur_cln[c]);

				varsH0_i_QuantForH4_j[c][i] = Util.varsToQant(cur_cln[c],
						channel1[c][i], channel2[c][i], channel3[c][i]);
				idleOfH0_i_FromH4_j[c][i] = Util.idleVars(cur_cln[c],
						channel1[c][i], channel2[c][i], channel3[c][i]);
			}

			// === H1
			for (int i = 0; i < N; i++) {
				varsH1_i_QuantForCl_i[c][i] = Util.varsToQant(cur_cln[c],
						cur_com[c], x_grn[c]);
				idleOfH1_i_FromCl_i[c][i] = Util.idleVars(cur_cln[c],
						cur_com[c], x_grn[c]);

				varsH1_i_QuantForH0_i[c][i] = Util.varsToQant(x_grn[c]);
				idleOfH1_i_FromH0_i[c][i] = Util.idleVars(x_grn[c]);

				varsH1_i_QuantForHu_i[c][i] = Util.varsToQant();
				idleOfH1_i_FromHu_i[c][i] = Util.idleVars();

				varsH1_i_QuantForH2_i[c][i] = Util.varsToQant(x_grn[c]);
				idleOfH1_i_FromH2_i[c][i] = Util.idleVars(x_grn[c]);

				varsH1_i_QuantForH3_i[c][i] = Util.varsToQant(cur_cln[c],
						x_grn[c]);
				idleOfH1_i_FromH3_i[c][i] = Util.idleVars(cur_cln[c], x_grn[c]);

				varsH1_i_QuantForH4_i[c][i] = Util.varsToQant(cur_cln[c],
						x_grn[c]);
				idleOfH1_i_FromH4_i[c][i] = Util.idleVars(cur_cln[c], x_grn[c]);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH1_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_cln[c], cur_com[c], x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH1_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_cln[c], cur_com[c], x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i]);

						varsH1_i_QuantForH0_j[c][i][j] = Util.varsToQant(
								x_grn[c], channel1[c][i], channel2[c][i],
								channel3[c][i], cur_cln[c]);
						idleOfH1_i_FromH0_j[c][i][j] = Util.idleVars(x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsH1_i_QuantForH1_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH1_i_FromH1_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsH1_i_QuantForHu_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH1_i_FromHu_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsH1_i_QuantForH2_j[c][i][j] = Util.varsToQant(
								x_grn[c], channel1[c][i], channel2[c][i],
								channel3[c][i], cur_cln[c]);
						idleOfH1_i_FromH2_j[c][i][j] = Util.idleVars(x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsH1_i_QuantForH3_j[c][i][j] = Util.varsToQant(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfH1_i_FromH3_j[c][i][j] = Util.idleVars(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);

						varsH1_i_QuantForH4_j[c][i][j] = Util.varsToQant(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfH1_i_FromH4_j[c][i][j] = Util.idleVars(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
					}
				}
			}

			// === Hu
			for (int i = 0; i < N; i++) {
				varsHu_i_QuantForCl_i[c][i] = Util.varsToQant(cur_cln[c],
						cur_com[c], x_grn[c]);
				idleOfHu_i_FromCl_i[c][i] = Util.idleVars(cur_cln[c],
						cur_com[c], x_grn[c]);

				varsHu_i_QuantForH0_i[c][i] = Util.varsToQant(x_grn[c]);
				idleOfHu_i_FromH0_i[c][i] = Util.idleVars(x_grn[c]);

				varsHu_i_QuantForH1_i[c][i] = Util.varsToQant();
				idleOfHu_i_FromH1_i[c][i] = Util.idleVars();

				varsHu_i_QuantForH2_i[c][i] = Util.varsToQant(x_grn[c]);
				idleOfHu_i_FromH2_i[c][i] = Util.idleVars(x_grn[c]);

				varsHu_i_QuantForH3_i[c][i] = Util.varsToQant(cur_cln[c],
						x_grn[c]);
				idleOfHu_i_FromH3_i[c][i] = Util.idleVars(cur_cln[c], x_grn[c]);

				varsHu_i_QuantForH4_i[c][i] = Util.varsToQant(cur_cln[c],
						x_grn[c]);
				idleOfHu_i_FromH4_i[c][i] = Util.idleVars(cur_cln[c], x_grn[c]);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsHu_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_cln[c], cur_com[c], x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfHu_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_cln[c], cur_com[c], x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i]);

						varsHu_i_QuantForH0_j[c][i][j] = Util.varsToQant(
								x_grn[c], channel1[c][i], channel2[c][i],
								channel3[c][i], cur_cln[c]);
						idleOfHu_i_FromH0_j[c][i][j] = Util.idleVars(x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsHu_i_QuantForH1_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfHu_i_FromH1_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsHu_i_QuantForHu_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfHu_i_FromHu_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsHu_i_QuantForH2_j[c][i][j] = Util.varsToQant(
								x_grn[c], channel1[c][i], channel2[c][i],
								channel3[c][i], cur_cln[c]);
						idleOfHu_i_FromH2_j[c][i][j] = Util.idleVars(x_grn[c],
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsHu_i_QuantForH3_j[c][i][j] = Util.varsToQant(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfHu_i_FromH3_j[c][i][j] = Util.idleVars(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);

						varsHu_i_QuantForH4_j[c][i][j] = Util.varsToQant(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfHu_i_FromH4_j[c][i][j] = Util.idleVars(
								cur_cln[c], x_grn[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
					}
				}
			}

			// === H2
			for (int i = 0; i < N; i++) {

				varsH2_i_QuantForCl_i[c][i] = Util.varsToQant(cur_cln[c],
						cur_com[c]);
				idleOfH2_i_FromCl_i[c][i] = Util.idleVars(cur_cln[c],
						cur_com[c]);
				for (int k = 0; k < N; k++) {
					varsH2_i_QuantForCl_i[c][i] = varsH2_i_QuantForCl_i[c][i]
							.union(Util.varsToQant(invald_l[c][k]));
					idleOfH2_i_FromCl_i[c][i] = idleOfH2_i_FromCl_i[c][i]
							.and(Util.idleVars(invald_l[c][k]));
				}

				varsH2_i_QuantForH0_i[c][i] = Util.varsToQant();
				idleOfH2_i_FromH0_i[c][i] = Util.idleVars();
				for (int k = 0; k < N; k++) {
					varsH2_i_QuantForH0_i[c][i] = varsH2_i_QuantForH0_i[c][i]
							.union(Util.varsToQant(invald_l[c][k]));
					idleOfH2_i_FromH0_i[c][i] = idleOfH2_i_FromH0_i[c][i]
							.and(Util.idleVars(invald_l[c][k]));
				}

				varsH2_i_QuantForH1_i[c][i] = Util.varsToQant();
				idleOfH2_i_FromH1_i[c][i] = Util.idleVars();
				for (int k = 0; k < N; k++) {
					varsH2_i_QuantForH1_i[c][i] = varsH2_i_QuantForH1_i[c][i]
							.union(Util.varsToQant(invald_l[c][k]));
					idleOfH2_i_FromH1_i[c][i] = idleOfH2_i_FromH1_i[c][i]
							.and(Util.idleVars(invald_l[c][k]));
				}

				varsH2_i_QuantForHu_i[c][i] = Util.varsToQant();
				idleOfH2_i_FromHu_i[c][i] = Util.idleVars();
				for (int k = 0; k < N; k++) {
					varsH2_i_QuantForHu_i[c][i] = varsH2_i_QuantForHu_i[c][i]
							.union(Util.varsToQant(invald_l[c][k]));
					idleOfH2_i_FromHu_i[c][i] = idleOfH2_i_FromHu_i[c][i]
							.and(Util.idleVars(invald_l[c][k]));
				}

				varsH2_i_QuantForH3_i[c][i] = Util.varsToQant(cur_cln[c]);
				idleOfH2_i_FromH3_i[c][i] = Util.idleVars(cur_cln[c]);
				for (int k = 0; k < N; k++) {
					if (k != i) {
						varsH2_i_QuantForH3_i[c][i] = varsH2_i_QuantForH3_i[c][i]
								.union(Util.varsToQant(invald_l[c][k]));
						idleOfH2_i_FromH3_i[c][i] = idleOfH2_i_FromH3_i[c][i]
								.and(Util.idleVars(invald_l[c][k]));
					}
				}

				varsH2_i_QuantForH4_i[c][i] = Util.varsToQant(cur_cln[c]);
				idleOfH2_i_FromH4_i[c][i] = Util.idleVars(cur_cln[c]);
				for (int k = 0; k < N; k++) {
					varsH2_i_QuantForH4_i[c][i] = varsH2_i_QuantForH4_i[c][i]
							.union(Util.varsToQant(invald_l[c][k]));
					idleOfH2_i_FromH4_i[c][i] = idleOfH2_i_FromH4_i[c][i]
							.and(Util.idleVars(invald_l[c][k]));
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH2_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_cln[c], cur_com[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						idleOfH2_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_cln[c], cur_com[c], channel1[c][i],
								channel2[c][i], channel3[c][i]);
						for (int k = 0; k < N; k++) {
							varsH2_i_QuantForCl_j[c][i][j] = varsH2_i_QuantForCl_j[c][i][j]
									.union(Util.varsToQant(invald_l[c][k]));
							idleOfH2_i_FromCl_j[c][i][j] = idleOfH2_i_FromCl_j[c][i][j]
									.and(Util.idleVars(invald_l[c][k]));
						}

						varsH2_i_QuantForH0_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH2_i_FromH0_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						for (int k = 0; k < N; k++) {
							varsH2_i_QuantForH0_j[c][i][j] = varsH2_i_QuantForH0_j[c][i][j]
									.union(Util.varsToQant(invald_l[c][k]));
							idleOfH2_i_FromH0_j[c][i][j] = idleOfH2_i_FromH0_j[c][i][j]
									.and(Util.idleVars(invald_l[c][k]));
						}

						varsH2_i_QuantForH1_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH2_i_FromH1_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						for (int k = 0; k < N; k++) {
							varsH2_i_QuantForH1_j[c][i][j] = varsH2_i_QuantForH1_j[c][i][j]
									.union(Util.varsToQant(invald_l[c][k]));
							idleOfH2_i_FromH1_j[c][i][j] = idleOfH2_i_FromH1_j[c][i][j]
									.and(Util.idleVars(invald_l[c][k]));
						}

						varsH2_i_QuantForHu_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH2_i_FromHu_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						for (int k = 0; k < N; k++) {
							varsH2_i_QuantForHu_j[c][i][j] = varsH2_i_QuantForHu_j[c][i][j]
									.union(Util.varsToQant(invald_l[c][k]));
							idleOfH2_i_FromHu_j[c][i][j] = idleOfH2_i_FromHu_j[c][i][j]
									.and(Util.idleVars(invald_l[c][k]));
						}

						varsH2_i_QuantForH2_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);
						idleOfH2_i_FromH2_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i],
								cur_cln[c]);

						varsH2_i_QuantForH3_j[c][i][j] = Util.varsToQant(
								cur_cln[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfH2_i_FromH3_j[c][i][j] = Util.idleVars(
								cur_cln[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						for (int k = 0; k < N; k++) {
							if (k != j) {
								varsH2_i_QuantForH3_j[c][i][j] = varsH2_i_QuantForH3_j[c][i][j]
										.union(Util.varsToQant(invald_l[c][k]));
								idleOfH2_i_FromH3_j[c][i][j] = idleOfH2_i_FromH3_j[c][i][j]
										.and(Util.idleVars(invald_l[c][k]));
							}
						}

						varsH2_i_QuantForH4_j[c][i][j] = Util.varsToQant(
								cur_cln[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfH2_i_FromH4_j[c][i][j] = Util.idleVars(
								cur_cln[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						for (int k = 0; k < N; k++) {
							varsH2_i_QuantForH4_j[c][i][j] = varsH2_i_QuantForH4_j[c][i][j]
									.union(Util.varsToQant(invald_l[c][k]));
							idleOfH2_i_FromH4_j[c][i][j] = idleOfH2_i_FromH4_j[c][i][j]
									.and(Util.idleVars(invald_l[c][k]));
						}
					}
				}
			}

			// === H3
			for (int i = 0; i < N; i++) {
				varsH3_i_QuantForCl_i[c][i] = Util.varsToQant(cur_com[c]);
				idleOfH3_i_FromCl_i[c][i] = Util.idleVars(cur_com[c]);
				varsH3_i_QuantForCl_i[c][i] = varsH3_i_QuantForCl_i[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromCl_i[c][i] = idleOfH3_i_FromCl_i[c][i].and(Util
						.idleVars(invald_l[c][i]));

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH3_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_com[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfH3_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_com[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						varsH3_i_QuantForCl_j[c][i][j] = varsH3_i_QuantForCl_j[c][i][j]
								.union(Util.varsToQant(invald_l[c][i]));
						idleOfH3_i_FromCl_j[c][i][j] = idleOfH3_i_FromCl_j[c][i][j]
								.and(Util.idleVars(invald_l[c][i]));
					}
				}

				varsH3_i_QuantForH0_i[c][i] = Util.varsToQant();
				idleOfH3_i_FromH0_i[c][i] = Util.idleVars();
				varsH3_i_QuantForH0_i[c][i] = varsH3_i_QuantForH0_i[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromH0_i[c][i] = idleOfH3_i_FromH0_i[c][i].and(Util
						.idleVars(invald_l[c][i]));
				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH3_i_QuantForH0_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH3_i_FromH0_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						varsH3_i_QuantForH0_j[c][i][j] = varsH3_i_QuantForH0_j[c][i][j]
								.union(Util.varsToQant(invald_l[c][i]));
						idleOfH3_i_FromH0_j[c][i][j] = idleOfH3_i_FromH0_j[c][i][j]
								.and(Util.idleVars(invald_l[c][i]));
					}
				}

				varsH3_i_QuantForH1_i[c][i] = Util.varsToQant();
				idleOfH3_i_FromH1_i[c][i] = Util.idleVars();
				varsH3_i_QuantForH1_i[c][i] = varsH3_i_QuantForH1_i[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromH1_i[c][i] = idleOfH3_i_FromH1_i[c][i].and(Util
						.idleVars(invald_l[c][i]));

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH3_i_QuantForH1_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH3_i_FromH1_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						varsH3_i_QuantForH1_j[c][i][j] = varsH3_i_QuantForH1_j[c][i][j]
								.union(Util.varsToQant(invald_l[c][i]));
						idleOfH3_i_FromH1_j[c][i][j] = idleOfH3_i_FromH1_j[c][i][j]
								.and(Util.idleVars(invald_l[c][i]));
					}
				}

				varsH3_i_QuantForHu_i[c][i] = Util.varsToQant();
				idleOfH3_i_FromHu_i[c][i] = Util.idleVars();
				varsH3_i_QuantForHu_i[c][i] = varsH3_i_QuantForHu_i[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromHu_i[c][i] = idleOfH3_i_FromHu_i[c][i].and(Util
						.idleVars(invald_l[c][i]));

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH3_i_QuantForHu_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH3_i_FromHu_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						varsH3_i_QuantForHu_j[c][i][j] = varsH3_i_QuantForHu_j[c][i][j]
								.union(Util.varsToQant(invald_l[c][i]));
						idleOfH3_i_FromHu_j[c][i][j] = idleOfH3_i_FromHu_j[c][i][j]
								.and(Util.idleVars(invald_l[c][i]));
					}
				}

				varsH3_i_QuantForH2_i[c][i] = Util.varsToQant();
				idleOfH3_i_FromH2_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH3_i_QuantForH2_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH3_i_FromH2_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH3_i_QuantForH3_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i]);
				idleOfH3_i_FromH3_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i]);
				varsH3_i_QuantForH3_j[c][i] = varsH3_i_QuantForH3_j[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromH3_j[c][i] = idleOfH3_i_FromH3_j[c][i].and(Util
						.idleVars(invald_l[c][i]));

				varsH3_i_QuantForH4_i[c][i] = Util.varsToQant();
				idleOfH3_i_FromH4_i[c][i] = Util.idleVars();
				varsH3_i_QuantForH4_i[c][i] = varsH3_i_QuantForH4_i[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromH4_i[c][i] = idleOfH3_i_FromH4_i[c][i].and(Util
						.idleVars(invald_l[c][i]));

				varsH3_i_QuantForH4_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i]);
				idleOfH3_i_FromH4_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i]);
				varsH3_i_QuantForH4_j[c][i] = varsH3_i_QuantForH4_j[c][i]
						.union(Util.varsToQant(invald_l[c][i]));
				idleOfH3_i_FromH4_j[c][i] = idleOfH3_i_FromH4_j[c][i].and(Util
						.idleVars(invald_l[c][i]));
			}

			// === H4
			for (int i = 0; i < N; i++) {
				varsH4_i_QuantForCl_i[c][i] = Util.varsToQant(cur_com[c]);
				idleOfH4_i_FromCl_i[c][i] = Util.idleVars(cur_com[c]);

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForCl_j[c][i][j] = Util.varsToQant(
								cur_com[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
						idleOfH4_i_FromCl_j[c][i][j] = Util.idleVars(
								cur_com[c], channel1[c][i], channel2[c][i],
								channel3[c][i]);
					}
				}

				varsH4_i_QuantForH0_i[c][i] = Util.varsToQant();
				idleOfH4_i_FromH0_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForH0_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH4_i_FromH0_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH4_i_QuantForH1_i[c][i] = Util.varsToQant();
				idleOfH4_i_FromH1_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForH1_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH4_i_FromH1_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH4_i_QuantForHu_i[c][i] = Util.varsToQant();
				idleOfH4_i_FromHu_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForHu_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH4_i_FromHu_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH4_i_QuantForH2_i[c][i] = Util.varsToQant();
				idleOfH4_i_FromH2_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForH2_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH4_i_FromH2_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH4_i_QuantForH3_i[c][i] = Util.varsToQant();
				idleOfH4_i_FromH3_i[c][i] = Util.idleVars();

				for (int j = 0; j < N; j++) {
					if (j != i) {
						varsH4_i_QuantForH3_j[c][i][j] = Util.varsToQant(
								channel1[c][i], channel2[c][i], channel3[c][i]);
						idleOfH4_i_FromH3_j[c][i][j] = Util.idleVars(
								channel1[c][i], channel2[c][i], channel3[c][i]);
					}
				}

				varsH4_i_QuantForH4_j[c][i] = Util.varsToQant(channel1[c][i],
						channel2[c][i], channel3[c][i]);
				idleOfH4_i_FromH4_j[c][i] = Util.idleVars(channel1[c][i],
						channel2[c][i], channel3[c][i]);
			}
		}
		// ////////// === FINISH initial settings

		long global_construction_time = System.currentTimeMillis();

		// compute thetas initially
		for (int c = 0; c < N; c++) {
			for (int i = 0; i < N; i++) {
				if (conf.do_delta_improvement) {
					deltaThetas_cl[c][i] = Util.newAllSuccNotIncl(
							oldThetas_cl[c][i], extTrans_cl[c][i],
							vars_cl[c][i]);
					deltaThetas_h0[c][i] = Util.newAllSuccNotIncl(
							oldThetas_h0[c][i], extTrans_h0[c][i],
							vars_h0[c][i]);
					deltaThetas_h1[c][i] = Util.newAllSuccNotIncl(
							oldThetas_h1[c][i], extTrans_h1[c][i],
							vars_h1[c][i]);
					deltaThetas_hu[c][i] = Util.newAllSuccNotIncl(
							oldThetas_hu[c][i], extTrans_hu[c][i],
							vars_hu[c][i]);
					deltaThetas_h2[c][i] = Util.newAllSuccNotIncl(
							oldThetas_h2[c][i], extTrans_h2[c][i],
							vars_h2[c][i]);
					deltaThetas_h3[c][i] = Util.newAllSuccNotIncl(
							oldThetas_h3[c][i], extTrans_h3[c][i],
							vars_h3[c][i]);
					deltaThetas_h4[c][i] = Util.newAllSuccNotIncl(
							oldThetas_h4[c][i], extTrans_h4[c][i],
							vars_h4[c][i]);
					newThetas_cl[c][i] = oldThetas_cl[c][i]
							.or(deltaThetas_cl[c][i]);
					newThetas_h0[c][i] = oldThetas_h0[c][i]
							.or(deltaThetas_h0[c][i]);
					newThetas_h1[c][i] = oldThetas_h1[c][i]
							.or(deltaThetas_h1[c][i]);
					newThetas_hu[c][i] = oldThetas_hu[c][i]
							.or(deltaThetas_hu[c][i]);
					newThetas_h2[c][i] = oldThetas_h2[c][i]
							.or(deltaThetas_h2[c][i]);
					newThetas_h3[c][i] = oldThetas_h3[c][i]
							.or(deltaThetas_h3[c][i]);
					newThetas_h4[c][i] = oldThetas_h4[c][i]
							.or(deltaThetas_h4[c][i]);
				} else {
					newThetas_cl[c][i] = Util.newAllSucc(oldThetas_cl[c][i],
							extTrans_cl[c][i], vars_cl[c][i]);
					newThetas_h0[c][i] = Util.newAllSucc(oldThetas_h0[c][i],
							extTrans_h0[c][i], vars_h0[c][i]);
					newThetas_h1[c][i] = Util.newAllSucc(oldThetas_h1[c][i],
							extTrans_h1[c][i], vars_h1[c][i]);
					newThetas_hu[c][i] = Util.newAllSucc(oldThetas_hu[c][i],
							extTrans_hu[c][i], vars_hu[c][i]);
					newThetas_h2[c][i] = Util.newAllSucc(oldThetas_h2[c][i],
							extTrans_h2[c][i], vars_h2[c][i]);
					newThetas_h3[c][i] = Util.newAllSucc(oldThetas_h3[c][i],
							extTrans_h3[c][i], vars_h3[c][i]);
					newThetas_h4[c][i] = Util.newAllSucc(oldThetas_h4[c][i],
							extTrans_h4[c][i], vars_h4[c][i]);
				}
				oldThetas_cl[c][i] = newThetas_cl[c][i];
				oldThetas_h0[c][i] = newThetas_h0[c][i];
				oldThetas_h1[c][i] = newThetas_h1[c][i];
				oldThetas_hu[c][i] = newThetas_hu[c][i];
				oldThetas_h2[c][i] = newThetas_h2[c][i];
				oldThetas_h3[c][i] = newThetas_h3[c][i];
				oldThetas_h4[c][i] = newThetas_h4[c][i];
			}
		}

		// flag indicating a fixpoint reached
		boolean fixPointFlag;
		// what processes give others:

		int counter = 0;
		do {
			counter++;
			System.out.println("counter = " + counter);

			// computing the global transition of each process
			BDD[][] globalTrans_cl = new BDD[N][N];
			BDD[][] globalTrans_h0 = new BDD[N][N];
			BDD[][] globalTrans_h1 = new BDD[N][N];
			BDD[][] globalTrans_hu = new BDD[N][N];
			BDD[][] globalTrans_h2 = new BDD[N][N];
			BDD[][] globalTrans_h3 = new BDD[N][N];
			BDD[][] globalTrans_h4 = new BDD[N][N];
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					if (conf.do_delta_improvement) {
						globalTrans_cl[c][i] = (trans_cl[c][i]
								.and(oldThetas_cl[c][i]).and(Env
								.prime(deltaThetas_cl[c][i])));
						globalTrans_h0[c][i] = (trans_h0[c][i]
								.and(oldThetas_h0[c][i]).and(Env
								.prime(deltaThetas_h0[c][i])));
						globalTrans_h1[c][i] = (trans_h1[c][i]
								.and(oldThetas_h1[c][i]).and(Env
								.prime(deltaThetas_h1[c][i])));
						globalTrans_hu[c][i] = (trans_hu[c][i]
								.and(oldThetas_hu[c][i]).and(Env
								.prime(deltaThetas_hu[c][i])));
						globalTrans_h2[c][i] = (trans_h2[c][i]
								.and(oldThetas_h2[c][i]).and(Env
								.prime(deltaThetas_h2[c][i])));
						globalTrans_h3[c][i] = (trans_h3[c][i]
								.and(oldThetas_h3[c][i]).and(Env
								.prime(deltaThetas_h3[c][i])));
						globalTrans_h4[c][i] = (trans_h4[c][i]
								.and(oldThetas_h4[c][i]).and(Env
								.prime(deltaThetas_h4[c][i])));
					} else {
						globalTrans_cl[c][i] = (trans_cl[c][i]
								.and(oldThetas_cl[c][i]).and(Env
								.prime(newThetas_cl[c][i])));
						globalTrans_h0[c][i] = (trans_h0[c][i]
								.and(oldThetas_h0[c][i]).and(Env
								.prime(newThetas_h0[c][i])));
						globalTrans_h1[c][i] = (trans_h1[c][i]
								.and(oldThetas_h1[c][i]).and(Env
								.prime(newThetas_h1[c][i])));
						globalTrans_hu[c][i] = (trans_hu[c][i]
								.and(oldThetas_hu[c][i]).and(Env
								.prime(newThetas_hu[c][i])));
						globalTrans_h2[c][i] = (trans_h2[c][i]
								.and(oldThetas_h2[c][i]).and(Env
								.prime(newThetas_h2[c][i])));
						globalTrans_h3[c][i] = (trans_h3[c][i]
								.and(oldThetas_h3[c][i]).and(Env
								.prime(newThetas_h3[c][i])));
						globalTrans_h4[c][i] = (trans_h4[c][i]
								.and(oldThetas_h4[c][i]).and(Env
								.prime(newThetas_h4[c][i])));
					}
				}
			}

			// == setting the new transition to be sent for each process
			// --clients give
			BDD[][] globalTrans_cl_i_For_H0_i = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H1_i = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_Hu_i = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H2_i = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H3_i = new BDD[N][N];
			BDD[][] globalTrans_cl_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_cl_i_For_H0_j = new BDD[N][N][N];
			BDD[][][] globalTrans_cl_i_For_H1_j = new BDD[N][N][N];
			BDD[][][] globalTrans_cl_i_For_Hu_j = new BDD[N][N][N];
			BDD[][][] globalTrans_cl_i_For_H2_j = new BDD[N][N][N];
			BDD[][][] globalTrans_cl_i_For_H3_j = new BDD[N][N][N];
			BDD[][][] globalTrans_cl_i_For_H4_j = new BDD[N][N][N];
			// --H0s give
			BDD[][] globalTrans_H0_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_Cl_j = new BDD[N][N][N];
			// BDD[][] globalTrans_H0_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_H0_j = new BDD[N][N][N];
			BDD[][] globalTrans_H0_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_H1_j = new BDD[N][N][N];
			BDD[][] globalTrans_H0_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_Hu_j = new BDD[N][N][N];
			BDD[][] globalTrans_H0_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_H2_j = new BDD[N][N][N];
			BDD[][] globalTrans_H0_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_H3_j = new BDD[N][N][N];
			BDD[][] globalTrans_H0_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_H0_i_For_H4_j = new BDD[N][N][N];
			// --H1s give
			BDD[][] globalTrans_H1_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_Cl_j = new BDD[N][N][N];
			BDD[][] globalTrans_H1_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_H0_j = new BDD[N][N][N];
			// BDD[][] globalTrans_H1_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_H1_j = new BDD[N][N][N];
			BDD[][] globalTrans_H1_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_Hu_j = new BDD[N][N][N];
			BDD[][] globalTrans_H1_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_H2_j = new BDD[N][N][N];
			BDD[][] globalTrans_H1_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_H3_j = new BDD[N][N][N];
			BDD[][] globalTrans_H1_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_H1_i_For_H4_j = new BDD[N][N][N];
			// --Hus give
			BDD[][] globalTrans_Hu_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_Cl_j = new BDD[N][N][N];
			BDD[][] globalTrans_Hu_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_H0_j = new BDD[N][N][N];
			BDD[][] globalTrans_Hu_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_H1_j = new BDD[N][N][N];
			// BDD[][] globalTrans_Hu_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_Hu_j = new BDD[N][N][N];
			BDD[][] globalTrans_Hu_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_H2_j = new BDD[N][N][N];
			BDD[][] globalTrans_Hu_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_H3_j = new BDD[N][N][N];
			BDD[][] globalTrans_Hu_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_Hu_i_For_H4_j = new BDD[N][N][N];
			// --H2s give
			BDD[][] globalTrans_H2_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_Cl_j = new BDD[N][N][N];
			BDD[][] globalTrans_H2_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_H0_j = new BDD[N][N][N];
			BDD[][] globalTrans_H2_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_H1_j = new BDD[N][N][N];
			BDD[][] globalTrans_H2_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_Hu_j = new BDD[N][N][N];
			// BDD[][] globalTrans_H2_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_H2_j = new BDD[N][N][N];
			BDD[][] globalTrans_H2_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_H3_j = new BDD[N][N][N];
			BDD[][] globalTrans_H2_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_H2_i_For_H4_j = new BDD[N][N][N];
			// --H3s give
			BDD[][] globalTrans_H3_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_Cl_j = new BDD[N][N][N];
			BDD[][] globalTrans_H3_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_H0_j = new BDD[N][N][N];
			BDD[][] globalTrans_H3_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_H1_j = new BDD[N][N][N];
			BDD[][] globalTrans_H3_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_Hu_j = new BDD[N][N][N];
			BDD[][] globalTrans_H3_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_H2_j = new BDD[N][N][N];
			// BDD[][] globalTrans_H3_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_H3_j = new BDD[N][N][N];
			BDD[][] globalTrans_H3_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_H3_i_For_H4_j = new BDD[N][N][N];
			// --H4s give
			BDD[][] globalTrans_H4_i_For_Cl_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_Cl_j = new BDD[N][N][N];
			BDD[][] globalTrans_H4_i_For_H0_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_H0_j = new BDD[N][N][N];
			BDD[][] globalTrans_H4_i_For_H1_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_H1_j = new BDD[N][N][N];
			BDD[][] globalTrans_H4_i_For_Hu_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_Hu_j = new BDD[N][N][N];
			BDD[][] globalTrans_H4_i_For_H2_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_H2_j = new BDD[N][N][N];
			BDD[][] globalTrans_H4_i_For_H3_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_H3_j = new BDD[N][N][N];
			// BDD[][] globalTrans_H4_i_For_H4_i = new BDD[N][N];
			BDD[][][] globalTrans_H4_i_For_H4_j = new BDD[N][N][N];

			for (int c = 0; c < N; c++) {
				// --clients give
				for (int i = 0; i < N; i++) {
					globalTrans_cl_i_For_H0_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForH0_i[c][i]);
					globalTrans_cl_i_For_H1_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForH1_i[c][i]);
					globalTrans_cl_i_For_Hu_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForHu_i[c][i]);
					globalTrans_cl_i_For_H2_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForH2_i[c][i]);
					globalTrans_cl_i_For_H3_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForH3_i[c][i]);
					globalTrans_cl_i_For_H4_i[c][i] = globalTrans_cl[c][i]
							.exist(varsCl_i_QuantForH4_i[c][i]);
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_cl_i_For_H0_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForH0_j[c][i]);
							globalTrans_cl_i_For_H1_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForH1_j[c][i]);
							globalTrans_cl_i_For_Hu_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForHu_j[c][i]);
							globalTrans_cl_i_For_H2_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForH2_j[c][i]);
							globalTrans_cl_i_For_H3_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForH3_j[c][i]);
							globalTrans_cl_i_For_H4_j[c][i][j] = globalTrans_cl[c][i]
									.exist(varsCl_i_QuantForH4_j[c][i]);
						}
					}
				}

				// --H0s give
				for (int i = 0; i < N; i++) {
					globalTrans_H0_i_For_Cl_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForCl_i[c][i]);
					// globalTrans_H0_i_For_H0_i[c][i] =
					// globalTrans_h0[c][i].exist(varsH0_i_QuantForH0_i[c][i]);
					globalTrans_H0_i_For_H1_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForH1_i[c][i]);
					globalTrans_H0_i_For_Hu_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForHu_i[c][i]);
					globalTrans_H0_i_For_H2_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForH2_i[c][i]);
					globalTrans_H0_i_For_H3_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForH3_i[c][i]);
					globalTrans_H0_i_For_H4_i[c][i] = globalTrans_h0[c][i]
							.exist(varsH0_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_H0_i_For_Cl_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForCl_j[c][i][j]);
							globalTrans_H0_i_For_H0_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForH0_j[c][i]);
							globalTrans_H0_i_For_H1_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForH1_j[c][i]);
							globalTrans_H0_i_For_Hu_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForHu_j[c][i]);
							globalTrans_H0_i_For_H2_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForH2_j[c][i]);
							globalTrans_H0_i_For_H3_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForH3_j[c][i]);
							globalTrans_H0_i_For_H4_j[c][i][j] = globalTrans_h0[c][i]
									.exist(varsH0_i_QuantForH4_j[c][i]);
						}
					}
				}
				// --H1s give
				for (int i = 0; i < N; i++) {
					globalTrans_H1_i_For_Cl_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForCl_i[c][i]);
					globalTrans_H1_i_For_H0_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForH0_i[c][i]);
					// globalTrans_H1_i_For_H1_i[c][i] =
					// globalTrans_h1[c][i].exist(varsH1_i_QuantForH1_i[c][i]);
					globalTrans_H1_i_For_Hu_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForHu_i[c][i]);
					globalTrans_H1_i_For_H2_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForH2_i[c][i]);
					globalTrans_H1_i_For_H3_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForH3_i[c][i]);
					globalTrans_H1_i_For_H4_i[c][i] = globalTrans_h1[c][i]
							.exist(varsH1_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_H1_i_For_Cl_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForCl_j[c][i][j]);
							globalTrans_H1_i_For_H0_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForH0_j[c][i][j]);
							globalTrans_H1_i_For_H1_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForH1_j[c][i][j]);
							globalTrans_H1_i_For_Hu_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForHu_j[c][i][j]);
							globalTrans_H1_i_For_H2_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForH2_j[c][i][j]);
							globalTrans_H1_i_For_H3_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForH3_j[c][i][j]);
							globalTrans_H1_i_For_H4_j[c][i][j] = globalTrans_h1[c][i]
									.exist(varsH1_i_QuantForH4_j[c][i][j]);
						}
					}
				}
				// --Hus give
				for (int i = 0; i < N; i++) {
					globalTrans_Hu_i_For_Cl_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForCl_i[c][i]);
					globalTrans_Hu_i_For_H0_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForH0_i[c][i]);
					globalTrans_Hu_i_For_H1_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForH1_i[c][i]);
					// globalTrans_Hu_i_For_Hu_i[c][i] =
					// globalTrans_hu[c][i].exist(varsHu_i_QuantForHu_i[c][i]);
					globalTrans_Hu_i_For_H2_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForH2_i[c][i]);
					globalTrans_Hu_i_For_H3_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForH3_i[c][i]);
					globalTrans_Hu_i_For_H4_i[c][i] = globalTrans_hu[c][i]
							.exist(varsHu_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_Hu_i_For_Cl_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForCl_j[c][i][j]);
							globalTrans_Hu_i_For_H0_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForH0_j[c][i][j]);
							globalTrans_Hu_i_For_H1_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForH1_j[c][i][j]);
							globalTrans_Hu_i_For_Hu_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForHu_j[c][i][j]);
							globalTrans_Hu_i_For_H2_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForH2_j[c][i][j]);
							globalTrans_Hu_i_For_H3_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForH3_j[c][i][j]);
							globalTrans_Hu_i_For_H4_j[c][i][j] = globalTrans_hu[c][i]
									.exist(varsHu_i_QuantForH4_j[c][i][j]);
						}
					}
				}
				// --H2s give
				for (int i = 0; i < N; i++) {
					globalTrans_H2_i_For_Cl_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForCl_i[c][i]);
					globalTrans_H2_i_For_H0_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForH0_i[c][i]);
					globalTrans_H2_i_For_H1_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForH1_i[c][i]);
					globalTrans_H2_i_For_Hu_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForHu_i[c][i]);
					// globalTrans_H2_i_For_H2_i[c][i] =
					// globalTrans_h2[c][i].exist(varsH2_i_QuantForH2_i[c][i]);
					globalTrans_H2_i_For_H3_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForH3_i[c][i]);
					globalTrans_H2_i_For_H4_i[c][i] = globalTrans_h2[c][i]
							.exist(varsH2_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_H2_i_For_Cl_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForCl_j[c][i][j]);
							globalTrans_H2_i_For_H0_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForH0_j[c][i][j]);
							globalTrans_H2_i_For_H1_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForH1_j[c][i][j]);
							globalTrans_H2_i_For_Hu_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForHu_j[c][i][j]);
							globalTrans_H2_i_For_H2_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForH2_j[c][i][j]);
							globalTrans_H2_i_For_H3_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForH3_j[c][i][j]);
							globalTrans_H2_i_For_H4_j[c][i][j] = globalTrans_h2[c][i]
									.exist(varsH2_i_QuantForH4_j[c][i][j]);
						}
					}
				}
				// --H3s give
				for (int i = 0; i < N; i++) {
					globalTrans_H3_i_For_Cl_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForCl_i[c][i]);
					globalTrans_H3_i_For_H0_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForH0_i[c][i]);
					globalTrans_H3_i_For_H1_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForH1_i[c][i]);
					globalTrans_H3_i_For_Hu_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForHu_i[c][i]);
					globalTrans_H3_i_For_H2_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForH2_i[c][i]);
					// globalTrans_H3_i_For_H3_i[c][i] =
					// globalTrans_h0[c][i].exist(varsH3_i_QuantForH3_i[c][i]);
					globalTrans_H3_i_For_H4_i[c][i] = globalTrans_h3[c][i]
							.exist(varsH3_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_H3_i_For_Cl_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForCl_j[c][i][j]);
							globalTrans_H3_i_For_H0_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForH0_j[c][i][j]);
							globalTrans_H3_i_For_H1_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForH1_j[c][i][j]);
							globalTrans_H3_i_For_Hu_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForHu_j[c][i][j]);
							globalTrans_H3_i_For_H2_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForH2_j[c][i][j]);
							globalTrans_H3_i_For_H3_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForH3_j[c][i]);
							globalTrans_H3_i_For_H4_j[c][i][j] = globalTrans_h3[c][i]
									.exist(varsH3_i_QuantForH4_j[c][i]);
						}
					}
				}
				// --H4s give
				for (int i = 0; i < N; i++) {
					globalTrans_H4_i_For_Cl_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForCl_i[c][i]);
					globalTrans_H4_i_For_H0_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForH0_i[c][i]);
					globalTrans_H4_i_For_H1_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForH1_i[c][i]);
					globalTrans_H4_i_For_Hu_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForHu_i[c][i]);
					globalTrans_H4_i_For_H2_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForH2_i[c][i]);
					globalTrans_H4_i_For_H3_i[c][i] = globalTrans_h4[c][i]
							.exist(varsH4_i_QuantForH3_i[c][i]);
					// globalTrans_H4_i_For_H4_i[c][i] =
					// globalTrans_h0[c][i].exist(varsH4_i_QuantForH4_i[c][i]);
					for (int j = 0; j < N; j++) {
						if (j != i) {
							globalTrans_H4_i_For_Cl_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForCl_j[c][i][j]);
							globalTrans_H4_i_For_H0_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForH0_j[c][i][j]);
							globalTrans_H4_i_For_H1_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForH1_j[c][i][j]);
							globalTrans_H4_i_For_Hu_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForHu_j[c][i][j]);
							globalTrans_H4_i_For_H2_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForH2_j[c][i][j]);
							globalTrans_H4_i_For_H3_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForH3_j[c][i][j]);
							globalTrans_H4_i_For_H4_j[c][i][j] = globalTrans_h4[c][i]
									.exist(varsH4_i_QuantForH4_j[c][i]);
						}
					}
				}

			}
			// ====== build new transition for each process =========
			// for clients
			BDD[][] newGlobalTransFor_Cl_i = new BDD[N][N];
			// for Home0s
			BDD[][] newGlobalTransFor_H0_i = new BDD[N][N];
			// For Home1s
			BDD[][] newGlobalTransFor_H1_i = new BDD[N][N];
			// For Homeus
			BDD[][] newGlobalTransFor_Hu_i = new BDD[N][N];
			// For Home2s
			BDD[][] newGlobalTransFor_H2_i = new BDD[N][N];
			// For home3s
			BDD[][] newGlobalTransFor_H3_i = new BDD[N][N];
			// for home4s
			BDD[][] newGlobalTransFor_H4_i = new BDD[N][N];

			for (int c = 0; c < N; c++) {
				// for clients
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_Cl_i[c][i] = Env.FALSE();
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_H0_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromH0_i[c][i]));
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_H1_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromH1_i[c][i]));
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_Hu_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromHu_i[c][i]));
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_H2_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromH2_i[c][i]));
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_H3_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromH3_i[c][i]));
					newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
							.or(globalTrans_H4_i_For_Cl_i[c][i]
									.and(idleOfCl_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_H0_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromH0_j[c][i][j]));
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_H1_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromH1_j[c][i][j]));
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_Hu_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromHu_j[c][i][j]));
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_H2_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromH2_j[c][i][j]));
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_H3_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromH3_j[c][i][j]));
							newGlobalTransFor_Cl_i[c][i] = newGlobalTransFor_Cl_i[c][i]
									.or(globalTrans_H4_i_For_Cl_j[c][j][i]
											.and(idleOfCl_i_FromH4_j[c][i][j]));
						}
					}
				}

				// for Home0s
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_H0_i[c][i] = Env.FALSE();
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_cl_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromCl_i[c][i]));
					// newGlobalTransFor_H0_i[c][i] =
					// newGlobalTransFor_H0_i[c][i].or(globalTrans_H0_i_For_H0_i[c][i]
					// .and(idleOfH0_i_FromH0_i[c][i]));
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_H1_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromH1_i[c][i]));
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_Hu_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromHu_i[c][i]));
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_H2_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromH2_i[c][i]));
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_H3_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromH3_i[c][i]));
					newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
							.or(globalTrans_H4_i_For_H0_i[c][i]
									.and(idleOfH0_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_cl_i_For_H0_j[c][j][i]// ariel
											// j
											.and(idleOfH0_i_FromCl_j[c][i][j]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_H0_i_For_H0_j[c][j][i]// ariel
											// j
											.and(idleOfH0_i_FromH0_j[c][i]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_H1_i_For_H0_j[c][j][i]
											.and(idleOfH0_i_FromH1_j[c][i]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_Hu_i_For_H0_j[c][j][i]
											.and(idleOfH0_i_FromHu_j[c][i]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_H2_i_For_H0_j[c][j][i]
											.and(idleOfH0_i_FromH2_j[c][i]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_H3_i_For_H0_j[c][j][i]
											.and(idleOfH0_i_FromH3_j[c][i]));
							newGlobalTransFor_H0_i[c][i] = newGlobalTransFor_H0_i[c][i]
									.or(globalTrans_H4_i_For_H0_j[c][j][i]
											.and(idleOfH0_i_FromH4_j[c][i]));
						}
					}
				}
				// For Home1s
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_H1_i[c][i] = Env.FALSE();
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_cl_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromCl_i[c][i]));
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_H0_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromH0_i[c][i]));
					// newGlobalTransFor_H1_i[c][i] =
					// newGlobalTransFor_H1_i[c][i].or(globalTrans_H1_i_For_H1_i[c][i]
					// .and(idleOfH1_i_FromH1_i[c][i]));
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_Hu_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromHu_i[c][i]));
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_H2_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromH2_i[c][i]));
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_H3_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromH3_i[c][i]));
					newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
							.or(globalTrans_H4_i_For_H1_i[c][i]
									.and(idleOfH1_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_cl_i_For_H1_j[c][j][i]// ariel
											// j
											.and(idleOfH1_i_FromCl_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_H0_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromH0_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_H1_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromH1_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_Hu_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromHu_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_H2_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromH2_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_H3_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromH3_j[c][i][j]));
							newGlobalTransFor_H1_i[c][i] = newGlobalTransFor_H1_i[c][i]
									.or(globalTrans_H4_i_For_H1_j[c][j][i]
											.and(idleOfH1_i_FromH4_j[c][i][j]));
						}
					}
				}
				// For Homeus
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_Hu_i[c][i] = Env.FALSE();
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_cl_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromCl_i[c][i]));
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_H0_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromH0_i[c][i]));
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_H1_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromH1_i[c][i]));
					// newGlobalTransFor_Hu_i[c][i] =
					// newGlobalTransFor_Hu_i[c][i].or(globalTrans_Hu_i_For_Hu_i[c][i]
					// .and(idleOfHu_i_FromHu_i[c][i]));
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_H2_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromH2_i[c][i]));
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_H3_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromH3_i[c][i]));
					newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
							.or(globalTrans_H4_i_For_Hu_i[c][i]
									.and(idleOfHu_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_cl_i_For_Hu_j[c][j][i]// ariel
											// j
											.and(idleOfHu_i_FromCl_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_H0_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromH0_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_H1_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromH1_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_Hu_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromHu_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_H2_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromH2_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_H3_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromH3_j[c][i][j]));
							newGlobalTransFor_Hu_i[c][i] = newGlobalTransFor_Hu_i[c][i]
									.or(globalTrans_H4_i_For_Hu_j[c][j][i]
											.and(idleOfHu_i_FromH4_j[c][i][j]));
						}
					}
				}
				// For Home2s
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_H2_i[c][i] = Env.FALSE();
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_cl_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromCl_i[c][i]));
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_H0_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromH0_i[c][i]));
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_H1_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromH1_i[c][i]));
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_Hu_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromHu_i[c][i]));
					// newGlobalTransFor_H2_i[c][i] =
					// newGlobalTransFor_H2_i[c][i].or(globalTrans_H2_i_For_H2_i[c][i]
					// .and(idleOfH2_i_FromH2_i[c][i]));
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_H3_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromH3_i[c][i]));
					newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
							.or(globalTrans_H4_i_For_H2_i[c][i]
									.and(idleOfH2_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_cl_i_For_H2_j[c][j][i]// ariel
											// j
											.and(idleOfH2_i_FromCl_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_H0_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromH0_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_H1_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromH1_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_Hu_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromHu_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_H2_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromH2_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_H3_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromH3_j[c][i][j]));
							newGlobalTransFor_H2_i[c][i] = newGlobalTransFor_H2_i[c][i]
									.or(globalTrans_H4_i_For_H2_j[c][j][i]
											.and(idleOfH2_i_FromH4_j[c][i][j]));
						}
					}
				}
				// For home3s
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_H3_i[c][i] = Env.FALSE();
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_cl_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromCl_i[c][i]));
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_H0_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromH0_i[c][i]));
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_H1_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromH1_i[c][i]));
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_Hu_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromHu_i[c][i]));
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_H2_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromH2_i[c][i]));
					// newGlobalTransFor_H3_i[c][i] =
					// newGlobalTransFor_H3_i[c][i].or(globalTrans_H3_i_For_H3_i[c][i]
					// .and(idleOfH3_i_FromH3_i[c][i]));
					newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
							.or(globalTrans_H4_i_For_H3_i[c][i]
									.and(idleOfH3_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_cl_i_For_H3_j[c][j][i]// ariel
											// j
											.and(idleOfH3_i_FromCl_j[c][i][j]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_H0_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromH0_j[c][i][j]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_H1_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromH1_j[c][i][j]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_Hu_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromHu_j[c][i][j]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_H2_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromH2_j[c][i][j]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_H3_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromH3_j[c][i]));
							newGlobalTransFor_H3_i[c][i] = newGlobalTransFor_H3_i[c][i]
									.or(globalTrans_H4_i_For_H3_j[c][j][i]
											.and(idleOfH3_i_FromH4_j[c][i]));
						}
					}
				}
				// for home4s
				for (int i = 0; i < N; i++) {
					newGlobalTransFor_H4_i[c][i] = Env.FALSE();
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_cl_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromCl_i[c][i]));
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_H0_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromH0_i[c][i]));
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_H1_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromH1_i[c][i]));
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_Hu_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromHu_i[c][i]));
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_H2_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromH2_i[c][i]));
					newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
							.or(globalTrans_H3_i_For_H4_i[c][i]
									.and(idleOfH4_i_FromH3_i[c][i]));
					// newGlobalTransFor_H4_i[c][i] =
					// newGlobalTransFor_H4_i[c][i].or(globalTrans_H4_i_For_H4_i[c][i]
					// .and(idleOfH4_i_FromH4_i[c][i]));
					for (int j = 0; j < N; j++) {
						if (j != i) {
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_cl_i_For_H4_j[c][j][i]// ariel
											// j
											.and(idleOfH4_i_FromCl_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_H0_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromH0_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_H1_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromH1_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_Hu_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromHu_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_H2_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromH2_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_H3_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromH3_j[c][i][j]));
							newGlobalTransFor_H4_i[c][i] = newGlobalTransFor_H4_i[c][i]
									.or(globalTrans_H4_i_For_H4_j[c][j][i]
											.and(idleOfH4_i_FromH4_j[c][i]));
						}
					}
				}
			}

			// collecting the transitions of each process
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					extTrans_cl[c][i] = extTrans_cl[c][i]
							.or(newGlobalTransFor_Cl_i[c][i]);
					extTrans_h0[c][i] = extTrans_h0[c][i]
							.or(newGlobalTransFor_H0_i[c][i]);
					extTrans_h1[c][i] = extTrans_h1[c][i]
							.or(newGlobalTransFor_H1_i[c][i]);
					extTrans_hu[c][i] = extTrans_hu[c][i]
							.or(newGlobalTransFor_Hu_i[c][i]);
					extTrans_h2[c][i] = extTrans_h2[c][i]
							.or(newGlobalTransFor_H2_i[c][i]);
					extTrans_h3[c][i] = extTrans_h3[c][i]
							.or(newGlobalTransFor_H3_i[c][i]);
					extTrans_h4[c][i] = extTrans_h4[c][i]
							.or(newGlobalTransFor_H4_i[c][i]);
				}
			}

			fixPointFlag = true;
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					// clients
					if (conf.do_delta_improvement) {
						deltaThetas_cl[c][i] = Util.newAllSuccNotIncl(
								oldThetas_cl[c][i], extTrans_cl[c][i],
								vars_cl[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_cl[c][i].isZero());
						newThetas_cl[c][i] = oldThetas_cl[c][i]
								.or(deltaThetas_cl[c][i]);

						// home0
						deltaThetas_h0[c][i] = Util.newAllSuccNotIncl(
								oldThetas_h0[c][i], extTrans_h0[c][i],
								vars_h0[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_h0[c][i].isZero());
						newThetas_h0[c][i] = oldThetas_h0[c][i]
								.or(deltaThetas_h0[c][i]);

						// home1
						deltaThetas_h1[c][i] = Util.newAllSuccNotIncl(
								oldThetas_h1[c][i], extTrans_h1[c][i],
								vars_h1[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_h1[c][i].isZero());
						newThetas_h1[c][i] = oldThetas_h1[c][i]
								.or(deltaThetas_h1[c][i]);

						// homeu
						deltaThetas_hu[c][i] = Util.newAllSuccNotIncl(
								oldThetas_hu[c][i], extTrans_hu[c][i],
								vars_hu[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_hu[c][i].isZero());
						newThetas_hu[c][i] = oldThetas_hu[c][i]
								.or(deltaThetas_hu[c][i]);

						// home2
						deltaThetas_h2[c][i] = Util.newAllSuccNotIncl(
								oldThetas_h2[c][i], extTrans_h2[c][i],
								vars_h2[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_h2[c][i].isZero());
						newThetas_h2[c][i] = oldThetas_h2[c][i]
								.or(deltaThetas_h2[c][i]);

						// home3
						deltaThetas_h3[c][i] = Util.newAllSuccNotIncl(
								oldThetas_h3[c][i], extTrans_h3[c][i],
								vars_h3[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_h3[c][i].isZero());
						newThetas_h3[c][i] = oldThetas_h3[c][i]
								.or(deltaThetas_h3[c][i]);

						// home4
						deltaThetas_h4[c][i] = Util.newAllSuccNotIncl(
								oldThetas_h4[c][i], extTrans_h4[c][i],
								vars_h4[c][i]);
						fixPointFlag = fixPointFlag
								& (deltaThetas_h4[c][i].isZero());
						newThetas_h4[c][i] = oldThetas_h4[c][i]
								.or(deltaThetas_h4[c][i]);

					} else {
						newThetas_cl[c][i] = Util.newAllSucc(
								oldThetas_cl[c][i], extTrans_cl[c][i],
								vars_cl[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_cl[c][i]
										.equals(oldThetas_cl[c][i]));
						// home0
						newThetas_h0[c][i] = Util.newAllSucc(
								oldThetas_h0[c][i], extTrans_h0[c][i],
								vars_h0[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_h0[c][i]
										.equals(oldThetas_h0[c][i]));
						// home1
						newThetas_h1[c][i] = Util.newAllSucc(
								oldThetas_h1[c][i], extTrans_h1[c][i],
								vars_h1[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_h1[c][i]
										.equals(oldThetas_h1[c][i]));
						// homeus
						newThetas_hu[c][i] = Util.newAllSucc(
								oldThetas_hu[c][i], extTrans_hu[c][i],
								vars_hu[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_hu[c][i]
										.equals(oldThetas_hu[c][i]));
						// home2s
						newThetas_h2[c][i] = Util.newAllSucc(
								oldThetas_h2[c][i], extTrans_h2[c][i],
								vars_h2[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_h2[c][i]
										.equals(oldThetas_h2[c][i]));
						// home3s
						newThetas_h3[c][i] = Util.newAllSucc(
								oldThetas_h3[c][i], extTrans_h3[c][i],
								vars_h3[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_h3[c][i]
										.equals(oldThetas_h3[c][i]));
						// home4s
						newThetas_h4[c][i] = Util.newAllSucc(
								oldThetas_h4[c][i], extTrans_h4[c][i],
								vars_h4[c][i]);
						fixPointFlag = fixPointFlag
								& (newThetas_h4[c][i]
										.equals(oldThetas_h4[c][i]));
					}
					oldThetas_cl[c][i] = newThetas_cl[c][i];
					oldThetas_h0[c][i] = newThetas_h0[c][i];
					oldThetas_h1[c][i] = newThetas_h1[c][i];
					oldThetas_hu[c][i] = newThetas_hu[c][i];
					oldThetas_h2[c][i] = newThetas_h2[c][i];
					oldThetas_h3[c][i] = newThetas_h3[c][i];
					oldThetas_h4[c][i] = newThetas_h4[c][i];
				}
			}
		} while (!fixPointFlag);

		// IT IS NOT A MISTAKE!! the overall time for sequential and parallel
		// should be taken BEFORE computing foundStates
		long time = System.currentTimeMillis();
		System.out.println("====== SPLIT-INVARIANT SEQUENTIAL ======");
		System.out.println("Global construction time: "
				+ (global_construction_time - start));
		System.out.println("Algorithm Run-time: "
				+ (time - global_construction_time));
		System.out.println("Overall time: " + (time - start));

		System.out.println("node num of size:"
				+ Env.TRUE().getFactory().getNodeNum());

		BDD foundStates = Env.TRUE();
		for (int c = 0; c < N; c++) {
			for (int i = 0; i < N; i++) {
				foundStates = foundStates.and(newThetas_cl[c][i]);
				foundStates = foundStates.and(newThetas_h0[c][i]);
				foundStates = foundStates.and(newThetas_h1[c][i]);
				foundStates = foundStates.and(newThetas_hu[c][i]);
				foundStates = foundStates.and(newThetas_h2[c][i]);
				foundStates = foundStates.and(newThetas_h3[c][i]);
				foundStates = foundStates.and(newThetas_h4[c][i]);
			}
		}
		return foundStates;
	}

}
