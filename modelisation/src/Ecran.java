
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ecran extends JPanel {

	
	private	 Main main;
	private static final long serialVersionUID = 1L;
	private ArrayList<Model3D> models = new ArrayList<Model3D>();
	private int affichage;
	private final ArrayList<Point> lumiere = new ArrayList<Point>();
	private BarreAjout barreAjout;
	private BarreSelect barreSelect;
	private final UserListener userListener;
	private Image background;
	
	private JLabel infoModel;

	public Ecran(Main main) {
		this.main = main;
		infoModel = new JLabel("");

		this.setSize(new Dimension(1000, 750));
		userListener = new UserListener(this);
		this.addMouseWheelListener(userListener);
		this.addMouseMotionListener(userListener);
		this.addMouseListener(userListener);
		main.addKeyListener(userListener);
		this.repaint();
		init();
	}

	private void init() {
		barreAjout = new BarreAjout(this);
		barreSelect = new BarreSelect(this);
		affichage = 2;
		lumiere.add(new Point(2, 0, 1));
		background = new ImageIcon("fond.jpg").getImage();
		this.add(infoModel);
	}

	public void trifigure() {
		try {
			Collections.sort(models, new Comparator<Model3D>() {
				@Override
				public int compare(Model3D m1, Model3D m2) {
					if (m1.maxZ() > m2.maxZ()) {
						return 1;
					} else if (m1.maxZ() == m2.maxZ()) {
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

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(background, 0, 0, null);

		if (affichage == 0) {
			for (Model3D m : models) {
				m.afficherSegments(g);
			}
		} else if (affichage == 1) {
			for (Model3D m : models) {
				m.afficherFaces(g);
			}
		} else {
			trifigure();
			for (Model3D m : models) {
				m.afficher(g, lumiere);
			}
		}
	}

	public ArrayList<Model3D> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model3D> models) {
		this.models = models;
		System.out.println(models.get(0).points.get(0).x);
		this.repaint();
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

}
