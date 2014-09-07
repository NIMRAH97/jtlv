package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class CtlLtlProductionCellTC extends ParsingTestCase {
	public static final String filename = "./testcases/legacy/ctl-ltl/production-cell.smv";

	public CtlLtlProductionCellTC() {
		this(false);
	}

	public CtlLtlProductionCellTC(boolean save_ref) {
		super("ctl_ltl_production_cell", filename, save_ref);
	}
	
	@Test
	public void ctl_ltl_production_cell() throws IOException {
		super.doParsingTest();
	}
}
