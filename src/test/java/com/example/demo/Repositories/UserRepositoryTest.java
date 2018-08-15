package com.example.demo.Repositories;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;

public class UserRepositoryTest {

	private Trader trader;
	
	@Before
	public void init() {
		trader = new Trader(1, "Siva", "Lim", "123456", 88776644, "siva.lim@gmail.com");
	}
	
	@Test
	public void WhenPlaceCorrectOrderThenSuccessful() {
		Order order = new Order();
		
	}

}
