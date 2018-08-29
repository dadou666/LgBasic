package semantique;

import java.util.List;

import model.Literal;
import model.Ref;

public class CreationTypeReserve extends Erreur {
	public List<Ref> refs;
	public int idx;
	public String type;
}
