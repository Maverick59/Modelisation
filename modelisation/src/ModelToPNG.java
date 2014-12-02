import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ModelToPNG extends JPanel {

	private String m;
	private Model3D model = null;
	private ArrayList<Point> l = new ArrayList<Point>();

	public ModelToPNG(String m) {
		this.m = m;
		this.setSize(512, 512);
		l.add(new Point(0, 0, 1));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Calcul.zoom(model, 0.8, this.getWidth(), this.getHeight());
		model.afficher(g, l);
	}

	public void getScreenShot() {
		try {
			File f = new File("./img/" + m.replaceAll(".gts", "") + ".png");
			if(!f.exists()){
				this.model = Charger.chargerModel(m);
				BufferedImage bufferedImage = null;
				bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = bufferedImage.createGraphics();
				this.paint(g);
				System.out.println("Creation image : " + "./img/" + m.replaceAll(".gts", "") + ".png");
				ImageIO.write(bufferedImage, "png", new File("./img/" + m.replaceAll(".gts", "") + ".png"));
			}else{
				System.out.println("Existant : " + "./img/" + m.replaceAll(".gts", "") + ".png");
			}
		}catch (Exception e) {
			System.out.println("erreur enregistrement image...");
			e.printStackTrace();
		}
	}
}