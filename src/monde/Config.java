package monde;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Config {
	public int vie;
	public int puissance;
	public int population;
	public int porte;
	public int vitesse;
	public int vitesseTire;
	public List<Soldat> soldats;


	public void gererActions(EcranDessin ecran) {

		if (soldats.size() < population) {
				this.gererAugmentationPopulation(ecran, population-soldats.size());
				return;
		}
		for (Soldat soldat : soldats) {
			this.gererActions(soldat, ecran);

		}

	}

	public void gererAugmentationPopulation(EcranDessin ecran, int n) {
		List<Reproduction> reproductions = new ArrayList<>();
		for (Soldat soldat : soldats) {
			if (soldat.cible == null) {
				Reproduction reproduction = ecran.donnerRessource(Reproduction.class, soldat);
				if (reproduction != null) {
					reproduction.libre = false;
					soldat.cible = reproduction;
					soldat.deplacer(reproduction.position, soldat.vitesse()*ecran.vitesseFactor);
					n--;
					if (n == 0) {
						for (Reproduction r : reproductions) {
							r.libre = true;
						}
					}
				}
			}
		}
		for (Reproduction r : reproductions) {
			r.libre = true;
		}

	}

	public void gererActions(Soldat soldat, EcranDessin ecranDessin) {
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
				soldat.deplacer(vie.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.puissances.size() < puissance) {
			Puissance puissance = ecranDessin.donnerRessource(Puissance.class, soldat);
			if (puissance != null) {
				soldat.cible = puissance;
				soldat.deplacer(puissance.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}

		if (soldat.vitesses.size() < vitesse) {
			Vitesse vitesse = ecranDessin.donnerRessource(Vitesse.class, soldat);
			if (vitesse != null) {
				soldat.cible = vitesse;
				soldat.deplacer(vitesse.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.vitesseTires.size() < vitesseTire) {
			VitesseTire vitesseTire = ecranDessin.donnerRessource(VitesseTire.class, soldat);
			if (vitesseTire != null) {
				soldat.cible = vitesseTire;
				soldat.deplacer(vitesseTire.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		if (soldat.portes.size() < porte) {
			Porte porte = ecranDessin.donnerRessource(Porte.class, soldat);
			if (porte != null) {
				soldat.cible = porte;
				soldat.deplacer(porte.position, soldat.vitesse()*ecranDessin.vitesseFactor);
				return;
			}
		}
		

	}
	public void attaquer(Soldat soldat , Config adversaire,EcranDessin ecranDessin) {
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
		ecranDessin.projectiles.add(projectile);
		
	}

}
