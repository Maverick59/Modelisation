package barre;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fenetre.Ecran;
import model.Charger;
import model.Model3D;
import utilitaire.Calcul;
import utilitaire.Parametre;

@SuppressWarnings("serial")
public class PanneauModel extends JPanel implements MouseListener {

	private final String m;
	private Model3D model;

	private final Ecran e;
	private Image image;
	private int imgX = 0;
	private int imgY = 0;
	
	/**
	 * initialise les donnees
	 * @param m le nom du modele
	 * @param e l'ecran
	 */
	public PanneauModel(String m, Ecran e) {
		ModelToPNG modelPng = new ModelToPNG(m);
		String img = modelPng.getScreenShot();
		this.m = m;
		this.e = e;
		this.addMouseListener(this);
		this.refresh();
		imgX = e.getWidth() / 5;
		imgY = imgX;
		// image = new ImageIcon(img).getImage();
		try {
			image = ImageIO.read(new File(img));
		} catch (Exception exception) {
			image = new ImageIcon(img).getImage();
		}
	}
	
	/**
	 * affiche l'image du model
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, imgX, imgY, null);
		g.drawString(m.replaceAll("model/", "").replaceAll(".gts", ""), 10, 20);
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
	
	/**
	 * ajoute le modele a l'ecran principal
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		Model3D m3d = Charger.chargerModel(Parametre.workspace + "/" + m);
		Calcul.zoom(m3d, 0.5, e.getWidth(), e.getHeight());
		e.getBarreSelect().add(m3d);
		e.getModels().add(m3d);
		e.repaint();
		e.getUserListener().saveUndoRedo();
	}
	
	/**
	 * refresh le panel
	 */
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
