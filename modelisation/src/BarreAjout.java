import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class BarreAjout extends JPanel {
	private Ecran e;
	private JButton bouton = new JButton();
	private JScrollPane scroll;
	private JButton choix;
	private final JPanel jp = new JPanel();
	private final ArrayList<PanneauModel> models = new ArrayList<PanneauModel>();
	private boolean ouvert = false;

	public BarreAjout(Ecran e) {

		this.e = e;
		init();
	}

	
	/*
	public void choisir() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GTS file", "gts");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(e);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			PanneauModel panneau = new PanneauModel(chooser.getSelectedFile().getName(), main);
			models.add(panneau);
			jp.add(panneau);
			this.refresh();
			scroll.validate();
		}
	}
	*/

	private void init() {
		/*
		models.add(new PanneauModel("Altair.gts", e));
		models.add(new PanneauModel("cloud.gts", e));
		models.add(new PanneauModel("panda.gts", e));
		models.add(new PanneauModel("snowman.gts", e));
		models.add(new PanneauModel("horse.gts", e));
		models.add(new PanneauModel("bunny.gts", e));
		models.add(new PanneauModel("StormTrooper.gts", e));
		*/
		
		File folder = new File("model");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
				models.add(new PanneauModel(file.getName(), e));
		    }
		}
		for (PanneauModel p : models) {
			jp.add(p);
		}
		
		scroll = new JScrollPane(jp);
		this.add(scroll);
		this.setBounds(0, 0, e.getWidth() / 5 + 20, e.getHeight());
		this.setLocation(0, 0);
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, 50 * e.getWidth() / 5));
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.gray));


		choix = new JButton();
		choix.setFocusable(false);
		choix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			

			}
		});

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
		e.add(choix);

	}

	public void repositionnerBouton() {
		bouton.setBounds(this.getX() + this.getWidth(), this.getHeight() / 2 - 40, 80, 80);
		choix.setBounds(550, 0, 100, 50);// a mettre dans un JmenuBar
		choix.setText("Ouvrir");

	}

	public void refresh() {
		if (ouvert) {
			this.setBounds(-e.getWidth() / 5 - 25, 0, e.getWidth() / 5 + 25, e.getHeight());
		} else {
			this.setBounds(0, 0, e.getWidth() / 5 + 25, e.getHeight());
		}
		this.repositionnerBouton();
		jp.setPreferredSize(new Dimension(e.getWidth() / 5, (e.getWidth() + 30) * models.size() / 5));
		scroll.setPreferredSize(new Dimension(e.getWidth() / 5 + 20, e.getHeight()));
		for (PanneauModel pm : models) {
			pm.refresh();
		}

	}

	public void switchposition() {
		this.ouvert = !this.ouvert;
	}

	public boolean isOuvert() {
		return ouvert;
	}

}
