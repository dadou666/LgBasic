package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

class TestJavaassist {

	@Test
	void test() throws CannotCompileException, NotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.makeClass("test.toto");
		CtClass innerClass = ctClass.makeNestedClass("lolo", true);
		Class cls2 = innerClass.toClass();
		ctClass.setSuperclass(pool.get(API.class.getName()));

		CtMethod ctMethod = CtMethod.make("public test.API.toto$m  toto() { return new test.API.toto$m();} ", ctClass);
		CtMethod ctMethod2 = CtMethod.make("public test.toto.lolo  momo() { return new test.toto.lolo();} ", ctClass);

		ctClass.addMethod(ctMethod);
		ctClass.addMethod(ctMethod2);
		Class cls = ctClass.toClass();
		test.API api = (API) cls.newInstance();
		Method m = cls.getMethod("toto");
		Object o = m.invoke(api);
		assertTrue(o.getClass() == API.toto$m.class);
		assertTrue(cls.getDeclaredClasses().length == 1);
		m = cls.getMethod("momo");
		o = m.invoke(api);
		assertTrue(o.getClass() == cls2);

	}

	@Test
	void test2() throws CannotCompileException, NotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.makeClass("test.nini");
		CtClass loloClass = ctClass.makeNestedClass("lolo", true);
		CtClass momoClass = ctClass.makeNestedClass("momo", true);
		CtField fieldA=new CtField(momoClass,"a", loloClass);
		loloClass.addField(fieldA);
		CtField fieldB=new CtField(loloClass,"b", momoClass);
		momoClass.addField(fieldB);
		loloClass.toClass();
		momoClass.toClass();
		ctClass.toClass();
		
		
		
		
	}

}
