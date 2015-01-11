package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import utilitaire.Calcul;
import utilitaire.GestionBDD;

public class Model3D implements Serializable {

	private ArrayList<Point> points;
	private ArrayList<Segment> segments;
	private ArrayList<Face> faces;

	private double decalageY = 0;
	private double decalageX = 0;
	private Color color;
	private String nom;

	public Model3D(ArrayList<Point> points, ArrayList<Segment> segments, ArrayList<Face> faces, String nom) {

		this.points = points;
		this.segments = segments;
		this.faces = faces;
		this.nom = nom;
		color = GestionBDD.getColor(this.toString());
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
		g.setColor(color);
		tri();
		Polygon p;
		for (Face f : faces) {
			p = f.getTriangle((int) decalageX, (int) decalageY);
			g.fillPolygon(p);
		}

	}

	public void afficherPoint(Graphics g) {
		g.setColor(color);
		for (Point p : points) {
			g.fillOval((int) (p.x + decalageX), (int) (p.y + decalageY), 2, 2);
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

		Point n = new Point(u.y * v.z - u.z * v.y, u.z * v.x - u.x * v.z, u.x * v.y - u.y * v.x);

		Calcul.normaliser(n);

		Point r = lumiere.get(0);
		Calcul.normaliser(r);

		double ps = n.x * r.x + n.y * r.y + n.z * r.z;

		double cos = Math.abs(ps);


		return new Color((int) (cos * color.getRed()), (int) (cos * color.getGreen()), (int) (cos * color.getBlue()));
	}

	public void tri() {
		synchronized (this) {
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

	}

	public void zoom(double i) {
		for (Point p : points) {
			p.zoom(i);
		}
	}

	public void pivoH(double i) {
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
		double tmpy;
		double tmpz;
		for (Point p : points) {
			tmpy = p.y;
			tmpz = p.z;

			p.y = Math.cos(i) * tmpy - Math.sin(i) * tmpz;
			p.z = Math.sin(i) * tmpy + Math.cos(i) * tmpz;

		}
	}

	public void pivoZ(double i) {
		double tmpx;
		double tmpy;
		for (Point p : points) {
			tmpx = p.x;
			tmpy = p.y;

			p.x = Math.cos(i) * tmpx - Math.sin(i) * tmpy;
			p.y = Math.sin(i) * tmpx + Math.cos(i) * tmpy;

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
		String s = "" + nom;
		s = s.replaceAll(".gts", "");
		s = s.replace('\\', '/');
		s = s.split("[/]")[s.split("[/]").length - 1];

		return s;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public Model3D clone() {

		Model3D clone = Charger.chargerModel(nom);
		for (int i = 0; i < points.size(); i++) {
			clone.points.get(i).set(points.get(i));
		}
		clone.decalageX = decalageX;
		clone.decalageY = decalageY;
		return clone;

	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	public ArrayList<Face> getFaces() {
		return faces;
	}

	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}
	
	
}
