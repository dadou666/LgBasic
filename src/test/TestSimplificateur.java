package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.Expression;
import model.Univers;
import quantification.Simplificateur;
import semantique.Verificateur;
import syntaxe.Afficheur;
import syntaxe.Parseur;

class TestSimplificateur {

	@Test
	void test() {
		String source = "type zero { } type n:zero {zero:n } fonction + zero:a zero:b | si a est n alors n { n=a.n+b } sinon b fonction c1 | n { n= zero {} } fonction c2 | n {n=zero {}}";
		Parseur parser = new Parseur();
		Map<String, String> sources = new HashMap<>();
		sources.put("m1", source);

		Univers univers = parser.lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Simplificateur s = new Simplificateur();
		s.variables.put("a", verif.fonctions.get("m1$c1/0").get(0).fonction.expression);
		s.variables.put("b", verif.fonctions.get("m1$c2/0").get(0).fonction.expression);
		s.verificateur = verif;
		Expression a = verif.fonctions.get("m1$+/2").get(0).fonction.expression;
		Expression e = a.transformer(s);
		Afficheur afficheur = new Afficheur();
		afficheur.verif = verif;
		String u = e.transformer(afficheur);
		System.out.println(u);
		System.out.println(a.transformer(afficheur));
	}
	@Test
	void test2() {
		String tmp[] = "eee$rr".split("\\$");
		System.out.println(tmp.length==2);
		
	}

}
