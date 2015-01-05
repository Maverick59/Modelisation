import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {

	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);
		
		int width = 700;
		int height = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		JLabel img = new JLabel();
		ImageIcon ii = new ImageIcon("bearwalk.gif");
		Image ima;
		ima = ii.getImage();		
		img = new JLabel(new ImageIcon(ima), SwingConstants.CENTER);
		content.add(img, BorderLayout.CENTER);

		JLabel copyrt = new JLabel("Programme Modélisation", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 24));
	
		content.add(copyrt, BorderLayout.SOUTH);
		
		
		Color oraRed = new Color(0, 50, 50);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

		setVisible(true);
	}

	public void showSplashExit() {
		System.exit(0);
	}

	public static void main(String[] args) {

		SplashScreen splash = new SplashScreen();
		splash.showSplash();

	}
}