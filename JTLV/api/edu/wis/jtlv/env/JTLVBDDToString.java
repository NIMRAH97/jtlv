package edu.wis.jtlv.env;

import java.math.BigInteger;
import java.util.HashMap;

import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDD.BDDToString;
import edu.wis.jtlv.env.module.SMVModule;

public class JTLVBDDToString extends BDDToString {
	private HashMap<Integer, String[]> dom2vals;
	private HashMap<Integer, Integer[]> dom2range;

	public JTLVBDDToString() {
		dom2vals = new HashMap<Integer, String[]>(100);
		dom2range = new HashMap<Integer, Integer[]>(100);
	}

	// register....
	// public void register_domain_values(int dom, String[] values) {
	// dom2vals.put(dom, values);
	// }

	// public void register_domain_values(BDDDomain dom, String[] values) {
	// this.register_domain_values(dom.getIndex(), values);
	// }

	public void register_domain_module_values(SMVModule m, int dom,
			String[] values) {
		dom2vals.put(dom, mk_unique_val_name(m.getFullInstName(), values));
	}

	public void register_domain_module_values(SMVModule m, BDDDomain dom,
			String[] values) {
		this.register_domain_module_values(m, dom.getIndex(), values);
	}

	public void register_domain_module_values(SMVModule m, int dom,
			int range_start, int range_size) {
		dom2range.put(dom, new Integer[] { range_start, range_size });
	}

	public void register_domain_module_values(SMVModule m, BDDDomain dom,
			int range_start, int range_size) {
		this.register_domain_module_values(m, dom.getIndex(), range_start,
				range_size);
	}

	// has.... (only for names...)
	// public boolean domain_has_all_values(BDDDomain dom, String[] values) {
	// return this.domain_has_all_values(dom.getIndex(), values);
	// }
	//
	// public boolean domain_has_all_values(int dom, String[] values) {
	// for (int i = 0; i < values.length; i++) {
	// if (!domain_has_value(dom, values[i])) {
	// return false;
	// }
	// }
	// return true;
	// }
	//
	// public boolean domain_has_value(BDDDomain dom, String value) {
	// return this.domain_has_value(dom.getIndex(), value);
	// }
	//
	// public boolean domain_has_value(int dom, String value) {
	// String[] all_vals = dom2vals.get(dom);
	// if (all_vals == null)
	// return false;
	// for (int i = 0; i < all_vals.length; i++) {
	// if (all_vals[i].equals(value)) {
	// return true;
	// }
	// }
	// return false;
	// }

	// has.... (only for names...)
	public boolean domain_has_all_module_values(SMVModule m, BDDDomain dom,
			String[] values) {
		return this.domain_has_all_module_values(m, dom.getIndex(), values);
	}

	public boolean domain_has_all_module_values(SMVModule m, int dom,
			String[] values) {
		for (int i = 0; i < values.length; i++) {
			if (!domain_has_module_value(m, dom, values[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean domain_has_module_value(SMVModule m, BDDDomain dom,
			String value) {
		return this.domain_has_module_value(m, dom.getIndex(), value);
	}

	public boolean domain_has_module_value(SMVModule m, int dom, String value) {
		String[] all_vals = dom2vals.get(dom);
		if (all_vals == null)
			return false;
		for (int i = 0; i < all_vals.length; i++) {
			if (all_vals[i].equals(mk_unique_val_name(m.getFullInstName(),
					value))) {
				return true;
			}
		}
		return false;
	}

	public BigInteger get_module_value_loc(SMVModule m, BDDDomain dom,
			String value) {
		return this.get_module_value_loc(m, dom.getIndex(), value);
	}

	public BigInteger get_module_value_loc(SMVModule m, int dom, String value) {
		String[] all_vals = dom2vals.get(dom);
		if (all_vals == null)
			return new BigInteger("-1");
		for (int i = 0; i < all_vals.length; i++) {
			if (all_vals[i].equals(mk_unique_val_name(m.getFullInstName(),
					value))) {
				String val = "" + i;
				return new BigInteger(val);
			}
		}
		return new BigInteger("-1");
	}

	public BigInteger get_module_value_loc(SMVModule m, BDDDomain dom,
			int range_value) {
		return this.get_module_value_loc(m, dom.getIndex(), range_value);
	}

	public BigInteger get_module_value_loc(SMVModule m, int dom, int range_value) {
		Integer[] range = dom2range.get(dom);
		if (range != null) {
			if ((range_value >= range[0])
					& (range_value <= range[0] + range[1])) {
				int int_val = range_value - range[0];
				String str_val = "" + int_val;
				return new BigInteger(str_val);
			}
		}
		return new BigInteger("-1");
	}

	// implementations
	public String elementName(BDDDomain dom, BigInteger j) {
		return elementName(dom.getIndex(), j);
	}

	public String elementName(int i, BigInteger j) {
		String res = "";
		int val_idx = j.intValue();
		String[] all_vals = dom2vals.get(i);
		if (all_vals == null) {
			if (val_idx == 0) {
				res = "0";
			} else if (val_idx == 1) {
				res = "1";
			} else {
				// looking for range domain
				Integer[] range = dom2range.get(i);
				if (range != null) {
					int range_val = range[0] + val_idx;
					res += range_val;
				} else {
					res += "#?#";
				}
			}
		} else {
			if (val_idx < all_vals.length) {
				res = all_vals[val_idx];
			} else {
				res = "#?#";
			}
		}
		return res;
	}

	public static boolean print_as_range = false;

	public String elementNames(BDDDomain dom, BigInteger lo, BigInteger hi) {
		return elementNames(dom.getIndex(), lo, hi);
	}

	public String elementNames(int i, BigInteger lo, BigInteger hi) {
		int lo_idx = lo.intValue();
		int hi_idx = hi.intValue();
		if (lo_idx == hi_idx) {
			return elementName(i, lo);
		}

		if (print_as_range) {
			return "{" + elementName(i, lo) + ".." + elementName(i, hi) + "}";
		}

		String res = "{";
		for (int iter = lo_idx; iter <= hi_idx; iter++) {
			res += elementName(i, new BigInteger("" + lo_idx));
			res += (iter != hi_idx) ? ", " : "}";
		}
		return res;
	}

	private String[] mk_unique_val_name(String prefix, String[] unique_val_name) {
		String[] res = new String[unique_val_name.length];
		for (int i = 0; i < unique_val_name.length; i++) {
			res[i] = mk_unique_val_name(prefix, unique_val_name[i]);
		}
		return res;
	}

	private String mk_unique_val_name(String prefix, String val_name) {
		// if it is a number, then I'm not making it unique.
		//
		// make problems with param, since I don't always have the holder if it
		// is a long address (e.g. the param is "cg.mode")
		// try {
		// Integer.parseInt(val_name);
		// return val_name;
		// } catch (NumberFormatException nfe) {
		// }
		// return "___value___" + prefix + "[$]" + val_name;
		return val_name;
	}

	// private String strip_unique_val_name(String prefix, String
	// unique_val_name) {
	// String uni_player = "___value___" + prefix + "[$]";
	// if (unique_val_name.startsWith(uni_player)) {
	// return unique_val_name.substring(uni_player.length());
	// }
	// try {
	// Integer.parseInt(unique_val_name);
	// return unique_val_name;
	// } catch (NumberFormatException nfe) {
	// }
	// return "";
	// }

}
