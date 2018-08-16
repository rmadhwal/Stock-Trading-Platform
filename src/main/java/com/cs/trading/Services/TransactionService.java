package com.cs.trading.Services;

import com.cs.trading.Models.Transaction;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class TransactionService {

    @Autowired
    OrderRepository or;

    @Autowired
    TransactionRepository tr;

	public List<Transaction> listAllTransactions() {
        return tr.findAll();
    }

    public Transaction findTransactionById(int id) {
	    return tr.findTransactionById(id);
    }

    public int createNewTransaction(int buyOrderId, int sellOrderId, int quantity, double price, Date timestamp){
	    return tr.addTransaction(buyOrderId, sellOrderId, quantity, price, timestamp);
    }
    public Transaction findLastTransactionBySymbol(String symbol) {
    	return tr.findLastTransactionBySymbol(symbol);
    }

}
