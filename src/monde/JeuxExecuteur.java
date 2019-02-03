package monde;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import editeur.Executeur;
import editeur.ObjetInterface;
import editeur.Terminal;
import execution.Traducteur;
import ihm.swing.SwingBuilder;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import reader.Factory;
import semantique.Verificateur;
import semantique.VerificationFonction;

public class JeuxExecuteur implements Executeur {
	public static String chemin = "";
	@Override
	public Class classAPI() {
		// TODO Auto-generated method stub
		return API.class;
	}

	@Override
	public Map<Class, String> typeReserve() {

		Map<Class, String> map = new HashMap<>();
		map.put(String.class, "base$string");
		map.put(int.class, "base$int");
		return map;
	}

	@Override
	public void executer(Terminal terminal) {
		try {
			executer(terminal.verificateur, terminal.list.getSelectedValue());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void executer(Verificateur v, String module) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, NotFoundException, CannotCompileException, IOException {
		Config config1 = this.creerConfig(v, module, true);
		Config config2 = this.creerConfig(v, module, false);


		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	window.setBounds(30, 30, nx * EcranJeux.tailleCase, ny * EcranJeux.tailleCase+30);
		EcranJeux mc;
		try {
			String srcSauvegarde = new String(Files.readAllBytes(Paths.get(JeuxExecuteur.chemin, "sauvegarde.txt")));
			Factory factory = new Factory(srcSauvegarde);
			Sauvegarde sauvegarde = (Sauvegarde) factory.toObject();
			int nx = sauvegarde.nx;
			int ny = sauvegarde.ny;
			mc = new EcranJeux(sauvegarde, config1, config2);
			window.addWindowListener(mc);
			SwingBuilder sb = new SwingBuilder(window);
			mc.soldatDetruit1 = new JLabel();
			mc.soldatDetruit2 = new JLabel();
			sb.beginY();
			sb.setSize(100, 30);
			sb.beginX();
			sb.setSize(200, 30);
			sb.add(new JLabel("nom soldat detruit rouge"));
			sb.setSize(100, 30);
			sb.add(mc.soldatDetruit1);
			sb.setSize(200, 30);
			sb.add(new JLabel("nom soldat detruit noir"));
			sb.setSize(100, 30);
			sb.add(mc.soldatDetruit2);
			sb.end();
			sb.setSize(nx * EcranJeux.tailleCase, ny * EcranJeux.tailleCase);
			sb.add(mc);
			sb.end();
			
			sb.open("War");
			window.addKeyListener(mc);
			mc.lancerThread();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Config creerConfig(Verificateur v, String module, boolean estConfig1) throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {

		Traducteur traducteur = new Traducteur("test" + System.currentTimeMillis(), v);
		traducteur.typesReserve.put("base$string", String.class);
		traducteur.typesReserve.put("base$int", int.class);
		traducteur.literalTracducteurs = new HashMap<>();
		traducteur.api = this.classAPI();
		traducteur.literalTracducteurs.put("base$string", (String s) -> "\"" + s + "\"");
		traducteur.literalTracducteurs.put("base$int", (String s) -> s);
		Class cls;
		
			cls = traducteur.traduire();

			API o = (API) cls.newInstance();
			Config config = new Config();
			config.api = o;
			if (estConfig1) {
				config.method = cls.getMethod(module + "$strategie1_0");
			} else {
				config.method = cls.getMethod(module + "$strategie2_0");

			}
			return config;



	}

	@Override
	public boolean test(Terminal terminal) {
		String module = terminal.list.getSelectedValue();
		terminal.msgImpossibleExecuter.setText("");
		if (module == null) {
			terminal.msgImpossibleExecuter.setText("Aucun module de selectionné ");
			return false;
		}
		if (terminal.verificateur != null) {
			List<VerificationFonction> fd1 = terminal.verificateur.fonctions.get(module + "$strategie1/0");
			List<VerificationFonction> fd2 = terminal.verificateur.fonctions.get(module + "$strategie2/0");
			if (fd1==null || fd1.size() != 1) {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction strategie/0");
				return false;
			}
			if (fd2==null || fd2.size() != 1) {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction strategie2/0");
				return false;
			}
			if (!terminal.verificateur.herite(fd1.get(0).typeRetour, "api$operation")) {
				terminal.msgImpossibleExecuter.setText("Le type de la fonction config1/0 doit être api$action");
				return false;
			}
			if (!terminal.verificateur.herite(fd2.get(0).typeRetour, "api$operation")) {
				terminal.msgImpossibleExecuter.setText("Le type de la fonction config2/0 doit être api$action");
				return false;
			}

			return true;
		}
		return false;
	}

	public static void main(String[] args) {
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				JeuxExecuteur je = new JeuxExecuteur();
				Verificateur verificateur;
				try {
					chemin ="F://GitHub//LgBasic//src//monde";
					verificateur = new Verificateur(je.classAPI(),je.typeReserve(),chemin);
					if (verificateur.erreurs.isEmpty()) {
				
						JFrame window = new JFrame();
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						SwingBuilder sb = new SwingBuilder(window);
						ObjetInterface.nbColonne = 6;
						ObjetInterface.nbLigne = 32;
						ObjetInterface oi = new ObjetInterface("api$strategie",verificateur,sb);
						oi.sb.open("Test");
						//je.executer(verificateur, "main");
				
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	//		}
//		});

	}
}
