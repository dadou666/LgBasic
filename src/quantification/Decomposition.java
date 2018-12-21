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
import semantique.Verificateur;

public class Decomposition  {
	public String var;
	public List<String> sousTypes= new ArrayList<>();
	public String type;
	public List<Element> elements = new ArrayList<>();

	

	public void ajouterElements(Verificateur verif, Element element) {

		for (String type : sousTypes) {
			Element enfant = new Element();
			for (Map.Entry<String, String> e : element.params.entrySet()) {
				if (e.getKey().equals(var)) {
					enfant.params.put(var, type);
				} else {
					enfant.params.put(e.getKey(), e.getValue());
				}
			}
			enfant.expression = element.expression;
			Simplificateur simplificateur = new Simplificateur();
			simplificateur.typesPourVariable.putAll(enfant.params);
			simplificateur.verificateur = verif;
			enfant.expression = element.expression.transformer(simplificateur);
			enfant.supprimerVariableInutilise();
			this.elements.add(enfant);
			enfant.parent = element;
		}
		if (type != null) {
			Map<String, String> map = new HashMap<>();
			verif.listeVarAvecType(type, map);
			Objet objet = new Objet();
			String tmp[] = type.split("\\$");

			objet.type = new Ref(tmp[0], tmp[1]);
			for (Map.Entry<String, String> e : map.entrySet()) {
				ObjetParam op = new ObjetParam();
				op.nom = e.getKey();
				op.expression = new VarRef(var + "_" + e.getKey());
				objet.params.add(op);
			}
			Element enfant = new Element();
			for (Map.Entry<String, String> e : element.params.entrySet()) {
				if (e.getKey().equals(var)) {
					for (Map.Entry<String, String> e2 : map.entrySet()) {
						enfant.params.put(var + "_" + e2.getKey(), e.getValue());
					}

				} else {
					enfant.params.put(e.getKey(), e.getValue());
				}

			}
			Simplificateur simplificateur = new Simplificateur();
			simplificateur.variables.put(var, objet);
			simplificateur.verificateur = verif;
			enfant.expression = element.expression.transformer(simplificateur);
			enfant.supprimerVariableInutilise();
			elements.add(enfant);
			enfant.parent = element;
		}

	}

}
