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
		T__29=1, T__28=2, T__27=3, T__26=4, T__25=5, T__24=6, T__23=7, T__22=8, 
		T__21=9, T__20=10, T__19=11, T__18=12, T__17=13, T__16=14, T__15=15, T__14=16, 
		T__13=17, T__12=18, T__11=19, T__10=20, T__9=21, T__8=22, T__7=23, T__6=24, 
		T__5=25, T__4=26, T__3=27, T__2=28, T__1=29, T__0=30, ID=31, WS=32;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'/'", "'sinon'", "'alors'", "'{'", "'='", "'}'", "'$'", "'&'", "'('", 
		"'*'", "'.'", "'abstrait'", "'est'", "'->'", "'si'", "':'", "'['", "'=='", 
		"'|'", "'<'", "']'", "'>'", "'type'", "'!'", "'=>'", "'<>'", "'fonction'", 
		"')'", "'+'", "'-'", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", 
		"T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", 
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "ID", "WS"
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
		case 31: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\"\u00ad\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \7 \u00a2\n \f \16 \u00a5\13 \3"+
		"!\6!\u00a8\n!\r!\16!\u00a9\3!\3!\2\"\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r"+
		"\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21"+
		"\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65"+
		"\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\2\3\2\5\6\62;C\\aac|\6\62;C\\aac"+
		"|\5\13\f\17\17\"\"\u00ae\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2"+
		"\2\5E\3\2\2\2\7K\3\2\2\2\tQ\3\2\2\2\13S\3\2\2\2\rU\3\2\2\2\17W\3\2\2\2"+
		"\21Y\3\2\2\2\23[\3\2\2\2\25]\3\2\2\2\27_\3\2\2\2\31a\3\2\2\2\33j\3\2\2"+
		"\2\35n\3\2\2\2\37q\3\2\2\2!t\3\2\2\2#v\3\2\2\2%x\3\2\2\2\'{\3\2\2\2)}"+
		"\3\2\2\2+\177\3\2\2\2-\u0081\3\2\2\2/\u0083\3\2\2\2\61\u0088\3\2\2\2\63"+
		"\u008a\3\2\2\2\65\u008d\3\2\2\2\67\u0090\3\2\2\29\u0099\3\2\2\2;\u009b"+
		"\3\2\2\2=\u009d\3\2\2\2?\u009f\3\2\2\2A\u00a7\3\2\2\2CD\7\61\2\2D\4\3"+
		"\2\2\2EF\7u\2\2FG\7k\2\2GH\7p\2\2HI\7q\2\2IJ\7p\2\2J\6\3\2\2\2KL\7c\2"+
		"\2LM\7n\2\2MN\7q\2\2NO\7t\2\2OP\7u\2\2P\b\3\2\2\2QR\7}\2\2R\n\3\2\2\2"+
		"ST\7?\2\2T\f\3\2\2\2UV\7\177\2\2V\16\3\2\2\2WX\7&\2\2X\20\3\2\2\2YZ\7"+
		"(\2\2Z\22\3\2\2\2[\\\7*\2\2\\\24\3\2\2\2]^\7,\2\2^\26\3\2\2\2_`\7\60\2"+
		"\2`\30\3\2\2\2ab\7c\2\2bc\7d\2\2cd\7u\2\2de\7v\2\2ef\7t\2\2fg\7c\2\2g"+
		"h\7k\2\2hi\7v\2\2i\32\3\2\2\2jk\7g\2\2kl\7u\2\2lm\7v\2\2m\34\3\2\2\2n"+
		"o\7/\2\2op\7@\2\2p\36\3\2\2\2qr\7u\2\2rs\7k\2\2s \3\2\2\2tu\7<\2\2u\""+
		"\3\2\2\2vw\7]\2\2w$\3\2\2\2xy\7?\2\2yz\7?\2\2z&\3\2\2\2{|\7~\2\2|(\3\2"+
		"\2\2}~\7>\2\2~*\3\2\2\2\177\u0080\7_\2\2\u0080,\3\2\2\2\u0081\u0082\7"+
		"@\2\2\u0082.\3\2\2\2\u0083\u0084\7v\2\2\u0084\u0085\7{\2\2\u0085\u0086"+
		"\7r\2\2\u0086\u0087\7g\2\2\u0087\60\3\2\2\2\u0088\u0089\7#\2\2\u0089\62"+
		"\3\2\2\2\u008a\u008b\7?\2\2\u008b\u008c\7@\2\2\u008c\64\3\2\2\2\u008d"+
		"\u008e\7>\2\2\u008e\u008f\7@\2\2\u008f\66\3\2\2\2\u0090\u0091\7h\2\2\u0091"+
		"\u0092\7q\2\2\u0092\u0093\7p\2\2\u0093\u0094\7e\2\2\u0094\u0095\7v\2\2"+
		"\u0095\u0096\7k\2\2\u0096\u0097\7q\2\2\u0097\u0098\7p\2\2\u00988\3\2\2"+
		"\2\u0099\u009a\7+\2\2\u009a:\3\2\2\2\u009b\u009c\7-\2\2\u009c<\3\2\2\2"+
		"\u009d\u009e\7/\2\2\u009e>\3\2\2\2\u009f\u00a3\t\2\2\2\u00a0\u00a2\t\3"+
		"\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4@\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a8\t\4\2\2"+
		"\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\b!\2\2\u00acB\3\2\2\2\5\2\u00a3"+
		"\u00a9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}