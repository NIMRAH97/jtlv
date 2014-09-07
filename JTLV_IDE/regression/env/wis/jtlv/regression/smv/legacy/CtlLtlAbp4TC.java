package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlAbp4TC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/abp4.smv";

	public CtlLtlAbp4TC() {
		this(false);
	}

	public CtlLtlAbp4TC(boolean save_ref) {
		super("ctl_ltl_abp4", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_abp4() throws IOException {
		super.doParsingTest();
	}
}
