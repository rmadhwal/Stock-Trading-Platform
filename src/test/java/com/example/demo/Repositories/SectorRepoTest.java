package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Repositories.SectorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class SectorRepoTest {
	
		@Autowired
		SectorRepository sectorRepo;
		
		private Sector invalidSector;
		private Sector validSector;
		private Sector sectorWithCompanies;
		private Sector sectorWithoutCompanies;

		@Before
		public void init() {
			validSector = new Sector(0,"invalid","n/a");
			invalidSector = new Sector(999,"invalid","n/a");
			sectorWithCompanies = new Sector(1,"invalid","n/a"); 
			sectorWithoutCompanies = new Sector(2,"invalid","n/a");
		}
		
		
		@Test
		public void whenUpdateValidSectorThenShouldSuccess() {
			
			int res =  sectorRepo.updateMarketSector(validSector);
			assertEquals(1, res);	
		}
		
		@Test
		public void WhenUpdateNonexistentSectorThenShouldReturnFail() {
			int res =  sectorRepo.updateMarketSector(invalidSector);
			assertEquals(0, res);	
		}
		
		@Test
		public void WhenDeleteValidSectorWithoutCompaniesThenShouldSuccess() {
			int res = sectorRepo.deleteMarketSector(sectorWithoutCompanies);
			assertEquals(1, res);	
		}
		
		@Test
		public void WhenDeleteValidSectorWithCompaniesThenShouldFail() {
			int res = sectorRepo.deleteMarketSector(sectorWithCompanies);
			assertEquals(0, res);
		}
}
