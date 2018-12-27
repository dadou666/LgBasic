package semantique;

import model.AccesChamp;

public class AccesChampInexistant extends Erreur {
	public String nomRef;

	public AccesChamp acces;
	public String toString() {
		return "Dans "+nomRef+"Acces champ "+acces.nomChamp()+" inconnu";
	}
}
