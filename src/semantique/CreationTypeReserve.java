package semantique;

import java.util.List;

import model.Literal;
import model.Ref;
import model.RefLiteral;

public class CreationTypeReserve extends Erreur {
	public List<RefLiteral> refs;
	public int idx;
	public String type;
}
