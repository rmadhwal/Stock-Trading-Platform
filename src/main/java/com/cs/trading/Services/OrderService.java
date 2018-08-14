package com.cs.trading.Services;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class OrderService {

    @Autowired
    OrderRepository or;

	public List<Order> listAllOrders() {
        return or.findAll();
    }

    public Order findOrderById(int id) {
	    return or.findOrderById(id);
    }

    public List<Order> findOrdersByTraderId(int traderId) {
	    return or.findOrderByTraderId(traderId);
    }
}
