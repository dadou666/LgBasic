package syntaxe;

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
import model.VarRef;
import semantique.Verificateur;

public class Afficheur implements TransformationExpression<String> {
	public Verificateur verif;
	static List<String> operateurs;

	public static List<String> operateurs() {
		if (operateurs == null) {
			operateurs = new ArrayList<>();
			operateurs.add("+");
			operateurs.add("->");
			operateurs.add("-");
			operateurs.add("*");
			operateurs.add("/");
			operateurs.add(">");
			operateurs.add("<");
			operateurs.add("&");
			operateurs.add("|");
			operateurs.add("==");
			operateurs.add("=");

		}
		return operateurs;

	}

	@Override
	public String transformer(Acces acces) {

		return acces.cible.transformer(this) + "." + acces.nom;
	}

	@Override
	public String transformer(Objet objet) {
		StringBuilder sb = new StringBuilder();
		if (verif != null) {
			sb.append(verif.simplifierType(objet.type.nomRef()));
		} else {
			sb.append(objet.type.nomRef());
		}
		sb.append("{");
		for (ObjetParam op : objet.params) {
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
		StringBuilder sb = new StringBuilder();
		if (operateurs().contains(appel.nom.nom)) {
			sb.append("(");
			sb.append(appel.params.get(0));
			sb.append(appel.nom.nom);
			sb.append(appel.params.get(1));
			sb.append(")");
			return sb.toString();
			
		}
		if (verif == null) {
			sb.append(appel.nom.nomRef());
		} else {
			sb.append(verif.simplifierFonction(appel.nom.nomRef()));
		}
		sb.append("(");
		for (Expression e : appel.params) {
			sb.append(" ");
			sb.append(e.transformer(this));
		}
		sb.append(" )");
		return sb.toString();
	}

	@Override
	public String transformer(TestType testType) {
		String nomType = testType.typeRef.nomRef();
		if (verif != null) {
			nomType = verif.simplifierType(nomType);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("si ");
		sb.append(testType.cible.transformer(this));
		sb.append(" est ");
		sb.append(nomType);
		sb.append(" alors ");
		sb.append(testType.alors.transformer(this));
		sb.append(" sinon ");
		sb.append(testType.sinon.transformer(this));
		return sb.toString();
	}

	@Override
	public String transformer(Literal literal) {
		return literal.expression.transformer(this);
	}

}
