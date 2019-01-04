package monde;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import reader.Factory;
import reader.ReadAttributes;

@ReadAttributes(names= {"nx","ny","x1","y1","x1","y2","ressources"})
public class Sauvegarde {
	public int nx;
	public int ny;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public RessourceSauvegardeVide ressources;
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(Sauvegarde.class.getName());
		sb.append(" ");
		List<Point> points = new ArrayList<Point>();
		int nx=40;
		int ny=20;
		sb.append(nx);
		sb.append(" ");
		sb.append(ny);
		sb.append(" ");
		for (int ix = 0; ix <nx - 1; ix++) {

			for (int iy = 0; iy < ny - 1; iy++) {
				points.add(new Point(ix , iy ));

			}
		}
		Random random = new Random();
		Point p = points.get(random.nextInt(points.size() - 1));
		points.remove(p);
		sb.append(p.x);
		sb.append(" ");
		sb.append(p.y);
		sb.append(" ");
		
		p = points.get(random.nextInt(points.size() - 1));
		points.remove(p);
		sb.append(p.x);
		sb.append(" ");
		sb.append(p.y);
		sb.append(" ");
		List<Class<? extends Ressource>> liste = new ArrayList<>();
		liste.add(Vie.class);
		liste.add(Reproduction.class);
		liste.add(Vitesse.class);
		liste.add(VitesseTire.class);
		liste.add(Puissance.class);
		liste.add(Porte.class);

		int size = points.size() / 4;

		while (points.size() > size) {
			 p = points.get(random.nextInt(points.size() - 1));
			Class<? extends Ressource> rc = liste.get(random.nextInt(liste.size() - 1));
			
			sb.append(RessourceSauvegarde.class.getName());
			sb.append(" ");
			sb.append(p.x);
			sb.append(" ");
			sb.append(p.y);
			sb.append(" ");
			sb.append(rc.getName());
			sb.append("\n");
			
			points.remove(p);

		}
		sb.append(RessourceSauvegardeVide.class.getName());
		Factory factory = new Factory(sb.toString());
		Object obj=factory.toObject();
		System.out.println(sb);
		Files.write(Paths.get("F://GitHub//LgBasic//src//monde", "sauvegarde.txt"), sb.toString().getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
		
		
	}
}
