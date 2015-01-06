import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Contact extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Ecran ecran;
	private JLabel adresse;
	private JLabel nom;

	public Contact(Ecran ecran) {
		this.ecran = ecran;
		init();
	}

	public void init() {
		this.setTitle("Contact");
		this.setResizable(true);
		this.setVisible(true);

		this.setSize(ecran.getWidth() / 2, ecran.getHeight() / 2);

		this.setLayout(new GridLayout());
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2,
				ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(4, 2, 10, 15));
		nom = new JLabel("BRICOUT Eliott");
		adresse = new JLabel("eliott.bricout@univ-lille1.fr");

		jp1.add(nom);
		jp1.add(adresse);

		nom = new JLabel("GHESQUIERE Jerome");
		adresse = new JLabel("jerome.ghesquiere@univ-lille1.fr");

		jp1.add(nom);
		jp1.add(adresse);

		nom = new JLabel("CUVILLIER Alexandre");
		adresse = new JLabel("alexandre.cuviller@univ-lille1.fr");

		jp1.add(nom);
		jp1.add(adresse);

		nom = new JLabel("GUILBERT Florian");
		adresse = new JLabel("florian.guilbert@univ-lille1.fr");

		jp1.add(nom);
		jp1.add(adresse);

		this.add(jp1);

	}
}
