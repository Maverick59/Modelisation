package petiteFonction;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utilitaire.Parametre;

public class ChangerWorkspace {
	
	/**
	 * change de workspace (lieu ou se trouve les ressources)
	 */
	public static void changer(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(new JFrame());

			if (returnVal == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().isDirectory()) {
				String lien =chooser.getSelectedFile().getAbsolutePath();
				if(!new File(lien+"/bdd_models").exists() || !new File(lien+"/model").exists() || !new File(lien+"/img").exists()){
					JFrame f=new JFrame("Erreur");
					f.add(new JLabel("Toutes les ressources ne sont pas dans ce dossier"));
					f.setVisible(true);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.pack();
				}else{
					Parametre.workspace = lien;
				}
			}
		} catch (Exception e) {
			
		}
	}
}
