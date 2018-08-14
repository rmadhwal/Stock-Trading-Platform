package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepository adminRepo;
	
	
	@Test
	public void whenCreateNewTraderThenSuccessTest() {
		Trader trader = new Trader("Kevin", "Lim", "123456", 88776644, "kevin.lim@gmail.com", Role.valueOf("TRADER"));
//		List<User> traderList = adminRepo.listAllTraders();
		int latestId = adminRepo.findLatestId();
		int res = adminRepo.createTrader(trader);
		assertEquals(res, latestId + 1);	
	}
	
	
	@Test
	public void listAllExistingTraderTest() {
		List<User> traderList = adminRepo.listAllTraders();
		assertEquals(traderList.size(),3);
	}
	

}


