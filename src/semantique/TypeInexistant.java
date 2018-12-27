package semantique;

import model.Def;
import model.Expression;

public class TypeInexistant extends Erreur {
	
	public Class<? extends Def> classDef;;
	public String nomRef;
	public Expression expression;
	public String nom;
	public String toString() {
		return "Dans " +nomRef+ " le type "+nom+" inexistant "; 
	}
	

}
