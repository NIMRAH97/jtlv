/**
 * 
 */
package edu.wis.jtlv.env;

import java.util.Vector;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDException;
import net.sf.javabdd.BDDPairing;
import net.sf.javabdd.BDDVarSet;

/**
 * @author yaniv
 * 
 */
public final class JTLVBDDManagerPairing {
	// //////////////////////////////////
	// the current implementation is to order the bdd as unprime variable
	// first, and the immediately followed variable is its primed version.
	private Vector<JTLVBDDCouple> all_couples;

	public JTLVBDDManagerPairing() {
		all_couples = new Vector<JTLVBDDCouple>(100);
	}

	/**
	 * The main implementation for creating a variable.
	 * 
	 * @param preface
	 *            module name.
	 * @param name
	 *            variable name, can also be in prime notation (e.g. x').
	 * @return the couple bdd created for this name.
	 * @throws JTLVIllegalVariableException
	 *             if the name already exists.
	 */
	public JTLVBDDCouple new_var(String preface, String name_with_tick,
			int values_size) throws JTLVIllegalVariableException {
		// preparing a proper name.
		if (is_prime_name(name_with_tick)) {
			throw new JTLVIllegalVariableException(
					"Cannot declare a variable with \'.");
		}
		String name = this.to_simple_name(name_with_tick);

		// preparing the actual bdds.
		if (search_couple(preface, this.to_simple_name(name)) != null) {
			throw new JTLVIllegalVariableException("variable \"" + "<"
					+ preface + ">." + name + "\" already exists.");
		}

		BDDDomain[] couple = Env.create_couple_bdds(values_size);
		// making the pair
		JTLVBDDCouple new_pair = new JTLVBDDCouple(couple[0], couple[1],
				preface, name);

		all_couples.add(new_pair);

		// returning the relevant couple.
		return new_pair;
	}

	/**
	 * a loop of variable list creation.
	 * 
	 * @param preface
	 *            module name.
	 * @param names
	 *            list of names.
	 * @return list of couples created, corresponding to the list of names
	 *         given.
	 * @throws JTLVIllegalVariableException
	 *             if one of the names already exists.
	 * @see edu.wis.jtlv.env.JTLVBDDManager#new_var(String, String)
	 * @see edu.wis.jtlv.tb.JTLVBDDCouple
	 */
	public JTLVBDDCouple[] new_var(String preface, String[] names,
			int[] values_size) throws JTLVIllegalVariableException {
		JTLVBDDCouple[] res = new JTLVBDDCouple[names.length];
		for (int i = 0; i < names.length; i++) {
			res[i] = new_var(preface, names[i], values_size[i]);
		}
		return res;
	}

	public JTLVBDDCouple new_glob_var(String name, int values_size)
			throws JTLVIllegalVariableException {
		return new_var("global", name, values_size);
	}

	public JTLVBDDCouple new_glob_var(String name)
			throws JTLVIllegalVariableException {
		return new_var("global", name, 2);
	}

	public JTLVBDDCouple[] new_glob_var(String[] names, int[] values_size)
			throws JTLVIllegalVariableException {
		return new_var("global", names, values_size);
	}

	public JTLVBDDCouple[] new_glob_var(String[] names)
			throws JTLVIllegalVariableException {
		int[] values_size_arr = new int[names.length];
		for (int i = 0; i < values_size_arr.length; i++) {
			values_size_arr[i] = 2;
		}
		return new_var("global", names, values_size_arr);
	}

	public JTLVBDDCouple getVarForDomain(BDDDomain dom) {
		for (JTLVBDDCouple coup : this.all_couples) {
			if (dom.getIndex() == coup.getDomain().getIndex()) 
				return coup;
			if (dom.getIndex() == coup.other().getDomain().getIndex()) 
				return coup.other();
		}
		return null;
	}
	
	// ////////////////////////////////////
	// getter without creation
	private JTLVBDDCouple search_couple(String preface, String name) {
		JTLVBDDCouple[] iter = new JTLVBDDCouple[this.all_couples.size()];
		all_couples.toArray(iter);
		for (int i = 0; i < iter.length; i++) {
			if ((iter[i].get_path().equals(preface))
					& (iter[i].get_name().equals(name))) {
				return iter[i];
			}
		}
		return null;
	}

	public JTLVBDDCouple get_var(String preface, String name)
			throws JTLVIllegalVariableException {
		JTLVBDDCouple res = search_couple(preface, this.to_simple_name(name));
		if (res == null)
			throw new JTLVIllegalVariableException(
					"Couldn't find variable named <" + preface + ">." + name);
		return this.is_prime_name(name) ? res.prime() : res;
	}

	public boolean has_var(String preface, String name) {
		boolean res = false;
		try {
			res = (null != search_couple(preface, this.to_simple_name(name)));
		} catch (JTLVIllegalVariableException e) {
			// not exists
		}
		return res;
	}

	public JTLVBDDCouple[] get_var(String preface, String[] names)
			throws JTLVIllegalVariableException {
		JTLVBDDCouple[] res = new JTLVBDDCouple[names.length];
		for (int i = 0; i < names.length; i++) {
			res[i] = get_var(preface, names[i]);
			if (res[i] == null)
				throw new JTLVIllegalVariableException(
						"ERROR: Couldn't find variable named <" + preface
								+ ">." + names[i]);
		}
		return res;
	}

	public boolean has_vars(String preface, String[] names) {
		for (int i = 0; i < names.length; i++) {
			if (!has_var(preface, names[i]))
				return false;
		}
		return true;
	}

	public JTLVBDDCouple get_glob_var(String name)
			throws JTLVIllegalVariableException {
		return get_var("global", name);
	}

	public JTLVBDDCouple[] get_glob_var(String[] names)
			throws JTLVIllegalVariableException {
		return get_var("global", names);
	}

	// ////////////////////////////////////
	// until here all was single var BDD manipulations.
	// here on there are general BDD operations.

	private boolean is_prime_name(String name)
			throws JTLVIllegalVariableException {
		if (name.indexOf("'") != name.lastIndexOf("'")) {
			throw new JTLVIllegalVariableException(
					"ERROR: cannot name a variable: \"" + name + "\"");
		}
		return (name.indexOf("'") != -1);
	}

	private String to_simple_name(String name)
			throws JTLVIllegalVariableException {
		return is_prime_name(name) ? name.substring(0, name.indexOf("'"))
				: name;
	}

	// /////////////////////// pairings ///////////////////////
	private JTLVBDDCouple[] all_couples_arr() {
		JTLVBDDCouple[] res = new JTLVBDDCouple[all_couples.size()];
		all_couples.toArray(res);
		return res;
	}

	public BDD prime(BDD unp_bdd, JTLVBDDCouple[] pairs) throws BDDException {
		if (has_prime_vars(unp_bdd, pairs)) {
			throw new BDDException("ERROR: Cannot prime primed variables: \n\t"
					+ get_prime_vars(pairs));
		}
		BDD res = null;
		if (Env.factory_name() == "BuDDyFactory") { // workaround due to some
			// kind of a bug in pairing with buddy
			res = unp_bdd.and(this.make_buddy_pairs(pairs));
			res = res.exist(get_unprime_vars(pairs));
		} else {
			res = unp_bdd.replace(this.make_pairs(pairs));
		}
		return res;
	}

	public BDD prime(BDD unp_bdd) throws BDDException {
		return prime(unp_bdd, all_couples_arr());
	}

	public BDD unprime(BDD p_bdd, JTLVBDDCouple[] pairs) throws BDDException {
		if (has_unprime_vars(p_bdd, pairs)) {
			throw new BDDException(
					"ERROR: Cannot unprime unprimed variables: \n\t"
							+ get_unprime_vars(pairs));
		}
		BDD res = null;
		if (Env.factory_name() == "BuDDyFactory") { // workaround due to some
			// kind of a bug in pairing with buddy
			res = p_bdd.and(this.make_buddy_pairs(pairs));
			res = res.exist(get_prime_vars(pairs));
		} else {
			res = p_bdd.replace(this.make_pairs(pairs));
		}
		return res;
	}

	public BDD unprime(BDD p_bdd) throws BDDException {
		return unprime(p_bdd, all_couples_arr());
	}

	public boolean has_prime_vars(BDD set, JTLVBDDCouple[] pairs) {
		BDDVarSet all_vars = set.support();
		for (int j = 0; j < pairs.length; j++) {
			BDDVarSet p_var = pairs[j].is_prime() ? pairs[j].toBDD().support()
					: pairs[j].primeBDD().support();
			// there is a bug with the intersect
			BDDVarSet un = all_vars.union(p_var);
			if (un.size() != (p_var.size() + all_vars.size()))
				return true;
		}
		return false;
	}

	public boolean has_prime_vars(BDD set) {
		return has_prime_vars(set, all_couples_arr());
	}

	public BDDVarSet get_prime_vars(JTLVBDDCouple[] pairs) {
		BDDVarSet res = Env.emptySet();
		for (int j = 0; j < pairs.length; j++) {
			BDDVarSet p_var = pairs[j].is_prime() ? pairs[j].toBDD().support()
					: pairs[j].primeBDD().support();
			res = res.id().union(p_var);
		}
		return res;
	}

	public BDDVarSet get_prime_vars() {
		return get_prime_vars(all_couples_arr());
	}

	public boolean has_unprime_vars(BDD set, JTLVBDDCouple[] pairs) {
		BDDVarSet all_vars = set.support();
		for (int j = 0; j < pairs.length; j++) {
			BDDVarSet up_var = pairs[j].is_prime() ? pairs[j].unprimeBDD()
					.support() : pairs[j].toBDD().support();
			BDDVarSet un = all_vars.id().union(up_var);
			if (un.size() != (up_var.size() + all_vars.size()))
				return true;
		}
		return false;
	}

	public boolean has_unprime_vars(BDD set) {
		return has_unprime_vars(set, all_couples_arr());
	}

	public BDDVarSet get_unprime_vars(JTLVBDDCouple[] pairs) {
		BDDVarSet res = Env.emptySet();
		for (int j = 0; j < pairs.length; j++) {
			BDDVarSet up_var = pairs[j].is_prime() ? pairs[j].unprimeBDD()
					.support() : pairs[j].toBDD().support();
			res = res.id().union(up_var);
		}
		return res;
	}

	public BDDVarSet get_unprime_vars() {
		return get_unprime_vars(all_couples_arr());
	}

	private BDD make_buddy_pairs(JTLVBDDCouple[] all_couples) {
		BDD res = Env.TRUE();
		for (int i = 0; i < all_couples.length; i++) {
			res = res.id().and(
					all_couples[i].toBDD().biimp(all_couples[i].otherBDD()));
		}
		return res;
	}

	private BDDPairing make_pairs(JTLVBDDCouple[] all_couples) {
		BDDPairing res = Env.makePair();
		res.reset();
		for (int i = 0; i < all_couples.length; i++) {
			res.set(all_couples[i].toBDD().var(), all_couples[i].otherBDD()
					.var());
			res.set(all_couples[i].otherBDD().var(), all_couples[i].toBDD()
					.var());
		}
		return res;
	}

}
