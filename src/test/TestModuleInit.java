package test;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.VarRef;
import model.VisiteurExpression;
import model.VisiteurExpressionImplementation;

public class TestModuleInit extends VisiteurExpressionImplementation {

	@Override
	public void visiter(Objet objet) {
		super.visiter(objet);
		objet.type.nomRef();

	}

	@Override
	public void visiter(Appel appel) {
		super.visiter(appel);
		appel.nom.nomRef();

	}

	@Override
	public void visiter(TestType testType) {
		super.visiter(testType);
		testType.typeRef.nomRef();
	}

	@Override
	public void visiter(Acces acces) {
		super.visiter(acces);

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
