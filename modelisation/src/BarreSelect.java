import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BarreSelect extends JPanel {

	private final Ecran e;
	private JButton bouton = new JButton();
	private JScrollPane scroll;
	private final DefaultListModel models = new DefaultListModel();
	private final JList listModels = new JList(models);

	public BarreSelect(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {

		scroll = new JScrollPane(listModels);
		scroll.setViewportView(listModels);
		scroll.setPreferredSize(new Dimension(200, 500));
		this.add(scroll);
		this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		this.setLocation(e.getWidth(), 0);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5, models.size()
				* e.getWidth() / 5));
		scroll.getVerticalScrollBar().setUnitIncrement(5);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e
				.getHeight()));
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
				Color.black, Color.gray));

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
		listModels.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent ex) {

			}
		});
	}

	public void repositionnerBouton() {
		bouton.setBounds(this.getX() - bouton.getIcon().getIconWidth(),
				this.getHeight() / 2 - 40, 80, 80);
	}

	public void add(Model3D m) {
		models.addElement(m);
	}

	public void refresh() {
		this.setBounds(e.getWidth(), 0, e.getWidth() / 5 + 20, e.getHeight());
		this.repositionnerBouton();
	}

}
