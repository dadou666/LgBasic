package model;

public class VarRef extends Expression {
	public int debut;
	public int fin;
	public String nom;
	public boolean estLibre = false;
	public VarRef(String nom) {
		this.nom=nom;
	}
	public VarRef(String nom,boolean estLibre) {
		this.nom=nom;
		this.estLibre =estLibre;
	}
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
