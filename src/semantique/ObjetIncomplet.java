package semantique;

import java.util.Set;

import model.Objet;

public class ObjetIncomplet extends Erreur {
	public Objet objet;
	public Set<String> absents;

}
