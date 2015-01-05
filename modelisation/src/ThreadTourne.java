public class ThreadTourne extends Thread {

	private final Ecran e;
	private int h = 0;
	private int v = 0;
	private boolean fini = true;

	public ThreadTourne(Ecran e) {
		this.e = e;
		this.start();
	}

	public void init(int h, int v) {

		this.h = h;
		this.v = v;
	}

	@Override
	public void run() {
		fini = false;

		while (!fini) {

			if (h != 0 || v != 0) {

				for (Model3D m : e.getUserListener().getModelSelect()) {
					synchronized (m) {
						m.pivoH(h / 100.0);
						m.pivoV(v / 100.0);
					}
				}
				e.repaint();

			}
			try {
				this.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
