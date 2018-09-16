package model;

import java.util.ArrayList;
import java.util.List;

public class Objet extends Expression {
	public Ref type;
	public List<ObjetParam> params= new ArrayList<>();
	@Override
	public void visiter(VisiteurExpression visiteur) {
		visiteur.visiter(this);
		
	}

}
