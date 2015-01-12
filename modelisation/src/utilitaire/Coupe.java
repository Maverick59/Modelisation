package utilitaire;
import java.util.ArrayList;

import model.Face;
import model.Model3D;
import model.Point;
import model.Segment;

public class Coupe {
	
	/**
	 * renvoie la tranche du modele au point z
	 * @param m le modele duquel obtenir la tranche
	 * @param z le point sur l'axe z auquel prendre la tranche
	 * @return la liste des segments qui forment la tranche du modele au point z
	 */
	public static ArrayList<Segment> tranche(Model3D m, int z) {

		ArrayList<Segment> segments = new ArrayList<Segment>();

		for (Face f : m.getFaces()) {
			Segment s = intersectionTrianglePlan(f, z);
			if (s != null)
				segments.add(s);
		}

		return segments;
	}

	/**
	 * renvoie l'endroit ou est coupe le triangle
	 * @param f la face que l'on coupe
	 * @param z l'endroit ou on coupe sur l'axe z
	 * @return le segment ou le plan coupe le triangle
	 */
	public static Segment intersectionTrianglePlan(Face f, double z) {
		Point a = intersectionSegmentPlan(f.p1, f.p2, z);
		Point b = intersectionSegmentPlan(f.p2, f.p3, z);
		Point c = intersectionSegmentPlan(f.p3, f.p1, z);

		if (b != null && c != null) {
			return new Segment(b, c);
		} else if (a != null && c != null) {
			return new Segment(a, c);
		} else if (a != null && b != null) {
			return new Segment(a, b);
		}
		return null;
	}

	/**
	 * renvoie l'endroit ou est coupe le Segment
	 * @param a premier point du segment
	 * @param b deuxieme point du segment
	 * @param z l'endroit ou on coupe sur l'axe z
	 * @return le point ou le plan coupe le segment
	 */
	public static Point intersectionSegmentPlan(Point a, Point b, double z) {
		Point ab = new Point(a.x - b.x, a.y - b.y, a.z - b.z);

		double k = (z - b.z) / ab.z;

		if (k > 1 || k < 0)
			return null;

		double x = b.x + ab.x * k;
		double y = b.y + ab.y * k;

		return new Point(x, y, z);
	}

}
