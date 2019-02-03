package test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.swing.JFrame;

import editeur.Controlleur;
import editeur.ObjetInterface;
import editeur.SimpleExecuteur;
import ihm.swing.SwingBuilder;
import semantique.Erreur;
import semantique.Verificateur;

public class MainUI5 {
	public static void main(String args[]) throws IOException {
		SimpleExecuteur se= new SimpleExecuteur();
		Verificateur verificateur = new Verificateur(TestAPI.class,se.typeReserve(),"F://GitHub//LgBasic//src//test//ui");
		if (! verificateur.erreurs.isEmpty()  || verificateur.types.isEmpty())  {
			System.out.println(" ko ");
			for( Erreur e:verificateur.erreurs) {
				System.out.println(e);
				
			}
			return;
		}
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Controlleur oi = new Controlleur(verificateur, "model$entreprise", null);
		oi.construire();
	}

}
