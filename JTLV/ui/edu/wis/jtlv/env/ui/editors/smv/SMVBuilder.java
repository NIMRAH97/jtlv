package edu.wis.jtlv.env.ui.editors.smv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.JTLVErrorListener;
import edu.wis.jtlv.env.core.smv.SMVLexer;
import edu.wis.jtlv.env.core.smv.SMVParseException;
import edu.wis.jtlv.env.core.smv.SMVParser;
import edu.wis.jtlv.env.core.smv.SMVVarWalker;
import edu.wis.jtlv.env.core.smv.StructuralInit;

public class SMVBuilder extends IncrementalProjectBuilder {

	private class SMVDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				checkSMV(resource);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				checkSMV(resource);
				break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	private class SMVResourceVisitor implements IResourceVisitor {
		private IResourceDelta[] file_arr;

		// constructor for all resources
		SMVResourceVisitor() {
			file_arr = null;
		}

		// constructor for all resources
		SMVResourceVisitor(IResourceDelta[] a_file_arr) {
			file_arr = a_file_arr;
		}

		public boolean visit(IResource resource) {
			// if this is a single file resource visitor, then checking
			// if this is the actual file... if not then returning.
			if (this.file_arr != null) {
				boolean to_check = false;
				for (int i = 0; i < this.file_arr.length; i++) {
					if (this.file_arr[i].getFullPath().equals(
							resource.getFullPath())) {
						to_check = true;
					}
				}
				if (!to_check)
					return true;
			}

			checkSMV(resource);
			// return true to continue visiting children.
			return true;
		}
	}

	private class SMVErrorReporter implements JTLVErrorListener {
		private IFile file;
		private IDocument fileDoc;

		public SMVErrorReporter(IFile a_file) {
			this.file = a_file;
			try {
				this.fileDoc = this.getDocumentForFile(this.file);
			} catch (Exception e) {
				this.fileDoc = SMVEditor.getDocumentCurrDoc();
			}
		}

		public void doError(Exception e, String msg) {
			if (e instanceof RecognitionException) {
				error((RecognitionException) e, msg);
			}
		}

		private void addMarker(RecognitionException e, String msg, int severity) {
			int line;
			if (e instanceof SMVParseException
					&& ((SMVParseException) e).hasParseInfo()) {
				line = ((SMVParseException) e).getParseInfo().row_no;
			} else {
				line = e.line;
			}

			try {
				if (line == 0)
					line = fileDoc.getLineOfOffset(fileDoc.getLength());

				int start = fileDoc.getLineOffset(line - 1)
						+ this.getIndentOfLine(fileDoc, line - 1).length();
				// e.charPositionInLine;
				int end = fileDoc.getLineOffset(line) - 1;
				SMVBuilder.this.addMarker(file, e, line, start, end, severity,
						msg);
			} catch (BadLocationException e1) {
				SMVBuilder.this.addMarker(file, e, line, severity, msg);
			}
		}

		public int lineOffset(int line) {
			return 0;
		}

		public void error(RecognitionException exception, String msg) {
			addMarker(exception, msg, IMarker.SEVERITY_ERROR);
		}

		public void fatalError(RecognitionException exception, String msg) {
			addMarker(exception, msg, IMarker.SEVERITY_ERROR);
		}

		public void warning(RecognitionException exception, String msg) {
			addMarker(exception, msg, IMarker.SEVERITY_WARNING);
		}

		private String getIndentOfLine(IDocument document, int line)
				throws BadLocationException {
			if (line > -1) {
				int start = document.getLineOffset(line);
				int end = start + document.getLineLength(line) - 1;
				int whiteend = findEndOfWhiteSpace(document, start, end);
				return document.get(start, whiteend - start);
			} else {
				return "";
			}
		}

		private int findEndOfWhiteSpace(IDocument document, int offset, int end)
				throws BadLocationException {
			while (offset < end) {
				char c = document.getChar(offset);
				if (c != ' ' && c != '\t') {
					return offset;
				}
				offset++;
			}
			return end;
		}

		private IDocument getDocumentForFile(IFile file) throws Exception {
			String charset = file.getCharset(true);
			InputStream contentStream = file.getContents();
			BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(contentStream,
					charset));

			String line = null;
			StringWriter stringWriter = new StringWriter();
			PrintWriter writer = new PrintWriter(stringWriter);

			try {
				while ((line = reader.readLine()) != null) {
					writer.println(line);
				}
			} finally {
				reader.close();
			}
			return new Document(stringWriter.toString());
		}
	}

	public static final String BUILDER_ID = "JTLV.SMVBuilder";
	public static final String MARKER_TYPE = "JTLV.SMVProblem";

	void checkSMV(IResource resource) {
		if (resource instanceof IFile && resource.getName().endsWith(".smv")) {
			IFile file = (IFile) resource;
			deleteMarkers(file);
			Env.clearErrorListener();
			Env.registerErrorListener(new SMVErrorReporter(file));
			try {
				doParse(file.getContents());
			} catch (Exception e1) {
			}
		}
	}

	private void addMarker(IFile file, RecognitionException e, int line_number,
			int start_offset, int end_offset, int severity, String msg) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (line_number == -1) {
				line_number = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, line_number);
			marker.setAttribute(IMarker.CHAR_START, start_offset);
			marker.setAttribute(IMarker.CHAR_END, end_offset);
		} catch (CoreException ce) {
		}
	}

	private void addMarker(IFile file, RecognitionException e, int line_number,
			int severity, String msg) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (line_number == -1) {
				line_number = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, line_number);
		} catch (CoreException ce) {
		}
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				// TODO: could better extract the delta...
				fullSingleFileBuild(delta.getAffectedChildren(), monitor);
				// incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SMVResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void fullSingleFileBuild(IResourceDelta[] all_files,
			final IProgressMonitor monitor) throws CoreException {
		try {
			getProject().accept(new SMVResourceVisitor(all_files));
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new SMVDeltaVisitor());
	}

	private void doParse(InputStream input) {
		// prefer to do the parsing with java
		System.setProperty("bdd", "java");
		Env.reset_env();
		StructuralInit.get_instance().do_phase_end();
		// reseting the environment.
		try {
			// //////////////////////////////////////////
			// 1. // lexer and parsing //////////////////
			// //////////////////////////////////////////
			CharStream char_input = new ANTLRInputStream(input);
			SMVLexer lex = new SMVLexer(char_input);
			CommonTokenStream tokens = new CommonTokenStream(lex);
			SMVParser parser = new SMVParser(tokens);
			SMVParser.program_return r;
			r = parser.program();
			// //////////////////////////////////////////
			// 2. // variable declarations //////////////
			// //////////////////////////////////////////
			tokens.rewind();
			CommonTreeNodeStream expr_nodes = new CommonTreeNodeStream((Tree) r
					.getTree());
			expr_nodes.setTokenStream(tokens);
			SMVVarWalker walker2 = new SMVVarWalker(expr_nodes);
			walker2.program();
		} catch (SMVParseException smp) {
		} catch (RecognitionException e) {
		} catch (Exception e) {
		}
	}

	// ////////////////////////////////////////////////////////////////////////

}
