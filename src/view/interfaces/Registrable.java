package view.interfaces;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
/**
 * 
 * @author Kegulf
 *
 * Used for ALL registration panels bcs they will display their respective objects in JLists.
 *
 */
public interface Registrable {
	JPanel getForm();
	DefaultComboBoxModel<String> getSortingModel();
	String getInfoTemplate();
	//JPanel getUI();
}
