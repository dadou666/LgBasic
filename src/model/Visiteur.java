package model;

public interface Visiteur {
	public void visiter(Objet objet);
	public void visiter(Appel appel);

	public void visiter(TestType testType);
	public void visiter(Acces acces);
	public void visiter(VarRef varRef);
	public void visiter(Literal literal);

}
