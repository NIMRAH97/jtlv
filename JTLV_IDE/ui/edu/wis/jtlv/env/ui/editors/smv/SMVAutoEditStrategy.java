package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;

public class SMVAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {

//	private void autoIndentAfterNewLine(IDocument d, DocumentCommand c) {
//
//		if (c.offset == -1 || d.getLength() == 0)
//			return;
//
//		try {
//			// find start of line
//			int p = (c.offset == d.getLength() ? c.offset - 1 : c.offset);
//			IRegion info = d.getLineInformationOfOffset(p);
//			int start = info.getOffset();
//
//			// find white spaces
//			int end = findEndOfWhiteSpace(d, start, c.offset);
//
//			StringBuffer buf = new StringBuffer(c.text);
//			if (end > start) {
//				// append to input
//				buf.append(d.get(start, end - start));
//			}
//
//			c.text = buf.toString();
//
//		} catch (BadLocationException excp) {
//			// stop work
//		}
//	}

	@Override
	public void customizeDocumentCommand(IDocument document,
			DocumentCommand command) {
		try {
			// if (command.text.equals("\n")) {
			// command.text
			// } else
			if (suffixOf(document, command, "/*")) {
				command.text = "*  */";
				shiftCommand(command, 2);
			} else if (suffixOfMainDeclaration(document, command)) {
				MainDeclarationWithNewLine(document, command);
				command.text += "\n\t";
				shiftCommand(command, 3);
			} else if (suffixOfDeclaration(document, command)) {
				DeclarationWithNewLine(document, command);
				command.text += "\n\t\t";
				shiftCommand(command, 4);
			} else {
				super.customizeDocumentCommand(document, command);
//				if (command.length == 0
//						&& command.text != null
//						&& TextUtilities.endsWith(document
//								.getLegalLineDelimiters(), command.text) != -1)
//					autoIndentAfterNewLine(document, command);
			}
		} catch (BadLocationException e) {
			// wont do anything... i.e. won't auto editing
		}
	}

	private static String[] fgDeclarations = { "DEFINE", "VAR", "IVAR", "INIT",
			"TRANS", "INVAR", "SPEC", "CTLSPEC", "LTLSPEC", "PSLSPEC",
			"COMPUTE", "INVARSPEC", "CONSTRAINT", "CONSTANTS", "SIMPWFF",
			"CTLWFF", "LTLWFF", "COMPWFF", "FAIRNESS", "JUSTICE", "COMPASSION",
			"ISA", "ASSIGN", "REACHTARGET", "AVOIDTARGET", "REACHDEADLOCK",
			"AVOIDDEADLOCK", "BUCHIGAME", "GENREACTIVITY", "PRED",
			"PREDICATES", "MIRROR" };

	private static String[] fgMainDeclarations = { "MODULE", "GAME",
			"PLAYER_1", "PLAYER_2" };

	private boolean suffixOfMainDeclaration(IDocument document,
			DocumentCommand command) throws BadLocationException {
		for (String str : SMVAutoEditStrategy.fgMainDeclarations)
			if (suffixOf(document, command, str))
				return true;
		return false;
	}

	private void MainDeclarationWithNewLine(IDocument document,
			DocumentCommand command) throws BadLocationException {
		for (String str : SMVAutoEditStrategy.fgMainDeclarations)
			if (suffixOf(document, command, str)) {
				int line = document.getLineOfOffset(command.offset);
				int line_offset = document.getLineOffset(line);
				int prefix_len = str.length() - 1;

				String prefix = document.get(line_offset, command.offset
						- line_offset);
				String exp_prefix = str.substring(0, prefix_len);
				boolean is_good_line = exp_prefix.equals(prefix);

				// making the new string
				String str_prefix = (is_good_line ? "" : "\n");
				str_prefix += str.substring(0, prefix_len);
				command.offset = command.offset - prefix_len;
				document.replace(command.offset, prefix_len, str_prefix);
				command.offset = command.offset + prefix_len
						+ (is_good_line ? 0 : 1);
				return;
			}
	}

	private boolean suffixOfDeclaration(IDocument document,
			DocumentCommand command) throws BadLocationException {
		for (String str : SMVAutoEditStrategy.fgDeclarations)
			if (suffixOf(document, command, str))
				return true;
		return false;
	}

	private void DeclarationWithNewLine(IDocument document,
			DocumentCommand command) throws BadLocationException {
		for (String str : SMVAutoEditStrategy.fgDeclarations)
			if (suffixOf(document, command, str)) {
				int line = document.getLineOfOffset(command.offset);
				int line_offset = document.getLineOffset(line);
				int prefix_len = str.length() - 1;

				String prefix = document.get(line_offset, command.offset
						- line_offset);
				String exp_prefix = "\t" + str.substring(0, prefix_len);
				boolean is_good_line = exp_prefix.equals(prefix);

				// maybe just fix line
				// int start_prefix_offset = command.offset - prefix_len;
				// if (this.findEndOfWhiteSpace(document, line_offset,
				// start_prefix_offset) == start_prefix_offset) {
				// document.replace(line_offset, start_prefix_offset
				// - line_offset + 1, "");
				// is_good_line = true;
				// }

				// making the new string
				String str_prefix = (is_good_line ? "" : "\n\t");
				str_prefix += str.substring(0, prefix_len);
				command.offset = command.offset - prefix_len;
				document.replace(command.offset, prefix_len, str_prefix);
				command.offset = command.offset + prefix_len
						+ (is_good_line ? 0 : 2);
				return;
			}
	}

	private boolean suffixOf(IDocument document, DocumentCommand command,
			String compare) throws BadLocationException {
		// the last char has yet been entered.
		int before_length = compare.length() - 1;
		String before = document.get(command.offset - before_length - 1, 1);
		String prefix = document.get(command.offset - before_length,
				before_length);
		String last_char = command.text;

		String compare_prefix = compare.substring(0, before_length);
		String compare_last_char = compare.substring(before_length);

		return Character.isWhitespace(before.charAt(0))
				&& prefix.equals(compare_prefix)
				&& last_char.equals(compare_last_char);
	}

	private void shiftCommand(DocumentCommand command, int offset) {
		// puts the caret between both the quotes
		command.caretOffset = command.offset + offset;
		command.shiftsCaret = false;
	}

	public String getIndentOfLine(IDocument document, int line)
			throws BadLocationException {
		if (line > -1) {
			int start = document.getLineOffset(line);
			int end = start + document.getLineLength(line) - 1;
			int whiteend = findEndOfWhiteSpace(document, start, end);
			return document.get(start, whiteend - start);
		} else {
			return "";
		}
	}
}
