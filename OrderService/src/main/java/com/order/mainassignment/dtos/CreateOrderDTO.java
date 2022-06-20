package com.order.mainassignment.dtos;

import com.order.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
	private Long productId;
	private double quantity;
	private Long userId;
	private PaymentStatus payment;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public PaymentStatus getPayment() {
		return payment;
	}
	public void setPayment(PaymentStatus payment) {
		this.payment = payment;
	}
	
	
}
