package com.cs.trading.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepsitory;

@Component
public class AdminService {
	
	
	@Autowired
	AdminRepsitory adminRepo;
	
	public int createTrader(Trader trader) {
		
		return adminRepo.createTrader(trader);
	}
		
		
	
}
