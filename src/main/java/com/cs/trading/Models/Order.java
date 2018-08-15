package com.cs.trading.Models;

import java.util.Date;

public class Order implements Comparable<Order>{

	private int id;
	private OrderType orderType;
	private Status status;
	private Side side;
	private Date timeStamp;
	private int filledQuantity;
	private double price;
	private int quantity;
	private String tickerSymbol;
	private int ownerId;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int id, OrderType orderType, Status status, Side side, Date timeStamp,
			int filledQuantity, double price, int quantity, String tickerSymbol, int ownerId) {
		super();
		this.id = id;
		this.orderType = orderType;
		this.status = status;
		this.side = side;
		this.timeStamp = timeStamp;
		this.filledQuantity = filledQuantity;
		this.price = price;
		this.quantity = quantity;
		this.tickerSymbol = tickerSymbol;
		this.ownerId= ownerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getFilledQuantity() {
		return filledQuantity;
	}

	public void setFilledQuantity(int filledQuantity) {
		this.filledQuantity = filledQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		if (this.timeStamp == null || o.getTimeStamp() == null) {
		      return 0;
		}
		return o.getTimeStamp().compareTo(this.timeStamp);
	}

}

