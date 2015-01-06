import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class Charger {

	private static BufferedReader in;

	public static Model3D chargerModel(String fichier) {
		try {
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

			Vector<Point> points = new Vector<Point>();
			Vector<Segment> segments = new Vector<Segment>();
			Vector<Face> faces = new Vector<Face>();

			for (int i = 0; i < nbpoint; i++) {
				ligne = in.readLine();
				points.add(new Point((Double.parseDouble(ligne.split(" ")[0])), (Double.parseDouble(ligne.split(" ")[1])),
						(Double.parseDouble(ligne.split(" ")[2]))));
			}
			for (int i = 0; i < nbsegment; i++) {
				ligne = in.readLine();
				segments.add(new Segment(points.get(Integer.parseInt(ligne.split(" ")[0]) - 1), points.get(Integer.parseInt(ligne.split(" ")[1]) - 1)));
			}
			for (int i = 0; i < nbface; i++) {
				ligne = in.readLine();
				faces.add(new Face(segments.get(Integer.parseInt(ligne.split(" ")[0]) - 1), segments.get(Integer.parseInt(ligne.split(" ")[1]) - 1), segments
						.get(Integer.parseInt(ligne.split(" ")[2]) - 1)));
			}
			ipsr.close();
			ips.close();
			return new Model3D(points, segments, faces, fichier);
		} catch (Exception e) {
			System.out.println("bug");
			e.printStackTrace();
		}
		return null;
	}

	public static int[] chargerModelHeader(String gts) throws IOException {
		InputStream ips = new FileInputStream(gts);
		InputStreamReader ipsr = new InputStreamReader(ips);
		in = new BufferedReader(ipsr);
		String ligne = in.readLine();
		int nbpoint = 0;
		int nbsegment = 0;
		int nbface = 0;

		if (ligne != null) {
			nbpoint = Integer.parseInt(ligne.split(" ")[0]);
			nbsegment = Integer.parseInt(ligne.split(" ")[1]);
			nbface = Integer.parseInt(ligne.split(" ")[2]);
		}
		return new int[] { nbpoint, nbsegment, nbface };
	}
}
