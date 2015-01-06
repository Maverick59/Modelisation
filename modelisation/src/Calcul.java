import java.util.ArrayList;

public class Calcul {

	public static void recalulerCentreGravite(ArrayList<Model3D> listModel) {

		if (listModel.isEmpty())
			return;

		for (Model3D m : listModel) {
			for (Point p : m.points) {
				p.x += m.getDecalageX();
				p.y += m.getDecalageY();
			}
			m.setDecalageX(0);
			m.setDecalageY(0);
		}

		Point init = listModel.get(0).points.get(0);
		double smlX = init.x, hgX = init.x, smlY = init.y, hgY = init.y, smlZ = init.z, hgZ = init.z;
		for (Model3D m : listModel) {
			for (int i = 0; i < m.points.size(); i++) {
				if (m.points.get(i).x < smlX) {
					smlX = m.points.get(i).x;
				} else if (m.points.get(i).x > hgX) {
					hgX = m.points.get(i).x;
				} else if (m.points.get(i).y < smlY) {
					smlY = m.points.get(i).y;
				} else if (m.points.get(i).y > hgY) {
					hgY = m.points.get(i).y;
				} else if (m.points.get(i).z > hgZ) {
					hgZ = m.points.get(i).z;
				} else if (m.points.get(i).z < smlZ) {
					smlZ = m.points.get(i).z;
				}
			}
		}
		double baryX, baryY, baryZ;
		baryX = (hgX + smlX) / 2;
		baryY = (hgY + smlY) / 2;
		baryZ = (hgZ + smlZ) / 2;
		double miniX, miniY, maxX, maxY, miniZ, maxZ;
		miniX = smlX - baryX;
		miniY = smlY - baryY;
		miniZ = smlZ - baryZ;
		maxX = hgX - baryX;
		maxY = hgY - baryY;
		maxZ = hgZ - baryZ;
		if (smlX != miniX || hgX != maxX || smlY != miniY || hgY != maxY) {
			double ecartX, ecartY, ecartZ;
			ecartX = miniX - smlX;
			ecartY = miniY - smlY;
			ecartZ = miniZ - smlZ;
			for (Model3D m : listModel) {
				for (int i = 0; i < m.points.size(); i++) {
					m.points.get(i).x = m.points.get(i).x + ecartX;
					m.points.get(i).y = m.points.get(i).y + ecartY;
					m.points.get(i).z = m.points.get(i).z + ecartZ;
				}
				m.setDecalageX(-ecartX);
				m.setDecalageY(-ecartY);
			}
		}

	}

	public static void recalulerCentreGravite(Model3D model) {
		if (model.points.isEmpty())
			return;

		Point init = model.points.get(0);
		double smlX = init.x, hgX = init.x, smlY = init.y, hgY = init.y, smlZ = init.z, hgZ = init.z;
		for (int i = 0; i < model.points.size(); i++) {
			if (model.points.get(i).x < smlX) {
				smlX = model.points.get(i).x;
			} else if (model.points.get(i).x > hgX) {
				hgX = model.points.get(i).x;
			} else if (model.points.get(i).y < smlY) {
				smlY = model.points.get(i).y;
			} else if (model.points.get(i).y > hgY) {
				hgY = model.points.get(i).y;
			} else if (model.points.get(i).z > hgZ) {
				hgZ = model.points.get(i).z;
			} else if (model.points.get(i).z < smlZ) {
				smlZ = model.points.get(i).z;
			}
		}

		double baryX, baryY, baryZ;
		baryX = (hgX + smlX) / 2;
		baryY = (hgY + smlY) / 2;
		baryZ = (hgZ + smlZ) / 2;
		double miniX, miniY, maxX, maxY, miniZ, maxZ;
		miniX = smlX - baryX;
		miniY = smlY - baryY;
		miniZ = smlZ - baryZ;
		maxX = hgX - baryX;
		maxY = hgY - baryY;
		maxZ = hgZ - baryZ;
		if (smlX != miniX || hgX != maxX || smlY != miniY || hgY != maxY) {
			double ecartX, ecartY, ecartZ;
			ecartX = miniX - smlX;
			ecartY = miniY - smlY;
			ecartZ = miniZ - smlZ;
			for (int i = 0; i < model.points.size(); i++) {
				model.points.get(i).x = model.points.get(i).x + ecartX;
				model.points.get(i).y = model.points.get(i).y + ecartY;
				model.points.get(i).z = model.points.get(i).z + ecartZ;
			}
		}
	}

	public static void zoom(Model3D m, double d, int w, int h) {
		if (h < 6 || w < 6)
			return;
		m.setDecalageX(w / 2);
		m.setDecalageY(h / 2);
		double smlX = w, hgX = 0, smlY = h, hgY = 0;
		for (int i = 0; i < m.points.size(); i++) {
			if (m.points.get(i).x < smlX) {
				smlX = m.points.get(i).x;
			} else if (m.points.get(i).x > hgX) {
				hgX = m.points.get(i).x;
			} else if (m.points.get(i).y < smlY) {
				smlY = m.points.get(i).y;
			} else if (m.points.get(i).y > hgY) {
				hgY = m.points.get(i).y;
			}
		}
		double sizeX, sizeY;
		sizeX = hgX - smlX;
		sizeY = hgY - smlY;
		if (sizeX > sizeY) {
			m.zoom((w / sizeX) * d);
		} else {
			m.zoom((h / sizeY) * d);
		}
	}

	public static ArrayList<Model3D> clone(ArrayList<Model3D> l) {
		ArrayList<Model3D> models = new ArrayList<Model3D>();
		Model3D model;
		for (Model3D m : l) {
			model = new Model3D();
			for (Face f : m.faces) {
				model.faces.add(new Face(new Point(f.p1.x, f.p1.y, f.p1.z), new Point(f.p2.x, f.p2.y, f.p2.z), new Point(f.p3.x, f.p3.y, f.p3.z)));
			}
			for (Segment s : m.segments) {
				model.segments.add(new Segment(new Point(s.p1.x, s.p1.y, s.p1.z), new Point(s.p2.x, s.p2.y, s.p2.z)));
			}
			for (Point p : m.points) {
				model.points.add(new Point(p.x, p.y, p.z));
			}
			model.setDecalageX(m.getDecalageX());
			model.setDecalageY(m.getDecalageY());
			model.setNom(m.getNom());
			models.add(model);

		}

		return models;
	}

	public static double minZ(Model3D m) {
		double min = m.points.get(0).z;
		for (Point p : m.points) {
			if (p.z < min) {
				min = p.z;
			}
		}
		return min;
	}

	public static double maxZ(Model3D m) {
		double max = m.points.get(0).z;
		for (Point p : m.points) {
			if (p.z > max) {
				max = p.z;
			}
		}
		return max;
	}

	public static void normaliser(Point p) {
		double longueur = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
		p.x /= longueur;
		p.y /= longueur;
		p.z /= longueur;
	}

}
