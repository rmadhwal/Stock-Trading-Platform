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
	
/*	@RequestMapping("/orders/{id}")
	public Transaction findTransactionById(@PathVariable(value="id") int id) {
		return or.findTransactionById(id);
	}


	@RequestMapping(value="/placeLimitBuyTransaction", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitBuyTransaction(Principal principal, double price, int quantity, String tickerSymbol) {
		return os.placeTransaction(TransactionType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}*/

}

