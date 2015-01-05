import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class Loading extends JWindow {
	public Loading(String filename, Frame f) {
		super(f);
		JLabel l = new JLabel(new ImageIcon(filename));
		getContentPane().add(l, BorderLayout.CENTER);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = l.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
		setVisible(true);
		screenSize = null;
		labelSize = null;
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(200,200);
		Loading l = new Loading(null, null);
	}
}