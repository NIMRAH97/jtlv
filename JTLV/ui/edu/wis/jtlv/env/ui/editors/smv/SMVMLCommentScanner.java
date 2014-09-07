package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

public class SMVMLCommentScanner extends RuleBasedScanner {
	private static String[] fgKeywords = { "TODO", "FIXME" };

	public SMVMLCommentScanner(SMVColorProvider provider) {
		IToken ml_keyword = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.MULTI_LINE_KEYWORD), null, SWT.BOLD));
		// the default here is a multi line comment
		IToken ml_comment = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.MULTI_LINE_COMMENT)));
		IRule[] rules = new IRule[1];
		// Add word rule for keywords in comments.
		WordRule wordRule = new WordRule(new SMVWordDetector(), ml_comment);
		for (int i = 0; i < fgKeywords.length; i++)
			wordRule.addWord(fgKeywords[i], ml_keyword);
		rules[0] = wordRule;
		setRules(rules);
	}

	protected class SMVWordDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}
	}

}
