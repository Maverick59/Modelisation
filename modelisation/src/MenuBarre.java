import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuBarre extends JMenuBar {

	private final Ecran e;
	private final ArrayList<JMenu> menu = new ArrayList<JMenu>();
	private ArrayList<Model3D> copier=new ArrayList<Model3D>();
	
	public MenuBarre(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {
		/*
		 * MENU
		 */
		int i;
		menu.add(new JMenu("Fichier"));
		menu.add(new JMenu("Edition"));
		menu.add(new JMenu("Affichage"));
		menu.add(new JMenu("Modélisation"));
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
				e.getBarreAjout().ajouterModels(GestionBDD.rechercheGTS(""));
			}
		});
		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Ouvrir");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Ouvrir--");
				SaveLoadProject.deserialise(e);
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Enregistrer sous...");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Enregistrer sous--");

				try {
					SaveLoadProject.serialise(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		menu.get(i).add(c);

		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Importer");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.getBarreAjout().choisir();
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Exporter");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Exporter--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Quitter");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
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
				System.out.println("--Annuler--");
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Retablir");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Retablir--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Copier");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				copier.clear();
				for(Model3D m : e.getUserListener().getModelSelect()){
					copier.add(m.clone());
				}
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Coller");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(Model3D m : copier){
					m=m.clone();
					e.getModels().add(m);
					e.getBarreSelect().add(m);
				}
			}
		});
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
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Design trait");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(0);
				e.repaint();
			}
		});
		menu.get(i).add(c);
		c = new JMenuItem("Design plein");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(1);
				e.repaint();
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Lumiere");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(2);
				e.repaint();
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Coupe");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(3);
				e.repaint();
			}
		});
		menu.get(i).add(c);
		
		c = new JMenuItem("Point");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				e.setAffichage(4);
				e.repaint();
			}
		});
		menu.get(i).add(c);
		/*
		 * MENU MODELISATION
		 */
		i = 3;

		c = new JMenuItem("Pivot");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetrePivo(e);
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Translation");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Translation--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Zoom");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Zoom--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Découpage en tranche");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FenetreConfigModel(e);
			}
		});
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
				System.out.println("--Notice d'utilisation--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Contacts");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Contacts--");
			}
		});
		menu.get(i).add(c);
	}
}
