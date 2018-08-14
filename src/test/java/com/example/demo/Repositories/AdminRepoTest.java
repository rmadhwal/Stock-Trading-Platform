package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepsitory;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminRepsitory.class)
public class AdminRepoTest {

//	@Autowired
	AdminRepsitory adminRepo;
	
	@Before
	public void init() {
		 adminRepo = new AdminRepsitory();
	}
	
	@Test
	public void whenCreateNewTraderThenSuccess() {
		int res = adminRepo.createTrader(new Trader(4, "Siva", "Lim", "123456", 88776644, "siva.lim@gmail.com", Role.ADMIN));
		assertEquals(res, 1);	
//		System.out.println(res);
	}

}


