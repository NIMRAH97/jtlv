
import java.io.IOException;

import net.sf.javabdd.BDD;

import org.antlr.runtime.RecognitionException;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.FDSModule;

/**
 * @author yaniv
 * 
 */
public class FDSFileConstructionTest {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[] file_names = new String[8];
		// file_name = args[0];
		file_names[0] = "/home/yaniv/workspace/JTLV/testcases/testcase1.fds";
		file_names[1] = "/home/yaniv/workspace/JTLV/testcases/testcase2.fds";
		file_names[2] = "/home/yaniv/workspace/JTLV/testcases/testcase3.fds";
		file_names[3] = "/home/yaniv/workspace/JTLV/testcases/testcase4.fds";
		file_names[4] = "/home/yaniv/workspace/JTLV/testcases/testcase5.fds";
		file_names[5] = "/home/yaniv/workspace/JTLV/testcases/testcase6.fds";
		file_names[6] = "/home/yaniv/workspace/JTLV/testcases/testcase7.fds";
		file_names[7] = "/home/yaniv/workspace/JTLV/testcases/testcase8.fds";
		//for (int i = 0; i < file_names.length; i++) {
			test_main(file_names[1]);
			Env.reset_env();
		//}
	}

	public static void test_main(String args) {
		String file_name;
		file_name = args;
		
		try {
			FDSModule.load_from_file(file_name);
			FDSModule player1 = (FDSModule) Env.all_modules.get("sys");
			FDSModule player2 = (FDSModule) Env.all_modules.get("env");

			System.out.print("=============================================\n");
			System.out.println(player1);
			System.out.print("=============================================\n");
			System.out.println(player2);
			System.out.print("=============================================\n");
			System.out.println("");

			BDD step0 = player2.initial();
			BDD step1 = player2.succ(step0);
			BDD step2 = player2.succ(step1);
			BDD step3 = player2.succ(step2);
			BDD step4 = player2.succ(step3);
			BDD pred3 = player2.succ(step4);
			BDD pred2 = player2.succ(pred3);
			BDD pred1 = player2.succ(pred2);
			BDD pred0 = player2.succ(pred1);
			System.out.println(step0.toStringWithDomains(Env.stringer));
			System.out.println(step1.toStringWithDomains(Env.stringer));
			System.out.println(step2.toStringWithDomains(Env.stringer));
			System.out.println(step3.toStringWithDomains(Env.stringer));
			System.out.println(step4.toStringWithDomains(Env.stringer));
			System.out.println(pred3.toStringWithDomains(Env.stringer));
			System.out.println(pred2.toStringWithDomains(Env.stringer));
			System.out.println(pred1.toStringWithDomains(Env.stringer));
			System.out.println(pred0.toStringWithDomains(Env.stringer));
			
			

		} catch (IOException e) {
			// error with reading the file.
			e.printStackTrace();
			System.exit(0);
		} catch (RecognitionException e) {
			// error with parsing the file.
			e.printStackTrace();
			System.exit(0);
		}
	}
}
