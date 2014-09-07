package edu.wis.jtlv.env.ui.editors.spc;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

public class SPCCodeScanner extends RuleBasedScanner {
	private static String[] fgKeywords = { "SPEC", "CTLSPEC", "LTLSPEC",
			"CTL*SPEC", "INVARSPEC",

			"bool", "word1", "word", "Word", "READ", "WRITE",

			"next", "case", "esac" };

	private static String[] fgOperatorKeywords = { "mod", "union", "in", "xor",
			"xnor",

			"EX", "AX", "EF", "AF", "EG", "AG", "E", "F", "EVENTUALLY",
			"FINALLY", "O", "ONCE", "G", "ALWAYS", "GLOBALLY", "H",
			"HISTORICALLY", "X", "NEXT", "Y", "PREV", "Z", "A", "U", "UNTIL",
			"S", "SINCE", "V", "RELEASES", "T", "TRIGGERED", "BU", "EBF",
			"ABF", "EBG", "ABG", "Until", "Since", "Awaits", "Backto" };

	private static String[] fgSignOperatorKeywords = { "+", "-", "*", "/",
			"<<", ">>", "=", "!=", "<=", ">=", "<", ">", "->", "<->", "|", "&",
			"!", "..",

			"<>", "<_>", "[]", "[_]", "()", "(_)" };

	private static String[] fgValueKeywords = { "FALSE", "TRUE" };

	public SPCCodeScanner(SPCColorProvider provider) {
		IToken keyword = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.KEYWORD), null, SWT.BOLD));
		IToken values = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.VALUE), null, SWT.BOLD));
		IToken operators = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.OPERATOR), null, SWT.BOLD));
		IToken sign_operators = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.OPERATOR), null, SWT.ITALIC));
		// .getColor(SPCColorProvider.OPERATOR)));
		IToken comment = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.SINGLE_LINE_COMMENT)));
		// IToken mlcomment = new Token(new TextAttribute(provider
		// .getColor(SMVColorProvider.SINGLE_LINE_COMMENT)));
		IToken other = new Token(new TextAttribute(provider
				.getColor(SPCColorProvider.DEFAULT)));
		IRule[] rules = new IRule[5];
		// Add rule for single line comments.
		rules[0] = new EndOfLineRule("//", comment);
		rules[1] = new EndOfLineRule("--", comment);
		// Add rule for multi line comments.
		// rules[2] = new MultiLineRule("/*", "*/", mlcomment);
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new SPCWhitespaceDetector());
		// Add word rule for keywords.
		WordRule wordRule = new WordRule(new SPCWordDetector(), other);
		WordRule spWordRule = new WordRule(new SPCSpecialCharDetector(), other);
		for (int i = 0; i < fgKeywords.length; i++)
			wordRule.addWord(fgKeywords[i], keyword);
		for (int i = 0; i < fgValueKeywords.length; i++)
			wordRule.addWord(fgValueKeywords[i], values);
		for (int i = 0; i < fgOperatorKeywords.length; i++)
			wordRule.addWord(fgOperatorKeywords[i], operators);
		for (int i = 0; i < fgSignOperatorKeywords.length; i++)
			spWordRule.addWord(fgSignOperatorKeywords[i], sign_operators);
		rules[3] = wordRule;
		rules[4] = spWordRule;

		setRules(rules);
	}

	protected class SPCWhitespaceDetector implements IWhitespaceDetector {
		public boolean isWhitespace(char c) {
			return Character.isWhitespace(c);
		}
	}

	protected class SPCWordDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c) | c == '*';
		}
	}

	protected class SPCSpecialCharDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return (c == '+') | (c == '-') | (c == '*') | (c == '/')
					| (c == '<') | (c == '>') | (c == '=') | (c == '!')
					| (c == '>') | (c == '\'') | (c == '&') | (c == '.');
		}

		public boolean isWordPart(char c) {
			return (c == '+') | (c == '-') | (c == '*') | (c == '/')
					| (c == '<') | (c == '>') | (c == '=') | (c == '!')
					| (c == '>') | (c == '\'') | (c == '&') | (c == '.');
		}
	}

}
