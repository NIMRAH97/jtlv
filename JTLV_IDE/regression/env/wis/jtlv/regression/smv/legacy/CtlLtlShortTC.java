package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlShortTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/short.smv";

	public CtlLtlShortTC() {
		this(false);
	}

	public CtlLtlShortTC(boolean save_ref) {
		super("ctl_ltl_short", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_short() throws IOException {
		super.doParsingTest();
	}
}
