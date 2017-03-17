package util;

import model.administration.Course;
import model.administration.Department;
import model.hr.Employee;
import model.hr.Person;
import model.hr.Student;
import model.interfaces.PrintableObject;

public abstract class ArrayHelper {
	
	

	
	public static Person getPersonByFullName(String name){
		
		for(PrintableObject p : Person.getPersons()) {
			Person person = (Person) p;
			String fullName = person.getFirstName() + " " + person.getLastName();
			if(fullName.equals(name)) {
				return person;
			}
		}
		return null;	
	}
	public static Person getPersonById(String id){
		
		for(PrintableObject person : Person.getPersons()) {
			if(person instanceof Student) {
				if(((Student)person).getID().equals(id)) {
					return (Student) person;
				}		
			} else if (person instanceof Employee) {
				if(((Employee)person).getID().equals(id)){
					return (Employee) person;
				}
			}
		} return null;  
	}
	public static Course getCourseByID(String id) {
		
		for(PrintableObject c : Course.getCourses()) {
			Course crs = (Course) c;
			if(crs.getCourseID().equals(id)) {
				return crs;
			}
		} return null;
	}	
	public static Department getDepartmentByID(String id) {
		
		for(PrintableObject d : Department.getDepartments()) {
			Department dpt = (Department) d;
			if(dpt.getCode().equals(id)) {
				return dpt;
			}
		} return null;
	}
	public static Department getDepartmentByName(String name) {

		for(PrintableObject d : Department.getDepartments()) {
			Department dpt = (Department) d;
			if(dpt.getName().equals(name)) {
				return dpt;
			}
		} return null;
	}
}
