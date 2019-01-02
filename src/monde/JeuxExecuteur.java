package monde;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import editeur.Executeur;
import editeur.Terminal;
import execution.Traducteur;
import ihm.swing.SwingBuilder;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import semantique.Verificateur;
import semantique.VerificationFonction;

public class JeuxExecuteur implements Executeur {
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

		int nx = 40;
		int ny = 20;
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, nx * EcranJeux.tailleCase, ny * EcranJeux.tailleCase);
		EcranJeux mc;
		try {
			mc = new EcranJeux(nx, ny, config1, config2);
			window.addWindowListener(mc);
			window.getContentPane().add(mc);
			window.setVisible(true);
			window.addKeyListener(mc);
			mc.lancerThread();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
				config.method = cls.getMethod(module + "$config1");
			} else {
				config.method = cls.getMethod(module + "$config2");

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
			VerificationFonction fd1 = terminal.verificateur.fonctions.get(module + "$config1/0");
			VerificationFonction fd2 = terminal.verificateur.fonctions.get(module + "$config2/0");
			if (fd1 == null) {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction config1/0");
				return false;
			}
			if (fd2 == null) {
				terminal.msgImpossibleExecuter.setText("Il manque dans le module une fonction config2/0");
				return false;
			}
			if (!terminal.verificateur.herite(fd1.typeRetour, "api$action")) {
				terminal.msgImpossibleExecuter.setText("Le type de la fonction config1/0 doit être api$action");
				return false;
			}
			if (!terminal.verificateur.herite(fd2.typeRetour, "api$action")) {
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
					verificateur = new Verificateur(je.classAPI(),je.typeReserve(),"F://GitHub//LgBasic//src//monde");
					if (verificateur.erreurs.isEmpty()) {
					try {
						je.executer(verificateur, "main");
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
					}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	//		}
//		});

	}
}
