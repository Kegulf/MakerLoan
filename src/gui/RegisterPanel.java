package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.administration.Course;
import model.administration.Department;
import model.hr.Person;
import model.interfaces.PrintableObject;
import view.JListCellRenderer;

public abstract class RegisterPanel implements DisplayInterface {

	// Fields
	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;
	// Constructor

	// Additional Constructors
	
	// Sorting specific methods@
	public void defaultSort(ArrayList<? extends PrintableObject> list) {
		Collections.sort(list);
	}
	
	public void sortById(ArrayList<? extends PrintableObject> list, int sortOrder) {
		Collections.sort(list, new Comparator<PrintableObject>() {
		
			@Override
			public int compare(PrintableObject thisObject, PrintableObject otherObject) {
				int compareValue = 0;
				if(sortOrder == ASCENDING) {
					compareValue = thisObject.getID().compareTo(otherObject.getID());
				} else if(sortOrder == DESCENDING) {
					compareValue = otherObject.getID().compareTo(thisObject.getID());
				}
				return compareValue;	
			}
		});
	}
	
	public JPanel addSortingPanel(DefaultComboBoxModel<String> model) {
		
		
		return null;
	}
	
	// Methods

	public JComboBox<PrintableObject> addComboBox(int gridX, int gridY, Insets margins, JPanel parent, boolean fill) {

		JComboBox<PrintableObject> comboBox = new JComboBox<>();

		GridBagConstraints gbc = new GridBagConstraints();
		if(fill)
			gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.insets = margins;
		gbc.weightx = 0;
		parent.add(comboBox, gbc);

		return comboBox;

	}

	/**
	 * 
	 * @param parent
	 * @return
	 */
	public JList<PrintableObject> addDisplayList(JTextPane headerPane, JTextArea infoArea, JPanel parent) {

		JList<PrintableObject> list = new JList<>();
		list.setCellRenderer(new JListCellRenderer());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!(list.getSelectedValue() == null)) {
					PrintableObject selected = list.getSelectedValue();
					if (headerPane != null)
						headerPane.setText(selected.getClass().getSimpleName());
					infoArea.setText(selected.getAllValuesListed() + "\n");
					infoArea.setCaretPosition(0);
					clearTextFields();

				} else {
					System.out.println("dat shit is null yo");
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.insets = new Insets(25, 0, 0, 0);
		gbc.weightx = 1;
		gbc.weighty = 8;
		parent.add(scrollPane, gbc);

		return list;
	}

	/**
	 * <code> public JTextPane addHeaderTextPane(Font font, String title, <br>Color backgroundColor, JPanel parent); </code>
	 * <br>
	 * <br>
	 *
	 * @param font
	 * @param title
	 *            {String} String to display as title/header.
	 * @param backgroundColor
	 *            {String} Background color of the
	 * @param parent
	 * @return
	 */
	public JTextPane addHeaderTextPane(Font font, String title, Color backgroundColor, JPanel parent) {
		// Configure
		JTextPane headerTextPane = new JTextPane();
		headerTextPane.setFont(font);
		headerTextPane.setText(title);
		headerTextPane.setEditable(false);
		headerTextPane.setFocusable(false);
		headerTextPane.setBackground(backgroundColor);

		// Center text
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = headerTextPane.getStyledDocument();
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
		parent.add(headerTextPane, gbc);

		return headerTextPane;
	}

	/**
	 * <code>public JLabel addLabels(String text, int gridX, int gridY, Insets margins, JPanel parent)</code>
	 * <br>
	 * <br>
	 * 
	 * @param text
	 * @param gridX
	 * @param gridY
	 * @param margins
	 * @param parent
	 * @return
	 */
	public JLabel addLabels(String text, int gridX, int gridY, Insets margins, JPanel parent) {
		JLabel label = new JLabel(text);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST; // where it anchors itself
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.insets = margins;
		parent.add(label, gbc);

		return label;
	}

	/**
	 * <code>	public JButton addRegisterButton(int anchor, int gridX, int gridY, <br>JPanel parent)</code>
	 * <br>
	 * <br>
	 * Creates a registration button and places it in its parent panel.
	 * 
	 * @param anchor
	 * @param gridX
	 * @param gridY
	 * @param parent
	 * @return
	 */

	public JButton addRegisterButton(int anchor, int gridX, int gridY, JPanel parent) {
		JButton button = new JButton("Register");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = anchor;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.insets = new Insets(15, 5, 5, 5);
		gbc.weighty = 1;
		parent.add(button, gbc);

		return button;
	}
	
	/**
	 * <code>public JTextArea addTextArea(String inputText, JPanel parent)</code>
	 * <br>
	 * <br>
	 * Will probably only be used for information display JTextArea
	 * 
	 * @param inputText
	 *            {String} String to be displayed in JTextArea
	 * @param parent
	 *            {String} JPanel to add JTextArea to.
	 * @return {String} The JTextArea that was created.
	 */
	public JTextArea addTextArea(String inputText, JPanel parent) {
		// Make and configure JTextArea
		JTextArea textArea = new JTextArea("\n\t" + inputText);
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(2);
		textArea.setEditable(false);

		// Configure JScrollPane to make the JTextArea scrollable
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setViewportBorder(new LineBorder(new Color(240, 240, 240)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;
		parent.add(scrollPane, gbc);

		return textArea;
	}

	/**
	 * <code>	public JTextField addTextFields(int gridX, int gridY, Insets margins, JPanel parent)</code>
	 * <br>
	 * <br>
	 * Creates a JTextField and adds it to it's parent panel
	 * 
	 * @param gridX
	 *            The row to place the JTextField in.
	 * @param gridY
	 *            The column to place the JTextField in.
	 * @param margins
	 *            The JTextFields margins.
	 * @param parent
	 *            JPanel to add JTextField to.
	 * @return The JTextField that was created.
	 */
	public JTextField addTextFields(int gridX, int gridY, Insets margins, JPanel parent) {
		// TextField for department name
		JTextField textField = new JTextField(23);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.insets = margins;
		gbc.weightx = 0;
		parent.add(textField, gbc);

		return textField;
	}

	public void updateDisplayList(ArrayList<? extends PrintableObject> list, JList<PrintableObject> displayList) {
		DefaultListModel<PrintableObject> model = new DefaultListModel<>();
		for (PrintableObject object : list)
			model.addElement(object);
		displayList.setModel(model);
	}

	public void updateComboBox(ArrayList<? extends PrintableObject> list, JComboBox<PrintableObject> comboBox) {
		DefaultComboBoxModel<PrintableObject> model = new DefaultComboBoxModel<>();
		for (PrintableObject object : list)
			model.addElement(object);
		comboBox.setModel(model);
	}

	// Getters and Setters

	// Overrides
	public void deleteObject(JList<PrintableObject> displayList) {
		PrintableObject selected = displayList.getSelectedValue();
		System.out.println(selected);
		if (selected != null) {

			String message = "Are you sure you want to delete \"" + selected + "\"?\nThis will permanently delete ";

			if (selected instanceof Person) {
				message += "this person";
			} else if (selected instanceof Department) {
				message += "this department";
			} else if (selected instanceof Course) {
				message += "this course";
			}

			message += " from the system!";

			int delete = JOptionPane.showConfirmDialog(null, // HAHAHA
					message, // THE
					"Delete department", // RAINBOW
					JOptionPane.OK_CANCEL_OPTION, // IS
					JOptionPane.WARNING_MESSAGE // REAL
			); // :) <3
			System.out.println(delete);
			if (delete != 2)
				selected.getList().remove(selected);
			updateDisplayList(selected.getList(), displayList);
		}
	}
}
