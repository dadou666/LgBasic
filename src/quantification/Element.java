package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.remote.TargetedNotification;

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
	public Transformation transformation;

	public boolean estFinal = false;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (estFinal) {
			sb.append("final#");
		}
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

	public void calculerEvaluations(Generalisation dem) {
		ListerAppel t = new ListerAppel();
		expression.visiter(t);
		if (t.r.isEmpty()) {
			return;
		}
		Evaluation eval = new Evaluation();
		eval.appels = t.r;
		this.transformation = eval;
	}

	public void calculerDecompositions(Generalisation dem) {
		ListerVariablePourDecomposition listerVariablePourDecomposition = new ListerVariablePourDecomposition();
		Decompositions decompositions = new Decompositions();
		expression.visiter(listerVariablePourDecomposition);
		for (Map.Entry<String, String> e : this.params.entrySet()) {
			String var = e.getKey();
			String type = e.getValue();
			Decomposition d = null;
			if (listerVariablePourDecomposition.variablesTest.contains(var)) {
				List<String> sousTypes = dem.verificateur.listeSousTypes(type);

				if (!sousTypes.isEmpty()) {
					d = new Decomposition();
					d.var = var;
					d.sousTypes = sousTypes;

				}
			}

			TypeDef td = dem.verificateur.types.get(type);
			if (!td.estAbstrait && (listerVariablePourDecomposition.variablesTest.contains(var)
					|| listerVariablePourDecomposition.variablesAcces.contains(var))) {
				if (d == null) {
					d = new Decomposition();
				}
				d.var = var;
				d.type = type;

			}
			if (d != null) {
				decompositions.decompositions.add(d);
			}

		}
		if (!decompositions.decompositions.isEmpty()) {
			this.transformation = decompositions; }

	}

	public void calculerTransformations(Generalisation dem) {
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
		if (transformation == null) {

			this.calculerDecompositions(dem);
			if (transformation == null)
				this.calculerEvaluations(dem);

		}

	}

	public void supprimerVariableInutilise() {
		ListerVariable listerVariable = new ListerVariable();
		expression.visiter(listerVariable);
		HashMap<String, String> newMap = new HashMap<String, String>();

		for (String var : listerVariable.r) {
			newMap.put(var, this.params.get(var));

		}
		this.params = newMap;

	}

}
