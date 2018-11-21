package model;

public class TestType extends Test {
	public Expression cible;
	public Ref typeRef;
	@Override
	public void visiter(VisiteurExpression visiteur) {
		visiteur.visiter(this);
		
	}
	@Override
	public <T> T transformer(TransformationExpression<T> transformateur) {
		// TODO Auto-generated method stub
		return transformateur.transformer(this);
	}
	

	

}
