package monde;

import java.awt.Color;

public class Vitesse extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executer(EcranDessin ed, Soldat soldat) {
		// TODO Auto-generated method stub
		soldat.vitesses.add(this);
		
	}

}
