package com.example.demo.Repositories;

<<<<<<< HEAD
import com.cs.trading.Models.*;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import com.cs.trading.Services.SectorService;
import com.cs.trading.UsersDbApplication;
import org.junit.Before;
=======
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;


>>>>>>> origin/master
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class TransactionRepoTest {

	@Autowired
	TransactionRepository transactionRepo;

	private List<Transaction> initialTransactionsList;
	private int initialTransactionsSize;


	@Before
	public void init() {
		initialTransactionsList = transactionRepo.findAll();
		initialTransactionsSize = initialTransactionsList.size();
	}

	@Test
	public void whenCreateNewTransactionThenSuccess() {
		assertEquals(1, transactionRepo.addTransaction(12,13,100,1.23,new Date()));
	};

	@Test
	public void listAllExistingTransactionsWorks() {
		transactionRepo.addTransaction(12,13,100,1.23,new Date());
		List<Transaction> transactionsList = transactionRepo.findAll();
		assertEquals(initialTransactionsSize + 1, transactionsList.size());
	}

    @Test
    public void whenQueryTransactionWhoseCompanyDoesntExistShouldFail() {
        Transaction transaction = ts.findLastTransactionBySymbol("sdfsdfsdf");
    }
}



