package com.cs.trading.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(value = "/companies/sort", method=RequestMethod.GET)
	public List<Company> findCompanyByLetters(@RequestParam(value="start_with") String letters) {
		return cs.findCompanyByLetters(letters);
	}
	
	@RequestMapping(value ="/companies/{symbol}", method=RequestMethod.GET)
	public Company findCompanyById(@PathVariable(value="symbol") String symbol) {
		return cs.findCompanyBySymbol(symbol);
	}
	
	@RequestMapping(value = "/companies/delete", method=RequestMethod.POST)
	public Object deleteCompanyById(@RequestBody Map<String, Object> form) {
		
		Object symbol = form.get("symbol");
		if(symbol==null) {
			return "please specify the company id";
		}else {
			int res = cs.deleteCompanyBySymbol(symbol.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", res);
			return map;
		}
	}
	@RequestMapping(value = "/companies/create_company", consumes = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	public Object createCompany(@RequestBody Company company) {
		
			int res = cs.createCompany(company);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", res);
			return map;
		
	}
	
}


