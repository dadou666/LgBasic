package monde;

import java.awt.Point;
import java.util.Random;

public class Quad {
	Point pos;
	Point dim;
	boolean estCercle;
	Quad pp;
	Quad pm;
	Quad mp;
	Quad mm;

	public Quad(Point pos, Point dim, int nbCercle, int rayon, Random r) {
		this.pos = pos;
		this.dim = dim;
		Point newDim = new Point(dim.x / 2, dim.y / 2);
		if (newDim.x / 2 > rayon && newDim.y > rayon) {
			int n = nbCercle;

			if (nbCercle <= 0) {
				return;
			}
			n = r.nextInt(nbCercle);
			mp = new Quad(new Point(pos.x - newDim.x, pos.y + newDim.y), newDim, n, rayon, r);
			nbCercle = nbCercle - n;
			if (nbCercle <= 0) {
				return;
			}
			n = r.nextInt(nbCercle);

			mm = new Quad(new Point(pos.x - newDim.x, pos.y - newDim.y), newDim, n, rayon, r);
			nbCercle = nbCercle - n;
			if (nbCercle <= 0) {
				return;
			}
			n = r.nextInt(nbCercle);
			pp = new Quad(new Point(pos.x + newDim.x, pos.y + newDim.y), newDim, n, rayon, r);
			nbCercle = nbCercle - n;
			if (nbCercle <= 0) {
				return;
			}
			n = r.nextInt(nbCercle);
			pm = new Quad(new Point(pos.x + newDim.x, pos.y - newDim.y), newDim, n, rayon, r);
			return;

		}
		this.estCercle = true;
		

	}

}
