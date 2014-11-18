import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PanneauModel extends JPanel implements MouseListener {

	private final String m;
	private Model3D model;
	private final Ecran e;

	public PanneauModel(String m, Ecran e) {
		this.model = Charger.chargerModel(m);
		this.m = m;
		this.e = e;
		this.addMouseListener(this);
		this.refresh();

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		model.afficherFaces(g);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		Model3D m3d = Charger.chargerModel(m);
		Calcul.zoom(m3d, 0.5, e.getWidth(), e.getHeight());
		e.getBarreSelect().add(m3d);
		e.getModels().add(m3d);
		e.repaint();

	}

	public void refresh() {
		this.setPreferredSize(new Dimension(e.getWidth() / 5, e.getWidth() / 5));
		Calcul.zoom(model, 0.8, e.getWidth() / 5, e.getWidth() / 5);
	}

	public Model3D getModel() {
		return model;
	}

	public void setModel(Model3D model) {
		this.model = model;
	}

}
