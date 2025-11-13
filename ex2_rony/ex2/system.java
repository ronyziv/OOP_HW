package ex2;

import java.util.ArrayList;
import java.util.Arrays;

public class system {
	protected ArrayList<Member> members;
	protected ArrayList<Manager> managers;
	protected ArrayList<Taxi> taxies;
	protected int membersSize;
	protected int managersSize;
	protected int taxiesSize;
	
	public system(ArrayList<Member> members, ArrayList<Manager> managers, ArrayList<Taxi> taxies) { // full constructor
		this.members = members;
		this.managers = managers;
		this.taxies = taxies;
		this.membersSize = members.size();
		this.managersSize = managers.size();
		this.taxiesSize = taxies.size();
	}
	
	// ---------------------------------> getters & setters
	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
		this.membersSize = members.size();
	}

	public ArrayList<Manager> getManagers() {
		return managers;
	}

	public void setManagers(ArrayList<Manager> managers) {
		this.managers = managers;
		this.managersSize = managers.size();
	}

	public ArrayList<Taxi> getTaxies() {
		return taxies;
	}

	public void setTaxies(ArrayList<Taxi> taxies) {
		this.taxies = taxies;
		this.taxiesSize = taxies.size();
	}

	public int getMembersSize() {
		return membersSize;
	}


	public int getManagersSize() {
		return managersSize;
	}


	public int getTaxiesSize() {
		return taxiesSize;
	}

	
	public boolean isAlreadyMember(Member member) {
		for(Member m: members) {
			if(m.getMemberCode().equals(member.getMemberCode())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAlreadyManager(Manager manager) {
		for(Manager m: managers) {
			if(m.getManagerID() == manager.getManagerID()) {
				return true;
			}
		}
		return false;
	}

	
	public boolean isAlreadyTaxi(Taxi taxi) {
		for(Taxi t: taxies) {
			if(t.getTaxiCode().equals(taxi.getTaxiCode())) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------> toString
	public String toString() {
		return "system [members=" + members.toString() + ", managers=" + managers.toString() + ", taxies="
				+ taxies.toString() + ", membersSize=" + membersSize + ", managersSize=" + managersSize
				+ ", taxiesSize=" + taxiesSize + "]";
	}
}
