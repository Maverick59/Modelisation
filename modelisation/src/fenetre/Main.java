package fenetre;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import petiteFonction.ChangerWorkspace;
import petiteFonction.MenuBarre;
import chargement.SplashScreen;
import utilitaire.Insert;
import utilitaire.Parametre;

@SuppressWarnings("serial")
public class Main extends JFrame implements ComponentListener , DropTargetListener {

	private final Ecran e;
	/**
	 * demande quel workspace choisir (la ou se trouve les ressources)
	 * affiche le chargement (avec l'ours)
	 * affiche la frame principale
	 * @param args
	 */
	public static void main(String[] args) {
		ChangerWorkspace.changer();
		if(Parametre.workspace!=null){
			SplashScreen splash = new SplashScreen();
			splash.showSplash();
	
			new Main();
			splash.dispose();
		}
	}
	/**
	 * initialise la frame
	 */
	public Main() {
		e = new Ecran(this);
		this.setTitle("Modelisation");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(e);

		this.setJMenuBar(new MenuBarre(e));

		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
		 
		this.setDropTarget(new DropTarget (this,this));
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}
	/**
	 * refresh les barres (ajout et select)
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		e.getBarreAjout().refresh();
		e.getBarreSelect().refresh();
		e.validate();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		
	}
	/**
	 * permet de gliser des gts dans la frame et de les inserer
	 */
	@Override
	public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop (DnDConstants.ACTION_LINK);
        Transferable trans = dtde.getTransferable();
       
        try {
              Insert.insert((List)trans.getTransferData (DataFlavor.javaFileListFlavor),e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
	
	

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		
	}

}