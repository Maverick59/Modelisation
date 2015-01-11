package barre;
import fenetre.Ecran;

public class ThreadBarreSelect extends Thread {

	private final Ecran ecran;

	ThreadBarreSelect(Ecran ecran) {
		this.ecran = ecran;
	}
	/**
	 * ouvre ou ferme la barre select (laterale droite)
	 */
	@Override
	public void run() {
		if (ecran.getBarreSelect().isOuvert()) {
			ouvrir();
		} else {
			fermer();
		}
		ecran.getBarreSelect().switchposition();
	}
	/**
	 * annimation de fermeture
	 */
	private void fermer() {
		while (ecran.getBarreSelect().getX() > ecran.getWidth()
				- ecran.getBarreSelect().getWidth()) {
			ecran.getBarreSelect().setLocation(
					ecran.getBarreSelect().getX()
							- ecran.getBarreSelect().getWidth() / 20,
					ecran.getBarreSelect().getY());
			ecran.getBarreSelect().repositionnerBouton();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreSelect().setLocation(
				ecran.getWidth() - ecran.getBarreSelect().getWidth(), 0);
		ecran.getBarreSelect().repositionnerBouton();
	}
	/**
	 * annimation de ouverture
	 */
	private void ouvrir() {
		while (ecran.getBarreSelect().getX() < ecran.getWidth()) {
			ecran.getBarreSelect().setLocation(
					ecran.getBarreSelect().getX()
							+ ecran.getBarreSelect().getWidth() / 20,
					ecran.getBarreSelect().getY());
			ecran.getBarreSelect().repositionnerBouton();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreSelect().setLocation(ecran.getWidth() + 1, 0);
		ecran.getBarreSelect().repositionnerBouton();

	}

}
