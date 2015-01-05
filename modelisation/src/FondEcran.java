import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FondEcran {

	public FondEcran(Ecran e) {

		JFileChooser chooser = new JFileChooser(new File("wallpaper"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(e);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			e.setBackground(chooser.getSelectedFile().getAbsolutePath());
		}
	}

}