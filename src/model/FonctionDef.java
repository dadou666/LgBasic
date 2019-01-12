package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FonctionDef extends Def {
	
	public List<Var> params= new ArrayList<Var>();
	public Expression expression;
	public Ref typeRetour;
	public int idx=0;
	public void visiter(VisiteurModule visiteur) {
		visiteur.visiter(this);
		
	}

}
