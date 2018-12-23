package monde;

import java.awt.Color;
import java.awt.Point;

public class Reproduction extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.ORANGE;
	}

	@Override
	public void executer(EcranDessin ed, Soldat soldat) {
		Soldat nvSoldat = new Soldat();
		nvSoldat.position = new Point(position.x,position.y);
		nvSoldat.config = soldat.config;
		nvSoldat.reproduction = this;
		
		soldat.config.soldats.add(nvSoldat);
		
	}

}
