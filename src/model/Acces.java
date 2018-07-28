package model;

public class Acces extends Expression implements AccesChamp{
	public Expression cible;
	public String nom;
	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);
		
	}
	@Override
	public String nomChamp() {
		// TODO Auto-generated method stub
		return nom;
	}

}
