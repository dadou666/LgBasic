package model;

import semantique.InitModuleRef;

public class Ref {
	public int debut;
	public int fin;
	public String module;
	public String nom;
	public boolean moduleInit = false;
	public boolean moduleDansDefininition;

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
		moduleDansDefininition = (module != null);

	}

	public Ref(String nom, int debut, int fin) {

		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		moduleDansDefininition = false;
	}

	public String nomRef() {
		if (this.moduleInit) {
		//	throw new Error("module non initialisé "+this.nom);
		}
		return module + "$" + nom;
	}
}
