package model;

import java.util.ArrayList;
import java.util.List;

public class Appel extends Expression {
	public Ref nom;
	public boolean erreur = false;

	public String nomRef() {
		return nom.nomRef() + "/" + params.size();
	}

	public String nomRefPartiel() {
		return nom.nom + "/" + params.size();
	}

	public List<Expression> params = new ArrayList<>();

	@Override
	public void visiter(VisiteurExpression visiteur) {
		visiteur.visiter(this);

	}

}
