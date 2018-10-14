package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.Test;

import execution.Traducteur;
import model.TypeDef;
import model.Var;

class TestExecutionJava {

	@Test
	void test() {
		String s="a b	b";
		String array[] = s.split("[ \t\n]");
	      Stack<String> stack = new Stack<>();
		for(int i=0;i< array.length;i++) {
			System.out.println( " array["+i+"]="+array[i]);
			stack.push(array[i]);
		}
		Collections.reverse(stack);
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
		
	}
	@Test
	void testCreerObjet() {
		
		
	}


}
