// Generated from src\grammaire\lg.g4 by ANTLR 4.0
package grammaire;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface lgListener extends ParseTreeListener {
	void enterTestEgalite(lgParser.TestEgaliteContext ctx);
	void exitTestEgalite(lgParser.TestEgaliteContext ctx);

	void enterNegation(lgParser.NegationContext ctx);
	void exitNegation(lgParser.NegationContext ctx);

	void enterChamps(lgParser.ChampsContext ctx);
	void exitChamps(lgParser.ChampsContext ctx);

	void enterCode(lgParser.CodeContext ctx);
	void exitCode(lgParser.CodeContext ctx);

	void enterAppel(lgParser.AppelContext ctx);
	void exitAppel(lgParser.AppelContext ctx);

	void enterOperationOuAcces(lgParser.OperationOuAccesContext ctx);
	void exitOperationOuAcces(lgParser.OperationOuAccesContext ctx);

	void enterTestType(lgParser.TestTypeContext ctx);
	void exitTestType(lgParser.TestTypeContext ctx);

	void enterType(lgParser.TypeContext ctx);
	void exitType(lgParser.TypeContext ctx);

	void enterLiteral(lgParser.LiteralContext ctx);
	void exitLiteral(lgParser.LiteralContext ctx);

	void enterSi(lgParser.SiContext ctx);
	void exitSi(lgParser.SiContext ctx);

	void enterTestDifference(lgParser.TestDifferenceContext ctx);
	void exitTestDifference(lgParser.TestDifferenceContext ctx);

	void enterChamp(lgParser.ChampContext ctx);
	void exitChamp(lgParser.ChampContext ctx);

	void enterElement(lgParser.ElementContext ctx);
	void exitElement(lgParser.ElementContext ctx);

	void enterTypeRef(lgParser.TypeRefContext ctx);
	void exitTypeRef(lgParser.TypeRefContext ctx);

	void enterVar(lgParser.VarContext ctx);
	void exitVar(lgParser.VarContext ctx);

	void enterModule(lgParser.ModuleContext ctx);
	void exitModule(lgParser.ModuleContext ctx);

	void enterAttribut(lgParser.AttributContext ctx);
	void exitAttribut(lgParser.AttributContext ctx);

	void enterAcces(lgParser.AccesContext ctx);
	void exitAcces(lgParser.AccesContext ctx);

	void enterObjet(lgParser.ObjetContext ctx);
	void exitObjet(lgParser.ObjetContext ctx);

	void enterAttributs(lgParser.AttributsContext ctx);
	void exitAttributs(lgParser.AttributsContext ctx);

	void enterId_externe(lgParser.Id_externeContext ctx);
	void exitId_externe(lgParser.Id_externeContext ctx);

	void enterFonction(lgParser.FonctionContext ctx);
	void exitFonction(lgParser.FonctionContext ctx);

	void enterTmpCode(lgParser.TmpCodeContext ctx);
	void exitTmpCode(lgParser.TmpCodeContext ctx);

	void enterOperateur(lgParser.OperateurContext ctx);
	void exitOperateur(lgParser.OperateurContext ctx);

	void enterSuperType(lgParser.SuperTypeContext ctx);
	void exitSuperType(lgParser.SuperTypeContext ctx);

	void enterOperation(lgParser.OperationContext ctx);
	void exitOperation(lgParser.OperationContext ctx);
}