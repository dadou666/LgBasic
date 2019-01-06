package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import editeur.Executeur;
import editeur.Terminal;
import reader.Factory;
import semantique.VerificationFonction;

public class EcranJeux extends JComponent implements KeyListener, WindowListener {

	public Config config1;
	public Config config2;
	public static int tailleCase = 40;
	public float vitesseFactor = 3;
	public float vitesseTireFactor = 7;
	public int porteFactor = 15;
	public List<Projectile> projectiles = new ArrayList<>();
	public List<Projectile> projectilesTmp = new ArrayList<>();
	public List<Ressource> ressources = new ArrayList<>();
	public boolean stop = false;
	public JLabel soldatDetruit1;
	public JLabel soldatDetruit2;

	public API.api$ressourcesVide ressources() {
		API.api$ressourcesVide rs = new API.api$ressourcesVide();
		for (Ressource r : ressources) {
			if (!r.libre) {
				API.api$ressources tmp = null;
				if (r instanceof Vie) {
					tmp = new API.api$vie();
				}
				if (r instanceof Vitesse) {
					tmp = new API.api$vitesse();
				}
				if (r instanceof VitesseTire) {
					tmp = new API.api$vitesseTire();
				}
				if (r instanceof Puissance) {
					tmp = new API.api$puissance();
				}
				if (r instanceof Porte) {
					tmp = new API.api$porte();
				}
				if (r instanceof Reproduction) {
					tmp = new API.api$reproduction();
				}
				tmp.suivant = rs;
				rs = tmp;
			}
		}
		return rs;

	}

	PrintWriter out;

	public <T extends Ressource> T donnerRessource(Class<T> cls, Soldat soldat) {
		Ressource rs = null;
		for (Ressource r : ressources) {
			if (r.getClass() == cls && r.libre) {
				float dist = soldat.distance(r);
				if (dist > 20) {
					if (rs == null) {
						rs = r;
					} else {
						if (dist < soldat.distance(rs)) {
							rs = r;
						}

					}
				}

			}
		}
		if (rs != null) {
			rs.libre = false;
		}
		return (T) rs;
	}

	public EcranJeux(Sauvegarde sauvegarde, Config config1, Config config2)
			throws InstantiationException, IllegalAccessException {

		RessourceSauvegardeVide tmp = sauvegarde.ressources;
		while (tmp instanceof RessourceSauvegarde) {
			RessourceSauvegarde rs = (RessourceSauvegarde) tmp;
			Point p = new Point(rs.x * tailleCase + tailleCase / 2, rs.y * tailleCase + tailleCase / 2);

			Ressource ressource = rs.ressource;
			ressource.libre = true;
			ressource.position = p;

			this.ressources.add(ressource);
			tmp = rs.suivant;

		}
		Point p = new Point(sauvegarde.x1 * tailleCase + tailleCase / 2, sauvegarde.y1 * tailleCase + tailleCase / 2);

		this.config1 = config1;
		Soldat s = new Soldat();
		s.position = p;
		s.config = this.config1;
		this.config1.soldats.add(s);
		this.config2 = config2;
		p = new Point(sauvegarde.x2 * tailleCase + tailleCase / 2, sauvegarde.y2 * tailleCase + tailleCase / 2);
		s = new Soldat();
		s.position = p;
		s.config = this.config2;
		this.config2.soldats.add(s);
	}

	public void loop()
			throws InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		while (!config1.soldats.isEmpty() && !config2.soldats.isEmpty() && !stop) {
			Thread.sleep(100);
			this.step();
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					EcranJeux.this.repaint();

				}

			});
		}

	}

	public void lancerThread() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					loop();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		});
		t.start();
	}

	public void step() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.soldatDetruit1.setText("" + config1.nombreDetruit);
		this.soldatDetruit2.setText("" + config2.nombreDetruit);

		this.config1.deplacer(this);
		this.config2.deplacer(this);
		projectilesTmp.clear();
		projectilesTmp.addAll(projectiles);
		projectiles.clear();
		for (Projectile p : projectilesTmp) {
			
				p.etat.step(this, p);
				if (p.etat != null) {
					projectiles.add(p);
				}
			
		}

		config1.executer(this, config2);
		config2.executer(this, config1);
		config1.gererActions(this, config2);
		config2.gererActions(this, config1);
	}

	public void paint(Graphics g) {
		for (Soldat s : config1.soldats) {
			s.color = Color.BLACK;
			s.paint(g);
		}
		for (Soldat s : config2.soldats) {
			s.color = Color.RED;
			s.paint(g);
		}
		for (Projectile p : this.projectiles) {
			p.paint(g);
		}
		for (Ressource r : this.ressources) {
			if (r.libre)
				r.paint(g);
		}

	}

	public static void main(String[] a) {

		try {
			String srcSauvegarde = new String(Files.readAllBytes(Paths.get(JeuxExecuteur.chemin, "sauvegarde.txt")));
			Factory factory = new Factory(srcSauvegarde);
			Sauvegarde sauvegarde = (Sauvegarde) factory.toObject();
			JFrame window = new JFrame();
			// window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setBounds(30, 30, sauvegarde.nx * tailleCase, sauvegarde.ny * tailleCase);
			EcranJeux mc;
			mc = new EcranJeux(sauvegarde, new Config(), new Config());
			window.addWindowListener(mc);

			window.getContentPane().add(mc);
			window.setVisible(true);
			window.addKeyListener(mc);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		stop = true;
		System.out.println(" closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		stop = true;
		System.out.println(" closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
