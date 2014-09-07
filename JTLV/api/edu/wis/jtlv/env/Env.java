package edu.wis.jtlv.env;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDException;
import net.sf.javabdd.BDDPairing;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.module.Module;

/**
 * The main JTLV environment Facade pattern, which supplies all API to basic
 * functionalities through delegators to the private internal managers.
 * 
 * The environment is somewhat corresponding to the java "System" environment.
 * It contains input output and error stream which in the current implementation
 * are just a reference to the System's streams.
 * 
 * The two main field which we are delegating with are: The BDDManager, which is
 * responsible of communicating with the actual BDD implementation (through
 * JavaBDD package). The BDDNamer, which is responsible of all BDD naming and
 * coupling operations (i.e. prime and unprime versions of all variables).
 * 
 * @see edu.wis.jtlv.env.JTLVBDDManager
 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing
 * 
 * @author yaniv sa'ar.
 */
public final class Env {
	
	public static final String Version = "1.0.1";
	
	private static final JTLVBDDManager bdd_manager = new JTLVBDDManager(10000,
			10000);
	private static JTLVBDDManagerPairing bdd_namer = new JTLVBDDManagerPairing();
	public static HashMap<String, Module> all_modules = new HashMap<String, Module>(
			10);

	public static JTLVBDDToString stringer = new JTLVBDDToString();

	public static Module[] getAllModules() {
		Vector<Module> res = new Vector<Module>(10);

		Set<String> all = Env.all_modules.keySet();
		for (String key : all) {
			res.add(Env.all_modules.get(key));
		}

		Module[] res_arr = new Module[res.size()];
		res.toArray(res_arr);
		return res_arr;
	}

	public static Module getModule(String full_inst_name) {
		return Env.all_modules.get(full_inst_name);
	}

	public static void reset_env() {
		bdd_manager.reset_factory();
		bdd_namer = new JTLVBDDManagerPairing();
		all_modules = new HashMap<String, Module>(10);
		stringer = new JTLVBDDToString();
	}

	public static BDDDomain create_single_bdd(int values_size) {
		return bdd_manager.create_bdd(values_size);
	}

	/**
	 * @return
	 * @see edu.wis.jtlv.env.JTLVBDDManager#create_couple_bdds()
	 */
	public static BDDDomain[] create_couple_bdds(int values_size) {
		return bdd_manager.create_couple_bdds(values_size);
	}

	/**
	 * @return
	 * @see edu.wis.jtlv.env.JTLVBDDManager#factory_name()
	 */
	public static String factory_name() {
		return bdd_manager.factory_name();
	}

	/**
	 * @return
	 * @see edu.wis.jtlv.env.JTLVBDDManager#FALSE()
	 */
	public static BDD FALSE() {
		return bdd_manager.FALSE();
	}

	/**
	 * @return
	 * @see edu.wis.jtlv.env.JTLVBDDManager#TRUE()
	 */
	public static BDD TRUE() {
		return bdd_manager.TRUE();
	}

	public static BDD ithVar(int var) {
		return bdd_manager.ithVar(var);
	}

	public static BDDVarSet emptySet() {
		return bdd_manager.emptySet();
	}

	public static BDDPairing makePair() {
		return bdd_manager.makePair();
	}

	public static int numberOfDomains() {
		return bdd_manager.numberOfDomains();
	}

	public static BDDDomain getDomain(int d) {
		return bdd_manager.getDomain(d);
	}

	public static void beQuiet() {
		bdd_manager.beQuiet();
	}

	public static String toNiceString(Module con, BDD b) {
		return bdd_manager.toNiceString(con, b);
	}

	public static String toNiceString(Module con, BDD b, String startIndent) {
		return bdd_manager.toNiceString(con, b, startIndent);
	}

	public static String toNiceSignleLineString(Module con, BDD b) {
		return bdd_manager.toNiceSignleLineString(con, b);
	}

	public void save(String filename, BDD to_save) throws IOException {
		bdd_manager.save(filename, to_save);
	}

	public BDD load(String filename) throws IOException {
		return bdd_manager.load(filename);
	}

	// /////////////////////////////////////
	// naming functionality.
	
	public static JTLVBDDCouple getVarForDomain(BDDDomain dom) {
		return bdd_namer.getVarForDomain(dom);
	}
	
	/**
	 * @param name
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#get_glob_var(java.lang.String)
	 */
	public static JTLVBDDCouple get_glob_var(String name)
			throws JTLVIllegalVariableException {
		return bdd_namer.get_glob_var(name);
	}

	/**
	 * @param names
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#get_glob_var(java.lang.String[])
	 */
	public static JTLVBDDCouple[] get_glob_var(String[] names)
			throws JTLVIllegalVariableException {
		return bdd_namer.get_glob_var(names);
	}

	/**
	 * @param preface
	 * @param name
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#get_var(java.lang.String,
	 *      java.lang.String)
	 */
	public static JTLVBDDCouple get_var(String preface, String name)
			throws JTLVIllegalVariableException {
		return bdd_namer.get_var(preface, name);
	}

	public static boolean has_var(String preface, String name) {
		return bdd_namer.has_var(preface, name);
	}

	/**
	 * @param preface
	 * @param names
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#get_var(java.lang.String,
	 *      java.lang.String[])
	 */
	public static JTLVBDDCouple[] get_var(String preface, String[] names)
			throws JTLVIllegalVariableException {
		return bdd_namer.get_var(preface, names);
	}

	public static boolean has_var(String preface, String[] names) {
		return bdd_namer.has_vars(preface, names);
	}

	/**
	 * @param name
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#new_glob_var(java.lang.String)
	 */
	public static JTLVBDDCouple new_glob_var(String name)
			throws JTLVIllegalVariableException {
		return bdd_namer.new_glob_var(name);
	}

	/**
	 * @param names
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#new_glob_var(java.lang.String[])
	 */
	public static JTLVBDDCouple[] new_glob_var(String[] names)
			throws JTLVIllegalVariableException {
		return bdd_namer.new_glob_var(names);
	}

	/**
	 * @param preface
	 * @param name
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#new_var(java.lang.String,
	 *      java.lang.String)
	 */
	public static JTLVBDDCouple new_var(String preface, String name,
			int values_size) throws JTLVIllegalVariableException {
		return bdd_namer.new_var(preface, name, values_size);
	}

	public static JTLVBDDCouple new_var(String preface, String name)
			throws JTLVIllegalVariableException {
		return new_var(preface, name, 2);
	}

	/**
	 * @param preface
	 * @param names
	 * @return
	 * @throws JTLVIllegalVariableException
	 * @see edu.wis.jtlv.env.JTLVBDDManagerPairing#new_var(java.lang.String,
	 *      java.lang.String[])
	 */
	public static JTLVBDDCouple[] new_var(String preface, String[] names,
			int[] values_size) throws JTLVIllegalVariableException {
		return bdd_namer.new_var(preface, names, values_size);
	}

	public static JTLVBDDCouple[] new_var(String preface, String[] names)
			throws JTLVIllegalVariableException {
		int[] values_size_arr = new int[names.length];
		for (int i = 0; i < values_size_arr.length; i++) {
			values_size_arr[i] = 2;
		}
		return new_var(preface, names, values_size_arr);
	}

	/**
	 * @param unp_bdd
	 * @return
	 * @throws BDDException
	 * @see edu.wis.jtlv.env.JTLVBDDManager#prime_set(net.sf.javabdd.BDD)
	 */
	public static BDD prime(BDD unp_bdd) throws BDDException {
		return bdd_namer.prime(unp_bdd);
	}

	public static BDD prime(BDD unp_bdd, JTLVBDDCouple[] couples)
			throws BDDException {
		return bdd_namer.prime(unp_bdd, couples);
	}

	/**
	 * @param p_bdd
	 * @return
	 * @throws BDDException
	 * @see edu.wis.jtlv.env.JTLVBDDManager#unprime_set(net.sf.javabdd.BDD)
	 */
	public static BDD unprime(BDD p_bdd) throws BDDException {
		return bdd_namer.unprime(p_bdd);
	}

	public static BDD unprime(BDD p_bdd, JTLVBDDCouple[] couples)
			throws BDDException {
		return bdd_namer.unprime(p_bdd, couples);
	}

	public static boolean has_prime_vars(BDD set) {
		return bdd_namer.has_prime_vars(set);
	}

	public static boolean has_unprime_vars(BDD set) {
		return bdd_namer.has_unprime_vars(set);
	}

	public static BDDVarSet get_prime_vars() {
		return bdd_namer.get_prime_vars();
	}

	public static BDDVarSet get_unprime_vars() {
		return bdd_namer.get_unprime_vars();
	}

	// a work around for a bug in the BDDVarSet
	public static BDDVarSet var_intersection(BDD a, BDD b) {
		BDDVarSet res = bdd_manager.emptySet();
		int[] a_idx = a.support().toArray();
		int[] b_idx = b.support().toArray();
		for (int i = 0; i < a_idx.length; i++)
			for (int j = 0; j < b_idx.length; j++)
				if (a_idx[i] == b_idx[j])
					res = res.id().union(a_idx[i]);
		return res;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// error handling.
	public static LinkedList<JTLVErrorListener> all_error_listeners = new LinkedList<JTLVErrorListener>();

	public static void registerErrorListener(JTLVErrorListener lis) {
		all_error_listeners.add(lis);
	}

	public static boolean removeErrorListener(JTLVErrorListener lis) {
		return all_error_listeners.remove(lis);
	}

	public static void clearErrorListener() {
		all_error_listeners.clear();
	}

	public static void activateErrorListeners(Exception e, String msg) {
		for (JTLVErrorListener listener : all_error_listeners) {
			listener.doError(e, msg);
		}
	}

	// TODO: should also add a log file with the trace.
	public static void doError(Exception e, String msg) {
		// System.err.println("ERROR: " + e.getMessage());
		System.err.println(msg);
		activateErrorListeners(e, msg);
	}
}
