package generateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Module;
import model.VarRef;

class TestParser {

	@Test
	void testTypeVide() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a { } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1 );
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.isEmpty());
		
	}
	
	@Test
	void testTypeUnChamp() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a {u:a } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1 );
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 1);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module==null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));
		
	}
	@Test
	void testTypeUnChampDansAutreModule() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a {toto$u:a } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1 );
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
		assertTrue(module.types.size() == 1 );
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 2);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module==null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));
		


		assertTrue(module.types.get(0).vars.get(1).nom.equals("b"));
		assertTrue(module.types.get(0).vars.get(1).type.module==null);
		assertTrue(module.types.get(0).vars.get(1).type.nom.equals("m"));
		
	}
	
	@Test
	void testTypeDeuxChampsAvecHeritage() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("type a: h {u:a m:b } ");
		assertTrue(module != null);
		assertTrue(module.types.size() == 1 );
		assertTrue(module.types.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.size() == 2);
		assertTrue(module.types.get(0).vars.get(0).nom.equals("a"));
		assertTrue(module.types.get(0).vars.get(0).type.module==null);
		assertTrue(module.types.get(0).vars.get(0).type.nom.equals("u"));
		


		assertTrue(module.types.get(0).vars.get(1).nom.equals("b"));
		assertTrue(module.types.get(0).vars.get(1).type.module==null);
		assertTrue(module.types.get(0).vars.get(1).type.nom.equals("m"));
		
		assertTrue(module.types.get(0).superType!=null);
		assertTrue(module.types.get(0).superType.nom.equals("h"));
		assertTrue(module.types.get(0).superType.module == null);
		
	}
	
	@Test
	void testFonctionIdentite() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f o:x | x");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1 );
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof VarRef);
		VarRef varRef =(VarRef) module.fonctions.get(0).expression;
		assertTrue(varRef.nom.equals("x"));
		assertTrue(module.fonctions.get(0).params.size()==1);
		assertTrue(module.fonctions.get(0).params.get(0).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));
	}
	
	@Test
	void testFonctionIdentiteParamAvecAutreModule() {
		Parseur parseur = new Parseur();
		Module module = parseur.lireModule("fonction f toto$o:x | x");
		assertTrue(module != null);
		assertTrue(module.fonctions.size() == 1 );
		assertTrue(module.fonctions.get(0).nom.equals("f"));
		assertTrue(module.fonctions.get(0).expression != null);
		assertTrue(module.fonctions.get(0).expression instanceof VarRef);
		VarRef varRef =(VarRef) module.fonctions.get(0).expression;
		assertTrue(varRef.nom.equals("x"));
		assertTrue(module.fonctions.get(0).params.size()==1);
		assertTrue(module.fonctions.get(0).params.get(0).type.nom.equals("o"));
		assertTrue(module.fonctions.get(0).params.get(0).type.module == null);
		assertTrue(module.fonctions.get(0).params.get(0).nom.equals("x"));
	}
	
	
	

}
