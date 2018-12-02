package model;

import syntaxe.Afficheur;

public abstract class Expression {

	public abstract void visiter(VisiteurExpression visiteur);
	public abstract <T> T transformer(TransformationExpression<T> transformateur);
	public String toString() {
		Afficheur afficheur = new Afficheur();
		return this.transformer(afficheur);
	}

}
