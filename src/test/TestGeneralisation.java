package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import quantification.Element;
import quantification.Generalisation;
import semantique.Verificateur;

class TestGeneralisation {

	@Test
	void test() throws IOException {
		Verificateur verificateur = new Verificateur("F://GitHub//LgBasic//src//test");
		assertTrue(verificateur.erreurs.isEmpty());
		Generalisation gen = verificateur.creerGeneralisation("math$gen/2","logic$true");
		assertTrue(gen != null);
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
	}
	
	@Test
	void test2() throws IOException {
		Verificateur verificateur = new Verificateur("F://GitHub//LgBasic//src//test");
		assertTrue(verificateur.erreurs.isEmpty());
		Generalisation gen = verificateur.creerGeneralisation("arbre$prop/1","logic$true");
		assertTrue(gen != null);
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
	}


}
