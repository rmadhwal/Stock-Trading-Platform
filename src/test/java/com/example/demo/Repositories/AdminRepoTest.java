package com.example.demo.Repositories;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Status;
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.OrderRepository;
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
	OrderRepository orderRepo;
	
	@Autowired
	SectorService sectorService;
	
	private List<User> traderList;
	private int initialListSize; 
	private List<Order> orderList; 
	private int initialOrderSize; 
	
	
	@Before
	public void init() {
		traderList = adminRepo.listAllTraders();
		initialListSize = traderList.size(); 
		orderList = orderRepo.findAll();
		initialOrderSize = orderList.size(); 
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
		assertEquals(traderList.get(2).getPhone(),trader.getPhone());
		assertEquals(traderList.get(2).getEmail(), trader.getEmail());
	}
	
	@Test
	public void deleteExistingTraderThenFail() {
		//delete trader with id 999 who has an order 
		int status = adminRepo.deleteExistingTrader(999);
		assertEquals(2, status);
	}
	
	@Test
	public void deleteTraderWithNoOrdersThenSuccess() {
		//delete trader with id 5 with no existing orders
		int status = adminRepo.deleteExistingTrader(3);
		assertEquals(1, status);
	}
	
	@Test
	public void findLatestTimeFromTraderIdThenSuccess() {
		//delete trader with id 5 with no existing orders
		int traderId = 999; 
		String givenDate = "04-Jul-2018 12:08:56.235";
		Date date = adminRepo.retrieveLatestTimeTraderLastOrder(traderId);
		Date modifiedDate = new Date(); 
		 try {
			modifiedDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS").parse(givenDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		assertEquals(modifiedDate, date);
	}
	
	@Test
	public void findTotalNumberOfOrdersByStatus() {
		
		HashMap<Status, List<Order>> map = adminRepo.retrieveOrdersByStatus();
		
		List<Order> openedList = map.get(Status.OPEN);
		int[] expectedOpenId = {11,12};
		int[] openArr = new int[openedList.size()];
		for(int i = 0; i < openedList.size(); i++) {
			openArr[i] = openedList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedOpenId), hasItem(openArr));
		
		List<Order> fulfilledList = map.get(Status.FULFILLED);
		int[] expectedFulfilledId = {13, 14, 16};
		int[] fulfillArr = new int[fulfilledList.size()];
		for(int i = 0; i < fulfilledList.size(); i++) {
			fulfillArr[i] = fulfilledList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedFulfilledId), hasItem(fulfillArr));
		
		List<Order> cancelledList = map.get(Status.CANCELLED);
		int[] expectedCancelledId = {15};
		int[] cancelledArr = new int[cancelledList.size()];
		for(int i = 0; i < cancelledList.size(); i++) {
			cancelledArr[i] = cancelledList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedCancelledId), hasItem(cancelledArr));
		
		int totalNumOfOrders = openedList.size() + fulfilledList.size() + cancelledList.size();
		assertEquals(initialOrderSize, totalNumOfOrders);
		
	}
	
	
}


