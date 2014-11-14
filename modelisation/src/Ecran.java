import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Ecran extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArrayList<Model3D> models = new ArrayList<Model3D>();
	private int affichage;
	private final ArrayList<Point> lumiere = new ArrayList<Point>();
	private BarreAjout barreAjout;
	private BarreSelect barreSelect;
	private final UserListener userListener;

	public Ecran(Main f) {

		this.setSize(new Dimension(1000, 750));
		userListener = new UserListener(this);
		this.addMouseWheelListener(userListener);
		this.addMouseMotionListener(userListener);
		this.addMouseListener(userListener);
		f.addKeyListener(userListener);
		this.repaint();
		init();
	}

	private void init() {
		barreAjout = new BarreAjout(this);
		barreSelect = new BarreSelect(this);
		affichage = 2;
		lumiere.add(new Point(0, 0, 1));
	}

	// @SuppressWarnings("unused")
	@Override
	public void paintComponent(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (affichage == 0) {
			for (Model3D m : models) {
				m.afficherSegments(g);
			}
		} else if (affichage == 1) {
			for (Model3D m : models) {
				m.afficherFaces(g, null);
			}
		} else {
			for (Model3D m : models) {
				m.afficher(g, lumiere);
			}
		}
		g.setColor(Color.BLACK);
		g.drawLine(600, 600, (int) lumiere.get(0).x + 600,
				(int) lumiere.get(0).y + 600);

	}

	public ArrayList<Model3D> getModels() {
		return models;
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
