import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PanneauModel extends JPanel implements MouseListener {

	private final String m;
	private final Model3D model;
	private final Ecran e;

	public PanneauModel(String m, Ecran e) {
		this.model = Charger.chargerModel(m);
		this.m = m;
		this.e = e;
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(e.getWidth() / 5, e.getWidth() / 5));

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		/*
		 * nbRepaint++; System.out.println("nbRepaint: "+nbRepaint);
		 */
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// resize dans les carres bleus
		double smlX = this.getWidth(), hgX = 0, smlY = this.getHeight(), hgY = 0;
		for (int i = 0; i < model.points.size(); i++) {
			if (model.points.get(i).x < smlX) {
				smlX = model.points.get(i).x;
			} else if (model.points.get(i).x > hgX) {
				hgX = model.points.get(i).x;
			} else if (model.points.get(i).y < smlY) {
				smlY = model.points.get(i).y;
			} else if (model.points.get(i).y > hgY) {
				hgY = model.points.get(i).y;
			}
		}
		double sizeX, sizeY;
		sizeX = hgX - smlX;
		sizeY = hgY - smlY;
		/*
		 * System.out.println("taille X: "+sizeX);
		 * System.out.println("taille Y: "+sizeY);
		 */
		if (sizeX > sizeY) {
			model.zoom((this.getWidth() / sizeX) * 0.8);
		} else {
			model.zoom((this.getHeight() / sizeY) * 0.8);
		}
		model.decalageX = this.getWidth() / 2;
		model.decalageY = this.getHeight() / 2;
		model.afficherFaces(g, null);

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
		m3d.deplacementH(-e.getWidth() / 2);
		m3d.deplacementV(-e.getHeight() / 2);
		e.getUserListener().addModel(m3d);
		e.getModels().add(m3d);
		e.getBarreSelect().add(m3d);
		/*debut modifs resize*/
		// resize dans la fenetre principale
		double smlX = this.e.getWidth(), hgX = 0, smlY = this.e.getHeight(), hgY = 0;
		for (int i = 0; i < m3d.points.size(); i++) {
			if (m3d.points.get(i).x < smlX) {
				smlX = m3d.points.get(i).x;
			} else if (m3d.points.get(i).x > hgX) {
				hgX = m3d.points.get(i).x;
			} else if (m3d.points.get(i).y < smlY) {
				smlY = m3d.points.get(i).y;
			} else if (m3d.points.get(i).y > hgY) {
				hgY = m3d.points.get(i).y;
			}
		}
		double sizeX, sizeY;
		sizeX = hgX - smlX;
		sizeY = hgY - smlY;
		/*
		 * System.out.println("taille X: "+sizeX);
		 * System.out.println("taille Y: "+sizeY);
		 */	
		if (sizeX > sizeY) {
			m3d.zoom((this.e.getWidth() / sizeX) * 0.6);
		}else{	
			m3d.zoom((this.e.getHeight() / sizeY) * 0.6);
		}	
		/*fin modifs resize*/
		e.repaint();

	}
}
