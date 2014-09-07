package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class FDSConfiguration extends SourceViewerConfiguration {
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler pr = new PresentationReconciler();
		DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(new FDSScanner());
		pr.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		pr.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		return pr;
	}

//	public IContentAssistant getContentAssistant(ISourceViewer sv) {
//		ContentAssistant assistant= new ContentAssistant();
//		assistant.setContentAssistProcessor(new FDSCompletionProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
//		
//		return assistant;
		
		
//		ContentAssistant ca = new ContentAssistant();
//		IContentAssistProcessor cap = new FDSCompletionProcessor();
//		ca.setContentAssistProcessor(cap, IDocument.DEFAULT_CONTENT_TYPE);
		//ca.setInformationControlCreator(getInformationControlCreator(sv));
//		return ca;
//	}

//	public ITextHover getTextHover(ISourceViewer sv, String contentType) {
//		return '''new TextHover()''';
//	}
}
