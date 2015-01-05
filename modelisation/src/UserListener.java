import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class UserListener implements MouseWheelListener, MouseMotionListener, MouseListener, KeyListener {

	private final Ecran ecran;
	private int x = 0;
	private int y = 0;
	private boolean wheeluse = false;
	private int bouton;
	private final ArrayList<Model3D> modelSelect = new ArrayList<Model3D>();
	private final UndoRedo<ArrayList<SaveModel3D>> undoRedo = new UndoRedo<ArrayList<SaveModel3D>>();
	private boolean control = false;
	private boolean reaffichage2 = false;

	// private final ThreadTourne threadTourne;

	public UserListener(Ecran ecran) {
		this.ecran = ecran;
		// threadTourne = new ThreadTourne(ecran);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		if (ecran.getAffichage() == 3) {
			if (e.getWheelRotation() > 0) {
				ecran.setCoupeEnZ(ecran.getCoupeEnZ() - 1);
				System.out.println(ecran.getCoupeEnZ());
			} else {
				ecran.setCoupeEnZ(ecran.getCoupeEnZ() + 1);
			}

		} else {
			if (!wheeluse) {
				ArrayList<SaveModel3D> savemodels = new ArrayList<SaveModel3D>();
				for (Model3D m : ecran.getModels()) {
					savemodels.add(new SaveModel3D(m));
				}
				undoRedo.ajouteZ(savemodels);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		int h = x - e.getX();
		int v = y - e.getY();
		if (ecran.getAffichage() == 2) {
			int points = 0;
			for (Model3D m : ecran.getModels()) {
				points += m.points.size();
			}
			if (points > Parametre.nbLimitePoint) {
				ecran.setAffichage(0);
			}
			reaffichage2 = true;
		}
		if (control && bouton == MouseEvent.BUTTON1) {
			// threadTourne.init(h, v);

		}
		for (Model3D m : modelSelect) {
			if (bouton == MouseEvent.BUTTON1) {
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
		// threadTourne.init(0, 0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ArrayList<SaveModel3D> savemodels = new ArrayList<SaveModel3D>();
		for (Model3D m : ecran.getModels()) {
			savemodels.add(new SaveModel3D(m));
		}
		undoRedo.ajouteZ(savemodels);

		if (reaffichage2) {
			ecran.setAffichage(2);
			reaffichage2 = false;
		}
		ecran.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_CONTROL)
			control = true;

		if (key == KeyEvent.VK_DELETE && !modelSelect.isEmpty()) {
			ecran.getModels().removeAll(modelSelect);
			ecran.getBarreSelect().removeAll(modelSelect);
			modelSelect.clear();
		} else if (control && key == KeyEvent.VK_Z) {
			if (undoRedo.retourZ()) {
				ecran.getModels().clear();
				ecran.getBarreSelect().clear();
				for (SaveModel3D sm : undoRedo.retourArriere()) {
					ecran.getModels().add(sm.getModel());
					ecran.getBarreSelect().add(sm.getModel());
				}
			}
		} else if (control && key == KeyEvent.VK_Y) {
			if (undoRedo.retourY()) {
				ecran.getModels().clear();
				ecran.getBarreSelect().clear();
				for (SaveModel3D sm : undoRedo.retourAvant()) {
					ecran.getModels().add(sm.getModel());
					ecran.getBarreSelect().add(sm.getModel());
				}
			}
		} else if (key == KeyEvent.VK_F1) {
			ecran.setAffichage(0);
		} else if (key == KeyEvent.VK_F2) {
			ecran.setAffichage(1);
		} else if (key == KeyEvent.VK_F3) {
			ecran.setAffichage(2);
		} else if (key == KeyEvent.VK_F4) {
			ecran.setAffichage(3);
		} else if (key == KeyEvent.VK_F5) {
			ecran.setAffichage(4);
		}
		ecran.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_CONTROL) {
			control = false;
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

	public void refreshModelSelect() {

		Calcul.recalulerCentreGravite(modelSelect);

	}

	public Point getPoint() {
		return new Point(x, y, 0);
	}

}
