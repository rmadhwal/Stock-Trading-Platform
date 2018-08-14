package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepository adminRepo;
	
	
	@Test
	public void whenCreateNewTraderThenSuccess() {
		int res = adminRepo.createTrader(new Trader(4, "Kevin", "Lim", "123456", 88776644, "kevin.lim@gmail.com", Role.valueOf("TRADER")));
		assertEquals(res, 1);	
	}

}


