package com.cs.trading.Controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Services.OrderService;

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

	@RequestMapping(value="/placeLimitBuyOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitBuyOrder(Principal principal, Double price, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeLimitSellOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitSellOrder(Principal principal, Double price, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.LIMIT, Status.OPEN, Side.SELL, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeMarketBuyOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketBuyOrder(Principal principal, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.MARKET, Status.OPEN, Side.BUY, new Date(), 0, 0.0, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeMarketSellOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketSellOrder(Principal principal, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.MARKET, Status.OPEN, Side.SELL, new Date(), 0, 0.0, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/deleteOrder/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public int deleteOrder(Principal principal, @PathVariable(value="id") int id) {
		return os.deleteOrder(id, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/updateOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int updateOrder(Principal principal, int orderId, @RequestParam(value = "orderType", required = false) OrderType orderType, @RequestParam(value = "price", required = false) Double price, @RequestParam(value = "quantity", required = false) Integer quantity) {
		return os.updateOrder(orderId, orderType, price, quantity, Integer.parseInt(principal.getName()));
	}
	
	@RequestMapping(value = "/quotes/{symbol}", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET)
	public Object getQuote(@PathVariable(value="symbol") String symbol ,@RequestParam(value="start") String start, @RequestParam(value="end") String end, @RequestParam(value="sort", required = false) String sort){
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/HH:mm:ss.SSS");
		List<Order> orders;
		
		
		String _sort = (sort.equalsIgnoreCase("DESC"))? "DESC": "ASC";
		try {
			orders = os.findOrdersBySymbol(symbol, formatter.parse(start), formatter.parse(end),_sort);
			return orders;
		} catch (ParseException e) {

			System.out.println(e.getMessage());
			return "Please pass the date format dd/MM/yyyy/HH:mm:ss.SSS";
		}
	}
	
}


