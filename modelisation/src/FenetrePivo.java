import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FenetrePivo extends JFrame {

	private final Ecran ecran;
	private JTextField jTextFieldX;
	private JTextField jTextFieldY;
	private JTextField jTextFieldZ;

	private JButton jButtonX1=new JButton("<");
	private JButton jButtonY1=new JButton("<");
	private JButton jButtonZ1=new JButton("<");
	
	private JButton jButtonX2=new JButton(">");
	private JButton jButtonY2=new JButton(">");
	private JButton jButtonZ2=new JButton(">");
	
	public FenetrePivo(Ecran ecran) {
		this.ecran = ecran;
		init();
	}

	public void init() {
		this.setTitle("Config Model");
		this.setResizable(true);
		this.setVisible(true);

		//this.setSize(ecran.getWidth() / 2, ecran.getHeight() / 2);

		this.setLayout(new GridLayout(3,1));
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2, ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);
		
		jTextFieldX=new JTextField(15);
		jTextFieldY=new JTextField(15);
		jTextFieldZ=new JTextField(15);
		
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		
		this.add(jp1);		
		this.add(jp2);		
		this.add(jp3);
		
		jp1.add(new JLabel("Rotation X"));
		jp2.add(new JLabel("Rotation Y"));
		jp3.add(new JLabel("Rotation Z"));
		
		
		
		jp1.add(new JButton("<"));
		jp2.add(new JButton("<"));
		jp3.add(new JButton("<"));
		
		jp1.add(new JButton(">"));
		jp2.add(new JButton(">"));
		jp3.add(new JButton(">"));
		
		jp1.add(jTextFieldX);
		jp2.add(jTextFieldY);
		jp3.add(jTextFieldZ);
		
		jTextFieldX.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					double nb;
					try{
						nb=Double.parseDouble(jTextFieldX.getText());
						for(Model3D m:ecran.getUserListener().getModelSelect()){
							m.pivoH(nb/360);
						}
						ecran.repaint();
					}catch(Exception exc){
						messageErreur();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		jTextFieldY.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					double nb;
					try{
						nb=Double.parseDouble(jTextFieldY.getText());
						for(Model3D m:ecran.getUserListener().getModelSelect()){
							m.pivoV(nb/360);
						}
						ecran.repaint();
					}catch(Exception exc){
						messageErreur();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		jTextFieldZ.addKeyListener(new KeyListener() {
	
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					double nb;
					try{
						nb=Double.parseDouble(jTextFieldZ.getText());
						for(Model3D m:ecran.getUserListener().getModelSelect()){
							m.pivoZ(nb/360);
						}
						ecran.repaint();
					}catch(Exception exc){
						messageErreur();
					}
				}
			}
		
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		this.pack();
	}
		
	private void messageErreur() {
		
	}	

}

