import java.util.ArrayList;

public class Coupe {
	public static ArrayList<Segment> tranche(Model3D m, int z) {

		ArrayList<Segment> segments = new ArrayList<Segment>();

		for (Face f : m.faces) {
			Segment s = intersectionTrinanglePlan(f, z);
			if (s != null)
				segments.add(s);
		}

		return segments;
	}

	private static Segment intersectionTrinanglePlan(Face f, double i) {
		Point a = intersectionSegmentPlan(f.p1, f.p2, i);
		Point b = intersectionSegmentPlan(f.p2, f.p3, i);
		Point c = intersectionSegmentPlan(f.p3, f.p1, i);

		if (b != null && c != null) {
			return new Segment(b, c);
		} else if (a != null && c != null) {
			return new Segment(a, c);
		} else if (a != null && b != null) {
			return new Segment(a, b);
		}
		return null;
	}

	private static Point intersectionSegmentPlan(Point a, Point b, double i) {
		Point ab = new Point(a.x - b.x, a.y - b.y, a.z - b.z);

		double z = i;
		double k = (z - b.z) / ab.z;

		if (k > 1 || k < 0)
			return null;

		double x = b.x + ab.x * k;
		double y = b.y + ab.y * k;

		return new Point(x, y, z);
	}
}
