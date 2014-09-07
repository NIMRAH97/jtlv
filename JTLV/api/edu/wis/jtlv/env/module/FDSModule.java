package edu.wis.jtlv.env.module;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDException;
import net.sf.javabdd.BDDVarSet;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVBDDCouple;
import edu.wis.jtlv.env.JTLVIllegalVariableException;
import edu.wis.jtlv.env.core.ASTDebugger;
import edu.wis.jtlv.env.core.fds.FDSLexer;
import edu.wis.jtlv.env.core.fds.FDSParser;
import edu.wis.jtlv.env.core.fds.FDSStmtWalker;
import edu.wis.jtlv.env.core.fds.FDSVarWalker;

public class FDSModule implements Module {
	private String player_name;
	private String[] var_names;
	private BDD initial;
	private BDD trasition;
	private Vector<BDD> winning; // just conditions

	private Vector<JTLVBDDCouple> all_couples;

	/**
	 * @param var_names
	 */
	public FDSModule(String player_name) {
		this.player_name = player_name;
		this.var_names = new String[0];
		this.initial = Env.TRUE();
		this.trasition = Env.TRUE();
		this.winning = new Vector<BDD>(20);
		this.all_couples = new Vector<JTLVBDDCouple>(20);

		try { // this is safe, var_names is empty.
			add_var_list(var_names);
		} catch (ModuleException e) {
			e.printStackTrace();
		} catch (JTLVIllegalVariableException e) {
			e.printStackTrace();
		}
	}

	public FDSModule(String player_name, String[] var_names)
			throws ModuleException {
		this(player_name);
		try {
			add_var_list(var_names);
		} catch (JTLVIllegalVariableException e) {
			e.printStackTrace();
		}
	}

	// /////////////////////////
	// setters

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#add_var_list(java.lang.String[])
	 */
	public void add_var_list(String[] new_vars) throws ModuleException, JTLVIllegalVariableException {
		JTLVBDDCouple[] all_bdd_vars = Env.new_var(this.player_name, new_vars);
		String[] new_var_list = new String[this.var_names.length
				+ new_vars.length];
		for (int i = 0; i < this.var_names.length; i++) {
			new_var_list[i] = this.var_names[i];
		}
		for (int i = 0; i < new_vars.length; i++) {
			new_var_list[this.var_names.length + i] = new_vars[i];
		}
		this.var_names = new_var_list;
		// update once so we won't have to do it every unused of
		// prime/unprime
		set_player_var(all_bdd_vars);
	}

	private void set_player_var(JTLVBDDCouple[] couples) {
		for (int i = 0; i < couples.length; i++) {
			this.all_couples.add(couples[i]);
		}
	}

	private void set_player_var(JTLVBDDCouple a_couple) {
		this.all_couples.add(a_couple);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#add_var(java.lang.String)
	 */
	public void addVar(String new_var) throws ModuleException, JTLVIllegalVariableException {
		JTLVBDDCouple all_bdd_var = Env.new_var(this.player_name, new_var);
		String[] new_var_list = new String[this.var_names.length + 1];
		for (int i = 0; i < this.var_names.length; i++) {
			new_var_list[i] = this.var_names[i];
		}
		new_var_list[this.var_names.length] = new_var;
		this.var_names = new_var_list;
		// update once so we won't have to do it every unused of
		// prime/unprime
		set_player_var(all_bdd_var);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#get_var(java.lang.String)
	 */
	public JTLVBDDCouple get_var_look_hard(String var) {
		JTLVBDDCouple bdd_var = null;
		try {
			bdd_var = Env.get_var(this.player_name, var);
		} catch (JTLVIllegalVariableException e) {
			e.printStackTrace();
		}
		return bdd_var;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#add_initial(net.sf.javabdd.BDD)
	 */
	public void add_initial(BDD to_add) throws ModuleException {
		if (Env.has_prime_vars(to_add)) {
			throw new ModuleException(
					"ERROR: Cannot set initiatial state with primed variables: "
							+ to_add.support().intersect(this.prime_vars())
									.getDomains() + "\n");
		}
		this.initial = this.initial.id().and(to_add.id());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#conjuct_trans(net.sf.javabdd.BDD)
	 */
	public void conjunctTrans(BDD to_add) throws ModuleException {
		this.trasition = this.trasition.id().and(to_add.id());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#disjunt_trans(net.sf.javabdd.BDD)
	 */
	public void disjunctTrans(BDD to_add) throws ModuleException {
		this.trasition = this.trasition.id().or(to_add.id());
	}

	public void add_winnnig_cond(BDD to_add) throws ModuleException {
		this.winning.add(to_add.id());
	}

	// /////////////////////////
	// getters
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#initial()
	 */
	public BDD initial() {
		return this.initial.id();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#trans()
	 */
	public BDD trans() {
		return this.trasition.id();
	}

	public int justice_num() {
		return this.winning.size();
	}

	public BDD justice(int i) {
		return this.winning.elementAt(i).id();
	}

	// //////////////////////////////////////
	// operations

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#pred(net.sf.javabdd.BDD)
	 */
	public BDD pred(BDD to) throws BDDException {
		BDD prime_to = this.prime(to);
		return prime_to.and(this.trasition).exist(prime_vars());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wis.jtlv.env.module.fds.Module#succ(net.sf.javabdd.BDD)
	 */
	public BDD succ(BDD from) throws BDDException {
		BDD prime_to = from.and(this.trasition).exist(unprime_vars());
		return this.unprime(prime_to);
	}

	public JTLVBDDCouple[] all_couples() {
		JTLVBDDCouple[] res = new JTLVBDDCouple[all_couples.size()];
		all_couples.toArray(res);
		return res;
	}

	public BDD prime(BDD unprime_set) {
		return Env.prime(unprime_set, all_couples());
	}

	public BDD unprime(BDD prime_set) {
		return Env.unprime(prime_set, all_couples());
	}

	public BDDVarSet prime_vars() {
		BDDVarSet res = Env.emptySet();
		for (Iterator<JTLVBDDCouple> iter_couples = this.all_couples.iterator(); iter_couples
				.hasNext();) {
			JTLVBDDCouple c = iter_couples.next();
			res = res.id()
					.union(
							c.is_prime() ? c.toBDD().support() : c.primeBDD()
									.support());
		}
		return res;
	}

	public BDDVarSet unprime_vars() {
		BDDVarSet res = Env.emptySet();
		for (Iterator<JTLVBDDCouple> iter_couples = this.all_couples.iterator(); iter_couples
				.hasNext();) {
			JTLVBDDCouple c = iter_couples.next();
			res = res.id().union(
					c.is_prime() ? c.unprimeBDD().support() : c.toBDD()
							.support());
		}
		return res;
	}

	private static boolean debug_parse_tree = false;

	public static FDSModule[] load_from_file(String filename)
			throws IOException, RecognitionException {
		FDSModule[] res = null;
		String[] all_names_before = new String[Env.all_modules.size()];
		int count = 0;
		for (Iterator<String> name_iter = Env.all_modules.keySet().iterator(); name_iter
				.hasNext();) {
			all_names_before[count] = name_iter.next();
			count++;
		}

		// constructing the tree.
		CharStream input = new ANTLRFileStream(filename);
		FDSLexer lex = new FDSLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		FDSParser parser = new FDSParser(tokens);
		FDSParser.program_return r = parser.program();

		// debuging...
		if (debug_parse_tree) {
			ASTDebugger deb = new ASTDebugger("stam name", (CommonTree) r
					.getTree());
			deb.setSize(800, 600);
			deb.setVisible(true);
		}

		// declaring the variables.
		CommonTreeNodeStream var_decl_nodes = new CommonTreeNodeStream((Tree) r
				.getTree());
		var_decl_nodes.setTokenStream(tokens);
		FDSVarWalker var_decl_walker = new FDSVarWalker(var_decl_nodes);
		var_decl_walker.program();

		tokens.rewind();

		// other constructions.
		CommonTreeNodeStream expr_nodes = new CommonTreeNodeStream((Tree) r
				.getTree());
		expr_nodes.setTokenStream(tokens);
		FDSStmtWalker expr_walker = new FDSStmtWalker(expr_nodes);
		expr_walker.program();

		// placing the result.
		Set<String> all_names_after = Env.all_modules.keySet();
		res = new FDSModule[all_names_after.size() - all_names_before.length];
		count = 0;
		for (Iterator<String> iterator = all_names_after.iterator(); iterator
				.hasNext();) {
			String name = iterator.next();
			boolean to_add = true;
			for (int i = 0; i < all_names_before.length; i++)
				if (name.equals(all_names_before[i]))
					to_add = false;
			if (to_add) {
				res[count] = (FDSModule) Env.all_modules.get(name);
				count++;
			}
		}

		return res;
	}

	public String toString() {
		String res = "";
		res += this.player_name + " Variables are : ";
		for (int i = 0; i < this.var_names.length; i++) {
			res += this.var_names[i];
			if (i < this.var_names.length - 1)
				res += ", ";
		}
		res += "\n";
		res += this.player_name + " Initials are:\n";
		res += "\t" + this.initial().toStringWithDomains(Env.stringer) + "\n";
		res += this.player_name + " Transitions are:\n";
		res += "\t" + this.trans().toStringWithDomains(Env.stringer) + "\n";
		res += this.player_name + " Justice are:";
		if (this.justice_num() == 0) {
			res += "\n\t" + "<no winning conditions>";
		} else {
			for (int i = 0; i < this.justice_num(); i++) {
				res += "\n\t[" + i + "] "
						+ this.justice(i).toStringWithDomains(Env.stringer);
			}
		}
		return res;
	}

	public String getFullInstName() {
		return this.player_name;
	}

	public String getName() {
		String full = this.getFullInstName();
		int idx = full.lastIndexOf('.');
		return full.substring(0, idx);
	}

	public String getPath() {
		String full = this.getFullInstName();
		int idx = full.lastIndexOf('.');
		return full.substring(idx + 1);
	}
}
