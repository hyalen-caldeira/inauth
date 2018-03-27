package com.avenuecode.orders.service;

import com.avenuecode.orders.Application;
import com.avenuecode.orders.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
public class OrderServiceTest {
    private static final String ORDER_ID = "1";
    private static final String NON_EXIST_ORDER_ID = "999";
    private static final int TOTAL_ORDERS = 5;

	@TestConfiguration
	static class OrderServiceTestContextConfiguration {
		@Bean
        public OrderService orderService() {
			return new OrderService();
		}
	}

	@Autowired
	private OrderService orderService;

	@Test
	public void testFetchAllOrders() {
		List<Order> result = orderService.listOrders();
		assertEquals(TOTAL_ORDERS, result.size());
	}

	@Test
	public void when_ANonExistOrderIdIsGiven_then_NullIsReturned() {
		Order result = orderService.getOrder(NON_EXIST_ORDER_ID);
		assertNull(result);
	}

	@Test
	public void when_AnExistOrderIdIsGiven_then_ValidOrderIsReturned() {
		Order result = orderService.getOrder(ORDER_ID);
		assertEquals(ORDER_ID, result.getOrderId());
	}
}