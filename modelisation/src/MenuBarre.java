import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuBarre extends JMenuBar {

	private final Ecran e;
	private final ArrayList<JMenu> menu = new ArrayList<JMenu>();

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

		c = new JMenuItem("Enregistrer");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Enregister--");
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Enregistrer sous");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Enregistrer sous--");
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
				System.out.println("--Quitter--");
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

		c = new JMenuItem("Rétablir");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Rétablir--");
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Copier");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Copier--");
			}
		});
		menu.get(i).add(c);

		c = new JMenuItem("Coller");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Coller--");
			}
		});
		menu.get(i).add(c);

		/*
		 * MENU AFFICHAGE
		 */
		i = 2;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		c = new JMenuItem("Couleur de modèle");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Couleur de modèle--");
				ArrayList<Model3D> l = e.getModels();
				for(int i=0; i<l.size(); i++){
					System.out.println(l.get(i).getNom());
					GestionBDD.setColor(l.get(i).getNom(), "200/200/200");
				}
				e.repaint();
			}
		});
		menu.get(i).add(c);
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Fond d'écran");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Fond d'écran--");
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
		menu.get(i).add(new JSeparator());

		c = new JMenuItem("Lumière");
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
		/*
		 * MENU MODELISATION
		 */
		i = 3;
		// menu.get(i).add(new JMenuItem("Nouveau"));
		// menu.get(i).add(new JSeparator());

		c = new JMenuItem("Pivot");
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("--Pivot--");
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
				System.out.println("--Découpage en tranche--");
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
