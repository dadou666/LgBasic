package editeur;

public class Attribut {
	public String nom;
	public String type;
	public Attribut(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
		
	}
	public String toString() {
		return type+":"+nom;
	}

}
