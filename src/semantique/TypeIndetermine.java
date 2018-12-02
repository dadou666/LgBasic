package semantique;

import model.Expression;

public class TypeIndetermine extends Erreur {
	public String nomRef;
	public Expression e;
	public String toString() {
		return "Type "+nomRef+" indetermine dans : "+e;
	}

}
