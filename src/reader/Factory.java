package reader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Factory {
	public Map<String, String> mapping;
	private List<String> list;
	public List<String> getList() {
		return list;
	}


	public Factory(String src) {

		this.list =toList(src);
		Collections.reverse(list);
		
	}
	public void initSrc(String src) {
		this.list =toList(src);
		Collections.reverse(list);
		
	}

	 private List<String> toList(String src) {
		List<String> r = new ArrayList<String>();
		int idx = 0;
		StringBuilder sb = null;
		Character stringChar = null;
		while (idx < src.length()) {
			char c = src.charAt(idx);
			if (stringChar == null) {
				if (c != ' ' && c != '\n' && c != '	' && c != '"' && c != '\'') {
					if (sb == null) {
						sb = new StringBuilder();
					}
					sb.append(c);
				} else {
					if (sb != null) {
						r.add(sb.toString());
						sb = null;
					}
					if (c == '"') {
						stringChar = c;
					}

				}
			} else {
				if (c != stringChar) {
					if (sb == null) {
						sb = new StringBuilder();
					}
					sb.append(c);

				} else {
					stringChar = null;
					if (sb != null) {
						r.add(sb.toString());
						sb = null;
					} else {
						r.add("");
					}

				}

			}
			idx++;
		}
		if (sb != null) {
			r.add(sb.toString());
			sb = null;
		}

		return r;
	}

	 public Object toObject( ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Stack<String> stack= new Stack<String>();
	 
	    stack.addAll(list);
		return toObject(stack,null);

	}
	 public Object literal(String value,Class cls) {
			if (cls == String.class) {
				return value;
			}
			if (cls == Integer.class) {
				return Integer.parseInt(value);
			}
			if (cls == int.class) {
				return Integer.parseInt(value);
			}
			return null;
		 
	 }

	 private Object toObject(Stack<String> stack,Class targetCls) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String className = stack.pop();
		Object value = literal(className, targetCls);
		if (value != null) {
			return value;
		}
		if (mapping != null) {
			String tmp = mapping.get(className);
			if (tmp != null) {
				className = tmp;
			}
		}
		Class cls = Class.forName(className);
		Object r = cls.newInstance();
		initObject(r,cls,stack);
		return r;

	}
	 private  void initObject(Object obj,Class cls, Stack<String> stack) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException, SecurityException {
		if (cls == null) {
			return;
		}
		initObject(obj,cls.getSuperclass(),stack);
		ReadAttributes anotation = (ReadAttributes) cls.getAnnotation(ReadAttributes.class);
		if (anotation != null) {
			for (String name : anotation.names()) {
				Field field = cls.getField(name);
				field.set(obj, toObject( stack,field.getType()));

			}

		}
		
		
	}

}
