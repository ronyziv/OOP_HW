package ex2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main_Manager extends Manager {
	private String username;
	private String password;
	
	public Main_Manager() { // empty constructor
		super();
	} 
	
	public Main_Manager(String managerID, String managerFirstName, String managerLastName, Address managerAddress,
			String managerPhone, ArrayList<Taxi> taxies, ArrayList<Order> orders, String username, String password) { // full constructor 
		super(managerID, managerFirstName, managerLastName, managerAddress, managerPhone, taxies, orders);
		this.username = username;
		this.password = password;
	}
	
	public Main_Manager(String managerID, String managerFirstName, String managerLastName, Address managerAddress,
			String managerPhone, ArrayList<Taxi> taxies, ArrayList<Order> orders) { // Manager constructor 
		super(managerID, managerFirstName, managerLastName, managerAddress, managerPhone, taxies, orders);
	}
	
	// ---------------------------------> getters & setters
	public String getUsername() {
		return username;
	}

	public boolean setUsername(String username) {
		if(!username.isEmpty()) {
			this.username = username;
			return true;
		}
		System.out.println("MainManagerUsername: Can't update manager username- can't be empty string.");
		return false;
	}

	public String getPassword() {
		return password;
	}

	public boolean setPassword(String password) {
		if(!password.isEmpty()) {
			this.password = password;
			return true;
		}
		System.out.println("password: Can't update manager password- can't be empty string.");
		return false;
	}
	
	@Override
	public String getManagerType() {
		return "Main";
	}
	
	// ---------------------------------> toString
	@Override
	public String toString() {
		return "Main_Manager [username=" + username + ", password=" + password + ", managerID=" + managerID
				+ ", managerFirstName=" + managerFirstName + ", managerLastName=" + managerLastName
				+ ", managerAddress=" + managerAddress.toString() + ", managerPhone=" + managerPhone + ", taxies="
				+ taxies.toString() + ", orders=" + orders.toString() + "]";
	}
	
				
	
}
