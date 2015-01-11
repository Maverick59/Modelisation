package petitFonction;
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

@SuppressWarnings("serial")
public class ExportToPng extends JPanel {

	private final Ecran e;

	public ExportToPng(Ecran e) {
		this.e = e;
		this.setSize(e.getWidth(), e.getHeight());
		export();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(e.getFond(), 0, 0, this.getWidth(), this.getHeight(), null);

		for (Model3D m : e.getModels()) {
			m.afficher(g, e.getLumiere());
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
			BufferedImage bufferedImage = null;
			bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = bufferedImage.createGraphics();
			this.paint(g);
			try {
				ImageIO.write(bufferedImage, "png", new File(fileToSave.getAbsolutePath()+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}