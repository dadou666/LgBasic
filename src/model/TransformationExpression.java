package model;

public interface  TransformationExpression<T> {
	public T transformer(Acces acces);
	public T transformer(Objet objet);
	public T transformer(VarRef varRef);
	public T transformer(Appel appel);
	public T transformer(TestType testType);
	public T transformer(Literal literal);
	

}
