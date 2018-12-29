package execution;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.Modifier;
import javassist.NotFoundException;
import model.Acces;
import model.Appel;
import model.Expression;
import model.FonctionDef;
import model.Literal;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.ParamDef;
import model.Ref;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;
import model.VisiteurExpression;
import model.VisiteurUnivers;
import semantique.TypeReserveValidation;
import semantique.Verificateur;
import semantique.VerificationFonction;
import semantique.VerificationType;
import test.API;

public class Traducteur implements VisiteurExpression {
	public Map<String, LiteralTraducteur> literalTracducteurs;
	public Verificateur verificateur;
	public Class api;
	public Map<String, Class> typesReserve = new HashMap<>();
	public StringBuilder source;
	public FonctionDef fonctionDef;
	public Map<String, String> indexVars;
	public Map<String, String> nomFonctions = new HashMap<>();
	public Map<String, String> varCast = new HashMap<>();
	public Map<Expression, String> tmpVars = new HashMap<>();
	public int idxTmpVar;
	public String nom;
	public Map<String, Class> mapClass = new HashMap<>();
	public LgClassLoader classLoader;

	public LgClassLoader classLoader() {
		if (classLoader == null) {
			if (api != null) {
				classLoader = new LgClassLoader(api.getClassLoader());
			} else {
				classLoader = new LgClassLoader();
			}
		}
		return classLoader;
	}

	public Object construire(String source) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException, ClasseAbsente {
		Stack<String> list = new Stack<>();
		String[] array = source.split("[ \t\n]");
		for (String s : array) {
			if (!s.isEmpty()) {
				list.add(s);
			}
		}
		Collections.reverse(list);

		return construire(list);

	}

	public String simplifier(String nom) {
		String tmp[] = nom.split("\\$");
		TypeDef td = null;
		for (String module : verificateur.modules) {
			if (td == null) {
				td = this.verificateur.types.get(module + "$" + tmp[1]);

			} else {
				return nom;
			}
		}
		return tmp[1];

	}

	public void source(Object o, StringBuilder sb)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String type = o.getClass().getSimpleName();
		TypeDef td = this.verificateur.types.get(type);
		if (td == null) {
			sb.append(" ");
			sb.append(o);
			return;
		}
		sb.append(simplifier(type));
		sb.append(" ");
		this.source(o, td, sb);

	}

	public String source(Object o)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		source(o, sb);
		return sb.toString();
	}

	public void source(Object o, TypeDef td, StringBuilder sb)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (td.superType != null) {
			this.source(o, this.verificateur.types.get(td.superType.nomRef()), sb);
		}
		for (Var var : td.vars) {
			Field f = o.getClass().getField(var.nom);
			
			source(f.get(o), sb);

		}

	}

	public Object construire(Stack<String> stack) throws ClasseAbsente, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, InstantiationException {
		String type = stack.pop();
		Ref ref = new Ref();
		ref.moduleInit = true;
		String tmp[] = type.split("\\$");
		String nomObjet;
		if (tmp.length == 2) {
			ref.nom = tmp[1];
			ref.module = tmp[0];
			ref.moduleDansDefininition =true;
			nomObjet = type;

		} else {
			ref.nom = type;
			ref.moduleDansDefininition =false;
			if (!verificateur.trouverType(ref, TypeDef.class, type)) {
				throw new ClasseAbsente(type);
			}
			nomObjet = ref.nomRef();
		}
		Class cls = mapClass.get(nomObjet);
		if (cls == null) {
			throw new ClasseAbsente(type);
		}
		Object r = cls.newInstance();
		this.initField(cls, ref, r, stack);

		return r;

	}

	public void initField(Class cls, Ref ref, Object o, Stack<String> stack) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClasseAbsente {
		TypeDef td = this.verificateur.types.get(ref.nomRef());
		if (td.superType != null) {
			initField(cls, td.superType, o, stack);
		}
		for (Var var : td.vars) {

			Field field = cls.getField(var.nom);
			field.set(o, this.construire(stack));
		}

	}

	public String traduire(Expression e) {
		if (e instanceof VarRef) {
			VarRef vr = (VarRef) e;
			if (vr.estLibre) {
				for (Map.Entry<String, TypeReserveValidation> et : verificateur.validations.entrySet()) {
					if (et.getValue().valider(vr.nom)) {
						return literalTracducteurs.get(et.getKey()).traduire(vr.nom);
					}

				}

			}
			if (this.verificateur.params.get(vr.nom) != null) {
				return vr.nom;
				
			}
			String tmpVar = this.varCast.get(vr.nom);
			if (tmpVar != null) {
				return tmpVar;
			}
			return this.indexVars.get(vr.nom);

		}
	
		return null;
	}

	public String var(String nom) {
		String tmpVar = this.varCast.get(nom);
		if (tmpVar != null) {
			return tmpVar;
		}
		return this.indexVars.get(nom);
	}

	public String source(Expression e) {
		if (e instanceof Acces) {
			Acces acces = (Acces) e;
			if (acces.cible instanceof Acces) {
				return this.source((Acces) acces.cible) + "." + acces.nom;
			}
			acces.cible.visiter(this);
			String tmpVar = tmpVars.get(acces.cible);
			if (tmpVar != null) {
				return tmpVar + "." + acces.nom;

			}
			return traduire(acces.cible) + "." + acces.nom;
		}
		String var = this.tmpVars.get(e);
		if (var != null) {
			return var;
		}
		return traduire(e);
	}

	public Traducteur(String nom, Verificateur verificateur) {
		nomFonctions.put("+", "add");
		nomFonctions.put("->", "fleche");
		nomFonctions.put("=>", "flecheEgale");
		nomFonctions.put("-", "moins");
		nomFonctions.put("*", "mul");
		nomFonctions.put("/", "div");
		nomFonctions.put(">", "sup");
		nomFonctions.put("<", "inf");
		nomFonctions.put("&", "et");
		nomFonctions.put("|", "ou");
		nomFonctions.put("==", "doubleEgale");
		nomFonctions.put("=", "egale");
		this.nom = nom;

		this.verificateur = verificateur;

	}

	public Class traduire() throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
		ClassPool classPool = this.classPool();

		CtClass resultClass = null;

		resultClass = classPool.makeClass(nom);

		if (api != null) {
			CtClass ctClass = classPool.get(api.getName());
			resultClass.setSuperclass(ctClass);
		}
		HashMap<String, CtClass> types = new HashMap<>();
		if (api != null) {
			for (Class cls : api.getDeclaredClasses()) {
				if (verificateur.types.get(cls.getSimpleName()) != null) {
					mapClass.put(cls.getSimpleName(), cls);
				}
			}
		}
		if (typesReserve != null) {
			for (Map.Entry<String, Class> e : typesReserve.entrySet()) {
				CtClass typeReserve = classPool.get(e.getValue().getName());
				types.put(e.getKey(), typeReserve);
			}
		}
		for (Map.Entry<String, TypeDef> vt : verificateur.types.entrySet()) {
			Module module = verificateur.univers.modules.get(vt.getValue().module);
			if (!module.estAPI) {
				this.traduire(resultClass, vt.getKey(), vt.getValue(), types);
			} else {
				CtClass ctClass = classPool.get(mapClass.get(vt.getKey()).getName());
				types.put(vt.getKey(), ctClass);
			}

		}
		for (Map.Entry<String, TypeDef> vt : verificateur.types.entrySet()) {
			TypeDef typeDef = vt.getValue();
			if (!verificateur.univers.modules.get(vt.getValue().module).estAPI) {
				CtClass typeClass = types.get(vt.getKey());
				for (Var var : typeDef.vars) {
					String typeVarRef = var.type.nomRef();
					CtField field = new CtField(types.get(typeVarRef), var.nom, typeClass);
					field.setModifiers(Modifier.PUBLIC);
					typeClass.addField(field);
				}
			}
		}
		for (ParamDef pd : this.verificateur.params.values()) {
			if (!verificateur.univers.modules.get(pd.module).estAPI) {
			CtField field = new CtField(types.get(pd.type.nomRef()), pd.nom, resultClass);
			field.setModifiers(Modifier.PUBLIC);
			resultClass.addField(field); }

		}

		Map<String, CtMethod> methodes = new HashMap<>();
		for (Map.Entry<String, VerificationFonction> vf : this.verificateur.fonctions.entrySet()) {
			String tmp[] = vf.getKey().split("\\$");
			if (vf.getValue().fonction.expression != null) {

				List<Var> vars = vf.getValue().fonction.params;
				CtClass typesParam[] = new CtClass[vars.size()];
				for (int i = 0; i < vars.size(); i++) {
					typesParam[i] = types.get(vars.get(i).type.nomRef());
				}
				CtClass typeRetour = types.get(vf.getValue().typeRetour);
				String nomFonction = vf.getValue().module + "$" + this.nomFonction(vf.getValue().fonction.nom);
				CtMethod m = new CtMethod(typeRetour, nomFonction, typesParam, resultClass);

				resultClass.addMethod(m);
				methodes.put(vf.getKey(), m);

			}

		}
		for (Map.Entry<String, VerificationFonction> vf : this.verificateur.fonctions.entrySet()) {
			CtMethod method = methodes.get(vf.getKey());
			if (method != null) {
				this.source = new StringBuilder();
				this.varCast.clear();
				indexVars = new HashMap<>();
				fonctionDef = vf.getValue().fonction;
				int idx = 1;
				for (Var var : fonctionDef.params) {
					indexVars.put(var.nom, "$" + idx);
					idx++;

				}
				idxTmpVar = idx;
				tmpVars = new HashMap<>();
				source.append("{");
				fonctionDef.expression.visiter(this);
				if (fonctionDef.expression instanceof Appel) {
					source.append("return ");
					source.append(this.tmpVars.get(fonctionDef.expression));
					source.append(";");
				}
				source.append("}");
				method.setBody(source.toString());
			}

		}
		resultClass.setModifiers(resultClass.getModifiers() & ~Modifier.ABSTRACT);
		for (Map.Entry<String, CtClass> e : types.entrySet()) {
			this.defineClass(types, e.getKey(), e.getValue());

		}
		byte[] bytes = resultClass.toBytecode();
		Class cls = classLoader().define(nom, bytes);
		resultClass.defrost();
		return cls;
	}
	public void defineClass(Map<String,CtClass> types,String nom , CtClass ctClass) throws IOException, CannotCompileException, ClassNotFoundException {
		TypeDef td=this.verificateur.types.get(nom);
		if (td == null) {
			return;
		}
		if (td.superType != null) {
			String nomSuperType = td.superType.nomRef();
			defineClass(types,td.superType.nomRef(),types.get(nomSuperType));
		}
		
		if (mapClass.get(nom) == null && this.typesReserve.get(nom) == null) {
			byte bytes[] = ctClass.toBytecode();

			mapClass.put(nom, classLoader().define(ctClass.getName(), bytes));
			ctClass.defrost();

		}		
		
	}

	public String nomFonction(String s) {
		String r = this.nomFonctions.get(s);
		if (r == null) {
			return s;
		}
		return r;

	}

	public CtClass traduire(CtClass resultClass, String nom, TypeDef typeDef, Map<String, CtClass> types)
			throws CannotCompileException {
		if (types.get(nom) != null) {
			return types.get(nom);
		}

		CtClass typeClass = resultClass.makeNestedClass(nom, true);
		CtConstructor defaultConstructor = CtNewConstructor.make("public " + typeClass.getSimpleName() + "() {}",
				typeClass);
		typeClass.addConstructor(defaultConstructor);
		if (typeDef.superType != null) {
			String superTypeRef = typeDef.superType.nomRef();
			typeClass.setSuperclass(
					this.traduire(resultClass, superTypeRef, this.verificateur.types.get(superTypeRef), types));
		}

		types.put(nom, typeClass);
		return typeClass;
	}

	public ClassPool classPool() {
		return ClassPool.getDefault();

	}

	public String nomObjet(Ref ref) {
		boolean estAPI = this.verificateur.univers.modules.get(ref.module).estAPI;
		String type = null;
		if (estAPI) {
			type = api.getName() + "." + ref.nomRef();
		} else {
			type = nom + "." + ref.nomRef();
		}
		return type;
	}

	public String nomFonction(Ref ref) {
		boolean estAPI = this.verificateur.univers.modules.get(ref.module).estAPI;
		String type = null;
		if (estAPI) {
			type = ref.module + "$" + this.nomFonction(ref.nom);
		} else {
			type = ref.module + "$" + this.nomFonction(ref.nom);
		}
		return type;
	}

	public String nomObjet(String nom) {
		String tmp[] = nom.split("\\$");
		boolean estAPI = this.verificateur.univers.modules.get(tmp[0]).estAPI;
		String type = null;
		if (estAPI) {
			type = api.getName() + "." + nom;
		} else {
			type = this.nom + "." + nom;
		}
		return type;
	}

	@Override
	public void visiter(Objet objet) {

		String var = "_$" + this.idxTmpVar;
		this.tmpVars.put(objet, var);
		this.idxTmpVar++;

		String type = nomObjet(objet.typeOrVar);
		this.source.append(type);
		this.source.append(" ");
		this.source.append(var);
		this.source.append("=");
		this.source.append("new ");
		this.source.append(type);
		this.source.append("();\n");
		for (ObjetParam op : objet.params) {
			op.expression.visiter(this);
			this.source.append(var);
			this.source.append(".");
			this.source.append(op.nom);
			this.source.append("=");
			this.source.append(this.source(op.expression));
			this.source.append(";");

		}
		if (fonctionDef.expression == objet) {
			source.append(";");
			source.append("return ");
			source.append(var);
			source.append(";");
		}

	}

	@Override
	public void visiter(Appel appel) {
		String var = "_$" + this.idxTmpVar;
		this.tmpVars.put(appel, var);
		this.idxTmpVar++;
		String fonction = this.nomFonction(appel.nom);
		for (Expression e : appel.params) {
			e.visiter(this);
		}
		VerificationFonction vf = this.verificateur.fonctions.get(appel.nomRef());
		String typeRetour = vf.typeRetour;
		if (typesReserve.get(typeRetour) != null) {

			typeRetour = typesReserve.get(typeRetour).getName();

		} else {
			typeRetour = this.nomObjet(typeRetour);

		}
		this.source.append(typeRetour);
		this.source.append(" ");

		this.source.append(var);
		this.source.append("=");
		this.source.append(fonction);
		this.source.append("(");
		boolean first = true;
		for (Expression e : appel.params) {
			if (!first) {
				this.source.append(",");
			}
			if (first) {
				first = !first;
			}

			this.source.append(this.source(e));

		}
		this.source.append(");\n");

	}

	@Override
	public void visiter(TestType testType) {

		String tmpVar = "_$" + this.idxTmpVar;
		this.idxTmpVar++;

		String type = nomObjet(testType.typeRef);
		Map<String, String> oldVarCast = this.varCast;
		varCast = new HashMap<>();
		varCast.putAll(oldVarCast);
		if (testType.cible instanceof VarRef) {
			VarRef var = (VarRef) testType.cible;
			this.source.append("if");
			this.source.append("  (");
			this.source.append(this.indexVars.get(var.nom));
			this.source.append(" instanceof ");
			this.source.append(type);
			this.source.append(") {");
			this.source.append(type);
			this.source.append(" ");
			this.source.append(tmpVar);
			this.source.append("=(");
			this.source.append(type);
			this.source.append(")");
			this.source.append(this.indexVars.get(var.nom));
			this.source.append(";\n");
			this.varCast.put(var.nom, tmpVar);

		} else {

			testType.visiter(this);
			this.source.append("if");
			this.source.append("  (");
			this.source.append(this.source(testType.cible));
			this.source.append(" instanceof ");
			this.source.append(type);
			this.source.append(") {");

		}

		testType.alors.visiter(this);

		this.source.append(";return ");

		this.source.append(this.source(testType.alors));

		this.source.append("; } else {");
		this.varCast = oldVarCast;
		testType.sinon.visiter(this);
		this.source.append("return ");

		this.source.append(this.source(testType.sinon));

		this.source.append("; }");

	}

	@Override
	public void visiter(Acces acces) {
		if (fonctionDef.expression != acces) {
			return;
		}
		acces.cible.visiter(this);
		this.source.append("return ");
		this.source.append(this.source(acces));
		this.source.append(";");

	}

	@Override
	public void visiter(VarRef varRef) {
		if (this.fonctionDef.expression != varRef) {
			return;
		}
		this.source.append("return ");
		this.source.append(this.traduire(varRef));
		this.source.append(";");

	}

	@Override
	public void visiter(Literal literal) {
		literal.expression.visiter(this);

	}

}
