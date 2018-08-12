package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.Module;
import model.Univers;
import model.Var;
import model.VarRef;
import semantique.Erreur;
import semantique.TypeInexistant;
import semantique.Verificateur;
import syntaxe.Parseur;

class TestSemantique {

	@Test
	void test() {
		Parseur parser = new Parseur();
		Map<String,String> sources = new HashMap<>();
		sources.put("m1", "type t {}");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.isEmpty());
		
		
	}
	
	@Test
	void testTypeInexistant() {
		Parseur parser = new Parseur();
		Map<String,String> sources = new HashMap<>();
		sources.put("m1", "type t { a:m }");
		Univers univers = parser.lireSourceCode(sources);
		Verificateur verif = new Verificateur();
		verif.executerPourTypes(univers);
		assertTrue(verif.erreurs.size()==1);
		Erreur erreur = verif.erreurs.get(0);
		assertTrue(erreur instanceof TypeInexistant);
		TypeInexistant  ti = (TypeInexistant) erreur;
		VarRef v = (VarRef) ti.expression;
		assertTrue(v.nom.equals("m"));
		
		
		
	}

}
