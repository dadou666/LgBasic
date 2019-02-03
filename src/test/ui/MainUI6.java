package test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import editeur.Controlleur;
import editeur.ObjetInterface;
import editeur.SimpleExecuteur;
import execution.Traducteur;
import ihm.swing.SwingBuilder;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import semantique.Erreur;
import semantique.Verificateur;
import test.ui.TestAPI.api$addition;

public class MainUI6 {
	public static void main(String args[]) throws IOException, ClassNotFoundException, NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
		SimpleExecuteur se = new SimpleExecuteur();
		Verificateur verificateur = new Verificateur(TestAPI.class, se.typeReserve(),
				"F://GitHub//LgBasic//src//test//ui");
		if (!verificateur.erreurs.isEmpty() || verificateur.types.isEmpty()) {
			System.out.println(" ko ");
			for (Erreur e : verificateur.erreurs) {
				System.out.println(e);

			}
			return;
		}
		Traducteur traducteur = new Traducteur("test" + System.currentTimeMillis(), verificateur);
		traducteur.typesReserve.put("base$string", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.api = TestAPI.class;
		traducteur.literalTracducteurs.put("base$string", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls;

		cls = traducteur.traduire();
		TestAPI o=(TestAPI) cls.newInstance();
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingBuilder sb = new SwingBuilder();
		Controlleur oi = new Controlleur(verificateur, "api$addition", null, sb);
		JButton calculer = new JButton("Calculer");
		sb.painter.add((s)->{ s.add(calculer);});
		Controlleur oi2 = new Controlleur(verificateur, "model$affichage", null, sb);
		
		calculer.addActionListener((e)->{
			try {
				o.addition = (api$addition) traducteur.construire(oi.contenu);
				Method m = cls.getMethod("model$afficher_0");
				Object obj = m.invoke(o);
				oi2.init(obj);
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
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} );
		sb.paint("calcul");

	}

}
