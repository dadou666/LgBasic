package monde;

import java.awt.Point;

abstract public class DeplacerSoldat extends Deplacer {
	public Soldat soldat;

	public DeplacerSoldat(Soldat balle, Point destination, float vitesse, float d) {
		super(balle, destination, vitesse, d);
		this.soldat = balle;
		// TODO Auto-generated constructor stub
	}

	public DeplacerSoldat(Soldat balle, Point destination, float vitesse) {
		super(balle, destination, vitesse);
		this.soldat = balle;
	}

}
