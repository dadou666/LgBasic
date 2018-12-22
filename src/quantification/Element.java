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
	public ExpressionType et;
	public Element parent;

	public Transformation transformation;

	public boolean estFinal = false;

	public String toString() {
		return et.toString();

	}

	public void calculerEvaluations(Generalisation dem) {
		ListerAppel t = new ListerAppel();
		t.element = this;
		et.expression.visiter(t);
		if (t.r.isEmpty()) {
			return;
		}
		Evaluation eval = new Evaluation();
		eval.appels = t.r;
		eval.et = t.courant;
		this.transformation = eval;
	}

	public void calculerDecompositions(Generalisation dem) {
		ListerVariablePourDecomposition listerVariablePourDecomposition = new ListerVariablePourDecomposition();
		Decompositions decompositions = new Decompositions();
		et.expression.visiter(listerVariablePourDecomposition);
		for (Map.Entry<String, String> e : et.params.entrySet()) {
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
			this.transformation = decompositions;
		}

	}

	public void calculerTransformations(Generalisation dem) {

		if (transformation == null) {

			this.calculerDecompositions(dem);
			if (transformation == null)
				this.calculerEvaluations(dem);

		}

	}



}
