package com.cs.trading.Controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.Status;
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Services.AdminService;

@RestController
public class TraderController {
	
	@Autowired
	AdminRepository ar; 
	
	@Autowired
	AdminService as; 
	
	@Autowired
	TraderRepository tr; 
	
	
	@RequestMapping(value="/traders", method=RequestMethod.GET)
	public List<User> returnAllTraders() {
		return ar.listAllTraders();
	}
	
	@RequestMapping(value="/traders/createTrader", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int createNewTrader(String firstName, String lastName, String password, Long phone, String email) {
		Trader trader = new Trader(firstName, lastName, password, (Long)phone, email);
		return as.createTrader(trader);
	}
	
	@RequestMapping(value="/traders/deleteTrader/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public int deleteExistingTrader(@PathVariable(value="id") int id) {
		return ar.deleteExistingTrader(id);
	}
	
	@RequestMapping("/traders/byId/{id}")
	public Trader findTraderById(@PathVariable(value="id") int id) {
		return (Trader) ar.getTrader(id).get(0);
	}
	
	@RequestMapping("/traders/byId/latestOrder/{id}")
	public Date indLatestOrderByTraderId(@PathVariable(value="id") int id) {
		return ar.retrieveLatestTimeTraderLastOrder(id);
	}

	@RequestMapping("/traders/listOrdersByStatus")
	public HashMap<Status, List<Order>> listTotalNumerOfOrdersByStatus() {
		return ar.retrieveOrdersByStatus();
	}
	
	@RequestMapping("/traders/topNTradersByNum/{limit}")
	public List<Trader> listTopNTradersByNum(@PathVariable(value="limit") int limit) {
		return tr.findTopNTradersByNumber(limit);
	}
}


