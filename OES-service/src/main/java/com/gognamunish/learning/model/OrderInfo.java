package com.gognamunish.learning.model;

import java.math.BigDecimal;
import java.util.Date;

import com.gognamunish.learning.constant.Status;
import com.gognamunish.learning.entity.Order;

public class OrderInfo {

	private Integer orderId;
	private String symbol;
	private BigDecimal price;
	private String currency;
	private Integer quantity;
	private Status status;
	private String createdBy;
	private Date createdOn;

	public OrderInfo() {
	}

	public OrderInfo(Integer orderId, String symbol, BigDecimal price, String currency, Integer quantity, Status status,
			String createdBy, Date createdOn) {
		super();
		this.orderId = orderId;
		this.symbol = symbol;
		this.price = price;
		this.currency = currency;
		this.quantity = quantity;
		this.status = status;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public static OrderInfoBuilder getBuilder() {
		return new OrderInfoBuilder();
	}

	public static class OrderInfoBuilder {
		private Integer orderId;
		private String symbol;
		private BigDecimal price;
		private String currency;
		private Integer quantity;
		private Status status = Status.DRAFT;
		private String createdBy;
		private Date createdOn;

		public OrderInfoBuilder withOrderId(Integer orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderInfoBuilder withSymbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public OrderInfoBuilder withPrice(BigDecimal price) {
			this.price = price;
			return this;
		}

		public OrderInfoBuilder withCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public OrderInfoBuilder withQuantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderInfoBuilder orderedBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public OrderInfoBuilder orderedDate(Date createdOn) {
			this.createdOn = createdOn;
			return this;
		}

		public OrderInfoBuilder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public OrderInfo build() {
			return new OrderInfo(orderId, symbol, price, currency, quantity, status, createdBy, createdOn);
		}
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Order toOrder() {
		return new Order(orderId, symbol, price, currency, quantity, status, createdBy, createdOn);
	}

}
