package com.cs.trading.Controllers;

import com.cs.trading.Models.Transaction;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.TraderService;
import com.cs.trading.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

	@Autowired
	TransactionService ts;

	@RequestMapping(value="/transactions", method=RequestMethod.GET)
	public List<Transaction> returnAllTransactions() {
		return ts.listAllTransactions();
	}

	@RequestMapping(value="/transactions/symbol/{symbol}", method=RequestMethod.GET)
	public List<Transaction> returnTransactionsBySymbol(@PathVariable(value="symbol") String symbol) {
		return ts.listAllTransactionsBySymbol(symbol);
	}


	@RequestMapping(value="/transactions/traderid/{id}", method=RequestMethod.GET)
	public List<Transaction> returnAllTransactionsByTraderId(@PathVariable(value="id") int id) {
		return ts.listAllTransactionsByTraderId(id);
	}

	@RequestMapping(value="/transactions/symbol", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public List<Transaction> findTransactionBySymbolWithFilters(String symbol, @RequestParam(value = "filter", required = false) String filter) {
		if(filter == null)
			return ts.listAllTransactionsBySymbol(symbol);
		else
			return ts.listAllTransactionsBySymbolWithFilters(symbol, filter);
	}
}

