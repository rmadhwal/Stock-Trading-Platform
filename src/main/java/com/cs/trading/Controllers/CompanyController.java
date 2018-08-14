package com.cs.trading.Controllers;

import com.cs.trading.Models.Company;
import com.cs.trading.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyRepository cr;
	
	@RequestMapping(value="/companies", method=RequestMethod.GET)
	public List<Company> returnAllCompaniess() {
		return cr.findAll();
	}
	
	@RequestMapping("/companies/{symbol}")
	public Company findOrderById(@PathVariable(value="symbol") String symbol) {
		return cr.findCompanyBySymbol(symbol);
	}
}


