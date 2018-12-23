package monde;

import java.awt.Color;

public class Vie extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.green;
	}

	@Override
	public void executer(EcranDessin ed, Soldat soldat) {
		// TODO Auto-generated method stub
		soldat.vies.add(this);
		
	}

}
