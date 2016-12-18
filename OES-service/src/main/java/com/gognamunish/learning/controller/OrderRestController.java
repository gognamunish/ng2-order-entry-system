package com.gognamunish.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gognamunish.learning.model.OrderInfo;
import com.gognamunish.learning.service.OrderService;

@RestController
@RequestMapping(value = "/api/order", produces = { MediaType.APPLICATION_JSON_VALUE })
public class OrderRestController {

	private OrderService orderService;

	@Autowired
	private OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	List<OrderInfo> getAllOrders() {
		return this.orderService.getAllOrders();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	OrderInfo save(@RequestBody OrderInfo orderInfo) {
		return this.orderService.save(orderInfo);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	ResponseEntity<String> delete(@PathVariable("id") Integer orderId) {
		orderService.delete(orderId);
		return ResponseEntity.ok("{\"response\" : \"ok\"}");
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
	ResponseEntity<String> cancel(@PathVariable("id") Integer orderId) {
		orderService.cancel(orderId);
		return ResponseEntity.ok("{\"response\" : \"ok\"}");
	}

}
