package petiteFonction;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import fenetre.Ecran;
import model.Model3D;

public class FenetreCouleur extends JFrame {
	/**
	 * l'utilisateur choisit une couleur et les models se colorent
	 * @param ecran
	 */
	public FenetreCouleur(Ecran ecran) {
		Color c = JColorChooser.showDialog(null, "Couleur", null);
		ArrayList<Model3D> list = ecran.getUserListener().getModelSelect();
		for (Model3D m : list) {
			m.setColor(c);
		}
		ecran.repaint();
	}

}