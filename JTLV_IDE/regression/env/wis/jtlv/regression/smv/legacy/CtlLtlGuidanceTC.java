package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlGuidanceTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/guidance.smv";

	public CtlLtlGuidanceTC() {
		this(false);
	}

	public CtlLtlGuidanceTC(boolean save_ref) {
		super("ctl_ltl_guidance", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_guidance() throws IOException {
		super.doParsingTest();
	}
}
