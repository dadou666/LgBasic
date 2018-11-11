package semantique;

import java.util.HashMap;
import java.util.Map;

import model.Acces;
import model.Appel;
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

	@Override
	public void visiter(Objet objet) {
		this.type = objet.type.nomRef();

	}

	@Override
	public void visiter(Appel appel) {
		if (appel.erreur) {
			return;
		}
		if (appel.vf == null) {
			this.verificateur.completer(appel);
		}
		VerificationFonction vf = appel.vf;
		if (vf == null) {
			return;
		}
		if (vf.typeRetour != null) {
			type = vf.typeRetour;
		} else {
			if (!vf.calculerTypeEnCours) {
				vf.calculerTypeEnCours = true;
				if (vf.typeRetour != null) {
					vf.typeRetour = vf.fonction.typeRetour.nomRef();
					this.type = vf.typeRetour;
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
				this.type = vf.typeRetour;
			}
		}

	}

	@Override
	public void visiter(TestType testType) {
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
		testType.alors.visiter(calculerAlors);

		CalculerTypeRetour calculerSinon = new CalculerTypeRetour();
		calculerSinon.verificateur = this.verificateur;
		calculerSinon.variables = this.variables;
		calculerSinon.nomRef = nomRef;
		testType.sinon.visiter(calculerSinon);
		if (calculerAlors.type == null && calculerSinon.type == null) {
			TypeIndetermine erreur = new TypeIndetermine();
			erreur.e = testType;
			erreur.nomRef = nomRef;
			this.verificateur.erreurs.add(erreur);
			return;

		}
		if (calculerAlors.type == null) {
			this.type = calculerAlors.type;
			return;
		}

		if (calculerSinon.type == null) {
			this.type = calculerSinon.type;
			return;
		}
		this.type = this.verificateur.superTypeCommun(calculerAlors.type, calculerSinon.type);
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
		this.type = this.verificateur.typeVar(calculer.type, acces.nom);

	}

	@Override
	public void visiter(VarRef varRef) {
		this.type = this.variables.get(varRef.nom);
		if (this.type == null) {
			ParamDef pd = this.verificateur.params.get(varRef.nom);
			if (pd != null) {
				this.type = pd.type.nomRef();
				return;
			}
			for (String typeReserve : this.verificateur.validations.keySet()) {
				TypeReserveValidation validation = this.verificateur.validations.get(typeReserve);
				if (validation != null && validation.valider(varRef.nom)) {
					this.type = typeReserve;
					return;

				}
			}
		}

	}

	@Override
	public void visiter(Literal literal) {
		this.type = literal.mots.get(0).nomRef();

	}

}
