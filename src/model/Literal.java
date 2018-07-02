package model;

import java.util.ArrayList;
import java.util.List;

public class Literal extends Expression {
	public List<Ref> mots= new ArrayList<Ref>();

	@Override
	public void visiter(Visiteur visiteur) {
	  visiteur.visiter(this);
	  
		
	}

}
