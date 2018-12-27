package monde;

import java.awt.Color;

public class Projectile extends Entite {
	Soldat cible;
	Soldat attaquant;
	int puissance;
	public void finDeplacer(EcranJeux ecranDessin) {
		if (cible.collision(this)) {
			while(puissance>=0) {
			if (cible.vies.isEmpty()) {
				cible.detruire();
				return;
			}
			Vie vie = cible.vies.pop();
			vie.libre =true;
			puissance--;
			}
		}
		attaquant.projectile = null;
		
		
	}
	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.magenta;
	}

}
