package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

public class SMVEditor extends TextEditor {

	public SMVEditor() {
		super();
		setDocumentProvider(new SMVDocumentProvider());
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setSourceViewerConfiguration(new SMVSourceViewerConfiguration());
		// ...
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		fAnnotationAccess = getAnnotationAccess();
		fOverviewRuler = createOverviewRuler(getSharedColors());

		SMVSourceViewer viewer = new SMVSourceViewer(parent, ruler,
				fOverviewRuler, isOverviewRulerVisible(), styles);
		fSourceViewerDecorationSupport = new SourceViewerDecorationSupport(
				viewer, fOverviewRuler, fAnnotationAccess, getSharedColors());
		configureSourceViewerDecorationSupport(fSourceViewerDecorationSupport);
		return viewer;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	
	private static IDocument currDoc;
	
	public static IDocument getDocumentCurrDoc() {
		return currDoc;
	}
	
	@Override
	public void updatePartControl(IEditorInput input) {
		super.updatePartControl(input);
		currDoc = this.getDocumentProvider().getDocument(this.getEditorInput());
	}

}
