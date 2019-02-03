package editeur;

import javax.swing.JLabel;

import ihm.swing.SwingBuilder;


public class ArbreSansContenu extends ArbreContenu {
	public JLabel label;
	@Override
	public void ajouter(SwingBuilder sb) {
sb.add(label);
		
	}
	@Override
	public void init(Object object) {
		// TODO Auto-generated method stub
		
	}

}
