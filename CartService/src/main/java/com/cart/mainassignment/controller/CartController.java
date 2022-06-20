package com.cart.mainassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.mainassignment.models.Cart;
import com.cart.mainassignment.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllCartItems(@PathVariable("id") Long userId) {
		return service.getAllCartItems(userId);
	}

		
	@PostMapping
	public ResponseEntity<?> updateItemToCart(@RequestBody Cart cart) {
		return service.updateItemToCart(cart);
	}
}
