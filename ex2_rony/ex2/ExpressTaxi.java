package ex2;

import java.util.Arrays;

public class ExpressTaxi extends Taxi{
	private boolean isCityTaxi;
	private double extraPrice;
	
	public ExpressTaxi() { // empty constructor
		super();
	} 
	
	public ExpressTaxi(String taxiCode, boolean isTaken, double minPrice, boolean isCityTaxi, double extraPrice) { // full constructor
		super(taxiCode, isTaken, minPrice);
		this.isCityTaxi = isCityTaxi;
		this.extraPrice = extraPrice;
	}
	
	// ---------------------------------> getters & setters
	public boolean isCityTaxi() {
		return isCityTaxi;
	}

	public void setCityTaxi(boolean isCityTaxi) {
		this.isCityTaxi = isCityTaxi;
	}

	public double getExtraPrice() {
		return extraPrice;
	}

	public boolean setExtraPrice(double extraPrice) {
		if(extraPrice>0) {
			this.extraPrice = extraPrice;
			return true;
		}
		System.out.println("ExpressTaxi extraPrice: Invalid input- price must be more than 0");
		return false;
	}
	
	@Override
	public boolean equals(Taxi other) {
		if(!(other instanceof ExpressTaxi)) // checks that other is ExpressTaxi
			return false;
		ExpressTaxi otherTaxi = (ExpressTaxi)other;
		return super.equals(otherTaxi) && extraPrice==otherTaxi.extraPrice && isCityTaxi == otherTaxi.isCityTaxi;
	}
	
	@Override
	public double calculatePrice() {
		return this.getMinPrice() + this.getExtraPrice();
	}
	
	@Override
	public String getTaxiTypeName() {
		return "Express";
	}

	// ---------------------------------> toString
	@Override
	public String toString() {
		return "ExpressTaxi [isCityTaxi=" + isCityTaxi + ", extraPrice=" + extraPrice + ", taxiCode=" + taxiCode
				+ ", isTaken=" + isTaken + ", minPrice=" + minPrice + "]";
	}

}

