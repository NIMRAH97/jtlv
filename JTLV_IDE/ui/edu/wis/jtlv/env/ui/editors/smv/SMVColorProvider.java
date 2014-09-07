package edu.wis.jtlv.env.ui.editors.smv;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SMVColorProvider {
	public static final RGB MULTI_LINE_COMMENT = new RGB(0, 128, 0);
	public static final RGB MULTI_LINE_KEYWORD = new RGB(0, 128, 128);
	public static final RGB SINGLE_LINE_COMMENT = new RGB(0, 128, 0);
	public static final RGB KEYWORD = new RGB(128, 0, 0);
	public static final RGB TYPE = new RGB(128, 128, 0);
	public static final RGB VALUE = new RGB(0, 128, 128);
	public static final RGB OPERATOR = new RGB(128, 0, 128);
	public static final RGB DEFAULT = new RGB(0, 0, 0);

	protected Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	public void dispose() {
		Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext())
			e.next().dispose();
	}

	public Color getColor(RGB rgb) {
		Color color = fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}

}
