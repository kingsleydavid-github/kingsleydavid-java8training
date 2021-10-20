package com.kingsley.slearnoauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.slearnoauth.models.Order;
import com.kingsley.slearnoauth.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable Integer orderId) {
		
		Order od = orderService.getOrderById(orderId);
		if(od != null)
		{
			return ResponseEntity.ok(od);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
