package com.cs.trading.Services;

import com.cs.trading.Models.*;
import com.cs.trading.Repositories.TraderRepository;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


@Component
public class TraderService {

    @Autowired
    TraderRepository tr;

	public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, double price, int quantity, String tickerSymbol, int traderId) {
        return tr.placeOrder(orderType, status, side, timestamp, filledQuantity, price, quantity, tickerSymbol, traderId);
    }
}
