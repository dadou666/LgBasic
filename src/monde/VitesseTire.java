package monde;

import java.awt.Color;

public class VitesseTire extends Ressource {

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
		soldat.vitesseTires.add(this);
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Vt";
	}

}
