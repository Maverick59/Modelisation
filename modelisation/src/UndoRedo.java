import java.util.Stack;

public class UndoRedo<E> {

	private final Stack<E> controleZ = new Stack<E>();
	private E current;
	private final Stack<E> controleY = new Stack<E>();

	public void ajouteZ(E e) {
		if (controleZ.size() > 9)
			controleZ.remove(0);

		if (current != null)
			controleZ.add(current);

		current = e;
	}

	public void ajouteY(E e) {
		if (controleY.size() > 9)
			controleY.remove(0);

		if (current != null)
			controleY.add(current);

		current = e;
	}

	public E retourArrière() {
		E e = null;
		if (!controleZ.isEmpty()) {
			e = controleZ.pop();
			ajouteY(current);
			current = e;
		}
		return e;
	}

	public E retourAvant() {
		E e = null;
		if (!controleY.isEmpty()) {
			e = controleY.pop();
			ajouteZ(current);
			current = e;
		}
		return e;
	}

	public void clearZ() {
		controleZ.clear();
	}

	public void clearY() {
		controleY.clear();
	}

	@Override
	public String toString() {
		String sz = "Z[";
		String sy = "Y[";
		for (E e : controleZ)
			sz += e.toString() + ", ";

		for (E e : controleY)
			sy += e.toString() + ", ";

		if (sz.length() > 2)
			sz = sz.substring(0, sz.length() - 2);

		if (sy.length() > 2)
			sy = sy.substring(0, sy.length() - 2);

		return sz + "]\n" + sy + "]";
	}

	public boolean retourZ() {
		return !controleZ.isEmpty();
	}

	public boolean retourY() {
		return !controleY.isEmpty();
	}
}
