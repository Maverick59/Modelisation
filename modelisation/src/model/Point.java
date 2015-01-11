package model;
import java.io.Serializable;

public class Point implements Serializable {

	public double x;
	public double y;
	public double z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * multiplie par i le x/y/z
	 * @param i
	 */
	public void zoom(double i) {
		this.x = x * i;
		this.y = y * i;
		this.z = z * i;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public void set(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		
	}

}
