package model.administration;

import java.util.ArrayList;

import model.hr.Employee;
import model.interfaces.PrintableObject;

public class Department implements PrintableObject {

	// Fields
	private static ArrayList<Department> departments = new ArrayList<>();
	private ArrayList<Employee> employees; 
	private ArrayList<Course> courses;
	private String name;
	private String departmentID;
	
	// Constructor
	public Department(String name, String code) {
		this.name = name;
		this.departmentID = code;
		this.employees = new ArrayList<>();
		this.courses = new ArrayList<>();
		departments.add(this);
	}
	
	// Methods
	public void addCourse(Course course) {
		courses.add(course);
	}
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public String getCode() {
		return departmentID;
	}
	public static ArrayList<Department> getDepartments() {
		return departments;
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.departmentID = code;
	}
	
	// Overrides
	@Override
	public String toString() {
		return departmentID + " - " + name;
	}
	@Override
	public String getFormatedPrint() {
		return "Code: [" + departmentID + "], Name: [" + name + "]";
	}
	@Override
	public String getID() {
		return departmentID;
	}

	@Override
	public String getCSV() {
		return name + ";" + departmentID + "\n";
	}

	@Override
	public String getAllValuesListed() {
		String text = "\n\tCode:\t " + departmentID 
					+ "\n\n\tName:\t " + name;
		
		text += "\n\n\tCourses:";
		for(Course course : courses) 
			text += "\n\t\t" + course;
		
		text += "\n\n\tEmployees:";
		for(Employee employee : employees)
			text += "\n\t\t" + employee;
		
		return text;
	}

	@Override
	public ArrayList<? extends PrintableObject> getList() {
		return departments;
	}

	@Override
	public int compareTo(PrintableObject arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
