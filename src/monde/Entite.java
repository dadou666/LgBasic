package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Entite {
	public Point position;
	protected int rayon;

	abstract public Color color();

	public int rayon() {
		return rayon;
	}

	public EtatEntite etat;

	public void paint(Graphics g) {
		g.setColor(color());
		g.fillOval(position.x - rayon(), position.y - rayon(), 2 * rayon(), 2 * rayon());
	}

	public boolean collision(Entite b) {
		float dx = b.position.x - position.x;
		float dy = b.position.y - position.y;
		float db = rayon() + b.rayon();
		return (dx * dx + dy * dy) <= db * db;

	}
}
