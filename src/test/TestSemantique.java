package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runners.parameterized.ParametersRunnerFactory;

import model.Acces;
import model.Literal;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;
import semantique.AccesChampInexistant;
import semantique.CreationTypeAbstrait;
import semantique.CreationTypeReserve;
import semantique.DoublonChampType;
import semantique.DoublonNomFonction;
import semantique.DoublonNomParam;
import semantique.DoublonNomType;
import semantique.DoublonObjetParam;
import semantique.DoublonParamFonction;
import semantique.Erreur;
import semantique.ErreurTypeNonArbre;
import semantique.FonctionInexistante;
import semantique.MultipleDefinitionFonction;
import semantique.MultipleDefinitionType;
import semantique.NomTypeReserve;
import semantique.NombreParametreInvalide;
import semantique.ObjetIncomplet;
import semantique.OperationInvalideSurTypeReserve;
import semantique.TypeExpressionInvalideDansObjet;
import semantique.TypeIndetermine;
import semantique.TypeInexistant;
import semantique.TypeInvalideDansLiteral;
import semantique.TypeParametreFonctionInvalide;
import semantique.TypeReserveInvalideDansLiteral;
import semantique.Verificateur;
import semantique.VerificationFonction;
import syntaxe.Afficheur;
import syntaxe.Parseur;

class TestSemantique {

	@Test
	void test() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t {}");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t { a:m }");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		VarRef v = (VarRef) ti.expression;
		assertTrue(v.nom.equals("a"));

	}

	@Test
	void testDoublonChamp() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u {} type t { u:m u:m}");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		sources.put("m1", "type m :  symbol {}");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testNomFonctionEnDouble() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction a symbol:a | a   fonction a symbol:v | v");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonNomFonction);
		DoublonNomFonction doublonNomFonction = (semantique.DoublonNomFonction) erreur;
		assertTrue(doublonNomFonction.nom.equals("m1$a/1"));

	}

	@Test
	void testTypeInconnuDansParametreFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction a u:a | a ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nom.equals("m1$u"));
		assertTrue(ti.nomRef.equals("m1$a/1"));

	}

	@Test
	void testNomChampInconnu() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { symbol:s } fonction a u:a | a.m ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		VerificationFonction vf = verif.fonctions.get("m1$not/1");
		assertTrue(vf.typeRetour.equals("m1$bool"));

	}

	@Test
	void testDoublonNomParametreFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type u { symbol:s } fonction f u:a symbol:a | a ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonParamFonction);
		DoublonParamFonction doublonParamFonction = (DoublonParamFonction) erreur;
		assertTrue(doublonParamFonction.nom.equals("a"));
		assertTrue(doublonParamFonction.nomFonction.equals("m1$f/2"));

	}

	@Test
	void testTypeInexistanteCreationObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:a | momo {s=a } ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() >= 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$momo"));

	}

	@Test
	void testDoublonAttributCreationObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo { symbol:s } fonction f symbol:a | momo {s=a s=a} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof DoublonObjetParam);
		DoublonObjetParam ti = (DoublonObjetParam) erreur;
		assertTrue(ti.nomFonction.equals("m1$f/1"));
		assertTrue(ti.nom.equals("s"));

	}

	@Test
	void testCreationObjetAvecAttributInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo { symbol:s } fonction f symbol:a | momo {s=a inexistant=a} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
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
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeExpressionInvalideDansObjet);
		TypeExpressionInvalideDansObjet ti = (TypeExpressionInvalideDansObjet) erreur;
		assertTrue(ti.nomFonction.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m"));

	}

	@Test
	void testCreationObjetAvecAttribuAvecHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"type momo :toto { } type toto { symbol:s} type nini { toto:m } fonction f symbol:a | nini {m=momo{s=a}} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testCreationObjetAvecAttribuMauvaisTypeEtHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type momo :toto { } " + "type toto {} " + "type nini { e:m }" + " type e {} "
				+ " fonction f symbol:a | nini {m=momo{}} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeExpressionInvalideDansObjet);
		TypeExpressionInvalideDansObjet ti = (TypeExpressionInvalideDansObjet) erreur;
		assertTrue(ti.nomFonction.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m"));

	}

	@Test
	void testFonctionInexistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " fonction f symbol:a | m(a) ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof FonctionInexistante);
		FonctionInexistante ti = (FonctionInexistante) erreur;
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$m/1"));
	}

	@Test
	void testFonctionAvecTestTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { } " + "  fonction f s:a | si a est momo alors s {} sinon a  ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() >= 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$momo"));
	}

	@Test
	void testFonctionAvecTestTypeInexistantAvecModule() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { } " + "  fonction f s:a | si a est m1$momo alors s {} sinon a  ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() >= 1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant ti = (TypeInexistant) erreur;
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$momo"));
	}

	@Test
	void testResolutionTypeDansTypeDef() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { m:x} ");
		sources.put("m2", "type m {} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
	}

	@Test
	void testResolutionTypeDansFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction u symbol:s | m(s)");
		sources.put("m2", "fonction m symbol:s | s ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
	}

	@Test
	void testMultipleDefinitionFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction u symbol:s | m(s)");
		sources.put("m2", "fonction m symbol:s | s ");
		sources.put("m3", "fonction m symbol:s |s ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof MultipleDefinitionFonction);
		MultipleDefinitionFonction erreur = (MultipleDefinitionFonction) verif.erreurs.get(0);
		assertTrue(erreur.noms().contains("m2$m/1"));
		assertTrue(erreur.noms().contains("m3$m/1"));
		assertTrue(erreur.nomFonction.equals("m1$u/1"));
		assertTrue(erreur.fonctions.size() == 2);
	}

	@Test
	void testMultipleDefinitionTypeDansType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { symbol:x} ");
		sources.put("m2", "type symbol {} ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof MultipleDefinitionType);
		MultipleDefinitionType erreur = (MultipleDefinitionType) verif.erreurs.get(0);
		assertTrue(erreur.nom.equals("m1$s"));
		assertTrue(erreur.classDef == TypeDef.class);
		assertTrue(erreur.types.contains("base$symbol"));
		assertTrue(erreur.types.contains("m2$symbol"));
		assertTrue(erreur.types.size() == 2);
	}

	@Test
	void testCreationSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | symbol { e=m } ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);

	}

	@Test
	void testTestSurSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | si s est symbol alors s sinon s ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);

	}

	@Test
	void testDoublonType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type a  {} type a  {} ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof DoublonNomType);

	}

	@Test
	void testHeritageProfondeur3() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type a:b  {} type b:c  {symbol:b}  type c { symbol:a}");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testReferenceFonctionInexistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | m2$f(s)");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof FonctionInexistante);

	}

	@Test
	void testReferenceExterneFonctionExistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | m2$f(s)");
		sources.put("m2", "fonction f symbol:s | s");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testReferenceExterneTypeExistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t { m2$a:x }");
		sources.put("m2", "type a {}");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	void testInferenceTypeImpossible() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "  type a {}  type b {}   type c {} fonction f  c:a | si a est c alors a {} sinon b {}  ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof TypeIndetermine);

	}

	@Test
	void testAccesSurTypeReserve() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " fonction f  symbol:s | s.a ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);

	}

	@Test
	void testAccesSurTypeReserveDansObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type m {symbol:s } fonction f  m:s | (s.s).p ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);
	}

	@Test
	void testVarLibreSymbol() {

		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"abstrait type  bool {} type true:bool { } type false:bool {}  fonction f  bool:b | si b est true alors true sinon false ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		VerificationFonction vf = verif.fonctions.get("m1$f/1");
		assertTrue(vf.typeRetour.equals("base$symbol"));

	}

	@Test
	void testLiteral() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a symbol:b }  fonction f symbol:u symbol:v | [a u v] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/2");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression != null);
		Objet o = l.expression;
		assertTrue(o.typeOrVar.nomRef().equals("m1$a"));
		assertTrue(o.params.get(0).nom.equals("a"));
		assertTrue(o.params.get(0).expression instanceof VarRef);
		VarRef vr = (VarRef) o.params.get(0).expression;
		assertTrue(vr.nom.equals("u"));
		assertTrue(o.params.get(1).nom.equals("b"));
		assertTrue(o.params.get(1).expression instanceof VarRef);
		vr = (VarRef) o.params.get(1).expression;
		assertTrue(vr.nom.equals("v"));

	}

	@Test
	void testLiteralAvecErreurTypeVariable() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a symbol:b } type b {} fonction f symbol:u b:v | [a u v] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/2");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression == null);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof TypeInvalideDansLiteral);
		TypeInvalideDansLiteral erreur = (TypeInvalideDansLiteral) verif.erreurs.get(0);
		assertTrue(erreur.idx == 2);

	}

	@Test
	void testLiteralAvecErreurTypeObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a b:b } type b {} fonction f symbol:u symbol:v | [a u a u b] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/2");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression == null);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof TypeInvalideDansLiteral);
		TypeInvalideDansLiteral erreur = (TypeInvalideDansLiteral) verif.erreurs.get(0);
		assertTrue(erreur.idx == 2);
	}

	@Test
	void testLiteralAvec2Objet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				" type a {symbol:a b:b } type b {symbol:a symbol:b} fonction f symbol:u symbol:v | [a u b u v] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/2");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression != null);

	}

	@Test
	void testLiteralAvec2ObjetEtUneVariableLibre() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a b:b } type b {symbol:a symbol:b} fonction f symbol:u  | [a u b u totot] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> true);

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/1");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression != null);

	}

	@Test
	void testLiteralAvecErreurValidation() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a b:b } type b {symbol:a symbol:b} fonction f symbol:u  | [a u b u totot] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/1");
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression == null);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof TypeReserveInvalideDansLiteral);

	}

	@Test
	void testLiteralAvecErreurCreationTypeReserve() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type a {symbol:a b:b } type b {symbol:a symbol:b} fonction f symbol:u  | [a u b u totot] ");

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$f/1");
		assertTrue(vf.fonction.expression != null);
		Literal l = (Literal) vf.fonction.expression;
		assertTrue(l.expression == null);
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof CreationTypeReserve);

	}

	@Test
	void testInferenceType() {
		String source = "type zero {}\r\n" + "type n:zero { zero:n}\r\n"
				+ "fonction + zero:a zero:b | si a est n alors n { n= a.n} sinon b  ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
	}

	@Test
	void testFonctionAPI() {
		String source = "type b {} fonction t b:a -> b  fonction u b:a | t(a) ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
	}

	@Test
	void testMultipleFonctionValide() {
		String source = "type b {}  type a:b {} type c:b {}  fonction u b:x | f(a {}) ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		sources.put("m2", "fonction  f a:a | a ");
		sources.put("m3", "fonction  f b:a | a ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(!verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof MultipleDefinitionFonction);
	}
	
	@Test
	void testCreationTypeAbstrait() {
		String source = "abstrait type b {}   fonction u b:x | b {}  ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(!verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof CreationTypeAbstrait);
	}
	
	@Test
	void testCreationTypeAbstraitAvecVar() {
		String source = "abstrait type b {}   fonction u b:x | x {}  ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(!verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof CreationTypeAbstrait);
	}
	@Test
	void testCreationAvecVar() {
		String source = "type u {} type b {u:a u:b}   fonction u b:x | x { a= u { } }  ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}
	@Test
	void testCreationAvecVarAvecTestType() {
		String source = "type u {} abstrait type b {u:a u:b}  type a:b {} fonction u b:x | si x est a alors x { a= u { } } sinon x ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		VerificationFonction vf = verif.fonctions.get("m1$u/1");
		assertTrue(vf != null);
		TestType test = (TestType) vf.fonction.expression;
		Objet objet = (Objet) test.alors;
		assertTrue(verif.erreurs.isEmpty());
		assertTrue(objet.typeOrVar.nomRef().equals("m1$a"));
	}

	@Test
	void testMultipleFonctionInexistante() {
		String source = "type b {}  type a:b {} type c:b {}  fonction u symbol:a | f(a) ";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		sources.put("m2", "fonction  f a:a | a ");
		sources.put("m3", "fonction  f b:a | a ");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		// verif.validations.put("base$symbol", (String s) -> s.startsWith("_"));

		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(!verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof FonctionInexistante);
	}

	public boolean estInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	public boolean estFloat(String s) {
		try {
			Float.parseFloat(s.replace('p', '.'));
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	@Test
	public void testTypeReserveIntEtFloat() {
		String source = "type b {int:a } fonction m b:a | b { a= er }";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> !Character.isDigit(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertFalse(verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);

	}

	@Test
	public void testTypeReserveInt() {
		String source = "type b {int:a } fonction m b:a | b { a= 5 }";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> !Character.isDigit(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		// assertTrue(verif.erreurs.size() == 1);

	}

	@Test
	public void testTypeReserveFloatEtInt() {
		String source = "type b {float:a int:b} fonction m b:a | b { a= 5p45 b=5}";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> !Character.isDigit(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		// assertTrue(verif.erreurs.size() == 1);

	}

	@Test
	public void testFonctionSymbol() {
		String source = "  fonction  u int:a | a+45";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		sources.put("m2", " fonction + symbol:a symbol:b -> symbol");
		sources.put("m3", "type c {} fonction + c:a c:b -> symbol");
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> Character.isLetter(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertFalse(verif.erreurs.isEmpty());
		// assertTrue(verif.erreurs.size() == 1);

	}

	@Test
	public void testObjetIncomplet() {
		String source = "type b {float:a int:b} fonction m b:a | b { a= 5p45 }";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> !Character.isDigit(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertFalse(verif.erreurs.isEmpty());
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof ObjetIncomplet);
		ObjetIncomplet erreur = (ObjetIncomplet) verif.erreurs.get(0);
		assertTrue(erreur.absents.contains("b"));
		assertTrue(erreur.absents.size() == 1);

	}

	@Test
	public void testTypeSurTypeReserve() {
		String source = "type u {} fonction m u:u symbol:a int:b | si u est u alors a sinon b";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$symbol", (String s) -> !Character.isDigit(s.charAt(0)));
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$float", (String s) -> estFloat(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0) instanceof TypeIndetermine);

	}

	@Test
	public void testParam() {
		String source = "type t { } fonction f t:a | a param t:u  fonction main | f(u)";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	public void testParam2() {
		String source = "type t { } fonction f t:a | a param m:u  fonction main | f(u)";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0).getClass() == TypeInexistant.class);

	}

	@Test
	public void testParam3() {
		String source = "type t { } fonction f m:a | a param t:u  type m {} fonction main | f(u)";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0).getClass() == FonctionInexistante.class);

	}

	@Test
	public void testParam4() {
		String source1 = "type t { } param t:a";
		String source2 = " type m {} param m:a";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source1);
		sources.put("m2", source2);
		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();

		assertTrue(verif.erreurs.size() == 1);
		assertTrue(verif.erreurs.get(0).getClass() == DoublonNomParam.class);

	}

	@Test
	public void testFonctionRec() {

		String source = "type zero {} type n:zero { zero:n } fonction m zero:a | si a est n alors m(a.n) sinon a";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();

	}

	@Test
	public void testParam5() {
		String source = "type zero { } type n:zero {zero:n } param zero:in fonction f | n { n=in }";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	public void testOperateurEgalite() {
		String source = "type zero { } type n:zero {zero:n }  abstrait type  bool {} type true:bool {} type false:bool {} "
				+ "fonction = zero:a zero:b | si a est n alors ( si b est n alors a.n=b.n sinon false {} ) sinon ( si b est n alors false {} sinon true {} ) "
				+ "fonction + zero:a zero:b | si a est n alors n { n= a.n+b} sinon b "
				+ "fonction commutation zero:a zero:b |  (a+b) = (b+a)  ";

		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		Afficheur afficheur = new Afficheur();
		System.out.println(verif.fonctions.get("m1$commutation/2").fonction.expression.transformer(afficheur));
		assertTrue(verif.erreurs.isEmpty());

	}

	@Test
	public void testOperateurEgalite2() {
		String source = "type zero { } type n:zero {zero:n } abstrait type  bool {} type true:bool {} type false:bool {} "
				+ "fonction = zero:a zero:b | si a est n alors ( si b est n alors a.n=b.n sinon false {} ) sinon ( si b est n alors false {} sinon true {} ) ";

		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		assertTrue(verif.fonctions.get("m1$=/2").typeRetour != null);

	}

	@Test
	public void testOperateurEgalite3() {
		String source = "type zero { } type n:zero {zero:n }  "
				+ "fonction = zero:a zero:b | si a est n alors ( si b est n alors a.n=b.n sinon false {} ) sinon ( si b est n alors false {} sinon true {} ) "
				+ "fonction + zero:a zero:b | si a est n alors n { n= a.n+b} sinon b "
				+ "fonction commutation zero:a zero:b |  (a+b) = (b+a)  ";
		String source2 = " abstrait type  bool {} type true:bool {} type false:bool {}";

		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);
		sources.put("m2", source2);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();

		assertTrue(verif.erreurs.isEmpty());

	}
	
	@Test
	public void testBug() throws IOException {
		String source =source(this.getClass().getResourceAsStream("/test/src/logic.src"));
	
		String source2 =source(this.getClass().getResourceAsStream("/test/src/arbre.src"));


		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("logic", source);
		sources.put("arbre", source2);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executer();

		assertTrue(verif.erreurs.isEmpty());
	}

	public String source(InputStream is) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		// StandardCharsets.UTF_8.name() > JDK 7
		return result.toString("UTF-8");
	}

}
