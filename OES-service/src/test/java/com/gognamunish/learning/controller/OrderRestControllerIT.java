package com.gognamunish.learning.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.http.MediaType.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.gognamunish.learning.constant.Status;
import com.gognamunish.learning.model.OrderInfo;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderRestControllerIT {

	private static final String PASSWORD = "s3cr3t";
	private static final String USERNAME = "munish";
	@LocalServerPort
	private int port;

	@Before
	public void setupRestAssured() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	@Test
	public void oneBigFatTest() {

		createSingleOrder();
		getAllOrders();
		cancelOrder();
		deleteOrder();

	}

	private void createSingleOrder() {
		OrderInfo newPlacedOrder = OrderInfo.getBuilder().withPrice(new BigDecimal(100)).withCurrency("USD")
				.withQuantity(100).withStatus(Status.DRAFT).orderedBy("Gogna").orderedDate(new Date()).build();

		given().contentType(APPLICATION_JSON_VALUE).body(newPlacedOrder).auth().digest(USERNAME, PASSWORD)

				.when().post("/api/order/save")

				.then().statusCode(200).and().body("orderId", notNullValue()).and()
				.body("currency", equalTo(newPlacedOrder.getCurrency())).and()
				.body("status", equalTo(newPlacedOrder.getStatus().name())).and()
				.body("createdBy", equalTo(newPlacedOrder.getCreatedBy())).and()
				.body("createdOn", equalTo(newPlacedOrder.getCreatedOn().getTime())).and()
				.body("quantity", equalTo(newPlacedOrder.getQuantity()));
	}

	private void getAllOrders() {
		@SuppressWarnings("unchecked")
		List<OrderInfo> allOrders = given().contentType(APPLICATION_JSON_VALUE).auth().digest(USERNAME, PASSWORD)
		.when().get("/api/order/all").as(List.class);
		
		assertTrue(allOrders.size() > 0);
	}

	private void deleteOrder() {
		given().contentType(APPLICATION_JSON_VALUE).auth().digest(USERNAME, PASSWORD)
		.when().post("/api/order/delete/1")
		.then().body("response", equalTo("ok"));
	}

	private void cancelOrder() {
		given().contentType(APPLICATION_JSON_VALUE).auth().digest(USERNAME, PASSWORD)
		.when().post("/api/order/cancel/1")
		.then().body("response", equalTo("ok"));
	}

}
