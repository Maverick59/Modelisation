import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class BarreAjout extends JPanel {

	private final Ecran e;
	private JButton bouton = new JButton();
	private JScrollPane scroll;
	private final JPanel jp = new JPanel();
	private final ArrayList<PanneauModel> models = new ArrayList<PanneauModel>();

	public BarreAjout(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {

		models.add(new PanneauModel("x_wing.gts", e));
		models.add(new PanneauModel("tie.gts", e));
		models.add(new PanneauModel("space_station.gts", e));
		models.add(new PanneauModel("space_shuttle.gts", e));
		models.add(new PanneauModel("horse.gts", e));
		models.add(new PanneauModel("bunny.gts", e));

		for (PanneauModel p : models) {
			jp.add(p);
		}
		scroll = new JScrollPane(jp);
		this.add(scroll);
		this.setBounds(0, 0, e.getWidth() / 5 + 20, e.getHeight());
		this.setLocation(0, 0);
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, models.size()
				* e.getWidth() / 5));
		scroll.getVerticalScrollBar().setUnitIncrement(5);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e
				.getHeight()));
		jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
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
				new ThreadBarreAjout(e).start();

			}
		});
		repositionnerBouton();

		e.setLayout(null);
		e.add(this);
		e.add(bouton);

	}

	public void repositionnerBouton() {
		bouton.setBounds(this.getX() + this.getWidth(),
				this.getHeight() / 2 - 40, 80, 80);

	}

	public void refresh() {
		this.setBounds(0, 0, e.getWidth() / 5 + 20, e.getHeight());
		this.repositionnerBouton();
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, models.size()
				* e.getWidth() / 5));
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e
				.getHeight()));
	}
}
