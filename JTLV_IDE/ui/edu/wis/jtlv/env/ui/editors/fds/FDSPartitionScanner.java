package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class FDSPartitionScanner extends RuleBasedPartitionScanner {
	public final static String MULTILINE_COMMENT = "multiline_comment";
	public final static String SINGLELINE_COMMENT = "singleline_comment";
	public final static String[] PARTITION_TYPES = new String[] {
			MULTILINE_COMMENT, SINGLELINE_COMMENT };

	public FDSPartitionScanner() {
		super();
		IToken multilinecomment = new Token(MULTILINE_COMMENT);
		IToken singlelinecomment = new Token(SINGLELINE_COMMENT);
		IPredicateRule[] rules = new IPredicateRule[3];
		// Add rule for single line comments.
		rules[0] = new EndOfLineRule("//", singlelinecomment);
		rules[1] = new EndOfLineRule("--", singlelinecomment);
		// Add rules for multi-line comments and javadoc.
		rules[2] = new MultiLineRule("/*", "*/", multilinecomment, (char) 0,
				true);
		setPredicateRules(rules);
	}
}
