package com.cs.trading.Models;

public class Trader extends User{
	
	
	

	public Trader() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Trader(int id, String firstName, String lastName, String password, long phone, String email, Role role) {
		super(id, firstName, lastName, password, phone, email, role);
		// TODO Auto-generated constructor stub
	}





	public void placeOrder(Order o) {
		
	}

}
