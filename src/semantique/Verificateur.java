package semantique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Acces;
import model.Appel;
import model.Literal;
import model.Module;
import model.Objet;
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

	List<Erreur> executer(Univers univers) {
		List<Erreur> erreurs = new ArrayList<>();
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
		for (Map.Entry<String, Module> module : univers.modules.entrySet()) {
			for (TypeDef type : module.getValue().types) {

				if (type.superType != null) {
					if (types.get(type.superType.nomRef()) == null) {
						TypeInexistant typeInexistant = new TypeInexistant();
						typeInexistant.def = type;
						typeInexistant.nom = type.superType.nomRef();
						typeInexistant.expression = null;
						erreurs.add(typeInexistant);

					}
					for (Var var : type.vars) {
						if (!var.type.nom.equals("symbol") && types.get(var.type.nomRef()) == null) {
							TypeInexistant typeInexistant = new TypeInexistant();
							typeInexistant.def = type;
							typeInexistant.nom = var.type.nomRef();
							typeInexistant.expression = new VarRef(var.nom);
							erreurs.add(typeInexistant);
						}
					}

				}
			}
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
	
		
		return erreurs;

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

	@Override
	public void visiter(Objet objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visiter(Appel appel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visiter(TestEgalite testEgalite) {
		// TODO Auto-generated method stub

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
