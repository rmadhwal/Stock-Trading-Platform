package com.cs.trading.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepository;

@Component
public class AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	SectorService sectorService;
	
	public int createTrader(Trader trader) {
		return adminRepo.createTrader(trader);
	}
	
	public int createCompany(Company company) {
		return adminRepo.createCompany(company);
	}
	
	
}
