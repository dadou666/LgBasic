package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.Acces;
import model.Module;
import model.Univers;
import model.Var;
import model.VarRef;
import semantique.AccesChampInexistant;
import semantique.DoublonChampType;
import semantique.DoublonNomFonction;
import semantique.Erreur;
import semantique.ErreurTypeNonArbre;
import semantique.NomTypeReserve;
import semantique.TypeInexistant;
import semantique.Verificateur;
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
		assertTrue(verif.erreurs.size()==1);
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
		assertTrue(verif.erreurs.size()==1);
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
		assertTrue(verif.erreurs.size()==1);
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
	
}
