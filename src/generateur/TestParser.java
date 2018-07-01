package generateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Module;
import model.Objet;
import model.TestEgalite;
import model.TestType;
import model.VarRef;

class TestParser {

	@Test
	void testTypeVide() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a { } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1);
		assertTrue(module.types.get(0).nom.equals("a"));
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
	void testFonctionAvecTestEgalite() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x == m  alors q sinon m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestEgalite);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestEgalite testType = (TestEgalite) module.fonctions.get(0).expression;
		assertTrue(testType.a instanceof VarRef);
		assertTrue(testType.b instanceof VarRef);
		VarRef a = (VarRef) testType.a;
		VarRef b = (VarRef) testType.b;
		
		assertTrue(a.nom.equals("x"));
		assertTrue(b.nom.equals("m"));
		
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof VarRef);
		VarRef varRef = (VarRef) testType.alors;
		assertTrue(varRef.nom.equals("q"));
		varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("m"));

	}
	
	@Test
	void testFonctionAvecTestDifference() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | si x <> m  alors q sinon m");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1);
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof TestEgalite);

		assertTrue(module.fonctions.get(0).params.size() == 1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module.equals("toto"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));

		TestEgalite testType = (TestEgalite) module.fonctions.get(0).expression;
		assertTrue(testType.a instanceof VarRef);
		assertTrue(testType.b instanceof VarRef);
		VarRef a = (VarRef) testType.a;
		VarRef b = (VarRef) testType.b;
		
		assertTrue(a.nom.equals("x"));
		assertTrue(b.nom.equals("m"));
		
		assertTrue(testType.alors != null);
		assertTrue(testType.alors instanceof VarRef);
		VarRef varRef = (VarRef) testType.alors;
		assertTrue(varRef.nom.equals("m"));
		varRef = (VarRef) testType.sinon;
		assertTrue(varRef.nom.equals("q"));

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
		Objet obj =  (Objet) objet.params.get(0).expression;
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
	
	

}
