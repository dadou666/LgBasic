package model;

public class TestType extends Test {
	public Expression cible;
	public Ref typeRef;
	@Override
	public void visiter(VisiteurExpression visiteur) {
		visiteur.visiter(this);
		
	}

	

}
