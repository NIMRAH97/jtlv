package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.ui.editors.text.TextEditor;

public class FDSEditor extends TextEditor {
	public FDSEditor() {
		super();
		setSourceViewerConfiguration(new FDSConfiguration());
	}

	protected void createActions() {
		super.createActions();
		
//		IAction a= new TextOperationAction(
//				JavaEditorMessages.getResourceBundle(), "ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS); //$NON-NLS-1$
//		a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
//		setAction("ContentAssistProposal", a); 
//			
//		a= new TextOperationAction(JavaEditorMessages.getResourceBundle(), "ContentAssistTip.", this, ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION);  //$NON-NLS-1$
//		a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
//		setAction("ContentAssistTip", a); 
	}
}
