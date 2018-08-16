package com.example.demo.Services;


import com.cs.trading.Models.*;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.SectorService;
import com.cs.trading.Services.TransactionService;
import com.cs.trading.UsersDbApplication;
import org.junit.Before;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;


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
public class TransactionServiceTest {

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OrderService orderService;

    private List<Transaction> initialTransactionsList;
    private int initialTransactionsSize;


    @Before
    public void init() {
        initialTransactionsList = transactionService.listAllTransactions();
        initialTransactionsSize = initialTransactionsList.size();
    }

    @Test
    public void whenCreateNewTransactionThenSuccess() {
        assertEquals(1, transactionService.createNewTransaction(12,13,100,1.23,new Date()));
    };

    @Test
    public void listAllExistingTransactionsWorks() {
        transactionService.createNewTransaction(12,13,100,1.23,new Date());
        List<Transaction> transactionsList = transactionService.listAllTransactions();
        assertEquals(initialTransactionsSize + 1, transactionsList.size());
    }

    @Test
    public void listAllExistingTransactionsBySymbolWorks() {
        int oldATHTransactionsSize = transactionService.listAllTransactionsBySymbol("ATH").size();
        transactionService.createNewTransaction(orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0),orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.SELL, new Date(), 0, 1.23, 100, "ATH", 0),100,1.23,new Date());
        int currentATHTransactionsSize = transactionService.listAllTransactionsBySymbol("ATH").size();
        assertEquals(oldATHTransactionsSize + 1, currentATHTransactionsSize);
    }

    @Test
    public void listAllExistingTransactionsByTraderIdWorks() {
        int oldTrader0TransactionsSize = transactionService.listAllTransactionsByTraderId(0).size();
        transactionService.createNewTransaction(orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0),orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.SELL, new Date(), 0, 1.23, 100, "ATH", 1),100,1.23,new Date());
        int currentTrader1TransactionsSize = transactionService.listAllTransactionsByTraderId(0).size();
        assertEquals(oldTrader0TransactionsSize + 1, currentTrader1TransactionsSize);
    }
}



