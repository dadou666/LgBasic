package monde;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Soldat extends Entite {
	public Stack<Vie> vies = new Stack<>();
	public List<Porte> portes = new ArrayList<>();
	public List<Vitesse> vitesses = new ArrayList<>();
	public List<VitesseTire> vitesseTires = new ArrayList<>();
	public List<Puissance> puissances = new ArrayList<>();
	public Reproduction reproduction;
	public Soldat cibleSoldat;
	public Config config;
	public void detruire() {
		reproduction.libre = true;
		for(Porte p:portes) {
			p.libre = true;
		}
		for(Vitesse v:vitesses) {
			v.libre = true;
		}
		for(VitesseTire vt:vitesseTires) {
			vt.libre=true;
		}
		
		for(Puissance p:puissances) {
			p.libre =true;
		}
		config.soldats.remove(this);
	}
	public void finDeplacer(EcranDessin ecranDessin) {
		if (cibleSoldat != null) {
			Projectile projectile = new Projectile();
			projectile.cible =cibleSoldat;
			projectile.puissance = puissances.size();
			projectile.position = new Point(position.x,position.y);
			projectile.deplacer(cible.position, vitesseTires.size()*ecranDessin.vitesseTireFactor);
			ecranDessin.projectiles.add(projectile);
			cibleSoldat = null;
			return;
		}
		if (cible != null) {
			if (cible.libre) {
				cible.executer(ecranDessin, this);
				cible.libre = false;
				
			}
			
		}
		
	}

	public Ressource cible;
	public float vitesse() {
		return vitesses.size();
	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	public int distance(Ressource r) {
		int dx = position.x - r.position.x;
		int dy = position.y - r.position.y;
		return dx * dx + dy * dy;
	}
	public int distance(Soldat r) {
		int dx = position.x - r.position.x;
		int dy = position.y - r.position.y;
		return dx * dx + dy * dy;
	}
	
	

}
