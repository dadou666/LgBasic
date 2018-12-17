package quantification;

import java.util.Map;
import java.util.Set;

import model.Appel;
import semantique.Verificateur;

public class Evaluation extends Transformation {
	public Set<Appel> appels;

	@Override
	public void ajouterElements(Verificateur verif, Element element) {
		
		
		Simplificateur simplificateur = new Simplificateur();
		simplificateur.appels =appels;
		Element enfant =new Element();
		for(Map.Entry<String, String> e:element.params.entrySet()) {
			enfant.params.put(e.getKey(), e.getValue());
		}
	
		simplificateur.verificateur =verif;
		enfant.expression = element.expression.transformer(simplificateur);
		enfant.supprimerVariableInutilise();
		this.elements.add(enfant);
		enfant.parent = element;
		
	}

}
