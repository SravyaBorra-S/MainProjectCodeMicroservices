package com.order.mainassignment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.mainassignment.dtos.CreatePaymentDTO;
import com.order.mainassignment.dtos.PaymentResponse;

@FeignClient("PAYMENT-SERVICE")
public interface PaymentsAPI {

	@PostMapping("/payments")
	public PaymentResponse createPayment(@RequestBody CreatePaymentDTO createPayment);
	//System.out.println("gfghgjh");
	
}
