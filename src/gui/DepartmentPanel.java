package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.administration.Department;
import model.interfaces.PrintableObject;

public class DepartmentPanel extends RegisterPanel{

	// Fields	
	private JPanel departmentPanel;
	private JList<PrintableObject> displayList;
	private JTextField nameTextField;
	private JTextField codeTextField;
	private JTextArea infoTextArea;
	private Color background;

	
	//Override
	@Override
	public void clearTextFields() {
		nameTextField.setText("");
		codeTextField.setText("");
	}
	
	@Override
	public JPanel getUI() {
		return departmentPanel;
	}

	
	// Constructor
	public DepartmentPanel(Color backgroundColor) {
		// Set the fields variable background to the 
		background = backgroundColor;
		
		// Initiate and configure the main JPanel
		departmentPanel = new JPanel();
		departmentPanel.setLayout(new GridLayout(1, 2, 0, 0));
		departmentPanel.setBackground(background);
		
		
		// Make and configure container for the form and information display (half the window, left side)
		JPanel formAndInfoPanel = new JPanel();
		formAndInfoPanel.setLayout(new GridLayout(2, 1, 0, 0));
		formAndInfoPanel.setBackground(background);
		departmentPanel.add(formAndInfoPanel);
		
		JPanel formPanel = getForm();
		formAndInfoPanel.add(formPanel);
		
		
		// Make and configure information JPanel
		GridBagLayout infoPanelLayout = new GridBagLayout();
		infoPanelLayout.columnWeights = new double[]{1.0};
		
		JPanel infoPanel = new JPanel(infoPanelLayout);
		infoPanel.setBorder(new EmptyBorder(10,10,10,10));
		infoPanel.setBackground(background);
		formAndInfoPanel.add(infoPanel);
		
		addHeaderTextPane(
				new Font("Consolas", Font.BOLD, 25), "Department", 
				background, infoPanel
		);
						
		// Initiate and configure the information JTextArea		
		infoTextArea = addTextArea(
				"Code: \n\n\tName:\n\n\tCourses:\n\n\tEmployees:", 
				infoPanel 
		);
		// Make and configure the JPanel for the edit and delete buttons
		GridBagConstraints btnPnlGbc = new GridBagConstraints();
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.setBackground(background);
		btnPnlGbc.fill = GridBagConstraints.HORIZONTAL;
		btnPnlGbc.gridx = 0;
		btnPnlGbc.gridy = 2;
		btnPnlGbc.weightx = 1;		
		infoPanel.add(buttonsPanel, btnPnlGbc);
		
		// Make and configure edit button
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//editObject(displayList);
			}	
		});
		buttonsPanel.add(editButton);
		
		// Make and configure delete button
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteObject(displayList);
				infoTextArea.setText("\n\tCode:\n\n\tName:\n\n\tCourses:\n\n\tEmployees:");
			}	
		});
		buttonsPanel.add(deleteButton);
				
		// Make and configure displayListPanel
		JPanel displayListPanel = new JPanel();
		displayListPanel.setBackground(background);
		displayListPanel.setLayout(new GridBagLayout());
		displayListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		departmentPanel.add(displayListPanel);
		
		JPanel sortingPanel = getSortingPanel();
		GridBagConstraints sortingGbc = new GridBagConstraints();
		sortingGbc.weightx = 1;
		sortingGbc.weighty = 1;
		displayListPanel.add(sortingPanel, sortingGbc);
		
		
		// Initiate and configure display JList
		displayList = addDisplayList(null, infoTextArea, displayListPanel);
		updateDisplayList(Department.getDepartments(), displayList);

	}
	@Override
	public JPanel getSortingPanel() {
		// Initiate and configure sorting panel
								
				JButton sortByNameButton = new JButton("by Name");
				JButton sortByIDButton = new JButton("by ID");
				
				JPanel sortingPanel = new JPanel(new FlowLayout());
				sortingPanel.add(sortByNameButton);
				sortingPanel.add(sortByIDButton);

				return sortingPanel; 
	}

	@Override
	public JPanel getForm() {
		
		// Make and configure form Panel
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(background);
				
		// Make a header for the Registration form.
		addHeaderTextPane(
				new Font("Consolas", Font.BOLD, 25), "Register Department", 
				background, formPanel
		);
		
		// Make and configure container for the registration form
		GridBagConstraints formGbc = new GridBagConstraints();
		JPanel formContainer = new JPanel(new GridBagLayout());
		formContainer.setBackground(background);
		formGbc.fill = GridBagConstraints.BOTH;
		formGbc.gridx = 0;
		formGbc.gridy = 1;
		formGbc.weighty = 1;
		formPanel.add(formContainer, formGbc);
		
		// Make and configure JLabels
		addLabels("Name: ", 0, 0, new Insets(26, 5, 5, 15), formContainer);
		addLabels("Code: ", 0, 1, new Insets(7, 5, 5, 15), formContainer);

		// Initiate and configure TextFields 	
		nameTextField = addTextFields(1, 0, new Insets(25, 90, 0, 5), formContainer);
		codeTextField = addTextFields(1, 1, new Insets(5, 90, 0, 5), formContainer);
			
		// Make and configure register button
		JButton registerButton = addRegisterButton(GridBagConstraints.NORTH, 0, 2, formContainer);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String code = codeTextField.getText();
				
				if(name.length() > 5 && code.length() >= 2) {
					new Department(name, code);
					updateDisplayList(Department.getDepartments(), displayList);
				}
			}
		});
		return formPanel;	
	}
}
