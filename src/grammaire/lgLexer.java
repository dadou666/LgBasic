// Generated from src\grammaire\lg.g4 by ANTLR 4.0
package grammaire;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class lgLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__26=1, T__25=2, T__24=3, T__23=4, T__22=5, T__21=6, T__20=7, T__19=8, 
		T__18=9, T__17=10, T__16=11, T__15=12, T__14=13, T__13=14, T__12=15, T__11=16, 
		T__10=17, T__9=18, T__8=19, T__7=20, T__6=21, T__5=22, T__4=23, T__3=24, 
		T__2=25, T__1=26, T__0=27, ENTIER=28, ENTIER_EXTERNE=29, ID=30, WS=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'/'", "'!='", "'{'", "'='", "'}'", "'if'", "'$'", "'&'", "'('", "'is'", 
		"'*'", "'.'", "'->'", "':'", "'=='", "'|'", "'<'", "'>'", "'type'", "'!'", 
		"'=>'", "'else'", "'fonction'", "')'", "'then'", "'+'", "'-'", "ENTIER", 
		"ENTIER_EXTERNE", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__26", "T__25", "T__24", "T__23", "T__22", "T__21", "T__20", "T__19", 
		"T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", 
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "ENTIER", "ENTIER_EXTERNE", "ID", "WS"
	};


	public lgLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lg.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 30: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4!\u00b5\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\7\35\u0090\n\35\f\35\16\35\u0093"+
		"\13\35\3\35\3\35\3\35\3\35\5\35\u0099\n\35\3\36\3\36\7\36\u009d\n\36\f"+
		"\36\16\36\u00a0\13\36\3\36\3\36\3\36\3\36\5\36\u00a6\n\36\3\37\3\37\7"+
		"\37\u00aa\n\37\f\37\16\37\u00ad\13\37\3 \6 \u00b0\n \r \16 \u00b1\3 \3"+
		" \2!\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1"+
		"\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1"+
		"+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\2"+
		"\3\2\t\3\63;\3\62;\3\63;\3\62;\5C\\aac|\6\62;C\\aac|\5\13\f\17\17\"\""+
		"\u00ba\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5C\3\2\2\2\7F\3\2\2\2\t"+
		"H\3\2\2\2\13J\3\2\2\2\rL\3\2\2\2\17O\3\2\2\2\21Q\3\2\2\2\23S\3\2\2\2\25"+
		"U\3\2\2\2\27X\3\2\2\2\31Z\3\2\2\2\33\\\3\2\2\2\35_\3\2\2\2\37a\3\2\2\2"+
		"!d\3\2\2\2#f\3\2\2\2%h\3\2\2\2\'j\3\2\2\2)o\3\2\2\2+q\3\2\2\2-t\3\2\2"+
		"\2/y\3\2\2\2\61\u0082\3\2\2\2\63\u0084\3\2\2\2\65\u0089\3\2\2\2\67\u008b"+
		"\3\2\2\29\u008d\3\2\2\2;\u009a\3\2\2\2=\u00a7\3\2\2\2?\u00af\3\2\2\2A"+
		"B\7\61\2\2B\4\3\2\2\2CD\7#\2\2DE\7?\2\2E\6\3\2\2\2FG\7}\2\2G\b\3\2\2\2"+
		"HI\7?\2\2I\n\3\2\2\2JK\7\177\2\2K\f\3\2\2\2LM\7k\2\2MN\7h\2\2N\16\3\2"+
		"\2\2OP\7&\2\2P\20\3\2\2\2QR\7(\2\2R\22\3\2\2\2ST\7*\2\2T\24\3\2\2\2UV"+
		"\7k\2\2VW\7u\2\2W\26\3\2\2\2XY\7,\2\2Y\30\3\2\2\2Z[\7\60\2\2[\32\3\2\2"+
		"\2\\]\7/\2\2]^\7@\2\2^\34\3\2\2\2_`\7<\2\2`\36\3\2\2\2ab\7?\2\2bc\7?\2"+
		"\2c \3\2\2\2de\7~\2\2e\"\3\2\2\2fg\7>\2\2g$\3\2\2\2hi\7@\2\2i&\3\2\2\2"+
		"jk\7v\2\2kl\7{\2\2lm\7r\2\2mn\7g\2\2n(\3\2\2\2op\7#\2\2p*\3\2\2\2qr\7"+
		"?\2\2rs\7@\2\2s,\3\2\2\2tu\7g\2\2uv\7n\2\2vw\7u\2\2wx\7g\2\2x.\3\2\2\2"+
		"yz\7h\2\2z{\7q\2\2{|\7p\2\2|}\7e\2\2}~\7v\2\2~\177\7k\2\2\177\u0080\7"+
		"q\2\2\u0080\u0081\7p\2\2\u0081\60\3\2\2\2\u0082\u0083\7+\2\2\u0083\62"+
		"\3\2\2\2\u0084\u0085\7v\2\2\u0085\u0086\7j\2\2\u0086\u0087\7g\2\2\u0087"+
		"\u0088\7p\2\2\u0088\64\3\2\2\2\u0089\u008a\7-\2\2\u008a\66\3\2\2\2\u008b"+
		"\u008c\7/\2\2\u008c8\3\2\2\2\u008d\u0091\t\2\2\2\u008e\u0090\t\3\2\2\u008f"+
		"\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0098\5=\37\2\u0095"+
		"\u0096\7&\2\2\u0096\u0099\5=\37\2\u0097\u0099\3\2\2\2\u0098\u0095\3\2"+
		"\2\2\u0098\u0097\3\2\2\2\u0099:\3\2\2\2\u009a\u009e\t\4\2\2\u009b\u009d"+
		"\t\5\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a5\5="+
		"\37\2\u00a2\u00a3\7&\2\2\u00a3\u00a6\5=\37\2\u00a4\u00a6\3\2\2\2\u00a5"+
		"\u00a2\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6<\3\2\2\2\u00a7\u00ab\t\6\2\2"+
		"\u00a8\u00aa\t\7\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac>\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae"+
		"\u00b0\t\b\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\b \2\2\u00b4"+
		"@\3\2\2\2\t\2\u0091\u0098\u009e\u00a5\u00ab\u00b1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}