package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ihm.swing.SwingBuilder;
import model.TypeDef;
import model.Var;
import semantique.TypeReserveValidation;
import semantique.Verificateur;
import semantique.VerificationType;

public class ObjetInterface {
	public Verificateur verificateur;
	public int tailleColonne = 150;
	public List<ChampInterface> champs = new ArrayList<>();
	public ChampObjet champ;
	public String type;
	public SwingBuilder sb;

	public ObjetInterface() {

	}

	public ObjetInterface(String type, Verificateur verificateur, SwingBuilder sb) {
		this.sb = sb;
		this.verificateur = verificateur;
		this.sb.beginY();
		this.creer(type, 0);
		this.sb.end();
	}

	public void creer(String type, int profondeur) {
		this.type = type;
		List<Var> vars = new ArrayList<>();
		verificateur.listeVarAvecType(type, vars);
		if (champ != null) {
			champ.historique.put(type, this);
		}
		for (Var var : vars) {

			String champType = var.type.nomRef();
			String champNom = var.nom;
			TypeReserveValidation trv = verificateur.validations.get(champType);
			TypeDef td = verificateur.types.get(champType);

			if (trv != null) {
				ChampSaisie champSaisie = new ChampSaisie();
				champSaisie.nom = champNom;
				champSaisie.type = champType;
				champs.add(champSaisie);

				sb.beginX();
				sb.space(profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champSaisie.label = new JLabel(champNom));
				JTextField textField = new JTextField();
				champSaisie.jTextField = textField;
				sb.add(textField);
				sb.end();
			} else if (td != null) {
				ChampObjet champObjet = new ChampObjet();
				champObjet.nom = champNom;
				champObjet.type = champType;
				champs.add(champObjet);
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
				sb.space(profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champObjet.label = new JLabel(champNom));
				sb.add(jcomboBox);
				JCheckBox jcheckbox = new JCheckBox();
				sb.add(jcheckbox);
				ObjetInterface oi = new ObjetInterface();
				oi.verificateur = verificateur;
				jcomboBox.addActionListener(champObjet);
				jcheckbox.addActionListener(champObjet);
				oi.champ = champObjet;
				champObjet.jComboBox = jcomboBox;
				champObjet.jCheckBox = jcheckbox;
				champObjet.objetInterface = oi;
				champObjet.parent = this;
				sb.end();
				oi.sb = sb;

				oi.creer(champType, profondeur + 1);

			}

		}

	}

	public ObjetInterface racine() {
		if (champ == null) {
			return this;
		}
		return champ.parent.racine();
	}

	public void reconstruire(ChampObjet ci, String nouveauType, int profondeur) {
		if (ci != null && ci == champ) {

			type = nouveauType;
			ObjetInterface oi = ci.historique.get(nouveauType);
			if (oi != null) {
				ci.objetInterface = oi;
				oi.reconstruire(null, nouveauType, profondeur);
			} else {
				oi = new ObjetInterface();
				ci.objetInterface = oi;
				oi.sb = sb;
				oi.verificateur = this.verificateur;
				oi.champ = ci;
				oi.creer(nouveauType, profondeur);

			}

		} else {
			for (ChampInterface champTmp : this.champs) {
				champTmp.reconstruire(ci, nouveauType, sb, tailleColonne, profondeur);

			}

		}
	}

}
