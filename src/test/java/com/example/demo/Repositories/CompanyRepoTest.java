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
import com.cs.trading.Models.User;
import com.cs.trading.Services.CompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class CompanyRepoTest {

	private Company companyWithoutOrders;
	private Company companyWithOrders;
	@Autowired CompanyService companyService;
	
	@Before
	public void init() {
		companyWithoutOrders = new Company("COMPANY_WITH_ORDERS","test",44);
		companyWithOrders = new Company("COMPANY_WITHOUT_ORDERS","test",44);
	}
	
	@Test
	public void whenCreateCompanyThenSuccess() {
		int res = companyService.createCompany(new Company("APPL","APPLE INC",0));
		assertEquals(1, res);	
	}
	
	@Test
	public void whenCreateDuplicateCompanyThenShouldReturnFail() {
		int res = companyService.createCompany(new Company("ATH","ATHENE",0));
		assertEquals(-1, res);	
	}
	
	@Test
	public void whenCreateCompanyWithInvalidSectorThenShouldReturnFail() {
		int res = companyService.createCompany(new Company("ATH","ATHENE",100));
		assertEquals(-2, res);	
	}

	@Test
	public void WhenDeleteCompanyWithoutOrdersThenShouldSuccess() {
		int res = companyService.deleteCompany(companyWithOrders);
		assertEquals(1, res);
	}
	
	@Test
	public void WhenDeleteCompanyWithOrdersThenShouldFail() {
		int res = companyService.deleteCompany(companyWithoutOrders);
		assertEquals(0, res);
	}
}
