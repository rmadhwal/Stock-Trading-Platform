package com.cs.trading.Models;

public class Trader extends User{
	
	
	

	public Trader() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Trader(String firstName, String lastName, String password, long phone, String email) {
		super(0, firstName, lastName, password, phone, email, Role.TRADER);
		// TODO Auto-generated constructor stub
	}


	public Trader(int id, String firstName, String lastName, String password, long phone, String email) {
		super(id, firstName, lastName, password, phone, email, Role.TRADER);
		// TODO Auto-generated constructor stub
	}


}
