package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import editeur.SimpleExecuteur;
import quantification.Element;
import quantification.Generalisation;
import semantique.Verificateur;

class TestGeneralisation {

	@Test
	void test() throws IOException {
		SimpleExecuteur se= new SimpleExecuteur();
		Verificateur verificateur = new Verificateur(se.classAPI(),se.typeReserve(),"F://GitHub//LgBasic//src//test");
		System.out.println(" erreurs="+verificateur.erreurs);
		assertTrue(verificateur.erreurs.isEmpty());

		Generalisation gen = verificateur.creerGeneralisation("math$gen/2","logic$true");
		assertTrue(gen != null);
		TestModuleInit testModuleInit = new TestModuleInit();
		gen.element.et.expression.visiter(testModuleInit);
		gen.calculer();
		assertFalse(gen.element.transformation == null);
		assertFalse(gen.enfants.isEmpty());
		System.out.println(" etape 1");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			assertTrue(e.parent == gen.element);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 2");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 3");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 4");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 5");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 6");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 7");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
	}
	
	@Test
	void test2() throws IOException {
		SimpleExecuteur se= new SimpleExecuteur();
		Verificateur verificateur = new Verificateur(se.classAPI(),se.typeReserve(),"F://GitHub//LgBasic//src//test");
		assertTrue(verificateur.erreurs.isEmpty());
		Generalisation gen = verificateur.creerGeneralisation("arbre$prop/1","logic$true");
		
		assertTrue(gen != null);
		TestModuleInit testModuleInit = new TestModuleInit();
		gen.element.et.expression.visiter(testModuleInit);
		verificateur.fonctions.get("arbre$=/2").fonction.expression.visiter(testModuleInit);
		gen.calculer();
		assertFalse(gen.element.transformation == null);
		assertFalse(gen.enfants.isEmpty());
		System.out.println(" etape 1");
		for(Element e:gen.enfants) {
			assertTrue(e.parent == gen.element);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 2");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 3");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 4");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 5");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
		gen.calculer();
		System.out.println(" etape 5");
		for(Element e:gen.enfants) {
			assertTrue(e.parent != null);
			System.out.println(e);
		}
	
	}


}
