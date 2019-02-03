package editeur;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import ihm.swing.SwingBuilder;

public class ArbreSansChoix extends Arbre {
	public JLabel label;

	@Override
	public void ajouter(SwingBuilder sb) {
		sb.beginX();
		contenu.ajouter(sb);
		sb.add(label);
		sb.end();
	}

	@Override
	public void init(Object object) {
		this.contenu.init(object);

	}

}
