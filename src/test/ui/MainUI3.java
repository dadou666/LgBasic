package test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import editeur.ObjetInterface;
import editeur.SimpleExecuteur;
import execution.Traducteur;
import ihm.swing.SwingBuilder;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import monde.JeuxExecuteur;
import semantique.Verificateur;

public class MainUI3 {
	public static void main(String args[]) throws IOException {
		JeuxExecuteur se= new JeuxExecuteur();
		String chemin ="F://GitHub//LgBasic//src//monde";
		Verificateur verificateur = new Verificateur(se.classAPI(),se.typeReserve(),chemin);
		assertTrue(verificateur.erreurs.isEmpty());
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingBuilder sb = new SwingBuilder(window);
		ObjetInterface.nbColonne = 6;
		ObjetInterface.nbLigne = 33;
		ObjetInterface oi = new ObjetInterface("api$strategie",verificateur,sb);
		JButton button= new JButton("Creer objet");
		button.addActionListener((e)-> { 
			Traducteur traducteur = new Traducteur("test" + System.currentTimeMillis(), verificateur);
			traducteur.typesReserve.put("base$string", String.class);
			traducteur.typesReserve.put("base$int", int.class);
			traducteur.literalTracducteurs = new HashMap<>();
			traducteur.api = se.classAPI();
			traducteur.literalTracducteurs.put("base$string", (String s) -> "\"" + s + "\"");
			traducteur.literalTracducteurs.put("base$int", (String s) -> s);
			Class cls;
			
				try {
					cls = traducteur.traduire();
					Object obj = traducteur.construire("api$strategie",oi);
					System.out.println(" obj="+obj);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CannotCompileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			System.out.println("click");});
		sb.add(button);
		oi.sb.open("Test");
	}

}
