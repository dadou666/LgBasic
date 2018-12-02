package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;

import model.Expression;
import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.TransformationExpression;
import model.VarRef;
import quantification.Element.Etat;

public class Decomposition {

	public String variables[];
	public List<Element> elements;
	public Element.Etat etat;

	public Etat calculerEtat() {
		if (this.etat != null) {
			return this.etat;
		}
		Element.Etat r = Element.Etat.Recursion;
		for (Element element : elements) {
			Element.Etat tmp = element.calculerEtat();
			if (tmp != Element.Etat.Recursion && tmp != Element.Etat.Final) {
				return null;
			}
			if (tmp == Element.Etat.Final) {
				r = Element.Etat.Final;
			}
		}
		this.etat =r;
		return r;
	}

	public Decomposition(String variables[], Element parent, Demonstration dem) {
		this.variables = variables;
		List<List<String>> types = new ArrayList<>();
		for (String nom : variables) {
			List<String> sousTypes = new ArrayList<>();
			dem.listeSousTypes(parent.params.get(nom), sousTypes);
			types.add(sousTypes);
		}
		List<List<String>> solutions = Demonstration.produitCartesien(types);
		elements = new ArrayList<>();
		for (List<String> solution : solutions) {
			Map<String, Expression> params = new HashMap<>();
			Map<String, String> newParams = new HashMap<>();
			for (int i = 0; i < solutions.size(); i++) {
				String type = solution.get(i);
				HashMap<String, String> attributs = new HashMap<>();
				dem.verificateur.listeVarAvecType(type, attributs);
				Objet objet = new Objet();
				String tmp[] = type.split("\\$");
				objet.type = new Ref(tmp[0], tmp[1]);
				for (String attribut : attributs.keySet()) {
					ObjetParam op = new ObjetParam();
					op.nom = attribut;
					String newVar = variables[i] + "_" + attribut;
					op.expression = new VarRef(newVar);
					newParams.put(newVar, attributs.get(newVar));

					objet.params.add(op);
				}
				params.put(variables[i], objet);

			}
			for (String nom : parent.params.keySet()) {
				if (params.get(nom) != null) {
					params.put(nom, new VarRef(nom));
					newParams.put(nom, parent.params.get(nom));
				}

			}
			Simplificateur simplificateur = new Simplificateur();
			simplificateur.verificateur = dem.verificateur;
			simplificateur.variables = params;
			Expression e = parent.expression.transformer(simplificateur);
			Element element = new Element(newParams, e, parent);
			elements.add(element);

		}

	}

}
