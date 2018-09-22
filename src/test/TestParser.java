package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Acces;
import model.Appel;
import model.Literal;
import model.Module;
import model.Objet;

import model.TestType;
import model.VarRef;
import syntaxe.Parseur;

class TestParser {
	@Test
	void testFonction() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type zero {}\r\n"
				+ "type n:zero { zero:n} fonction + zero:a zero:b | si a est n alors n { n=  a.n+b  } sinon b  ");
		assertFalse(parseur.error);
	}
	@Test
	void testFonction2() {
		Parseur parseur = new Parseur();
		Module module = parseur
				.lireModule("type zero {}\r\n" + "type n:zero { zero:n} fonction + zero:a zero:b | a.n+b ");
		assertFalse(parseur.error);
	}
	


	@Test
	void testTypeVide() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a { } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.isEmpty());
		assertTrue(!module.types.get(0).estAbstrait);

	}

	@Test
	void testTypeAbstrait() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("abstrait type a { } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.isEmpty());
		assertTrue(module.types.get(0).estAbstrait);

	}

	@Test
	void testTypeAvecNonChiffre() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type 45 { } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("45"));
		assertTrue(module.types.get(0).vars.isEmpty());

	}

	@Test
	void testTypeUnChamp() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a {u:a } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 1);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module == null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));

	}

	@Test
	void testTypeUnChampDansAutreModule() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a {toto$u:a } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 1);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module.equals("toto"));
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));

	}

	@Test
	void testTypeDeuxChamps() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a {u:a m:b } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 2);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module == null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));

		assertTrue(module.types.get(0).vars.get(1).nom.equals("b"));
		assertTrue(module.types.get(0).vars.get(1).type.module == null);
		assertTrue(module.types.get(0).vars.get(1).type.nom.equals("m"));

	}

	@Test
	void testTypeDeuxChampsAvecHeritage() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a: h {u:a m:b } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 2);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module == null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));

		assertTrue(module.types.get(0).vars.get(1).nom.equals("b"));
		assertTrue(module.types.get(0).vars.get(1).type.module == null);
		assertTrue(module.types.get(0).vars.get(1).type.nom.equals("m"));

		assertTrue(module.types.get(0).superType != null);
		assertTrue(module.types.get(0).superType.nom.equals("h"));
		assertTrue(module.types.get(0).superType.module == null);

	}

	@Test
	void testFonctionIdentiteNomChiffre() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f o:5 | 5");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof VarRef);
		VarRef varRef = (VarRef) module.fonctions.get(0).expression;
		assertTrue(varRef.nom.equals("5"));
		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("5"));
	}

	@Test
	void testFonctionIdentite() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f o:x | x");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof VarRef);
		VarRef varRef = (VarRef) module.fonctions.get(0).expression;
		assertTrue(varRef.nom.equals("x"));
		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));
	}

	@Test
	void testFonctionQuiRetourneLiteral() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f o:x | [ x m  tot$m ]");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof Literal);
		Literal literal = (Literal) module.fonctions.get(0).expression;
		assertTrue(literal.mots.size() == 3);

		assertTrue(literal.mots.get(0).nom.equals("x"));
		assertTrue(literal.mots.get(1).nom.equals("m"));
		assertTrue(literal.mots.get(2).module.equals("tot"));
		assertTrue(literal.mots.get(2).nom.equals("m"));
	}

	@Test
	void testFonctionIdentiteParamAvecAutreModule() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | x");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof VarRef);
		VarRef varRef = (VarRef) module.fonctions.get(0).expression;
		assertTrue(varRef.nom.equals("x"));
		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));
	}

	@Test
	void testFonctionAvecTestType() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x est momo  alors q sinon m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestType);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestType testType = (TestType) module.fonctions.get(0).expression;
		assertTrue(testType.typeRef.module == null);
		assertTrue(testType.typeRef.nom.equals("momo"));
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof VarRef);
		VarRef varRef = (VarRef) testType.alors;
		assertTrue(varRef.nom.equals("q"));
		varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("m"));

	}

	@Test
	void testFonctionAvecTestTypeEtNegation() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x est !momo  alors q sinon m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestType);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestType testType = (TestType) module.fonctions.get(0).expression;
		assertTrue(testType.typeRef.module == null);
		assertTrue(testType.typeRef.nom.equals("momo"));
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof VarRef);
		VarRef varRef = (VarRef) testType.alors;
		assertTrue(varRef.nom.equals("m"));
		varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("q"));

	}

	@Test
	void testFonctionAvecTestTypeDansAutreModulEtNegation() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x est ! lulu$momo  alors q sinon m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestType);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestType testType = (TestType) module.fonctions.get(0).expression;
		assertTrue(testType.typeRef.module.equals("lulu"));
		assertTrue(testType.typeRef.nom.equals("momo"));
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof VarRef);
		VarRef varRef = (VarRef) testType.alors;
		assertTrue(varRef.nom.equals("m"));
		varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("q"));

	}
	@Test
	void testFonctionAPI() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x -> m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression == null);
		assertTrue(module.fonctions.get(0).typeRetour != null);
	}
	@Test
	void testFonctionAvecObjet() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | nini { m= a {} }");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof Objet);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		Objet objet = (Objet) module.fonctions.get(0).expression;
		assertTrue(objet.params.size() == 1);
		assertTrue(objet.type.nom.equals("nini"));
		assertTrue(objet.type.module == null);
		assertTrue(objet.params.get(0).nom.equals("m"));
		assertTrue(objet.params.get(0).expression instanceof Objet);
		Objet obj = (Objet) objet.params.get(0).expression;
		assertTrue(obj.type.nom.equals("a"));

	}

	@Test
	void testFonctionAvecObjetDansTestType() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x est lala alors nini { m= a {} } sinon o");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestType);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestType testType = (TestType) module.fonctions.get(0).expression;
		assertTrue(testType.typeRef.module == null);
		assertTrue(testType.typeRef.nom.equals("lala"));
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof Objet);
		Objet objet = (Objet) testType.alors;
		assertTrue(objet.type.nom.equals("nini"));

		VarRef varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("o"));
	}

	@Test
	void testFonctionOperateur() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction + toto$o:x lili:m | si x est lala alors nini { m= a {} } sinon o");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("+"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestType);

		assertTrue(module.fonctions.get(0).params.size() == 2);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		assertTrue(module.fonctions.get(0).params.get(1).type.nom.equals("lili"));
		assertTrue(module.fonctions.get(0).params.get(1).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(1).nom.equals("m"));

		TestType testType = (TestType) module.fonctions.get(0).expression;
		assertTrue(testType.typeRef.module == null);
		assertTrue(testType.typeRef.nom.equals("lala"));
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof Objet);
		Objet objet = (Objet) testType.alors;
		assertTrue(objet.type.nom.equals("nini"));

		VarRef varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("o"));
	}

	@Test
	void testAppelFonctionOperateur() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x lili:m | x.m > m + o");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);

		assertTrue(module.fonctions.get(0).params.size() == 2);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		assertTrue(module.fonctions.get(0).params.get(1).type.nom.equals("lili"));
		assertTrue(module.fonctions.get(0).params.get(1).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(1).nom.equals("m"));
		assertTrue(module.fonctions.get(0).expression instanceof Appel);
		Appel appel = (Appel) module.fonctions.get(0).expression;
		assertTrue(appel.nom.nom.equals(">"));
		assertTrue(appel.params.size() == 2);
		assertTrue(appel.params.get(1) instanceof Appel);
		Appel appel2 = (Appel) (appel.params.get(1));
		assertTrue(appel2.nom.nom.equals("+"));
		assertTrue(appel2.params.get(0) instanceof VarRef);
		VarRef varRef = (VarRef) appel2.params.get(0);
		assertTrue(varRef.nom.equals("m"));
		assertTrue(appel2.params.get(1) instanceof VarRef);
		varRef = (VarRef) appel2.params.get(1);
		assertTrue(varRef.nom.equals("o"));

	}

	@Test
	void testAppelFonctionOperateurAvecParenthese() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x lili:m | a > ( m + o )");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);

		assertTrue(module.fonctions.get(0).params.size() == 2);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		assertTrue(module.fonctions.get(0).params.get(1).type.nom.equals("lili"));
		assertTrue(module.fonctions.get(0).params.get(1).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(1).nom.equals("m"));
		assertTrue(module.fonctions.get(0).expression instanceof Appel);
		Appel appel = (Appel) module.fonctions.get(0).expression;
		assertTrue(appel.nom.nom.equals(">"));
		assertTrue(appel.params.size() == 2);
		assertTrue(appel.params.get(0) instanceof VarRef);
		assertTrue(appel.params.get(1) instanceof Appel);
		Appel appel2 = (Appel) (appel.params.get(1));
		assertTrue(appel2.nom.nom.equals("+"));

		assertTrue(appel2.params.get(0) instanceof VarRef);
		VarRef varRef = (VarRef) appel2.params.get(0);
		assertTrue(varRef.nom.equals("m"));
		assertTrue(appel2.params.get(1) instanceof VarRef);
		varRef = (VarRef) appel2.params.get(1);
		assertTrue(varRef.nom.equals("o"));

	}

	@Test
	void testAppelFonctionOperateurAvecParenthese2() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x lili:m | ( a > m ) + o ");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);

		assertTrue(module.fonctions.get(0).params.size() == 2);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		assertTrue(module.fonctions.get(0).params.get(1).type.nom.equals("lili"));
		assertTrue(module.fonctions.get(0).params.get(1).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(1).nom.equals("m"));
		assertTrue(module.fonctions.get(0).expression instanceof Appel);
		Appel appel = (Appel) module.fonctions.get(0).expression;
		assertTrue(appel.nom.nom.equals("+"));
		assertTrue(appel.params.size() == 2);
		assertTrue(appel.params.get(1) instanceof VarRef);
		assertTrue(appel.params.get(0) instanceof Appel);
		Appel appel2 = (Appel) (appel.params.get(0));
		assertTrue(appel2.nom.nom.equals(">"));

		assertTrue(appel2.params.get(0) instanceof VarRef);
		VarRef varRef = (VarRef) appel2.params.get(0);
		assertTrue(varRef.nom.equals("a"));
		assertTrue(appel2.params.get(1) instanceof VarRef);
		varRef = (VarRef) appel2.params.get(1);
		assertTrue(varRef.nom.equals("m"));

	}

	@Test
	void testAcces() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x lili:m | a.lolo ");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof Acces);
		Acces acces = (Acces) module.fonctions.get(0).expression;
		assertTrue(acces.nom.equals("lolo"));
		assertTrue(acces.cible instanceof VarRef);

	}

	@Test
	void testAccesEnCascade() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f t:x  | (a.lolo).momo ");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof Acces);
		Acces acces = (Acces) module.fonctions.get(0).expression;
		assertTrue(acces.nom.equals("momo"));
		assertTrue(acces.cible instanceof Acces);
		acces = (Acces) acces.cible;
		assertTrue(acces.nom.equals("lolo"));

	}

	@Test
	void testPositionDansSouce() {
		Parseur parseur = new Parseur();
		String source = "fonction f t:x  | mo(x)";
		Module module = parseur.lireModule(source);
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);

		Appel acces = (Appel) module.fonctions.get(0).expression;
		String n = source.substring(acces.nom.debut, acces.nom.fin + 1);
		assertTrue(n.equals("mo"));

	}

	@Test
	void testPositionOperateurDansSouce() {
		Parseur parseur = new Parseur();
		String source = "fonction f t:x  | x * x";
		Module module = parseur.lireModule(source);
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);

		Appel acces = (Appel) module.fonctions.get(0).expression;
		String n = source.substring(acces.nom.debut, acces.nom.fin + 1);
		assertTrue(n.equals("*"));

	}

}
