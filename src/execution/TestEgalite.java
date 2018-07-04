package execution;

public class TestEgalite extends Test {
		public Code a;
		public Code b;
		@Override
		public int executer(M machine, int paramIdx) {
			int ao = this.executer(machine, paramIdx);
			int bo = this.executer(machine, paramIdx);
			if (machine.comparer(ao, bo)) {
				return alors.executer(machine, paramIdx);
			}
			return sinon.executer(machine, paramIdx);
		}

	
}
