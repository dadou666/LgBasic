package model;

import java.util.ArrayList;
import java.util.List;

import semantique.VerificationFonction;

public class Appel extends Expression {
	public Ref nom;
	public int idx;
	public boolean erreur = false;
	public VerificationFonction vf;

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

	@Override
	public <T> T transformer(TransformationExpression<T> transformateur) {
	
		return transformateur.transformer(this);
	}

}
