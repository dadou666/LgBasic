package monde;

import java.awt.Color;

public class Puissance extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.magenta;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		soldat.puissances.add(this);		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Pu";
	}

}
