package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcMultiplierTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/bmc/multiplier.smv";

	public BmcMultiplierTC() {
		this(false);
	}

	public BmcMultiplierTC(boolean save_ref) {
		super("bmc_multiplier", filename, save_ref);
	}
	
	@Test
	public void bmc_multiplier() throws IOException {
		super.doParsingTest();
	}
}
