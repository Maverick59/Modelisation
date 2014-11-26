import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ModelToPNG extends JPanel {

	private final String m;
	private final Model3D model;
	private final ArrayList<Point> l = new ArrayList<Point>();

	public ModelToPNG(String m) {
		this.model = Charger.chargerModel(m);
		this.m = m;
		this.setSize(512, 512);
		l.add(new Point(0, 0, 1));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		Calcul.zoom(model, 0.8, this.getWidth(), this.getHeight());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		model.afficher(g, l);
	}

	public void getScreenShot() {
		BufferedImage bufferedImage;
		bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		this.paint(g);

		try {
			ImageIO.write(bufferedImage, "png", new File("./img/" + m.replaceAll(".gts", "") + ".png"));
		}

		catch (Exception e) {
			System.out.println("erreur enregistrement image...");
			e.printStackTrace();
		}
	}
}