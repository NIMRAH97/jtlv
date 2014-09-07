import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import net.sf.javabdd.BDDFactory;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.core.ASTDebugger;
import edu.wis.jtlv.env.core.smv.SMVLexer;
import edu.wis.jtlv.env.core.smv.SMVParser;
import edu.wis.jtlv.env.core.smv.SMVStmtWalker;
import edu.wis.jtlv.env.core.smv.Initiator;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.ModuleBDDDefine;
import edu.wis.jtlv.env.module.ModuleBDDField;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;

public class Patch1_2_0_loadSMVWithOrd {
	public static Module[] loadModule(String filename, String ordfile)
			throws IOException {
		String text = "";
		FileReader fr = new FileReader(new File(ordfile));
		BufferedReader in = new BufferedReader(fr);
		String next_read = in.readLine();
		while (next_read != null) {
			text += "\n" + next_read;
			next_read = in.readLine();
		}

		Stack<String> vars = new Stack<String>();
		StringTokenizer st = new StringTokenizer(text);
		while (st.hasMoreElements()) {
			vars.add(st.nextToken());
		}

		String[] vars_arr = new String[vars.size()];
		vars.toArray(vars_arr);
		return loadModule(filename, vars_arr);
	}

	public static Module[] loadModule(String filename) throws IOException {
		return loadModule(filename, (String[]) null);
	}

	// ///////////////////////////////////////////////
	// Env Source to be changed.... //////////////////
	// ///////////////////////////////////////////////
	public static Module[] loadModule(String filename, String[] order)
			throws IOException {
		try {
			if (filename.toLowerCase().endsWith("smv")) {
				return loadSMVModule(new ANTLRFileStream(filename), false,
						false, order);
			}
		} catch (RecognitionException e) {
			Env.doError(e, e.getMessage());
		} catch (ModuleException e) {
			Env.doError(e, e.getMessage());
		}
		throw new IOException("Cannot load module with formats other then"
				+ " .fds or .smv");
	}

	private static SMVModule[] loadSMVModule(CharStream input,
			boolean do_parse_only, boolean show_parse_tree, String[] order)
			throws IOException, RecognitionException, ModuleException {
		SMVModule[] res = null;
		String[] all_names_before = new String[Env.getModuleNum()];
		int count = 0;
		for (Iterator<String> name_iter = Env.getModuleNames().iterator(); name_iter
				.hasNext();) {
			all_names_before[count] = name_iter.next();
			count++;
		}

		// //////////////////////////////////////////
		// 1. // lexer and parsing //////////////////
		// //////////////////////////////////////////
		// CharStream input = new ANTLRFileStream(filename);
		SMVLexer lex = new SMVLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		SMVParser parser = new SMVParser(tokens);
		SMVParser.program_return r = parser.program();
		// //////////////////////////////////////////
		// debugging... /////////////////////////////
		// //////////////////////////////////////////
		if (show_parse_tree) {
			ASTDebugger deb = new ASTDebugger("stam name", (CommonTree) r
					.getTree());
			deb.setSize(800, 600);
			deb.setVisible(true);
		}
		// //////////////////////////////////////////
		// 2. // variable declarations //////////////
		// //////////////////////////////////////////
		tokens.rewind();
		CommonTreeNodeStream expr_nodes = new CommonTreeNodeStream((Tree) r
				.getTree());
		expr_nodes.setTokenStream(tokens);
		// TODO: removed as of 1.4.0
		//SMVVarWalker walker2 = new SMVVarWalker(expr_nodes);
		//walker2.program();

		// //////////////////////////////////////////
		// 2.a // order variable, before creating any BDD //
		// //////////////////////////////////////////
		if (order != null) {
			BDDFactory factory = Env.TRUE().getFactory();
			if ((2 * order.length) != factory.numberOfDomains()) {
				Env.doError(new Exception("The order given is not complete."),
						"The order given is not complete.");
			} else {
				// reordering all factories
				try {
					Method m = Env.class.getMethod("NOP", (Class[]) null);
					factory.registerReorderCallback(Env.class, m);
					factory.setVarOrder(makeOrderArr(order));
					for (int i = 0; i < Env.getThreadPoolSize(); i++) {
						factory = Env.copyBDDIntoManager(i + 1, Env.TRUE())
								.getFactory();
						factory.registerReorderCallback(Env.class, m);
						factory.setVarOrder(makeOrderArr(order));
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}

		// //////////////////////////////////////////
		// 3. // attaching defines //////////////////
		// //////////////////////////////////////////
		if (!do_parse_only) {
			Initiator initiator = Initiator.get_instance();
			initiator.startDefinePhase();
			int def_inited = -1;
			while (def_inited < initiator.countAllInitiatedDefines()) {
				def_inited = initiator.countAllInitiatedDefines();
				tokens.rewind();
				expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
				expr_nodes.setTokenStream(tokens);
				SMVStmtWalker walker3 = new SMVStmtWalker(expr_nodes);
				walker3.program();
			}
			if (def_inited != initiator.countAllDefines()) {
				// not all defines where initiated.... !!
				ModuleBDDDefine[] defs = initiator.getAllUninitiatedDefines();
				String def_str = "";
				for (int i = 0; i < defs.length; i++) {
					def_str += defs[i].toString();
					def_str += ((i + 1) != defs.length) ? ", " : "";
				}
				throw new ModuleException(
						"Recursive define statements between "
								+ "one of the following defines:\n\t" + def_str);
			}
			initiator.endDefinePhase();
			// //////////////////////////////////////////
			// 4. // attaching statements ///////////////
			// //////////////////////////////////////////
			initiator.startInitTransPhase();
			tokens.rewind();
			expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
			expr_nodes.setTokenStream(tokens);
			SMVStmtWalker walker4 = new SMVStmtWalker(expr_nodes);
			walker4.program();
			initiator.endInitTransPhase();
			// //////////////////////////////////////////
			// //////////////////////////////////////////
			// //////////////////////////////////////////
			// // other constructions.
			// expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
			// expr_nodes.setTokenStream(tokens);
			// SMVXXXWalker walker3 = new SMVXXXWalker(expr_nodes);
			// walker3.program();
			// //////////////////////////////////////////
			// //////////////////////////////////////////
			// //////////////////////////////////////////
		}
		// placing the result.
		Set<String> all_names_after = Env.getModuleNames();
		res = new SMVModule[all_names_after.size() - all_names_before.length];
		count = 0;
		for (Iterator<String> iterator = all_names_after.iterator(); iterator
				.hasNext();) {
			String name = iterator.next();
			boolean to_add = true;
			for (int i = 0; i < all_names_before.length; i++)
				if (name.equals(all_names_before[i]))
					to_add = false;
			if (to_add) {
				res[count] = (SMVModule) Env.getModule(name);
				count++;
			}
		}

		return res;
	}

	public static int[] makeOrderArr(String[] vars) throws IOException {
		int[] order = new int[2 * vars.length];
		for (int i = 0; i < order.length; i++) {
			int idx = vars[i / 2].lastIndexOf('.');
			ModuleBDDField f;
			if (idx == -1)
				f = Env.getVar("", vars[i / 2]);
			else
				f = Env.getVar(vars[i / 2].substring(0, idx), vars[i / 2]
						.substring(idx + 1));
			if (i % 2 == 0)
				order[i] = f.getDomain().getIndex();
			else
				order[i] = f.getOtherDomain().getIndex();
		}
		return order;
	}
}
