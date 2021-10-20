package com.kingsley.orderhistory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.orderhistory.service.OrderHistoryService;

@RestController
@RequestMapping("/orderhistory")
public class OrderHistoryCtrlr {

	@Autowired
	OrderHistoryService orderHistorySvc;
	
	@GetMapping("/{userId}")
	public @ResponseBody ResponseEntity<?> getOrderHistory(HttpServletRequest request, @PathVariable String userId) 
	{
		return ResponseEntity.ok(orderHistorySvc.getOrderHistory(request, userId));
	}

}
