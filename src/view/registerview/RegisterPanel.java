package view.registerview;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.interfaces.PrintableObject;
import view.interfaces.Registrable;

public abstract class RegisterPanel implements Registrable {

	// Fields
	private Color background;
	private JPanel contentPane;
	protected JButton registerButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton sortButton;
	private JTextArea infoTextArea;

	protected String infoAreaTemplate;

	private JList<PrintableObject> displayList;
	private JComboBox<String> sortByComboBox;
	private JComboBox<String> sortOrderComboBox;
	private JTextPane infoHeadline;

	// Main Construcor
	public RegisterPanel(Color backgroundColor, String headlines) {
		// Set the fields variable background to the
		background = backgroundColor;

		// Initiate and configure the main JPanel
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		contentPane.setBackground(background);

		if (this instanceof PersonRegPanel)
			this.infoAreaTemplate = "\n\tPerson ID:\n\n\tName:\n\n\tPhone:\n\n\tDepartment:\n\n\tCourses:\n";
		else if (this instanceof CourseRegPanel)
			this.infoAreaTemplate = "\n\tCourse ID:\n\n\tName:\n\n\tDepartment:\n\n\tLecturer:\n";
		else if (this instanceof DepartmentRegPanel)
			this.infoAreaTemplate = "\n\tCode:\n\n\tName:\n\n\tCourses:\n\n\tEmployees:\n";
		else if (this instanceof ItemRegPanel)
			this.infoAreaTemplate = "\n\tItem ID:\n\n\tName:\n";
		else if (this instanceof LoanRegPanel)
			this.infoAreaTemplate = "\n\tLoan ID:\n\n\tItem:\n\n\tLoaner:\n\n\tLoaned at:\n";

		// Make and configure container for the form and information display
		// (half the window, left side)
		JPanel formAndInfoPanel = new JPanel();
		formAndInfoPanel.setLayout(new GridLayout(2, 1, 0, 0));
		formAndInfoPanel.setBackground(background);
		contentPane.add(formAndInfoPanel);

		JPanel fPanel = new JPanel(new GridBagLayout());
		fPanel.setBackground(background);
		formAndInfoPanel.add(fPanel);

		addHeaderTextPane("Register " + headlines, fPanel);

		GridBagConstraints formGbc = new GridBagConstraints();
		formGbc.fill = GridBagConstraints.BOTH;
		formGbc.gridx = 0;
		formGbc.gridy = 1;
		formGbc.weightx = 0;
		formGbc.weighty = 1;
		fPanel.add(getForm(), formGbc);

		// Make and configure information JPanel
		GridBagLayout infoPanelLayout = new GridBagLayout();
		infoPanelLayout.columnWeights = new double[] { 1.0 };

		JPanel infoPanel = new JPanel(infoPanelLayout);
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setBackground(background);
		formAndInfoPanel.add(infoPanel);

		setInfoHeadline(addHeaderTextPane(headlines, infoPanel));

		// Information panel
		JTextArea textArea = new JTextArea("\n\t" + infoAreaTemplate);
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(2);
		textArea.setEditable(false);

		// Configure JScrollPane to make the JTextArea scrollable
		JScrollPane infoScrollPane = new JScrollPane(textArea);
		infoScrollPane.setViewportBorder(new LineBorder(new Color(240, 240, 240)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;
		infoPanel.add(infoScrollPane, gbc);

		// Make and configure the JPanel for the edit and delete buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		gbc = new GridBagConstraints();
		buttonsPanel.setBackground(background);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		gbc.weightx = 1;
		infoPanel.add(buttonsPanel, gbc);

		// Make and configure edit button
		editButton = new JButton("Edit");
		buttonsPanel.add(editButton);

		// Make and configure delete button
		deleteButton = new JButton("Delete");
		buttonsPanel.add(deleteButton);

		// Make and configure displayListPanel
		JPanel displayListPanel = new JPanel();
		displayListPanel.setBackground(background);
		displayListPanel.setLayout(new GridBagLayout());
		displayListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(displayListPanel);

		addHeaderTextPane(headlines + "s in system", displayListPanel);

		JScrollPane displayScrollPane = new JScrollPane(displayList = new JList<>());
		displayScrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.insets = new Insets(15, 0, 0, 0);
		gbc.weightx = 1;
		gbc.weighty = 8;
		displayListPanel.add(displayScrollPane, gbc);

		JPanel sortingPanel = new JPanel(new GridBagLayout());
		sortingPanel.setBackground(background);
		GridBagConstraints displayPanelGbc = new GridBagConstraints();
		displayPanelGbc.anchor = GridBagConstraints.LINE_START;
		displayPanelGbc.fill = GridBagConstraints.BOTH;
		displayPanelGbc.gridy = 2;
		displayPanelGbc.weightx = 1;
		displayPanelGbc.weighty = 1;
		displayListPanel.add(sortingPanel, displayPanelGbc);

		GridBagConstraints sortPanelGbc = new GridBagConstraints();
		sortPanelGbc.anchor = GridBagConstraints.LINE_START;
		sortPanelGbc.insets = new Insets(0, 0, 0, 6);
		sortPanelGbc.gridx = 0;
		sortPanelGbc.gridy = 0;

		JLabel sortByLbl = new JLabel("Sort by: ");
		sortingPanel.add(sortByLbl, sortPanelGbc);

		JComboBox<String> sortByComboBox = new JComboBox<>();
		sortByComboBox.setModel(this.getSortingModel());
		sortPanelGbc.gridx = 1;
		sortingPanel.add(sortByComboBox, sortPanelGbc);

		JLabel sortOrderLbl = new JLabel(" Order: ");
		sortPanelGbc.gridx = 2;
		sortingPanel.add(sortOrderLbl, sortPanelGbc);

		JComboBox<String> sortOrderComboBox = new JComboBox<>();
		sortOrderComboBox
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Choose..", "Ascending", "Descending" }));
		sortPanelGbc.gridx = 3;
		sortingPanel.add(sortOrderComboBox, sortPanelGbc);

		JButton sortButton = new JButton("Sort");
		sortPanelGbc.gridx = 4;
		sortingPanel.add(sortButton, sortPanelGbc);

		// Initiate and configure display JList
		// displayList = addDisplayList(null, infoTextArea, displayListPanel);
		// updateDisplayList(Department.getDepartments(), displayList);

	}

	private JTextPane addHeaderTextPane(String headline, JPanel parent) {
		// Configure
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Consolas", Font.BOLD, 25));
		textPane.setText(headline);
		textPane.setEditable(false);
		textPane.setFocusable(false);
		textPane.setBackground(background);

		// Center text
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = textPane.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		// Configure constraints to be used by parent.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.weightx = 1;
		parent.add(textPane, gbc);

		return textPane;

	}

	public JButton addRegisterButton(int gridY, JPanel parent) {
		JButton button = new JButton("Register");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = gridY;
		gbc.insets = new Insets(15, 5, 5, 5);
		gbc.weighty = 1;
		parent.add(button, gbc);

		return button;
	}
	// Methods

	// Getters
	public JPanel getUI() {
		return contentPane;
	}

	public Color getBackground() {
		return background;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getSortButton() {
		return sortButton;
	}

	public JList<PrintableObject> getDisplayList() {
		return displayList;
	}

	public JTextArea getInfoTextArea() {
		return infoTextArea;
	}

	public JComboBox<String> getSortByComboBox() {
		return sortByComboBox;
	}

	public JComboBox<String> getSortOrderComboBox() {
		return sortOrderComboBox;
	}

	public String getInfoAreaTemplate() {
		return infoAreaTemplate;
	}

	// Setters
	public void setBackground(Color background) {
		this.background = background;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public void setSortButton(JButton sortButton) {
		this.sortButton = sortButton;
	}

	public void setDisplayList(JList<PrintableObject> displayList) {
		this.displayList = displayList;
	}

	public void setInfoTextArea(JTextArea infoTextArea) {
		this.infoTextArea = infoTextArea;
	}

	public void setSortByComboBox(JComboBox<String> sortByComboBox) {
		this.sortByComboBox = sortByComboBox;
	}

	public void setSortOrderComboBox(JComboBox<String> sortOrderComboBox) {
		this.sortOrderComboBox = sortOrderComboBox;
	}

	public void setInfoAreaTemplate(String infoAreaTemplate) {
		this.infoAreaTemplate = infoAreaTemplate;
	}

	public JTextPane getInfoHeadline() {
		return infoHeadline;
	}

	public void setInfoHeadline(JTextPane infoHeadline) {
		this.infoHeadline = infoHeadline;
	}

	// Overrides

}
