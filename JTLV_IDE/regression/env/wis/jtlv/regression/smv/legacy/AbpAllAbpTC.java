package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class AbpAllAbpTC extends ParsingTestCase {
	public static final String foldername = "./testcases/legacy/abp/";

	public AbpAllAbpTC(String testname) {
		this(testname, false);
	}

	public AbpAllAbpTC(String testname, boolean save_ref) {
		super("abp_" + testname, foldername + testname + ".smv", save_ref);
	}

	@Test
	public void abp_abp4() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void abp_abp8() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void abp_abp10() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void abp_abp11() throws IOException {
		super.doParsingTest();
	}

	@Test
	public void abp_abp16() throws IOException {
		super.doParsingTest();
	}

}
