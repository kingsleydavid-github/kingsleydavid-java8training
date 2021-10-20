package com.kingsley.jpahibernate.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.jpahibernate.model.cart.Cart;
import com.kingsley.jpahibernate.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getCart(@PathVariable Integer id){
		Optional<Cart> cart = cartService.getCart(id);
		if(cart.isPresent()) {
			return ResponseEntity.ok(cart.get());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public @ResponseBody ResponseEntity<?> createCart(@RequestBody Cart cart){
		Optional<Cart> cObj = cartService.createCart(cart);
		if(cObj.isPresent()) {
			return ResponseEntity.ok(cObj.get());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

}
