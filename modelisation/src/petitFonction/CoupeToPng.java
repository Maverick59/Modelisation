package petitFonction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fenetre.Ecran;
import model.Model3D;
import utilitaire.Calcul;
import utilitaire.Coupe;

@SuppressWarnings("serial")
public class CoupeToPng extends JPanel {

	private final Ecran e;
	private int z;
	private int trancheSize;
	
	public CoupeToPng(Ecran e,int trancheSize) {
		this.e = e;
		this.trancheSize=trancheSize;
		this.setSize(e.getWidth(), e.getHeight());
		export();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (Model3D m : e.getModels()) {
			m.afficherSegments(g, Coupe.tranche(m, z));
		}
		
	}

	public void export() {
		JFrame parentFrame = new JFrame();
		File fileToSave = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			fileToSave.mkdirs();
			BufferedImage bufferedImage = null;
			Graphics g;
			z=(int) Calcul.minZ(e.getModels());
			for(int i=0;z<(int) Calcul.maxZ(e.getModels());i++){
				z+=trancheSize;
				bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
				g = bufferedImage.createGraphics();
				this.paint(g);
				try {
					ImageIO.write(bufferedImage, "png", new File(fileToSave.getPath()+"/"+i+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}