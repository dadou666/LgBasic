package monde;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import monde.API.api$action;
import monde.API.api$ecrire;
import monde.API.api$objectif;
import monde.API.api$recolter;

public class Config {

	public API.api$config config;
	public int nombreDetruit = 0;

	public List<Soldat> soldats = new ArrayList<>();
	public List<Soldat> nvSoldats = new ArrayList<>();
	int energie;
	public API api;
	public Method method;

	public void calculerEnergie() {
		for (Soldat soldat : soldats) {
			energie += soldat.energies.size();

		}
	}

	public void executer(EcranJeux ecranJeux, Config adversaire)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (method == null) {
			return;
		}
		api.enemies = adversaire.soldats();
		api.moi = this.soldats();
		if (api.strategie == null) {
			api.strategie = new API.api$strategie();
		}

		api.ressources = ecranJeux.ressources();
		API.api$operation r = (API.api$operation) method.invoke(api);
		if (r instanceof API.api$strategie) {
			api.strategie = (API.api$strategie) r;
		}
		if (r instanceof API.api$ecrire) {
			API.api$ecrire ecrire = (api$ecrire) r;
			api.objets.put(ecrire.nom, ecrire.valeur);
		}

	}

	public int population(API.api$configUnit configUnit) {
		int n = 0;
		for (Soldat s : this.soldats) {
			if (s.configUnit == configUnit) {
				n++;
			}
		}
		return n;

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

	public void deplacer(EcranJeux ecranDessin) {
		nvSoldats.clear();
		for (Soldat soldat : soldats) {
			if (soldat.etat != null) {
				soldat.etat.step(ecranDessin, soldat);
			}
		}
		soldats.addAll(nvSoldats);

	}

	public API.api$action action(API.api$configUnit configUnit) {
		if (configUnit == this.config.dieu) {
			return this.api.strategie.dieu;
		}
		if (configUnit == this.config.soldat) {
			return this.api.strategie.soldat;
		}
		if (configUnit == this.config.maitre) {
			return this.api.strategie.maitre;
		}
		if (configUnit == this.config.roi) {
			return this.api.strategie.roi;
		}
		if (configUnit == this.config.ouvrier) {
			return this.api.strategie.ouvrier;
		}
		return null;

	}

	public void gererActions(EcranJeux ecran, Config adversaire) {
		this.calculerEnergie();
		for (int priorite = 1; priorite <= 5; priorite++) {
			this.gererAugmentationPopulation(priorite, this.config.dieu, ecran);
			this.gererAugmentationPopulation(priorite, this.config.roi, ecran);
			this.gererAugmentationPopulation(priorite, this.config.maitre, ecran);
			this.gererAugmentationPopulation(priorite, this.config.soldat, ecran);
			this.gererAugmentationPopulation(priorite, this.config.ouvrier, ecran);
		}

		for (Soldat soldat : soldats) {
			this.gererActions(soldat, ecran, adversaire);

		}
	}

	public void gererAugmentationPopulation(int priorite, API.api$configUnit configUnit, EcranJeux ecran) {
		if (configUnit.energie < this.energie) {
			return;
		}
		API.api$action action = action(configUnit);
		if (action.creer.valeur() != priorite) {
			return;
		}
		int populationCible = action.population;
		int population = population(configUnit);
		if (population >= populationCible) {
			return;
		}
		int n = populationCible - population;
		for (Soldat soldat : soldats) {
			if (soldat.etat == null) {
				Reproduction reproduction = ecran.donnerRessource(Reproduction.class, soldat);
				if (reproduction != null) {
					reproduction.configUnit = configUnit;

					soldat.deplacerPourRessource(reproduction.position, soldat.vitesse() * ecran.vitesseFactor,
							reproduction);
					n--;
					if (n == 0) {
						return;
					}
				} else {
					return;
				}
			}
		}

	}

	public void gererActions(Soldat soldat, EcranJeux ecranDessin, Config adversaire) {

		if (soldat.etat != null) {
			return;
		}
		Ressource r = null;
		API.api$action action = this.action(soldat.configUnit);
		API.api$objectif objectif = action.objectif;
		if (soldat.vies.size() < soldat.configUnit.vie) {
			if (!soldat.init ||objectif.recupererVie instanceof API.api$vrai) {
				r = ecranDessin.donnerRessource(Vie.class, soldat);
			}
		}

		if (r == null && soldat.vitesses.size() < soldat.configUnit.vitesse) {
			r = ecranDessin.donnerRessource(Vitesse.class, soldat);

		}
		if (objectif instanceof API.api$recolter) {
			if (r == null && soldat.energies.size() < soldat.configUnit.energie) {
				r = ecranDessin.donnerRessource(Energie.class, soldat);
				if (r == null) {
					API.api$recolter recolter = (api$recolter) objectif;
					objectif = recolter.attaquer;
				}
			}
		}

		if (objectif instanceof API.api$attaquer) {
			if (r == null && soldat.vitesseTires.size() < soldat.configUnit.vitesseTire) {
				r = ecranDessin.donnerRessource(VitesseTire.class, soldat);

			}
			if (r == null && soldat.portes.size() < soldat.configUnit.porte) {
				r = ecranDessin.donnerRessource(Porte.class, soldat);

			}
		}
		if (r != null) {
			soldat.deplacerPourRessource(r.position, soldat.vitesse() * ecranDessin.vitesseFactor, r);

			return;
		}
		
		this.attaquer(soldat, adversaire, ecranDessin);

	}

	public void attaquer(Soldat soldat, Config adversaire, EcranJeux ecranDessin) {
		if (soldat.puissances.size() < soldat.configUnit.puissance) {
			Puissance r = ecranDessin.donnerRessource(Puissance.class, soldat);
			if (r != null) {
				soldat.deplacerPourRessource(r.position, soldat.vitesse() * ecranDessin.vitesseFactor, r);

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
