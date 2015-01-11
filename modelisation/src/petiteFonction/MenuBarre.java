package petiteFonction;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import model.Model3D;
import utilitaire.GestionBDD;
import utilitaire.Insert;
import utilitaire.Parametre;
import utilitaire.SaveLoadProject;
import fenetre.Ecran;

public class MenuBarre extends JMenuBar {

	private final Ecran e;
	private final ArrayList<JMenu> menu = new ArrayList<JMenu>();
	private final ArrayList<Model3D> copier = new ArrayList<Model3D>();

	public MenuBarre(Ecran e) {
		this.e = e;
		init();
	}
	/**
	 * initialise la jMenuBarre
	 */
	private void init() {
		/*
		 * MENU
		 */
		int i;
		menu.add(new JMenu("Fichier"));
		menu.add(new JMenu("Edition"));
		menu.add(new JMenu("Affichage"));
		menu.add(new JMenu("Modelisation"));
		menu.add(new JMenu("Aide"));

		for (JMenu m : menu) {
			this.add(m);
		}

		/*
		 * MENU FICHIER
		 */
		i = 0;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		JMenuItem c = new JMenuItem("Nouveau");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getModels().clear();
				e.getBarreSelect().clear();
				e.getUserListener().getModelSelect().clear();
				e.getBarreAjout().getTextfield().setText("");
				e.getBarreAjout().ajouterModels(GestionBDD.searchModel(""));
			}
		});

		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));

		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Ouvrir");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveLoadProject.deserialise(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		c = new JMenuItem("Enregistrer sous...");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					SaveLoadProject.serialise(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		c = new JMenuItem("Changer Workspace");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

					ChangerWorkspace.changer();

			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Importer");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Insert.insert(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		c = new JMenuItem("Exporter en Png");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ExportToPng(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		
		c = new JMenuItem("Exporter Coupe en Png");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetreCoupe(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Quitter");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		menu.get(i).add(c);
		/*
		 * MENU EDITION
		 */
		i = 1;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		c = new JMenuItem("Annuler");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getUserListener().retourArriere();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		c = new JMenuItem("Retablir");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getUserListener().retourAvant();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Copier");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getUserListener().copier();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		c = new JMenuItem("Coller");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getUserListener().coller();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		/*
		 * MENU AFFICHAGE
		 */
		i = 2;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		c = new JMenuItem("Couleur de modele");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Couleur de modèle--");
				new FenetreCouleur(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Fond d'ecran");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FondEcran(e);
			}
		});
		menu.get(i).add(c);
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Avec Lumiere");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(2);
				e.repaint();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		menu.get(i).add(c);
		
		c = new JMenuItem("Sans Lumiere");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(1);
				e.repaint();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menu.get(i).add(c);

		c = new JMenuItem("Design trait");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(0);
				e.repaint();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.get(i).add(c);
		
		c = new JMenuItem("Design point");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(4);
				e.repaint();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menu.get(i).add(c);
		
		c = new JMenuItem("Coupe");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(3);
				e.repaint();
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		menu.get(i).add(c);

		/*
		 * MENU MODELISATION
		 */
		i = 3;

		c = new JMenuItem("Fonctions de modelisation");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetrePivo(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Couleur en bdd");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetreColor(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Gestion Hashtags");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetreConfigModel(e);
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		/*
		 * MENU Aide
		 */
		i = 4;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		c = new JMenuItem("Notice d'utilisation");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Desktop d = Desktop.getDesktop();
				try {
					d.open(new File(Parametre.workspace + "/noticeUtilisation.pdf"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Contacts");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Contact(e);
			}
		});
		menu.get(i).add(c);
	}
}
