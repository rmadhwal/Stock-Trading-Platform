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
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Services.SectorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	SectorService sectorService;
	
	private List<User> traderList;
	private int initialListSize; 
	
	@Before
	public void init() {
		traderList = adminRepo.listAllTraders();
		initialListSize = traderList.size(); 
	}
	
	@Test
	public void whenCreateNewTraderThenSuccess() {
		Trader trader = new Trader("Kevin", "Lim", "123456", 88776644, "kevin.lim@gmail.com");
		int latestId = adminRepo.findLatestId();
		int res = adminRepo.createTrader(trader);
		assertEquals(res, latestId + 1);	
	}
	
	@Test
	public void listAllExistingTrader() {
		List<User> traderList = adminRepo.listAllTraders();
		assertEquals(traderList.size(),initialListSize);
	}
	
	@Test
	public void getInformationAboutTrader() {
		//retrieve info about trader 2 
		User trader = adminRepo.getTrader(2).get(0);
		assertEquals(99887766,trader.getPhone());
		assertEquals("xyz.tan@gmail.com", trader.getEmail());
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

}


