package semantique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Acces;
import model.Appel;
import model.Expression;
import model.FonctionDef;
import model.Literal;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.TestEgalite;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;
import model.Visiteur;

public class Verificateur implements Visiteur {
	public Map<String, TypeDef> types = new HashMap<>();
	public Map<String, VerificationType> verificationTypes = new HashMap<>();
	public Map<String, VerificationFonction> fonctions = new HashMap<>();
	public List<Erreur> erreurs = new ArrayList<>();
	public String nomRef;

	void executerPourFonctions(Univers univers) {
		boolean erreur = false;
		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (FonctionDef fonction : module.getValue().fonctions) {
				String nomRef = module.getKey() + "$" + fonction.nom;
				if (fonctions.get(nomRef) != null) {
					DoublonNomFonction doublon = new DoublonNomFonction();
					doublon.nom = nomRef;

					erreurs.add(doublon);
					erreur = true;

				} else {
					VerificationFonction vf = new VerificationFonction();
					vf.fonction = fonction;
					fonctions.put(nomRef, vf);
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
				if (types.get(var.type.nomRef()) == null) {
					TypeInexistant typeInexistant = new TypeInexistant();
					typeInexistant.nomRef = vf.getKey();
					typeInexistant.nom = var.type.nomRef();
					typeInexistant.expression = new VarRef(var.nom);
					erreurs.add(typeInexistant);
					erreur = true;
				}
			}
		}
		if (erreur) {
			return;
		}
	}

	void executerPourTypes(Univers univers) {

		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (TypeDef type : module.getValue().types) {
				if (type.nom.equals("symbol")) {
					NomTypeReserve nomTypeReserve = new NomTypeReserve();
					nomTypeReserve.nom = type.nom;
				} else {
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

		}
		for (Map.Entry<String, TypeDef> e : types.entrySet()) {

			TypeDef type = e.getValue();

			if (type.superType != null) {
				if (types.get(type.superType.nomRef()) == null) {
					TypeInexistant typeInexistant = new TypeInexistant();
					typeInexistant.nomRef = e.getKey();
					typeInexistant.estFonction = false;
					typeInexistant.nom = type.superType.nomRef();
					typeInexistant.expression = null;
					erreurs.add(typeInexistant);

				}
				for (Var var : type.vars) {
					if (!var.type.nom.equals("symbol") && types.get(var.type.nomRef()) == null) {
						TypeInexistant typeInexistant = new TypeInexistant();
						typeInexistant.nomRef = e.getKey();
						typeInexistant.estFonction = false;
						typeInexistant.nom = var.type.nomRef();
						typeInexistant.expression = new VarRef(var.nom);
						erreurs.add(typeInexistant);
					}
				}

			}

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

					composant.add(var.type.nomRef());
				}
				e.getValue().sousTypes.add(e.getKey());
				while (superType != null) {
					String nomRef = superType.nomRef();
					TypeDef sousTypeDef = types.get(nomRef);
					for (Var var : sousTypeDef.vars) {

						composant.add(var.type.nomRef());
					}
					this.verificationTypes.get(nomRef).sousTypes.add(e.getKey());
					superType = this.types.get(nomRef).superType;

				}
				composants.put(e.getKey(), composant);
			}

		}
		for (Map.Entry<String, VerificationType> e : this.verificationTypes.entrySet()) {
			TypeDef typeDef = this.types.get(e.getKey());
			if (!typeDef.estAbstrait) {
				List<String> composant = composants.get(e.getKey());
				for (String s : composant) {
					e.getValue().composants.add(this.verificationTypes.get(s).sousTypes);
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
		// TODO Auto-generated method stub
		TypeDef typeDef = types.get(objet.type.nomRef());
		if (typeDef == null) {
			TypeInexistant typeInexistant = new TypeInexistant();
			typeInexistant.nomRef = nomRef;
			typeInexistant.estFonction = true;
			typeInexistant.nom = objet.type.nomRef();
			typeInexistant.expression = objet;
			erreurs.add(typeInexistant);
			return;

		}
		List<String> champs = this.verificationTypes.get(objet.type.nomRef()).champs;
		for (ObjetParam op : objet.params) {
			if (!champs.contains(op.nom)) { 
				AccesChampInexistant erreur =new AccesChampInexistant();
				erreur.acces = op;
				erreur.nomRef = nomRef;
				erreurs.add(erreur);
				
			}
				op.expression.visiter(this);
			
		}

	}

	@Override
	public void visiter(Appel appel) {
		// TODO Auto-generated method stub
		if (fonctions.get(appel.nom.nomRef()) == null) {
			FonctionInexistante fonctionInexistante = new FonctionInexistante();
			fonctionInexistante.nom = appel.nom.nomRef();
			fonctionInexistante.nomRef = nomRef;
			erreurs.add(fonctionInexistante);
			
			
		}
		for(Expression e:appel.params) {
			e.visiter(this);
		}
		

	}

	@Override
	public void visiter(TestEgalite testEgalite) {
		// TODO Auto-generated method stub
		testEgalite.a.visiter(this);
		testEgalite.b.visiter(this);
		testEgalite.alors.visiter(this);
		testEgalite.sinon.visiter(this);

	}

	@Override
	public void visiter(TestType testType) {
		// TODO Auto-generated method stub
		
		TypeDef typeDef = types.get(testType.typeRef.nomRef());
		if (typeDef == null) {
			TypeInexistant typeInexistant = new TypeInexistant();
			typeInexistant.nomRef = nomRef;
			typeInexistant.estFonction = true;
			typeInexistant.nom = testType.typeRef.nomRef();
			typeInexistant.expression = testType;
			erreurs.add(typeInexistant);
			

		}
		testType.alors.visiter(this);
		testType.sinon.visiter(this);
		testType.cible.visiter(this);
		

	}

	@Override
	public void visiter(Acces acces) {
		// TODO Auto-generated method stub
		acces.cible.visiter(this);

	}

	@Override
	public void visiter(VarRef varRef) {
		// TODO Auto-generated method stub

	}
	
	public String superTypeCommun(String nomType1,String nomType2) {
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
	public String typeVar(String nomType,String var) {
		TypeDef type  = this.types.get(nomType);
		for(Var v:type.vars) {
			if (v.nom.equals(var)) {
				return v.type.nomRef();
			}
			
		}
		if (type.superType == null) {
			return null;
		}
		return typeVar(type.superType.nomRef(),var);
	}

	@Override
	public void visiter(Literal literal) {
		// TODO Auto-generated method stub

	}
}
