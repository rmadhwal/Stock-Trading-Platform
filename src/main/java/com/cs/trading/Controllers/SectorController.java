package com.cs.trading.Controllers;

import com.cs.trading.Models.Sector;
import com.cs.trading.Repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SectorController {
	
	@Autowired
	SectorRepository sr;
	
	@RequestMapping(value="/sectors", method=RequestMethod.GET)
	public List<Sector> returnAllSectors() {
		return sr.findAll();
	}
	
	@RequestMapping("/sectors/{id}")
	public Sector findSectorById(@PathVariable(value="id") int id) {
		return sr.findSectorById(id);
	}
}


