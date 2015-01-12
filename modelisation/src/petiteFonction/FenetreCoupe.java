package petiteFonction;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fenetre.Ecran;
import utilitaire.Calcul;

public class FenetreCoupe extends JFrame {

	private Ecran ecran;
	private JLabel jlabel;
	private JTextField jTextField;

	public FenetreCoupe(Ecran ecran) {
		this.ecran = ecran;
		init();
	}
	/**
	 * initialise la fenetre de choix pour la coupe
	 */
	public void init() {
		this.setTitle("coupe");
		this.setResizable(true);
		this.setVisible(true);

		this.setSize(200,100);
		this.setLayout(new GridLayout(2, 1));
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2, ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

		jlabel =new JLabel("cela donnera X tranche");
		
		jTextField =new JTextField();
		
		this.add(jlabel);
		this.add(jTextField);
		
		jTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					int nb;
					nb =(int)Double.parseDouble(jTextField.getText());
							
					if(nb>=1){
						jlabel.setText("cela donnera "+nbTranche(nb)+" tranche(s)");
						if (e.getKeyCode() == e.VK_ENTER && nbTranche(nb)>0) {
							new CoupeToPng(ecran,(int) nb);
						}
					}
				}catch(Exception ex){
					
				}
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	
	}
	/**
	 * 
	 * @param nb nombre de tranches
	 * @return nombre de tranches que l'on va obtenir.
	 */
	private int nbTranche(int nb) {
		return (int) ((Calcul.maxZ(ecran.getModels())-Calcul.minZ(ecran.getModels()))/nb);
	}
	
	
}
