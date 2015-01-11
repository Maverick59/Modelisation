package utilitaire;
import java.util.ArrayList;

import model.Face;
import model.Model3D;
import model.Point;
import model.Segment;

public class Calcul {
	/**
	 * recalcule le centre de gravite pour le remettre au milieu de chaque modele
	 * @param listModel la liste des modeles pour laquelle replacer le centre de gravite
	 */
	public static void recalulerCentreGravite(ArrayList<Model3D> listModel) {

		if (listModel.isEmpty())
			return;

		for (Model3D m : listModel) {
			for (Point p : m.getPoints()) {
				p.x += m.getDecalageX();
				p.y += m.getDecalageY();
			}
			m.setDecalageX(0);
			m.setDecalageY(0);
		}

		Point init = listModel.get(0).getPoints().get(0);
		double smlX = init.x, hgX = init.x, smlY = init.y, hgY = init.y, smlZ = init.z, hgZ = init.z;
		for (Model3D m : listModel) {
			for (int i = 0; i < m.getPoints().size(); i++) {
				if (m.getPoints().get(i).x < smlX) {
					smlX = m.getPoints().get(i).x;
				} else if (m.getPoints().get(i).x > hgX) {
					hgX = m.getPoints().get(i).x;
				} else if (m.getPoints().get(i).y < smlY) {
					smlY = m.getPoints().get(i).y;
				} else if (m.getPoints().get(i).y > hgY) {
					hgY = m.getPoints().get(i).y;
				} else if (m.getPoints().get(i).z > hgZ) {
					hgZ = m.getPoints().get(i).z;
				} else if (m.getPoints().get(i).z < smlZ) {
					smlZ = m.getPoints().get(i).z;
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
				for (int i = 0; i < m.getPoints().size(); i++) {
					m.getPoints().get(i).x = m.getPoints().get(i).x + ecartX;
					m.getPoints().get(i).y = m.getPoints().get(i).y + ecartY;
					m.getPoints().get(i).z = m.getPoints().get(i).z + ecartZ;
				}
				m.setDecalageX(-ecartX);
				m.setDecalageY(-ecartY);
			}
		}

	}
	
	/**
	 * recalcule et replace le centre de gravite au milieu du modele passe en parametre
	 * @param model le modele pour lequel modifier le centre de gravite
	 */
	public static void recalulerCentreGravite(Model3D model) {
		if (model.getPoints().isEmpty())
			return;

		Point init = model.getPoints().get(0);
		double smlX = init.x, hgX = init.x, smlY = init.y, hgY = init.y, smlZ = init.z, hgZ = init.z;
		for (int i = 0; i < model.getPoints().size(); i++) {
			if (model.getPoints().get(i).x < smlX) {
				smlX = model.getPoints().get(i).x;
			} else if (model.getPoints().get(i).x > hgX) {
				hgX = model.getPoints().get(i).x;
			} else if (model.getPoints().get(i).y < smlY) {
				smlY = model.getPoints().get(i).y;
			} else if (model.getPoints().get(i).y > hgY) {
				hgY = model.getPoints().get(i).y;
			} else if (model.getPoints().get(i).z > hgZ) {
				hgZ = model.getPoints().get(i).z;
			} else if (model.getPoints().get(i).z < smlZ) {
				smlZ = model.getPoints().get(i).z;
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
			for (int i = 0; i < model.getPoints().size(); i++) {
				model.getPoints().get(i).x = model.getPoints().get(i).x + ecartX;
				model.getPoints().get(i).y = model.getPoints().get(i).y + ecartY;
				model.getPoints().get(i).z = model.getPoints().get(i).z + ecartZ;
			}
		}
	}

	/**
	 * effectue un zoom sur le modele
	 * @param m le modele sur lequel zoomer
	 * @param d un nombre entre 0 et 1 qui defnit le coefficient du zoom
	 * @param w la largeur de la zone ou s'affiche le modele
	 * @param h la hauteur de la zone ou s'affiche le modele
	 */
	public static void zoom(Model3D m, double d, int w, int h) {
		if (h < 6 || w < 6)
			return;
		m.setDecalageX(w / 2);
		m.setDecalageY(h / 2);
		double smlX = w, hgX = 0, smlY = h, hgY = 0;
		for (int i = 0; i < m.getPoints().size(); i++) {
			if (m.getPoints().get(i).x < smlX) {
				smlX = m.getPoints().get(i).x;
			} else if (m.getPoints().get(i).x > hgX) {
				hgX = m.getPoints().get(i).x;
			} else if (m.getPoints().get(i).y < smlY) {
				smlY = m.getPoints().get(i).y;
			} else if (m.getPoints().get(i).y > hgY) {
				hgY = m.getPoints().get(i).y;
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

	/**
	 * effectue la copie exacte de modeles
	 * @param l la iste des modeles a copier
	 * @return la liste des copies effectuees
	 */
	public static ArrayList<Model3D> clone(ArrayList<Model3D> l) {
		ArrayList<Model3D> models = new ArrayList<Model3D>();
		Model3D model;
		for (Model3D m : l) {
			model = new Model3D();
			for (Face f : m.getFaces()) {
				model.getFaces().add(new Face(new Point(f.p1.x, f.p1.y, f.p1.z), new Point(f.p2.x, f.p2.y, f.p2.z), new Point(f.p3.x, f.p3.y, f.p3.z)));
			}
			for (Segment s : m.getSegments()) {
				model.getSegments().add(new Segment(new Point(s.p1.x, s.p1.y, s.p1.z), new Point(s.p2.x, s.p2.y, s.p2.z)));
			}
			for (Point p : m.getPoints()) {
				model.getPoints().add(new Point(p.x, p.y, p.z));
			}
			model.setDecalageX(m.getDecalageX());
			model.setDecalageY(m.getDecalageY());
			model.setNom(m.getNom());
			models.add(model);

		}

		return models;
	}

	/**
	 * renvoie le point du modele le plus proche sur l'axe z
	 * @param m le modele
	 * @return la valeur a laquelle se trouve le point le plus proche
	 */
	public static double minZ(Model3D m) {
		if(m.getPoints().isEmpty())
			return 0;
		
		double min = m.getPoints().get(0).z;
		for (Point p : m.getPoints()) {
			if (p.z < min) {
				min = p.z;
			}
		}
		return min;
	}

	/**
	 * renvoie le point du modele le plus lointain sur l'axe z
	 * @param m le modele
	 * @return la valeur a laquelle se trouve le point le plus lointain
	 */
	public static double maxZ(Model3D m) {
		if(m.getPoints().isEmpty())
			return 0;
		
		double max = m.getPoints().get(0).z;
		for (Point p : m.getPoints()) {
			if (p.z > max) {
				max = p.z;
			}
		}
		return max;
	}
	
	/**
	 * renvoie le point le plus proche sur l'axe des z parmis les differents modeles
	 * @param models la liste des modeles pour laqelle regarder les points
	 * @return la valeur ou se trouve le point le plus proche parmis tous les modeles
	 */
	public static double minZ(ArrayList<Model3D> models) {
		if(models.isEmpty() || models.get(0).getPoints().isEmpty())
			return 0;
		
		double min = models.get(0).getPoints().get(0).z;
		for(Model3D m :models){
			for (Point p : m.getPoints()) {
				if (p.z < min) {
					min = p.z;
				}
			}
		}
		return min;
	}
	
	/**
	 * renvoie le point le plus lointain sur l'axe des z parmis les differents modeles
	 * @param models la liste des modeles pour laqelle regarder les points
	 * @return la valeur ou se trouve le point le plus lointain parmis tous les modeles
	 */
	public static double maxZ(ArrayList<Model3D> models) {
		if(models.isEmpty() || models.get(0).getPoints().isEmpty())
			return 0;
		
		double max = models.get(0).getPoints().get(0).z;
		for(Model3D m :models){
			for (Point p : m.getPoints()) {
				if (p.z > max) {
					max = p.z;
				}
			}
		}
		return max;
	}

	/**
	 * normalise un point
	 * @param p le point a normaliser
	 */
	public static void normaliser(Point p) {
		double longueur = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
		p.x /= longueur;
		p.y /= longueur;
		p.z /= longueur;
	}

}
