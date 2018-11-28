package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Var;
import semantique.Verificateur;

public class Element {
	public Map<String, String> params = new HashMap<>();
	public Expression expression;
	public List<Decomposition> enfants;

	public Element(Map<String,String> params, Expression expression) {
		this.params =params;
		this.expression = expression;

	}

	public void decomposer(Demonstration dem) {
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
