package model.hr;

import java.util.ArrayList;

import model.administration.Course;
import model.administration.Department;

public class AcademicEmployee extends Employee {

	// Fields
	private ArrayList<Course> courses;
	
	// Constructor
	public AcademicEmployee(String firstName, String lastName, String phone, Department department) {
		super(firstName, lastName, phone, department);
		this.courses = new ArrayList<>();
		this.setClassType("academicemployee");
	}

	// Getters and Setters
	public ArrayList<Course> getCourseList() {
		return courses;
	}
	// Overrides
	@Override
	public String getAllValuesListed() {
		String text = super.getAllValuesListed() + "\n\n\tCourses:";
		for(Course course : courses) 
			text += "\n\t\t" + course;
		return text + "\n";	
	}
	@Override
	public String getCSV() {
		String csv = super.getCSV() + ";";
		for(Course course : courses){
			csv += course.getCourseID() + ";";
		}
		return csv + "\n";
	}
}
