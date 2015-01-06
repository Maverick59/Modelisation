import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame implements ComponentListener {

	private final Ecran e;

	public static void main(String[] args) {
		SplashScreen splash = new SplashScreen();
		splash.showSplash();
		new Main();
		splash.dispose();
	}

	public Main() {
		e = new Ecran(this);
		this.setTitle("Modelisation");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(e);

		this.setJMenuBar(new MenuBarre(e));

		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		e.getBarreAjout().refresh();
		e.getBarreSelect().refresh();
		e.validate();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

}