package edu.wis.jtlv.new_tb;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import edu.wis.jtlv.env.Env;

/**
 * @author yaniv
 * 
 */
public class SingleParserTestCase {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd", "buddy");
		for (int i = 0; i < 5; i++) {

			String file_name = "testcases/legacy/abp/abp11.smv";

			try {

				// SMVModule.debug_parse_tree = true;
				Env.loadModule(file_name);
				Set<String> all = Env.getModuleNames();
				for (Iterator<String> iter_names = all.iterator(); iter_names
						.hasNext();) {
					String key = iter_names.next();
					System.out
							.println("===========================================");
					System.out.println(Env.getModule(key));
					System.out
							.println("===========================================");

				}
			} catch (IOException e) {
				// error with reading the file.
				Env.doError(e, e.toString());
				System.exit(0);
			}
			System.out.println("!!!!!!!!!!!!!!!reseting " + i);
			Env.resetEnv();
			System.out.println("!!!!!!!!!!!!!!!end reseting " + i);			
		}
	}
}
