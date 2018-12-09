package quantification;

import java.util.HashMap;
import java.util.Map;

import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.TypeDef;
import model.VarRef;
import semantique.Verificateur;

public class Instanciation extends Transformation {
	public String var;
	public String type;
	@Override
	public void ajouterElements(Verificateur verif, Element element) {
	
		Map<String,String> map = new HashMap<>();
		verif.listeVarAvecType(type, map);
		Objet objet= new Objet();
		String tmp []= type.split("\\$");
		
		objet.type = new Ref(tmp[0],tmp[1]);
		for(Map.Entry<String, String> e:map.entrySet()) {
			ObjetParam op = new ObjetParam();
			op.nom =e.getKey();
			op.expression = new VarRef(var+"_"+e.getKey());
			objet.params.add(op);
		}
		Element enfant = new Element();
		for(Map.Entry<String, String> e:element.params.entrySet()) {
			if (e.getKey().equals(var)) {
				for(Map.Entry<String, String> e2:map.entrySet()) {
					enfant.params.put(var+"_"+e2.getKey(), e.getValue());
				}
				
			} else {
				enfant.params.put(e.getKey(), e.getValue());
			}
			
		}
		Simplificateur simplificateur = new Simplificateur();
		simplificateur.variables.put(var, objet);
		enfant.expression = element.expression.transformer(simplificateur);
		elements.add(enfant);
		enfant.parent = element;

		
		
	}

}
