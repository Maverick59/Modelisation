import java.awt.Polygon;

public class Face {

	Point p1, p2, p3;

	public Face(Segment s1, Segment s2, Segment s3) {

		p1 = s1.p1;
		if (p1.equals(s2.p2)) {
			p2 = s2.p1;
		} else {
			p2 = s2.p2;
		}

		if (!(p1.equals(s3.p1) || p2.equals(s3.p1))) {
			p3 = s3.p1;
		} else if (!(p1.equals(s3.p2) || p2.equals(s3.p2))) {
			p3 = s3.p2;
		} else {
			p3 = s1.p2;
		}
	}

	public Face(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	public double centreX() {
		return (p1.x + p2.x + p3.x) / 3;
	}

	public double centreY() {
		return (p1.y + p2.y + p3.y) / 3;
	}

	public double centreZ() {
		return (p1.z + p2.z + p3.z) / 3;
	}

	public Polygon getTriangle(int decX, int decY) {

		int[] coordx = new int[] { (int) p1.x + decX, (int) p2.x + decX, (int) p3.x + decX };
		int[] coordy = new int[] { (int) p1.y + decY, (int) p2.y + decY, (int) p3.y + decY };

		return new Polygon(coordx, coordy, 3);
	}
}
