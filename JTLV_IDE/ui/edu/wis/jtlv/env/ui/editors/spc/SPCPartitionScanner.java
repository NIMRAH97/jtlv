package edu.wis.jtlv.env.ui.editors.spc;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SPCPartitionScanner extends RuleBasedPartitionScanner {
	public final static String MULTILINE_COMMENT = "multiline_comment";
	public final static String[] PARTITION_TYPES = new String[] { MULTILINE_COMMENT };

	public SPCPartitionScanner() {
		super();
		IToken multiline_comment = new Token(MULTILINE_COMMENT);

		IPredicateRule[] rules = new IPredicateRule[1];
		rules[0] = new MultiLineRule("/*", "*/", multiline_comment);
		setPredicateRules(rules);
	}
}
