package com.payment.mainassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payment.mainassignment.dtos.CreatePaymentDTO;
import com.payment.mainassignment.enums.PaymentStatus;
import com.payment.mainassignment.exceptions.InvalidOrderIdException;
import com.payment.mainassignment.exceptions.InvalidPaymentStatusException;
import com.payment.mainassignment.exceptions.PaymentRecordAlreadyExistsException;
import com.payment.mainassignment.exceptions.PaymentRecordNotFoundException;
import com.payment.mainassignment.models.Payment;
import com.payment.mainassignment.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	public ResponseEntity<?> getPaymentById(long id) {
		try {
			Optional<Payment> payment = repository.findById(id);
//			if (payment.isEmpty()) {
//				throw new PaymentRecordNotFoundException();
//			}
			return new ResponseEntity<>(payment.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> createPayment(CreatePaymentDTO payment) {
//			if (payment.getOrderId() == null) {
//				throw new InvalidOrderIdException();
//			}
		System.out.println("entered create payment in payment repo"+ payment);
			Optional<Payment> p = repository.findById(payment.getOrderId());
//			if (!p.isEmpty()) {
//				throw new PaymentRecordAlreadyExistsException();
//			}
			System.out.println("payment.getOrderId()"+ payment.getOrderId()+"payment.getStatus()"+payment.getStatus());
			Payment newPayment = new Payment();
			newPayment.setOrderId(payment.getOrderId());
			if (payment.getStatus() != null) {
				newPayment.setStatus(payment.getStatus());
			} else {
				newPayment.setStatus(PaymentStatus.PENDING);
			}
			newPayment = repository.save(newPayment);
			return new ResponseEntity<>(newPayment, HttpStatus.OK);
	}

	public ResponseEntity<?> updatePaymentById(Long id, PaymentStatus status) {
		Optional<Payment> payment = repository.findById(id);
//		if (payment.isEmpty()) {
//			throw new PaymentRecordNotFoundException();
//		}
		Payment oldPayment = payment.get();
//		if (status == null) {
//			throw new InvalidPaymentStatusException();
//		}
		oldPayment.setStatus(status);
		oldPayment = repository.save(oldPayment);
		return new ResponseEntity<>(oldPayment, HttpStatus.OK);
	}
}
