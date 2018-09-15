package semantique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import model.Visiteur;

public class Verificateur implements Visiteur {
	public Map<String, TypeDef> types = new HashMap<>();
	public Set<String> typeReserve = new HashSet<>();
	public Map<String, VerificationType> verificationTypes = new HashMap<>();
	public Map<String, VerificationFonction> fonctions = new HashMap<>();
	public List<Erreur> erreurs = new ArrayList<>();
	public String nomRef;
	public List<String> modules = new ArrayList<>();
	public Map<String, TypeReserveValidation> validations = new HashMap<>();
	public Map<String, String> variables = new HashMap<>();

	public Verificateur() {
		modules.add("base");
		this.typeReserve.add("base$symbol");

	}

	public boolean trouverType(Ref ref, boolean estFonction, String nom) {
		if (ref.moduleInit) {
			if (this.types.get(ref.nomRef()) != null) {
				return true;
			}
			if (this.typeReserve.contains(ref.nomRef())) {
				return true;
			}

			List<String> noms = new ArrayList<>();
			String tmp = null;
			for (String module : modules) {
				String nomRefTmp = module + "$" + ref.nom;

				if (types.get(nomRefTmp) != null || typeReserve.contains(nomRefTmp)) {
					tmp = module;
					noms.add(nomRefTmp);
				}
			}
			if (noms.isEmpty()) {
				TypeInexistant typeInexistant = new TypeInexistant();
				typeInexistant.nomRef = nom;
				typeInexistant.estFonction = estFonction;
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
			erreur.estFonction = estFonction;
			erreur.nom = nom;
			erreur.types = noms;
			erreurs.add(erreur);
			return false;
		}
		boolean r = types.get(ref.nomRef()) != null || typeReserve.contains(ref.nomRef());
		if (!r) {
			TypeInexistant typeInexistant = new TypeInexistant();
			typeInexistant.nomRef = nom;
			typeInexistant.estFonction = estFonction;
			typeInexistant.nom = ref.nomRef();
			typeInexistant.expression = new VarRef(ref.nom);
			erreurs.add(typeInexistant);
			return false;
		}
		return true;
	}

	public VerificationFonction recuperer(Appel appel) {
		if (appel.erreur) {
			return null;
		}
		if (appel.nom.moduleInit) {
			VerificationFonction vf = fonctions.get(appel.nomRef());
			if (vf != null) {
				return vf;

			}
			List<String> noms = new ArrayList<>();
			String tmp = null;
			for (String module : modules) {
				String nomRefTmp = module + "$" + appel.nomRefPartiel();

				vf = fonctions.get(nomRefTmp);
				if (vf != null) {
					tmp = module;
					noms.add(nomRefTmp);
				}
			}
			if (noms.isEmpty()) {
				FonctionInexistante fonctionInexistante = new FonctionInexistante();
				fonctionInexistante.nom = appel.nomRef();
				fonctionInexistante.nomRef = nomRef;
				appel.erreur = true;
				erreurs.add(fonctionInexistante);
				return null;
			}
			if (noms.size() == 1) {
				appel.nom.moduleInit = false;

				appel.nom.module = tmp;
				return vf;
			}
			MultipleDefinitionFonction md = new MultipleDefinitionFonction();
			md.nomFonction = nomRef;
			md.appel = appel;
			md.fonctions = noms;
			appel.erreur = true;
			erreurs.add(md);
			return null;

		}

		VerificationFonction vf = fonctions.get(appel.nomRef());
		if (vf == null) {
			FonctionInexistante fonctionInexistante = new FonctionInexistante();
			fonctionInexistante.nom = appel.nom.nomRef();
			fonctionInexistante.nomRef = nomRef;
			appel.erreur = true;
			erreurs.add(fonctionInexistante);
			return null;
		}
		return vf;
	}

	public void executerPourFonctions(Univers univers) {
		boolean erreur = false;
		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (FonctionDef fonction : module.getValue().fonctions) {
				String nomRefTmp = module.getKey() + "$" + fonction.nom + "/" + fonction.params.size();
				if (fonctions.get(nomRefTmp) != null) {
					DoublonNomFonction doublon = new DoublonNomFonction();
					doublon.nom = nomRefTmp;

					erreurs.add(doublon);
					erreur = true;

				} else {
					VerificationFonction vf = new VerificationFonction();
					vf.fonction = fonction;
					fonctions.put(nomRefTmp, vf);
				}
			}

		}
		if (erreur) {
			return;
		}
		for (Map.Entry<String, VerificationFonction> vf : fonctions.entrySet()) {
			FonctionDef fonction = vf.getValue().fonction;
			List<String> noms = new ArrayList<>();
			for (Var var : fonction.params) {
				if (noms.contains(var.nom)) {
					DoublonParamFonction e = new DoublonParamFonction();
					e.nom = var.nom;
					e.nomFonction = vf.getKey();
					erreurs.add(e);
					erreur = true;

				} else {
					noms.add(var.nom);
				}
				if (!this.trouverType(var.type, true, vf.getKey())) {
					erreur = true;
				}
			}
		}
		if (erreur) {
			return;
		}
		for (Map.Entry<String, VerificationFonction> vf : fonctions.entrySet()) {
			this.variables = new HashMap<String, String>();
			for (Var var : vf.getValue().fonction.params) {
				this.variables.put(var.nom, var.type.nomRef());
			}
			this.nomRef = vf.getKey();
			vf.getValue().fonction.expression.visiter(this);
			CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
			calculerTypeRetour.variables.putAll(variables);
			calculerTypeRetour.verificateur = this;
			vf.getValue().fonction.expression.visiter(calculerTypeRetour);
			vf.getValue().typeRetour = calculerTypeRetour.type;

		}
	}

	public void executerPourTypes(Univers univers) {
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

				if (trouverType(type.superType, false, e.getKey()) && typeReserve.contains(type.superType.nomRef())) {
					NomTypeReserve erreur = new NomTypeReserve();
					erreur.nom = type.superType.nomRef();
					erreurs.add(erreur);

				}
			}
			for (Var var : type.vars) {
				this.trouverType(var.type, false, e.getKey());
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
					if (!this.typeReserve.contains(var.type.nomRef())) {
						composant.add(var.type.nomRef());
					}
				}
				e.getValue().sousTypes.add(e.getKey());
				while (superType != null) {
					String nomRefTmp = superType.nomRef();
					TypeDef sousTypeDef = types.get(nomRefTmp);
					for (Var var : sousTypeDef.vars) {
						if (!typeReserve.contains(var.type.nomRef())) {
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

		if (!this.trouverType(objet.type, true, nomRef)) {
			return;
		}
		if (typeReserve.contains(objet.type.nomRef())) {
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
		List<String> champs = this.verificationTypes.get(objet.type.nomRef()).champs;
		for (ObjetParam op : objet.params) {
			if (!champs.contains(op.nom)) {
				AccesChampInexistant erreur = new AccesChampInexistant();
				erreur.acces = op;
				erreur.nomRef = nomRef;
				erreurs.add(erreur);

			}
			op.expression.visiter(this);

		}
		if (!erreurs.isEmpty()) {
			return;
		}

		for (ObjetParam op : objet.params) {
			CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
			calculerTypeRetour.variables = this.variables;
			String type = this.typeVar(objet.type.nomRef(), op.nom);
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
		if (typeReserve.contains(type1)) {
			return false;
		}
		TypeDef td = this.types.get(type1);
		if (td.superType == null) {
			return false;
		}
		return this.herite(td.superType.nomRef(), type2);
	}

	@Override
	public void visiter(Appel appel) {
		VerificationFonction fd = this.recuperer(appel);
		if (fd == null) {
			return;

		}
		int idx = 0;

		for (Expression e : appel.params) {
			Var var = fd.fonction.params.get(idx);
			idx++;
			CalculerTypeRetour calculerTypeRetour = new CalculerTypeRetour();
			calculerTypeRetour.variables = this.variables;
			calculerTypeRetour.verificateur = this;
			int nbErreur = this.erreurs.size();
			e.visiter(this);
			if (nbErreur == this.erreurs.size()) {
				e.visiter(calculerTypeRetour);
				if (calculerTypeRetour.type != null) {
					if (!this.herite(calculerTypeRetour.type, var.type.nomRef())) {
						TypeParametreFonctionInvalide erreur = new TypeParametreFonctionInvalide();
						erreur.nomFonction = this.nomRef;
						erreur.appel = appel;
						erreur.idx = idx;
						erreurs.add(erreur);
					}
				}

			}

		}

	}

	@Override
	public void visiter(TestType testType) {
		if (!this.trouverType(testType.typeRef, true, nomRef)) {
			return;
		}
		if (typeReserve.contains(testType.typeRef.nomRef())) {
			OperationInvalideSurTypeReserve erreur = new OperationInvalideSurTypeReserve();
			erreur.nomFonction = nomRef;
			erreur.expression = testType;
			erreurs.add(erreur);
			return;

		}
		testType.alors.visiter(this);
		testType.sinon.visiter(this);
		testType.cible.visiter(this);

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
				if (this.typeReserve.contains(calculerTypeRetour.type)) {
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
		varRef.estLibre = this.variables.get(varRef.nom) == null;

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

	public Objet creerObjet(List<Ref> refs) {
		Ref ref = refs.get(idxLiteral);
		idxLiteral++;
		if (this.trouverType(ref, true, this.nomRef)) {
			Objet objet = new Objet();
			objet.type = ref;

			List<String> champs = this.verificationTypes.get(ref.nomRef()).champs;
			for (String champ : champs) {
				ObjetParam op = new ObjetParam();
				op.nom = champ;
				String type = this.typeVar(ref.nomRef(), champ);
				Ref opRef = refs.get(idxLiteral);
				String typeVar = this.variables.get(opRef.nom);
				if (typeVar != null) {
					op.expression = new VarRef(refs.get(idxLiteral).nom);
					
					if (!herite(typeVar, type)) {
						TypeInvalideDansLiteral erreur = new TypeInvalideDansLiteral();
						erreur.idx = idxLiteral;
						erreur.refs =refs;
						this.erreurs.add(erreur);
						return null;
					}

					idxLiteral++;
				} else if (this.typeReserve.contains(type)) {
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
						op.expression = new VarRef(refs.get(idxLiteral).nom);
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
					Objet o =this.creerObjet(refs);
					op.expression = o;
					if (op.expression == null) {
						return null;
					}
					if (!herite(o.type.nomRef(), type)) {
						TypeInvalideDansLiteral erreur = new TypeInvalideDansLiteral();
						erreur.idx = idxOld;
						erreur.refs =refs;
						this.erreurs.add(erreur);
						return null;
					}
				}
				objet.params.add(op);

			}
			return objet;
		}
		return null;

	}
}
