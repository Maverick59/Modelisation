import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauModel extends JPanel implements MouseListener {

	private final String m;
	private Model3D model;

	private final Ecran e;
	private final Image image;
	private int imgX = 0;
	private int imgY = 0;

	public PanneauModel(String m, Ecran e) {
		ModelToPNG modelPng = new ModelToPNG(m);
		String img = modelPng.getScreenShot();
		this.m = m;
		this.e = e;
		this.addMouseListener(this);
		this.refresh();
		imgX = e.getWidth() / 5;
		imgY = imgX;
		image = new ImageIcon(img).getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, imgX, imgY, null);
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
		e.getFrame().requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		Model3D m3d = Charger.chargerModel(m);
		Calcul.zoom(m3d, 0.5, e.getWidth(), e.getHeight());
		e.getBarreSelect().add(m3d);
		e.getModels().add(m3d);
		//e.getUserListener().getModelSelect().add(m3d);
		e.repaint();
	}

	public void refresh() {
		imgX = e.getWidth() / 5;
		imgY = e.getWidth() / 5;
		this.setPreferredSize(new Dimension(e.getWidth() / 5, e.getWidth() / 5));
		this.repaint();
	}

	public Model3D getModel() {
		return model;
	}

	public void setModel(Model3D model) {
		this.model = model;
	}

}