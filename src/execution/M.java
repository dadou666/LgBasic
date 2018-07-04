package execution;

public class M {
	public T types[];
	public String nomTypes[];
	public Objet fonctions[];
	public String nomFonctions[];
	public int [] pile;
	public int sp =0;
	public boolean comparer(int adr1,int adr2) {
		if (pile[adr1] != pile[adr2]) {
			return false;
		}
		T type = types[pile[adr1]];
		for(int i=0;i < type.valeurs.length;i++) {
			if (!comparer(pile[adr1+1+i],pile[adr2+1+i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public int taille(int adr) {
		T type = types[pile[adr]];
		int t =1+type.valeurs.length;
		for(int i=0;i < type.valeurs.length ;i++) {
			t+=taille(pile[adr+1+i]);
		}
		return t;
		
	}
	public void copier(int adr) {
		int taille = taille(adr);
		for(int i=0;i < taille;i++) {
			pile[sp]=pile[adr+i];
			sp++;
		}
		
	}


}
