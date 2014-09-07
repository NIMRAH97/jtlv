import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.InflaterInputStream;

import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.Env;

public class Loader {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// String tmpDir = System.getProperty("java.io.tmpdir");
			// BDD bdd = Env.loadBDD(tmpDir + "\\savedBdd.bdd");
			BDD bdd = Env.loadBDD("./savedBdd.bdd");
			Patch1_3_2_saveLoadWithDomains
					.loadStructureAndDomains("./savedBdd.doms");
			System.out.println(bdd.toStringWithDomains(Env.stringer));
			bdd.printSet();

			@SuppressWarnings("unused")
			BDD b = Env.TRUE().getFactory().load(
					new BufferedReader(new InputStreamReader(
							new InflaterInputStream(new FileInputStream(new File("./savedBdd.doms.zip"))))));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
