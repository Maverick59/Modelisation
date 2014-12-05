import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Charger {

	public static Model3D chargerModel(String fichier) {
		try {
			System.out.println(fichier);
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader in = new BufferedReader(ipsr);
			String ligne = in.readLine();
			int nbpoint = 0;
			int nbsegment = 0;
			int nbface = 0;

			if (ligne != null) {
				nbpoint = Integer.parseInt(ligne.split(" ")[0]);
				nbsegment = Integer.parseInt(ligne.split(" ")[1]);
				nbface = Integer.parseInt(ligne.split(" ")[2]);
			}

			ArrayList<Point> points = new ArrayList<Point>();
			ArrayList<Segment> segments = new ArrayList<Segment>();
			ArrayList<Face> faces = new ArrayList<Face>();

			for (int i = 0; i < nbpoint; i++) {
				ligne = in.readLine();
				points.add(new Point((Double.parseDouble(ligne.split(" ")[0])), (Double.parseDouble(ligne.split(" ")[1])), (Double.parseDouble(ligne.split(" ")[2]))));
			}
			for (int i = 0; i < nbsegment; i++) {
				ligne = in.readLine();
				segments.add(new Segment(points.get(Integer.parseInt(ligne.split(" ")[0]) - 1), points.get(Integer.parseInt(ligne.split(" ")[1]) - 1)));
			}
			for (int i = 0; i < nbface; i++) {
				ligne = in.readLine();
				faces.add(new Face(segments.get(Integer.parseInt(ligne.split(" ")[0]) - 1), segments.get(Integer.parseInt(ligne.split(" ")[1]) - 1),
						segments.get(Integer.parseInt(ligne.split(" ")[2]) - 1)));
			}
			fichier = fichier.replaceAll(".gts", "");
			fichier = fichier.split("[/]")[fichier.split("[/]").length - 1];
			return new Model3D(points, segments, faces, fichier);
		} catch (Exception e) {
			System.out.println("bug");
			e.printStackTrace();
		}
		return null;
	}
}
