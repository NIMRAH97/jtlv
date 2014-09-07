package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlMutex1TC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/mutex1.smv";

	public CtlLtlMutex1TC() {
		this(false);
	}

	public CtlLtlMutex1TC(boolean save_ref) {
		super("ctl_ltl_mutex1", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_mutex1() throws IOException {
		super.doParsingTest();
	}
}
