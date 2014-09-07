import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.HashMap;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;

public class Patch1_3_2_saveLoadWithDomains {

	public static void saveStructureAndDomains(String file) throws IOException {
		// saving map from String <full field name> to String[] {<module
		// name>,<field name>, <val 1>, <val 2>, ... <val n> }
		HashMap<String, String[]> res = new HashMap<String, String[]>();
		for (Module m : Env.getAllModules()) {
			for (ModuleBDDField f : m.getAllFields()) {
				String[] vals = new String[f.getDomain().size().intValue() + 2];
				vals[0] = m.getFullInstName();
				vals[1] = f.getName();
				for (int i = 2; i < vals.length; i++) {
					vals[i] = Env.stringer.elementName(f.getDomain(),
							new BigInteger("" + (i - 2)));
				}
				res.put(vals[0] + "." + vals[1], vals);
			}
		}
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(res);
	}

	@SuppressWarnings("unchecked")
	public static void loadStructureAndDomains(String file) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap<String, String[]> map = (HashMap<String, String[]>) ois
				.readObject();

		for (String str : map.keySet()) {
			String[] module_field_and_vals = map.get(str);
			SMVModule mod = (SMVModule) Env.getModule(module_field_and_vals[0]);
			if (mod == null) {
				mod = new SMVModule(module_field_and_vals[0]);
				Env.putModule(mod.getFullInstName(), mod);
			}
			String[] vals = new String[module_field_and_vals.length - 2];
			System.arraycopy(module_field_and_vals, 2, vals, 0, vals.length);
			
			try {
				mod.addVar(module_field_and_vals[1], vals);
			} catch (ModuleException e) {
			}
		}
	}
}
