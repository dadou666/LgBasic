package monde;

import java.awt.Point;
import java.util.Map;

public class Deplacer extends EtatEntite{

	public float distance;
	public float vitesse;
	public float vx;
	public float vy;
	public Point destination;
	public Point depart;

	public Deplacer(Entite balle, Point destination, float vitesse, float d) {
		float dx = destination.x - balle.position.x;
		float dy = destination.y - balle.position.y;
		depart = new Point(balle.position);
		distance = (float) Math.sqrt(dx * dx + dy * dy);

		if (distance > 0.0f) {

			vx = vitesse * (dx / distance);
			vy = vitesse * (dy / distance);
			float x = balle.position.x;
			float y = balle.position.y;
			x += d * dx / distance;
			y += d * dy / distance;
			this.destination = new Point((int) x, (int) y);
			this.vitesse = vitesse;
			return;
		}
		throw new Error(" distance nulle");

	}

	public Deplacer(Entite balle, Point destination, float vitesse) {
		float dx = destination.x - balle.position.x;
		float dy = destination.y - balle.position.y;
		depart = new Point(balle.position);
		distance = (float) Math.sqrt(dx * dx + dy * dy);
		if (distance >= 0.0f) {

			vx = vitesse * (dx / distance);
			vy = vitesse * (dy / distance);
			this.destination = destination;
			this.vitesse = vitesse;
			return;
		}
		throw new Error(" distance nulle");

	}

	public void step(EcranJeux ecranJeux, Entite entite) {
		this.deplacer(ecranJeux, entite);
	}

	public void deplacer(EcranJeux ecran, Entite balle) {
		float dx = depart.x - balle.position.x;
		float dy = depart.y - balle.position.y;

		float x = balle.position.x;
		float y = balle.position.y;
		x += vx;
		y += vy;
		balle.position.x = (int) x;
		balle.position.y = (int) y;
		float d = (float) Math.sqrt(dx * dx + dy * dy);

		if (d >= distance) {

			balle.position.setLocation(destination.x, destination.y);
			balle.etat.finEtat(ecran, balle);
		
			return;
		}

	}

}
