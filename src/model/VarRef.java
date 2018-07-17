package model;

public class VarRef extends Expression {
	public String nom;
	public VarRef(String nom) {
		this.nom=nom;
	}

	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);

	}

}
