package test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.swing.JFrame;

import editeur.ObjetInterface;
import editeur.SimpleExecuteur;
import ihm.swing.SwingBuilder;
import semantique.Verificateur;

public class MainUI2 {
	public static void main(String args[]) throws IOException {
		SimpleExecuteur se= new SimpleExecuteur();
		Verificateur verificateur = new Verificateur(se.classAPI(),se.typeReserve(),"F://GitHub//LgBasic//src//test//ui");
		assertTrue(verificateur.erreurs.isEmpty());
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingBuilder sb = new SwingBuilder(window);
		ObjetInterface oi = new ObjetInterface("model$personne",verificateur,sb);
		assertTrue(oi.champs.size() == 3);
		oi.sb.open("Test");
	}

}
