package quantification;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.Var;
import model.VarRef;

public class Comparaison {
	public Map<String, String> map1 = new HashMap<>();
	public Map<String, String> map2 = new HashMap<>();
	public boolean comparerExpressionType(ExpressionType e1,ExpressionType e2) {
		
		map1 = new HashMap<>();
		map2 = new HashMap<>();
		
				
		if (!this.comparerExpression(e1.expression, e2.expression)) {
			return false;
		}
		for(Map.Entry<String, String> e:map1.entrySet()) {
			String type2 = e2.params.get(e.getValue());
			String type1 = e1.params.get(e.getKey());
				
			if (!type2.equals(type1)) {
				return false;
			}
		}
		return true;
	}

	public boolean comparerExpression(Expression e1, Expression e2) {
		if (e1 instanceof Acces) {
			if (e2 instanceof Acces) {
				return this.comparerAcces((Acces) e1, (Acces) e2);
			}
			return false;
		}
		if (e1 instanceof Objet) {
			if (e2 instanceof Objet) {
				return this.comparerObjet((Objet) e1, (Objet) e2);
			}
			return false;
		}
		if (e1 instanceof Appel) {
			if (e2 instanceof Appel) {
				return this.comparerAppel((Appel) e1, (Appel) e2);
			}
			return false;
		}
		if (e1 instanceof TestType) {
			if (e2 instanceof TestType) {
				return this.comaprerTestType((TestType) e1, (TestType) e2);
			}
			return false;
		}
		
		if (e1 instanceof VarRef) {
			if (e2 instanceof VarRef) {
				return this.comparerVarRef((VarRef)e1, (VarRef)e2);
			}
			return false;
		}
		if (e1 instanceof Literal) {
			if (e2 instanceof Literal) {
				return this.comparerLiteral((Literal)e1, (Literal)e2);
			}
			return false;
		}

		return false;

	}

	public boolean comparerAcces(Acces e1, Acces e2) {
		
		if (! comparerExpression(e1.cible, e2.cible)) {
			return false;
		}
		return e1.nom.equals(e2.nom);

	}

	public boolean comparerObjet(Objet o1, Objet o2) {
		if (!o1.typeOrVar.nomRef().equals(o2.typeOrVar.nomRef())) {
			return false;
		}
		
		Map<String,Expression> mapAttribut1 = new HashMap<>();
		Map<String,Expression> mapAttribut2 = new HashMap<>();
		
		Set<String> noms = new HashSet<>();
		for(ObjetParam op:o1.params) {
			noms.add(op.nom);
			mapAttribut1.put(op.nom, op.expression);
			
		}
		for(ObjetParam op:o2.params) {
			noms.add(op.nom);
			mapAttribut2.put(op.nom, op.expression);
			
		}
		
		for(String nom:noms) {
			Expression e1 = mapAttribut1.get(nom);
			Expression e2 = mapAttribut2.get(nom);
			if (e1 == null || e2 == null) {
				return false;
			}
			if (!this.comparerExpression(e1, e2)) {
				return false;
			}
			
			
		}
		
		
		return true;
	}

	public boolean comparerAppel(Appel a1, Appel a2) {
		if (!a1.nom.nomRef().equals(a2.nom.nomRef())) {
			return false;
		}
		if (a1.params.size() != a2.params.size()) {
			return false;
		}
		for(int i=0;i < a1.params.size();i++) {
			Expression e1 = a1.params.get(i);
			Expression e2 = a2.params.get(i);
			if (!this.comparerExpression(e1, e2)) {
				return false;
			}
		}
		return true;

	}

	public boolean comaprerTestType(TestType t1, TestType t2) {
		if (!t1.typeRef.nomRef().equals(t2.typeRef.nomRef())) {
			return false;
		}
		if (!this.comparerExpression(t1.cible, t2.cible)) {
			return false;
		}
		if (!this.comparerExpression(t1.alors,t2.alors)) {
			return false;
		}
		if (!this.comparerExpression(t1.sinon, t2.sinon)) {
			return false;
		}
		return true;

	}

	public boolean comparerVarRef(VarRef v1, VarRef v2) {
		String nom2 = map1.get(v1.nom);
		if (nom2 != null) {
			if (!nom2.equals(v2.nom)) {
				return false;
			}
		} else {
			map1.put(v1.nom, v2.nom);
		}
		String nom1 = map2.get(v1.nom);
		if (nom1 != null) {
			if (!nom1.equals(v1.nom)) {
				return false;
			}
			
		} else {
			map2.put(v2.nom,v1.nom);
		}
		return true;

	}
	public boolean comparerLiteral(Literal v1, Literal v2) {
		return this.comparerObjet(v1.expression, v2.expression);
		

	}

}
