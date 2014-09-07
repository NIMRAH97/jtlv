package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlPeriodicTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/periodic.smv";

	public CtlLtlPeriodicTC() {
		this(false);
	}

	public CtlLtlPeriodicTC(boolean save_ref) {
		super("ctl_ltl_periodic", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_periodic() throws IOException {
		super.doParsingTest();
	}
}
