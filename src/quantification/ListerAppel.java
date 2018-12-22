package quantification;

import java.lang.annotation.ElementType;
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

public class ListerAppel implements VisiteurExpression {
	public Element element;

	public ExpressionType courant;
	public Set<Appel> r = new HashSet<>();
	public boolean validerAppel(Appel appel) {
		for(Expression e:appel.params) {
			if (!(e instanceof VarRef) && !(e instanceof Objet)) {
				return false;
			}
		}
		return true;
	}

	public boolean valider(ExpressionType et) {
		Element tmp = element;

		while (tmp != null) {
			if (tmp.transformation!=null && !tmp.transformation.valider(et)) {
				return false;

			}
			tmp = tmp.parent;
		}
		return true;
	}

	@Override
	public void visiter(Objet objet) {
		for (ObjetParam op : objet.params) {
			op.expression.visiter(this);
		}

	}

	@Override
	public void visiter(Appel appel) {
		/*ExpressionType nv = new ExpressionType();
		nv.params.putAll(element.et.params);
		nv.expression = appel;
		if (this.valider(nv)) {

			if (courant == null) {
				courant = nv;
				r.add(appel);
			} else {
				if (courant.comparer(nv)) {
					r.add(appel);
				}
			}
		} else {
			System.out.println(" non valide " +nv);
		}*/
		if (this.validerAppel(appel)) {
			r.add(appel);
			return;
		}
		for (Expression e : appel.params) {
			e.visiter(this);
		}

	}

	@Override
	public void visiter(TestType testType) {
		 testType.cible.visiter(this);
		// testType.alors.visiter(this);
		// testType.sinon.visiter(this);

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
