package monde;

import java.awt.Color;
import java.awt.Point;

public class Projectile extends Entite {
	Soldat cible;
	Soldat attaquant;
	int puissance;
	public Projectile() {
		this.rayon=8;
	}
	
	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.magenta;
	}
	
	public void deplacer(Point destination, float vitesse) {
		this.etat = new DeplacerProjectile(this, destination, vitesse);
	}

}
