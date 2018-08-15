package com.cs.trading.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.TraderRepository;

@Component
public class AdminService {
	
	@Autowired
	CompanyService companyService;
	@Autowired
	AdminRepository adminRepo;
	
	
	@Autowired
	TraderRepository traderRepo;
	
	
	@Autowired
	SectorService sectorService;
	
	public int createTrader(Trader trader) {
		return traderRepo.createTrader(trader);
	}
	
	public int createCompany(Company company) {
		return companyService.createCompany(company);
	}
	
	
}
