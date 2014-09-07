package edu.wis.jtlv.bench.mtmc;


import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.ModuleBDDField;

public class Util {
	// computing one step successors
	public static BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD prime_to = from.and(trans).exist(unprimeVars);
		return Env.unprime(prime_to);
	}

	// computing all successors
	// !! I should probably change the while-loop to a fixpoint
	public static BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		} while (!oldSucc.equals(newSucc));
		return newSucc;
	}

	// ADDED!!! /////////////////////////////////////////////
	public static BDD newSuccWithLoopCond(int max_iter, BDD from, BDD trans,
			BDDVarSet unprimeVars) {
		int counter = 0;
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
			counter++;
		} while (!oldSucc.equals(newSucc) & (counter != max_iter));
		return newSucc;
	}

	// //////////////////////////////////////////////////////

	public static BDD newAllSuccNotIncl(BDD from, BDD trans,
			BDDVarSet unprimeVars) {
		return newSuccNotInclWithLoopCond(-1, from, trans, unprimeVars);
	}

	public static BDD newSuccNotInclWithLoopCond(int max_iter, BDD from,
			BDD trans, BDDVarSet unprimeVars) {
		// /////////////////////////
		// int counter = 0;
		// BDD oldSucc = from;
		// BDD newSucc = from;
		//
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
		// from.not());
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		int counter = 0;
		BDD deltaSucc = newSucc(from, trans, unprimeVars).and(from.not());
		BDD newSucc = deltaSucc;
		BDD oldSucc = deltaSucc;

		do {
			oldSucc = newSucc;
			deltaSucc = newSucc(deltaSucc, trans, unprimeVars).and(
					oldSucc.not()).and(from.not());
			newSucc = oldSucc.or(deltaSucc);
			counter++;
		} while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		return newSucc;
		// /////////////////////////
		// int counter = 0;
		// trans = trans.and(Env.prime(from).not());
		// BDD oldSucc, newSucc = newSucc(from, trans, unprimeVars);
		//		
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		// BDD oldSucc = from;
		// BDD newSucc = from;
		// trans = trans.and(Env.prime(from).not());
		//		
		// boolean first_time = true;
		// do {
		// oldSucc = newSucc;
		// newSucc = newSucc(oldSucc, trans, unprimeVars);
		// if (first_time) { // only newSucc
		// first_time = false;
		// } else { // otherwise, include the previous
		// newSucc = oldSucc.or(newSucc);
		// }
		// } while (!oldSucc.equals(newSucc));
		// return newSucc;
		// /////////////////////////
	}

	public static BDD newSuccNotInclWithTimeCond(long max_time, BDD from,
			BDD trans, BDDVarSet unprimeVars) {
		long start = System.currentTimeMillis();
		long time_from_start = start;
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
					from.not());
			time_from_start = System.currentTimeMillis() - start;
		} while ((!oldSucc.equals(newSucc)) & (time_from_start < max_time));
		return newSucc;
	}

	public static void printTimedMes(String header, long rel) {
		System.out.println(header + ": " + (System.currentTimeMillis() - rel));
	}

	// Added by Ariel Jan 16
	public static BDDVarSet addVarsToQant(BDDVarSet varSet,
			ModuleBDDField field_1) {
		varSet = varSet.union(field_1.getDomain().set());
		varSet = varSet.union(field_1.prime().getDomain().set());
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime, BDDVarSet field_2, BDDVarSet field_2_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		varSet = varSet.union(field_2);
		varSet = varSet.union(field_2_prime);
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime, BDDVarSet field_2,
			BDDVarSet field_2_prime, BDDVarSet field_3, BDDVarSet field_3_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		varSet = varSet.union(field_2);
		varSet = varSet.union(field_2_prime);
		varSet = varSet.union(field_3);
		varSet = varSet.union(field_3_prime);
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime, BDDVarSet field_2,
			BDDVarSet field_2_prime, BDDVarSet field_3,
			BDDVarSet field_3_prime, BDDVarSet field_4, BDDVarSet field_4_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		varSet = varSet.union(field_2);
		varSet = varSet.union(field_2_prime);
		varSet = varSet.union(field_3);
		varSet = varSet.union(field_3_prime);
		varSet = varSet.union(field_4);
		varSet = varSet.union(field_4_prime);
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime, BDDVarSet field_2,
			BDDVarSet field_2_prime, BDDVarSet field_3,
			BDDVarSet field_3_prime, BDDVarSet field_4,
			BDDVarSet field_4_prime, BDDVarSet field_5, BDDVarSet field_5_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		varSet = varSet.union(field_2);
		varSet = varSet.union(field_2_prime);
		varSet = varSet.union(field_3);
		varSet = varSet.union(field_3_prime);
		varSet = varSet.union(field_4);
		varSet = varSet.union(field_4_prime);
		varSet = varSet.union(field_5);
		varSet = varSet.union(field_5_prime);
		return varSet;
	}

	public static BDDVarSet addVarsToQant(BDDVarSet varSet, BDDVarSet field_1,
			BDDVarSet field_1_prime, BDDVarSet field_2,
			BDDVarSet field_2_prime, BDDVarSet field_3,
			BDDVarSet field_3_prime, BDDVarSet field_4,
			BDDVarSet field_4_prime, BDDVarSet field_5,
			BDDVarSet field_5_prime, BDDVarSet field_6, BDDVarSet field_6_prime) {
		varSet = varSet.union(field_1);
		varSet = varSet.union(field_1_prime);
		varSet = varSet.union(field_2);
		varSet = varSet.union(field_2_prime);
		varSet = varSet.union(field_3);
		varSet = varSet.union(field_3_prime);
		varSet = varSet.union(field_4);
		varSet = varSet.union(field_4_prime);
		varSet = varSet.union(field_5);
		varSet = varSet.union(field_5_prime);
		varSet = varSet.union(field_6);
		varSet = varSet.union(field_6_prime);
		return varSet;
	}

	public static BDDVarSet varsToQant() {
		BDDVarSet varSet = Env.getEmptySet();
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1,
			ModuleBDDField field_2) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		varSet = addVarsToQant(varSet, field_2);
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1,
			ModuleBDDField field_2, ModuleBDDField field_3) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		varSet = addVarsToQant(varSet, field_2);
		varSet = addVarsToQant(varSet, field_3);
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1,
			ModuleBDDField field_2, ModuleBDDField field_3,
			ModuleBDDField field_4) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		varSet = addVarsToQant(varSet, field_2);
		varSet = addVarsToQant(varSet, field_3);
		varSet = addVarsToQant(varSet, field_4);
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1,
			ModuleBDDField field_2, ModuleBDDField field_3,
			ModuleBDDField field_4, ModuleBDDField field_5) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		varSet = addVarsToQant(varSet, field_2);
		varSet = addVarsToQant(varSet, field_3);
		varSet = addVarsToQant(varSet, field_4);
		varSet = addVarsToQant(varSet, field_5);
		return varSet;
	}

	public static BDDVarSet varsToQant(ModuleBDDField field_1,
			ModuleBDDField field_2, ModuleBDDField field_3,
			ModuleBDDField field_4, ModuleBDDField field_5,
			ModuleBDDField field_6) {
		BDDVarSet varSet = Env.getEmptySet();
		varSet = addVarsToQant(varSet, field_1);
		varSet = addVarsToQant(varSet, field_2);
		varSet = addVarsToQant(varSet, field_3);
		varSet = addVarsToQant(varSet, field_4);
		varSet = addVarsToQant(varSet, field_5);
		varSet = addVarsToQant(varSet, field_6);
		return varSet;
	}

	public static BDD idleVars() {
		return Env.copyBDDIntoBaseManager(Env.TRUE());
	}

	public static BDD idleVars(ModuleBDDField field_1) {
		return field_1.getDomain().buildEquals(field_1.prime().getDomain());
	}

	public static BDD idleVars(ModuleBDDField field_1, ModuleBDDField field_2) {
		BDD temp = idleVars(field_1);
		temp = temp.and(idleVars(field_2));
		return temp;
	}

	public static BDD idleVars(ModuleBDDField field_1, ModuleBDDField field_2,
			ModuleBDDField field_3) {
		BDD temp = idleVars(field_1);
		temp = temp.and(idleVars(field_2));
		temp = temp.and(idleVars(field_3));
		return temp;
	}

	public static BDD idleVars(ModuleBDDField field_1, ModuleBDDField field_2,
			ModuleBDDField field_3, ModuleBDDField field_4) {
		BDD temp = idleVars(field_1);
		temp = temp.and(idleVars(field_2));
		temp = temp.and(idleVars(field_3));
		temp = temp.and(idleVars(field_4));
		return temp;
	}

	public static BDD idleVars(ModuleBDDField field_1, ModuleBDDField field_2,
			ModuleBDDField field_3, ModuleBDDField field_4,
			ModuleBDDField field_5) {
		BDD temp = idleVars(field_1);
		temp = temp.and(idleVars(field_2));
		temp = temp.and(idleVars(field_3));
		temp = temp.and(idleVars(field_4));
		temp = temp.and(idleVars(field_5));
		return temp;
	}

	public static BDD idleVars(ModuleBDDField field_1, ModuleBDDField field_2,
			ModuleBDDField field_3, ModuleBDDField field_4,
			ModuleBDDField field_5, ModuleBDDField field_6) {
		BDD temp = idleVars(field_1);
		temp = temp.and(idleVars(field_2));
		temp = temp.and(idleVars(field_3));
		temp = temp.and(idleVars(field_4));
		temp = temp.and(idleVars(field_5));
		temp = temp.and(idleVars(field_6));
		return temp;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	public static BDD idleVars(BDDFactory f, ModuleBDDField[] fields) {
		BDD temp = f.one();
		for (int i = 0; i < fields.length; i++)
			temp = temp.and(idleVars(f, fields[i]));
		return temp;
	}

	public static BDD idleVars(BDDFactory f, ModuleBDDField field) {
		BDDDomain dp = f.getDomain(field.getDomain().getIndex());
		BDDDomain dup = f.getDomain(field.getOtherDomain().getIndex());
		BDD id = dp.buildEquals(dup);
		return id;
	}

	public static BDDVarSet varsToQant(BDDFactory f, ModuleBDDField[] fields) {
		BDDVarSet temp = f.emptySet();
		for (int i = 0; i < fields.length; i++)
			temp = temp.union(varsToQant(f, fields[i]));
		return temp;
	}

	public static BDDVarSet varsToQant(BDDFactory f, ModuleBDDField field) {
		return f.getDomain(field.getDomain().getIndex()).set();
	}
}
