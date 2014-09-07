package edu.wis.jtlv.env;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import edu.wis.jtlv.env.module.Module;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDPairing;
import net.sf.javabdd.BDDVarSet;

//import net.sf.javabdd.BuDDyFactory;

/**
 * BDDManager is responsible of communicating with all JavaBDD functionalities.
 * It only holder is the Env, and all operation should be done through the Env.
 * 
 * @see edu.wis.jtlv.env.Env
 * 
 * @author yaniv sa'ar.
 */
public final class JTLVBDDManager {
	private static BDDFactory factory;
	private String factory_name;
	private String factory_package_name;// = "net.sf.javabdd." + factory_name;
	private int nodenum;// = 1000;
	private int cachesize;// = 1000;

	// private int curr_num_vars;// = 0;

	public JTLVBDDManager(int nodenum, int cachesize) {
		init64();
		// this.curr_num_vars = 0;
		this.nodenum = nodenum;
		this.cachesize = cachesize;
		tear_down();
		setup();
	}

	private void init64() {
		if (System.getProperty("java.vm.name").contains("64")) {
			System.setProperty("buddylib", "buddy64");
			// for some reason he is not using the property for CAL and CUDD.
			// System.setProperty("cuddlib", "cudd64");
			// System.setProperty("callib", "cal64");
		}
	}

	public JTLVBDDManager() {
		this(10000, 10000);
	}

	public static final String getProperty(String key, String def) {
		try {
			return System.getProperty(key, def);
		} catch (AccessControlException _) {
			return def;
		}
	}

	// ////////////////////////////
	// factory methods...
	public void reset_factory() {
		this.tear_down();
		this.setup();
	}

	private void setup() {
		this.factory_name = prompt2name();
		this.factory_package_name = "net.sf.javabdd." + factory_name;
		// System.out.println("Initiating factory : " + this.factory_name);
		init_factories();
	}

	public void beQuiet() {
		try {
			Method mg = JTLVBDDManager.class.getMethod("doQuietGcCallback",
					(Class[]) null);
			JTLVBDDManager.factory.registerGCCallback(this, mg);
			Method mr = JTLVBDDManager.class.getMethod("doQuietResizeCallback",
					(Class[]) null);
			JTLVBDDManager.factory.registerResizeCallback(this, mr);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static void doQuietGcCallback() {
	}

	public static void doQuietResizeCallback() {
	}

	private void init_factories() {
		assert (factory == null);
		// ////////////////////////
		// a work around for buddy since he cannot be reset
		// more then couple of times. see destroy_factory()
		// if (this.factory_name.equals("BuDDyFactory")) {
		// if (buddy_factory != null) {
		// factory = buddy_factory;
		// // BDDFactory.init(nodenum, cachesize);
		// return;
		// }
		// }
		// ////////////////////////
		String prev_user_dir = System.getProperty("user.dir", ".");
		System.setProperty("user.dir", prev_user_dir
				+ getProperty("file.separator", "/") + "JTLV_DLIB");
		try {
			factory = BDDFactory.init(BDDFactory.getProperty("bdd", "java"),
					nodenum, cachesize);
			// Class<?> c = Class.forName(factory_package_name);
			// Method m = c
			// .getMethod("init", new Class[] { int.class, int.class });
			// factory = (BDDFactory) m.invoke(null, new Object[] {
			// new Integer(nodenum), new Integer(cachesize) });
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException)
				t = ((InvocationTargetException) t).getTargetException();
			System.out.println("Failed initializing " + factory_package_name
					+ ": " + t);
		}
		System.setProperty("user.dir", prev_user_dir);
	}

	private String prompt2name() {
		// default is BuDDy...
		String bddpackage = BDDFactory.getProperty("bdd", "java");
		if (bddpackage.equals("buddy"))
			return "BuDDyFactory";
		if (bddpackage.equals("cudd"))
			return "CUDDFactory";
		if (bddpackage.equals("cal"))
			return "CALFactory";
		if (bddpackage.equals("j") || bddpackage.equals("java"))
			return "JFactory";
		if (bddpackage.equals("u"))
			return "UberMicroFactory";
		if (bddpackage.equals("micro"))
			return "MicroFactory";
		if (bddpackage.equals("jdd"))
			return "JDDFactory";
		if (bddpackage.equals("test"))
			return "TestBDDFactory";
		if (bddpackage.equals("typed"))
			return "TypedBDDFactory";
		// if (bddpackage.equals("zdd")) {
		// BDDFactory bdd = JFactory.init(nodenum, cachesize);
		// ((JFactory)bdd).ZDD = true;
		// return bdd;
		// }
		return "BuDDyFactory";
	}

	private void tear_down() {
		destroy_factory();
	}

	private void destroy_factory() {
		if (factory == null)
			return;
		// // ////////////////////////
		// // a work around for buddy since he cannot be reset
		// // more then couple of times. see init_factories()
		// if (BuDDyFactory.class.isInstance(factory)) {
		// // factory.clearVarBlocks();
		// // factory.clearAllDomains();
		// // factory.clearError();
		// factory.reset();
		// // factory.setVarNum(0);
		// // factory.done();
		// // factory = BDDFactory.init(cachesize, cachesize);
		// buddy_factory = (BuDDyFactory) JTLVBDDManager.factory;
		// factory = null;
		// return;
		// }
		// // ////////////////////////
		factory.done();
		factory = null;
	}

	// /////////////////////////////////
	public BDD TRUE() {
		return factory.one();
	}

	public BDD FALSE() {
		return factory.zero();
	}

	// /////////////////////////////////
	public BDDDomain[] create_couple_bdds(int values_size) {
		BDDDomain[] res = new BDDDomain[2];
		res[0] = factory.extDomain(values_size);
		res[1] = factory.extDomain(values_size);
		return res;
	}

	public BDDDomain create_bdd(int values_size) {
		return factory.extDomain(values_size);
	}

	// ///////////////////////////////////////////
	// general util.
	public int numberOfDomains() {
		return factory.numberOfDomains();
	}

	public BDDDomain getDomain(int d) {
		return factory.getDomain(d);
	}

	public BDDVarSet emptySet() {
		return factory.emptySet();
	}

	public BDDPairing makePair() {
		return factory.makePair();
	}

	/**
	 * @return the factory_name
	 */
	public String factory_name() {
		return this.factory_name;
	}

	/**
	 * @param var
	 * @return
	 * @see net.sf.javabdd.BDDFactory#ithVar(int)
	 */
	public BDD ithVar(int var) {
		return factory.ithVar(var);
	}

	public void save(String filename, BDD to_save) throws IOException {
		factory.save(filename, to_save);
	}

	public BDD load(String filename) throws IOException {
		return factory.load(filename);
	}

	public String toNiceString(Module con, BDD b) {
		return toNiceStringHelper(con, b, true, "");
	}

	public String toNiceString(Module con, BDD b, String startIndent) {
		return toNiceStringHelper(con, b, true, startIndent);
	}

	public String toNiceSignleLineString(Module con, BDD b) {
		return toNiceStringHelper(con, b, false, "");
	}

	private String toNiceStringHelper(Module con, BDD b, boolean withIndent,
			String currIndent) {
		String res = "";
		String pre = ((withIndent) ? currIndent : ""); // prefix indent...
		String suf = ((withIndent) ? "\n" : " "); // suffix line break...
		BDDDomain[] all_doms = b.support().getDomains();

		// if there is no domain in the BDD, i.e. FALSE, or TRUE.
		if (all_doms.length == 0)
			return pre + (b.isUniverse() ? "TRUE" : "FALSE");
		// ////////////////////////////////////////////////////////////////////

		// all relevant domains...
		sortArr(all_doms, new BDDDomainComparator());
		BDDDomain curDom = all_doms[0];
		String fName = getFieldName(con, curDom);
		BDDVarSet other_var_set = factory.emptySet();
		for (int i = 1; i < all_doms.length; i++)
			other_var_set = other_var_set.union(all_doms[i].set());
		BigInteger[] allVals = curDom.getVarIndices(b.exist(other_var_set),
				curDom.size().intValue());
		// for some reason I sometime get values not in the range...
		allVals = getValuesBelow(allVals, curDom.size());
		sortArr(allVals, new BigIntegerComparator());
		// ////////////////////////////////////////////////////////////////////

		// now we are starting to work on this domain....

		// if this is the last domain, then shortening it...
		if (all_doms.length == 1) {
			res = pre + getValueStr(fName, curDom, allVals);
			// bdd2string.put(new IndentBDD(currIndent, b), res);
			return res;
		}

		// first checking if I can do a simple grouping of everything...
		if (valsCanBeGrouped(b, curDom, allVals)) {
			BDD rest = b.and(curDom.ithVar(allVals[0])).exist(curDom.set());
			res += pre + "(" + getValueStr(fName, curDom, allVals) + " and";

			// making new line for the next indent in line...
			boolean doIndent = (all_doms.length > 2);
			res += doIndent ? suf : " ";
			res += toNiceStringHelper(con, rest, doIndent & withIndent,
					currIndent);
			res += ")";
			return res;
		}

		// otherwise, enumarating everything.
		// first conjuncting out all not possible values...
		BigInteger[] notVals = opVals(allVals, curDom.size().intValue());
		if (notVals.length > 0)
			res += pre + getNotValueStr(fName, curDom, notVals) + " and" + suf;

		// then conjuncting in all possible values...
		boolean isFirst = true;
		for (BigInteger val : allVals) {
			if (isUniverseValue(curDom, val, b)) {
				// for now do nothing (since we are conjuncting the notVals)
				continue;
			}
			// else
			BDD rest = b.and(curDom.ithVar(val)).exist(curDom.set());
			res += (isFirst ? "" : " and" + suf) + pre;
			res += "(" + fName + "=" + Env.stringer.elementName(curDom, val);
			res += " -> ";

			// making new line for the next indent in line...
			boolean doIndent = (all_doms.length > 2);
			res += doIndent ? suf : "";
			res += toNiceStringHelper(con, rest, doIndent & withIndent,
					currIndent + "\t");
			res += ")";
			isFirst = false;
		}
		return res;
	}

	private boolean valsCanBeGrouped(BDD b, BDDDomain dom, BigInteger[] allVals) {
		BDD toCompare = b.and(dom.ithVar(allVals[0])).exist(dom.set());
		for (int i = 1; i < allVals.length; i++) {
			if (!b.and(dom.ithVar(allVals[i])).exist(dom.set())
					.biimp(toCompare).isUniverse()) {
				return false;
			}
		}
		return true;
	}

	private BigInteger[] getValuesBelow(BigInteger[] allVals, BigInteger maxVal) {
		Vector<BigInteger> res_vec = new Vector<BigInteger>(allVals.length);
		for (BigInteger val : allVals) {
			if (!val.max(maxVal).equals(val))
				res_vec.add(val);
		}
		BigInteger[] res = new BigInteger[res_vec.size()];
		res_vec.toArray(res);
		return res;
	}

	private String getFieldName(Module con, BDDDomain dom) {
		JTLVBDDCouple coup = Env.getVarForDomain(dom);
		if ((con != null) && (coup.get_path().equals(con.getFullInstName())))
			return coup.get_name();
		else
			return coup.get_path() + "." + coup.get_name();
	}

	// all_vals is always sorted...
	private String getValueStr(String field, BDDDomain dom, BigInteger[] allVals) {
		if (allVals.length > (dom.size().intValue() / 2))
			return getNotValueStr(field, dom, opVals(allVals, dom.size()
					.intValue()));
		if (allVals.length == 1)
			return field + "=" + Env.stringer.elementName(dom, allVals[0]);
		String res = field + "=" + "{";
		for (int i = 0; i < allVals.length; i++) {
			res += (i == 0) ? "" : ", ";
			int until = rangeUntil(allVals, i);
			if (until != -1)
				res += Env.stringer.elementNames(dom, allVals[i],
						allVals[until]);
			else
				res += Env.stringer.elementName(dom, allVals[i]);
		}
		res += "}";
		return res;
	}

	private static int range_thrashold = 3;

	private int rangeUntil(BigInteger[] all_vals, int start_idx) {
		int range_elem_count = 0;
		for (int i = start_idx; i < all_vals.length - 1; i++)
			if (all_vals[i].intValue() + 1 == all_vals[i + 1].intValue())
				range_elem_count++;
			else if (range_elem_count >= range_thrashold)
				return i;
			else
				return -1;
		if (range_elem_count >= range_thrashold)
			return all_vals.length - 1;
		else
			return -1;
	}

	// all_vals is always sorted...
	private String getNotValueStr(String field, BDDDomain dom,
			BigInteger[] allVals) {
		if (allVals.length > (dom.size().intValue() / 2))
			return getValueStr(field, dom, opVals(allVals, dom.size()
					.intValue()));
		if (allVals.length == 1)
			return field + "!=" + Env.stringer.elementName(dom, allVals[0]);
		String res = field + "!=" + "{";
		for (int i = 0; i < allVals.length; i++) {
			res += (i == 0) ? "" : ", ";
			int until = rangeUntil(allVals, i);
			if (until != -1)
				res += Env.stringer.elementNames(dom, allVals[i],
						allVals[until]);
			else
				res += Env.stringer.elementName(dom, allVals[i]);
		}
		res += "}";
		return res;
	}

	private BigInteger[] opVals(BigInteger[] cur_vals, int max) {
		Vector<BigInteger> res_vec = new Vector<BigInteger>(max);
		for (int i = 0; i < max; i++) {
			boolean exists = false;
			for (int j = 0; j < cur_vals.length; j++)
				if (cur_vals[j].intValue() == i)
					exists = true;
			if (!exists)
				res_vec.add(new BigInteger("" + i));
		}
		BigInteger[] res = new BigInteger[res_vec.size()];
		res_vec.toArray(res);
		sortArr(res, new BigIntegerComparator());
		return res;
	}

	private boolean isUniverseValue(BDDDomain dom, BigInteger val, BDD b) {
		if (b.and(dom.ithVar(val)).exist(dom.set()).isUniverse()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void sortArr(Object[] objArr, Comparator c) {
		List<Object> objList = new LinkedList<Object>();
		for (int i = 0; i < objArr.length; i++) {
			objList.add(objArr[i]);
		}
		Collections.sort(objList, c);
		objList.toArray(objArr);
	}

	private static int print_order_domain_threshold = 32;

	private class BDDDomainComparator implements Comparator<BDDDomain> {
		public int compare(BDDDomain arg0, BDDDomain arg1) {
			int left = enumDomain(arg0);
			int right = enumDomain(arg1);
			return (left - right);
		}

		public int enumDomain(BDDDomain dom) {
			if (dom.size().intValue() <= print_order_domain_threshold)
				return dom.getIndex();
			else
				// factory.varNum() won't be changed during a single print...
				return (int) (Math.pow(2, factory.varNum())) + dom.getIndex();
		}
	}

	private class BigIntegerComparator implements Comparator<BigInteger> {
		public int compare(BigInteger arg0, BigInteger arg1) {
			return (int) (arg0.longValue() - arg1.longValue());
		}
	}
}
