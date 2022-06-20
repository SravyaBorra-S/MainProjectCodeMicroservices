package com.order.mainassignment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentRecordAlreadyExistsException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PaymentRecordAlreadyExistsException(String msg) {
		super(msg);
	}

	public PaymentRecordAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
}
