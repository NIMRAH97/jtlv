import java.io.IOException;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.module.SMVModule.SyncStatus;

public class Patch1_1_0_idleStep {
	public static BDD idleStep(SMVModule me) {
		BDD res = Env.TRUE();
		for (ModuleBDDField f : me.getAllFields()) {
			res = res.id().and(f.getDomain().buildEquals(f.getOtherDomain()));
		}
		for (SMVModule m : me.getAllInstances(SyncStatus.SYNC)) {
			res = res.id().and(idleStep(m));
		}
		return res;
	}

	// an example of usage.
	public static void main(String[] args) {
		String file_name = "testcases/legacy/ctl-ltl/counter.smv";
		try {
			Env.beQuiet();
			Env.loadModule(file_name);
			for (String key : Env.getModuleNames()) {
				System.out.println("=========================================");
				SMVModule m = (SMVModule) Env.getModule(key);
				m.setFullPrintingMode(true);
				System.out.println(m);
				System.out.println("=========================================");
				System.out.println("-----------------------------------------");
				System.out.println("-----------------------------------------");
				// ///////////////////
				System.out.println(Env.toNiceString(m, Patch1_1_0_idleStep
						.idleStep(m), ""));
				// ///////////////////
				System.out.println("-----------------------------------------");
				System.out.println("-----------------------------------------");

			}
			System.out.println("=========== DONE ========================");
		} catch (IOException e) {
			Env.doError(e, e.toString());
			System.exit(0);
		}
	}
}
