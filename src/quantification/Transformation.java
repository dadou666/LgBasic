package quantification;

import java.util.ArrayList;
import java.util.List;

import semantique.Verificateur;

abstract public class Transformation {

	abstract public void ajouterElements(Verificateur verif,Element element) ;
	abstract public void ajouterElementsDansListe(List<Element> liste);
}
