package monde;

import java.awt.Point;

public class DeplacerProjectile extends Deplacer {
	public Projectile projectile;

	public DeplacerProjectile(Projectile balle, Point destination, float vitesse) {
		super(balle, destination, vitesse);
		this.projectile = balle;
	}

	public DeplacerProjectile(Projectile balle, Point destination, float vitesse, float d) {
		super(balle, destination, vitesse, d);
		this.projectile = balle;
	}

	public void finEtat(EcranJeux ecranJeux, Entite entite) {

		projectile.attaquant.etat = null;
		entite.etat = null;
		if (projectile.collision(projectile.cible)) {

			while (!projectile.attaquant.puissances.isEmpty()) {
				if (projectile.cible.vies.isEmpty()) {
					projectile.cible.detruire();
					projectile.cible.config.nombreDetruit++;
					return;
				}
				Vie vie = projectile.cible.vies.pop();
				vie.libre = true;
				Puissance p = projectile.attaquant.puissances.pop();
				p.libre = true;
			}
		}
	}

}
