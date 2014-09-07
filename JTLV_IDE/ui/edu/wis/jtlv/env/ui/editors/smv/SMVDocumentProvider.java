package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import edu.wis.jtlv.env.ui.JTLVActivator;

public class SMVDocumentProvider extends FileDocumentProvider {

	@Override
	protected IDocument createDocument(Object input) throws CoreException {
		// IDocument document = super.createDocument(input);
		// // we will look at document partitions later in this tutorial
		// if (document instanceof IDocumentExtension3) {
		// IDocumentExtension3 extension3 = (IDocumentExtension3) document;
		// IDocumentPartitioner partitioner = new FastPartitioner(
		// JTLVActivator.getDefault().getSMVPartitionScanner(),
		// SMVPartitionScanner.PARTITION_TYPES);
		// extension3.setDocumentPartitioner(JTLVActivator.SMV_PARTITIONING,
		// partitioner);
		// partitioner.connect(document);
		// }
		// return document;

		IDocument document = super.createDocument(input);
		if (document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(
					JTLVActivator.getDefault().getSMVPartitionScanner(),
					SMVPartitionScanner.PARTITION_TYPES);
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}

	@Override
	protected IDocument createEmptyDocument() {
		return new SMVDocument();
	}
	
}