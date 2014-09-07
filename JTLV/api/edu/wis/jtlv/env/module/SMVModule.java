package edu.wis.jtlv.env.module;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
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
import edu.wis.jtlv.env.JTLVBDDDefine;
import edu.wis.jtlv.env.JTLVBDDToString;
import edu.wis.jtlv.env.JTLVIllegalVariableException;
import edu.wis.jtlv.env.core.ASTDebugger;
import edu.wis.jtlv.env.core.smv.SMVLexer;
import edu.wis.jtlv.env.core.smv.SMVParser;
import edu.wis.jtlv.env.core.smv.SMVStmtWalker;
import edu.wis.jtlv.env.core.smv.SMVVarWalker;
import edu.wis.jtlv.env.core.smv.StmtInit;
import edu.wis.jtlv.env.core.smv.schema.SMVModuleInfo;

public class SMVModule implements Module {
	private String player_name;
	private Vector<JTLVBDDCouple> all_couples;
	private Vector<JTLVBDDDefine> all_defines;
	private SMVModuleParamHolder[] params_holders;
	private SMVModule holder;
	private SyncStatus is_sync_with_holder;
	private Vector<SMVModule> instances;
	private SMVModuleInfo module_info;

	// FDS components
	private BDD initial;
	private BDD transition;
	private Vector<BDD> justice; // just conditions
	private Vector<BDD> compassion_p; // comp conditions
	private Vector<BDD> compassion_q; // comp conditions

	private enum SyncStatus {
		SYNC, ASYNC, UNDEF
	}

	public SMVModule(SMVModuleInfo a_module_info, String player_name)
			throws ModuleException {
		this(a_module_info, player_name, new String[0], new String[0]);
	}

	public SMVModule(SMVModuleInfo a_module_info, String player_name,
			String[] an_args, String[] a_params) throws ModuleException {
		this.player_name = player_name;
		this.module_info = a_module_info;
		this.all_couples = new Vector<JTLVBDDCouple>(20);
		this.all_defines = new Vector<JTLVBDDDefine>(20);
		this.params_holders = new SMVModuleParamHolder[an_args.length];
		for (int i = 0; i < an_args.length; i++) {
			this.params_holders[i] = new SMVModuleParamHolder(this, an_args[i],
					a_params[i]);
			for (int j = 0; j < i; j++) {
				if (this.params_holders[i].equals(this.params_holders[j])) {
					throw new ModuleException("Cannot declare MODULE "
							+ this.player_name
							+ " with two identical param names "
							+ this.params_holders[i] + ".");
				}
			}
		}
		this.holder = null;
		this.is_sync_with_holder = SyncStatus.UNDEF; // will be set when
		this.instances = new Vector<SMVModule>(20);

		// FDS components
		this.initial = Env.TRUE();
		this.transition = null;// Env.FALSE();
		this.justice = new Vector<BDD>(20);
		this.compassion_p = new Vector<BDD>(20);
		this.compassion_q = new Vector<BDD>(20);

		// // every module has an implicit variable 'running'
		// try {
		// this.addVar("running");
		// } catch (JTLVIllegalVariableException e) {
		// e.printStackTrace();
		//		}
	}

	// /////////////////////////
	// setters
	public void add_initial(BDD to_add) throws ModuleException {
		if (Env.has_prime_vars(to_add)) {
			throw new ModuleException(
					"ERROR: Cannot set initiatial state with primed variables: "
							+ to_add.support().intersect(this.prime_vars())
									.getDomains() + "\n");
		}
		this.initial = this.initial.id().and(to_add.id());
	}

	public void conjunctTrans(BDD to_add) throws ModuleException {
		if (this.transition == null) {
			this.transition = to_add.id();
			return;
		}
		this.transition = this.transition.id().and(to_add.id());
		// this.transition = this.transition.and(to_add);
	}

	public void disjunctTrans(BDD to_add) throws ModuleException {
		if (this.transition == null) {
			this.transition = to_add.id();
			return;
		}
		this.transition = this.transition.id().or(to_add.id());
	}

	public void add_justice(BDD to_add) throws ModuleException {
		this.justice.add(to_add.id());
	}

	public void add_compassion(BDD p, BDD q) throws ModuleException {
		this.compassion_p.add(p.id());
		this.compassion_q.add(q.id());
	}

	// /////////////////////////
	// getters
	public BDD initial() {
		BDD ini = this.initial.id();
		SMVModule[] all_inst = this.getAllInstances(SyncStatus.SYNC);
		for (int j = 0; j < all_inst.length; j++) {
			ini = ini.id().and(all_inst[j].initial());
		}
		return ini;
	}

	public BDD trans() {
		BDD trans = (this.transition == null) ? Env.TRUE() : this.transition
				.id();
		SMVModule[] all_inst = this.getAllInstances(SyncStatus.SYNC);
		for (int j = 0; j < all_inst.length; j++) {
			trans = trans.id().and(all_inst[j].trans());
		}
		return trans;
	}

	public int justice_num() {
		return this.justice.size();
	}

	public BDD justice(int i) {
		return this.justice.elementAt(i).id();
	}

	public int compassion_num() {
		return this.compassion_p.size();
	}

	public BDD compassion_p(int i) {
		return this.compassion_p.elementAt(i).id();
	}

	public BDD compassion_q(int i) {
		return this.compassion_q.elementAt(i).id();
	}

	// //////////////////////////////////////
	// operations
	public BDD pred(BDD to) throws BDDException {
		BDD prime_to = this.prime(to);
		return prime_to.and(this.trans()).exist(prime_vars());
	}

	public BDD succ(BDD from) throws BDDException {
		BDD prime_to = from.and(this.trans()).exist(unprime_vars());
		return this.unprime(prime_to);
	}

	public BDD prime(BDD unprime_set) {
		return Env.prime(unprime_set, getAllCouples());
	}

	public BDD unprime(BDD prime_set) {
		return Env.unprime(prime_set, getAllCouples());
	}

	public BDDVarSet prime_vars() {
		BDDVarSet res = Env.emptySet();
		for (JTLVBDDCouple c : this.getAllCouples())
			res = res.id()
					.union(// TODO: remove the toBDD
							c.is_prime() ? c.toBDD().support() : c.primeBDD()
									.support());
		return res;
	}

	public BDDVarSet unprime_vars() {
		BDDVarSet res = Env.emptySet();
		for (JTLVBDDCouple c : this.getAllCouples())
			res = res.id().union(
					c.is_prime() ? c.unprimeBDD().support() : c.toBDD()
							.support());
		return res;
	}

	public static boolean debug_parse_tree = false;
	public static boolean debug_define_parse_tree = false;
	public static boolean parse_only = false;

	public static SMVModule[] load_from_file(String filename)
			throws IOException, RecognitionException, ModuleException {
		SMVModule[] res = null;
		String[] all_names_before = new String[Env.all_modules.size()];
		int count = 0;
		for (Iterator<String> name_iter = Env.all_modules.keySet().iterator(); name_iter
				.hasNext();) {
			all_names_before[count] = name_iter.next();
			count++;
		}

		// //////////////////////////////////////////
		// 1. // lexer and parsing //////////////////
		// //////////////////////////////////////////
		CharStream input = new ANTLRFileStream(filename);
		SMVLexer lex = new SMVLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		SMVParser parser = new SMVParser(tokens);
		SMVParser.program_return r = parser.program();
		// //////////////////////////////////////////
		// debugging... /////////////////////////////
		// //////////////////////////////////////////
		if (debug_parse_tree) {
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
		SMVVarWalker walker2 = new SMVVarWalker(expr_nodes);
		walker2.program();
		// //////////////////////////////////////////
		// 3. // attaching defines //////////////////
		// //////////////////////////////////////////
		if (!parse_only) {
			StmtInit initiator = StmtInit.get_instance();
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
				JTLVBDDDefine[] defs = initiator.getAllUninitiatedDefines();
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
		Set<String> all_names_after = Env.all_modules.keySet();
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
				res[count] = (SMVModule) Env.all_modules.get(name);
				count++;
			}
		}

		return res;
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////// internal implementation start here //////////////////////
	// ///////////////////////////////////////////////////////////////////

	// new implementation to setters, hasers and getters of elements in a SMV
	// module...
	// ///////////////////////////////////////////////////////////////////
	// setters
	private void setHolder(SMVModule h) {
		this.holder = h;
	}

	public void setFullInstName(String a_name) {
		this.player_name = a_name;
	}

	public void setParams() throws ModuleException {
		for (SMVModuleParamHolder param : this.getAllParams()) {
			param.attachPointer();
		}
	}

	private void setPlayerVar(JTLVBDDCouple a_couple) {
		this.all_couples.add(a_couple);
	}

	private void setPlayerDefine(JTLVBDDDefine a_def) {
		this.all_defines.add(a_def);
	}

	public void addDefine(String addr) throws ModuleException {
		if (addr == null || addr.equals(""))
			throw new ModuleException("Couldn't declare a define with no name.");

		// 1. if it was found since it is a param, then defining this define at
		// the above holder...
		if (this.hasVar(addr, true) | this.hasDefine(addr, true)) {
			if (this.hasParam(addr, true)) {
				SMVModuleParamHolder par = getParam(addr, true);
				SMVModule holder = par.getForInstance().getHolder();
				holder.addDefine(par.getInitString());
				return;
			}
			throw new ModuleException("Variable " + addr + " already declared.");
		}

		// 2. if it is a simple define...
		int idx = addr.lastIndexOf('.');
		if (idx == -1) {
			setPlayerDefine(new JTLVBDDDefine(this.getFullInstName(), addr));
			return;
		}

		// 3. if it is a define to a lower instance...
		String inst_name = addr.substring(0, idx);
		if (this.hasInstance(inst_name, true)) {
			this.getInstance(inst_name, true)
					.addDefine(addr.substring(idx + 1));
			return;
		}

		System.err.println("Couldn't find instance " + inst_name
				+ " to define " + addr + ".");
	}

	public void addDefineFromParam(String addr) throws ModuleException {
		if (addr == null || addr.equals(""))
			throw new ModuleException("Couldn't declare a define with no name.");

		// 2. if it is a simple define...
		int idx = addr.lastIndexOf('.');
		if (idx == -1) {
			setPlayerDefine(new JTLVBDDDefine(this.getFullInstName(), addr));
			return;
		}

		// 3. if it is a define to a lower instance...
		String inst_name = addr.substring(0, idx);
		if (this.hasInstance(inst_name, true)) {
			this.getInstance(inst_name, true)
					.addDefine(addr.substring(idx + 1));
			return;
		}

		System.err.println("Couldn't find instance " + inst_name
				+ " to define " + addr + ".");
	}

	public void addVar(String new_var) throws ModuleException,
			JTLVIllegalVariableException {
		addVar(new_var, 2, null, -1);
	}

	public void addVar(String new_var, String[] val_names)
			throws ModuleException, JTLVIllegalVariableException {
		addVar(new_var, val_names.length, val_names, -1);
	}

	public void addVar(String new_var, int range_start, int range_end)
			throws ModuleException, JTLVIllegalVariableException {
		addVar(new_var, (range_end - range_start + 1), null, range_start);
	}

	public void addVar(String new_var, int values_size, String[] val_names,
			int range_start) throws ModuleException,
			JTLVIllegalVariableException {
		if (new_var == null || new_var.equals(""))
			throw new ModuleException(
					"Couldn't declare a variable with no name.");

		// 1. if it was found since it is a param, then defining this define at
		// the above holder...
		if (this.hasVar(new_var, true) | this.hasDefine(new_var, true)) {
			if (this.hasParam(new_var, true)) {
				SMVModuleParamHolder par = getParam(new_var, true);
				SMVModule holder = par.getForInstance().getHolder();
				holder.addVar(par.getInitString());
				return;
			}
			throw new ModuleException("Variable " + new_var
					+ " already declared.");
		}

		// 2. if it is a simple define...
		int idx = new_var.lastIndexOf('.');
		if (idx != -1) {
			throw new ModuleException(
					"Couldn't declare a variable with dot product.");
		}

		// if it is a pointer, then just assigning the reference.
		JTLVBDDCouple bdd_var = Env.new_var(this.getFullInstName(), new_var,
				values_size);
		// register value names
		if (val_names != null) {
			if (values_size != val_names.length)
				throw new ModuleException(
						"Internal error: values list do not match the size");
			Env.stringer.register_domain_module_values(this, bdd_var
					.getDomain(), val_names);
			Env.stringer.register_domain_module_values(this, bdd_var
					.getOtherDomain(), val_names);
		} else if (values_size > 2) {
			// it is a range, only registering the beginning and the length
			Env.stringer.register_domain_module_values(this, bdd_var
					.getDomain(), range_start, values_size);
			Env.stringer.register_domain_module_values(this, bdd_var
					.getOtherDomain(), range_start, values_size);
		} // for two or 1 I'm leaving it as is handled with boolean.

		setPlayerVar(bdd_var);
	}

	public void addInstance(SMVModule inner, boolean is_sync) {
		if (!is_sync) {
			// every async module has an implicit variable 'running'
			try {
				inner.addVar("running");
			} catch (JTLVIllegalVariableException e) {
				e.printStackTrace();
			} catch (ModuleException e) {
				e.printStackTrace();
			}
		}		
		inner.setHolder(this);
		inner.is_sync_with_holder = is_sync ? SyncStatus.SYNC
				: SyncStatus.ASYNC;
		this.instances.add(inner);
	}

	// ///////////////////////////////////////////////////////////////////
	// simple hasers...
	public boolean hasVar(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (JTLVBDDCouple coup : this.getAllCouples())
				if (coup.get_name().equals(addr))
					return true;
			for (SMVModuleParamHolder param : this.getAllParams()) {
				// it would of been better to quire for the name first..
				// but I have no choice since I need the couples first.
				if (addr.startsWith(param.getLocalName() + "[")) {
					if (param.isVarArray()) {
						for (JTLVBDDCouple coup : param.getVarArray())
							if (coup.get_name().equals(addr))
								return true;
					}
				}
				// for this case I can quire the name first.
				if (param.getLocalName().equals(addr)) {
					if (param.isVar()) {
						return true;
					}
				}
			}
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasVar(var_name, look_hard);
		return false;
	}

	public boolean hasVarArray(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (JTLVBDDCouple coup : this.getAllCouples())
				if (coup.getSimpleArrayName().equals(addr))
					return true;
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isVarArray())
						return true;
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasVarArray(var_name,
					look_hard);
		return false;
	}

	public boolean hasDefine(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (JTLVBDDDefine def : this.getAllDefines())
				if (def.get_name().equals(addr))
					return true;
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isDefine())
						return true;
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasDefine(var_name, look_hard);
		return false;
	}

	// this could be implemented in s direct query since player_name is
	// unique to each instance...
	// since i want the implementation to be identical to all components,
	// I'm leaving it as such...
	public boolean hasInstance(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		// special case
		if (addr.equals("self"))
			return true;

		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (SMVModule inst : this.getAllInstances())
				if (inst.getFullInstName().equals(
						this.getFullInstName() + "." + addr))
					return true;
			for (SMVModuleParamHolder param : this.getAllParams()) {
				// it would of been better to quire for the name first..
				// but I have no choice since I need the couples first.
				if (addr.startsWith(param.getLocalName() + "[")) {
					if (param.isInstanceArray()) {
						for (JTLVBDDCouple coup : param.getVarArray())
							if (coup.get_name().equals(addr))
								return true;
					}
				}
				// for this case I can quire the name first.
				if (param.getLocalName().equals(addr)) {
					if (param.isInstance()) {
						return true;
					}
				}
			}
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasInstance(var_name,
					look_hard);
		return false;
	}

	public boolean hasInstanceArray(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		// special case
		if (addr.equals("self"))
			return false;

		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (SMVModule inst : this.getAllInstances())
				if (inst.getSimpleArrayName().equals(
						this.getFullInstName() + "." + addr))
					return true;
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isInstanceArray())
						return true;
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasInstanceArray(var_name,
					look_hard);
		return false;
	}

	private boolean was_here = false;

	// 0. if a number, then it is a value... only then:
	// 1. first look here
	// 2. then look at the params
	// 3. finally look at lower levels.
	public boolean hasValue(String name) {
		if (name == null || name.equals(""))
			return false;
		// 0.
		try {
			Integer.parseInt(name);
			return true;
		} catch (NumberFormatException nfe) {
			// do nothing and continuing.
		}
		// 1.
		for (JTLVBDDCouple coup : this.getAllCouples())
			if (Env.stringer.domain_has_module_value(this, coup.getDomain(),
					name))
				return true;
		// 2.
		for (SMVModuleParamHolder param : this.getAllParams()) {
			if (param.isVar()) {
				if (Env.stringer.domain_has_module_value(this.getHolder(),
						param.getVar().getDomain(), name))
					return true;
			} else if (param.isInstance()) {
				this.was_here = true;
				SMVModule inst = param.getInstance();
				if (!inst.was_here)
					if (inst.hasValue(name))
						return true;
				this.was_here = false;
			}
		}
		// 3.
		this.was_here = true;
		for (SMVModule inst : this.getAllInstances())
			if (!inst.was_here)
				if (inst.hasValue(name))
					return true;
		this.was_here = false;

		return false;
	}

	public boolean hasParam(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return false;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					return true;
			return false;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).hasParam(var_name, look_hard);
		return false;
	}

	// ///////////////////////////////////////////////////////////////////
	public JTLVBDDCouple getVar(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (JTLVBDDCouple coup : this.getAllCouples())
				if (coup.get_name().equals(addr))
					return coup;
			for (SMVModuleParamHolder param : this.getAllParams()) {
				// it would of been better to quire for the name first..
				// but I have no choice since I need the couples first.
				if (addr.startsWith(param.getLocalName() + "[")) {
					if (param.isVarArray()) {
						for (JTLVBDDCouple coup : param.getVarArray())
							if (coup.get_name().equals(addr))
								return coup;
					}
				}
				// for this case I can quire the name first.
				if (param.getLocalName().equals(addr)) {
					if (param.isVar()) {
						return param.getVar();
					}
				}
			}
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getVar(var_name, look_hard);
		return null;
	}

	public JTLVBDDCouple[] getVarArray(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			Vector<JTLVBDDCouple> col = new Vector<JTLVBDDCouple>(10);
			for (JTLVBDDCouple coup : this.getAllCouples())
				if (coup.getSimpleArrayName().equals(addr))
					col.add(coup);
			if (col.size() > 0) {
				JTLVBDDCouple[] res = new JTLVBDDCouple[col.size()];
				col.toArray(res);
				return res;
			}
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isVar())
						return param.getVarArray();
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getVarArray(var_name,
					look_hard);
		return null;
	}

	public JTLVBDDDefine getDefine(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (JTLVBDDDefine def : this.getAllDefines())
				if (def.get_name().equals(addr))
					return def;
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isDefine())
						return param.getDefine();
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getDefine(var_name, look_hard);
		return null;
	}

	public SMVModule getInstance(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		// special case
		if (addr.equals("self"))
			return this;

		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (SMVModule inst : this.getAllInstances())
				if (inst.getFullInstName().equals(
						this.getFullInstName() + "." + addr))
					return inst;
			for (SMVModuleParamHolder param : this.getAllParams()) {
				// it would of been better to quire for the name first..
				// but I have no choice since I need the couples first.
				if (addr.startsWith(param.getLocalName() + "[")) {
					if (param.isInstanceArray()) {
						for (SMVModule inst : param.getInstanceArray())
							// TODO: !!! this is a bug... I'm only checking
							// the beginning...
							if (addr.startsWith(param.getLocalName()))
								return inst;
					}
				}
				// for this case I can quire the name first.
				if (param.getLocalName().equals(addr)) {
					if (param.isInstance()) {
						return param.getInstance();
					}
				}
			}
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getInstance(var_name,
					look_hard);
		return null;
	}

	public SMVModule[] getInstanceArray(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		// special case
		if (addr.equals("self"))
			return null;

		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			Vector<SMVModule> col = new Vector<SMVModule>(10);
			for (SMVModule inst : this.getAllInstances())
				if (inst.getSimpleArrayName().equals(
						this.getFullInstName() + "." + addr))
					col.add(inst);
			if (col.size() > 0) {
				SMVModule[] res = new SMVModule[col.size()];
				col.toArray(res);
				return res;
			}
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					if (param.isInstanceArray())
						return param.getInstanceArray();
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getInstanceArray(var_name,
					look_hard);
		return null;
	}

	public BDD getValue(String name) {
		BDD res = Env.FALSE();
		JTLVBDDToString stger = Env.stringer; // just for readability.
		if (name == null || name.equals(""))
			return res;
		// 0.
		try {
			Integer.parseInt(name);
			return res;
		} catch (NumberFormatException nfe) {
			// do nothing and continueing.
		}
		// 1.
		for (JTLVBDDCouple coup : this.getAllCouples()) {
			BDDDomain dom = coup.getDomain();
			if (stger.domain_has_module_value(this, dom, name))
				res = res.id()
						.or(
								dom.ithVar(stger.get_module_value_loc(this,
										dom, name)));
		}
		// 2.
		for (SMVModuleParamHolder param : this.getAllParams()) {
			if (param.isVar()) {
				BDDDomain dom = param.getVar().getDomain();
				if (stger.domain_has_module_value(this.getHolder(), dom, name))
					res = res.id().or(
							dom.ithVar(stger.get_module_value_loc(this
									.getHolder(), dom, name)));
			} else if (param.isInstance()) {
				if (param.getInstance().hasValue(name))
					res = res.id().or(param.getInstance().getValue(name));
			}
		}
		// 3.
		this.was_here = true;
		for (SMVModule inst : this.getAllInstances())
			if (!inst.was_here)
				if (inst.hasValue(name))
					res = res.id().or(inst.getValue(name));
		this.was_here = false;

		return res;
	}

	public SMVModuleParamHolder getParam(String addr, boolean look_hard) {
		if (addr == null || addr.equals(""))
			return null;
		int idx = addr.indexOf('.');
		if (idx == -1 | !look_hard) {
			for (SMVModuleParamHolder param : this.getAllParams())
				if (param.getLocalName().equals(addr))
					return param;
			return null;
		}
		String inst_name = addr.substring(0, idx);
		String var_name = addr.substring(idx + 1);
		if (hasInstance(inst_name, false))
			return getInstance(inst_name, false).getParam(var_name, look_hard);
		return null;
	}

	public SMVModuleParamHolder getParam(int at_index) {
		SMVModuleParamHolder[] all_par = this.getAllParams();
		if ((at_index < 0) | (at_index >= all_par.length))
			return null;
		return all_par[at_index];
	}

	// ///////////////////////////////////////////////////////////////////
	// general getters.....
	public JTLVBDDCouple[] getAllCouples() {
		JTLVBDDCouple[] res = new JTLVBDDCouple[all_couples.size()];
		all_couples.toArray(res);
		return res;
	}

	public JTLVBDDDefine[] getAllDefines() {
		JTLVBDDDefine[] res = new JTLVBDDDefine[all_defines.size()];
		all_defines.toArray(res);
		return res;
	}

	public SMVModule[] getAllInstances() {
		SMVModule[] res_internal = new SMVModule[this.instances.size()];
		this.instances.toArray(res_internal);
		return res_internal;
	}

	public SMVModule[] getAllInstances(SyncStatus status) {
		SMVModule[] all = this.getAllInstances();
		Vector<SMVModule> res = new Vector<SMVModule>(all.length);
		for (SMVModule m : all) {
			if (m.is_sync_with_holder == status)
				res.add(m);
		}
		SMVModule[] res_arr = new SMVModule[res.size()];
		res.toArray(res_arr);
		return res_arr;
	}

	public SMVModuleParamHolder[] getAllParams() {
		return this.params_holders;
	}

	public SMVModule getHolder() {
		return this.holder;
	}

	public SMVModuleInfo getModuleInfo() {
		return this.module_info;
	}

	// ///////////////////////////////////////////////////////////////////
	// printing and naming.....
	public boolean isArrayElement() {
		return ((this.getFullInstName().lastIndexOf(']') + 1) == this
				.getFullInstName().length());
	}

	public String getSimpleArrayName() {
		int last_RB = this.getFullInstName().lastIndexOf(']');
		if ((last_RB + 1) != this.getFullInstName().length())
			return "";

		int last_LB = last_RB - 1;
		int count = 1;
		while ((last_LB > 0) & (count > 0)) {
			if (this.getFullInstName().charAt(last_LB) == ']')
				count++;
			if (this.getFullInstName().charAt(last_LB) == '[')
				count--;
			last_LB--;
		}
		return this.getFullInstName().substring(0, last_LB);
	}

	public String getFullInstName() {
		return this.player_name;
	}

	public String getFullInstNameWithParams() {
		String f_name = this.getFullInstName() + "(";
		SMVModuleParamHolder[] all = this.getAllParams();
		for (int i = 0; i < all.length; i++) {
			f_name += (i == 0) ? "" : ", ";
			f_name += all[i].getLocalName();
		}
		f_name += ")";
		return f_name;
	}

	public boolean print_tostring_with_bdd = false;

	public void set_toString_print_with_bdd() {
		this.print_tostring_with_bdd = true;
	}

	public void reset_toString_print_with_bdd() {
		this.print_tostring_with_bdd = false;
	}

	public String toString() {
		String res = "MODULE " + getFullInstNameWithParams() + "\n";

		JTLVBDDCouple[] all_vars = this.getAllCouples();
		res += this.getFullInstName() + " Variables are :";
		if (all_vars.length > 0) {
			res += "\n\t";
		}
		for (int i = 0; i < all_vars.length; i++) {
			res += all_vars[i].get_name() + ", ";
		}

		SMVModule[] all_inst = this.getAllInstances(SyncStatus.SYNC);
		for (int j = 0; j < all_inst.length; j++) {
			res += "\n\t[[[ SYNC <" + all_inst[j].getFullInstName() + ">.{";
			JTLVBDDCouple[] all_inst_vars = all_inst[j].getAllCouples();
			for (int i = 0; i < all_inst_vars.length; i++) {
				res += all_inst_vars[i].get_name();
				if (i < all_inst_vars.length - 1)
					res += ", ";
			}
			res += "} ]]],";
		}

		all_inst = this.getAllInstances(SyncStatus.ASYNC);
		for (int j = 0; j < all_inst.length; j++) {
			res += "\n\t[[[ ASYNC <" + all_inst[j].getFullInstName() + ">.{";
			JTLVBDDCouple[] all_inst_vars = all_inst[j].getAllCouples();
			for (int i = 0; i < all_inst_vars.length; i++) {
				res += all_inst_vars[i].get_name();
				if (i < all_inst_vars.length - 1)
					res += ", ";
			}
			res += "} ]]],";
		}
		res += "\n";

		res += this.getFullInstName() + " Define statements are:\n";
		res += "\t";
		for (JTLVBDDDefine def : this.getAllDefines()) {
			if (!hasParam(def.get_name(), false)) {
				res += def.get_name() + ", ";
			} // for cases that this is a define from param, I'm not printing
			// it....
		}
		res += "\n";

		res += this.getFullInstName() + " Initials are:\n";
		if (this.print_tostring_with_bdd) {
			// res += "\t" + this.initial().toStringWithDomains(Env.stringer)
			// + "\n";
			res += Env.toNiceString(this, this.initial(), "\t") + "\n";
		} else {
			res += "\t<low printing mode>\n";
		}

		res += this.getFullInstName() + " Transitions are:\n";
		if (this.print_tostring_with_bdd) {
			// res += "\t" + this.trans().toStringWithDomains(Env.stringer) +
			// "\n";
			res += Env.toNiceString(this, this.trans(), "\t") + "\n";
		} else {
			res += "\t<low printing mode>\n";
		}

		res += this.getFullInstName() + " Justice are:";
		if (this.justice_num() == 0) {
			res += "\n\t" + "<no winning conditions>";
		} else {
			BDD[] all_just_arr = new BDD[this.justice.size()];
			this.justice.toArray(all_just_arr);
			for (int i = 0; i < all_just_arr.length; i++) {
				res += "\n\t[" + i + "] ";
				if (this.print_tostring_with_bdd) {
					// res += all_just_arr[i].toStringWithDomains(Env.stringer);
					res += Env.toNiceString(this, all_just_arr[i]);
				} else {
					res += "<low printing mode>";
				}
			}
		}
		res += "\n";

		res += this.getFullInstName() + " Compassions are:";
		if (this.compassion_num() == 0) {
			res += "\n\t" + "<no winning conditions>";
		} else {
			BDD[] all_comp_p_arr = new BDD[this.compassion_p.size()];
			BDD[] all_comp_q_arr = new BDD[this.compassion_q.size()];
			this.compassion_p.toArray(all_comp_p_arr);
			this.compassion_q.toArray(all_comp_q_arr);
			for (int i = 0; i < all_comp_p_arr.length; i++) {
				res += "\n\t[" + i + "] ";
				if (this.print_tostring_with_bdd) {
					// res +=
					// all_comp_p_arr[i].toStringWithDomains(Env.stringer)
					// + "\n\t\t -> "
					// + all_comp_q_arr[i]
					// .toStringWithDomains(Env.stringer);
					res += Env.toNiceString(this, all_comp_p_arr[i])
							+ "\n\t\t -> "
							+ Env.toNiceString(this, all_comp_q_arr[i]);
				} else {
					res += "<low printing mode>";
				}

			}
		}

		return res;
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
