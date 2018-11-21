package quantification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Ref;
import model.TypeDef;
import model.Univers;
import semantique.Verificateur;

public class Demonstration {
	public Element element;
	public Ref type;
	public Verificateur verificateur;
	
	public void listeSousTypes(String t,List<String> sousTypes) {
		TypeDef td= verificateur.types.get(t);
		if (!td.estAbstrait) {
			sousTypes.add(t);
		}
		for(Map.Entry<String, TypeDef> e:verificateur.types.entrySet()) {
			td = e.getValue();
			if (td.superType != null) {
				if (td.superType.nomRef().equals(t)) {
					this.listeSousTypes(e.getKey(), sousTypes);
				}
			}
		}
		
		
	}
	public void produitCartesien(List<List<String>> elements,int idx,List<String> result,List<List<String>> results) {
		if (elements.size() == idx) {
			results.add(result);
			return;
		}
		for(String s:elements.get(idx)) {
			List<String> nvResult = new ArrayList<>();
			nvResult.addAll(result);
			nvResult.add(s);
			produitCartesien(elements,idx+1,nvResult,results);
			
		}
		
		
		
	}

}
