package edu.wis.jtlv.env;

import java.math.BigInteger;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDPairing;
import net.sf.javabdd.BDDVarSet;
import net.sf.javabdd.BDDFactory.BDDOp;

public class JTLVBDD extends BDD {
	private BDD real_val;

	public JTLVBDD(BDD a_val) {
		this.real_val = a_val;
	}

	@Override
	public AllSatIterator allsat() {
		return real_val.allsat();
	}

	@Override
	public JTLVBDD and(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.and(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.and(that));
	}

	@Override
	public JTLVBDD andWith(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.andWith(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.andWith(that));
	}

	@Override
	public JTLVBDD apply(BDD that, BDDOp opr) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.apply(((JTLVBDD) that).real_val, opr));
		return new JTLVBDD(real_val.apply(that, opr));
	}

	@Override
	public JTLVBDD applyAll(BDD that, BDDOp opr, BDDVarSet var) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.applyAll(((JTLVBDD) that).real_val,
					opr, var));
		return new JTLVBDD(real_val.applyAll(that, opr, var));
	}

	@Override
	public JTLVBDD applyEx(BDD that, BDDOp opr, BDDVarSet var) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.applyEx(((JTLVBDD) that).real_val, opr,
					var));
		return new JTLVBDD(real_val.applyEx(that, opr, var));
	}

	@Override
	public JTLVBDD applyUni(BDD that, BDDOp opr, BDDVarSet var) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.applyUni(((JTLVBDD) that).real_val,
					opr, var));
		return new JTLVBDD(real_val.applyUni(that, opr, var));
	}

	@Override
	public JTLVBDD applyWith(BDD that, BDDOp opr) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.applyWith(((JTLVBDD) that).real_val,
					opr));
		return new JTLVBDD(real_val.applyWith(that, opr));
	}

	@Override
	public JTLVBDD biimp(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.biimp(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.biimp(that));
	}

	@Override
	public JTLVBDD biimpWith(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.biimpWith(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.biimpWith(that));
	}

	@Override
	public JTLVBDD compose(BDD g, int var) {
		if (g instanceof JTLVBDD)
			return new JTLVBDD(real_val.compose(((JTLVBDD) g).real_val, var));
		return new JTLVBDD(real_val.compose(g, var));
	}

	@Override
	public JTLVBDD constrain(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.constrain(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.constrain(that));
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof JTLVBDD)
			return real_val.equals(((JTLVBDD) o).real_val);
		return real_val.equals(o);
	}
	
	@Override
	public boolean equals(BDD that) {
		if (that instanceof JTLVBDD)
			return real_val.equals(((JTLVBDD) that).real_val);
		return real_val.equals(that);
	}

	@Override
	public JTLVBDD exist(BDDVarSet var) {
		return new JTLVBDD(real_val.exist(var));
	}

	@Override
	public JTLVBDD forAll(BDDVarSet var) {
		return new JTLVBDD(real_val.forAll(var));
	}

	@Override
	public void free() {
		real_val.free();
	}

	@Override
	public JTLVBDD fullSatOne() {
		return new JTLVBDD(real_val.fullSatOne());
	}

	@Override
	public BDDFactory getFactory() {
		return real_val.getFactory();
	}

	@Override
	public int hashCode() {
		return real_val.hashCode();
	}

	@Override
	public JTLVBDD high() {
		return new JTLVBDD(real_val.high());
	}

	@Override
	public JTLVBDD id() {
		return new JTLVBDD(real_val.id());
	}

	@Override
	public JTLVBDD imp(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.imp(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.imp(that));
	}

	@Override
	public JTLVBDD impWith(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.impWith(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.impWith(that));
	}

	@Override
	public boolean isOne() {
		return real_val.isOne();
	}

	@Override
	public boolean isUniverse() {
		return real_val.isUniverse();
	}

	@Override
	public boolean isZero() {
		return real_val.isZero();
	}

	@Override
	public JTLVBDD ite(BDD thenBDD, BDD elseBDD) {
		if ((thenBDD instanceof JTLVBDD) & (elseBDD instanceof JTLVBDD))
			return new JTLVBDD(real_val.ite(((JTLVBDD) thenBDD).real_val,
					((JTLVBDD) elseBDD).real_val));
		if (thenBDD instanceof JTLVBDD)
			return new JTLVBDD(real_val.ite(((JTLVBDD) thenBDD).real_val,
					elseBDD));
		if (elseBDD instanceof JTLVBDD)
			return new JTLVBDD(real_val.ite(thenBDD,
					((JTLVBDD) elseBDD).real_val));
		return new JTLVBDD(real_val.ite(thenBDD, elseBDD));
	}

	@Override
	public BDDIterator iterator(BDDVarSet var) {
		return real_val.iterator(var);
	}

	@Override
	public int level() {
		return real_val.level();
	}

	@Override
	public double logSatCount() {
		return real_val.logSatCount();
	}

	@Override
	public double logSatCount(BDDVarSet varset) {
		return real_val.logSatCount(varset);
	}

	@Override
	public JTLVBDD low() {
		return new JTLVBDD(real_val.low());
	}

	@Override
	public int nodeCount() {
		return real_val.nodeCount();
	}

	@Override
	public JTLVBDD not() {
		return new JTLVBDD(real_val.not());
	}

	@Override
	public JTLVBDD or(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.or(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.or(that));
	}

	@Override
	public JTLVBDD orWith(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.orWith(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.orWith(that));
	}

	@Override
	public double pathCount() {
		return real_val.pathCount();
	}

	@Override
	public void printDot() {
		real_val.printDot();
	}

	@Override
	public void printSet() {
		real_val.printSet();
	}

	@Override
	public void printSetWithDomains() {
		real_val.printSetWithDomains();
	}

	@Override
	public JTLVBDD relprod(BDD that, BDDVarSet var) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.relprod(((JTLVBDD) that).real_val, var));
		return new JTLVBDD(real_val.relprod(that, var));
	}

	@Override
	public JTLVBDD replace(BDDPairing pair) {
		return new JTLVBDD(real_val.replace(pair));
	}

	@Override
	public JTLVBDD replaceWith(BDDPairing pair) {
		return new JTLVBDD(real_val.replaceWith(pair));
	}

	@Override
	public JTLVBDD restrict(BDD var) {
		if (var instanceof JTLVBDD)
			return new JTLVBDD(real_val.restrict(((JTLVBDD) var).real_val));
		return new JTLVBDD(real_val.restrict(var));
	}

	@Override
	public JTLVBDD restrictWith(BDD var) {
		if (var instanceof JTLVBDD)
			return new JTLVBDD(real_val.restrictWith(((JTLVBDD) var).real_val));
		return new JTLVBDD(real_val.restrictWith(var));
	}

	@Override
	public double satCount() {
		return real_val.satCount();
	}

	@Override
	public double satCount(BDDVarSet varset) {
		return real_val.satCount(varset);
	}

	@Override
	public JTLVBDD satOne() {
		return new JTLVBDD(real_val.satOne());
	}

	@Override
	public JTLVBDD satOne(BDDVarSet var, boolean pol) {
		return new JTLVBDD(real_val.satOne(var, pol));
	}

	@Override
	public BigInteger[] scanAllVar() {
		return real_val.scanAllVar();
	}

	@Override
	public BigInteger scanVar(BDDDomain d) {
		return real_val.scanVar(d);
	}

	@Override
	public JTLVBDD simplify(BDDVarSet d) {
		return new JTLVBDD(real_val.simplify(d));
	}

	@Override
	public BDDVarSet support() {
		return real_val.support();
	}

	@Override
	public String toString() {
		return real_val.toString();
	}

	@Override
	public String toStringWithDomains() {
		return real_val.toStringWithDomains();
	}

	@Override
	public String toStringWithDomains(BDDToString ts) {
		return real_val.toStringWithDomains(ts);
	}

	@Override
	public BDDVarSet toVarSet() {
		return real_val.toVarSet();
	}

	@Override
	public JTLVBDD unique(BDDVarSet var) {
		return new JTLVBDD(real_val.unique(var));
	}

	@Override
	public int var() {
		return real_val.var();
	}

	@Override
	public int[] varProfile() {
		return real_val.varProfile();
	}

	@Override
	public JTLVBDD veccompose(BDDPairing pair) {
		return new JTLVBDD(real_val.veccompose(pair));
	}

	@Override
	public JTLVBDD xor(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.xor(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.xor(that));
	}

	@Override
	public JTLVBDD xorWith(BDD that) {
		if (that instanceof JTLVBDD)
			return new JTLVBDD(real_val.xorWith(((JTLVBDD) that).real_val));
		return new JTLVBDD(real_val.xorWith(that));
	}

}
