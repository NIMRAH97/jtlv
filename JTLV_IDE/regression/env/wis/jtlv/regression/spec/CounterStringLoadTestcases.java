package env.wis.jtlv.regression.spec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

import junit.framework.TestCase;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.ErrorListener;
import edu.wis.jtlv.env.core.smv.SMVParseException;
import edu.wis.jtlv.env.core.spec.SpecParseException;
import edu.wis.jtlv.env.spec.Operator;
import edu.wis.jtlv.env.spec.Spec;
import edu.wis.jtlv.env.spec.SpecBDD;
import edu.wis.jtlv.env.spec.SpecCTLRange;
import edu.wis.jtlv.env.spec.SpecExp;
import env.wis.jtlv.regression.AllTests;

public class CounterStringLoadTestcases extends TestCase {
	private int test_num;

	public CounterStringLoadTestcases(int num) {
		// setting the procedure to execute as a test.
		super("counter_specification_" + num);
		this.test_num = num;
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("bdd", "jtlv");
		Env.resetEnv();
		Env.beQuiet();

		Env.loadModule("testcases/spc_parser/counter.smv");

		FileOutputStream out_fos = new FileOutputStream(".junit_tmp.out", false);
		System.setOut(new PrintStream(out_fos));
		FileOutputStream err_fos = new FileOutputStream(".junit_tmp.err", false);
		System.setErr(new PrintStream(err_fos));

		Env.registerErrorListener(new JUnitErrorListener());
	}

	public Spec[] results;
	public Vector<Exception> resultsError = new Vector<Exception>();
	public Vector<String> resultsErrorMsg = new Vector<String>();

	class JUnitErrorListener implements ErrorListener {
		public void doError(Exception e, String msg) {
			resultsError.add(e);
			String pre = "";
			if (e instanceof RecognitionException) {
				RecognitionException re = (RecognitionException) e;
				pre += "line " + re.line + ":" + re.charPositionInLine + " ";
			}
			resultsErrorMsg.add(pre + msg);
		}
	}

	@After
	public void tearDown() throws Exception {
		if (this.test_num == 0)
			check_0();
		else if (this.test_num == 1)
			check_1();
		else if (this.test_num == 2)
			check_2();
		else if (this.test_num == 3)
			check_3();
		else if (this.test_num == 4)
			check_4();
		else if (this.test_num == 5)
			check_5();
		else if (this.test_num == 6)
			check_6();
		else if (this.test_num == 7)
			check_7();
		else if (this.test_num == 8)
			check_8();
		else if (this.test_num == 9)
			check_9();
		else if (this.test_num == 10)
			check_10();
		else if (this.test_num == 11)
			check_11();
		else if (this.test_num == 12)
			check_12();
		else if (this.test_num == 13)
			check_13();
		else if (this.test_num == 14)
			check_14();
		else if (this.test_num == 15)
			check_15();
		else if (this.test_num == 16)
			check_16();
		else if (this.test_num == 17)
			check_17();
		else if (this.test_num == 18)
			check_18();
		else if (this.test_num == 19)
			check_19();
		else if (this.test_num == 20)
			check_20();
		else if (this.test_num == 21)
			check_21();
		else if (this.test_num == 22)
			check_22();
		else if (this.test_num == 23)
			check_23();
		else if (this.test_num == 24)
			check_24();
		else if (this.test_num == 25)
			check_25();
		else if (this.test_num == 26)
			check_26();
		else if (this.test_num == 27)
			check_27();
		else {
			if (AllTests.rmLogs) {
				new File(".junit_tmp.out").delete();
				new File(".junit_tmp.err").delete();
			}
			throw new Exception("Could not find checking procedure.");
		}

		if (AllTests.rmLogs) {
			new File(".junit_tmp.out").delete();
			new File(".junit_tmp.err").delete();
		}

	}

	@Test
	public void counter_specification_0() {
		String to_parse = "SPEC\n" + "	AG AF bit2.carry_out\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_0() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AG);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(!son.isLTLSpec());
		assertTrue(!son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.AF);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_1() {
		String to_parse = "SPEC\n"
				+ "	EG AF ((bit0.value <-> bit1.value) & bit2.value)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_1() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.EG);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(!son.isLTLSpec());
		assertTrue(!son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.AF);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_2() {
		String to_parse = "SPEC\n"
				+ "	AG AF ((it0.value <-> it1.value) & bit2.value)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_2() throws Exception {
		assertTrue(resultsError.size() == 2);
		assertTrue(resultsError.elementAt(0) instanceof SMVParseException);
		assertTrue(resultsError.elementAt(1) instanceof SMVParseException);
		SMVParseException spe0 = (SMVParseException) resultsError.elementAt(0);
		SMVParseException spe1 = (SMVParseException) resultsError.elementAt(1);
		assertTrue(spe0.line == 2);
		assertTrue(spe0.charPositionInLine == 13);
		assertTrue(spe1.line == 2);
		assertTrue(spe1.charPositionInLine == 27);

		assertTrue(resultsErrorMsg.size() == 2);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:13 Couldn't find value it0.value."));
		assertTrue(resultsErrorMsg.elementAt(1).equals(
				"line 2:27 Couldn't find value it1.value."));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_3() {
		String to_parse = "SPEC\n"
				+ "	EG EF (bit0.value & bit1.value & bit2.value)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_3() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.EG);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(!son.isLTLSpec());
		assertTrue(!son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.EF);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_4() {
		String to_parse = "SPEC\n" + "	AG AF bit2.carry_out\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_4() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AG);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(!son.isLTLSpec());
		assertTrue(!son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.AF);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_5() {
		String to_parse = "LTLSPEC\n" + "	G F bit2.carry_out\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_5() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(!me.isRealTimeCTLSpec());
		assertTrue(me.isLTLSpec());
		assertTrue(me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.GLOBALLY);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(!son.isCTLSpec());
		assertTrue(!son.isRealTimeCTLSpec());
		assertTrue(son.isLTLSpec());
		assertTrue(son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.FINALLY);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_6() {
		String to_parse = "SPEC\n" + "	AG(!bit2.carry_out);\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_6() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AG);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(son.isLTLSpec());
		assertTrue(son.isFutureLTLSpec());
		assertTrue(son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(!son.hasTemporalOperators());
		assertTrue(son instanceof SpecBDD);
	}

	@Test
	public void counter_specification_7() {
		String to_parse = "SPEC\n" + "	!AG(!bit2.carry_out)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_7() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.NOT);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(!son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(!son.isLTLSpec());
		assertTrue(!son.isFutureLTLSpec());
		assertTrue(!son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(son.hasTemporalOperators());
		assertTrue(son instanceof SpecExp);

		// a more specific asserts.
		SpecExp son_exp = (SpecExp) son;
		assertTrue(son_exp.getOperator() == Operator.AG);
		assertTrue(son_exp.getChildren() != null);
		assertTrue(son_exp.getChildren().length == 1);
		Spec grandson = son_exp.getChildren()[0];
		assertTrue(grandson != null);
		assertTrue(grandson.isPropSpec());
		assertTrue(grandson.isCTLSpec());
		assertTrue(grandson.isRealTimeCTLSpec());
		assertTrue(grandson.isLTLSpec());
		assertTrue(grandson.isFutureLTLSpec());
		assertTrue(grandson.isPastLTLSpec());
		assertTrue(grandson.isCTLStarSpec());
		assertTrue(!grandson.hasTemporalOperators());
		assertTrue(grandson instanceof SpecBDD);
	}

	@Test
	public void counter_specification_8() {
		String to_parse = "LTLSPEC\n" + "	G (!bit2.carry_out)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_8() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(!me.isRealTimeCTLSpec());
		assertTrue(me.isLTLSpec());
		assertTrue(me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.GLOBALLY);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(son != null);
		assertTrue(son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(son.isLTLSpec());
		assertTrue(son.isFutureLTLSpec());
		assertTrue(son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(!son.hasTemporalOperators());
		assertTrue(son instanceof SpecBDD);
	}

	@Test
	public void counter_specification_9() {
		String to_parse = "SPEC\n"
				+ "	A [(!bit2.carry_out) UNTIL (bit2.carry_out)]\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_9() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		for (Spec son : me_exp.getChildren()) {
			assertTrue(son != null);
			assertTrue(son.isPropSpec());
			assertTrue(son.isCTLSpec());
			assertTrue(son.isRealTimeCTLSpec());
			assertTrue(son.isLTLSpec());
			assertTrue(son.isFutureLTLSpec());
			assertTrue(son.isPastLTLSpec());
			assertTrue(son.isCTLStarSpec());
			assertTrue(!son.hasTemporalOperators());
			assertTrue(son instanceof SpecBDD);
		}
	}

	@Test
	public void counter_specification_10() {
		String to_parse = "SPEC\n"
				+ "	A [ bit2.value BU 1..2 bit0.value ] & bit1.value\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_10() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AND);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		Spec son1 = me_exp.getChildren()[0];
		Spec son2 = me_exp.getChildren()[1];

		assertTrue(son2 != null);
		assertTrue(son2 != null);
		assertTrue(son2.isPropSpec());
		assertTrue(son2.isCTLSpec());
		assertTrue(son2.isRealTimeCTLSpec());
		assertTrue(son2.isLTLSpec());
		assertTrue(son2.isFutureLTLSpec());
		assertTrue(son2.isPastLTLSpec());
		assertTrue(son2.isCTLStarSpec());
		assertTrue(!son2.hasTemporalOperators());
		assertTrue(son2 instanceof SpecBDD);

		assertTrue(son1 != null);
		assertTrue(!son1.isPropSpec());
		assertTrue(!son1.isCTLSpec());
		assertTrue(son1.isRealTimeCTLSpec());
		assertTrue(!son1.isLTLSpec());
		assertTrue(!son1.isFutureLTLSpec());
		assertTrue(!son1.isPastLTLSpec());
		assertTrue(son1.isCTLStarSpec());
		assertTrue(son1.hasTemporalOperators());
		assertTrue(son1 instanceof SpecExp);

		// a more specific asserts.
		SpecExp son1_exp = (SpecExp) son1;
		assertTrue(son1_exp.getOperator() == Operator.ABU);
		assertTrue(son1_exp.getChildren() != null);
		assertTrue(son1_exp.getChildren().length == 3);
		Spec grandson1 = son1_exp.getChildren()[0];
		Spec grandson2 = son1_exp.getChildren()[1];
		Spec grandson3 = son1_exp.getChildren()[2];

		assertTrue(grandson1 != null);
		assertTrue(grandson1.isPropSpec());
		assertTrue(grandson1.isCTLSpec());
		assertTrue(grandson1.isRealTimeCTLSpec());
		assertTrue(grandson1.isLTLSpec());
		assertTrue(grandson1.isFutureLTLSpec());
		assertTrue(grandson1.isPastLTLSpec());
		assertTrue(grandson1.isCTLStarSpec());
		assertTrue(!grandson1.hasTemporalOperators());
		assertTrue(grandson1 instanceof SpecBDD);

		assertTrue(grandson2 != null);
		assertTrue(grandson2.isPropSpec());
		assertTrue(grandson2.isCTLSpec());
		assertTrue(grandson2.isRealTimeCTLSpec());
		assertTrue(grandson2.isLTLSpec());
		assertTrue(grandson2.isFutureLTLSpec());
		assertTrue(grandson2.isPastLTLSpec());
		assertTrue(grandson2.isCTLStarSpec());
		assertTrue(!grandson2.hasTemporalOperators());
		assertTrue(grandson2 instanceof SpecCTLRange);

		assertTrue(grandson3 != null);
		assertTrue(grandson3.isPropSpec());
		assertTrue(grandson3.isCTLSpec());
		assertTrue(grandson3.isRealTimeCTLSpec());
		assertTrue(grandson3.isLTLSpec());
		assertTrue(grandson3.isFutureLTLSpec());
		assertTrue(grandson3.isPastLTLSpec());
		assertTrue(grandson3.isCTLStarSpec());
		assertTrue(!grandson3.hasTemporalOperators());
		assertTrue(grandson3 instanceof SpecBDD);
	}

	@Test
	public void counter_specification_11() {
		String to_parse = "SPEC\n" + "	(!bit2.carry_out) U x;\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_11() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof SpecParseException);
		SpecParseException spe0 = (SpecParseException) resultsError
				.elementAt(0);
		assertTrue(spe0.getErrorStartLine() == 2);
		assertTrue(spe0.getErrorStartCharPos() == 19);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:19 Failed to parse expression 'U x;'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_12() {
		String to_parse = "CTL*SPEC\n"
				+ "	A [ !bit2.carry_out UNTIL bit2.carry_out ]\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_12() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		for (Spec son : me_exp.getChildren()) {
			assertTrue(son != null);
			assertTrue(son.isPropSpec());
			assertTrue(son.isCTLSpec());
			assertTrue(son.isRealTimeCTLSpec());
			assertTrue(son.isLTLSpec());
			assertTrue(son.isFutureLTLSpec());
			assertTrue(son.isPastLTLSpec());
			assertTrue(son.isCTLStarSpec());
			assertTrue(!son.hasTemporalOperators());
			assertTrue(son instanceof SpecBDD);
		}
	}

	@Test
	public void counter_specification_13() {
		String to_parse = "LTLSPEC\n" + "	(!bit2.carry_out) U    ;\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_13() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof NoViableAltException);
		NoViableAltException nvae = (NoViableAltException) resultsError
				.elementAt(0);
		assertTrue(nvae.line == 2);
		assertTrue(nvae.charPositionInLine == 24);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:24 no viable alternative at input ';'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_14() {
		String to_parse = "CTLSPEC\n"
				+ "	A [ bit0.carry_out BU 1..2 bit2.carry_out ] & bit2.carry_out;\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_14() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AND);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		Spec son1 = me_exp.getChildren()[0];
		Spec son2 = me_exp.getChildren()[1];

		assertTrue(son2 != null);
		assertTrue(son2.isPropSpec());
		assertTrue(son2.isCTLSpec());
		assertTrue(son2.isRealTimeCTLSpec());
		assertTrue(son2.isLTLSpec());
		assertTrue(son2.isFutureLTLSpec());
		assertTrue(son2.isPastLTLSpec());
		assertTrue(son2.isCTLStarSpec());
		assertTrue(!son2.hasTemporalOperators());
		assertTrue(son2 instanceof SpecBDD);

		assertTrue(son1 != null);
		assertTrue(!son1.isPropSpec());
		assertTrue(!son1.isCTLSpec());
		assertTrue(son1.isRealTimeCTLSpec());
		assertTrue(!son1.isLTLSpec());
		assertTrue(!son1.isFutureLTLSpec());
		assertTrue(!son1.isPastLTLSpec());
		assertTrue(son1.isCTLStarSpec());
		assertTrue(son1.hasTemporalOperators());
		assertTrue(son1 instanceof SpecExp);

		// a more specific asserts.
		SpecExp son1_exp = (SpecExp) son1;
		assertTrue(son1_exp.getOperator() == Operator.ABU);
		assertTrue(son1_exp.getChildren() != null);
		assertTrue(son1_exp.getChildren().length == 3);
		Spec grandson1 = son1_exp.getChildren()[0];
		Spec grandson2 = son1_exp.getChildren()[1];
		Spec grandson3 = son1_exp.getChildren()[2];

		assertTrue(grandson1 != null);
		assertTrue(grandson1.isPropSpec());
		assertTrue(grandson1.isCTLSpec());
		assertTrue(grandson1.isRealTimeCTLSpec());
		assertTrue(grandson1.isLTLSpec());
		assertTrue(grandson1.isFutureLTLSpec());
		assertTrue(grandson1.isPastLTLSpec());
		assertTrue(grandson1.isCTLStarSpec());
		assertTrue(!grandson1.hasTemporalOperators());
		assertTrue(grandson1 instanceof SpecBDD);

		assertTrue(grandson2 != null);
		assertTrue(grandson2.isPropSpec());
		assertTrue(grandson2.isCTLSpec());
		assertTrue(grandson2.isRealTimeCTLSpec());
		assertTrue(grandson2.isLTLSpec());
		assertTrue(grandson2.isFutureLTLSpec());
		assertTrue(grandson2.isPastLTLSpec());
		assertTrue(grandson2.isCTLStarSpec());
		assertTrue(!grandson2.hasTemporalOperators());
		assertTrue(grandson2 instanceof SpecCTLRange);

		assertTrue(grandson3 != null);
		assertTrue(grandson3.isPropSpec());
		assertTrue(grandson3.isCTLSpec());
		assertTrue(grandson3.isRealTimeCTLSpec());
		assertTrue(grandson3.isLTLSpec());
		assertTrue(grandson3.isFutureLTLSpec());
		assertTrue(grandson3.isPastLTLSpec());
		assertTrue(grandson3.isCTLStarSpec());
		assertTrue(!grandson3.hasTemporalOperators());
		assertTrue(grandson3 instanceof SpecBDD);
	}

	@Test
	public void counter_specification_15() {
		String to_parse = "LTLSPEC\n" + "	U (!bit2.carry_out)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_15() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof NoViableAltException);
		NoViableAltException nvae = (NoViableAltException) resultsError
				.elementAt(0);
		assertTrue(nvae.line == 2);
		assertTrue(nvae.charPositionInLine == 1);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:1 no viable alternative at input 'U'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_16() {
		String to_parse = "CTL*SPEC\n"
				+ "	A [ bit2.value U bit0.carry_out ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_16() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		for (Spec son : me_exp.getChildren()) {
			assertTrue(son != null);
			assertTrue(son.isPropSpec());
			assertTrue(son.isCTLSpec());
			assertTrue(son.isRealTimeCTLSpec());
			assertTrue(son.isLTLSpec());
			assertTrue(son.isFutureLTLSpec());
			assertTrue(son.isPastLTLSpec());
			assertTrue(son.isCTLStarSpec());
			assertTrue(!son.hasTemporalOperators());
			assertTrue(son instanceof SpecBDD);
		}
	}

	public void counter_specification_17() {
		String to_parse = "SPEC\n" + "	(!bit2.carry_out) U ;\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_17() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof SpecParseException);
		SpecParseException spe0 = (SpecParseException) resultsError
				.elementAt(0);
		assertTrue(spe0.getErrorStartLine() == 2);
		assertTrue(spe0.getErrorStartCharPos() == 19);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:19 Failed to parse expression 'U ;'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_18() {
		String to_parse = "CTLSPEC\n"
				+ "	A [ bit0.value BU 1..2 bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_18() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.ABU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 3);
		Spec son1 = me_exp.getChildren()[0];
		Spec son2 = me_exp.getChildren()[1];
		Spec son3 = me_exp.getChildren()[2];

		assertTrue(son1 != null);
		assertTrue(son1.isPropSpec());
		assertTrue(son1.isCTLSpec());
		assertTrue(son1.isRealTimeCTLSpec());
		assertTrue(son1.isLTLSpec());
		assertTrue(son1.isFutureLTLSpec());
		assertTrue(son1.isPastLTLSpec());
		assertTrue(son1.isCTLStarSpec());
		assertTrue(!son1.hasTemporalOperators());
		assertTrue(son1 instanceof SpecBDD);

		assertTrue(son2 != null);
		assertTrue(son2.isPropSpec());
		assertTrue(son2.isCTLSpec());
		assertTrue(son2.isRealTimeCTLSpec());
		assertTrue(son2.isLTLSpec());
		assertTrue(son2.isFutureLTLSpec());
		assertTrue(son2.isPastLTLSpec());
		assertTrue(son2.isCTLStarSpec());
		assertTrue(!son2.hasTemporalOperators());
		assertTrue(son2 instanceof SpecCTLRange);

		assertTrue(son3 != null);
		assertTrue(son3.isPropSpec());
		assertTrue(son3.isCTLSpec());
		assertTrue(son3.isRealTimeCTLSpec());
		assertTrue(son3.isLTLSpec());
		assertTrue(son3.isFutureLTLSpec());
		assertTrue(son3.isPastLTLSpec());
		assertTrue(son3.isCTLStarSpec());
		assertTrue(!son3.hasTemporalOperators());
		assertTrue(son3 instanceof SpecBDD);
	}

	@Test
	public void counter_specification_19() {
		String to_parse = "CTL*SPEC\n"
				+ "	A [ bit0.value BU 1..2 bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_19() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.ABU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 3);
		Spec son1 = me_exp.getChildren()[0];
		Spec son2 = me_exp.getChildren()[1];
		Spec son3 = me_exp.getChildren()[2];

		assertTrue(son1 != null);
		assertTrue(son1.isPropSpec());
		assertTrue(son1.isCTLSpec());
		assertTrue(son1.isRealTimeCTLSpec());
		assertTrue(son1.isLTLSpec());
		assertTrue(son1.isFutureLTLSpec());
		assertTrue(son1.isPastLTLSpec());
		assertTrue(son1.isCTLStarSpec());
		assertTrue(!son1.hasTemporalOperators());
		assertTrue(son1 instanceof SpecBDD);

		assertTrue(son2 != null);
		assertTrue(son2.isPropSpec());
		assertTrue(son2.isCTLSpec());
		assertTrue(son2.isRealTimeCTLSpec());
		assertTrue(son2.isLTLSpec());
		assertTrue(son2.isFutureLTLSpec());
		assertTrue(son2.isPastLTLSpec());
		assertTrue(son2.isCTLStarSpec());
		assertTrue(!son2.hasTemporalOperators());
		assertTrue(son2 instanceof SpecCTLRange);

		assertTrue(son3 != null);
		assertTrue(son3.isPropSpec());
		assertTrue(son3.isCTLSpec());
		assertTrue(son3.isRealTimeCTLSpec());
		assertTrue(son3.isLTLSpec());
		assertTrue(son3.isFutureLTLSpec());
		assertTrue(son3.isPastLTLSpec());
		assertTrue(son3.isCTLStarSpec());
		assertTrue(!son3.hasTemporalOperators());
		assertTrue(son3 instanceof SpecBDD);
	}

	@Test
	public void counter_specification_20() {
		String to_parse = "CTLSPEC\n" + "	A [ bit0.value U bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_20() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		for (Spec son : me_exp.getChildren()) {
			assertTrue(son != null);
			assertTrue(son.isPropSpec());
			assertTrue(son.isCTLSpec());
			assertTrue(son.isRealTimeCTLSpec());
			assertTrue(son.isLTLSpec());
			assertTrue(son.isFutureLTLSpec());
			assertTrue(son.isPastLTLSpec());
			assertTrue(son.isCTLStarSpec());
			assertTrue(!son.hasTemporalOperators());
			assertTrue(son instanceof SpecBDD);
		}
	}

	@Test
	public void counter_specification_21() {
		String to_parse = "CTL*SPEC\n" + "	A [ bit0.value U bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_21() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.AU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 2);
		for (Spec son : me_exp.getChildren()) {
			assertTrue(son != null);
			assertTrue(son.isPropSpec());
			assertTrue(son.isCTLSpec());
			assertTrue(son.isRealTimeCTLSpec());
			assertTrue(son.isLTLSpec());
			assertTrue(son.isFutureLTLSpec());
			assertTrue(son.isPastLTLSpec());
			assertTrue(son.isCTLStarSpec());
			assertTrue(!son.hasTemporalOperators());
			assertTrue(son instanceof SpecBDD);
		}
	}

	public void counter_specification_22() {
		String to_parse = "CTLSPEC\n" + "	A [ bit0.value BU bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_22() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof NoViableAltException);
		NoViableAltException nvae = (NoViableAltException) resultsError
				.elementAt(0);
		assertTrue(nvae.line == 2);
		assertTrue(nvae.charPositionInLine == 19);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:19 no viable alternative at input 'bit2'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	public void counter_specification_23() {
		String to_parse = "CTL*SPEC\n" + "	A [ bit0.value BU bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_23() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof NoViableAltException);
		NoViableAltException nvae = (NoViableAltException) resultsError
				.elementAt(0);
		assertTrue(nvae.line == 2);
		assertTrue(nvae.charPositionInLine == 19);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:19 no viable alternative at input 'bit2'"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	public void counter_specification_24() {
		String to_parse = "CTLSPEC\n"
				+ "	A [ bit0.value U 0..2 bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_24() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof MismatchedTokenException);
		MismatchedTokenException mte = (MismatchedTokenException) resultsError
				.elementAt(0);
		assertTrue(mte.line == 2);
		assertTrue(mte.charPositionInLine == 23);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:23 mismatched input 'bit2' expecting TOK_RB"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	public void counter_specification_25() {
		String to_parse = "CTL*SPEC\n"
				+ "	A [ bit0.value U 0..2 bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_25() throws Exception {
		assertTrue(resultsError.size() == 1);
		assertTrue(resultsError.elementAt(0) instanceof MismatchedTokenException);
		MismatchedTokenException mte = (MismatchedTokenException) resultsError
				.elementAt(0);
		assertTrue(mte.line == 2);
		assertTrue(mte.charPositionInLine == 23);

		assertTrue(resultsErrorMsg.size() == 1);
		assertTrue(resultsErrorMsg.elementAt(0).equals(
				"line 2:23 mismatched input 'bit2' expecting TOK_RB"));

		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me == null);
	}

	@Test
	public void counter_specification_26() {
		String to_parse = "LTLSPEC\n" + "	G (!bit2.carry_out)\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_26() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(!me.isRealTimeCTLSpec());
		assertTrue(me.isLTLSpec());
		assertTrue(me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.GLOBALLY);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 1);
		Spec son = me_exp.getChildren()[0];
		assertTrue(son != null);
		assertTrue(son != null);
		assertTrue(son.isPropSpec());
		assertTrue(son.isCTLSpec());
		assertTrue(son.isRealTimeCTLSpec());
		assertTrue(son.isLTLSpec());
		assertTrue(son.isFutureLTLSpec());
		assertTrue(son.isPastLTLSpec());
		assertTrue(son.isCTLStarSpec());
		assertTrue(!son.hasTemporalOperators());
		assertTrue(son instanceof SpecBDD);
	}

	@Test
	public void counter_specification_27() {
		String to_parse = "CTLSPEC\n"
				+ "	A [ bit0.value BU 1..2 bit2.value ];\n";
		results = Env.loadSpecString(to_parse);
	}

	public void check_27() throws Exception {
		assertTrue(resultsError.size() == 0);
		assertTrue(resultsErrorMsg.size() == 0);
		assertTrue(results != null);
		assertTrue(results.length == 1);
		Spec me = results[0];
		assertTrue(me != null);
		assertTrue(!me.isPropSpec());
		assertTrue(!me.isCTLSpec());
		assertTrue(me.isRealTimeCTLSpec());
		assertTrue(!me.isLTLSpec());
		assertTrue(!me.isFutureLTLSpec());
		assertTrue(!me.isPastLTLSpec());
		assertTrue(me.isCTLStarSpec());
		assertTrue(me.hasTemporalOperators());
		assertTrue(me instanceof SpecExp);

		// a more specific asserts.
		SpecExp me_exp = (SpecExp) me;
		assertTrue(me_exp.getOperator() == Operator.ABU);
		assertTrue(me_exp.getChildren() != null);
		assertTrue(me_exp.getChildren().length == 3);
		Spec son1 = me_exp.getChildren()[0];
		Spec son2 = me_exp.getChildren()[1];
		Spec son3 = me_exp.getChildren()[2];

		assertTrue(son1 != null);
		assertTrue(son1.isPropSpec());
		assertTrue(son1.isCTLSpec());
		assertTrue(son1.isRealTimeCTLSpec());
		assertTrue(son1.isLTLSpec());
		assertTrue(son1.isFutureLTLSpec());
		assertTrue(son1.isPastLTLSpec());
		assertTrue(son1.isCTLStarSpec());
		assertTrue(!son1.hasTemporalOperators());
		assertTrue(son1 instanceof SpecBDD);

		assertTrue(son2 != null);
		assertTrue(son2.isPropSpec());
		assertTrue(son2.isCTLSpec());
		assertTrue(son2.isRealTimeCTLSpec());
		assertTrue(son2.isLTLSpec());
		assertTrue(son2.isFutureLTLSpec());
		assertTrue(son2.isPastLTLSpec());
		assertTrue(son2.isCTLStarSpec());
		assertTrue(!son2.hasTemporalOperators());
		assertTrue(son2 instanceof SpecCTLRange);

		assertTrue(son3 != null);
		assertTrue(son3.isPropSpec());
		assertTrue(son3.isCTLSpec());
		assertTrue(son3.isRealTimeCTLSpec());
		assertTrue(son3.isLTLSpec());
		assertTrue(son3.isFutureLTLSpec());
		assertTrue(son3.isPastLTLSpec());
		assertTrue(son3.isCTLStarSpec());
		assertTrue(!son3.hasTemporalOperators());
		assertTrue(son3 instanceof SpecBDD);
	}

}