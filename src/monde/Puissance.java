package monde;

import java.awt.Color;

public class Puissance extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.magenta;
	}

	@Override
	public void executer(EcranDessin ed, Soldat soldat) {
		soldat.puissances.add(this);		
	}

}
