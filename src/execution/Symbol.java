package execution;

public class Symbol extends Code {
    public int adr;
	@Override
	public int executer(M machine, int paramIdx) {
		int objetIdx = machine.sp;
		machine.pile[objetIdx] = adr;
		return objetIdx;
	}

}
