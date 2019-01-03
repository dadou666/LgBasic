package monde;

import java.awt.Point;

public class DeplacerSoldatPourAttaque extends DeplacerSoldat {
	Soldat cible;

	public DeplacerSoldatPourAttaque(Soldat cible, Soldat balle, Point destination, float vitesse, float d) {
		super(balle, destination, vitesse, d);
		this.cible = cible;
	}

	public DeplacerSoldatPourAttaque(Soldat cible, Soldat balle, Point destination, float vitesse) {
		super(balle, destination, vitesse);
		this.cible = cible;
	}

	public void finEtat(EcranJeux ecranJeux, Entite entite) {
		soldat.lancerProjectile(ecranJeux, cible);
	}

}
