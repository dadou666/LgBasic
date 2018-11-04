package monde;

import java.awt.Point;
import java.util.Map;

public class Deplacer {

	public float distance;
	public float vitesse;
	public float vx;
	public float vy;
	public Point destination;
	public Point depart;

	public Deplacer(Balle balle, Point destination, float vitesse) {
		float dx = destination.x - balle.position.x;
		float dy = destination.y - balle.position.y;
		depart = new Point(balle.position);
		distance = (float) Math.sqrt(dx * dx + dy * dy);
		if (distance > 0.0f) {

			vx = vitesse * (dx / distance);
			vy = vitesse * (dy / distance);
			this.destination = destination;
			this.vitesse = vitesse;
			balle.deplacer = this;
		}

	}

	public void deplacer(Ecran ecran,Balle balle,String ref) {
		float dx = depart.x - balle.position.x;
		float dy = depart.y - balle.position.y;

		float x = balle.position.x;
		float y = balle.position.y;
		x += vx;
		y += vy;
		balle.position.x = (int) x;
		balle.position.y = (int) y;
		float d = (float) Math.sqrt(dx * dx + dy * dy);
		for(Map.Entry<String, Balle> e:ecran.balles.entrySet()) {
			Balle b = e.getValue();
			if (b != balle) {
				if (b.collision(balle)) {
					balle.deplacer= null;
					b.deplacer = null;
					x -= vx;
					y -= vy;
					balle.position.x = (int) x;
					balle.position.y = (int) y;
					ecran.out.println("Collision "+ref+" "+e.getKey());
					ecran.out.flush();
					return;
					
				}
			}
			
			
		}
		if (d >= distance) {
			System.out.println(
					" pos=" + balle.position + " - " + destination + " - " + vitesse + " vx=" + vx + " - vy=" + vy);
			balle.position.setLocation(destination.x, destination.y);
			balle.deplacer = null;
		
				ecran.out.println("FinDeplacer "+ref);
				ecran.out.flush();
				
			
			return;
		}

	}

}
