package model;

public class Acces extends Expression implements AccesChamp{
	public int debut;
	public int fin;
	public Expression cible;
	public String nom;
	public boolean erreur=false;
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
