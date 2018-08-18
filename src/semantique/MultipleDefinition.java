package semantique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Appel;

public class MultipleDefinition extends Erreur {
	public Appel appel;
	public String nomFonction;
	public Set<String> fonctions= new HashSet<>();
}
