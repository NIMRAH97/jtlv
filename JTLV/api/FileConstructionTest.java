
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.antlr.runtime.RecognitionException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;

/**
 * @author yaniv
 * 
 */
public class FileConstructionTest {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd", "java");
		String file_name;
		String[] all_test_names = get_all_test_files();
		// bug 1 : constant parameter
		// file_name = "testcases/mutex1.smv";
		// bug 2 : in the error recovery of "Variable already exists"
		// file_name = "testcases/pci/pci.smv";
		// Bug in the design!!! Variable not existing, (Not nice enough error..)
		// the design is with a bug after my change....
		// file_name = "testcases/reactor/base.smv";
		// bug 2 : in the error recovery of "Variable already exists"
		// file_name = "testcases/smv-dist/robot.smv";

		// ////////////////////////////////////////////
		// * 0-31, 63-77, 122-123 : not good tests
		// * 45-51, 53, 55, 95, 163-164 : bug with the range - exception (look
		// like I need to extend the variables num - or use jFactory)
		// * 80-81, 84, 103-105, 108, 114-116, 134, 146-150, 153 : (bug 1)
		// constant parameter
		// * 86, 109, 120, 128 : (bug 2) in the error recovery of "Variable
		// already exists"
		// * 93, 113, 141-142, 154-156, 158 : looks like another bug in the
		// recovery
		// * 138, 161-162 : looks like another bug in the recovery
		// * 117, 126, 129-130, 132 : throw a nice error when the string file
		// name is null
		// * 135-136 : "Could not find variable" ???
		// * 144-145 : design bugs??
		file_name = all_test_names[80];

		try {

			// SMVModule.debug_parse_tree = true;
			SMVModule.load_from_file(file_name);
			Set<String> all = Env.all_modules.keySet();
			for (Iterator<String> iter_names = all.iterator(); iter_names
					.hasNext();) {
				String key = iter_names.next();
				System.out
						.println("=============================================");
				System.out.println(Env.all_modules.get(key));
				System.out
						.println("=============================================");

			}
			// System.out.println(Env.get_var("main.pr0", "state0").toBDD());
			// System.out.println(Env.get_var("main", "s0").toBDD());
			// System.out.println(Env.get_var("main.pr0", "state1").toBDD());
			// System.out.println(Env.get_var("main", "s1").toBDD());
			// System.out.println(Env.get_var("main.pr0", "turn").toBDD());
			// System.out.println(Env.get_var("main", "turn").toBDD());
			// System.out.println(Env.get_var("main.pr0", "turn0").toBDD());
			// System.out.println(Env.get_var("main", "s0").toBDD());

			// System.out.println(Env.get_var("main.pr0", "s0"));

			// BDD dom = Env.get_var("main", "rr").getDomain().varRange(3, 5);
			// System.out.println(dom.toStringWithDomains(Env.stringer));
			// dom.printSetWithDomains();

			// BigInteger val = Env.TRUE().scanVar(Env.get_var("main",
			// "rr").getDomain());
			// System.out.println(val + "\n\n\n");
			//			
			// BDD three = Env.get_var("main", "rr").getDomain().ithVar(3);
			// BDD four = Env.get_var("main", "rr").getDomain().ithVar(4);
			// BDD five = Env.get_var("main", "rr").getDomain().ithVar(5);
			// BDD six = Env.get_var("main", "rr").getDomain().ithVar(6);
			// BDD four_and_five = four.or(five);
			// BDD three_and_six = six.or(three);
			// BDD all_n = four_and_five.or(three_and_six);
			// System.out.println(three.toStringWithDomains(Env.stringer));
			// System.out.println(four.toStringWithDomains(Env.stringer));
			// System.out.println(five.toStringWithDomains(Env.stringer));
			// System.out.println(six.toStringWithDomains(Env.stringer));
			// System.out.println(four_and_five.toStringWithDomains(Env.stringer));
			// System.out.println(three_and_six.toStringWithDomains(Env.stringer));
			// System.out.println(all_n.toStringWithDomains(Env.stringer));
			//			
			// BDD v0 = Env.get_var("main.s2r_in", "tag").getDomain().ithVar(0);
			// BDD v1 = Env.get_var("main.s2r_in", "tag").getDomain().ithVar(1);
			// BDD v2 = Env.get_var("main.s2r_in", "tag").getDomain().ithVar(2);
			// BDD v3 = Env.get_var("main.s2r_in", "tag").getDomain().ithVar(3);
			// System.out.println(v0.toStringWithDomains(Env.stringer));
			// System.out.println(v1.toStringWithDomains(Env.stringer));
			// System.out.println(v2.toStringWithDomains(Env.stringer));
			// System.out.println(v3.toStringWithDomains(Env.stringer));
			// BDD v01 = v0.or(v1);
			// BDD v23 = v2.or(v3);
			// BDD v03 = v0.or(v3);
			// BDD v023 = v0.or(v2).or(v3);
			// System.out.println(v01.toStringWithDomains(Env.stringer));
			// System.out.println(v23.toStringWithDomains(Env.stringer));
			// System.out.println(v03.toStringWithDomains(Env.stringer));
			// System.out.println(v023.toStringWithDomains(Env.stringer));
			//			
			// BDD w0 = Env.get_var("main", "w").getDomain().ithVar(0);
			// BDD w1 = Env.get_var("main", "w").getDomain().ithVar(96);
			// BDD w2 = Env.get_var("main", "w").getDomain().ithVar(2);
			// BDD w3 = Env.get_var("main", "w").getDomain().ithVar(255);
			// System.out.println(w0.toStringWithDomains(Env.stringer));
			// System.out.println(w1.toStringWithDomains(Env.stringer));
			// System.out.println(w2.toStringWithDomains(Env.stringer));
			// System.out.println(w3.toStringWithDomains(Env.stringer));
			// BDD w01 = w0.or(w1);
			// BDD w23 = w2.or(w3);
			// BDD w03 = w0.or(w3);
			// BDD w023 = w0.or(w2).or(w3);
			// System.out.println(w01.toStringWithDomains(Env.stringer));
			// System.out.println(w23.toStringWithDomains(Env.stringer));
			// System.out.println(w03.toStringWithDomains(Env.stringer));
			// System.out.println(w023.toStringWithDomains(Env.stringer));
			//			
		} catch (IOException e) {
			// error with reading the file.
			Env.doError(e, e.toString());
			System.exit(0);
		} catch (RecognitionException e) {
			// error with parsing the file.
			Env.doError(e, e.toString());
			System.exit(0);
		}
	}

	public static String[] get_all_test_files() {
		// file_name = "testcases/abp/abp10.smv";
		// file_name = "testcases/abp/abp11.smv";
		// file_name = "testcases/bmc/counter.smv";
		// file_name = "testcases/bmc/multiplier.smv";
		// file_name = "testcases/brp/brp.smv";
		// file_name = "testcases/deadlock/dartes.smv";
		// file_name = "testcases/deadlock/ftp3.smv";
		// file_name = "testcases/example_cmu/gigamax.smv";
		// file_name = "testcases/example_cmu/ring.smv";
		// file_name = "testcases/queue/queue.smv";

		String[] file_names = new String[165];
		// file_names[0] = "testcases/abcd/abcd10.smv";
		// file_names[1] = "testcases/abcd/abcd12.smv";
		// file_names[2] = "testcases/abcd/abcd14.smv";
		// file_names[3] = "testcases/abcd/abcd16.smv";
		// file_names[4] = "testcases/abcd/abcd18.smv";
		// file_names[5] = "testcases/abcd/abcd20.smv";
		// file_names[6] = "testcases/abcd/abcd22.smv";
		// file_names[7] = "testcases/abcd/abcd24.smv";
		// file_names[8] = "testcases/abcd/abcd26.smv";
		// file_names[9] = "testcases/abcd/abcd28.smv";
		// file_names[10] = "testcases/abcd/abcd30.smv";
		// file_names[11] = "testcases/abcd/abcd32.smv";
		// file_names[12] = "testcases/abcd/abcd34.smv";
		// file_names[13] = "testcases/abcd/abcd36.smv";
		// file_names[14] = "testcases/abcd/abcd38.smv";
		// file_names[15] = "testcases/abcd/abcd4.smv";
		// file_names[16] = "testcases/abcd/abcd40.smv";
		// file_names[17] = "testcases/abcd/abcd42.smv";
		// file_names[18] = "testcases/abcd/abcd44.smv";
		// file_names[19] = "testcases/abcd/abcd46.smv";
		// file_names[20] = "testcases/abcd/abcd48.smv";
		// file_names[21] = "testcases/abcd/abcd50.smv";
		// file_names[22] = "testcases/abcd/abcd52.smv";
		// file_names[23] = "testcases/abcd/abcd54.smv";
		// file_names[24] = "testcases/abcd/abcd56.smv";
		// file_names[25] = "testcases/abcd/abcd58.smv";
		// file_names[26] = "testcases/abcd/abcd6.smv";
		// file_names[27] = "testcases/abcd/abcd60.smv";
		// file_names[28] = "testcases/abcd/abcd62.smv";
		// file_names[29] = "testcases/abcd/abcd64.smv";
		// file_names[30] = "testcases/abcd/abcd8.smv";
		// file_names[31] = "testcases/abcd/abcdN.smv";
		file_names[32] = "testcases/abp/abp10.smv";
		file_names[33] = "testcases/abp/abp11.smv";
		file_names[34] = "testcases/abp/abp16.smv";
		file_names[35] = "testcases/abp/abp4.smv";
		file_names[36] = "testcases/abp/abp8.smv";
		file_names[37] = "testcases/bmc/MCP.smv";
		file_names[38] = "testcases/bmc/barrel5.smv";
		file_names[39] = "testcases/bmc/counter.smv";
		file_names[40] = "testcases/bmc/dme/dme10.smv";
		file_names[41] = "testcases/bmc/dme/dme11.smv";
		file_names[42] = "testcases/bmc/dme/dme12.smv";
		file_names[43] = "testcases/bmc/dme/dme13.smv";
		file_names[44] = "testcases/bmc/dme/dme14.smv";
		file_names[45] = "testcases/bmc/dme/dme15.smv";
		file_names[46] = "testcases/bmc/dme/dme16.smv";
		file_names[47] = "testcases/bmc/dme/dme17.smv";
		file_names[48] = "testcases/bmc/dme/dme18.smv";
		file_names[49] = "testcases/bmc/dme/dme19.smv";
		file_names[50] = "testcases/bmc/dme/dme20.smv";
		file_names[51] = "testcases/bmc/dme/dme30.smv";
		file_names[52] = "testcases/bmc/dme/dme4.smv";
		file_names[53] = "testcases/bmc/dme/dme40.smv";
		file_names[54] = "testcases/bmc/dme/dme5.smv";
		file_names[55] = "testcases/bmc/dme/dme50.smv";
		file_names[56] = "testcases/bmc/dme/dme6.smv";
		file_names[57] = "testcases/bmc/dme/dme7.smv";
		file_names[58] = "testcases/bmc/dme/dme8.smv";
		file_names[59] = "testcases/bmc/dme/dme9.smv";
		file_names[60] = "testcases/bmc/multiplier.smv";
		file_names[61] = "testcases/bmc_tutorial/bmc_tutorial.smv";
		file_names[62] = "testcases/brp/brp.smv";
		file_names[63] = "testcases/calculator/calculator12.smv";
		file_names[64] = "testcases/calculator/calculator16.smv";
		file_names[65] = "testcases/calculator/calculator20.smv";
		file_names[66] = "testcases/calculator/calculator24.smv";
		file_names[67] = "testcases/calculator/calculator28.smv";
		file_names[68] = "testcases/calculator/calculator32.smv";
		file_names[69] = "testcases/calculator/calculator36.smv";
		file_names[70] = "testcases/calculator/calculator40.smv";
		file_names[71] = "testcases/calculator/calculator44.smv";
		file_names[72] = "testcases/calculator/calculator48.smv";
		file_names[73] = "testcases/calculator/calculator52.smv";
		file_names[74] = "testcases/calculator/calculator56.smv";
		file_names[75] = "testcases/calculator/calculator60.smv";
		file_names[76] = "testcases/calculator/calculator64.smv";
		file_names[77] = "testcases/calculator/calculatorN.smv";
		file_names[78] = "testcases/ctl-ltl/abp4.smv";
		file_names[79] = "testcases/ctl-ltl/abp8.smv";
		file_names[80] = "testcases/ctl-ltl/counter.smv";
		file_names[81] = "testcases/ctl-ltl/dme2.smv";
		file_names[82] = "testcases/ctl-ltl/guidance.smv";
		file_names[83] = "testcases/ctl-ltl/mutex.smv";
		file_names[84] = "testcases/ctl-ltl/mutex1.smv";
		file_names[85] = "testcases/ctl-ltl/p-queue.smv";
		file_names[86] = "testcases/ctl-ltl/periodic.smv";
		file_names[87] = "testcases/ctl-ltl/prod-cons.smv";
		file_names[88] = "testcases/ctl-ltl/production-cell.smv";
		file_names[89] = "testcases/ctl-ltl/queue.smv";
		file_names[90] = "testcases/ctl-ltl/ring.smv";
		file_names[91] = "testcases/ctl-ltl/semaphore.smv";
		file_names[92] = "testcases/ctl-ltl/short.smv";
		file_names[93] = "testcases/ctl-ltl/syncarb5.smv";
		file_names[94] = "testcases/deadlock/dartes.smv";
		file_names[95] = "testcases/deadlock/dpd75.smv";
		file_names[96] = "testcases/deadlock/dph35.smv";
		file_names[97] = "testcases/deadlock/ftp3.smv";
		file_names[98] = "testcases/deadlock/furnace17.smv";
		file_names[99] = "testcases/deadlock/gas-nq7.smv";
		file_names[100] = "testcases/deadlock/key10.smv";
		file_names[101] = "testcases/deadlock/mmgt20.smv";
		file_names[102] = "testcases/deadlock/over12.smv";
		file_names[103] = "testcases/example_cmu/counter.smv";
		file_names[104] = "testcases/example_cmu/dme1.smv";
		file_names[105] = "testcases/example_cmu/dme2.smv";
		file_names[106] = "testcases/example_cmu/gigamax.smv";
		file_names[107] = "testcases/example_cmu/mutex.smv";
		file_names[108] = "testcases/example_cmu/mutex1.smv";
		file_names[109] = "testcases/example_cmu/periodic.smv";
		file_names[110] = "testcases/example_cmu/ring.smv";
		file_names[111] = "testcases/example_cmu/semaphore.smv";
		file_names[112] = "testcases/example_cmu/short.smv";
		file_names[113] = "testcases/example_cmu/syncarb5.smv";
		file_names[114] = "testcases/example_irst/dme4.smv";
		file_names[115] = "testcases/example_irst/dme5.smv";
		file_names[116] = "testcases/example_irst/dme6.smv";
		// file_names[117] =
		file_names[118] = "testcases/example_irst/gigamax.smv";
		file_names[119] = "testcases/example_irst/gigamax_ltl.smv";
		file_names[120] = "testcases/example_irst/periodic.smv";
		file_names[121] = "testcases/guidance/guidance.smv";
		file_names[122] = "testcases/m4/modcounter.m4.smv";
		file_names[123] = "testcases/m4/non_selective.m4.smv";
		file_names[124] = "testcases/msi/msi_wtrans.smv";
		file_names[125] = "testcases/mutex1.smv";
		// file_names[126] = "testcases/p-queue/p-queue.ordnusmv";
		file_names[127] = "testcases/p-queue/p-queue.smv";
		file_names[128] = "testcases/pci/pci.smv";
		// file_names[129] = "testcases/pci/pci.smv".varord;
		// file_names[130] = "testcases/prod-cons/prod-cons.ordnusmv";
		file_names[131] = "testcases/prod-cons/prod-cons.smv";
		// file_names[132] =
		// "testcases/production-cell/production-cell.ordnusmv";
		file_names[133] = "testcases/production-cell/production-cell.smv";
		file_names[134] = "testcases/psl-samples/counter.smv";
		file_names[135] = "testcases/psl-samples/dme2-16.smv";
		file_names[136] = "testcases/psl-samples/dme2.smv";
		file_names[137] = "testcases/psl-samples/gigamax.smv";
		file_names[138] = "testcases/psl-samples/ring.smv";
		file_names[139] = "testcases/psl-samples/semaphore.smv";
		file_names[140] = "testcases/psl-samples/short.smv";
		file_names[141] = "testcases/psl-samples/syncarb10.smv";
		file_names[142] = "testcases/psl-samples/syncarb5.smv";
		file_names[143] = "testcases/queue/queue.smv";
		file_names[144] = "testcases/reactor/base.smv";
		file_names[145] = "testcases/reactor/idle.smv";
		file_names[146] = "testcases/smv-dist/counter.smv";
		file_names[147] = "testcases/smv-dist/dme1-16.smv";
		file_names[148] = "testcases/smv-dist/dme1.smv";
		file_names[149] = "testcases/smv-dist/dme2-16.smv";
		file_names[150] = "testcases/smv-dist/dme2.smv";
		file_names[151] = "testcases/smv-dist/gigamax.smv";
		file_names[152] = "testcases/smv-dist/mutex.smv";
		file_names[153] = "testcases/smv-dist/mutex1.smv";
		file_names[154] = "testcases/smv-dist/pci3p.smv";
		file_names[155] = "testcases/smv-dist/pci4p.smv";
		file_names[156] = "testcases/smv-dist/periodic.smv";
		file_names[157] = "testcases/smv-dist/ring.smv";
		file_names[158] = "testcases/smv-dist/robot.smv";
		file_names[159] = "testcases/smv-dist/semaphore.smv";
		file_names[160] = "testcases/smv-dist/short.smv";
		file_names[161] = "testcases/smv-dist/syncarb10.smv";
		file_names[162] = "testcases/smv-dist/syncarb5.smv";
		file_names[163] = "testcases/tcas/tcas-t.smv";
		file_names[164] = "testcases/tcas/tcas.smv";
		return file_names;
	}
}
