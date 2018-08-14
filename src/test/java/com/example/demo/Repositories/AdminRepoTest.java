package com.example.demo.Repositories;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Company;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepsitory;
import com.cs.trading.Services.SectorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class AdminRepoTest {

	@Autowired
	AdminRepsitory adminRepo;
	
	@Autowired
	SectorService sectorService;
	
	//@Test
	public void whenCreateNewTraderThenSuccess() {
		int res = adminRepo.createTrader(new Trader(4, "Siva", "Lim", "123456", 88776644, "siva.lim@gmail.com", Role.ADMIN));
		assertEquals(res, 1);	
//		System.out.println(res);
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


