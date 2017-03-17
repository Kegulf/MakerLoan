package util;

import model.administration.Course;
import model.administration.Department;
import model.hr.AcademicEmployee;
import model.hr.AdminEmployee;
import model.hr.Student;
import model.hr.StudentAssistant;
import model.inventory.Item;
import model.inventory.Loan;

public abstract class Initializer {

	public static String getRandomPhoneNumber() {
		String phoneNumber = "+47 ";
		phoneNumber += (Math.random() < 0.35) ? "4" : "9";
		for(int i = 0; i < 7; i++) {
			phoneNumber += Integer.toString((int)(Math.floor(Math.random() * 10)));
			if(i == 1 || i == 3)
				phoneNumber += " ";
		} return phoneNumber;
	}
	public static void createDepartments(){
		new Department("Informasjonsteknologi", "IT");
		new Department("Økonomi, språk og samfunnsfag", "ØSS");
		new Department("Lærerutdanningen", "LU");
	}
	public static void createCourses(){
		new Course("Objektorientert Programmering", "ITF10611", ArrayHelper.getDepartmentByID("IT"));
		new Course("Matematikk 1", "ITD15013", ArrayHelper.getDepartmentByID("IT"));
		new Course("Datateknikk", "ITD13012", ArrayHelper.getDepartmentByID("IT"));
		new Course("Innføring i Programmering", "ITF10213", ArrayHelper.getDepartmentByID("IT"));
		new Course("Innføring i Datasikkerhet", "ITF15015", ArrayHelper.getDepartmentByID("IT"));
	}
	public static void createPeople() {
		
		new AdminEmployee("Dekan", "Dekansen", "1", ArrayHelper.getDepartmentByID("IT"));
		new AdminEmployee("Sekretær", "Sekretærusen", "1", ArrayHelper.getDepartmentByID("IT"));
		new AcademicEmployee("Tom Heine", "Nätt", "1", ArrayHelper.getDepartmentByID("IT"));
		new AcademicEmployee("Christian F", "Heide", "1", ArrayHelper.getDepartmentByID("IT"));
		new AcademicEmployee("Per Olav", "Bisseberg", "1", ArrayHelper.getDepartmentByID("IT"));
		new AcademicEmployee("Robert", "Roppestad", "1", ArrayHelper.getDepartmentByID("IT"));	
		new StudentAssistant("Berzi", "Wasfy", "1", ArrayHelper.getDepartmentByID("IT"));
		new Student("Odd Martin", "Hansen", "1", ArrayHelper.getDepartmentByID("IT"));
		new Student("Per Gunnar", "Steinsvik", "1", ArrayHelper.getDepartmentByID("IT"));
		new Student("Kim Gustav", "Hjertrudsen", "1", ArrayHelper.getDepartmentByID("IT"));
	}
	public static void createItems() {
		new Item("Arduino Kit", "50");
	}
	public static void createLoans() {
		new Loan(Item.getItems().get(0), ArrayHelper.getPersonById("S000002"));
	}
	public static void createThisManyStudents(int num) {
		for(int i = 1; i <= num; i++){
			System.out.println(new Student("Nummer", Integer.toString(i), getRandomPhoneNumber(), ArrayHelper.getDepartmentByID("IT")));
		}
	}
	public static void addHeadOfCourses() {
		
		// Objekt Orientert Programmering
		ArrayHelper.getCourseByID("ITF10611").setLecturer(
				(AcademicEmployee) ArrayHelper.getPersonById("E000005") // Per Bisseberg
			);
		
		// Matematikk 1
		ArrayHelper.getCourseByID("ITD15013").setLecturer(
				(AcademicEmployee) ArrayHelper.getPersonById("E000004") // Christian Heide
			);
		
		// Innf�ring i Programmering
		ArrayHelper.getCourseByID("ITF10213").setLecturer(
				(AcademicEmployee) ArrayHelper.getPersonById("E000003") // Tom Heine
			);
		
		// Innf�ring i Datasikkerhet
		ArrayHelper.getCourseByID("ITF15015").setLecturer(
				(AcademicEmployee) ArrayHelper.getPersonById("E000003") // Tom Heine
			);
		
		// Datateknikk
		ArrayHelper.getCourseByID("ITD13012").setLecturer(
				(AcademicEmployee) ArrayHelper.getPersonById("E000006") // Robert Roppestad
			);
	}

}
