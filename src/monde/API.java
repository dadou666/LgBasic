package monde;

import java.util.HashMap;
import java.util.Map;

public class API {
	public Map<String,api$message> memoire = new HashMap<>();
	static public abstract class api$action {
		
	}
	static public abstract class api$actionSurCible extends api$action {
		public String cible;
	}
	static public abstract class api$actionVide extends api$action {}

	static public abstract class api$message {
	}
	static public  class api$messageVide extends api$message {
	}

	static public class api$messagesVide {
	}

	static public class api$messages extends api$messagesVide {
		public api$message message;
		public api$messagesVide suivant;
	}

	static public class api$attaquer extends api$action {
	}

	static public class api$recolter0 extends api$action {
	}
	static public class api$recolter1 extends api$action {
	}

	static public class api$scanner extends api$action {
		
	}

	static public class api$memoriser extends api$action {
		public api$message message;
	}

	static public class api$effacer extends api$action {
	}

	static public abstract class api$objet {
		public String ref;
	}

	static public abstract class api$ressource extends api$objet {
	}

	static public class api$ami extends api$objet {
		public api$acteurEtat etat;
		public api$messagesVide messages;
		public api$action action;
	}

	static public class api$0 extends api$ressource {
	}

	static public class api$1 extends api$ressource {
	}

	static public class api$enemi extends api$objet {
		public api$acteurEtatVide etat;
	}

	static public class api$acteurEtatVide {
	}

	static public class api$acteurEtat extends api$acteurEtatVide {
		public int taille;
		public int energie;
		public int porte;
		public int vitesse;
		public int vitesseTire;
		public int puissance;
		public int vie;

	}
	public api$message consulter(String nom) {
		api$message r = this.memoire.get(nom);
		if (r == null) {
			r = new api$messageVide();
		}
		return r;
	}

}
