package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlMutexTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/mutex.smv";

	public CtlLtlMutexTC() {
		this(false);
	}

	public CtlLtlMutexTC(boolean save_ref) {
		super("ctl_ltl_mutex", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_mutex() throws IOException {
		super.doParsingTest();
	}
}
