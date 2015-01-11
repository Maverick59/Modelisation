package model;
import java.io.Serializable;

public class Segment implements Serializable {

	public Point p1;
	public Point p2;

	public Segment(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

}
