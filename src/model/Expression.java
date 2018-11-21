package model;

public abstract class Expression {

	public abstract void visiter(VisiteurExpression visiteur);
	public abstract <T> T transformer(TransformationExpression<T> transformateur);

}
