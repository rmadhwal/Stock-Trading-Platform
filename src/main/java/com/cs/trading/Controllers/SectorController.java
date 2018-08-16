package com.cs.trading.Controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Services.CompanyService;
import com.cs.trading.Services.SectorService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SectorController {
	
	@Autowired
	SectorService ss;
	
	@Autowired
	CompanyService cs;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping(
			value="/sectors", 
			produces={MediaType.APPLICATION_JSON_VALUE},
			method=RequestMethod.GET)
	
	public List<Object> returnAllSectors() {
		List<Sector> sectors = ss.findAll();
		List<Object> objArray = new ArrayList<Object>();
		for(Sector sector: sectors) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Company> companies = cs.findCompanyBySector(sector.getId()); 

			map.put("number of companies", companies.size());
			map.put("description", sector.getDescription());
			map.put("name", sector.getName());
			map.put("id", sector.getId());
			
			objArray.add(map);
		}
		
		return objArray;
	}
	
	@RequestMapping(
			 value ="/sectors/{id}",
			 produces={MediaType.APPLICATION_JSON_VALUE}
	)
	public Object findSectorById(@PathVariable(value="id") int id) {
		
		Sector sector = ss.findSectorById(id);
		List<Object> objArray = new ArrayList<Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Company> companies = cs.findCompanyBySector(sector.getId()); 

		map.put("companies", companies);
		map.put("number of companies", companies.size());
		map.put("description", sector.getDescription());
		map.put("name", sector.getName());
		map.put("id", sector.getId());
			
		objArray.add(map);
		
		
		return objArray;
		
	}
	
	
	@RequestMapping(
			 value ="/update_sector",
			 consumes={MediaType.APPLICATION_JSON_VALUE},
			 method = RequestMethod.POST
	)
	public String updateSector(@RequestBody Sector sector) {
		int res = ss.updateSector(sector);
		return (res==1)? "SUCCESS": "FAIL";
	}
	
	@RequestMapping(
			 value ="/deleteSector",
			 consumes={MediaType.APPLICATION_JSON_VALUE}
	)
	public String deleteSector(@RequestBody Sector sector) {
		int res = ss.updateSector(sector);
		return (res==1)? "SUCCESS": "FAIL";
	}
	
	
}


