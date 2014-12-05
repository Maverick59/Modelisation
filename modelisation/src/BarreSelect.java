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

@SuppressWarnings("serial")
public class BarreSelect extends JPanel {

	private final Ecran e;
	private JButton bouton = new JButton();
	private JScrollPane scroll;
	private final DefaultListModel models = new DefaultListModel();
	private final JList listModels = new JList(models);
	private boolean ouvert = false;

	public BarreSelect(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {

		listModels.setFocusable(false);
		scroll = new JScrollPane(listModels);
		scroll.setViewportView(listModels);
		this.add(scroll);
		this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		this.setLocation(e.getWidth(), 0);
		scroll.getVerticalScrollBar().setUnitIncrement(5);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.gray));

		bouton = new JButton(new ImageIcon("fleche.png"));
		bouton.setFocusable(false);

		bouton.setBorderPainted(false);
		bouton.setContentAreaFilled(false);
		bouton.setFocusPainted(false);
		bouton.setOpaque(false);
		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ThreadBarreSelect(e).start();
			}
		});
		repositionnerBouton();
		e.setLayout(null);
		e.add(this);
		e.add(bouton);
		listModels.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (listModels.isSelectedIndex(index0)) {
					listModels.removeSelectionInterval(index0, index1);
				} else {
					listModels.addSelectionInterval(index0, index1);
				}
			}
		});

		listModels.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent ex) {
				e.getFrame().requestFocus();
				e.getUserListener().getModelSelect().clear();
				for (int i = 0; i < listModels.getSelectedValues().length; i++) {
					e.getUserListener().getModelSelect().add((Model3D) listModels.getSelectedValues()[i]);
				}
				e.getUserListener().refreshModelSelect();
			}
		});

	}

	public void repositionnerBouton() {
		bouton.setBounds(this.getX() - bouton.getIcon().getIconWidth(), this.getHeight() / 2 - 40, 80, 80);
	}

	public void add(Model3D m) {
		models.addElement(m);
	}

	public void addAll(ArrayList<Model3D> l) {
		models.clear();
		for (Model3D m : l) {
			models.addElement(m);
		}
	}

	public void removeAll(ArrayList<Model3D> l) {
		while (!l.isEmpty()) {
			models.removeElement(l.get(0));
		}
	}

	public void refresh() {

		if (ouvert) {
			this.setBounds(e.getWidth() - e.getWidth() / 5, 0, e.getWidth() / 5 + 20, e.getHeight());
		} else {
			this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		}
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));

		this.repositionnerBouton();
	}

	public void switchposition() {
		this.ouvert = !this.ouvert;
	}

	public boolean isOuvert() {
		return ouvert;
	}

	public void clear() {
		models.clear();
	}

}
