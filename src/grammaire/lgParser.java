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
		T__28=1, T__27=2, T__26=3, T__25=4, T__24=5, T__23=6, T__22=7, T__21=8, 
		T__20=9, T__19=10, T__18=11, T__17=12, T__16=13, T__15=14, T__14=15, T__13=16, 
		T__12=17, T__11=18, T__10=19, T__9=20, T__8=21, T__7=22, T__6=23, T__5=24, 
		T__4=25, T__3=26, T__2=27, T__1=28, T__0=29, ID=30, WS=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'sinon'", "'alors'", "'{'", "'='", "'}'", "'$'", 
		"'&'", "'('", "'*'", "'.'", "'est'", "'->'", "'si'", "':'", "'['", "'=='", 
		"'|'", "'<'", "']'", "'>'", "'type'", "'!'", "'=>'", "'<>'", "'fonction'", 
		"')'", "'+'", "'-'", "ID", "WS"
	};
	public static final int
		RULE_module = 0, RULE_element = 1, RULE_type = 2, RULE_superType = 3, 
		RULE_champs = 4, RULE_champ = 5, RULE_typeRef = 6, RULE_attribut = 7, 
		RULE_attributs = 8, RULE_code = 9, RULE_var = 10, RULE_appel = 11, RULE_objet = 12, 
		RULE_id_externe = 13, RULE_acces = 14, RULE_operation = 15, RULE_operationOuAcces = 16, 
		RULE_operateur = 17, RULE_testType = 18, RULE_testEgalite = 19, RULE_testDifference = 20, 
		RULE_si = 21, RULE_negation = 22, RULE_fonction = 23, RULE_tmpCode = 24, 
		RULE_literal = 25;
	public static final String[] ruleNames = {
		"module", "element", "type", "superType", "champs", "champ", "typeRef", 
		"attribut", "attributs", "code", "var", "appel", "objet", "id_externe", 
		"acces", "operation", "operationOuAcces", "operateur", "testType", "testEgalite", 
		"testDifference", "si", "negation", "fonction", "tmpCode", "literal"
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==22 || _la==26) {
				{
				{
				setState(52); element();
				}
				}
				setState(57);
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
			setState(60);
			switch (_input.LA(1)) {
			case 22:
				enterOuterAlt(_localctx, 1);
				{
				setState(58); type();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 2);
				{
				setState(59); fonction();
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
			setState(62); match(22);
			setState(63); match(ID);
			setState(66);
			switch (_input.LA(1)) {
			case 15:
				{
				setState(64); superType();
				}
				break;
			case 4:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(68); match(4);
			setState(69); champs();
			setState(70); match(6);
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
		enterRule(_localctx, 6, RULE_superType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(72); match(15);
			setState(75);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(73); match(ID);
				}
				break;

			case 2:
				{
				setState(74); id_externe();
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
		enterRule(_localctx, 8, RULE_champs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(77); champ();
				}
				}
				setState(82);
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
		enterRule(_localctx, 10, RULE_champ);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); typeRef();
			setState(84); match(15);
			setState(85); match(ID);
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
		enterRule(_localctx, 12, RULE_typeRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(87); match(ID);
				}
				break;

			case 2:
				{
				setState(88); id_externe();
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
		enterRule(_localctx, 14, RULE_attribut);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(ID);
			setState(92); match(5);
			setState(93); tmpCode();
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
		enterRule(_localctx, 16, RULE_attributs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(95); attribut();
				}
				}
				setState(100);
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
		public OperationOuAccesContext operationOuAcces() {
			return getRuleContext(OperationOuAccesContext.class,0);
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
		enterRule(_localctx, 18, RULE_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				{
				setState(101); match(9);
				setState(102); code();
				setState(103); match(27);
				}
				}
				break;

			case 2:
				{
				setState(112);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(105); appel();
					}
					break;

				case 2:
					{
					setState(106); objet();
					}
					break;

				case 3:
					{
					setState(107); var();
					}
					break;

				case 4:
					{
					{
					setState(108); match(9);
					{
					setState(109); si();
					}
					setState(110); match(27);
					}
					}
					break;
				}
				}
				break;
			}
			setState(118);
			switch (_input.LA(1)) {
			case 1:
			case 5:
			case 8:
			case 10:
			case 11:
			case 13:
			case 18:
			case 19:
			case 21:
			case 24:
			case 28:
			case 29:
				{
				setState(116); operationOuAcces();
				}
				break;
			case EOF:
			case 2:
			case 3:
			case 6:
			case 9:
			case 12:
			case 14:
			case 16:
			case 17:
			case 22:
			case 25:
			case 26:
			case 27:
			case ID:
				{
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
		enterRule(_localctx, 20, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(ID);
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
		enterRule(_localctx, 22, RULE_appel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(122); match(ID);
				}
				break;

			case 2:
				{
				setState(123); id_externe();
				}
				break;
			}
			setState(126); match(9);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 14) | (1L << 16) | (1L << ID))) != 0)) {
				{
				{
				setState(127); tmpCode();
				}
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(133); match(27);
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
		enterRule(_localctx, 24, RULE_objet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(135); match(ID);
				}
				break;

			case 2:
				{
				setState(136); id_externe();
				}
				break;
			}
			setState(139); match(4);
			setState(140); attributs();
			setState(141); match(6);
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
		enterRule(_localctx, 26, RULE_id_externe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(ID);
			setState(144); match(7);
			setState(145); match(ID);
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
		enterRule(_localctx, 28, RULE_acces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(11);
			setState(148); match(ID);
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
		enterRule(_localctx, 30, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); operateur();
			setState(151); tmpCode();
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
		enterRule(_localctx, 32, RULE_operationOuAcces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			switch (_input.LA(1)) {
			case 1:
			case 5:
			case 8:
			case 10:
			case 13:
			case 18:
			case 19:
			case 21:
			case 24:
			case 28:
			case 29:
				{
				setState(153); operation();
				}
				break;
			case 11:
				{
				setState(154); acces();
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
		enterRule(_localctx, 34, RULE_operateur);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 8) | (1L << 10) | (1L << 13) | (1L << 18) | (1L << 19) | (1L << 21) | (1L << 24) | (1L << 28) | (1L << 29))) != 0)) ) {
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
		enterRule(_localctx, 36, RULE_testType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); code();
			setState(160); match(12);
			setState(161); negation();
			setState(162); typeRef();
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
		enterRule(_localctx, 38, RULE_testEgalite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); code();
			setState(165); match(17);
			setState(166); code();
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
		enterRule(_localctx, 40, RULE_testDifference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); code();
			setState(169); match(25);
			setState(170); code();
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
		enterRule(_localctx, 42, RULE_si);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); match(14);
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(173); testType();
				}
				break;

			case 2:
				{
				setState(174); testEgalite();
				}
				break;

			case 3:
				{
				setState(175); testDifference();
				}
				break;
			}
			setState(178); match(3);
			setState(179); code();
			setState(180); match(2);
			setState(183);
			switch (_input.LA(1)) {
			case 14:
				{
				setState(181); si();
				}
				break;
			case 9:
			case ID:
				{
				setState(182); code();
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
		enterRule(_localctx, 44, RULE_negation);
		try {
			setState(187);
			switch (_input.LA(1)) {
			case 23:
				enterOuterAlt(_localctx, 1);
				{
				setState(185); match(23);
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
		enterRule(_localctx, 46, RULE_fonction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189); match(26);
			setState(192);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(190); match(ID);
				}
				break;
			case 1:
			case 5:
			case 8:
			case 10:
			case 13:
			case 18:
			case 19:
			case 21:
			case 24:
			case 28:
			case 29:
				{
				setState(191); operateur();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(194); champs();
			setState(195); match(18);
			setState(196); tmpCode();
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
		enterRule(_localctx, 48, RULE_tmpCode);
		try {
			setState(206);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199); appel();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200); si();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(201); code();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(202); match(9);
				setState(203); tmpCode();
				setState(204); match(27);
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

	public static class LiteralContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(lgParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(lgParser.ID, i);
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
		enterRule(_localctx, 50, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(16);
			setState(209); match(ID);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(210); match(ID);
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216); match(20);
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
		"\2\3!\u00dd\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\3\2\7\28\n\2\f\2\16\2;\13\2\3"+
		"\3\3\3\5\3?\n\3\3\4\3\4\3\4\3\4\5\4E\n\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5N\n\5\3\6\7\6Q\n\6\f\6\16\6T\13\6\3\7\3\7\3\7\3\7\3\b\3\b\5\b\\\n\b"+
		"\3\t\3\t\3\t\3\t\3\n\7\nc\n\n\f\n\16\nf\13\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13s\n\13\5\13u\n\13\3\13\3\13\5\13y\n"+
		"\13\3\f\3\f\3\r\3\r\5\r\177\n\r\3\r\3\r\7\r\u0083\n\r\f\r\16\r\u0086\13"+
		"\r\3\r\3\r\3\16\3\16\5\16\u008c\n\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\5\22\u009e\n\22\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\5\27\u00b3\n\27\3\27\3\27\3\27\3\27\3\27\5\27\u00ba\n"+
		"\27\3\30\3\30\5\30\u00be\n\30\3\31\3\31\3\31\5\31\u00c3\n\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00d1\n\32\3\33"+
		"\3\33\3\33\7\33\u00d6\n\33\f\33\16\33\u00d9\13\33\3\33\3\33\3\33\2\34"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\2\3\13\3\3\7"+
		"\7\n\n\f\f\17\17\24\25\27\27\32\32\36\37\u00dc\29\3\2\2\2\4>\3\2\2\2\6"+
		"@\3\2\2\2\bJ\3\2\2\2\nR\3\2\2\2\fU\3\2\2\2\16[\3\2\2\2\20]\3\2\2\2\22"+
		"d\3\2\2\2\24t\3\2\2\2\26z\3\2\2\2\30~\3\2\2\2\32\u008b\3\2\2\2\34\u0091"+
		"\3\2\2\2\36\u0095\3\2\2\2 \u0098\3\2\2\2\"\u009d\3\2\2\2$\u009f\3\2\2"+
		"\2&\u00a1\3\2\2\2(\u00a6\3\2\2\2*\u00aa\3\2\2\2,\u00ae\3\2\2\2.\u00bd"+
		"\3\2\2\2\60\u00bf\3\2\2\2\62\u00d0\3\2\2\2\64\u00d2\3\2\2\2\668\5\4\3"+
		"\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\3\3\2\2\2;9\3\2\2\2"+
		"<?\5\6\4\2=?\5\60\31\2><\3\2\2\2>=\3\2\2\2?\5\3\2\2\2@A\7\30\2\2AD\7 "+
		"\2\2BE\5\b\5\2CE\3\2\2\2DB\3\2\2\2DC\3\2\2\2EF\3\2\2\2FG\7\6\2\2GH\5\n"+
		"\6\2HI\7\b\2\2I\7\3\2\2\2JM\7\21\2\2KN\7 \2\2LN\5\34\17\2MK\3\2\2\2ML"+
		"\3\2\2\2N\t\3\2\2\2OQ\5\f\7\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2"+
		"S\13\3\2\2\2TR\3\2\2\2UV\5\16\b\2VW\7\21\2\2WX\7 \2\2X\r\3\2\2\2Y\\\7"+
		" \2\2Z\\\5\34\17\2[Y\3\2\2\2[Z\3\2\2\2\\\17\3\2\2\2]^\7 \2\2^_\7\7\2\2"+
		"_`\5\62\32\2`\21\3\2\2\2ac\5\20\t\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3"+
		"\2\2\2e\23\3\2\2\2fd\3\2\2\2gh\7\13\2\2hi\5\24\13\2ij\7\35\2\2ju\3\2\2"+
		"\2ks\5\30\r\2ls\5\32\16\2ms\5\26\f\2no\7\13\2\2op\5,\27\2pq\7\35\2\2q"+
		"s\3\2\2\2rk\3\2\2\2rl\3\2\2\2rm\3\2\2\2rn\3\2\2\2su\3\2\2\2tg\3\2\2\2"+
		"tr\3\2\2\2ux\3\2\2\2vy\5\"\22\2wy\3\2\2\2xv\3\2\2\2xw\3\2\2\2y\25\3\2"+
		"\2\2z{\7 \2\2{\27\3\2\2\2|\177\7 \2\2}\177\5\34\17\2~|\3\2\2\2~}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0084\7\13\2\2\u0081\u0083\5\62\32\2\u0082"+
		"\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\7\35\2\2\u0088"+
		"\31\3\2\2\2\u0089\u008c\7 \2\2\u008a\u008c\5\34\17\2\u008b\u0089\3\2\2"+
		"\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7\6\2\2\u008e\u008f"+
		"\5\22\n\2\u008f\u0090\7\b\2\2\u0090\33\3\2\2\2\u0091\u0092\7 \2\2\u0092"+
		"\u0093\7\t\2\2\u0093\u0094\7 \2\2\u0094\35\3\2\2\2\u0095\u0096\7\r\2\2"+
		"\u0096\u0097\7 \2\2\u0097\37\3\2\2\2\u0098\u0099\5$\23\2\u0099\u009a\5"+
		"\62\32\2\u009a!\3\2\2\2\u009b\u009e\5 \21\2\u009c\u009e\5\36\20\2\u009d"+
		"\u009b\3\2\2\2\u009d\u009c\3\2\2\2\u009e#\3\2\2\2\u009f\u00a0\t\2\2\2"+
		"\u00a0%\3\2\2\2\u00a1\u00a2\5\24\13\2\u00a2\u00a3\7\16\2\2\u00a3\u00a4"+
		"\5.\30\2\u00a4\u00a5\5\16\b\2\u00a5\'\3\2\2\2\u00a6\u00a7\5\24\13\2\u00a7"+
		"\u00a8\7\23\2\2\u00a8\u00a9\5\24\13\2\u00a9)\3\2\2\2\u00aa\u00ab\5\24"+
		"\13\2\u00ab\u00ac\7\33\2\2\u00ac\u00ad\5\24\13\2\u00ad+\3\2\2\2\u00ae"+
		"\u00b2\7\20\2\2\u00af\u00b3\5&\24\2\u00b0\u00b3\5(\25\2\u00b1\u00b3\5"+
		"*\26\2\u00b2\u00af\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b5\7\5\2\2\u00b5\u00b6\5\24\13\2\u00b6\u00b9\7"+
		"\4\2\2\u00b7\u00ba\5,\27\2\u00b8\u00ba\5\24\13\2\u00b9\u00b7\3\2\2\2\u00b9"+
		"\u00b8\3\2\2\2\u00ba-\3\2\2\2\u00bb\u00be\7\31\2\2\u00bc\u00be\3\2\2\2"+
		"\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be/\3\2\2\2\u00bf\u00c2\7"+
		"\34\2\2\u00c0\u00c3\7 \2\2\u00c1\u00c3\5$\23\2\u00c2\u00c0\3\2\2\2\u00c2"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\5\n\6\2\u00c5\u00c6\7\24"+
		"\2\2\u00c6\u00c7\5\62\32\2\u00c7\61\3\2\2\2\u00c8\u00d1\5\64\33\2\u00c9"+
		"\u00d1\5\30\r\2\u00ca\u00d1\5,\27\2\u00cb\u00d1\5\24\13\2\u00cc\u00cd"+
		"\7\13\2\2\u00cd\u00ce\5\62\32\2\u00ce\u00cf\7\35\2\2\u00cf\u00d1\3\2\2"+
		"\2\u00d0\u00c8\3\2\2\2\u00d0\u00c9\3\2\2\2\u00d0\u00ca\3\2\2\2\u00d0\u00cb"+
		"\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\63\3\2\2\2\u00d2\u00d3\7\22\2\2\u00d3"+
		"\u00d7\7 \2\2\u00d4\u00d6\7 \2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2"+
		"\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7"+
		"\3\2\2\2\u00da\u00db\7\26\2\2\u00db\65\3\2\2\2\269>DMR[drtx~\u0084\u008b"+
		"\u009d\u00b2\u00b9\u00bd\u00c2\u00d0\u00d7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}