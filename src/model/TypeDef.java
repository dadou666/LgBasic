package model;

import java.util.List;

public class TypeDef extends Def {

	public boolean estAbstrait;
	
	public Ref superType;
	public List<Var> vars;
	public void visiter(VisiteurModule visiteur) {
		visiteur.visiter(this);
		
	}

}
