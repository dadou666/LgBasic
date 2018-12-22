package monde;

import java.awt.Color;
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
