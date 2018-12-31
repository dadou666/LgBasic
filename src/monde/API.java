package monde;

import java.util.HashMap;
import java.util.Map;

public class API {
	public api$soldats moi;
	public api$soldats enemies;
	public api$ressources ressources;
	public api$objectif o;
	public api$vrai vrai = new api$vrai();
	public api$faux faux = new api$faux();
	
	static public class api$soldatVides {}
	static public class api$soldats extends api$soldatVides {
	
		public int porte;
		public int vitesseDeplacement;
		public int vitesseTire;
		public int puissance;
		public int vie;
		public api$soldatVides suivant;

	}
	static  class api$ressourcesVide { }
	static abstract class api$ressources extends api$ressourcesVide  {
		
		public api$ressourcesVide suivant;}
	static class api$vie extends  api$ressources {}
	static class api$puissance extends  api$ressources {}
	static class api$reproduction extends  api$ressources {}
	static class api$vitesse extends  api$ressources {}
	static class api$vitesseTire extends  api$ressources {}
	static class api$porte extends  api$ressources {}
	static public abstract  class api$action {}
	
	static public class api$objectif extends api$action{
		public int vie;
		public int puissance;
		public int population;
		public int porte;
		public int vitesse;
		public int vitesseTire;
		
	}
	public int api$add(int a,int b) {
		return a+b;
	}
	static public abstract class api$boolean {}
	static public class api$vrai extends api$boolean {}
	static public class api$faux extends api$boolean {}
	public api$boolean api$sup(int a ,int b) {
		
		if (a > b) {
			return vrai;
		}
		return faux;
	}

}
