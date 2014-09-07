package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class BmcMcpTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/bmc/MCP.smv";

	public BmcMcpTC() {
		this(false);
	}

	public BmcMcpTC(boolean save_ref) {
		super("bmc_MCP", filename, save_ref);
	}
	
	@Test
	public void bmc_MCP() throws IOException {
		super.doParsingTest();
	}
}
