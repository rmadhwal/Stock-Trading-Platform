package com.cs.trading.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Repositories.CompanyRepository;

@Component
public class CompanyService {

	@Autowired
	CompanyRepository companyRepo;
	
	public List<Company> findAll(){
		return companyRepo.findAll();
	}
	
	public Company findCompanyBySymbol(String symbol) {
		return companyRepo.findCompanyBySymbol(symbol);
	}
	
	public List<Company> findCompanyBySector(Sector sector){
		return companyRepo.findCompanyBySector(sector);
	}
	
	public List<Company> findCompanyBySector(int sectorId){
		return companyRepo.findCompanyBySector(sectorId);
	}
}
