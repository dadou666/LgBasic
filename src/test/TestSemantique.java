package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.Acces;
import model.Module;
import model.ObjetParam;
import model.Univers;
import model.Var;
import model.VarRef;
import semantique.AccesChampInexistant;
import semantique.DoublonChampType;
import semantique.DoublonNomFonction;
import semantique.DoublonObjetParam;
import semantique.DoublonParamFonction;
import semantique.Erreur;
import semantique.ErreurTypeNonArbre;
import semantique.FonctionInexistante;
import semantique.NomTypeReserve;
import semantique.NombreParametreInvalide;
import semantique.TypeExpressionInvalideDansObjet;
import semantique.TypeInexistant;
import semantique.Verificateur;
import semantique.VerificationFonction;
import syntaxe.Parseur;

class TestSemantique {

	@Test
	void test() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t {}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t { a:m }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		VarRef v = (VarRef) ti.expression;
		assertTrue(v.nom.equals("m"));

	}

	@Test
	void testDoublonChamp() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u {} type t { u:m u:m}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof DoublonChampType);
		DoublonChampType erreur = (DoublonChampType) verif.erreurs.get(0);
		assertTrue(erreur.nom.equals("m"));

	}

	@Test
	void testDoublonChampAvecHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u {symbol:a } type t :u  { symbol:a}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof DoublonChampType);
		DoublonChampType erreur = (DoublonChampType) verif.erreurs.get(0);
		assertTrue(erreur.nom.equals("a"));

	}

	@Test
	void testSuperTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t : m { }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nom.equals("m1$m"));

	}

	@Test
	void testPasModeleArbre() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t  { t:a }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof ErreurTypeNonArbre);
		ErreurTypeNonArbre erreurTypeNonArbre = (ErreurTypeNonArbre) erreur;
		assertTrue(erreurTypeNonArbre.nom.equals("m1$t"));

	}

	@Test
	void testPasModeleArbreBis() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { t:a } type t  { u:a }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 2);
		Erreur erreur0 = verif.erreurs.get(0);
		assertTrue(erreur0 instanceof ErreurTypeNonArbre);
		ErreurTypeNonArbre erreurTypeNonArbre0 = (ErreurTypeNonArbre) erreur0;

		Erreur erreur1 = verif.erreurs.get(1);
		assertTrue(erreur1 instanceof ErreurTypeNonArbre);
		ErreurTypeNonArbre erreurTypeNonArbre1 = (ErreurTypeNonArbre) erreur1;

		assertTrue((erreurTypeNonArbre0.nom.equals("m1$t") && erreurTypeNonArbre1.nom.equals("m1$u"))
				|| (erreurTypeNonArbre0.nom.equals("m1$u") && erreurTypeNonArbre1.nom.equals("m1$t")));

	}

	@Test
	void testPasModeleArbreAvecHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u : m { t:a } abstrait type m { } type t  { m:a }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 2);
		Erreur erreur0 = verif.erreurs.get(0);
		assertTrue(erreur0 instanceof ErreurTypeNonArbre);
		ErreurTypeNonArbre erreurTypeNonArbre0 = (ErreurTypeNonArbre) erreur0;

		Erreur erreur1 = verif.erreurs.get(1);
		assertTrue(erreur1 instanceof ErreurTypeNonArbre);
		ErreurTypeNonArbre erreurTypeNonArbre1 = (ErreurTypeNonArbre) erreur1;

		assertTrue((erreurTypeNonArbre0.nom.equals("m1$t") && erreurTypeNonArbre1.nom.equals("m1$u"))
				|| (erreurTypeNonArbre0.nom.equals("m1$u") && erreurTypeNonArbre1.nom.equals("m1$t")));

	}

	@Test
	void testTypeReserve() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type symbol {}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur0 = verif.erreurs.get(0);
		assertTrue(erreur0 instanceof NomTypeReserve);
		// NomTypeReserve erreurNomTypeReserve = (NomTypeReserve) erreur0;

	}

	@Test
	void testTypeSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type m { symbol:a}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testNomFonctionEnDouble() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction a symbol:a | a   fonction a symbol:v | v");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonNomFonction);
		DoublonNomFonction doublonNomFonction = (semantique.DoublonNomFonction) erreur;
		assertTrue(doublonNomFonction.nom.equals("m1$a"));

	}

	@Test
	void testTypeInconnuDansParametreFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction a u:a | a ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nom.equals("m1$u"));
		assertTrue(ti.nomRef.equals("m1$a"));

	}

	@Test
	void testNomChampInconnu() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { symbol:s } fonction a u:a | a.m ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof AccesChampInexistant);
		AccesChampInexistant ti = (AccesChampInexistant) erreur;
		Acces acces = (Acces) ti.acces;
		assertTrue(acces.nom.equals("m"));

	}

	@Test
	void testTestInferenceType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { symbol:s } fonction m symbol:s | u {s=s} fonction a symbol:s | m(s).s ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testTestInferenceTypePourSi() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"abstrait type bool {} type true : bool  {} type false :bool {} "
						+ " fonction not bool:b | si b est true alors false {} sinon true {} "
						+ " fonction id bool:b | not(not(b)) ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());
		VerificationFonction vf = verif.fonctions.get("m1$not");
		assertTrue(vf.typeRetour.equals("m1$bool"));

	}

	@Test
	void testDoublonNomParametreFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { symbol:s } fonction f u:a symbol:a | a ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonParamFonction);
		DoublonParamFonction doublonParamFonction = (DoublonParamFonction) erreur;
		assertTrue(doublonParamFonction.nom.equals("a"));
		assertTrue(doublonParamFonction.nomFonction.equals("m1$f"));

	}

	@Test
	void testTypeInexistanteCreationObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:a | momo {s=a } ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nomRef.equals("m1$f"));
		assertTrue(ti.nom.equals("m1$momo"));

	}

	@Test
	void testDoublonAttributCreationObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo { symbol:s } fonction f symbol:a | momo {s=a s=a} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonObjetParam);
		DoublonObjetParam ti = (DoublonObjetParam) erreur;
		assertTrue(ti.nomFonction.equals("m1$f"));
		assertTrue(ti.nom.equals("s"));

	}

	@Test
	void testCreationObjetAvecAttributInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo { symbol:s } fonction f symbol:a | momo {s=a inexistant=a} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof AccesChampInexistant);
		AccesChampInexistant ti = (AccesChampInexistant) erreur;
		ObjetParam op = (ObjetParam) ti.acces;
		assertTrue(op.nom.equals("inexistant"));

	}

	@Test
	void testCreationObjetAvecAttributDuMauvaisType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo { symbol:s } type nini { momo:m } fonction f symbol:a | nini {m=a} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeExpressionInvalideDansObjet);
		TypeExpressionInvalideDansObjet ti = (TypeExpressionInvalideDansObjet) erreur;
		assertTrue(ti.nomFonction.equals("m1$f"));
		assertTrue(ti.nom.equals("m"));

	}

	@Test
	void testCreationObjetAvecAttribuAvecHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"type momo :toto { } type toto {} type nini { toto:m } fonction f symbol:a | nini {m=momo{}} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testCreationObjetAvecAttribuMauvaisTypeEtHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo :toto { } " + "type toto {} " + "type nini { e:m }" + " type e {} "
				+ " fonction f symbol:a | nini {m=momo{}} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeExpressionInvalideDansObjet);
		TypeExpressionInvalideDansObjet ti = (TypeExpressionInvalideDansObjet) erreur;
		assertTrue(ti.nomFonction.equals("m1$f"));
		assertTrue(ti.nom.equals("m"));

	}

	@Test
	void testFonctionInexistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " fonction f symbol:a | m(a) ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof FonctionInexistante);
		FonctionInexistante ti = (FonctionInexistante) erreur;
		assertTrue(ti.nomRef.equals("m1$f"));
		assertTrue(ti.nom.equals("m1$m"));
	}

	@Test
	void testFonctionAvecNombreParametreKo() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"type s { symbol:a symbol:b } fonction u symbol:a symbol:b | s{a=a b=b } fonction f symbol:a | u(a) ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof NombreParametreInvalide);
		NombreParametreInvalide ti = (NombreParametreInvalide) erreur;
		assertTrue(ti.nomFonction.equals("m1$f"));
	}

	@Test
	void testFonctionAvecTestTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { } " + "  fonction f s:a | si a est momo alors s {} sinon a  ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nomRef.equals("m1$f"));
		assertTrue(ti.nom.equals("m1$momo"));
	}
}
