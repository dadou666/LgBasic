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
		if (soldat.estMort) {
			this.libre = true;
			return;
		}
		soldat.vitesses.add(this);
		if (soldat.configUnit.vie == soldat.vies.size() && soldat.configUnit.vitesse == soldat.vitesses.size()) {
			soldat.init = true;
		}

	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "V";
	}

}
