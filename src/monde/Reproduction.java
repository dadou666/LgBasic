package monde;

import java.awt.Color;
import java.awt.Point;

public class Reproduction extends Ressource {
	public API.api$configUnit configUnit;

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.ORANGE;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		if (soldat.estMort) {
			this.libre = true;
			configUnit = null;
			return;
		}
		Soldat nvSoldat = new Soldat();
		nvSoldat.position = new Point(position.x,position.y);
		nvSoldat.config = soldat.config;
		nvSoldat.reproduction = this;
		nvSoldat.configUnit = this.configUnit;
		configUnit = null;
		
		soldat.config.nvSoldats.add(nvSoldat);
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Re";
	}

}
