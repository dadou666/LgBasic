package model;

import java.util.ArrayList;
import java.util.List;

public class Objet extends Expression {
	public Ref typeOrVar;
	public List<ObjetParam> params= new ArrayList<>();
	@Override
	public void visiter(VisiteurExpression visiteur) {
		visiteur.visiter(this);
		
	}
	@Override
	public <T> T transformer(TransformationExpression<T> transformateur) {
		// TODO Auto-generated method stub
		return transformateur.transformer(this);
	}

}
