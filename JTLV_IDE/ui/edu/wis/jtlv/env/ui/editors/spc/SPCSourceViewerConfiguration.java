package edu.wis.jtlv.env.ui.editors.spc;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import edu.wis.jtlv.env.ui.JTLVActivator;

public class SPCSourceViewerConfiguration extends SourceViewerConfiguration {
	private SPCCodeScanner scanner;
	private SPCMLCommentScanner ml_scanner;
	private static SPCColorProvider colorManager = new SPCColorProvider();

	public SPCSourceViewerConfiguration() {
	}

	protected SPCCodeScanner getSPCCodeScanner() {
		if (scanner == null) {
			scanner = new SPCCodeScanner(
					SPCSourceViewerConfiguration.colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(SPCColorProvider.DEFAULT))));
		}
		return scanner;
	}

	protected SPCMLCommentScanner getSPCMLCommantScanner() {
		if (ml_scanner == null) {
			ml_scanner = new SPCMLCommentScanner(
					SPCSourceViewerConfiguration.colorManager);
			ml_scanner
					.setDefaultReturnToken(new Token(
							new TextAttribute(
									colorManager
											.getColor(SPCColorProvider.MULTI_LINE_COMMENT))));
		}
		return ml_scanner;
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				getSPCCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		DefaultDamagerRepairer mldr = new DefaultDamagerRepairer(
				getSPCMLCommantScanner());
		reconciler.setDamager(mldr, SPCPartitionScanner.MULTILINE_COMMENT);
		reconciler.setRepairer(mldr, SPCPartitionScanner.MULTILINE_COMMENT);

		return reconciler;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// partitioning between multiline comment section and simple code.
	@Override
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		return JTLVActivator.SPC_PARTITIONING;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE,
				SPCPartitionScanner.MULTILINE_COMMENT };
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		MonoReconciler reconciler = new MonoReconciler(
				new SPCReconcilingStrategy(), false);
		reconciler.install(sourceViewer);
		return reconciler;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// auto edit... e.g. indentation.
	// public IAutoEditStrategy[] getAutoEditStrategies(
	// ISourceViewer sourceViewer, String contentType) {
	// IAutoEditStrategy strategy = (IDocument.DEFAULT_CONTENT_TYPE
	// .equals(contentType) ? new SPCAutoEditStrategy()
	// : new DefaultIndentLineAutoEditStrategy());
	// return new IAutoEditStrategy[] { strategy };
	// }
}