import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FenetreConfigModel extends JFrame {

	private JTextField ajouthashtag;
	private final DefaultListModel listModelDefault_hashtag = new DefaultListModel();
	private final DefaultListModel listModelDefault_model = new DefaultListModel();
	private final DefaultListModel listModelDefault_modelavechashtag = new DefaultListModel();
	private final JList<String> jList_hashtag = new JList<String>(listModelDefault_hashtag);
	private final JList<String> jList_model = new JList<String>(listModelDefault_model);
	private final JList<String> jList_modelavechashtag = new JList<String>(listModelDefault_modelavechashtag);
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
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2, ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

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
		jp3.setLayout(new GridLayout(2, 1,20,20));

		jp1.add(ajouthashtag, BorderLayout.NORTH);
		jp1.add(scrollBarreW, BorderLayout.CENTER);

		this.add(jp1);
		this.add(scrollBarreEN);
		this.add(jp3);
		this.add(scrollBarreES);

		ajout=new JButton(new ImageIcon("flecheD.png"));
		retire=new JButton(new ImageIcon("flecheG.png"));
		
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
				// TODO Auto-generated method stub
				
			}
		});
		
		ajout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		ajouthashtag.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					GestionBDD.ajoutHashTag(ajouthashtag.getText());
				
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}
	
	private void refreshJlistHashtag(){
		listModelDefault_hashtag.clear();
		for(String h : GestionBDD.rechercheGTS("")){
			listModelDefault_hashtag.addElement(h);
		}
	}
	
	private void refreshJListModel(){
		String s = "";
		for (int i = 0; i < jList_hashtag.getSelectedValues().length; i++) {
			s+=jList_hashtag.getSelectedValues()[i]+" ";
		}
		ArrayList<String> touslesmodel=GestionBDD.rechercheGTS("");
		ArrayList<String> modelavechashtag=GestionBDD.rechercheGTS(s);
		
		listModelDefault_modelavechashtag.clear();
		listModelDefault_model.clear();
		
		for(String m : touslesmodel){
			if(modelavechashtag.contains(m)){
				listModelDefault_modelavechashtag.addElement(m);
			}else{
				listModelDefault_model.addElement(m);
			}
		}
	}
	
}
