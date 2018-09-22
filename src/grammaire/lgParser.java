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
		T__29=1, T__28=2, T__27=3, T__26=4, T__25=5, T__24=6, T__23=7, T__22=8, 
		T__21=9, T__20=10, T__19=11, T__18=12, T__17=13, T__16=14, T__15=15, T__14=16, 
		T__13=17, T__12=18, T__11=19, T__10=20, T__9=21, T__8=22, T__7=23, T__6=24, 
		T__5=25, T__4=26, T__3=27, T__2=28, T__1=29, T__0=30, ID=31, WS=32;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'sinon'", "'alors'", "'{'", "'='", "'}'", "'$'", 
		"'&'", "'('", "'*'", "'.'", "'abstrait'", "'est'", "'->'", "'si'", "':'", 
		"'['", "'=='", "'|'", "'<'", "']'", "'>'", "'type'", "'!'", "'=>'", "'<>'", 
		"'fonction'", "')'", "'+'", "'-'", "ID", "WS"
	};
	public static final int
		RULE_module = 0, RULE_element = 1, RULE_type = 2, RULE_estAbstrait = 3, 
		RULE_superType = 4, RULE_champs = 5, RULE_champ = 6, RULE_typeRef = 7, 
		RULE_attribut = 8, RULE_attributs = 9, RULE_code = 10, RULE_var = 11, 
		RULE_appel = 12, RULE_objet = 13, RULE_id_externe = 14, RULE_acces = 15, 
		RULE_operation = 16, RULE_operationOuAcces = 17, RULE_operateur = 18, 
		RULE_testType = 19, RULE_testEgalite = 20, RULE_testDifference = 21, RULE_si = 22, 
		RULE_negation = 23, RULE_fonction = 24, RULE_tmpCode = 25, RULE_ref = 26, 
		RULE_literal = 27;
	public static final String[] ruleNames = {
		"module", "element", "type", "estAbstrait", "superType", "champs", "champ", 
		"typeRef", "attribut", "attributs", "code", "var", "appel", "objet", "id_externe", 
		"acces", "operation", "operationOuAcces", "operateur", "testType", "testEgalite", 
		"testDifference", "si", "negation", "fonction", "tmpCode", "ref", "literal"
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
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 23) | (1L << 27))) != 0)) {
				{
				{
				setState(56); element();
				}
				}
				setState(61);
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
			setState(64);
			switch (_input.LA(1)) {
			case 12:
			case 23:
				enterOuterAlt(_localctx, 1);
				{
				setState(62); type();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 2);
				{
				setState(63); fonction();
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
			setState(66); estAbstrait();
			setState(67); match(23);
			setState(68); match(ID);
			setState(71);
			switch (_input.LA(1)) {
			case 16:
				{
				setState(69); superType();
				}
				break;
			case 4:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(73); match(4);
			setState(74); champs();
			setState(75); match(6);
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
		enterRule(_localctx, 6, RULE_estAbstrait);
		try {
			setState(79);
			switch (_input.LA(1)) {
			case 12:
				enterOuterAlt(_localctx, 1);
				{
				setState(77); match(12);
				}
				break;
			case 23:
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
		enterRule(_localctx, 8, RULE_superType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(81); match(16);
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(82); match(ID);
				}
				break;

			case 2:
				{
				setState(83); id_externe();
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
		enterRule(_localctx, 10, RULE_champs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(86); champ();
				}
				}
				setState(91);
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
		enterRule(_localctx, 12, RULE_champ);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); typeRef();
			setState(93); match(16);
			setState(94); match(ID);
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
		enterRule(_localctx, 14, RULE_typeRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(96); match(ID);
				}
				break;

			case 2:
				{
				setState(97); id_externe();
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
		enterRule(_localctx, 16, RULE_attribut);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(ID);
			setState(101); match(5);
			setState(102); tmpCode();
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
		enterRule(_localctx, 18, RULE_attributs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(104); attribut();
				}
				}
				setState(109);
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
		enterRule(_localctx, 20, RULE_code);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				{
				setState(110); match(9);
				setState(111); code();
				setState(112); match(28);
				}
				}
				break;

			case 2:
				{
				setState(121);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(114); appel();
					}
					break;

				case 2:
					{
					setState(115); objet();
					}
					break;

				case 3:
					{
					setState(116); var();
					}
					break;

				case 4:
					{
					{
					setState(117); match(9);
					{
					setState(118); si();
					}
					setState(119); match(28);
					}
					}
					break;
				}
				}
				break;
			}
			{
			setState(128);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(125); operationOuAcces();
					}
					} 
				}
				setState(130);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 22, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); match(ID);
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
		enterRule(_localctx, 24, RULE_appel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(133); match(ID);
				}
				break;

			case 2:
				{
				setState(134); id_externe();
				}
				break;
			}
			setState(137); match(9);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 15) | (1L << 17) | (1L << ID))) != 0)) {
				{
				{
				setState(138); tmpCode();
				}
				}
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(144); match(28);
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
		enterRule(_localctx, 26, RULE_objet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(146); match(ID);
				}
				break;

			case 2:
				{
				setState(147); id_externe();
				}
				break;
			}
			setState(150); match(4);
			setState(151); attributs();
			setState(152); match(6);
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
		enterRule(_localctx, 28, RULE_id_externe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(ID);
			setState(155); match(7);
			setState(156); match(ID);
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
		enterRule(_localctx, 30, RULE_acces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(11);
			setState(159); match(ID);
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
		enterRule(_localctx, 32, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); operateur();
			setState(162); tmpCode();
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
		enterRule(_localctx, 34, RULE_operationOuAcces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			switch (_input.LA(1)) {
			case 1:
			case 5:
			case 8:
			case 10:
			case 14:
			case 18:
			case 19:
			case 20:
			case 22:
			case 25:
			case 29:
			case 30:
				{
				setState(164); operation();
				}
				break;
			case 11:
				{
				setState(165); acces();
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
		enterRule(_localctx, 36, RULE_operateur);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 8) | (1L << 10) | (1L << 14) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 22) | (1L << 25) | (1L << 29) | (1L << 30))) != 0)) ) {
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
		enterRule(_localctx, 38, RULE_testType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170); code();
			setState(171); match(13);
			setState(172); negation();
			setState(173); typeRef();
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
		enterRule(_localctx, 40, RULE_testEgalite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); code();
			setState(176); match(18);
			setState(177); code();
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
		enterRule(_localctx, 42, RULE_testDifference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179); code();
			setState(180); match(26);
			setState(181); code();
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
		enterRule(_localctx, 44, RULE_si);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); match(15);
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(184); testType();
				}
				break;

			case 2:
				{
				setState(185); testEgalite();
				}
				break;

			case 3:
				{
				setState(186); testDifference();
				}
				break;
			}
			setState(189); match(3);
			setState(190); code();
			setState(191); match(2);
			setState(194);
			switch (_input.LA(1)) {
			case 15:
				{
				setState(192); si();
				}
				break;
			case 9:
			case ID:
				{
				setState(193); code();
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
		enterRule(_localctx, 46, RULE_negation);
		try {
			setState(198);
			switch (_input.LA(1)) {
			case 24:
				enterOuterAlt(_localctx, 1);
				{
				setState(196); match(24);
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
		enterRule(_localctx, 48, RULE_fonction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(27);
			setState(203);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(201); match(ID);
				}
				break;
			case 1:
			case 5:
			case 8:
			case 10:
			case 14:
			case 18:
			case 19:
			case 20:
			case 22:
			case 25:
			case 29:
			case 30:
				{
				setState(202); operateur();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(205); champs();
			setState(210);
			switch (_input.LA(1)) {
			case 19:
				{
				{
				setState(206); match(19);
				setState(207); tmpCode();
				}
				}
				break;
			case 14:
				{
				{
				setState(208); match(14);
				setState(209); typeRef();
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
		enterRule(_localctx, 50, RULE_tmpCode);
		try {
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); appel();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); si();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); code();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(216); match(9);
				setState(217); tmpCode();
				setState(218); match(28);
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
		enterRule(_localctx, 52, RULE_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(222); match(ID);
				}
				break;

			case 2:
				{
				setState(223); id_externe();
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
		enterRule(_localctx, 54, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226); match(17);
			setState(227); ref();
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(228); ref();
				}
				}
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(234); match(21);
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
		"\2\3\"\u00ef\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\7\2<"+
		"\n\2\f\2\16\2?\13\2\3\3\3\3\5\3C\n\3\3\4\3\4\3\4\3\4\3\4\5\4J\n\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\5\5R\n\5\3\6\3\6\3\6\5\6W\n\6\3\7\7\7Z\n\7\f\7\16"+
		"\7]\13\7\3\b\3\b\3\b\3\b\3\t\3\t\5\te\n\t\3\n\3\n\3\n\3\n\3\13\7\13l\n"+
		"\13\f\13\16\13o\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f"+
		"|\n\f\5\f~\n\f\3\f\7\f\u0081\n\f\f\f\16\f\u0084\13\f\3\r\3\r\3\16\3\16"+
		"\5\16\u008a\n\16\3\16\3\16\7\16\u008e\n\16\f\16\16\16\u0091\13\16\3\16"+
		"\3\16\3\17\3\17\5\17\u0097\n\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\5\23\u00a9\n\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\5\30\u00be\n\30\3\30\3\30\3\30\3\30\3\30\5\30\u00c5\n\30\3"+
		"\31\3\31\5\31\u00c9\n\31\3\32\3\32\3\32\5\32\u00ce\n\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00d5\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u00df\n\33\3\34\3\34\5\34\u00e3\n\34\3\35\3\35\3\35\7\35\u00e8\n\35\f"+
		"\35\16\35\u00eb\13\35\3\35\3\35\3\35\2\36\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668\2\3\13\3\3\7\7\n\n\f\f\20\20\24\26\30"+
		"\30\33\33\37 \u00ef\2=\3\2\2\2\4B\3\2\2\2\6D\3\2\2\2\bQ\3\2\2\2\nS\3\2"+
		"\2\2\f[\3\2\2\2\16^\3\2\2\2\20d\3\2\2\2\22f\3\2\2\2\24m\3\2\2\2\26}\3"+
		"\2\2\2\30\u0085\3\2\2\2\32\u0089\3\2\2\2\34\u0096\3\2\2\2\36\u009c\3\2"+
		"\2\2 \u00a0\3\2\2\2\"\u00a3\3\2\2\2$\u00a8\3\2\2\2&\u00aa\3\2\2\2(\u00ac"+
		"\3\2\2\2*\u00b1\3\2\2\2,\u00b5\3\2\2\2.\u00b9\3\2\2\2\60\u00c8\3\2\2\2"+
		"\62\u00ca\3\2\2\2\64\u00de\3\2\2\2\66\u00e2\3\2\2\28\u00e4\3\2\2\2:<\5"+
		"\4\3\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\3\3\2\2\2?=\3\2\2\2@C"+
		"\5\6\4\2AC\5\62\32\2B@\3\2\2\2BA\3\2\2\2C\5\3\2\2\2DE\5\b\5\2EF\7\31\2"+
		"\2FI\7!\2\2GJ\5\n\6\2HJ\3\2\2\2IG\3\2\2\2IH\3\2\2\2JK\3\2\2\2KL\7\6\2"+
		"\2LM\5\f\7\2MN\7\b\2\2N\7\3\2\2\2OR\7\16\2\2PR\3\2\2\2QO\3\2\2\2QP\3\2"+
		"\2\2R\t\3\2\2\2SV\7\22\2\2TW\7!\2\2UW\5\36\20\2VT\3\2\2\2VU\3\2\2\2W\13"+
		"\3\2\2\2XZ\5\16\b\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\r\3\2\2"+
		"\2][\3\2\2\2^_\5\20\t\2_`\7\22\2\2`a\7!\2\2a\17\3\2\2\2be\7!\2\2ce\5\36"+
		"\20\2db\3\2\2\2dc\3\2\2\2e\21\3\2\2\2fg\7!\2\2gh\7\7\2\2hi\5\64\33\2i"+
		"\23\3\2\2\2jl\5\22\n\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\25\3\2"+
		"\2\2om\3\2\2\2pq\7\13\2\2qr\5\26\f\2rs\7\36\2\2s~\3\2\2\2t|\5\32\16\2"+
		"u|\5\34\17\2v|\5\30\r\2wx\7\13\2\2xy\5.\30\2yz\7\36\2\2z|\3\2\2\2{t\3"+
		"\2\2\2{u\3\2\2\2{v\3\2\2\2{w\3\2\2\2|~\3\2\2\2}p\3\2\2\2}{\3\2\2\2~\u0082"+
		"\3\2\2\2\177\u0081\5$\23\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\27\3\2\2\2\u0084\u0082\3\2\2"+
		"\2\u0085\u0086\7!\2\2\u0086\31\3\2\2\2\u0087\u008a\7!\2\2\u0088\u008a"+
		"\5\36\20\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008f\7\13\2\2\u008c\u008e\5\64\33\2\u008d\u008c\3\2\2\2\u008e"+
		"\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0092\u0093\7\36\2\2\u0093\33\3\2\2\2\u0094\u0097"+
		"\7!\2\2\u0095\u0097\5\36\20\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2"+
		"\u0097\u0098\3\2\2\2\u0098\u0099\7\6\2\2\u0099\u009a\5\24\13\2\u009a\u009b"+
		"\7\b\2\2\u009b\35\3\2\2\2\u009c\u009d\7!\2\2\u009d\u009e\7\t\2\2\u009e"+
		"\u009f\7!\2\2\u009f\37\3\2\2\2\u00a0\u00a1\7\r\2\2\u00a1\u00a2\7!\2\2"+
		"\u00a2!\3\2\2\2\u00a3\u00a4\5&\24\2\u00a4\u00a5\5\64\33\2\u00a5#\3\2\2"+
		"\2\u00a6\u00a9\5\"\22\2\u00a7\u00a9\5 \21\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a7\3\2\2\2\u00a9%\3\2\2\2\u00aa\u00ab\t\2\2\2\u00ab\'\3\2\2\2\u00ac"+
		"\u00ad\5\26\f\2\u00ad\u00ae\7\17\2\2\u00ae\u00af\5\60\31\2\u00af\u00b0"+
		"\5\20\t\2\u00b0)\3\2\2\2\u00b1\u00b2\5\26\f\2\u00b2\u00b3\7\24\2\2\u00b3"+
		"\u00b4\5\26\f\2\u00b4+\3\2\2\2\u00b5\u00b6\5\26\f\2\u00b6\u00b7\7\34\2"+
		"\2\u00b7\u00b8\5\26\f\2\u00b8-\3\2\2\2\u00b9\u00bd\7\21\2\2\u00ba\u00be"+
		"\5(\25\2\u00bb\u00be\5*\26\2\u00bc\u00be\5,\27\2\u00bd\u00ba\3\2\2\2\u00bd"+
		"\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7\5"+
		"\2\2\u00c0\u00c1\5\26\f\2\u00c1\u00c4\7\4\2\2\u00c2\u00c5\5.\30\2\u00c3"+
		"\u00c5\5\26\f\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5/\3\2\2\2"+
		"\u00c6\u00c9\7\32\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7"+
		"\3\2\2\2\u00c9\61\3\2\2\2\u00ca\u00cd\7\35\2\2\u00cb\u00ce\7!\2\2\u00cc"+
		"\u00ce\5&\24\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d4\5\f\7\2\u00d0\u00d1\7\25\2\2\u00d1\u00d5\5\64\33\2\u00d2"+
		"\u00d3\7\20\2\2\u00d3\u00d5\5\20\t\2\u00d4\u00d0\3\2\2\2\u00d4\u00d2\3"+
		"\2\2\2\u00d5\63\3\2\2\2\u00d6\u00df\58\35\2\u00d7\u00df\5\32\16\2\u00d8"+
		"\u00df\5.\30\2\u00d9\u00df\5\26\f\2\u00da\u00db\7\13\2\2\u00db\u00dc\5"+
		"\64\33\2\u00dc\u00dd\7\36\2\2\u00dd\u00df\3\2\2\2\u00de\u00d6\3\2\2\2"+
		"\u00de\u00d7\3\2\2\2\u00de\u00d8\3\2\2\2\u00de\u00d9\3\2\2\2\u00de\u00da"+
		"\3\2\2\2\u00df\65\3\2\2\2\u00e0\u00e3\7!\2\2\u00e1\u00e3\5\36\20\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\67\3\2\2\2\u00e4\u00e5\7\23\2"+
		"\2\u00e5\u00e9\5\66\34\2\u00e6\u00e8\5\66\34\2\u00e7\u00e6\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7\27\2\2\u00ed9\3\2\2\2\31=BIQV["+
		"dm{}\u0082\u0089\u008f\u0096\u00a8\u00bd\u00c4\u00c8\u00cd\u00d4\u00de"+
		"\u00e2\u00e9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}