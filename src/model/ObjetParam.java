package model;

public class ObjetParam implements AccesChamp{
	public int debut;
	public int fin;
	public String nom;
	public Expression expression;
	@Override
	public String nomChamp() {
		// TODO Auto-generated method stub
		return nom;
	}

}
