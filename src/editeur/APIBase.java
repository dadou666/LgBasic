package editeur;

public class APIBase {
	static public abstract class base$boolean { }
	static public  class base$vrai extends base$boolean  { }
	static public  class base$faux extends base$boolean  { }
	public base$vrai vrai = new base$vrai();
	public base$faux faux = new base$faux();
	public int base$add(int a,int b) {
		return a+b;
		
		
	}
	public base$boolean base$sup(int a,int b) {
		if (a > b) {
			return vrai;
		}
		return faux;
	}
	public base$boolean base$inf(int a,int b) {
		if (a < b) {
			return vrai;
		}
		return faux;
	}
	public base$boolean base$egale(int a,int b) {
		if (a == b) {
			return vrai;
		}
		return faux;
	}
	

}
