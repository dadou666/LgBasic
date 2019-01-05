package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ihm.swing.SwingBuilder;
import model.TypeDef;
import semantique.Verificateur;
import semantique.VerificationType;

public class ObjetInterface implements ActionListener {
	public Verificateur verificateur;
	public int tailleColonne = 150;
	public List<ChampInterface> champs = new ArrayList<>();
	public ChampInterface parent;
	public String type;
	public SwingBuilder sb;

	public void creer(String type, int profondeur) {
		this.type = type;
		Map<String, String> map = new HashMap<>();
		verificateur.listeVarAvecType(type, map);
		for (Map.Entry<String, String> e : map.entrySet()) {

			String champType = e.getValue();
			String champNom = e.getKey();
			VerificationType vt = verificateur.verificationTypes.get(champType);
			TypeDef td = verificateur.types.get(champType);
			ChampInterface champInterface = new ChampInterface();
			champInterface.nom = champNom;
			champInterface.type = champType;
			champs.add(champInterface);
			if (vt != null) {
				sb.beginX();
				sb.space(2 * profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champInterface.label = new JLabel(champNom));
				JTextField textField = new JTextField();
				champInterface.component = textField;
				sb.add(textField);
				sb.end();
			} else if (td != null) {
				sb.beginX();
				List<String> typesCompatible = new ArrayList<>();
				verificateur.listerTypesCompatible(champType, typesCompatible, true);

				if (td.estAbstrait) {
					champType = typesCompatible.get(0);
				}
				JComboBox<String> jcomboBox = new JComboBox<String>();
				jcomboBox.setSelectedItem(champType);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel();
				for (String tp : typesCompatible) {
					model.addElement(tp);
				}
				jcomboBox.setModel(model);
				sb.space(2 * profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champInterface.label = new JLabel(champNom));
				sb.add(jcomboBox);
				ObjetInterface oi = new ObjetInterface();
				oi.verificateur = verificateur;
				jcomboBox.addActionListener(oi);
				oi.parent = champInterface;
				champInterface.component = jcomboBox;

				sb.end();
				oi.sb = sb;
				oi.creer(champType, profondeur + 1);

			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JComboBox cb = (JComboBox) e.getSource();
		String nouveauType = (String) cb.getSelectedItem();
		this.racine().reconstruire(parent, nouveauType, 0);

	}

	public ObjetInterface racine() {
		if (parent == null) {
			return this;
		}
		return parent.objetInterface.racine();
	}

	public void reconstruire(ChampInterface ci, String nouveauType, int profondeur) {
		if (ci == parent) {
			ci.historique.put(type, ci.objetInterface);
			type = nouveauType;
			ObjetInterface oi = ci.historique.get(nouveauType);
			if (oi != null) {
				ci.objetInterface = oi;
				oi.reconstruire(ci, nouveauType, profondeur);
			} else {
				oi = new ObjetInterface();
				ci.objetInterface = oi;
				oi.verificateur = this.verificateur;
				oi.creer(nouveauType, profondeur );

			}

		} else {
			for (ChampInterface champ : this.champs) {
				sb.beginX();
				sb.space(2 * profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champ.label);

				sb.add(champ.component);
				sb.end();
				if (champ.objetInterface != null) {
					champ.objetInterface.reconstruire(ci, nouveauType, profondeur + 1);

				}

			}

		}
	}

}
