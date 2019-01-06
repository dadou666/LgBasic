package editeur;

import javax.swing.JTextField;

import ihm.swing.SwingBuilder;

public class ChampSaisie extends ChampInterface {
	public JTextField  jTextField;

	@Override
	public void reconstruire(ChampObjet champObjet,String nouveauType,SwingBuilder sb,int tailleColonne,int profondeur) {
		sb.beginX();
		sb.space(profondeur * tailleColonne);
		sb.setSize(tailleColonne, ObjetInterface.tailleLigne);
		sb.add(label);

		sb.add(jTextField);
		sb.end();
		
	}

}
