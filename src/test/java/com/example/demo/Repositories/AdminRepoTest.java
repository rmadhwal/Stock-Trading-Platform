package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepsitory;

public class AdminRepoTest {

//	@Autowired
	AdminRepsitory adminRepo;
	
	@Before
	public void init() {
		 adminRepo = new AdminRepsitory();
	}
	
	@Test
	public void whenCreateNewTraderThenSuccess() {
		int res = adminRepo.createTrader(new Trader(4, "Siva", "Lim", "123456", 88776644, "siva.lim@gmail.com"));
		assertEquals(res, 1);	
//		System.out.println(res);
	}

}


