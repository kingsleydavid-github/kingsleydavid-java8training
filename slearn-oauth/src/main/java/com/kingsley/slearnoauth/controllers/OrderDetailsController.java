package com.kingsley.slearnoauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/orderDetails")
public class OrderDetailsController {
	
	@GetMapping("/getShippingAddress/{orderId}")
	public @ResponseBody ResponseEntity<String> getShippingAddress(@PathVariable String orderId)
	{
		return ResponseEntity.ok(orderId);
	}
}
