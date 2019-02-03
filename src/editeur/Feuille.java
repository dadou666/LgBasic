package editeur;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ihm.swing.SwingBuilder;

public class Feuille extends Noeud {
	public String type;
	public JTextField valeur;
	public JLabel nom;

	@Override
	public void ajouter(SwingBuilder sb) {
		// TODO Auto-generated method stub
		sb.beginX();
		sb.add(nom);
		sb.add(valeur);
		sb.end();

	}

	@Override
	public void init(Object object) {
		valeur.setText(object.toString());

	}

}
