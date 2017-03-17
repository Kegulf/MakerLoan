package model.hr;

import java.util.ArrayList;

import model.administration.Course;
import model.administration.Department;

public class Student extends Person {
	
	// Fields
	static String hexCounter = "0";
	private String studentID;
	private ArrayList<Course> courses;
	
	// Constructor
	public Student(String firstName, String lastName, String phone, Department department) {
		super(firstName, lastName, phone, department);
		this.studentID = newID(this, hexCounter);
		this.courses = new ArrayList<>();
		this.setClassType("student");
	}
 
	// Getters 
	public ArrayList<Course> getCourseList() {
		return courses;
	}
	
	// Overrides
	@Override
	public String toString() {
		return super.toString();
	}
	@Override
	public String getAllValuesListed() {
		String text = super.getAllValuesListed() + "\n\n\tCourses:";
		for(Course course : courses) 
			text += "\n\t\t" + course;
		return text + "\n";	
	}
	@Override
	public String getFormatedPrint() {
		return "ID: [" + studentID + "], " + super.getFormatedPrint();
	}

	@Override
	public String getID() {
		return studentID;
	}
	@Override
	public String getCSV() {
		String csv = studentID + ";" + super.getCSV() + ";";
		for(Course course : courses){
			csv += course.getCourseID() + ";";
		}
		return csv + "\n";
	}
}
