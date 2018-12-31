package monde;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import editeur.Executeur;
import editeur.Terminal;
import execution.Traducteur;
import semantique.Verificateur;
import semantique.VerificationFonction;

public class JeuxExecuteur implements Executeur {
	@Override
	public Class classAPI() {
		// TODO Auto-generated method stub
		return API.class;
	}

	@Override
	public Map<Class, String> typeReserve() {

		Map<Class,String> map = new HashMap<>();
		map.put(String.class, "base$string");
		map.put(int.class, "base$int");
		return map;
	}
	@Override
	public void executer(Terminal terminal) {
		// TODO Auto-generated method stub
		
	}
	public Config creerConfig(Terminal terminal,boolean estConfig1) {
		
		Verificateur v=terminal.verificateur;
		String module = terminal.list.getSelectedValue();

		Traducteur traducteur = new Traducteur("test"+System.currentTimeMillis(), v);
		traducteur.typesReserve.put("base$symbol", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.api = this.classAPI();
		traducteur.literalTracducteurs.put("base$symbol", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls;
		try {
			cls = traducteur.traduire();

			API o =(API) cls.newInstance();
			Config config = new Config();
			config.api = o;
			if (estConfig1) {
				config.method = cls.getMethod(module+"$config1/0");
			} else {
				config.method = cls.getMethod(module+"$config2/0");
				
			}
			return config;
			
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean test(Terminal terminal) {
		String module = terminal.list.getSelectedValue();
		terminal.msgImpossibleExecuter.setText("");
		if (module == null) {
			terminal.msgImpossibleExecuter.setText("Aucun module de selectionné ");
			return false;
		}
		if (terminal.verificateur != null) {
			VerificationFonction fd1 =terminal.verificateur.fonctions.get(module+"$config1/0");
			VerificationFonction fd2 =terminal.verificateur.fonctions.get(module+"$config2/0");
			if (fd1  == null )  {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction config1/0");
				return false;
			}
			if (fd2  == null )  {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction config2/0");
				return false;
			}
			if (!terminal.verificateur.herite(fd1.typeRetour,"api$action")) {
				terminal.msgImpossibleExecuter.setText("Le type de la fonction config1/0 doit être api$action");
				return false;
			}
			if (!terminal.verificateur.herite(fd2.typeRetour,"api$action")) {
				terminal.msgImpossibleExecuter.setText("Le type de la fonction config2/0 doit être api$action");
				return false;
			}
			
			return true;
		}
		return false;
	}
}
