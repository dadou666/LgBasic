package model;

public class Ref {
	public String module;
	public String nom;
	public boolean moduleInit = false;

	public Ref() {

	}

	public Ref(String nom) {
	
		this.nom = nom;
	}

	public String nomRef() {
			return module + "$" + nom;
	}
}
