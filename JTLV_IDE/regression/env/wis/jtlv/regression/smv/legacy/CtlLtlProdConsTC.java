package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlProdConsTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/prod-cons.smv";

	public CtlLtlProdConsTC() {
		this(false);
	}

	public CtlLtlProdConsTC(boolean save_ref) {
		super("ctl_ltl_prod_cons", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_prod_cons() throws IOException {
		super.doParsingTest();
	}
}
