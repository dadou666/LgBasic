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

class EcranDessin extends JComponent implements KeyListener {
	public static int width = 512;
	public static int height = 512;
	public Config config1;
	public Config config2;
	public float vitesseFactor = 10;
	public float vitesseTireFactor = 10;
	public int porteFactor = 10;
	public List<Projectile> projectiles= new ArrayList<>();
	public List<Ressource> ressources = new ArrayList<>();
	
	
	PrintWriter out;
	public <T extends Ressource>  T donnerRessource(Class<T> cls,Soldat soldat) {
		Ressource rs = null;
		for(Ressource r:ressources) {
			if (r.getClass() == cls && r.libre) {
				if (rs == null) {
					rs =r;
				}else {
					if (soldat.distance(r) <soldat.distance(rs)) {
						rs=r;
					}
				
				}
			
			}
		}
		return (T) rs;
	}

	public EcranDessin() {


	}
	
	public void deplacer() {
		
	}

	



	public void paint(Graphics g) {
		

	}

	public static void main(String[] a) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		EcranDessin mc = new EcranDessin();
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

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
}
