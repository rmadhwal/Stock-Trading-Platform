package com.cs.trading.Services;

import com.cs.trading.Models.*;
import com.cs.trading.Repositories.TraderRepository;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;


@Component
public class TraderService {

    @Autowired
    TraderRepository tr;

    @Autowired
    OrderService os;

	public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, double price, int quantity, String tickerSymbol, int traderId) {
        return tr.placeOrder(orderType, status, side, timestamp, filledQuantity, price, quantity, tickerSymbol, traderId);
    }

    public int deleteOrder(int orderId, int traderId) {
	    if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getOwnerId() == traderId && item.getStatus() == Status.OPEN)))
	        return tr.deleteOrder(orderId);
	    return 0;
    }

    public int updateOrder(int orderId, OrderType orderType, Double price, Integer quantity, int traderId) {
	    if(orderType == null && price == null && quantity == null)
	        return 0;
        if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getId() == orderId && item.getOwnerId() == traderId && item.getStatus() == Status.OPEN && (quantity == null || quantity > item.getFilledQuantity())))) {
            return tr.updateOrder(orderId, orderType, price, quantity);
        }
        else {
            return 0;
        }
    }
}
