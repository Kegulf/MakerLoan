package view.registerview;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.interfaces.PrintableObject;
import view.JListCellRenderer;

public class LoanRegPanel extends RegisterPanel {

	// Fields
	private JComboBox<PrintableObject> itemComboBox;
	private JList<PrintableObject> personList;

	// Main Construcor
	public LoanRegPanel(Color backgroundColor, String headlines) {
		super(backgroundColor, headlines);
		// TODO Auto-generated constructor stub
	}
	// Methods

	// Getters

	// Setters

	// Overrides
	@Override
	public JPanel getForm() {
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(this.getBackground());

		// Add labels
		String[] labelTexts = { "Item: ", "People: " };
		for (int i = 0; i < labelTexts.length; i++) {
			GridBagConstraints labelGbc = new GridBagConstraints();
			
				labelGbc.anchor = (i == 0) ? GridBagConstraints.WEST : GridBagConstraints.NORTH; // where it anchors
														// itself
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

		itemComboBox = new JComboBox<>();
		formPanel.add(itemComboBox, formGbc);

		
		personList = new JList<>();
		personList.setCellRenderer(new JListCellRenderer());
		JScrollPane scroll = new JScrollPane(personList);
		formGbc.gridy++;
		formPanel.add(scroll, formGbc);
		

		registerButton = addRegisterButton(++formGbc.gridy, formPanel);

		return formPanel;
	}

	@Override
	public DefaultComboBoxModel<String> getSortingModel() {
		return new DefaultComboBoxModel<String>(
				new String[] {"Choose..", "Loaned at", "Loaners lastname"}
		);
	}

	@Override
	public String getInfoTemplate() {
		return "\n\tLoan ID:\n\n\tItem:\n\n\tLoaner:\n\n\tLoaned at:\n";
	}

}
