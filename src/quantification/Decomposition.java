package quantification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;

import model.Expression;
import model.Objet;
import model.ObjetParam;
import model.Ref;
import model.TransformationExpression;
import model.VarRef;
import semantique.Verificateur;


public class Decomposition extends Transformation {
	public String var;
	public List<String> sousTypes;
	@Override
	public void ajouterElements(Verificateur verif, Element element) {
		
		for(String type:sousTypes) {
			Element enfant = new Element();
			for(Map.Entry<String, String> e:element.params.entrySet()) {
				if (e.getKey().equals(var)) {
					enfant.params.put(var,type);
				} else {
					enfant.params.put(e.getKey(),e.getValue());
				}
			}
			enfant.expression = element.expression;
			enfant.transformation = this;
		}
		
		
	}

	
	

}
