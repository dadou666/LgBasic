package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import ihm.swing.SwingBuilder;

abstract public class ArbreContenu {
	public String type;
	

	abstract public void ajouter(SwingBuilder sb);

	public void ajouterDansChemin() {

	}
	public void editer() {
		
	}
	abstract public void init(Object object) ;

}
