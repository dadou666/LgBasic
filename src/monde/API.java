package monde;

import java.util.HashMap;
import java.util.Map;

public class API {

	static public class api$vision {
		public api$action actionEnCours;
		public String moi;
		public api$acteurEtat etat;
		public api$objetsVide objets;
		public api$memoiresVide memoire;


	}

	abstract static public class api$memoire {

	}

	public static class api$memoiresVide {
	}

	public static class api$memoires extends api$memoiresVide {
		public String clef;
		public api$memoire memoire;
		public api$memoiresVide suivants;

	}

	static public class api$objetsVide {
	}

	static public class api$objets extends api$objetsVide {
		public api$objet objet;
		public api$objetsVide suivants;
	}

	static public abstract class api$action {

	}

	static public abstract class api$actionSurCible extends api$action {
		public String cible;
	}

	static public abstract class api$actionVide extends api$action {
	}

	static public abstract class api$message {
	}

	static public class api$messages extends api$messagesVide {
		public api$message message;
		public api$messagesVide suivant;
	}

	static public class api$messagesVide {
	}

	static public class api$attaquer extends api$actionSurCible {
	}

	static public class api$recolter0 extends api$actionSurCible {
	}

	static public class api$recolter1 extends api$actionSurCible {
	}

	static public class api$constuire extends api$actionSurCible {

	}

	static public class api$reproduire extends api$actionSurCible {

	}

	static public class api$scanner extends api$actionSurCible {

	}

	static public class api$libererRessource extends api$actionSurCible {

	}

	static public class api$memoriser extends api$actionSurCible {
		public api$memoire memoire;
	}

	static public class api$envoyer extends api$actionSurCible {
		public api$message message;
	}

	static public class api$effacer extends api$actionSurCible {
	}

	static public abstract class api$objet {
		public int distance;
		public String ref;
	}


	static public abstract class api$ressource extends api$objet {
	
	}

	static public class api$ami extends api$objet {

		public api$acteurEtat etat;
		public api$messagesVide messages;

	}

	static public class api$0 extends api$ressource {
	}

	static public class api$1 extends api$ressource {
	}

	static public class api$oeuf extends api$ressource {
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
		public int vitesseDeplacement;
		public int vitesseTire;
		public int puissance;
		public int vie;

	}

}
