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
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.administration.Department;
import model.hr.AcademicEmployee;
import model.hr.Person;
import model.hr.Student;
import model.hr.StudentAssistant;
import model.interfaces.PrintableObject;

public class PersonPanel extends RegisterPanel {

	// Fields
	private JPanel personPanel;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneNumTextField;
	private JTextArea infoTextArea;	
	private JComboBox<String> typeOfPersonComboBox;
	private JComboBox<PrintableObject> departmentComboBox;
	private JList<PrintableObject> displayList;
	private Color background;
	private JTextPane infoHeaderTextPane;

	// Methods
	


	
	// Constructor
	public PersonPanel(Color backgroundColor) {
		// Set the fields variable background to the 
				background = backgroundColor;
				
				// Initate and configure the main JPanel
				personPanel = new JPanel();
				personPanel.setLayout(new GridLayout(1, 2, 0, 0));
				personPanel.setBackground(background);
				
				
				// Make and configure container for the form and information display (half the window, left side)
				JPanel formAndInfoPanel = new JPanel();
				formAndInfoPanel.setLayout(new GridLayout(2, 1, 0, 0));
				formAndInfoPanel.setBackground(background);
				personPanel.add(formAndInfoPanel);
				
				JPanel formPanel = getForm();
				formAndInfoPanel.add(formPanel);
				// Make and configure form Panel
				
				
				// Make and configure information JPanel
				JPanel infoPanel = new JPanel(new GridBagLayout());
				infoPanel.setBorder(new EmptyBorder(10,10,10,10));
				infoPanel.setBackground(background);
				formAndInfoPanel.add(infoPanel);
				
				infoHeaderTextPane = addHeaderTextPane(
						new Font("Consolas", Font.BOLD, 25), "Person", 
						background, infoPanel
				);
								
				// Initiate and configure the information JTextArea		
				infoTextArea = addTextArea(
						"Person ID:\n\n\tName:\n\n\tPhone:\n\n\tDepartment:\n\n\tCourses:\n", 
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
						infoTextArea.setText("\n\tPerson ID:\n\n\tName:\n\n\tPhone:\n\n\tDepartment:\n\n\tCourses:\n");
					}	
				});
				buttonsPanel.add(deleteButton);
						
				// Make and configure displayListPanel
				JPanel displayListPanel = new JPanel();
				displayListPanel.setBackground(background);
				displayListPanel.setLayout(new GridBagLayout());
				displayListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				personPanel.add(displayListPanel);

				
				addHeaderTextPane(new Font("Consolas", Font.BOLD, 25), "People in system", background, displayListPanel);
				
				// Initiate and configure display JList
				displayList = addDisplayList(infoHeaderTextPane, infoTextArea, displayListPanel);
				updateDisplayList(Person.getPersons(), displayList);
				

				JPanel sortingPanel = getSortingPanel();
				GridBagConstraints sortingGbc = new GridBagConstraints();
				sortingGbc.anchor = GridBagConstraints.LINE_START;
				sortingGbc.fill = GridBagConstraints.BOTH;
				sortingGbc.gridy = 2;
				sortingGbc.weightx = 1;
				sortingGbc.weighty = 1;
				displayListPanel.add(sortingPanel, sortingGbc);
				
	}
	
	// Overrides
	@Override
	public void clearTextFields() {
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		phoneNumTextField.setText("");
		typeOfPersonComboBox.setSelectedIndex(0);
	}
	@Override
	public JPanel getUI() {
		return personPanel;
	}
	
	@Override
	public JPanel getSortingPanel() {
		// Initiate and configure sorting panel
	
		JPanel sortingPanel = new JPanel(new GridBagLayout());
		sortingPanel.setBackground(background);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 6);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JLabel sortByLbl = new JLabel("Sort by: ");
		sortingPanel.add(sortByLbl, gbc);
		
		
		JComboBox<String> sortByComboBox = new JComboBox<>();
		sortByComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Choose..", "Person ID", "First name", "Last Name", "Department"}));
		gbc.gridx = 1;
		sortingPanel.add(sortByComboBox, gbc);
		
		JLabel sortOrderLbl = new JLabel(" Order: ");
		gbc.gridx = 2;
		sortingPanel.add(sortOrderLbl, gbc);
		
		JComboBox<String> sortOrderComboBox = new JComboBox<>();
		sortOrderComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Choose..", "Ascending", "Descending" }));
		gbc.gridx = 3;
		sortingPanel.add(sortOrderComboBox, gbc);
		
		JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sortBy = sortByComboBox.getSelectedIndex();
				int sortOrder = sortOrderComboBox.getSelectedIndex();
				if(sortBy != 0 && sortOrder != 0) {
					System.out.println(sortBy + " " + sortOrder);
					
					switch(sortBy) {
					case 1:   // Person ID
						sortById(Person.getPersons(), sortOrder);
						break;
					case 2:   // First Name
						sortByFirstName(sortOrder);
						break;
					case 3:   // Last Name
						sortByLastName(sortOrder);
						break;
					case 4:   // Department
						sortByDepartment(sortOrder);
						break;						
					}
					updateDisplayList(Person.getPersons(), displayList);			
				}
			}
		});
		gbc.gridx = 4;
		sortingPanel.add(sortButton, gbc);

		return sortingPanel; 
	}
	
	
	@Override
	public JPanel getForm() {
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(background);
		
		
		// Make a header for the Registration form.
		addHeaderTextPane(
				new Font("Consolas", Font.BOLD, 25), "Register Person", 
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
		addLabels("First Name: ", 0, 0, new Insets(25, 5, 5, 15), formContainer);
		addLabels("Last Name: ", 0, 1, new Insets(5, 5, 5, 15), formContainer);
		addLabels("Phone: ", 0, 2, new Insets(5, 5, 5, 15), formContainer);
		addLabels("Department: ", 0, 3, new Insets(5, 5, 5, 15), formContainer);
		addLabels("Category: ", 0, 4, new Insets(5, 5, 5, 15), formContainer);

		// Initiate and configure TextFields 	
		firstNameTextField = addTextFields(1, 0, new Insets(25, 90, 0, 5), formContainer);
		lastNameTextField = addTextFields(1, 1, new Insets(5, 90, 0, 5), formContainer);
		phoneNumTextField = addTextFields(1, 2, new Insets(5, 90, 0, 5), formContainer);
		
		// Initiate and configure ComboBoxes
		departmentComboBox = addComboBox(1, 3, new Insets(5, 90, 0, 5), formContainer, true);
		updateComboBox(Department.getDepartments(), departmentComboBox);
		
		typeOfPersonComboBox = new JComboBox<String>();
		typeOfPersonComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Choose..", "Academic Employee", "Administrative Employee", "Student", "Student Assistant"}));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(5, 90, 0, 5);
		gbc.weightx = 0;
		formContainer.add(typeOfPersonComboBox, gbc);
			
		// Make and configure register button
		JButton registerButton = addRegisterButton(GridBagConstraints.NORTH, 0, 5, formContainer);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String phone = phoneNumTextField.getText();
				String typeOf = ((String)typeOfPersonComboBox.getSelectedItem()).toLowerCase();
				Department department = (Department) departmentComboBox.getSelectedItem();
				
				if(firstName.length() >= 2 && lastName.length() >= 2 && phone.length() == 8 && typeOf != "choose..") {
					switch (typeOf) {
					case "student":
						new Student(firstName, lastName, phone, department);
						break;
						
					case "student assistant":
						new StudentAssistant(firstName, lastName, phone, department);
						break;
						
					case "academic employee":
						new AcademicEmployee(firstName, lastName, phone, department);
						break;
						
					case "administrative employee":
						new Student(firstName, lastName, phone, department);
						break;	
					}							
				} else {
					System.out.println("FAILED TO REGISTER");
				}
				clearTextFields();
				updateDisplayList(Person.getPersons(), displayList);
			}
		});
		return formPanel;
	}
	
	
	// SORTING
	
	
	
	public void sortByFirstName(int sortOrder) {
		Collections.sort(Person.getPersons(), new Comparator<Person>() {
			
			@Override
			public int compare(Person firstPerson, Person otherPerson) {
				
				int compareValue = 0;
				if(sortOrder == ASCENDING) {
					compareValue = firstPerson.getFirstName().compareTo(otherPerson.getFirstName());
				} else if(sortOrder == DESCENDING) {
					compareValue = otherPerson.getFirstName().compareTo(firstPerson.getFirstName());
				}
				return compareValue;	
			}
		});
	}
	public void sortByLastName(int sortOrder) {
		Collections.sort(Person.getPersons(), new Comparator<Person>() {
			
			@Override
			public int compare(Person firstPerson, Person otherPerson) {
				
				int compareValue = 0;
				if(sortOrder == ASCENDING) {
					compareValue = firstPerson.getLastName().compareTo(otherPerson.getLastName());
				} else if(sortOrder == DESCENDING) {
					compareValue = otherPerson.getLastName().compareTo(firstPerson.getLastName());
				}
				return compareValue;	
			}
		});
	}
	public void sortByDepartment(int sortOrder) {
		Collections.sort(Person.getPersons(), new Comparator<Person>() {
			
			@Override
			public int compare(Person firstPerson, Person otherPerson) {
				
				int compareValue = 0;
				if(sortOrder == ASCENDING) {
					compareValue = firstPerson.getDepartment().getName().compareTo(otherPerson.getDepartment().getName());
				} else if(sortOrder == DESCENDING) {
					compareValue = otherPerson.getDepartment().getName().compareTo(firstPerson.getDepartment().getName());
				}
				return compareValue;	
			}
		});
	}
}
