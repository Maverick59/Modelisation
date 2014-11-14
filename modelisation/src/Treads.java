public class Treads extends Thread {

	int delai;
	private final Ecran ecran;

	public Treads(int i, Ecran ecran) {
		delai = i;
		this.ecran = ecran;
	}

	@Override
	public void run() {
		while (true) {
			ecran.repaint();
			try {
				this.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
