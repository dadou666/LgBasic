package semantique;

public class CreationTypeAbstrait extends Erreur {
	public String nomRef;
	public String nom;
	public String toString() {
		return "Impossible dans "+nomRef+" créer une instance du type abstrait "+nom;
	}
}
