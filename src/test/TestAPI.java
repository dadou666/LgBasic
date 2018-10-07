package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.FonctionDef;
import model.Module;
import model.Univers;
import syntaxe.Parseur;

class TestAPI {

	@Test
	void test() {
		Map<Class,String> typeReserve = new HashMap<>();
		typeReserve.put(int.class, "base$int");
		typeReserve.put(String.class, "base$string");
		Map<String, String> sources = Univers.sources(API.class,typeReserve);
		assertTrue(sources.size() == 3);
		Parseur parser = new Parseur();
		Module module = parser.lireModule("toto", sources.get("toto"));
		assertTrue(module.types.size() >= 1);
		assertTrue(module.fonctions.size() >= 2);
	
		FonctionDef fd = getFonctionDef(module, "t");
		assertTrue(fd != null);
		assertTrue(fd.params.size() == 0);
		fd = getFonctionDef(module,"m");
		assertTrue(fd != null);
		assertTrue(fd.typeRetour != null);
		assertTrue(fd.typeRetour.nom.equals("int"));
		assertTrue(fd.typeRetour.module.equals("base"));
		assertTrue(fd.params.size() == 1);
		assertTrue(fd.params.get(0).type.nom.equals("string"));
		assertTrue(fd.params.get(0).type.module.equals("base"));		

		fd = getFonctionDef(module, "f");
		assertTrue(fd != null);
		assertTrue(fd.params.size() == 1);
		module = parser.lireModule("momo", sources.get("momo"));
		assertTrue(module.types.get(0).estAbstrait);
		assertTrue(module.types.get(0).superType == null);
		module = parser.lireModule("lili", sources.get("lili"));
		assertTrue(module.types.get(0).vars.size() == 1);
		assertTrue(module.types.get(0).superType != null);

	}

	public FonctionDef getFonctionDef(Module module, String nom) {
		for (FonctionDef fd : module.fonctions) {
			if (fd.nom.equals(nom)) {
				return fd;
			}
		}
		return null;
	}

}
