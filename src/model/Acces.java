package model;

public class Acces extends Expression {
	public Expression cible;
	public String nom;
	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);
		
	}

}
