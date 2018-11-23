package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import quantification.Element;

class TestDecomposition {

	@Test
	void test1() {
		List<String> vars = new ArrayList<String>();
		vars.add("x");
		List<String> r = Element.listeDecomposition(vars);
		assertTrue(r.size() == 1);
		assertTrue(r.get(0).equals("x"));
		
	}
	@Test
	void test2() {
		List<String> vars = new ArrayList<String>();
		vars.add("x");
		vars.add("y");
		List<String> r = Element.listeDecomposition(vars);
		assertTrue(r.size() == 3);
		assertTrue(r.get(0).equals("x"));
		assertTrue(r.get(1).equals("y"));
		assertTrue(r.get(2).equals("x,y"));
		
		
	}
	@Test
	void test3() {
		List<String> vars = new ArrayList<String>();
		vars.add("x");
		vars.add("y");
		vars.add("z");
		List<String> r = Element.listeDecomposition(vars);
		assertTrue(r.size() == 7);
		assertTrue(r.get(0).equals("x"));
		assertTrue(r.get(1).equals("y"));
		assertTrue(r.get(2).equals("z"));
		assertTrue(r.get(3).equals("x,y"));
		assertTrue(r.get(4).equals("x,z"));
		assertTrue(r.get(5).equals("y,z"));
		assertTrue(r.get(6).equals("x,y,z"));
		
		
		
	}

}
