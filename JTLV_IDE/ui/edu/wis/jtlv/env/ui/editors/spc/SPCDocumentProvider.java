package edu.wis.jtlv.env.ui.editors.spc;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import edu.wis.jtlv.env.ui.JTLVActivator;

public class SPCDocumentProvider extends FileDocumentProvider {

	@Override
	protected IDocument createDocument(Object input) throws CoreException {
		IDocument document = super.createDocument(input);
		if (document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(
					JTLVActivator.getDefault().getSPCPartitionScanner(),
					SPCPartitionScanner.PARTITION_TYPES);
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}

	@Override
	protected IDocument createEmptyDocument() {
		return new SPCDocument();
	}

}