package quantification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Ref;
import model.TypeDef;
import model.Univers;
import semantique.Verificateur;

public class Generalisation {
	public Element element;
	public Ref type;
	public Verificateur verificateur;
	public List<Element> enfants;
	public void calculer() {
		if (enfants == null) {
			enfants = new ArrayList<Element>();
			enfants.add(element);
		}
		List<Element> tmp = new ArrayList<>();
		for(Element enfant:enfants) {
			enfant.calculerTransformations(this);
			for(Transformation t:enfant.enfants) {
				t.ajouterElements(verificateur, enfant);
				tmp.addAll(t.elements);
			}
		}
		this.enfants = tmp;
		
	}

}
