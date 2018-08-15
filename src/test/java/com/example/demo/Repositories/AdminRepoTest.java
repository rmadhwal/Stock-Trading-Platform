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
import com.cs.trading.Models.Sector;
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Services.SectorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	TraderRepository traderRepo;
	
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
		int res = traderRepo.createTrader(trader);
		assertEquals(latestId + 1, res);	
	}

	@Test
	public void listAllExistingTrader() {
		List<User> traderList = adminRepo.listAllTraders();
		assertEquals(initialListSize, traderList.size());
	}
	
	@Test
	public void getInformationAboutTrader() {
		//retrieve info about trader 2 
		User trader = adminRepo.getTrader(2).get(0);
		assertEquals(99887766,trader.getPhone());
		assertEquals("xyz.tan@gmail.com", trader.getEmail());
	}
	
	@Test
	public void deleteExistingTraderThenSuccess() {
		//delete trader with id 999 who has an order 
		int status = adminRepo.deleteExistingTrader(999);
		assertEquals(1, status);
	}
	
	@Test
	public void deleteTraderWithNoOrdersThenFail() {
		//delete trader with id 5 with no existing orders
		int status = adminRepo.deleteExistingTrader(5);
		assertEquals(0, status);
	}
	
}


