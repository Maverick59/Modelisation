import java.util.ArrayList;

public class Calcul {

	/*
	 * public static void recalulerCentreGravite(ArrayList<Point> points) {
	 * 
	 * if (points.isEmpty()) return;
	 * 
	 * double smlX = points.get(0).x, hgX = points.get(0).x, smlY = points
	 * .get(0).y, hgY = points.get(0).y, smlZ = points.get(0).z, hgZ = points
	 * .get(0).z; for (int i = 0; i < points.size(); i++) { if (points.get(i).x
	 * < smlX) { smlX = points.get(i).x; } else if (points.get(i).x > hgX) { hgX
	 * = points.get(i).x; } else if (points.get(i).y < smlY) { smlY =
	 * points.get(i).y; } else if (points.get(i).y > hgY) { hgY =
	 * points.get(i).y; } else if (points.get(i).z > hgZ) { hgZ =
	 * points.get(i).z; } else if (points.get(i).z < smlZ) { smlZ =
	 * points.get(i).z; } } double baryX, baryY, baryZ; baryX = (hgX + smlX) /
	 * 2; baryY = (hgY + smlY) / 2; baryZ = (hgZ + smlZ) / 2; double miniX,
	 * miniY, maxX, maxY, miniZ, maxZ; miniX = smlX - baryX; miniY = smlY -
	 * baryY; miniZ = smlZ - baryZ; maxX = hgX - baryX; maxY = hgY - baryY; maxZ
	 * = hgZ - baryZ; if (smlX != miniX || hgX != maxX || smlY != miniY || hgY
	 * != maxY) { double ecartX, ecartY, ecartZ; ecartX = miniX - smlX; ecartY =
	 * miniY - smlY; ecartZ = miniZ - smlZ; for (int i = 0; i < points.size();
	 * i++) { points.get(i).x = points.get(i).x + ecartX; points.get(i).y =
	 * points.get(i).y + ecartY; } }
	 * 
	 * }
	 */

	public static void recalulerCentreGravite(ArrayList<Model3D> listModel) {

		if (listModel.isEmpty())
			return;

		for (Model3D m : listModel) {
			for (Point p : m.points) {
				p.x += m.decalageX;
				p.y += m.decalageY;
			}
			m.decalageX = 0;
			m.decalageY = 0;
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
				m.decalageX -= ecartX;
				m.decalageY -= ecartY;
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

}
