package quantification;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Appel;
import semantique.Verificateur;

public class Evaluation extends Transformation {
	public Set<Appel> appels;
	public Element element;

	@Override
	public void ajouterElements(Verificateur verif, Element parent) {
		
		
		Simplificateur simplificateur = new Simplificateur();
		simplificateur.appels =appels;
		Element enfant =new Element();
		for(Map.Entry<String, String> e:parent.params.entrySet()) {
			enfant.params.put(e.getKey(), e.getValue());
		}
	
		simplificateur.verificateur =verif;
		simplificateur.typesPourVariable.putAll(enfant.params);
		enfant.expression = parent.expression.transformer(simplificateur);
		enfant.supprimerVariableInutilise();
		element = enfant;
		enfant.parent = parent;
		
	}

	@Override
	public void ajouterElementsDansListe(List<Element> liste) {
		// TODO Auto-generated method stub
		liste.add(element);
		
	}

}
