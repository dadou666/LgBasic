package monde;

import java.util.HashMap;
import java.util.Map;

public class API {
	public api$soldatVides moi;
	public api$soldatVides enemies;
	public api$ressourcesVide ressources;
	public api$strategie strategie;
	public api$vrai vrai = new api$vrai();
	public api$faux faux = new api$faux();

	static public abstract class api$objet {
	}

	static public class api$null extends api$objet {
	}

	static public class api$soldatVides extends api$objet {
	}

	static public class api$soldats extends api$soldatVides {

		public int porte;
		public int energie;
		public int vitesseDeplacement;
		public int vitesseTire;
		public int puissance;
		public int vie;
		public api$soldatVides suivant;

	}

	static class api$ressourcesVide extends api$objet {
	}

	static public abstract class api$ressources extends api$ressourcesVide {

		public api$ressourcesVide suivant;
	}

	static class api$vie extends api$ressources {
	}

	static class api$puissance extends api$ressources {
	}

	static class api$reproduction extends api$ressources {
	}

	static class api$vitesse extends api$ressources {
	}

	static class api$vitesseTire extends api$ressources {
	}

	static class api$porte extends api$ressources {
	}
	
	static class api$energie extends api$ressources {
	
	}
	
	static public class api$action extends api$objet {
		public int population;
		public api$priorite attaque;
		public api$priorite creer;
		public api$objectif objectif;

	}

	static public abstract class api$priorite {
		public abstract int valeur();
	}
	

	static public class api$p1 extends api$priorite {

		@Override
		public int valeur() {
			// TODO Auto-generated method stub
			return 1;
		}

	}

	static public class api$p2 extends api$priorite {

		@Override
		public int valeur() {
			// TODO Auto-generated method stub
			return 2;
		}

	}

	static public class api$p3 extends api$priorite {

		@Override
		public int valeur() {
			// TODO Auto-generated method stub
			return 3;
		}

	}

	static public class api$p4 extends api$priorite {

		@Override
		public int valeur() {
			// TODO Auto-generated method stub
			return 4;
		}

	}
	static public class api$p5 extends api$priorite {

		@Override
		public int valeur() {
			// TODO Auto-generated method stub
			return 5;
		}

	}

	static public class api$configUnit  {
		public int vie;
		public int puissance;
		public int energie;
		public int porte;
		public int vitesse;
		public int vitesseTire;
		public int cout;

	}

	static public class api$config {
		public api$configUnit ouvrier;
		public api$configUnit soldat;
		public api$configUnit maitre;
		public api$configUnit roi;
		public api$configUnit dieu;
	}

	static public class api$strategie extends api$operation {
		public api$action ouvrier;
		public api$action soldat;
		public api$action maitre;
		public api$action roi;
		public api$action dieu;
	}

	static abstract public class api$objectif {
		public api$boolean recupererVie;
	}

	static public class api$recolter extends api$objectif {
			public api$attaquer attaquer;
	}

	static public class api$attaquer extends api$objectif {
		

	}

	abstract static public class api$operation {
	}

	static public class api$ecrire extends api$operation {
		public String nom;
		public api$objet valeur;
	}

	public int api$add(int a, int b) {
		return a + b;
	}

	static public abstract class api$boolean {
	}

	static public class api$vrai extends api$boolean {
	}

	static public class api$faux extends api$boolean {
	}

	public api$boolean api$sup(int a, int b) {

		if (a > b) {
			return vrai;
		}
		return faux;
	}

	Map<String, api$objet> objets = new HashMap<>();

	public api$objet api$lire(String nom) {
		api$objet r = objets.get(nom);
		if (r == null) {
			return new api$null();
		}
		return r;

	}

}
