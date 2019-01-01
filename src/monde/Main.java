package monde;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import editeur.SimpleExecuteur;
import editeur.Terminal;
import ihm.swing.SwingBuilder;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SwingBuilder sb = new SwingBuilder(frame);
				Terminal.chemin = "F://GitHub//LgBasic//src//monde";
				new Terminal(new JeuxExecuteur(),sb);
				sb.open("Langage fonctionnel");
				frame.setVisible(true);

			}
		});

	}

}
