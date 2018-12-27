package monde;

import java.util.HashMap;
import java.util.Map;

public class API {
	public api$soldats moi;
	public api$soldats enemies;
	public api$ressources ressources;
	
	static public class api$soldatVides {}
	static public class api$soldats extends api$soldatVides {
	
		public int porte;
		public int vitesseDeplacement;
		public int vitesseTire;
		public int puissance;
		public int vie;
		public api$soldatVides suivant;

	}
	static abstract class api$ressourcesVide { }
	static abstract class api$ressources extends api$ressourcesVide  {
		
		public api$ressourcesVide suivant;}
	static class api$vie extends  api$ressources {}
	static class api$puissance extends  api$ressources {}
	static class api$reproduction extends  api$ressources {}
	static class api$vitesse extends  api$ressources {}
	static class api$vitesseTire extends  api$ressources {}
	static class api$porte extends  api$ressources {}
	
	static public class api$objectif{
		public int vie;
		public int puissance;
		public int population;
		public int porte;
		public int vitesse;
		public int vitesseTire;
		
	}

}
