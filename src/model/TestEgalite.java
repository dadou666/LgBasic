package model;

public class TestEgalite extends Test {
	public Expression a;
	public Expression b;
	@Override
	public void visiter(Visiteur visiteur) {
		visiteur.visiter(this);
		
	}


}
