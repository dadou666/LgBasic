package monde;

import java.awt.Color;
import java.awt.Point;

abstract public class Ressource  {
	public Point position;
	public boolean libre = true;



	abstract public Color color() ;
	abstract public void executer(EcranDessin ed,Soldat soldat);

}
