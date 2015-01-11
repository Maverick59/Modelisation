package petitFonction;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fenetre.Ecran;
import utilitaire.Parametre;

public class FondEcran {

	public FondEcran(Ecran e) {

		JFileChooser chooser = new JFileChooser(new File(Parametre.workspace + "/wallpaper"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(e);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			e.setfond(chooser.getSelectedFile().getAbsolutePath());
		}
	}

}