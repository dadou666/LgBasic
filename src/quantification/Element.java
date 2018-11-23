package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Var;

public class Element {
	public List<Var> params = new ArrayList<Var>();
	public Expression expression;
	public  List<Decomposition> enfants = new ArrayList<>();

	static public List<String> listeDecomposition(List<String> vars) {
		List<String> result = new ArrayList<String>();
		for (int nbVar = 1; nbVar <= vars.size(); nbVar++) {
			listeDecomposition(vars, null, 0, nbVar, result);
		}

		return result;

	}

	public	static  void listeDecomposition(List<String> vars, String base, int idx, int nbVar, List<String> results) {
		if (nbVar == 0) {
			results.add(base);
			return;
		}
		if (idx == vars.size()) {
			return;
		}

		String nvBase = null;
		if (base == null) {
			nvBase = vars.get(idx);
		} else {
			nvBase = base + "," + vars.get(idx);
		}
		listeDecomposition(vars, nvBase, idx + 1, nbVar - 1, results);
		listeDecomposition(vars, base, idx + 1, nbVar, results);

	}

}
