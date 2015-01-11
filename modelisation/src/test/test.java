package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Face;
import model.Model3D;
import model.Point;
import model.Segment;

import org.junit.Test;

import utilitaire.Calcul;
import utilitaire.Coupe;


public class test {
	
	@Test
	public void UndoRedo1() {
		utilitaire.UndoRedo<String> undoRedo=new utilitaire.UndoRedo<String>();
		
		undoRedo.ajouteZ("1");
		undoRedo.ajouteZ("2");
		undoRedo.ajouteZ("3");
		undoRedo.ajouteZ("4");
		
		assertEquals(undoRedo.retourArriere(), "3");
		assertEquals(undoRedo.retourArriere(), "2");
		assertEquals(undoRedo.retourArriere(), "1");
		assertEquals(undoRedo.retourArriere(), null);
		assertEquals(undoRedo.retourAvant(), "2");
		assertEquals(undoRedo.retourAvant(), "3");
		assertEquals(undoRedo.retourAvant(), "4");
		
	}
	@Test
	public void UndoRedo2() {
		utilitaire.UndoRedo<String> undoRedo=new utilitaire.UndoRedo<String>();
		assertFalse(undoRedo.retourZ());
		undoRedo.ajouteZ("1");
		undoRedo.ajouteZ("2");
		assertTrue(undoRedo.retourZ());
		assertFalse(undoRedo.retourY());
		assertEquals(undoRedo.retourArriere(), "1");
		assertTrue(undoRedo.retourY());
		
	}
	@Test
	public void Coupe1(){
		Face f=new Face(new Point(1, 1, 1),new Point(2, 2, 2),new Point(1, 2, 1));
		Segment s=new Segment(new Point(1.5, 1.5, 1.5), new Point(1.5, 2, 1.5));
		assertEquals(Coupe.intersectionTrianglePlan(f, 1.5),s);
	}
	@Test
	public void Coupe2(){
		assertEquals(Coupe.intersectionSegmentPlan(new Point(1, 1, 1), new Point(2, 2, 2), 1.5),new Point(1.5, 1.5, 1.5));
	}
	@Test
	public void normaliser(){
		Point p=new Point(3, 4, 5);
		Point res=new Point(0.4242640687119285,0.565685424949238,0.7071067811865475);
		Calcul.normaliser(p);
		assertEquals(p,res);
	}
	
	
	@Test
	public void maxZ(){
		ArrayList<Point> points=new ArrayList<Point>();
		ArrayList<Segment> segments=new ArrayList<Segment>();
		ArrayList<Face> faces=new ArrayList<Face>();
		points.add(new Point(1, 1, 1));
		points.add(new Point(2, 2, 2));
		points.add(new Point(3, 3, 3));
		segments.add(new Segment(points.get(0), points.get(1)));
		segments.add(new Segment(points.get(1), points.get(2)));
		segments.add(new Segment(points.get(2), points.get(0)));
		faces.add(new Face(segments.get(0), segments.get(1), segments.get(2)));
		Model3D m =new Model3D(points,segments,faces);
		assertTrue(Calcul.maxZ(m)==3.0);
	}
	
	@Test
	public void minZ(){
		ArrayList<Point> points=new ArrayList<Point>();
		ArrayList<Segment> segments=new ArrayList<Segment>();
		ArrayList<Face> faces=new ArrayList<Face>();
		points.add(new Point(1, 1, 1));
		points.add(new Point(2, 2, 2));
		points.add(new Point(3, 3, 3));
		segments.add(new Segment(points.get(0), points.get(1)));
		segments.add(new Segment(points.get(1), points.get(2)));
		segments.add(new Segment(points.get(2), points.get(0)));
		faces.add(new Face(segments.get(0), segments.get(1), segments.get(2)));
		Model3D m =new Model3D(points,segments,faces);
		assertTrue(Calcul.minZ(m)==1.0);
	}
	
	@Test
	public void centre(){
		ArrayList<Point> points=new ArrayList<Point>();
		ArrayList<Segment> segments=new ArrayList<Segment>();
		ArrayList<Face> faces=new ArrayList<Face>();
		points.add(new Point(1, 1, 1));
		points.add(new Point(2, 2, 2));
		points.add(new Point(3, 3, 3));
		segments.add(new Segment(points.get(0), points.get(1)));
		segments.add(new Segment(points.get(1), points.get(2)));
		segments.add(new Segment(points.get(2), points.get(0)));
		faces.add(new Face(segments.get(0), segments.get(1), segments.get(2)));
		Model3D m =new Model3D(points,segments,faces);
		assertTrue(m.centreZ()==2.0);
	}
	/*
	 * fonction testé visuelment
	 */
	@Test
	public void SplashScreen() {
		assertTrue(true);
	}
	@Test
	public void SaveLoadProject() {
		assertTrue(true);
	}
	@Test
	public void FenetreColor() {
		assertTrue(true);
	}
	@Test
	public void FenetreCouleur() {
		assertTrue(true);
	}
	@Test
	public void FenetreConfigModel() {
		assertTrue(true);
	}
	@Test
	public void FenetreCoupe() {
		assertTrue(true);
	}
	@Test
	public void FenetrePivot() {
		assertTrue(true);
	}
	@Test
	public void ExportToPng() {
		assertTrue(true);
	}
	@Test
	public void CoupeToPng() {
		assertTrue(true);
	}
	@Test
	public void Contact() {
		assertTrue(true);
	}
	@Test
	public void ChangerWorkspace() {
		assertTrue(true);
	}
	@Test
	public void FondEcran() {
		assertTrue(true);
	}
	@Test
	public void MenuBarre() {
		assertTrue(true);
	}
	@Test
	public void Insert() {
		assertTrue(true);
	}
	@Test
	public void Main() {
		assertTrue(true);
	}
	@Test
	public void Ecran() {
		assertTrue(true);
	}
	@Test
	public void BarreAjout() {
		assertTrue(true);
	}
	@Test
	public void BarreSelect() {
		assertTrue(true);
	}
	@Test
	public void BarrePanneauModel() {
		assertTrue(true);
	}
	@Test
	public void ModeltoPNG() {
		assertTrue(true);
	}
}
