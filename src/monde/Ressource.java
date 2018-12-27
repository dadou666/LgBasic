package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Ressource  {
	public Point position;
	public boolean libre = true;


	abstract public String name();
	abstract public Color color() ;
	abstract public void executer(EcranJeux ed,Soldat soldat);
	public void paint(Graphics g) {
		int rayon = 15;
		g.setColor(Color.BLUE);
		g.drawOval(position.x - rayon, position.y - rayon, 2 * rayon, 2 * rayon);

		
		g.drawString(name(), position.x-rayon/4, position.y+rayon/4);
	}

}
