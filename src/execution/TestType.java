package execution;

public class TestType extends Test {
	public int type;
	public Code objet;
	@Override
	public int executer(M machine,int paramIdx) {
		int o = objet.executer(machine, paramIdx);
		T to = machine.types[machine.pile[o]];
		T tt = machine.types[type];
		if (to.debut>= tt.debut && to.fin <=tt.fin) {
			return alors.executer(machine, paramIdx);
		}
		return sinon.executer(machine, paramIdx);
	}


}
