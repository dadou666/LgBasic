package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Test;

import execution.ClasseAbsente;
import execution.Traducteur;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import model.TypeDef;
import model.Univers;
import model.Var;
import semantique.Verificateur;
import syntaxe.Parseur;

class TestExecutionJava {

	@Test
	void test() {
		String s = "a b	b";
		String array[] = s.split("[ \t\n]");
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < array.length; i++) {
			System.out.println(" array[" + i + "]=" + array[i]);
			stack.push(array[i]);
		}
		Collections.reverse(stack);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

	}

	@Test
	void testPeano() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClasseAbsente, InvocationTargetException {
		Map<String, String> sources = new HashMap<>();
		sources.put("math", " type zero {}  type n:zero { zero:n } "
				+ "  fonction + zero:a zero:b | si a est n alors n {n=a.n+b} sinon b  ");
		Univers univers = (new Parseur()).lireSourceCode(sources, null);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("peano", verif);
		Class cls = traducteur.traduire();
		Class math$zero = traducteur.mapClass.get("math$zero");
		assertTrue(math$zero != null);
		Method m =cls.getMethod("math$add", math$zero,math$zero);
		Object a = traducteur.construire("n n   zero");
		Object b= traducteur.construire("n   zero");
		Object r = m.invoke(cls.newInstance(),a,b);
		assertTrue(traducteur.source(r).equals(traducteur.source(traducteur.construire("n n n zero"))));
		
		
		

	}
	@Test
	void testPeano2() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClasseAbsente, InvocationTargetException {
		Map<String, String> sources = new HashMap<>();
		sources.put("main",  "  fonction + zero:a zero:b | si a est n alors n {n=a.n+b} sinon b  ");
		List<String> modules = new ArrayList<>();
		modules.add("math");
		Univers.sources(APITestExcution.class,null,sources);
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("peano2", verif);
		traducteur.api = APITestExcution.class;
		Class cls = traducteur.traduire();
		Class math$zero = traducteur.mapClass.get("math$zero");
		assertTrue( math$zero != null);
		assertTrue(math$zero == APITestExcution.math$zero.class);
		Method m =cls.getMethod("main$add", math$zero,math$zero);
		Object a = traducteur.construire("n n   zero");
		Object b= traducteur.construire("n   zero");
		Object r = m.invoke(cls.newInstance(),a,b);
		assertTrue(traducteur.source(r).equals(traducteur.source(traducteur.construire("n n n zero"))));
		
		
		

	}

}
