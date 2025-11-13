package ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class SystemDataBase {
	private Main_Manager Administrator;
	private ArrayList<Manager> ManagerList;
	private ArrayList<Taxi> taxiList;
	private ArrayList<Station> stationList;
	private ArrayList<Order> orderList;
	private ArrayList<Member> memberList;
	private Hashtable<String, ArrayList<Taxi>> memberTaxies;
	private HashMap<String, ArrayList<Order>> memberOrders;
	
	public SystemDataBase() { // constructor
		Administrator = new Main_Manager("100000000","Meytal", "Gilad", new Address("Haifa", "Hanamal", "16", "Israel"), "0505945000", new ArrayList<Taxi>(), new ArrayList<Order>() ,"system", "12345");
		ManagerList = new ArrayList<Manager>();
		ManagerList.add((Main_Manager) Administrator);
		taxiList = new ArrayList<Taxi>();
		stationList = new ArrayList<Station>();
		orderList = new ArrayList<Order>();
		memberList = new ArrayList<Member>();
		memberOrders = new HashMap<String,ArrayList<Order>>();
		memberTaxies = new Hashtable<String, ArrayList<Taxi>>();
	}   
	
	// ---------------------------------> getters & setters
	public Main_Manager getAdministrator() {
		return Administrator;
	}

	public void setAdministrator(Main_Manager administrator) {
		Administrator = administrator;
	}

	public ArrayList<Manager> getManagerList() {
		return ManagerList;
	}

	public void setManagerList(ArrayList<Manager> ManagerList) {
		this.ManagerList = ManagerList;
	}

	public ArrayList<Taxi> getTaxiList() {
		return taxiList;
	}

	public void setTaxiList(ArrayList<Taxi> taxiList) {
		this.taxiList = taxiList;
	}

	public ArrayList<Station> getStationList() {
		return stationList;
	}

	public void setStationList(ArrayList<Station> stationList) {
		this.stationList = stationList;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<Member> memberList) {
		this.memberList = memberList;
	}

	public HashMap<String, ArrayList<Order>> getMemberOrders() {
		return memberOrders;
	}

	public void setMemberOrders(HashMap<String, ArrayList<Order>> memberOrders) {
		this.memberOrders = memberOrders;
	}

	public Hashtable<String, ArrayList<Taxi>> getMemberTaxies() {
		return memberTaxies;
	}

	public void setMemberTaxies(Hashtable<String, ArrayList<Taxi>> memberTaxies) {
		this.memberTaxies = memberTaxies;
	}
	
    // ------------------ Generic ------------------
	// -----------------------------------------> add
	public <T> boolean addItem(ArrayList<T> list, T element){
		if(element == null) {
			System.out.println(element+" is null");
			return false;
		}
		for(T t: list) {
			if(t.equals(element)) {
				System.out.println("Eror- already in the list.");
				return false;
			}
		}
		list.add(element);
		return true;
	}
	
	// -----------------------------------------> remove
	public <T> void removeItem(ArrayList<T> list, T element){
    	list.remove(element);
	}
	
	// -----------------------------------------> addOrderToMember
    public boolean addOrderToMember(String memberCode, Order order) {
    	if(order == null) {
			System.out.println("order is null");
			return false;
		}
    	if(!Member.isMemberCodeExists(memberList, memberCode)) {
            System.out.println("Member code not found");
            return false;
        }
		if(memberOrders.get(memberCode)==null) {
			ArrayList<Order> newList =  new ArrayList<Order>();
			newList.add(order);
    		memberOrders.put(memberCode, newList);
		} else if(memberOrders.get(memberCode).contains(order)) {
			System.out.println("Eror- order already in the list.");
			return false;
		} else
			memberOrders.get(memberCode).add(order);
		System.out.println("Added order to member successfully.");
		return true;
	}
    
    // -----------------------------------------> addTaxiToMember
    public boolean addTaxiToMember(String memberCode, Taxi taxi) {
    	if(taxi == null) {
			System.out.println("taxi is null");
			return false;
		}
    	if(!Member.isMemberCodeExists(memberList, memberCode)) {
            System.out.println("Member code not found");
            return false;
        }
		if(memberTaxies.get(memberCode)==null) {
			ArrayList<Taxi> newList =  new ArrayList<Taxi>();
			newList.add(taxi);
    		memberTaxies.put(memberCode, newList);
		} else if(memberTaxies.get(memberCode).contains(taxi)) {
			System.out.println("Eror- taxi already in the list.");
			return false;
		} else 
			memberTaxies.get(memberCode).add(taxi);
		System.out.println("Added taxi to member successfully.");
		return true;
	}
    

	// -----------------------------------------> expressTaxisForMemberList
	// The function receives a member and returns an array list of all the express taxi that member ordered
	public ArrayList<ExpressTaxi> expressTaxisForMemberList (Member member) {
		ArrayList<ExpressTaxi> expressTaxisList = new ArrayList<ExpressTaxi>();
		 if(member == null || member.getMemberCode() == null) {
				System.out.println("Error- can't find express taxis for this member");
		        return expressTaxisList; 
		    }
		 if(!memberOrders.containsKey(member.getMemberCode()) || memberOrders.get(member.getMemberCode()) == null) {
			 System.out.println("Error- can't find express taxis for this member");
			 return expressTaxisList; 
			 }
		for(Order o: memberOrders.get(member.getMemberCode())) {
			if(o!=null && o.getTaxi() instanceof ExpressTaxi) {
				ExpressTaxi expressTaxi = (ExpressTaxi) o.getTaxi();
				expressTaxisList.add(expressTaxi);
			}
		}
		return expressTaxisList;
	}

	
	// -----------------------------------------> freeTaxisInStationList
	// Receives a station and returns an array list with the codes of the free taxies in that station 
	public ArrayList<String> freeTaxisInStationList (Station station) {
		ArrayList<String> freeTaxiesCode = new ArrayList<String>();
		if(station == null || station.getTaxies() == null) {
			System.out.println("Error- can't find free taxis in the station list");
	        return freeTaxiesCode; 
	    }
		for (Taxi t: station.getTaxies()) {
			if(t!=null && !t.isTaken)
				freeTaxiesCode.add(t.getTaxiCode());
		}
		return freeTaxiesCode;
	}
	
	 // ------------------ STATION TAXIES ------------------
    public void addTaxiToStation(Station station, Taxi taxi) {
    	station.addTaxi(station, taxi);
    }
    
    public void removeTaxiFromStation(Station station, Taxi taxi) {
    	station.removeTaxiFromStation(station, taxi);
    }

    // -----------------------------------------> removeOrderFromMember
    public void removeOrderFromMember(String memberCode, Order o) {
        if (memberOrders.containsKey(memberCode)) {
            memberOrders.get(memberCode).remove(o);
        }
    }
    
 // -----------------------------------------> removeOrderFromMember
    public void removeTaxiFromMember(String memberCode, Taxi t) {
        if (memberTaxies.containsKey(memberCode)) {
        	memberTaxies.get(memberCode).remove(t);
        }
    }
	
	// ---------------------------------> toString
		public String toString() {
			return "SystemDataBase: [Administrator: "+ Administrator.toString()+ ", ManagerList: "+ ManagerList.toString()+ ", taxiList: "+taxiList.toString()
			+ ", stationList: "+stationList.toString()+", orderList: "+orderList.toString()+", memberList: "+memberList+
			", memberTaxies: "+memberTaxies.toString()+", memberOrders: "+memberOrders.toString()+"]";
		}

}
