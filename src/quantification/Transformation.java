package quantification;

import java.util.ArrayList;
import java.util.List;

import semantique.Verificateur;

abstract public class Transformation {
	public List<Element> elements = new ArrayList<Element>();
	abstract public void ajouterElements(Verificateur verif,Element element) ;
}
