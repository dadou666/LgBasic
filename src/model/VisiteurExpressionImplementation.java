package model;

public class VisiteurExpressionImplementation implements VisiteurExpression {

	@Override
	public void visiter(Objet objet) {
		// TODO Auto-generated method stub
		for(ObjetParam op:objet.params) {
			op.expression.visiter(this);
		}

	}

	@Override
	public void visiter(Appel appel) {
		// TODO Auto-generated method stub
		for(Expression e:appel.params) {
			e.visiter(this);
		}

	}

	@Override
	public void visiter(TestType testType) {
		testType.cible.visiter(this);
		testType.alors.visiter(this);
		testType.sinon.visiter(this);
	}

	@Override
	public void visiter(Acces acces) {
		acces.cible.visiter(this);

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
