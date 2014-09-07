import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.wis.jtlv.env.Env;

/**
 * @author yaniv sa'ar.
 * 
 */
public class RegTestcase {
	/**
	 * main run for the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("bdd", "java");
		String path = "./testcases/legacy/";
		try {
			Env.loadModule(path + "/abp/abp4.smv");
			String[] names = sortStringSet(Env.getModuleNames());
			for (String key : names) {
				System.out.println("=========================================");
				// Module m = Env.getModule(key);
				// if (m instanceof SMVModule)
				// ((SMVModule) m).setFullPrintingMode(true);
				System.out.println(Env.getModule(key));
			}

		} catch (IOException e) {
			// error with reading the file.
			Env.doError(e, e.toString());
			System.exit(0);
		}
	}

	public static String[] sortStringSet(Set<String> allStr) {
		List<String> strList = new LinkedList<String>();
		for (String str : allStr) {
			strList.add(str);
		}
		Collections.sort(strList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1 == null) 
					return -1;
				if(o2 == null) 
					return 1;
				while((!o1.equals("")) && (!o2.equals(""))) {
					if(o1.charAt(0) != o2.charAt(0))
						return o1.charAt(0) - o2.charAt(0);
					o1 = o1.substring(1);
					o2 = o2.substring(1);
				}
				if (!o1.equals(""))
					return 1;
				if (!o2.equals(""))
					return -1;
				return 0;
			}});
		String[] res = new String[strList.size()];
		strList.toArray(res);
		return res;
	}
}
