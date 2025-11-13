package ex2;

import java.util.ArrayList;
import java.util.Scanner;

public class Taxi {
	protected String taxiCode; 
	protected boolean isTaken; 
	protected double minPrice; 

	public Taxi() {}// empty constructor

	public Taxi(String taxiCode, boolean isTaken, double minPrice) { // full constructor
		this.taxiCode = taxiCode;
		this.isTaken = isTaken;
		this.minPrice = minPrice;
	}

	// ---------------------------------> getters & setters
	public String getTaxiCode() {
		return taxiCode;
	}

	public boolean setTaxiCode(String taxiCode) {
		if(!taxiCode.isEmpty()) {
			this.taxiCode = taxiCode;
			return true;
		} 
		System.out.println("taxiCode: Invalid input- empty string.");
		return false;

	}

	public boolean getIsTaken() {
		return isTaken;
	}

	public void setIsTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public boolean setMinPrice(double minPrice) {
		if(minPrice>0) {
			this.minPrice = minPrice;
			return true;
		}
		System.out.println("TaxiMinPrice: Invalid input- price must be more than 0");
		return false;
	}
	
	public String getTaxiTypeName() {
		return "Regular";
	}
	
	public boolean equals(Taxi other) {
		return taxiCode.equals(other.taxiCode) && isTaken==other.isTaken && minPrice==other.minPrice;
	}
	
	public double calculatePrice() {
		return this.getMinPrice();
	}
	
	// ---------------------------------> toString
	@Override
	public String toString() {
        return "Taxi [Code=" + taxiCode + ", Type=" + getTaxiTypeName() + 
               ", Status=" + (isTaken ? "Taken" : "Available") + 
               ", Min Price=" + minPrice + "]";
    }
	
	
}
