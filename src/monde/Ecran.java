package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class Ecran extends JComponent implements KeyListener {
	public static int width = 512;
	public static int height = 512;
	public Map<String, Balle> balles = new HashMap<>();
	PrintWriter out;

	public Ecran() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				lancerServeur();

			}

		});
		t.start();

	}

	public void lancerServeur() {
		ServerSocket listener;
		try {
			listener = new ServerSocket(9090);
			while (true) {
				System.out.println(" accept ");
				Socket socket = listener.accept();
				lancerClientHorloge(socket);
				lancerClientExecuter(socket);

			}

		} catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		}

	}

	public void lancerClientHorloge(Socket socket) {

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(30);
							out.println("Boucler\n");
							out.flush();
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									synchronized (balles) {
										for (Map.Entry<String, Balle> balle : balles.entrySet()) {
											balle.getValue().deplacer(Ecran.this, balle.getKey());

										}
									}
									repaint();

								}

							});
							// System.out.println("Boucler");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							return;
						}

					}

				}

			});
			t.start();

		} catch (IOException e) {

		}

	}

	public void lancerClientExecuter(Socket socket) {
		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			List<String> commandes = new ArrayList<>();
			while (true) {
				String commande = input.readLine();
				if (commande != null) {
					System.out.println(commandes);
					if (commande.equals("Executer")) {

						final List<String> commandesFinal = new ArrayList<>();
						commandesFinal.addAll(commandes);
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								executerCommandes(commandesFinal);

							}

						});
						commandes.clear();
					} else {

						commandes.add(commande);
					}

				} else {
					return;
				}
			}

		} catch (IOException e) {

		}

	}

	public void executerCommandes(List<String> commandes) {
		synchronized (balles) {
			if (commandes.isEmpty()) {
				this.balles = new HashMap<>();
				this.repaint();
				return;
			}
			for (String c : commandes) {
				this.executerCommande(c);

			}
		}
		this.repaint();

	}

	public void executerCommande(String commande) {
		String[] commandes = commande.split(" ");
		if (commandes[0].equals("Creer")) {
			if (commandes.length >= 6) {
				int x = Integer.parseInt(commandes[1]);
				int y = Integer.parseInt(commandes[2]);
				String couleur = commandes[3];
				int rayon = Integer.parseInt(commandes[4]);
				String ref = commandes[5];
				Balle balle = new Balle();
				balle.position = new Point(x, y);

				balle.rayon = rayon;
				if (couleur.equals("rouge")) {
					balle.color = Color.red;
				}
				if (couleur.equals("noir")) {
					balle.color = Color.BLACK;
				}
				if (couleur.equals("bleue")) {
					balle.color = Color.BLUE;
				}
				if (couleur.equals("vert")) {
					balle.color = Color.green;
				}
				synchronized (balles) {
					
				
				balles.put(ref, balle);
				}
			}

		}

		if (commandes[0].equals("Deplacer")) {
			String ref = commandes[1];
			int x = Integer.parseInt(commandes[2]);
			int y = Integer.parseInt(commandes[3]);
			float vitesse = Float.parseFloat(commandes[4]);
			Balle balle = this.balles.get(ref);
			if (balle != null) {
				balle.deplacer(new Point(x, y), vitesse);
			}

		}

		if (commandes[0].equals("Supprimer")) {
			String ref = commandes[1];
			synchronized (balles) {
				balles.remove(ref);
			}
		}
		if (commandes[0].equals("Effacer")) {
		
			synchronized (balles) {
				balles.clear();
			}
		}

	}

	public void paint(Graphics g) {
		for (Map.Entry<String, Balle> e : balles.entrySet()) {
			e.getValue().paint(g);

		}

	}

	public static void main(String[] a) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		Ecran mc = new Ecran();
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		out.println("KeyPressed " + arg0.getKeyChar() + "\n");
		out.flush();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
