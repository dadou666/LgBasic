package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Objet;
import model.Var;
import semantique.Verificateur;

public class Element {
	public Map<String, String> params = new HashMap<>();
	public Element parent;
	public Expression expression;
	public List<Decomposition> enfants;
	public enum Etat { Recursion,Final,Arbre }
	

	public Etat etat = null;

	public Element(Map<String, String> params, Expression expression, Element parent) {
		this.params = params;
		this.expression = expression;
		this.parent = parent;

	}
	public Etat calculerEtat() {
		if (enfants == null) {
			return etat;
		}
		for(Decomposition decomposition:this.enfants) {
			Etat r =decomposition.calculerEtat();
			if (r == Etat.Final || r==Etat.Recursion) {
				return etat;
			}
			
		}
		
		return null;
	}

	public void decomposer(Demonstration dem) throws Echec {
		if (etat == Etat.Recursion || etat == Etat.Final) {
			return ;
		}
		if (enfants == null) {
			this.initDecomposition(dem);
			return;
		}
		for (Decomposition enfant : enfants) {

			for (Element elt : enfant.elements) {
				elt.decomposer(dem);
			}
		}

	}

	public void initDecomposition(Demonstration dem) throws Echec {
		if (expression instanceof Objet) {
			Objet objet = (Objet) expression;
			if (objet.type.nomRef().equals(dem.type.nomRef())) {
				etat = Etat.Final;
				return;
			}

			throw new Echec();
		}

		if (etat == null) {
			Element tmp = parent;
			Comparaison comparaison = new Comparaison();
			while (tmp != null) {
				if (comparaison.comparerElement(tmp, this)) {
					etat = Etat.Recursion;
					return;
				}

				tmp = tmp.parent;
			}
			etat = Etat.Arbre;

		}
		if (etat != Etat.Arbre) {
			return;
		}

		List<String> noms = new ArrayList<String>();
		for (String var : params.keySet()) {
			noms.add(var);

		}

		List<String> decompositions = Demonstration.listeDecomposition(noms);
		enfants = new ArrayList<>();
		for (String decomposition : decompositions) {
			Decomposition d = new Decomposition(decomposition.split(","), this, dem);

			enfants.add(d);

		}

	}

}
