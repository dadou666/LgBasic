package model;

public class Ref {
	public int debut;
	public int fin;
	public String module;
	public String nom;
	public boolean moduleInit = false;

	public Ref() {

	}

	public Ref(String nom) {

		String tmp[] = nom.split("\\$");

		if (tmp.length == 2) {
			this.module = tmp[0];
			this.nom = tmp[1];
		} else {
			this.nom = nom;
		}

	}

	public Ref(String module, String nom) {
		this.nom = nom;
		this.module = module;

	}

	public Ref(String nom, int debut, int fin) {

		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
	}

	public String nomRef() {
		return module + "$" + nom;
	}
}
