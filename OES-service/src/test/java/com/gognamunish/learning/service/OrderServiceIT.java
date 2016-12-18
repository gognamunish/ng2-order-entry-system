package com.gognamunish.learning.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gognamunish.learning.constant.Status;
import com.gognamunish.learning.model.OrderInfo;
import com.gognamunish.learning.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceIT {

	@Autowired
	private OrderService testObject;

	@Test
	public void saveAndGet_SingleOrder() {
		// given
		OrderInfo orderInfo = OrderInfo.getBuilder().withOrderId(null).withCurrency("USD").withQuantity(12)
				.withSymbol("GOOG").orderedBy("gognasaab").build();

		// when
		orderInfo = testObject.save(orderInfo);
		orderInfo = testObject.getOrderInfo(orderInfo.getOrderId());

		// then
		assertNotNull(orderInfo.getOrderId());
		assertEquals(orderInfo.getCurrency(), "USD");
	}

	@Test
	public void getAllOrders() {
		// when
		List<OrderInfo> allOrders = testObject.getAllOrders();

		// then
		assertTrue(allOrders.size() > 0);
	}

	@Test
	public void saveAndCancel_SingleOrder() {
		// given
		OrderInfo orderInfo = OrderInfo.getBuilder().withOrderId(null).withCurrency("USD").withQuantity(12)
				.withSymbol("GOOG").orderedBy("gognasaab").build();
		orderInfo = testObject.save(orderInfo);
		assertEquals(Status.DRAFT, orderInfo.getStatus());

		// when
		testObject.cancel(orderInfo.getOrderId());
		orderInfo = testObject.getOrderInfo(orderInfo.getOrderId());

		// then
		assertEquals(Status.CANCELLED, orderInfo.getStatus());
	}

	@Test
	public void delete_AllOrders() {
		// given
		OrderInfo orderInfo = OrderInfo.getBuilder().withOrderId(null).withCurrency("USD").withQuantity(12)
				.withSymbol("GOOG").orderedBy("gognasaab").build();
		orderInfo = testObject.save(orderInfo);
		assertEquals(Status.DRAFT, orderInfo.getStatus());

		// when
		testObject.delete(orderInfo.getOrderId());

		// then
		orderInfo = testObject.getOrderInfo(orderInfo.getOrderId());
		assertTrue(orderInfo == null);
	}

}
