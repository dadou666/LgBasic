package execution;

public class Param extends Code {
	public int idx;

	@Override
	public int executer(M machine,int paramIdx) {
		// TODO Auto-generated method stub
		return machine.pile[paramIdx+idx];
	}



}
