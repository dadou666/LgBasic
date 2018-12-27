package monde;

import java.awt.Color;

public class Vitesse extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		// TODO Auto-generated method stub
		soldat.vitesses.add(this);
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "V";
	}

}
