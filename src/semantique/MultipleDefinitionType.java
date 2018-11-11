package semantique;

import java.util.List;
import java.util.Set;

import model.Def;
import model.Ref;
import model.Var;

public class MultipleDefinitionType extends Erreur {
	public Ref ref;
	public String nom;
	public List<String> types;
	public Class<? extends Def> classDef;
	

}
