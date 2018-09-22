package semantique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Appel;

public class MultipleDefinitionFonction extends Erreur {
	public Appel appel;
	public String nomFonction;
	public List<String> types;
	public List<VerificationFonction> fonctions= new ArrayList<>();
	public List<String> noms() {
		List<String> r = new ArrayList<String>();
		for(VerificationFonction vf:fonctions) {
			r.add(vf.nomFonction);
		}
		return r;
	}
}
