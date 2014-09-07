package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BrpBrpTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/brp/brp.smv";

	public BrpBrpTC() {
		this(false);
	}

	public BrpBrpTC(boolean save_ref) {
		super("brp_brp", filename, save_ref);
	}
	
	@Test
	public void brp_brp() throws IOException {
		super.doParsingTest();
	}
}
