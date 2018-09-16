package model;

public class RefLiteral extends Ref {
	public enum Type { Var,Symbol,Type }
	public Type type;
	public RefLiteral(String nom, int debut, int fin) {
		super(nom, debut, fin);

	}
}
