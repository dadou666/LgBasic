package semantique;

import model.Expression;

public class FonctionInexistante extends Erreur {
	public String nomRef;
	public String nom;
	public String toString() {
		return " la fonction "+nomRef+" est inexistante"+nom;
	}
}
