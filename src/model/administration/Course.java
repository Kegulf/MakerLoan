package model.administration;

import java.util.ArrayList;

import model.hr.AcademicEmployee;
import model.interfaces.PrintableObject;

public class Course implements PrintableObject  {

	// Fields
	private static ArrayList<Course> courses = new ArrayList<>();
	private String name;
	private String courseID;
	private Department department;
	private AcademicEmployee lecturer;
		
	// Constructor
	public Course(String name, String courseID, Department department) {
		super();
		this.name = name;
		this.courseID = courseID;
		this.department = department;
		this.lecturer = null;
		
		courses.add(this);
		department.addCourse(this);
	}
	
	// Getters		
	public String getName() {
		return name;
	}
	public String getCourseID() {
		return courseID;
	}
	public Department getDepartment() {
		return department;
	}
	public AcademicEmployee getLecturer() {
		return lecturer;
	}
	public static ArrayList<Course> getCourses() {
		return courses;
	}
	
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setLecturer(AcademicEmployee lecturer) {
		if(this.lecturer != null) 							// if there currently is a lecturer
			this.lecturer.getCourseList().remove(this);		// remove this course from his/hers courseList
		this.lecturer = lecturer;							// set this courses lecturer to the supplied lecturer
		this.lecturer.getCourseList().add(this);			// add this course to the new lecturers courseList 
	}
	
	// Overrides
	@Override
	public String toString() {
		return courseID + " \t" + name;
	}

	@Override
	public String getFormatedPrint() {
		return "ID: [" + courseID + "], Name: [" + name + "]";
	}

	@Override
	public String getID() {
		return courseID;
	}

	@Override
	public String getCSV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllValuesListed() {
		return "\n\tCourse ID:\t\t" + courseID
			 + "\n\n\tName:\t\t\t" + name
			 + "\n\n\tDepartment:\t" + department
			 + "\n\n\tLecturer:\t\t" + lecturer;
		}

	@Override
	public ArrayList<? extends PrintableObject> getList() {
		return courses;
	}

	@Override
	public int compareTo(PrintableObject otherCourse) {
		
		return this.getID().compareTo(otherCourse.getID());
	}
}
