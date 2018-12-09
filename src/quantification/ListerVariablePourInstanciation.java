package quantification;

import java.util.HashSet;
import java.util.Set;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.VarRef;
import model.VisiteurExpression;

public class ListerVariablePourInstanciation implements VisiteurExpression {
	Set<String> r = new HashSet<>();

	@Override
	public void visiter(Objet objet) {
		for (ObjetParam op : objet.params) {
			op.expression.visiter(this);
		}

	}

	@Override
	public void visiter(Appel appel) {
		for (Expression e : appel.params) {
			e.visiter(this);
		}

	}

	@Override
	public void visiter(TestType testType) {
		if (testType.cible instanceof VarRef) {
			VarRef var = (VarRef) testType.cible;
			r.add(var.nom);

		} else
			testType.cible.visiter(this);
		testType.alors.visiter(this);
		testType.sinon.visiter(this);

	}

	@Override
	public void visiter(Acces acces) {
		if (acces.cible instanceof VarRef) {
			VarRef var = (VarRef) acces.cible;
		} else {
			acces.cible.visiter(this);
		}

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
