package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlSyncarb5TC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/syncarb5.smv";

	public CtlLtlSyncarb5TC() {
		this(false);
	}

	public CtlLtlSyncarb5TC(boolean save_ref) {
		super("ctl_ltl_syncarb5", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_syncarb5() throws IOException {
		super.doParsingTest();
	}
}
