package monde;

import java.awt.Color;

public class Energie extends Ressource {

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "En";
	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		if (soldat.estMort) {
			this.libre = true;
			return;
		}
		soldat.energies.add(this);

	}

}
