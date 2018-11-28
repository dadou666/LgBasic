package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import quantification.Demonstration;

class TestProduitCartesien {

	@Test
	void test() {
		List<List<String>> in= new ArrayList<>();
		List<String> lettres = new ArrayList<>();
		lettres.add("a");
		lettres.add("b");
		in.add(lettres);
		List<String> chiffres = new ArrayList<>();
		chiffres.add("1");
		chiffres.add("2");

		in.add(chiffres);
		List<String> symbols = new ArrayList<>();
		symbols.add("@");
		symbols.add("#");

		in.add(symbols);
		List<List<String>> out = Demonstration.produitCartesien(in);
		String m="[[a, 1, @], [a, 1, #], [a, 2, @], [a, 2, #], [b, 1, @], [b, 1, #], [b, 2, @], [b, 2, #]]";
		assertTrue(m.equals(out.toString()));
	}

}
