package view.registerview;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemRegPanel extends RegisterPanel {

	private JTextField nameTextField;
	private JTextField itemIdTextField;

	public ItemRegPanel(Color backgroundColor, String headlines) {
		super(backgroundColor, headlines);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel getForm() {
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(this.getBackground());
		
		// Add labels
		String[] labelTexts = {"Name: ", "Item ID: "};
		for(int i = 0; i < labelTexts.length; i++) {
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

		
		nameTextField = new JTextField(15);
		formPanel.add(nameTextField, formGbc);
		
		itemIdTextField = new JTextField(15);
		formGbc.gridy++;
		formPanel.add(itemIdTextField, formGbc);
		
		registerButton = addRegisterButton(++formGbc.gridy, formPanel);
		
		return formPanel;
	}

	@Override
	public DefaultComboBoxModel<String> getSortingModel() {
		return new DefaultComboBoxModel<String>(
				new String[] {"Choose..", "Item ID", "Name"}
		);
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
