package com.cs.trading.Services;

import com.cs.trading.Models.*;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class OrderService {

    @Autowired
    OrderRepository or;

    @Autowired
    TransactionRepository tr;

    @Autowired
    OrderService os;

    public List<Order> findOrdersBySide(Side side) {
        return or.findOrdersBySide(side);
    }
    
    public List<Order> findOrdersByStatus(Status status) {
	    return or.findOrdersByStatus(status);
    }

    public List<Order> findOrdersByType(OrderType type) {
        return or.findOrdersByType(type);
    }

    public List<Order> findOrdersByStatus(Status status) {
        return or.findOrdersByStatus(status);
    }

    private void matchOrder(Order orderToBeMatched) {
        LinkedList<Order> potentialMatchingOrders = new LinkedList<Order>();
        if(orderToBeMatched.getOrderType().equals(OrderType.LIMIT)) {
            if (orderToBeMatched.getSide() == Side.BUY) {
                potentialMatchingOrders = findOrdersBySide(Side.SELL)
                        .stream()
                        .filter(order -> order.getTickerSymbol().equals(orderToBeMatched.getTickerSymbol()) && order.getPrice() <= orderToBeMatched.getPrice() && order.getStatus().equals(Status.OPEN))
                        .sorted(Comparator.comparing(Order::getPrice))
                        .collect(Collectors.toCollection(LinkedList::new));
            } else {
                List<Order> buyOrders = findOrdersBySide(Side.BUY);
                potentialMatchingOrders = findOrdersBySide(Side.BUY)
                        .stream()
                        .filter(order -> order.getTickerSymbol().equals(orderToBeMatched.getTickerSymbol()) && order.getPrice() >= orderToBeMatched.getPrice() && order.getStatus().equals(Status.OPEN))
                        .sorted(Comparator.comparing(Order::getPrice).reversed())
                        .collect(Collectors.toCollection(LinkedList::new));
            }
        }
        else {
            if (orderToBeMatched.getSide() == Side.BUY) {
                potentialMatchingOrders = findOrdersBySide(Side.SELL)
                        .stream()
                        .filter(order -> order.getTickerSymbol().equals(orderToBeMatched.getTickerSymbol()) && order.getOrderType().equals(OrderType.LIMIT) && order.getStatus().equals( Status.OPEN))
                        .sorted(Comparator.comparing(Order::getPrice))
                        .collect(Collectors.toCollection(LinkedList::new));
            }
            else {
                List<Order> buyOrders = findOrdersBySide(Side.BUY);
                potentialMatchingOrders = findOrdersBySide(Side.BUY)
                        .stream()
                        .filter(order -> order.getTickerSymbol().equals(orderToBeMatched.getTickerSymbol()) && order.getOrderType().equals(OrderType.LIMIT) && order.getStatus().equals(Status.OPEN))
                        .sorted(Comparator.comparing(Order::getPrice).reversed())
                        .collect(Collectors.toCollection(LinkedList::new));
            }
        }
                while (!potentialMatchingOrders.isEmpty() && orderToBeMatched.getStatus() != Status.FULFILLED) {
                    Order sellSideOrder;
                    Order buySideOrder;
                    if (orderToBeMatched.getSide() == Side.BUY) {
                        sellSideOrder = potentialMatchingOrders.removeFirst();
                        buySideOrder = orderToBeMatched;
                    } else {
                        sellSideOrder = orderToBeMatched;
                        buySideOrder = potentialMatchingOrders.removeFirst();
                    }
                    int transactionQuantity;
                    if(sellSideOrder.getQuantity() - sellSideOrder. getFilledQuantity() > buySideOrder.getQuantity() - buySideOrder.getFilledQuantity()) {
                        transactionQuantity = buySideOrder.getQuantity() - buySideOrder.getFilledQuantity();
                        buySideOrder.setFilledQuantity(buySideOrder.getQuantity());
                        buySideOrder.setStatus(Status.FULFILLED);
                        sellSideOrder.setFilledQuantity(sellSideOrder.getFilledQuantity()+transactionQuantity);
                    }
                    else if(buySideOrder.getQuantity() - buySideOrder.getFilledQuantity() > sellSideOrder.getQuantity() - sellSideOrder. getFilledQuantity()) {
                        transactionQuantity = sellSideOrder.getQuantity() - sellSideOrder. getFilledQuantity();
                        sellSideOrder.setFilledQuantity(sellSideOrder.getQuantity());
                        sellSideOrder.setStatus(Status.FULFILLED);
                        buySideOrder.setFilledQuantity(buySideOrder.getFilledQuantity()+transactionQuantity);
                    }
                    else {
                        transactionQuantity = sellSideOrder.getQuantity() - sellSideOrder. getFilledQuantity();
                        sellSideOrder.setFilledQuantity(sellSideOrder.getQuantity());
                        sellSideOrder.setStatus(Status.FULFILLED);
                        buySideOrder.setFilledQuantity(buySideOrder.getFilledQuantity()+transactionQuantity);
                        buySideOrder.setStatus(Status.FULFILLED);
                    }
                    double transactionPrice;
                    if(sellSideOrder.getOrderType().equals(OrderType.MARKET))
                        transactionPrice = buySideOrder.getPrice();
                    else
                        transactionPrice = sellSideOrder.getPrice();
                    tr.addTransaction(buySideOrder.getId(), sellSideOrder.getId(), transactionQuantity, transactionPrice, new Date());
                    updateOrder(buySideOrder);
                    updateOrder(sellSideOrder);
                }
    }

	public List<Order> listAllOrders() {
        return or.findAll();
    }

    public List<Order> findOrdersGroupedBy(String parameter){
        if(parameter.equals("side"))
            return or.findOrdersGroupedBySide();
        else if(parameter.equals("type"))
            return or.findOrdersGroupedByType();
        else if(parameter.equals("status"))
            return or.findOrdersGroupedByStatus();
        else
            return new ArrayList<Order>();
    }

    public Order findOrderById(int id) {
	    return or.findOrderById(id);
    }


    public List<Order> findOrdersByTraderId(int traderId) {
	    return or.findOrdersByTraderId(traderId);
    }

    public List<Order> findOrdersBySymbol(String tickerSymbol) {
	    return or.findOrdersBySymbol(tickerSymbol);
    }

    public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, Integer filledQuantity, Double price, Integer quantity, String tickerSymbol, int traderId) {
        int orderId =  or.placeOrder(orderType, status, side, timestamp, filledQuantity, price, quantity, tickerSymbol, traderId);
        matchOrder(new Order(orderId, orderType, status, side, timestamp, filledQuantity, price, quantity, tickerSymbol, traderId));
        return orderId;
    }

    public int deleteOrder(int orderId, int traderId) {
        if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getOwnerId() == traderId && item.getStatus().equals(Status.OPEN))))
            return or.changeOrderStatus(orderId, Status.CANCELLED);
        return 0;
    }

    public int updateOrder(Order order) {
        return or.updateOrder(order);
    }

    public int updateOrder(int orderId, OrderType orderType, Double price, Integer quantity, int traderId) {
        if(orderType == null && price == null && quantity == null)
            return 0;
        if(os.findOrdersByTraderId(traderId).stream().anyMatch(item -> (item.getId() == orderId && item.getOwnerId() == traderId && item.getStatus().equals(Status.OPEN) && (quantity == null || quantity >= item.getFilledQuantity())))) {
            or.updateOrder(orderId, orderType, price, quantity);
            matchOrder(findOrderById(orderId));
            return orderId;
        }
        else {
            return 0;
        }
    }
}
