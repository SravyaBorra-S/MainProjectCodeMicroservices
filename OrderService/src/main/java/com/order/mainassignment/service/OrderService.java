package com.order.mainassignment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.mainassignment.dtos.CreateOrderDTO;
import com.order.mainassignment.dtos.CreatePaymentDTO;
import com.order.mainassignment.dtos.PaymentResponse;
import com.order.mainassignment.enums.OrderStatus;
import com.order.mainassignment.enums.PaymentStatus;
import com.order.mainassignment.feign.PaymentsAPI;
import com.order.mainassignment.models.Order;
import com.order.mainassignment.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentsAPI paymentsAPI;

	@Transactional
	public ResponseEntity<?> createOrder(CreateOrderDTO order) {
		System.out.println("enteredcreateOrder");
			Order o = new Order();
			o.setProductId(order.getProductId());
			o.setQuantity(order.getQuantity());
			o.setUserId(order.getUserId());
			o.setStatus(OrderStatus.PAYMENT_PENDING);
			System.out.println("o value"+ o);
			Order savedOrder = repository.save(o);
			System.out.println("savedOrder value---"+ savedOrder+ "savedOrder.getId()---"+savedOrder.getId()+"order.getPayment()p-----"+order.getPayment());
			PaymentResponse response = paymentsAPI.createPayment(new CreatePaymentDTO(savedOrder.getId(), order.getPayment()));
			System.out.println("response====="+response+"response.getStatus()------"+response.getStatus());
			if (response.getStatus() == PaymentStatus.ACCEPTED) {
				savedOrder.setStatus(OrderStatus.ACCEPTED);
			} else if (response.getStatus() == PaymentStatus.REJECTED) {
				savedOrder.setStatus(OrderStatus.PAYMENT_REJECTED);
			} else if (response.getStatus() == PaymentStatus.PENDING) {
				savedOrder.setStatus(OrderStatus.PAYMENT_PENDING);
			}
			savedOrder = repository.save(savedOrder);
			return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
		}

	public ResponseEntity<?> getOrderById(Long id) {
		try {
			Optional<Order> order = repository.findById(id);
			if (order.isEmpty()) {
				return new ResponseEntity<>("No order found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrdersForUser(Long id) {
		try {
			List<Order> orders = repository.findByUserId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getAllOrders() {
		try {
			List<Order> orders = repository.findAll();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
