package com.cs.trading.Models;

public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String firstName, String lastName, String password, long phone, String email) {
		super(id, firstName, lastName, password, phone, email, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}

	

}
