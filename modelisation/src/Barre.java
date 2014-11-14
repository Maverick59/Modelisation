import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Barre extends JPanel {

	private final Ecran e;
	private final ArrayList<PanneauModel> models = new ArrayList<PanneauModel>();

	public Barre(Ecran e) {
		this.e = e;
		init();
	}

	private void init() {
		models.add(new PanneauModel("space_station.gts", e));
		models.add(new PanneauModel("x_wing.gts", e));
		models.add(new PanneauModel("tie.gts", e));
		this.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("coco");
				for(PanneauModel pm :models){
					if (e.getWheelRotation() > 0) {
						pm.setAlignmentY(pm.getAlignmentY()-10);
					} else {
						pm.setAlignmentY(pm.getAlignmentY()+10);
					}
					
				}
				
			}
		});
		//models.add(new PanneauModel("space_shuttle.gts", e));

		JPanel jp = new JPanel();

		for (PanneauModel p : models) {
			jp.add(p);
		}
		JScrollPane jb = new JScrollPane(jp);
		jp.setPreferredSize(new Dimension(200, 900));
		this.add(jb);

	}

}
