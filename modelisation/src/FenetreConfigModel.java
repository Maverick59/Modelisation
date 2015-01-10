import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FenetreConfigModel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ajouthashtag;
	private final DefaultListModel listModelDefault_hashtag = new DefaultListModel();
	private final DefaultListModel listModelDefault_model = new DefaultListModel();
	private final DefaultListModel listModelDefault_modelavechashtag = new DefaultListModel();
	private final JList jList_hashtag = new JList(listModelDefault_hashtag);
	private final JList jList_model = new JList(listModelDefault_model);
	private final JList jList_modelavechashtag = new JList(listModelDefault_modelavechashtag);
	private JScrollPane scrollBarreW;
	private JScrollPane scrollBarreEN;
	private JScrollPane scrollBarreES;
	private final Ecran ecran;
	private JButton ajout;
	private JButton retire;

	public FenetreConfigModel(Ecran ecran) {
		this.ecran = ecran;
		init();
	}

	public void init() {
		this.setTitle("Config Model");
		this.setResizable(true);
		this.setVisible(true);

		this.setSize(ecran.getWidth() / 2, ecran.getHeight() / 2);

		this.setLayout(new GridLayout());
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2,
				ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==e.VK_DELETE){
					for (int i = 0; i < jList_hashtag.getSelectedValues().length; i++) {
						GestionBDD.deleteTag((String) jList_hashtag.getSelectedValues()[i]);
					}
					refreshJlistHashtag();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		jList_hashtag.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		jList_model.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		jList_modelavechashtag.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		ajouthashtag = new JTextField();

		scrollBarreW = new JScrollPane(jList_hashtag);
		scrollBarreEN = new JScrollPane(jList_model);
		scrollBarreES = new JScrollPane(jList_modelavechashtag);

		scrollBarreW.setBorder(BorderFactory.createTitledBorder("hastag"));
		scrollBarreEN.setBorder(BorderFactory.createTitledBorder("model"));
		scrollBarreES.setBorder(BorderFactory.createTitledBorder("model : "));

		JPanel jp1 = new JPanel();
		JPanel jp3 = new JPanel();

		jp1.setLayout(new BorderLayout());
		jp3.setLayout(new GridLayout(2, 1, 20, 20));

		jp1.add(ajouthashtag, BorderLayout.NORTH);
		jp1.add(scrollBarreW, BorderLayout.CENTER);

		this.add(jp1);
		this.add(scrollBarreEN);
		this.add(jp3);
		this.add(scrollBarreES);

		ajout = new JButton(new ImageIcon("flecheD.png"));
		retire = new JButton(new ImageIcon("flecheG.png"));

		ajout.setFocusable(false);
		ajout.setBorderPainted(false);
		ajout.setContentAreaFilled(false);
		ajout.setFocusPainted(false);
		ajout.setOpaque(false);

		retire.setFocusable(false);
		retire.setBorderPainted(false);
		retire.setContentAreaFilled(false);
		retire.setFocusPainted(false);
		retire.setOpaque(false);

		jp3.add(ajout);
		jp3.add(retire);

		retire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jList_hashtag.getSelectedValues().length; i++) {
					for (int j = 0; j < jList_model.getSelectedValues().length; j++) {
						System.out.println("" + (String) jList_hashtag.getSelectedValues()[i] + "," + (String) jList_model.getSelectedValues()[j]);
						GestionBDD.removeConnection((String) jList_model.getSelectedValues()[j], (String) jList_hashtag.getSelectedValues()[i]);
					}
				}
				refreshJListModel();
			}
		});

		ajout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jList_hashtag.getSelectedValues().length; i++) {
					for (int j = 0; j < jList_model.getSelectedValues().length; j++) {
						System.out.println("" + (String) jList_hashtag.getSelectedValues()[i] + "," + (String) jList_model.getSelectedValues()[j]);
						GestionBDD.addConnection((String) jList_model.getSelectedValues()[j], (String) jList_hashtag.getSelectedValues()[i]);
					}
				}
				refreshJListModel();
			}
		});

		ajouthashtag.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!ajouthashtag.getText().equals("")) {
						GestionBDD.insertTag(ajouthashtag.getText());
					}
					refreshJlistHashtag();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		jList_hashtag.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent ex) {
				refreshJListModel();
			}
		});

		this.refreshJlistHashtag();
		this.refreshJListModel();
	}

	private void refreshJlistHashtag() {
		listModelDefault_hashtag.clear();
		for (String h : GestionBDD.selectAllTags()) {
			listModelDefault_hashtag.addElement(h);
		}
	}

	private void refreshJListModel() {
		ArrayList<String> modelavechashtag = new ArrayList<String>();
		for (int i = 0; i < jList_hashtag.getSelectedValues().length; i++) {
			modelavechashtag.addAll(GestionBDD.searchByTag((String) jList_hashtag.getSelectedValues()[i]));
		}
		ArrayList<String> touslesmodel = GestionBDD.searchModel("");

		listModelDefault_modelavechashtag.clear();
		listModelDefault_model.clear();
		String nom;
		for (String m : touslesmodel) {

			nom = m.replace('\\', '/');
			nom = nom.replace(".gts", "");
			nom = nom.split("/")[m.split("/").length - 1];

			if (modelavechashtag.contains(nom)) {
				listModelDefault_modelavechashtag.addElement(nom);
			} else {
				listModelDefault_model.addElement(nom);
			}
		}
	}
}
