package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
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
import test.APITestExcution2.truc$t;
import test.APITestExcution4.math$n;

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
	void testPeano() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException,
			ClasseAbsente, InvocationTargetException, ClassNotFoundException, IOException {
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
		Method m = cls.getMethod("math$add_0", math$zero, math$zero);
		Object a = traducteur.construire("n n   zero");
		Object b = traducteur.construire("n   zero");
		Object r = m.invoke(cls.newInstance(), a, b);
		String srcA = traducteur.source(r);
		String srcB = traducteur.source(traducteur.construire("n n n zero"));
		assertTrue(srcA.equals(srcB));

	}

	@Test
	void testPeano2() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException,
			ClasseAbsente, InvocationTargetException, ClassNotFoundException, IOException {
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction + zero:a zero:b | si a est n alors n {n=a.n+b} sinon b  ");
		List<String> modules = new ArrayList<>();
		modules.add("math");
		Univers.sources(APITestExcution.class, null, sources);
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("peano", verif);
		traducteur.api = APITestExcution.class;
		Class cls = traducteur.traduire();
		Class math$zero = traducteur.mapClass.get("math$zero");
		assertTrue(math$zero != null);
		assertTrue(math$zero == APITestExcution.math$zero.class);
		Method m = cls.getMethod("main$add_0", math$zero, math$zero);
		Object a = traducteur.construire("n n   zero");
		Object b = traducteur.construire("n   zero");
		Object r = m.invoke(cls.newInstance(), a, b);
		assertTrue(traducteur.source(r).equals(traducteur.source(traducteur.construire("n n n zero"))));

	}
	
	@Test
	void testPeano3() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException,
			ClasseAbsente, InvocationTargetException, ClassNotFoundException, IOException {
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "   fonction nb | [n n n zero ]");
		List<String> modules = new ArrayList<>();
		modules.add("math");
		Univers.sources(APITestExcution.class, null, sources);
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("peano", verif);
		traducteur.api = APITestExcution.class;
		Class cls = traducteur.traduire();
		Class math$zero = traducteur.mapClass.get("math$zero");
		assertTrue(math$zero != null);
		assertTrue(math$zero == APITestExcution.math$zero.class);
		Method m = cls.getMethod("main$nb_0");
		Object r = m.invoke(cls.newInstance());
		assertTrue(traducteur.source(r).equals(traducteur.source(traducteur.construire("n n n zero"))));

	}
	@Test
	void testPeano4() throws NotFoundException, CannotCompileException, NoSuchMethodException, SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException,
			ClasseAbsente, InvocationTargetException, ClassNotFoundException, IOException {
		Map<String, String> sources = new HashMap<>();
		sources.put("main", " fonction s n:a | a.n  fonction nb | s([n n n zero ])");
		List<String> modules = new ArrayList<>();
		modules.add("math");
		Univers.sources(APITestExcution.class, null, sources);
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("peano", verif);
		traducteur.api = APITestExcution.class;
		Class cls = traducteur.traduire();
		Class math$zero = traducteur.mapClass.get("math$zero");
		assertTrue(math$zero != null);
		assertTrue(math$zero == APITestExcution.math$zero.class);
		Method m = cls.getMethod("main$nb_0");
		Object r = m.invoke(cls.newInstance());
		assertTrue(traducteur.source(r).equals(traducteur.source(traducteur.construire(" n n zero"))));

	}

	@Test
	void testTypeString() throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  f t:x | x.val  fonction id t:x | x  ");
		Univers.sources(APITestExcution2.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("truc");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.api = APITestExcution2.class;
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$f_0", APITestExcution2.truc$t.class);
		APITestExcution2.truc$t t = new APITestExcution2.truc$t();
		t.val = "hello";
		Object r = m.invoke(cls.newInstance(), t);
		assertTrue(r.equals(t.val));
		m = cls.getMethod("main$id_0", APITestExcution2.truc$t.class);

		assertTrue(m.invoke(cls.newInstance(), t) == t);

	}

	@Test
	void testTypeStringCons() throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$string");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  cons string:s |  t { val=s }  ");
		Univers.sources(APITestExcution2.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("truc");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$string", String.class);
		traducteur.api = APITestExcution2.class;
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$cons_0", String.class);

		APITestExcution2.truc$t t = (truc$t) m.invoke(cls.newInstance(), "hello");
		assertTrue(t.val.equals("hello"));

	}

	@Test
	void testLiteralTraducteur() throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		typeReserve.put(int.class, "base$int");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  cons string:s |  t { val=s val2=popo }  ");
		Univers.sources(APITestExcution3.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("truc");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.executerPourTypes();
		verif.executerPourFonctions();
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$string", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.api = APITestExcution3.class;
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$string", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$cons_0", String.class);

		APITestExcution3.truc$t t = (APITestExcution3.truc$t) m.invoke(cls.newInstance(), "hello");
		assertTrue(t.val.equals("hello"));
		assertTrue(t.val2.equals("popo"));

	}

	public boolean estInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	public boolean estChaine(String s) {
		return !estInt(s);
	}

	@Test
	void testLiteralTraducteurInt() throws ClassNotFoundException, NotFoundException, CannotCompileException,
			IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		typeReserve.put(int.class, "base$int");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  cons symbol:s |  u { val2=45 val=s }  ");
		Univers.sources(APITestExcution3.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("truc");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$symbol", (String s) -> estChaine(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.api = APITestExcution3.class;
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$cons_0", String.class);

		APITestExcution3.truc$u t = (APITestExcution3.truc$u) m.invoke(cls.newInstance(), "hello");
		assertTrue(t.val.equals("hello"));
		assertTrue(t.val2==45);

	}
	@Test
	void testLiteralTraducteurInt2() throws ClassNotFoundException, NotFoundException, CannotCompileException,
			IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, NoSuchFieldException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		typeReserve.put(int.class, "base$int");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  type ls:fin { int:val fin:next } type fin { }  fonction m | [ls 5 ls 78 ls 6 fin] ");
		Univers.sources(APITestExcution3.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("truc");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$symbol", (String s) -> estChaine(s));
		verif.executerPourTypes();
		verif.executerPourFonctions();
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.api = APITestExcution3.class;
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$m_0");

		Object t =  m.invoke(cls.newInstance());
		String src = traducteur.source(t);

	}
	@Test
	void testParam1() throws ClassNotFoundException, NotFoundException, CannotCompileException,
			IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		typeReserve.put(int.class, "base$int");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  f | n {n=in  }  ");
		Univers.sources(APITestExcution4.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("math");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$symbol", (String s) -> estChaine(s));
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.api = APITestExcution4.class;
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$f_0");
		APITestExcution4 api =(APITestExcution4) cls.newInstance();
		api.in = new APITestExcution4.math$zero();
		APITestExcution4.math$zero t = (APITestExcution4.math$zero) m.invoke(api);
		assertTrue(t instanceof APITestExcution4.math$n);
		APITestExcution4.math$n n=(math$n) t;
		assertTrue(n.n!=null);
		assertTrue(n.n ==api.in);
		

	}
	
	
	@Test
	void testParam2() throws ClassNotFoundException, NotFoundException, CannotCompileException,
			IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		Map<Class, String> typeReserve = new HashMap<>();
		typeReserve.put(String.class, "base$symbol");
		typeReserve.put(int.class, "base$int");
		Map<String, String> sources = new HashMap<>();
		sources.put("main", "  fonction  f | a+b  ");
		Univers.sources(APITestExcution5.class, typeReserve, sources);
		List<String> modules = new ArrayList<>();
		modules.add("math");
		modules.add("base");
		Univers univers = (new Parseur()).lireSourceCode(sources, modules);
		Verificateur verif = new Verificateur(univers);
		verif.validations.put("base$int", (String s) -> estInt(s));
		verif.validations.put("base$symbol", (String s) -> estChaine(s));
		verif.executerPourTypes();
		verif.executerPourParams();
		verif.executerPourFonctions();
		
		assertTrue(verif.erreurs.isEmpty());
		Traducteur traducteur = new Traducteur("test", verif);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.api = APITestExcution5.class;
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls = traducteur.traduire();
		Method m = cls.getMethod("main$f_0");
		APITestExcution5 api =(APITestExcution5) cls.newInstance();
		api.a = 5;
		api.b = 5;
		Integer t = (Integer) m.invoke(api);
		
	
		assertTrue(t!=null);
		assertTrue(t==10);
		

	}

}
