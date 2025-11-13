package ex2;

import java.util.ArrayList;
import java.util.Arrays;

public class IntercityTaxi extends Taxi {
	private String[] citiesArray;
	private double extraPrice;
	private int maxHours;
	
	public IntercityTaxi() { // empty constructor
		super();
	} 
	
	public IntercityTaxi(String taxiCode, boolean isTaken, double minPrice, String[] citiesArray, double extraPrice,
			int maxHours) { // full constructor
		super(taxiCode, isTaken, minPrice);
		this.citiesArray = citiesArray;
		this.extraPrice = extraPrice;
		this.maxHours = maxHours;
	}

	// ---------------------------------> getters & setters
	public String[] getCitiesArray() {
		return citiesArray;
	}

	public void setCitiesArray(String[] citiesArray) {
		this.citiesArray = citiesArray;
	}

	public double getExtraPrice() {
		return extraPrice;
	}

	public boolean setExtraPrice(double extraPrice) {
		if(extraPrice > 0) {
			this.extraPrice = extraPrice;
			return true;
		}
		System.out.println("IntercityTaxi extraPrice: Invalid input- price must be more than 0");
		return false;	
	}

	public int getMaxHours() {
		return maxHours;
	}

	public boolean setMaxHours(int maxHours) { 
		if(maxHours>0) {
			this.maxHours = maxHours;
			return true;
		}
		System.out.println("IntercityTaxi maxHours: Invalid input- hour must be more than 0");
		return false;
	}
	
	@Override
	public boolean equals(Taxi other) {
		if(!(other instanceof IntercityTaxi)) // checks that other is IntercityTaxi
			return false;
		IntercityTaxi otherTaxi = (IntercityTaxi)other;
		return super.equals(otherTaxi) && citiesArray.equals(otherTaxi.citiesArray) && 
				extraPrice==otherTaxi.extraPrice && maxHours == otherTaxi.maxHours;
	}
	
	@Override
	public double calculatePrice() {
		return this.getMinPrice() + this.getExtraPrice();
	}
	
	@Override
	public String getTaxiTypeName() {
		return "Intercity";
	}
	
	// ---------------------------------> toString
	@Override
	public String toString() {
		return "IntercityTaxi [citiesArray=" + Arrays.toString(citiesArray) + ", extraPrice=" + extraPrice
				+ ", maxHours=" + maxHours + ", taxiCode=" + taxiCode + ", isTaken=" + isTaken + ", minPrice="
				+ minPrice + "]";
	}		
}
