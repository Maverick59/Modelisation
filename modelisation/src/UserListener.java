import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class UserListener implements MouseWheelListener, MouseMotionListener,
		MouseListener, KeyListener {

	private final Ecran ecran;
	private int x = 0;
	private int y = 0;
	private int bouton;
	private final ArrayList<Model3D> modelSelect = new ArrayList<Model3D>();

	public UserListener(Ecran ecran) {
		this.ecran = ecran;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for (Model3D m : modelSelect) {
			if (e.getWheelRotation() > 0) {
				m.zoom(1.1);
			} else {
				m.zoom(0.9);
			}
		}
		ecran.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int h = x - e.getX();
		int v = y - e.getY();
		for (Model3D m : modelSelect) {
			if (bouton == e.BUTTON1) {
				m.pivoH(h / 360.0);
				m.pivoV(v / 360.0);

			} else if (bouton == e.BUTTON3) {
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		bouton = e.getButton();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Model3D> getModelSelect() {
		return modelSelect;
	}

	public void addModel(Model3D m3d) {
		modelSelect.add(m3d);

	}

}
