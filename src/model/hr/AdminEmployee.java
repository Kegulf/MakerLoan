package model.hr;

import model.administration.Department;

public class AdminEmployee extends Employee {

	// Constructor
	public AdminEmployee(String firstName, String lastName, String phone, Department department) {
		super(firstName, lastName, phone, department);
		this.setClassType("adminemployee");
	}

	// Overrides
	@Override
	public String getCSV() {
		return super.getCSV() + "\n";
	}
}
