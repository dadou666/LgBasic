package semantique;

import model.Acces;
import model.Appel;
import model.Expression;
import model.Literal;
import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.TestEgalite;
import model.TestType;
import model.VarRef;
import model.Visiteur;

public class InitModuleRef implements Visiteur{
	public String module;

	@Override
	public void visiter(Objet objet) {

		if (objet.type.module == null) {
			objet.type.module = module;
		}
		for(ObjetParam op:objet.params) {
			op.expression.visiter(this);
		}
		
	}

	@Override
	public void visiter(Appel appel) {
		if (appel.nom.module == null) {
			appel.nom.module = module;
		}
		for(Expression e:appel.params) {
			e.visiter(this);
		}
		
		
	}

	@Override
	public void visiter(TestEgalite testEgalite) {
		testEgalite.a.visiter(this);
		testEgalite.b.visiter(this);
		testEgalite.alors.visiter(this);
		testEgalite.sinon.visiter(this);
		
	}

	@Override
	public void visiter(TestType testType) {
		if (testType.typeRef.module == null) {
			testType.typeRef.module =module;
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
			}
			
		}
		
	}

}
