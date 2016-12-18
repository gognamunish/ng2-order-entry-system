package com.gognamunish.learning.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gognamunish.learning.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Serializable> {

	@Modifying
	@Query("update Order o set o.status = 'CANCELLED' where o.orderId = ?1")
	void cancelOrder(Serializable id);

}
