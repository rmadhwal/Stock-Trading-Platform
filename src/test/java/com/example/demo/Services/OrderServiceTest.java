package com.example.demo.Services;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.UsersDbApplication;
import com.cs.trading.Models.Order;
import com.cs.trading.Repositories.OrderRepository;
import com.cs.trading.Services.OrderService;

import junit.framework.Assert;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService os;
	
	@Mock
    OrderRepository or;
	
	@Mock
	Order order;
	
	@Before
	public void init() {
		 
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		Order order = mock(Order.class);
		when(order.getId()).thenReturn(10);
		when(or.findOrderById(10)).thenReturn(order);
		Assert.assertEquals(os.findOrderById(10).getId(),10);
	}

}
