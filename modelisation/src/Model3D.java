import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Model3D {

	ArrayList<Point> points;
	ArrayList<Segment> segments;
	ArrayList<Face> faces;
	private double decalageY = 0;
	private double decalageX = 0;
	private double rotationH = 0;
	private double rotationV = 0;
	private double zoom = 1;
	private Color color;
	private String nom;

	public Model3D(ArrayList<Point> points, ArrayList<Segment> segments, ArrayList<Face> faces, String nom) {
		System.out.println(nom);
		color = GestionBDD.getColor(nom);
		this.points = points;
		this.segments = segments;
		this.faces = faces;
		this.nom = nom;
		Calcul.recalulerCentreGravite(this);
	}

	public Model3D() {
		this.points = new ArrayList<Point>();
		this.segments = new ArrayList<Segment>();
		this.faces = new ArrayList<Face>();
	}

	public void afficherSegments(Graphics g) {
		g.setColor(color);
		for (Segment s : segments) {
			g.drawLine((int) (s.p1.x + decalageX), (int) (s.p1.y + decalageY), (int) (s.p2.x + decalageX), (int) (s.p2.y + decalageY));
		}
	}

	public void afficherSegments(Graphics g, ArrayList<Segment> seg) {
		g.setColor(Color.BLACK);
		for (Segment s : seg) {
			g.drawLine((int) (s.p1.x + decalageX), (int) (s.p1.y + decalageY), (int) (s.p2.x + decalageX), (int) (s.p2.y + decalageY));
		}
	}

	public void afficherFaces(Graphics g) {
		tri();
		Polygon p;
		for (Face f : faces) {
			p = f.getTriangle((int) decalageX, (int) decalageY);
			g.fillPolygon(p);
		}

	}

	public void afficher(Graphics g, ArrayList<Point> lumiere) {
		g.setColor(color);
		tri();
		Polygon p;
		for (Face f : faces) {

			p = f.getTriangle((int) decalageX, (int) decalageY);

			g.setColor(eclairage(lumiere, f));

			g.fillPolygon(p);
		}
	}

	private Color eclairage(ArrayList<Point> lumiere, Face f) {
		Point u = new Point(f.p1.x - f.p2.x, f.p1.y - f.p2.y, f.p1.z - f.p2.z);
		Point v = new Point(f.p1.x - f.p3.x, f.p1.y - f.p3.y, f.p1.z - f.p3.z);

		double x = u.y * v.z - u.z * v.y;
		double y = u.z * v.x - u.x * v.z;
		double z = u.x * v.y - u.y * v.x;
		double longueur = Math.sqrt(x * x + y * y + z * z);
		x /= longueur;
		y /= longueur;
		z /= longueur;
		Point r = lumiere.get(0);

		double ps = x * r.x + y * r.y + z * r.z;

		double cos = Math.abs(ps);
		return new Color((int) (cos * color.getRed()), (int) (cos * color.getGreen()), (int) (cos * color.getBlue()));
	}

	public void tri() {
		try {
			Collections.sort(faces, new Comparator<Face>() {
				@Override
				public int compare(Face f1, Face f2) {
					if (f1.centreZ() > f2.centreZ()) {
						return 1;
					} else if (f1.centreZ() == f2.centreZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void zoom(double i) {
		zoom *= i;
		for (Point p : points) {
			p.zoom(i);
		}
	}

	public void pivoH(double i) {
		this.setRotationH(this.getRotationH() + i);
		double tmpx;
		double tmpz;
		for (Point p : points) {
			tmpx = p.x;
			tmpz = p.z;

			p.x = Math.cos(i) * tmpx - Math.sin(i) * tmpz;
			p.z = Math.sin(i) * tmpx + Math.cos(i) * tmpz;

		}

	}

	public void pivoV(double i) {
		this.setRotationV(this.getRotationV() + i);
		double tmpy;
		double tmpz;
		for (Point p : points) {
			tmpy = p.y;
			tmpz = p.z;

			p.y = Math.cos(i) * tmpy - Math.sin(i) * tmpz;
			p.z = Math.sin(i) * tmpy + Math.cos(i) * tmpz;

		}
	}

	public void deplacementV(int i) {
		decalageY -= i;
	}

	public void deplacementH(int i) {
		decalageX -= i;
	}

	public void setColor(Color c) {
		color = c;

	}

	@Override
	public String toString() {
		return nom;
	}

	public void setDecalageX(double i) {
		this.decalageX = i;
	}

	public void setDecalageY(double i) {
		this.decalageY = i;
	}

	public double getDecalageX() {
		// TODO Auto-generated method stub
		return decalageX;
	}

	public double getDecalageY() {
		// TODO Auto-generated method stub
		return decalageY;
	}

	public double centreZ() {
		double d = 0;
		for (Face f : faces) {
			d += f.centreZ();
		}

		return d / faces.size();
	}

	public double maxZ() {
		double max = faces.get(0).centreZ();
		for (Face f : faces) {
			if (max < faces.get(0).centreZ()) {
				max = faces.get(0).centreZ();
			}
		}
		return max;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getRotationH() {
		return rotationH;
	}

	public void setRotationH(double rotationH) {
		this.rotationH = rotationH % (Math.PI * 2);
		if (this.rotationH < 0) {
			this.rotationH += (Math.PI * 2);
		}
	}

	public double getRotationV() {
		return rotationV;
	}

	public void setRotationV(double rotationV) {
		this.rotationV = rotationV % (Math.PI * 2);
		if (this.rotationV < 0) {
			this.rotationV += (Math.PI * 2);
		}
	}

	public double getZoom() {
		return zoom;
	}
}
