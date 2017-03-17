package view.registerview;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepartmentRegPanel extends RegisterPanel {

	// Fields
	private JTextField nameTextField;
	private JTextField codeTextField;

	// Main Construcor
	public DepartmentRegPanel(Color backgroundColor, String headlines) {
		super(backgroundColor, headlines);

	}

	// Methods

	// Getters

	// Setters

	// Overrides
	@Override
	public JPanel getForm() {
		
		// Make panel to return
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(this.getBackground());

		// Add labels
		String[] labelTexts = { "Name: ", "Code: " };
		for (int i = 0; i < labelTexts.length; i++) {
			GridBagConstraints labelGbc = new GridBagConstraints();
			labelGbc.anchor = GridBagConstraints.WEST;
			labelGbc.fill = GridBagConstraints.HORIZONTAL;
			labelGbc.gridwidth = GridBagConstraints.REMAINDER;
			labelGbc.gridx = 0;
			labelGbc.gridy = i;
			labelGbc.insets = new Insets(5, 5, 5, 15);
			formPanel.add(new JLabel(labelTexts[i]), labelGbc);
		}

		// gbc for input components
		GridBagConstraints formGbc = new GridBagConstraints();
		formGbc.fill = GridBagConstraints.HORIZONTAL;
		formGbc.anchor = GridBagConstraints.WEST;
		formGbc.gridwidth = GridBagConstraints.REMAINDER;
		formGbc.gridx = 1;
		formGbc.gridy = 0;
		formGbc.insets = new Insets(5, 90, 0, 5);

		// Instanciate JTextFields and add them to the panel
		nameTextField = new JTextField(15);
		formPanel.add(nameTextField, formGbc);

		codeTextField = new JTextField(15);
		formGbc.gridy++;
		formPanel.add(codeTextField, formGbc);

		// Instanciate the registerbutton with the method in RegisterPanel
		registerButton = addRegisterButton(++formGbc.gridy, formPanel);

		return formPanel;
	}

	@Override
	public DefaultComboBoxModel<String> getSortingModel() {
		return new DefaultComboBoxModel<String>(new String[] { "Choose..", "Dept. ID", "Name" });
	}

	@Override
	public String getInfoTemplate() {
		return infoAreaTemplate;
	}

}
