package editeur;

import java.util.Map;

public interface Executeur {
	public Class classAPI();
	public Map<Class,String>  typeReserve();
	public void executer(Terminal terminal);
	public boolean test(Terminal terminal);

}
