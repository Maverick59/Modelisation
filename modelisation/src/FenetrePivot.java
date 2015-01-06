import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetrePivot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ecran ecran;
	private JTextField jTextFieldX;
	private JTextField jTextFieldY;
	private JTextField jTextFieldZ;
	private JTextField jTextFieldZoom;
	private JTextField jTextFieldTX;
	private JTextField jTextFieldTY;

	private final JButton jButtonX1 = new JButton("<");
	private final JButton jButtonY1 = new JButton("<");
	private final JButton jButtonZ1 = new JButton("<");
	private final JButton jButtonMoins = new JButton("-");
	private final JButton jButtonTX1 = new JButton("<");
	private final JButton jButtonTY1 = new JButton("^");

	private final JButton jButtonX2 = new JButton(">");
	private final JButton jButtonY2 = new JButton(">");
	private final JButton jButtonZ2 = new JButton(">");
	private final JButton jButtonPlus = new JButton("+");
	private final JButton jButtonTX2 = new JButton(">");
	private final JButton jButtonTY2 = new JButton("v");

	public FenetrePivot(Ecran ecran) {
		this.ecran = ecran;
		init();
	}

	public void init() {
		this.setTitle("Config Model");
		this.setResizable(true);
		this.setVisible(true);

		// this.setSize(ecran.getWidth() / 2, ecran.getHeight() / 2);

		this.setLayout(new GridLayout(6, 3));
		this.setLocation(ecran.getFrame().getX() + ecran.getWidth() / 2 - this.getWidth() / 2,
				ecran.getFrame().getY() + ecran.getHeight() / 2 - this.getHeight() / 2);

		jTextFieldX = new JTextField(15);
		jTextFieldY = new JTextField(15);
		jTextFieldZ = new JTextField(15);
		jTextFieldZoom = new JTextField(15);
		jTextFieldTX = new JTextField(15);
		jTextFieldTY = new JTextField(15);

		JPanel jp11 = new JPanel();
		JPanel jp12 = new JPanel();
		JPanel jp13 = new JPanel();
		JPanel jp21 = new JPanel();
		JPanel jp22 = new JPanel();
		JPanel jp23 = new JPanel();
		JPanel jp31 = new JPanel();
		JPanel jp32 = new JPanel();
		JPanel jp33 = new JPanel();
		JPanel jp41 = new JPanel();
		JPanel jp42 = new JPanel();
		JPanel jp43 = new JPanel();
		JPanel jp51 = new JPanel();
		JPanel jp52 = new JPanel();
		JPanel jp53 = new JPanel();
		JPanel jp61 = new JPanel();
		JPanel jp62 = new JPanel();
		JPanel jp63 = new JPanel();

		this.add(jp11);
		this.add(jp12);
		this.add(jp13);
		this.add(jp21);
		this.add(jp22);
		this.add(jp23);
		this.add(jp31);
		this.add(jp32);
		this.add(jp33);
		this.add(jp41);
		this.add(jp42);
		this.add(jp43);
		this.add(jp51);
		this.add(jp52);
		this.add(jp53);
		this.add(jp61);
		this.add(jp62);
		this.add(jp63);

		jp11.add(new JLabel("Rotation X"));
		jp21.add(new JLabel("Rotation Y"));
		jp31.add(new JLabel("Rotation Z"));
		jp41.add(new JLabel("   Zoom    "));
		jp51.add(new JLabel("Translation X"));
		jp61.add(new JLabel("Translation y"));

		jp12.add(jButtonX1);
		jp22.add(jButtonY1);
		jp32.add(jButtonZ1);
		jp42.add(jButtonMoins);
		jp52.add(jButtonTX1);
		jp62.add(jButtonTY1);

		jp12.add(jButtonX2);
		jp22.add(jButtonY2);
		jp32.add(jButtonZ2);
		jp42.add(jButtonPlus);
		jp52.add(jButtonTX2);
		jp62.add(jButtonTY2);

		jp13.add(jTextFieldX);
		jp23.add(jTextFieldY);
		jp33.add(jTextFieldZ);
		jp43.add(jTextFieldZoom);
		jp53.add(jTextFieldTX);
		jp63.add(jTextFieldTY);

		jButtonX1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoH(0.2);
				}
				ecran.repaint();
			}
		});
		jButtonY1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoV(0.2);
				}
				ecran.repaint();
			}
		});
		jButtonZ1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoZ(0.2);
				}
				ecran.repaint();
			}
		});

		jButtonMoins.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.zoom(0.9);
				}
				ecran.repaint();
			}
		});

		jButtonTX1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.deplacementH(10);
				}
				ecran.repaint();
			}
		});

		jButtonTY1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.deplacementV(10);
				}
				ecran.repaint();
			}
		});

		jButtonX2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoH(-0.2);
				}
				ecran.repaint();
			}
		});
		jButtonY2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoV(-0.2);
				}
				ecran.repaint();
			}
		});
		jButtonZ2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.pivoZ(-0.2);
				}
				ecran.repaint();
			}
		});

		jButtonPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.zoom(1.1);
				}
				ecran.repaint();
			}
		});

		jButtonTX2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.deplacementH(-10);
				}
				ecran.repaint();
			}
		});

		jButtonTY2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Model3D m : ecran.getUserListener().getModelSelect()) {
					m.deplacementV(-10);
				}
				ecran.repaint();
			}
		});

		jTextFieldX.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldX.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.pivoH(nb / 360);
						}
						ecran.repaint();
					} catch (Exception exc) {
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldY.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.pivoV(nb / 360);
						}
						ecran.repaint();
					} catch (Exception exc) {
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldZ.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.pivoZ(nb / 360);
						}
						ecran.repaint();
					} catch (Exception exc) {
						messageErreur();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		jTextFieldZoom.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldZoom.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.zoom(nb);
						}
						ecran.repaint();
					} catch (Exception exc) {
						messageErreur();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		jTextFieldTX.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldTX.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.deplacementH((int) nb);
						}
						ecran.repaint();
					} catch (Exception exc) {
						messageErreur();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		jTextFieldTY.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					double nb;
					try {
						nb = Double.parseDouble(jTextFieldTY.getText());
						for (Model3D m : ecran.getUserListener().getModelSelect()) {
							m.deplacementV((int) nb);
						}
						ecran.repaint();
					} catch (Exception exc) {
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
