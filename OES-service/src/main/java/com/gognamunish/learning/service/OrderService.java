package com.gognamunish.learning.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gognamunish.learning.entity.Order;
import com.gognamunish.learning.model.OrderInfo;
import com.gognamunish.learning.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	private OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public void delete(Serializable id) {
		repository.delete(id);
	}

	public void cancel(Serializable id) {
		repository.cancelOrder(id);
	}

	public OrderInfo getOrderInfo(Serializable id) {
		OrderInfo orderInfo = null;
		Order order = repository.findOne(id);

		if (order != null) {
			orderInfo = OrderInfo.getBuilder().withOrderId(order.getOrderId()).withCurrency(order.getCurrency())
					.withQuantity(order.getQuantity()).withSymbol(order.getSymbol()).withStatus(order.getStatus())
					.orderedBy(order.getCreatedBy()).orderedDate(order.getCreatedOn()).build();

		}

		return orderInfo;
	}

	public OrderInfo save(OrderInfo orderInfo) {
		Order orderToSave = orderInfo.toOrder();
		Order updatedOrder = repository.save(orderToSave);

		return OrderInfo.getBuilder().withOrderId(updatedOrder.getOrderId()).withPrice(updatedOrder.getPrice())
				.withCurrency(updatedOrder.getCurrency()).withQuantity(updatedOrder.getQuantity())
				.withSymbol(updatedOrder.getSymbol()).withStatus(updatedOrder.getStatus())
				.orderedBy(updatedOrder.getCreatedBy()).orderedDate(updatedOrder.getCreatedOn()).build();
	}

	public List<OrderInfo> getAllOrders() {
		List<OrderInfo> orders = new ArrayList<>();

		repository.findAll().forEach(order -> {
			OrderInfo orderInfo = OrderInfo.getBuilder().withOrderId(order.getOrderId()).withPrice(order.getPrice())
					.withCurrency(order.getCurrency()).withQuantity(order.getQuantity()).withSymbol(order.getSymbol())
					.withStatus(order.getStatus()).orderedBy(order.getCreatedBy()).orderedDate(order.getCreatedOn())
					.build();
			orders.add(orderInfo);
		});

		return orders;
	}

}
