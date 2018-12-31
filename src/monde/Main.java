package monde;

import javax.swing.SwingUtilities;

import editeur.SimpleExecuteur;
import editeur.Terminal;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Terminal.chemin = "F://GitHub//LgBasic//src//monde";
				new Terminal(new JeuxExecuteur()).setVisible(true);

			}
		});

	}

}
