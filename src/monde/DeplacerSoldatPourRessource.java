package monde;

import java.awt.Point;

public class DeplacerSoldatPourRessource extends DeplacerSoldat {
	Ressource ressource;
	public DeplacerSoldatPourRessource(Ressource ressource,Soldat balle, Point destination, float vitesse, float d) {
		super(balle, destination, vitesse, d);
		this.ressource = ressource;
	}

	public DeplacerSoldatPourRessource(Ressource ressource,Soldat balle, Point destination, float vitesse) {
		super(balle, destination, vitesse);
		this.ressource = ressource;
	}
	public void finEtat(EcranJeux ecranJeux,Entite entite) {
		
		if (ressource.libre) {
			ressource.executer(ecranJeux, soldat);
			ressource.libre = false;
			

		}
		soldat.etat = null;


	}

}
