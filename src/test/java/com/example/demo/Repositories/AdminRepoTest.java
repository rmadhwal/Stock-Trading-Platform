package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Company;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Sector;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Services.SectorService;

import com.cs.trading.Models.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	SectorService sectorService;
	
	private Sector invalidSector;
	private Sector validSector;
	private Sector sectorWithCompanies;
	private Sector sectorWithoutCompanies;
	private Company companyWithoutOrders;
	private Company companyWithOrders;
	
	@Before
	public void init() {
		validSector = new Sector(0,"invalid","n/a");
		invalidSector = new Sector(999,"invalid","n/a");
		sectorWithCompanies = new Sector(1,"invalid","n/a");
		sectorWithoutCompanies = new Sector(2,"invalid","n/a");
		companyWithoutOrders = new Company("COMPANY_WITH_ORDERS","test",44);
		companyWithOrders = new Company("COMPANY_WITHOUT_ORDERS","test",44);
	}
	
	@Test
	public void whenCreateNewTraderThenSuccessTest() {
		Trader trader = new Trader("Kevin", "Lim", "123456", 88776644, "kevin.lim@gmail.com");
		int latestId = adminRepo.findLatestId();
		int res = adminRepo.createTrader(trader);
		assertEquals(res, latestId + 1);	
	}

	@Test
	public void listAllExistingTraderTest() {
		List<User> traderList = adminRepo.listAllTraders();
		assertEquals(traderList.size(),3);
	}
	
	@Test
	public void whenCreateCompanyThenSuccess() {
		int res = adminRepo.createCompany(new Company("APPL","APPLE INC",0));
		assertEquals(1, res);	
	}
	
	@Test
	public void whenCreateDuplicateCompanyThenShouldReturnFail() {
		int res = adminRepo.createCompany(new Company("ATH","ATHENE",0));
		assertEquals(-1, res);	
	}
	
	@Test
	public void whenCreateCompanyWithInvalidSectorThenShouldReturnFail() {
		int res = adminRepo.createCompany(new Company("ATH","ATHENE",100));
		assertEquals(-2, res);	
	}
	
	@Test
	public void whenDeleteCompanyWithOutstandingOrderThenShouldReturnFail() {
		
	}
	@Test
	public void whenUpdateValidSectorThenShouldSuccess() {
		
		int res =  adminRepo.updateMarketSector(validSector);
		assertEquals(1, res);	
	}
	
	@Test
	public void WhenUpdateNonexistentSectorThenShouldReturnFail() {
		int res =  adminRepo.updateMarketSector(invalidSector);
		assertEquals(0, res);	
	}
	
	@Test
	public void WhenDeleteValidSectorWithoutCompaniesThenShouldSuccess() {
		int res = adminRepo.deleteMarketSector(sectorWithoutCompanies);
		assertEquals(1, res);	
	}
	
	@Test
	public void WhenDeleteValidSectorWithCompaniesThenShouldFail() {
		int res = adminRepo.deleteMarketSector(sectorWithCompanies);
		assertEquals(0, res);	
	}
	
	@Test
	public void WhenDeleteCompanyWithoutOrdersThenShouldSuccess() {
		int res = adminRepo.deleteCompany(companyWithOrders);
		assertEquals(1, res);
	}
	
	@Test
	public void WhenDeleteCompanyWithOrdersThenShouldFail() {
		int res = adminRepo.deleteCompany(companyWithoutOrders);
		assertEquals(0, res);
	}
}


