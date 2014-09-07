package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlPQueueTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/p-queue.smv";

	public CtlLtlPQueueTC() {
		this(false);
	}

	public CtlLtlPQueueTC(boolean save_ref) {
		super("ctl_ltl_p_queue", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_p_queue() throws IOException {
		super.doParsingTest();
	}
}
