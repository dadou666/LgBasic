package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Balle {
	public Point position;
	int rayon;
	Color color;
	public Deplacer deplacer;

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(position.x - rayon, position.y - rayon, 2 * rayon, 2 * rayon);
	}

	public void deplacer(Point destination, float vitesse) {
		new Deplacer(this, destination, vitesse);
	}

	public void deplacer(Ecran ecran, String ref) {
		if (deplacer == null) {
			return;
		}
		deplacer.deplacer(ecran, this, ref);

	}

	public boolean collision(Balle b) {
		float dx = b.position.x - position.x;
		float dy = b.position.y - position.y;
		float db=rayon+b.rayon;
		return  (dx*dx+dy*dy) <= db*db;

	}
}
