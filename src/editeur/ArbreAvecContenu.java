package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import ihm.swing.SwingBuilder;

public class ArbreAvecContenu extends ArbreContenu implements ActionListener {
	public JButton editer;
	public Map<String, Noeud> noeuds;
	public Controlleur editeur;

	public void init(Object object) {

		for (Map.Entry<String, Noeud> e : noeuds.entrySet()) {
			String nom = e.getKey();
			e.getValue().init(editeur.getObject(object, nom));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (editeur.acces.contains(editer)) {
			int idx = editeur.acces.indexOf(editer);
			this.editeur.acces = editeur.acces.subList(0, idx);

		} else {

			editeur.contenu.ajouterDansChemin();
		}

		editeur.contenu = this;

		editeur.label.setText(type + ":" + editer.getText());
		editeur.construire();

	}

	@Override
	public void ajouter(SwingBuilder sb) {
		sb.add(editer);

	}

	public void ajouterDansChemin() {
		this.editeur.acces.add(editer);
	}

	public void editer() {
		this.editeur.contenu = this;
	}
}
