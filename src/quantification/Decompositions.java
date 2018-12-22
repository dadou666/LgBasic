package quantification;

import java.util.ArrayList;
import java.util.List;

import semantique.Verificateur;

public class Decompositions extends Transformation {
	public List<Decomposition> decompositions = new ArrayList<Decomposition>();

	@Override
	public void ajouterElements(Verificateur verif, Element element) {
		for (Decomposition d : this.decompositions) {
			d.ajouterElements(verif, element);
		}

	}

	@Override
	public void ajouterElementsDansListe(List<Element> liste) {
		for(Decomposition d:this.decompositions) {
			liste.addAll(d.elements);
		}
		
	}

	@Override
	public boolean valider(ExpressionType et) {
		// TODO Auto-generated method stub
		return true;
	}

}
