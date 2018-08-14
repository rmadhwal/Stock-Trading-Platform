package com.cs.trading.Models;

import java.util.Date;

public class MarketOrder extends Order{

	public MarketOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarketOrder(int id, OrderType orderType, Status status, String username, Side side, Date timeStamp,
			int filledQuantity, double price, int quantity, String tickerSymbol, int ownerId) {
		super(id, orderType, status, side, timeStamp, filledQuantity, price, quantity, tickerSymbol, ownerId);
		// TODO Auto-generated constructor stub
	}

}
