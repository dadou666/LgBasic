package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.convert.Transformer;
import model.Expression;
import model.Objet;
import model.TypeDef;
import model.Var;
import semantique.Verificateur;

public class Element {
	public Map<String, String> params = new HashMap<>();
	public Element parent;
	public Expression expression;
	public List<Transformation> enfants;

	public boolean estFinal = false;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean estPremier = true;
		for (Map.Entry<String, String> e : params.entrySet()) {
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

	public void calculerEvaluations(Demonstration dem) {
		ListerNomFonction t = new ListerNomFonction();
		expression.visiter(t);
		for (String e : t.r) {
			Evaluation eval = new Evaluation();
			eval.nomFonction = e;
			this.enfants.add(eval);
		}
	}

	public void calculerInstanciations(Demonstration dem) {
		ListerVariablePourInstanciation listerVariablePourInstanciation = new ListerVariablePourInstanciation();
		expression.visiter(listerVariablePourInstanciation);
		for (Map.Entry<String, String> e : this.params.entrySet()) {
			String var = e.getKey();
			if (listerVariablePourInstanciation.r.contains(var)) {
				String type = e.getValue();
				TypeDef td = dem.verificateur.types.get(type);
				if (!td.estAbstrait) {
					Instanciation i = new Instanciation();
					i.var = var;
					i.type = type;
					enfants.add(i);

				}
			}
		}

	}

	public void calculerDecompositions(Demonstration dem) {

		for (Map.Entry<String, String> e : this.params.entrySet()) {
			String var = e.getKey();
			String type = e.getValue();

			List<String> sousTypes = dem.verificateur.listeSousTypes(type);
			if (!sousTypes.isEmpty()) {
				Decomposition d = new Decomposition();
				d.var = var;
				d.sousTypes = sousTypes;
				enfants.add(d);

			}
		}

	}

	public void calculerTransformations(Demonstration dem) {
		if (!estFinal) {
			if (expression instanceof Objet) {
				estFinal = true;
				return;
			}
			Element tmp = parent;
			while (tmp != null) {
				Comparaison comparaison = new Comparaison();
				if (comparaison.comparerExpression(tmp.expression, expression)) {
					estFinal = true;
					return;
				}
				tmp = tmp.parent;
			}

		}
		if (enfants == null) {

			enfants = new ArrayList<>();
			this.calculerDecompositions(dem);
			this.calculerEvaluations(dem);
			this.calculerInstanciations(dem);
		}

	}

}
