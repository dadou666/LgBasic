package editeur;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import model.Acces;
import model.Appel;
import model.Expression;
import model.FonctionDef;
import model.Literal;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.RefLiteral;
import model.TestType;
import model.TypeDef;
import model.Var;
import model.VarRef;
import model.VisiteurExpression;
import model.VisiteurModule;

public class ColorierSource implements VisiteurModule {
	Color declarationFonction;
	Color declarationType;
	Color declarationFonctionParametre;
	Color referenceFonctionParametre;
	Color referenceType;
	Color referenceFonction;
	Color referenceAttribut;
	Color declarationAttribut;
	Color symbol;
	JTextPane tp;
	Map<Color, AttributeSet> asets = new HashMap<>();

	@Override
	public void visiter(Objet objet) {
		this.setColor(referenceType, objet.type.debut, objet.type.fin);
		for (ObjetParam pm : objet.params) {
			this.setColor(referenceAttribut, pm.debut, pm.fin);
			pm.expression.visiter(this);
		}

	}

	@Override
	public void visiter(Appel appel) {
		this.setColor(referenceFonction, appel.nom.debut, appel.nom.fin);
		for (Expression e : appel.params) {
			e.visiter(this);
		}

	}

	@Override
	public void visiter(TestType testType) {
		this.setColor(referenceType, testType.typeRef.debut, testType.typeRef.fin);
		testType.alors.visiter(this);
		testType.sinon.visiter(this);
		testType.cible.visiter(this);

	}

	@Override
	public void visiter(Acces acces) {
		
		this.setColor(this.referenceAttribut, acces.debut, acces.fin);
		acces.cible.visiter(this);
		

	}

	@Override
	public void visiter(VarRef varRef) {
		if (!varRef.estLibre) {
			this.setColor(this.referenceFonctionParametre, varRef.debut, varRef.fin);
		} else {
			this.setColor(this.symbol, varRef.debut, varRef.fin);
		}

	}

	@Override
	public void visiter(Literal literal) {
		for (RefLiteral ref : literal.mots) {
			if (ref.type == RefLiteral.Type.Var) {
				this.setColor(this.referenceFonctionParametre, ref.debut, ref.fin);
			}
			if (ref.type == RefLiteral.Type.Type) {
				this.setColor(this.referenceType, ref.debut, ref.fin);
			}
			if (ref.type == RefLiteral.Type.Symbol) {
				this.setColor(this.symbol, ref.debut, ref.fin);
			}

		}

	}

	public void setColor(Color c, int debut, int fin) {

		tp.getStyledDocument().setCharacterAttributes(debut, fin - debut + 1, this.attributeSet(c), true);

	}

	AttributeSet attributeSet(Color c) {
		AttributeSet as = asets.get(c);
		if (as == null) {
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			attributes = new SimpleAttributeSet();
			attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
			attributes.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.FALSE);
			attributes.addAttribute(StyleConstants.CharacterConstants.Foreground, c);
			StyleConstants.setFontSize(attributes, 14);
			as = attributes;
			asets.put(c, as);
		}
		return as;

	}

	@Override
	public void visiter(FonctionDef fd) {
		this.setColor(this.declarationFonction, fd.debut, fd.fin);
		for (Var var : fd.params) {
			this.setColor(this.declarationFonctionParametre, var.debut, var.fin);
			this.setColor(this.referenceType, var.type.debut, var.type.fin);
		}
		fd.expression.visiter(this);

	}

	@Override
	public void visiter(TypeDef td) {
		this.setColor(this.declarationType, td.debut, td.fin);
		this.setColor(this.referenceType, td.superType.debut, td.superType.fin);
		for (Var v : td.vars) {
			this.setColor(this.referenceType, v.type.debut, v.type.fin);
			this.setColor(this.declarationAttribut, v.debut, v.fin);
		}

	}

	@Override
	public void visiter(Module module) {
		for (FonctionDef fd : module.fonctions) {
			fd.visiter(this);
		}
		for (TypeDef td : module.types) {
			td.visiter(this);
		}
	}

}
