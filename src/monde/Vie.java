package monde;

import java.awt.Color;

public class Vie extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.green;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		
		if (soldat.estMort) {
			this.libre = true;
			return;
		}
		soldat.vies.add(this);
		if (soldat.configUnit.vie == soldat.vies.size() && soldat.configUnit.vitesse ==soldat.vitesses.size()) {
			soldat.init = true;
		}
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Vi";
	}

}
