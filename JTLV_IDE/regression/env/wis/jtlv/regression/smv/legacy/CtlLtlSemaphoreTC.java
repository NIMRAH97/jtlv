package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlSemaphoreTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/semaphore.smv";

	public CtlLtlSemaphoreTC() {
		this(false);
	}

	public CtlLtlSemaphoreTC(boolean save_ref) {
		super("ctl_ltl_semaphore", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_semaphore() throws IOException {
		super.doParsingTest();
	}
}
