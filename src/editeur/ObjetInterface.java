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
import model.Var;
import semantique.TypeReserveValidation;
import semantique.Verificateur;
import semantique.VerificationType;

public class ObjetInterface implements ActionListener {
	public Verificateur verificateur;
	public int tailleColonne = 150;
	public List<ChampInterface> champs = new ArrayList<>();
	public ChampInterface champ;
	public String type;
	public SwingBuilder sb;
	public ObjetInterface() {
		
	}
	public ObjetInterface(String type,Verificateur verificateur,SwingBuilder sb) {
		this.sb=sb;
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
		for (Var var:vars) {

			String champType = var.type.nomRef();
			String champNom = var.nom;
			TypeReserveValidation trv = verificateur.validations.get(champType);
			TypeDef td = verificateur.types.get(champType);
			ChampInterface champInterface = new ChampInterface();
			champInterface.nom = champNom;
			champInterface.type = champType;
			champs.add(champInterface);
			champInterface.parent = this;
			
			if (trv != null) {
				sb.beginX();
				sb.space( profondeur * tailleColonne);
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
				sb.space( profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champInterface.label = new JLabel(champNom));
				sb.add(jcomboBox);
				ObjetInterface oi = new ObjetInterface();
				oi.verificateur = verificateur;
				jcomboBox.addActionListener(oi);
				oi.champ = champInterface;
				champInterface.component = jcomboBox;
				champInterface.objetInterface = oi;

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
	//	sb.frame.getRootPane().removeAll();
		sb.beginY();
		this.racine().reconstruire(champ, nouveauType, 0);
		sb.end();
		this.sb.reopen("Test");
	}

	public ObjetInterface racine() {
		if (champ == null) {
			return this;
		}
		return champ.parent.racine();
	}

	public void reconstruire(ChampInterface ci, String nouveauType, int profondeur) {
		if (ci != null &&ci == champ) {

			type = nouveauType;
			ObjetInterface oi =ci.historique.get(nouveauType);
			if (oi != null) {
				ci.objetInterface = oi;
				oi.reconstruire(null, nouveauType, profondeur);
			} else {
				oi = new ObjetInterface();
				ci.objetInterface = oi;
				oi.sb=sb;
				oi.verificateur = this.verificateur;
				oi.champ =ci;
				oi.creer(nouveauType, profondeur );
				
				

			}
			JComboBox<String> jcb = (JComboBox<String>) ci.component;
			jcb.removeActionListener(this);
			
			jcb.addActionListener(oi);

		} else {
			for (ChampInterface champTmp : this.champs) {
				sb.beginX();
				sb.space(profondeur * tailleColonne);
				sb.setSize(tailleColonne, 20);
				sb.add(champTmp.label);

				sb.add(champTmp.component);
				sb.end();
				if (champTmp.objetInterface != null) {
					int d=1;
					if (champ!=null && champ.type.equals(champTmp.type)) {
						d=0;
						sb.beginX();
						sb.add(new JLabel());
						sb.end();
					}
					champTmp.objetInterface.reconstruire(ci, nouveauType, profondeur + d);

				}

			}

		}
	}

}
