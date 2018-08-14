package com.cs.trading.Models;

public class Trader extends User{
	
	
	

	public Trader() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Trader(int id, String firstName, String lastName, String password, long phone, String email) {
		super(id, firstName, lastName, password, phone, email);
		// TODO Auto-generated constructor stub
	}


	public void placeOrder(Order o) {
		
	}

}
