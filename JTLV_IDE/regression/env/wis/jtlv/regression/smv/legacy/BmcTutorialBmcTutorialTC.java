package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcTutorialBmcTutorialTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/bmc_tutorial/bmc_tutorial.smv";

	public BmcTutorialBmcTutorialTC() {
		this(false);
	}

	public BmcTutorialBmcTutorialTC(boolean save_ref) {
		super("bmc_tutorial_bmc_tutorial", filename, save_ref);
	}
	
	@Test
	public void bmc_tutorial_bmc_tutorial() throws IOException {
		super.doParsingTest();
	}
}
