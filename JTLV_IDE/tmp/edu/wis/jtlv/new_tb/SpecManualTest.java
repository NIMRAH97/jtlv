package edu.wis.jtlv.new_tb;

import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.spec.Spec;

public class SpecManualTest {
	public static void main(String[] args) {
		// System.setProperty("bdd", "buddy");
		String file_name = "testcases/legacy/ctl-ltl/counter.smv";

		try {
			Env.loadModule(file_name);
			System.out.println("========= DONE Loading Modules ==========");

			String to_parse = ""
					+ "SPEC															\n"
					+ "	AG AF main.bit2.carry_out									\n"
					+ "																\n"
					+ "SPEC															\n"
					+ "	AG AF main.bit2.carry_out									\n"
					+ "																\n"
					+ "LTLSPEC														\n"
					+ "	G F main.bit2.carry_out										\n"
					+ "																\n"
					+ "SPEC															\n"
					+ "	AG(!main.bit2.carry_out);									\n"
					+ "																\n"
					+ "SPEC															\n"
					+ "	!AG(!main.bit2.carry_out)									\n"
					+ "																\n"
					+ "LTLSPEC														\n"
					+ "	G (!main.bit2.carry_out)									\n"
					+ "																\n"
					+ "SPEC															\n"
					+ "	A [(!main.bit2.carry_out) UNTIL (main.bit2.carry_out)]		\n"
					+ "																\n"
					+ "SPEC															\n"
					+ "	A [ x BU 1..2 x.z.y ] & x & y & z & w						\n"
					+ "																\n"
					+ "SPEC	// error												\n"
					+ "	(!main.bit2.carry_out) U x;									\n"
					+ "																\n"
					+ "CTL*SPEC														\n"
					+ "	A [ !main.bit2.carry_out UNTIL main.bit2.carry_out ]		\n"
					+ "																\n"
					+ "LTLSPEC	// error											\n"
					+ "	(!main.bit2.carry_out) U    ; 								\n"
					+ "																\n"
					+ "CTLSPEC														\n"
					+ "	A [ x BU 1..2 x.z.y ] & x & y & z & w;						\n"
					+ "																\n"
					+ "LTLSPEC	// error											\n"
					+ "	U (!main.bit2.carry_out) 									\n"
					+ "																\n"
					+ "CTL*SPEC														\n" 
					+ "	A [ x.y.z.w U x.z.y ];										\n"
					+ "																\n"
					+ "SPEC	// error												\n"
					+ "	(!main.bit2.carry_out) U ;									\n"
					+ "																\n";

			Spec[] ls = Env.loadSpecString(to_parse);
			for (Spec s : ls) {
				System.out.println("=========");
				System.out.println(s);
				if (s == null)
					continue;
				System.out.print("isPropSpec --------------- ");
				System.out.println(s.isPropSpec());
				System.out.print("isCTLSpec ---------------- ");
				System.out.println(s.isCTLSpec());
				System.out.print("isRealTimeCTLSpec -------- ");
				System.out.println(s.isRealTimeCTLSpec());
				System.out.print("isLTLSpec ---------------- ");
				System.out.println(s.isLTLSpec());
				System.out.print("isFutureLTLSpec ---------- ");
				System.out.println(s.isFutureLTLSpec());
				System.out.print("isPastLTLSpec ------------ ");
				System.out.println(s.isPastLTLSpec());
				System.out.print("isCTLStarSpec ------------ ");
				System.out.println(s.isCTLStarSpec());
				System.out.print("hasTemporalOperators ----- ");
				System.out.println(s.hasTemporalOperators());
			}
			System.out.println("========= DONE Loading Specs ============");

		} catch (IOException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		}

	}
}
