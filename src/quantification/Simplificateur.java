package quantification;

import java.util.HashMap;
import java.util.Map;

import model.Acces;
import model.Appel;
import model.Expression;
import model.FonctionDef;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.TransformationExpression;
import model.Var;
import model.VarRef;
import semantique.Verificateur;

public class Simplificateur implements TransformationExpression<Expression> {
	public Map<String, Expression> variables = new HashMap<>();
	public Verificateur verificateur;
	public boolean change = false;

	@Override
	public Expression transformer(Acces acces) {
		Expression cible = acces.cible.transformer(this);
		if (cible instanceof Objet) {
			Objet objet = (Objet) cible;
			for (ObjetParam op : objet.params) {
				if (op.nom.equals(acces.nom)) {
					change = true;
					return op.expression;
				}
			}

		}

		return acces;
	}

	@Override
	public Expression transformer(Objet objet) {
		Objet result = new Objet();
		result.type = objet.type;
		for (ObjetParam op : objet.params) {
			ObjetParam tmp = new ObjetParam();
			tmp.nom = op.nom;
			tmp.expression = op.expression.transformer(this);
			result.params.add(tmp);
		}
		return result;
	}

	@Override
	public Expression transformer(VarRef varRef) {
		// TODO Auto-generated method stub
		change = true;
		return this.variables.get(varRef.nom);
	}

	@Override
	public Expression transformer(Appel appel) {
		FonctionDef fd = this.verificateur.fonctions.get(appel.nom.nomRef()).fonction;
		Simplificateur simplificateur = new Simplificateur();
		simplificateur.verificateur = verificateur;
		for (int idx = 0; idx < appel.params.size(); idx++) {
			Var var = fd.params.get(idx);
			Expression e = appel.params.get(idx).transformer(this);
			if (e instanceof Objet) {
				change = true;
			}
			simplificateur.variables.put(var.nom, e);

		}
		if (change) {

			return fd.expression.transformer(simplificateur);
		}
		return appel;
	}

	@Override
	public Expression transformer(TestType testType) {
		Expression cible = testType.cible.transformer(this);
		if (cible instanceof Objet) {
			Objet objet = (Objet) cible;
			change = true;
			if ( verificateur.herite(objet.type.nomRef(), testType.typeRef.nomRef()) ) {
				
				return testType.alors.transformer(this);
			}
			return testType.sinon.transformer(this);
			
		}
		return testType;
	}

	@Override
	public Expression transformer(Literal literal) {
		// TODO Auto-generated method stub
		return literal.expression.transformer(this);
	}

}
