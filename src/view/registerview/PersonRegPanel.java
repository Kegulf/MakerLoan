package view.registerview;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.PrintableObject;

public class PersonRegPanel extends RegisterPanel {

	
	// Fields
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneNumTextField;
	private JComboBox<String> typeOfPersonComboBox;
	private JComboBox<PrintableObject> departmentComboBox;
	
	// Main Construcor

	// Methods
	
	// Getters
	public String getFirstName() {
		return firstNameTextField.getText();
	}
	public String getLastName() {
		return lastNameTextField.getText();
	}
	public String getPhoneNum() {
		return phoneNumTextField.getText();
	}
	public String getTypeOfPerson() {
		return (String) typeOfPersonComboBox.getSelectedItem();
	}
	
	// Setters

	// Overrides
	public PersonRegPanel(Color backgroundColor, String headlines) {
		super(backgroundColor, headlines);
	
	}

	@Override
	public JPanel getForm() {
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(this.getBackground());
		
		// Add labels
		String[] labelTexts = {"First Name: ", "Last Name: ", "Phone: ", "Department: ", "Category: "};
		for(int i = 0; i < 5; i++) {
			GridBagConstraints labelGbc = new GridBagConstraints();
			labelGbc.anchor = GridBagConstraints.WEST; // where it anchors itself
			labelGbc.fill = GridBagConstraints.HORIZONTAL;
			labelGbc.gridwidth = GridBagConstraints.REMAINDER;
			labelGbc.gridx = 0;
			labelGbc.gridy = i;
			labelGbc.insets = new Insets(5, 5, 5, 15);
			formPanel.add(new JLabel(labelTexts[i]), labelGbc);
		}
		GridBagConstraints formGbc = new GridBagConstraints();
		formGbc.fill = GridBagConstraints.HORIZONTAL;
		formGbc.anchor = GridBagConstraints.WEST;
		formGbc.gridwidth = GridBagConstraints.REMAINDER;
		formGbc.gridx = 1;
		formGbc.gridy = 0;
		formGbc.insets = new Insets(5, 90, 0, 5);

		
		firstNameTextField = new JTextField(15);
		formPanel.add(firstNameTextField, formGbc);
		
		lastNameTextField = new JTextField(15);
		formGbc.gridy++;
		formPanel.add(lastNameTextField, formGbc);
		
		phoneNumTextField = new JTextField(15);
		formGbc.gridy++;
		formPanel.add(phoneNumTextField, formGbc);
		
		departmentComboBox = new JComboBox<>();
		formGbc.gridy++;
		formPanel.add(departmentComboBox, formGbc);
		
		typeOfPersonComboBox = new JComboBox<String>();
		typeOfPersonComboBox.setModel(
			new DefaultComboBoxModel<String>(
					new String[] {"Choose..", "Academic Employee", "Administrative Employee", "Student", "Student Assistant"}
			));
		formGbc.gridy++;
		formPanel.add(typeOfPersonComboBox, formGbc);
		
		
		registerButton = addRegisterButton(++formGbc.gridy, formPanel);
		
		return formPanel;		
	}

	@Override
	public DefaultComboBoxModel<String> getSortingModel() {
		return new DefaultComboBoxModel<String>(
				new String[] {"Choose..", "Person ID", "Department", "First name", "Last Name"}
		);
	}

	@Override
	public String getInfoTemplate() {
		return infoAreaTemplate;
	}

}
