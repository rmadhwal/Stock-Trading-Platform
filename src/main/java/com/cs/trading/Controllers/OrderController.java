package com.cs.trading.Controllers;

import com.cs.trading.Models.Order;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
	
	@Autowired
	OrderRepository or;

	@Autowired
	OrderService os;
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public List<Order> returnAllOrders() {
		return or.findAll();
	}
	
	@RequestMapping("/orders/{id}")
	public Order findOrderById(@PathVariable(value="id") int id) {
		return or.findOrderById(id);
	}

	@RequestMapping("/ordersByTrader/{traderid}")
	public List<Order> findOrderByTraderId(@PathVariable(value="traderid") int traderid) {
		return os.findOrdersByTraderId(traderid);
	}

	@RequestMapping("/ordersBySymbol/{symbol}")
	public List<Order> findOrderBySymbol(@PathVariable(value="symbol") String symbol) {
		return os.findOrdersBySymbol(symbol);
	}
}


