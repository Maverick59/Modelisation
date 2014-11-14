import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class FileListCellRenderer extends DefaultListCellRenderer {
	private final JLabel label;
	private final Color textSelectionColor = Color.WHITE;
	private final Color backgroundSelectionColor = Color.GRAY;
	private final Color textNonSelectionColor = Color.BLACK;
	private final Color backgroundNonSelectionColor = Color.WHITE;

	FileListCellRenderer() {
		label = new JLabel();
		label.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object m,
			int index, boolean selected, boolean expanded) {

		label.setText(m.toString());

		if (selected) {
			label.setBackground(backgroundSelectionColor);
			label.setForeground(textSelectionColor);
		} else {
			label.setBackground(backgroundNonSelectionColor);
			label.setForeground(textNonSelectionColor);
		}

		return label;
	}

}