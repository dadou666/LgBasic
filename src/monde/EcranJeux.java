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

public class EcranJeux extends JComponent implements KeyListener {
	
	public Config config1;
	public Config config2;
	public static int tailleCase = 40;
	public float vitesseFactor = 10;
	public float vitesseTireFactor = 10;
	public int porteFactor = 10;
	public List<Projectile> projectiles= new ArrayList<>();
	public List<Projectile> projectilesTmp= new ArrayList<>();
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

	public EcranJeux(int nx,int ny) throws InstantiationException, IllegalAccessException {
		List<Point> points = new ArrayList<Point>();
		for(int ix=0;ix < nx-1;ix++) {
			
			for(int iy=0;iy<ny-1;iy++) {
				points.add(new Point(ix*tailleCase+tailleCase/2,iy*tailleCase+tailleCase/2));
				
			}
		}
	List<Class<? extends Ressource>> liste = new ArrayList<>();
	liste.add(Vie.class);
	liste.add(Reproduction.class);
	liste.add(Vitesse.class);
	liste.add(VitesseTire.class);
	liste.add(Puissance.class);
	liste.add(Porte.class);
	Random random = new Random();
	int size = points.size() / 4;
	while(points.size()> size) {
		Point p = points.get(random.nextInt(points.size()-1));
		Class<? extends Ressource> rc= liste.get(random.nextInt(liste.size()-1));
		Ressource ressource = rc.newInstance();
		ressource.position =p;
		
		this.ressources.add(ressource);
		System.out.println(" ajout "+rc);
		points.remove(p);
		
	}
	Point p = points.get(random.nextInt(points.size()-1));
	points.remove(p);
	this.config1 = new Config();
	Soldat s = new Soldat();
	s.position = p;
	s.config = this.config1;
	this.config1.soldats.add(s);
	this.config2 = new Config();
	 p = points.get(random.nextInt(points.size()-1));
	points.remove(p);
	s = new Soldat();
	s.position = p;
	s.config = this.config2;
	this.config2.soldats.add(s);
	}
	
	public void step() {
		this.config1.deplacer(this);
		this.config2.deplacer(this);
		projectilesTmp.clear();
		for(Projectile p:projectiles) {
			p.deplacer.deplacer(this, p);
			if (p.deplacer != null) {
				projectilesTmp.add(p);
			}
		}
		List<Projectile> tmp = projectiles;
	
		projectiles = projectilesTmp;
		projectilesTmp = tmp;
		config1.gererActions(this, config2);
		config2.gererActions(this, config1);
		
	}

	



	public void paint(Graphics g) {
		for(Soldat s:config1.soldats) {
			s.color =Color.BLACK;
			s.paint(g);
		}
		for(Soldat s:config2.soldats) {
			s.color = Color.RED;
			s.paint(g);
		}
		for(Projectile p:this.projectiles) {
			p.paint(g);
		}
		for(Ressource r:this.ressources) {
			r.paint(g);
		}

	}

	public static void main(String[] a) {
		int nx = 40;
		int ny=  20;
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, nx*tailleCase, ny*tailleCase);
		EcranJeux mc;
		try {
			mc = new EcranJeux(nx,ny);
			window.getContentPane().add(mc);
			window.setVisible(true);
			window.addKeyListener(mc);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
}