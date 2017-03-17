package model.inventory;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.hr.Person;
import model.interfaces.PrintableObject;

public class Loan implements PrintableObject{

	// Fields
	private static ArrayList<Loan> loans = new ArrayList<>();
	private static int counter = 1000;
	private String loanID;
	private Item item;
	private Person person;
	private GregorianCalendar loanDate;
	
	// Constructor
	public Loan(Item item, Person person) {
		this.loanID = ++counter + "";
		this.item = item;
		this.person = person;
		this.loanDate = new GregorianCalendar();
		loans.add(this);
	}
	
	// Getters
	public Item getItem() {
		return item;
	}
	public Person getPerson() {
		return person;
	}
	public GregorianCalendar getLoanDate() {
		return loanDate;
	}
	
	/** 
	 * God dammit this method sucks :'(
	 * @return All your loans, motherflopper
	 */
	public static ArrayList<Loan> getLoans() {
		return loans;
	}


	// Setters
	public void setItem(Item item) {
		this.item = item;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	
	// Overrides
	@Override
	public String getFormatedPrint() {
		return null;
	}
	@Override
	public String getID() {
		return loanID;
	}
	@Override
	public String getCSV() {
		return null;
	}

	@Override
	public String getAllValuesListed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<? extends PrintableObject> getList() {
		return loans;
	}

	@Override
	public int compareTo(PrintableObject otherLoan) {
		return this.getID().compareTo(otherLoan.getID());
	}
}
