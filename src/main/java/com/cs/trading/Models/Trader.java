package com.cs.trading.Models;

public class Trader extends User{
	
	//for story 16
	private int numTrade; 
	private int volume; 
	

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


	public Trader(int id, String firstName, String lastName, String password, long phone, String email, int numTrade) {
		super(id, firstName, lastName, password, phone, email, Role.TRADER);
		this.numTrade = numTrade; 
		// TODO Auto-generated constructor stub
	}

	public Trader(int id, String firstName, String lastName, String password, long phone, int volume, String email) {
		super(id, firstName, lastName, password, phone, email, Role.TRADER);
		this.volume = volume; 
		// TODO Auto-generated constructor stub
	}


	public int getNumTrades() {
		return numTrade;
	}


	public void setNumTrades(int numTrades) {
		this.numTrade = numTrades;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}

}
