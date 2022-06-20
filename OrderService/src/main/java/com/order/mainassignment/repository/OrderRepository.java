package com.order.mainassignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.mainassignment.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByUserId(Long userId);

}
