package editeur;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import ihm.swing.SwingBuilder;

abstract public class ChampInterface {
	
	

	public String nom;
	public String type;
	public JLabel label;
	abstract	public void reconstruire(ChampObjet champObjet,String nouveauType,SwingBuilder sb,int tailleColonne,int profondeur); 



}
