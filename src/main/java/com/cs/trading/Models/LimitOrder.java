package com.cs.trading.Models;

import java.util.Date;

public class LimitOrder extends Order{

	public LimitOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitOrder(int id, OrderType orderType, Status status, Side side, Date timeStamp,
			int filledQuantity, double price, int quantity, String tickerSymbol, int ownerId) {
		super(id, orderType, status, side, timeStamp, filledQuantity, price, quantity, tickerSymbol, ownerId);
		// TODO Auto-generated constructor stub
	}
	
	
}
