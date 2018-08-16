package com.cs.trading.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Company;
import com.cs.trading.Services.CompanyService;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.TransactionService;

@RestController
public class MarketController {

	@Autowired
	CompanyService cs;
	
	@Autowired
	OrderService os;
	
	@Autowired
	TransactionService ts;
	
	@RequestMapping(value = "/marketSummary", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET)
	public Object getMarketSummary() {
		
		List<Company> companies = cs.findAll();
		for(Company company: companies) {
			
			double price = ts.findLastTransactionBySymbol(company.getSymbol()).getPrice();
		}
		return null;
		
	}
}
