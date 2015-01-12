package barre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import fenetre.Ecran;
import utilitaire.GestionBDD;
import utilitaire.Parametre;

@SuppressWarnings("serial")
public class BarreAjout extends JPanel {

	private final Ecran e;
	private JButton boutonFleche = new JButton();
	private JScrollPane scrollBarre;
	private final JPanel jp = new JPanel();
	private final ArrayList<PanneauModel> models = new ArrayList<PanneauModel>();
	private boolean ouvert = false;
	private JTextField textfield;

	public BarreAjout(Ecran e) {
		this.e = e;
		init();
	}
	/**
	 * initialise le Jpanel dans la barre laterale gauche
	 */
	private void init() {

		textfield = new JTextField();
		this.add(textfield);
		textfield.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				refreshImg();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		scrollBarre = new JScrollPane(jp);
		this.add(scrollBarre);
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, 50 * e.getWidth() / 5));
		scrollBarre.getVerticalScrollBar().setUnitIncrement(20);
		scrollBarre.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.gray));

		ajouterModels(GestionBDD.searchModel(""));

		boutonFleche = new JButton(new ImageIcon(Parametre.workspace + "/flecheD.png"));
		boutonFleche.setFocusable(false);

		boutonFleche.setBorderPainted(false);
		boutonFleche.setContentAreaFilled(false);
		boutonFleche.setFocusPainted(false);
		boutonFleche.setOpaque(false);
		boutonFleche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ThreadBarreAjout(e).start();

			}
		});
		repositionnerBouton();

		e.setLayout(null);
		e.add(this);
		e.add(boutonFleche);

	}
	/**
	 * cherche les modeles presents dans la barre
	 * @param tab l'arraylist des modeles visible sur la barre d'ajout
	 */
	public void ajouterModels(ArrayList<String> tab) {
		for (PanneauModel p : models) {
			jp.remove(p);
		}
		models.clear();
		File file;
		for (String lien : tab) {
			file = new File(Parametre.workspace + "/" + lien);
			if (file.isFile()) {
				models.add(new PanneauModel("model/" + file.getName(), e));
			}
		}
		for (PanneauModel p : models) {
			jp.add(p);
		}

		this.refresh();
	}
	/**
	 * repositionne le bouton
	 */
	public void repositionnerBouton() {
		boutonFleche.setBounds(this.getX() + this.getWidth(), this.getHeight() / 2 - 40, 80, 80);
	}
	/**
	 * refresh la barre
	 */
	public void refresh() {
		if (ouvert) {
			this.setBounds(-e.getWidth() / 5 - 25, 0, e.getWidth() / 5 + 25, e.getHeight());
		} else {
			this.setBounds(0, 0, e.getWidth() / 5 + 25, e.getHeight());
		}
		this.textfield.setPreferredSize(new Dimension(this.getWidth() - 20, 25));
		this.repositionnerBouton();
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, (e.getWidth() + 30) * models.size() / 5));
		scrollBarre.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		for (PanneauModel pm : models) {
			pm.refresh();
			pm.validate();
		}
		e.validate();
		this.validate();
		jp.repaint();

	}
	/**
	 * switch entre ouvert et fermer
	 */
	public void switchposition() {
		this.ouvert = !this.ouvert;
	}
	
	public boolean isOuvert() {
		return ouvert;
	}

	public JTextField getTextfield() {
		return textfield;
	}
	/**
	 * refresh l'image du panel
	 */
	public void refreshImg() {
		ajouterModels(GestionBDD.searchModel(textfield.getText()));
	}
}
