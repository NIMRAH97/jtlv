package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
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

public class SMVSourceViewerConfiguration extends SourceViewerConfiguration {
	private SMVCodeScanner scanner;
	private SMVMLCommentScanner ml_scanner;
	private static SMVColorProvider colorManager = new SMVColorProvider();

	public SMVSourceViewerConfiguration() {
	}

	protected SMVCodeScanner getSMVCodeScanner() {
		if (scanner == null) {
			scanner = new SMVCodeScanner(
					SMVSourceViewerConfiguration.colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(SMVColorProvider.DEFAULT))));
		}
		return scanner;
	}

	protected SMVMLCommentScanner getSMVMLCommantScanner() {
		if (ml_scanner == null) {
			ml_scanner = new SMVMLCommentScanner(
					SMVSourceViewerConfiguration.colorManager);
			ml_scanner
					.setDefaultReturnToken(new Token(
							new TextAttribute(
									colorManager
											.getColor(SMVColorProvider.MULTI_LINE_COMMENT))));
		}
		return ml_scanner;
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				getSMVCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		DefaultDamagerRepairer mldr = new DefaultDamagerRepairer(
				getSMVMLCommantScanner());
		reconciler.setDamager(mldr, SMVPartitionScanner.MULTILINE_COMMENT);
		reconciler.setRepairer(mldr, SMVPartitionScanner.MULTILINE_COMMENT);

		return reconciler;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// partitioning between multiline comment section and simple code.
	@Override
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		return JTLVActivator.SMV_PARTITIONING;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE,
				SMVPartitionScanner.MULTILINE_COMMENT };
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		MonoReconciler reconciler = new MonoReconciler(
				new SMVReconcilingStrategy(), false);
		reconciler.install(sourceViewer);
		return reconciler;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// auto edit... e.g. indentation.
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		IAutoEditStrategy strategy = (IDocument.DEFAULT_CONTENT_TYPE
				.equals(contentType) ? new SMVAutoEditStrategy()
				: new DefaultIndentLineAutoEditStrategy());
		return new IAutoEditStrategy[] { strategy };
	}
}