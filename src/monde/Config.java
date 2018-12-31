package monde;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Config {
	public int vie;
	public int puissance;
	public int population;
	public int porte;
	public int vitesse;
	public int vitesseTire;
	public List<Soldat> soldats=new ArrayList<>();
	public List<Soldat> nvSoldats = new ArrayList<>();
	public List<Ressource> tmp = new ArrayList<>();
	public API api;
	public Method method;
	public API.api$soldatVides soldats() {
		API.api$soldatVides tmp = new API.api$soldatVides ();
		for(Soldat s:soldats) {
			API.api$soldats soldats = new API.api$soldats();
			soldats.suivant =tmp;
			tmp = soldats;
			soldats.porte = s.portes.size();
			soldats.vie = s.vies.size();
			soldats.vitesseTire = s.vitesseTires.size();
			soldats.vitesseDeplacement = s.vitesses.size();
			soldats.puissance =s.puissances.size();
		}
		return tmp;
		
		
	}
	public void init(API.api$objectif o) {
		
		this.vie = o.vie;
		this.population =o.population;
		this.puissance = o.puissance;
		this.vitesse = o.vitesse;
		this.vitesseTire = o.vitesseTire;
		this.porte = o.porte;
	}
	public void deplacer(EcranJeux ecranDessin) {
		nvSoldats.clear();
		for(Soldat soldat:soldats) {
			soldat.deplacer.deplacer(ecranDessin, soldat);
		}
		soldats.addAll(nvSoldats);
		
		
	}

	public void gererActions(EcranJeux ecran,Config adversaire) {

		if (soldats.size() < population) {
				this.gererAugmentationPopulation(ecran, population-soldats.size());
				return;
		}
		tmp.clear();
		for (Soldat soldat : soldats) {
			this.gererActions(soldat, ecran,adversaire);

		}
		for (Ressource r :tmp) {
			r.libre = true;
		}

	}

	public void gererAugmentationPopulation(EcranJeux ecran, int n) {
		tmp.clear();
		for (Soldat soldat : soldats) {
			if (soldat.cible == null) {
				Reproduction reproduction = ecran.donnerRessource(Reproduction.class, soldat);
				if (reproduction != null) {
					reproduction.libre = false;
					tmp.add(reproduction);
					soldat.cible = reproduction;
					soldat.deplacer(reproduction.position, soldat.vitesse()*ecran.vitesseFactor);
					n--;
					if (n == 0) {
						for (Ressource r :tmp) {
							r.libre = true;
						}
					}
				}
			}
		}
		for (Ressource r :tmp) {
			r.libre = true;
		}

	}

	public void gererActions(Soldat soldat, EcranJeux ecranDessin,Config adversaire) {
		if (soldat.projectile != null) {
			return;
		}
		if (soldat.deplacer != null) {
			return;
		}
		if (soldat.cible != null) {
			return;
		}
		if (soldat.vies.size() < vie) {
			Vie vie = ecranDessin.donnerRessource(Vie.class, soldat);
			if (vie != null) {
				soldat.cible = vie;
				tmp.add(vie);
				soldat.deplacer(vie.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.puissances.size() < puissance) {
			Puissance puissance = ecranDessin.donnerRessource(Puissance.class, soldat);
			if (puissance != null) {
				soldat.cible = puissance;
				tmp.add(puissance);
				soldat.deplacer(puissance.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}

		if (soldat.vitesses.size() < vitesse) {
			Vitesse vitesse = ecranDessin.donnerRessource(Vitesse.class, soldat);
			if (vitesse != null) {
				soldat.cible = vitesse;
				tmp.add(vitesse);
				soldat.deplacer(vitesse.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.vitesseTires.size() < vitesseTire) {
			VitesseTire vitesseTire = ecranDessin.donnerRessource(VitesseTire.class, soldat);
			if (vitesseTire != null) {
				soldat.cible = vitesseTire;
				tmp.add(vitesseTire);
				soldat.deplacer(vitesseTire.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.portes.size() < porte) {
			Porte porte = ecranDessin.donnerRessource(Porte.class, soldat);
			if (porte != null) {
				soldat.cible = porte;
				tmp.add(porte);
				soldat.deplacer(porte.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		this.attaquer(soldat, adversaire, ecranDessin);
		

	}
	public void attaquer(Soldat soldat , Config adversaire,EcranJeux ecranDessin) {
		Soldat cible=null;
		for(Soldat s:adversaire.soldats) {
			if (cible == null) {
				cible=s;
			}else {
				if (s.distance(soldat)< cible.distance(soldat)) {
					cible=s;
					
				}
				
			}
		}
		if (cible == null) {
			return;
		}
		float d = soldat.distance(cible);
		d = (float) Math.sqrt(d);
		float p = ecranDessin.porteFactor*soldat.portes.size();
		if (d> p ) {
			soldat.cibleSoldat = cible;
			soldat.deplacer(cible.position, soldat.vitesse()*ecranDessin.vitesseFactor, d-p);
			return;
		}
		Projectile projectile = new Projectile();
		projectile.cible =cible;
		projectile.puissance = soldat.puissances.size();
		projectile.position = new Point(soldat.position.x,soldat.position.y);
		projectile.deplacer(cible.position, soldat.vitesseTires.size()*ecranDessin.vitesseTireFactor);
		projectile.attaquant = soldat;
		soldat.projectile =projectile;
		ecranDessin.projectiles.add(projectile);
		
	}

}
