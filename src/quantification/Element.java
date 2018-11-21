package quantification;

import java.util.ArrayList;
import java.util.List;

import model.Expression;
import model.Var;

public class Element {
	public List<Var> params= new ArrayList<Var>();
	public Expression expression;
	public List<Element> enfants = new ArrayList<>();
	

}
