// Generated from src\grammaire\lg.g4 by ANTLR 4.0
package grammaire;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class lgParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__30=1, T__29=2, T__28=3, T__27=4, T__26=5, T__25=6, T__24=7, T__23=8, 
		T__22=9, T__21=10, T__20=11, T__19=12, T__18=13, T__17=14, T__16=15, T__15=16, 
		T__14=17, T__13=18, T__12=19, T__11=20, T__10=21, T__9=22, T__8=23, T__7=24, 
		T__6=25, T__5=26, T__4=27, T__3=28, T__2=29, T__1=30, T__0=31, ID=32, 
		WS=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'sinon'", "'alors'", "'{'", "'='", "'}'", "'$'", 
		"'&'", "'('", "'*'", "'.'", "'abstrait'", "'param'", "'est'", "'->'", 
		"'si'", "':'", "'['", "'=='", "'|'", "'<'", "']'", "'>'", "'type'", "'!'", 
		"'=>'", "'<>'", "'fonction'", "')'", "'+'", "'-'", "ID", "WS"
	};
	public static final int
		RULE_module = 0, RULE_element = 1, RULE_type = 2, RULE_param = 3, RULE_estAbstrait = 4, 
		RULE_superType = 5, RULE_champs = 6, RULE_champ = 7, RULE_typeRef = 8, 
		RULE_attribut = 9, RULE_attributs = 10, RULE_code = 11, RULE_var = 12, 
		RULE_appel = 13, RULE_objet = 14, RULE_id_externe = 15, RULE_acces = 16, 
		RULE_operation = 17, RULE_operationOuAcces = 18, RULE_operateur = 19, 
		RULE_testType = 20, RULE_testEgalite = 21, RULE_testDifference = 22, RULE_si = 23, 
		RULE_negation = 24, RULE_fonction = 25, RULE_tmpCode = 26, RULE_ref = 27, 
		RULE_literal = 28;
	public static final String[] ruleNames = {
		"module", "element", "type", "param", "estAbstrait", "superType", "champs", 
		"champ", "typeRef", "attribut", "attributs", "code", "var", "appel", "objet", 
		"id_externe", "acces", "operation", "operationOuAcces", "operateur", "testType", 
		"testEgalite", "testDifference", "si", "negation", "fonction", "tmpCode", 
		"ref", "literal"
	};

	@Override
	public String getGrammarFileName() { return "lg.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public lgParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModuleContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitModule(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 13) | (1L << 24) | (1L << 28))) != 0)) {
				{
				{
				setState(58); element();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public FonctionContext fonction() {
			return getRuleContext(FonctionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_element);
		try {
			setState(67);
			switch (_input.LA(1)) {
			case 12:
			case 24:
				enterOuterAlt(_localctx, 1);
				{
				setState(64); type();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 2);
				{
				setState(65); fonction();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 3);
				{
				setState(66); param();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ChampsContext champs() {
			return getRuleContext(ChampsContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public EstAbstraitContext estAbstrait() {
			return getRuleContext(EstAbstraitContext.class,0);
		}
		public SuperTypeContext superType() {
			return getRuleContext(SuperTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); estAbstrait();
			setState(70); match(24);
			setState(71); match(ID);
			setState(74);
			switch (_input.LA(1)) {
			case 17:
				{
				setState(72); superType();
				}
				break;
			case 4:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(76); match(4);
			setState(77); champs();
			setState(78); match(6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(lgParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(lgParser.ID, i);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(13);
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(81); match(ID);
				}
				break;

			case 2:
				{
				setState(82); id_externe();
				}
				break;
			}
			setState(85); match(17);
			setState(86); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EstAbstraitContext extends ParserRuleContext {
		public EstAbstraitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_estAbstrait; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterEstAbstrait(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitEstAbstrait(this);
		}
	}

	public final EstAbstraitContext estAbstrait() throws RecognitionException {
		EstAbstraitContext _localctx = new EstAbstraitContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_estAbstrait);
		try {
			setState(90);
			switch (_input.LA(1)) {
			case 12:
				enterOuterAlt(_localctx, 1);
				{
				setState(88); match(12);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperTypeContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public SuperTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterSuperType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitSuperType(this);
		}
	}

	public final SuperTypeContext superType() throws RecognitionException {
		SuperTypeContext _localctx = new SuperTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_superType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(92); match(17);
			setState(95);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(93); match(ID);
				}
				break;

			case 2:
				{
				setState(94); id_externe();
				}
				break;
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChampsContext extends ParserRuleContext {
		public ChampContext champ(int i) {
			return getRuleContext(ChampContext.class,i);
		}
		public List<ChampContext> champ() {
			return getRuleContexts(ChampContext.class);
		}
		public ChampsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_champs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterChamps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitChamps(this);
		}
	}

	public final ChampsContext champs() throws RecognitionException {
		ChampsContext _localctx = new ChampsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_champs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(97); champ();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChampContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public ChampContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_champ; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterChamp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitChamp(this);
		}
	}

	public final ChampContext champ() throws RecognitionException {
		ChampContext _localctx = new ChampContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_champ);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); typeRef();
			setState(104); match(17);
			setState(105); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeRefContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public TypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitTypeRef(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(107); match(ID);
				}
				break;

			case 2:
				{
				setState(108); id_externe();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributContext extends ParserRuleContext {
		public TmpCodeContext tmpCode() {
			return getRuleContext(TmpCodeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public AttributContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribut; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterAttribut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitAttribut(this);
		}
	}

	public final AttributContext attribut() throws RecognitionException {
		AttributContext _localctx = new AttributContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_attribut);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(ID);
			setState(112); match(5);
			setState(113); tmpCode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributsContext extends ParserRuleContext {
		public List<AttributContext> attribut() {
			return getRuleContexts(AttributContext.class);
		}
		public AttributContext attribut(int i) {
			return getRuleContext(AttributContext.class,i);
		}
		public AttributsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterAttributs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitAttributs(this);
		}
	}

	public final AttributsContext attributs() throws RecognitionException {
		AttributsContext _localctx = new AttributsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attributs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(115); attribut();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeContext extends ParserRuleContext {
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public AppelContext appel() {
			return getRuleContext(AppelContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public SiContext si() {
			return getRuleContext(SiContext.class,0);
		}
		public List<OperationOuAccesContext> operationOuAcces() {
			return getRuleContexts(OperationOuAccesContext.class);
		}
		public OperationOuAccesContext operationOuAcces(int i) {
			return getRuleContext(OperationOuAccesContext.class,i);
		}
		public ObjetContext objet() {
			return getRuleContext(ObjetContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_code);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				{
				setState(121); match(9);
				setState(122); code();
				setState(123); match(29);
				}
				}
				break;

			case 2:
				{
				setState(132);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(125); appel();
					}
					break;

				case 2:
					{
					setState(126); objet();
					}
					break;

				case 3:
					{
					setState(127); var();
					}
					break;

				case 4:
					{
					{
					setState(128); match(9);
					{
					setState(129); si();
					}
					setState(130); match(29);
					}
					}
					break;
				}
				}
				break;
			}
			{
			setState(139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(136); operationOuAcces();
					}
					} 
				}
				setState(141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AppelContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public List<TmpCodeContext> tmpCode() {
			return getRuleContexts(TmpCodeContext.class);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public TmpCodeContext tmpCode(int i) {
			return getRuleContext(TmpCodeContext.class,i);
		}
		public AppelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_appel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterAppel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitAppel(this);
		}
	}

	public final AppelContext appel() throws RecognitionException {
		AppelContext _localctx = new AppelContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_appel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(144); match(ID);
				}
				break;

			case 2:
				{
				setState(145); id_externe();
				}
				break;
			}
			setState(148); match(9);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 16) | (1L << 18) | (1L << ID))) != 0)) {
				{
				{
				setState(149); tmpCode();
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(155); match(29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjetContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public AttributsContext attributs() {
			return getRuleContext(AttributsContext.class,0);
		}
		public ObjetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterObjet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitObjet(this);
		}
	}

	public final ObjetContext objet() throws RecognitionException {
		ObjetContext _localctx = new ObjetContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_objet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(157); match(ID);
				}
				break;

			case 2:
				{
				setState(158); id_externe();
				}
				break;
			}
			setState(161); match(4);
			setState(162); attributs();
			setState(163); match(6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_externeContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(lgParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(lgParser.ID, i);
		}
		public Id_externeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_externe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterId_externe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitId_externe(this);
		}
	}

	public final Id_externeContext id_externe() throws RecognitionException {
		Id_externeContext _localctx = new Id_externeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_id_externe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(ID);
			setState(166); match(7);
			setState(167); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccesContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public AccesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterAcces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitAcces(this);
		}
	}

	public final AccesContext acces() throws RecognitionException {
		AccesContext _localctx = new AccesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_acces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(11);
			setState(170); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public TmpCodeContext tmpCode() {
			return getRuleContext(TmpCodeContext.class,0);
		}
		public OperateurContext operateur() {
			return getRuleContext(OperateurContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); operateur();
			setState(173); tmpCode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationOuAccesContext extends ParserRuleContext {
		public AccesContext acces() {
			return getRuleContext(AccesContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public OperationOuAccesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationOuAcces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterOperationOuAcces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitOperationOuAcces(this);
		}
	}

	public final OperationOuAccesContext operationOuAcces() throws RecognitionException {
		OperationOuAccesContext _localctx = new OperationOuAccesContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_operationOuAcces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			switch (_input.LA(1)) {
			case 1:
			case 5:
			case 8:
			case 10:
			case 15:
			case 19:
			case 20:
			case 21:
			case 23:
			case 26:
			case 30:
			case 31:
				{
				setState(175); operation();
				}
				break;
			case 11:
				{
				setState(176); acces();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperateurContext extends ParserRuleContext {
		public OperateurContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operateur; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterOperateur(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitOperateur(this);
		}
	}

	public final OperateurContext operateur() throws RecognitionException {
		OperateurContext _localctx = new OperateurContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_operateur);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 8) | (1L << 10) | (1L << 15) | (1L << 19) | (1L << 20) | (1L << 21) | (1L << 23) | (1L << 26) | (1L << 30) | (1L << 31))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestTypeContext extends ParserRuleContext {
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TestTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterTestType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitTestType(this);
		}
	}

	public final TestTypeContext testType() throws RecognitionException {
		TestTypeContext _localctx = new TestTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_testType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); code();
			setState(182); match(14);
			setState(183); negation();
			setState(184); typeRef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestEgaliteContext extends ParserRuleContext {
		public List<CodeContext> code() {
			return getRuleContexts(CodeContext.class);
		}
		public CodeContext code(int i) {
			return getRuleContext(CodeContext.class,i);
		}
		public TestEgaliteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testEgalite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterTestEgalite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitTestEgalite(this);
		}
	}

	public final TestEgaliteContext testEgalite() throws RecognitionException {
		TestEgaliteContext _localctx = new TestEgaliteContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_testEgalite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); code();
			setState(187); match(19);
			setState(188); code();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestDifferenceContext extends ParserRuleContext {
		public List<CodeContext> code() {
			return getRuleContexts(CodeContext.class);
		}
		public CodeContext code(int i) {
			return getRuleContext(CodeContext.class,i);
		}
		public TestDifferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testDifference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterTestDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitTestDifference(this);
		}
	}

	public final TestDifferenceContext testDifference() throws RecognitionException {
		TestDifferenceContext _localctx = new TestDifferenceContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_testDifference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190); code();
			setState(191); match(27);
			setState(192); code();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SiContext extends ParserRuleContext {
		public TestEgaliteContext testEgalite() {
			return getRuleContext(TestEgaliteContext.class,0);
		}
		public List<CodeContext> code() {
			return getRuleContexts(CodeContext.class);
		}
		public SiContext si() {
			return getRuleContext(SiContext.class,0);
		}
		public TestTypeContext testType() {
			return getRuleContext(TestTypeContext.class,0);
		}
		public TestDifferenceContext testDifference() {
			return getRuleContext(TestDifferenceContext.class,0);
		}
		public CodeContext code(int i) {
			return getRuleContext(CodeContext.class,i);
		}
		public SiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_si; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterSi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitSi(this);
		}
	}

	public final SiContext si() throws RecognitionException {
		SiContext _localctx = new SiContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_si);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); match(16);
			setState(198);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(195); testType();
				}
				break;

			case 2:
				{
				setState(196); testEgalite();
				}
				break;

			case 3:
				{
				setState(197); testDifference();
				}
				break;
			}
			setState(200); match(3);
			setState(201); code();
			setState(202); match(2);
			setState(205);
			switch (_input.LA(1)) {
			case 16:
				{
				setState(203); si();
				}
				break;
			case 9:
			case ID:
				{
				setState(204); code();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_negation);
		try {
			setState(209);
			switch (_input.LA(1)) {
			case 25:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); match(25);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FonctionContext extends ParserRuleContext {
		public ChampsContext champs() {
			return getRuleContext(ChampsContext.class,0);
		}
		public TmpCodeContext tmpCode() {
			return getRuleContext(TmpCodeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public OperateurContext operateur() {
			return getRuleContext(OperateurContext.class,0);
		}
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fonction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); match(28);
			setState(214);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(212); match(ID);
				}
				break;
			case 1:
			case 5:
			case 8:
			case 10:
			case 15:
			case 19:
			case 20:
			case 21:
			case 23:
			case 26:
			case 30:
			case 31:
				{
				setState(213); operateur();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(216); champs();
			setState(221);
			switch (_input.LA(1)) {
			case 20:
				{
				{
				setState(217); match(20);
				setState(218); tmpCode();
				}
				}
				break;
			case 15:
				{
				{
				setState(219); match(15);
				setState(220); typeRef();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TmpCodeContext extends ParserRuleContext {
		public AppelContext appel() {
			return getRuleContext(AppelContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public SiContext si() {
			return getRuleContext(SiContext.class,0);
		}
		public TmpCodeContext tmpCode() {
			return getRuleContext(TmpCodeContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TmpCodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tmpCode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterTmpCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitTmpCode(this);
		}
	}

	public final TmpCodeContext tmpCode() throws RecognitionException {
		TmpCodeContext _localctx = new TmpCodeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_tmpCode);
		try {
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(223); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(224); appel();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(225); si();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(226); code();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(227); match(9);
				setState(228); tmpCode();
				setState(229); match(29);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RefContext extends ParserRuleContext {
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
		public TerminalNode ID() { return getToken(lgParser.ID, 0); }
		public RefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitRef(this);
		}
	}

	public final RefContext ref() throws RecognitionException {
		RefContext _localctx = new RefContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(233); match(ID);
				}
				break;

			case 2:
				{
				setState(234); id_externe();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public RefContext ref(int i) {
			return getRuleContext(RefContext.class,i);
		}
		public List<RefContext> ref() {
			return getRuleContexts(RefContext.class);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lgListener ) ((lgListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); match(18);
			setState(238); ref();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(239); ref();
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(245); match(22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3#\u00fa\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36"+
		"\3\2\7\2>\n\2\f\2\16\2A\13\2\3\3\3\3\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\5\4M\n\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5V\n\5\3\5\3\5\3\5\3\6\3\6\5\6"+
		"]\n\6\3\7\3\7\3\7\5\7b\n\7\3\b\7\be\n\b\f\b\16\bh\13\b\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\5\np\n\n\3\13\3\13\3\13\3\13\3\f\7\fw\n\f\f\f\16\fz\13\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0087\n\r\5\r\u0089\n\r\3"+
		"\r\7\r\u008c\n\r\f\r\16\r\u008f\13\r\3\16\3\16\3\17\3\17\5\17\u0095\n"+
		"\17\3\17\3\17\7\17\u0099\n\17\f\17\16\17\u009c\13\17\3\17\3\17\3\20\3"+
		"\20\5\20\u00a2\n\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\24\3\24\5\24\u00b4\n\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\5\31\u00c9\n\31\3\31\3\31\3\31\3\31\3\31\5\31\u00d0\n\31\3\32\3\32\5"+
		"\32\u00d4\n\32\3\33\3\33\3\33\5\33\u00d9\n\33\3\33\3\33\3\33\3\33\3\33"+
		"\5\33\u00e0\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u00ea\n"+
		"\34\3\35\3\35\5\35\u00ee\n\35\3\36\3\36\3\36\7\36\u00f3\n\36\f\36\16\36"+
		"\u00f6\13\36\3\36\3\36\3\36\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:\2\3\13\3\3\7\7\n\n\f\f\21\21\25\27\31\31\34\34"+
		" !\u00fb\2?\3\2\2\2\4E\3\2\2\2\6G\3\2\2\2\bR\3\2\2\2\n\\\3\2\2\2\f^\3"+
		"\2\2\2\16f\3\2\2\2\20i\3\2\2\2\22o\3\2\2\2\24q\3\2\2\2\26x\3\2\2\2\30"+
		"\u0088\3\2\2\2\32\u0090\3\2\2\2\34\u0094\3\2\2\2\36\u00a1\3\2\2\2 \u00a7"+
		"\3\2\2\2\"\u00ab\3\2\2\2$\u00ae\3\2\2\2&\u00b3\3\2\2\2(\u00b5\3\2\2\2"+
		"*\u00b7\3\2\2\2,\u00bc\3\2\2\2.\u00c0\3\2\2\2\60\u00c4\3\2\2\2\62\u00d3"+
		"\3\2\2\2\64\u00d5\3\2\2\2\66\u00e9\3\2\2\28\u00ed\3\2\2\2:\u00ef\3\2\2"+
		"\2<>\5\4\3\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\3\3\2\2\2A?\3\2"+
		"\2\2BF\5\6\4\2CF\5\64\33\2DF\5\b\5\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2F\5"+
		"\3\2\2\2GH\5\n\6\2HI\7\32\2\2IL\7\"\2\2JM\5\f\7\2KM\3\2\2\2LJ\3\2\2\2"+
		"LK\3\2\2\2MN\3\2\2\2NO\7\6\2\2OP\5\16\b\2PQ\7\b\2\2Q\7\3\2\2\2RU\7\17"+
		"\2\2SV\7\"\2\2TV\5 \21\2US\3\2\2\2UT\3\2\2\2VW\3\2\2\2WX\7\23\2\2XY\7"+
		"\"\2\2Y\t\3\2\2\2Z]\7\16\2\2[]\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]\13\3\2\2"+
		"\2^a\7\23\2\2_b\7\"\2\2`b\5 \21\2a_\3\2\2\2a`\3\2\2\2b\r\3\2\2\2ce\5\20"+
		"\t\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\17\3\2\2\2hf\3\2\2\2ij\5"+
		"\22\n\2jk\7\23\2\2kl\7\"\2\2l\21\3\2\2\2mp\7\"\2\2np\5 \21\2om\3\2\2\2"+
		"on\3\2\2\2p\23\3\2\2\2qr\7\"\2\2rs\7\7\2\2st\5\66\34\2t\25\3\2\2\2uw\5"+
		"\24\13\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\27\3\2\2\2zx\3\2\2\2"+
		"{|\7\13\2\2|}\5\30\r\2}~\7\37\2\2~\u0089\3\2\2\2\177\u0087\5\34\17\2\u0080"+
		"\u0087\5\36\20\2\u0081\u0087\5\32\16\2\u0082\u0083\7\13\2\2\u0083\u0084"+
		"\5\60\31\2\u0084\u0085\7\37\2\2\u0085\u0087\3\2\2\2\u0086\177\3\2\2\2"+
		"\u0086\u0080\3\2\2\2\u0086\u0081\3\2\2\2\u0086\u0082\3\2\2\2\u0087\u0089"+
		"\3\2\2\2\u0088{\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008d\3\2\2\2\u008a"+
		"\u008c\5&\24\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\31\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091"+
		"\7\"\2\2\u0091\33\3\2\2\2\u0092\u0095\7\"\2\2\u0093\u0095\5 \21\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u009a\7\13"+
		"\2\2\u0097\u0099\5\66\34\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009d\u009e\7\37\2\2\u009e\35\3\2\2\2\u009f\u00a2\7\"\2\2\u00a0\u00a2"+
		"\5 \21\2\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a4\7\6\2\2\u00a4\u00a5\5\26\f\2\u00a5\u00a6\7\b\2\2\u00a6\37\3\2\2"+
		"\2\u00a7\u00a8\7\"\2\2\u00a8\u00a9\7\t\2\2\u00a9\u00aa\7\"\2\2\u00aa!"+
		"\3\2\2\2\u00ab\u00ac\7\r\2\2\u00ac\u00ad\7\"\2\2\u00ad#\3\2\2\2\u00ae"+
		"\u00af\5(\25\2\u00af\u00b0\5\66\34\2\u00b0%\3\2\2\2\u00b1\u00b4\5$\23"+
		"\2\u00b2\u00b4\5\"\22\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4"+
		"\'\3\2\2\2\u00b5\u00b6\t\2\2\2\u00b6)\3\2\2\2\u00b7\u00b8\5\30\r\2\u00b8"+
		"\u00b9\7\20\2\2\u00b9\u00ba\5\62\32\2\u00ba\u00bb\5\22\n\2\u00bb+\3\2"+
		"\2\2\u00bc\u00bd\5\30\r\2\u00bd\u00be\7\25\2\2\u00be\u00bf\5\30\r\2\u00bf"+
		"-\3\2\2\2\u00c0\u00c1\5\30\r\2\u00c1\u00c2\7\35\2\2\u00c2\u00c3\5\30\r"+
		"\2\u00c3/\3\2\2\2\u00c4\u00c8\7\22\2\2\u00c5\u00c9\5*\26\2\u00c6\u00c9"+
		"\5,\27\2\u00c7\u00c9\5.\30\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\7\5\2\2\u00cb\u00cc\5\30"+
		"\r\2\u00cc\u00cf\7\4\2\2\u00cd\u00d0\5\60\31\2\u00ce\u00d0\5\30\r\2\u00cf"+
		"\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0\61\3\2\2\2\u00d1\u00d4\7\33\2"+
		"\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\63"+
		"\3\2\2\2\u00d5\u00d8\7\36\2\2\u00d6\u00d9\7\"\2\2\u00d7\u00d9\5(\25\2"+
		"\u00d8\u00d6\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00df"+
		"\5\16\b\2\u00db\u00dc\7\26\2\2\u00dc\u00e0\5\66\34\2\u00dd\u00de\7\21"+
		"\2\2\u00de\u00e0\5\22\n\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0"+
		"\65\3\2\2\2\u00e1\u00ea\5:\36\2\u00e2\u00ea\5\34\17\2\u00e3\u00ea\5\60"+
		"\31\2\u00e4\u00ea\5\30\r\2\u00e5\u00e6\7\13\2\2\u00e6\u00e7\5\66\34\2"+
		"\u00e7\u00e8\7\37\2\2\u00e8\u00ea\3\2\2\2\u00e9\u00e1\3\2\2\2\u00e9\u00e2"+
		"\3\2\2\2\u00e9\u00e3\3\2\2\2\u00e9\u00e4\3\2\2\2\u00e9\u00e5\3\2\2\2\u00ea"+
		"\67\3\2\2\2\u00eb\u00ee\7\"\2\2\u00ec\u00ee\5 \21\2\u00ed\u00eb\3\2\2"+
		"\2\u00ed\u00ec\3\2\2\2\u00ee9\3\2\2\2\u00ef\u00f0\7\24\2\2\u00f0\u00f4"+
		"\58\35\2\u00f1\u00f3\58\35\2\u00f2\u00f1\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f4\3\2"+
		"\2\2\u00f7\u00f8\7\30\2\2\u00f8;\3\2\2\2\32?ELU\\afox\u0086\u0088\u008d"+
		"\u0094\u009a\u00a1\u00b3\u00c8\u00cf\u00d3\u00d8\u00df\u00e9\u00ed\u00f4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}