package semantique;

import java.util.HashMap;
import java.util.Map;

import model.Acces;
import model.Appel;
import model.Literal;
import model.Objet;
import model.TestEgalite;
import model.TestType;
import model.TypeDef;
import model.Var;
import model.VarRef;
import model.Visiteur;


public class CalculerTypeRetour implements Visiteur {
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
		VerificationFonction vf = verificateur.recuperer(appel);
		if (vf.typeRetour != null) {
			type = vf.typeRetour;
		} else {
			if (!vf.calculerTypeEnCours) {
				vf.calculerTypeEnCours = true;
				CalculerTypeRetour calculer = new CalculerTypeRetour();
				calculer.verificateur = this.verificateur;
				calculer.nomRef = appel.nom.nomRef();
			
				for (Var v : vf.fonction.params) {
					calculer.variables.put(v.nom, v.type.nomRef());

				}
				vf.fonction.expression.visiter(calculer);
				vf.typeRetour = calculer.type;
				this.type = vf.typeRetour;
			}
		}

	}

	@Override
	public void visiter(TestEgalite testEgalite) {
		CalculerTypeRetour calculer = new CalculerTypeRetour();
		calculer.verificateur = this.verificateur;
		calculer.variables = this.variables;
		calculer.nomRef = nomRef;
		testEgalite.a.visiter(calculer);

		calculer = new CalculerTypeRetour();
		calculer.verificateur = this.verificateur;
		calculer.variables = this.variables;
		calculer.nomRef = nomRef;
		testEgalite.b.visiter(calculer);

		CalculerTypeRetour calculerAlors = new CalculerTypeRetour();
		calculerAlors.verificateur = this.verificateur;
		calculerAlors.variables = this.variables;
		calculerAlors.nomRef = nomRef;
		testEgalite.alors.visiter(calculerAlors);

		CalculerTypeRetour calculerSinon = new CalculerTypeRetour();
		calculerSinon.verificateur = this.verificateur;
		calculerSinon.variables = this.variables;
		calculerSinon.nomRef = nomRef;
		testEgalite.sinon.visiter(calculerSinon);
		if (calculerAlors.type == null && calculerSinon.type == null) {
			TypeIndetermine erreur = new TypeIndetermine();
			erreur.e = testEgalite;
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
			typeIndetermine.e = testEgalite;
			this.verificateur.erreurs.add(typeIndetermine);
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
		if (calculer.type.equals(TypeDef.Symbol)) {
			AccesSurSymbol erreur = new AccesSurSymbol();
			erreur.nomFonction = nomRef;
			verificateur.erreurs.add(erreur);
			return;

		}
		this.type = this.verificateur.typeVar(calculer.type, acces.nom);
		if (type == null) {
			AccesChampInexistant erreur = new AccesChampInexistant();
			erreur.acces = acces;
			erreur.nomRef = this.nomRef;
		}

	}

	@Override
	public void visiter(VarRef varRef) {
		this.type = this.variables.get(varRef.nom);
		if (this.type == null) {
			this.type = TypeDef.Symbol;
		}

	}

	@Override
	public void visiter(Literal literal) {
		this.type = literal.mots.get(0).nomRef();

	}

}
