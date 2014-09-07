package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class FDSColorManager {
	public Color KEYWORD;
	public Color COMMENT;
	public Color STRING;
	public Color VALUE;
	public String[] keywords;
	public String[] values;

	public FDSColorManager() {
		COMMENT = new Color(Display.getCurrent(), new RGB(0, 128, 0));
		STRING = new Color(Display.getCurrent(), new RGB(0, 0, 128));
		
		VALUE = new Color(Display.getCurrent(), new RGB(0, 128, 128));
		values = new String[4];
		values[0] = "TRUE";
		values[1] = "T";
		values[2] = "FALSE";
		values[3] = "F";
		
		KEYWORD = new Color(Display.getCurrent(), new RGB(128, 0, 0));
		keywords = new String[23];
		keywords[0] = "MODULE";
		keywords[1] = "Module";
		keywords[2] = "PLAYER";
		keywords[3] = "Player";
		keywords[4] = "VAR";
		keywords[5] = "Var";
		keywords[6] = "INIT";
		keywords[7] = "Init";
		keywords[8] = "TRANS";
		keywords[9] = "Trans";
		keywords[10] = "JUST";
		keywords[11] = "Just";
		keywords[12] = "JUSTICE";
		keywords[13] = "Justice";
		keywords[14] = "COMP";
		keywords[15] = "Comp";
		keywords[16] = "COMPASSION";
		keywords[17] = "Compassion";
		keywords[18] = "next";
		keywords[19] = "and";
		keywords[20] = "or";
		keywords[21] = "bool";
		keywords[22] = "boolean";
//		keywords[23] = "";
//		keywords[24] = "";
//		keywords[25] = "";
//		keywords[26] = "";
//		keywords[27] = "";
//		keywords[28] = "";
//		keywords[29] = "";
	}

}
