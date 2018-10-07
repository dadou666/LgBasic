package test;

public class API {
	static public class tot {}
	
	public int f() {
		return 0;
	}
	public int f$() {
		return 0;
	}
	public int $f() {
		return 0;
	}
	public momo$ko f(String x) {
		return null;
	}
	public int lili$f(String x) {
		return 0;
	}
	public int lili$f(momo$ko x) {
		return 0;
	}
	static public abstract class momo$ko {}
	static public class toto$m {
		public toto$m() {
		}
	}
	static public class lili$tot extends momo$ko {
		public toto$m a;
	}
	public toto$m toto$t() {
		return new toto$m();
	}
	public  lili$tot toto$f(toto$m l) {
		return new lili$tot();
	}
	
	public int toto$m(String s) {
		return 0;
	}
}
