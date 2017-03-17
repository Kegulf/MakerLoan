package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.registerview.CourseRegPanel;
import view.registerview.DepartmentRegPanel;
import view.registerview.ItemRegPanel;
import view.registerview.LoanRegPanel;
import view.registerview.PersonRegPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private Color background = new Color(180, 180, 180);
	
	
	/**
	 * Create the frame.
	 */
	public MainFrame(Color backgroundColor) {
		background = backgroundColor;
		PersonRegPanel personPanel = new PersonRegPanel(background, "Person");
		CourseRegPanel coursePanel = new CourseRegPanel(background, "Course");
		DepartmentRegPanel departmentPanel = new DepartmentRegPanel(background, "Department");
		ItemRegPanel itemPanel = new ItemRegPanel(background, "Item");
		LoanRegPanel loanPanel = new LoanRegPanel(background, "Loan");
	//	CoursePanel coursePanel = new CoursePanel(background);
	//	DepartmentPanel departmentPanel = new DepartmentPanel(background);
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 820, 540);
		setMinimumSize(new Dimension(870, 635));
		setTitle("MakeLoan v3.8");
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		setContentPane(contentPane);		 
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Person", personPanel.getUI());	
		tabbedPane.addTab("Course", coursePanel.getUI());
		tabbedPane.addTab("Department", departmentPanel.getUI());
		tabbedPane.addTab("Item", itemPanel.getUI());
		tabbedPane.addTab("Loan", loanPanel.getUI());
		
		

		contentPane.add(tabbedPane);
		
	}

	// Fields

	// Constructor

	// Additional Constructors

	// Methods

	// Getters and Setters

	// Overrides

}
