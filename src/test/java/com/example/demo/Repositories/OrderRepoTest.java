package com.example.demo.Repositories;

import com.cs.trading.Models.*;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Services.SectorService;
import com.cs.trading.UsersDbApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class OrderRepoTest {

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
	private List<Order> initialOrdersList;
	private int initialOrderSize;


	@Before
	public void init() {
		initialOrdersList = orderRepo.findAll();
		initialOrderSize = initialOrdersList.size();
	}

	@Test
	public void whenCreateNewOrderThenSuccess() {
		int latestId = orderRepo.findLatestId();
		int orderId = orderRepo.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
		assertEquals(1 + latestId, orderId);
	}

	@Test
	public void listAllExistingOrdersWorks() {
		orderRepo.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH1", 0);
		List<Order> ordersList = orderRepo.findAll();
		assertEquals(initialOrderSize + 1, ordersList.size());
	}

	@Test
	public void updateStatusChangesStatus() {
		int orderId = orderRepo.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH1", 0);
		orderRepo.changeOrderStatus(orderId, Status.CANCELLED);
		Order order = orderRepo.findOrderById(orderId);
		assertEquals(Status.CANCELLED, order.getStatus());
	}

	@Test
	public void updateOrderWorks() {
		int orderId = orderRepo.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH1", 0);
		orderRepo.updateOrder(orderId, OrderType.LIMIT, 1.23, 300);
		Order order = orderRepo.findOrderById(orderId);
		assertEquals(300, order.getQuantity());
	}
}



