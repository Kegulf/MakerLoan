package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import model.interfaces.PrintableObject;
import view.registerview.PersonRegPanel;
import view.registerview.RegisterPanel;

public class Controller {
	
	
	
	// Sorting finals
	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;
	public static final int ID = 1;
	public static final int DEPARTMENT = 2;
	
	// Person finals
	public static final int FIRST_NAME = 3;
	public static final int LAST_NAME = 4;
	
	// Course finals
	public static final int COURSE_NAME = 3;
	
	// Department finals
	public static final int DEPT_NAME = 2;
	
	// Item finals
	public static final int ITEM_NAME = 2;
	
	// Loan finals
	public static final int LOAN_DATE = 1;
	public static final int LOANER_NAME = 2;
	
	
	// Fields
	private RegisterPanel gui;
	
	private Comparator<PrintableObject> comparator;
	private ActionListener registerListener;
	private ActionListener displayListListener;
	
	
	// Main Construcor
	public Controller(RegisterPanel gui) {
		
		if(gui instanceof PersonRegPanel) {
			comparator = new Comparator<PrintableObject>() {
				@Override
				public int compare(PrintableObject person, PrintableObject otherPerson) {	
					return 0;
				}				
			};
			
			registerListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			};
			
			
			
			
		}	
	}
	// Methods

	// Getters

	// Setters

	// Overrides

}
