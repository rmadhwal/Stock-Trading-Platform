package com.cs.trading.Models;

public class Company {
	private String symbol;
	private String name;
	private int sector_id;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSector_id() {
		return sector_id;
	}
	public void setSector_id(int sector_id) {
		this.sector_id = sector_id;
	}
	public Company(String symbol, String name, int sector_id) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.sector_id = sector_id;
	}
	
}
