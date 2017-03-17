package model.hr;

import java.util.ArrayList;

import model.administration.Department;
import model.interfaces.PrintableObject;

public abstract class Person implements PrintableObject {

	// Fields
	private static ArrayList<Person> persons = new ArrayList<>();
	private String firstName;
	private String lastName;
	private String phone;
	private Department department;
	private String classType;
		
	// Constructor
	public Person(String firstName, String lastName, String phone, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;		
		this.department = department;
		this.phone = (phone == "1") ? formatPhoneNumber(getRandomPhoneNumber()) : formatPhoneNumber(phone);
		persons.add(this);
	}

	// Methods
	
	/**
	 * Returns the next ID in the chain based on if the paramenter person is an instance of a Student or an Employee. <br>
	 * Examples: <br>
	 * <br>
	 * newID(student, hex)  returns "S000AE0" <br>
	 * newID(employee, hex) returns "E000AE0" <br> 
	 * @param person	the Person object for subClass reference.
	 * @param hex	a hexCounter that differs based on subClass type.
	 * @return A String representation of the next ID in the chain.  
	 */
	public static String newID(Person person, String hex) {	
		Long newID = Long.parseLong(hex, 16);
		String finalID = "";
		hex = Long.toHexString(++newID).toUpperCase();
		while(hex.length() < 6) {
			hex = 0 + hex;
		}
		if(hex.length() == 6) {
			if(person instanceof Student){
				Student.hexCounter = hex;
				finalID = "S" + hex;
			} else if (person instanceof Employee) {
				Employee.hexCounter = hex;
				finalID = "E" + hex;
			}
		} 
		return finalID;
	}
		
	public static String getRandomPhoneNumber() {
		String phoneNumber = (Math.random() < 0.35) ? "4" : "9";
		for(int i = 0; i < 7; i++) {
			phoneNumber += Integer.toString((int)(Math.floor(Math.random() * 10)));
		} return phoneNumber;
	}
	public static String formatPhoneNumber(String phoneNumber) {
		String number = "+47 ";
		char[] phoneChars = phoneNumber.toCharArray();
		for(int i = 0; i < phoneChars.length; i++) {
			number += phoneChars[i];
			if (i == 2 || i == 4) 
				number += " ";
		}	
		return number;
	}
	
	
	
	// Getters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public Department getDepartment() {
		return department;
	}
	public String getClassType() {
		return classType;
	}
	public static ArrayList<Person> getPersons() {
		return persons;
	}
	
	// Setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}

	// Overrides
	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}
	@Override
	public String getCSV() {
		return firstName + ";" + lastName + ";" + phone + ";" + department.getCode() + ";" + classType;
	}
	@Override
	public String getAllValuesListed() {
		return "\n\tPerson ID:\t\t" + this.getID()
			 + "\n\n\tName:\t\t\t" + toString()
			 + "\n\n\tPhone:\t\t\t" + phone
			 + "\n\n\tDepartment:\t" + department;
	}
	@Override
	public String getFormatedPrint() {
		return "Department: [" + department.getCode() 
			 + "], Name: [" + toString() +"]";
	}
	@Override
	public ArrayList<? extends PrintableObject> getList() {
		return persons;
	}
	@Override
	public int compareTo(PrintableObject otherPerson) {
		return this.getID().compareTo(otherPerson.getID());
	}
}
