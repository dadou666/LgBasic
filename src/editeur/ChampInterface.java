package editeur;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ChampInterface {
	public JComponent component;
	public ObjetInterface objetInterface;
	public String nom;
	public String type;
	public JLabel label;
	public Map<String,ObjetInterface> historique = new HashMap<>();

}
