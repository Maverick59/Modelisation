import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoBarreBottom extends JPanel {

	private final Ecran e;

	public InfoBarreBottom(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {
		this.setBounds(0, 0, e.getWidth(), e.getHeight() / 5);
		this.setPreferredSize(new Dimension(e.getWidth(), e.getHeight() / 5));
		
		
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, e.getWidth(), e.getHeight());
	}
}
