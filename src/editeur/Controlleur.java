package editeur;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ihm.swing.Painter;
import ihm.swing.SwingBuilder;
import model.Var;
import semantique.Verificateur;

public class Controlleur implements Painter {
	public JLabel label;

	public ArbreAvecContenu contenu;
	public ArbreAvecContenu racine;
	public Arbre valeur;
	public List<JButton> acces = new ArrayList<>();
	public Verificateur verificateur;
	public Map<String, Map<String, Noeud>> historique = new HashMap<>();
	public SwingBuilder sb;

	public static int elementWidth = 200;
	public static int elementHeight = 20;
	public static int nombreDeLigne = 10;

	public Controlleur(Verificateur verificateur, String type, Object object) {
		this(verificateur, type, object, null);

	}

	public Controlleur(Verificateur verificateur, String type, Object object, SwingBuilder sb) {
		label = new JLabel();
		this.verificateur = verificateur;
		if (sb == null) {
			sb = new SwingBuilder();
		}
		this.sb = sb;
		sb.painter.add(this);
		label.setText(type);

		this.creerContenu(type, object);

	}

	public void creerContenu(String type, Object object) {
		contenu = new ArbreAvecContenu();
		contenu.editeur = this;
		contenu.type = type;
		racine = contenu;

		contenu.noeuds = this.creerMap(type, object);
		contenu.editer = new JButton("/");
		contenu.editer.addActionListener(contenu);

	}

	public void init(Object object) {
		String type = object.getClass().getSimpleName();
		if (contenu.type.equals(type)) {
			contenu.init(object);

		} else {
			this.creerContenu(type, object);
		}

	}

	public void construire() {
		for (Painter p : sb.painter) {
			p.paint(sb);
		}
		sb.open(contenu.type);

	}

	public Object getObject(Object object, String champ) {
		if (object != null) {
			try {
				Field field = object.getClass().getField(champ);
				return field.get(object);

			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	public String getType(String type, Object object) {
		if (object == null) {
			return type;
		}
		return object.getClass().getSimpleName();
	}

	Map<String, Noeud> creerMap(String type, Object object) {
		type = this.getType(type, object);
		Map<String, Noeud> map = new HashMap<>();
		List<Var> attributs = new ArrayList<>();
		verificateur.listeVarAvecType(type, attributs);

		for (Var attribut : attributs) {

			if (verificateur.types.get(attribut.type.nomRef()) == null) {
				Feuille feuille = new Feuille();
				feuille.valeur = new JTextField();
				Object o = this.getObject(object, attribut.nom);
				if (o != null) {
					feuille.valeur.setText(o.toString());
				}
				feuille.type = attribut.type.nomRef();
				feuille.nom = new JLabel(attribut.nom);
				map.put(attribut.nom, feuille);

			} else {

				List<String> tmp = new ArrayList<String>();
				verificateur.listerTypesCompatible(attribut.type.nomRef(), tmp, true);
				Vector<String> sousTypes = new Vector<>();
				for (String t : tmp) {
					sousTypes.add(t);
				}
				Object objectAttribut = this.getObject(object, attribut.nom);

				String tmpType = sousTypes.get(0);
				tmpType = this.getType(tmpType, objectAttribut);
				if (sousTypes.size() > 1) {
					ArbreAvecChoix arbre = new ArbreAvecChoix();
					arbre.editeur = this;
					arbre.nom = attribut.nom;
					if (!verificateur.estTypeVide(tmpType)) {
						ArbreAvecContenu ac = new ArbreAvecContenu();
						ac.editer = new JButton(attribut.nom);
						ac.editer.addActionListener(ac);
						ac.editeur = this;
						ac.type = tmpType;
						ac.noeuds = this.creerMap(tmpType, objectAttribut);

						arbre.contenu = ac;
					} else {
						ArbreSansContenu asc = new ArbreSansContenu();

						asc.label = new JLabel(attribut.nom);
						asc.type = tmpType;
						arbre.contenu = asc;

					}
					arbre.contenu.type = tmpType;

					arbre.types = new JComboBox<>(sousTypes);
					arbre.types.addActionListener(arbre);

					map.put(attribut.nom, arbre);
				} else {
					ArbreSansChoix arbre = new ArbreSansChoix();
					arbre.nom = attribut.nom;
					if (!verificateur.estTypeVide(tmpType)) {
						ArbreAvecContenu ac = new ArbreAvecContenu();
						ac.editer = new JButton(attribut.nom);
						ac.editer.addActionListener(ac);
						ac.editeur = this;
						ac.type = tmpType;
						ac.noeuds = this.creerMap(tmpType, objectAttribut);
						arbre.contenu = ac;
					} else {
						ArbreSansContenu asc = new ArbreSansContenu();

						asc.label = new JLabel(attribut.nom);
						asc.type = tmpType;
						arbre.contenu = asc;

					}

					arbre.contenu.type = tmpType;
					arbre.label = new JLabel(tmpType);
					map.put(attribut.nom, arbre);
				}

			}

		}

		return map;

	}

	@Override
	public void paint(SwingBuilder sb) {
		sb.setSize(elementWidth, elementHeight);

		this.sb.beginY();
		sb.beginX();

		sb.add(label);
		sb.setSize(2 * elementWidth, elementHeight);

		sb.setSize(elementWidth, elementHeight);
		sb.end();
		// sb.space(elementHeight);
		sb.beginX();

		int n = 0;
		sb.beginY();
		// sb.add(new JLabel("chemin"));
		while (n < nombreDeLigne) {
			if (n < acces.size()) {
				sb.add(acces.get(n));

			} else {
				sb.beginX();
				sb.space(elementWidth);
				sb.end();
			}
			n++;

		}
		sb.end();
		sb.beginY();
		List<Var> ls = new ArrayList<>();
		verificateur.listeVarAvecType(contenu.type, ls);
		// model.atributs(contenu.type, ls);
		for (Var attribut : ls) {

			this.contenu.noeuds.get(attribut.nom).ajouter(sb);

		}

		sb.end();

		sb.end();
		sb.end();

	}

}
