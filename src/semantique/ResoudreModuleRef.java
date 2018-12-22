package semantique;

import java.util.Map;

import model.Acces;
import model.Appel;
import model.FonctionDef;
import model.Literal;
import model.Objet;
import model.TestType;
import model.VarRef;
import model.VisiteurExpressionImplementation;

public class ResoudreModuleRef extends VisiteurExpressionImplementation {
	Verificateur verificateur;
	public String nom;
	public Map<String,String> variables;
	@Override
	public void visiter(Objet objet) {
		super.visiter(objet);
		verificateur.trouverType(objet.type, FonctionDef.class, nom);

	}

	@Override
	public void visiter(Appel appel) {
		super.visiter(appel);
		if (appel.nom.moduleInit) {
			appel.erreur = false;
		verificateur.completer(appel, variables);
		if (appel.nom.moduleInit) {
			System.out.println(" ko ");
		}
		}

	}

	@Override
	public void visiter(TestType testType) {
		testType.cible.visiter(this);
		String oldType = null;
		String nom = null;
		if (testType.cible instanceof VarRef) {
			VarRef var = (VarRef) testType.cible;
			nom = var.nom;
			oldType = this.variables.get(nom);
			this.variables.put(nom,testType.typeRef.nomRef());
		}
		testType.alors.visiter(this);
		if (oldType != null) {
			this.variables.put(nom, oldType);
		}
		testType.sinon.visiter(this);
		
	}

	@Override
	public void visiter(Acces acces) {
		super.visiter(acces);

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
