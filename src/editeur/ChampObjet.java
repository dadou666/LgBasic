package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import ihm.swing.SwingBuilder;

public class ChampObjet extends ChampInterface implements ActionListener {
	public JComboBox<String> jComboBox;
	public JCheckBox jCheckBox;
	public ObjetInterface objetInterface;
	public ObjetInterface parent;
	public JButton focusOrParent;
	public Map<String, ObjetInterface> historique = new HashMap<>();
	public boolean hide = false;
	public boolean focus = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jComboBox) {
			String nouveauType = (String) jComboBox.getSelectedItem();
			// sb.frame.getRootPane().removeAll();
			objetInterface.sb.beginY();
			ObjetInterface racine = objetInterface.racine();
			racine.reconstruire(this, nouveauType, 0);
			objetInterface.sb.end();
			objetInterface.sb.reopen(racine.type);
		}
		if (e.getSource() == jCheckBox) {
			hide = jCheckBox.isSelected();
			objetInterface.sb.beginY();
			ObjetInterface racine = objetInterface.racine();
			racine.reconstruire(null, null, 0);
			objetInterface.sb.end();
			objetInterface.sb.reopen(racine.type);
		}
		if (e.getSource() == focusOrParent) {
			if (!focus) {
				objetInterface.sb.beginY();
				objetInterface.sb.add(focusOrParent);
				objetInterface.reconstruire(null, null, 0);
				objetInterface.sb.end();
				objetInterface.sb.reopen(objetInterface.type);	
				focus = true;
				focusOrParent.setText("Parent");
			} else {
				objetInterface.sb.beginY();
				if (parent.champ != null) {
					objetInterface.sb.add(parent.champ.focusOrParent);	
					parent.champ.focusOrParent.setText("Parent");
					parent.champ.focus = true;
				}
				parent.reconstruire(null, null, 0);
				objetInterface.sb.end();
				objetInterface.sb.reopen(objetInterface.type);	
				focus = false;
				focusOrParent.setText("Focus");
				
			}
		}
	}

	@Override
	public void reconstruire(ChampObjet champObjet, String nouveauType, SwingBuilder sb, int tailleColonne,
			int profondeur) {
		int d = 1;
		sb.beginX();
		sb.space(profondeur * tailleColonne);
		sb.setSize(tailleColonne, ObjetInterface.tailleLigne);
		sb.add(label);

		sb.add(jComboBox);
		sb.add(jCheckBox);
		sb.add(focusOrParent);
		sb.end();
		if (parent.champ != null && parent.champ.type.equals(type)) {
			d = 0;
			sb.beginX();
			sb.add(new JLabel());
			sb.end();
		}
		if (!hide) {
			objetInterface.reconstruire(champObjet, nouveauType, profondeur + d);
		}
	}
}
