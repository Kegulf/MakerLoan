package model.hr;

import model.administration.Department;

public abstract class Employee extends Person {

	// Fields
	static String hexCounter = "0";
	private String employeeID;

	// Constructor
	public Employee(String firstName, String lastName, String phone, Department department) {
		super(firstName, lastName, phone, department);
		this.employeeID = newID(this, hexCounter);
		department.addEmployee(this);
	}
	
	// Overrides
	@Override
	public String getID() {
		return employeeID;
	}
	@Override
	public String getFormatedPrint() {
		return "ID: [" + employeeID + "], " + super.getFormatedPrint();
	}
	@Override 
	public String getCSV() {
		return employeeID + ";" + super.getCSV() + ";";
	}
}
