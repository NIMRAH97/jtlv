package edu.wis.jtlv.env.ui.editors.spc;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;

public class SPCSourceViewer extends SourceViewer {
	// private IVerticalRuler ruler;

	public SPCSourceViewer(Composite parent, IVerticalRuler ruler,
			IOverviewRuler overview_ruler, boolean is_overview_ruler, int styles) {
		super(parent, ruler, overview_ruler, is_overview_ruler, styles);
	}

	public void setDocument(IDocument document,
			IAnnotationModel annotationModel, int modelRangeOffset,
			int modelRangeLength) {
		super.setDocument(document, annotationModel, modelRangeOffset,
				modelRangeLength);
	}

}
