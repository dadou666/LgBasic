package execution;

public class Appel extends Code {
	public int idx;
	public Code[] params;

	@Override
	public int executer(M machine, int paramIdx) {
		int newParamIdx = machine.sp;

		for (int i = 0; i < params.length; i++) {
			int idx = this.params[i].executer(machine, paramIdx);
			machine.pile[machine.sp] = idx;
			machine.sp++;

		}

		int r= machine.fonctions[idx].executer(machine, newParamIdx);
		machine.sp = newParamIdx;
		machine.copier(r);
		return newParamIdx;
	}

}
