package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcBarrel5TC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/bmc/barrel5.smv";

	public BmcBarrel5TC() {
		this(false);
	}

	public BmcBarrel5TC(boolean save_ref) {
		super("bmc_barrel5", filename, save_ref);
	}
	
	@Test
	public void bmc_barrel5() throws IOException {
		super.doParsingTest();
	}
}
