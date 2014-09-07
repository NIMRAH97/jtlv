package edu.wis.jtlv.env.ui.editors.smv;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;

public class SMVReconcilingStrategy implements IReconcilingStrategy {
	// private IDocument main_doc;

	public void setDocument(IDocument document) {
		// this.main_doc = document;
		// this.doParse(new ANTLRStringStream(this.main_doc.get()));
	}

	public void reconcile(IRegion partition) {
		// this.doParse(new ANTLRStringStream(this.main_doc.get()));
	}

	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		// this.doParse(new ANTLRStringStream(this.main_doc.get()));
	}

	// private void doParse(CharStream input) {
	// // prefer to do the parsing with java
	// System.setProperty("bdd", "java");
	// Env.reset_env();
	// StructuralInit.get_instance().do_phase_end();
	// // reseting the environment.
	// try {
	// // //////////////////////////////////////////
	// // 1. // lexer and parsing //////////////////
	// // //////////////////////////////////////////
	// SMVLexer lex = new SMVLexer(input);
	// CommonTokenStream tokens = new CommonTokenStream(lex);
	// SMVParser parser = new SMVParser(tokens);
	// SMVParser.program_return r;
	// r = parser.program();
	// // //////////////////////////////////////////
	// // 2. // variable declarations //////////////
	// // //////////////////////////////////////////
	// tokens.rewind();
	// CommonTreeNodeStream expr_nodes = new CommonTreeNodeStream((Tree) r
	// .getTree());
	// expr_nodes.setTokenStream(tokens);
	// SMVVarWalker walker2 = new SMVVarWalker(expr_nodes);
	// walker2.program();
	// // not sure I need the actual attachments.
	// // // //////////////////////////////////////////
	// // // 3. // attaching defines //////////////////
	// // // //////////////////////////////////////////
	// // StmtInit initiator = StmtInit.get_instance();
	// // initiator.startDefinePhase();
	// // int def_inited = -1;
	// // while (def_inited < initiator.countAllInitiatedDefines()) {
	// // def_inited = initiator.countAllInitiatedDefines();
	// // tokens.rewind();
	// // expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
	// // expr_nodes.setTokenStream(tokens);
	// // SMVStmtWalker walker3 = new SMVStmtWalker(expr_nodes);
	// // walker3.program();
	// // }
	// // if (def_inited != initiator.countAllDefines()) {
	// // // not all defines where initiated.... !!
	// // JTLVBDDDefine[] defs = initiator.getAllUninitiatedDefines();
	// // String def_str = "";
	// // for (int i = 0; i < defs.length; i++) {
	// // def_str += defs[i].toString();
	// // def_str += ((i + 1) != defs.length) ? ", " : "";
	// // }
	// // throw new SMVParseException(
	// // "Recursive define statements between "
	// // + "one of the following defines:\n\t" + def_str);
	// // }
	// // initiator.endDefinePhase();
	// // // //////////////////////////////////////////
	// // // 4. // attaching statements ///////////////
	// // // //////////////////////////////////////////
	// // initiator.startInitTransPhase();
	// // tokens.rewind();
	// // expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
	// // expr_nodes.setTokenStream(tokens);
	// // SMVStmtWalker walker4 = new SMVStmtWalker(expr_nodes);
	// // walker4.program();
	// // initiator.endInitTransPhase();
	// // // //////////////////////////////////////////
	// // // //////////////////////////////////////////
	// // // //////////////////////////////////////////
	// // // // other constructions.
	// // // expr_nodes = new CommonTreeNodeStream((Tree) r.getTree());
	// // // expr_nodes.setTokenStream(tokens);
	// // // SMVXXXWalker walker3 = new SMVXXXWalker(expr_nodes);
	// // // walker3.program();
	// // // //////////////////////////////////////////
	// // // //////////////////////////////////////////
	// // // //////////////////////////////////////////
	// } catch (SMVParseException smp) {
	// System.out.println("in SMVParseException");
	// System.out.println(smp);
	// } catch (RecognitionException e) {
	// System.out.println("in RecognitionException");
	//			System.out.println(e);
	//		} catch (Exception e) {
	//
	//		}
	//	}
}
