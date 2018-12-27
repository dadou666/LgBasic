package monde;
import java.awt.Color;

public class Porte extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executer(EcranJeux ed, Soldat soldat) {
		soldat.portes.add(this);
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Po";
	}

}
