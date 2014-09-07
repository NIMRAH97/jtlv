package edu.wis.jtlv.new_tb;

import junit.framework.TestSuite;
import edu.wis.jtlv.new_tb.parsing.ParsingTestCase;

public class ParsingTestSuite extends TestSuite {
	public ParsingTestSuite(Class<? extends ParsingTestCase> theClass, String name,
			String file_name_to_parse) {
		super(theClass, name);
		for (int i = 0; i < super.countTestCases(); i++) {
			ParsingTestCase t = (ParsingTestCase) super.testAt(i);
			t.set_file(file_name_to_parse);
		}
	}
}
