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
	public Sector findSectorById(int id) {
		return sectorRepo.findSectorById(id);
	}
	public int updateSector(Sector sector) {
		return sectorRepo.updateMarketSector(sector);
	}
	
	public int deleteSector(int sectorId) {
		return sectorRepo.deleteMarketSector(sectorId);
	}
	
	public int deleteSector(Sector sector) {
		return sectorRepo.deleteMarketSector(sector);
	}
	
	public Sector getSectorById(int id) {
		return sectorRepo.getSectorById(id);
	}
	
	
}
