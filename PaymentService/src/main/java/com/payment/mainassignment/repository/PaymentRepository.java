package com.payment.mainassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.mainassignment.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
