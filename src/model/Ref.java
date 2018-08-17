package model;

public class Ref {
	public String module;
	public String nom;
	public boolean moduleInit = false;

	public Ref() {

	}

	public Ref(String nom) {
		moduleInit = true;
		this.nom = nom;
	}

	public String nomRef() {
		if (nom.equals("symbol")) {
			return nom;
		}
		return module + "$" + nom;
	}
}
