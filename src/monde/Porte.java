package monde;
import java.awt.Color;

public class Porte extends Ressource {

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executer(EcranDessin ed, Soldat soldat) {
		soldat.portes.add(this);
		
	}

}
