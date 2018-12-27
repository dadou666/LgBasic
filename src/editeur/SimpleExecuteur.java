package editeur;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import execution.Traducteur;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import model.FonctionDef;
import semantique.Verificateur;
import semantique.VerificationFonction;
import test.APITestExcution5;

public class SimpleExecuteur implements Executeur {

	@Override
	public void executer(Terminal terminal) {
		Verificateur v=terminal.verificateur;
		String module = terminal.list.getSelectedValue();

		Traducteur traducteur = new Traducteur("test"+System.currentTimeMillis(), v);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls;
		try {
			cls = traducteur.traduire();
			Method m = cls.getMethod(module+"$main");
			Object o =cls.newInstance();
			Object r = m.invoke(o);
			String src = traducteur.source(r);
			System.out.println(">>"+src);
			terminal.streamOutput.flush();
			
		} catch (Exception e) {
			//terminal.output.setText(t);
			terminal.output.setText( e.getMessage());
			e.printStackTrace();
		} 



	}

	@Override
	public boolean test(Terminal terminal) {
		String module = terminal.list.getSelectedValue();
		if (module == null) {
			return false;
		}
		if (terminal.verificateur != null) {
			VerificationFonction fd =terminal.verificateur.fonctions.get(module+"$main/0");
			return fd != null;
		}
		return false;
	}

}
