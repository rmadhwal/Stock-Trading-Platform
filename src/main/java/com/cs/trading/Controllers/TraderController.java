package com.cs.trading.Controllers;

import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
import com.cs.trading.Models.Status;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Services.TraderService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class TraderController {
	
	@Autowired
	TraderService ts;


	
	@RequestMapping(value="/placeLimitBuyOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitBuyOrder(Principal principal, double price, int quantity, String tickerSymbol) {
		return ts.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeLimitSellOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeLimitSellOrder(Principal principal, double price, int quantity, String tickerSymbol) {
		return ts.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeMarketBuyOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketBuyOrder(Principal principal, double price, int quantity, String tickerSymbol) {
		return ts.placeOrder(OrderType.MARKET, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/placeMarketSellOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public int placeMarketSellOrder(Principal principal, double price, int quantity, String tickerSymbol) {
		return ts.placeOrder(OrderType.MARKET, Status.OPEN, Side.BUY, new Date(), 0, price, quantity, tickerSymbol, Integer.parseInt(principal.getName()));
	}

	@RequestMapping(value="/deleteOrder/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public int deleteOrder(Principal principal, @PathVariable(value="id") int id) {
		return ts.deleteOrder(id, Integer.parseInt(principal.getName()));
	}

    @RequestMapping(value="/updateOrder", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
    public int updateOrder(Principal principal, int orderId, @RequestParam(value = "orderType", required = false) OrderType orderType, @RequestParam(value = "price", required = false) Double price, @RequestParam(value = "quantity", required = false) Integer quantity) {
        return ts.updateOrder(orderId, orderType, price, quantity, Integer.parseInt(principal.getName()));
    }

}


