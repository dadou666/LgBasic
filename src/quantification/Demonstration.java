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
	static public List<String> listeDecomposition(List<String> vars) {
		List<String> result = new ArrayList<String>();
		for (int nbVar = 1; nbVar <= vars.size(); nbVar++) {
			listeDecomposition(vars, null, 0, nbVar, result);
		}

		return result;

	}

	public	static  void listeDecomposition(List<String> vars, String base, int idx, int nbVar, List<String> results) {
		if (nbVar == 0) {
			results.add(base);
			return;
		}
		if (idx == vars.size()) {
			return;
		}

		String nvBase = null;
		if (base == null) {
			nvBase = vars.get(idx);
		} else {
			nvBase = base + "," + vars.get(idx);
		}
		listeDecomposition(vars, nvBase, idx + 1, nbVar - 1, results);
		listeDecomposition(vars, base, idx + 1, nbVar, results);

	}
	
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
	static public void produitCartesien(List<List<String>> elements,int idx,List<String> result,List<List<String>> results) {
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
	static public List<List<String>> produitCartesien(List<List<String>> elements) {
		 List<List<String>> r = new ArrayList<>();
		 produitCartesien(elements, 0, new ArrayList<>(),r);
		 return r;
		
	}

}
