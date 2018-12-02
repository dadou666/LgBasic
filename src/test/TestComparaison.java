package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.FonctionDef;
import model.Module;
import model.Univers;
import quantification.Comparaison;
import semantique.Verificateur;
import syntaxe.Parseur;

class TestComparaison {

	@Test
	void test() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule(null, " fonction f | (m(x)+t(x))/u  fonction u | (m(o)+t(o))/v");
		FonctionDef f =module.fonctions.get(0);
		FonctionDef u =module.fonctions.get(1);
		Comparaison comparaison = new Comparaison();
		
		assertFalse(parseur.error);
		assertTrue(comparaison.comparerExpression(f.expression, u.expression));
		assertTrue(comparaison.map1.get("x") != null);
		assertTrue(comparaison.map1.get("u") != null);
		
		assertTrue(comparaison.map2.get("o") != null);
		assertTrue(comparaison.map2.get("v") != null);
		
		assertTrue(comparaison.map2.get("o").equals("x"));
		assertTrue(comparaison.map1.get("x").equals("o"));
		
		assertTrue(comparaison.map2.get("v").equals("u"));
		assertTrue(comparaison.map1.get("u").equals("v"));
		
	}
	
	@Test
	void testObjet() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule(null, " fonction f |t { x=a y=k }  fonction u | t {y=u x=o}");
		FonctionDef f =module.fonctions.get(0);
		FonctionDef u =module.fonctions.get(1);
		Comparaison comparaison = new Comparaison();
		
		assertFalse(parseur.error);
		assertTrue(comparaison.comparerExpression(f.expression, u.expression));
		assertTrue(comparaison.map1.get("a") != null);
		assertTrue(comparaison.map1.get("k") != null);
		
		assertTrue(comparaison.map2.get("u") != null);
		assertTrue(comparaison.map2.get("o") != null);
		
		assertTrue(comparaison.map2.get("o").equals("a"));
		assertTrue(comparaison.map1.get("a").equals("o"));
		
		assertTrue(comparaison.map2.get("u").equals("k"));
		assertTrue(comparaison.map1.get("k").equals("u"));
		
	}
	
	@Test
	void testSi() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule(null, " fonction f |si x est t alors m sinon u  fonction u | si y est t alors p sinon w");
		FonctionDef f =module.fonctions.get(0);
		FonctionDef u =module.fonctions.get(1);
		Comparaison comparaison = new Comparaison();
		
		assertFalse(parseur.error);
		assertTrue(comparaison.comparerExpression(f.expression, u.expression));
		assertTrue(comparaison.map1.get("x") != null);
		assertTrue(comparaison.map1.get("m") != null);
		assertTrue(comparaison.map1.get("u") != null);
		
		assertTrue(comparaison.map2.get("y") != null);
		assertTrue(comparaison.map2.get("p") != null);
		assertTrue(comparaison.map2.get("w") != null);
		
		assertTrue(comparaison.map2.get("y").equals("x"));
		assertTrue(comparaison.map1.get("x").equals("y"));
		
		assertTrue(comparaison.map2.get("p").equals("m"));
		assertTrue(comparaison.map1.get("m").equals("p"));
		
		assertTrue(comparaison.map2.get("w").equals("u"));
		assertTrue(comparaison.map1.get("u").equals("w"));
		
	}
	@Test
	void testAcces() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule(null, " fonction f | a.m  fonction u |x.m");
		FonctionDef f =module.fonctions.get(0);
		FonctionDef u =module.fonctions.get(1);
		Comparaison comparaison = new Comparaison();
		
		assertFalse(parseur.error);
		assertTrue(comparaison.comparerExpression(f.expression, u.expression));
		assertTrue(comparaison.map1.get("a") != null);
	
		
		assertTrue(comparaison.map2.get("x") != null);		
		assertTrue(comparaison.map2.get("x").equals("a"));
		assertTrue(comparaison.map1.get("a").equals("x"));
		

		
	}

}
