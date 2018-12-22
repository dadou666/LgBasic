package quantification;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Appel;
import semantique.Verificateur;

public class Evaluation extends Transformation {
	public Set<Appel> appels;
	public Element element;
	public ExpressionType et;

	@Override
	public boolean valider(ExpressionType etTest) {

		return !et.comparer(etTest);
	}

	@Override
	public void ajouterElements(Verificateur verif, Element parent) {

		Simplificateur simplificateur = new Simplificateur();
		simplificateur.appels = appels;
		Element enfant = new Element();
		enfant.et = new ExpressionType();
		for (Map.Entry<String, String> e : parent.et.params.entrySet()) {
			enfant.et.params.put(e.getKey(), e.getValue());
		}

		simplificateur.verificateur = verif;
		simplificateur.typesPourVariable.putAll(enfant.et.params);
		enfant.et.expression = parent.et.expression.transformer(simplificateur);
		enfant.et.supprimerVariableInutilise();
		element = enfant;
		enfant.parent = parent;

	}

	@Override
	public void ajouterElementsDansListe(List<Element> liste) {
		// TODO Auto-generated method stub
		liste.add(element);

	}

}
