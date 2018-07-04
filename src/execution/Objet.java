package execution;

public class Objet extends Code {
	int type;
	Code valeurs[];
	@Override
	public int executer(M machine, int paramIdx) {
		
		int objetIdx = machine.sp;
		machine.pile[objetIdx] = type;
		machine.sp+= valeurs.length;
		
		for(int i=0;i< valeurs.length;i++) {
			machine.pile[objetIdx+i]=valeurs[i].executer(machine, paramIdx);
		}
		return objetIdx;
	}

	

}
