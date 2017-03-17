package model.interfaces;

import java.util.ArrayList;

public interface PrintableObject extends Comparable<PrintableObject>{
	String getFormatedPrint();
	String getID();
	String getCSV();
	String getAllValuesListed();
	ArrayList<? extends PrintableObject> getList();
}
