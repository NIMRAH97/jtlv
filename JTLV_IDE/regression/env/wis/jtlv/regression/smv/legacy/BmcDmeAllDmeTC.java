package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcDmeAllDmeTC extends ParsingTestCase {
	public static final String foldername = "./testcases/legacy/bmc/dme/";

	public BmcDmeAllDmeTC(String testname) {
		this(testname, false);
	}

	public BmcDmeAllDmeTC(String testname, boolean save_ref) {
		super("bmc_dme_" + testname, foldername + testname + ".smv", save_ref);
	}

	@Test
	public void bmc_dme_dme4() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme5() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme6() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme7() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme8() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme9() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme10() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme11() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme12() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme13() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme14() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme15() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme16() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme17() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme18() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme19() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme20() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme30() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme40() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void bmc_dme_dme50() throws IOException {
		super.doParsingTest();
	}
}
