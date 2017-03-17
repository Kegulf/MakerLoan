package gui;

import javax.swing.JPanel;
/**
 * 
 * @author Kegulf
 *
 * Used for ALL registration panels bcs they will display their respective objects in JLists.
 *
 */
public interface DisplayInterface {
	void clearTextFields();
	JPanel getForm();
	JPanel getSortingPanel();
	JPanel getUI();
}
