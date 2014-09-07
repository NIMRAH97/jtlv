package edu.wis.jtlv.env.core.smv;

import java.util.Stack;
import java.util.Vector;

import org.antlr.runtime.IntStream;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVBDDCouple;
import edu.wis.jtlv.env.JTLVBDDDefine;
import edu.wis.jtlv.env.core.smv.eval.AbstractElement;
import edu.wis.jtlv.env.core.smv.eval.OpAnd;
import edu.wis.jtlv.env.core.smv.eval.OpConcat;
import edu.wis.jtlv.env.core.smv.eval.OpCond;
import edu.wis.jtlv.env.core.smv.eval.OpDivide;
import edu.wis.jtlv.env.core.smv.eval.OpEqual;
import edu.wis.jtlv.env.core.smv.eval.OpGE;
import edu.wis.jtlv.env.core.smv.eval.OpGT;
import edu.wis.jtlv.env.core.smv.eval.OpIff;
import edu.wis.jtlv.env.core.smv.eval.OpImplies;
import edu.wis.jtlv.env.core.smv.eval.OpIn;
import edu.wis.jtlv.env.core.smv.eval.OpLE;
import edu.wis.jtlv.env.core.smv.eval.OpLT;
import edu.wis.jtlv.env.core.smv.eval.OpLshift;
import edu.wis.jtlv.env.core.smv.eval.OpMinus;
import edu.wis.jtlv.env.core.smv.eval.OpMod;
import edu.wis.jtlv.env.core.smv.eval.OpNext;
import edu.wis.jtlv.env.core.smv.eval.OpNot;
import edu.wis.jtlv.env.core.smv.eval.OpNotEqual;
import edu.wis.jtlv.env.core.smv.eval.OpOr;
import edu.wis.jtlv.env.core.smv.eval.OpPlus;
import edu.wis.jtlv.env.core.smv.eval.OpRshift;
import edu.wis.jtlv.env.core.smv.eval.OpTimes;
import edu.wis.jtlv.env.core.smv.eval.OpUnaryMinus;
import edu.wis.jtlv.env.core.smv.eval.OpUnion;
import edu.wis.jtlv.env.core.smv.eval.OpXnor;
import edu.wis.jtlv.env.core.smv.eval.OpXor;
import edu.wis.jtlv.env.core.smv.eval.StmtDefineOperator;
import edu.wis.jtlv.env.core.smv.eval.StmtOperator;
import edu.wis.jtlv.env.core.smv.eval.ValueConsRangeStmt;
import edu.wis.jtlv.env.core.smv.eval.ValueConsStrStmt;
import edu.wis.jtlv.env.core.smv.eval.ValueDomStmt;
import edu.wis.jtlv.env.core.smv.eval.ValueStmt;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLAnd;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLIff;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLImplies;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLNot;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLOr;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureAA;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureABF;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureABG;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureAF;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureAG;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureAX;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEBF;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEBG;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEE;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEF;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEG;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLPureEX;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLXnor;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpCTLXor;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLFuture;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLGlobal;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLHistorical;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLNext;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLNot;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLNotPrecNot;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLOnce;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLPrec;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLRelease;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLSince;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLTriggered;
import edu.wis.jtlv.env.core.smv.eval.temporals.OpLTLUntil;
import edu.wis.jtlv.env.core.smv.schema.StmtValueArrayWA;
import edu.wis.jtlv.env.module.Module;
import edu.wis.jtlv.env.module.ModuleException;
import edu.wis.jtlv.env.module.SMVModule;
import edu.wis.jtlv.env.module.SMVModuleParamHolder;

public class StmtInit {
	// singleton instance
	private static StmtInit single = new StmtInit();

	private StmtInit() {
	}

	public static StmtInit get_instance() {
		return single;
	}

	// ////////////////////////////////////////////////////////////////////////
	// phase 3 -
	private SMVModule[] module_pool;

	public void collect_module_inst(String module_name) {
		endAllDefineScope();

		Vector<SMVModule> pool_vec = new Vector<SMVModule>(10);

		for (String key : Env.all_modules.keySet()) {
			SMVModule m = (SMVModule) Env.all_modules.get(key);
			if (m.getModuleInfo().name.equals(module_name)) {
				pool_vec.add(m);
			}
		}
		this.module_pool = new SMVModule[pool_vec.size()];
		pool_vec.toArray(this.module_pool);

		startAllDefineScope();
	}

	// ////////////////////////////////////////////////////////////////////////
	// //////////////////// init trans phase //////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	private boolean init_trans_phase = false;

	public void startInitTransPhase() {
		this.init_trans_phase = true;
	}

	public void endInitTransPhase() {
		this.init_trans_phase = false;
	}

	public void attachINIT(StmtValueArrayWA val) throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			ValueStmt single_val = new StmtOperator(this.module_pool[i],
					val.arr[i]).eval_stmt();
			try {
				this.module_pool[i].add_initial(single_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachTRANS(StmtValueArrayWA val) throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			ValueStmt single_val = new StmtOperator(this.module_pool[i],
					val.arr[i]).eval_stmt();
			try {
				this.module_pool[i].conjunctTrans(single_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachASSIGNStmt(String var_name, StmtValueArrayWA val)
			throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			JTLVBDDCouple coup = this.module_pool[i].getVar(var_name, true);
			ValueDomStmt to_assign = new ValueDomStmt(this.module_pool[i], coup);
			ValueStmt single_val = new StmtOperator(this.module_pool[i],
					new OpEqual(to_assign, val.arr[i])).eval_stmt();
			try {
				this.module_pool[i].conjunctTrans(single_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachINITStmt(String var_name, StmtValueArrayWA val)
			throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			JTLVBDDCouple coup = this.module_pool[i].getVar(var_name, true);
			ValueDomStmt to_assign = new ValueDomStmt(this.module_pool[i], coup);
			ValueStmt single_val = new StmtOperator(this.module_pool[i],
					new OpEqual(to_assign, val.arr[i])).eval_stmt();
			try {
				this.module_pool[i].add_initial(single_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachNEXTStmt(String var_name, StmtValueArrayWA val)
			throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			JTLVBDDCouple coup = this.module_pool[i].getVar(var_name, true);
			JTLVBDDCouple prime_coup = coup.prime();
			ValueDomStmt to_assign = new ValueDomStmt(this.module_pool[i],
					prime_coup);
			ValueStmt single_val = new StmtOperator(this.module_pool[i],
					new OpEqual(to_assign, val.arr[i])).eval_stmt();
			try {
				this.module_pool[i].conjunctTrans(single_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachJUSTStmt(StmtValueArrayWA val) throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			ValueStmt just_val = new StmtOperator(this.module_pool[i],
					val.arr[i]).eval_stmt();
			try {
				this.module_pool[i].add_justice(just_val.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	public void attachCOMPStmt(StmtValueArrayWA val_p, StmtValueArrayWA val_q)
			throws SMVParseException {
		if (!this.init_trans_phase)
			return;
		for (int i = 0; i < this.module_pool.length; i++) {
			ValueStmt comp_val_p = new StmtOperator(this.module_pool[i],
					val_p.arr[i]).eval_stmt();
			ValueStmt comp_val_q = new StmtOperator(this.module_pool[i],
					val_q.arr[i]).eval_stmt();
			try {
				this.module_pool[i].add_compassion(comp_val_p.toBDD(),
						comp_val_q.toBDD());
			} catch (ModuleException e) {
				throw new SMVParseException(e.getMessage());
				// Env.do_error(e);
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////
	// //////////////////// define phase //////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// whether we are in the phase of defining defines...
	// to be set by external call when starting the walker
	private boolean define_phase = true;

	public void startDefinePhase() {
		this.define_phase = true;
	}

	public void endDefinePhase() {
		this.define_phase = false;
	}

	// whether we are now calculating a define...
	// to be set when starting a define in the tree, and reset
	// when ending the define of when this define is depending
	// on another un-prepared define.
	// the scope is per instance
	private Stack<boolean[]> define_scope = initDefineScope();

	private Stack<boolean[]> initDefineScope() {
		Stack<boolean[]> res = new Stack<boolean[]>();
		res.add(mkDefineScope(Env.getAllModules().length, false));
		return res;
	}

	private boolean[] mkDefineScope(int size, boolean initial_value) {
		boolean[] new_scope = new boolean[size];
		for (int i = 0; i < new_scope.length; i++) {
			new_scope[i] = initial_value;
		}
		return new_scope;
	}

	public void startAllDefineScope() {
		this.define_scope.push(mkDefineScope(this.module_pool.length, true));
	}

	public void endAllDefineScope() {
		this.define_scope.pop();
	}

	public void endDefineScope(int at) {
		this.define_scope.peek()[at] = false;
	}

	public boolean calcStmt(int at) {
		return (!this.define_phase) | this.define_scope.peek()[at];
	}

	public void attachParamDefine(String proc_name,
			Vector<StmtValueArrayWA> param_vals) throws SMVParseException {
		// vector of parameters where each is a vector of values depending on
		// the instance.
		for (int j = 0; j < param_vals.size(); j++) {
			for (int i = 0; i < this.module_pool.length; i++) {
				if (!calcStmt(i))
					continue;
				SMVModule inst = this.module_pool[i].getInstance(proc_name,
						false);
				SMVModuleParamHolder param = inst.getParam(j);
				if (!param.isDefine())
					continue;
				JTLVBDDDefine def = param.getDefine();
				def.attachValue(new StmtDefineOperator(this.module_pool[i],
						param_vals.elementAt(j).arr[i]));
			}
		}
		this.endAllDefineScope();
	}

	public void attachDefine(String def_name, StmtValueArrayWA val)
			throws SMVParseException {
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			JTLVBDDDefine def = this.module_pool[i].getDefine(def_name, true);
			def.attachValue(new StmtDefineOperator(this.module_pool[i],
					val.arr[i]));
		}
		this.endAllDefineScope();
	}

	public int countAllInitiatedDefines() {
		int res = 0;

		for (Module m : Env.getAllModules())
			if (SMVModule.class.isInstance(m))
				for (JTLVBDDDefine def : ((SMVModule) m).getAllDefines())
					if (def.isInitiated())
						res++;

		return res;
	}

	public JTLVBDDDefine[] getAllUninitiatedDefines() {
		Vector<JTLVBDDDefine> res = new Vector<JTLVBDDDefine>(10);

		for (Module m : Env.getAllModules())
			if (SMVModule.class.isInstance(m))
				for (JTLVBDDDefine def : ((SMVModule) m).getAllDefines())
					if (!def.isInitiated())
						res.add(def);

		JTLVBDDDefine[] res_arr = new JTLVBDDDefine[res.size()];
		res.toArray(res_arr);
		return res_arr;
	}

	public int countAllDefines() {
		int res = 0;

		for (Module m : Env.getAllModules())
			if (SMVModule.class.isInstance(m))
				res += ((SMVModule) m).getAllDefines().length;

		return res;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////// making of leaf elements /////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	public StmtValueArrayWA mk_range_val(String module_name,
			Vector<String> a_range, IntStream input) throws SMVParseException {
		if (a_range.size() != 2) {
			throw new SMVParseException(
					"Could not initiate a range with other then 2 values.");
		}
		int range_start, range_end;
		try {
			range_start = Integer.parseInt(a_range.elementAt(0));
			range_end = Integer.parseInt(a_range.elementAt(1));
		} catch (NumberFormatException nfe) {
			throw new SMVParseException("Range must be an integer.", input);
		}

		// String[] vals = new String[range_end - range_start + 1];
		// for (int i = 0; i < vals.length; i++) {
		// int val = i + range_start;
		// vals[i] = "" + val;
		// }
		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			// res.arr[i] = new ValueConsStmt(this.module_pool[i], vals);
			res.arr[i] = new ValueConsRangeStmt(this.module_pool[i],
					range_start, range_end);
		}
		return res;
	}

	public class CaseElement {
		StmtValueArrayWA if_bdd;
		StmtValueArrayWA then_bdd;

		public CaseElement(StmtValueArrayWA an_if_bdd,
				StmtValueArrayWA a_then_bdd) {
			this.if_bdd = an_if_bdd;
			this.then_bdd = a_then_bdd;
		}
	}

	public StmtValueArrayWA mk_case_val(String module_name,
			Vector<CaseElement> elems, IntStream input)
			throws SMVParseException {
		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			AbstractElement[] cond_elem = new AbstractElement[elems.size()];
			AbstractElement[] then_elem = new AbstractElement[elems.size()];
			for (int j = 0; j < cond_elem.length; j++) {
				cond_elem[j] = elems.elementAt(j).if_bdd.arr[i];
				then_elem[j] = elems.elementAt(j).then_bdd.arr[i];
			}
			res.arr[i] = new OpCond(cond_elem, then_elem);

			// res_stmt = new ValueBDDStmt(this.module_pool[i], Env.FALSE());
			// for (int j = elems.size() - 1; j >= 0; j--) {
			// AbstractElement cond_elem = elems.elementAt(j).if_bdd.arr[i];
			// AbstractElement then_elem = elems.elementAt(j).then_bdd.arr[i];
			// // first implementation.
			// res_stmt = new OpCond(cond_elem, then_elem, res_stmt);
			// // second implementation.
			// // AbstractElement not_cond_elem = new OpNot(cond_elem);
			// // res_stmt = new OpAnd(new OpIff(cond_elem, then_elem), new
			// // OpIff(not_cond_elem, res_stmt));
			// }
			// res.arr[i] = res_stmt;
		}
		return res;
	}

	// public StmtValueArrayWA mk_case_val(String module_name,
	// Vector<CaseElement> elems, IntStream input)
	// throws SMVParseException {
	// StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
	// for (int i = 0; i < this.module_pool.length; i++) {
	// if (!calcStmt(i))
	// continue;
	//
	// AbstractElement res_stmt;
	// res_stmt = new ValueBDDStmt(this.module_pool[i], Env.FALSE());
	// for (int j = elems.size() - 1; j >= 0; j--) {
	// AbstractElement cond_elem = elems.elementAt(j).if_bdd.arr[i];
	// AbstractElement then_elem = elems.elementAt(j).then_bdd.arr[i];
	// res_stmt = new OpITE(cond_elem, then_elem, res_stmt);
	// }
	// res.arr[i] = res_stmt;
	// }
	// return res;
	// }

	public StmtValueArrayWA mk_constant_false_val(String module_name,
			IntStream input) throws SMVParseException {
		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			res.arr[i] = new ValueConsStrStmt(this.module_pool[i],
					new String[] { "0" });
		}
		return res;
	}

	public StmtValueArrayWA mk_constant_true_val(String module_name,
			IntStream input) throws SMVParseException {
		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			res.arr[i] = new ValueConsStrStmt(this.module_pool[i],
					new String[] { "1" });
		}
		return res;
	}

	public StmtValueArrayWA mk_constant_numeric_val(String module_name,
			String val, IntStream input) throws SMVParseException {
		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			res.arr[i] = new ValueConsStrStmt(this.module_pool[i],
					new String[] { val });
		}
		return res;
	}

	public StmtValueArrayWA mk_constant_word_val(String module_name,
			String val, IntStream input) throws SMVParseException {
		// TODO:
		throw new SMVParseException("Unimplemented Constant value.", input);
	}

	public StmtValueArrayWA mk_ref_val(String module_name, String var,
			String addr, IntStream input) throws SMVParseException {
		String look_for;
		String empty = "";
		if (addr == null | empty.equals(addr)) {
			look_for = var;
		} else if (addr.startsWith("[")) {
			look_for = var + addr;
		} else {
			// for some reason addr include a "." at its begining
			look_for = var + addr;
		}

		StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
		for (int i = 0; i < this.module_pool.length; i++) {
			if (!calcStmt(i))
				continue;
			if (this.module_pool[i].hasDefine(look_for, true)) {
				JTLVBDDDefine def = this.module_pool[i].getDefine(look_for,
						true);
				// if this define is not initiated, then we are stopping it
				// all... (and wait to the next round of defines...)
				if (!def.isInitiated()) {
					this.endDefineScope(i);
					continue;
				}
				res.arr[i] = def.getElement();
			} else if (this.module_pool[i].hasInstance(look_for, true)) {
				// do nothing... cannot do operations on such kind of ref
				res.arr[i] = null;
			} else if (this.module_pool[i].hasVar(look_for, true)) {
				JTLVBDDCouple coup = this.module_pool[i].getVar(look_for, true);
				res.arr[i] = new ValueDomStmt(this.module_pool[i], coup);
			} else if ((this.module_pool[i].hasVarArray(look_for, true))
					| (this.module_pool[i].hasInstanceArray(look_for, true))) {
				// do nothing... cannot do operations on such kind of ref
				res.arr[i] = null;
			}
			// the following to has bad performance, this is why
			// I'm doing them as a last option...
			else if (this.module_pool[i].hasValue(look_for)) {
				res.arr[i] = new ValueConsStrStmt(this.module_pool[i],
						new String[] { look_for });
			} else if ((this.module_pool[i].getHolder() != null)
					&& this.module_pool[i].getHolder().hasValue(look_for)) {
				res.arr[i] = new ValueConsStrStmt(this.module_pool[i]
						.getHolder(), new String[] { look_for });
			} else {
				SMVParseException spe = new SMVParseException(
						"Couldn't find value " + look_for + ".");
				throw spe;
				// Env.do_error(spe);
				// System.exit(1);
			}
		}
		return res;
	}

	public StmtValueArrayWA mk_self_ref_val(String module_name,
			String addr_from_self, IntStream input) throws SMVParseException {
		return mk_ref_val(module_name, "self", addr_from_self, input);
	}

	// public StmtValueArrayWA mk_set_expr_val(String module_name,
	// Vector<StmtValueArrayWA> elems, IntStream input)
	// throws SMVParseException {
	// StmtValueArrayWA res = new StmtValueArrayWA(this.module_pool.length);
	//		
	// for (StmtValueArrayWA e : elems) {
	// for (int i = 0; i < this.module_pool.length; i++) {
	// if (!calcStmt(i))
	// continue;
	// res.arr[i] = new OpUnion(res.arr[i], e.arr[i]);
	// }
	// }
	// return res;
	// }

	public void do_phase3() throws SMVParseException {
		// do nothing for now.
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////// making of operator elements /////////////////////
	// ////////////////////////////////////////////////////////////////////////

	public StmtValueArrayWA AND_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpAnd(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CONCAT_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpConcat(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_AA_val(StmtValueArrayWA first, String type,
			Vector<String> range, StmtValueArrayWA other)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureAA(first.arr[i], type, range,
					other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_ABF_val(StmtValueArrayWA first,
			Vector<String> range) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureABF(first.arr[i], range);
		}
		return first;
	}

	public StmtValueArrayWA CTL_ABG_val(StmtValueArrayWA first,
			Vector<String> range) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureABG(first.arr[i], range);
		}
		return first;
	}

	public StmtValueArrayWA CTL_AF_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureAF(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_AG_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureAG(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_AND_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLAnd(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_AX_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureAX(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EBF_val(StmtValueArrayWA first,
			Vector<String> range) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEBF(first.arr[i], range);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EBG_val(StmtValueArrayWA first,
			Vector<String> range) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEBG(first.arr[i], range);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EE_val(StmtValueArrayWA first, String type,
			Vector<String> range, StmtValueArrayWA other)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEE(first.arr[i], type, range,
					other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EF_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEF(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EG_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEG(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_EX_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLPureEX(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_IFF_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLIff(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_IMPLIES_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLImplies(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_NOT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLNot(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_OR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLOr(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_XNOR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLXnor(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA CTL_XOR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpCTLXor(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA DIVIDE_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpDivide(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA EQUAL_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpEqual(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA GE_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpGE(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA GT_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpGT(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA IFF_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpIff(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA IMPLIES_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpImplies(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA IN_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpIn(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA LE_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLE(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA LSHIFT_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLshift(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA LT_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLT(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA LTL_NOT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLNot(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA MINUS_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpMinus(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA MOD_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpMod(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA NEXT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpNext(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA NOT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpNot(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA NOTEQUAL_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpNotEqual(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_FUTURE_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLFuture(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_GLOBAL_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLGlobal(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_HISTORICAL_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLHistorical(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_NEXT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLNext(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_NOTPRECNOT_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLNotPrecNot(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_ONCE_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLOnce(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OP_PREC_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLPrec(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA OR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpOr(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA PLUS_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpPlus(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA RELEASES_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLRelease(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA RSHIFT_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpRshift(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA SINCE_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLSince(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA TIMES_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpTimes(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA TRIGGERED_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLTriggered(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA UNARY_MINUS_val(StmtValueArrayWA first)
			throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpUnaryMinus(first.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA UNION_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpUnion(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA UNTIL_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpLTLUntil(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA XNOR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpXnor(first.arr[i], other.arr[i]);
		}
		return first;
	}

	public StmtValueArrayWA XOR_val(StmtValueArrayWA first,
			StmtValueArrayWA other) throws SMVParseException {
		for (int i = 0; i < first.length; i++) {
			if (!calcStmt(i))
				continue;
			first.arr[i] = new OpXor(first.arr[i], other.arr[i]);
		}
		return first;
	}
}
