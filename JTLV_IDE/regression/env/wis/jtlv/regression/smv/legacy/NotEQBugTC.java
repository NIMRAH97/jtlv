package env.wis.jtlv.regression.smv.legacy;

import java.io.IOException;

import org.junit.Test;

import env.wis.jtlv.regression.smv.ParsingTestCase;

public class NotEQBugTC extends ParsingTestCase {
	@Test
	public void not_eq_bug() throws IOException {
		super.doParsingTest();
	}

	public static final String filename = "./testcases/legacy/not_eq_bug.smv";

	public NotEQBugTC() {
		this(false);
	}

	public NotEQBugTC(boolean save_ref) {
		super("not_eq_bug", filename, save_ref);
	}
	
}
