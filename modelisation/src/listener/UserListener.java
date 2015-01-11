package listener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import fenetre.Ecran;
import model.Model3D;
import model.Point;
import utilitaire.Calcul;
import utilitaire.Parametre;
import utilitaire.UndoRedo;

public class UserListener implements MouseWheelListener, MouseMotionListener, MouseListener, KeyListener {

	private final Ecran ecran;
	private int x = 0;
	private int y = 0;
	private boolean wheeluse = false;
	private int bouton;
	private final ArrayList<Model3D> modelSelect = new ArrayList<Model3D>();
	private final UndoRedo<ArrayList<Model3D>> undoRedo = new UndoRedo<ArrayList<Model3D>>();
	private boolean reaffichage2 = false;
	private final ArrayList<Model3D> copier = new ArrayList<Model3D>();
	private boolean control=false;

	public UserListener(Ecran ecran) {
		this.ecran = ecran;
	}
	/**
	 * permet le zoom ou fait defiler les tranche
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		if (ecran.getAffichage() == 3 && !modelSelect.isEmpty()){
			if(ecran.getCoupeEnZ()<Calcul.minZ(modelSelect)){
				ecran.setCoupeEnZ((int) Calcul.minZ(modelSelect));
			}else if(ecran.getCoupeEnZ()>Calcul.maxZ(modelSelect)){
				ecran.setCoupeEnZ((int) Calcul.maxZ(modelSelect));
			}else if (e.getWheelRotation() > 0 && ecran.getCoupeEnZ()>Calcul.minZ(modelSelect)) {
				ecran.setCoupeEnZ(ecran.getCoupeEnZ() - 1);
			} else if(ecran.getCoupeEnZ()<Calcul.maxZ(modelSelect)){
				ecran.setCoupeEnZ(ecran.getCoupeEnZ() + 1);
			}

		} else {
			if (!wheeluse) {
				saveUndoRedo();
			}
			wheeluse = true;
			for (Model3D m : modelSelect) {
				if (e.getWheelRotation() > 0) {
					m.zoom(1.1);
				} else {
					m.zoom(0.9);
				}
			}
		}
		ecran.repaint();
	}
	/**
	 * permet le pivo et la translation
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int h = x - e.getX();
		int v = y - e.getY();

		if (ecran.getAffichage() == 2) {
			int points = 0;
			for (Model3D m : ecran.getModels()) {
				points += m.getPoints().size();
			}
			if (points > Parametre.nbLimitePoint) {
				ecran.setAffichage(0);
			}
			reaffichage2 = true;
		}
		for (Model3D m : modelSelect) {
			if (control && bouton == MouseEvent.BUTTON1) {
				if(Math.abs(h)>Math.abs(v)){
					m.pivoZ(h/ 360.0);
				}else{
					m.pivoZ(v/ 360.0);
				}
				
			}else if (bouton == MouseEvent.BUTTON1) {
				
				m.pivoH(h / 360.0);
				m.pivoV(v / 360.0);

			} else if (bouton == MouseEvent.BUTTON3) {
				m.deplacementH(h);
				m.deplacementV(v);
			}
		}
		x = e.getX();
		y = e.getY();
		ecran.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		ecran.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		ecran.getFrame().requestFocus();
		bouton = e.getButton();
		wheeluse = false;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

		saveUndoRedo();
		if (reaffichage2) {
			ecran.setAffichage(2);
			reaffichage2 = false;
		}
		ecran.repaint();
	}
	/**
	 * permet de supprimer 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_CONTROL){
			control=true;
		}
		
		if (key == KeyEvent.VK_DELETE && !modelSelect.isEmpty()) {
			ecran.getModels().removeAll(modelSelect);
			ecran.getBarreSelect().removeAll(modelSelect);
			modelSelect.clear();
			this.saveUndoRedo();
		}

		ecran.repaint();

	}
	/**
	 * le control Z 
	 */
	public void retourAvant() {
		if (undoRedo.retourY()) {
			ecran.getModels().clear();
			ecran.getBarreSelect().clear();
			for (Model3D m : undoRedo.retourAvant()) {
				ecran.getModels().add(m);
				ecran.getBarreSelect().add(m);
			}
		}
	}
	/**
	 * le control Y 
	 */
	public void retourArriere() {
		if (undoRedo.retourZ()) {
			ecran.getModels().clear();
			ecran.getBarreSelect().clear();
			for (Model3D m : undoRedo.retourArriere()) {
				ecran.getModels().add(m);
				ecran.getBarreSelect().add(m);
			}
		}
	}
	/**
	 * 
	 */
	public void saveUndoRedo() {
		int nb = 0;
		for (Model3D m : modelSelect) {
			nb += m.getPoints().size();
		}

		if (nb < Parametre.nbLimitePointSave) {
			ArrayList<Model3D> savemodels = new ArrayList<Model3D>();
			for (Model3D m : ecran.getModels()) {
				savemodels.add(m.clone());
			}
			undoRedo.ajouteZ(savemodels);

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_CONTROL){
			control=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public ArrayList<Model3D> getModelSelect() {
		return modelSelect;
	}

	public void addModel(Model3D m3d) {
		modelSelect.add(m3d);
	}
	/**
	 * recalule le centre de gravite
	 */
	public void refreshModelSelect() {
		Calcul.recalulerCentreGravite(modelSelect);
	}

	public Point getPoint() {
		return new Point(x, y, 0);
	}
	/**
	 * copier les model selectionné
	 */
	public void copier() {
		copier.clear();
		for (Model3D m : modelSelect) {
			copier.add(m.clone());
		}
	}
	/**
	 * coller les model selectionné
	 */
	public void coller() {
		for (Model3D m : copier) {
			m = m.clone();
			ecran.getModels().add(m);
			ecran.getBarreSelect().add(m);
		}
	}

}
