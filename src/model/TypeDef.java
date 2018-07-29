package model;

import java.util.List;

public class TypeDef extends Def {
	public final static String Symbol="symbol";
	public boolean estAbstrait;
	
	public Ref superType;
	public List<Var> vars;

}
