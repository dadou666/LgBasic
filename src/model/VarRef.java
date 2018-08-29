package model;

public class VarRef extends Expression {
	public String nom;
	public boolean estLibre = false;
	public VarRef(String nom) {
		this.nom=nom;
	}

	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);

	}

}
