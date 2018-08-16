package com.cs.trading.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.CompanySummary;
import com.cs.trading.Models.Transaction;
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
	
	@RequestMapping(value = "/marketSummary", 
			produces={MediaType.APPLICATION_JSON_VALUE}, 
			method=RequestMethod.GET)
	public Object getMarketSummary(@RequestParam(value= "order", required=false) Optional<String> order) {
		
		List<CompanySummary> summaries= new ArrayList<CompanySummary>();
		List<Company> companies = cs.findAll();
		
		for(Company company: companies) {
			int volume = ts.listAllTransactionsBySymbol(company.getSymbol()).stream().mapToInt(o->o.getQuantityTraded()).sum();
			
			Transaction lastTransaction = ts.findLastTransactionBySymbol(company.getSymbol());
			if(lastTransaction == null) continue;
			else { 
				CompanySummary companySummary = new CompanySummary(company.getSymbol(),company.getName(),volume,lastTransaction.getPrice());
				summaries.add(companySummary);
			}
		}
		summaries.sort(new Comparator<CompanySummary>() {
			public int compare(CompanySummary cs1, CompanySummary cs2) {
				return 
					cs1.getVolume()>cs2.getVolume()? -1
					:cs1.getVolume()<cs2.getVolume()? 1
					:0; 
			}
		});
		if(order.isPresent()) {
			if(order.get().equalsIgnoreCase("ASC")) {
				Collections.reverse(summaries);
			}
		}
		
		return summaries;
		
	}
}
