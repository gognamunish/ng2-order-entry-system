package com.gognamunish.learning.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gognamunish.learning.constant.Status;

@Entity
@Table(name = "ORDER_DETAILS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private String symbol;
	private BigDecimal price;
	private String currency;
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String createdBy;
	private Date createdOn;

	public Order() {
	}

	public Order(Integer orderId, String symbol, BigDecimal price, String currency, Integer quantity, Status status,
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

}
