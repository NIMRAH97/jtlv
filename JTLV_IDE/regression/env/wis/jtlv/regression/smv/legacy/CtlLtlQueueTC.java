package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlQueueTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/queue.smv";

	public CtlLtlQueueTC() {
		this(false);
	}

	public CtlLtlQueueTC(boolean save_ref) {
		super("ctl_ltl_queue", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_queue() throws IOException {
		super.doParsingTest();
	}
}
