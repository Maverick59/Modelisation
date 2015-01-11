package barre;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Charger;
import model.Model3D;
import model.Point;
import utilitaire.Calcul;
import utilitaire.GestionBDD;
import utilitaire.Parametre;

@SuppressWarnings("serial")
public class ModelToPNG extends JPanel {

	private final String m;
	private Model3D model = null;
	private final ArrayList<Point> l = new ArrayList<Point>();

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

	public String getScreenShot() {
		try {
			File f = new File(Parametre.workspace + "/" + GestionBDD.searchPNG(m));
			if (!f.exists()) {
				this.model = Charger.chargerModel(Parametre.workspace + "/" + m);

				BufferedImage bufferedImage = null;
				bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = bufferedImage.createGraphics();
				this.paint(g);
				System.out.println("Creation image : " + f.getPath());
				ImageIO.write(bufferedImage, "png", new File(f.getPath()));
			}
			return f.getPath();
		} catch (Exception e) {
			System.out.println("erreur enregistrement image...");
			e.printStackTrace();
		}
		return null;
	}
}