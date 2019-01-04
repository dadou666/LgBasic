package monde;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Soldat extends Entite {
	public Color color;
	public Stack<Vie> vies = new Stack<>();

	public List<Porte> portes = new ArrayList<>();
	public List<Vitesse> vitesses = new ArrayList<>();
	public List<VitesseTire> vitesseTires = new ArrayList<>();
	public Stack<Puissance> puissances = new Stack<>();
	public Reproduction reproduction;
	
	public Config config;
	
	public Soldat() {
		this.rayon = 20;
	}
	public int rayon() {
		float v= this.vies.size();
		float maxv = config.vie;
		float p=v/maxv;
		float r = rayon;
		r =r *p;
		int tmpRayon =(int) r;
		if (tmpRayon > 5) {
			return tmpRayon;
		}
		return 5;
		
		
		
	}
	public void detruire() {
		if (reproduction != null) {
			reproduction.libre = true;
		}
		for (Porte p : portes) {
			p.libre = true;
		}
		for (Vitesse v : vitesses) {
			v.libre = true;
		}
		for (VitesseTire vt : vitesseTires) {
			vt.libre = true;
		}

		for (Puissance p : puissances) {
			p.libre = true;
		}
		
		config.soldats.remove(this);
	}

	public void deplacerPourAttaque(Point destination,float distance, float vitesse,Soldat cible) {
		this.etat = new DeplacerSoldatPourAttaque(cible,this, destination, vitesse,distance);
	}
	public void deplacerPourRessource(Point destination, float vitesse,Ressource ressource) {
		this.etat = new DeplacerSoldatPourRessource(ressource,this, destination, vitesse);
	}
	public void lancerProjectile(EcranJeux ecranJeux,Soldat cible) {
		Projectile projectile = new Projectile();
		projectile.cible = cible;
		projectile.puissance = this.puissances.size();
		projectile.position = new Point(position.x, position.y);
		DeplacerProjectile deplacerProjectile= new DeplacerProjectile(projectile,cible.position, vitesseTire() * ecranJeux.vitesseTireFactor);
		projectile.attaquant = this;
		projectile.etat =deplacerProjectile;
		etat = new Attaquer(deplacerProjectile);
		ecranJeux.projectiles.add(projectile);
	}

	
	public Ressource cible;

	public float vitesse() {
		return vitesses.size() + 1;
	}
	public float porte() {
		return portes.size()+1;
	}

	public float vitesseTire() {
		return vitesseTires.size() + 1;
	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return color;
	}

	public float distance(Ressource r) {
		int dx = position.x - r.position.x;
		int dy = position.y - r.position.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public float distance(Soldat r) {
		int dx = position.x - r.position.x;
		int dy = position.y - r.position.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

}
