package edu.wis.jtlv.env;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDException;
import edu.wis.jtlv.env.core.smv.SMVParseException;
import edu.wis.jtlv.env.core.smv.eval.AbstractElement;
import edu.wis.jtlv.env.core.smv.eval.StmtDefineOperator;
import edu.wis.jtlv.env.core.smv.eval.ValueStmt;

public class JTLVBDDDefine extends JTLVBDDCouple {
	protected StmtDefineOperator main_stmt;

	public JTLVBDDDefine(String a_path, String name) {
		this.path = a_path;
		this.name = name;
		this.is_prime = false;
		this.main_stmt = null;
	}

	public boolean isInitiated() {
		return this.main_stmt != null;
	}

	public void attachValue(StmtDefineOperator stmt) throws SMVParseException {
		this.main_stmt = stmt;
	}

	private ValueStmt evaluated_bdd = null;

	@Override
	public BDD toBDD() {
		try {
			if (this.evaluated_bdd == null)
				this.evaluated_bdd = this.main_stmt.eval_stmt();
			return this.evaluated_bdd.toBDD();
		} catch (SMVParseException e) {
			// I have no choice.
			Env.doError(e, e.toString());
			return Env.FALSE();
		}
	}

	public Integer[] toInt() {
		try {
			if (this.evaluated_bdd == null)
				this.evaluated_bdd = this.main_stmt.eval_stmt();
			return this.evaluated_bdd.toValueIntStmt().getAllValues();
		} catch (SMVParseException e) {
			// I have no choice.
			Env.doError(e, e.toString());
			return new Integer[] {};
		}
	}
	
	public AbstractElement getElement() {
		return this.main_stmt;
	}

	@Override
	public BDDDomain getDomain() {
		throw new BDDException("Define has no domain.");
	}

	// all runtime errors
	public JTLVBDDCouple other() throws BDDException {
		throw new BDDException("Cannot get other instance for define with"
				+ " prime and unprime variables.");
	}

	public JTLVBDDCouple prime() throws BDDException {
		throw new BDDException("Cannot prime define with prime variables.");
	}

	public JTLVBDDCouple unprime() throws BDDException {
		throw new BDDException("Cannot unprime define with unprime variables.");
	}
}
