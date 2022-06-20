package com.order.mainassignment.dtos;

import com.order.mainassignment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDTO {

	
	private Long orderId;
	private PaymentStatus status;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public CreatePaymentDTO(Long orderId, PaymentStatus status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}
	
}
