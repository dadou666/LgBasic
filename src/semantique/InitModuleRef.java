package semantique;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.Ref;

import model.TestType;
import model.VarRef;
import model.VisiteurExpression;

public class InitModuleRef implements VisiteurExpression{
	public String module;

	@Override
	public void visiter(Objet objet) {

		if (objet.type.module == null) {
			objet.type.module = module;
			objet.type.moduleInit = true;
		}
		for(ObjetParam op:objet.params) {
			if (op.expression == null) {
				return;
			}
			op.expression.visiter(this);
		}
		
	}

	@Override
	public void visiter(Appel appel) {
		if (appel.nom.module == null) {
			appel.nom.module = module;
			appel.nom.moduleInit = true;
		}
		for(Expression e:appel.params) {
			e.visiter(this);
		}
		
		
	}


	@Override
	public void visiter(TestType testType) {
		if (testType.typeRef.module == null) {
			testType.typeRef.module =module;
			testType.typeRef.moduleInit = true;
		}
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
		for(Ref ref:literal.mots) {
			if ( ref.module == null ) {
				ref.module = module;
				ref.moduleInit = true;
			}
			
		}
		
	}

}
