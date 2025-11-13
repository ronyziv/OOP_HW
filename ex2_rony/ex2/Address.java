package ex2;

public class Address {
	private String city;
    private String street;
    private String houseNumber;
    private String country;
    
	public Address(String city, String street, String houseNumber, String country) { // full constructor
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.country = country;
	}
	
	// ---------------------------------> getters & setters
	public String getCity() {
		return city;
	}
	
	public boolean setCity(String city) {
		if (!city.isEmpty()) { 
			this.city = city;
			return true;
		}
		System.out.println("Can't update city - can't be empty");
		return false;
	}
	
	public String getStreet() {
		return street;
	}
	
	public boolean setStreet(String street) {
		if (!street.isEmpty()) { 
			this.street = street;
			return true;
		}
		System.out.println("Can't update street - can't be empty");
		return false;
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	
	public boolean setHouseNumber(String houseNumber) {
		if (!houseNumber.isEmpty()) { 
			this.houseNumber = houseNumber;
			return true;
		}
		System.out.println("Can't update House number - can't be empty");
		return false;
	}
	
	public String getCountry() {
		return country;
	}
	
	public boolean setCountry(String country) {
		if (!country.isEmpty()) {
			this.country = country;
			return true;
		}
		System.out.println("Can't update country - can't be empty");
		return false;
	}
	
	public boolean isValid() {
		return !city.isEmpty() && !street.isEmpty() && !houseNumber.isEmpty() && !country.isEmpty();
	}
	
	public boolean equals(Address other) {
		return city.equals(other.city) && street.equals(other.street) && 
				houseNumber.equals(other.houseNumber) && country.equals(other.country);
	}
	
    // ---------------------------------> toString
	public String toString() {
		return "Address: city=" + city + ", street=" + street + ", houseNumber=" + houseNumber + ", country="
				+ country;
	}

}