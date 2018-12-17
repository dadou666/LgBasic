package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestTool {

	@Test
	void test() {
		List<String> l = (new reader.Factory("a  r " )).getList();
		Collections.reverse(l);
		assertTrue(l.size() == 2);
		assertTrue(l.get(0).equals("a"));
		assertTrue(l.get(1).equals("r"));
		l = (new reader.Factory(" \"qq\"  er")).getList();
		Collections.reverse(l);
		assertTrue(l.size() == 2);
		assertTrue(l.get(0).equals("qq"));
		assertTrue(l.get(1).equals("er"));
		
		l = (new reader.Factory(" \"qq\"  'er'")).getList();
		Collections.reverse(l);
		assertTrue(l.size() == 2);
		assertTrue(l.get(0).equals("qq"));
		assertTrue(l.get(1).equals("er"));
		
		l =(new reader.Factory(" \"qq\"  'er' titi.toto")).getList();
		Collections.reverse(l);
		assertTrue(l.size() == 3);
		assertTrue(l.get(0).equals("qq"));
		assertTrue(l.get(1).equals("er"));
		assertTrue(l.get(2).equals("titi.toto"));
		
	}
	@Test
 void testToObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object a =(new reader.Factory("test.Nono aa bb")).toObject();
		assertTrue(a != null);
		assertTrue(a instanceof Nono);
		Nono nono= (Nono) a;
		assertEquals(nono.a,"aa");
		assertEquals(nono.c,"bb");
		
		
	}
	
	@Test
 void testToObject2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object a =(new reader.Factory("test.Momo  aa bb 45")).toObject();
		assertTrue(a != null);
		assertTrue(a instanceof Momo);
		Momo nono= (Momo) a;
		assertEquals(nono.a,"aa");
		assertEquals(nono.c,"bb");
		assertEquals(nono.n,45);
		
		
	}
	@Test
 void testToObject3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object a =(new reader.Factory("test.Nini  test.Momo aa bb 66 45")).toObject();
		assertTrue(a != null);
		assertTrue(a instanceof Nini);
		Nini nini= (Nini) a;
		assertEquals(nini.momo.a,"aa");
		assertEquals(nini.momo.c,"bb");
		assertEquals(nini.momo.n,66);
		assertEquals(nini.a, 45);
		
		
	}

}
