package model;

public class ParamDef extends Def {
	public Ref type;
	public void visiter(VisiteurModule vm) {
		vm.visiter(this);
	}

}
