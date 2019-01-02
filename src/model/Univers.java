package model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import syntaxe.Parseur;

public class Univers {
	public Map<String, Module> modules = new HashMap<>();
	public static Map<String, String> conversionsOp;

	public static Map<String, String> conversionsOp() {
		if (conversionsOp == null) {
			conversionsOp = new HashMap<String, String>();
			conversionsOp.put("add", "+");
			conversionsOp.put("sub", "-");
			conversionsOp.put("mul", "*");
			conversionsOp.put("sup", ">");
			conversionsOp.put("inf", "/");
			conversionsOp.put("egale", "=");

		}
		return conversionsOp;
	}

	static public Univers creerUnivers(Class classAPI, Map<Class, String> typeReserve, String chemin,
			List<String> moduleAPI) throws IOException {
		String ls[] = new File(chemin).list();
		Map<String, String> sources = new HashMap<String, String>();
		Vector<String> vector = new Vector<String>();
		for (String s : ls) {
			if (s.endsWith(".src")) {
				int i = s.indexOf(".src");
				String nom = s.substring(0, i);
				vector.add(nom);
				sources.put(nom, new String(Files.readAllBytes(Paths.get(chemin, s))));

			}
		}
		Parseur parseur = new Parseur();
		if (classAPI != null) {
			List<String> modules = new ArrayList<>();
			modules.addAll(sources.keySet());
			if (moduleAPI == null) {
				moduleAPI = new ArrayList<>();
			}
			sources = Univers.sources(classAPI, typeReserve, sources);
			for (String module : sources.keySet()) {
				if (!modules.contains(module)) {
					moduleAPI.add(module);
					vector.add(module);
				}
			}
		}
		Univers univers = parseur.lireSourceCode(sources, moduleAPI);
		if (parseur.error) {
			return null;
		}
		return univers;

	}

	public void visiter(VisiteurUnivers visiteur) {
		visiteur.visiter(this);
	}

	static public Map<String, String> sources(Class api, Map<Class, String> typeReserve, Map<String, String> result) {
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
		for (Field f : api.getDeclaredFields()) {
			if (f.getName().indexOf('$') == -1 && Modifier.isPublic(f.getModifiers())
					&& !Modifier.isStatic(f.getModifiers())) {
				String simpleName = f.getType().getSimpleName();
				Class cls = types.get(simpleName);
				if (cls == null) {
					simpleName = typeReserve.get(f.getType());
				}
				if (simpleName != null) {
					String tmp[] = simpleName.split("\\$");
					StringBuilder sb = sources.get(tmp[0]);
					if (sb == null) {
						sb = new StringBuilder();
						sources.put(tmp[0], sb);
					}
					sb.append("param ");
					sb.append(simpleName);
					sb.append(":");
					sb.append(" ");
					sb.append(f.getName());
					sb.append("\n");

				}
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
			if (Modifier.isAbstract(cls.getModifiers())) {
				sb.append("abstrait ");
			}
			sb.append("type ");

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
					} else {
						String type = typeReserve.get(field.getType());
						if (type != null) {
							sb.append(type);
							sb.append(":");
							sb.append(field.getName());
							sb.append("\n");
						}

					}
				}

			}
			sb.append("}\n");

		}
		for (Method m : api.getDeclaredMethods()) {
			if (!Modifier.isStatic(m.getModifiers())) {
				addSource(m, types, sources, typeReserve);
			}

		}
		if (result == null) {
			result = new HashMap<>();
		}
		for (Map.Entry<String, StringBuilder> e : sources.entrySet()) {
			result.put(e.getKey(), e.getValue().toString());
		}

		return result;
	}

	public static void addSource(Method m, Map<String, Class> types, Map<String, StringBuilder> sources,
			Map<Class, String> typeReserve) {
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
		String nomFonction = conversionsOp().get(tmp[1]);
		if (nomFonction != null) {
			sb.append(nomFonction);
		} else {
			sb.append(tmp[1]);
		}
		sb.append(" ");

		int idxParam = 0;
		for (Class cls : m.getParameterTypes()) {
			String type = cls.getSimpleName();
			if (types.get(type) == null) {
				type = typeReserve.get(cls);
				if (type == null) {
					return;
				}

			}
			sb.append(type);
			sb.append(":");
			sb.append("p");
			sb.append(idxParam);
			sb.append(" ");
			idxParam++;

		}
		String type = m.getReturnType().getSimpleName();
		if (types.get(type) == null) {
			type = typeReserve.get(m.getReturnType());
			if (type == null) {
				return;
			}
		}
		sb.append("->");
		sb.append(type);
		sb.append("\n");
		StringBuilder source = sources.get(tmp[0]);
		if (source == null) {
			source = new StringBuilder();
			sources.put(tmp[0], source);
		}
		source.append(sb.toString());
	}

}
