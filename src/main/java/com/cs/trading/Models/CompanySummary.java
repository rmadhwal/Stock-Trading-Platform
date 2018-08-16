package com.cs.trading.Models;

public class CompanySummary {

	private String symbol;
	private String name;
	private int volume;
	private double lastPrice;
	
	public double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
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
	public CompanySummary(String symbol, String name,int volume, double lastPrice) {
		super();
		this.lastPrice = lastPrice;
		this.volume = volume;
		this.symbol = symbol;
		this.name = name;
	}
	public CompanySummary() {
		super();
	}
	
	
	
}
