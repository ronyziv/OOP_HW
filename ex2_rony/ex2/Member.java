package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Member {
	private String memberCode; 
	private String memberFirstName;
	private String memberLastName;
	private Address memberAddress;
	private String memberPhone;

	public Member() {} // empty constructor

	public Member(String memberCode, String memberFirstName, String memberLastName, Address memberAddress,
			String memberPhone) { // full constructor
		this.memberCode = memberCode;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.memberAddress = memberAddress; 
		this.memberPhone = memberPhone;
	}

	// ---------------------------------> getters & setters
	public String getMemberCode() {
		return memberCode;
	}
	
	public boolean setMemberCode(String memberCode) {
		if(!memberCode.isEmpty()) {
			this.memberCode = memberCode;			
			return true;
		}
		System.out.println("MemberCode: Invalid input- empty string.");
		return false;
	}

	public String getMemberFirstName() {
		return memberFirstName;
	}
	
	public boolean setMemberFirstName(String memberFirstName) {
		if(isOnlyLetters(memberFirstName) && !memberFirstName.isEmpty()) {
			this.memberFirstName = memberFirstName;
			return true;
		}
		System.out.println("memberFirstName: Can't update member first name - can't be empty and can contain only letters.");
		return false;
	}
	
	public String getMemberLastName() {
		return memberLastName;
	}
	
	public boolean setMemberLastName(String memberLastName) {
		if(isOnlyLetters(memberLastName) && !memberLastName.isEmpty()) {
			this.memberLastName = memberLastName;
			return true;
		}
		System.out.println("memberLastName: Can't update member last name - can't be empty and can contain only letters.");
		return false;
	}

	public Address getMemberAddress() {
		return memberAddress;
	}
	
	public void setMemberAddress(Address memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	public String getMemberPhone() {
		return memberPhone;
	}
	
	public boolean setMemberPhone(String memberPhone) {
		boolean onlyDigits = true;
		if(memberPhone.length()==10) {
			 for(int i=0; i<memberPhone.length(); i++) {
		            if (!Character.isDigit(memberPhone.charAt(i))) {
		            	onlyDigits = false; 
		            	break;
		            }
			 }
			 if(onlyDigits) {
				 this.memberPhone = memberPhone;
				 return true;
			 }
		}
		System.out.println("memberPhone: Can't update member phone, should be 10 digits.");
		return false;
	}
	
	public boolean equals(Member other) {
		return memberCode.equals(other.memberCode) && memberFirstName.equals(other.memberFirstName) && 
				memberLastName.equals(other.memberLastName) && memberPhone.equals(other.memberPhone) &&
				memberAddress.equals(other.memberAddress);
	}	

	private boolean isOnlyLetters(String checkedString) {
		for(int i=0; i<checkedString.length(); i++) {
			if(!Character.isLetter(checkedString.charAt(i))) 
				return false; 
		}
		 return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(memberAddress, memberCode, memberFirstName, memberLastName, memberPhone);
	}


	// ---------------------------------> toString
	public String toString() {
		return "[Member: " + memberFirstName + " " + memberLastName + ", phone=" + memberPhone +
	               ", code=" + memberCode + ", " + memberAddress.toString()+"]";
	    }
	
	// -----------------------------------------> create Member
		// Function description: Creates a new Member based on user selection. 
		// Returns the newly created Member.
		public static Member createMember(ArrayList<Member> members, String[] cityArray) {
			Scanner in = new Scanner(System.in);
			boolean isValid;
			Member member = new Member();
			do {
				System.out.println("Enter Member code: ");
				String memberCode = in.nextLine();
				isValid = member.setMemberCode(memberCode);
				if(isValid && isMemberCodeExists(members, memberCode)) {
					System.out.println("Member code already exists, try again.");
					isValid=false;
				}
			} while (!isValid);	    
			do {
				System.out.println("Enter Member First Name: ");
				String firstName = in.nextLine();
				isValid = member.setMemberFirstName(firstName);
			} while (!isValid);
			do {
				System.out.println("Enter Member Last Name: ");
				String lastName = in.nextLine();
				isValid = member.setMemberLastName(lastName);
			} while (!isValid);
			do {
				System.out.println("Enter Member Phone: ");
				String phone = in.nextLine();
				isValid = member.setMemberPhone(phone);
			} while (!isValid);
			System.out.println("Create member Address: ");
		    Address address = createAddress(cityArray);
			member.setMemberAddress(address);
			return member;
		}
	
	// -----------------------------------------> create Member
			// Function description: Creates a new Member based on user selection - and a given memberCode. 
			// Returns the newly created Member.
		public static Member createMember(String memberCode) {
			String[] cityArray = {"Netanya", "Haifa", "Ramat Gan", "Jerusalem", "Pardes Hanna",
				    "Tel Aviv", "Eilat", "Beer Sheva", "Zikhron Yaakov", "Kfar Saba",
				    "Ashdod", "Herzliya"};
			Scanner in = new Scanner(System.in);
			boolean isValid;
			Member member = new Member();
			member.setMemberCode(memberCode);	    
			do {
				System.out.println("Enter Member First Name: ");
				String firstName = in.nextLine();
				isValid = member.setMemberFirstName(firstName);
			} while (!isValid);
			do {
				System.out.println("Enter Member Last Name: ");
				String lastName = in.nextLine();
				isValid = member.setMemberLastName(lastName);
			} while (!isValid);
			do {
				System.out.println("Enter Member Phone: ");
				String phone = in.nextLine();
				isValid = member.setMemberPhone(phone);
			} while (!isValid);
			System.out.println("Create member Address: ");
		    Address address = createAddress(cityArray);
			member.setMemberAddress(address);
			return member;
		}
	
		// -----------------------------------------> isMemberCodeExists
		// Function description: Checks whether a given member code exists in the members array.
		// Returns true if found, false otherwise.
		public static boolean isMemberCodeExists(ArrayList<Member> members, String membercode) {
			for(Member m: members) {
				if(m.getMemberCode().equals(membercode))
					return true;
			}
			return false;
		}
		
	// -----------------------------------------> createAddress
	// Function description: Creates a new address based on user selection.
	// Returns the newly created Address.
	private static Address createAddress(String[] cityArray) {
		Scanner in = new Scanner(System.in);
		System.out.println("Cities list: ");
	    System.out.print(Arrays.toString(cityArray));
		String city, street, houseNumber, country;
		boolean isCityValid= false;
		boolean addressValid = false;
		do {
			System.out.println("Enter a city from the list: ");
			city = in.nextLine();
			for(int i=0; i<cityArray.length; i++) {
				if(cityArray[i].equals(city)) {
					isCityValid=true;
					break;
				}
			}
		} 
		while (!isCityValid);  
		do {
			System.out.println("Enter Street Name: ");
			street =  in.nextLine();
			System.out.println("Enter houseNumber: ");
			houseNumber =  in.nextLine();
			System.out.println("Enter country: ");
			country =  in.nextLine();
			addressValid = !street.isEmpty() && !houseNumber.isEmpty() && !country.isEmpty();
			if(!addressValid)
				System.out.println("Fields can't be empty, please try again.");
			}
		while(!addressValid);
		return new Address(city, street, houseNumber, country);
	}
	
	
}