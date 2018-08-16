package com.cs.trading.Services;

import com.cs.trading.Models.*;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class TransactionService {

    @Autowired
    OrderRepository or;

    @Autowired
    OrderService os;

    @Autowired
    TransactionRepository tr;

	public List<Transaction> listAllTransactions() {
        return tr.findAll();
    }

    public List<Transaction> listAllTransactionsBySymbol(String symbol) {
	    List<Transaction> allTransactions = listAllTransactions();
	    List<Order> allOrders = os.listAllOrders();
	    return allTransactions.stream()
                .filter(transaction -> allOrders.stream().anyMatch(order -> order.getId() == transaction.getBuyOrderId() && order.getTickerSymbol().equals(symbol) ))
                .collect(Collectors.toList());
    }

    public List<Transaction> listAllTransactionsByTraderId(int traderId) {
        List<Transaction> allTransactions = listAllTransactions();
        List<Order> allOrders = os.listAllOrders();
        return allTransactions.stream()
                .filter(transaction -> allOrders.stream().anyMatch(order -> (order.getId() == transaction.getBuyOrderId() || order.getId() == transaction.getSellOrderId()) && order.getOwnerId() == traderId ))
                .collect(Collectors.toList());
    }

    public List<Transaction> listAllTransactionsBySymbolWithFilters(String symbol, String filter){
	    List<Transaction> symbolSortedTransactions = listAllTransactionsBySymbol(symbol);
	    if(!filter.contains("buyorderid"))
	        symbolSortedTransactions.forEach(transaction -> transaction.setBuyOrderId(null));
        if(!filter.contains("sellorderid"))
            symbolSortedTransactions.forEach(transaction -> transaction.setSellOrderId(null));
        if(!filter.contains("price"))
            symbolSortedTransactions.forEach(transaction -> transaction.setPrice(null));
        if(!filter.contains("quantity"))
            symbolSortedTransactions.forEach(transaction -> transaction.setQuantityTraded(null));
        if(!filter.contains("timestamp"))
            symbolSortedTransactions.forEach(transaction -> transaction.setTimeStamp(null));
	    return symbolSortedTransactions;
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
