package edu.wis.jtlv.env.ui.editors.spc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.ui.texteditor.MarkerAnnotation;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.ErrorListener;
import edu.wis.jtlv.env.core.spec.InternalSpec;
import edu.wis.jtlv.env.core.spec.InternalSpecBDD;
import edu.wis.jtlv.env.core.spec.InternalSpecExp;
import edu.wis.jtlv.env.core.spec.SPCLexer;
import edu.wis.jtlv.env.core.spec.SPCParser;
import edu.wis.jtlv.env.core.spec.SpecParseException;

public class SPCBuilder extends IncrementalProjectBuilder {

	private class SPCErrorReporter implements ErrorListener {
		private IFile file;

		public SPCErrorReporter(IFile a_file) {
			this.file = a_file;
		}

		public void doError(Exception e, String msg) {
			if (e instanceof RecognitionException) {
				addMarker((RecognitionException) e, msg, IMarker.SEVERITY_ERROR);
			}
		}

		private void addMarker(RecognitionException e, String msg, int severity) {
			int line = e.line, start, end;

			try {
				IDocument fDoc = getDocumentForFile(this.file);
				if (e.line == 0)
					line = fDoc.getLineOfOffset(fDoc.getLength());

				if (e instanceof SpecParseException) {
					SpecParseException spe = (SpecParseException) e;
					start = fDoc.getLineOffset(spe.getErrorStartLine() - 1)
							+ spe.getErrorStartCharPos();
					end = fDoc.getLineOffset(spe.getErrorStopLine() - 1)
							+ spe.getErrorStopCharPos();
				} else if (e instanceof MismatchedTokenException) {
					start = fDoc.getLineOffset(e.line - 1)
							+ e.charPositionInLine;
					TokenStream ts = (TokenStream) e.input;
					end = fDoc.getLineOffset(ts.LT(2).getLine() - 1)
							+ ts.LT(2).getCharPositionInLine();
				} else {
					TokenStream ts = (TokenStream) e.input;
					// start = fDoc.getLineOffset(e.line - 1)
					// + e.charPositionInLine - 1;
					start = fDoc.getLineOffset(ts.LT(1).getLine() - 1)
							+ ts.LT(1).getCharPositionInLine();
					end = fDoc.getLineOffset(ts.LT(1).getLine() - 1)
							+ ts.LT(1).getCharPositionInLine()
							+ ts.LT(1).getText().length();
					// end = this.findEndOfLine(fDoc, start) + 1;
					if (start == end)
						start--;
				}

				SPCBuilder.this.addMarker(file, e.line, start, end, severity,
						msg);
			} catch (Exception e2) {
				SPCBuilder.this.addMarker(file, line, severity, msg);
			}
		}

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
				writer.print(line + "\n");
			}
		} finally {
			reader.close();
		}
		return new Document(stringWriter.toString());
	}

	public static final String BUILDER_ID = "JTLV.SPCBuilder";
	public static final String MARKER_TYPE = "JTLV.SPCProblem";
	public static final String MARKER_BDD_TYPE = "JTLV.SPCBDDMarker";

	public void checkSPC(IResource resource) {
		if (resource instanceof IFile && resource.getName().endsWith(".spc")) {
			IFile file = (IFile) resource;
			deleteMarkers(file);
			Env.clearErrorListener();
			Env.registerErrorListener(new SPCErrorReporter(file));
			try {
				InternalSpec[] loaded = doParse(file);
				markBDDs(file, loaded);
			} catch (Exception e1) {
			}
		}
	}

	public static void deleteMarkers() {
		deleteMarkers(SPCEditor.getAllAssociatedFiles());
	}
	
	public static void deleteMarkers(IFile[] file) {
		for (IFile f : file) {
			deleteMarkers(f);
		}
	}
	
	public static void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_INFINITE);
			file
					.deleteMarkers(MARKER_BDD_TYPE, false,
							IResource.DEPTH_INFINITE);
		} catch (CoreException ce) {
		}
	}

	private void addMarker(IFile file, int line_number, int start_offset,
			int end_offset, int severity, String msg) {
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

	private void addMarker(IFile file, int line_number, int severity, String msg) {
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

	private void markBDDs(IFile file, InternalSpec[] to_mark) {
		for (InternalSpec internalSpec : to_mark) {
			if (internalSpec instanceof InternalSpecExp) {
				markBDDs(file, ((InternalSpecExp) internalSpec).getChildren());
			} else if (internalSpec instanceof InternalSpecBDD) {
				markBDDSpec(file, (InternalSpecBDD) internalSpec);
			}
			// else, do nothing
		}
	}

	private void markBDDSpec(IFile file, InternalSpecBDD bddSpec) {
		try {
			IDocument fDoc = getDocumentForFile(file);
			Token tstart = bddSpec.tstart, tend = bddSpec.tend;
			int start_offset, end_offset;

			start_offset = fDoc.getLineOffset(tstart.getLine() - 1)
					+ tstart.getCharPositionInLine();
			if (tend != null) {
				end_offset = fDoc.getLineOffset(tend.getLine() - 1)
						+ tend.getCharPositionInLine()
						+ tend.getText().length();
			} else {
				end_offset = start_offset + bddSpec.getExprStr().length();
			}

			String type = SPCBuilder.MARKER_BDD_TYPE;
			IMarker marker = file.createMarker(type);
			marker.setAttribute(IMarker.LINE_NUMBER, tstart.getLine());
			marker.setAttribute(IMarker.CHAR_START, start_offset);
			marker.setAttribute(IMarker.CHAR_END, end_offset);

			MarkerAnnotation a = new MarkerAnnotation(type, marker);
			Position p = new Position(start_offset, end_offset - start_offset);
			SPCEditor.getCurrAnnotationModel(file).addAnnotation(a, p);

		} catch (Exception e) {
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
				while ((delta.getAffectedChildren() != null) && (delta.getAffectedChildren().length != 0)) 
					delta = delta.getAffectedChildren()[0];
				if (delta instanceof IResourceDelta) {
					IResourceDelta[] scan = new IResourceDelta[] {(IResourceDelta)delta};
					fullSingleFileBuild(scan, monitor);
				}
				//fullSingleFileBuild(delta.getAffectedChildren(), monitor);
				// incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SPCResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void fullSingleFileBuild(IResourceDelta[] all_files,
			final IProgressMonitor monitor) throws CoreException {
		try {
			getProject().accept(new SPCResourceVisitor(all_files));
		} catch (CoreException e) {
		}
	}

	// protected void incrementalBuild(IResourceDelta all_files,
	// IProgressMonitor monitor) throws CoreException {
	// // the visitor does the work.
	// getProject().accept(
	// new SPCResourceVisitor(new IResourceDelta[] { all_files }));
	// // delta.accept(new SPCDeltaVisitor());
	// }

	private InternalSpec[] doParse(IFile file) throws Exception {
		InputStream input = file.getContents();
		// prefer to do the parsing with the default (jtlv).
		// System.setProperty("bdd", "java");
		boolean old_evalBDDS = InternalSpec.evalBDDs;
		InternalSpec.evalBDDs = false;
		InternalSpec[] loaded = new InternalSpec[] {};
		try {
			// doing the parsing here, so I could play with the internal Tokens
			// //////////////////////////////////////////
			// 1. // lexer and parsing //////////////////
			// //////////////////////////////////////////
			SPCLexer lex = new SPCLexer(new ANTLRInputStream(input));
			CommonTokenStream tokens = new CommonTokenStream(lex);
			SPCParser parser = new SPCParser(tokens);
			loaded = parser.spec().ret.getArray();

		} catch (RecognitionException re) {
			Env.doError(re, re.getMessage());
		} catch (Exception e) {
			// do nothing
		}
		InternalSpec.evalBDDs = old_evalBDDS;
		return loaded;
	}

	// ////////////////////////////////////////////////////////////////////////

	private class SPCResourceVisitor implements IResourceVisitor {
		private IResourceDelta[] file_arr;

		// constructor for all resources
		public SPCResourceVisitor() {
			file_arr = null;
		}

		// constructor for all resources
		public SPCResourceVisitor(IResourceDelta[] a_file_arr) {
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

			checkSPC(resource);
			// return true to continue visiting children.
			return true;
		}
	}

	//
	// private class SPCDeltaVisitor implements IResourceDeltaVisitor {
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	// */
	// public boolean visit(IResourceDelta delta) throws CoreException {
	// IResource resource = delta.getResource();
	// switch (delta.getKind()) {
	// case IResourceDelta.ADDED:
	// // handle added resource
	// checkSPC(resource);
	// break;
	// case IResourceDelta.REMOVED:
	// // handle removed resource
	// break;
	// case IResourceDelta.CHANGED:
	// // handle changed resource
	// checkSPC(resource);
	// break;
	// }
	// // return true to continue visiting children.
	// return true;
	// }
	// }
}
