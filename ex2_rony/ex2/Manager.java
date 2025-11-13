package ex2;

import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
	protected String managerID;
	protected String managerFirstName;
	protected String managerLastName;
	protected Address managerAddress;
	protected String managerPhone;	
	protected ArrayList<Taxi> taxies;
	protected ArrayList<Order> orders;
	
	public Manager() {} // empty constructor
	
	public Manager(String managerID, String managerFirstName, String managerLastName, Address managerAddress,
			String managerPhone, ArrayList<Taxi> taxies, ArrayList<Order> orders) { // full constructor 
		this.managerID = managerID;
		this.managerFirstName = managerFirstName;
		this.managerLastName = managerLastName;
		this.managerAddress = managerAddress;
		this.managerPhone = managerPhone;
		this.taxies = taxies;
		this.orders = orders;
	}

	// ---------------------------------> getters & setters
	public String getManagerID() {
		return managerID;
	}

	public boolean setManagerID(String managerID) {
		boolean isAllDigits = true;
		if(managerID.length()==9) {
			 for(int i=0; i<managerID.length(); i++) {
		            if(!Character.isDigit(managerID.charAt(i))) {
		            	isAllDigits = false; 
		            	break;
		            }
			 }
			 if(isAllDigits) {
				 this.managerID = managerID;
				 return true;
			 }
		}
		System.out.println("managerID: Invalid input - ID must be 9 digits.");
		return false;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public boolean setManagerFirstName(String managerFirstName) {
		if(isOnlyLetters(managerFirstName) && !managerFirstName.isEmpty()) {
			this.managerFirstName = managerFirstName;
			return true;
		}
		System.out.println("managerFirstName: Can't update manager first name - needs to be only letters and not empty.");
		return false;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public boolean setManagerLastName(String managerLastName) {
		if(isOnlyLetters(managerLastName) && !managerLastName.isEmpty()) {
			this.managerLastName = managerLastName;
			return true;
		}
		System.out.println("managerLastName: Can't update manager last name - needs to be only letters and not empty.");
		return false;
	}

	public Address getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(Address managerAddress) {
		this.managerAddress = managerAddress;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public boolean setManagerPhone(String managerPhone) {
		boolean onlyDigits = true;
		if(managerPhone.length()==10) {
			 for(int i=0; i<managerPhone.length(); i++) {
		            if (!Character.isDigit(managerPhone.charAt(i))) {
		            	onlyDigits = false; 
		            	break;
		            }
			 }
			 if(onlyDigits) {
				 this.managerPhone = managerPhone;
				 return true;
			 }
		}
		System.out.println("managerPhone: Can't update manager phone - must be 10 digits.");
		return false;
	}

	public ArrayList<Taxi> getTaxies() {
		return taxies;
	}

	public void setTaxies(ArrayList<Taxi> taxies) {
		this.taxies = taxies;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public boolean equals(Manager other) {
		return managerID.equals(other.managerID) && managerFirstName.equals(other.managerFirstName) && 
				managerLastName.equals(other.managerLastName) && managerAddress.equals(other.managerAddress) &&
				managerPhone.equals(other.managerPhone) && taxies.equals(other.getTaxies()) &&
				orders.equals(other.getOrders());
	}
	
	public String getManagerType() {
		return "Regular";
	}
	
	private boolean isOnlyLetters(String checkedString) {
		for(int i=0; i<checkedString.length(); i++) {
			if(!Character.isLetter(checkedString.charAt(i))) 
				return false; 
		}
		 return true;
	}
	
	// ---------------------------------> toString
	public String toString() {
		return "Manager [managerID=" + managerID + ", managerFirstName=" + managerFirstName + ", managerLastName="
				+ managerLastName + ", managerAddress=" + managerAddress.toString() + ", managerPhone=" + managerPhone
				+ ", taxies=" + taxies.toString() + ", orders=" + orders.toString() + "]";
	}
}
