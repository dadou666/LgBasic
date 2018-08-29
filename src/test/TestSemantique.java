package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runners.parameterized.ParametersRunnerFactory;

import model.Acces;
import model.Module;
import model.ObjetParam;
import model.Univers;
import model.Var;
import model.VarRef;
import semantique.AccesChampInexistant;
import semantique.DoublonChampType;
import semantique.DoublonNomFonction;
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
import semantique.OperationInvalideSurTypeReserve;
import semantique.TypeExpressionInvalideDansObjet;
import semantique.TypeIndetermine;
import semantique.TypeInexistant;
import semantique.TypeParametreFonctionInvalide;
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
		assertTrue(v.nom.equals("a"));

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
		sources.put("m1", "type m :  symbol {}");
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
		assertTrue(doublonNomFonction.nom.equals("m1$a/1"));

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
		assertTrue(ti.nomRef.equals("m1$a/1"));

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
		VerificationFonction vf = verif.fonctions.get("m1$not/1");
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
		assertTrue(doublonParamFonction.nomFonction.equals("m1$f/2"));

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
		assertTrue(ti.nomRef.equals("m1$f/1"));
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
		assertTrue(ti.nomFonction.equals("m1$f/1"));
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
		assertTrue(ti.nomFonction.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m"));

	}

	@Test
	void testCreationObjetAvecAttribuAvecHeritage() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1",
				"type momo :toto { } type toto { symbol:s} type nini { toto:m } fonction f symbol:a | nini {m=momo{s=a}} ");
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
		assertTrue(ti.nomFonction.equals("m1$f/1"));
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
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$m/1"));
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
		assertTrue(ti.nomRef.equals("m1$f/1"));
		assertTrue(ti.nom.equals("m1$momo"));
	}
	
	@Test
	void testFonctionAvecTestTypeInexistantAvecModule() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { } " + "  fonction f s:a | si a est m1$momo alors s {} sinon a  ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size() == 1);
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
		sources.put("m1", "type s { m:x} " );
		sources.put("m2","type m {} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());
	}
	
	@Test
	void testResolutionTypeDansFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction u symbol:s | m(s)" );
		sources.put("m2","fonction m symbol:s | m(s) ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());
	}

	@Test
	void testMultipleDefinitionFonction() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction u symbol:s | m(s)" );
		sources.put("m2","fonction m symbol:s | m(s) ");
		sources.put("m3","fonction m symbol:s | m(s) ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof MultipleDefinitionFonction);
		MultipleDefinitionFonction erreur = (MultipleDefinitionFonction) verif.erreurs.get(0);
		assertTrue(erreur.fonctions.contains("m2$m/1"));
		assertTrue(erreur.fonctions.contains("m3$m/1"));
		assertTrue(erreur.nomFonction.equals("m1$u/1"));
		assertTrue(erreur.fonctions.size()==2);
	}
	@Test
	void testMultipleDefinitionTypeDansType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type s { symbol:x} " );
		sources.put("m2","type symbol {} ");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof MultipleDefinitionType);
		MultipleDefinitionType erreur = (MultipleDefinitionType)verif.erreurs.get(0) ;
		assertTrue(erreur.nom.equals("m1$s"));
		assertTrue(!erreur.estFonction);
		assertTrue(erreur.types.contains("base$symbol"));
		assertTrue(erreur.types.contains("m2$symbol"));
		assertTrue(erreur.types.size() == 2);
	}
	@Test
	void testCreationSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | symbol { e=m } " );
		
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);

	}
	@Test
	void testTestSurSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | si s est symbol alors s sinon s " );
		
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);

	}
	@Test
	void testDoublonType() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type a  {} type a  {} " );
		
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof DoublonNomType);
		

	}
	@Test
	void testHeritageProfondeur3() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type a:b  {} type b:c  {symbol:b}  type c { symbol:a}" );
		
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());
	
		

	}
	@Test 
	void testReferenceFonctionInexistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | m2$f(s)" );
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof FonctionInexistante);
		
		
	}
	
	@Test 
	void testReferenceExterneFonctionExistante() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | m2$f(s)" );
		sources.put("m2", "fonction f symbol:s | s" );
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());

		
		
	}
	
	@Test 
	void testReferenceExterneTypeExistant() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "type t { m2$a:x }" );
		sources.put("m2", "type a {}" );
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());

		
		
	}
	
	@Test 
	void testParametreFonctionInvalide() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "fonction f symbol:s | m2$f(s)" );
		sources.put("m2", "type a {} fonction f a:s | s" );
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof TypeParametreFonctionInvalide);

		
		
	}
	@Test 
	void testInferenceTypeImpossible() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "  type a {}  type b {}   type c {} fonction f  c:a | si a est c alors a {} sinon b {}  "   );
	
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof TypeIndetermine);
		
		
		
	}
	@Test 
	void testAccesSurTypeReserve() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " fonction f  symbol:s | s.a "   );
	
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);
		
		
		
	}
	@Test 
	void testAccesSurTypeReserveDansObjet() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", " type m {symbol:s } fonction f  m:s | (s.s).p "   );
	
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.size()==1);
		assertTrue(verif.erreurs.get(0) instanceof OperationInvalideSurTypeReserve);
	}
	
	@Test 
	void testVarLibreSymbol() {
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", "abstrait type  bool {} type true:bool { } type false:bool {}  fonction f  bool:b | si b est true alors true sinon false "   );
	
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.validations.put("base$symbol",(String s)->true);
		verif.executerPourTypes(univers);
		verif.executerPourFonctions(univers);
		assertTrue(verif.erreurs.isEmpty());
		VerificationFonction vf =verif.fonctions.get("m1$f/1");
		assertTrue(vf.typeRetour.equals("base$symbol"));
	
	}
	
}
