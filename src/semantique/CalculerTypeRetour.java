package semantique;

import java.util.HashMap;
import java.util.Map;

import model.Acces;
import model.Appel;
import model.FonctionDef;
import model.Literal;
import model.Objet;
import model.ParamDef;
import model.TestType;
import model.TypeDef;
import model.Var;
import model.VarRef;
import model.VisiteurExpression;

public class CalculerTypeRetour implements VisiteurExpression {
	public Verificateur verificateur;
	public String type;
	public Map<String, String> variables = new HashMap<>();
	public String nomRef;

	public void initType(String type) {
		if (type == null) {
			//throw new Error("type null");
		}
		this.type = type;
	}

	@Override
	public void visiter(Objet objet) {
		verificateur.trouverType(objet.typeOrVar, FonctionDef.class, nomRef);

		initType(objet.typeOrVar.nomRef());

	}

	@Override
	public void visiter(Appel appel) {
		if (appel.erreur) {
			return;
		}
		if (appel.vf == null) {
			this.verificateur.completer(appel, variables);
		}
		VerificationFonction vf = appel.vf;
		if (vf == null) {
			return;
		}
		if (vf.typeRetour != null) {
			initType(vf.typeRetour);
			return;
		}
		if (vf.calculerTypeRetour == null) {
			vf.calculerTypeRetour = this;
			if (vf.typeRetour != null) {
				vf.typeRetour = vf.fonction.typeRetour.nomRef();
				initType(vf.typeRetour);
				return;
			}
			CalculerTypeRetour calculer = new CalculerTypeRetour();
			calculer.verificateur = this.verificateur;
			calculer.nomRef = appel.nom.nomRef();

			for (Var v : vf.fonction.params) {
				calculer.variables.put(v.nom, v.type.nomRef());

			}
			if (vf.fonction.expression != null) {
				vf.fonction.expression.visiter(calculer);
				vf.typeRetour = calculer.type;
			} else {
				vf.typeRetour = vf.fonction.typeRetour.nomRef();
			}
			initType(vf.typeRetour);
			vf.calculerTypeRetour = null;
			return;
		}
		throw new CalculerTypeRetourEnCours();
		// throw new Error("pas de calcul "+vf.calculerTypeRetour);

	}

	@Override
	public void visiter(TestType testType) {
		this.verificateur.trouverType(testType.typeRef, FonctionDef.class, this.nomRef);
		CalculerTypeRetour calculer = new CalculerTypeRetour();
		calculer.verificateur = this.verificateur;
		calculer.variables = this.variables;
		calculer.nomRef = nomRef;
		testType.cible.visiter(calculer);

		CalculerTypeRetour calculerAlors = new CalculerTypeRetour();
		calculerAlors.verificateur = this.verificateur;
		calculerAlors.variables.putAll(this.variables);
		if (testType.cible instanceof VarRef) {
			VarRef var = (VarRef) testType.cible;
			if (calculerAlors.variables.get(var.nom) != null) {
				calculerAlors.variables.put(var.nom, testType.typeRef.nomRef());
			}
		}
		calculerAlors.nomRef = nomRef;
		try {
			testType.alors.visiter(calculerAlors);
		} catch (CalculerTypeRetourEnCours e) {

		}

		CalculerTypeRetour calculerSinon = new CalculerTypeRetour();
		calculerSinon.verificateur = this.verificateur;
		calculerSinon.variables = this.variables;
		calculerSinon.nomRef = nomRef;
		try {
			testType.sinon.visiter(calculerSinon);
		} catch (CalculerTypeRetourEnCours e) {

		}
		if (calculerAlors.type == null && calculerSinon.type == null) {
			TypeIndetermine erreur = new TypeIndetermine();
			erreur.e = testType;
			erreur.nomRef = nomRef;
			this.verificateur.erreurs.add(erreur);
			return;

		}
		if (calculerAlors.type == null) {
			initType(calculerSinon.type);
			return;
		}

		if (calculerSinon.type == null) {
			initType(calculerAlors.type);
			return;
		}
		initType(this.verificateur.superTypeCommun(calculerAlors.type, calculerSinon.type));
		if (this.type == null) {
			TypeIndetermine typeIndetermine = new TypeIndetermine();
			typeIndetermine.e = testType;
			this.verificateur.erreurs.add(typeIndetermine);
		}

	}

	@Override
	public void visiter(Acces acces) {
		if (acces.erreur) {
			return;
		}
		CalculerTypeRetour calculer = new CalculerTypeRetour();
		calculer.verificateur = this.verificateur;
		calculer.variables = this.variables;
		calculer.nomRef = nomRef;
		acces.cible.visiter(calculer);
		if (calculer.type == null) {
			TypeIndetermine typeIndetermine = new TypeIndetermine();
			typeIndetermine.e = acces.cible;
			typeIndetermine.nomRef = nomRef;
			this.verificateur.erreurs.add(typeIndetermine);

			return;
		}
		initType(this.verificateur.typeVar(calculer.type, acces.nom));

	}

	@Override
	public void visiter(VarRef varRef) {
		initType(this.variables.get(varRef.nom));
		if (this.type == null) {
			ParamDef pd = this.verificateur.params.get(varRef.nom);
			if (pd != null) {
				initType(pd.type.nomRef());
				return;
			}
			for (String typeReserve : this.verificateur.validations.keySet()) {
				TypeReserveValidation validation = this.verificateur.validations.get(typeReserve);
				if (validation != null && validation.valider(varRef.nom)) {
					initType(typeReserve);
					return;

				}
			}
		}

	}

	@Override
	public void visiter(Literal literal) {
		initType(literal.mots.get(0).nomRef());

	}

}
