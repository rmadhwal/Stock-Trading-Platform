package com.cs.trading.Services;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class OrderService {

    @Autowired
    OrderRepository or;

    @Autowired
    TraderRepository tr;

    @Autowired
    OrderService os;

	public List<Order> listAllOrders() {
        return or.findAll();
    }

    public Order findOrderById(int id) {
	    return or.findOrderById(id);
    }

    public List<Order> findOrdersByTraderId(int traderId) {
	    return or.findOrdersByTraderId(traderId);
    }

    public List<Order> findOrdersBySymbol(String tickerSymbol) {
	    return or.findOrdersBySymbol(tickerSymbol);
    }

    public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, double price, int quantity, String tickerSymbol, int traderId) {
        return or.placeOrder(orderType, status, side, timestamp, filledQuantity, price, quantity, tickerSymbol, traderId);
    }

    public int deleteOrder(int orderId, int traderId) {
        if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getOwnerId() == traderId && item.getStatus() == Status.OPEN)))
            return or.deleteOrder(orderId);
        return 0;
    }

    public int updateOrder(int orderId, OrderType orderType, Double price, Integer quantity, int traderId) {
        if(orderType == null && price == null && quantity == null)
            return 0;
        if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getId() == orderId && item.getOwnerId() == traderId && item.getStatus() == Status.OPEN && (quantity == null || quantity > item.getFilledQuantity())))) {
            return or.updateOrder(orderId, orderType, price, quantity);
        }
        else {
            return 0;
        }
    }
}
