package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.convert.Transformer;
import model.Expression;
import model.Objet;
import model.Var;
import semantique.Verificateur;

public class Element {
	public Map<String, String> params = new HashMap<>();
	public Element parent;
	public Expression expression;
	public List<Element> enfants;
	public boolean estFinal = false;
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean estPremier = true;
		for(Map.Entry<String, String> e:params.entrySet()) {
			if (!estPremier) {
				sb.append(" ");
			}
			
			sb.append(e.getValue());
			sb.append(":");
			sb.append(e.getKey());
			estPremier = false;
		}
		sb.append("|");
		sb.append(expression);
		return sb.toString();
		
	}

	public List<Transformation> transformations(Demonstration dem) {
		Transformeur t = new Transformeur();
		return expression.transformer(t);

	}

	public void calculerEnfants(Demonstration dem) {
		if (!estFinal) {
			if (expression instanceof Objet) {
				estFinal = true;
				return;
			}
			Element tmp  = parent;
			while(tmp != null) {
				Comparaison comparaison = new Comparaison();
				if (comparaison.comparerExpression(tmp.expression, expression)) {
					estFinal = true;
					return;
				}
				tmp = tmp.parent;
			}
			
		}
		if (enfants == null) {
			List<Transformation> r = this.transformations(dem);
			enfants = new ArrayList<>();
			for(Transformation t:r) {
				t.ajouterElements(dem.verificateur, this);
			}
			for(Element enfant:enfants) {
				enfant.parent = this;
			}
		}

	}

}
