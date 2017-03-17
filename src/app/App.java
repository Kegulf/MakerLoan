package app;

import java.awt.Color;
import java.awt.EventQueue;

import gui.MainFrame;
import util.Initializer;

public class App {

	public static void main(String[] args) {
		initialize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(Color.lightGray); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}


	// Fields

	// Constructor

	// Additional Constructors

	// Methods
	private static void initialize() {
		Initializer.createDepartments();
		Initializer.createCourses();
		Initializer.createPeople();
		Initializer.createItems();
		Initializer.createLoans();
		Initializer.addHeadOfCourses();
		Initializer.createThisManyStudents(0);	
	}
	// Getters and Setters

	// Overrides

}
