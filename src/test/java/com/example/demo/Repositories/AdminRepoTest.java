package com.example.demo.Repositories;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import com.cs.trading.Services.TraderService;

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
	
	@Autowired
	TraderService traderService;
	
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
		assertEquals(99887766,trader.getPhone());
		assertEquals("xyz.tan@gmail.com", trader.getEmail());
	}
	
	@Test
	public void deleteExistingTraderThenFail() {
		//delete trader with id 999 who has an order 
		int status = adminRepo.deleteExistingTrader(999);
		assertEquals(2, status);
	}
	
	@Test
	public void deleteTraderWithNoOrdersThenSuccess() {
		//delete trader with id 7 with no existing orders
		int status = adminRepo.deleteExistingTrader(7);
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
		int[] expectedOpenId = {11,12, 19, 20};
		int[] openArr = new int[openedList.size()];
		for(int i = 0; i < openedList.size(); i++) {
			openArr[i] = openedList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedOpenId), hasItem(openArr));
		
		List<Order> fulfilledList = map.get(Status.FULFILLED);
		int[] expectedFulfilledId = {13, 14, 16, 17};
		int[] fulfillArr = new int[fulfilledList.size()];
		for(int i = 0; i < fulfilledList.size(); i++) {
			fulfillArr[i] = fulfilledList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedFulfilledId), hasItem(fulfillArr));
		
		List<Order> cancelledList = map.get(Status.CANCELLED);
		int[] expectedCancelledId = {15, 18};
		int[] cancelledArr = new int[cancelledList.size()];
		for(int i = 0; i < cancelledList.size(); i++) {
			cancelledArr[i] = cancelledList.get(i).getId();
		}
		assertThat(Arrays.asList(expectedCancelledId), hasItem(cancelledArr));
		
		int totalNumOfOrders = openedList.size() + fulfilledList.size() + cancelledList.size();
		assertEquals(initialOrderSize, totalNumOfOrders);
		
	}
	
	@Test
	public void findTopNTradersByTrades() {
		final int N = 5; 
		List<Trader> userList = traderRepo.findTopNTradersByNumber(N);
		
		int[] expectedTraderId = {2, 999, 5 , 4, 1};
		int[] expectedTraderNum = {3, 2, 2, 1, 1};
		//expected top 5 traders by id: 2, 999, 5, (6,4, or 1)
		int[] traderId = new int[N];
		int[] traderNum = new int[N];
		for(int i = 0; i < userList.size(); i++) {
			traderId[i] = userList.get(i).getId();
			traderNum[i] = userList.get(i).getNumTrades();
		}
//		assertArrayEquals(expectedTraderId, traderId);
		assertArrayEquals(expectedTraderNum, traderNum);
	}
	
	@Test
	public void findTopNTradersByVolume() {
		List<String> traderList = new ArrayList<>();
		final int N = 5; 
		//order: 2, 999, 5, 1 ,4
		traderList.add("2");
		traderList.add("999");
		traderList.add("5");
		traderList.add("1");
		traderList.add("4");
		List<String> list = traderService.findTopNTradersByVolume(N);
		assertEquals(traderList, list);
	}
	
}


