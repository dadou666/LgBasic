package model;

import java.util.ArrayList;
import java.util.List;

public class Literal extends Expression {
	public List<RefLiteral> mots= new ArrayList<RefLiteral>();
	public Objet expression;
	public Literal() {
		
	}

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
