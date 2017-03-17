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

public class CourseRegPanel extends RegisterPanel {

	private JTextField nameTextField;
	private JTextField codeTextField;
	private JComboBox<PrintableObject> departmentComboBox;

	public CourseRegPanel(Color backgroundColor, String headlines) {
		super(backgroundColor, headlines);
	}

	@Override
	public JPanel getForm() {
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(this.getBackground());

		// Add labels
		String[] labelTexts = { "Name: ", "Code: ", "Department: " };
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
		GridBagConstraints formGbc = new GridBagConstraints();
		formGbc.fill = GridBagConstraints.HORIZONTAL;
		formGbc.anchor = GridBagConstraints.WEST;
		formGbc.gridwidth = GridBagConstraints.REMAINDER;
		formGbc.gridx = 1;
		formGbc.gridy = 0;
		formGbc.insets = new Insets(5, 90, 0, 5);

		nameTextField = new JTextField(15);
		formPanel.add(nameTextField, formGbc);

		codeTextField = new JTextField(15);
		formGbc.gridy++;
		formPanel.add(codeTextField, formGbc);

		departmentComboBox = new JComboBox<>();
		formGbc.gridy++;
		formPanel.add(departmentComboBox, formGbc);

		registerButton = addRegisterButton(++formGbc.gridy, formPanel);

		return formPanel;
	}

	@Override
	public DefaultComboBoxModel<String> getSortingModel() {
		return new DefaultComboBoxModel<String>(new String[] { "Choose..", "Course ID", "Department", "Course name"});
	}

	@Override
	public String getInfoTemplate() {
		return infoAreaTemplate;
	}

	// Fields

	// Main Construcor

	// Methods

	// Getters

	// Setters

	// Overrides

}
