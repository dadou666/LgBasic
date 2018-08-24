package semantique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Appel;

public class MultipleDefinitionFonction extends Erreur {
	public Appel appel;
	public String nomFonction;
	public List<String> fonctions= new ArrayList<>();
}
