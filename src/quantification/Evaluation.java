package quantification;

import java.util.Map;

import model.Appel;
import semantique.Verificateur;

public class Evaluation extends Transformation {
	public String nomFonction;

	@Override
	public void ajouterElements(Verificateur verif, Element element) {
		
		
		Simplificateur simplificateur = new Simplificateur();
		simplificateur.nomFonction =nomFonction;
		Element enfant =new Element();
		for(Map.Entry<String, String> e:element.params.entrySet()) {
			enfant.params.put(e.getKey(), e.getValue());
		}
		enfant.expression = element.expression.transformer(simplificateur);
		enfant.supprimerVariableInutilise();
		this.elements.add(enfant);
		enfant.parent = element;
		
	}

}
