package monde;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import monde.API.api$action;
import monde.API.api$ecrire;
import monde.API.api$objectif;

public class Config {
	public int vie;
	public int puissance;
	public int population;
	public int porte;
	public int vitesse;
	public int vitesseTire;
	public int nombreDetruit = 0;
	public boolean ressourcesActif = true;
	public boolean populationActif = true;

	public List<Soldat> soldats = new ArrayList<>();
	public List<Soldat> nvSoldats = new ArrayList<>();
	public List<Ressource> tmp = new ArrayList<>();
	public API api;
	public Method method;

	public void executer(EcranJeux ecranJeux, Config adversaire)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (method == null) {
			return;
		}
		api.enemies = adversaire.soldats();
		api.moi = this.soldats();
		if (api.o == null) {
			api.o = new API.api$objectif();
		}
		api.o.population = population;
		api.o.vie = vie;
		api.o.porte = porte;
		api.o.vitesse = vitesse;
		api.o.vitesseTire = vitesseTire;
		api.o.puissance = puissance;
		api.ressources = ecranJeux.ressources();
		API.api$action r = (api$action) method.invoke(api);
		if (r instanceof API.api$objectif) {
			API.api$objectif o = (api$objectif) r;
			this.init(o);
		}
		if (r instanceof API.api$ecrire) {
			API.api$ecrire ecrire = (api$ecrire) r;
			api.objets.put(ecrire.nom, ecrire.valeur);
		}

	}

	public API.api$soldatVides soldats() {
		API.api$soldatVides tmp = new API.api$soldatVides();
		for (Soldat s : soldats) {
			API.api$soldats soldats = new API.api$soldats();
			soldats.suivant = tmp;
			tmp = soldats;
			soldats.porte = s.portes.size();
			soldats.vie = s.vies.size();
			soldats.vitesseTire = s.vitesseTires.size();
			soldats.vitesseDeplacement = s.vitesses.size();
			soldats.puissance = s.puissances.size();
		}
		return tmp;

	}

	public void init(API.api$objectif o) {

		this.vie = o.vie;
		this.population = o.population;
		this.puissance = o.puissance;
		this.vitesse = o.vitesse;
		this.vitesseTire = o.vitesseTire;
		this.porte = o.porte;
		this.ressourcesActif = (o.ressourcesActif instanceof API.api$vrai);
		this.populationActif = (o.populationActif instanceof API.api$vrai);
	}

	public void deplacer(EcranJeux ecranDessin) {
		nvSoldats.clear();
		for (Soldat soldat : soldats) {
			if (soldat.etat != null) {
				soldat.etat.step(ecranDessin, soldat);
			}
		}
		soldats.addAll(nvSoldats);

	}

	public void gererActions(EcranJeux ecran, Config adversaire) {

		if (soldats.size() < population && this.populationActif) {
			this.gererAugmentationPopulation(ecran, population - soldats.size());
			return;
		}
		tmp.clear();
		for (Soldat soldat : soldats) {
			this.gererActions(soldat, ecran, adversaire);

		}
		// System.out.println(" ressources ="+tmp);
		for (Ressource r : tmp) {
			r.libre = true;
		}

	}

	public void gererAugmentationPopulation(EcranJeux ecran, int n) {
		tmp.clear();
		for (Soldat soldat : soldats) {
			if (soldat.etat == null) {
				Reproduction reproduction = ecran.donnerRessource(Reproduction.class, soldat);
				if (reproduction != null) {

					tmp.add(reproduction);

					soldat.deplacerPourRessource(reproduction.position, soldat.vitesse() * ecran.vitesseFactor,
							reproduction);
					n--;
					if (n == 0) {
						for (Ressource r : tmp) {
							r.libre = true;
						}
					}
				}
			}
		}
		for (Ressource r : tmp) {
			r.libre = true;
		}

	}

	public void gererActions(Soldat soldat, EcranJeux ecranDessin, Config adversaire) {

		if (soldat.etat != null) {
			return;
		}
		Ressource r = null;
		if (this.ressourcesActif) {
			if (soldat.vies.size() < vie) {
				r = ecranDessin.donnerRessource(Vie.class, soldat);
			}


			if (r == null && soldat.vitesses.size() < vitesse) {
				r = ecranDessin.donnerRessource(Vitesse.class, soldat);

			}
			if (r == null && soldat.vitesseTires.size() < vitesseTire) {
				r = ecranDessin.donnerRessource(VitesseTire.class, soldat);

			}
			if (r == null && soldat.portes.size() < porte) {
				r = ecranDessin.donnerRessource(Porte.class, soldat);

			}
			if (r != null) {
				soldat.deplacerPourRessource(r.position, soldat.vitesse() * ecranDessin.vitesseFactor, r);
				tmp.add(r);
				return;
			}
		}
		this.attaquer(soldat, adversaire, ecranDessin);

	}

	public void attaquer(Soldat soldat, Config adversaire, EcranJeux ecranDessin) {
		if (soldat.puissances.size() < puissance) {
			Puissance r = ecranDessin.donnerRessource(Puissance.class, soldat);
			if (r != null) {
				soldat.deplacerPourRessource(r.position, soldat.vitesse() * ecranDessin.vitesseFactor, r);
				tmp.add(r);
				return;
			}
		}
		Soldat cible = null;
		if (soldat.cible != null) {
			return;
		}
		for (Soldat s : adversaire.soldats) {
			if (cible == null) {
				cible = s;
			} else {
				float distance = s.distance(soldat);
				if (distance > 15 && distance < cible.distance(soldat)) {
					cible = s;

				}

			}
		}
		if (cible == null) {

			return;
		}
		float d = soldat.distance(cible);
		if (d > 0) {

			float p = ecranDessin.porteFactor * soldat.porte();
			if (d > p) {

				float distance = d - p;
				soldat.deplacerPourAttaque(cible.position, distance, soldat.vitesse() * ecranDessin.vitesseFactor,
						cible);

				return;
			}
			soldat.lancerProjectile(ecranDessin, cible);
			System.out.println(" projectile distance =" + d);
		}
	}

}
