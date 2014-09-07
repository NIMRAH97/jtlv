package edu.wis.jtlv.env;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDDomain;
import net.sf.javabdd.BDDException;

public class JTLVBDDCouple {
	public static boolean full_print = true;
	protected String path;
	protected String name;
	protected BDDDomain main;

	protected boolean is_prime;
	protected JTLVBDDCouple pair;

	public JTLVBDDCouple(BDDDomain unprime, BDDDomain prime, String a_path,
			String name) {
		this.main = unprime;
		this.main.setName(name);
		this.path = a_path;
		this.name = name;
		this.is_prime = false;
		this.pair = new JTLVBDDCouple(prime, this, a_path, name + "'");
	}

	private JTLVBDDCouple(BDDDomain prime, JTLVBDDCouple main_pair,
			String a_path, String name) {
		this.main = prime;
		this.main.setName(name);
		this.path = a_path;
		this.name = name;
		this.is_prime = true;
		this.pair = main_pair;
	}

	protected JTLVBDDCouple() {
		// do nothing for pointers...
	}

	public JTLVBDDCouple other() {
		return this.pair;
	}

	public JTLVBDDCouple prime() throws BDDException {
		if (is_prime) {
			throw new BDDException("Cannot prime primed variables.");
		}
		return this.other();
	}

	public JTLVBDDCouple unprime() throws BDDException {
		if (!is_prime) {
			throw new BDDException("Cannot unprime unprimed variables.");
		}
		return this.other();
	}

	public BDD toBDD() {
		// TODO: This implementation is only for FDS!!!!
		return this.getDomain().ithVar(1);
	}

	public BDD otherBDD() throws BDDException {
		return this.other().toBDD();
	}

	public BDD primeBDD() throws BDDException {
		return this.prime().toBDD();
	}

	public BDD unprimeBDD() throws BDDException {
		return this.unprime().toBDD();
	}

	public boolean is_prime() {
		return is_prime;
	}

	public BDDDomain getDomain() {
		return this.main;
	}

	public BDDDomain getOtherDomain() throws BDDException {
		return this.other().getDomain();
	}

	public String toString() {
		String res = "";
		if (JTLVBDDCouple.full_print)
			res += this.path + "->";
		res += this.name;
		return res;
	}

	public boolean equals(Object other) {
		if (!(JTLVBDDCouple.class.isInstance(other))) {
			return false;
		}
		if (this.path != ((JTLVBDDCouple) other).path) {
			return false;
		}
		if (this.name != ((JTLVBDDCouple) other).name) {
			return false;
		}
		return true;
	}

	public boolean weakEquals(Object other) {
		if (!(JTLVBDDCouple.class.isInstance(other))) {
			return false;
		}
		if (!this.getDomain().size().equals(
				((JTLVBDDCouple) other).getDomain().size())) {
			return false;
		}
		return true;
	}

	public String get_path() {
		return this.path;
	}

	public String get_name() {
		return this.name;
	}

	public boolean isArrayElement() {
		return ((this.name.lastIndexOf(']') + 1) == this.name.length());
	}

	public String getSimpleArrayName() {
		int last_RB = this.name.lastIndexOf(']');
		if ((last_RB + 1) != this.name.length())
			return "";

		int last_LB = last_RB - 1;
		int count = 1;
		while ((last_LB > 0) & (count > 0)) {
			if (this.name.charAt(last_LB) == ']')
				count++;
			if (this.name.charAt(last_LB) == '[')
				count--;
			last_LB--;
		}
		return this.name.substring(0, last_LB + 1);
	}
}
