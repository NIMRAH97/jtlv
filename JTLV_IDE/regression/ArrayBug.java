import java.io.IOException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;


public class ArrayBug {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Env.loadModule("./testcases/array_bug.smv");
		SMVModule main = (SMVModule) Env.getModule("main");
		main.setFullPrintingMode(true);
		System.out.println(main);
	}

}
