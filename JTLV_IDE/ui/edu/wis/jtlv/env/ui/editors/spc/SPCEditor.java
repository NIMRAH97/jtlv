package edu.wis.jtlv.env.ui.editors.spc;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

public class SPCEditor extends TextEditor {
	private boolean didFirstChecked;

	public SPCEditor() {
		super();
		setDocumentProvider(new SPCDocumentProvider());
		this.didFirstChecked = false;
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setSourceViewerConfiguration(new SPCSourceViewerConfiguration());
		// ...
	}

	@Override
	public void dispose() {
		super.dispose();
		fileAnnotationModel.remove(this.getEditorInput());
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		fAnnotationAccess = getAnnotationAccess();
		fOverviewRuler = createOverviewRuler(getSharedColors());

		SPCSourceViewer viewer = new SPCSourceViewer(parent, ruler,
				fOverviewRuler, isOverviewRulerVisible(), styles);
		fSourceViewerDecorationSupport = new SourceViewerDecorationSupport(
				viewer, fOverviewRuler, fAnnotationAccess, getSharedColors());
		configureSourceViewerDecorationSupport(fSourceViewerDecorationSupport);
		return viewer;
	}

	// ////////////////////////////////////////////////////////////////////////
	// Monitoring the annotationModel per file ////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	private static HashMap<IFile, IAnnotationModel> fileAnnotationModel = new HashMap<IFile, IAnnotationModel>();

	public static IAnnotationModel getCurrAnnotationModel(IFile file) {
		return fileAnnotationModel.get(file);
	}

	@Override
	public void updatePartControl(IEditorInput input) {
		super.updatePartControl(input);
		
		//super.performSaveAs(progressMonitor)
		if (input instanceof IFileEditorInput) {
			IFileEditorInput finput = (IFileEditorInput) input;
			if (fileAnnotationModel.containsKey(finput.getFile()))
				fileAnnotationModel.remove(finput.getFile());
			// adding this file...
			fileAnnotationModel.put(finput.getFile(), this
					.getDocumentProvider().getAnnotationModel(finput));

			// if this is the first time loaded, then building.
			if (!didFirstChecked)
				try {
					finput.getFile().getProject().build(
							IncrementalProjectBuilder.FULL_BUILD, null);
				} catch (CoreException e) {
				}
			didFirstChecked = true;
		}
	}

	public static IFile[] getAllAssociatedFiles() {   
		Set<IFile> res_col = fileAnnotationModel.keySet();
		IFile[] res = new IFile[res_col.size()];
		res_col.toArray(res);
		return res;
	}
}
