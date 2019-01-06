package monde;

import java.awt.Point;
import java.util.Map;

public class Deplacer extends EtatEntite{

	public float distance;
	public float vitesse;
	public float vx;
	public float vy;
	
	public float px;
	public float py;
	public float ix;
	public float iy;
	
	public float destx;
	public float desty;
	

	public Deplacer(Entite balle, Point destination, float vitesse, float d) {
		float dx = destination.x - balle.position.x;
		float dy = destination.y - balle.position.y;
		px = balle.position.x;
		py=  balle.position.y;
		ix=px;
		iy=py;
		distance = (float) Math.sqrt(dx * dx + dy * dy);

		if (distance > 0.0f) {

			vx = vitesse * (dx / distance);
			vy = vitesse * (dy / distance);
			float x = balle.position.x;
			float y = balle.position.y;
			x += d * dx / distance;
			y += d * dy / distance;

			this.destx=x;
			this.desty=y;
			this.vitesse = vitesse;
			return;
		}
		throw new Error(" distance nulle");

	}

	public Deplacer(Entite balle, Point destination, float vitesse) {
		float dx = destination.x - balle.position.x;
		float dy = destination.y - balle.position.y;

		px= balle.position.x;
		py= balle.position.y;
		ix=px;
		iy=py;
		distance = (float) Math.sqrt(dx * dx + dy * dy);
		if (distance >= 0.0f) {

			vx = vitesse * (dx / distance);
			vy = vitesse * (dy / distance);
			this.destx= destination.x;
			this.desty =destination.y;
			this.vitesse = vitesse;
			return;
		}
		throw new Error(" distance nulle");

	}

	public void step(EcranJeux ecranJeux, Entite entite) {
		this.deplacer(ecranJeux, entite);
	}

	public void deplacer(EcranJeux ecran, Entite balle) {
		float dx = ix - px;
		float dy = iy - py;


		px += vx;
		py += vy;
		balle.position.x = (int) px;
		balle.position.y = (int) py;
		float d = (float) Math.sqrt(dx * dx + dy * dy);

		if (d >= distance) {

			balle.position.setLocation(destx, desty);
			balle.etat.finEtat(ecran, balle);
		
			return;
		}

	}

}
