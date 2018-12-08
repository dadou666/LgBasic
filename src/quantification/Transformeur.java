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

public class Transformeur implements TransformationExpression<List<Transformation>> {
	public Element element;
	public Verificateur verificateur;

	@Override
	public List<Transformation> transformer(Acces acces) {

		return acces.cible.transformer(this);
	}

	@Override
	public List<Transformation> transformer(Objet objet) {

		List<Transformation> r = new ArrayList<>();
		for (ObjetParam op : objet.params) {
			r.addAll(op.expression.transformer(this));
		}
		return r;
	}

	@Override
	public List<Transformation> transformer(VarRef varRef) {
		List<Transformation> r = new ArrayList<>();
		String var = varRef.nom;
		String type = element.params.get(var);
		TypeDef td = this.verificateur.types.get(type);
		if (!td.estAbstrait) {
			Instanciation i = new Instanciation();
			i.var = varRef.nom;
			i.type = type;
			r.add(i);

		}

		List<String> sousTypes = this.verificateur.listeSousTypes(type);
		if (!sousTypes.isEmpty()) {
			Decomposition d = new Decomposition();
			d.var = varRef.nom;
			d.sousTypes = sousTypes;
			r.add(d);

		}

		return r;
	}

	@Override
	public List<Transformation> transformer(Appel appel) {
		List<Transformation> r = new ArrayList<>();
		Evaluation evaluation = new Evaluation();
		evaluation.appel =appel;
		r.add(evaluation);
		return r;
	}

	@Override
	public List<Transformation> transformer(TestType testType) {
		List<Transformation> r = new ArrayList<>();
		r.addAll(testType.cible.transformer(this));
		r.addAll(testType.alors.transformer(this));
		r.addAll(testType.sinon.transformer(this));
		return r;
	}

	@Override
	public List<Transformation> transformer(Literal literal) {
		// TODO Auto-generated method stub
		return literal.expression.transformer(this);
	}

}
