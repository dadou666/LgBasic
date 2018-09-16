package model;

public interface VisiteurModule extends VisiteurExpression {
	public void visiter(FonctionDef fd);
	public void visiter(TypeDef td);
	public void visiter(Module module);

}
