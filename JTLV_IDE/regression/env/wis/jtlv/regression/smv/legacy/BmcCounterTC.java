package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcCounterTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/bmc/counter.smv";

	public BmcCounterTC() {
		this(false);
	}

	public BmcCounterTC(boolean save_ref) {
		super("bmc_counter", filename, save_ref);
	}
	
	@Test
	public void bmc_counter() throws IOException {
		super.doParsingTest();
	}
}
