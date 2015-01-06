public class SaveModel3D {

	private final Model3D model;

	public SaveModel3D(Model3D m) {
		this.model = null;
		/*
		 * Charger.chargerModel(m.getNom() + ".gts");
		 * this.model.zoom(m.getZoom()); this.model.deplacementH((int)
		 * -m.getDecalageX()); this.model.deplacementV((int) -m.getDecalageY());
		 * this.model.pivoH(-m.getRotationH());
		 * this.model.pivoV(-m.getRotationV());
		 */
	}

	public Model3D getModel() {
		return model;
	}

}
