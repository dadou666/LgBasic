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
		T__26=1, T__25=2, T__24=3, T__23=4, T__22=5, T__21=6, T__20=7, T__19=8, 
		T__18=9, T__17=10, T__16=11, T__15=12, T__14=13, T__13=14, T__12=15, T__11=16, 
		T__10=17, T__9=18, T__8=19, T__7=20, T__6=21, T__5=22, T__4=23, T__3=24, 
		T__2=25, T__1=26, T__0=27, ENTIER=28, ENTIER_EXTERNE=29, ID=30, WS=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'!='", "'{'", "'='", "'}'", "'if'", "'$'", "'&'", 
		"'('", "'is'", "'*'", "'.'", "'->'", "':'", "'=='", "'|'", "'<'", "'>'", 
		"'type'", "'!'", "'=>'", "'else'", "'fonction'", "')'", "'then'", "'+'", 
		"'-'", "ENTIER", "ENTIER_EXTERNE", "ID", "WS"
	};
	public static final int
		RULE_module = 0, RULE_element = 1, RULE_type = 2, RULE_superType = 3, 
		RULE_champs = 4, RULE_champ = 5, RULE_typeRef = 6, RULE_attribut = 7, 
		RULE_attributs = 8, RULE_code = 9, RULE_var = 10, RULE_appel = 11, RULE_objet = 12, 
		RULE_id_externe = 13, RULE_acces = 14, RULE_operation = 15, RULE_operationOuAcces = 16, 
		RULE_operateur = 17, RULE_testType = 18, RULE_testEgalite = 19, RULE_testDifference = 20, 
		RULE_si = 21, RULE_negation = 22, RULE_fonction = 23, RULE_tmpCode = 24;
	public static final String[] ruleNames = {
		"module", "element", "type", "superType", "champs", "champ", "typeRef", 
		"attribut", "attributs", "code", "var", "appel", "objet", "id_externe", 
		"acces", "operation", "operationOuAcces", "operateur", "testType", "testEgalite", 
		"testDifference", "si", "negation", "fonction", "tmpCode"
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
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==19 || _la==23) {
				{
				{
				setState(50); element();
				}
				}
				setState(55);
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
			setState(58);
			switch (_input.LA(1)) {
			case 19:
				enterOuterAlt(_localctx, 1);
				{
				setState(56); type();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 2);
				{
				setState(57); fonction();
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
			setState(60); match(19);
			setState(61); match(ID);
			setState(64);
			switch (_input.LA(1)) {
			case 14:
				{
				setState(62); superType();
				}
				break;
			case 3:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(66); match(3);
			setState(67); champs();
			setState(68); match(5);
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
			setState(70); match(14);
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(71); match(ID);
				}
				break;

			case 2:
				{
				setState(72); id_externe();
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
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(75); champ();
				}
				}
				setState(80);
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
			setState(81); typeRef();
			setState(82); match(14);
			setState(83); match(ID);
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
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(85); match(ID);
				}
				break;

			case 2:
				{
				setState(86); id_externe();
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
			setState(89); match(ID);
			setState(90); match(4);
			setState(91); tmpCode();
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
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(93); attribut();
				}
				}
				setState(98);
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
		enterRule(_localctx, 18, RULE_code);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				{
				setState(99); match(9);
				setState(100); code();
				setState(101); match(24);
				}
				}
				break;

			case 2:
				{
				setState(110);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(103); appel();
					}
					break;

				case 2:
					{
					setState(104); objet();
					}
					break;

				case 3:
					{
					setState(105); var();
					}
					break;

				case 4:
					{
					{
					setState(106); match(9);
					{
					setState(107); si();
					}
					setState(108); match(24);
					}
					}
					break;
				}
				}
				break;
			}
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(114); operationOuAcces();
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public Id_externeContext id_externe() {
			return getRuleContext(Id_externeContext.class,0);
		}
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
			setState(122);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(120); match(ID);
				}
				break;

			case 2:
				{
				setState(121); id_externe();
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
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(124); match(ID);
				}
				break;

			case 2:
				{
				setState(125); id_externe();
				}
				break;
			}
			setState(128); match(9);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 9) | (1L << ID))) != 0)) {
				{
				{
				setState(129); tmpCode();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135); match(24);
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
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(137); match(ID);
				}
				break;

			case 2:
				{
				setState(138); id_externe();
				}
				break;
			}
			setState(141); match(3);
			setState(142); attributs();
			setState(143); match(5);
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
			setState(145); match(ID);
			setState(146); match(7);
			setState(147); match(ID);
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
			setState(149); match(12);
			setState(150); match(ID);
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
			setState(152); operateur();
			setState(153); tmpCode();
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
			setState(157);
			switch (_input.LA(1)) {
			case 12:
				{
				setState(155); acces();
				}
				break;
			case 1:
			case 4:
			case 8:
			case 11:
			case 13:
			case 16:
			case 17:
			case 18:
			case 21:
			case 26:
			case 27:
				{
				setState(156); operation();
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
			setState(159);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 4) | (1L << 8) | (1L << 11) | (1L << 13) | (1L << 16) | (1L << 17) | (1L << 18) | (1L << 21) | (1L << 26) | (1L << 27))) != 0)) ) {
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
			setState(161); code();
			setState(162); match(10);
			setState(163); negation();
			setState(164); typeRef();
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
			setState(166); code();
			setState(167); match(15);
			setState(168); code();
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
			setState(170); code();
			setState(171); match(2);
			setState(172); code();
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
			setState(174); match(6);
			setState(178);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(175); testType();
				}
				break;

			case 2:
				{
				setState(176); testEgalite();
				}
				break;

			case 3:
				{
				setState(177); testDifference();
				}
				break;
			}
			setState(180); match(25);
			setState(181); code();
			setState(182); match(22);
			setState(185);
			switch (_input.LA(1)) {
			case 6:
				{
				setState(183); si();
				}
				break;
			case 9:
			case ID:
				{
				setState(184); code();
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
			setState(189);
			switch (_input.LA(1)) {
			case 20:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); match(20);
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
			setState(191); match(23);
			setState(194);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(192); match(ID);
				}
				break;
			case 1:
			case 4:
			case 8:
			case 11:
			case 13:
			case 16:
			case 17:
			case 18:
			case 21:
			case 26:
			case 27:
				{
				setState(193); operateur();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(196); champs();
			setState(197); match(16);
			setState(198); tmpCode();
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
			setState(207);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200); appel();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); si();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(202); code();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); match(9);
				setState(204); tmpCode();
				setState(205); match(24);
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

	public static final String _serializedATN =
		"\2\3!\u00d4\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\3\2\7\2\66\n\2\f\2\16\29\13\2\3\3\3\3\5"+
		"\3=\n\3\3\4\3\4\3\4\3\4\5\4C\n\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5L\n\5"+
		"\3\6\7\6O\n\6\f\6\16\6R\13\6\3\7\3\7\3\7\3\7\3\b\3\b\5\bZ\n\b\3\t\3\t"+
		"\3\t\3\t\3\n\7\na\n\n\f\n\16\nd\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13q\n\13\5\13s\n\13\3\13\7\13v\n\13\f\13\16\13"+
		"y\13\13\3\f\3\f\5\f}\n\f\3\r\3\r\5\r\u0081\n\r\3\r\3\r\7\r\u0085\n\r\f"+
		"\r\16\r\u0088\13\r\3\r\3\r\3\16\3\16\5\16\u008e\n\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\5\22\u00a0"+
		"\n\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\5\27\u00b5\n\27\3\27\3\27\3\27\3\27\3\27"+
		"\5\27\u00bc\n\27\3\30\3\30\5\30\u00c0\n\30\3\31\3\31\3\31\5\31\u00c5\n"+
		"\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00d2"+
		"\n\32\3\32\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\2\3\n\3\3\6\6\n\n\r\r\17\17\22\24\27\27\34\35\u00d3\2\67\3\2\2\2\4<\3"+
		"\2\2\2\6>\3\2\2\2\bH\3\2\2\2\nP\3\2\2\2\fS\3\2\2\2\16Y\3\2\2\2\20[\3\2"+
		"\2\2\22b\3\2\2\2\24r\3\2\2\2\26|\3\2\2\2\30\u0080\3\2\2\2\32\u008d\3\2"+
		"\2\2\34\u0093\3\2\2\2\36\u0097\3\2\2\2 \u009a\3\2\2\2\"\u009f\3\2\2\2"+
		"$\u00a1\3\2\2\2&\u00a3\3\2\2\2(\u00a8\3\2\2\2*\u00ac\3\2\2\2,\u00b0\3"+
		"\2\2\2.\u00bf\3\2\2\2\60\u00c1\3\2\2\2\62\u00d1\3\2\2\2\64\66\5\4\3\2"+
		"\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\3\3\2\2\29\67\3"+
		"\2\2\2:=\5\6\4\2;=\5\60\31\2<:\3\2\2\2<;\3\2\2\2=\5\3\2\2\2>?\7\25\2\2"+
		"?B\7 \2\2@C\5\b\5\2AC\3\2\2\2B@\3\2\2\2BA\3\2\2\2CD\3\2\2\2DE\7\5\2\2"+
		"EF\5\n\6\2FG\7\7\2\2G\7\3\2\2\2HK\7\20\2\2IL\7 \2\2JL\5\34\17\2KI\3\2"+
		"\2\2KJ\3\2\2\2L\t\3\2\2\2MO\5\f\7\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3"+
		"\2\2\2Q\13\3\2\2\2RP\3\2\2\2ST\5\16\b\2TU\7\20\2\2UV\7 \2\2V\r\3\2\2\2"+
		"WZ\7 \2\2XZ\5\34\17\2YW\3\2\2\2YX\3\2\2\2Z\17\3\2\2\2[\\\7 \2\2\\]\7\6"+
		"\2\2]^\5\62\32\2^\21\3\2\2\2_a\5\20\t\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2"+
		"bc\3\2\2\2c\23\3\2\2\2db\3\2\2\2ef\7\13\2\2fg\5\24\13\2gh\7\32\2\2hs\3"+
		"\2\2\2iq\5\30\r\2jq\5\32\16\2kq\5\26\f\2lm\7\13\2\2mn\5,\27\2no\7\32\2"+
		"\2oq\3\2\2\2pi\3\2\2\2pj\3\2\2\2pk\3\2\2\2pl\3\2\2\2qs\3\2\2\2re\3\2\2"+
		"\2rp\3\2\2\2sw\3\2\2\2tv\5\"\22\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2"+
		"\2\2x\25\3\2\2\2yw\3\2\2\2z}\7 \2\2{}\5\34\17\2|z\3\2\2\2|{\3\2\2\2}\27"+
		"\3\2\2\2~\u0081\7 \2\2\177\u0081\5\34\17\2\u0080~\3\2\2\2\u0080\177\3"+
		"\2\2\2\u0081\u0082\3\2\2\2\u0082\u0086\7\13\2\2\u0083\u0085\5\62\32\2"+
		"\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\32\2\2"+
		"\u008a\31\3\2\2\2\u008b\u008e\7 \2\2\u008c\u008e\5\34\17\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\5\2\2\u0090"+
		"\u0091\5\22\n\2\u0091\u0092\7\7\2\2\u0092\33\3\2\2\2\u0093\u0094\7 \2"+
		"\2\u0094\u0095\7\t\2\2\u0095\u0096\7 \2\2\u0096\35\3\2\2\2\u0097\u0098"+
		"\7\16\2\2\u0098\u0099\7 \2\2\u0099\37\3\2\2\2\u009a\u009b\5$\23\2\u009b"+
		"\u009c\5\62\32\2\u009c!\3\2\2\2\u009d\u00a0\5\36\20\2\u009e\u00a0\5 \21"+
		"\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0#\3\2\2\2\u00a1\u00a2"+
		"\t\2\2\2\u00a2%\3\2\2\2\u00a3\u00a4\5\24\13\2\u00a4\u00a5\7\f\2\2\u00a5"+
		"\u00a6\5.\30\2\u00a6\u00a7\5\16\b\2\u00a7\'\3\2\2\2\u00a8\u00a9\5\24\13"+
		"\2\u00a9\u00aa\7\21\2\2\u00aa\u00ab\5\24\13\2\u00ab)\3\2\2\2\u00ac\u00ad"+
		"\5\24\13\2\u00ad\u00ae\7\4\2\2\u00ae\u00af\5\24\13\2\u00af+\3\2\2\2\u00b0"+
		"\u00b4\7\b\2\2\u00b1\u00b5\5&\24\2\u00b2\u00b5\5(\25\2\u00b3\u00b5\5*"+
		"\26\2\u00b4\u00b1\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b7\7\33\2\2\u00b7\u00b8\5\24\13\2\u00b8\u00bb"+
		"\7\30\2\2\u00b9\u00bc\5,\27\2\u00ba\u00bc\5\24\13\2\u00bb\u00b9\3\2\2"+
		"\2\u00bb\u00ba\3\2\2\2\u00bc-\3\2\2\2\u00bd\u00c0\7\26\2\2\u00be\u00c0"+
		"\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0/\3\2\2\2\u00c1"+
		"\u00c4\7\31\2\2\u00c2\u00c5\7 \2\2\u00c3\u00c5\5$\23\2\u00c4\u00c2\3\2"+
		"\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\5\n\6\2\u00c7"+
		"\u00c8\7\22\2\2\u00c8\u00c9\5\62\32\2\u00c9\61\3\2\2\2\u00ca\u00d2\5\30"+
		"\r\2\u00cb\u00d2\5,\27\2\u00cc\u00d2\5\24\13\2\u00cd\u00ce\7\13\2\2\u00ce"+
		"\u00cf\5\62\32\2\u00cf\u00d0\7\32\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00ca"+
		"\3\2\2\2\u00d1\u00cb\3\2\2\2\u00d1\u00cc\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d2"+
		"\63\3\2\2\2\26\67<BKPYbprw|\u0080\u0086\u008d\u009f\u00b4\u00bb\u00bf"+
		"\u00c4\u00d1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}