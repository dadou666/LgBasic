package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Objet;
import model.Var;
import semantique.Verificateur;

public class Element {
	public Map<String, String> params = new HashMap<>();
	public Element parent;
	public Expression expression;
	public List<Element> enfants;
	

	

}
