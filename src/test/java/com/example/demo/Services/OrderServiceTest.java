package com.example.demo.Services;


import com.cs.trading.Models.*;
import com.cs.trading.Repositories.AdminRepository;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.Repositories.TransactionRepository;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.SectorService;
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
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    private List<Order> initialOrdersList;
    private int initialOrdersSize;


    @Before
    public void init() {
        initialOrdersList = orderService.listAllOrders();
        initialOrdersSize = initialOrdersList.size();
    }

    @Test
    public void whenCreateNewOrderThenSuccess() {
        int latestId = orderRepository.findLatestId();
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        assertEquals(latestId+1, orderId);
    };

    @Test
    public void cancelOrderCancelsOpenOrderWhenCalledByOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        assertEquals(1, orderService.cancelOrder(orderId, 0));
    }

    @Test
    public void cancelOrderDoesNotCancelsOpenOrderWhenCalledByNonOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        assertEquals(0, orderService.cancelOrder(orderId, 1));
    }

    @Test
    public void cancelOrderDoesNotCancelsNonOpenOrderWhenCalledByOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        assertEquals(1, orderService.cancelOrder(orderId, 0));
    }

    @Test
    public void updateOrderUpdatesOpenOrderWhenCalledByOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        int returnVal = orderService.updateOrder(orderId, OrderType.LIMIT, 1.23, 500, 0);
        Order order = orderService.findOrderById(orderId);
        assertEquals(1, returnVal);
        assertEquals(500, order.getQuantity());
    }

    @Test
    public void updateOrderDoesNotUpdateOpenOrderWhenCalledByNonOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.OPEN, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        int returnVal = orderService.updateOrder(orderId, OrderType.LIMIT, 1.23, 500, 1);
        Order order = orderService.findOrderById(orderId);
        assertEquals(0, returnVal);
        assertEquals(100, order.getQuantity());
    }

    @Test
    public void updateOrderDoesNotUpdateNonOpenOrderWhenCalledByOwner() {
        int orderId = orderService.placeOrder(OrderType.LIMIT, Status.FULFILLED, Side.BUY, new Date(), 0, 1.23, 100, "ATH", 0);
        int returnVal = orderService.updateOrder(orderId, OrderType.LIMIT, 1.23, 500, 0);
        Order order = orderService.findOrderById(orderId);
        assertEquals(0, returnVal);
        assertEquals(100, order.getQuantity());
    }
}



