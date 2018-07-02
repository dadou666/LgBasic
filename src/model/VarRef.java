package model;

public class VarRef extends Expression {
	public String nom;

	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);
		
	}

}
