package edu.wis.jtlv.env.ui.editors.smv;

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

public class SMVCodeScanner extends RuleBasedScanner {
	private static String[] fgKeywords = { "MODULE", "process", "DEFINE",
			"VAR", "IVAR", "INIT", "TRANS", "INVAR", "SPEC", "CTLSPEC",
			"LTLSPEC", "PSLSPEC", "COMPUTE", "INVARSPEC", "CONSTRAINT",
			"CONSTANTS", "SIMPWFF", "CTLWFF", "LTLWFF", "COMPWFF", "FAIRNESS",
			"JUSTICE", "COMPASSION", "ISA", "ASSIGN", "GOTO", "GAME",
			"PLAYER_1", "PLAYER_2", "REACHTARGET", "AVOIDTARGET",
			"REACHDEADLOCK", "AVOIDDEADLOCK", "BUCHIGAME", "GENREACTIVITY",
			"PRED", "PREDICATES", "MIRROR", "IN",

			"array", "of", "boolean", "bool", "integer", "Integer", "real",
			"Real", "word1", "word", "Word", "running",

			"next", "init", "self", "case", "esac", "for"};

	private static String[] fgOperatorKeywords = { "READ", "WRITE", "mod",
			"union", "in", "xor", "xnor",

			"EX", "AX", "EF", "AF", "EG", "AG", "E", "F", "O", "G", "H", "X",
			"Y", "Z", "A", "U", "S", "V", "T", "BU", "EBF", "ABF", "EBG",
			"ABG", "MIN", "MAX" };

	private static String[] fgSignOperatorKeywords = { ":=", "+", "-", "*",
			"/", "<<", ">>", "<<<", ">>>", "=", "!=", "<=", ">=", "<", ">",
			"->", "<->", "|", "&", "!" };

	private static String[] fgValueKeywords = { "FALSE", "TRUE" };

	public SMVCodeScanner(SMVColorProvider provider) {
		IToken keyword = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.KEYWORD), null, SWT.BOLD));
		IToken values = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.VALUE), null, SWT.BOLD));
		IToken operators = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.OPERATOR), null, SWT.BOLD));
		IToken sign_operators = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.OPERATOR), null, SWT.ITALIC));
		IToken comment = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.SINGLE_LINE_COMMENT)));
		// IToken mlcomment = new Token(new TextAttribute(provider
		// .getColor(SMVColorProvider.SINGLE_LINE_COMMENT)));
		IToken other = new Token(new TextAttribute(provider
				.getColor(SMVColorProvider.DEFAULT)));
		IRule[] rules = new IRule[5];
		// Add rule for single line comments.
		rules[0] = new EndOfLineRule("//", comment);
		rules[1] = new EndOfLineRule("--", comment);
		// Add rule for multi line comments.
		// rules[2] = new MultiLineRule("/*", "*/", mlcomment);
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new SMVWhitespaceDetector());
		WordRule spWordRule = new WordRule(new SMVSpecialCharDetector(), other);
		// Add word rule for keywords.
		WordRule wordRule = new WordRule(new SMVWordDetector(), other);
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

	protected class SMVWhitespaceDetector implements IWhitespaceDetector {
		public boolean isWhitespace(char c) {
			return Character.isWhitespace(c);
		}
	}

	protected class SMVWordDetector implements IWordDetector {
		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}
	}

	protected class SMVSpecialCharDetector implements IWordDetector {
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
