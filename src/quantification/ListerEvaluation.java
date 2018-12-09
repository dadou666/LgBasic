package quantification;

import java.util.ArrayList;
import java.util.List;

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
import semantique.Verificateur;

public class ListerEvaluation implements TransformationExpression<List<Evaluation>> {
	
	public Element element;
	public Verificateur verificateur;

	@Override
	public List<Evaluation> transformer(Acces acces) {

		return acces.cible.transformer(this);
	}

	@Override
	public List<Evaluation> transformer(Objet objet) {

		List<Evaluation> r = new ArrayList<>();
		for (ObjetParam op : objet.params) {
			r.addAll(op.expression.transformer(this));
		}
		return r;
	}

	@Override
	public List<Evaluation> transformer(VarRef varRef) {
		List<Evaluation> r = new ArrayList<>();
	
		return r;
	}

	@Override
	public List<Evaluation> transformer(Appel appel) {
		List<Evaluation> r = new ArrayList<>();
		Evaluation e = new Evaluation();
		e.appel =appel;
		r.add(e);
		return r;
	}

	@Override
	public List<Evaluation> transformer(TestType testType) {
		List<Evaluation> r = new ArrayList<>();
		r.addAll(testType.cible.transformer(this));
		r.addAll(testType.alors.transformer(this));
		r.addAll(testType.sinon.transformer(this));
		return r;
	}

	@Override
	public List<Evaluation> transformer(Literal literal) {
		// TODO Auto-generated method stub
		return literal.expression.transformer(this);
	}

}
