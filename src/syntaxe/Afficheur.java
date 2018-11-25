package syntaxe;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.TestType;
import model.TransformationExpression;
import model.VarRef;

public class Afficheur implements TransformationExpression<String>{

	@Override
	public String transformer(Acces acces) {
		
		return acces.transformer(this)+"."+acces.nom;
	}

	@Override
	public String transformer(Objet objet) {
		StringBuilder sb = new StringBuilder();
		sb.append(objet.type.nomRef());
		sb.append("{");
		for(ObjetParam op:objet.params) {
			sb.append(" ");
			sb.append(op.nom);
			sb.append("=");
			sb.append(op.expression.transformer(this));
		}
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public String transformer(VarRef varRef) {
		// TODO Auto-generated method stub
		return varRef.nom;
	}

	@Override
	public String transformer(Appel appel) {
		StringBuilder sb = new StringBuilder() ;
		sb.append(appel.nom.nomRef());
		sb.append("(");
		for(Expression e:appel.params) {
			sb.append(" ");
			sb.append(e.transformer(this));
		}
		sb.append(" )");
		return sb.toString();
	}

	@Override
	public String transformer(TestType testType) {
	
		return "si "+testType.cible.transformer(this)+" "+testType.typeRef.nomRef()+" alors "+testType.alors.transformer(this)+" sinon "+testType.sinon.transformer(this);
	}

	@Override
	public String transformer(Literal literal) {
			return literal.expression.transformer(this);
	}

}
