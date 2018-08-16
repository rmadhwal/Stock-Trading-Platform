package com.cs.trading.Controllers;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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

	@RequestMapping("/orders/side/{side}")
	public List<Order> findOrdersBySide(@PathVariable(value="side") Side side) {
		return os.findOrdersBySide(side);
	}

	@RequestMapping("/orders/type/{type}")
	public List<Order> findOrdersByType(@PathVariable(value="type") OrderType type) {
		return os.findOrdersByType(type);
	}

	@RequestMapping("/orders/status/{status}")
	public List<Order> findOrdersByStatus(@PathVariable(value="status") Status status) {
		return os.findOrdersByStatus(status);
	}

	@RequestMapping("/orders/trader/{traderid}")
	public List<Order> findOrderByTraderId(@PathVariable(value="traderid") int traderid) {
		return os.findOrdersByTraderId(traderid);
	}

	@RequestMapping("/orders/symbol/{symbol}")
	public List<Order> findOrderBySymbol(@PathVariable(value="symbol") String symbol) {
		return os.findOrdersBySymbol(symbol);
	}

	@RequestMapping(value="/orders/limit/buy", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitBuyOrder(Principal principal, Double price, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/orders/limit/sell", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitSellOrder(Principal principal, Double price, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.LIMIT, Status.OPEN, Side.SELL, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/orders/market/buy", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketBuyOrder(Principal principal, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.MARKET, Status.OPEN, Side.BUY, new Date(), 0, 0.0, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/orders/market/sell", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketSellOrder(Principal principal, Integer quantity, String tickerSymbol) {
		return os.placeOrder(OrderType.MARKET, Status.OPEN, Side.SELL, new Date(), 0, 0.0, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/orders/cancel/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public int deleteOrder(Principal principal, @PathVariable(value="id") int id) {
		return os.deleteOrder(id, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/orders/update", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int updateOrder(Principal principal, int orderId, @RequestParam(value = "orderType", required = false) OrderType orderType, @RequestParam(value = "price", required = false) Double price, @RequestParam(value = "quantity", required = false) Integer quantity) {
		return os.updateOrder(orderId, orderType, price, quantity, Integer.parseInt(principal.getName()));
	}
}


