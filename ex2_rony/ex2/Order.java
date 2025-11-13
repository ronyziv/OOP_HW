package ex2;

import java.util.Objects;
import java.util.Scanner;

public class Order {
	// To help us manage order codes globally
	private static int OrderCounter = 0;
	private String orderCode;
	private String managerCode; 
	private int day;
	private int month;
	private int hour;
	private String memberCode;
	private Taxi taxi;
	private double orderPrice; 
	
	public Order() {
		// Automatically sets order code using the counter
		this.orderCode = String.valueOf(Order.OrderCounter++);
	}
	
	public Order(String managerCode, int day, int month, int hour, String memberCode, Taxi taxi,
			double orderPrice) { // full constructor
		// Automatically sets order code using the counter
		this.orderCode = String.valueOf(Order.OrderCounter++);
		this.managerCode = managerCode;
		this.day = day;
		this.month = month;
		this.hour = hour;
		this.memberCode = memberCode;
		this.taxi = taxi;
		this.orderPrice = orderPrice;
	}
			
	// ---------------------------------> getters & setters
	public String getOrderCode() {
		return orderCode;
	}

//	We are not using this setter because we want to set order code automatically
/*	
   public boolean setOrderCode(String orderCode) {
		if(!orderCode.isEmpty()) {
			this.orderCode = orderCode;
			return true;
		}
		System.out.println("orderCode: Invalid input- empty string.");
		return false;
	} 
*/

	public String getManagerCode() {
		return managerCode;
	}

	public boolean setManagerCode(String managerCode) {
		if(!managerCode.isEmpty()) {
			this.managerCode = managerCode;
			return true;
		} 
		System.out.println("managerCode: Invalid input- can't be empty string.");
		return false;
	}

	public int getDay() {
		return day;
	}

	public boolean setDay(int day) {
		if(day>0 && day<=31) {
			this.day = day;
		return true;
		}
		System.out.println("Order day: Invalid input- must be between 1 to 31.");
		return false;
	}

	public int getMonth() {
		return month;
	}

	public boolean setMonth(int month) {
		if(month>0 && month<=12) {
			this.month = month;
			return true;
		}
		System.out.println("Order month: Invalid input- must be between 1 to 12.");
		return false;
	}

	public int getHour() {
		return hour;
	}

	public boolean setHour(int hour) {
		if(hour>=0 && hour<24) {
			this.hour = hour;
			return true;
		}
		System.out.println("Order hour: Invalid input- must be between 0 to 23.");
		return false;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public boolean setMemberCode(String memberCode) {
		if(!memberCode.isEmpty()) {
			this.memberCode = memberCode;
			return true;
		}
		System.out.println("member Code: Invalid input- can't be empty string.");
		return false;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public boolean setOrderPrice(double orderPrice) {
		if(orderPrice>0) {
			this.orderPrice = orderPrice;
			return true;
		}
		System.out.println("Order Price: Invalid input- price must be more than 0");
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(day, hour, managerCode, memberCode, month, orderCode, orderPrice, taxi);
	}

	public boolean equals(Order other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		return day == other.day && hour == other.hour && Objects.equals(managerCode, other.managerCode)
				&& Objects.equals(memberCode, other.memberCode) && month == other.month
				&& Objects.equals(orderCode, other.orderCode)
				&& Double.doubleToLongBits(orderPrice) == Double.doubleToLongBits(other.orderPrice)
				&& Objects.equals(taxi, other.taxi);
	}

	// ---------------------------------> toString	
	public String toString() {
		return "orderCode:" + orderCode + ", managerCode:" + managerCode +", day:" + day + ", month:" +month
				+ ", hour:" + hour +", memberCode:" + memberCode +", taxi:" + taxi.toString() +", orderPrice:"+ orderPrice;
	}
	
	
}

