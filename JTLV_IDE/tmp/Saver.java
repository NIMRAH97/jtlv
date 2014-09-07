import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.DeflaterOutputStream;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDException;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;

public class Saver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// tc1(args);
		tc2(args);
	}

	public static void tc2(String[] args) {
		try {
			SMVModule mod = new SMVModule("testModule");
			Env.putModule(mod.getFullInstName(), mod);
			ModuleBDDField var = mod.addVar("varA", new String[] { "a1", "a2",
					"a3" });
			ModuleBDDField varb = mod.addVar("varB", new String[] { "b1", "b2",
					"b3" });
			mod.conjunctTrans(var.getDomain().ithVar(0).imp(
					var.prime().getDomain().ithVar(1)));
			mod.conjunctTrans(var.getDomain().ithVar(1).imp(
					var.prime().getDomain().ithVar(2)));
			mod.conjunctTrans(var.getDomain().ithVar(2).imp(
					var.prime().getDomain().ithVar(0)));

			mod.conjunctTrans(varb.getDomain().ithVar(0).imp(
					varb.prime().getDomain().ithVar(1)));
			mod.conjunctTrans(varb.getDomain().ithVar(1).imp(
					varb.prime().getDomain().ithVar(2)));
			mod.conjunctTrans(varb.getDomain().ithVar(2).imp(
					varb.prime().getDomain().ithVar(0)));
			BDD trans = mod.trans();
			System.out.println(trans.toStringWithDomains(Env.stringer));
			trans.printSet();
			// String tmpDir = System.getProperty("java.io.tmpdir");
			// Env.saveBDD(tmpDir + "\\savedBdd.bdd", trans);
			Env.saveBDD("./savedBdd.bdd", trans);
			Patch1_3_2_saveLoadWithDomains
					.saveStructureAndDomains("./savedBdd.doms");

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new DeflaterOutputStream(new FileOutputStream(new File(
							"./savedBdd.doms.zip")))));
			Env.TRUE().getFactory().save(bw, trans);
			bw.flush();
			bw.close();
			
		} catch (BDDException e) {
			e.printStackTrace();
		} catch (ModuleException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void tc1(String[] args) {
		try {
			SMVModule mod = new SMVModule("testModule");
			ModuleBDDField var = mod.addVar("varA", new String[] { "a1", "a2",
					"a3" });
			mod.conjunctTrans(var.getDomain().ithVar(0).imp(
					var.prime().getDomain().ithVar(1)));
			mod.conjunctTrans(var.getDomain().ithVar(1).imp(
					var.prime().getDomain().ithVar(2)));
			mod.conjunctTrans(var.getDomain().ithVar(2).imp(
					var.prime().getDomain().ithVar(0)));
			BDD trans = mod.trans();
			// System.out.println(trans.toStringWithDomains(Env.stringer));
			trans.printSet();
			// String tmpDir = System.getProperty("java.io.tmpdir");
			// Env.saveBDD(tmpDir + "\\savedBdd.bdd", trans);
			Env.saveBDD("./savedBdd.bdd", trans);
			FileOutputStream fos = new FileOutputStream("./stringer.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Env.stringer);

		} catch (BDDException e) {
			e.printStackTrace();
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}