package barre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fenetre.Ecran;
import utilitaire.Parametre;
import model.Model3D;

@SuppressWarnings("serial")
public class BarreSelect extends JPanel {

	private final Ecran e;
	private JButton boutonFleche = new JButton();
	private JScrollPane scrollBarre;
	private DefaultListModel listModelDefault = new DefaultListModel();

	private final JList listModelJList = new JList(listModelDefault);

	private boolean ouvert = true;

	public BarreSelect(Ecran e) {
		this.e = e;
		init();
	}
	
	/**
	 * initialise le Jpanel de la barre latterale gauche
	 */
	private void init() {

		listModelJList.setFocusable(false);
		scrollBarre = new JScrollPane(listModelJList);
		scrollBarre.setViewportView(listModelJList);
		this.add(scrollBarre);
		this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		this.setLocation(e.getWidth(), 0);
		scrollBarre.getVerticalScrollBar().setUnitIncrement(5);
		scrollBarre.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		scrollBarre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.gray));

		boutonFleche = new JButton(new ImageIcon(Parametre.workspace + "/flecheG.png"));
		boutonFleche.setFocusable(false);

		boutonFleche.setBorderPainted(false);
		boutonFleche.setContentAreaFilled(false);
		boutonFleche.setFocusPainted(false);
		boutonFleche.setOpaque(false);
		boutonFleche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ThreadBarreSelect(e).start();
			}
		});
		repositionnerBouton();
		e.setLayout(null);
		e.add(this);
		e.add(boutonFleche);
		listModelJList.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (listModelJList.isSelectedIndex(index0)) {
					listModelJList.removeSelectionInterval(index0, index1);
				} else {
					listModelJList.addSelectionInterval(index0, index1);
				}
			}
		});

		listModelJList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent ex) {
				e.getFrame().requestFocus();
				e.getUserListener().getModelSelect().clear();
				for (int i = 0; i < listModelJList.getSelectedValues().length; i++) {
					e.getUserListener().getModelSelect().add((Model3D) listModelJList.getSelectedValues()[i]);
				}
				e.getUserListener().refreshModelSelect();
			}
		});

	}

	public DefaultListModel getModels() {
		return listModelDefault;
	}

	public void setModels(DefaultListModel m) {
		listModelDefault = m;
		listModelJList.setModel(listModelDefault);
	}
	
	/**
	 * repositionne le bouton
	 */
	public void repositionnerBouton() {
		boutonFleche.setBounds(this.getX() - boutonFleche.getIcon().getIconWidth(), this.getHeight() / 2 - 40, 80, 80);
	}
	
	/**
	 * ajoute le model a la barre
	 * @param m le modele a ajouter
	 */
	public void add(Model3D m) {
		listModelDefault.addElement(m);
	}
	
	/**
	 * ajoute une liste de model
	 * @param l une liste contenant les modeles a ajouter
	 */
	public void addAll(ArrayList<Model3D> l) {
		listModelDefault.clear();
		for (Model3D m : l) {
			listModelDefault.addElement(m);
		}
	}
	
	/**
	 * supprime les model passes en parametre
	 * @param l la liste des modeles a supprimer
	 */
	public void removeAll(ArrayList<Model3D> l) {
		while (!l.isEmpty()) {
			listModelDefault.removeElement(l.get(0));
		}
	}

	public JList getListModels() {
		return listModelJList;
	}
	
	/**
	 * refresh la barre
	 */
	public void refresh() {

		if (ouvert) {
			this.setBounds(e.getWidth() - e.getWidth() / 5, 0, e.getWidth() / 5 + 20, e.getHeight());
		} else {
			this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		}
		scrollBarre.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));

		this.repositionnerBouton();
	}
	
	/**
	 * switch entre ouvert et ferme
	 */
	public void switchposition() {
		this.ouvert = !this.ouvert;
	}
	
	public boolean isOuvert() {
		return ouvert;
	}
	
	/**
	 * supprime tous les models de la barre
	 */
	public void clear() {
		listModelDefault.clear();
	}

}
