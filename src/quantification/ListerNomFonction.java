package quantification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.TransformationExpression;

import model.TypeDef;
import model.VarRef;
import model.VisiteurExpression;
import semantique.Verificateur;

public class ListerNomFonction  implements VisiteurExpression{
	
public Set<String> r = new HashSet<>();
	@Override
	public void visiter(Objet objet) {
		for(ObjetParam op:objet.params) {
			op.expression.visiter(this);
		}
		
		
	}
	@Override
	public void visiter(Appel appel) {
		
		r.add(appel.nomRef());
		for(Expression e:appel.params) {
			e.visiter(this);
		}
		
	}
	@Override
	public void visiter(TestType testType) {
		testType.cible.visiter(this);
		testType.alors.visiter(this);
		testType.sinon.visiter(this);
		
	}
	@Override
	public void visiter(Acces acces) {
		acces.cible.visiter(this);
		
	}
	@Override
	public void visiter(VarRef varRef) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visiter(Literal literal) {
		// TODO Auto-generated method stub
		
	}

	

}
