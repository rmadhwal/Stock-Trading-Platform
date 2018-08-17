package com.cs.trading.Services;

import com.cs.trading.Models.EventType;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Log;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class LogService {

    @Autowired
    OrderRepository or;

    @Autowired
    OrderService os;

    @Autowired
    LogRepository logRepository;

	public List<Log> listAllLogs() {
        return logRepository.findAll();
    }

    /*public List<Log> listAllLogsBySymbol(String symbol) {
	    List<Log> allLogs = listAllLogs();
	    List<Order> allOrders = os.listAllOrders();
	    return allLogs.stream()
                .filter(transaction -> allOrders.stream().anyMatch(order -> order.getId() == transaction.getBuyOrderId() && order.getTickerSymbol().equals(symbol) ))
                .collect(Collectors.toList());
    }

    public List<Log> listAllLogsByTraderId(int traderId) {
        List<Log> allLogs = listAllLogs();
        List<Order> allOrders = os.listAllOrders();
        return allLogs.stream()
                .filter(transaction -> allOrders.stream().anyMatch(order -> (order.getId() == transaction.getBuyOrderId() || order.getId() == transaction.getSellOrderId()) && order.getOwnerId() == traderId ))
                .collect(Collectors.toList());
    }

    public List<Log> listAllLogsBySymbolWithFilters(String symbol, String filter){
	    List<Log> symbolSortedLogs = listAllLogsBySymbol(symbol);
	    if(!filter.contains("buyorderid"))
	        symbolSortedLogs.forEach(transaction -> transaction.setBuyOrderId(null));
        if(!filter.contains("sellorderid"))
            symbolSortedLogs.forEach(transaction -> transaction.setSellOrderId(null));
        if(!filter.contains("price"))
            symbolSortedLogs.forEach(transaction -> transaction.setPrice(null));
        if(!filter.contains("quantity"))
            symbolSortedLogs.forEach(transaction -> transaction.setQuantityTraded(null));
        if(!filter.contains("timestamp"))
            symbolSortedLogs.forEach(transaction -> transaction.setTimeStamp(null));
	    return symbolSortedLogs;
    }*/

    public Log findLogById(int id) {
	    return logRepository.findLogById(id);
    }

    public int createNewLog(EventType eventType, Integer buySideOrderOwnerId, Integer sellSideOrderOwnerId, Integer quantity, Double price, OrderType buyOrderType, OrderType sellOrderType, String tickerSymbol, Date timestamp){
	    return logRepository.addLog(eventType, buySideOrderOwnerId, sellSideOrderOwnerId, quantity, price, buyOrderType, sellOrderType, tickerSymbol, timestamp);
    }
    /*public Log findLastLogBySymbol(String symbol) {
    	return tr.findLastLogBySymbol(symbol);
    }*/


}
