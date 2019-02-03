package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import ihm.swing.SwingBuilder;

public class ArbreAvecChoix extends Arbre implements ActionListener {
	public JComboBox<String> types;
	public Map<String, ArbreContenu> historique = new HashMap<>();
	public Controlleur editeur;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String selection = (String) types.getSelectedItem();
		ArbreContenu r = historique.get(selection);
		if (r == null) {
			if (!editeur.verificateur.estTypeVide(selection)) {
				Map<String, Noeud> map = editeur.creerMap(selection, null);
				ArbreAvecContenu aac = new ArbreAvecContenu();
				aac.editeur = this.editeur;
				aac.noeuds = map;
				aac.editer = new JButton(nom);
				aac.editer.addActionListener(aac);
				aac.type = selection;
				r = aac;
			} else {

				ArbreSansContenu asc = new ArbreSansContenu();
				asc.label = new JLabel(nom);
				asc.type = selection;
				r = asc;

			}

			historique.put(selection, r);
		}
		contenu = r;

		editeur.construire();

	}

	@Override
	public void ajouter(SwingBuilder sb) {
		sb.beginX();
		contenu.ajouter(sb);
		sb.add(types);
		sb.end();

	}

	@Override
	public void init(Object object) {
		String type = object.getClass().getSimpleName();
		ArbreContenu ac = this.historique.get(type);
		if (ac != null) {
			ac.init(object);
			return;
		}
		if (!editeur.verificateur.estTypeVide(type)) {
			Map<String, Noeud> map = editeur.creerMap(type, object);
			ArbreAvecContenu aac = new ArbreAvecContenu();
			aac.editeur = this.editeur;
			aac.noeuds = map;
			aac.editer = new JButton(nom);
			aac.editer.addActionListener(aac);
			aac.type = type;
			ac = aac;
		} else {

			ArbreSansContenu asc = new ArbreSansContenu();
			asc.label = new JLabel(nom);
			asc.type = type;
			ac = asc;

		}
		this.historique.put(type, ac);

	}

}
