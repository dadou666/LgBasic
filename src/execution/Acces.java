package execution;

public class Acces extends Code {
	public Code objet;
	public int idx;
	@Override
	public int executer(M machine, int paramIdx) {
		
		int adr =  objet.executer(machine, paramIdx) ;
		 return machine.pile[adr+1+idx];
		
		
	}

}
