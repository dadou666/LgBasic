package model;

public class Ref {
	public int debut;
	public int fin;
	public String module;
	public String nom;
	public boolean moduleInit = false;

	public Ref() {

	}

	public Ref(String nom,int debut,int fin) {
	
		this.nom = nom;
		this.debut = debut;
		this.fin=fin;
	}

	public String nomRef() {
			return module + "$" + nom;
	}
}
