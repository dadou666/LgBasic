package model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Univers {
	public Map<String, Module> modules = new HashMap<>();

	public Map<String, String> sources(Class api) {
		Class classes[] = api.getDeclaredClasses();

		Map<String, Class> types = new HashMap<>();
		Map<String, StringBuilder> sources = new HashMap<>();

		for (Class cls : classes) {
			String nom = cls.getSimpleName();
			String tmp[] = nom.split("\\$");
			if (tmp.length == 2 && !tmp[0].isEmpty() && !tmp[1].isEmpty()) {

				types.put(nom, cls);

			}

		}
		for (Class cls : types.values()) {
			String nom = cls.getSimpleName();
			String tmp[] = nom.split("\\$");
			StringBuilder sb = sources.get(tmp[0]);
			if (sb == null) {
				sb = new StringBuilder();
				sources.put(tmp[0], sb);
			}
			sb.append("type ");
			if (Modifier.isAbstract(cls.getModifiers())) {
				sb.append(" abstrait ");
			}
			sb.append(tmp[1]);
			String superClassName = cls.getSuperclass().getSimpleName();
			if (types.get(superClassName) != null) {
				sb.append(":");
				sb.append(superClassName);
			}
			sb.append("{\n");

			for (Field field : cls.getDeclaredFields()) {
				if (!field.getName().contains("$")) {
					Class typeField = types.get(field.getType().getSimpleName());
					if (typeField != null) {
						sb.append(field.getType().getSimpleName());
						sb.append(":");
						sb.append(field.getName());
						sb.append("\n");
					}
				}

			}
			sb.append("}\n");

		}
		for (Method m : api.getDeclaredMethods()) {
			if (!Modifier.isStatic(m.getModifiers())) {
				addSource(m,types,sources);
			}

		}
		Map<String, String> result = new HashMap<>();
		for (Map.Entry<String, StringBuilder> e : sources.entrySet()) {
			result.put(e.getKey(), e.getValue().toString());
		}

		return result;
	}

	public static void addSource(Method m, Map<String, Class> types, Map<String, StringBuilder> sources) {
		String name = m.getName();
		String tmp[] = name.split("\\$");

		if (tmp.length != 2) {
			return;
		}
		if (tmp[0].isEmpty()) {
			return;
		}
		if (tmp[1].isEmpty()) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("fonction ");
		if (tmp[1].equals("add")) {
			sb.append("+");
		} else if (tmp[1].equals("sub")) {
			sb.append("-");
		} else if (tmp[1].equals("mul")) {
			sb.append("*");
		} else if (tmp[1].equals("sup")) {
			sb.append(">");
		} else if (tmp[1].equals("inf")) {
			sb.append("/");
		} else if (tmp[1].equals("egale")) {
			sb.append("=");
		} else {
			sb.append(tmp[1]);
		}
		sb.append(" ");

		int idxParam = 0;
		for (Class cls : m.getParameterTypes()) {
			if (types.get(cls.getSimpleName()) == null) {
				return;
			}
			sb.append(cls.getSimpleName());
			sb.append(":");
			sb.append("p");
			sb.append(idxParam);
			idxParam++;

		}
		if (types.get(m.getReturnType().getSimpleName()) == null) {
			return;
		}
		sb.append("->");
		sb.append(m.getReturnType().getSimpleName());
		sb.append("\n");
		StringBuilder source = sources.get(tmp[1]);
		if (source == null) {
			source = new StringBuilder();
			sources.put(tmp[1],source);
		}
		source.append(sb.toString());
	}

	public Univers(Class api) {

	}

}
