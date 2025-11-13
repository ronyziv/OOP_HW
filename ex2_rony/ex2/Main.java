package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	// -----------------------------------------> adminLogin
	// Function description: Manages the admin menu options allowing addition of Members, managers, and taxies,
	// as well as associating taxies to managers and any other update.
	public static void adminLogin(SystemDataBase myDataBase, String[] cityArray){
		Scanner in = new Scanner(System.in);
		int button = -1;
		while(button!=0) {
			System.out.println("Welcome to the Main Menu for Admin. To exit at any time press 0, or: ");
			System.out.println("--1 to go to main manager menu--");
			System.out.println("--2 to update a collection--");
			button = in.nextInt();
			in.nextLine();
			switch(button) {
			case 1: // main manager menu
				mainManagerLogin(myDataBase, cityArray);
				break;
			case 2: // admin menu
				manageAllCollections(myDataBase, cityArray);
				break;
				}
			}
		}
	
	// -----------------------------------------> manageAllCollections
	public static void manageAllCollections(SystemDataBase myDataBase, String[] cityArray) {
		Scanner in = new Scanner(System.in);
		int choice = -1;
	    int button = -1;
	    while (button != 0) {
	        System.out.println("--- Manage Collections Menu ---");
	        System.out.println("--1 to manage Taxis--");
	        System.out.println("--2 to manage Members--");
	        System.out.println("--3 to manage Orders--");
	        System.out.println("--4 to manage Stations--");
	        System.out.println("--5 to manage Managers--");
	        System.out.println("--6 to manage member taxies--");
	        System.out.println("--7 to manage member orders--");
	        System.out.println("--0 to go back to Main Manager Menu--");
	        System.out.println("Please choose: ");
	        button = in.nextInt();
	        in.nextLine(); 
	        switch (button) {
	            case 1: // Manage Taxis
	            	System.out.println("--1 to add taxi, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
					if(choice == 1){
						Taxi taxi = createTaxi(myDataBase.getTaxiList(), cityArray);
						if(taxi!=null) {
							myDataBase.addItem(myDataBase.getTaxiList(), taxi);
							System.out.println("Added successfully.");
						}
						else{
							System.out.println("Failed to create.");
						}
					}
					else if(choice == 2){
						if(myDataBase.getTaxiList().isEmpty())
							System.out.println("no taxies to remove");
						else {
							System.out.println(myDataBase.getTaxiList().toString());
							System.out.println("Choose which taxi to remove- - enter taxi code: ");
							String taxiCode = in.nextLine();
							for(Taxi t: myDataBase.getTaxiList()){
								if(t!=null && t.getTaxiCode().equals(taxiCode)){
									myDataBase.removeItem(myDataBase.getTaxiList(), t);
									System.out.println("Removed successfully.");
									break;
								}
							}
						}
					}
					else
						System.out.println("Input invalid.");
	                break;
	            case 2: // Manage Members
	            	System.out.println("--1 to add member, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
					if(choice == 1){
						Member member = Member.createMember(myDataBase.getMemberList(), cityArray);
						if(member!=null) {
							myDataBase.addItem(myDataBase.getMemberList(), member);
							myDataBase.getMemberOrders().put(member.getMemberCode(), new ArrayList<Order>());
							myDataBase.getMemberTaxies().put(member.getMemberCode(), new ArrayList<Taxi>());
							System.out.println("Added successfully.");
						} else 
	                        System.out.println("Failed to create member.");
					}
					else if(choice == 2){
						if(myDataBase.getMemberList().isEmpty())
							System.out.println("no members to remove");
						else {
							System.out.println(myDataBase.getMemberList().toString());
							System.out.println("Choose which member to remove- enter member code: ");
							String memberCode = in.nextLine();
							for(Member m: myDataBase.getMemberList()){
								if(m!=null && m.getMemberCode().equals(memberCode)){
									myDataBase.removeItem(myDataBase.getMemberList(), m);
									myDataBase.getMemberOrders().remove(memberCode);
									myDataBase.getMemberTaxies().remove(memberCode);
									System.out.println("Removed successfully.");
									break;
								}
							}
						}
					}
					else
						System.out.println("Input invalid.");
	                break;
	            case 3: // Manage Orders
	            	System.out.println("--1 to add order, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
					if(choice == 1){
						Order order = createOrder(myDataBase, cityArray);
						if(order != null) {
	                        myDataBase.addItem(myDataBase.getOrderList(), order);
	                        System.out.println("Added successfully.");
	                    } else 
	                        System.out.println("Failed to create order.");
					}
					else if(choice == 2){
						if(myDataBase.getOrderList().isEmpty()) 
	                        System.out.println("No orders to remove.");
	                    else {
							System.out.println(myDataBase.getOrderList().toString());
							System.out.println("Choose which order to remove: ");
							String orderCode = in.nextLine();
							for(Order o: myDataBase.getOrderList()){
								if(o!=null && o.getOrderCode().equals(orderCode)){
									myDataBase.removeItem(myDataBase.getOrderList(), o);
									System.out.println("Removed successfully.");
									break;
								}
							}
	                    }
					}
	                break;
	            case 4: // Manage Stations
	            	System.out.println("--1 to add station, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
					if(choice == 1){
						Station station = createStation(myDataBase, cityArray);
						if(station != null) {
	                        myDataBase.addItem(myDataBase.getStationList(), station);
	                        System.out.println("Added successfully.");
	                    } else 
	                        System.out.println("Failed to create station.");					
						}
					else if(choice == 2){
						if(myDataBase.getStationList().isEmpty()) 
	                        System.out.println("No stations to remove.");
	                    else {
							System.out.println("Choose which station to remove (enter name): ");
							System.out.println(myDataBase.getStationList().toString());
							String stationName = in.nextLine();
							for(Station s: myDataBase.getStationList()){
								if(s!=null && s.getStationName().equals(stationName)){
									myDataBase.removeItem(myDataBase.getStationList(), s);
									System.out.println("Removed successfully.");
									break;
								}
							}
						}
					}
					break;
	            case 5: // Manage Managers
	            	System.out.println("--1 to add manager, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
					if(choice == 1){
						Manager manager = createManager(myDataBase, cityArray);
						 if(manager != null) {
		                        myDataBase.addItem(myDataBase.getManagerList(), manager);
		                        System.out.println("Added successfully.");
						 } else 
		                        System.out.println("Failed to create manager.");					
						 }
					else if(choice == 2){
						if(myDataBase.getManagerList().isEmpty()) 
	                        System.out.println("No managers to remove.");
	                    else {
							System.out.println("Choose which manager to remove: ");
							System.out.println(myDataBase.getManagerList().toString());
							String managerCode = in.nextLine();
							if(managerCode.equals(myDataBase.getAdministrator().getManagerID())) {
								System.out.println("Can't remove the Admin.");
								return;
							}
							for(Manager m: myDataBase.getManagerList()){
								if(m!=null && m.getManagerID().equals(managerCode)){
									myDataBase.removeItem(myDataBase.getManagerList(), m);
									System.out.println("Removed successfully.");
									break;
								}
							}
						}
					}
	                break;
	            case 6:  // member's taxies
	            	System.out.println("--1 to add taxi to member, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
			        Member member = null;
			        boolean isValid = false;
			        do {
						System.out.println(myDataBase.getMemberList().toString());
						System.out.println("Choose which member to update (enter code): ");
						String memberCode = in.nextLine();
						for(Member m: myDataBase.getMemberList()){
							if(m != null && m.getMemberCode().equals(memberCode)){
								member = m;
								isValid = true;
								break;
							}
						}
						if(!isValid)
							System.out.println("Invalid input");
						}
					while (!isValid);
					if(choice == 1){
						System.out.println(member+" is the member chosen. please create a taxi to add: ");
						Taxi taxi = createTaxi(myDataBase.getTaxiList(), cityArray);
						if(taxi!=null) {
							myDataBase.addItem(myDataBase.getTaxiList(), taxi);
							ArrayList<Taxi> memberTaxiList = myDataBase.getMemberTaxies().get(member.getMemberCode());
							if(memberTaxiList == null) 
								memberTaxiList = new ArrayList<Taxi>();
							memberTaxiList.add(taxi);
							System.out.println("Added successfully.");
							myDataBase.getMemberTaxies().put(member.getMemberCode(), memberTaxiList);
						}
						else
							System.out.println("Failed to create taxi");
					}
					else if(choice == 2) {
				        ArrayList<Taxi> memberTaxiList = myDataBase.getMemberTaxies().get(member.getMemberCode());
				        if(memberTaxiList == null || memberTaxiList.isEmpty()) {
				            System.out.println("This member has no taxis to remove.");
				        } else {
				            System.out.println(member+" is the member chosen. Current taxis: " + memberTaxiList);
				            System.out.println("Enter taxi code to remove: ");
				            String taxiCode = in.nextLine();
				            boolean found = false;
				            for(Taxi t: memberTaxiList) {
				                if(t.getTaxiCode().equals(taxiCode)) {
				                    memberTaxiList.remove(t);
				                    myDataBase.getMemberTaxies().put(member.getMemberCode(), memberTaxiList);
				                    System.out.println("Removed successfully.");
				                    found = true;
				                    break;
				                }
				            } if(!found) 
				                System.out.println("Taxi not found.");
				        }
				    }
				    break;
	            case 7: // member's orders
	            	System.out.println("--1 to add order to member, 2 to remove: ");
	            	choice = in.nextInt();
			        in.nextLine(); 
			        Member newMember = null;
			        boolean isThisValid = false;
			        do {
			        	System.out.println(myDataBase.getMemberList().toString());
						System.out.println("Choose which member to update (enter code): ");
						String memberCode = in.nextLine();
						for(Member m: myDataBase.getMemberList()){
							if(m!=null && m.getMemberCode().equals(memberCode)){
								newMember = m;
								isThisValid = true;
								break;
							}
						}
						if(!isThisValid)
							System.out.println("Invalid input");
						}
					while (!isThisValid);
					if(choice == 1){
						System.out.println(newMember+" is the member chosen. please create an order to add: ");
						Order order = createOrder(myDataBase, cityArray);
						if(order!=null) {
							myDataBase.addItem(myDataBase.getOrderList(), order);
							ArrayList<Order> memberOrderList = myDataBase.getMemberOrders().get(newMember.getMemberCode());
					        if(memberOrderList == null) {
					            memberOrderList = new ArrayList<Order>();
					        }						
					        memberOrderList.add(order);
							myDataBase.getMemberOrders().put(newMember.getMemberCode(), memberOrderList);
							System.out.println("Added successfully.");
						}
						else
	                        System.out.println("Failed to create order.");
					}
					else if(choice == 2) {
				        ArrayList<Order> memberOrderList = myDataBase.getMemberOrders().get(newMember.getMemberCode());
				        if(memberOrderList == null || memberOrderList.isEmpty()) {
				            System.out.println("This member has no orders to remove.");
				        } else {
				            System.out.println(newMember+" is the member chosen. Current orders: " + memberOrderList);
				            System.out.println("Enter order code to remove: ");
				            String orderCode = in.nextLine();
				            boolean found = false;
				            for(Order o: memberOrderList) {
				                if(o != null && o.getOrderCode().equals(orderCode)) {
				                    memberOrderList.remove(o);
				                    myDataBase.getMemberOrders().put(newMember.getMemberCode(), memberOrderList);
				                    System.out.println("Removed successfully.");
				                    found = true;
				                    break;
				                }
				            }
				            if(!found) {
				                System.out.println("Order not found.");
				            }
				        }
				    }
				    break;
	            	case 0:
	            		System.out.println("Returning to Main Manager Menu.");
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        	}
	    	}
		}
	
	// -----------------------------------------> mainManagerLogin
	// Function description: Manages the main manager menu options allowing addition of Members, managers, and taxies,
	// as well as associating taxies to managers.
	public static void mainManagerLogin(SystemDataBase myDataBase, String[] cityArray){
		Scanner in = new Scanner(System.in);
		int button = -1;
		while(button!=0) {
			System.out.println("Welcome to the Main Menu for Main Manager. To exit at any time press 0, or: ");
			System.out.println("--1 to add Member--");
			System.out.println("--2 to add manager--");
			System.out.println("--3 for taxi subscription--");
			System.out.println("--4 to associate taxi to a manager--");
			System.out.println("please choose: ");
			button = in.nextInt();
			in.nextLine();
			switch(button) {
			case 1: // add Member
				Member member = Member.createMember(myDataBase.getMemberList(), cityArray);
				if(member!=null) {
					myDataBase.getMemberList().add(member);
					myDataBase.getMemberOrders().put(member.getMemberCode(), new ArrayList<Order>());
					myDataBase.getMemberTaxies().put(member.getMemberCode(), new ArrayList<Taxi>());
					System.out.println("Member added successfully!");
				} 
				else
					System.out.println("Failed to add member");
				break;
			case 2: // add manager
				Manager manager = createManager(myDataBase, cityArray);
                if (manager!=null && !isManagerCodeExists(myDataBase.getManagerList(), manager.getManagerID())) {
                    myDataBase.getManagerList().add(manager);
                    System.out.println("Manager added successfully!");
                }
                else
					System.out.println("Failed to add manager");
                break;
			case 3: //  taxi subscription
				Taxi newTaxi = createTaxi(myDataBase.getTaxiList(), cityArray);
                if (newTaxi!=null && !isTaxiCodeExists(myDataBase.getTaxiList(), newTaxi.getTaxiCode())) {
                	myDataBase.getTaxiList().add(newTaxi);
                    System.out.println("Taxi added successfully!");
                }
                else
					System.out.println("Failed to add taxi");
                break;
			case 4: // associate taxi to a manager
				int retryButton = 0;
				do {
					System.out.println("enter manager ID - 9 digits: ");
					String managerCode = in.nextLine();
					System.out.println("enter taxi code: ");
					String taxiCode = in.nextLine();
					Taxi taxiToAdd = null;
					if (isManagerCodeExists(myDataBase.getManagerList(), managerCode) && isTaxiCodeExists(myDataBase.getTaxiList(), taxiCode)) {
						for (Taxi t: myDataBase.getTaxiList()) {
							if (t.getTaxiCode().equals(taxiCode))
								taxiToAdd = t;
						}
						if (taxiToAdd != null) {
							for (Manager m: myDataBase.getManagerList()) {
								if (m.getManagerID().equals(managerCode)) {
									ArrayList<Taxi> currentTaxies = m.getTaxies();
									currentTaxies.add(taxiToAdd);
									m.setTaxies(currentTaxies);
									System.out.println("Taxi was associated to the manager successfully!");
									retryButton = 0;
									break;
								}
							}
						}
					} else {
						System.out.println("Manager or taxi not found. Please check the codes. press 0 to go back or any other number to try again.");
						retryButton = in.nextInt();
						in.nextLine();
					}
				} while (retryButton != 0);
				break;
            }
        }
	}
	
	// -----------------------------------------> regularManagerLogin
	// Function description: Manages the manager menu options allowing for adding new orders and modifying
	// existing taxi assignments in orders. 
	public static void regularManagerLogin(SystemDataBase myDataBase, String[] cityArray, String managerCode){
		Scanner in = new Scanner(System.in);
		Manager manager = null;
		for (Manager m: myDataBase.getManagerList()) {
			if (m!=null && m.getManagerID().equals(managerCode)) {
				manager = m;
				break;
			}
		}
		int button = -1;
		while(button!=0) {
			System.out.println("Welcome to the Main Menu for regular Manager. To exit at any time press 0, or: ");
			System.out.println("--1 to add an order--");
			System.out.println("--2 to change taxi in the order--");
			System.out.println("please choose: ");
			button = in.nextInt();
			in.nextLine();
			switch(button) {
			case 1: // add an order
				String memberCode = getMemberCode(myDataBase.getMemberList());
				if (memberCode == null)
					break;
				String taxiCode = getTaxiCode(myDataBase.getTaxiList());
				if (taxiCode == null)
					break;
				Taxi taxi = getAssociatedTaxi(taxiCode, manager);
				if (taxi != null) {
					if (taxi.getIsTaken()) {
						System.out.println("Taxi is already taken.");
						break;
					} else {
						Order order = new Order();
						int hour, day, month;
						do {
							System.out.println("Please enter the Order's hour: ");
							hour = in.nextInt();
							in.nextLine();
						} while(!order.setHour(hour));
						do {
							System.out.println("Please enter the Order's day: ");
							day = in.nextInt();
							in.nextLine();
						} while(!order.setDay(day));
						do {
							System.out.println("Please enter the Order's month: ");
							month = in.nextInt();
							in.nextLine();
						} while(!order.setMonth(month));
						// We do not check if the result of these is valid, since we already checked this all before
						order.setManagerCode(managerCode);
						order.setMemberCode(memberCode);
						order.setTaxi(taxi);
						order.setOrderPrice(taxi.calculatePrice());
						ArrayList<Order> managerOrders = manager.getOrders();
						manager.getOrders().add(order);
						taxi.setIsTaken(true);
						myDataBase.addTaxiToMember(memberCode, taxi);
						myDataBase.addOrderToMember(memberCode, order);
						myDataBase.getOrderList().add(order);
						System.out.println("Successfully created new order: " + order.toString());
					}
				} else {
					System.out.println("Couldn't find taxi on current manager");
				}
				break;
			case 2: // change taxi in the order (only if it is regular one!)
				Order order = getManagerOrder(manager);
				if (order != null) {
					Taxi orderTaxi = order.getTaxi();
					if (orderTaxi.getTaxiTypeName().equals("Regular")) {
						changeTaxiTypeForOrder(myDataBase, order, manager);
						System.out.println("Taxi was associated to the order successfully!");
					} else {
						System.out.println("Can't change order that doesn't use a Regular taxi");
					}
				}
				break;
			}
		}
	}
	
	// -----------------------------------------> MemberLogin
	// Function description: Manages the member menu providing options for viewing past orders, updating personal 
	// information, and viewing taxi details.
	public static void MemberLogin(SystemDataBase myDataBase, String[] cityArray, String membercode){
		Scanner in = new Scanner(System.in);
		int button = -1;
		Member member= null;
		for(Member m: myDataBase.getMemberList()) {
			if(m.getMemberCode().equals(membercode)) {
				member = m;
				break;
			}
		}
		while(button!=0) {
			System.out.println("Welcome to the Main Menu for Member Login. To exit at any time press 0, or: ");
			System.out.println("--1 to print past orders");
			System.out.println("--2 to update member details");
			System.out.println("--3 to see taxi details --");
			System.out.println("please choose: ");
			button = in.nextInt();
			in.nextLine();
			switch(button) {
			case 1: // to print past orders
				for(Order o: myDataBase.getMemberOrders().get(membercode)) {
					System.out.println(o);	
				}
				break;
			case 2: // to update member details
				updateMemberDetails(member, cityArray);	
				break;
			case 3: // to see taxi details
				boolean foundTaxi= false;
				System.out.println("enter taxi code: ");
				String taxicode = in.nextLine();
				for(Taxi t: myDataBase.getTaxiList()) {
					if(t.getTaxiCode().equals(taxicode)) {
						System.out.println(t);
						foundTaxi = true;
					}
				}
				if(!foundTaxi)
					System.out.println("didn't find the taxi with this code.");
				break;
			}
		}
	}
	
	
	// -----------------------------------------> getManagerOrder
	// Function description: Receive order code by the user, and returns the corresponding Order object.
	private static Order getManagerOrder(Manager manager) {
		Scanner in = new Scanner(System.in);
		int button;
		ArrayList<Order> orders = manager.getOrders();
		System.out.println(manager.getOrders().toString());
		do{
			System.out.println("Enter order code: ");
			String orderCode = in.nextLine();
			for (Order o: orders) {
				if(o!=null && o.getOrderCode().equals(orderCode))
					return o;
			}			
			System.out.println("Code doesn't exists.");
			System.out.println("To exit press 2, otherwise press any other number: ");
			button = in.nextInt();
			in.nextLine();
		} while(button!=2);
		return null;
	}
	
	// -----------------------------------------> getOrder
		// Function description: Receive order code by the user, and returns the corresponding Order object.
		private static Order getOrder(SystemDataBase mydataBase) {
			Scanner in = new Scanner(System.in);
			int button;
			ArrayList<Order> orders = mydataBase.getOrderList();
			do{
				System.out.println("Enter order code: ");
				String orderCode = in.nextLine();
				for (Order o: orders) {
					if(o!=null && o.getOrderCode().equals(orderCode))
						return o;
				}			
				System.out.println("Code doesn't exists.");
				System.out.println("To exit press 2, otherwise press any other number: ");
				button = in.nextInt();
				in.nextLine();
			} while(button!=2);
			return null;
		}
	
	// -----------------------------------------> getMemberCode
	// Function description: Receive a member code from the user, and returns the code if it exists. 
	
	private static String getMemberCode(ArrayList<Member> members) {
		Scanner in = new Scanner(System.in);
		int button;
		do{
			System.out.println("Enter member code: ");
			String memberCode = in.nextLine();
			if(isMemberCodeExists(members, memberCode)) 
				return memberCode;
			else {
				System.out.println("Code doesn't exists.");
				System.out.println("To exit press 2, otherwise press any other number: ");
				button = in.nextInt();
				in.nextLine();
			}
		} while(button!=2);
		return null;
	}
	
	// -----------------------------------------> getTaxi
	// Function description: Receive systemdb, and returns a taxi object.
	private static Taxi getTaxi(SystemDataBase myDataBase, String[] cityArray) {
		Scanner in = new Scanner(System.in);
		System.out.println(myDataBase.getTaxiList());
		System.out.println("choose taxi code from the list: ");
		String taxiCode = in.nextLine();
		for(Taxi t: myDataBase.getTaxiList()) {
			if(t!=null && t.getTaxiCode().equals(taxiCode))
				return t;
		}
		System.out.println("cant find the taxi code, please creat taxi.");
		Taxi taxi = createTaxi(myDataBase.getTaxiList(), cityArray);
		return taxi;
	}
		
	
	// -----------------------------------------> changeTaxiTypeForOrder
		// Function description: Allows the manager to change the taxi assigned to an existing order.
		private static void changeTaxiTypeForOrder(SystemDataBase myDataBase, Order order, Manager manager) {
			if (order == null || manager == null) {
		        System.out.println("Invalid order or manager.");
		        return;
		    }
			Scanner in = new Scanner(System.in);
			int button = -1;
			String requestedTaxiType = null;
			Taxi oldOrderTaxi = order.getTaxi();
			do {
				System.out.println("--1 to update to Intercity Taxi--");
				System.out.println("--2 to update to Express Taxi--");
				System.out.println("--0 to exit--");
				button = in.nextInt();
				in.nextLine();
				if(button == 1)
					requestedTaxiType = "Intercity";
				else if (button == 2)
					requestedTaxiType = "Express";
				else if (button == 0) 
					return; 
	            else 
	            	System.out.println("Invalid input. Please try again.");
	    } while (button != 1 && button != 2); 
			do {
				String taxiCode = getTaxiCode(manager.getTaxies());
				if (taxiCode == null)
					return;
				Taxi taxi = getAssociatedTaxi(taxiCode, manager);
				if (taxi != null && !taxi.getTaxiTypeName().equals(requestedTaxiType)) {
					System.out.println("Invalid choice: Selected taxi's type is "+ taxi.getTaxiTypeName() + " and you wanted to change to " + requestedTaxiType);
					System.out.println("To exit press 0, otherwise press any other number");
					button = in.nextInt();
					in.nextLine();
				} else {
					if (taxi != null) {
						if (taxi.getIsTaken()) {
							System.out.println("Taxi is already taken, to exit press 0, otherwise press any other number");
							button = in.nextInt();
							in.nextLine();
						} else {
						    order.setTaxi(taxi);
						    order.setOrderPrice(taxi.calculatePrice());
						    taxi.setIsTaken(true);
						    oldOrderTaxi.setIsTaken(false);
						    if (myDataBase.getMemberTaxies().containsKey(order.getMemberCode())) { // Update member's taxi list - remove old taxi, add new taxi
						        ArrayList<Taxi> memberTaxis = myDataBase.getMemberTaxies().get(order.getMemberCode());
						        memberTaxis.remove(oldOrderTaxi); 
						        if (!memberTaxis.contains(taxi)) { 
						            memberTaxis.add(taxi);
						        }
						    }
						    for (int i = 0; i < myDataBase.getOrderList().size(); i++) { // Update order in the order list 
						        if (myDataBase.getOrderList().get(i).getOrderCode().equals(order.getOrderCode())) {
						            myDataBase.getOrderList().set(i, order); 
						            break;
						        }
						    }						    
						    System.out.println("Successfully updated taxi in order: " + order.toString());
						    return;
						}
					}
					else 
			            System.out.println("Taxi not found.");
				}
			} while (button != 0);
		}
	
	// -----------------------------------------> getAssociatedTaxi
	// Function description: Searches the manager's list of taxis for a taxi whose code matches the provided taxi code.
	// Returns the Taxi object associated with a given taxi code.
	private static Taxi getAssociatedTaxi(String taxiCode, Manager manager) {
		ArrayList<Taxi> managerTaxies = manager.getTaxies();
		for(Taxi t: managerTaxies) {
			if (t!=null && t.getTaxiCode().equals(taxiCode)) {
				return t;
			}
		}
		return null;
	}
		
	
	// -----------------------------------------> getTaxiCode
	// Function description: Receive a taxi code from the user, and returns the code if it exists.
	private static String getTaxiCode(ArrayList<Taxi> taxies) {
		Scanner in = new Scanner(System.in);
		int button;
		do{
			System.out.println("Enter taxi code: ");
			String taxiCode = in.nextLine();
			if(isTaxiCodeExists(taxies, taxiCode)) 
				return taxiCode;
			else {
				System.out.println("Code doesn't exists.");
				System.out.println("To exit press 2, otherwise press any other number: ");
				button = in.nextInt();
				in.nextLine();
			}
		} while(button!=2);
		return null;
	}
	
	// -----------------------------------------> updateMemberDetails
	// Function description: Allows the user to update details of a given Member.
	public static void updateMemberDetails(Member member, String[] cityArray) {
		int button=-1;
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("--1 to update phone number");
			System.out.println("--2 to update address");
			System.out.println("--0 to exit--");
			button = in.nextInt();
			in.nextLine();
			switch (button) {
			case 1: 
				System.out.println("please enter the updated phone number:");
				String phone = in.nextLine();
				boolean setSuccessfully = member.setMemberPhone(phone);
				if(setSuccessfully)
					System.out.println("updated successfully!");
				break;
			case 2: 
				Address address = createAddress(cityArray);
				member.setMemberAddress(address);
				System.out.println("updated successfully!");
				break;
			}
		}while(button!=0);
	}
	
	//-------------------------isCodeExists---------------------------------
	// -----------------------------------------> isMemberCodeExists
	// Function description: Checks whether a given member code exists in the members array.
	// Returns true if found, false otherwise.
	public static boolean isMemberCodeExists(ArrayList<Member> members, String membercode) {
		for(Member m: members) {
			if(m!=null && m.getMemberCode().equals(membercode))
				return true;
		}
		return false;
	}
	
	// -----------------------------------------> isTaxiCodeExists
	// Function description: Checks whether a given taxi code exists in the taxies array.
	// Returns true if found, false otherwise.
	public static boolean isTaxiCodeExists(ArrayList<Taxi> taxies, String taxiCode) {
		for(Taxi t: taxies) {
			if(t!=null && t.getTaxiCode().equals(taxiCode))
				return true;
		}
		return false;
	}
	
	// -----------------------------------------> isManagerCodeExists
	// Function description: Checks whether a given manager ID exists in the managers array.
	// Returns true if found, false otherwise. 
	public static boolean isManagerCodeExists(ArrayList<Manager> managers, String managerID) {
		for(Manager m: managers) {
			if(m!=null && m.getManagerID().equals(managerID))
				return true;
		}
		return false;
	}
	
	// -----------------------------CREATE----------------------------
	// -----------------------------------------> create order
		// Function description: Creates a new order based on user selection. 
		// Returns the newly created order.
		public static Order createOrder(SystemDataBase myDataBase, String[] cityArray) {
			Scanner in = new Scanner(System.in);
			boolean isValid;
			Order order = new Order();
			do {
				System.out.println("Enter manager code: ");
				String managerCode = in.nextLine();
				isValid = order.setManagerCode(managerCode);
			} while (!isValid);
			do {
				System.out.println("Enter member code: ");
				String memberCode = in.nextLine();
				isValid = order.setMemberCode(memberCode);
			} while (!isValid);
			do {
				System.out.println("Enter day - between 1 to 31: ");
				int day = in.nextInt();
				System.out.println("Enter month - between 1 to 12: ");
				int month = in.nextInt();
				System.out.println("Enter hour - between 0 to 23: ");
				int hour = in.nextInt();
				isValid = order.setDay(day) && order.setMonth(month) && order.setHour(hour);
			} while (!isValid);
			do {
				Taxi taxi = getTaxi(myDataBase, cityArray);
				order.setTaxi(taxi);
				if(taxi != null)
					isValid = true;
			} while (!isValid);
			return order;
		}
			
	// -----------------------------------------> createStation
	public static Station createStation(SystemDataBase myDataBase, String[] cityArray) {
	    Scanner in = new Scanner(System.in);
	    ArrayList<Taxi> taxies = new ArrayList<Taxi>();
	    Station station = new Station();
	    int choice;
	    boolean isValid;	    
	    do {
	        System.out.println("Enter station name: ");
	        String stationName = in.nextLine();
	        isValid = station.setStationName(stationName);
	    } while (!isValid);
	    do {
	        System.out.println("To add taxies to the list press 1, any other number to finish: ");
	        choice = in.nextInt();
	        in.nextLine(); 
	        if(choice == 1) {
	            Taxi taxi = getTaxi(myDataBase, cityArray);
	            if(myDataBase.addItem(myDataBase.getTaxiList(), taxi)) {
	                taxies.add(taxi); 
	                System.out.println("Taxi added successfully!");
	            } else 
	                System.out.println("Failed to add taxi to database.");   
	        } 
	        else {
	            isValid = true; 
	        }
	    } while (!isValid);
	    station.setTaxies(taxies); 
	    return station;
	}
	
	// -----------------------------------------> create Manager
	// Function description: Creates a new Manager or Main_Manager based on user selection. 
	// Returns the newly created Manager. 
	public static Manager createManager(SystemDataBase myDataBase, String[] cityArray) {
		Scanner in = new Scanner(System.in);
		Manager manager;
		int managerType;
		do {
        System.out.println("Please enter which manager you want to add- 1 for general or 2 for main: ");
        managerType = in.nextInt();
        in.nextLine();
		}
		while(managerType!=1 && managerType!=2);
		if (managerType == 1) {
			manager = new Manager();
		} else {
			manager = new Main_Manager();
		}
		boolean isValid;
		do {
			System.out.println("Enter Manager ID- 9 digits: ");
			String managerCode = in.nextLine();
			isValid = manager.setManagerID(managerCode);
			if(isValid && isManagerCodeExists(myDataBase.getManagerList(), managerCode)) {
				System.out.println("manager ID already exists, try again.");
				isValid=false;
			}
		} while (!isValid);
		// Get and set manager first name, last name and phone in a loop
		// to ensure that the input is valid.
		do {
			System.out.println("Enter Manager First Name: ");
			String firstName = in.nextLine();
			isValid = manager.setManagerFirstName(firstName);
		} while (!isValid);
		do {
			System.out.println("Enter Manager Last Name: ");
			String lastName = in.nextLine();
			isValid = manager.setManagerLastName(lastName);
		} while (!isValid);
		do {
			System.out.println("Enter Manager Phone: ");
			String phone = in.nextLine();
			isValid = manager.setManagerPhone(phone);
		} while (!isValid);
		System.out.println("Create manager Address: ");
		Address address = createAddress(cityArray);
		manager.setManagerAddress(address);
	    manager.setTaxies(new ArrayList<Taxi>());
		manager.setOrders(new ArrayList<Order>());
		if (managerType == 2) {
			do {
				System.out.println("Enter your username: ");
				String username = in.nextLine();
				isValid = ((Main_Manager)manager).setUsername(username);
			} while (!isValid);
			do {
				System.out.println("Enter your password: ");
				String password = in.nextLine();
				isValid = ((Main_Manager)manager).setPassword(password);
			} while (!isValid);
		}
	return manager;
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

	// -----------------------------------------> create taxi
	// Function description: Creates a new Taxi, ExpressTaxi, or IntercityTaxi based on user selection.
	// Returns the newly created Taxi.
	public static Taxi createTaxi(ArrayList<Taxi> taxies, String[] cityArray) {
		Scanner in = new Scanner(System.in);
		int taxiType;
		Taxi newTaxi;
		do{
			System.out.println("Please enter which taxi you want to add- 1 for general or 2 for express and 3 for intercity: ");
			taxiType = in.nextInt();
			in.nextLine();
		}
		while(taxiType>3 || taxiType<1);
		if (taxiType == 1) {
			newTaxi = new Taxi();
		} else if (taxiType == 2) {
			newTaxi = new ExpressTaxi();
		} else {
			newTaxi = new IntercityTaxi();
		}
		boolean isValid;
		do {
			System.out.println("Enter taxi code: ");
			String taxiCode = in.nextLine();
			isValid = newTaxi.setTaxiCode(taxiCode);
			if(isValid && isTaxiCodeExists(taxies, taxiCode)) {
				System.out.println("Taxi code already exists, try again.");
				isValid=false;
			}
		} while (!isValid);	   
		newTaxi.setIsTaken(false);
		do {
			System.out.println("Please enter min price: ");
			double minPrice = in.nextDouble();
			in.nextLine();
			isValid = newTaxi.setMinPrice(minPrice);
		} while(!isValid);
		if (taxiType == 2) {
			do {
				System.out.println("Please enter extra price: ");
				int extraPriceExpressTaxi = in.nextInt();
				in.nextLine();
				isValid = ((ExpressTaxi)newTaxi).setExtraPrice(extraPriceExpressTaxi);
			} while(!isValid);
			boolean isCityTaxi;
			System.out.println("Please choose if it is a city taxi - true or false: ");
			isCityTaxi = in.nextBoolean();
			in.nextLine();
			((ExpressTaxi)newTaxi).setCityTaxi(isCityTaxi);
		} else if (taxiType == 3) {
			do {
				System.out.println("Please enter extra price: ");
				int extraPriceExpressTaxi = in.nextInt();
				in.nextLine();
				isValid = ((IntercityTaxi)newTaxi).setExtraPrice(extraPriceExpressTaxi);
			} while(!isValid);
			do {
				System.out.println("Please enter max hours: ");
				int maxHours = in.nextInt();
				in.nextLine();
				isValid = ((IntercityTaxi)newTaxi).setMaxHours(maxHours);
			} while(!isValid);
			((IntercityTaxi)newTaxi).setCitiesArray(cityArray);
		}
		return newTaxi;
	}
	
	// -----------------------------------------> validateMainManagerCredentials
	// Function description: Validates the provided username and password against stored Main_Manager credentials.
	// Checks each manager to identify Main_Manager instances and compares their login details with the provided
	// credentials. Returns true if valid, false otherwise.
		public static boolean validateMainManagerCredentials(ArrayList<Manager> managers,String managerUsername, String managerPassword) {
			Main_Manager mainManager;
			for(Manager m: managers) {
				if(m instanceof Main_Manager) {
					if(((Main_Manager) m).getUsername().equals(managerUsername) && ((Main_Manager) m).getPassword().equals(managerPassword))
						return true;
				}
			}
			return false;
		}
		
	// -------------------------------MAIN-------------------------------------
	public static void main(String[] args) {
		SystemDataBase myDataBase = new SystemDataBase();
		// -----------------------------------------> City Array
		String[] cityArray = {"Netanya", "Haifa", "Ramat Gan", "Jerusalem", "Pardes Hanna",
					    "Tel Aviv", "Eilat", "Beer Sheva", "Zikhron Yaakov", "Kfar Saba",
					    "Ashdod", "Herzliya"};
		// -------------------------------------------------------------------------> Inputs
		// -----------------------------------------> Taxis
		myDataBase.getTaxiList().add(new Taxi("1001", false, 25));
		myDataBase.getTaxiList().add(new Taxi("1002", true, 30));
		myDataBase.getTaxiList().add(new Taxi("1003", false, 28));
		myDataBase.getTaxiList().add(new Taxi("1004", true, 35));
		myDataBase.getTaxiList().add(new Taxi("1005", false, 22));
		myDataBase.getTaxiList().add(new Taxi("1006", true, 40));
		myDataBase.getTaxiList().add(new Taxi("1007", false, 32));
		myDataBase.getTaxiList().add(new Taxi("1008", true, 27));
		myDataBase.getTaxiList().add(new Taxi("1009", false, 33));
		myDataBase.getTaxiList().add(new Taxi("1010", true, 29));
		myDataBase.getTaxiList().add(new ExpressTaxi("1011", false, 40, false, 10));
		myDataBase.getTaxiList().add(new ExpressTaxi("1012", true, 32, true, 15));
		myDataBase.getTaxiList().add(new ExpressTaxi("1013", false, 45, false, 12));
		myDataBase.getTaxiList().add(new ExpressTaxi("1014", true, 38, true, 14));
		myDataBase.getTaxiList().add(new ExpressTaxi("1015", false, 42, false, 11));
		myDataBase.getTaxiList().add(new ExpressTaxi("1016", true, 36, true, 16));
		myDataBase.getTaxiList().add(new ExpressTaxi("1017", false, 44, false, 13));
		myDataBase.getTaxiList().add(new ExpressTaxi("1018", true, 39, true, 17));
		myDataBase.getTaxiList().add(new ExpressTaxi("1019", false, 37, false, 9));
		myDataBase.getTaxiList().add(new ExpressTaxi("1020", true, 41, true, 18));
		myDataBase.getTaxiList().add(new IntercityTaxi("1021", false, 50, cityArray, 20.0, 5));
		myDataBase.getTaxiList().add(new IntercityTaxi("1022", true, 55, cityArray, 22.0, 6));
		myDataBase.getTaxiList().add(new IntercityTaxi("1023", false, 48, cityArray, 18.0, 7));
		myDataBase.getTaxiList().add(new IntercityTaxi("1024", true, 53, cityArray, 25.0, 8));
		myDataBase.getTaxiList().add(new IntercityTaxi("1025", false, 52, cityArray, 21.0, 4));
		myDataBase.getTaxiList().add(new IntercityTaxi("1026", true, 58, cityArray, 23.0, 9));
		myDataBase.getTaxiList().add(new IntercityTaxi("1027", false, 47, cityArray, 19.0, 5));
		myDataBase.getTaxiList().add(new IntercityTaxi("1028", true, 56, cityArray, 24.0, 7));
		myDataBase.getTaxiList().add(new IntercityTaxi("1029", false, 51, cityArray, 17.0, 6));
		myDataBase.getTaxiList().add(new IntercityTaxi("1030", true, 54, cityArray, 26.0, 8));
	
		// Creating arrays of taxies for each manager
		ArrayList<Taxi> aliceTaxies = new ArrayList<Taxi>();
		aliceTaxies.add(myDataBase.getTaxiList().get(0)); 
		aliceTaxies.add(myDataBase.getTaxiList().get(5)); 
		aliceTaxies.add(myDataBase.getTaxiList().get(8)); 
		aliceTaxies.add(myDataBase.getTaxiList().get(18)); 

		ArrayList<Taxi> bobTaxies = new ArrayList<Taxi>();
		bobTaxies.add(myDataBase.getTaxiList().get(1)); 
		bobTaxies.add(myDataBase.getTaxiList().get(6)); 
	
		ArrayList<Taxi> charlieTaxies = new ArrayList<Taxi>();
		charlieTaxies.add(myDataBase.getTaxiList().get(2)); 
		charlieTaxies.add(myDataBase.getTaxiList().get(7)); 
	
		ArrayList<Taxi> dinaTaxies = new ArrayList<Taxi>();
		dinaTaxies.add(myDataBase.getTaxiList().get(3)); 
		dinaTaxies.add(myDataBase.getTaxiList().get(9)); 
		dinaTaxies.add(myDataBase.getTaxiList().get(22)); 
	
		ArrayList<Taxi> eliTaxies = new ArrayList<Taxi>();
		eliTaxies.add(myDataBase.getTaxiList().get(4)); 
		
		// -----------------------------------------> Members
		myDataBase.getMemberList().add(new Member("501", "David", "Cohen", new Address("Tel Aviv", "Dizengoff", "112", "Israel"), "0541234567"));
		myDataBase.getMemberList().add(new Member("502", "Sarah", "Levy", new Address("Jerusalem", "Jaffa", "45", "Israel"), "0527654321"));
		myDataBase.getMemberList().add(new Member("503", "Michael", "Goldberg", new Address("Haifa", "Ha'atzmaut", "78", "Israel"), "0533456789"));
		myDataBase.getMemberList().add(new Member("504", "Rachel", "Friedman", new Address("Beer Sheva", "Rager", "23", "Israel"), "0548765432"));
		myDataBase.getMemberList().add(new Member("505", "Daniel", "Mizrahi", new Address("Eilat", "HaTmarim", "56", "Israel"), "0529876543"));
		myDataBase.getMemberList().add(new Member("506", "Tamar", "Avraham", new Address("Netanya", "Herzl", "91", "Israel"), "0546543210"));
		myDataBase.getMemberList().add(new Member("507", "Yossi", "Berkowitz", new Address("Ramat Gan", "Bialik", "34", "Israel"), "0521122334"));
		myDataBase.getMemberList().add(new Member("508", "Noa", "Shapira", new Address("Ashdod", "Kineret", "67", "Israel"), "0532233445"));
		myDataBase.getMemberList().add(new Member("509", "Avi", "Rosenberg", new Address("Herzliya", "Ha'Sharon", "19", "Israel"), "0547788990"));
		myDataBase.getMemberList().add(new Member("510", "Maya", "Katz", new Address("Kfar Saba", "Weizmann", "82", "Israel"), "0528899001"));
		myDataBase.getMemberList().add(new Member("511", "Eitan", "Dayan", new Address("Zikhron Yaakov", "HaMeyasdim", "41", "Israel"), "0539911223"));
		myDataBase.getMemberList().add(new Member("512", "Shira", "Golan", new Address("Pardes Hanna", "Derech HaNadiv", "29", "Israel"), "0544455667"));
		myDataBase.getMemberList().add(new Member("513", "Moshe", "Biton", new Address("Tel Aviv", "Allenby", "103", "Israel"), "0525566778"));
		myDataBase.getMemberList().add(new Member("514", "Ayala", "Peretz", new Address("Jerusalem", "King George", "71", "Israel"), "0536677889"));
		myDataBase.getMemberList().add(new Member("515", "Yair", "Alon", new Address("Haifa", "Ben Gurion", "55", "Israel"), "0547788991"));

		
		// -----------------------------------------> Orders
		myDataBase.getOrderList().add(new Order("1", 15, 4, 10, "501", myDataBase.getTaxiList().get(0), 45.5));
		myDataBase.addOrderToMember("501", myDataBase.getOrderList().get(0));
		myDataBase.addTaxiToMember("501", myDataBase.getTaxiList().get(0));
		myDataBase.getOrderList().add(new Order("2", 16, 4, 12, "502", myDataBase.getTaxiList().get(1), 50.0));
		myDataBase.addOrderToMember("502", myDataBase.getOrderList().get(1));
		myDataBase.addTaxiToMember("502", myDataBase.getTaxiList().get(1));
		myDataBase.getOrderList().add(new Order("3", 17, 4, 9, "503", myDataBase.getTaxiList().get(2), 42.75));
		myDataBase.addOrderToMember("503", myDataBase.getOrderList().get(2));
		myDataBase.addTaxiToMember("503", myDataBase.getTaxiList().get(2));
		myDataBase.getOrderList().add(new Order("4", 18, 4, 14, "504", myDataBase.getTaxiList().get(3), 55.25));
		myDataBase.addOrderToMember("504", myDataBase.getOrderList().get(3));
		myDataBase.addTaxiToMember("504", myDataBase.getTaxiList().get(3));
		myDataBase.getOrderList().add(new Order("5", 19, 4, 8, "505", myDataBase.getTaxiList().get(4), 30.0));
		myDataBase.addOrderToMember("505", myDataBase.getOrderList().get(4));
		myDataBase.addTaxiToMember("505", myDataBase.getTaxiList().get(4));
		myDataBase.getOrderList().add(new Order("1", 20, 4, 17, "506", myDataBase.getTaxiList().get(5), 60.0));
		myDataBase.addOrderToMember("506", myDataBase.getOrderList().get(5));
		myDataBase.addTaxiToMember("506", myDataBase.getTaxiList().get(5));
		myDataBase.getOrderList().add(new Order("2", 21, 4, 11, "507", myDataBase.getTaxiList().get(6), 48.0));
		myDataBase.addOrderToMember("507", myDataBase.getOrderList().get(6));
		myDataBase.addTaxiToMember("507", myDataBase.getTaxiList().get(6));
		myDataBase.getOrderList().add(new Order("3", 22, 4, 15, "508", myDataBase.getTaxiList().get(7), 52.0));
		myDataBase.addOrderToMember("508", myDataBase.getOrderList().get(7));
		myDataBase.addTaxiToMember("508", myDataBase.getTaxiList().get(7));
		myDataBase.getOrderList().add(new Order("4", 23, 4, 13, "509", myDataBase.getTaxiList().get(8), 40.5));
		myDataBase.addOrderToMember("509", myDataBase.getOrderList().get(8));
		myDataBase.addTaxiToMember("509", myDataBase.getTaxiList().get(8));
		myDataBase.getOrderList().add(new Order("5", 24, 4, 16, "510", myDataBase.getTaxiList().get(9), 57.75));
		myDataBase.addOrderToMember("510", myDataBase.getOrderList().get(9));
		myDataBase.addTaxiToMember("510", myDataBase.getTaxiList().get(9));
		
		ArrayList<Order> aliceOrders = new ArrayList<Order>();
		aliceOrders.add(myDataBase.getOrderList().get(0));
		aliceOrders.add(myDataBase.getOrderList().get(5));

		ArrayList<Order> bobOrders = new ArrayList<Order>();
		bobOrders.add(myDataBase.getOrderList().get(1));
		bobOrders.add(myDataBase.getOrderList().get(6));

		ArrayList<Order> charlieOrders = new ArrayList<Order>();
		charlieOrders.add(myDataBase.getOrderList().get(2));

		ArrayList<Order> dinaOrders = new ArrayList<Order>();
		dinaOrders.add(myDataBase.getOrderList().get(3));
		dinaOrders.add(myDataBase.getOrderList().get(8));

		ArrayList<Order> eliOrders = new ArrayList<Order>();
		eliOrders.add(myDataBase.getOrderList().get(4));
		eliOrders.add(myDataBase.getOrderList().get(9));
		
		// -----------------------------------------> Managers
		myDataBase.getManagerList().add(new Main_Manager("100000001", "Alice", "Levi",
		    new Address("Haifa", "Hanamal", "65", "Israel"),
		    "0521234561", aliceTaxies, aliceOrders, "aliceL", "11111"));
		myDataBase.getManagerList().add(new Manager("100000002", "Bob", "Cohen",
		    new Address("Tel Aviv", "Ibn Gabirol", "12", "Israel"),
		    "0521234562", bobTaxies, bobOrders));
		myDataBase.getManagerList().add(new Manager("100000003", "Charlie", "Mizrahi",
		    new Address("Jerusalem", "Jaffa", "101", "Israel"),
		    "0521234563", charlieTaxies, charlieOrders));
		myDataBase.getManagerList().add(new Manager("100000004", "Dina", "Peretz",
		    new Address("Eilat", "Hatmarim", "7", "Israel"),
		    "0521234564", dinaTaxies, dinaOrders));
		myDataBase.getManagerList().add(new Manager("100000005", "Eli", "Azoulay",
		    new Address("Beer Sheva", "Rager", "45", "Israel"),
		    "0521234565", eliTaxies, eliOrders));

		// -----------------------------------------> Stations
		ArrayList<Taxi> centralStationTaxies = new ArrayList<Taxi>();
        Station centralStation = new Station("Central station", centralStationTaxies);
        centralStationTaxies.add(myDataBase.getTaxiList().get(0)); 
        centralStationTaxies.add(myDataBase.getTaxiList().get(1)); 
        centralStationTaxies.add(myDataBase.getTaxiList().get(5)); 
        centralStationTaxies.add(myDataBase.getTaxiList().get(10)); 
        myDataBase.getStationList().add(centralStation);
	        
        ArrayList<Taxi> airportStationTaxies = new ArrayList<Taxi>();
        Station airportStation = new Station("Airport station", airportStationTaxies);
        airportStationTaxies.add(myDataBase.getTaxiList().get(2)); 
        airportStationTaxies.add(myDataBase.getTaxiList().get(6)); 
        airportStationTaxies.add(myDataBase.getTaxiList().get(11)); 
        airportStationTaxies.add(myDataBase.getTaxiList().get(13)); 
        myDataBase.getStationList().add(airportStation);
	        
        ArrayList<Taxi> haifaStationTaxies = new ArrayList<Taxi>();
        Station haifaStation = new Station("Haifa station", haifaStationTaxies);	        
        haifaStationTaxies.add(myDataBase.getTaxiList().get(3)); 
        haifaStationTaxies.add(myDataBase.getTaxiList().get(4)); 
        haifaStationTaxies.add(myDataBase.getTaxiList().get(7)); 
        myDataBase.getStationList().add(haifaStation);
	    
		// -----------------------------------------> system
		Scanner in = new Scanner(System.in);
		int button = -1;
		while(button!=0) {	
			System.out.println("Welcome to the Main Menu. To exit at any time press 0, or: ");
			System.out.println("--1 for main manager--");
			System.out.println("--2 for manager--");
			System.out.println("--3 for member--");
			System.out.println("please choose: ");
			button = in.nextInt();
			in.nextLine();
			switch (button) {
			case 1: // for main manager
				do{
					System.out.println("enter your username: "); 
					String managerUsername = in.nextLine();
					System.out.println("enter your password: "); 
					String managerPassword = in.nextLine();
					if(validateMainManagerCredentials(myDataBase.getManagerList(), managerUsername, managerPassword)) {
						if(managerUsername.equals("system") && managerPassword.equals("12345"))
							adminLogin(myDataBase, cityArray);
						else
							mainManagerLogin(myDataBase, cityArray);
						break;
					} else {
						System.out.println("Validition failed.");
						System.out.println("If you want to try again press 1, to exit press 2: ");
						button = in.nextInt();
						in.nextLine();
					}
				} while(button!=2);
				break;
			case 2: // for manager
				do{
					System.out.println("enter manager ID - 9 digits: ");
					String managerCode = in.nextLine();
					if(isManagerCodeExists(myDataBase.getManagerList(), managerCode)) {
						regularManagerLogin(myDataBase, cityArray, managerCode);
						break;
					}
					else {
						System.out.println("ID doesn't exists.");
						System.out.println("If you want to try again press 1, to exit press 2: ");
						button = in.nextInt();
						in.nextLine();
					}
				} while(button!=2);
				break;
			case 3: // for member
				do{
					System.out.println("enter member code: ");
					String membercode = in.nextLine();
					if(isMemberCodeExists(myDataBase.getMemberList(), membercode)) {
						MemberLogin(myDataBase, cityArray, membercode);
						break;
					}
					else {
						System.out.println("Code doesn't exists.");
						System.out.println("If you want to try again press 1, to exit press 2: ");
						button = in.nextInt();
						in.nextLine();
					}
				} while(button!=2);
				break;
			}
		}
        System.out.println("Goodbye!");
	}
}
