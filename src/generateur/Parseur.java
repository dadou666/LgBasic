package generateur;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.TerminalNode;

import grammaire.lgLexer;
import grammaire.lgParser;
import grammaire.lgParser.AppelContext;
import grammaire.lgParser.AttributContext;
import grammaire.lgParser.ChampContext;
import grammaire.lgParser.ChampsContext;
import grammaire.lgParser.CodeContext;
import grammaire.lgParser.ElementContext;
import grammaire.lgParser.FonctionContext;
import grammaire.lgParser.Id_externeContext;
import grammaire.lgParser.ModuleContext;
import grammaire.lgParser.ObjetContext;
import grammaire.lgParser.OperationOuAccesContext;
import grammaire.lgParser.SiContext;
import grammaire.lgParser.TmpCodeContext;
import grammaire.lgParser.TypeContext;
import model.Acces;
import model.Appel;
import model.Expression;
import model.FonctionDef;
import model.Module;
import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.Test;
import model.TestEgalite;
import model.TestType;
import model.TypeDef;
import model.Univers;
import model.Var;
import model.VarRef;

public class Parseur implements ANTLRErrorListener {

	public boolean error = false;

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4,
			RecognitionException arg5) {
		error = true;

	}

	public Univers lireSourceCode(Map<String, String> sources) {
		error = false;
		Univers u = new Univers();
		for (Map.Entry<String, String> e : sources.entrySet()) {

			Module module = this.lireModule(e.getValue());
			if (module != null) {
				u.modules.put(e.getKey(), module);
			}
		}
		return u;

	}

	public Module lireModule(String src) {
		error = false;
		lgLexer lgLexer = new lgLexer(org.antlr.v4.runtime.CharStreams.fromString(src));
		CommonTokenStream tokens = new CommonTokenStream(lgLexer);
		lgParser parser = new lgParser(tokens);
		parser.addErrorListener(this);
		if (error) {
			return null;
		}
		return this.transformer(parser.module());

	}

	public Module transformer(ModuleContext context) {
		Module module = new Module();
		for (ElementContext ec : context.element()) {
			if (ec.type() != null) {
				TypeDef td = this.transformer(ec.type());
				module.types.add(td);
			}
			if (ec.fonction() != null) {
				FonctionDef fd = this.transformer(ec.fonction());
				module.fonctions.add(fd);
			}

		}
		return module;

	}

	public TypeDef transformer(TypeContext tc) {
		TypeDef type = new TypeDef();
		type.nom = tc.ID().getText();
		if (tc.champs() != null) {
			type.vars = this.transformer(tc.champs());
		}
		if (tc.superType() != null) {

			if (tc.superType().id_externe() != null) {

				type.superType = this.transformer(tc.superType().id_externe());
			}
			if (tc.superType().ID() != null) {
				type.superType = new Ref(tc.superType().ID().getText());

			}
		}

		return type;
	}

	public FonctionDef transformer(FonctionContext fd) {
		FonctionDef fonction = new FonctionDef();
		if (fd.operateur() != null) {
			fonction.nom = fd.operateur().getText();
		} else {
			fonction.nom = fd.ID().getText();
		}
		fonction.params = this.transformer(fd.champs());
		fonction.expression = this.transformer(fd.tmpCode());
		return fonction;

	}

	public List<Var> transformer(ChampsContext champs) {
		List<Var> r = new ArrayList<Var>();
		for (ChampContext cc : champs.champ()) {
			r.add(this.transformer(cc));

		}
		return r;
	}

	public Var transformer(ChampContext champ) {
		Var var = new Var();

		var.nom = champ.ID().getText();
		if (champ.typeRef().id_externe() != null) {
			var.type = this.transformer(champ.typeRef().id_externe());
		}
		if (champ.typeRef().ID() != null) {
			var.type = new Ref(champ.typeRef().ID().getText());

		}

		return var;

	}

	public Ref transformer(Id_externeContext refContext) {
		TerminalNode mn = refContext.ID(0);
		TerminalNode tn = refContext.ID(1);
		Ref ref = new Ref();
		ref.module = mn.getText();
		ref.nom = tn.getText();
		return ref;

	}

	public Objet transformer(ObjetContext objetContext) {
		Ref typeRef = null;

		if (objetContext.id_externe() != null) {
			typeRef = this.transformer(objetContext.id_externe());
		}
		if (objetContext.ID() != null) {
			typeRef = new Ref(objetContext.ID().getText());
		}
		Objet objet = new Objet();

		objet.type = typeRef;
		for (AttributContext attributContext : objetContext.attributs().attribut()) {
			ObjetParam op = new ObjetParam();
			op.nom = attributContext.ID().getText();
			op.expression = this.transformer(attributContext.tmpCode());
			objet.params.add(op);
		}
		return objet;

	}

	public Expression transformerDebut(CodeContext codeContext) {
		if (codeContext.appel() != null) {
			return this.transformer(codeContext.appel());
		}
		if (codeContext.si() != null) {
			return this.transformer(codeContext.si());
		}
		if (codeContext.code() != null) {
			return this.transformer(codeContext.code());
		}
		if (codeContext.objet() != null) {
			return this.transformer(codeContext.objet());

		}
		if (codeContext.var() != null) {
			VarRef varRef = new VarRef();
			varRef.nom = codeContext.var().ID().getText();
			return varRef;

		}

		return null;
	}

	public Expression transformer(CodeContext codeContext) {
		Expression expression = this.transformerDebut(codeContext);
		if (codeContext.operationOuAcces() == null) {
			return expression;
		}
		for (OperationOuAccesContext val : codeContext.operationOuAcces()) {
			if (val.operation() != null) {
				Appel appel = new Appel();
				appel.nom = new Ref(val.operation().operateur().getText());
				appel.params.add(expression);
				appel.params.add(this.transformer(val.operation().tmpCode()));
				expression = appel;

			}
			if (val.acces() != null) {
				Acces acces = new Acces();
				acces.cible = expression;
				acces.nom = val.acces().ID().getText();
				expression = acces;

			}

		}
		return expression;

	}

	public Expression transformer(TmpCodeContext tmpCode) {
		if (tmpCode.appel() != null) {
			return this.transformer(tmpCode.appel());

		}
		if (tmpCode.si() != null) {
			return this.transformer(tmpCode.si());
		}
		if (tmpCode.code() != null) {
			return this.transformer(tmpCode.code());
		}
		if (tmpCode.tmpCode() != null) {
			return this.transformer(tmpCode.tmpCode());
		}
		return null;

	}

	Appel transformer(AppelContext appelContext) {

		Appel appel = new Appel();
		if (appelContext.id_externe() != null) {
			appel.nom = this.transformer(appelContext.id_externe());
		}
		if (appelContext.ID() != null) {
			appel.nom = new Ref(appelContext.ID().getText());
		}
		for (TmpCodeContext tcc : appelContext.tmpCode()) {
			appel.params.add(this.transformer(tcc));
		}
		return appel;

	}

	public Test transformer(SiContext siContext) {

		if (siContext.testEgalite() != null) {
			TestEgalite r = new TestEgalite();
			r.a = this.transformer(siContext.testEgalite().code(0));
			r.b = this.transformer(siContext.testEgalite().code(1));

			r.alors = this.transformer(siContext.code(0));
			r.sinon = this.transformer(siContext.code(1));
			return r;

		}
		if (siContext.testDifference() != null) {
			TestEgalite r = new TestEgalite();
			r.a = this.transformer(siContext.testEgalite().code(0));
			r.b = this.transformer(siContext.testEgalite().code(1));

			r.alors = this.transformer(siContext.code(1));
			r.sinon = this.transformer(siContext.code(0));
			return r;

		}

		if (siContext.testType() != null) {
			TestType r = new TestType();
			r.cible = this.transformer(siContext.testType().code());
			if (siContext.testType().negation() == null) {
				r.alors = this.transformer(siContext.code(1));
				r.sinon = this.transformer(siContext.code(0));

			} else {

				r.alors = this.transformer(siContext.code(0));
				r.sinon = this.transformer(siContext.code(1));
			}
			if (siContext.testType().typeRef().id_externe() != null) {
				r.typeRef = this.transformer(siContext.testType().typeRef().id_externe());
			}
			if (siContext.testType().typeRef().ID() != null) {
				r.typeRef = new Ref(siContext.testType().typeRef().ID().getText());
			}
			return r;
		}
		return null;

	}

}
