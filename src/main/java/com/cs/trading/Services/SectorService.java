package com.cs.trading.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Models.Sector;
import com.cs.trading.Repositories.SectorRepository;

@Component
public class SectorService {
	@Autowired
	SectorRepository sectorRepo;
	
	public  List<Sector> findAll(){
		return sectorRepo.findAll();
	}
	
}
