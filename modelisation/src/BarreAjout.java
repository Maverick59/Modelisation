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
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class BarreAjout extends JPanel {
	private final Ecran e;
	private JButton bouton = new JButton();
	private JScrollPane scroll;
	private final JPanel jp = new JPanel();
	private final ArrayList<PanneauModel> models = new ArrayList<PanneauModel>();
	private boolean ouvert = false;
	private JTextField textfield;

	public BarreAjout(Ecran e) {

		this.e = e;
		init();
	}

	private void init() {

		textfield = new JTextField();
		this.add(textfield);
		textfield.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				ajouterModels(GestionBDD.rechercheGTS(textfield.getText()));

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		scroll = new JScrollPane(jp);
		this.add(scroll);
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, 50 * e.getWidth() / 5));
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.gray));

		ajouterModels(GestionBDD.rechercheGTS(""));

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

	public void ajouterModels(ArrayList<String> tab) {
		for (PanneauModel p : models) {
			jp.remove(p);
		}
		models.clear();
		File file;
		for (String lien : tab) {
			file = new File(lien);
			if (file.isFile()) {
				models.add(new PanneauModel(file.getPath(), e));
			}
		}
		for (PanneauModel p : models) {
			jp.add(p);
		}
		this.refresh();
	}

	public void repositionnerBouton() {
		bouton.setBounds(this.getX() + this.getWidth(), this.getHeight() / 2 - 40, 80, 80);
	}

	public void refresh() {
		if (ouvert) {
			this.setBounds(-e.getWidth() / 5 - 25, 0, e.getWidth() / 5 + 25, e.getHeight());
		} else {
			this.setBounds(0, 0, e.getWidth() / 5 + 25, e.getHeight());
		}
		this.textfield.setPreferredSize(new Dimension(this.getWidth() - 20, 25));
		this.repositionnerBouton();
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, (e.getWidth() + 30) * models.size() / 5));
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		for (PanneauModel pm : models) {
			pm.refresh();
			pm.validate();
		}
		this.validate();
		jp.validate();
		e.validate();
	}

	public void choisir() {
		JFileChooser chooser = new JFileChooser(new File("model"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GTS file", "gts");
		chooser.setFileFilter(filter);
		System.out.println(filter.toString());
		int returnVal = chooser.showOpenDialog(e);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// GestionBDD.insert(filter.toString());
			ajouterModels(GestionBDD.rechercheGTS(textfield.getText()));
			this.refresh();
			scroll.validate();
		}
	}

	public void switchposition() {
		this.ouvert = !this.ouvert;
	}

	public boolean isOuvert() {
		return ouvert;
	}

	public JTextField getTextfield() {
		return textfield;
	}
}
