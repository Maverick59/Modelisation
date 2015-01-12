package utilitaire;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import fenetre.Ecran;
import model.Model3D;

public class SaveLoadProject implements Serializable {
	/**
	 * serialise l'ecran;
	 * @param e
	 * @throws IOException
	 */
	public static void serialise(Ecran e) throws IOException {
		try {
			JFrame parentFrame = new JFrame();
			File fileToSave = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");

			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				fileToSave = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());

				FileOutputStream fos = new FileOutputStream(fileToSave.getAbsolutePath());
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(e.getModels());

				oos.close();
			}

		} catch (Exception i) {
			System.out.println("-- Erreur d'enregistrement du fichier --");
		}

	}
	/**
	 * deserialise l'ecran;
	 * @param e
	 */
	public static void deserialise(Ecran e) {
		try {
			JFileChooser chooser = new JFileChooser();
			int returnName = chooser.showOpenDialog(null);
			String path = null;

			if (returnName == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				if (f != null) { // Make sure the user didn't choose a
									// directory.

					path = f.getAbsolutePath();

				}
			}

			if (path != null) {
				FileInputStream fis = new FileInputStream(path);

				ObjectInputStream ois = new ObjectInputStream(fis);

				ArrayList<Model3D> array = (ArrayList<Model3D>) (ois.readObject());

				e.setModels(array);

				DefaultListModel list = new DefaultListModel();
				for (Model3D val : array)
					list.addElement(val);
				e.getBarreSelect().setModels(list);

				ois.close();
			}
			e.repaint();

		} catch (Exception i) {
			System.out.println("-- Erreur d'ouverture du fichier --");
		}
	}

}
