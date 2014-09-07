// $ANTLR 3.0.1 /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g 2008-05-06 16:05:15

package edu.wis.jtlv.env.core.smv;

import java.util.Vector;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;

import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.core.smv.schema.SMVAbstractElementInfo;

public class SMVVarWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NOP", "PROGRAM_T", "MODULE_SIGN_T", "VAR_DECL_T", "IVAR_DECL_T", "DEFINE_DECL_T", "ASSIGN_DECL_T", "INIT_ASSIGN_DECL_T", "NEXT_ASSIGN_DECL_T", "JUSTICE_DECL_T", "COMPASSION_DECL_T", "SUBRANGE_T", "TYPE_VALUE_LIST_T", "SYNC_PROCESS_T", "ASYNC_PROCESS_T", "VALUE_T", "VALUE_LIST_T", "REF_T", "REF_LIST_T", "SET_LIST_EXP_T", "BLOCK_T", "CASE_LIST_EXPR_T", "CASE_ELEMENT_EXPR_T", "BIT_SELECT_T", "ARRAY_INDEX_T", "TOK_UNARY_MINUS_T", "PURE_CTL_T", "PURE_LTL_T", "TOK_NUMBER", "TOK_PLUS", "TOK_MINUS", "TOK_NUMBER_WORD", "TOK_TWODOTS", "TOK_FALSEEXP", "TOK_TRUEEXP", "TOK_LP", "TOK_RP", "TOK_BOOL", "TOK_WORD1", "TOK_NEXT", "TOK_CASE", "TOK_ESAC", "TOK_WAREAD", "TOK_COMMA", "TOK_WAWRITE", "TOK_ATOM", "TOK_SELF", "TOK_DOT", "TOK_LB", "TOK_RB", "TOK_COLON", "TOK_NOT", "TOK_SEMI", "TOK_CONCATENATION", "TOK_TIMES", "TOK_DIVIDE", "TOK_MOD", "TOK_LSHIFT", "TOK_RSHIFT", "TOK_LCB", "TOK_RCB", "TOK_UNION", "TOK_SETIN", "TOK_EQUAL", "TOK_NOTEQUAL", "TOK_LT", "TOK_GT", "TOK_LE", "TOK_GE", "TOK_EX", "TOK_AX", "TOK_EF", "TOK_AF", "TOK_EG", "TOK_AG", "TOK_AA", "TOK_UNTIL", "TOK_BUNTIL", "TOK_EE", "TOK_EBF", "TOK_ABF", "TOK_EBG", "TOK_ABG", "TOK_AND", "TOK_OR", "TOK_XOR", "TOK_XNOR", "TOK_IFF", "TOK_IMPLIES", "TOK_OP_NEXT", "TOK_OP_PREC", "TOK_OP_NOTPRECNOT", "TOK_OP_GLOBAL", "TOK_OP_HISTORICAL", "TOK_OP_FUTURE", "TOK_OP_ONCE", "TOK_SINCE", "TOK_RELEASES", "TOK_TRIGGERED", "TOK_BOOLEAN", "TOK_WORD", "TOK_ARRAY", "TOK_OF", "TOK_PROCESS", "TOK_MODULE", "TOK_GAME", "TOK_PLAYER_1", "TOK_PLAYER_2", "TOK_VAR", "TOK_IVAR", "TOK_DEFINE", "TOK_EQDEF", "TOK_ASSIGN", "TOK_SMALLINIT", "TOK_INIT", "TOK_INVAR", "TOK_TRANS", "TOK_FAIRNESS", "TOK_JUSTICE", "TOK_COMPASSION", "TOK_INVARSPEC", "TOK_SPEC", "TOK_CTLSPEC", "TOK_LTLSPEC", "TOK_PSLSPEC", "TOK_CONSTANTS", "TOK_PRED", "TOK_MIRROR", "TOK_REACHTARGET", "TOK_AVOIDTARGET", "TOK_REACHDEADLOCK", "TOK_AVOIDDEADLOCK", "TOK_BUCHIGAME", "TOK_GENREACTIVITY", "TOK_COMPUTE", "TOK_MMIN", "TOK_MMAX", "TOK_ISA", "TOK_GOTO", "TOK_CONSTRAINT", "TOK_SIMPWFF", "TOK_INCONTEXT", "TOK_CTLWFF", "TOK_LTLWFF", "TOK_COMPWFF", "TOK_INTEGER", "TOK_REAL", "TOK_LROTATE", "TOK_RROTATE", "TOK_PREDSLIST", "TOK_NUMBER_FRAC", "JTOK_WS", "JTOK_MULTI_COMMENT", "JTOK_LINE_COMMENT"
    };
    public static final int TOK_MIRROR=131;
    public static final int TOK_AVOIDTARGET=133;
    public static final int TOK_GOTO=142;
    public static final int TOK_OP_NOTPRECNOT=95;
    public static final int TOK_GENREACTIVITY=137;
    public static final int TOK_MMIN=139;
    public static final int TOK_CASE=44;
    public static final int NOP=4;
    public static final int TOK_CONSTRAINT=143;
    public static final int VALUE_LIST_T=20;
    public static final int EOF=-1;
    public static final int TOK_LTLSPEC=127;
    public static final int TOK_ABF=84;
    public static final int TOK_NOT=55;
    public static final int TOK_ABG=86;
    public static final int TOK_EX=73;
    public static final int TOK_WORD=104;
    public static final int TOK_CTLWFF=146;
    public static final int TOK_ISA=141;
    public static final int VALUE_T=19;
    public static final int SET_LIST_EXP_T=23;
    public static final int INIT_ASSIGN_DECL_T=11;
    public static final int SYNC_PROCESS_T=17;
    public static final int TOK_TIMES=58;
    public static final int TOK_EG=77;
    public static final int TOK_RSHIFT=62;
    public static final int ARRAY_INDEX_T=28;
    public static final int TOK_TRIGGERED=102;
    public static final int TOK_EF=75;
    public static final int TOK_SINCE=100;
    public static final int TOK_ASSIGN=116;
    public static final int TOK_SIMPWFF=144;
    public static final int TOK_IFF=91;
    public static final int TOK_EE=82;
    public static final int TOK_SMALLINIT=117;
    public static final int TOK_RELEASES=101;
    public static final int JUSTICE_DECL_T=13;
    public static final int TOK_UNARY_MINUS_T=29;
    public static final int TOK_OP_GLOBAL=96;
    public static final int TOK_COMPWFF=148;
    public static final int DEFINE_DECL_T=9;
    public static final int TOK_LTLWFF=147;
    public static final int TOK_OP_NEXT=93;
    public static final int TOK_IMPLIES=92;
    public static final int IVAR_DECL_T=8;
    public static final int TOK_CTLSPEC=126;
    public static final int TOK_FALSEEXP=37;
    public static final int TOK_ESAC=45;
    public static final int TOK_NOTEQUAL=68;
    public static final int JTOK_MULTI_COMMENT=156;
    public static final int TOK_INVAR=119;
    public static final int TOK_AVOIDDEADLOCK=135;
    public static final int TOK_REAL=150;
    public static final int TOK_PSLSPEC=128;
    public static final int TOK_SEMI=56;
    public static final int TOK_WAWRITE=48;
    public static final int REF_T=21;
    public static final int PROGRAM_T=5;
    public static final int JTOK_LINE_COMMENT=157;
    public static final int TOK_XOR=89;
    public static final int TOK_LROTATE=151;
    public static final int TOK_REACHTARGET=132;
    public static final int TOK_CONSTANTS=129;
    public static final int TOK_GT=70;
    public static final int TOK_DEFINE=114;
    public static final int TOK_OF=106;
    public static final int TOK_PLUS=33;
    public static final int TOK_REACHDEADLOCK=134;
    public static final int TOK_NUMBER=32;
    public static final int BIT_SELECT_T=27;
    public static final int TOK_SPEC=125;
    public static final int TOK_COMMA=47;
    public static final int TOK_UNION=65;
    public static final int TOK_BUCHIGAME=136;
    public static final int PURE_LTL_T=31;
    public static final int TOK_GE=72;
    public static final int TOK_DIVIDE=59;
    public static final int COMPASSION_DECL_T=14;
    public static final int TOK_OR=88;
    public static final int MODULE_SIGN_T=6;
    public static final int NEXT_ASSIGN_DECL_T=12;
    public static final int TOK_RCB=64;
    public static final int TOK_INCONTEXT=145;
    public static final int TOK_RP=40;
    public static final int TOK_MMAX=140;
    public static final int CASE_ELEMENT_EXPR_T=26;
    public static final int TOK_AA=79;
    public static final int TOK_NEXT=43;
    public static final int TOK_MODULE=108;
    public static final int TOK_CONCATENATION=57;
    public static final int TOK_NUMBER_WORD=35;
    public static final int TOK_AF=76;
    public static final int TOK_WAREAD=46;
    public static final int TOK_PREDSLIST=153;
    public static final int TOK_AG=78;
    public static final int TOK_EBG=85;
    public static final int TOK_TRANS=120;
    public static final int TOK_EBF=83;
    public static final int TOK_DOT=51;
    public static final int TOK_NUMBER_FRAC=154;
    public static final int TOK_LCB=63;
    public static final int PURE_CTL_T=30;
    public static final int TOK_INTEGER=149;
    public static final int TOK_AX=74;
    public static final int TOK_AND=87;
    public static final int TOK_INIT=118;
    public static final int TOK_COLON=54;
    public static final int TOK_XNOR=90;
    public static final int SUBRANGE_T=15;
    public static final int TOK_SELF=50;
    public static final int TOK_OP_FUTURE=98;
    public static final int TOK_BUNTIL=81;
    public static final int TOK_MOD=60;
    public static final int TOK_OP_PREC=94;
    public static final int BLOCK_T=24;
    public static final int TOK_RB=53;
    public static final int TOK_PLAYER_2=111;
    public static final int TOK_UNTIL=80;
    public static final int REF_LIST_T=22;
    public static final int TOK_PLAYER_1=110;
    public static final int TOK_COMPUTE=138;
    public static final int JTOK_WS=155;
    public static final int TOK_LP=39;
    public static final int TOK_PRED=130;
    public static final int TOK_BOOL=41;
    public static final int TOK_IVAR=113;
    public static final int TOK_LT=69;
    public static final int ASYNC_PROCESS_T=18;
    public static final int TOK_RROTATE=152;
    public static final int CASE_LIST_EXPR_T=25;
    public static final int TOK_INVARSPEC=124;
    public static final int VAR_DECL_T=7;
    public static final int TOK_SETIN=66;
    public static final int TOK_LB=52;
    public static final int TOK_TWODOTS=36;
    public static final int TOK_WORD1=42;
    public static final int TOK_VAR=112;
    public static final int TOK_BOOLEAN=103;
    public static final int TOK_ARRAY=105;
    public static final int ASSIGN_DECL_T=10;
    public static final int TOK_LE=71;
    public static final int TOK_TRUEEXP=38;
    public static final int TOK_EQDEF=115;
    public static final int TYPE_VALUE_LIST_T=16;
    public static final int TOK_JUSTICE=122;
    public static final int TOK_ATOM=49;
    public static final int TOK_GAME=109;
    public static final int TOK_PROCESS=107;
    public static final int TOK_LSHIFT=61;
    public static final int TOK_OP_ONCE=99;
    public static final int TOK_FAIRNESS=121;
    public static final int TOK_MINUS=34;
    public static final int TOK_COMPASSION=123;
    public static final int TOK_OP_HISTORICAL=97;
    public static final int TOK_EQUAL=67;

        public SMVVarWalker(TreeNodeStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g"; }


    public static String curr_module_name = "main";
    public static String curr_var_name = "___anonymous";
    public static StructuralInit smv_initiator = StructuralInit.get_instance();

    public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    	String msg = null;
    	if ( e instanceof SMVUnimplementedException) {
    		msg = e.toString();
        		Env.activateErrorListeners(e, msg);
    	} else if ( e instanceof SMVParseException) {
    		msg = e.toString();
        		Env.activateErrorListeners(e, msg);
    	} else if ( e instanceof SMVInternalParseException) {
    		msg = e.toString();
        		Env.activateErrorListeners(e, msg);
    	} else {
    		msg = super.getErrorMessage(e, tokenNames);
        		Env.activateErrorListeners(e, msg);
    	}
    	return msg;
    }
    public String getErrorHeader(RecognitionException e) {
    	// I don't like Parse tree error header.
    	// I prefer the BaseRecognizer one...
    	return "line "+e.line+":"+e.charPositionInLine;
    }



    // $ANTLR start program
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:55:1: program : ( ^( PROGRAM_T module_list ) | command );
    public final void program() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:55:10: ( ^( PROGRAM_T module_list ) | command )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==PROGRAM_T) ) {
                alt1=1;
            }
            else if ( (LA1_0==TOK_INIT||(LA1_0>=TOK_TRANS && LA1_0<=TOK_FAIRNESS)||(LA1_0>=TOK_GOTO && LA1_0<=TOK_SIMPWFF)||(LA1_0>=TOK_CTLWFF && LA1_0<=TOK_COMPWFF)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("55:1: program : ( ^( PROGRAM_T module_list ) | command );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:55:12: ^( PROGRAM_T module_list )
                    {
                    match(input,PROGRAM_T,FOLLOW_PROGRAM_T_in_program53); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_module_list_in_program55);
                    module_list();
                    _fsp--;


                    match(input, Token.UP, null); 
                     smv_initiator.do_phase2(); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:58:5: command
                    {
                    pushFollow(FOLLOW_command_in_program68);
                    command();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end program


    // $ANTLR start number
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:69:1: number : TOK_NUMBER ;
    public final void number() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:69:9: ( TOK_NUMBER )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:69:11: TOK_NUMBER
            {
            match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_number93); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end number

    public static class integer_return extends TreeRuleReturnScope {
    };

    // $ANTLR start integer
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:70:1: integer : ( ^( TOK_PLUS TOK_NUMBER ) | ^( TOK_MINUS TOK_NUMBER ) );
    public final integer_return integer() throws RecognitionException {
        integer_return retval = new integer_return();
        retval.start = input.LT(1);

        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:70:10: ( ^( TOK_PLUS TOK_NUMBER ) | ^( TOK_MINUS TOK_NUMBER ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==TOK_PLUS) ) {
                alt2=1;
            }
            else if ( (LA2_0==TOK_MINUS) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("70:1: integer : ( ^( TOK_PLUS TOK_NUMBER ) | ^( TOK_MINUS TOK_NUMBER ) );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:70:12: ^( TOK_PLUS TOK_NUMBER )
                    {
                    match(input,TOK_PLUS,FOLLOW_TOK_PLUS_in_integer102); 

                    match(input, Token.DOWN, null); 
                    match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_integer104); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:71:5: ^( TOK_MINUS TOK_NUMBER )
                    {
                    match(input,TOK_MINUS,FOLLOW_TOK_MINUS_in_integer112); 

                    match(input, Token.DOWN, null); 
                    match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_integer114); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end integer


    // $ANTLR start number_word
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:73:1: number_word : TOK_NUMBER_WORD ;
    public final void number_word() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:73:14: ( TOK_NUMBER_WORD )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:73:16: TOK_NUMBER_WORD
            {
            match(input,TOK_NUMBER_WORD,FOLLOW_TOK_NUMBER_WORD_in_number_word126); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end number_word


    // $ANTLR start subrange
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:83:1: subrange returns [Vector<String> ret] : ^( SUBRANGE_T f= integer s= integer ) ;
    public final Vector<String> subrange() throws RecognitionException {
        Vector<String> ret = null;

        integer_return f = null;

        integer_return s = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:84:3: ( ^( SUBRANGE_T f= integer s= integer ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:84:5: ^( SUBRANGE_T f= integer s= integer )
            {
            match(input,SUBRANGE_T,FOLLOW_SUBRANGE_T_in_subrange144); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_integer_in_subrange148);
            f=integer();
            _fsp--;

            pushFollow(FOLLOW_integer_in_subrange152);
            s=integer();
            _fsp--;


            match(input, Token.UP, null); 
            ret = new Vector<String>(2);
            ret.add(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(f.start),
              input.getTreeAdaptor().getTokenStopIndex(f.start)));
            ret.add(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(s.start),
              input.getTreeAdaptor().getTokenStopIndex(s.start)));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end subrange


    // $ANTLR start constant
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:89:1: constant : ( TOK_FALSEEXP | TOK_TRUEEXP | number | number_word );
    public final void constant() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:89:11: ( TOK_FALSEEXP | TOK_TRUEEXP | number | number_word )
            int alt3=4;
            switch ( input.LA(1) ) {
            case TOK_FALSEEXP:
                {
                alt3=1;
                }
                break;
            case TOK_TRUEEXP:
                {
                alt3=2;
                }
                break;
            case TOK_NUMBER:
                {
                alt3=3;
                }
                break;
            case TOK_NUMBER_WORD:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("89:1: constant : ( TOK_FALSEEXP | TOK_TRUEEXP | number | number_word );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:89:13: TOK_FALSEEXP
                    {
                    match(input,TOK_FALSEEXP,FOLLOW_TOK_FALSEEXP_in_constant176); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:90:5: TOK_TRUEEXP
                    {
                    match(input,TOK_TRUEEXP,FOLLOW_TOK_TRUEEXP_in_constant182); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:91:5: number
                    {
                    pushFollow(FOLLOW_number_in_constant188);
                    number();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:92:5: number_word
                    {
                    pushFollow(FOLLOW_number_word_in_constant194);
                    number_word();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constant


    // $ANTLR start primary_expr_helper1
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:98:1: primary_expr_helper1 : ( ^( VALUE_T constant NOP primary_expr_select ) | ^( VALUE_T TOK_ATOM NOP primary_expr_select ) | ^( VALUE_T TOK_SELF NOP primary_expr_select ) | ^( BLOCK_T basic_expr NOP primary_expr_select ) | ^( TOK_BOOL basic_expr NOP primary_expr_select ) | ^( TOK_WORD1 basic_expr NOP primary_expr_select ) | ^( TOK_NEXT basic_expr NOP primary_expr_select ) | ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select ) | ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select ) | ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select ) );
    public final void primary_expr_helper1() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:98:22: ( ^( VALUE_T constant NOP primary_expr_select ) | ^( VALUE_T TOK_ATOM NOP primary_expr_select ) | ^( VALUE_T TOK_SELF NOP primary_expr_select ) | ^( BLOCK_T basic_expr NOP primary_expr_select ) | ^( TOK_BOOL basic_expr NOP primary_expr_select ) | ^( TOK_WORD1 basic_expr NOP primary_expr_select ) | ^( TOK_NEXT basic_expr NOP primary_expr_select ) | ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select ) | ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select ) | ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select ) )
            int alt4=10;
            switch ( input.LA(1) ) {
            case VALUE_T:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case TOK_SELF:
                        {
                        alt4=3;
                        }
                        break;
                    case TOK_ATOM:
                        {
                        alt4=2;
                        }
                        break;
                    case TOK_NUMBER:
                    case TOK_NUMBER_WORD:
                    case TOK_FALSEEXP:
                    case TOK_TRUEEXP:
                        {
                        alt4=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("98:1: primary_expr_helper1 : ( ^( VALUE_T constant NOP primary_expr_select ) | ^( VALUE_T TOK_ATOM NOP primary_expr_select ) | ^( VALUE_T TOK_SELF NOP primary_expr_select ) | ^( BLOCK_T basic_expr NOP primary_expr_select ) | ^( TOK_BOOL basic_expr NOP primary_expr_select ) | ^( TOK_WORD1 basic_expr NOP primary_expr_select ) | ^( TOK_NEXT basic_expr NOP primary_expr_select ) | ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select ) | ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select ) | ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select ) );", 4, 9, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("98:1: primary_expr_helper1 : ( ^( VALUE_T constant NOP primary_expr_select ) | ^( VALUE_T TOK_ATOM NOP primary_expr_select ) | ^( VALUE_T TOK_SELF NOP primary_expr_select ) | ^( BLOCK_T basic_expr NOP primary_expr_select ) | ^( TOK_BOOL basic_expr NOP primary_expr_select ) | ^( TOK_WORD1 basic_expr NOP primary_expr_select ) | ^( TOK_NEXT basic_expr NOP primary_expr_select ) | ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select ) | ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select ) | ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select ) );", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case BLOCK_T:
                {
                alt4=4;
                }
                break;
            case TOK_BOOL:
                {
                alt4=5;
                }
                break;
            case TOK_WORD1:
                {
                alt4=6;
                }
                break;
            case TOK_NEXT:
                {
                alt4=7;
                }
                break;
            case CASE_LIST_EXPR_T:
                {
                alt4=8;
                }
                break;
            case TOK_WAREAD:
                {
                alt4=9;
                }
                break;
            case TOK_WAWRITE:
                {
                alt4=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("98:1: primary_expr_helper1 : ( ^( VALUE_T constant NOP primary_expr_select ) | ^( VALUE_T TOK_ATOM NOP primary_expr_select ) | ^( VALUE_T TOK_SELF NOP primary_expr_select ) | ^( BLOCK_T basic_expr NOP primary_expr_select ) | ^( TOK_BOOL basic_expr NOP primary_expr_select ) | ^( TOK_WORD1 basic_expr NOP primary_expr_select ) | ^( TOK_NEXT basic_expr NOP primary_expr_select ) | ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select ) | ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select ) | ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select ) );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:98:24: ^( VALUE_T constant NOP primary_expr_select )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_primary_expr_helper1217); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_constant_in_primary_expr_helper1219);
                    constant();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1221); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1223);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:99:5: ^( VALUE_T TOK_ATOM NOP primary_expr_select )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_primary_expr_helper1231); 

                    match(input, Token.DOWN, null); 
                    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_primary_expr_helper1233); 
                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1235); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1237);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:100:5: ^( VALUE_T TOK_SELF NOP primary_expr_select )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_primary_expr_helper1245); 

                    match(input, Token.DOWN, null); 
                    match(input,TOK_SELF,FOLLOW_TOK_SELF_in_primary_expr_helper1247); 
                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1249); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1251);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:101:5: ^( BLOCK_T basic_expr NOP primary_expr_select )
                    {
                    match(input,BLOCK_T,FOLLOW_BLOCK_T_in_primary_expr_helper1260); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basic_expr_in_primary_expr_helper1262);
                    basic_expr();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1264); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1266);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:102:5: ^( TOK_BOOL basic_expr NOP primary_expr_select )
                    {
                    match(input,TOK_BOOL,FOLLOW_TOK_BOOL_in_primary_expr_helper1274); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basic_expr_in_primary_expr_helper1276);
                    basic_expr();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1278); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1280);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:103:5: ^( TOK_WORD1 basic_expr NOP primary_expr_select )
                    {
                    match(input,TOK_WORD1,FOLLOW_TOK_WORD1_in_primary_expr_helper1288); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basic_expr_in_primary_expr_helper1290);
                    basic_expr();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1292); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1294);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:104:5: ^( TOK_NEXT basic_expr NOP primary_expr_select )
                    {
                    match(input,TOK_NEXT,FOLLOW_TOK_NEXT_in_primary_expr_helper1302); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basic_expr_in_primary_expr_helper1304);
                    basic_expr();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1306); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1308);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 8 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:105:5: ^( CASE_LIST_EXPR_T case_element_list_expr NOP primary_expr_select )
                    {
                    match(input,CASE_LIST_EXPR_T,FOLLOW_CASE_LIST_EXPR_T_in_primary_expr_helper1316); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_case_element_list_expr_in_primary_expr_helper1318);
                    case_element_list_expr();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1320); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1322);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 9 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:106:5: ^( TOK_WAREAD simple_expression simple_expression NOP primary_expr_select )
                    {
                    match(input,TOK_WAREAD,FOLLOW_TOK_WAREAD_in_primary_expr_helper1330); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_primary_expr_helper1332);
                    simple_expression();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_primary_expr_helper1334);
                    simple_expression();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1336); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1338);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 10 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:107:5: ^( TOK_WAWRITE simple_expression simple_expression simple_expression NOP primary_expr_select )
                    {
                    match(input,TOK_WAWRITE,FOLLOW_TOK_WAWRITE_in_primary_expr_helper1346); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_primary_expr_helper1348);
                    simple_expression();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_primary_expr_helper1350);
                    simple_expression();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_primary_expr_helper1352);
                    simple_expression();
                    _fsp--;

                    match(input,NOP,FOLLOW_NOP_in_primary_expr_helper1354); 
                    pushFollow(FOLLOW_primary_expr_select_in_primary_expr_helper1356);
                    primary_expr_select();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end primary_expr_helper1


    // $ANTLR start primary_expr_select
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:1: primary_expr_select : ( primary_expr_select_helper | primary_expr_ref )* ;
    public final void primary_expr_select() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:21: ( ( primary_expr_select_helper | primary_expr_ref )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:23: ( primary_expr_select_helper | primary_expr_ref )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:23: ( primary_expr_select_helper | primary_expr_ref )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=BIT_SELECT_T && LA5_0<=ARRAY_INDEX_T)) ) {
                    alt5=1;
                }
                else if ( (LA5_0==TOK_NUMBER||LA5_0==TOK_ATOM) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:24: primary_expr_select_helper
            	    {
            	    pushFollow(FOLLOW_primary_expr_select_helper_in_primary_expr_select368);
            	    primary_expr_select_helper();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:109:53: primary_expr_ref
            	    {
            	    pushFollow(FOLLOW_primary_expr_ref_in_primary_expr_select372);
            	    primary_expr_ref();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end primary_expr_select


    // $ANTLR start primary_expr_ref
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:111:1: primary_expr_ref : ( TOK_ATOM | TOK_NUMBER ) ;
    public final void primary_expr_ref() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:111:18: ( ( TOK_ATOM | TOK_NUMBER ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:111:20: ( TOK_ATOM | TOK_NUMBER )
            {
            if ( input.LA(1)==TOK_NUMBER||input.LA(1)==TOK_ATOM ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_primary_expr_ref385);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end primary_expr_ref


    // $ANTLR start primary_expr_select_helper
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:113:1: primary_expr_select_helper : ( ^( ARRAY_INDEX_T simple_expression ) | ^( BIT_SELECT_T simple_expression simple_expression ) );
    public final void primary_expr_select_helper() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:113:28: ( ^( ARRAY_INDEX_T simple_expression ) | ^( BIT_SELECT_T simple_expression simple_expression ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ARRAY_INDEX_T) ) {
                alt6=1;
            }
            else if ( (LA6_0==BIT_SELECT_T) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("113:1: primary_expr_select_helper : ( ^( ARRAY_INDEX_T simple_expression ) | ^( BIT_SELECT_T simple_expression simple_expression ) );", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:113:30: ^( ARRAY_INDEX_T simple_expression )
                    {
                    match(input,ARRAY_INDEX_T,FOLLOW_ARRAY_INDEX_T_in_primary_expr_select_helper402); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_primary_expr_select_helper404);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:114:5: ^( BIT_SELECT_T simple_expression simple_expression )
                    {
                    match(input,BIT_SELECT_T,FOLLOW_BIT_SELECT_T_in_primary_expr_select_helper412); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_primary_expr_select_helper414);
                    simple_expression();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_primary_expr_select_helper416);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end primary_expr_select_helper


    // $ANTLR start primary_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:116:1: primary_expr : ( primary_expr_helper1 | ^( TOK_UNARY_MINUS_T primary_expr ) | ^( TOK_NOT primary_expr ) );
    public final void primary_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:116:15: ( primary_expr_helper1 | ^( TOK_UNARY_MINUS_T primary_expr ) | ^( TOK_NOT primary_expr ) )
            int alt7=3;
            switch ( input.LA(1) ) {
            case VALUE_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
                {
                alt7=1;
                }
                break;
            case TOK_UNARY_MINUS_T:
                {
                alt7=2;
                }
                break;
            case TOK_NOT:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("116:1: primary_expr : ( primary_expr_helper1 | ^( TOK_UNARY_MINUS_T primary_expr ) | ^( TOK_NOT primary_expr ) );", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:116:17: primary_expr_helper1
                    {
                    pushFollow(FOLLOW_primary_expr_helper1_in_primary_expr428);
                    primary_expr_helper1();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:117:5: ^( TOK_UNARY_MINUS_T primary_expr )
                    {
                    match(input,TOK_UNARY_MINUS_T,FOLLOW_TOK_UNARY_MINUS_T_in_primary_expr435); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_primary_expr_in_primary_expr437);
                    primary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:118:5: ^( TOK_NOT primary_expr )
                    {
                    match(input,TOK_NOT,FOLLOW_TOK_NOT_in_primary_expr445); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_primary_expr_in_primary_expr447);
                    primary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end primary_expr


    // $ANTLR start case_element_list_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:122:1: case_element_list_expr : ( case_element_expr )+ ;
    public final void case_element_list_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:122:24: ( ( case_element_expr )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:122:26: ( case_element_expr )+
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:122:26: ( case_element_expr )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==CASE_ELEMENT_EXPR_T) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:122:26: case_element_expr
            	    {
            	    pushFollow(FOLLOW_case_element_expr_in_case_element_list_expr461);
            	    case_element_expr();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end case_element_list_expr


    // $ANTLR start case_element_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:124:1: case_element_expr : ^( CASE_ELEMENT_EXPR_T basic_expr basic_expr ) ;
    public final void case_element_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:124:19: ( ^( CASE_ELEMENT_EXPR_T basic_expr basic_expr ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:124:21: ^( CASE_ELEMENT_EXPR_T basic_expr basic_expr )
            {
            match(input,CASE_ELEMENT_EXPR_T,FOLLOW_CASE_ELEMENT_EXPR_T_in_case_element_expr473); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_basic_expr_in_case_element_expr475);
            basic_expr();
            _fsp--;

            pushFollow(FOLLOW_basic_expr_in_case_element_expr477);
            basic_expr();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end case_element_expr


    // $ANTLR start concatination_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:126:1: concatination_expr : ( ^( TOK_CONCATENATION concatination_expr concatination_expr ) | primary_expr );
    public final void concatination_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:126:20: ( ^( TOK_CONCATENATION concatination_expr concatination_expr ) | primary_expr )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==TOK_CONCATENATION) ) {
                alt9=1;
            }
            else if ( (LA9_0==VALUE_T||(LA9_0>=BLOCK_T && LA9_0<=CASE_LIST_EXPR_T)||LA9_0==TOK_UNARY_MINUS_T||(LA9_0>=TOK_BOOL && LA9_0<=TOK_NEXT)||LA9_0==TOK_WAREAD||LA9_0==TOK_WAWRITE||LA9_0==TOK_NOT) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("126:1: concatination_expr : ( ^( TOK_CONCATENATION concatination_expr concatination_expr ) | primary_expr );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:126:22: ^( TOK_CONCATENATION concatination_expr concatination_expr )
                    {
                    match(input,TOK_CONCATENATION,FOLLOW_TOK_CONCATENATION_in_concatination_expr489); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_concatination_expr_in_concatination_expr491);
                    concatination_expr();
                    _fsp--;

                    pushFollow(FOLLOW_concatination_expr_in_concatination_expr493);
                    concatination_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:127:5: primary_expr
                    {
                    pushFollow(FOLLOW_primary_expr_in_concatination_expr500);
                    primary_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end concatination_expr


    // $ANTLR start multiplicative_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:129:1: multiplicative_expr : ( ^( TOK_TIMES multiplicative_expr multiplicative_expr ) | ^( TOK_DIVIDE multiplicative_expr multiplicative_expr ) | concatination_expr );
    public final void multiplicative_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:129:21: ( ^( TOK_TIMES multiplicative_expr multiplicative_expr ) | ^( TOK_DIVIDE multiplicative_expr multiplicative_expr ) | concatination_expr )
            int alt10=3;
            switch ( input.LA(1) ) {
            case TOK_TIMES:
                {
                alt10=1;
                }
                break;
            case TOK_DIVIDE:
                {
                alt10=2;
                }
                break;
            case VALUE_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("129:1: multiplicative_expr : ( ^( TOK_TIMES multiplicative_expr multiplicative_expr ) | ^( TOK_DIVIDE multiplicative_expr multiplicative_expr ) | concatination_expr );", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:129:23: ^( TOK_TIMES multiplicative_expr multiplicative_expr )
                    {
                    match(input,TOK_TIMES,FOLLOW_TOK_TIMES_in_multiplicative_expr511); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicative_expr_in_multiplicative_expr513);
                    multiplicative_expr();
                    _fsp--;

                    pushFollow(FOLLOW_multiplicative_expr_in_multiplicative_expr515);
                    multiplicative_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:130:5: ^( TOK_DIVIDE multiplicative_expr multiplicative_expr )
                    {
                    match(input,TOK_DIVIDE,FOLLOW_TOK_DIVIDE_in_multiplicative_expr523); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicative_expr_in_multiplicative_expr525);
                    multiplicative_expr();
                    _fsp--;

                    pushFollow(FOLLOW_multiplicative_expr_in_multiplicative_expr527);
                    multiplicative_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:131:5: concatination_expr
                    {
                    pushFollow(FOLLOW_concatination_expr_in_multiplicative_expr534);
                    concatination_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end multiplicative_expr


    // $ANTLR start additive_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:133:1: additive_expr : ( ^( TOK_PLUS additive_expr additive_expr ) | ^( TOK_MINUS additive_expr additive_expr ) | multiplicative_expr );
    public final void additive_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:133:16: ( ^( TOK_PLUS additive_expr additive_expr ) | ^( TOK_MINUS additive_expr additive_expr ) | multiplicative_expr )
            int alt11=3;
            switch ( input.LA(1) ) {
            case TOK_PLUS:
                {
                alt11=1;
                }
                break;
            case TOK_MINUS:
                {
                alt11=2;
                }
                break;
            case VALUE_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("133:1: additive_expr : ( ^( TOK_PLUS additive_expr additive_expr ) | ^( TOK_MINUS additive_expr additive_expr ) | multiplicative_expr );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:133:18: ^( TOK_PLUS additive_expr additive_expr )
                    {
                    match(input,TOK_PLUS,FOLLOW_TOK_PLUS_in_additive_expr547); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additive_expr_in_additive_expr549);
                    additive_expr();
                    _fsp--;

                    pushFollow(FOLLOW_additive_expr_in_additive_expr551);
                    additive_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:134:5: ^( TOK_MINUS additive_expr additive_expr )
                    {
                    match(input,TOK_MINUS,FOLLOW_TOK_MINUS_in_additive_expr559); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additive_expr_in_additive_expr561);
                    additive_expr();
                    _fsp--;

                    pushFollow(FOLLOW_additive_expr_in_additive_expr563);
                    additive_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:135:5: multiplicative_expr
                    {
                    pushFollow(FOLLOW_multiplicative_expr_in_additive_expr570);
                    multiplicative_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end additive_expr


    // $ANTLR start remainder_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:137:1: remainder_expr : ( ^( TOK_MOD remainder_expr remainder_expr ) | additive_expr );
    public final void remainder_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:137:16: ( ^( TOK_MOD remainder_expr remainder_expr ) | additive_expr )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==TOK_MOD) ) {
                alt12=1;
            }
            else if ( (LA12_0==VALUE_T||(LA12_0>=BLOCK_T && LA12_0<=CASE_LIST_EXPR_T)||LA12_0==TOK_UNARY_MINUS_T||(LA12_0>=TOK_PLUS && LA12_0<=TOK_MINUS)||(LA12_0>=TOK_BOOL && LA12_0<=TOK_NEXT)||LA12_0==TOK_WAREAD||LA12_0==TOK_WAWRITE||LA12_0==TOK_NOT||(LA12_0>=TOK_CONCATENATION && LA12_0<=TOK_DIVIDE)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("137:1: remainder_expr : ( ^( TOK_MOD remainder_expr remainder_expr ) | additive_expr );", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:137:18: ^( TOK_MOD remainder_expr remainder_expr )
                    {
                    match(input,TOK_MOD,FOLLOW_TOK_MOD_in_remainder_expr581); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_remainder_expr_in_remainder_expr583);
                    remainder_expr();
                    _fsp--;

                    pushFollow(FOLLOW_remainder_expr_in_remainder_expr585);
                    remainder_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:138:5: additive_expr
                    {
                    pushFollow(FOLLOW_additive_expr_in_remainder_expr592);
                    additive_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end remainder_expr


    // $ANTLR start shift_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:140:1: shift_expr : ( ^( TOK_LSHIFT shift_expr shift_expr ) | ^( TOK_RSHIFT shift_expr shift_expr ) | remainder_expr );
    public final void shift_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:140:13: ( ^( TOK_LSHIFT shift_expr shift_expr ) | ^( TOK_RSHIFT shift_expr shift_expr ) | remainder_expr )
            int alt13=3;
            switch ( input.LA(1) ) {
            case TOK_LSHIFT:
                {
                alt13=1;
                }
                break;
            case TOK_RSHIFT:
                {
                alt13=2;
                }
                break;
            case VALUE_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("140:1: shift_expr : ( ^( TOK_LSHIFT shift_expr shift_expr ) | ^( TOK_RSHIFT shift_expr shift_expr ) | remainder_expr );", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:140:15: ^( TOK_LSHIFT shift_expr shift_expr )
                    {
                    match(input,TOK_LSHIFT,FOLLOW_TOK_LSHIFT_in_shift_expr604); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_shift_expr_in_shift_expr606);
                    shift_expr();
                    _fsp--;

                    pushFollow(FOLLOW_shift_expr_in_shift_expr608);
                    shift_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:141:5: ^( TOK_RSHIFT shift_expr shift_expr )
                    {
                    match(input,TOK_RSHIFT,FOLLOW_TOK_RSHIFT_in_shift_expr616); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_shift_expr_in_shift_expr618);
                    shift_expr();
                    _fsp--;

                    pushFollow(FOLLOW_shift_expr_in_shift_expr620);
                    shift_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:142:5: remainder_expr
                    {
                    pushFollow(FOLLOW_remainder_expr_in_shift_expr627);
                    remainder_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end shift_expr


    // $ANTLR start set_list_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:147:1: set_list_expr : ( basic_expr )+ ;
    public final void set_list_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:147:16: ( ( basic_expr )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:147:18: ( basic_expr )+
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:147:18: ( basic_expr )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==SUBRANGE_T||LA14_0==VALUE_T||(LA14_0>=SET_LIST_EXP_T && LA14_0<=CASE_LIST_EXPR_T)||(LA14_0>=TOK_UNARY_MINUS_T && LA14_0<=PURE_LTL_T)||(LA14_0>=TOK_PLUS && LA14_0<=TOK_MINUS)||(LA14_0>=TOK_BOOL && LA14_0<=TOK_NEXT)||LA14_0==TOK_WAREAD||LA14_0==TOK_WAWRITE||LA14_0==TOK_NOT||(LA14_0>=TOK_CONCATENATION && LA14_0<=TOK_RSHIFT)||(LA14_0>=TOK_UNION && LA14_0<=TOK_GE)||LA14_0==TOK_UNTIL||(LA14_0>=TOK_AND && LA14_0<=TOK_IMPLIES)||(LA14_0>=TOK_SINCE && LA14_0<=TOK_TRIGGERED)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:147:18: basic_expr
            	    {
            	    pushFollow(FOLLOW_basic_expr_in_set_list_expr642);
            	    basic_expr();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end set_list_expr


    // $ANTLR start set_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:149:1: set_expr : ( shift_expr | subrange | ^( SET_LIST_EXP_T set_list_expr ) );
    public final void set_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:149:11: ( shift_expr | subrange | ^( SET_LIST_EXP_T set_list_expr ) )
            int alt15=3;
            switch ( input.LA(1) ) {
            case VALUE_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
            case TOK_LSHIFT:
            case TOK_RSHIFT:
                {
                alt15=1;
                }
                break;
            case SUBRANGE_T:
                {
                alt15=2;
                }
                break;
            case SET_LIST_EXP_T:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("149:1: set_expr : ( shift_expr | subrange | ^( SET_LIST_EXP_T set_list_expr ) );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:149:13: shift_expr
                    {
                    pushFollow(FOLLOW_shift_expr_in_set_expr654);
                    shift_expr();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:150:5: subrange
                    {
                    pushFollow(FOLLOW_subrange_in_set_expr660);
                    subrange();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:151:5: ^( SET_LIST_EXP_T set_list_expr )
                    {
                    match(input,SET_LIST_EXP_T,FOLLOW_SET_LIST_EXP_T_in_set_expr667); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_set_list_expr_in_set_expr669);
                    set_list_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end set_expr


    // $ANTLR start union_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:153:1: union_expr : ( ^( TOK_UNION union_expr union_expr ) | set_expr );
    public final void union_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:153:13: ( ^( TOK_UNION union_expr union_expr ) | set_expr )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==TOK_UNION) ) {
                alt16=1;
            }
            else if ( (LA16_0==SUBRANGE_T||LA16_0==VALUE_T||(LA16_0>=SET_LIST_EXP_T && LA16_0<=CASE_LIST_EXPR_T)||LA16_0==TOK_UNARY_MINUS_T||(LA16_0>=TOK_PLUS && LA16_0<=TOK_MINUS)||(LA16_0>=TOK_BOOL && LA16_0<=TOK_NEXT)||LA16_0==TOK_WAREAD||LA16_0==TOK_WAWRITE||LA16_0==TOK_NOT||(LA16_0>=TOK_CONCATENATION && LA16_0<=TOK_RSHIFT)) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("153:1: union_expr : ( ^( TOK_UNION union_expr union_expr ) | set_expr );", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:153:15: ^( TOK_UNION union_expr union_expr )
                    {
                    match(input,TOK_UNION,FOLLOW_TOK_UNION_in_union_expr682); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_union_expr_in_union_expr684);
                    union_expr();
                    _fsp--;

                    pushFollow(FOLLOW_union_expr_in_union_expr686);
                    union_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:154:5: set_expr
                    {
                    pushFollow(FOLLOW_set_expr_in_union_expr693);
                    set_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end union_expr


    // $ANTLR start in_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:156:1: in_expr : ( ^( TOK_SETIN in_expr in_expr ) | union_expr );
    public final void in_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:156:10: ( ^( TOK_SETIN in_expr in_expr ) | union_expr )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==TOK_SETIN) ) {
                alt17=1;
            }
            else if ( (LA17_0==SUBRANGE_T||LA17_0==VALUE_T||(LA17_0>=SET_LIST_EXP_T && LA17_0<=CASE_LIST_EXPR_T)||LA17_0==TOK_UNARY_MINUS_T||(LA17_0>=TOK_PLUS && LA17_0<=TOK_MINUS)||(LA17_0>=TOK_BOOL && LA17_0<=TOK_NEXT)||LA17_0==TOK_WAREAD||LA17_0==TOK_WAWRITE||LA17_0==TOK_NOT||(LA17_0>=TOK_CONCATENATION && LA17_0<=TOK_RSHIFT)||LA17_0==TOK_UNION) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("156:1: in_expr : ( ^( TOK_SETIN in_expr in_expr ) | union_expr );", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:156:12: ^( TOK_SETIN in_expr in_expr )
                    {
                    match(input,TOK_SETIN,FOLLOW_TOK_SETIN_in_in_expr705); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_in_expr_in_in_expr707);
                    in_expr();
                    _fsp--;

                    pushFollow(FOLLOW_in_expr_in_in_expr709);
                    in_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:157:5: union_expr
                    {
                    pushFollow(FOLLOW_union_expr_in_in_expr716);
                    union_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end in_expr


    // $ANTLR start relational_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:159:1: relational_expr : ( ^( TOK_EQUAL relational_expr relational_expr ) | ^( TOK_NOTEQUAL relational_expr relational_expr ) | ^( TOK_LT relational_expr relational_expr ) | ^( TOK_GT relational_expr relational_expr ) | ^( TOK_LE relational_expr relational_expr ) | ^( TOK_GE relational_expr relational_expr ) | in_expr );
    public final void relational_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:159:17: ( ^( TOK_EQUAL relational_expr relational_expr ) | ^( TOK_NOTEQUAL relational_expr relational_expr ) | ^( TOK_LT relational_expr relational_expr ) | ^( TOK_GT relational_expr relational_expr ) | ^( TOK_LE relational_expr relational_expr ) | ^( TOK_GE relational_expr relational_expr ) | in_expr )
            int alt18=7;
            switch ( input.LA(1) ) {
            case TOK_EQUAL:
                {
                alt18=1;
                }
                break;
            case TOK_NOTEQUAL:
                {
                alt18=2;
                }
                break;
            case TOK_LT:
                {
                alt18=3;
                }
                break;
            case TOK_GT:
                {
                alt18=4;
                }
                break;
            case TOK_LE:
                {
                alt18=5;
                }
                break;
            case TOK_GE:
                {
                alt18=6;
                }
                break;
            case SUBRANGE_T:
            case VALUE_T:
            case SET_LIST_EXP_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
            case TOK_LSHIFT:
            case TOK_RSHIFT:
            case TOK_UNION:
            case TOK_SETIN:
                {
                alt18=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("159:1: relational_expr : ( ^( TOK_EQUAL relational_expr relational_expr ) | ^( TOK_NOTEQUAL relational_expr relational_expr ) | ^( TOK_LT relational_expr relational_expr ) | ^( TOK_GT relational_expr relational_expr ) | ^( TOK_LE relational_expr relational_expr ) | ^( TOK_GE relational_expr relational_expr ) | in_expr );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:159:19: ^( TOK_EQUAL relational_expr relational_expr )
                    {
                    match(input,TOK_EQUAL,FOLLOW_TOK_EQUAL_in_relational_expr727); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr729);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr731);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:160:5: ^( TOK_NOTEQUAL relational_expr relational_expr )
                    {
                    match(input,TOK_NOTEQUAL,FOLLOW_TOK_NOTEQUAL_in_relational_expr739); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr741);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr743);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:161:5: ^( TOK_LT relational_expr relational_expr )
                    {
                    match(input,TOK_LT,FOLLOW_TOK_LT_in_relational_expr751); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr753);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr755);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:162:5: ^( TOK_GT relational_expr relational_expr )
                    {
                    match(input,TOK_GT,FOLLOW_TOK_GT_in_relational_expr763); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr765);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr767);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:163:5: ^( TOK_LE relational_expr relational_expr )
                    {
                    match(input,TOK_LE,FOLLOW_TOK_LE_in_relational_expr775); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr777);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr779);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:164:5: ^( TOK_GE relational_expr relational_expr )
                    {
                    match(input,TOK_GE,FOLLOW_TOK_GE_in_relational_expr787); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relational_expr_in_relational_expr789);
                    relational_expr();
                    _fsp--;

                    pushFollow(FOLLOW_relational_expr_in_relational_expr791);
                    relational_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:165:5: in_expr
                    {
                    pushFollow(FOLLOW_in_expr_in_relational_expr798);
                    in_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end relational_expr


    // $ANTLR start pure_ctl_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:167:1: pure_ctl_expr : ( ^( TOK_EX ctl_expr ) | ^( TOK_AX ctl_expr ) | ^( TOK_EF ctl_expr ) | ^( TOK_AF ctl_expr ) | ^( TOK_EG ctl_expr ) | ^( TOK_AG ctl_expr ) | ^( TOK_AA ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EE ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EBF subrange ctl_expr ) | ^( TOK_ABF subrange ctl_expr ) | ^( TOK_EBG subrange ctl_expr ) | ^( TOK_ABG subrange ctl_expr ) | ^( TOK_NOT pure_ctl_expr ) );
    public final void pure_ctl_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:167:16: ( ^( TOK_EX ctl_expr ) | ^( TOK_AX ctl_expr ) | ^( TOK_EF ctl_expr ) | ^( TOK_AF ctl_expr ) | ^( TOK_EG ctl_expr ) | ^( TOK_AG ctl_expr ) | ^( TOK_AA ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EE ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EBF subrange ctl_expr ) | ^( TOK_ABF subrange ctl_expr ) | ^( TOK_EBG subrange ctl_expr ) | ^( TOK_ABG subrange ctl_expr ) | ^( TOK_NOT pure_ctl_expr ) )
            int alt21=13;
            switch ( input.LA(1) ) {
            case TOK_EX:
                {
                alt21=1;
                }
                break;
            case TOK_AX:
                {
                alt21=2;
                }
                break;
            case TOK_EF:
                {
                alt21=3;
                }
                break;
            case TOK_AF:
                {
                alt21=4;
                }
                break;
            case TOK_EG:
                {
                alt21=5;
                }
                break;
            case TOK_AG:
                {
                alt21=6;
                }
                break;
            case TOK_AA:
                {
                alt21=7;
                }
                break;
            case TOK_EE:
                {
                alt21=8;
                }
                break;
            case TOK_EBF:
                {
                alt21=9;
                }
                break;
            case TOK_ABF:
                {
                alt21=10;
                }
                break;
            case TOK_EBG:
                {
                alt21=11;
                }
                break;
            case TOK_ABG:
                {
                alt21=12;
                }
                break;
            case TOK_NOT:
                {
                alt21=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("167:1: pure_ctl_expr : ( ^( TOK_EX ctl_expr ) | ^( TOK_AX ctl_expr ) | ^( TOK_EF ctl_expr ) | ^( TOK_AF ctl_expr ) | ^( TOK_EG ctl_expr ) | ^( TOK_AG ctl_expr ) | ^( TOK_AA ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EE ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr ) | ^( TOK_EBF subrange ctl_expr ) | ^( TOK_ABF subrange ctl_expr ) | ^( TOK_EBG subrange ctl_expr ) | ^( TOK_ABG subrange ctl_expr ) | ^( TOK_NOT pure_ctl_expr ) );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:167:18: ^( TOK_EX ctl_expr )
                    {
                    match(input,TOK_EX,FOLLOW_TOK_EX_in_pure_ctl_expr810); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr812);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:168:5: ^( TOK_AX ctl_expr )
                    {
                    match(input,TOK_AX,FOLLOW_TOK_AX_in_pure_ctl_expr820); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr822);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:169:5: ^( TOK_EF ctl_expr )
                    {
                    match(input,TOK_EF,FOLLOW_TOK_EF_in_pure_ctl_expr830); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr832);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:170:5: ^( TOK_AF ctl_expr )
                    {
                    match(input,TOK_AF,FOLLOW_TOK_AF_in_pure_ctl_expr840); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr842);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:171:5: ^( TOK_EG ctl_expr )
                    {
                    match(input,TOK_EG,FOLLOW_TOK_EG_in_pure_ctl_expr850); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr852);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:172:5: ^( TOK_AG ctl_expr )
                    {
                    match(input,TOK_AG,FOLLOW_TOK_AG_in_pure_ctl_expr860); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr862);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:173:5: ^( TOK_AA ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr )
                    {
                    match(input,TOK_AA,FOLLOW_TOK_AA_in_pure_ctl_expr870); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_basic_expr_in_pure_ctl_expr872);
                    ctl_basic_expr();
                    _fsp--;

                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:173:29: ( TOK_UNTIL | TOK_BUNTIL subrange )
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==TOK_UNTIL) ) {
                        alt19=1;
                    }
                    else if ( (LA19_0==TOK_BUNTIL) ) {
                        alt19=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("173:29: ( TOK_UNTIL | TOK_BUNTIL subrange )", 19, 0, input);

                        throw nvae;
                    }
                    switch (alt19) {
                        case 1 :
                            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:173:30: TOK_UNTIL
                            {
                            match(input,TOK_UNTIL,FOLLOW_TOK_UNTIL_in_pure_ctl_expr875); 

                            }
                            break;
                        case 2 :
                            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:173:42: TOK_BUNTIL subrange
                            {
                            match(input,TOK_BUNTIL,FOLLOW_TOK_BUNTIL_in_pure_ctl_expr879); 
                            pushFollow(FOLLOW_subrange_in_pure_ctl_expr881);
                            subrange();
                            _fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_ctl_basic_expr_in_pure_ctl_expr884);
                    ctl_basic_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 8 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:174:5: ^( TOK_EE ctl_basic_expr ( TOK_UNTIL | TOK_BUNTIL subrange ) ctl_basic_expr )
                    {
                    match(input,TOK_EE,FOLLOW_TOK_EE_in_pure_ctl_expr892); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_basic_expr_in_pure_ctl_expr894);
                    ctl_basic_expr();
                    _fsp--;

                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:174:29: ( TOK_UNTIL | TOK_BUNTIL subrange )
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==TOK_UNTIL) ) {
                        alt20=1;
                    }
                    else if ( (LA20_0==TOK_BUNTIL) ) {
                        alt20=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("174:29: ( TOK_UNTIL | TOK_BUNTIL subrange )", 20, 0, input);

                        throw nvae;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:174:30: TOK_UNTIL
                            {
                            match(input,TOK_UNTIL,FOLLOW_TOK_UNTIL_in_pure_ctl_expr897); 

                            }
                            break;
                        case 2 :
                            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:174:42: TOK_BUNTIL subrange
                            {
                            match(input,TOK_BUNTIL,FOLLOW_TOK_BUNTIL_in_pure_ctl_expr901); 
                            pushFollow(FOLLOW_subrange_in_pure_ctl_expr903);
                            subrange();
                            _fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_ctl_basic_expr_in_pure_ctl_expr906);
                    ctl_basic_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 9 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:175:5: ^( TOK_EBF subrange ctl_expr )
                    {
                    match(input,TOK_EBF,FOLLOW_TOK_EBF_in_pure_ctl_expr914); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_pure_ctl_expr916);
                    subrange();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr918);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 10 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:176:5: ^( TOK_ABF subrange ctl_expr )
                    {
                    match(input,TOK_ABF,FOLLOW_TOK_ABF_in_pure_ctl_expr926); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_pure_ctl_expr928);
                    subrange();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr930);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 11 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:177:5: ^( TOK_EBG subrange ctl_expr )
                    {
                    match(input,TOK_EBG,FOLLOW_TOK_EBG_in_pure_ctl_expr938); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_pure_ctl_expr940);
                    subrange();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr942);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 12 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:178:5: ^( TOK_ABG subrange ctl_expr )
                    {
                    match(input,TOK_ABG,FOLLOW_TOK_ABG_in_pure_ctl_expr950); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_pure_ctl_expr952);
                    subrange();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_expr_in_pure_ctl_expr954);
                    ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 13 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:180:5: ^( TOK_NOT pure_ctl_expr )
                    {
                    match(input,TOK_NOT,FOLLOW_TOK_NOT_in_pure_ctl_expr966); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_pure_ctl_expr_in_pure_ctl_expr968);
                    pure_ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end pure_ctl_expr


    // $ANTLR start ctl_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:182:1: ctl_expr : ( ^( PURE_CTL_T pure_ctl_expr ) | relational_expr );
    public final void ctl_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:182:11: ( ^( PURE_CTL_T pure_ctl_expr ) | relational_expr )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==PURE_CTL_T) ) {
                alt22=1;
            }
            else if ( (LA22_0==SUBRANGE_T||LA22_0==VALUE_T||(LA22_0>=SET_LIST_EXP_T && LA22_0<=CASE_LIST_EXPR_T)||LA22_0==TOK_UNARY_MINUS_T||(LA22_0>=TOK_PLUS && LA22_0<=TOK_MINUS)||(LA22_0>=TOK_BOOL && LA22_0<=TOK_NEXT)||LA22_0==TOK_WAREAD||LA22_0==TOK_WAWRITE||LA22_0==TOK_NOT||(LA22_0>=TOK_CONCATENATION && LA22_0<=TOK_RSHIFT)||(LA22_0>=TOK_UNION && LA22_0<=TOK_GE)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("182:1: ctl_expr : ( ^( PURE_CTL_T pure_ctl_expr ) | relational_expr );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:182:13: ^( PURE_CTL_T pure_ctl_expr )
                    {
                    match(input,PURE_CTL_T,FOLLOW_PURE_CTL_T_in_ctl_expr981); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_pure_ctl_expr_in_ctl_expr983);
                    pure_ctl_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:183:5: relational_expr
                    {
                    pushFollow(FOLLOW_relational_expr_in_ctl_expr990);
                    relational_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_expr


    // $ANTLR start ctl_and_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:192:1: ctl_and_expr : ( ^( TOK_AND ctl_and_expr ctl_and_expr ) | ctl_expr );
    public final void ctl_and_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:192:15: ( ^( TOK_AND ctl_and_expr ctl_and_expr ) | ctl_expr )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==TOK_AND) ) {
                alt23=1;
            }
            else if ( (LA23_0==SUBRANGE_T||LA23_0==VALUE_T||(LA23_0>=SET_LIST_EXP_T && LA23_0<=CASE_LIST_EXPR_T)||(LA23_0>=TOK_UNARY_MINUS_T && LA23_0<=PURE_CTL_T)||(LA23_0>=TOK_PLUS && LA23_0<=TOK_MINUS)||(LA23_0>=TOK_BOOL && LA23_0<=TOK_NEXT)||LA23_0==TOK_WAREAD||LA23_0==TOK_WAWRITE||LA23_0==TOK_NOT||(LA23_0>=TOK_CONCATENATION && LA23_0<=TOK_RSHIFT)||(LA23_0>=TOK_UNION && LA23_0<=TOK_GE)) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("192:1: ctl_and_expr : ( ^( TOK_AND ctl_and_expr ctl_and_expr ) | ctl_expr );", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:192:17: ^( TOK_AND ctl_and_expr ctl_and_expr )
                    {
                    match(input,TOK_AND,FOLLOW_TOK_AND_in_ctl_and_expr1008); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_and_expr_in_ctl_and_expr1010);
                    ctl_and_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_and_expr_in_ctl_and_expr1012);
                    ctl_and_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:193:5: ctl_expr
                    {
                    pushFollow(FOLLOW_ctl_expr_in_ctl_and_expr1019);
                    ctl_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_and_expr


    // $ANTLR start ctl_or_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:195:1: ctl_or_expr : ( ^( TOK_OR ctl_or_expr ctl_or_expr ) | ^( TOK_XOR ctl_or_expr ctl_or_expr ) | ^( TOK_XNOR ctl_or_expr ctl_or_expr ) | ctl_and_expr );
    public final void ctl_or_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:195:14: ( ^( TOK_OR ctl_or_expr ctl_or_expr ) | ^( TOK_XOR ctl_or_expr ctl_or_expr ) | ^( TOK_XNOR ctl_or_expr ctl_or_expr ) | ctl_and_expr )
            int alt24=4;
            switch ( input.LA(1) ) {
            case TOK_OR:
                {
                alt24=1;
                }
                break;
            case TOK_XOR:
                {
                alt24=2;
                }
                break;
            case TOK_XNOR:
                {
                alt24=3;
                }
                break;
            case SUBRANGE_T:
            case VALUE_T:
            case SET_LIST_EXP_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case PURE_CTL_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
            case TOK_LSHIFT:
            case TOK_RSHIFT:
            case TOK_UNION:
            case TOK_SETIN:
            case TOK_EQUAL:
            case TOK_NOTEQUAL:
            case TOK_LT:
            case TOK_GT:
            case TOK_LE:
            case TOK_GE:
            case TOK_AND:
                {
                alt24=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("195:1: ctl_or_expr : ( ^( TOK_OR ctl_or_expr ctl_or_expr ) | ^( TOK_XOR ctl_or_expr ctl_or_expr ) | ^( TOK_XNOR ctl_or_expr ctl_or_expr ) | ctl_and_expr );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:195:16: ^( TOK_OR ctl_or_expr ctl_or_expr )
                    {
                    match(input,TOK_OR,FOLLOW_TOK_OR_in_ctl_or_expr1031); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1033);
                    ctl_or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1035);
                    ctl_or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:196:5: ^( TOK_XOR ctl_or_expr ctl_or_expr )
                    {
                    match(input,TOK_XOR,FOLLOW_TOK_XOR_in_ctl_or_expr1043); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1045);
                    ctl_or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1047);
                    ctl_or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:197:5: ^( TOK_XNOR ctl_or_expr ctl_or_expr )
                    {
                    match(input,TOK_XNOR,FOLLOW_TOK_XNOR_in_ctl_or_expr1055); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1057);
                    ctl_or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_or_expr1059);
                    ctl_or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:198:5: ctl_and_expr
                    {
                    pushFollow(FOLLOW_ctl_and_expr_in_ctl_or_expr1066);
                    ctl_and_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_or_expr


    // $ANTLR start ctl_iff_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:200:1: ctl_iff_expr : ( ^( TOK_IFF ctl_iff_expr ctl_iff_expr ) | ctl_or_expr );
    public final void ctl_iff_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:200:15: ( ^( TOK_IFF ctl_iff_expr ctl_iff_expr ) | ctl_or_expr )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==TOK_IFF) ) {
                alt25=1;
            }
            else if ( (LA25_0==SUBRANGE_T||LA25_0==VALUE_T||(LA25_0>=SET_LIST_EXP_T && LA25_0<=CASE_LIST_EXPR_T)||(LA25_0>=TOK_UNARY_MINUS_T && LA25_0<=PURE_CTL_T)||(LA25_0>=TOK_PLUS && LA25_0<=TOK_MINUS)||(LA25_0>=TOK_BOOL && LA25_0<=TOK_NEXT)||LA25_0==TOK_WAREAD||LA25_0==TOK_WAWRITE||LA25_0==TOK_NOT||(LA25_0>=TOK_CONCATENATION && LA25_0<=TOK_RSHIFT)||(LA25_0>=TOK_UNION && LA25_0<=TOK_GE)||(LA25_0>=TOK_AND && LA25_0<=TOK_XNOR)) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("200:1: ctl_iff_expr : ( ^( TOK_IFF ctl_iff_expr ctl_iff_expr ) | ctl_or_expr );", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:200:17: ^( TOK_IFF ctl_iff_expr ctl_iff_expr )
                    {
                    match(input,TOK_IFF,FOLLOW_TOK_IFF_in_ctl_iff_expr1078); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_iff_expr_in_ctl_iff_expr1080);
                    ctl_iff_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_iff_expr_in_ctl_iff_expr1082);
                    ctl_iff_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:201:5: ctl_or_expr
                    {
                    pushFollow(FOLLOW_ctl_or_expr_in_ctl_iff_expr1089);
                    ctl_or_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_iff_expr


    // $ANTLR start ctl_implies_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:203:1: ctl_implies_expr : ( ^( TOK_IMPLIES ctl_iff_expr ctl_implies_expr ) | ctl_iff_expr );
    public final void ctl_implies_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:203:18: ( ^( TOK_IMPLIES ctl_iff_expr ctl_implies_expr ) | ctl_iff_expr )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==TOK_IMPLIES) ) {
                alt26=1;
            }
            else if ( (LA26_0==SUBRANGE_T||LA26_0==VALUE_T||(LA26_0>=SET_LIST_EXP_T && LA26_0<=CASE_LIST_EXPR_T)||(LA26_0>=TOK_UNARY_MINUS_T && LA26_0<=PURE_CTL_T)||(LA26_0>=TOK_PLUS && LA26_0<=TOK_MINUS)||(LA26_0>=TOK_BOOL && LA26_0<=TOK_NEXT)||LA26_0==TOK_WAREAD||LA26_0==TOK_WAWRITE||LA26_0==TOK_NOT||(LA26_0>=TOK_CONCATENATION && LA26_0<=TOK_RSHIFT)||(LA26_0>=TOK_UNION && LA26_0<=TOK_GE)||(LA26_0>=TOK_AND && LA26_0<=TOK_IFF)) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("203:1: ctl_implies_expr : ( ^( TOK_IMPLIES ctl_iff_expr ctl_implies_expr ) | ctl_iff_expr );", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:203:20: ^( TOK_IMPLIES ctl_iff_expr ctl_implies_expr )
                    {
                    match(input,TOK_IMPLIES,FOLLOW_TOK_IMPLIES_in_ctl_implies_expr1100); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ctl_iff_expr_in_ctl_implies_expr1102);
                    ctl_iff_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ctl_implies_expr_in_ctl_implies_expr1104);
                    ctl_implies_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:204:5: ctl_iff_expr
                    {
                    pushFollow(FOLLOW_ctl_iff_expr_in_ctl_implies_expr1111);
                    ctl_iff_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_implies_expr


    // $ANTLR start ctl_basic_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:206:1: ctl_basic_expr : ctl_implies_expr ;
    public final void ctl_basic_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:206:17: ( ctl_implies_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:206:19: ctl_implies_expr
            {
            pushFollow(FOLLOW_ctl_implies_expr_in_ctl_basic_expr1122);
            ctl_implies_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_basic_expr


    // $ANTLR start pure_ltl_unary_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:209:1: pure_ltl_unary_expr : ( ^( TOK_OP_NEXT ltl_unary_expr ) | ^( TOK_OP_PREC ltl_unary_expr ) | ^( TOK_OP_NOTPRECNOT ltl_unary_expr ) | ^( TOK_OP_GLOBAL ltl_unary_expr ) | ^( TOK_OP_HISTORICAL ltl_unary_expr ) | ^( TOK_OP_FUTURE ltl_unary_expr ) | ^( TOK_OP_ONCE ltl_unary_expr ) | ^( TOK_NOT pure_ltl_unary_expr ) );
    public final void pure_ltl_unary_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:209:21: ( ^( TOK_OP_NEXT ltl_unary_expr ) | ^( TOK_OP_PREC ltl_unary_expr ) | ^( TOK_OP_NOTPRECNOT ltl_unary_expr ) | ^( TOK_OP_GLOBAL ltl_unary_expr ) | ^( TOK_OP_HISTORICAL ltl_unary_expr ) | ^( TOK_OP_FUTURE ltl_unary_expr ) | ^( TOK_OP_ONCE ltl_unary_expr ) | ^( TOK_NOT pure_ltl_unary_expr ) )
            int alt27=8;
            switch ( input.LA(1) ) {
            case TOK_OP_NEXT:
                {
                alt27=1;
                }
                break;
            case TOK_OP_PREC:
                {
                alt27=2;
                }
                break;
            case TOK_OP_NOTPRECNOT:
                {
                alt27=3;
                }
                break;
            case TOK_OP_GLOBAL:
                {
                alt27=4;
                }
                break;
            case TOK_OP_HISTORICAL:
                {
                alt27=5;
                }
                break;
            case TOK_OP_FUTURE:
                {
                alt27=6;
                }
                break;
            case TOK_OP_ONCE:
                {
                alt27=7;
                }
                break;
            case TOK_NOT:
                {
                alt27=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("209:1: pure_ltl_unary_expr : ( ^( TOK_OP_NEXT ltl_unary_expr ) | ^( TOK_OP_PREC ltl_unary_expr ) | ^( TOK_OP_NOTPRECNOT ltl_unary_expr ) | ^( TOK_OP_GLOBAL ltl_unary_expr ) | ^( TOK_OP_HISTORICAL ltl_unary_expr ) | ^( TOK_OP_FUTURE ltl_unary_expr ) | ^( TOK_OP_ONCE ltl_unary_expr ) | ^( TOK_NOT pure_ltl_unary_expr ) );", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:209:23: ^( TOK_OP_NEXT ltl_unary_expr )
                    {
                    match(input,TOK_OP_NEXT,FOLLOW_TOK_OP_NEXT_in_pure_ltl_unary_expr1135); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1137);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:210:5: ^( TOK_OP_PREC ltl_unary_expr )
                    {
                    match(input,TOK_OP_PREC,FOLLOW_TOK_OP_PREC_in_pure_ltl_unary_expr1145); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1147);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:211:5: ^( TOK_OP_NOTPRECNOT ltl_unary_expr )
                    {
                    match(input,TOK_OP_NOTPRECNOT,FOLLOW_TOK_OP_NOTPRECNOT_in_pure_ltl_unary_expr1155); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1157);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:212:5: ^( TOK_OP_GLOBAL ltl_unary_expr )
                    {
                    match(input,TOK_OP_GLOBAL,FOLLOW_TOK_OP_GLOBAL_in_pure_ltl_unary_expr1165); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1167);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:213:5: ^( TOK_OP_HISTORICAL ltl_unary_expr )
                    {
                    match(input,TOK_OP_HISTORICAL,FOLLOW_TOK_OP_HISTORICAL_in_pure_ltl_unary_expr1175); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1177);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:214:5: ^( TOK_OP_FUTURE ltl_unary_expr )
                    {
                    match(input,TOK_OP_FUTURE,FOLLOW_TOK_OP_FUTURE_in_pure_ltl_unary_expr1185); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1187);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:215:5: ^( TOK_OP_ONCE ltl_unary_expr )
                    {
                    match(input,TOK_OP_ONCE,FOLLOW_TOK_OP_ONCE_in_pure_ltl_unary_expr1195); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1197);
                    ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 8 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:217:5: ^( TOK_NOT pure_ltl_unary_expr )
                    {
                    match(input,TOK_NOT,FOLLOW_TOK_NOT_in_pure_ltl_unary_expr1209); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_pure_ltl_unary_expr_in_pure_ltl_unary_expr1211);
                    pure_ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end pure_ltl_unary_expr


    // $ANTLR start ltl_unary_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:220:1: ltl_unary_expr : ( ^( PURE_LTL_T pure_ltl_unary_expr ) | ctl_expr );
    public final void ltl_unary_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:220:17: ( ^( PURE_LTL_T pure_ltl_unary_expr ) | ctl_expr )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==PURE_LTL_T) ) {
                alt28=1;
            }
            else if ( (LA28_0==SUBRANGE_T||LA28_0==VALUE_T||(LA28_0>=SET_LIST_EXP_T && LA28_0<=CASE_LIST_EXPR_T)||(LA28_0>=TOK_UNARY_MINUS_T && LA28_0<=PURE_CTL_T)||(LA28_0>=TOK_PLUS && LA28_0<=TOK_MINUS)||(LA28_0>=TOK_BOOL && LA28_0<=TOK_NEXT)||LA28_0==TOK_WAREAD||LA28_0==TOK_WAWRITE||LA28_0==TOK_NOT||(LA28_0>=TOK_CONCATENATION && LA28_0<=TOK_RSHIFT)||(LA28_0>=TOK_UNION && LA28_0<=TOK_GE)) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("220:1: ltl_unary_expr : ( ^( PURE_LTL_T pure_ltl_unary_expr ) | ctl_expr );", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:220:19: ^( PURE_LTL_T pure_ltl_unary_expr )
                    {
                    match(input,PURE_LTL_T,FOLLOW_PURE_LTL_T_in_ltl_unary_expr1226); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_pure_ltl_unary_expr_in_ltl_unary_expr1228);
                    pure_ltl_unary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:221:5: ctl_expr
                    {
                    pushFollow(FOLLOW_ctl_expr_in_ltl_unary_expr1238);
                    ctl_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ltl_unary_expr


    // $ANTLR start ltl_binary_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:224:1: ltl_binary_expr : ( ^( TOK_UNTIL ltl_binary_expr ltl_binary_expr ) | ^( TOK_SINCE ltl_binary_expr ltl_binary_expr ) | ^( TOK_RELEASES ltl_binary_expr ltl_binary_expr ) | ^( TOK_TRIGGERED ltl_binary_expr ltl_binary_expr ) | ltl_unary_expr );
    public final void ltl_binary_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:224:17: ( ^( TOK_UNTIL ltl_binary_expr ltl_binary_expr ) | ^( TOK_SINCE ltl_binary_expr ltl_binary_expr ) | ^( TOK_RELEASES ltl_binary_expr ltl_binary_expr ) | ^( TOK_TRIGGERED ltl_binary_expr ltl_binary_expr ) | ltl_unary_expr )
            int alt29=5;
            switch ( input.LA(1) ) {
            case TOK_UNTIL:
                {
                alt29=1;
                }
                break;
            case TOK_SINCE:
                {
                alt29=2;
                }
                break;
            case TOK_RELEASES:
                {
                alt29=3;
                }
                break;
            case TOK_TRIGGERED:
                {
                alt29=4;
                }
                break;
            case SUBRANGE_T:
            case VALUE_T:
            case SET_LIST_EXP_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case PURE_CTL_T:
            case PURE_LTL_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
            case TOK_LSHIFT:
            case TOK_RSHIFT:
            case TOK_UNION:
            case TOK_SETIN:
            case TOK_EQUAL:
            case TOK_NOTEQUAL:
            case TOK_LT:
            case TOK_GT:
            case TOK_LE:
            case TOK_GE:
                {
                alt29=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("224:1: ltl_binary_expr : ( ^( TOK_UNTIL ltl_binary_expr ltl_binary_expr ) | ^( TOK_SINCE ltl_binary_expr ltl_binary_expr ) | ^( TOK_RELEASES ltl_binary_expr ltl_binary_expr ) | ^( TOK_TRIGGERED ltl_binary_expr ltl_binary_expr ) | ltl_unary_expr );", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:224:19: ^( TOK_UNTIL ltl_binary_expr ltl_binary_expr )
                    {
                    match(input,TOK_UNTIL,FOLLOW_TOK_UNTIL_in_ltl_binary_expr1252); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1254);
                    ltl_binary_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1256);
                    ltl_binary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:225:5: ^( TOK_SINCE ltl_binary_expr ltl_binary_expr )
                    {
                    match(input,TOK_SINCE,FOLLOW_TOK_SINCE_in_ltl_binary_expr1264); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1266);
                    ltl_binary_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1268);
                    ltl_binary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:226:5: ^( TOK_RELEASES ltl_binary_expr ltl_binary_expr )
                    {
                    match(input,TOK_RELEASES,FOLLOW_TOK_RELEASES_in_ltl_binary_expr1276); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1278);
                    ltl_binary_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1280);
                    ltl_binary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:227:5: ^( TOK_TRIGGERED ltl_binary_expr ltl_binary_expr )
                    {
                    match(input,TOK_TRIGGERED,FOLLOW_TOK_TRIGGERED_in_ltl_binary_expr1288); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1290);
                    ltl_binary_expr();
                    _fsp--;

                    pushFollow(FOLLOW_ltl_binary_expr_in_ltl_binary_expr1292);
                    ltl_binary_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:228:5: ltl_unary_expr
                    {
                    pushFollow(FOLLOW_ltl_unary_expr_in_ltl_binary_expr1299);
                    ltl_unary_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ltl_binary_expr


    // $ANTLR start and_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:230:1: and_expr : ( ^( TOK_AND and_expr and_expr ) | ltl_binary_expr );
    public final void and_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:230:11: ( ^( TOK_AND and_expr and_expr ) | ltl_binary_expr )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==TOK_AND) ) {
                alt30=1;
            }
            else if ( (LA30_0==SUBRANGE_T||LA30_0==VALUE_T||(LA30_0>=SET_LIST_EXP_T && LA30_0<=CASE_LIST_EXPR_T)||(LA30_0>=TOK_UNARY_MINUS_T && LA30_0<=PURE_LTL_T)||(LA30_0>=TOK_PLUS && LA30_0<=TOK_MINUS)||(LA30_0>=TOK_BOOL && LA30_0<=TOK_NEXT)||LA30_0==TOK_WAREAD||LA30_0==TOK_WAWRITE||LA30_0==TOK_NOT||(LA30_0>=TOK_CONCATENATION && LA30_0<=TOK_RSHIFT)||(LA30_0>=TOK_UNION && LA30_0<=TOK_GE)||LA30_0==TOK_UNTIL||(LA30_0>=TOK_SINCE && LA30_0<=TOK_TRIGGERED)) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("230:1: and_expr : ( ^( TOK_AND and_expr and_expr ) | ltl_binary_expr );", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:230:13: ^( TOK_AND and_expr and_expr )
                    {
                    match(input,TOK_AND,FOLLOW_TOK_AND_in_and_expr1311); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_and_expr_in_and_expr1313);
                    and_expr();
                    _fsp--;

                    pushFollow(FOLLOW_and_expr_in_and_expr1315);
                    and_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:231:5: ltl_binary_expr
                    {
                    pushFollow(FOLLOW_ltl_binary_expr_in_and_expr1322);
                    ltl_binary_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end and_expr


    // $ANTLR start or_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:233:1: or_expr : ( ^( TOK_OR or_expr or_expr ) | ^( TOK_XOR or_expr or_expr ) | ^( TOK_XNOR or_expr or_expr ) | and_expr );
    public final void or_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:233:10: ( ^( TOK_OR or_expr or_expr ) | ^( TOK_XOR or_expr or_expr ) | ^( TOK_XNOR or_expr or_expr ) | and_expr )
            int alt31=4;
            switch ( input.LA(1) ) {
            case TOK_OR:
                {
                alt31=1;
                }
                break;
            case TOK_XOR:
                {
                alt31=2;
                }
                break;
            case TOK_XNOR:
                {
                alt31=3;
                }
                break;
            case SUBRANGE_T:
            case VALUE_T:
            case SET_LIST_EXP_T:
            case BLOCK_T:
            case CASE_LIST_EXPR_T:
            case TOK_UNARY_MINUS_T:
            case PURE_CTL_T:
            case PURE_LTL_T:
            case TOK_PLUS:
            case TOK_MINUS:
            case TOK_BOOL:
            case TOK_WORD1:
            case TOK_NEXT:
            case TOK_WAREAD:
            case TOK_WAWRITE:
            case TOK_NOT:
            case TOK_CONCATENATION:
            case TOK_TIMES:
            case TOK_DIVIDE:
            case TOK_MOD:
            case TOK_LSHIFT:
            case TOK_RSHIFT:
            case TOK_UNION:
            case TOK_SETIN:
            case TOK_EQUAL:
            case TOK_NOTEQUAL:
            case TOK_LT:
            case TOK_GT:
            case TOK_LE:
            case TOK_GE:
            case TOK_UNTIL:
            case TOK_AND:
            case TOK_SINCE:
            case TOK_RELEASES:
            case TOK_TRIGGERED:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("233:1: or_expr : ( ^( TOK_OR or_expr or_expr ) | ^( TOK_XOR or_expr or_expr ) | ^( TOK_XNOR or_expr or_expr ) | and_expr );", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:233:12: ^( TOK_OR or_expr or_expr )
                    {
                    match(input,TOK_OR,FOLLOW_TOK_OR_in_or_expr1334); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_or_expr_in_or_expr1336);
                    or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_or_expr_in_or_expr1338);
                    or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:234:5: ^( TOK_XOR or_expr or_expr )
                    {
                    match(input,TOK_XOR,FOLLOW_TOK_XOR_in_or_expr1346); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_or_expr_in_or_expr1348);
                    or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_or_expr_in_or_expr1350);
                    or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:235:5: ^( TOK_XNOR or_expr or_expr )
                    {
                    match(input,TOK_XNOR,FOLLOW_TOK_XNOR_in_or_expr1358); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_or_expr_in_or_expr1360);
                    or_expr();
                    _fsp--;

                    pushFollow(FOLLOW_or_expr_in_or_expr1362);
                    or_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:236:5: and_expr
                    {
                    pushFollow(FOLLOW_and_expr_in_or_expr1369);
                    and_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end or_expr


    // $ANTLR start iff_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:238:1: iff_expr : ( ^( TOK_IFF iff_expr iff_expr ) | or_expr );
    public final void iff_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:238:11: ( ^( TOK_IFF iff_expr iff_expr ) | or_expr )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==TOK_IFF) ) {
                alt32=1;
            }
            else if ( (LA32_0==SUBRANGE_T||LA32_0==VALUE_T||(LA32_0>=SET_LIST_EXP_T && LA32_0<=CASE_LIST_EXPR_T)||(LA32_0>=TOK_UNARY_MINUS_T && LA32_0<=PURE_LTL_T)||(LA32_0>=TOK_PLUS && LA32_0<=TOK_MINUS)||(LA32_0>=TOK_BOOL && LA32_0<=TOK_NEXT)||LA32_0==TOK_WAREAD||LA32_0==TOK_WAWRITE||LA32_0==TOK_NOT||(LA32_0>=TOK_CONCATENATION && LA32_0<=TOK_RSHIFT)||(LA32_0>=TOK_UNION && LA32_0<=TOK_GE)||LA32_0==TOK_UNTIL||(LA32_0>=TOK_AND && LA32_0<=TOK_XNOR)||(LA32_0>=TOK_SINCE && LA32_0<=TOK_TRIGGERED)) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("238:1: iff_expr : ( ^( TOK_IFF iff_expr iff_expr ) | or_expr );", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:238:13: ^( TOK_IFF iff_expr iff_expr )
                    {
                    match(input,TOK_IFF,FOLLOW_TOK_IFF_in_iff_expr1381); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_iff_expr_in_iff_expr1383);
                    iff_expr();
                    _fsp--;

                    pushFollow(FOLLOW_iff_expr_in_iff_expr1385);
                    iff_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:239:5: or_expr
                    {
                    pushFollow(FOLLOW_or_expr_in_iff_expr1392);
                    or_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end iff_expr


    // $ANTLR start implies_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:242:1: implies_expr : ( ^( TOK_IMPLIES iff_expr implies_expr ) | iff_expr );
    public final void implies_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:242:15: ( ^( TOK_IMPLIES iff_expr implies_expr ) | iff_expr )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==TOK_IMPLIES) ) {
                alt33=1;
            }
            else if ( (LA33_0==SUBRANGE_T||LA33_0==VALUE_T||(LA33_0>=SET_LIST_EXP_T && LA33_0<=CASE_LIST_EXPR_T)||(LA33_0>=TOK_UNARY_MINUS_T && LA33_0<=PURE_LTL_T)||(LA33_0>=TOK_PLUS && LA33_0<=TOK_MINUS)||(LA33_0>=TOK_BOOL && LA33_0<=TOK_NEXT)||LA33_0==TOK_WAREAD||LA33_0==TOK_WAWRITE||LA33_0==TOK_NOT||(LA33_0>=TOK_CONCATENATION && LA33_0<=TOK_RSHIFT)||(LA33_0>=TOK_UNION && LA33_0<=TOK_GE)||LA33_0==TOK_UNTIL||(LA33_0>=TOK_AND && LA33_0<=TOK_IFF)||(LA33_0>=TOK_SINCE && LA33_0<=TOK_TRIGGERED)) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("242:1: implies_expr : ( ^( TOK_IMPLIES iff_expr implies_expr ) | iff_expr );", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:242:17: ^( TOK_IMPLIES iff_expr implies_expr )
                    {
                    match(input,TOK_IMPLIES,FOLLOW_TOK_IMPLIES_in_implies_expr1406); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_iff_expr_in_implies_expr1408);
                    iff_expr();
                    _fsp--;

                    pushFollow(FOLLOW_implies_expr_in_implies_expr1410);
                    implies_expr();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:243:5: iff_expr
                    {
                    pushFollow(FOLLOW_iff_expr_in_implies_expr1417);
                    iff_expr();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end implies_expr


    // $ANTLR start basic_expr
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:245:1: basic_expr : implies_expr ;
    public final void basic_expr() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:245:13: ( implies_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:245:15: implies_expr
            {
            pushFollow(FOLLOW_implies_expr_in_basic_expr1428);
            implies_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end basic_expr

    public static class simple_expression_return extends TreeRuleReturnScope {
    };

    // $ANTLR start simple_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:251:1: simple_expression : basic_expr ;
    public final simple_expression_return simple_expression() throws RecognitionException {
        simple_expression_return retval = new simple_expression_return();
        retval.start = input.LT(1);

        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:251:19: ( basic_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:251:21: basic_expr
            {
            pushFollow(FOLLOW_basic_expr_in_simple_expression1440);
            basic_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end simple_expression


    // $ANTLR start next_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:253:1: next_expression : basic_expr ;
    public final void next_expression() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:253:17: ( basic_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:253:19: basic_expr
            {
            pushFollow(FOLLOW_basic_expr_in_next_expression1450);
            basic_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end next_expression


    // $ANTLR start ctl_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:256:1: ctl_expression : basic_expr ;
    public final void ctl_expression() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:256:16: ( basic_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:256:18: basic_expr
            {
            pushFollow(FOLLOW_basic_expr_in_ctl_expression1462);
            basic_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctl_expression


    // $ANTLR start ltl_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:258:1: ltl_expression : basic_expr ;
    public final void ltl_expression() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:258:17: ( basic_expr )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:258:19: basic_expr
            {
            pushFollow(FOLLOW_basic_expr_in_ltl_expression1473);
            basic_expr();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ltl_expression


    // $ANTLR start itype
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:266:1: itype returns [SMVAbstractElementInfo ret] : ( TOK_BOOLEAN | ^( TOK_WORD se= simple_expression ) | sr= subrange | ^( TYPE_VALUE_LIST_T tvl= type_value_list ) | ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) ) | ^( TOK_ARRAY sr= subrange ri= itype ) );
    public final SMVAbstractElementInfo itype() throws RecognitionException {
        SMVAbstractElementInfo ret = null;

        simple_expression_return se = null;

        Vector<String> sr = null;

        Vector<String> tvl = null;

        SMVAbstractElementInfo ri = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:266:43: ( TOK_BOOLEAN | ^( TOK_WORD se= simple_expression ) | sr= subrange | ^( TYPE_VALUE_LIST_T tvl= type_value_list ) | ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) ) | ^( TOK_ARRAY sr= subrange ri= itype ) )
            int alt34=6;
            switch ( input.LA(1) ) {
            case TOK_BOOLEAN:
                {
                alt34=1;
                }
                break;
            case TOK_WORD:
                {
                alt34=2;
                }
                break;
            case SUBRANGE_T:
                {
                alt34=3;
                }
                break;
            case TYPE_VALUE_LIST_T:
                {
                alt34=4;
                }
                break;
            case TOK_ARRAY:
                {
                int LA34_5 = input.LA(2);

                if ( (LA34_5==DOWN) ) {
                    int LA34_6 = input.LA(3);

                    if ( (LA34_6==TOK_WORD) ) {
                        alt34=5;
                    }
                    else if ( (LA34_6==SUBRANGE_T) ) {
                        alt34=6;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("266:1: itype returns [SMVAbstractElementInfo ret] : ( TOK_BOOLEAN | ^( TOK_WORD se= simple_expression ) | sr= subrange | ^( TYPE_VALUE_LIST_T tvl= type_value_list ) | ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) ) | ^( TOK_ARRAY sr= subrange ri= itype ) );", 34, 6, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("266:1: itype returns [SMVAbstractElementInfo ret] : ( TOK_BOOLEAN | ^( TOK_WORD se= simple_expression ) | sr= subrange | ^( TYPE_VALUE_LIST_T tvl= type_value_list ) | ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) ) | ^( TOK_ARRAY sr= subrange ri= itype ) );", 34, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("266:1: itype returns [SMVAbstractElementInfo ret] : ( TOK_BOOLEAN | ^( TOK_WORD se= simple_expression ) | sr= subrange | ^( TYPE_VALUE_LIST_T tvl= type_value_list ) | ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) ) | ^( TOK_ARRAY sr= subrange ri= itype ) );", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:266:45: TOK_BOOLEAN
                    {
                    match(input,TOK_BOOLEAN,FOLLOW_TOK_BOOLEAN_in_itype1496); 
                     ret = smv_initiator.mk_boolean_var(curr_var_name, input); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:272:5: ^( TOK_WORD se= simple_expression )
                    {
                    match(input,TOK_WORD,FOLLOW_TOK_WORD_in_itype1509); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_itype1513);
                    se=simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_word_var(curr_var_name, "2", input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(se.start),
                      input.getTreeAdaptor().getTokenStopIndex(se.start)), input); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:274:5: sr= subrange
                    {
                    pushFollow(FOLLOW_subrange_in_itype1526);
                    sr=subrange();
                    _fsp--;

                     ret = smv_initiator.mk_range_var(curr_var_name, sr.elementAt(0), sr.elementAt(1), input); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:276:5: ^( TYPE_VALUE_LIST_T tvl= type_value_list )
                    {
                    match(input,TYPE_VALUE_LIST_T,FOLLOW_TYPE_VALUE_LIST_T_in_itype1537); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_value_list_in_itype1541);
                    tvl=type_value_list();
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_values_var(curr_var_name, tvl, input); 

                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:278:5: ^( TOK_ARRAY ^( TOK_WORD simple_expression ) ^( TOK_WORD simple_expression ) )
                    {
                    match(input,TOK_ARRAY,FOLLOW_TOK_ARRAY_in_itype1553); 

                    match(input, Token.DOWN, null); 
                    match(input,TOK_WORD,FOLLOW_TOK_WORD_in_itype1556); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_itype1558);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 
                    match(input,TOK_WORD,FOLLOW_TOK_WORD_in_itype1562); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simple_expression_in_itype1564);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                     if (true) throw new SMVUnimplementedException("Unimplemented variable definition \"Array Word[..] Word[..]\"", input); 

                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:280:5: ^( TOK_ARRAY sr= subrange ri= itype )
                    {
                    match(input,TOK_ARRAY,FOLLOW_TOK_ARRAY_in_itype1577); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_itype1581);
                    sr=subrange();
                    _fsp--;

                    pushFollow(FOLLOW_itype_in_itype1585);
                    ri=itype();
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_range_array_var(curr_var_name, ri, sr.elementAt(0), sr.elementAt(1), input); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end itype


    // $ANTLR start type
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:283:1: type returns [SMVAbstractElementInfo ret] : ( ^( SYNC_PROCESS_T module_type[true] ) | itype | ^( ASYNC_PROCESS_T module_type[false] ) );
    public final SMVAbstractElementInfo type() throws RecognitionException {
        SMVAbstractElementInfo ret = null;

        SMVAbstractElementInfo module_type1 = null;

        SMVAbstractElementInfo itype2 = null;

        SMVAbstractElementInfo module_type3 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:283:42: ( ^( SYNC_PROCESS_T module_type[true] ) | itype | ^( ASYNC_PROCESS_T module_type[false] ) )
            int alt35=3;
            switch ( input.LA(1) ) {
            case SYNC_PROCESS_T:
                {
                alt35=1;
                }
                break;
            case SUBRANGE_T:
            case TYPE_VALUE_LIST_T:
            case TOK_BOOLEAN:
            case TOK_WORD:
            case TOK_ARRAY:
                {
                alt35=2;
                }
                break;
            case ASYNC_PROCESS_T:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("283:1: type returns [SMVAbstractElementInfo ret] : ( ^( SYNC_PROCESS_T module_type[true] ) | itype | ^( ASYNC_PROCESS_T module_type[false] ) );", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:283:44: ^( SYNC_PROCESS_T module_type[true] )
                    {
                    match(input,SYNC_PROCESS_T,FOLLOW_SYNC_PROCESS_T_in_type1604); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_module_type_in_type1606);
                    module_type1=module_type(true);
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = module_type1; 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:285:5: itype
                    {
                    pushFollow(FOLLOW_itype_in_type1618);
                    itype2=itype();
                    _fsp--;

                     ret = itype2; 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:287:5: ^( ASYNC_PROCESS_T module_type[false] )
                    {
                    match(input,ASYNC_PROCESS_T,FOLLOW_ASYNC_PROCESS_T_in_type1629); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_module_type_in_type1631);
                    module_type3=module_type(false);
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = module_type3; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end type


    // $ANTLR start type_value_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:290:1: type_value_list returns [Vector<String> ret] : ( type_value )+ ;
    public final Vector<String> type_value_list() throws RecognitionException {
        Vector<String> ret = null;

        type_value_return type_value4 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:291:3: ( ( type_value )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:291:5: ( type_value )+
            {
             ret = new Vector<String>(10); 
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:292:3: ( type_value )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==VALUE_T||(LA36_0>=TOK_PLUS && LA36_0<=TOK_MINUS)||(LA36_0>=TOK_FALSEEXP && LA36_0<=TOK_TRUEEXP)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:292:5: type_value
            	    {
            	    pushFollow(FOLLOW_type_value_in_type_value_list1658);
            	    type_value4=type_value();
            	    _fsp--;

            	     ret.add(input.getTokenStream().toString(
            	      input.getTreeAdaptor().getTokenStartIndex(type_value4.start),
            	      input.getTreeAdaptor().getTokenStopIndex(type_value4.start))); 

            	    }
            	    break;

            	default :
            	    if ( cnt36 >= 1 ) break loop36;
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end type_value_list

    public static class type_value_return extends TreeRuleReturnScope {
    };

    // $ANTLR start type_value
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:294:1: type_value : ( ^( VALUE_T complex_atom ) | integer | TOK_FALSEEXP | TOK_TRUEEXP );
    public final type_value_return type_value() throws RecognitionException {
        type_value_return retval = new type_value_return();
        retval.start = input.LT(1);

        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:294:13: ( ^( VALUE_T complex_atom ) | integer | TOK_FALSEEXP | TOK_TRUEEXP )
            int alt37=4;
            switch ( input.LA(1) ) {
            case VALUE_T:
                {
                alt37=1;
                }
                break;
            case TOK_PLUS:
            case TOK_MINUS:
                {
                alt37=2;
                }
                break;
            case TOK_FALSEEXP:
                {
                alt37=3;
                }
                break;
            case TOK_TRUEEXP:
                {
                alt37=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("294:1: type_value : ( ^( VALUE_T complex_atom ) | integer | TOK_FALSEEXP | TOK_TRUEEXP );", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:294:15: ^( VALUE_T complex_atom )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_type_value1675); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_complex_atom_in_type_value1677);
                    complex_atom();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:295:5: integer
                    {
                    pushFollow(FOLLOW_integer_in_type_value1686);
                    integer();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:296:5: TOK_FALSEEXP
                    {
                    match(input,TOK_FALSEEXP,FOLLOW_TOK_FALSEEXP_in_type_value1692); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:297:5: TOK_TRUEEXP
                    {
                    match(input,TOK_TRUEEXP,FOLLOW_TOK_TRUEEXP_in_type_value1698); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end type_value


    // $ANTLR start complex_atom
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:299:1: complex_atom : ( TOK_ATOM )+ ;
    public final void complex_atom() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:299:14: ( ( TOK_ATOM )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:299:16: ( TOK_ATOM )+
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:299:16: ( TOK_ATOM )+
            int cnt38=0;
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==TOK_ATOM) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:299:16: TOK_ATOM
            	    {
            	    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_complex_atom1708); 

            	    }
            	    break;

            	default :
            	    if ( cnt38 >= 1 ) break loop38;
                        EarlyExitException eee =
                            new EarlyExitException(38, input);
                        throw eee;
                }
                cnt38++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end complex_atom


    // $ANTLR start module_type
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:301:1: module_type[boolean is_sync] returns [SMVAbstractElementInfo ret] : ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) );
    public final SMVAbstractElementInfo module_type(boolean is_sync) throws RecognitionException {
        SMVAbstractElementInfo ret = null;

        CommonTree TOK_ATOM5=null;
        CommonTree TOK_ATOM6=null;
        SMVAbstractElementInfo mt = null;

        Vector<String> simple_list_expression7 = null;

        Vector<String> subrange8 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:302:3: ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) )
            int alt39=3;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==VALUE_T) ) {
                int LA39_1 = input.LA(2);

                if ( (LA39_1==DOWN) ) {
                    int LA39_3 = input.LA(3);

                    if ( (LA39_3==TOK_ATOM) ) {
                        int LA39_4 = input.LA(4);

                        if ( (LA39_4==UP) ) {
                            alt39=1;
                        }
                        else if ( (LA39_4==VALUE_LIST_T) ) {
                            alt39=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("301:1: module_type[boolean is_sync] returns [SMVAbstractElementInfo ret] : ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) );", 39, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("301:1: module_type[boolean is_sync] returns [SMVAbstractElementInfo ret] : ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) );", 39, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("301:1: module_type[boolean is_sync] returns [SMVAbstractElementInfo ret] : ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) );", 39, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA39_0==TOK_ARRAY) ) {
                alt39=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("301:1: module_type[boolean is_sync] returns [SMVAbstractElementInfo ret] : ( ^( VALUE_T TOK_ATOM ) | ^( VALUE_T TOK_ATOM simple_list_expression ) | ^( TOK_ARRAY subrange mt= module_type[is_sync] ) );", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:302:5: ^( VALUE_T TOK_ATOM )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_module_type1728); 

                    match(input, Token.DOWN, null); 
                    TOK_ATOM5=(CommonTree)input.LT(1);
                    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_module_type1730); 

                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_process_var(curr_var_name, TOK_ATOM5.getText(), is_sync, new Vector<String>(1), input); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:304:5: ^( VALUE_T TOK_ATOM simple_list_expression )
                    {
                    match(input,VALUE_T,FOLLOW_VALUE_T_in_module_type1742); 

                    match(input, Token.DOWN, null); 
                    TOK_ATOM6=(CommonTree)input.LT(1);
                    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_module_type1744); 
                    pushFollow(FOLLOW_simple_list_expression_in_module_type1746);
                    simple_list_expression7=simple_list_expression();
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_process_var(curr_var_name, TOK_ATOM6.getText(), is_sync, simple_list_expression7, input); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:306:5: ^( TOK_ARRAY subrange mt= module_type[is_sync] )
                    {
                    match(input,TOK_ARRAY,FOLLOW_TOK_ARRAY_in_module_type1758); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_subrange_in_module_type1760);
                    subrange8=subrange();
                    _fsp--;

                    pushFollow(FOLLOW_module_type_in_module_type1764);
                    mt=module_type(is_sync);
                    _fsp--;


                    match(input, Token.UP, null); 
                     ret = smv_initiator.mk_range_array_var(curr_var_name, mt, subrange8.elementAt(0), subrange8.elementAt(1), input); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end module_type


    // $ANTLR start simple_list_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:309:1: simple_list_expression returns [Vector<String> ret] : ^( VALUE_LIST_T simple_list_expression_helper ) ;
    public final Vector<String> simple_list_expression() throws RecognitionException {
        Vector<String> ret = null;

        Vector<String> simple_list_expression_helper9 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:310:3: ( ^( VALUE_LIST_T simple_list_expression_helper ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:310:5: ^( VALUE_LIST_T simple_list_expression_helper )
            {
             ret = new Vector<String>(10); 
            match(input,VALUE_LIST_T,FOLLOW_VALUE_LIST_T_in_simple_list_expression1790); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_simple_list_expression_helper_in_simple_list_expression1792);
            simple_list_expression_helper9=simple_list_expression_helper();
            _fsp--;


            match(input, Token.UP, null); 
             ret = simple_list_expression_helper9; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end simple_list_expression


    // $ANTLR start simple_list_expression_helper
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:314:1: simple_list_expression_helper returns [Vector<String> ret] : ( simple_expression )+ ;
    public final Vector<String> simple_list_expression_helper() throws RecognitionException {
        Vector<String> ret = null;

        simple_expression_return simple_expression10 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:315:3: ( ( simple_expression )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:315:5: ( simple_expression )+
            {
             ret = new Vector<String>(10); 
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:316:3: ( simple_expression )+
            int cnt40=0;
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==SUBRANGE_T||LA40_0==VALUE_T||(LA40_0>=SET_LIST_EXP_T && LA40_0<=CASE_LIST_EXPR_T)||(LA40_0>=TOK_UNARY_MINUS_T && LA40_0<=PURE_LTL_T)||(LA40_0>=TOK_PLUS && LA40_0<=TOK_MINUS)||(LA40_0>=TOK_BOOL && LA40_0<=TOK_NEXT)||LA40_0==TOK_WAREAD||LA40_0==TOK_WAWRITE||LA40_0==TOK_NOT||(LA40_0>=TOK_CONCATENATION && LA40_0<=TOK_RSHIFT)||(LA40_0>=TOK_UNION && LA40_0<=TOK_GE)||LA40_0==TOK_UNTIL||(LA40_0>=TOK_AND && LA40_0<=TOK_IMPLIES)||(LA40_0>=TOK_SINCE && LA40_0<=TOK_TRIGGERED)) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:316:4: simple_expression
            	    {
            	    pushFollow(FOLLOW_simple_expression_in_simple_list_expression_helper1818);
            	    simple_expression10=simple_expression();
            	    _fsp--;

            	     ret.add(input.getTokenStream().toString(
            	      input.getTreeAdaptor().getTokenStartIndex(simple_expression10.start),
            	      input.getTreeAdaptor().getTokenStopIndex(simple_expression10.start))); 

            	    }
            	    break;

            	default :
            	    if ( cnt40 >= 1 ) break loop40;
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end simple_list_expression_helper


    // $ANTLR start module_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:1: module_list : ( module | game_definition ) ( module | game_definition )* ;
    public final void module_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:14: ( ( module | game_definition ) ( module | game_definition )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:16: ( module | game_definition ) ( module | game_definition )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:16: ( module | game_definition )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==TOK_MODULE) ) {
                alt41=1;
            }
            else if ( (LA41_0==TOK_GAME) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("331:16: ( module | game_definition )", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:17: module
                    {
                    pushFollow(FOLLOW_module_in_module_list1845);
                    module();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:331:26: game_definition
                    {
                    pushFollow(FOLLOW_game_definition_in_module_list1849);
                    game_definition();
                    _fsp--;


                    }
                    break;

            }

            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:332:4: ( module | game_definition )*
            loop42:
            do {
                int alt42=3;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==TOK_MODULE) ) {
                    alt42=1;
                }
                else if ( (LA42_0==TOK_GAME) ) {
                    alt42=2;
                }


                switch (alt42) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:332:6: module
            	    {
            	    pushFollow(FOLLOW_module_in_module_list1858);
            	    module();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:333:6: game_definition
            	    {
            	    pushFollow(FOLLOW_game_definition_in_module_list1866);
            	    game_definition();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end module_list


    // $ANTLR start module
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:335:1: module : ^( TOK_MODULE module_sign declarations ) ;
    public final void module() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:335:9: ( ^( TOK_MODULE module_sign declarations ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:335:11: ^( TOK_MODULE module_sign declarations )
            {
            match(input,TOK_MODULE,FOLLOW_TOK_MODULE_in_module1880); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_module_sign_in_module1885);
            module_sign();
            _fsp--;

            pushFollow(FOLLOW_declarations_in_module1891);
            declarations();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end module


    // $ANTLR start module_sign
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:339:1: module_sign : ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) );
    public final void module_sign() throws RecognitionException {
        CommonTree TOK_ATOM11=null;
        CommonTree TOK_ATOM12=null;

        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:339:14: ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==MODULE_SIGN_T) ) {
                int LA43_1 = input.LA(2);

                if ( (LA43_1==DOWN) ) {
                    int LA43_2 = input.LA(3);

                    if ( (LA43_2==TOK_ATOM) ) {
                        int LA43_3 = input.LA(4);

                        if ( (LA43_3==UP) ) {
                            alt43=1;
                        }
                        else if ( (LA43_3==REF_LIST_T) ) {
                            alt43=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("339:1: module_sign : ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) );", 43, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("339:1: module_sign : ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) );", 43, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("339:1: module_sign : ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) );", 43, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("339:1: module_sign : ( ^( MODULE_SIGN_T TOK_ATOM ) | ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) ) );", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:339:16: ^( MODULE_SIGN_T TOK_ATOM )
                    {
                    match(input,MODULE_SIGN_T,FOLLOW_MODULE_SIGN_T_in_module_sign1904); 

                    match(input, Token.DOWN, null); 
                    TOK_ATOM11=(CommonTree)input.LT(1);
                    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_module_sign1906); 

                    match(input, Token.UP, null); 
                     curr_module_name = TOK_ATOM11.getText(); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:341:5: ^( MODULE_SIGN_T TOK_ATOM ^( REF_LIST_T sign_atom_list ) )
                    {
                    match(input,MODULE_SIGN_T,FOLLOW_MODULE_SIGN_T_in_module_sign1918); 

                    match(input, Token.DOWN, null); 
                    TOK_ATOM12=(CommonTree)input.LT(1);
                    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_module_sign1920); 
                     curr_module_name = TOK_ATOM12.getText(); 
                    match(input,REF_LIST_T,FOLLOW_REF_LIST_T_in_module_sign1930); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_sign_atom_list_in_module_sign1932);
                    sign_atom_list();
                    _fsp--;


                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end module_sign


    // $ANTLR start sign_atom_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:345:1: sign_atom_list : ( TOK_ATOM )+ ;
    public final void sign_atom_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:345:16: ( ( TOK_ATOM )+ )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:345:18: ( TOK_ATOM )+
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:345:18: ( TOK_ATOM )+
            int cnt44=0;
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==TOK_ATOM) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:345:18: TOK_ATOM
            	    {
            	    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_sign_atom_list1944); 

            	    }
            	    break;

            	default :
            	    if ( cnt44 >= 1 ) break loop44;
                        EarlyExitException eee =
                            new EarlyExitException(44, input);
                        throw eee;
                }
                cnt44++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end sign_atom_list


    // $ANTLR start declarations
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:349:1: declarations : ( declaration )* ;
    public final void declarations() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:349:15: ( ( declaration )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:349:17: ( declaration )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:349:17: ( declaration )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>=TOK_VAR && LA45_0<=TOK_DEFINE)||LA45_0==TOK_ASSIGN||(LA45_0>=TOK_INIT && LA45_0<=TOK_MIRROR)||LA45_0==TOK_COMPUTE||LA45_0==TOK_ISA) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:349:18: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_declarations1960);
            	    declaration();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end declarations


    // $ANTLR start declaration
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:350:1: declaration : ( var | isa | input_var | assign | init | invar | trans | define | fairness | justice | compassion | invarspec | ctlspec | ltlspec | pslspec | compute | constants | predicate | mirror );
    public final void declaration() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:350:14: ( var | isa | input_var | assign | init | invar | trans | define | fairness | justice | compassion | invarspec | ctlspec | ltlspec | pslspec | compute | constants | predicate | mirror )
            int alt46=19;
            switch ( input.LA(1) ) {
            case TOK_VAR:
                {
                alt46=1;
                }
                break;
            case TOK_ISA:
                {
                alt46=2;
                }
                break;
            case TOK_IVAR:
                {
                alt46=3;
                }
                break;
            case TOK_ASSIGN:
                {
                alt46=4;
                }
                break;
            case TOK_INIT:
                {
                alt46=5;
                }
                break;
            case TOK_INVAR:
                {
                alt46=6;
                }
                break;
            case TOK_TRANS:
                {
                alt46=7;
                }
                break;
            case TOK_DEFINE:
                {
                alt46=8;
                }
                break;
            case TOK_FAIRNESS:
                {
                alt46=9;
                }
                break;
            case TOK_JUSTICE:
                {
                alt46=10;
                }
                break;
            case TOK_COMPASSION:
                {
                alt46=11;
                }
                break;
            case TOK_INVARSPEC:
                {
                alt46=12;
                }
                break;
            case TOK_SPEC:
            case TOK_CTLSPEC:
                {
                alt46=13;
                }
                break;
            case TOK_LTLSPEC:
                {
                alt46=14;
                }
                break;
            case TOK_PSLSPEC:
                {
                alt46=15;
                }
                break;
            case TOK_COMPUTE:
                {
                alt46=16;
                }
                break;
            case TOK_CONSTANTS:
                {
                alt46=17;
                }
                break;
            case TOK_PRED:
                {
                alt46=18;
                }
                break;
            case TOK_MIRROR:
                {
                alt46=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("350:1: declaration : ( var | isa | input_var | assign | init | invar | trans | define | fairness | justice | compassion | invarspec | ctlspec | ltlspec | pslspec | compute | constants | predicate | mirror );", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:350:16: var
                    {
                    pushFollow(FOLLOW_var_in_declaration1970);
                    var();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:351:5: isa
                    {
                    pushFollow(FOLLOW_isa_in_declaration1976);
                    isa();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:352:5: input_var
                    {
                    pushFollow(FOLLOW_input_var_in_declaration1983);
                    input_var();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:353:5: assign
                    {
                    pushFollow(FOLLOW_assign_in_declaration1989);
                    assign();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:354:5: init
                    {
                    pushFollow(FOLLOW_init_in_declaration1996);
                    init();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:355:5: invar
                    {
                    pushFollow(FOLLOW_invar_in_declaration2002);
                    invar();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:356:5: trans
                    {
                    pushFollow(FOLLOW_trans_in_declaration2009);
                    trans();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:357:5: define
                    {
                    pushFollow(FOLLOW_define_in_declaration2015);
                    define();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:358:5: fairness
                    {
                    pushFollow(FOLLOW_fairness_in_declaration2021);
                    fairness();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:359:5: justice
                    {
                    pushFollow(FOLLOW_justice_in_declaration2028);
                    justice();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:360:5: compassion
                    {
                    pushFollow(FOLLOW_compassion_in_declaration2034);
                    compassion();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:361:5: invarspec
                    {
                    pushFollow(FOLLOW_invarspec_in_declaration2040);
                    invarspec();
                    _fsp--;


                    }
                    break;
                case 13 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:362:5: ctlspec
                    {
                    pushFollow(FOLLOW_ctlspec_in_declaration2047);
                    ctlspec();
                    _fsp--;


                    }
                    break;
                case 14 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:363:5: ltlspec
                    {
                    pushFollow(FOLLOW_ltlspec_in_declaration2054);
                    ltlspec();
                    _fsp--;


                    }
                    break;
                case 15 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:364:5: pslspec
                    {
                    pushFollow(FOLLOW_pslspec_in_declaration2061);
                    pslspec();
                    _fsp--;


                    }
                    break;
                case 16 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:365:5: compute
                    {
                    pushFollow(FOLLOW_compute_in_declaration2068);
                    compute();
                    _fsp--;


                    }
                    break;
                case 17 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:366:5: constants
                    {
                    pushFollow(FOLLOW_constants_in_declaration2075);
                    constants();
                    _fsp--;


                    }
                    break;
                case 18 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:367:5: predicate
                    {
                    pushFollow(FOLLOW_predicate_in_declaration2082);
                    predicate();
                    _fsp--;


                    }
                    break;
                case 19 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:368:5: mirror
                    {
                    pushFollow(FOLLOW_mirror_in_declaration2089);
                    mirror();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end declaration


    // $ANTLR start game_definition
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:377:1: game_definition : ^( TOK_GAME game_body ) ;
    public final void game_definition() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:377:17: ( ^( TOK_GAME game_body ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:377:19: ^( TOK_GAME game_body )
            {
            match(input,TOK_GAME,FOLLOW_TOK_GAME_in_game_definition2104); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_game_body_in_game_definition2106);
            game_body();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end game_definition


    // $ANTLR start game_body
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:379:1: game_body : game_body_element ( game_body )? ;
    public final void game_body() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:379:12: ( game_body_element ( game_body )? )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:379:14: game_body_element ( game_body )?
            {
            pushFollow(FOLLOW_game_body_element_in_game_body2118);
            game_body_element();
            _fsp--;

            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:379:32: ( game_body )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( ((LA47_0>=TOK_PLAYER_1 && LA47_0<=TOK_PLAYER_2)||(LA47_0>=TOK_REACHTARGET && LA47_0<=TOK_GENREACTIVITY)) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:379:33: game_body
                    {
                    pushFollow(FOLLOW_game_body_in_game_body2121);
                    game_body();
                    _fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end game_body


    // $ANTLR start game_body_element
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:383:1: game_body_element : ( ^( TOK_PLAYER_1 player_body ) | ^( TOK_PLAYER_2 player_body ) | reachtarget | avoidtarget | reachdeadlock | avoiddeadlock | buchigame | genreactivity );
    public final void game_body_element() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:383:19: ( ^( TOK_PLAYER_1 player_body ) | ^( TOK_PLAYER_2 player_body ) | reachtarget | avoidtarget | reachdeadlock | avoiddeadlock | buchigame | genreactivity )
            int alt48=8;
            switch ( input.LA(1) ) {
            case TOK_PLAYER_1:
                {
                alt48=1;
                }
                break;
            case TOK_PLAYER_2:
                {
                alt48=2;
                }
                break;
            case TOK_REACHTARGET:
                {
                alt48=3;
                }
                break;
            case TOK_AVOIDTARGET:
                {
                alt48=4;
                }
                break;
            case TOK_REACHDEADLOCK:
                {
                alt48=5;
                }
                break;
            case TOK_AVOIDDEADLOCK:
                {
                alt48=6;
                }
                break;
            case TOK_BUCHIGAME:
                {
                alt48=7;
                }
                break;
            case TOK_GENREACTIVITY:
                {
                alt48=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("383:1: game_body_element : ( ^( TOK_PLAYER_1 player_body ) | ^( TOK_PLAYER_2 player_body ) | reachtarget | avoidtarget | reachdeadlock | avoiddeadlock | buchigame | genreactivity );", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:383:21: ^( TOK_PLAYER_1 player_body )
                    {
                    match(input,TOK_PLAYER_1,FOLLOW_TOK_PLAYER_1_in_game_body_element2133); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        pushFollow(FOLLOW_player_body_in_game_body_element2135);
                        player_body();
                        _fsp--;


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:384:5: ^( TOK_PLAYER_2 player_body )
                    {
                    match(input,TOK_PLAYER_2,FOLLOW_TOK_PLAYER_2_in_game_body_element2143); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        pushFollow(FOLLOW_player_body_in_game_body_element2145);
                        player_body();
                        _fsp--;


                        match(input, Token.UP, null); 
                    }

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:390:5: reachtarget
                    {
                    pushFollow(FOLLOW_reachtarget_in_game_body_element2157);
                    reachtarget();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:391:5: avoidtarget
                    {
                    pushFollow(FOLLOW_avoidtarget_in_game_body_element2163);
                    avoidtarget();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:392:5: reachdeadlock
                    {
                    pushFollow(FOLLOW_reachdeadlock_in_game_body_element2169);
                    reachdeadlock();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:393:5: avoiddeadlock
                    {
                    pushFollow(FOLLOW_avoiddeadlock_in_game_body_element2175);
                    avoiddeadlock();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:394:5: buchigame
                    {
                    pushFollow(FOLLOW_buchigame_in_game_body_element2181);
                    buchigame();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:395:5: genreactivity
                    {
                    pushFollow(FOLLOW_genreactivity_in_game_body_element2187);
                    genreactivity();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end game_body_element


    // $ANTLR start player_body
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:400:1: player_body : ( player_body_element )* ;
    public final void player_body() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:400:14: ( ( player_body_element )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:400:16: ( player_body_element )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:400:16: ( player_body_element )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==TOK_VAR||LA49_0==TOK_DEFINE||LA49_0==TOK_ASSIGN||(LA49_0>=TOK_INIT && LA49_0<=TOK_TRANS)) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:400:17: player_body_element
            	    {
            	    pushFollow(FOLLOW_player_body_element_in_player_body2201);
            	    player_body_element();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end player_body


    // $ANTLR start player_body_element
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:402:1: player_body_element : ( var | assign | init | invar | trans | define );
    public final void player_body_element() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:402:21: ( var | assign | init | invar | trans | define )
            int alt50=6;
            switch ( input.LA(1) ) {
            case TOK_VAR:
                {
                alt50=1;
                }
                break;
            case TOK_ASSIGN:
                {
                alt50=2;
                }
                break;
            case TOK_INIT:
                {
                alt50=3;
                }
                break;
            case TOK_INVAR:
                {
                alt50=4;
                }
                break;
            case TOK_TRANS:
                {
                alt50=5;
                }
                break;
            case TOK_DEFINE:
                {
                alt50=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("402:1: player_body_element : ( var | assign | init | invar | trans | define );", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:402:23: var
                    {
                    pushFollow(FOLLOW_var_in_player_body_element2213);
                    var();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:404:5: assign
                    {
                    pushFollow(FOLLOW_assign_in_player_body_element2220);
                    assign();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:405:5: init
                    {
                    pushFollow(FOLLOW_init_in_player_body_element2227);
                    init();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:406:5: invar
                    {
                    pushFollow(FOLLOW_invar_in_player_body_element2233);
                    invar();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:407:5: trans
                    {
                    pushFollow(FOLLOW_trans_in_player_body_element2240);
                    trans();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:408:5: define
                    {
                    pushFollow(FOLLOW_define_in_player_body_element2246);
                    define();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end player_body_element


    // $ANTLR start var
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:419:1: var : ( TOK_VAR | ^( TOK_VAR var_decl_list ) );
    public final void var() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:419:6: ( TOK_VAR | ^( TOK_VAR var_decl_list ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==TOK_VAR) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==DOWN) ) {
                    alt51=2;
                }
                else if ( (LA51_1==UP||(LA51_1>=TOK_VAR && LA51_1<=TOK_DEFINE)||LA51_1==TOK_ASSIGN||(LA51_1>=TOK_INIT && LA51_1<=TOK_MIRROR)||LA51_1==TOK_COMPUTE||LA51_1==TOK_ISA) ) {
                    alt51=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("419:1: var : ( TOK_VAR | ^( TOK_VAR var_decl_list ) );", 51, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("419:1: var : ( TOK_VAR | ^( TOK_VAR var_decl_list ) );", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:419:8: TOK_VAR
                    {
                    match(input,TOK_VAR,FOLLOW_TOK_VAR_in_var2264); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:420:5: ^( TOK_VAR var_decl_list )
                    {
                    match(input,TOK_VAR,FOLLOW_TOK_VAR_in_var2271); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_var_decl_list_in_var2273);
                    var_decl_list();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end var


    // $ANTLR start var_decl_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:423:1: var_decl_list : var_decl ( var_decl )* ;
    public final void var_decl_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:423:16: ( var_decl ( var_decl )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:423:18: var_decl ( var_decl )*
            {
            pushFollow(FOLLOW_var_decl_in_var_decl_list2287);
            var_decl();
            _fsp--;

            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:423:27: ( var_decl )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==VAR_DECL_T) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:423:28: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_var_decl_list2290);
            	    var_decl();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end var_decl_list


    // $ANTLR start var_decl
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:426:1: var_decl : ^( VAR_DECL_T decl_var_id type ) ;
    public final void var_decl() throws RecognitionException {
        String decl_var_id13 = null;

        SMVAbstractElementInfo type14 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:426:11: ( ^( VAR_DECL_T decl_var_id type ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:426:13: ^( VAR_DECL_T decl_var_id type )
            {
            match(input,VAR_DECL_T,FOLLOW_VAR_DECL_T_in_var_decl2306); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_decl_var_id_in_var_decl2308);
            decl_var_id13=decl_var_id();
            _fsp--;

            curr_var_name = decl_var_id13;
            pushFollow(FOLLOW_type_in_var_decl2312);
            type14=type();
            _fsp--;

             smv_initiator.add_element(curr_module_name, type14, input); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end var_decl


    // $ANTLR start input_var
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:428:1: input_var : ( TOK_IVAR | ^( TOK_IVAR ivar_decl_list ) );
    public final void input_var() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:428:12: ( TOK_IVAR | ^( TOK_IVAR ivar_decl_list ) )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==TOK_IVAR) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==DOWN) ) {
                    alt53=2;
                }
                else if ( (LA53_1==UP||(LA53_1>=TOK_VAR && LA53_1<=TOK_DEFINE)||LA53_1==TOK_ASSIGN||(LA53_1>=TOK_INIT && LA53_1<=TOK_MIRROR)||LA53_1==TOK_COMPUTE||LA53_1==TOK_ISA) ) {
                    alt53=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("428:1: input_var : ( TOK_IVAR | ^( TOK_IVAR ivar_decl_list ) );", 53, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("428:1: input_var : ( TOK_IVAR | ^( TOK_IVAR ivar_decl_list ) );", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:428:14: TOK_IVAR
                    {
                    match(input,TOK_IVAR,FOLLOW_TOK_IVAR_in_input_var2326); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:429:5: ^( TOK_IVAR ivar_decl_list )
                    {
                    match(input,TOK_IVAR,FOLLOW_TOK_IVAR_in_input_var2333); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_ivar_decl_list_in_input_var2335);
                    ivar_decl_list();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end input_var


    // $ANTLR start ivar_decl_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:432:1: ivar_decl_list : ivar_decl ( ivar_decl )* ;
    public final void ivar_decl_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:432:17: ( ivar_decl ( ivar_decl )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:432:19: ivar_decl ( ivar_decl )*
            {
            pushFollow(FOLLOW_ivar_decl_in_ivar_decl_list2349);
            ivar_decl();
            _fsp--;

            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:432:29: ( ivar_decl )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==IVAR_DECL_T) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:432:30: ivar_decl
            	    {
            	    pushFollow(FOLLOW_ivar_decl_in_ivar_decl_list2352);
            	    ivar_decl();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ivar_decl_list


    // $ANTLR start ivar_decl
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:434:1: ivar_decl : ^( IVAR_DECL_T decl_var_id itype ) ;
    public final void ivar_decl() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:434:12: ( ^( IVAR_DECL_T decl_var_id itype ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:434:14: ^( IVAR_DECL_T decl_var_id itype )
            {
            match(input,IVAR_DECL_T,FOLLOW_IVAR_DECL_T_in_ivar_decl2366); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_decl_var_id_in_ivar_decl2368);
            decl_var_id();
            _fsp--;

            pushFollow(FOLLOW_itype_in_ivar_decl2370);
            itype();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ivar_decl


    // $ANTLR start define
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:438:1: define : ^( TOK_DEFINE define_list ) ;
    public final void define() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:438:9: ( ^( TOK_DEFINE define_list ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:438:11: ^( TOK_DEFINE define_list )
            {
            match(input,TOK_DEFINE,FOLLOW_TOK_DEFINE_in_define2386); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_define_list_in_define2388);
                define_list();
                _fsp--;


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end define


    // $ANTLR start define_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:440:1: define_list : ( define_decl )* ;
    public final void define_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:440:14: ( ( define_decl )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:440:16: ( define_decl )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:440:16: ( define_decl )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==DEFINE_DECL_T) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:440:16: define_decl
            	    {
            	    pushFollow(FOLLOW_define_decl_in_define_list2400);
            	    define_decl();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end define_list


    // $ANTLR start define_decl
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:442:1: define_decl : ^( DEFINE_DECL_T decl_var_id simple_expression ) ;
    public final void define_decl() throws RecognitionException {
        String decl_var_id15 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:442:14: ( ^( DEFINE_DECL_T decl_var_id simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:442:16: ^( DEFINE_DECL_T decl_var_id simple_expression )
            {
            match(input,DEFINE_DECL_T,FOLLOW_DEFINE_DECL_T_in_define_decl2413); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_decl_var_id_in_define_decl2415);
            decl_var_id15=decl_var_id();
            _fsp--;

            curr_var_name = decl_var_id15;
            pushFollow(FOLLOW_simple_expression_in_define_decl2419);
            simple_expression();
            _fsp--;

             smv_initiator.add_element(curr_module_name, smv_initiator.mk_define_var(curr_var_name, input), input); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end define_decl


    // $ANTLR start assign
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:447:1: assign : ^( TOK_ASSIGN assign_list ) ;
    public final void assign() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:447:9: ( ^( TOK_ASSIGN assign_list ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:447:11: ^( TOK_ASSIGN assign_list )
            {
            match(input,TOK_ASSIGN,FOLLOW_TOK_ASSIGN_in_assign2440); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_assign_list_in_assign2442);
                assign_list();
                _fsp--;


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end assign


    // $ANTLR start assign_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:449:1: assign_list : ( one_assign )* ;
    public final void assign_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:449:14: ( ( one_assign )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:449:16: ( one_assign )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:449:16: ( one_assign )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=ASSIGN_DECL_T && LA56_0<=NEXT_ASSIGN_DECL_T)) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:449:16: one_assign
            	    {
            	    pushFollow(FOLLOW_one_assign_in_assign_list2454);
            	    one_assign();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end assign_list


    // $ANTLR start one_assign
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:451:1: one_assign : ( ^( ASSIGN_DECL_T var_id simple_expression ) | ^( INIT_ASSIGN_DECL_T var_id simple_expression ) | ^( NEXT_ASSIGN_DECL_T var_id next_expression ) );
    public final void one_assign() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:451:12: ( ^( ASSIGN_DECL_T var_id simple_expression ) | ^( INIT_ASSIGN_DECL_T var_id simple_expression ) | ^( NEXT_ASSIGN_DECL_T var_id next_expression ) )
            int alt57=3;
            switch ( input.LA(1) ) {
            case ASSIGN_DECL_T:
                {
                alt57=1;
                }
                break;
            case INIT_ASSIGN_DECL_T:
                {
                alt57=2;
                }
                break;
            case NEXT_ASSIGN_DECL_T:
                {
                alt57=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("451:1: one_assign : ( ^( ASSIGN_DECL_T var_id simple_expression ) | ^( INIT_ASSIGN_DECL_T var_id simple_expression ) | ^( NEXT_ASSIGN_DECL_T var_id next_expression ) );", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:451:14: ^( ASSIGN_DECL_T var_id simple_expression )
                    {
                    match(input,ASSIGN_DECL_T,FOLLOW_ASSIGN_DECL_T_in_one_assign2466); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_var_id_in_one_assign2468);
                    var_id();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_one_assign2470);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:452:5: ^( INIT_ASSIGN_DECL_T var_id simple_expression )
                    {
                    match(input,INIT_ASSIGN_DECL_T,FOLLOW_INIT_ASSIGN_DECL_T_in_one_assign2478); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_var_id_in_one_assign2480);
                    var_id();
                    _fsp--;

                    pushFollow(FOLLOW_simple_expression_in_one_assign2482);
                    simple_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:453:5: ^( NEXT_ASSIGN_DECL_T var_id next_expression )
                    {
                    match(input,NEXT_ASSIGN_DECL_T,FOLLOW_NEXT_ASSIGN_DECL_T_in_one_assign2490); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_var_id_in_one_assign2492);
                    var_id();
                    _fsp--;

                    pushFollow(FOLLOW_next_expression_in_one_assign2494);
                    next_expression();
                    _fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end one_assign


    // $ANTLR start init
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:457:1: init : ^( TOK_INIT simple_expression ) ;
    public final void init() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:457:7: ( ^( TOK_INIT simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:457:9: ^( TOK_INIT simple_expression )
            {
            match(input,TOK_INIT,FOLLOW_TOK_INIT_in_init2510); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_simple_expression_in_init2512);
            simple_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end init


    // $ANTLR start invar
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:461:1: invar : TOK_INVAR ;
    public final void invar() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:461:8: ( TOK_INVAR )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:461:10: TOK_INVAR
            {
            match(input,TOK_INVAR,FOLLOW_TOK_INVAR_in_invar2527); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end invar


    // $ANTLR start trans
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:465:1: trans : ^( TOK_TRANS next_expression ) ;
    public final void trans() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:465:8: ( ^( TOK_TRANS next_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:465:10: ^( TOK_TRANS next_expression )
            {
            match(input,TOK_TRANS,FOLLOW_TOK_TRANS_in_trans2542); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_next_expression_in_trans2544);
            next_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end trans


    // $ANTLR start fairness
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:470:1: fairness : TOK_FAIRNESS ;
    public final void fairness() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:470:11: ( TOK_FAIRNESS )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:470:13: TOK_FAIRNESS
            {
            match(input,TOK_FAIRNESS,FOLLOW_TOK_FAIRNESS_in_fairness2561); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end fairness


    // $ANTLR start justice
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:473:1: justice : ^( TOK_JUSTICE justice_list ) ;
    public final void justice() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:473:10: ( ^( TOK_JUSTICE justice_list ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:473:12: ^( TOK_JUSTICE justice_list )
            {
            match(input,TOK_JUSTICE,FOLLOW_TOK_JUSTICE_in_justice2575); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_justice_list_in_justice2577);
                justice_list();
                _fsp--;


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end justice


    // $ANTLR start justice_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:475:1: justice_list : ( justice_decl )* ;
    public final void justice_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:475:14: ( ( justice_decl )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:475:16: ( justice_decl )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:475:16: ( justice_decl )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==JUSTICE_DECL_T) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:475:16: justice_decl
            	    {
            	    pushFollow(FOLLOW_justice_decl_in_justice_list2588);
            	    justice_decl();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end justice_list


    // $ANTLR start justice_decl
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:477:1: justice_decl : ^( JUSTICE_DECL_T simple_expression ) ;
    public final void justice_decl() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:477:14: ( ^( JUSTICE_DECL_T simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:477:16: ^( JUSTICE_DECL_T simple_expression )
            {
            match(input,JUSTICE_DECL_T,FOLLOW_JUSTICE_DECL_T_in_justice_decl2600); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_simple_expression_in_justice_decl2602);
            simple_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end justice_decl


    // $ANTLR start compassion
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:479:1: compassion : ^( TOK_COMPASSION compassion_list ) ;
    public final void compassion() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:479:12: ( ^( TOK_COMPASSION compassion_list ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:479:14: ^( TOK_COMPASSION compassion_list )
            {
            match(input,TOK_COMPASSION,FOLLOW_TOK_COMPASSION_in_compassion2614); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_compassion_list_in_compassion2616);
                compassion_list();
                _fsp--;


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compassion


    // $ANTLR start compassion_list
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:481:1: compassion_list : ( compassion_decl )* ;
    public final void compassion_list() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:481:17: ( ( compassion_decl )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:481:19: ( compassion_decl )*
            {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:481:19: ( compassion_decl )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==COMPASSION_DECL_T) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:481:19: compassion_decl
            	    {
            	    pushFollow(FOLLOW_compassion_decl_in_compassion_list2627);
            	    compassion_decl();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compassion_list


    // $ANTLR start compassion_decl
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:483:1: compassion_decl : ^( COMPASSION_DECL_T simple_expression TOK_COMMA simple_expression ) ;
    public final void compassion_decl() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:483:17: ( ^( COMPASSION_DECL_T simple_expression TOK_COMMA simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:483:19: ^( COMPASSION_DECL_T simple_expression TOK_COMMA simple_expression )
            {
            match(input,COMPASSION_DECL_T,FOLLOW_COMPASSION_DECL_T_in_compassion_decl2639); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_simple_expression_in_compassion_decl2641);
            simple_expression();
            _fsp--;

            match(input,TOK_COMMA,FOLLOW_TOK_COMMA_in_compassion_decl2643); 
            pushFollow(FOLLOW_simple_expression_in_compassion_decl2645);
            simple_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compassion_decl


    // $ANTLR start invarspec
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:486:1: invarspec : TOK_INVARSPEC ;
    public final void invarspec() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:486:12: ( TOK_INVARSPEC )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:486:14: TOK_INVARSPEC
            {
            match(input,TOK_INVARSPEC,FOLLOW_TOK_INVARSPEC_in_invarspec2659); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end invarspec


    // $ANTLR start ctlspec
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:490:1: ctlspec : ( TOK_SPEC | TOK_CTLSPEC );
    public final void ctlspec() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:490:10: ( TOK_SPEC | TOK_CTLSPEC )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:
            {
            if ( (input.LA(1)>=TOK_SPEC && input.LA(1)<=TOK_CTLSPEC) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_ctlspec0);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ctlspec


    // $ANTLR start ltlspec
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:493:1: ltlspec : TOK_LTLSPEC ;
    public final void ltlspec() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:493:10: ( TOK_LTLSPEC )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:493:12: TOK_LTLSPEC
            {
            match(input,TOK_LTLSPEC,FOLLOW_TOK_LTLSPEC_in_ltlspec2690); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ltlspec


    // $ANTLR start pslspec
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:495:1: pslspec : TOK_PSLSPEC ;
    public final void pslspec() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:495:10: ( TOK_PSLSPEC )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:495:12: TOK_PSLSPEC
            {
            match(input,TOK_PSLSPEC,FOLLOW_TOK_PSLSPEC_in_pslspec2701); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end pslspec


    // $ANTLR start constants
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:497:1: constants : TOK_CONSTANTS ;
    public final void constants() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:497:12: ( TOK_CONSTANTS )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:497:14: TOK_CONSTANTS
            {
            match(input,TOK_CONSTANTS,FOLLOW_TOK_CONSTANTS_in_constants2712); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constants


    // $ANTLR start constants_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:503:1: constants_expression : ( | complex_atom ( TOK_COMMA complex_atom )* );
    public final void constants_expression() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:503:22: ( | complex_atom ( TOK_COMMA complex_atom )* )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==EOF) ) {
                alt61=1;
            }
            else if ( (LA61_0==TOK_ATOM) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("503:1: constants_expression : ( | complex_atom ( TOK_COMMA complex_atom )* );", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:504:3: 
                    {
                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:504:5: complex_atom ( TOK_COMMA complex_atom )*
                    {
                    pushFollow(FOLLOW_complex_atom_in_constants_expression2732);
                    complex_atom();
                    _fsp--;

                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:504:18: ( TOK_COMMA complex_atom )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==TOK_COMMA) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:504:19: TOK_COMMA complex_atom
                    	    {
                    	    match(input,TOK_COMMA,FOLLOW_TOK_COMMA_in_constants_expression2735); 
                    	    pushFollow(FOLLOW_complex_atom_in_constants_expression2737);
                    	    complex_atom();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end constants_expression


    // $ANTLR start player_num
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:507:1: player_num : ( TOK_PLAYER_1 | TOK_PLAYER_2 );
    public final void player_num() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:507:13: ( TOK_PLAYER_1 | TOK_PLAYER_2 )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:
            {
            if ( (input.LA(1)>=TOK_PLAYER_1 && input.LA(1)<=TOK_PLAYER_2) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_player_num0);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end player_num


    // $ANTLR start predicate
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:512:1: predicate : TOK_PRED ;
    public final void predicate() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:512:12: ( TOK_PRED )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:512:14: TOK_PRED
            {
            match(input,TOK_PRED,FOLLOW_TOK_PRED_in_predicate2771); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end predicate


    // $ANTLR start mirror
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:514:1: mirror : TOK_MIRROR ;
    public final void mirror() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:514:9: ( TOK_MIRROR )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:514:11: TOK_MIRROR
            {
            match(input,TOK_MIRROR,FOLLOW_TOK_MIRROR_in_mirror2782); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end mirror


    // $ANTLR start reachtarget
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:518:1: reachtarget : ^( TOK_REACHTARGET player_num simple_expression ) ;
    public final void reachtarget() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:518:14: ( ^( TOK_REACHTARGET player_num simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:518:16: ^( TOK_REACHTARGET player_num simple_expression )
            {
            match(input,TOK_REACHTARGET,FOLLOW_TOK_REACHTARGET_in_reachtarget2797); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_reachtarget2799);
            player_num();
            _fsp--;

            pushFollow(FOLLOW_simple_expression_in_reachtarget2801);
            simple_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end reachtarget


    // $ANTLR start avoidtarget
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:520:1: avoidtarget : ^( TOK_AVOIDTARGET player_num simple_expression ) ;
    public final void avoidtarget() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:520:14: ( ^( TOK_AVOIDTARGET player_num simple_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:520:16: ^( TOK_AVOIDTARGET player_num simple_expression )
            {
            match(input,TOK_AVOIDTARGET,FOLLOW_TOK_AVOIDTARGET_in_avoidtarget2814); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_avoidtarget2816);
            player_num();
            _fsp--;

            pushFollow(FOLLOW_simple_expression_in_avoidtarget2818);
            simple_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end avoidtarget


    // $ANTLR start reachdeadlock
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:522:1: reachdeadlock : ^( TOK_REACHDEADLOCK player_num ) ;
    public final void reachdeadlock() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:522:15: ( ^( TOK_REACHDEADLOCK player_num ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:522:17: ^( TOK_REACHDEADLOCK player_num )
            {
            match(input,TOK_REACHDEADLOCK,FOLLOW_TOK_REACHDEADLOCK_in_reachdeadlock2830); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_reachdeadlock2832);
            player_num();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end reachdeadlock


    // $ANTLR start avoiddeadlock
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:524:1: avoiddeadlock : ^( TOK_AVOIDDEADLOCK player_num ) ;
    public final void avoiddeadlock() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:524:15: ( ^( TOK_AVOIDDEADLOCK player_num ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:524:17: ^( TOK_AVOIDDEADLOCK player_num )
            {
            match(input,TOK_AVOIDDEADLOCK,FOLLOW_TOK_AVOIDDEADLOCK_in_avoiddeadlock2844); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_avoiddeadlock2846);
            player_num();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end avoiddeadlock


    // $ANTLR start buchigame
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:526:1: buchigame : ^( TOK_BUCHIGAME player_num simple_list_expression ) ;
    public final void buchigame() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:526:12: ( ^( TOK_BUCHIGAME player_num simple_list_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:526:14: ^( TOK_BUCHIGAME player_num simple_list_expression )
            {
            match(input,TOK_BUCHIGAME,FOLLOW_TOK_BUCHIGAME_in_buchigame2859); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_buchigame2861);
            player_num();
            _fsp--;

            pushFollow(FOLLOW_simple_list_expression_in_buchigame2863);
            simple_list_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end buchigame


    // $ANTLR start genreactivity
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:528:1: genreactivity : ^( TOK_GENREACTIVITY player_num simple_list_expression TOK_IMPLIES simple_list_expression ) ;
    public final void genreactivity() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:528:16: ( ^( TOK_GENREACTIVITY player_num simple_list_expression TOK_IMPLIES simple_list_expression ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:528:18: ^( TOK_GENREACTIVITY player_num simple_list_expression TOK_IMPLIES simple_list_expression )
            {
            match(input,TOK_GENREACTIVITY,FOLLOW_TOK_GENREACTIVITY_in_genreactivity2876); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_player_num_in_genreactivity2878);
            player_num();
            _fsp--;

            pushFollow(FOLLOW_simple_list_expression_in_genreactivity2880);
            simple_list_expression();
            _fsp--;

            match(input,TOK_IMPLIES,FOLLOW_TOK_IMPLIES_in_genreactivity2882); 
            pushFollow(FOLLOW_simple_list_expression_in_genreactivity2884);
            simple_list_expression();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end genreactivity


    // $ANTLR start compute
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:532:1: compute : TOK_COMPUTE ;
    public final void compute() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:532:10: ( TOK_COMPUTE )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:532:12: TOK_COMPUTE
            {
            match(input,TOK_COMPUTE,FOLLOW_TOK_COMPUTE_in_compute2899); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compute


    // $ANTLR start compute_expression
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:534:1: compute_expression : ( TOK_MMIN TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB | TOK_MMAX TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB );
    public final void compute_expression() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:534:20: ( TOK_MMIN TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB | TOK_MMAX TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==TOK_MMIN) ) {
                alt62=1;
            }
            else if ( (LA62_0==TOK_MMAX) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("534:1: compute_expression : ( TOK_MMIN TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB | TOK_MMAX TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB );", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:534:22: TOK_MMIN TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB
                    {
                    match(input,TOK_MMIN,FOLLOW_TOK_MMIN_in_compute_expression2909); 
                    match(input,TOK_LB,FOLLOW_TOK_LB_in_compute_expression2911); 
                    pushFollow(FOLLOW_ctl_expression_in_compute_expression2913);
                    ctl_expression();
                    _fsp--;

                    match(input,TOK_COMMA,FOLLOW_TOK_COMMA_in_compute_expression2915); 
                    pushFollow(FOLLOW_ctl_expression_in_compute_expression2917);
                    ctl_expression();
                    _fsp--;

                    match(input,TOK_RB,FOLLOW_TOK_RB_in_compute_expression2919); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:535:5: TOK_MMAX TOK_LB ctl_expression TOK_COMMA ctl_expression TOK_RB
                    {
                    match(input,TOK_MMAX,FOLLOW_TOK_MMAX_in_compute_expression2925); 
                    match(input,TOK_LB,FOLLOW_TOK_LB_in_compute_expression2927); 
                    pushFollow(FOLLOW_ctl_expression_in_compute_expression2929);
                    ctl_expression();
                    _fsp--;

                    match(input,TOK_COMMA,FOLLOW_TOK_COMMA_in_compute_expression2931); 
                    pushFollow(FOLLOW_ctl_expression_in_compute_expression2933);
                    ctl_expression();
                    _fsp--;

                    match(input,TOK_RB,FOLLOW_TOK_RB_in_compute_expression2935); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end compute_expression


    // $ANTLR start isa
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:539:1: isa : TOK_ISA ;
    public final void isa() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:539:6: ( TOK_ISA )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:539:8: TOK_ISA
            {
            match(input,TOK_ISA,FOLLOW_TOK_ISA_in_isa2949); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end isa


    // $ANTLR start optsemi
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:544:1: optsemi : ( | TOK_SEMI );
    public final void optsemi() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:544:10: ( | TOK_SEMI )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==EOF) ) {
                alt63=1;
            }
            else if ( (LA63_0==TOK_SEMI) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("544:1: optsemi : ( | TOK_SEMI );", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:545:3: 
                    {
                    }
                    break;
                case 2 :
                    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:545:5: TOK_SEMI
                    {
                    match(input,TOK_SEMI,FOLLOW_TOK_SEMI_in_optsemi2970); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end optsemi


    // $ANTLR start decl_var_id
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:552:1: decl_var_id returns [String ret] : ^( REF_T decl_var_id_helper ) ;
    public final String decl_var_id() throws RecognitionException {
        String ret = null;

        String decl_var_id_helper16 = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:553:3: ( ^( REF_T decl_var_id_helper ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:553:5: ^( REF_T decl_var_id_helper )
            {
            match(input,REF_T,FOLLOW_REF_T_in_decl_var_id2987); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_decl_var_id_helper_in_decl_var_id2989);
            decl_var_id_helper16=decl_var_id_helper();
            _fsp--;


            match(input, Token.UP, null); 
             ret = decl_var_id_helper16; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end decl_var_id


    // $ANTLR start decl_var_id_helper
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:556:1: decl_var_id_helper returns [String ret] : f= TOK_ATOM (ra= TOK_ATOM | rn= TOK_NUMBER | ri= integer )* ;
    public final String decl_var_id_helper() throws RecognitionException {
        String ret = null;

        CommonTree f=null;
        CommonTree ra=null;
        CommonTree rn=null;
        integer_return ri = null;


        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:557:3: (f= TOK_ATOM (ra= TOK_ATOM | rn= TOK_NUMBER | ri= integer )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:557:5: f= TOK_ATOM (ra= TOK_ATOM | rn= TOK_NUMBER | ri= integer )*
            {
            f=(CommonTree)input.LT(1);
            match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_decl_var_id_helper3011); 
             ret = f.getText(); 
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:559:4: (ra= TOK_ATOM | rn= TOK_NUMBER | ri= integer )*
            loop64:
            do {
                int alt64=4;
                switch ( input.LA(1) ) {
                case TOK_ATOM:
                    {
                    alt64=1;
                    }
                    break;
                case TOK_NUMBER:
                    {
                    alt64=2;
                    }
                    break;
                case TOK_PLUS:
                case TOK_MINUS:
                    {
                    alt64=3;
                    }
                    break;

                }

                switch (alt64) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:559:6: ra= TOK_ATOM
            	    {
            	    ra=(CommonTree)input.LT(1);
            	    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_decl_var_id_helper3024); 
            	     ret = ret + "." + ra.getText(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:561:6: rn= TOK_NUMBER
            	    {
            	    rn=(CommonTree)input.LT(1);
            	    match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_decl_var_id_helper3038); 
            	     ret = ret + "." + rn.getText(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:563:6: ri= integer
            	    {
            	    pushFollow(FOLLOW_integer_in_decl_var_id_helper3052);
            	    ri=integer();
            	    _fsp--;

            	     ret = ret + "[" + input.getTokenStream().toString(
            	      input.getTreeAdaptor().getTokenStartIndex(ri.start),
            	      input.getTreeAdaptor().getTokenStopIndex(ri.start)) + "]"; 

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end decl_var_id_helper


    // $ANTLR start var_id
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:568:1: var_id : ^( REF_T var_id_helper ) ;
    public final void var_id() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:568:9: ( ^( REF_T var_id_helper ) )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:568:11: ^( REF_T var_id_helper )
            {
            match(input,REF_T,FOLLOW_REF_T_in_var_id3076); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_var_id_helper_in_var_id3078);
            var_id_helper();
            _fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end var_id


    // $ANTLR start var_id_helper
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:570:1: var_id_helper : ( TOK_ATOM | TOK_SELF ) ( TOK_ATOM | TOK_NUMBER | simple_expression )* ;
    public final void var_id_helper() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:570:16: ( ( TOK_ATOM | TOK_SELF ) ( TOK_ATOM | TOK_NUMBER | simple_expression )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:570:18: ( TOK_ATOM | TOK_SELF ) ( TOK_ATOM | TOK_NUMBER | simple_expression )*
            {
            if ( (input.LA(1)>=TOK_ATOM && input.LA(1)<=TOK_SELF) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_var_id_helper3090);    throw mse;
            }

            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:571:4: ( TOK_ATOM | TOK_NUMBER | simple_expression )*
            loop65:
            do {
                int alt65=4;
                switch ( input.LA(1) ) {
                case TOK_ATOM:
                    {
                    alt65=1;
                    }
                    break;
                case TOK_NUMBER:
                    {
                    alt65=2;
                    }
                    break;
                case SUBRANGE_T:
                case VALUE_T:
                case SET_LIST_EXP_T:
                case BLOCK_T:
                case CASE_LIST_EXPR_T:
                case TOK_UNARY_MINUS_T:
                case PURE_CTL_T:
                case PURE_LTL_T:
                case TOK_PLUS:
                case TOK_MINUS:
                case TOK_BOOL:
                case TOK_WORD1:
                case TOK_NEXT:
                case TOK_WAREAD:
                case TOK_WAWRITE:
                case TOK_NOT:
                case TOK_CONCATENATION:
                case TOK_TIMES:
                case TOK_DIVIDE:
                case TOK_MOD:
                case TOK_LSHIFT:
                case TOK_RSHIFT:
                case TOK_UNION:
                case TOK_SETIN:
                case TOK_EQUAL:
                case TOK_NOTEQUAL:
                case TOK_LT:
                case TOK_GT:
                case TOK_LE:
                case TOK_GE:
                case TOK_UNTIL:
                case TOK_AND:
                case TOK_OR:
                case TOK_XOR:
                case TOK_XNOR:
                case TOK_IFF:
                case TOK_IMPLIES:
                case TOK_SINCE:
                case TOK_RELEASES:
                case TOK_TRIGGERED:
                    {
                    alt65=3;
                    }
                    break;

                }

                switch (alt65) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:571:6: TOK_ATOM
            	    {
            	    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_var_id_helper3103); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:572:6: TOK_NUMBER
            	    {
            	    match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_var_id_helper3110); 

            	    }
            	    break;
            	case 3 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:573:6: simple_expression
            	    {
            	    pushFollow(FOLLOW_simple_expression_in_var_id_helper3117);
            	    simple_expression();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end var_id_helper


    // $ANTLR start command
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:581:1: command : command_case ;
    public final void command() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:581:10: ( command_case )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:581:12: command_case
            {
            pushFollow(FOLLOW_command_case_in_command3140);
            command_case();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end command


    // $ANTLR start command_case
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:585:1: command_case : ( TOK_GOTO | TOK_INIT | TOK_FAIRNESS | TOK_TRANS | TOK_CONSTRAINT | TOK_SIMPWFF | TOK_CTLWFF | TOK_LTLWFF | TOK_COMPWFF );
    public final void command_case() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:585:14: ( TOK_GOTO | TOK_INIT | TOK_FAIRNESS | TOK_TRANS | TOK_CONSTRAINT | TOK_SIMPWFF | TOK_CTLWFF | TOK_LTLWFF | TOK_COMPWFF )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:
            {
            if ( input.LA(1)==TOK_INIT||(input.LA(1)>=TOK_TRANS && input.LA(1)<=TOK_FAIRNESS)||(input.LA(1)>=TOK_GOTO && input.LA(1)<=TOK_SIMPWFF)||(input.LA(1)>=TOK_CTLWFF && input.LA(1)<=TOK_COMPWFF) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_command_case0);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end command_case


    // $ANTLR start context
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:596:1: context : TOK_ATOM ( TOK_DOT TOK_ATOM | TOK_LB simple_expression TOK_RB )* ;
    public final void context() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:596:10: ( TOK_ATOM ( TOK_DOT TOK_ATOM | TOK_LB simple_expression TOK_RB )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:596:12: TOK_ATOM ( TOK_DOT TOK_ATOM | TOK_LB simple_expression TOK_RB )*
            {
            match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_context3214); 
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:597:4: ( TOK_DOT TOK_ATOM | TOK_LB simple_expression TOK_RB )*
            loop66:
            do {
                int alt66=3;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==TOK_DOT) ) {
                    alt66=1;
                }
                else if ( (LA66_0==TOK_LB) ) {
                    alt66=2;
                }


                switch (alt66) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:597:6: TOK_DOT TOK_ATOM
            	    {
            	    match(input,TOK_DOT,FOLLOW_TOK_DOT_in_context3221); 
            	    match(input,TOK_ATOM,FOLLOW_TOK_ATOM_in_context3223); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:598:6: TOK_LB simple_expression TOK_RB
            	    {
            	    match(input,TOK_LB,FOLLOW_TOK_LB_in_context3231); 
            	    pushFollow(FOLLOW_simple_expression_in_context3233);
            	    simple_expression();
            	    _fsp--;

            	    match(input,TOK_RB,FOLLOW_TOK_RB_in_context3235); 

            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end context


    // $ANTLR start trace
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:600:1: trace : TOK_NUMBER ( TOK_DOT TOK_NUMBER )* ;
    public final void trace() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:600:8: ( TOK_NUMBER ( TOK_DOT TOK_NUMBER )* )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:600:10: TOK_NUMBER ( TOK_DOT TOK_NUMBER )*
            {
            match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_trace3248); 
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:600:21: ( TOK_DOT TOK_NUMBER )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==TOK_DOT) ) {
                    int LA67_1 = input.LA(2);

                    if ( (LA67_1==TOK_NUMBER) ) {
                        int LA67_2 = input.LA(3);

                        if ( (LA67_2==TOK_DOT) ) {
                            alt67=1;
                        }


                    }


                }


                switch (alt67) {
            	case 1 :
            	    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:600:22: TOK_DOT TOK_NUMBER
            	    {
            	    match(input,TOK_DOT,FOLLOW_TOK_DOT_in_trace3251); 
            	    match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_trace3253); 

            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end trace


    // $ANTLR start state
    // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:602:1: state : trace TOK_DOT TOK_NUMBER ;
    public final void state() throws RecognitionException {
        try {
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:602:8: ( trace TOK_DOT TOK_NUMBER )
            // /home/yaniv/jtlv_proj/jtlv_devspace/JTLV/parser/SMVVarWalker.g:602:10: trace TOK_DOT TOK_NUMBER
            {
            pushFollow(FOLLOW_trace_in_state3266);
            trace();
            _fsp--;

            match(input,TOK_DOT,FOLLOW_TOK_DOT_in_state3268); 
            match(input,TOK_NUMBER,FOLLOW_TOK_NUMBER_in_state3270); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end state


 

    public static final BitSet FOLLOW_PROGRAM_T_in_program53 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_module_list_in_program55 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_command_in_program68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_number93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_PLUS_in_integer102 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_integer104 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_MINUS_in_integer112 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_integer114 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NUMBER_WORD_in_number_word126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBRANGE_T_in_subrange144 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_integer_in_subrange148 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_integer_in_subrange152 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_FALSEEXP_in_constant176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_TRUEEXP_in_constant182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_number_in_constant188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_number_word_in_constant194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_T_in_primary_expr_helper1217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_constant_in_primary_expr_helper1219 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1221 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1223 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VALUE_T_in_primary_expr_helper1231 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_ATOM_in_primary_expr_helper1233 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1235 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1237 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VALUE_T_in_primary_expr_helper1245 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_SELF_in_primary_expr_helper1247 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1249 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1251 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_T_in_primary_expr_helper1260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basic_expr_in_primary_expr_helper1262 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1264 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1266 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_BOOL_in_primary_expr_helper1274 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basic_expr_in_primary_expr_helper1276 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1278 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1280 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_WORD1_in_primary_expr_helper1288 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basic_expr_in_primary_expr_helper1290 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1292 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1294 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NEXT_in_primary_expr_helper1302 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basic_expr_in_primary_expr_helper1304 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1306 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CASE_LIST_EXPR_T_in_primary_expr_helper1316 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_case_element_list_expr_in_primary_expr_helper1318 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1320 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1322 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_WAREAD_in_primary_expr_helper1330 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_helper1332 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_helper1334 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1336 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1338 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_WAWRITE_in_primary_expr_helper1346 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_helper1348 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_helper1350 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_helper1352 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NOP_in_primary_expr_helper1354 = new BitSet(new long[]{0x0002000118000008L});
    public static final BitSet FOLLOW_primary_expr_select_in_primary_expr_helper1356 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primary_expr_select_helper_in_primary_expr_select368 = new BitSet(new long[]{0x0002000118000002L});
    public static final BitSet FOLLOW_primary_expr_ref_in_primary_expr_select372 = new BitSet(new long[]{0x0002000118000002L});
    public static final BitSet FOLLOW_set_in_primary_expr_ref385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARRAY_INDEX_T_in_primary_expr_select_helper402 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_select_helper404 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BIT_SELECT_T_in_primary_expr_select_helper412 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_select_helper414 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_primary_expr_select_helper416 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primary_expr_helper1_in_primary_expr428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_UNARY_MINUS_T_in_primary_expr435 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_primary_expr_in_primary_expr437 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NOT_in_primary_expr445 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_primary_expr_in_primary_expr447 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_case_element_expr_in_case_element_list_expr461 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_CASE_ELEMENT_EXPR_T_in_case_element_expr473 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basic_expr_in_case_element_expr475 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_basic_expr_in_case_element_expr477 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_CONCATENATION_in_concatination_expr489 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_concatination_expr_in_concatination_expr491 = new BitSet(new long[]{0x02814E0023080000L});
    public static final BitSet FOLLOW_concatination_expr_in_concatination_expr493 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primary_expr_in_concatination_expr500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_TIMES_in_multiplicative_expr511 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicative_expr_in_multiplicative_expr513 = new BitSet(new long[]{0x0E814E0023080000L});
    public static final BitSet FOLLOW_multiplicative_expr_in_multiplicative_expr515 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_DIVIDE_in_multiplicative_expr523 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicative_expr_in_multiplicative_expr525 = new BitSet(new long[]{0x0E814E0023080000L});
    public static final BitSet FOLLOW_multiplicative_expr_in_multiplicative_expr527 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_concatination_expr_in_multiplicative_expr534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_PLUS_in_additive_expr547 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additive_expr_in_additive_expr549 = new BitSet(new long[]{0x0E814E0623080000L});
    public static final BitSet FOLLOW_additive_expr_in_additive_expr551 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_MINUS_in_additive_expr559 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additive_expr_in_additive_expr561 = new BitSet(new long[]{0x0E814E0623080000L});
    public static final BitSet FOLLOW_additive_expr_in_additive_expr563 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_MOD_in_remainder_expr581 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_remainder_expr_in_remainder_expr583 = new BitSet(new long[]{0x1E814E0623080000L});
    public static final BitSet FOLLOW_remainder_expr_in_remainder_expr585 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_additive_expr_in_remainder_expr592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_LSHIFT_in_shift_expr604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_shift_expr_in_shift_expr606 = new BitSet(new long[]{0x7E814E0623080000L});
    public static final BitSet FOLLOW_shift_expr_in_shift_expr608 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_RSHIFT_in_shift_expr616 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_shift_expr_in_shift_expr618 = new BitSet(new long[]{0x7E814E0623080000L});
    public static final BitSet FOLLOW_shift_expr_in_shift_expr620 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_remainder_expr_in_shift_expr627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basic_expr_in_set_list_expr642 = new BitSet(new long[]{0x7E814E06E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_shift_expr_in_set_expr654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subrange_in_set_expr660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SET_LIST_EXP_T_in_set_expr667 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_list_expr_in_set_expr669 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_UNION_in_union_expr682 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_union_expr_in_union_expr684 = new BitSet(new long[]{0x7E814E0623888000L,0x0000000000000002L});
    public static final BitSet FOLLOW_union_expr_in_union_expr686 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_expr_in_union_expr693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_SETIN_in_in_expr705 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_in_expr_in_in_expr707 = new BitSet(new long[]{0x7E814E0623888000L,0x0000000000000006L});
    public static final BitSet FOLLOW_in_expr_in_in_expr709 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_union_expr_in_in_expr716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_EQUAL_in_relational_expr727 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr729 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr731 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NOTEQUAL_in_relational_expr739 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr741 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr743 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_LT_in_relational_expr751 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr753 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr755 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_GT_in_relational_expr763 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr765 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr767 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_LE_in_relational_expr775 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr777 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr779 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_GE_in_relational_expr787 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr789 = new BitSet(new long[]{0x7E814E0623888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_relational_expr_in_relational_expr791 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_in_expr_in_relational_expr798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_EX_in_pure_ctl_expr810 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr812 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AX_in_pure_ctl_expr820 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr822 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_EF_in_pure_ctl_expr830 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr832 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AF_in_pure_ctl_expr840 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_EG_in_pure_ctl_expr850 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr852 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AG_in_pure_ctl_expr860 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr862 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AA_in_pure_ctl_expr870 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_basic_expr_in_pure_ctl_expr872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_TOK_UNTIL_in_pure_ctl_expr875 = new BitSet(new long[]{0x7E814E0663888000L,0x000000001F8001FEL});
    public static final BitSet FOLLOW_TOK_BUNTIL_in_pure_ctl_expr879 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr881 = new BitSet(new long[]{0x7E814E0663888000L,0x000000001F8001FEL});
    public static final BitSet FOLLOW_ctl_basic_expr_in_pure_ctl_expr884 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_EE_in_pure_ctl_expr892 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_basic_expr_in_pure_ctl_expr894 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_TOK_UNTIL_in_pure_ctl_expr897 = new BitSet(new long[]{0x7E814E0663888000L,0x000000001F8001FEL});
    public static final BitSet FOLLOW_TOK_BUNTIL_in_pure_ctl_expr901 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr903 = new BitSet(new long[]{0x7E814E0663888000L,0x000000001F8001FEL});
    public static final BitSet FOLLOW_ctl_basic_expr_in_pure_ctl_expr906 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_EBF_in_pure_ctl_expr914 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr916 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr918 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ABF_in_pure_ctl_expr926 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr928 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr930 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_EBG_in_pure_ctl_expr938 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr940 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr942 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ABG_in_pure_ctl_expr950 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_pure_ctl_expr952 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_ctl_expr_in_pure_ctl_expr954 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NOT_in_pure_ctl_expr966 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pure_ctl_expr_in_pure_ctl_expr968 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PURE_CTL_T_in_ctl_expr981 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pure_ctl_expr_in_ctl_expr983 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_relational_expr_in_ctl_expr990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_AND_in_ctl_and_expr1008 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_and_expr_in_ctl_and_expr1010 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000008001FEL});
    public static final BitSet FOLLOW_ctl_and_expr_in_ctl_and_expr1012 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ctl_expr_in_ctl_and_expr1019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_OR_in_ctl_or_expr1031 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1033 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000078001FEL});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1035 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_XOR_in_ctl_or_expr1043 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1045 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000078001FEL});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1047 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_XNOR_in_ctl_or_expr1055 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1057 = new BitSet(new long[]{0x7E814E0663888000L,0x00000000078001FEL});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_or_expr1059 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ctl_and_expr_in_ctl_or_expr1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_IFF_in_ctl_iff_expr1078 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_iff_expr_in_ctl_iff_expr1080 = new BitSet(new long[]{0x7E814E0663888000L,0x000000000F8001FEL});
    public static final BitSet FOLLOW_ctl_iff_expr_in_ctl_iff_expr1082 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ctl_or_expr_in_ctl_iff_expr1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_IMPLIES_in_ctl_implies_expr1100 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ctl_iff_expr_in_ctl_implies_expr1102 = new BitSet(new long[]{0x7E814E0663888000L,0x000000001F8001FEL});
    public static final BitSet FOLLOW_ctl_implies_expr_in_ctl_implies_expr1104 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ctl_iff_expr_in_ctl_implies_expr1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctl_implies_expr_in_ctl_basic_expr1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_OP_NEXT_in_pure_ltl_unary_expr1135 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1137 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_PREC_in_pure_ltl_unary_expr1145 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1147 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_NOTPRECNOT_in_pure_ltl_unary_expr1155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1157 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_GLOBAL_in_pure_ltl_unary_expr1165 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1167 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_HISTORICAL_in_pure_ltl_unary_expr1175 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1177 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_FUTURE_in_pure_ltl_unary_expr1185 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1187 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_OP_ONCE_in_pure_ltl_unary_expr1195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_pure_ltl_unary_expr1197 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_NOT_in_pure_ltl_unary_expr1209 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pure_ltl_unary_expr_in_pure_ltl_unary_expr1211 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PURE_LTL_T_in_ltl_unary_expr1226 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_pure_ltl_unary_expr_in_ltl_unary_expr1228 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ctl_expr_in_ltl_unary_expr1238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_UNTIL_in_ltl_binary_expr1252 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1254 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070000101FEL});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1256 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_SINCE_in_ltl_binary_expr1264 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1266 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070000101FEL});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1268 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_RELEASES_in_ltl_binary_expr1276 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1278 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070000101FEL});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1280 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_TRIGGERED_in_ltl_binary_expr1288 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1290 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070000101FEL});
    public static final BitSet FOLLOW_ltl_binary_expr_in_ltl_binary_expr1292 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ltl_unary_expr_in_ltl_binary_expr1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_AND_in_and_expr1311 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_and_expr_in_and_expr1313 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070008101FEL});
    public static final BitSet FOLLOW_and_expr_in_and_expr1315 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ltl_binary_expr_in_and_expr1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_OR_in_or_expr1334 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_or_expr_in_or_expr1336 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070078101FEL});
    public static final BitSet FOLLOW_or_expr_in_or_expr1338 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_XOR_in_or_expr1346 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_or_expr_in_or_expr1348 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070078101FEL});
    public static final BitSet FOLLOW_or_expr_in_or_expr1350 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_XNOR_in_or_expr1358 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_or_expr_in_or_expr1360 = new BitSet(new long[]{0x7E814E06E3888000L,0x00000070078101FEL});
    public static final BitSet FOLLOW_or_expr_in_or_expr1362 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_and_expr_in_or_expr1369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_IFF_in_iff_expr1381 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_iff_expr_in_iff_expr1383 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000700F8101FEL});
    public static final BitSet FOLLOW_iff_expr_in_iff_expr1385 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_or_expr_in_iff_expr1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_IMPLIES_in_implies_expr1406 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_iff_expr_in_implies_expr1408 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_implies_expr_in_implies_expr1410 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_iff_expr_in_implies_expr1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_implies_expr_in_basic_expr1428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basic_expr_in_simple_expression1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basic_expr_in_next_expression1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basic_expr_in_ctl_expression1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basic_expr_in_ltl_expression1473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_BOOLEAN_in_itype1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_WORD_in_itype1509 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_itype1513 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_subrange_in_itype1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VALUE_LIST_T_in_itype1537 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_value_list_in_itype1541 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ARRAY_in_itype1553 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_WORD_in_itype1556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_itype1558 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_WORD_in_itype1562 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_itype1564 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ARRAY_in_itype1577 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_itype1581 = new BitSet(new long[]{0x0000000000018000L,0x0000038000000000L});
    public static final BitSet FOLLOW_itype_in_itype1585 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SYNC_PROCESS_T_in_type1604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_module_type_in_type1606 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_itype_in_type1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASYNC_PROCESS_T_in_type1629 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_module_type_in_type1631 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_type_value_in_type_value_list1658 = new BitSet(new long[]{0x0000006600080002L});
    public static final BitSet FOLLOW_VALUE_T_in_type_value1675 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_complex_atom_in_type_value1677 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_integer_in_type_value1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_FALSEEXP_in_type_value1692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_TRUEEXP_in_type_value1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_ATOM_in_complex_atom1708 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_VALUE_T_in_module_type1728 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_ATOM_in_module_type1730 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VALUE_T_in_module_type1742 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_ATOM_in_module_type1744 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_simple_list_expression_in_module_type1746 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ARRAY_in_module_type1758 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_subrange_in_module_type1760 = new BitSet(new long[]{0x0000000000080000L,0x0000020000000000L});
    public static final BitSet FOLLOW_module_type_in_module_type1764 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VALUE_LIST_T_in_simple_list_expression1790 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_list_expression_helper_in_simple_list_expression1792 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_simple_expression_in_simple_list_expression_helper1818 = new BitSet(new long[]{0x7E814E06E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_module_in_module_list1845 = new BitSet(new long[]{0x0000000000000002L,0x0000300000000000L});
    public static final BitSet FOLLOW_game_definition_in_module_list1849 = new BitSet(new long[]{0x0000000000000002L,0x0000300000000000L});
    public static final BitSet FOLLOW_module_in_module_list1858 = new BitSet(new long[]{0x0000000000000002L,0x0000300000000000L});
    public static final BitSet FOLLOW_game_definition_in_module_list1866 = new BitSet(new long[]{0x0000000000000002L,0x0000300000000000L});
    public static final BitSet FOLLOW_TOK_MODULE_in_module1880 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_module_sign_in_module1885 = new BitSet(new long[]{0x0000000000000008L,0xFFD7000000000000L,0x000000000000240FL});
    public static final BitSet FOLLOW_declarations_in_module1891 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODULE_SIGN_T_in_module_sign1904 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_ATOM_in_module_sign1906 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODULE_SIGN_T_in_module_sign1918 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOK_ATOM_in_module_sign1920 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_REF_LIST_T_in_module_sign1930 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_sign_atom_list_in_module_sign1932 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ATOM_in_sign_atom_list1944 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations1960 = new BitSet(new long[]{0x0000000000000002L,0xFFD7000000000000L,0x000000000000240FL});
    public static final BitSet FOLLOW_var_in_declaration1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isa_in_declaration1976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_input_var_in_declaration1983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_declaration1989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_init_in_declaration1996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invar_in_declaration2002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trans_in_declaration2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_in_declaration2015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fairness_in_declaration2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_justice_in_declaration2028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compassion_in_declaration2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invarspec_in_declaration2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctlspec_in_declaration2047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ltlspec_in_declaration2054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pslspec_in_declaration2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compute_in_declaration2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constants_in_declaration2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_declaration2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mirror_in_declaration2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_GAME_in_game_definition2104 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_game_body_in_game_definition2106 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_game_body_element_in_game_body2118 = new BitSet(new long[]{0x0000000000000002L,0x0000C00000000000L,0x00000000000003F0L});
    public static final BitSet FOLLOW_game_body_in_game_body2121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_PLAYER_1_in_game_body_element2133 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_body_in_game_body_element2135 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_PLAYER_2_in_game_body_element2143 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_body_in_game_body_element2145 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_reachtarget_in_game_body_element2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_avoidtarget_in_game_body_element2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reachdeadlock_in_game_body_element2169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_avoiddeadlock_in_game_body_element2175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_buchigame_in_game_body_element2181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genreactivity_in_game_body_element2187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_player_body_element_in_player_body2201 = new BitSet(new long[]{0x0000000000000002L,0x01D5000000000000L});
    public static final BitSet FOLLOW_var_in_player_body_element2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_player_body_element2220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_init_in_player_body_element2227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invar_in_player_body_element2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trans_in_player_body_element2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_in_player_body_element2246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_VAR_in_var2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_VAR_in_var2271 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_decl_list_in_var2273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_var_decl_in_var_decl_list2287 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_var_decl_in_var_decl_list2290 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_VAR_DECL_T_in_var_decl2306 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_var_id_in_var_decl2308 = new BitSet(new long[]{0x0000000000078000L,0x0000038000000000L});
    public static final BitSet FOLLOW_type_in_var_decl2312 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_IVAR_in_input_var2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_IVAR_in_input_var2333 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ivar_decl_list_in_input_var2335 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ivar_decl_in_ivar_decl_list2349 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ivar_decl_in_ivar_decl_list2352 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_IVAR_DECL_T_in_ivar_decl2366 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_var_id_in_ivar_decl2368 = new BitSet(new long[]{0x0000000000018000L,0x0000038000000000L});
    public static final BitSet FOLLOW_itype_in_ivar_decl2370 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_DEFINE_in_define2386 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_define_list_in_define2388 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_define_decl_in_define_list2400 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_DEFINE_DECL_T_in_define_decl2413 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_var_id_in_define_decl2415 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_define_decl2419 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ASSIGN_in_assign2440 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assign_list_in_assign2442 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_one_assign_in_assign_list2454 = new BitSet(new long[]{0x0000000000001C02L});
    public static final BitSet FOLLOW_ASSIGN_DECL_T_in_one_assign2466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_id_in_one_assign2468 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_one_assign2470 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INIT_ASSIGN_DECL_T_in_one_assign2478 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_id_in_one_assign2480 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_one_assign2482 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEXT_ASSIGN_DECL_T_in_one_assign2490 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_id_in_one_assign2492 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_next_expression_in_one_assign2494 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_INIT_in_init2510 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_init2512 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_INVAR_in_invar2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_TRANS_in_trans2542 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_next_expression_in_trans2544 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_FAIRNESS_in_fairness2561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_JUSTICE_in_justice2575 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_justice_list_in_justice2577 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_justice_decl_in_justice_list2588 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_JUSTICE_DECL_T_in_justice_decl2600 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_justice_decl2602 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_COMPASSION_in_compassion2614 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_compassion_list_in_compassion2616 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_compassion_decl_in_compassion_list2627 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COMPASSION_DECL_T_in_compassion_decl2639 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simple_expression_in_compassion_decl2641 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_TOK_COMMA_in_compassion_decl2643 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_compassion_decl2645 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_INVARSPEC_in_invarspec2659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ctlspec0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_LTLSPEC_in_ltlspec2690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_PSLSPEC_in_pslspec2701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_CONSTANTS_in_constants2712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_atom_in_constants_expression2732 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_TOK_COMMA_in_constants_expression2735 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_complex_atom_in_constants_expression2737 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_set_in_player_num0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_PRED_in_predicate2771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_MIRROR_in_mirror2782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_REACHTARGET_in_reachtarget2797 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_reachtarget2799 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_reachtarget2801 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AVOIDTARGET_in_avoidtarget2814 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_avoidtarget2816 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_avoidtarget2818 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_REACHDEADLOCK_in_reachdeadlock2830 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_reachdeadlock2832 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_AVOIDDEADLOCK_in_avoiddeadlock2844 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_avoiddeadlock2846 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_BUCHIGAME_in_buchigame2859 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_buchigame2861 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_simple_list_expression_in_buchigame2863 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_GENREACTIVITY_in_genreactivity2876 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_player_num_in_genreactivity2878 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_simple_list_expression_in_genreactivity2880 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_TOK_IMPLIES_in_genreactivity2882 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_simple_list_expression_in_genreactivity2884 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_COMPUTE_in_compute2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_MMIN_in_compute_expression2909 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_TOK_LB_in_compute_expression2911 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_ctl_expression_in_compute_expression2913 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_TOK_COMMA_in_compute_expression2915 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_ctl_expression_in_compute_expression2917 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_TOK_RB_in_compute_expression2919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_MMAX_in_compute_expression2925 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_TOK_LB_in_compute_expression2927 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_ctl_expression_in_compute_expression2929 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_TOK_COMMA_in_compute_expression2931 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_ctl_expression_in_compute_expression2933 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_TOK_RB_in_compute_expression2935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_ISA_in_isa2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_SEMI_in_optsemi2970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REF_T_in_decl_var_id2987 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_var_id_helper_in_decl_var_id2989 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOK_ATOM_in_decl_var_id_helper3011 = new BitSet(new long[]{0x0002000700000002L});
    public static final BitSet FOLLOW_TOK_ATOM_in_decl_var_id_helper3024 = new BitSet(new long[]{0x0002000700000002L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_decl_var_id_helper3038 = new BitSet(new long[]{0x0002000700000002L});
    public static final BitSet FOLLOW_integer_in_decl_var_id_helper3052 = new BitSet(new long[]{0x0002000700000002L});
    public static final BitSet FOLLOW_REF_T_in_var_id3076 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_id_helper_in_var_id3078 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_var_id_helper3090 = new BitSet(new long[]{0x7E834E07E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_TOK_ATOM_in_var_id_helper3103 = new BitSet(new long[]{0x7E834E07E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_TOK_NUMBER_in_var_id_helper3110 = new BitSet(new long[]{0x7E834E07E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_var_id_helper3117 = new BitSet(new long[]{0x7E834E07E3888002L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_command_case_in_command3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_command_case0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOK_ATOM_in_context3214 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_TOK_DOT_in_context3221 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_TOK_ATOM_in_context3223 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_TOK_LB_in_context3231 = new BitSet(new long[]{0x7E814E06E3888000L,0x000000701F8101FEL});
    public static final BitSet FOLLOW_simple_expression_in_context3233 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_TOK_RB_in_context3235 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_trace3248 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_TOK_DOT_in_trace3251 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_trace3253 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_trace_in_state3266 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_TOK_DOT_in_state3268 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_TOK_NUMBER_in_state3270 = new BitSet(new long[]{0x0000000000000002L});

}