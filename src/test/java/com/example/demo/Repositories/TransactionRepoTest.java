package com.example.demo.Repositories;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Transaction;
import com.cs.trading.Repositories.TransactionRepository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
@Transactional
public class TransactionRepoTest {

	
	@Autowired 
	TransactionRepository ts;
	
	@Test
	public void whenQueryTransactionWhoseCompanyDoesntExistShouldFail() {
		Transaction transaction = ts.findLastTransactionBySymbol("sdfsdfsdf");
	}

}
