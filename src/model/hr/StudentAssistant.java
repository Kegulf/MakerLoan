package model.hr;

import model.administration.Department;

public class StudentAssistant extends Student {
	// Constructor
	public StudentAssistant(String firstName, String lastName, String phone, Department department) {
		super(firstName, lastName, phone, department);
		this.setClassType("studentassistant");
	}
}
