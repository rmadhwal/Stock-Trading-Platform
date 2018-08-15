package com.cs.trading.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Company;
import com.cs.trading.Services.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService cs;
	
	@RequestMapping(value="/companies", method=RequestMethod.GET)
	public List<Company> returnAllCompaniess() {
		return cs.findAll();
	}

	@RequestMapping("/companies/sort")
	public List<Company> findCompanyByLetters(@RequestParam(value="start_with") String letters) {
		return cs.findCompanyByLetters(letters);
	}
	
	@RequestMapping("/companies/{symbol}")
	public Company findCompanyById(@PathVariable(value="symbol") String symbol) {
		return cs.findCompanyBySymbol(symbol);
	}
	
}


