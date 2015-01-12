package model;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Charger {
	
	/**
	 * charge les donnees d'un fichier
	 * @param fichier le nom du fichier
	 * @return un Model3D
	 */
	public static Model3D chargerModel(String fichier) {
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader in = new BufferedReader(ipsr);
			String ligne = in.readLine();
			System.out.println(fichier);
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
			ipsr.close();
			ips.close();
			in.close();
			return new Model3D(points, segments, faces, fichier);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	/**
	 * renvoye le nb de points/segments/faces du gts
	 * @param gts le lien du fichier
	 * @return tableau de int avec {points,segment,faces}
	 * @throws IOException
	 */
	public static int[] chargerModelHeader(String gts) throws IOException {
		InputStream ips = new FileInputStream(gts);
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
		return new int[] { nbpoint, nbsegment, nbface };
	}
}
