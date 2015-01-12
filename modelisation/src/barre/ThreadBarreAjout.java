package barre;
import fenetre.Ecran;

public class ThreadBarreAjout extends Thread {

	private final Ecran ecran;

	public ThreadBarreAjout(Ecran ecran) {
		this.ecran = ecran;
	}
	
	/**
	 * ouvre ou ferme la barre ajout (laterale gauche)
	 */
	@Override
	public void run() {
		if (ecran.getBarreAjout().isOuvert()) {
			ouvrir();
		} else {
			fermer();
		}
		ecran.getBarreAjout().switchposition();
	}
	
	/**
	 * annimation de fermeture
	 */
	private void fermer() {
		while (ecran.getBarreAjout().getX() + ecran.getBarreAjout().getWidth() > 0) {
			ecran.getBarreAjout().setLocation(ecran.getBarreAjout().getX() - ecran.getBarreAjout().getWidth() / 20, ecran.getBarreAjout().getY());
			ecran.getBarreAjout().repositionnerBouton();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreAjout().setLocation(-ecran.getBarreAjout().getWidth(), ecran.getBarreAjout().getY());
		ecran.getBarreAjout().repositionnerBouton();
	}
	
	/**
	 * annimation d'ouverture
	 */
	private void ouvrir() {
		while (ecran.getBarreAjout().getX() < 0) {
			ecran.getBarreAjout().setLocation(ecran.getBarreAjout().getX() + ecran.getBarreAjout().getWidth() / 20, ecran.getBarreAjout().getY());
			ecran.getBarreAjout().repositionnerBouton();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreAjout().setLocation(0, ecran.getBarreAjout().getY());
		ecran.getBarreAjout().repositionnerBouton();

	}

}
