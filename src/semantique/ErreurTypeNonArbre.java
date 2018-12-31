package semantique;

public class ErreurTypeNonArbre extends Erreur {
	public String nom;
	public String toString() {
		return "Le type "+nom+" n'est pas un arbre ";
	}

}
