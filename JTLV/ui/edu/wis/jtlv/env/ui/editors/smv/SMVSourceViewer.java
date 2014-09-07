package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;

public class SMVSourceViewer extends SourceViewer {
	// private IVerticalRuler ruler;

	public SMVSourceViewer(Composite parent, IVerticalRuler ruler,
			IOverviewRuler overview_ruler, boolean is_overview_ruler, int styles) {
		super(parent, ruler, overview_ruler, is_overview_ruler, styles);
	}

	// @Override
	// public void setDocument(IDocument document, IAnnotationModel
	// annotationModel) {
	// super.setDocument(document, annotationModel);
	// annotationModel.addAnnotation(new
	// AnnotationBag("org.eclipse.core.resources.problemmarker"), new Position(
	// 5, 20));
	// this.getAnnotationModel().addAnnotation(new
	// AnnotationBag("org.eclipse.core.resources.problemmarker"), new Position(
	// 5, 20));
	//		
	// if (this.getVerticalRuler() != null)
	// this.getVerticalRuler().setModel(this.getAnnotationModel());
	// // if (annotationModel instanceof ResourceMarkerAnnotationModel) {
	// // Env.registerErrorListener(new SMVErrorListener(document,
	// // (ResourceMarkerAnnotationModel) annotationModel, this
	// // .getVerticalRuler()));
	// // }
	//
	// }

	public void setDocument(IDocument document,
			IAnnotationModel annotationModel, int modelRangeOffset,
			int modelRangeLength) {
		super.setDocument(document, annotationModel, modelRangeOffset,
				modelRangeLength);
//		allDocs.add(document);
	}
	
//	private static Vector<IDocument> allDocs;
//	public static IDocument getDocumentForFile(IFile file) {
//		for (IDocument a_doc : allDocs) {
//			if (file.getFullPath() == a_do)
//		}
//		return null;
//	}

}
