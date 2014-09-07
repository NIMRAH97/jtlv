package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

public class FDSScanner extends RuleBasedScanner {
	private static FDSColorManager color_manager = null;

	public FDSScanner() {
		if (color_manager == null) {
			color_manager = new FDSColorManager();
		}

		// comments...
		Token comment = new Token(new TextAttribute(color_manager.COMMENT));
		EndOfLineRule comment1_rule = new EndOfLineRule("//", comment);
		EndOfLineRule comment2_rule = new EndOfLineRule("--", comment);
		MultiLineRule multi_comment_rule = new MultiLineRule("/*", "*/",
				comment);

		// values...
		Token value = new Token(new TextAttribute(color_manager.VALUE));
		// stills the process id num...
		// FDSValueRule numeric_value_rule = new FDSValueRule(value);
		WordRule value_rules = new WordRule(new FDSWordDetector());
		for (int n = 0; n < color_manager.values.length; n++) {
			value_rules.addWord(color_manager.values[n], value);
		}

		// keywords...
		Token keyword = new Token(new TextAttribute(color_manager.KEYWORD,
				null, SWT.BOLD));
		WordRule keyword_rules = new WordRule(new FDSWordDetector());
		for (int n = 0; n < color_manager.keywords.length; n++) {
			keyword_rules.addWord(color_manager.keywords[n], keyword);
		}

		// whitespace...
		WhitespaceRule whitespace_rule = new WhitespaceRule(
				new FDSWhitespaceDetector());

		// strings... (not really needed)
		Token string = new Token(new TextAttribute(color_manager.STRING));
		SingleLineRule string1_rule = new SingleLineRule("\"", "\"", string,
				'\\');
		// used as prime in jtlv...
		// SingleLineRule string2_rule = new SingleLineRule("'", "'", string,
		// '\\');

		// adding everything...
		IRule[] rules = new IRule[] { comment1_rule, comment2_rule,
				multi_comment_rule, value_rules, keyword_rules,
				whitespace_rule, string1_rule };
		setRules(rules);
	}

	protected class FDSWhitespaceDetector implements IWhitespaceDetector {
		public boolean isWhitespace(char c) {
			return Character.isWhitespace(c);
		}
	}

	protected class FDSWordDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}
	}
}