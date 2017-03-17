package model.inventory;

import java.util.ArrayList;

import model.interfaces.PrintableObject;

public class Item implements PrintableObject{
	
	// Fields
	private static ArrayList<Item> items = new ArrayList<>();
	private String name;
	private String itemID;
		
	// Constructor
	public Item(String name, String itemID) {
		this.name = name;
		this.itemID = itemID;
		items.add(this);
	}
	
	// Getters 
	public String getName() {
		return name;
	}
	public String getItemID() {
		return itemID;
	}	
	public static ArrayList<Item> getItems() {
		return items;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	
	// Overrides
	@Override
	public String toString() {
		return itemID + " - " + name;
	}	
	@Override
	public String getFormatedPrint() {
		return "ID: [" + itemID + "], Name: [" + name + "]";
	}
	@Override
	public String getID() {
		return itemID;
	}
	@Override
	public String getCSV() {
		return itemID + ";" + name;
	}

	@Override
	public String getAllValuesListed() {
		return "\n\tItem ID:\t" + itemID
			 + "\n\n\tName:\t" + name; 
	}
	@Override
	public ArrayList<? extends PrintableObject> getList() {
		return items;
	}

	@Override
	public int compareTo(PrintableObject otherItem) {
		return getID().compareTo(otherItem.getID());
	}
}
