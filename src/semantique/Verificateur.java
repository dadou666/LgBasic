package semantique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Acces;
import model.Appel;
import model.Def;
import model.Expression;
import model.FonctionDef;
import model.Literal;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.ParamDef;
import model.Ref;
import model.RefLiteral;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;
import model.VisiteurExpression;
import quantification.Generalisation;
import quantification.Element;
import quantification.ExpressionType;

public class Verificateur implements VisiteurExpression {
	public Map<String, TypeDef> types = new HashMap<>();
	public Map<String, ParamDef> params = new HashMap<>();
	public Map<String, VerificationType> verificationTypes = new HashMap<>();
	public Map<String, List<VerificationFonction>> fonctions = new HashMap<>();
	public List<Erreur> erreurs = new ArrayList<>();
	public String nomRef;
	public Set<String> modules = new HashSet<>();
	public Map<String, TypeReserveValidation> validations = new HashMap<>();
	public Map<String, String> variables = new HashMap<>();
	public Univers univers;

	public boolean estInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	public boolean estFloat(String s) {
		try {
			Float.parseFloat(s.replace('p', '.'));
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	public Verificateur(Class clsAPI, Map<Class, String> typeReserve, String nomRepertoire) throws IOException {
		this(Univers.creerUnivers(clsAPI, typeReserve, nomRepertoire, null));
		this.executer();
	}

	public Generalisation creerGeneralisation(String fonction, String type) {
		List<VerificationFonction> vfs = this.fonctions.get(fonction);
		if (vfs.isEmpty()) {
			return null;
		}
		VerificationFonction vf = vfs.get(0);

		if (type == null) {
			return null;
		}
		Element r = new Element();
		r.et = new ExpressionType();
		r.et.expression = vf.fonction.expression;
		for (Var var : vf.fonction.params) {
			r.et.params.put(var.nom, var.type.nomRef());

		}

		Generalisation dem = new Generalisation();
		dem.element = r;
		dem.type = new Ref(type);
		dem.verificateur = this;
		return dem;

	}

	public Verificateur(Univers univers) {
		modules.add("base");

		validations.put("base$string", (String s) -> Character.isLetter(s.charAt(0)));
		validations.put("base$int", (String s) -> estInt(s));
		validations.put("base$float", (String s) -> estFloat(s));
		this.univers = univers;

	}

	public String simplifierType(String nom) {
		String tmp[] = nom.split("\\$");
		TypeDef td = null;
		for (String module : modules) {
			if (td == null) {
				td = types.get(module + "$" + tmp[1]);

			} else {
				return nom;
			}
		}
		return tmp[1];

	}

	public List<String> listeSousTypes(String t) {
		TypeDef td = types.get(t);
		List<String> sousTypes = new ArrayList<>();
		for (Map.Entry<String, TypeDef> e : types.entrySet()) {
			td = e.getValue();
			if (td.superType != null) {
				if (td.superType.nomRef().equals(t)) {
					sousTypes.add(e.getKey());
				}
			}
		}
		return sousTypes;

	}

	public void listerTypesCompatible(String t, List<String> resultat, boolean exclureTypeAbstrait) {
		TypeDef td = types.get(t);
		if (exclureTypeAbstrait) {
			if (!td.estAbstrait) {
				resultat.add(t);
			}
		} else {
			resultat.add(t);
		}

		for (Map.Entry<String, TypeDef> e : types.entrySet()) {
			td = e.getValue();
			if (td.superType != null) {
				if (td.superType.nomRef().equals(t)) {
					this.listerTypesCompatible(e.getKey(), resultat, exclureTypeAbstrait);
				}
			}
		}

	}

	public String simplifierFonction(String nom) {
		String tmp[] = nom.split("\\$");
		List<VerificationFonction> vf = null;
		for (String module : modules) {
			if (vf.isEmpty()) {
				vf = fonctions.get(module + "$" + tmp[1]);

			} else {
				return nom;
			}
		}
		return tmp[1];

	}

	public boolean trouverType(Ref ref, Class<? extends Def> classDef, String nom) {
		if (ref.moduleInit) {

			if (this.types.get(ref.nomRef()) != null) {
				ref.moduleInit = false;
				return true;
			}
			if (this.validations.get(ref.nomRef()) != null) {
				ref.moduleInit = false;
				return true;
			}

			List<String> noms = new ArrayList<>();
			String tmp = null;
			for (String module : modules) {
				String nomRefTmp = module + "$" + ref.nom;

				if (types.get(nomRefTmp) != null || this.validations.get(nomRefTmp) != null) {
					tmp = module;
					noms.add(nomRefTmp);
				}
			}
			if (noms.isEmpty()) {
				TypeInexistant typeInexistant = new TypeInexistant();
				typeInexistant.nomRef = nom;
				typeInexistant.classDef = classDef;
				typeInexistant.nom = ref.nomRef();
				typeInexistant.expression = new VarRef(ref.nom);
				erreurs.add(typeInexistant);
				return false;
			}
			if (noms.size() == 1) {
				ref.moduleInit = false;
				ref.module = tmp;

				return true;
			}
			MultipleDefinitionType erreur = new MultipleDefinitionType();
			erreur.ref = ref;
			erreur.classDef = classDef;
			erreur.nom = nom;
			erreur.types = noms;
			erreurs.add(erreur);
			return false;
		}
		boolean r = types.get(ref.nomRef()) != null || this.validations.get(ref.nomRef()) != null;
		if (!r) {
			TypeInexistant typeInexistant = new TypeInexistant();
			typeInexistant.nomRef = nom;
			typeInexistant.classDef = classDef;
			typeInexistant.nom = ref.nomRef();
			typeInexistant.expression = new VarRef(ref.nom);
			erreurs.add(typeInexistant);
			return false;
		}
		return true;
	}

	public List<VerificationFonction> recuperer(Appel appel) {
		ArrayList<VerificationFonction> rs = new ArrayList<>();
		if (appel.erreur) {
			return rs;
		}

		if (appel.nom.moduleInit) {

			List<VerificationFonction> vf = fonctions.get(appel.nomRef());
			if (vf != null && vf.size() == 1) {
				rs.add(vf.get(0));
				return rs;

			}
			if (vf != null) {
			//	rs.addAll(vf);
			}

			for (String module : modules) {
				String nomRefTmp = module + "$" + appel.nomRefPartiel();

				vf = fonctions.get(nomRefTmp);
				if (vf != null)
					for (VerificationFonction v : vf) {
						v.module = module;
						v.nomFonction = nomRefTmp;
						rs.add(v);
					}
			}
			if (rs.isEmpty()) {
				FonctionInexistante fonctionInexistante = new FonctionInexistante();
				fonctionInexistante.nom = appel.nomRef();
				fonctionInexistante.nomRef = nomRef;
				appel.erreur = true;
				erreurs.add(fonctionInexistante);
				return rs;
			}
			return rs;

		}

		List<VerificationFonction> vf = fonctions.get(appel.nomRef());
		if (vf == null || vf.isEmpty()) {
			FonctionInexistante fonctionInexistante = new FonctionInexistante();
			fonctionInexistante.nom = appel.nom.nomRef();
			fonctionInexistante.nomRef = nomRef;
			appel.erreur = true;
			erreurs.add(fonctionInexistante);
			return rs;
		}
		rs.addAll(vf);
		return rs;
	}

	public void executerPourParams() {
		List<String> noms = new ArrayList<String>();
		for (Map.Entry<String, Module> e : this.univers.modules.entrySet()) {
			Module module = e.getValue();

			for (ParamDef pd : module.params) {
				if (this.trouverType(pd.type, ParamDef.class, pd.nom)) {
					if (noms.contains(pd.nom)) {
						DoublonNomParam erreur = new DoublonNomParam();
						erreur.nom = e.getKey() + "$" + pd.nom;
						this.erreurs.add(erreur);
					} else {
						noms.add(pd.nom);
						params.put(pd.nom, pd);
					}

				}
			}

		}
	}

	public void executerPourFonctions() {
		boolean erreur = false;
		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (FonctionDef fonction : module.getValue().fonctions) {
				String nomRefTmp = module.getKey() + "$" + fonction.nom + "/" + fonction.params.size();
				List<VerificationFonction> ls = fonctions.get(nomRefTmp);
				if (fonctions.get(nomRefTmp) == null) {
					ls = new ArrayList<VerificationFonction>();
					fonctions.put(nomRefTmp, ls);

				}
				VerificationFonction vf = new VerificationFonction();
				vf.fonction = fonction;
				vf.module = module.getKey();
				fonction.idx = ls.size();
				ls.add(vf);

			}

		}
		if (erreur) {
			return;
		}
		for (Map.Entry<String, List<VerificationFonction>> vfs : fonctions.entrySet()) {
			for (VerificationFonction vf : vfs.getValue()) {
				FonctionDef fonction = vf.fonction;
				List<String> noms = new ArrayList<>();
				for (Var var : fonction.params) {
					if (noms.contains(var.nom)) {
						DoublonParamFonction e = new DoublonParamFonction();
						e.nom = var.nom;
						e.nomFonction = vfs.getKey();
						erreurs.add(e);
						erreur = true;

					} else {
						noms.add(var.nom);
					}
					if (!this.trouverType(var.type, FonctionDef.class, vfs.getKey())) {
						erreur = true;
					}
				}
				if (vf.fonction.typeRetour != null) {
					if (!this.trouverType(vf.fonction.typeRetour, FonctionDef.class, vfs.getKey())) {
						erreur = true;
					}
				}
			}
		}
		if (erreur) {
			return;
		}
		for (Map.Entry<String, List<VerificationFonction>> vfs : fonctions.entrySet()) {
			for (VerificationFonction vf : vfs.getValue()) {
				this.variables = new HashMap<String, String>();
				for (Var var : vf.fonction.params) {
					this.variables.put(var.nom, var.type.nomRef());
				}
				this.nomRef = vfs.getKey();
				if (vf.fonction.expression == null) {

					vf.typeRetour = vf.fonction.typeRetour.nomRef();
				} else {
					vf.fonction.expression.visiter(this);

				}
			}
		}
		for (Map.Entry<String, List<VerificationFonction>> vfs : fonctions.entrySet()) {
			for (VerificationFonction vf : vfs.getValue()) {
				this.variables = new HashMap<String, String>();
				for (Var var : vf.fonction.params) {
					this.variables.put(var.nom, var.type.nomRef());
				}
				this.nomRef = vfs.getKey();
				if (vf.fonction.expression == null) {

					vf.typeRetour = vf.fonction.typeRetour.nomRef();
				} else {
					// vf.getValue().fonction.expression.visiter(this);
					CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
					calculerTypeRetour.variables.putAll(variables);
					calculerTypeRetour.verificateur = this;

					vf.fonction.expression.visiter(calculerTypeRetour);
					vf.typeRetour = calculerTypeRetour.type;
				}

			}
		}
		if (this.erreurs.isEmpty()) {
			for (Map.Entry<String, List<VerificationFonction>> vfs : fonctions.entrySet()) {
				for (VerificationFonction vf : vfs.getValue()) {
					if (vf.fonction.expression != null) {
						this.variables = new HashMap<String, String>();
						for (Var var : vf.fonction.params) {
							this.variables.put(var.nom, var.type.nomRef());
						}
						ResoudreModuleRef resoudre = new ResoudreModuleRef();
						resoudre.nom = vfs.getKey();
						resoudre.variables = variables;
						resoudre.verificateur = this;
						this.nomRef = vfs.getKey();
						vf.fonction.expression.visiter(resoudre);

					}

				}
			}
		}
	}

	public void executer() {
		executerPourTypes();
		executerPourParams();
		executerPourFonctions();
	}

	public void executerPourTypes() {
		this.modules.addAll(univers.modules.keySet());

		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (TypeDef type : module.getValue().types) {

				String nom = module.getKey() + "$" + type.nom;
				if (types.get(nom) != null) {
					DoublonNomType doublon = new DoublonNomType();
					doublon.nom = nom;

					erreurs.add(doublon);

				} else {
					types.put(nom, type);
					verificationTypes.put(nom, new VerificationType());
				}

			}

		}
		for (Map.Entry<String, TypeDef> e : types.entrySet()) {

			TypeDef type = e.getValue();

			if (type.superType != null) {

				if (trouverType(type.superType, TypeDef.class, e.getKey())
						&& validations.get(type.superType.nomRef()) != null) {
					NomTypeReserve erreur = new NomTypeReserve();
					erreur.nom = type.superType.nomRef();
					erreurs.add(erreur);

				}
			}
			for (Var var : type.vars) {
				this.trouverType(var.type, TypeDef.class, e.getKey());
			}

		}
		if (!this.erreurs.isEmpty()) {
			return;
		}
		for (String nomType : types.keySet()) {
			this.verifierDoublonVar(nomType);
		}

		Map<String, List<String>> composants = new HashMap<>();
		for (Map.Entry<String, VerificationType> e : this.verificationTypes.entrySet()) {
			TypeDef typeDef = this.types.get(e.getKey());
			if (!typeDef.estAbstrait) {
				List<String> composant = new ArrayList<>();
				Ref superType = typeDef.superType;
				for (Var var : typeDef.vars) {
					if (this.validations.get(var.type.nomRef()) == null) {
						composant.add(var.type.nomRef());
					}
				}
				e.getValue().sousTypes.add(e.getKey());
				while (superType != null) {
					String nomRefTmp = superType.nomRef();
					TypeDef sousTypeDef = types.get(nomRefTmp);
					for (Var var : sousTypeDef.vars) {
						if (validations.get(var.type.nomRef()) == null) {
							composant.add(var.type.nomRef());
						}
					}
					this.verificationTypes.get(nomRefTmp).sousTypes.add(e.getKey());
					superType = this.types.get(nomRefTmp).superType;

				}
				composants.put(e.getKey(), composant);
			}

		}
		for (Map.Entry<String, VerificationType> e : this.verificationTypes.entrySet()) {
			TypeDef typeDef = this.types.get(e.getKey());
			if (!typeDef.estAbstrait) {
				List<String> composant = composants.get(e.getKey());
				for (String s : composant) {
					VerificationType vt = this.verificationTypes.get(s);
					e.getValue().composants.add(vt.sousTypes);
				}

			}

		}
		for (Map.Entry<String, VerificationType> e : this.verificationTypes.entrySet()) {
			String type = e.getKey();
			if (!this.verifierStructureArbre(new ArrayList<>(), type)) {
				ErreurTypeNonArbre erreur = new ErreurTypeNonArbre();
				erreur.nom = type;
				erreurs.add(erreur);

			}

		}

	}

	public boolean verifierStructureArbre(List<String> types, String type) {

		VerificationType vt = this.verificationTypes.get(type);
		for (List<String> typePossible : vt.composants) {
			List<String> typesNew = new ArrayList<>();
			typesNew.addAll(types);
			typesNew.add(type);
			boolean ok = false;
			for (String t : typePossible) {
				if (!typesNew.contains(t)) {
					if (this.verifierStructureArbre(typesNew, t)) {
						ok = true;
						break;
					}

				}

			}
			if (!ok) {
				return ok;
			}

		}
		return true;

	}

	public void listeVar(String nomRef, List<String> r) {
		TypeDef td = this.types.get(nomRef);
		for (Var var : td.vars) {
			r.add(var.nom);
		}
		if (td.superType != null) {
			this.listeVar(td.superType.nomRef(), r);
		}

	}

	public void listeVarAvecType(String nomRef, Map<String, String> r) {
		TypeDef td = this.types.get(nomRef);
		for (Var var : td.vars) {
			r.put(var.nom, var.type.nomRef());
		}
		if (td.superType != null) {
			this.listeVarAvecType(td.superType.nomRef(), r);
		}

	}

	public void listeVarAvecType(String nomRef, List<Var> r) {
		TypeDef td = this.types.get(nomRef);
		for (Var var : td.vars) {
			r.add(var);
		}
		if (td.superType != null) {
			this.listeVarAvecType(td.superType.nomRef(), r);
		}

	}
	public boolean estTypeVide(String nomRef) {
		TypeDef td = this.types.get(nomRef);
		if (!td.vars.isEmpty()) {
			return false;
		}
		if (td.superType != null) {
			return estTypeVide(td.superType.nomRef());
		}
		return true;
	}

	public void verifierDoublonVar(String nomType) {
		TypeDef type = this.types.get(nomType);
		List<String> nomsHeritage = new ArrayList<>();
		if (type.superType != null) {
			this.listeVar(type.superType.nomRef(), nomsHeritage);
		}
		VerificationType vt = this.verificationTypes.get(nomType);
		vt.champs.addAll(nomsHeritage);

		List<String> noms = new ArrayList<>();
		for (Var var : type.vars) {
			if (noms.contains(var.nom)) {
				DoublonChampType erreur = new DoublonChampType();
				erreur.nom = var.nom;
				erreur.nomType = nomType;
				erreur.heritage = false;
				erreurs.add(erreur);
			} else {
				noms.add(var.nom);
				if (nomsHeritage.contains(var.nom)) {
					DoublonChampType erreur = new DoublonChampType();
					erreur.nom = var.nom;
					erreur.nomType = nomType;
					erreur.heritage = true;
					erreurs.add(erreur);
				}

			}

		}
		vt.champs.addAll(noms);

	}

	@Override
	public void visiter(Objet objet) {
		if (!objet.typeOrVar.moduleDansDefininition) {
			String typeOfVar = this.variables.get(objet.typeOrVar.nom);
			if (typeOfVar != null) {
				Set<String> set = new HashSet<String>();
				for (ObjetParam op : objet.params) {
					set.add(op.nom);
				}
				List<String> list = new ArrayList<String>();
				this.listeVar(typeOfVar, list);
				for (String nom : list) {
					if (!set.contains(nom)) {
						ObjetParam op = new ObjetParam();
						Acces acces = new Acces();
						acces.cible = new VarRef(objet.typeOrVar.nom);
						acces.nom = nom;
						op.expression = acces;
						op.nom = nom;
						objet.params.add(op);
					}
				}
				objet.typeOrVar = new Ref(typeOfVar);
			}
		}
		if (!this.trouverType(objet.typeOrVar, FonctionDef.class, nomRef)) {
			return;
		}
		TypeDef td = this.types.get(objet.typeOrVar.nomRef());
		if (td != null && td.estAbstrait) {
			CreationTypeAbstrait erreur = new CreationTypeAbstrait();
			erreur.nomRef = nomRef;
			erreur.nom = objet.typeOrVar.nomRef();
			erreurs.add(erreur);
			return;
		}
		if (validations.get(objet.typeOrVar.nomRef()) != null) {
			OperationInvalideSurTypeReserve erreur = new OperationInvalideSurTypeReserve();
			erreur.nomFonction = nomRef;
			erreur.expression = objet;
			erreurs.add(erreur);
			return;

		}

		List<String> tmp = new ArrayList<String>();

		for (ObjetParam op : objet.params) {

			if (tmp.contains(op.nom)) {
				DoublonObjetParam erreur = new DoublonObjetParam();
				erreur.nom = op.nom;
				erreur.nomFonction = nomRef;
				erreur.objet = objet;
				erreurs.add(erreur);
			} else {
				tmp.add(op.nom);
			}
		}
		Set<String> set = new HashSet<String>();
		List<String> champs = this.verificationTypes.get(objet.typeOrVar.nomRef()).champs;
		for (ObjetParam op : objet.params) {
			if (!champs.contains(op.nom)) {
				AccesChampInexistant erreur = new AccesChampInexistant();
				erreur.acces = op;
				erreur.nomRef = nomRef;
				erreurs.add(erreur);

			}
			op.expression.visiter(this);
			set.add(op.nom);

		}
		if (!erreurs.isEmpty()) {
			return;
		}
		Set<String> absents = new HashSet<>();
		for (String champ : champs) {
			if (!set.contains(champ)) {
				absents.add(champ);
			}
		}
		if (!absents.isEmpty()) {
			ObjetIncomplet erreur = new ObjetIncomplet();
			erreur.absents = absents;
			erreur.objet = objet;
			this.erreurs.add(erreur);
		}

		for (ObjetParam op : objet.params) {

			CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
			calculerTypeRetour.variables = this.variables;
			calculerTypeRetour.verificateur = this;
			String type = this.typeVar(objet.typeOrVar.nomRef(), op.nom);
			op.expression.visiter(calculerTypeRetour);
			if (calculerTypeRetour.type != null && !herite(calculerTypeRetour.type, type)) {
				TypeExpressionInvalideDansObjet erreur = new TypeExpressionInvalideDansObjet();
				erreur.nomFonction = nomRef;
				erreur.expression = op.expression;
				erreur.objet = objet;
				erreur.nom = op.nom;

				erreurs.add(erreur);
			}

		}

	}

	public boolean herite(String type1, String type2) {
		if (type1.equals(type2)) {
			return true;
		}
		if (validations.get(type1) != null) {
			return false;
		}
		TypeDef td = this.types.get(type1);
		if (td.superType == null) {
			return false;
		}
		return this.herite(td.superType.nomRef(), type2);
	}

	public void completer(Appel appel, Map<String, String> variables) {

		List<VerificationFonction> ls = this.recuperer(appel);
		if (ls.isEmpty()) {
			return;
		}
		appel.erreur = true;
		List<String> appelTypes = new ArrayList<>();
		for (Expression e : appel.params) {
			CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
			calculerTypeRetour.variables = variables;
			calculerTypeRetour.verificateur = this;
			int nbErreur = this.erreurs.size();
			Map<String, String> oldVariables = this.variables;
			this.variables = variables;
			e.visiter(this);
			this.variables = oldVariables;
			if (nbErreur == this.erreurs.size()) {
				e.visiter(calculerTypeRetour);
				if (calculerTypeRetour.type == null) {
					return;
				}
				appelTypes.add(calculerTypeRetour.type);

			} else {

				return;
			}

		}
		List<VerificationFonction> lsValide = new ArrayList<>();
		for (VerificationFonction fd : ls) {
			int idx = 0;
			boolean erreur = false;
			for (Var var : fd.fonction.params) {

				if (!this.herite(appelTypes.get(idx), var.type.nomRef())) {
					erreur = true;
					break;
				}
				idx++;

			}
			if (!erreur) {
				lsValide.add(fd);
			}

		}
		if (lsValide.isEmpty()) {
			FonctionInexistante fonctionInexistante = new FonctionInexistante();
			fonctionInexistante.nom = appel.nomRef();
			fonctionInexistante.nomRef = nomRef;
			appel.erreur = true;
			erreurs.add(fonctionInexistante);
			return;

		}
		if (lsValide.size() > 1) {
			MultipleDefinitionFonction md = new MultipleDefinitionFonction();
			md.nomFonction = nomRef;
			md.appel = appel;
			md.fonctions = lsValide;
			md.types = appelTypes;
			appel.erreur = true;
			erreurs.add(md);
		}
		appel.nom.module = lsValide.get(0).module;
		appel.erreur = false;
		appel.idx = lsValide.get(0).fonction.idx;
		appel.nom.moduleInit = false;
		appel.vf = lsValide.get(0);
	}

	@Override
	public void visiter(Appel appel) {
		if (appel.vf != null) {
			return;
		}
		if (appel.erreur) {
			return;
		}
		this.completer(appel, variables);

	}

	@Override
	public void visiter(TestType testType) {
		if (!this.trouverType(testType.typeRef, FonctionDef.class, nomRef)) {
			return;
		}
		if (validations.get(testType.typeRef.nomRef()) != null) {
			OperationInvalideSurTypeReserve erreur = new OperationInvalideSurTypeReserve();
			erreur.nomFonction = nomRef;
			erreur.expression = testType;
			erreurs.add(erreur);
			return;

		}
		testType.cible.visiter(this);
		String oldType = null;
		if (testType.cible instanceof VarRef) {
			VarRef var = (VarRef) testType.cible;
			oldType = variables.get(var.nom);
			if (variables.get(var.nom) != null) {
				variables.put(var.nom, testType.typeRef.nomRef());
			}
		}
		testType.alors.visiter(this);
		if (oldType != null) {
			VarRef var = (VarRef) testType.cible;
			variables.put(var.nom, oldType);
		}
		testType.sinon.visiter(this);

	}

	@Override
	public void visiter(Acces acces) {
		if (acces.erreur) {
			return;
		}
		CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
		calculerTypeRetour.variables = this.variables;
		calculerTypeRetour.verificateur = this;

		acces.cible.visiter(this);
		if (this.erreurs.isEmpty()) {
			acces.cible.visiter(calculerTypeRetour);
			if (calculerTypeRetour.type != null) {
				if (this.validations.get(calculerTypeRetour.type) != null) {
					OperationInvalideSurTypeReserve erreur = new OperationInvalideSurTypeReserve();
					erreur.nomFonction = nomRef;
					acces.erreur = true;
					erreurs.add(erreur);
					return;

				}
				List<String> liste = this.verificationTypes.get(calculerTypeRetour.type).champs;
				if (!liste.contains(acces.nom)) {
					AccesChampInexistant erreur = new AccesChampInexistant();
					erreur.acces = acces;
					erreur.nomRef = nomRef;
					acces.erreur = true;
					erreurs.add(erreur);

				}

			}

		}

	}

	@Override
	public void visiter(VarRef varRef) {
		varRef.estLibre = this.variables.get(varRef.nom) == null && params.get(varRef.nom) == null;

	}

	public String superTypeCommun(String nomType1, String nomType2) {
		if (nomType1 == null) {
			return null;
		}
		if (nomType2 == null) {
			return null;
		}
		if (nomType1.equals(nomType2)) {
			return nomType1;
		}
		TypeDef type1 = this.types.get(nomType1);
		TypeDef type2 = this.types.get(nomType2);
		if (type1 == null || type2 == null) {
			return null;
		}
		if (type1.superType != null) {
			String r = superTypeCommun(type1.superType.nomRef(), nomType2);
			if (r != null) {
				return r;
			}
		}
		if (type2.superType != null) {
			String r = superTypeCommun(nomType1, type2.superType.nomRef());
			if (r != null) {
				return r;
			}
		}
		return null;

	}

	public String typeVar(String nomType, String var) {
		TypeDef type = this.types.get(nomType);
		for (Var v : type.vars) {
			if (v.nom.equals(var)) {
				return v.type.nomRef();
			}

		}
		if (type.superType == null) {
			return null;
		}
		return typeVar(type.superType.nomRef(), var);
	}

	@Override
	public void visiter(Literal literal) {
		if (literal.expression == null) {
			idxLiteral = 0;
			literal.expression = this.creerObjet(literal.mots);
			if (literal.expression != null) {
				// literal.expression.visiter(this);
			}

		}

	}

	public int idxLiteral;

	public Objet creerObjet(List<RefLiteral> refs) {
		RefLiteral ref = refs.get(idxLiteral);
		idxLiteral++;
		if (this.trouverType(ref, FonctionDef.class, this.nomRef)) {
			Objet objet = new Objet();
			objet.typeOrVar = ref;
			ref.type = RefLiteral.Type.Type;

			List<String> champs = this.verificationTypes.get(ref.nomRef()).champs;
			for (String champ : champs) {
				ObjetParam op = new ObjetParam();
				op.nom = champ;
				String type = this.typeVar(ref.nomRef(), champ);
				RefLiteral opRef = refs.get(idxLiteral);
				String typeVar = this.variables.get(opRef.nom);
				if (typeVar != null) {
					op.expression = new VarRef(refs.get(idxLiteral).nom);

					if (!herite(typeVar, type)) {
						TypeInvalideDansLiteral erreur = new TypeInvalideDansLiteral();
						erreur.idx = idxLiteral;
						erreur.refs = refs;
						this.erreurs.add(erreur);
						return null;
					}
					opRef.type = RefLiteral.Type.Var;
					idxLiteral++;
				} else if (this.validations.get(type) != null) {
					TypeReserveValidation validation = this.validations.get(type);
					if (validation == null) {
						CreationTypeReserve erreur = new CreationTypeReserve();
						erreur.idx = idxLiteral;
						erreur.refs = refs;
						erreur.type = type;
						this.erreurs.add(erreur);
						return null;

					}
					if (validation.valider(opRef.nom)) {
						op.expression = new VarRef(refs.get(idxLiteral).nom, true);
						opRef.type = RefLiteral.Type.Symbol;
						idxLiteral++;
					} else {
						TypeReserveInvalideDansLiteral erreur = new TypeReserveInvalideDansLiteral();

						erreur.idx = idxLiteral;
						erreur.refs = refs;
						erreur.type = type;
						this.erreurs.add(erreur);
						return null;
					}

				} else {
					int idxOld = this.idxLiteral;
					Objet o = this.creerObjet(refs);
					op.expression = o;
					if (op.expression == null) {
						return null;
					}
					if (!herite(o.typeOrVar.nomRef(), type)) {
						TypeInvalideDansLiteral erreur = new TypeInvalideDansLiteral();
						erreur.idx = idxOld;
						erreur.refs = refs;
						this.erreurs.add(erreur);
						return null;
					}
					opRef.type = RefLiteral.Type.Type;
				}
				objet.params.add(op);

			}
			return objet;
		}
		return null;

	}
}
