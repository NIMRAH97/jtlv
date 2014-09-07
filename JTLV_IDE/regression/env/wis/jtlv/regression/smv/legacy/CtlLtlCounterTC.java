package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlCounterTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/counter.smv";

	public CtlLtlCounterTC() {
		this(false);
	}

	public CtlLtlCounterTC(boolean save_ref) {
		super("ctl_ltl_counter", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_counter() throws IOException {
		super.doParsingTest();
	}
}
