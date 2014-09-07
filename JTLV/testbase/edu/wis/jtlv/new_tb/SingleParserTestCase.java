package edu.wis.jtlv.new_tb;

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
public class SingleParserTestCase {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd", "buddy");
		for (int i = 0; i < 5; i++) {

			String file_name = "testcases/abp/abp11.smv";

			try {

				// SMVModule.debug_parse_tree = true;
				SMVModule.load_from_file(file_name);
				Set<String> all = Env.all_modules.keySet();
				for (Iterator<String> iter_names = all.iterator(); iter_names
						.hasNext();) {
					String key = iter_names.next();
					System.out
							.println("===========================================");
					System.out.println(Env.all_modules.get(key));
					System.out
							.println("===========================================");

				}
			} catch (IOException e) {
				// error with reading the file.
				Env.doError(e, e.toString());
				System.exit(0);
			} catch (RecognitionException e) {
				// error with parsing the file.
				Env.doError(e, e.toString());
				System.exit(0);
			}
			System.out.println("!!!!!!!!!!!!!!!reseting " + i);
			Env.reset_env();
			System.out.println("!!!!!!!!!!!!!!!end reseting " + i);			
		}
	}
}
