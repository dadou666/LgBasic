package monde;

import reader.ReadAttributes;

@ReadAttributes(names= {"x","y","ressource","suivant"})
public class RessourceSauvegarde extends RessourceSauvegardeVide{
	public int x;
	public int y;
	public Ressource ressource;
	public RessourceSauvegardeVide suivant;


}
