package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlRingTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/ring.smv";

	public CtlLtlRingTC() {
		this(false);
	}

	public CtlLtlRingTC(boolean save_ref) {
		super("ctl_ltl_ring", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_ring() throws IOException {
		super.doParsingTest();
	}
}
