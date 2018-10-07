package execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
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
import model.Ref;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;
import model.VisiteurExpression;
import model.VisiteurUnivers;
import semantique.Verificateur;
import semantique.VerificationFonction;
import semantique.VerificationType;
import test.API;

public class Traducteur implements VisiteurExpression {
	public Map<String, LiteralTraducteur> literalTracducteurs;
	public Verificateur verificateur;
	public Class api;
	public Map<String, Class> typesReserve;
	public StringBuilder source;
	public FonctionDef fonctionDef;
	public Map<String,String> indexVars;
	public Map<String,String> nomFonctions = new HashMap<>();
	public Map<Expression,String> tmpVars = new HashMap<>();
	public int idxTmpVar;
	public String nom;

	public Traducteur(String nom,Verificateur verificateur) {
		nomFonctions.put("+", "add");
		nomFonctions.put("->","fleche");
		nomFonctions.put("=>","flecheEgale");
		nomFonctions.put("-","moins");
		nomFonctions.put("*", "mul");
		nomFonctions.put("/","div");
		nomFonctions.put(">","sup");
		nomFonctions.put("<","inf");
		nomFonctions.put("&","et");
		nomFonctions.put("|","ou");
		nomFonctions.put("==","doubleEgale");
		nomFonctions.put("=","egale");
		this.nom=nom;
	
		this.verificateur = verificateur;

	}

	public Class traduire() throws NotFoundException, CannotCompileException {
		ClassPool classPool = this.classPool();

		CtClass resultClass = classPool.makeClass(nom);
		if (api != null) {
			CtClass ctClass = classPool.get(api.getName());
			resultClass.setSuperclass(ctClass);
		}
		HashMap<String, CtClass> types = new HashMap<>();
		
		if (typesReserve != null) {
			for(Map.Entry<String, Class> e:typesReserve.entrySet()) {
				CtClass typeReserve = classPool.get(e.getValue().getName());
				types.put(e.getKey(), typeReserve);
			}
		}
		for (Map.Entry<String, TypeDef> vt : verificateur.types.entrySet()) {
			if (!verificateur.univers.modules.get(vt.getValue().module).estAPI) {
				this.traduire(resultClass, vt.getKey(), vt.getValue(), types);
			}

		}
		for (CtClass cc : types.values()) {
			cc.toClass();
		}
		Map<String,CtMethod> methodes = new HashMap<>();
		for(Map.Entry<String, VerificationFonction> vf:this.verificateur.fonctions.entrySet()) {
			String tmp[]= vf.getKey().split("\\$");
			if (vf.getValue().fonction.expression != null) {
				
				List<Var> vars = vf.getValue().fonction.params;
				CtClass typesParam[] = new CtClass[vars.size()];
				for(int i=0;i<vars.size();i++) {
					typesParam[i] =types.get(vars.get(i).type.nomRef());
				}
				CtMethod m = new CtMethod(types.get(vf.getValue().fonction.typeRetour.nomRef()), tmp[0]+"$"+this.nomFonction(tmp[1]),
						typesParam, resultClass);
				
				resultClass.addMethod(m);
				methodes.put(vf.getKey(),m);
				
			}
			
		}
		for(Map.Entry<String, VerificationFonction> vf:this.verificateur.fonctions.entrySet()) {
			CtMethod method = methodes.get(vf.getKey()); 
			if (method != null) {
				this.source = new StringBuilder();
				indexVars = new HashMap<>();
				fonctionDef = vf.getValue().fonction;
				int idx=1;
				for(Var var:fonctionDef.params) {
					indexVars.put(var.nom, "$"+idx);
					idx++;
					
				}
				idxTmpVar=idx;
				tmpVars = new HashMap<>();
				fonctionDef.expression.visiter(this);
				method.setBody(source.toString());
			}
		
			
			
			
		}
		resultClass.setModifiers(resultClass.getModifiers() & ~Modifier.ABSTRACT);
		
		return resultClass.toClass();
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
		if (typeDef.superType != null) {
			String superTypeRef = typeDef.superType.nomRef();
			typeClass.setSuperclass(
					this.traduire(resultClass, superTypeRef, this.verificateur.types.get(superTypeRef), types));
		}
		for (Var var : typeDef.vars) {
			String typeVarRef = var.type.nomRef();
			CtField field = new CtField(typeClass, var.nom,
					this.traduire(resultClass, typeVarRef, this.verificateur.types.get(typeVarRef), types));
			typeClass.addField(field);
		}

		types.put(nom, typeClass);
		return typeClass;
	}

	public ClassPool classPool() {
		return ClassPool.getDefault();
	}

	@Override
	public void visiter(Objet objet) {
		// TODO Auto-generated method stub
		String var = "_$"+this.idxTmpVar;
		this.tmpVars.put(objet, var);
		this.idxTmpVar++;
		boolean estAPI = this.verificateur.univers.modules.get(objet.type.module).estAPI;
		String type= null;
		if (estAPI) {
			type = api.getName()+"."+objet.type.nomRef();
		} else {
			type = nom+"."+objet.type.nomRef();
		}
		
		this.source.append(type);
		this.source.append(" ");
		this.source.append(var);
		this.source.append("=");
		this.source.append("new ");
		this.source.append(type);
		this.source.append("();\n");
		for(ObjetParam op:objet.params ) {
			op.expression.visiter(this);
			this.source.append(var);
			this.source.append(".");
			this.source.append(op.nom);
			this.source.append("=");
			this.source.append(this.tmpVars.get(op.expression));
		}

	}

	@Override
	public void visiter(Appel appel) {
		String var = "_$"+this.idxTmpVar;
		this.tmpVars.put(appel, var);
		this.idxTmpVar++;
		boolean estAPI = this.verificateur.univers.modules.get(appel.nom.module).estAPI;
		String fonction= null;
		if (estAPI) {
			fonction = api.getName()+"."+appel.nom.module+"$"+this.nomFonction(appel.nom.nom);
		} else {
			fonction = nom+"."+appel.nom.module+"$"+this.nomFonction(appel.nom.nom);
		}
		for(Expression e:appel.params) {
			e.visiter(this);
		}
		VerificationFonction vf = this.verificateur.fonctions.get(appel.nom.nomRef());
		String typeRetour = vf.typeRetour;
		if (typesReserve.get(typeRetour) != null) {
			
			typeRetour = typesReserve.get(typeRetour).getName();
			
		}
		this.source.append(typeRetour);
		this.source.append(" ");
		
		this.source.append(var);
		this.source.append("=");
		this.source.append(fonction);
		this.source.append("(");
		boolean first = true;
		for(Expression e:appel.params) {
			if (!first) {
			this.source.append(",");
			}
			if (first) {
				first = !first;
			}
			e.visiter(this);
		}
		this.source.append(");\n");
		
		
		
		

	}

	@Override
	public void visiter(TestType testType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visiter(Acces acces) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visiter(VarRef varRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visiter(Literal literal) {
		// TODO Auto-generated method stub

	}

}
