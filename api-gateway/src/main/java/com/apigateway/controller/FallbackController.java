package com.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	private static Logger logger = LoggerFactory.getLogger(FallbackController.class);
	
	@GetMapping("/user")
	public ResponseEntity<String> userFallback() {
		logger.error("User service is unavailable!");
		return new ResponseEntity<>("User service is unavailable!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/product")
	public ResponseEntity<String> productFallback() {
		logger.error("Post service is unavailable!");
		return new ResponseEntity<>("Post service is unavailable!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<String> cartFallback() {
		logger.error("Comment service is unavailable!");
		return new ResponseEntity<>("Comment service is unavailable!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/order")
	public ResponseEntity<String> orderFallback() {
		logger.error("Feed service is unavailable!");
		return new ResponseEntity<>("Feed service is unavailable!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/payment")
	public ResponseEntity<String> paymentFallback() {
		logger.error("Feed service is unavailable!");
		return new ResponseEntity<>("Feed service is unavailable!", HttpStatus.NOT_FOUND);
	}
}
