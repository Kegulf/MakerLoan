package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.PrintableObject;

/**
 * @author Odd Martin Hansen
 *
 */
@SuppressWarnings("serial")
public class JListCellRenderer extends JLabel implements ListCellRenderer<PrintableObject>{

	/**
	 * Creates a ListCellRenderer for PrintableObjects that the JList will use to render the objects
	 */
	public JListCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
        
    }

    /*
     * This method finds the text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    public Component getListCellRendererComponent(
                                       JList<? extends PrintableObject> list,
                                       PrintableObject object,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        if (isSelected) {
            setBackground(Color.MAGENTA);//list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        //Set the icon and text.  If icon was null, say so.
       setText(" " + object.getFormatedPrint());
       return this;
    }

}
