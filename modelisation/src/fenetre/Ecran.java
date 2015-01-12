package fenetre;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import barre.BarreAjout;
import barre.BarreSelect;
import listener.UserListener;
import model.Model3D;
import model.Point;
import utilitaire.Calcul;
import utilitaire.Coupe;
import utilitaire.Parametre;

@SuppressWarnings("serial")
public class Ecran extends JPanel {

	private final Main main;
	private int affichage;
	private Image fond;

	private int CoupeEnZ = -100;

	private ArrayList<Model3D> models = new ArrayList<Model3D>();
	private final ArrayList<Point> lumiere = new ArrayList<Point>();

	private BarreAjout barreAjout;
	private BarreSelect barreSelect;
	private final UserListener userListener;
	/**
	 * initialise la frame
	 * @param f
	 */
	public Ecran(Main f) {

		this.setPreferredSize(new Dimension(f.getWidth(), f.getHeight()));

		this.main = f;

		userListener = new UserListener(this);
		this.addMouseWheelListener(userListener);
		this.addMouseMotionListener(userListener);
		this.addMouseListener(userListener);
		main.addKeyListener(userListener);

		init();
	}
	/**
	 * initialise la frame
	 */
	private void init() {
		barreAjout = new BarreAjout(this);
		barreSelect = new BarreSelect(this);

		affichage = 2;
		lumiere.add(new Point(0, 0, 1));
		fond = new ImageIcon(Parametre.workspace + "/wallpaper/fondDeBase.jpg").getImage();

	}
	/**
	 * tri les figures
	 */
	public void trifigure() {
		try {
			Collections.sort(models, new Comparator<Model3D>() {
				@Override
				public int compare(Model3D m1, Model3D m2) {
					if (Calcul.maxZ(m1) > Calcul.maxZ(m2)) {
						return 1;
					} else if (Calcul.maxZ(m1) == Calcul.maxZ(m2)) {
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
	/**
	 * paint sur l'ecran
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(fond, 0, 0, main.getWidth(), main.getHeight(), null);

		if (affichage == 0) {
			for (Model3D m : models) {
				m.afficherSegments(g);
			}
		} else if (affichage == 1) {
			for (Model3D m : models) {
				m.afficherFaces(g);
			}
		} else if (affichage == 2) {
			trifigure();
			for (Model3D m : models) {
				m.afficher(g, lumiere);
			}
		} else if (affichage == 3) {
			for (Model3D m : models) {
				m.afficherSegments(g, Coupe.tranche(m, CoupeEnZ));
			}
		} else if (affichage == 4) {
			for (Model3D m : models) {
				m.afficherPoint(g);
			}
		}
	}

	public ArrayList<Model3D> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model3D> models) {
		this.models.clear();
		this.models = models;
	}

	public BarreAjout getBarreAjout() {
		return barreAjout;
	}

	public BarreSelect getBarreSelect() {
		return barreSelect;
	}

	public UserListener getUserListener() {
		return userListener;
	}

	public Main getFrame() {
		return main;
	}

	public int getAffichage() {
		return affichage;
	}

	public void setAffichage(int affichage) {
		this.affichage = affichage;
	}

	public int getCoupeEnZ() {
		return CoupeEnZ;
	}

	public void setCoupeEnZ(int coupeEnZ) {
		CoupeEnZ = coupeEnZ;
	}
	
	public void setfond(String name) {
		fond = new ImageIcon(name).getImage();
		this.repaint();
	}

	public Image getFond() {
		return fond;
	}

	public void setFond(Image fond) {
		this.fond = fond;
	}

	public ArrayList<Point> getLumiere() {
		// TODO Auto-generated method stub
		return lumiere;
	}

}
