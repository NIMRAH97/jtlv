package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlDme2TC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/dme2.smv";

	public CtlLtlDme2TC() {
		this(false);
	}

	public CtlLtlDme2TC(boolean save_ref) {
		super("ctl_ltl_dme2", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_dme2() throws IOException {
		super.doParsingTest();
	}
}
