// $ANTLR 3.0 /home/yaniv/Desktop/SPL.g 2007-06-03 05:00:34

package edu.wis.jspl;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SPLLexer extends Lexer {
    public static final int SENDS=75;
    public static final int COMMA=17;
    public static final int MINUS=62;
    public static final int ARRAY=26;
    public static final int ID_digit=87;
    public static final int COLLECTION_MODIFIER=10;
    public static final int ASSIGN_DECL_VAL=8;
    public static final int THEN=43;
    public static final int PRODUCE=72;
    public static final int CHANNEL=23;
    public static final int PROG=4;
    public static final int Letter=86;
    public static final int AND=53;
    public static final int PROCESSES=6;
    public static final int LOCAL=13;
    public static final int REQUEST=77;
    public static final int IMPLIES=54;
    public static final int CONSUME=73;
    public static final int Q_MARK=51;
    public static final int PARALLEL=35;
    public static final int LE=59;
    public static final int PLUS=61;
    public static final int LEFT_BRACES=41;
    public static final int HE=21;
    public static final int LEFT_CURLY=81;
    public static final int NONCRITICAL=70;
    public static final int THEY=19;
    public static final int WHEN=47;
    public static final int WS=88;
    public static final int STRING=68;
    public static final int EQ=55;
    public static final int LOOP=48;
    public static final int RATIONAL=30;
    public static final int LT=57;
    public static final int OF=25;
    public static final int FOREVER=49;
    public static final int LINE_COMMENT=90;
    public static final int SINGLE_DECL=7;
    public static final int ARE=20;
    public static final int RIGHT_BRACES=42;
    public static final int AWAIT=39;
    public static final int MINUS_ONE=85;
    public static final int GE=60;
    public static final int RIGHT_BRAK=80;
    public static final int ELSE=44;
    public static final int VAR_TYPE=9;
    public static final int SEMICOLON=15;
    public static final int COLON=14;
    public static final int DIV=64;
    public static final int IDENTIFIER=16;
    public static final int UNDERSCORE=66;
    public static final int WHERE=18;
    public static final int DOTS=33;
    public static final int CRITICAL=71;
    public static final int LIST=27;
    public static final int LABEL=37;
    public static final int OUT=12;
    public static final int MOD=65;
    public static final int OR=38;
    public static final int RIGHT_BRACKET=34;
    public static final int PROC_NAME=36;
    public static final int RELEASE=78;
    public static final int LEFT_BRACKET=31;
    public static final int INT=28;
    public static final int ASYNC=24;
    public static final int MULT=63;
    public static final int SKIP=74;
    public static final int RIGHT_CURLY=82;
    public static final int BOOL=29;
    public static final int DO=46;
    public static final int WHILE=45;
    public static final int IS=22;
    public static final int DECLARATIONS=5;
    public static final int NE=56;
    public static final int GT=58;
    public static final int RECEIVES=76;
    public static final int MULTI_COMMENT=89;
    public static final int QUOTE=69;
    public static final int ESC=91;
    public static final int IN=11;
    public static final int LEFT_BRAK=79;
    public static final int BOOL_CONST=67;
    public static final int BINOR=52;
    public static final int IF=40;
    public static final int LET=50;
    public static final int EOF=-1;
    public static final int INT_CONST=32;
    public static final int Tokens=92;
    public static final int NOT=83;
    public static final int PLUS_ONE=84;
    public SPLLexer() {;} 
    public SPLLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/home/yaniv/Desktop/SPL.g"; }

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // /home/yaniv/Desktop/SPL.g:154:7: ( 'or' )
            // /home/yaniv/Desktop/SPL.g:154:7: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start LEFT_BRAK
    public final void mLEFT_BRAK() throws RecognitionException {
        try {
            int _type = LEFT_BRAK;
            // /home/yaniv/Desktop/SPL.g:155:14: ( '<<' )
            // /home/yaniv/Desktop/SPL.g:155:14: '<<'
            {
            match("<<"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFT_BRAK

    // $ANTLR start RIGHT_BRAK
    public final void mRIGHT_BRAK() throws RecognitionException {
        try {
            int _type = RIGHT_BRAK;
            // /home/yaniv/Desktop/SPL.g:156:15: ( '>>' )
            // /home/yaniv/Desktop/SPL.g:156:15: '>>'
            {
            match(">>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHT_BRAK

    // $ANTLR start LEFT_BRACKET
    public final void mLEFT_BRACKET() throws RecognitionException {
        try {
            int _type = LEFT_BRACKET;
            // /home/yaniv/Desktop/SPL.g:157:16: ( '[' )
            // /home/yaniv/Desktop/SPL.g:157:16: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFT_BRACKET

    // $ANTLR start RIGHT_BRACKET
    public final void mRIGHT_BRACKET() throws RecognitionException {
        try {
            int _type = RIGHT_BRACKET;
            // /home/yaniv/Desktop/SPL.g:158:17: ( ']' )
            // /home/yaniv/Desktop/SPL.g:158:17: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHT_BRACKET

    // $ANTLR start LEFT_BRACES
    public final void mLEFT_BRACES() throws RecognitionException {
        try {
            int _type = LEFT_BRACES;
            // /home/yaniv/Desktop/SPL.g:159:16: ( '(' )
            // /home/yaniv/Desktop/SPL.g:159:16: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFT_BRACES

    // $ANTLR start LEFT_CURLY
    public final void mLEFT_CURLY() throws RecognitionException {
        try {
            int _type = LEFT_CURLY;
            // /home/yaniv/Desktop/SPL.g:160:15: ( '{' )
            // /home/yaniv/Desktop/SPL.g:160:15: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFT_CURLY

    // $ANTLR start RIGHT_CURLY
    public final void mRIGHT_CURLY() throws RecognitionException {
        try {
            int _type = RIGHT_CURLY;
            // /home/yaniv/Desktop/SPL.g:161:16: ( '}' )
            // /home/yaniv/Desktop/SPL.g:161:16: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHT_CURLY

    // $ANTLR start RIGHT_BRACES
    public final void mRIGHT_BRACES() throws RecognitionException {
        try {
            int _type = RIGHT_BRACES;
            // /home/yaniv/Desktop/SPL.g:162:16: ( ')' )
            // /home/yaniv/Desktop/SPL.g:162:16: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHT_BRACES

    // $ANTLR start SEMICOLON
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            // /home/yaniv/Desktop/SPL.g:163:14: ( ';' )
            // /home/yaniv/Desktop/SPL.g:163:14: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMICOLON

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // /home/yaniv/Desktop/SPL.g:164:10: ( ':' )
            // /home/yaniv/Desktop/SPL.g:164:10: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // /home/yaniv/Desktop/SPL.g:165:10: ( ',' )
            // /home/yaniv/Desktop/SPL.g:165:10: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // /home/yaniv/Desktop/SPL.g:168:8: ( 'int' )
            // /home/yaniv/Desktop/SPL.g:168:8: 'int'
            {
            match("int"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start BOOL
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            // /home/yaniv/Desktop/SPL.g:169:9: ( 'bool' )
            // /home/yaniv/Desktop/SPL.g:169:9: 'bool'
            {
            match("bool"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOOL

    // $ANTLR start RATIONAL
    public final void mRATIONAL() throws RecognitionException {
        try {
            int _type = RATIONAL;
            // /home/yaniv/Desktop/SPL.g:170:13: ( 'rational' )
            // /home/yaniv/Desktop/SPL.g:170:13: 'rational'
            {
            match("rational"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RATIONAL

    // $ANTLR start LIST
    public final void mLIST() throws RecognitionException {
        try {
            int _type = LIST;
            // /home/yaniv/Desktop/SPL.g:171:9: ( 'list' )
            // /home/yaniv/Desktop/SPL.g:171:9: 'list'
            {
            match("list"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LIST

    // $ANTLR start ARRAY
    public final void mARRAY() throws RecognitionException {
        try {
            int _type = ARRAY;
            // /home/yaniv/Desktop/SPL.g:172:10: ( 'array' )
            // /home/yaniv/Desktop/SPL.g:172:10: 'array'
            {
            match("array"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ARRAY

    // $ANTLR start CHANNEL
    public final void mCHANNEL() throws RecognitionException {
        try {
            int _type = CHANNEL;
            // /home/yaniv/Desktop/SPL.g:173:12: ( 'channel' )
            // /home/yaniv/Desktop/SPL.g:173:12: 'channel'
            {
            match("channel"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CHANNEL

    // $ANTLR start DOTS
    public final void mDOTS() throws RecognitionException {
        try {
            int _type = DOTS;
            // /home/yaniv/Desktop/SPL.g:174:9: ( '..' )
            // /home/yaniv/Desktop/SPL.g:174:9: '..'
            {
            match(".."); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOTS

    // $ANTLR start OF
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            // /home/yaniv/Desktop/SPL.g:175:7: ( 'of' )
            // /home/yaniv/Desktop/SPL.g:175:7: 'of'
            {
            match("of"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OF

    // $ANTLR start LOCAL
    public final void mLOCAL() throws RecognitionException {
        try {
            int _type = LOCAL;
            // /home/yaniv/Desktop/SPL.g:176:10: ( 'local' )
            // /home/yaniv/Desktop/SPL.g:176:10: 'local'
            {
            match("local"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOCAL

    // $ANTLR start IN
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            // /home/yaniv/Desktop/SPL.g:177:7: ( 'in' )
            // /home/yaniv/Desktop/SPL.g:177:7: 'in'
            {
            match("in"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IN

    // $ANTLR start OUT
    public final void mOUT() throws RecognitionException {
        try {
            int _type = OUT;
            // /home/yaniv/Desktop/SPL.g:178:8: ( 'out' )
            // /home/yaniv/Desktop/SPL.g:178:8: 'out'
            {
            match("out"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OUT

    // $ANTLR start LET
    public final void mLET() throws RecognitionException {
        try {
            int _type = LET;
            // /home/yaniv/Desktop/SPL.g:179:8: ( ':=' )
            // /home/yaniv/Desktop/SPL.g:179:8: ':='
            {
            match(":="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LET

    // $ANTLR start WHILE
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            // /home/yaniv/Desktop/SPL.g:182:10: ( 'while' )
            // /home/yaniv/Desktop/SPL.g:182:10: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHILE

    // $ANTLR start DO
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            // /home/yaniv/Desktop/SPL.g:183:7: ( 'do' )
            // /home/yaniv/Desktop/SPL.g:183:7: 'do'
            {
            match("do"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DO

    // $ANTLR start LOOP
    public final void mLOOP() throws RecognitionException {
        try {
            int _type = LOOP;
            // /home/yaniv/Desktop/SPL.g:184:9: ( 'loop' )
            // /home/yaniv/Desktop/SPL.g:184:9: 'loop'
            {
            match("loop"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOOP

    // $ANTLR start FOREVER
    public final void mFOREVER() throws RecognitionException {
        try {
            int _type = FOREVER;
            // /home/yaniv/Desktop/SPL.g:185:12: ( 'forever' )
            // /home/yaniv/Desktop/SPL.g:185:12: 'forever'
            {
            match("forever"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FOREVER

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // /home/yaniv/Desktop/SPL.g:186:7: ( 'if' )
            // /home/yaniv/Desktop/SPL.g:186:7: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start THEN
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            // /home/yaniv/Desktop/SPL.g:187:9: ( 'then' )
            // /home/yaniv/Desktop/SPL.g:187:9: 'then'
            {
            match("then"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end THEN

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // /home/yaniv/Desktop/SPL.g:188:9: ( 'else' )
            // /home/yaniv/Desktop/SPL.g:188:9: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start AWAIT
    public final void mAWAIT() throws RecognitionException {
        try {
            int _type = AWAIT;
            // /home/yaniv/Desktop/SPL.g:189:10: ( 'await' )
            // /home/yaniv/Desktop/SPL.g:189:10: 'await'
            {
            match("await"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AWAIT

    // $ANTLR start WHEN
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            // /home/yaniv/Desktop/SPL.g:190:9: ( 'when' )
            // /home/yaniv/Desktop/SPL.g:190:9: 'when'
            {
            match("when"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHEN

    // $ANTLR start SKIP
    public final void mSKIP() throws RecognitionException {
        try {
            int _type = SKIP;
            // /home/yaniv/Desktop/SPL.g:191:9: ( 'skip' )
            // /home/yaniv/Desktop/SPL.g:191:9: 'skip'
            {
            match("skip"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SKIP

    // $ANTLR start PARALLEL
    public final void mPARALLEL() throws RecognitionException {
        try {
            int _type = PARALLEL;
            // /home/yaniv/Desktop/SPL.g:192:13: ( '||' )
            // /home/yaniv/Desktop/SPL.g:192:13: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PARALLEL

    // $ANTLR start ASYNC
    public final void mASYNC() throws RecognitionException {
        try {
            int _type = ASYNC;
            // /home/yaniv/Desktop/SPL.g:193:10: ( LEFT_BRACKET '..n' RIGHT_BRACKET )
            // /home/yaniv/Desktop/SPL.g:193:10: LEFT_BRACKET '..n' RIGHT_BRACKET
            {
            mLEFT_BRACKET(); 
            match("..n"); 

            mRIGHT_BRACKET(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASYNC

    // $ANTLR start NONCRITICAL
    public final void mNONCRITICAL() throws RecognitionException {
        try {
            int _type = NONCRITICAL;
            // /home/yaniv/Desktop/SPL.g:196:16: ( 'noncritical' )
            // /home/yaniv/Desktop/SPL.g:196:16: 'noncritical'
            {
            match("noncritical"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NONCRITICAL

    // $ANTLR start CRITICAL
    public final void mCRITICAL() throws RecognitionException {
        try {
            int _type = CRITICAL;
            // /home/yaniv/Desktop/SPL.g:197:13: ( 'critical' )
            // /home/yaniv/Desktop/SPL.g:197:13: 'critical'
            {
            match("critical"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CRITICAL

    // $ANTLR start RELEASE
    public final void mRELEASE() throws RecognitionException {
        try {
            int _type = RELEASE;
            // /home/yaniv/Desktop/SPL.g:198:12: ( 'release' )
            // /home/yaniv/Desktop/SPL.g:198:12: 'release'
            {
            match("release"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RELEASE

    // $ANTLR start REQUEST
    public final void mREQUEST() throws RecognitionException {
        try {
            int _type = REQUEST;
            // /home/yaniv/Desktop/SPL.g:199:12: ( 'request' )
            // /home/yaniv/Desktop/SPL.g:199:12: 'request'
            {
            match("request"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end REQUEST

    // $ANTLR start CONSUME
    public final void mCONSUME() throws RecognitionException {
        try {
            int _type = CONSUME;
            // /home/yaniv/Desktop/SPL.g:200:12: ( 'consume' )
            // /home/yaniv/Desktop/SPL.g:200:12: 'consume'
            {
            match("consume"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONSUME

    // $ANTLR start SENDS
    public final void mSENDS() throws RecognitionException {
        try {
            int _type = SENDS;
            // /home/yaniv/Desktop/SPL.g:201:10: ( '<<==' )
            // /home/yaniv/Desktop/SPL.g:201:10: '<<=='
            {
            match("<<=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SENDS

    // $ANTLR start RECEIVES
    public final void mRECEIVES() throws RecognitionException {
        try {
            int _type = RECEIVES;
            // /home/yaniv/Desktop/SPL.g:202:13: ( '==>>' )
            // /home/yaniv/Desktop/SPL.g:202:13: '==>>'
            {
            match("==>>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RECEIVES

    // $ANTLR start WHERE
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            // /home/yaniv/Desktop/SPL.g:203:10: ( 'where' )
            // /home/yaniv/Desktop/SPL.g:203:10: 'where'
            {
            match("where"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHERE

    // $ANTLR start THEY
    public final void mTHEY() throws RecognitionException {
        try {
            int _type = THEY;
            // /home/yaniv/Desktop/SPL.g:204:9: ( 'they' )
            // /home/yaniv/Desktop/SPL.g:204:9: 'they'
            {
            match("they"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end THEY

    // $ANTLR start ARE
    public final void mARE() throws RecognitionException {
        try {
            int _type = ARE;
            // /home/yaniv/Desktop/SPL.g:205:8: ( 'are' )
            // /home/yaniv/Desktop/SPL.g:205:8: 'are'
            {
            match("are"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ARE

    // $ANTLR start HE
    public final void mHE() throws RecognitionException {
        try {
            int _type = HE;
            // /home/yaniv/Desktop/SPL.g:206:7: ( 'he' )
            // /home/yaniv/Desktop/SPL.g:206:7: 'he'
            {
            match("he"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end HE

    // $ANTLR start IS
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            // /home/yaniv/Desktop/SPL.g:207:7: ( 'is' )
            // /home/yaniv/Desktop/SPL.g:207:7: 'is'
            {
            match("is"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IS

    // $ANTLR start UNDERSCORE
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            int _type = UNDERSCORE;
            // /home/yaniv/Desktop/SPL.g:208:15: ( '_' )
            // /home/yaniv/Desktop/SPL.g:208:15: '_'
            {
            match('_'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end UNDERSCORE

    // $ANTLR start PRODUCE
    public final void mPRODUCE() throws RecognitionException {
        try {
            int _type = PRODUCE;
            // /home/yaniv/Desktop/SPL.g:209:12: ( 'produce' )
            // /home/yaniv/Desktop/SPL.g:209:12: 'produce'
            {
            match("produce"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRODUCE

    // $ANTLR start Q_MARK
    public final void mQ_MARK() throws RecognitionException {
        try {
            int _type = Q_MARK;
            // /home/yaniv/Desktop/SPL.g:212:11: ( '?' )
            // /home/yaniv/Desktop/SPL.g:212:11: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Q_MARK

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // /home/yaniv/Desktop/SPL.g:213:20: ( '&' )
            // /home/yaniv/Desktop/SPL.g:213:20: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start BINOR
    public final void mBINOR() throws RecognitionException {
        try {
            int _type = BINOR;
            // /home/yaniv/Desktop/SPL.g:214:22: ( '|' )
            // /home/yaniv/Desktop/SPL.g:214:22: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BINOR

    // $ANTLR start IMPLIES
    public final void mIMPLIES() throws RecognitionException {
        try {
            int _type = IMPLIES;
            // /home/yaniv/Desktop/SPL.g:215:24: ( '->' )
            // /home/yaniv/Desktop/SPL.g:215:24: '->'
            {
            match("->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPLIES

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // /home/yaniv/Desktop/SPL.g:216:7: ( '<' )
            // /home/yaniv/Desktop/SPL.g:216:7: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start GT
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            // /home/yaniv/Desktop/SPL.g:217:7: ( '>' )
            // /home/yaniv/Desktop/SPL.g:217:7: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GT

    // $ANTLR start EQ
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            // /home/yaniv/Desktop/SPL.g:218:7: ( '=' )
            // /home/yaniv/Desktop/SPL.g:218:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQ

    // $ANTLR start NE
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            // /home/yaniv/Desktop/SPL.g:219:7: ( '!=' )
            // /home/yaniv/Desktop/SPL.g:219:7: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NE

    // $ANTLR start LE
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            // /home/yaniv/Desktop/SPL.g:220:7: ( '<=' )
            // /home/yaniv/Desktop/SPL.g:220:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LE

    // $ANTLR start GE
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            // /home/yaniv/Desktop/SPL.g:221:7: ( '>=' )
            // /home/yaniv/Desktop/SPL.g:221:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GE

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // /home/yaniv/Desktop/SPL.g:222:8: ( '~' | '!' )
            // /home/yaniv/Desktop/SPL.g:
            {
            if ( input.LA(1)=='!'||input.LA(1)=='~' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // /home/yaniv/Desktop/SPL.g:225:9: ( '+' )
            // /home/yaniv/Desktop/SPL.g:225:9: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // /home/yaniv/Desktop/SPL.g:226:10: ( '-' )
            // /home/yaniv/Desktop/SPL.g:226:10: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start MULT
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            // /home/yaniv/Desktop/SPL.g:227:9: ( '*' )
            // /home/yaniv/Desktop/SPL.g:227:9: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MULT

    // $ANTLR start DIV
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            // /home/yaniv/Desktop/SPL.g:228:8: ( '/' | 'div' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='/') ) {
                alt1=1;
            }
            else if ( (LA1_0=='d') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("228:1: DIV : ( '/' | 'div' );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:228:8: '/'
                    {
                    match('/'); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:228:14: 'div'
                    {
                    match("div"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV

    // $ANTLR start MOD
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            // /home/yaniv/Desktop/SPL.g:229:8: ( '%' )
            // /home/yaniv/Desktop/SPL.g:229:8: '%'
            {
            match('%'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MOD

    // $ANTLR start PLUS_ONE
    public final void mPLUS_ONE() throws RecognitionException {
        try {
            int _type = PLUS_ONE;
            // /home/yaniv/Desktop/SPL.g:230:13: ( '++' )
            // /home/yaniv/Desktop/SPL.g:230:13: '++'
            {
            match("++"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS_ONE

    // $ANTLR start MINUS_ONE
    public final void mMINUS_ONE() throws RecognitionException {
        try {
            int _type = MINUS_ONE;
            // /home/yaniv/Desktop/SPL.g:231:14: ( '--' )
            // /home/yaniv/Desktop/SPL.g:231:14: '--'
            {
            match("--"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS_ONE

    // $ANTLR start LABEL
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            // /home/yaniv/Desktop/SPL.g:234:10: ( ( 'a' .. 'z' ) '_' ( '0' .. '9' )+ ':' )
            // /home/yaniv/Desktop/SPL.g:234:10: ( 'a' .. 'z' ) '_' ( '0' .. '9' )+ ':'
            {
            // /home/yaniv/Desktop/SPL.g:234:10: ( 'a' .. 'z' )
            // /home/yaniv/Desktop/SPL.g:234:11: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            match('_'); 
            // /home/yaniv/Desktop/SPL.g:234:25: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/yaniv/Desktop/SPL.g:234:26: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LABEL

    // $ANTLR start INT_CONST
    public final void mINT_CONST() throws RecognitionException {
        try {
            int _type = INT_CONST;
            // /home/yaniv/Desktop/SPL.g:235:14: ( '0' | ( ( '1' .. '9' ) ( '0' .. '9' )* ) | ( '-' ( '1' .. '9' ) ( '0' .. '9' )* ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case '0':
                {
                alt5=1;
                }
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt5=2;
                }
                break;
            case '-':
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("235:1: INT_CONST : ( '0' | ( ( '1' .. '9' ) ( '0' .. '9' )* ) | ( '-' ( '1' .. '9' ) ( '0' .. '9' )* ) );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:235:14: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:235:20: ( ( '1' .. '9' ) ( '0' .. '9' )* )
                    {
                    // /home/yaniv/Desktop/SPL.g:235:20: ( ( '1' .. '9' ) ( '0' .. '9' )* )
                    // /home/yaniv/Desktop/SPL.g:235:21: ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    // /home/yaniv/Desktop/SPL.g:235:21: ( '1' .. '9' )
                    // /home/yaniv/Desktop/SPL.g:235:22: '1' .. '9'
                    {
                    matchRange('1','9'); 

                    }

                    // /home/yaniv/Desktop/SPL.g:235:32: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:235:33: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 3 :
                    // /home/yaniv/Desktop/SPL.g:235:47: ( '-' ( '1' .. '9' ) ( '0' .. '9' )* )
                    {
                    // /home/yaniv/Desktop/SPL.g:235:47: ( '-' ( '1' .. '9' ) ( '0' .. '9' )* )
                    // /home/yaniv/Desktop/SPL.g:235:48: '-' ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    match('-'); 
                    // /home/yaniv/Desktop/SPL.g:235:52: ( '1' .. '9' )
                    // /home/yaniv/Desktop/SPL.g:235:53: '1' .. '9'
                    {
                    matchRange('1','9'); 

                    }

                    // /home/yaniv/Desktop/SPL.g:235:63: ( '0' .. '9' )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:235:64: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT_CONST

    // $ANTLR start BOOL_CONST
    public final void mBOOL_CONST() throws RecognitionException {
        try {
            int _type = BOOL_CONST;
            // /home/yaniv/Desktop/SPL.g:239:15: ( 'T' | 'TRUE' | 'F' | 'FALSE' )
            int alt6=4;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='T') ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='R') ) {
                    alt6=2;
                }
                else {
                    alt6=1;}
            }
            else if ( (LA6_0=='F') ) {
                int LA6_2 = input.LA(2);

                if ( (LA6_2=='A') ) {
                    alt6=4;
                }
                else {
                    alt6=3;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("239:1: BOOL_CONST : ( 'T' | 'TRUE' | 'F' | 'FALSE' );", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:239:15: 'T'
                    {
                    match('T'); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:239:22: 'TRUE'
                    {
                    match("TRUE"); 


                    }
                    break;
                case 3 :
                    // /home/yaniv/Desktop/SPL.g:239:31: 'F'
                    {
                    match('F'); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/Desktop/SPL.g:239:37: 'FALSE'
                    {
                    match("FALSE"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOOL_CONST

    // $ANTLR start PROC_NAME
    public final void mPROC_NAME() throws RecognitionException {
        try {
            int _type = PROC_NAME;
            // /home/yaniv/Desktop/SPL.g:240:14: ( 'P' ( '1' .. '9' ) ( '0' .. '9' )* '::' )
            // /home/yaniv/Desktop/SPL.g:240:14: 'P' ( '1' .. '9' ) ( '0' .. '9' )* '::'
            {
            match('P'); 
            // /home/yaniv/Desktop/SPL.g:240:18: ( '1' .. '9' )
            // /home/yaniv/Desktop/SPL.g:240:19: '1' .. '9'
            {
            matchRange('1','9'); 

            }

            // /home/yaniv/Desktop/SPL.g:240:29: ( '0' .. '9' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/yaniv/Desktop/SPL.g:240:30: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("::"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PROC_NAME

    // $ANTLR start IDENTIFIER
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            // /home/yaniv/Desktop/SPL.g:241:15: ( Letter ( Letter | ID_digit )* )
            // /home/yaniv/Desktop/SPL.g:241:15: Letter ( Letter | ID_digit )*
            {
            mLetter(); 
            // /home/yaniv/Desktop/SPL.g:241:22: ( Letter | ID_digit )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }
                else if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/yaniv/Desktop/SPL.g:241:23: Letter
            	    {
            	    mLetter(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/Desktop/SPL.g:241:32: ID_digit
            	    {
            	    mID_digit(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENTIFIER

    // $ANTLR start Letter
    public final void mLetter() throws RecognitionException {
        try {
            // /home/yaniv/Desktop/SPL.g:242:19: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                {
                alt9=1;
                }
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                {
                alt9=2;
                }
                break;
            case '_':
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("242:10: fragment Letter : ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:242:19: ( 'a' .. 'z' )
                    {
                    // /home/yaniv/Desktop/SPL.g:242:19: ( 'a' .. 'z' )
                    // /home/yaniv/Desktop/SPL.g:242:20: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:242:32: ( 'A' .. 'Z' )
                    {
                    // /home/yaniv/Desktop/SPL.g:242:32: ( 'A' .. 'Z' )
                    // /home/yaniv/Desktop/SPL.g:242:33: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/yaniv/Desktop/SPL.g:242:45: '_'
                    {
                    match('_'); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end Letter

    // $ANTLR start ID_digit
    public final void mID_digit() throws RecognitionException {
        try {
            // /home/yaniv/Desktop/SPL.g:243:21: ( ( '0' .. '9' ) )
            // /home/yaniv/Desktop/SPL.g:243:21: ( '0' .. '9' )
            {
            // /home/yaniv/Desktop/SPL.g:243:21: ( '0' .. '9' )
            // /home/yaniv/Desktop/SPL.g:243:22: '0' .. '9'
            {
            matchRange('0','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end ID_digit

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // /home/yaniv/Desktop/SPL.g:246:7: ( ( ' ' | '\\t' | '\\n' | '\\r' ( '\\n' )? ) )
            // /home/yaniv/Desktop/SPL.g:246:7: ( ' ' | '\\t' | '\\n' | '\\r' ( '\\n' )? )
            {
            // /home/yaniv/Desktop/SPL.g:246:7: ( ' ' | '\\t' | '\\n' | '\\r' ( '\\n' )? )
            int alt11=4;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt11=1;
                }
                break;
            case '\t':
                {
                alt11=2;
                }
                break;
            case '\n':
                {
                alt11=3;
                }
                break;
            case '\r':
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("246:7: ( ' ' | '\\t' | '\\n' | '\\r' ( '\\n' )? )", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:246:9: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:247:5: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // /home/yaniv/Desktop/SPL.g:248:5: '\\n'
                    {
                    match('\n'); 

                    }
                    break;
                case 4 :
                    // /home/yaniv/Desktop/SPL.g:249:5: '\\r' ( '\\n' )?
                    {
                    match('\r'); 
                    // /home/yaniv/Desktop/SPL.g:249:10: ( '\\n' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\n') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // /home/yaniv/Desktop/SPL.g:249:11: '\\n'
                            {
                            match('\n'); 

                            }
                            break;

                    }


                    }
                    break;

            }

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start MULTI_COMMENT
    public final void mMULTI_COMMENT() throws RecognitionException {
        try {
            int _type = MULTI_COMMENT;
            // /home/yaniv/Desktop/SPL.g:252:17: ( ( '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/' ) | ( '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/' ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='/') ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1=='-') ) {
                    alt14=1;
                }
                else if ( (LA14_1=='*') ) {
                    alt14=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("252:1: MULTI_COMMENT : ( ( '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/' ) | ( '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/' ) );", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("252:1: MULTI_COMMENT : ( ( '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/' ) | ( '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/' ) );", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:252:17: ( '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/' )
                    {
                    // /home/yaniv/Desktop/SPL.g:252:17: ( '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/' )
                    // /home/yaniv/Desktop/SPL.g:252:18: '/-' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '-/'
                    {
                    match("/-"); 

                    // /home/yaniv/Desktop/SPL.g:252:23: ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )*
                    loop12:
                    do {
                        int alt12=5;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='-') ) {
                            int LA12_1 = input.LA(2);

                            if ( (LA12_1=='/') ) {
                                alt12=5;
                            }
                            else if ( ((LA12_1>='\u0000' && LA12_1<='.')||(LA12_1>='0' && LA12_1<='\uFFFE')) ) {
                                alt12=4;
                            }


                        }
                        else if ( (LA12_0=='\r') ) {
                            int LA12_2 = input.LA(2);

                            if ( (LA12_2=='\n') ) {
                                alt12=1;
                            }
                            else if ( ((LA12_2>='\u0000' && LA12_2<='\t')||(LA12_2>='\u000B' && LA12_2<='\uFFFE')) ) {
                                alt12=2;
                            }


                        }
                        else if ( (LA12_0=='\n') ) {
                            alt12=3;
                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<=',')||(LA12_0>='.' && LA12_0<='\uFFFE')) ) {
                            alt12=4;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:254:7: '\\r' '\\n'
                    	    {
                    	    match('\r'); 
                    	    match('\n'); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/yaniv/Desktop/SPL.g:255:7: '\\r'
                    	    {
                    	    match('\r'); 

                    	    }
                    	    break;
                    	case 3 :
                    	    // /home/yaniv/Desktop/SPL.g:256:7: '\\n'
                    	    {
                    	    match('\n'); 

                    	    }
                    	    break;
                    	case 4 :
                    	    // /home/yaniv/Desktop/SPL.g:257:7: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    match("-/"); 

                    channel=HIDDEN;

                    }


                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:262:3: ( '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/' )
                    {
                    // /home/yaniv/Desktop/SPL.g:262:3: ( '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/' )
                    // /home/yaniv/Desktop/SPL.g:262:4: '/*' ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )* '*/'
                    {
                    match("/*"); 

                    // /home/yaniv/Desktop/SPL.g:262:9: ( options {greedy=false; } : '\\r' '\\n' | '\\r' | '\\n' | ~ ( '\\n' | '\\r' ) )*
                    loop13:
                    do {
                        int alt13=5;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='*') ) {
                            int LA13_1 = input.LA(2);

                            if ( (LA13_1=='/') ) {
                                alt13=5;
                            }
                            else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFE')) ) {
                                alt13=4;
                            }


                        }
                        else if ( (LA13_0=='\r') ) {
                            int LA13_2 = input.LA(2);

                            if ( (LA13_2=='\n') ) {
                                alt13=1;
                            }
                            else if ( ((LA13_2>='\u0000' && LA13_2<='\t')||(LA13_2>='\u000B' && LA13_2<='\uFFFE')) ) {
                                alt13=2;
                            }


                        }
                        else if ( (LA13_0=='\n') ) {
                            alt13=3;
                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFE')) ) {
                            alt13=4;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:264:7: '\\r' '\\n'
                    	    {
                    	    match('\r'); 
                    	    match('\n'); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/yaniv/Desktop/SPL.g:265:7: '\\r'
                    	    {
                    	    match('\r'); 

                    	    }
                    	    break;
                    	case 3 :
                    	    // /home/yaniv/Desktop/SPL.g:266:7: '\\n'
                    	    {
                    	    match('\n'); 

                    	    }
                    	    break;
                    	case 4 :
                    	    // /home/yaniv/Desktop/SPL.g:267:7: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    match("*/"); 

                    channel=HIDDEN;

                    }


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MULTI_COMMENT

    // $ANTLR start LINE_COMMENT
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            // /home/yaniv/Desktop/SPL.g:271:16: ( ( '--' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? ) | ( '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='-') ) {
                alt23=1;
            }
            else if ( (LA23_0=='/') ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("271:1: LINE_COMMENT : ( ( '--' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? ) | ( '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? ) );", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/yaniv/Desktop/SPL.g:271:16: ( '--' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? )
                    {
                    // /home/yaniv/Desktop/SPL.g:271:16: ( '--' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? )
                    // /home/yaniv/Desktop/SPL.g:271:17: '--' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )?
                    {
                    match("--"); 

                    // /home/yaniv/Desktop/SPL.g:271:22: (~ ( '\\n' | '\\r' ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFE')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:271:23: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    // /home/yaniv/Desktop/SPL.g:271:38: ( ( '\\n' | '\\r' ( '\\n' )? ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='\n'||LA18_0=='\r') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /home/yaniv/Desktop/SPL.g:271:39: ( '\\n' | '\\r' ( '\\n' )? )
                            {
                            // /home/yaniv/Desktop/SPL.g:271:39: ( '\\n' | '\\r' ( '\\n' )? )
                            int alt17=2;
                            int LA17_0 = input.LA(1);

                            if ( (LA17_0=='\n') ) {
                                alt17=1;
                            }
                            else if ( (LA17_0=='\r') ) {
                                alt17=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("271:39: ( '\\n' | '\\r' ( '\\n' )? )", 17, 0, input);

                                throw nvae;
                            }
                            switch (alt17) {
                                case 1 :
                                    // /home/yaniv/Desktop/SPL.g:271:40: '\\n'
                                    {
                                    match('\n'); 

                                    }
                                    break;
                                case 2 :
                                    // /home/yaniv/Desktop/SPL.g:271:45: '\\r' ( '\\n' )?
                                    {
                                    match('\r'); 
                                    // /home/yaniv/Desktop/SPL.g:271:49: ( '\\n' )?
                                    int alt16=2;
                                    int LA16_0 = input.LA(1);

                                    if ( (LA16_0=='\n') ) {
                                        alt16=1;
                                    }
                                    switch (alt16) {
                                        case 1 :
                                            // /home/yaniv/Desktop/SPL.g:271:50: '\\n'
                                            {
                                            match('\n'); 

                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    channel=HIDDEN;

                    }


                    }
                    break;
                case 2 :
                    // /home/yaniv/Desktop/SPL.g:273:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? )
                    {
                    // /home/yaniv/Desktop/SPL.g:273:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )? )
                    // /home/yaniv/Desktop/SPL.g:273:4: '//' (~ ( '\\n' | '\\r' ) )* ( ( '\\n' | '\\r' ( '\\n' )? ) )?
                    {
                    match("//"); 

                    // /home/yaniv/Desktop/SPL.g:273:9: (~ ( '\\n' | '\\r' ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\uFFFE')) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // /home/yaniv/Desktop/SPL.g:273:10: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    // /home/yaniv/Desktop/SPL.g:273:25: ( ( '\\n' | '\\r' ( '\\n' )? ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0=='\n'||LA22_0=='\r') ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /home/yaniv/Desktop/SPL.g:273:26: ( '\\n' | '\\r' ( '\\n' )? )
                            {
                            // /home/yaniv/Desktop/SPL.g:273:26: ( '\\n' | '\\r' ( '\\n' )? )
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0=='\n') ) {
                                alt21=1;
                            }
                            else if ( (LA21_0=='\r') ) {
                                alt21=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("273:26: ( '\\n' | '\\r' ( '\\n' )? )", 21, 0, input);

                                throw nvae;
                            }
                            switch (alt21) {
                                case 1 :
                                    // /home/yaniv/Desktop/SPL.g:273:27: '\\n'
                                    {
                                    match('\n'); 

                                    }
                                    break;
                                case 2 :
                                    // /home/yaniv/Desktop/SPL.g:273:32: '\\r' ( '\\n' )?
                                    {
                                    match('\r'); 
                                    // /home/yaniv/Desktop/SPL.g:273:36: ( '\\n' )?
                                    int alt20=2;
                                    int LA20_0 = input.LA(1);

                                    if ( (LA20_0=='\n') ) {
                                        alt20=1;
                                    }
                                    switch (alt20) {
                                        case 1 :
                                            // /home/yaniv/Desktop/SPL.g:273:37: '\\n'
                                            {
                                            match('\n'); 

                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    channel=HIDDEN;

                    }


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LINE_COMMENT

    // $ANTLR start QUOTE
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            // /home/yaniv/Desktop/SPL.g:274:10: ( '\\'' ( ESC | ~ '\\'' )* '\\'' )
            // /home/yaniv/Desktop/SPL.g:274:10: '\\'' ( ESC | ~ '\\'' )* '\\''
            {
            match('\''); 
            // /home/yaniv/Desktop/SPL.g:274:15: ( ESC | ~ '\\'' )*
            loop24:
            do {
                int alt24=3;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='\\') ) {
                    int LA24_2 = input.LA(2);

                    if ( (LA24_2=='b'||LA24_2=='n'||LA24_2=='r'||LA24_2=='t') ) {
                        alt24=1;
                    }
                    else if ( ((LA24_2>='\u0000' && LA24_2<='a')||(LA24_2>='c' && LA24_2<='m')||(LA24_2>='o' && LA24_2<='q')||LA24_2=='s'||(LA24_2>='u' && LA24_2<='\uFFFE')) ) {
                        alt24=2;
                    }


                }
                else if ( ((LA24_0>='\u0000' && LA24_0<='&')||(LA24_0>='(' && LA24_0<='[')||(LA24_0>=']' && LA24_0<='\uFFFE')) ) {
                    alt24=2;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/yaniv/Desktop/SPL.g:274:16: ESC
            	    {
            	    mESC(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/Desktop/SPL.g:274:20: ~ '\\''
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match('\''); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUOTE

    // $ANTLR start STRING
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            // /home/yaniv/Desktop/SPL.g:275:11: ( '\\\"' ( ESC | ~ '\\\"' )* '\\\"' )
            // /home/yaniv/Desktop/SPL.g:275:11: '\\\"' ( ESC | ~ '\\\"' )* '\\\"'
            {
            match('\"'); 
            // /home/yaniv/Desktop/SPL.g:275:16: ( ESC | ~ '\\\"' )*
            loop25:
            do {
                int alt25=3;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='\\') ) {
                    int LA25_2 = input.LA(2);

                    if ( (LA25_2=='b'||LA25_2=='n'||LA25_2=='r'||LA25_2=='t') ) {
                        alt25=1;
                    }
                    else if ( ((LA25_2>='\u0000' && LA25_2<='a')||(LA25_2>='c' && LA25_2<='m')||(LA25_2>='o' && LA25_2<='q')||LA25_2=='s'||(LA25_2>='u' && LA25_2<='\uFFFE')) ) {
                        alt25=2;
                    }


                }
                else if ( ((LA25_0>='\u0000' && LA25_0<='!')||(LA25_0>='#' && LA25_0<='[')||(LA25_0>=']' && LA25_0<='\uFFFE')) ) {
                    alt25=2;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/yaniv/Desktop/SPL.g:275:17: ESC
            	    {
            	    mESC(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/yaniv/Desktop/SPL.g:275:21: ~ '\\\"'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING

    // $ANTLR start ESC
    public final void mESC() throws RecognitionException {
        try {
            // /home/yaniv/Desktop/SPL.g:276:17: ( '\\\\' ( 't' | 'n' | 'r' | 'b' ) )
            // /home/yaniv/Desktop/SPL.g:276:17: '\\\\' ( 't' | 'n' | 'r' | 'b' )
            {
            match('\\'); 
            if ( input.LA(1)=='b'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end ESC

    public void mTokens() throws RecognitionException {
        // /home/yaniv/Desktop/SPL.g:1:10: ( OR | LEFT_BRAK | RIGHT_BRAK | LEFT_BRACKET | RIGHT_BRACKET | LEFT_BRACES | LEFT_CURLY | RIGHT_CURLY | RIGHT_BRACES | SEMICOLON | COLON | COMMA | INT | BOOL | RATIONAL | LIST | ARRAY | CHANNEL | DOTS | OF | LOCAL | IN | OUT | LET | WHILE | DO | LOOP | FOREVER | IF | THEN | ELSE | AWAIT | WHEN | SKIP | PARALLEL | ASYNC | NONCRITICAL | CRITICAL | RELEASE | REQUEST | CONSUME | SENDS | RECEIVES | WHERE | THEY | ARE | HE | IS | UNDERSCORE | PRODUCE | Q_MARK | AND | BINOR | IMPLIES | LT | GT | EQ | NE | LE | GE | NOT | PLUS | MINUS | MULT | DIV | MOD | PLUS_ONE | MINUS_ONE | LABEL | INT_CONST | BOOL_CONST | PROC_NAME | IDENTIFIER | WS | MULTI_COMMENT | LINE_COMMENT | QUOTE | STRING )
        int alt26=78;
        alt26 = dfa26.predict(input);
        switch (alt26) {
            case 1 :
                // /home/yaniv/Desktop/SPL.g:1:10: OR
                {
                mOR(); 

                }
                break;
            case 2 :
                // /home/yaniv/Desktop/SPL.g:1:13: LEFT_BRAK
                {
                mLEFT_BRAK(); 

                }
                break;
            case 3 :
                // /home/yaniv/Desktop/SPL.g:1:23: RIGHT_BRAK
                {
                mRIGHT_BRAK(); 

                }
                break;
            case 4 :
                // /home/yaniv/Desktop/SPL.g:1:34: LEFT_BRACKET
                {
                mLEFT_BRACKET(); 

                }
                break;
            case 5 :
                // /home/yaniv/Desktop/SPL.g:1:47: RIGHT_BRACKET
                {
                mRIGHT_BRACKET(); 

                }
                break;
            case 6 :
                // /home/yaniv/Desktop/SPL.g:1:61: LEFT_BRACES
                {
                mLEFT_BRACES(); 

                }
                break;
            case 7 :
                // /home/yaniv/Desktop/SPL.g:1:73: LEFT_CURLY
                {
                mLEFT_CURLY(); 

                }
                break;
            case 8 :
                // /home/yaniv/Desktop/SPL.g:1:84: RIGHT_CURLY
                {
                mRIGHT_CURLY(); 

                }
                break;
            case 9 :
                // /home/yaniv/Desktop/SPL.g:1:96: RIGHT_BRACES
                {
                mRIGHT_BRACES(); 

                }
                break;
            case 10 :
                // /home/yaniv/Desktop/SPL.g:1:109: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 11 :
                // /home/yaniv/Desktop/SPL.g:1:119: COLON
                {
                mCOLON(); 

                }
                break;
            case 12 :
                // /home/yaniv/Desktop/SPL.g:1:125: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 13 :
                // /home/yaniv/Desktop/SPL.g:1:131: INT
                {
                mINT(); 

                }
                break;
            case 14 :
                // /home/yaniv/Desktop/SPL.g:1:135: BOOL
                {
                mBOOL(); 

                }
                break;
            case 15 :
                // /home/yaniv/Desktop/SPL.g:1:140: RATIONAL
                {
                mRATIONAL(); 

                }
                break;
            case 16 :
                // /home/yaniv/Desktop/SPL.g:1:149: LIST
                {
                mLIST(); 

                }
                break;
            case 17 :
                // /home/yaniv/Desktop/SPL.g:1:154: ARRAY
                {
                mARRAY(); 

                }
                break;
            case 18 :
                // /home/yaniv/Desktop/SPL.g:1:160: CHANNEL
                {
                mCHANNEL(); 

                }
                break;
            case 19 :
                // /home/yaniv/Desktop/SPL.g:1:168: DOTS
                {
                mDOTS(); 

                }
                break;
            case 20 :
                // /home/yaniv/Desktop/SPL.g:1:173: OF
                {
                mOF(); 

                }
                break;
            case 21 :
                // /home/yaniv/Desktop/SPL.g:1:176: LOCAL
                {
                mLOCAL(); 

                }
                break;
            case 22 :
                // /home/yaniv/Desktop/SPL.g:1:182: IN
                {
                mIN(); 

                }
                break;
            case 23 :
                // /home/yaniv/Desktop/SPL.g:1:185: OUT
                {
                mOUT(); 

                }
                break;
            case 24 :
                // /home/yaniv/Desktop/SPL.g:1:189: LET
                {
                mLET(); 

                }
                break;
            case 25 :
                // /home/yaniv/Desktop/SPL.g:1:193: WHILE
                {
                mWHILE(); 

                }
                break;
            case 26 :
                // /home/yaniv/Desktop/SPL.g:1:199: DO
                {
                mDO(); 

                }
                break;
            case 27 :
                // /home/yaniv/Desktop/SPL.g:1:202: LOOP
                {
                mLOOP(); 

                }
                break;
            case 28 :
                // /home/yaniv/Desktop/SPL.g:1:207: FOREVER
                {
                mFOREVER(); 

                }
                break;
            case 29 :
                // /home/yaniv/Desktop/SPL.g:1:215: IF
                {
                mIF(); 

                }
                break;
            case 30 :
                // /home/yaniv/Desktop/SPL.g:1:218: THEN
                {
                mTHEN(); 

                }
                break;
            case 31 :
                // /home/yaniv/Desktop/SPL.g:1:223: ELSE
                {
                mELSE(); 

                }
                break;
            case 32 :
                // /home/yaniv/Desktop/SPL.g:1:228: AWAIT
                {
                mAWAIT(); 

                }
                break;
            case 33 :
                // /home/yaniv/Desktop/SPL.g:1:234: WHEN
                {
                mWHEN(); 

                }
                break;
            case 34 :
                // /home/yaniv/Desktop/SPL.g:1:239: SKIP
                {
                mSKIP(); 

                }
                break;
            case 35 :
                // /home/yaniv/Desktop/SPL.g:1:244: PARALLEL
                {
                mPARALLEL(); 

                }
                break;
            case 36 :
                // /home/yaniv/Desktop/SPL.g:1:253: ASYNC
                {
                mASYNC(); 

                }
                break;
            case 37 :
                // /home/yaniv/Desktop/SPL.g:1:259: NONCRITICAL
                {
                mNONCRITICAL(); 

                }
                break;
            case 38 :
                // /home/yaniv/Desktop/SPL.g:1:271: CRITICAL
                {
                mCRITICAL(); 

                }
                break;
            case 39 :
                // /home/yaniv/Desktop/SPL.g:1:280: RELEASE
                {
                mRELEASE(); 

                }
                break;
            case 40 :
                // /home/yaniv/Desktop/SPL.g:1:288: REQUEST
                {
                mREQUEST(); 

                }
                break;
            case 41 :
                // /home/yaniv/Desktop/SPL.g:1:296: CONSUME
                {
                mCONSUME(); 

                }
                break;
            case 42 :
                // /home/yaniv/Desktop/SPL.g:1:304: SENDS
                {
                mSENDS(); 

                }
                break;
            case 43 :
                // /home/yaniv/Desktop/SPL.g:1:310: RECEIVES
                {
                mRECEIVES(); 

                }
                break;
            case 44 :
                // /home/yaniv/Desktop/SPL.g:1:319: WHERE
                {
                mWHERE(); 

                }
                break;
            case 45 :
                // /home/yaniv/Desktop/SPL.g:1:325: THEY
                {
                mTHEY(); 

                }
                break;
            case 46 :
                // /home/yaniv/Desktop/SPL.g:1:330: ARE
                {
                mARE(); 

                }
                break;
            case 47 :
                // /home/yaniv/Desktop/SPL.g:1:334: HE
                {
                mHE(); 

                }
                break;
            case 48 :
                // /home/yaniv/Desktop/SPL.g:1:337: IS
                {
                mIS(); 

                }
                break;
            case 49 :
                // /home/yaniv/Desktop/SPL.g:1:340: UNDERSCORE
                {
                mUNDERSCORE(); 

                }
                break;
            case 50 :
                // /home/yaniv/Desktop/SPL.g:1:351: PRODUCE
                {
                mPRODUCE(); 

                }
                break;
            case 51 :
                // /home/yaniv/Desktop/SPL.g:1:359: Q_MARK
                {
                mQ_MARK(); 

                }
                break;
            case 52 :
                // /home/yaniv/Desktop/SPL.g:1:366: AND
                {
                mAND(); 

                }
                break;
            case 53 :
                // /home/yaniv/Desktop/SPL.g:1:370: BINOR
                {
                mBINOR(); 

                }
                break;
            case 54 :
                // /home/yaniv/Desktop/SPL.g:1:376: IMPLIES
                {
                mIMPLIES(); 

                }
                break;
            case 55 :
                // /home/yaniv/Desktop/SPL.g:1:384: LT
                {
                mLT(); 

                }
                break;
            case 56 :
                // /home/yaniv/Desktop/SPL.g:1:387: GT
                {
                mGT(); 

                }
                break;
            case 57 :
                // /home/yaniv/Desktop/SPL.g:1:390: EQ
                {
                mEQ(); 

                }
                break;
            case 58 :
                // /home/yaniv/Desktop/SPL.g:1:393: NE
                {
                mNE(); 

                }
                break;
            case 59 :
                // /home/yaniv/Desktop/SPL.g:1:396: LE
                {
                mLE(); 

                }
                break;
            case 60 :
                // /home/yaniv/Desktop/SPL.g:1:399: GE
                {
                mGE(); 

                }
                break;
            case 61 :
                // /home/yaniv/Desktop/SPL.g:1:402: NOT
                {
                mNOT(); 

                }
                break;
            case 62 :
                // /home/yaniv/Desktop/SPL.g:1:406: PLUS
                {
                mPLUS(); 

                }
                break;
            case 63 :
                // /home/yaniv/Desktop/SPL.g:1:411: MINUS
                {
                mMINUS(); 

                }
                break;
            case 64 :
                // /home/yaniv/Desktop/SPL.g:1:417: MULT
                {
                mMULT(); 

                }
                break;
            case 65 :
                // /home/yaniv/Desktop/SPL.g:1:422: DIV
                {
                mDIV(); 

                }
                break;
            case 66 :
                // /home/yaniv/Desktop/SPL.g:1:426: MOD
                {
                mMOD(); 

                }
                break;
            case 67 :
                // /home/yaniv/Desktop/SPL.g:1:430: PLUS_ONE
                {
                mPLUS_ONE(); 

                }
                break;
            case 68 :
                // /home/yaniv/Desktop/SPL.g:1:439: MINUS_ONE
                {
                mMINUS_ONE(); 

                }
                break;
            case 69 :
                // /home/yaniv/Desktop/SPL.g:1:449: LABEL
                {
                mLABEL(); 

                }
                break;
            case 70 :
                // /home/yaniv/Desktop/SPL.g:1:455: INT_CONST
                {
                mINT_CONST(); 

                }
                break;
            case 71 :
                // /home/yaniv/Desktop/SPL.g:1:465: BOOL_CONST
                {
                mBOOL_CONST(); 

                }
                break;
            case 72 :
                // /home/yaniv/Desktop/SPL.g:1:476: PROC_NAME
                {
                mPROC_NAME(); 

                }
                break;
            case 73 :
                // /home/yaniv/Desktop/SPL.g:1:486: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 74 :
                // /home/yaniv/Desktop/SPL.g:1:497: WS
                {
                mWS(); 

                }
                break;
            case 75 :
                // /home/yaniv/Desktop/SPL.g:1:500: MULTI_COMMENT
                {
                mMULTI_COMMENT(); 

                }
                break;
            case 76 :
                // /home/yaniv/Desktop/SPL.g:1:514: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 77 :
                // /home/yaniv/Desktop/SPL.g:1:527: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 78 :
                // /home/yaniv/Desktop/SPL.g:1:533: STRING
                {
                mSTRING(); 

                }
                break;

        }

    }


    protected DFA26 dfa26 = new DFA26(this);
    static final String DFA26_eotS =
        "\1\uffff\1\56\1\70\1\73\1\74\6\uffff\1\77\1\uffff\6\56\1\uffff\6"+
        "\56\1\125\1\56\1\130\1\56\1\132\1\56\2\uffff\1\136\1\44\1\uffff"+
        "\1\141\1\uffff\1\144\1\uffff\1\56\1\uffff\2\146\1\56\4\uffff\2\56"+
        "\1\153\1\154\1\156\11\uffff\1\160\1\161\1\162\14\56\1\u0083\4\56"+
        "\2\uffff\1\56\2\uffff\1\u0089\1\uffff\1\56\1\u008b\10\uffff\1\56"+
        "\1\uffff\2\56\1\u0090\1\56\4\uffff\1\u0092\3\uffff\10\56\1\u009b"+
        "\6\56\1\144\1\uffff\5\56\1\uffff\1\56\1\uffff\3\56\4\uffff\1\u00ac"+
        "\3\56\1\u00b0\1\u00b1\2\56\1\uffff\4\56\1\u00b8\3\56\1\u00bc\1\u00bd"+
        "\1\u00be\1\u00bf\2\56\1\146\1\56\1\uffff\3\56\2\uffff\1\u00c6\1"+
        "\u00c7\1\u00c8\3\56\1\uffff\1\u00cc\1\u00cd\1\56\4\uffff\2\56\1"+
        "\146\3\56\3\uffff\3\56\2\uffff\3\56\1\u00da\1\u00db\1\56\1\u00dd"+
        "\1\56\1\u00df\1\u00e0\1\56\1\u00e2\2\uffff\1\u00e3\1\uffff\1\u00e4"+
        "\2\uffff\1\56\3\uffff\2\56\1\u00e8\1\uffff";
    static final String DFA26_eofS =
        "\u00e9\uffff";
    static final String DFA26_minS =
        "\1\11\1\137\1\74\1\75\1\56\6\uffff\1\75\1\uffff\6\137\1\uffff\6"+
        "\137\1\174\1\137\1\75\1\137\1\60\1\137\2\uffff\1\55\1\75\1\uffff"+
        "\1\53\1\uffff\1\52\1\uffff\1\137\1\uffff\2\60\1\61\4\uffff\1\164"+
        "\3\60\1\75\11\uffff\3\60\1\157\1\154\1\164\1\163\1\143\1\145\1\141"+
        "\1\156\1\151\1\141\1\145\1\166\1\60\1\162\1\145\1\163\1\151\2\uffff"+
        "\1\156\2\uffff\1\60\1\uffff\1\157\1\0\10\uffff\1\125\1\uffff\1\114"+
        "\3\60\4\uffff\1\60\3\uffff\1\154\1\165\1\145\1\151\1\164\1\160\2"+
        "\141\1\60\1\151\1\163\1\164\2\156\1\154\1\60\1\uffff\1\145\1\156"+
        "\1\145\1\160\1\143\1\uffff\1\144\1\uffff\1\105\1\123\1\60\4\uffff"+
        "\1\60\1\145\1\141\1\157\2\60\1\154\1\171\1\uffff\1\164\1\165\1\151"+
        "\1\156\1\60\2\145\1\166\4\60\1\162\1\165\1\60\1\105\1\uffff\2\163"+
        "\1\156\2\uffff\3\60\1\155\1\143\1\145\1\uffff\2\60\1\145\4\uffff"+
        "\1\151\1\143\1\60\1\164\1\145\1\141\3\uffff\1\145\1\141\1\154\2"+
        "\uffff\1\162\1\164\1\145\2\60\1\154\1\60\1\154\2\60\1\151\1\60\2"+
        "\uffff\1\60\1\uffff\1\60\2\uffff\1\143\3\uffff\1\141\1\154\1\60"+
        "\1\uffff";
    static final String DFA26_maxS =
        "\1\176\1\165\1\75\1\76\1\56\6\uffff\1\75\1\uffff\1\163\1\157\1\145"+
        "\1\157\1\167\1\162\1\uffff\1\150\2\157\1\150\1\154\1\153\1\174\1"+
        "\157\1\75\1\145\1\172\1\162\2\uffff\1\76\1\75\1\uffff\1\53\1\uffff"+
        "\1\57\1\uffff\1\137\1\uffff\2\172\1\71\4\uffff\1\164\1\71\2\172"+
        "\1\75\11\uffff\3\172\1\157\1\161\1\164\1\163\1\157\1\162\1\141\1"+
        "\156\1\151\1\141\1\151\1\166\1\172\1\162\1\145\1\163\1\151\2\uffff"+
        "\1\156\2\uffff\1\172\1\uffff\1\157\1\ufffe\10\uffff\1\125\1\uffff"+
        "\1\114\1\72\1\172\1\72\4\uffff\1\172\3\uffff\1\154\1\165\1\145\1"+
        "\151\1\164\1\160\2\141\1\172\1\151\1\163\1\164\1\156\1\162\1\154"+
        "\1\172\1\uffff\1\145\1\171\1\145\1\160\1\143\1\uffff\1\144\1\uffff"+
        "\1\105\1\123\1\72\4\uffff\1\172\1\145\1\141\1\157\2\172\1\154\1"+
        "\171\1\uffff\1\164\1\165\1\151\1\156\1\172\2\145\1\166\4\172\1\162"+
        "\1\165\1\172\1\105\1\uffff\2\163\1\156\2\uffff\3\172\1\155\1\143"+
        "\1\145\1\uffff\2\172\1\145\4\uffff\1\151\1\143\1\172\1\164\1\145"+
        "\1\141\3\uffff\1\145\1\141\1\154\2\uffff\1\162\1\164\1\145\2\172"+
        "\1\154\1\172\1\154\2\172\1\151\1\172\2\uffff\1\172\1\uffff\1\172"+
        "\2\uffff\1\143\3\uffff\1\141\1\154\1\172\1\uffff";
    static final String DFA26_acceptS =
        "\5\uffff\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\14\6\uffff\1\23\14"+
        "\uffff\1\63\1\64\2\uffff\1\75\1\uffff\1\100\1\uffff\1\102\1\uffff"+
        "\1\106\3\uffff\1\111\1\112\1\115\1\116\5\uffff\1\73\1\67\1\74\1"+
        "\3\1\70\1\4\1\44\1\30\1\13\24\uffff\1\43\1\65\1\uffff\1\53\1\71"+
        "\1\uffff\1\61\2\uffff\1\66\1\77\1\72\1\103\1\76\1\113\1\114\1\101"+
        "\1\uffff\1\107\4\uffff\1\1\1\24\1\52\1\2\1\uffff\1\26\1\60\1\35"+
        "\20\uffff\1\32\5\uffff\1\57\1\uffff\1\104\3\uffff\1\110\1\27\1\105"+
        "\1\15\10\uffff\1\56\20\uffff\1\16\3\uffff\1\20\1\33\6\uffff\1\41"+
        "\3\uffff\1\55\1\36\1\37\1\42\6\uffff\1\25\1\21\1\40\3\uffff\1\54"+
        "\1\31\14\uffff\1\50\1\47\1\uffff\1\51\1\uffff\1\22\1\34\1\uffff"+
        "\1\62\1\17\1\46\3\uffff\1\45";
    static final String DFA26_specialS =
        "\u00e9\uffff}>";
    static final String[] DFA26_transitionS = {
            "\2\57\2\uffff\1\57\22\uffff\1\57\1\43\1\61\2\uffff\1\50\1\41"+
            "\1\60\1\6\1\11\1\46\1\45\1\14\1\42\1\23\1\47\12\52\1\13\1\12"+
            "\1\2\1\34\1\3\1\40\1\uffff\5\56\1\54\11\56\1\55\3\56\1\53\6"+
            "\56\1\4\1\uffff\1\5\1\uffff\1\36\1\uffff\1\21\1\16\1\22\1\25"+
            "\1\30\1\26\1\51\1\35\1\15\2\51\1\20\1\51\1\33\1\1\1\37\1\51"+
            "\1\17\1\31\1\27\2\51\1\24\3\51\1\7\1\32\1\10\1\44",
            "\1\63\6\uffff\1\65\13\uffff\1\64\2\uffff\1\62",
            "\1\66\1\67",
            "\1\71\1\72",
            "\1\75",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\76",
            "",
            "\1\63\6\uffff\1\102\7\uffff\1\100\4\uffff\1\101",
            "\1\63\17\uffff\1\103",
            "\1\63\1\uffff\1\105\3\uffff\1\104",
            "\1\63\11\uffff\1\106\5\uffff\1\107",
            "\1\63\22\uffff\1\110\4\uffff\1\111",
            "\1\63\10\uffff\1\114\6\uffff\1\112\2\uffff\1\113",
            "",
            "\1\63\10\uffff\1\115",
            "\1\63\11\uffff\1\116\5\uffff\1\117",
            "\1\63\17\uffff\1\120",
            "\1\63\10\uffff\1\121",
            "\1\63\14\uffff\1\122",
            "\1\63\13\uffff\1\123",
            "\1\124",
            "\1\63\17\uffff\1\126",
            "\1\127",
            "\1\63\5\uffff\1\131",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\63\22\uffff\1\133",
            "",
            "",
            "\1\134\3\uffff\11\52\4\uffff\1\135",
            "\1\137",
            "",
            "\1\140",
            "",
            "\1\142\2\uffff\1\142\1\uffff\1\143",
            "",
            "\1\63",
            "",
            "\12\56\7\uffff\21\56\1\145\10\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\1\147\31\56\4\uffff\1\56\1\uffff\32\56",
            "\11\150",
            "",
            "",
            "",
            "",
            "\1\151",
            "\12\152",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\155",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\23\56\1\157\6\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\163",
            "\1\165\4\uffff\1\164",
            "\1\166",
            "\1\167",
            "\1\171\13\uffff\1\170",
            "\1\173\14\uffff\1\172",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080\3\uffff\1\u0081",
            "\1\u0082",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "",
            "",
            "\1\u0088",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "\1\u008a",
            "\uffff\143",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u008c",
            "",
            "\1\u008d",
            "\12\u008e\1\u008f",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\152\1\u0091",
            "",
            "",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "",
            "",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0\3\uffff\1\u00a1",
            "\1\u00a2",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "\1\u00a3",
            "\1\u00a5\12\uffff\1\u00a4",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "\1\u00ab",
            "\12\u008e\1\u008f",
            "",
            "",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00b2",
            "\1\u00b3",
            "",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00c0",
            "\1\u00c1",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00c2",
            "",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00ce",
            "",
            "",
            "",
            "",
            "\1\u00cf",
            "\1\u00d0",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "",
            "",
            "",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00dc",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00de",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "\1\u00e1",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            "",
            "",
            "\1\u00e5",
            "",
            "",
            "",
            "\1\u00e6",
            "\1\u00e7",
            "\12\56\7\uffff\32\56\4\uffff\1\56\1\uffff\32\56",
            ""
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( OR | LEFT_BRAK | RIGHT_BRAK | LEFT_BRACKET | RIGHT_BRACKET | LEFT_BRACES | LEFT_CURLY | RIGHT_CURLY | RIGHT_BRACES | SEMICOLON | COLON | COMMA | INT | BOOL | RATIONAL | LIST | ARRAY | CHANNEL | DOTS | OF | LOCAL | IN | OUT | LET | WHILE | DO | LOOP | FOREVER | IF | THEN | ELSE | AWAIT | WHEN | SKIP | PARALLEL | ASYNC | NONCRITICAL | CRITICAL | RELEASE | REQUEST | CONSUME | SENDS | RECEIVES | WHERE | THEY | ARE | HE | IS | UNDERSCORE | PRODUCE | Q_MARK | AND | BINOR | IMPLIES | LT | GT | EQ | NE | LE | GE | NOT | PLUS | MINUS | MULT | DIV | MOD | PLUS_ONE | MINUS_ONE | LABEL | INT_CONST | BOOL_CONST | PROC_NAME | IDENTIFIER | WS | MULTI_COMMENT | LINE_COMMENT | QUOTE | STRING );";
        }
    }
 

}