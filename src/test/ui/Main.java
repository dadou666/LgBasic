package test.ui;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import editeur.SimpleExecuteur;
import editeur.Terminal;
import ihm.swing.SwingBuilder;

public class Main {
	public static void main(String[] args)
			throws XPathExpressionException, SQLException, SAXException, IOException, ParserConfigurationException {
		Terminal.chemin = "F://GitHub//LgBasic//src//test//ui";

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SwingBuilder sb = new SwingBuilder(frame);
				new Terminal(new SimpleExecuteur(), sb);
				frame.setVisible(true);
				sb.open("Langage fonctionnel");
			}
		});

	}

}
