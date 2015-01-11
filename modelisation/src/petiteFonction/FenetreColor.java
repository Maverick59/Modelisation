package petiteFonction;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fenetre.Ecran;
import utilitaire.GestionBDD;
import utilitaire.Parametre;

public class FenetreColor extends JFrame {

	private final Ecran ecran;
	private JColorChooser jColorChooser;
	private final DefaultListModel listModelDefault_model = new DefaultListModel();
	private final JList jList = new JList(listModelDefault_model);

	private final JButton jButtonQuitter = new JButton("Quitter");
	private final JButton jButtonAppliquer = new JButton("Appliquer");

	public FenetreColor(Ecran e) {
		this.ecran = e;
		init();
	}
	/**
	 * initialise la fenetre pour changer la couleur
	 * des models presents dans la bdd
	 */
	private void init() {
		this.setResizable(true);
		this.setVisible(true);

		this.setSize(950, 300);

		this.setLayout(new BorderLayout());
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2, ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

		jColorChooser = new JColorChooser();

		jColorChooser.remove(jColorChooser.getComponent(1));

		String nom;
		for (String m : GestionBDD.searchModel("")) {
			nom = m.replace(".gts", "");
			nom = nom.split("/")[nom.split("/").length - 1];
			listModelDefault_model.addElement(nom);
		}

		JScrollPane scroll = new JScrollPane(jList);
		scroll.setBorder(BorderFactory.createTitledBorder("model"));

		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(jColorChooser, BorderLayout.CENTER);
		jp.add(jp2, BorderLayout.SOUTH);

		jp2.add(jButtonQuitter);
		jp2.add(jButtonAppliquer);

		jButtonQuitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				quitter();
			}

		});

		jButtonAppliquer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < jList.getSelectedValues().length; i++) {
					GestionBDD.setColor((String) jList.getSelectedValues()[i], jColorChooser.getColor().getRGB());
					File f = new File(Parametre.workspace + "/" + GestionBDD.searchPNG("model/"+(String) jList.getSelectedValues()[i]+".gts"));
					f.delete();
					ecran.getBarreAjout().refreshImg();

				}
			}
		});

		this.add(scroll, BorderLayout.WEST);
		this.add(jp, BorderLayout.EAST);
	}
	/**
	 * quitte la frame
	 */
	private void quitter() {
		this.dispose();
	}

}
