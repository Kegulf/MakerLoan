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
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.administration.Course;
import model.administration.Department;
import model.interfaces.PrintableObject;

public class CoursePanel extends RegisterPanel{

	// Fields
	private JPanel coursePanel;
	private Color background;
	private JComboBox<PrintableObject> departmentComboBox;
	private JList<PrintableObject> displayList;
	private JTextField nameTextField;
	private JTextField courseIDTextField;
	private JTextArea infoTextArea;	

	// Overrides	
	@Override
	public void clearTextFields() {
		nameTextField.setText(""); 
		courseIDTextField.setText("");
	}
	
	/**
	 * Create the panel.
	 */
	

	public CoursePanel(Color backgroundColor) {
		// Set the fields variable background to the 
		background = backgroundColor;
		
		// Initate and configure the main JPanel
		coursePanel = new JPanel();
		coursePanel.setLayout(new GridLayout(1, 2, 0, 0));
		coursePanel.setBackground(background);
		
		
		// Make and configure container for the form and information display (half the window, left side)
		JPanel formAndInfoPanel = new JPanel();
		formAndInfoPanel.setLayout(new GridLayout(2, 1, 0, 0));
		formAndInfoPanel.setBackground(background);
		coursePanel.add(formAndInfoPanel);
		
		JPanel formPanel = getForm();
		formAndInfoPanel.add(formPanel);
		
		
		// Make and configure information JPanel
		GridBagLayout infoPanelLayout = new GridBagLayout();
//		infoPanelLayout.columnWeights = new double[]{1.0};
		
		JPanel infoPanel = new JPanel(infoPanelLayout);
		infoPanel.setBorder(new EmptyBorder(10,10,10,10));
		infoPanel.setBackground(background);
		formAndInfoPanel.add(infoPanel);
		
		// Make a header for the Information display.
		addHeaderTextPane(
				new Font("Consolas", Font.BOLD, 25), "Course", 
				background, infoPanel
		);
						
		// Initiate and configure the information JTextArea		
		infoTextArea = addTextArea(
				"Course ID:\n\n\tName:\n\n\tDepartment:\n\n\tLecturer:\n", 
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
				//editObject();
			}	
		});
		buttonsPanel.add(editButton);
		
		// Make and configure delete button
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteObject(displayList);
				infoTextArea.setText("\n\tCourse ID:\n\n\tName:\n\n\tDepartment:\n\n\tLecturer:\n");
			}	
		});
		buttonsPanel.add(deleteButton);
				
		// Make and configure displayListPanel
		JPanel displayListPanel = new JPanel();
		displayListPanel.setBackground(background);
		displayListPanel.setLayout(new GridBagLayout());
		displayListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		coursePanel.add(displayListPanel);

		JPanel sortingPanel = getSortingPanel();
		GridBagConstraints sortingGbc = new GridBagConstraints();
		sortingGbc.weightx = 1;
		sortingGbc.weighty = 1;
		displayListPanel.add(sortingPanel, sortingGbc);
		
		
		// Initiate and configure display JList
		displayList = addDisplayList(null, infoTextArea, displayListPanel);
		updateDisplayList(Course.getCourses(), displayList);
		
	}
	
	@Override
	public JPanel getUI() {
		return coursePanel;
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
						new Font("Consolas", Font.BOLD, 25), "Register Course", 
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
				addLabels("Name: ", 0, 0, new Insets(25, 5, 5, 15), formContainer);
				addLabels("Course ID: ", 0, 1, new Insets(5, 5, 5, 15), formContainer);
				addLabels("Department: ", 0, 2, new Insets(5, 5, 5, 15), formContainer);

				// Initiate and configure TextFields 	
				nameTextField = addTextFields(1, 0, new Insets(25, 90, 0, 5), formContainer);
				courseIDTextField = addTextFields(1, 1, new Insets(5, 90, 0, 5), formContainer);
				
				// Initiate and configure ComboBoxes
				departmentComboBox = addComboBox(1, 2, new Insets(5, 90, 0, 5), formContainer, true);
				updateComboBox(Department.getDepartments(), departmentComboBox);
					
				// Make and configure register button
				JButton registerButton = addRegisterButton(GridBagConstraints.NORTH, 0, 3, formContainer);
				registerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = nameTextField.getText();
						String courseID = courseIDTextField.getText();
						Department department = (Department) departmentComboBox.getSelectedItem();
						
						if(name.length() > 5 && courseID.length() >= 2) {
							new Course(name, courseID, department);
							updateDisplayList(Course.getCourses(), displayList);
						} else {
							System.out.println("FAILED TO REGISTER");
						}
						clearTextFields();
					}

					
				});
		return formPanel;
	}
}
