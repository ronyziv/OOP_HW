package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Station {
	private String stationName;
	private ArrayList<Taxi> taxies;
	
	
	public Station(String stationName, ArrayList<Taxi> taxies) { // full constructor 
		this.stationName = stationName;
		this.taxies = taxies;
	}
	
	public Station() {} // empty constructor 
	
	
	// ---------------------------------> getters & setters
	public String getStationName() {
		return stationName;
	}

	public boolean setStationName(String stationName) {
		if(!stationName.isEmpty()) {
			this.stationName = stationName;
			return true;
		}
		System.out.println("station name: Can't update station name - can't be empty string.");
		return false;
	}

	public ArrayList<Taxi> getTaxies() {
		return taxies;
	}

	public void setTaxies(ArrayList<Taxi> taxies) {
		this.taxies = taxies;
	}

	// ---------------------------------> toString
	public String toString() {
		return "Station [stationName=" + stationName + ", taxies=" + taxies + "]";
	}

	// ---------------------------------> addTaxiToStation
	public static boolean addTaxi(Station station, Taxi taxi) {
		if(taxi == null) {
			System.out.println("taxi is null");
			return false;
		}
		for(Taxi t: station.getTaxies()) {
			if(t.equals(taxi)) {
				System.out.println("Eror- already in the list.");
				return false;
			}
		}
		station.getTaxies().add(taxi);
		System.out.println("Added successfully.");
		return true;
	}
	
	// ---------------------------------> removeTaxiFromStation
	public static void removeTaxiFromStation(Station station, Taxi t) {
        for(Taxi taxi: station.getTaxies()) {
        	if (taxi.equals(t))
        		station.getTaxies().remove(t);
        }
    }
    
	
}
