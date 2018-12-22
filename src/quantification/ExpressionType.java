package quantification;

import java.util.HashMap;
import java.util.Map;

import model.Expression;

public class ExpressionType {
	public Map<String, String> params = new HashMap<>();
	public Expression expression;

	public void supprimerVariableInutilise() {
		ListerVariable listerVariable = new ListerVariable();
		expression.visiter(listerVariable);
		HashMap<String, String> newMap = new HashMap<String, String>();

		for (String var : listerVariable.r) {
			newMap.put(var, this.params.get(var));

		}
		this.params = newMap;

	}

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

	public boolean comparer(ExpressionType et) {
		Comparaison comp = new Comparaison();
		return comp.comparerExpressionType(et, this);

	}
}
